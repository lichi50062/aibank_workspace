package com.tfb.aibank.biz.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.ibm.tw.ibmb.base.AbstractBaseServiceApplication;

@SpringBootApplication
public class ServiceUserApplication extends AbstractBaseServiceApplication {

    private static Class<ServiceUserApplication> applicationClass = ServiceUserApplication.class;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    public static void main(String[] args) {
        System.getProperties().put("spring.datasource.piblog.enabled", "true");

        SpringApplication.run(ServiceUserApplication.class, args);
    }

}
