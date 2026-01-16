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
package com.tfb.aibank.component.fundcheckdata;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;

// @formatter:off
/**
 * @(#)FundCheckDataResource.java
 * <pre>
 * Description:溝通 service-mutual-fund 的介面
 *
 * Modify History:
 * v1.0, 2023/05/27, MP, 初版
 * </pre>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-fund-check-data-to-mutual-fund-service", url = "${aibank.common.feign.service-aibank-mutual-fund-url:http://svc-biz-mutual-fund:8080}")
@Component("FundCheckDataResource")
public interface FundCheckDataResource {

    @Operation(summary = "取得開放申購的基金代碼 - DBU")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/mutualfund/fund-open-buy-dbu/get", produces = { MediaType.APPLICATION_JSON_VALUE })
    List<FundInfo> queryFundOpenPurchaseDBU(@RequestParam("locale") String locale);

    @Operation(summary = "取得開放申購的基金代碼 - OBU")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/mutualfund/fund-open-buy-obu/get", produces = { MediaType.APPLICATION_JSON_VALUE })
    List<FundInfo> queryFundOpenPurchaseOBU(@RequestParam("locale") String locale);

    @Operation(summary = "基金申購最低限額資料")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/mutualfund/fund-limit-amt/get", produces = { MediaType.APPLICATION_JSON_VALUE })
    List<FundLimitAmount> getAllFundLimitAmounts();

    @Operation(summary = "取得基金配息頻率與基金代號Map")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/funds/fund-dividen-map/get")
    Map<String, List<String>> getFundDividendFreqsMap();
}
