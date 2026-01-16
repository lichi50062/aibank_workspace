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
package com.tfb.aibank.chl.component.bank;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

// @formatter:off
/**
 * @(#)BankResource.java
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
@FeignClient(name = "aibank-biz-component-bank-to-information-service", url = "${aibank.common.feign.service-aibank-information-url:http://svc-biz-information:8080}")
@Component("BankResource")
public interface BankResource {

    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/bank-codes/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<BankData> getAllBankData();

    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/e-trans-bank-codes/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ETransBankData> getAllETransBankData();

}
