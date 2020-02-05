package org.lafeuille.ljcp.rest;

import org.junit.jupiter.api.Test;
import org.lafeuille.ljcp.infra.SecurityTestExecutionListeners;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assume.assumeTrue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@SecurityTestExecutionListeners
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "example.com", uriPort = 443)
class RestDocumentationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Test
    void GET_actuator_health() throws Exception {
        mockMvc.perform(get("/actuator/health")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"))
                .andDo(document("actuator/health/GET",
                        preprocessResponse(prettyPrint())
                ));
    }

    @Test
    void GET_actuator_info() throws Exception {
        assumeTrue(context.getResource("classpath:META-INF/build-info.properties").exists());

        mockMvc.perform(get("/actuator/info")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.app.description").isNotEmpty())
                .andExpect(jsonPath("$.build.artifact").value("ljcp-api"))
                .andExpect(jsonPath("$.build.name").value("ljcp-api"))
                .andExpect(jsonPath("$.build.group").value("org.lafeuille"))
                .andDo(document("actuator/info/GET",
                        preprocessResponse(prettyPrint())
                ));
    }

    @Test
    @WithMockUser
    void GET_events() throws Exception {
        mockMvc.perform(get("/events")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.events").isEmpty())
                .andExpect(jsonPath("$.page.size").value(20))
                .andDo(document("events/GET",
                        preprocessResponse(prettyPrint())
                ));
    }

    @Test
    @WithMockUser
    void POST_events() throws Exception {
        mockMvc.perform(post("/events")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"My birthday\", \"description\":\"Party for my birthday\", \"startDate\":\"1982-08-07\", \"startTime\":\"18:30:00\"}"))
                .andExpect(status().is(201))
                .andExpect(jsonPath("title").value("My birthday"))
                .andExpect(jsonPath("description").value("Party for my birthday"))
                .andExpect(jsonPath("startDate").value("1982-08-07"))
                .andExpect(jsonPath("startTime").value("18:30:00"))
                .andDo(document("events/POST",
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("id").description("Event identifier"),
                                fieldWithPath("createdDate").description("Event creation date"),
                                fieldWithPath("title").description("Event title"),
                                fieldWithPath("description").description("Event description"),
                                fieldWithPath("startDate").description("Event start date"),
                                fieldWithPath("startTime").description("Event start time"),
                                subsectionWithPath("_links").description("<<events-links, Links>> to other resources")
                        )));
    }

}
