package com.tfb.aibank.chl.creditcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.ibm.tw.ibmb.base.AbstractBaseChannelApplication;

//@formatter:off
/**
 * @(#)AIBankCreditcardChannelApplication.java
 * 
 * <p>Description:AIBankCreditcardChannelApplication</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/24, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class AIBankCreditcardChannelApplication extends AbstractBaseChannelApplication {

    private static Class<AIBankCreditcardChannelApplication> applicationClass = AIBankCreditcardChannelApplication.class;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    public static void main(String[] args) {
        SpringApplication.run(AIBankCreditcardChannelApplication.class, args);
    }

}
