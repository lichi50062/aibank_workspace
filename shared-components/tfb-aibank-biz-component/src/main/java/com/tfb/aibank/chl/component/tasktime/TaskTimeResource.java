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
package com.tfb.aibank.chl.component.tasktime;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

// @formatter:off
/**
 * @(#)TaskTimeResource.java
 * 
 * <p>Description:溝通 service-aibank-system-config 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-task-time-to-system-config-service", url = "${aibank.common.feign.service-aibank-system-config-url:http://svc-biz-system-config:8080}")
@Component("TaskTimeResource")
public interface TaskTimeResource {

    /** 查詢資料表(TASK_TIME)，取得目前全部交易代號的可交易時間 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/task-time/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskTime> getAllTaskTimes();

}
