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
package com.tfb.aibank.chl.system.resource;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.aibank.chl.system.resource.dto.CoolingPeriodResponse;
import com.tfb.aibank.chl.system.resource.dto.KycCountsResponse;
import com.tfb.aibank.chl.system.resource.dto.KycRiskLevelModel;
import com.tfb.aibank.chl.system.resource.dto.PeopleSoftRes;

// @formatter:off
/**
 * @(#)InvestResource.java
 * 
 * <p>Description: 溝通 service-aibank-invest 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/01, Jackie Chien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-chl-invest-to-invest-service", url = "${aibank.common.feign.service-aibank-invest-url:http://svc-biz-loan:8080}")
public interface InvestResource {

    /** 查詢業管系統，取得KYC冷靜期結束日 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/kyc-cooling-period-status/get", produces = MediaType.APPLICATION_JSON_VALUE)
    CoolingPeriodResponse queryCoolingPeriod(@RequestParam("method") String method, @RequestParam("custID") String custId);

    /** 查詢業管系統，取得KYC填寫次數 +查詢DB，取得KYC填寫次數上限 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/kyc-counts-and-limit/get", produces = MediaType.APPLICATION_JSON_VALUE)
    KycCountsResponse queryKycCountsAndLimit(@RequestParam("method") String method, @RequestParam("custID") String custId);

    /** 取得投資風險資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/risk-level/get", produces = MediaType.APPLICATION_JSON_VALUE)
    KycRiskLevelModel getKycRiskLevel(@RequestParam("riskCode") String riskCode, @RequestParam("locale") String locale);

    /** 取得前次填答結果頁 第二部分 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/people-soft/get", produces = MediaType.APPLICATION_JSON_VALUE)
    List<PeopleSoftRes> getPeopleSoftList(@RequestHeader("custId") String custId, @RequestParam("locale") String locale, @RequestParam("idType") String idType);

}
