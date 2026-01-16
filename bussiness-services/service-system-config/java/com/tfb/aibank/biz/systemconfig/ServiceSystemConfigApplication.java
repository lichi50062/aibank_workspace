package com.tfb.aibank.biz.systemconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.ibm.tw.ibmb.base.AbstractBaseServiceApplication;

@SpringBootApplication
public class ServiceSystemConfigApplication extends AbstractBaseServiceApplication {

	private static Class<ServiceSystemConfigApplication> applicationClass = ServiceSystemConfigApplication.class;
	   
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

	public static void main(String[] args) {
		SpringApplication.run(ServiceSystemConfigApplication.class, args);
	}

}
