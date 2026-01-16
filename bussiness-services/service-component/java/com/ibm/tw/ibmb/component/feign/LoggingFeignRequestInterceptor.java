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

import java.util.Map;

import org.slf4j.MDC;

import feign.RequestInterceptor;
import feign.RequestTemplate;

// @formatter:off
/**
 * @(#)LoggingFeignRequestInterceptor.java
 * 
 * <p>Description:logging feign client interceptor</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/11, Horance Chou	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class LoggingFeignRequestInterceptor implements RequestInterceptor {
    private static String HEADER_FORMAT = "x-aibank-%s";

    @Override
    public void apply(RequestTemplate template) {
        // 將 MDC 中的值，做為 HTTP Header 傳送至 biz service
        Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();
        if (copyOfContextMap != null) {
            copyOfContextMap.forEach((k, v) -> {
                template.header(String.format(HEADER_FORMAT, k), v);
            });
        }
    }

}
