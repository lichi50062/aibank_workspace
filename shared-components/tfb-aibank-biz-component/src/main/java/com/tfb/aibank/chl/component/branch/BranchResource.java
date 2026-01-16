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
package com.tfb.aibank.chl.component.branch;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

// @formatter:off
/**
 * @(#)BranchResource.java
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
@FeignClient(name = "aibank-biz-component-branch-to-information-service", url = "${aibank.common.feign.service-aibank-information-url:http://svc-biz-information:8080}")
@Component("BranchResource")
public interface BranchResource {

    /** 查詢資料表(BRANCH)，取得目前全部的資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/branch-codes/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<BranchData> getAllBranch();

    /** 查詢資料表(BANK_BRANCH)，取得目前全部的資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/bank-branch-codes/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<BankBranchData> getAllBankBranch();

    /** 查詢資料表(BRANCHMAP)，取得目前全部的資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/branchmap-codes/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Branchmap> getAllBranchmap();
}
