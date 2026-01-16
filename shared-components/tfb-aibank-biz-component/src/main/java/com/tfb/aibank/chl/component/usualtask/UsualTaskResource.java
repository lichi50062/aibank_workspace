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
package com.tfb.aibank.chl.component.usualtask;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)UsualTaskResource.java
 * 
 * <p>Description:溝通 service-aibank-user 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-usual-task-to-user-service", url = "${aibank.common.feign.service-aibank-user-url:http://svc-biz-user:8080}")
@Component("UsualTaskResource")
public interface UsualTaskResource {

    @Operation(summary = "取得常用功能")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/usual-task/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UsualTask> getUsualTasks(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("userId") @Schema(description = "使用者代號") String userId, @RequestParam("uidDup") @Schema(description = "誤別碼") String uidDup, @RequestParam("companyKind") @Schema(description = "公司類型") Integer companyKind);

    @Operation(summary = "更新常用功能")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/usual-task/update", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UsualTask> updateUsualTasks(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("userId") @Schema(description = "使用者代號") String userId, @RequestParam("uidDup") @Schema(description = "誤別碼") String uidDup, @RequestParam("companyKind") @Schema(description = "公司類型") Integer companyKind, @RequestBody List<UsualTask> tasks);
}
