package com.ibm.tw.servers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class OauthApplication extends SpringBootServletInitializer {

    private static Class<OauthApplication> applicationClass = OauthApplication.class;

    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

}
