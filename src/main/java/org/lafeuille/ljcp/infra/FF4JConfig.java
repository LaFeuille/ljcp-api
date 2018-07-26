package org.lafeuille.ljcp.infra;

import org.ff4j.FF4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.ff4j.jmx")
public class FF4JConfig {

    @Bean
    FF4j getFF4j() {
        return new FF4j("ff4j.xml");
    }
}
