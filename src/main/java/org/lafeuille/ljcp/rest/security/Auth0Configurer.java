package org.lafeuille.ljcp.rest.security;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(prefix = "auth0", name = {"api-audience", "issuer"})
@Component
@Order(200)
public class Auth0Configurer implements HttpSecurityConfigurer {

    @Value("${auth0.api-audience}")
    private String apiAudience;

    @Value("${auth0.issuer}")
    private String issuer;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtWebSecurityConfigurer
                .forRS256(apiAudience, issuer)
                .configure(http);
    }
}
