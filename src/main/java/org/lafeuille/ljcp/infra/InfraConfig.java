package org.lafeuille.ljcp.infra;

import com.github.javafaker.Faker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class InfraConfig {

    @Bean
    @ConditionalOnClass(Faker.class)
    Faker faker() {
        return new Faker();
    }

    @Bean
    Clock clock() {
        return Clock.systemUTC();
    }

}
