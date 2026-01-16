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
package com.tfb.aibank.chl.component.userdatacache;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.tfb.aibank.chl.component.userdatacache.model.CEW013RRes;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.model.creditcard.CreditCardStatus;

import io.swagger.v3.oas.annotations.Operation;

// @formatter:off
/**
 * @(#)CreditCardResource.java
 * 
 * <p>Description:溝通 service-aibank-credit-card 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-user-data-cache-to-credit-card-service", url = "${aibank.common.feign.service-aibank-credit-card-url:http://svc-biz-credit-card:8080}")
public interface CreditCardResource {

    /** 查詢信用卡戶況、註記 */
    @Operation(summary = "查詢信用卡戶況、註記")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-cards/status/get", produces = { MediaType.APPLICATION_JSON_VALUE })
    BaseServiceResponse<CreditCardStatus> getCreditCardStatus(@RequestHeader("custId") String custId);

    /** 查詢歸戶信用卡清單 */
    @Operation(summary = "查詢歸戶信用卡清單")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-cards/cards/list", produces = { MediaType.APPLICATION_JSON_VALUE })
    List<CreditCard> getAllCreditCardList(@RequestHeader("PIN") String pin, @RequestParam("TYPE") String type);

    /** 查詢申辦信用卡客戶資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-cards/apply-customer-info/get", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW013RRes sendCEW013R(@RequestHeader("custId") String custId);

}
