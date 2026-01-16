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
package com.tfb.aibank.chl.component.remarkcontent;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;

// @formatter:off
/**
 * @(#)RemarkContentResource.java
 * 
 * <p>Description:溝通 service-aibank-information 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-remark-content-to-information-service", url = "${aibank.common.feign.service-aibank-information-url:http://svc-biz-information:8080}")
@Component("RemarkContentResource")
public interface RemarkContentResource {

    @Operation(summary = "單筆讀取輔助說明")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/memos/{key}/get", produces = MediaType.APPLICATION_JSON_VALUE)
    RemarkContent getMemo(@PathVariable("key") String remarkKey);

    @Operation(summary = "單筆讀取輔助說明")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/memos/{key}/get", produces = MediaType.APPLICATION_JSON_VALUE)
    RemarkContent getMemo(@PathVariable("key") String remarkKey, @RequestParam(name = "locale", required = false) String locale);

    @Operation(summary = "單筆讀取頁面備註")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/remarks/{key}/get", produces = MediaType.APPLICATION_JSON_VALUE)
    RemarkContent getRemark(@PathVariable("key") String remarkKey);

    @Operation(summary = "單筆讀取頁面備註")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/remarks/{key}/get", produces = MediaType.APPLICATION_JSON_VALUE)
    RemarkContent getRemark(@PathVariable("key") String remarkKey, @RequestParam(name = "locale", required = false) String locale);

    @Operation(summary = "單筆讀取條款")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/terms/{key}/get", produces = MediaType.APPLICATION_JSON_VALUE)
    RemarkContent getTerms(@PathVariable("key") String remarkKey);

    @Operation(summary = "單筆讀取條款")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/terms/{key}/get", produces = MediaType.APPLICATION_JSON_VALUE)
    RemarkContent getTerms(@PathVariable("key") String remarkKey, @RequestParam(name = "locale", required = false) String locale);

    @Operation(summary = "單筆讀取輔助說明(2)")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/memos/ext/{key}/get", produces = MediaType.APPLICATION_JSON_VALUE)
    RemarkContent getMemoExtension(@PathVariable("key") String remarkKey);

    @Operation(summary = "單筆讀取輔助說明(2)")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/memos/ext/{key}/get", produces = MediaType.APPLICATION_JSON_VALUE)
    RemarkContent getMemoExtension(@PathVariable("key") String remarkKey, @RequestParam(name = "locale", required = false) String locale);

}
