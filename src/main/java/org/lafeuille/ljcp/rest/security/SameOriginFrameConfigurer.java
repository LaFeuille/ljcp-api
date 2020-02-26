package org.lafeuille.ljcp.rest.security;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

@Component
@Order(500)
public class SameOriginFrameConfigurer implements HttpSecurityConfigurer {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin();
    }
}
