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
package com.tfb.aibank.chl.component.datacenter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.aibank.chl.component.datacenter.model.OfferActionRequest;
import com.tfb.aibank.chl.component.datacenter.model.OfferActionResponse;
import com.tfb.aibank.chl.component.datacenter.model.UserTagResponse;

import io.swagger.v3.oas.annotations.Operation;

// @formatter:off
/**
 * @(#)DataCenterResource.java
 * 
 * <p>Description:溝通 service-aibank-information 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/08, Alex PY Li 
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-data-center-to-information-service", url = "${aibank.common.feign.service-aibank-information-url:http://information}")
@Component("DataCenterResource")
public interface DataCenterResource {
    @Operation(summary = "數據中台讀取使用者標籤")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/user-tags/get", produces = MediaType.APPLICATION_JSON_VALUE)
    UserTagResponse getUserTag(@RequestHeader("custId") String custId, @RequestHeader("dup") String dup);

    @Operation(summary = "數據中台讀取版位資訊")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/offer-actions/get", produces = MediaType.APPLICATION_JSON_VALUE)
    OfferActionResponse getOfferAction(@RequestBody OfferActionRequest request);
}
