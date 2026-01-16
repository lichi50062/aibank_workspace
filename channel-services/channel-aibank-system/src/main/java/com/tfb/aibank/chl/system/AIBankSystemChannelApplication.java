package com.tfb.aibank.chl.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.ibm.tw.ibmb.base.AbstractBaseChannelApplication;

public class AIBankSystemChannelApplication extends AbstractBaseChannelApplication {

    private static Class<AIBankSystemChannelApplication> applicationClass = AIBankSystemChannelApplication.class;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    public static void main(String[] args) {
        System.setProperty("aibank.channel.session-cleaner.enabled", "true");
        SpringApplication.run(AIBankSystemChannelApplication.class, args);
    }

}
