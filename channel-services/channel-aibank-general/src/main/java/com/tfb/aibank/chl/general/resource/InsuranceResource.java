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
package com.tfb.aibank.chl.general.resource;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.aibank.chl.general.resource.dto.AiFubonInsurDataResponse;
import com.tfb.aibank.chl.general.resource.dto.AiOtherInsurDataResponse;
import com.tfb.aibank.chl.general.resource.dto.WS00002Request;
import com.tfb.aibank.chl.general.resource.dto.WS00002Response;

// @formatter:off
/**
 * @(#)InsuranceResource.java
 * 
 * <p>Description:溝通 service-aibank-insurance 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/21, Lillian.Tsai
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-chl-overview-to-insurance-service", url = "${aibank.common.feign.service-aibank-insurance-url:http://svc-biz-insurance:8080}")
public interface InsuranceResource {

    /** 取得富壽保險商品資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/fubon-insurance-data/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AiFubonInsurDataResponse> getAiFubonInsuranceData(@RequestHeader("custId") String custId, @RequestParam("birth") String birth);

    /** 取得非富壽保險商品資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/other-insurance-data/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AiOtherInsurDataResponse> getAiOtherInsurData(@RequestHeader("custId") String custId);

    /** 取得保單資料 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/insurance-info/get", produces = MediaType.APPLICATION_JSON_VALUE)
    WS00002Response getInsuranceInfo(@RequestBody WS00002Request request);

}
