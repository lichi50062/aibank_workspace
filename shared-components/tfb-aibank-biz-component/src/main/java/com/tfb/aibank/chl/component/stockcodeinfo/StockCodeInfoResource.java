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
package com.tfb.aibank.chl.component.stockcodeinfo;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

// @formatter:off
/**
 * @(#)StockCodeInfoResource.java
 * 
 * <p>Description:溝通 service-aibank-information 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/02, Jojo Wei
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-stock-code-info-to-information-service", url = "${aibank.common.feign.service-aibank-information-url:http://svc-biz-information:8080}")
@Component("StockCodeInfoResource")
public interface StockCodeInfoResource {

    /** 取得所有海外ETF/海外股票代碼資訊 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/stock-code-info/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StockCodeInfo> getAllStockCodeInfoData();

}
