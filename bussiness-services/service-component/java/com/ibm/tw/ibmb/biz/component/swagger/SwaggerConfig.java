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
package com.ibm.tw.ibmb.biz.component.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Configuration
@io.swagger.v3.oas.annotations.security.SecurityScheme(scheme = "oauth2", name = "oauth2", flows = @io.swagger.v3.oas.annotations.security.OAuthFlows(clientCredentials = @io.swagger.v3.oas.annotations.security.OAuthFlow(scopes = @io.swagger.v3.oas.annotations.security.OAuthScope(name = "biz"), tokenUrl = "${aibank.common.oauth2.access-token-uri:http://localhost:9999/oauth2/token}")), type = io.swagger.v3.oas.annotations.enums.SecuritySchemeType.OAUTH2)
@OpenAPIDefinition(info = @Info(title = "${spring.application.name}", version = "1.0.0"), security = @SecurityRequirement(name = "oauth2"))
public class SwaggerConfig {

    @Value("${spring.application.name:}")
    private String serviceName;

    @Value("${aibank.common.oauth2.access-token-uri:http://localhost:9999/oauth2/token}")
    private String accessTokenUri = "http://localhost:9999/oauth2/token";

    @Value("${aibank.common.api-base-uri:}")
    private String apiBaseUri;

    @Bean
    GroupedOpenApi api() {
        // @formatter:off
		return GroupedOpenApi.builder()
				.group(serviceName)
				.displayName(serviceName)
				.packagesToScan("com.tfb.aibank.biz")
				.pathsToMatch("/base/**", "/echo", apiBaseUri + "/**")
				.build();
		// @formatter:on
    }
}
