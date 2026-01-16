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
package com.tfb.aibank.chl.component.newfunctionintro;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

// @formatter:off
/**
 * @(#)NewFunctionIntroResource.java
 * 
 * <p>Description:溝通 service-aibank-information 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/27,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-new-function-intro-to-information-service", url = "${aibank.common.feign.service-aibank-information-url:http://svc-biz-information:8080}")
@Component("NewFunctionIntroResource")
public interface NewFunctionIntroResource {

    /** 查詢資料表(NEW_FUNCTION_INTRO)最新功能介紹 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/new-func-intro/new-funcs/query", produces = MediaType.APPLICATION_JSON_VALUE)
    List<NewFunctionIntro> queryNewFunctions();

}
