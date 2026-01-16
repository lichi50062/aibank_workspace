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
package com.ibm.tw.ibmb.component.systemparam;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ibm.tw.ibmb.base.model.BaseServiceResponse;

// @formatter:off
/**
 * @(#)SystemParamResource.java
 * 
 * <p>Description:溝通 service-aibank-system-config 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/24, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "common-system-param-to-system-config-service", url = "${aibank.common.feign.service-aibank-system-config-url:http://svc-biz-system-config:8080}")
public interface SystemParamResource {

    /** 讀取資料表(SYSTEM_PARAM)所有資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-param/list", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseServiceResponse<List<SystemParam>> getAllSystemParam();

    /** 更新指定的資料表(SYSTEM_PARAM)紀錄 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-params/{category},{paramKey}/update", produces = MediaType.APPLICATION_JSON_VALUE)
    void updateSystemParam(@PathVariable("category") String category, @PathVariable("paramKey") String paramKey, @RequestBody SystemParam param);

    /** 查詢資料表(SYSTEM_PARAM)，依指定條件查詢 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-params/{category},{paramKey}/get", produces = MediaType.APPLICATION_JSON_VALUE)
    SystemParam getSystemParam(@PathVariable("category") String category, @PathVariable("paramKey") String paramKey);

    /** 查詢資料表(SYSTEM_PARAM)，依指定條件查詢 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-params/{category}/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<SystemParam> getSystemParamList(@PathVariable("category") String category);

}
