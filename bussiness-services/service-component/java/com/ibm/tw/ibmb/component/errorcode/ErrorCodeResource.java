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
package com.ibm.tw.ibmb.component.errorcode;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

// @formatter:off
/**
 * @(#)ErrorCodeResource.java
 * 
 * <p>Description:溝通 service-aibank-system-config 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/24, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "common-error-code-to-system-config-service", url = "${aibank.common.feign.service-aibank-system-config-url:http://svc-biz-system-config:8080}")
@Component("exceptionCodeResource")
public interface ErrorCodeResource {

    /** 查詢資料表(ERROR_CODE)，取得目前全部的資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/error/error-code/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ErrorCodeData> getAllErrorCode();

    /** 查詢資料表(ERROR_INFO)，取得目前全部的資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/error/error-info/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ErrorInfoData> getAllErrorInfo();

    /** 查詢資料表(TX_SYSTEM_MAP)，取得目前全部的資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/error/tx-system-map/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<TxSystemMap> getAllTxSystemMap();

}
