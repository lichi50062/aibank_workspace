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
package com.tfb.aibank.chl.component.log;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


// @formatter:off
/**
 * @(#)AccessLogResource.java
 * 
 * <p>Description:溝通 service-aibank-user 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/03/07, David Huang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-accesslog-to-user-service", url = "${aibank.common.feign.service-aibank-user-url:http://svc-biz-user:8080}")
public interface AccessLogResource {
    /** 新增 ACCESS LOG RECORD */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/wbplusaccesslog/add", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean addWBPlusAccessLog(@RequestBody WBPlusAccessLogRequest request);
}
