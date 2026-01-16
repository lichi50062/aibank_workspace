/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.component.ipofund;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

// @formatter:off
/**
 * @(#)FundIpoResource.java
 * 
 * <p>Description:溝通 service-aibank-mutual-fund 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/04/10, Rong Gang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-fund-ipo-to-mutual-fund-service", url = "${aibank.common.feign.service-aibank-mutual-fund-url:http://svc-biz-mutual-fund:8080}")
@Component("FundIpoResource")
public interface FundIpoResource {

    /** 查詢募集中基金資訊(單筆) */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/mutual-funds/ipo/{fundCode}/get", produces = MediaType.APPLICATION_JSON_VALUE)
    FundIpo getFundIpo(@PathVariable("fundCode") String fundCode);

    /** 查詢募集中基金資訊(所有)" */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/mutual-funds/all-fund-ipo", produces = { MediaType.APPLICATION_JSON_VALUE })
    List<FundIpo> getAllFundIpo();
}
