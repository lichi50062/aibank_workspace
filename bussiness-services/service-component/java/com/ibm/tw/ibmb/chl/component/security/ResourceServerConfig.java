/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.ibm.tw.ibmb.chl.component.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;

@Configuration(proxyBeanMethods = false)
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@EnableWebSecurity
public class ResourceServerConfig {

    @Value("${aibank.common.csp.enable:false}")
    private boolean enableCsp;

    @SuppressWarnings("removal")
    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception { // need to throw all Exceptions
        // 這邊先設定 permitAll, 在 RestController 中才可用 @PreAuthorize / @PostAuthorize 處理
        // @formatter:off
		http.sessionManagement().sessionAuthenticationStrategy(new NullAuthenticatedSessionStrategy())
			.sessionCreationPolicy(SessionCreationPolicy.NEVER)
			// #4504 fortify Cross-Site Request Forgery 與 csrf.disable() 一樣效果
			.and().csrf(csrf -> csrf.ignoringRequestMatchers("/**"))
			.authorizeHttpRequests().anyRequest().permitAll().and()
			.formLogin().disable()
			.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
		
		 if (enableCsp) {
             http.headers(headers -> headers
                 .contentSecurityPolicy(csp -> csp
                     .policyDirectives("default-src 'self';")
                 )
             );
         }
		// @formatter:on
        return http.build();
    }
}
