package com.ibm.tw.ibmb.component.feign;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;

import feign.codec.Decoder;
import feign.optionals.OptionalDecoder;

public class IxteinPushFeignClientConfig {
	
    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    Decoder baseDecoder(ObjectProvider<HttpMessageConverterCustomizer> customizers) {
        return new OptionalDecoder(new ResponseEntityDecoder(new SpringDecoder(this.messageConverters, customizers)));
    }

}
