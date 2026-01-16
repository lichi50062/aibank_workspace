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
package com.tfb.aibank.chl.component.twdinterestrate;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

// @formatter:off
/**
 * @(#)TwdInterestRateResource.java
 * 
 * <p>Description:溝通 service-aibank-information 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/31, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-twd-interest-rate-to-deposit-service", url = "${aibank.common.feign.service-aibank-deposit-url:http://svc-biz-deposit:8080}")
@Component("TwdInterestRateResource")
public interface TwdInterestRateResource {

    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/twd-interest-rate/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<TwdInterestRate> getAllTwdInterestRateData();

}
