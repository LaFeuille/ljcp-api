package org.lafeuille.ljcp.rest.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(Auth0Configurer.class)
@Order(300)
public class BasicAuthConfigurer implements HttpSecurityConfigurer {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
    }
}
