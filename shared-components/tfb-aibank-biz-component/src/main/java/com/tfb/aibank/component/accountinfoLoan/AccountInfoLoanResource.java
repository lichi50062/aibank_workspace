package com.tfb.aibank.component.accountinfoLoan;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.Operation;

//@formatter:off
/**
 * @(#)AccountInfoLoanResource.java
 * 
 * <p>Description:貸款帳號名稱資訊檔 Resource</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/11/14, John Chang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@FeignClient(name = "aibank-biz-component-account-info-loan-to-loan-service", url = "${aibank.common.feign.service-aibank-loan-url:http://svc-biz-loan:8080}")
public interface AccountInfoLoanResource {

    @Operation(summary = "取得貸款帳號資料表")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/loan/account-info-loan/get", produces = { MediaType.APPLICATION_JSON_VALUE })
    GetAccountInfoLoanResponse getAccountInfoLoan();
}
