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
package com.tfb.aibank.component.crypto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

// @formatter:off
/**
 * @(#)HSMTxCodeResource.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Horance Chou
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-crypto-to-system-config-service", url = "${aibank.common.feign.service-aibank-system-config-url:http://svc-biz-system-config:8080}")
public interface HSMTxCodeResource {

    @GetMapping(value = "${aibank.common.api-base-uri:}/v1/nextHSMTxCode", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getHSMTxCode();

}
