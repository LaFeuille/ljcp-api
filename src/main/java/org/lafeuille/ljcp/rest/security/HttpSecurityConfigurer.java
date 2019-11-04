package org.lafeuille.ljcp.rest.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@FunctionalInterface
public interface HttpSecurityConfigurer {

    void configure(HttpSecurity http) throws Exception;
}
