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
package com.tfb.aibank.chl.component.fxtranscurrencydiscount;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

// @formatter:off
/**
 * @(#)FxTransCurrencyDiscountResource.java
 * 
 * <p>Description:溝通 service-aibank-foreign-exchange 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-fx-trans-currency-discount-to-foreign-exchange-service", url = "${aibank.common.feign.service-aibank-foreign-exchange-url:http://svc-biz-foreign-exchange:8080}")
@Component("FxTransCurrencyDiscountResource")
public interface FxTransCurrencyDiscountResource {

    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/fx-trans-discount/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<FxTransCurrencyDiscount> getFxTransCurrencyDiscounts();

}
