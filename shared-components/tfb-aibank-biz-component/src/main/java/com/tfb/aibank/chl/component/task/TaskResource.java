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
package com.tfb.aibank.chl.component.task;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.ibm.tw.ibmb.base.model.BaseServiceResponse;

// @formatter:off
/**
 * @(#)TaskResource.java
 * 
 * <p>Description:溝通 service-aibank-system-config 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-task-to-system-config-service", url = "${aibank.common.feign.service-aibank-system-config-url:http://svc-biz-system-config:8080}")
@Component("TaskResource")
public interface TaskResource {

    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/tasks/list", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseServiceResponse<List<Task>> getAllTasks();

    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/task-pages/list", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseServiceResponse<List<TaskPage>> getAllTaskPages();

    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/role-tasks/list", produces = MediaType.APPLICATION_JSON_VALUE)
    BaseServiceResponse<Map<String, List<String>>> getRoleTaskMapping();

}
