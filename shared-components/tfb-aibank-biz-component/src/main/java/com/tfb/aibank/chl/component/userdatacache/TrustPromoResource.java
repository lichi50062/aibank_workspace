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
package com.tfb.aibank.chl.component.userdatacache;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.aibank.chl.component.userdatacache.model.EB032280Res;
import com.tfb.aibank.chl.component.userdatacache.model.TrustPromoLog;

// @formatter:off
/**
 * @(#)FundResource.java
 * 
 * <p>Description:溝通 service-aibank-mutual-fund 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/02, Leiley
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-trust-promo-service", url = "${aibank.common.feign.service-aibank-mutual-fund-url:http://svc-biz-mutual-fund:8080}")
public interface TrustPromoResource {
    /**
     * 特定金錢信託推介電文
     */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/mutual-funds/trust-info/get", produces = MediaType.APPLICATION_JSON_VALUE)
    EB032280Res sendEB032280(@RequestHeader("custId") String custId, @RequestParam("func") String func);

    /** 寫特定金錢信託推介同意書簽署Log */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/mutual-funds/trust-promo-log/save", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean saveTrustPromoLog(@RequestBody TrustPromoLog request);
}
