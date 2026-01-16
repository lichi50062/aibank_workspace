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
package com.tfb.aibank.chl.component.ratecurrency;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

// @formatter:off
/**
 * @(#)RateCurrencyResource.java
 * 
 * <p>Description:溝通 service-foreign-exchange 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-rate-currency-to-foreign-exchange-service", url = "${aibank.common.feign.service-aibank-foreign-exchange-url:http://svc-biz-foreign-exchange:8080}")
@Component("RateCurrencyResource")
public interface RateCurrencyResource {

    /** 取得所有TABLE-RATE_CURRENCY資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/rate-currency/get", produces = MediaType.APPLICATION_JSON_VALUE)
    List<RateCurrency> getAllRateCurrency();

}
