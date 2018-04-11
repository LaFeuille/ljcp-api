package org.lafeuille.ljcp.rest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assume.assumeTrue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestDocumentationTest {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation)
                        .uris()
                        .withScheme("https")
                        .withHost("example.com")
                        .withPort(443))
                .build();
    }

    @Test
    public void GET_actuator_health() throws Exception {
        mockMvc.perform(get("/actuator/health")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"))
                .andDo(document("actuator/health/GET"));
    }

    @Test
    public void GET_actuator_info() throws Exception {
        assumeTrue(context.getResource("META-INF/build-info.properties").exists());

        mockMvc.perform(get("/actuator/info")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.app.description").isNotEmpty())
                .andExpect(jsonPath("$.build.artifact").value("ljcp-api"))
                .andExpect(jsonPath("$.build.name").value("ljcp-api"))
                .andExpect(jsonPath("$.build.group").value("org.lafeuille"))
                .andDo(document("actuator/info/GET"));
    }

    @Test
    public void GET_events() throws Exception {
        mockMvc.perform(get("/events")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.events").isEmpty())
                .andExpect(jsonPath("$.page.size").value(20))
                .andDo(document("events/GET"));
    }

    @Test
    public void POST_events() throws Exception {
        mockMvc.perform(post("/events")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"My birthday\", \"description\":\"Party for my birthday\", \"startDate\":\"1982-08-07\", \"startTime\":\"18:30:00\"}"))
                .andExpect(status().is(201))
                .andExpect(jsonPath("title").value("My birthday"))
                .andExpect(jsonPath("description").value("Party for my birthday"))
                .andExpect(jsonPath("startDate").value("1982-08-07"))
                .andExpect(jsonPath("startTime").value("18:30:00"))
                .andDo(document("events/POST"));
    }

}
