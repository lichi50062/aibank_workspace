package com.tfb.aibank.chl.general.resource;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tfb.aibank.chl.general.resource.dto.HomeCardLoanRequest;
import com.tfb.aibank.chl.general.resource.dto.HomeCardLoanResponse;

// @formatter:off
/**
 * @(#)LoanResource.java
 * 
 * <p>Description:溝通 service-aibank-loan 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/22, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-chl-general-to-loan-service", url = "${aibank.common.feign.service-aibank-loan-url:http://svc-biz-loan:8080}")
public interface LoanResource {


    /**
     * 取得貸款客群-首頁貸款牌卡用
     */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/loan/loan-group/homepage/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public HomeCardLoanResponse getLoanCustomerGroupData(@RequestBody HomeCardLoanRequest request);


}