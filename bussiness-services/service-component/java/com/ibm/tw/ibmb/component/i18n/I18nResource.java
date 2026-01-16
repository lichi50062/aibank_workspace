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
package com.ibm.tw.ibmb.component.i18n;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

// @formatter:off
/**
 * @(#)I18nResource.java
 * 
 * <p>Description:溝通 service-aibank-information 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/24, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "common-i18n-to-information-service", url = "${aibank.common.feign.service-aibank-information-url:http://svc-biz-information:8080}")
public interface I18nResource {

    /** 查詢資料表(AI_I18N)，取得目前全部的資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/ai-i18n/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<I18nModel> getAllI18n();

}
