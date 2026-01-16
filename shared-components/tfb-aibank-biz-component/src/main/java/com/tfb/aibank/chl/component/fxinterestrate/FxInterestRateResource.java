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
package com.tfb.aibank.chl.component.fxinterestrate;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

// @formatter:off
/**
 * @(#)FxInterestRateResource.java
 * 
 * <p>Description:溝通 service-aibank-deposit 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-fx-interest-rate-to-deposit-service", url = "${aibank.common.feign.service-aibank-deposit-url:http://svc-biz-deposit:8080}")
@Component("FxInterestRateResource")
public interface FxInterestRateResource {

    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/fx-interest-rate/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<FxInterestRate> getAllFxInterestRateData();

}
