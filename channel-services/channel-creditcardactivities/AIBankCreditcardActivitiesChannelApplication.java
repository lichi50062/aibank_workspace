package com.tfb.aibank.chl.creditcardactivities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.ibm.tw.ibmb.base.AbstractBaseChannelApplication;

//@formatter:off
/**
 * @(#)AIBankCreditcardActivitiesChannelApplication.java
 * 
 * <p>Description:AIBankCreditcardActivitiesChannelApplication</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/12/14, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class AIBankCreditcardActivitiesChannelApplication extends AbstractBaseChannelApplication {

    private static Class<AIBankCreditcardActivitiesChannelApplication> applicationClass = AIBankCreditcardActivitiesChannelApplication.class;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    public static void main(String[] args) {
        System.setProperty("aibank.channel.session-cleaner.enabled", "true");
        SpringApplication.run(AIBankCreditcardActivitiesChannelApplication.class, args);
    }

}
