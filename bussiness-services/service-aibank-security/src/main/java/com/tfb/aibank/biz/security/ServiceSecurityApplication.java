package com.tfb.aibank.biz.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.ibm.tw.ibmb.base.AbstractBaseServiceApplication;

@SpringBootApplication
public class ServiceSecurityApplication extends AbstractBaseServiceApplication {

	private static Class<ServiceSecurityApplication> applicationClass = ServiceSecurityApplication.class;
	   
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

	public static void main(String[] args) {
		SpringApplication.run(ServiceSecurityApplication.class, args);
	}

}
