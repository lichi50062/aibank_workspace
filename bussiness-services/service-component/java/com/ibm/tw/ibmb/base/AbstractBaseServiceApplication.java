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
package com.ibm.tw.ibmb.base;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.ibmb.component.feign.LoggingFeignRequestInterceptor;
import com.ibm.tw.ibmb.component.filter.LoggingFilter;

import feign.RequestInterceptor;

// @formatter:off
@RestController
@ComponentScan(basePackages = { 
        "com.ibm.tw.ibmb.component", 
        "com.ibm.tw.ibmb.biz.component", 
        "com.tfb.aibank.component", 
        "com.tfb.aibank.biz", 
        "com.tfb.aibank.integration", 
        "ineb.util" })
@EnableFeignClients(basePackages = { 
        "com.ibm.tw.ibmb.component", 
        "com.ibm.tw.ibmb.biz.component", 
        "com.tfb.aibank.component", 
        "com.tfb.aibank.biz"
})
@EnableConfigurationProperties
//@formatter:on
@EnableAsync
public abstract class AbstractBaseServiceApplication extends AbstractBaseApplication implements InitializingBean {

    @Value("${aibank.common.csp.enable:false}")
    private boolean enableCsp;

    @Autowired
    private MessageSource messageSource;

    @Configuration
    @EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
    @EnableWebSecurity
    public class ResourceServerConfig {

        // @formatter:off
        @Bean
        SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            // 這邊先設定 permitAll, 在 RestController 中才可用 @PreAuthorize / @PostAuthorize 處理
            http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // #4504 fortify Cross-Site Request Forgery csrf.disable() 一樣效果
                .csrf(csrf -> csrf.ignoringRequestMatchers("/**"))
                .authorizeHttpRequests(requests -> requests.anyRequest().permitAll())
                .oauth2ResourceServer(server -> server.jwt(Customizer.withDefaults()));
            
            if (enableCsp) {
                http.headers(headers -> headers
                    .contentSecurityPolicy(csp -> csp
                        .policyDirectives("default-src 'self';")
                    )
                );
            }
            return http.build();
        }
		// @formatter:on

    }

    @Bean
    FilterRegistrationBean<LoggingFilter> loggingFilterRegistrationBean() {
        FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setName("logging");
        LoggingFilter loggingFilter = new LoggingFilter();
        registrationBean.setFilter(loggingFilter);
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    RequestInterceptor loggingFeignRequestInterceptor() {
        return new LoggingFeignRequestInterceptor();
    }

    @Bean
    ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        I18NUtils.setMessageSource(messageSource);
    }
}
