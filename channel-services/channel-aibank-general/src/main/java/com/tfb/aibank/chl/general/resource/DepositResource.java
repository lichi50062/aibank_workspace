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

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.aibank.chl.general.resource.dto.EB12020011Response;

// @formatter:off
/**
 * @(#)DepositResource.java
 * 
 * <p>Description:溝通 service-aibank-deposit 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/04, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-chl-general-to-deposit-service", url = "${aibank.common.feign.service-aibank-deposit-url:http://svc-biz-deposit:8080}")
public interface DepositResource {

    /** GWAPI03取消客戶綁定 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/open-api/user-bind/delete", produces = { MediaType.APPLICATION_JSON_VALUE })
    String deleteOpenAPIUserBind(@RequestHeader("customerUid") String customerUid, @RequestHeader("mobilePhone") String mobilePhone);

    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/digital-account/open-account/get", produces = MediaType.APPLICATION_JSON_VALUE)
    EB12020011Response sendEB12020011(@RequestHeader("custId") String custId, @RequestParam("funcCod") String funcCod, @RequestParam("func") String func, @RequestParam(name = "appCode", required = false) String appCode);

}
