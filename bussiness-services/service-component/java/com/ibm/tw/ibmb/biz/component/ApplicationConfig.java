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
package com.ibm.tw.ibmb.biz.component;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.chl.component.i18n.I18nResourceBundleMessageSource;

@Configuration
public class ApplicationConfig {

    private static final IBLog logger = IBLog.getLog(ApplicationConfig.class);

    @Bean
    MessageSource messageSource() {
        logger.info("loading multiple i18n messages");
        ReloadableResourceBundleMessageSource messageSource = new I18nResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/messages", "classpath:/shared/aibank_messages");

        return messageSource;
    }
}
