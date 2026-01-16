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
package com.tfb.aibank.chl.component.suspendtask;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.ibm.tw.ibmb.base.model.BaseServiceResponse;

// @formatter:off
/**
 * @(#)SuspendTaskResource.java
 * 
 * <p>Description:溝通 service-aibank-information 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/12/25, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "service-information-suspend-task", url = "${aibank.common.feign.service-aibank-information-url:http://svc-biz-information:8080}")
@Component("SuspendTaskResource")
public interface SuspendTaskResource {

    /** 查詢資料表(SUSPEND_MASTER、SUSPEND_DETAIL、SUSPEND_MESSAGE)，取得暫停交易的資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/suspend-task/list", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseServiceResponse<List<SuspendTask>> getSuspendTaskList();

}
