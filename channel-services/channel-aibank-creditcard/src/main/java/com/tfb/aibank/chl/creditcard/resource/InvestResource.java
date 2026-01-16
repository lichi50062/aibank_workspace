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
package com.tfb.aibank.chl.creditcard.resource;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tfb.aibank.chl.creditcard.resource.dto.AnnualDetailReq;
import com.tfb.aibank.chl.creditcard.resource.dto.AnnualDetailRes;
import com.tfb.aibank.chl.creditcard.resource.dto.CategoryDetailReq;
import com.tfb.aibank.chl.creditcard.resource.dto.CategoryDetailRes;
import com.tfb.aibank.chl.creditcard.resource.dto.CategoryReq;
import com.tfb.aibank.chl.creditcard.resource.dto.CategoryRes;
import com.tfb.aibank.chl.creditcard.resource.dto.ConsumptionSummaryReq;
import com.tfb.aibank.chl.creditcard.resource.dto.ConsumptionSummaryRes;

// @formatter:off
/**
 * @(#)InvestResource.java
 * 
 * <p>Description:溝通 service-aibank-invest 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/22, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-chl-creditcard-to-invest-service", url = "${aibank.common.feign.service-aibank-invest-url:http://svc-biz-invest:8080}")
public interface InvestResource {

    // 消費金額彙總資訊查詢
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/oauth-api/monthly-consumption-summary/get", produces = MediaType.APPLICATION_JSON_VALUE)
    ConsumptionSummaryRes getConsumptionSummary(@RequestBody ConsumptionSummaryReq request);

    // 各消費類別金額查詢
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/oauth-api/monthly-category-consumption/get", produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryRes getCategoryConsumption(@RequestBody CategoryReq request);

    // 各類別消費明細查詢
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/oauth-api/monthly-category-details/get", produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDetailRes getCategoryDetails(@RequestBody CategoryDetailReq request);

    // 整年消費明細搜尋
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/oauth-api/annual-consumption-details/get", produces = MediaType.APPLICATION_JSON_VALUE)
    AnnualDetailRes getAnnualDetails(@RequestBody AnnualDetailReq request);

}
