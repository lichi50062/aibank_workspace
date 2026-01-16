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

import com.tfb.aibank.chl.component.userdatacache.model.AccountAlias;
import com.tfb.aibank.chl.component.userdatacache.model.EB202674D003Res;
import com.tfb.aibank.chl.component.userdatacache.model.EB552171Req;
import com.tfb.aibank.chl.component.userdatacache.model.EB552171Res;
import com.tfb.aibank.chl.component.userdatacache.model.EB5556911Req;
import com.tfb.aibank.chl.component.userdatacache.model.EB5556911Res;
import com.tfb.aibank.chl.component.userdatacache.model.EB5557891Res;
import com.tfb.aibank.chl.component.userdatacache.model.TrustAcctInRes;
import com.tfb.aibank.chl.model.account.FavoriteAccount;
import com.tfb.aibank.common.type.TransOutAcctType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)AccountResource.java
 * 
 * <p>Description:溝通 service-aibank-account 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-user-data-cache-to-account-service", url = "${aibank.common.feign.service-aibank-account-url:http://svc-biz-account:8080}")
@Component("AccountResource")
public interface AccountsResource {

    @Operation(summary = "查詢約定轉出帳號")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/accounts/trans-out-account/{transOutAcctType}/list", produces = MediaType.APPLICATION_JSON_VALUE)
    EB5557891Res getTransOutAccounts(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("userId") @Schema(description = "使用者代號") String userId, @RequestParam("nameCode") @Schema(description = "用戶代碼") String nameCode, @PathVariable("transOutAcctType") @Schema(description = "轉出帳號類型") TransOutAcctType transOutAcctType);

    @Operation(summary = "查詢約定轉入帳號")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/accounts/agreed-in-account/{companyKind}/list", produces = MediaType.APPLICATION_JSON_VALUE)
    EB5556911Res getAgreedInAccounts(@RequestBody EB5556911Req request, @PathVariable("companyKind") Integer companyKind);

    @Operation(summary = "查詢約定轉入帳號(外幣)")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/accounts/fx-agreed-in-account/{companyKind}/list", produces = MediaType.APPLICATION_JSON_VALUE)
    EB5556911Res getFxAgreedInAccounts(@RequestBody EB5556911Req request, @PathVariable("companyKind") Integer companyKind);

    @Operation(summary = "取得常用轉入帳號")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/accounts/favorite-payee/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<FavoriteAccount> getFavoritePayeeAccounts(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("userId") @Schema(description = "使用者代號") String userId, @RequestHeader("payerAccount") @Schema(description = "轉出帳號") String payerAccount, @RequestParam("companyKind") @Schema(description = "公司類型") Integer companyKind, @RequestParam("uidDup") @Schema(description = "誤別碼") String uidDup);

    @Operation(summary = "讀取客戶設定的帳號暱稱")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/account-alias-datas/{companyKind}/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AccountAlias> getAccountAliasList(@RequestHeader("custId") String custId, @RequestHeader("udiDup") String udiDup, @RequestHeader("userId") String userId, @PathVariable("companyKind") Integer companyKind);

    /** 依帳號取得OBU/DBU */
    @Operation(summary = "取得OBU/DBU")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/bu-types/{account}/get", produces = MediaType.APPLICATION_JSON_VALUE)
    Integer getBuTypeByAccount(@PathVariable("account") String account);

    @Operation(summary = "查詢轉出帳號建檔資料")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/accounts/trans-out-account-files/list", produces = MediaType.APPLICATION_JSON_VALUE)
    EB552171Res sendEB552171(@RequestHeader("custId") String custId, @RequestHeader("uid") String uxd, @RequestBody EB552171Req request);

    @Operation(summary = "查詢信託轉入帳號")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/accounts/trust-acct-in/list", produces = MediaType.APPLICATION_JSON_VALUE)
    TrustAcctInRes getTrustAcctIn(@RequestBody EB5556911Req request);

    @Operation(summary = "單一戶名檢核")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/acct-status/single-acct/check", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean checkSingleAccount(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("userId") @Schema(description = "使用者代號") String userId, @RequestParam("nameCode") @Schema(description = "戶名代碼") String nameCode);

    /** 查詢「活存帳號即時餘額」 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/account-balances/{acctId},{acctType}/get", produces = MediaType.APPLICATION_JSON_VALUE)
    EB202674D003Res getSavingAcctBalance(@PathVariable("acctId") String acctId, @PathVariable("acctType") String acctType, @RequestParam("curCode") String curCode);
}
