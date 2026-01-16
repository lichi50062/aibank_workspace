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
package com.ibm.tw.ibmb.component.feign;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.cloud.openfeign.security.OAuth2AccessTokenInterceptor;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;

import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.optionals.OptionalDecoder;

@Configuration
public class FeignClientConfig {

    private static final String FEIGN_DATETIME_FORMAT = "yyyyMMddHHmmssSSS";

    @Value("${aibank.common.oauth2.registration-id:biz}")
    private String regisrationId;

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;
    
    @Bean
    RequestInterceptor oauth2FeignRequestInterceptor(OAuth2AuthorizedClientManager authorizedClientManager) {
        return new OAuth2AccessTokenInterceptor(regisrationId, authorizedClientManager);
    }

    @Bean
    OAuth2AuthorizedClientManager feignOAuth2AuthorizedClientManager(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
        return new AuthorizedClientServiceOAuth2AuthorizedClientManager(clientRegistrationRepository, oAuth2AuthorizedClientService);
    }

    @Bean
    FeignFormatterRegistrar localDateFeignFormatterRegistrar() {
        return formatterRegistry -> formatterRegistry.addFormatter(new Formatter<Date>() {

            @Override
            public Date parse(String text, Locale locale) throws ParseException {
                return DateUtils.parseDate(text, FEIGN_DATETIME_FORMAT);
            }

            @Override
            public String print(Date object, Locale locale) {
                return DateFormatUtils.format(object, FEIGN_DATETIME_FORMAT);
            }
        });
    }

    @Bean
    Decoder baseDecoder(ObjectProvider<HttpMessageConverterCustomizer> customizers) {
        return new BaseServiceResponseDecoder(new OptionalDecoder(new ResponseEntityDecoder(new SpringDecoder(this.messageConverters, customizers))));
    }
}
