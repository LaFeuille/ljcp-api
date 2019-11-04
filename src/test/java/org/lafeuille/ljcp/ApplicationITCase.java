package org.lafeuille.ljcp;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lafeuille.ljcp.domain.event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = ApplicationITCase.Initializer.class)
public class ApplicationITCase {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer();

    @Autowired
    private EventRepository repository;

    @Test
    public void application() {
        var events = repository.findAll();
        assertThat(events).hasSizeGreaterThan(10);
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext context) {
            TestPropertyValues.of(
                    "ljcp.event.filler.enabled=" + true,
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword(),
                    "spring.jpa.generate-ddl=" + true,
                    "spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=" + true
            ).applyTo(context.getEnvironment());
        }
    }

}
