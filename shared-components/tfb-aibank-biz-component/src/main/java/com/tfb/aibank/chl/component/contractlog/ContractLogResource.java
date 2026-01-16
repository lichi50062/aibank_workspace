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
package com.tfb.aibank.chl.component.contractlog;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import io.swagger.v3.oas.annotations.Operation;

// @formatter:off
/**
 * @(#)ContractLogResource.java
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
@FeignClient(name = "aibank-biz-component-contract-log-to-information-service", url = "${aibank.common.feign.service-aibank-information-url:http://svc-biz-information:8080}")
@Component("ContractLogResource")
public interface ContractLogResource {

    /** 單筆新增審閱條款紀錄(CONTRACT_LOG) */
    @Operation(summary = "單筆新增審閱條款紀錄(CONTRACT_LOG)")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/contract/record/{uidDup},{companyKind}/add", produces = { MediaType.APPLICATION_JSON_VALUE })
    ContractLog saveContractLog(@RequestBody ContractLog model, @RequestHeader("custId") String custId, @PathVariable("uidDup") String uidDup, @RequestHeader("userId") String userId, @PathVariable("companyKind") Integer companyKind);

    /** 多筆新增審閱條款紀錄(CONTRACT_LOG) */
    @Operation(summary = "多筆新增審閱條款紀錄(CONTRACT_LOG)")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/contract/multiple-records/{uidDup},{companyKind}/add", produces = { MediaType.APPLICATION_JSON_VALUE })
    List<ContractLog> saveContractLog(@RequestBody List<ContractLog> models, @RequestHeader("custId") String custId, @PathVariable("uidDup") String uidDup, @RequestHeader("userId") String userId, @PathVariable("companyKind") Integer companyKind);

}
