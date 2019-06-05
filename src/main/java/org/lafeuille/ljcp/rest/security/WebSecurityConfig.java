package org.lafeuille.ljcp.rest.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.List;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final List<HttpSecurityConfigurer> configurers;

    public WebSecurityConfig(List<HttpSecurityConfigurer> configurers) {
        this.configurers = configurers;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        for (var configurer : configurers) {
            configurer.configure(http);
        }
    }
}
