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
package com.ibm.tw.ibmb.chl.component.error;

import com.ibm.tw.commons.decoder.ServiceErrorDecoder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.codec.ErrorDecoder;
import feign.gson.GsonDecoder;

@Configuration
public class ServiceErrorDecoderConfig {

    @Bean
    ErrorDecoder decoder() {
        return new ServiceErrorDecoder(new GsonDecoder());
    }
}
