package com.tfb.aibank.chl.component.userdatacache;

import java.util.Date;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.tfb.aibank.chl.component.userdatacache.model.CheckMotpStatusRequest;
import com.tfb.aibank.chl.component.userdatacache.model.CheckMotpStatusResponse;
import com.tfb.aibank.chl.component.userdatacache.model.EBHN002Response;
import com.tfb.aibank.chl.component.userdatacache.model.EBLN010Response;
import com.tfb.aibank.chl.component.userdatacache.model.LoanAccount;
import com.tfb.aibank.chl.component.userdatacache.model.LoanDetailBean;
import com.tfb.aibank.chl.component.userdatacache.model.OdsLoancustData;
import com.tfb.aibank.chl.component.userdatacache.model.SendEB555789Request;
import com.tfb.aibank.chl.component.userdatacache.model.SendEB555789Response;

import feign.Request;

//@formatter:off
/**
* @(#)LoanResource.java
* 
* <p>Description:貸款服務</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/10/12, John   
* <ol>
*  <li>初版</li>
* </ol>
*/ 
//@formatter:on
@FeignClient(name = "aibank-biz-component-user-data-cache-to-loan-service", url = "${aibank.common.feign.service-aibank-loan-url:http://svc-biz-loan:8080}")
public interface LoanResource {

    /** 使用MOTP驗證前檢查狀態 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/motp-auths/check", produces = MediaType.APPLICATION_JSON_VALUE)
    CheckMotpStatusResponse checkMotpStatus(@RequestBody CheckMotpStatusRequest request);

    /** 取得房貸可增貸額度 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/loan/house-loan/get", produces = { MediaType.APPLICATION_JSON_VALUE })
    EBHN002Response sendEBHN002(@RequestHeader("CustId") String custId);

    /** 檢核是否符合速貸通資格 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/loan/fast-loan/get", produces = { MediaType.APPLICATION_JSON_VALUE })
    EBLN010Response sendEBLN010(@RequestHeader("CustId") String custId);

    /** 檢核是否符合信貸觔斗雲名單 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/loan/jdy-loan/get", produces = { MediaType.APPLICATION_JSON_VALUE })
    String getJDYCreditData(@RequestHeader("CustId") String custId);

    /** 查詢貸款帳號 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/loan/accounts/get", produces = MediaType.APPLICATION_JSON_VALUE)
    SendEB555789Response getAllLoanAccount(@RequestBody SendEB555789Request request, Request.Options option);
    
    /** 貸款牌卡查詢貸款帳號 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/loan/accounts-homecard/get", produces = MediaType.APPLICATION_JSON_VALUE)
    SendEB555789Response getAllHomeCardLoanAccount(@RequestBody SendEB555789Request request, Request.Options option);

    /** 取得貸款客群 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/loan/loan-group/get", produces = { MediaType.APPLICATION_JSON_VALUE })
    OdsLoancustData getLoanCustomerGreup(@RequestHeader("CustId") String custId);

    /** 查詢貸款繳費明細(貸款類型=房貸(61)-02公教/03國宅) */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/loan/gov-pay-his/{startDate},{endDate}/query", produces = { MediaType.APPLICATION_JSON_VALUE })
    List<LoanDetailBean> sendEB362611(@RequestBody LoanAccount account, @PathVariable("startDate") @DateTimeFormat(pattern = "yyyyMMddHHmmssSSS") Date startDate, @PathVariable("endDate") @DateTimeFormat(pattern = "yyyyMMddHHmmssSSS") Date endDate, Request.Options option);

    /** 查詢貸款繳費明細(貸款類型=學貸(63)-01就貸) */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/loan/edu-pay-his/{startDate},{endDate}/query", produces = { MediaType.APPLICATION_JSON_VALUE })
    List<LoanDetailBean> sendEB382607(@RequestBody LoanAccount account, @PathVariable("startDate") @DateTimeFormat(pattern = "yyyyMMddHHmmssSSS") Date startDate, @PathVariable("endDate") @DateTimeFormat(pattern = "yyyyMMddHHmmssSSS") Date endDate, Request.Options option);

    /** 查詢貸款繳費明細(貸款類型=存單質借) */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/loan/com-pay-his/{startDate},{endDate}/query", produces = { MediaType.APPLICATION_JSON_VALUE })
    List<LoanDetailBean> sendEB572667(@RequestBody LoanAccount account, @PathVariable("startDate") @DateTimeFormat(pattern = "yyyyMMddHHmmssSSS") Date startDate, @PathVariable("endDate") @DateTimeFormat(pattern = "yyyyMMddHHmmssSSS") Date endDate, Request.Options option);

}