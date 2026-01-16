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
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.aibank.chl.component.session.InvestmentOverview;
import com.tfb.aibank.chl.component.userdatacache.model.EB032675Res;
import com.tfb.aibank.chl.component.userdatacache.model.EB552170Response;
import com.tfb.aibank.chl.component.userdatacache.model.EB67115Res;
import com.tfb.aibank.chl.component.userdatacache.model.FC032155Response;
import com.tfb.aibank.chl.component.userdatacache.model.FinancialDataOrder;
import com.tfb.aibank.chl.component.userdatacache.model.HasTrustAcct;
import com.tfb.aibank.chl.component.userdatacache.model.RetrieveUserOTPStatusResponse;

import io.swagger.v3.oas.annotations.Operation;

// @formatter:off
/**
 * @(#)UserResource.java
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
@FeignClient(name = "aibank-biz-component-user-data-cache-to-user-service", url = "${aibank.common.feign.service-aibank-user-url:http://svc-biz-user:8080}")
@Component("UserResource")
public interface UserResource {
    @Operation(summary = "取得使用者OTP狀態")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/otp-status/get", produces = MediaType.APPLICATION_JSON_VALUE)
    RetrieveUserOTPStatusResponse retrieveUserOTPStatus(@RequestHeader("userId") String userId, @RequestHeader("userIdDup") String userIdDup, @RequestHeader("userUuid") String userUuid, @RequestParam("nameCode") String nameCode);

    @Operation(summary = "取得多使用者代碼註記")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/user-code-memos/get", produces = MediaType.APPLICATION_JSON_VALUE)
    EB552170Response sendEB552170ForSingleUser(@RequestHeader("custId") String custId, @RequestHeader("dup") String dup);

    @Operation(summary = "取得本行客戶註記")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/user-marks/get", produces = MediaType.APPLICATION_JSON_VALUE)
    FC032155Response getUserRemark(@RequestHeader(name = "custId") String custId);

    @Operation(summary = "查詢客戶是否為行員")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/employees/status/get", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean isEmployee(@RequestHeader("custId") String custId);

    @Operation(summary = "查詢客戶是否為'退休'行員")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/employees/status-retired/get", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean isRetiredEmployee(@RequestHeader("custId") String custId);

    @Operation(summary = "查詢客戶各類投資資格註記(禁銷、FATCA、弱勢、專業投資人法人)")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/invest-eligible-data/query", produces = MediaType.APPLICATION_JSON_VALUE)
    EB032675Res sendEB032675(@RequestHeader("custId") String custId);

    @Operation(summary = "更新客戶各類投資資格註記")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/invest-eligible-data/add", produces = MediaType.APPLICATION_JSON_VALUE)
    EB032675Res sendEB032675Modify(@RequestHeader("custId") String custId, @RequestParam("busAddr1") String busAddr1);

    @Operation(summary = "取得客戶是否具備FATCA排外註記")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/un-fatca-flag/query", produces = MediaType.APPLICATION_JSON_VALUE)
    EB67115Res sendEB67115(@RequestHeader("custId") String custId);
    
    /** 檢核客戶是否開立信託帳號 */
    @Operation(summary = "檢核客戶是否開立信託帳號")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/has-trustAcct/query", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean hasTrustAcct(@RequestBody HasTrustAcct request);

    /** 儲存使用者投資商品排序 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/prod-data-order/{companyKind}/add", produces = { MediaType.APPLICATION_JSON_VALUE })
    FinancialDataOrder saveUserProdDataOrder(@RequestBody FinancialDataOrder data, @RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @PathVariable("companyKind") Integer companyKind, @RequestParam("uidDup") String uidDup);

    /** 取得使用者投資商品排序 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/prod-data-order/{companyKind}/get", produces = { MediaType.APPLICATION_JSON_VALUE })
    List<FinancialDataOrder> getProdDataOrderList(@RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @PathVariable("companyKind") Integer companyKind, @RequestParam("uidDup") String uidDup, @RequestParam("taskId") String taskId);

    /** 查詢投資總覽市值大於0之商品 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/investment/overview-avalible-card/get", produces = MediaType.APPLICATION_JSON_VALUE)
    InvestmentOverview getInvestOverviewMarketValueGreater0(@RequestHeader("custId") String custId, @RequestHeader("nameCode") String nameCode, @RequestParam("dbu") boolean dbu, @RequestParam("haveCreditCard") boolean haveCreditCard);

    /** 取得用戶是否為專業投資人 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/isProfessionalInvestor", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean isProfessionalInvestor(@RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @RequestParam("nameCode") String nameCode);

}
