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
package com.tfb.aibank.chl.component.paytype;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

// @formatter:off
/**
 * @(#)PayTypeCacheResource.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/27, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "common-paytype--to-payment-service", url = "${aibank.common.feign.service-aibank-payment-url:http://svc-biz-payment:8080}")
public interface PayItemCacheResource {

    /** 取得所有繳費項目 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/payment-items/get", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<PayItems> getPaymentItems();

}
