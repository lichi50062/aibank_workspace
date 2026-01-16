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
package com.tfb.aibank.chl.component.favoriteaccount;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.aibank.chl.component.favoriteaccount.model.CreateFavoriteAccountRequest;
import com.tfb.aibank.chl.component.favoriteaccount.model.CreateFavoriteAccountResponse;
import com.tfb.aibank.chl.component.favoriteaccount.model.UpdateFavoriteAccountRequest;
import com.tfb.aibank.chl.component.favoriteaccount.model.UpdateFavoriteAccountResponse;

// @formatter:off
/**
 * @(#)FavoriteAccountResource.java
 * 
 * <p>Description:常用帳號服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/14, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-favorite-account-to-account-service", url = "${aibank.common.feign.service-aibank-account-url:http://svc-biz-account:8080}")
public interface FavoriteAccountResource {

    /** 新增常用帳號 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/accounts/favorite-accounts/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    CreateFavoriteAccountResponse createFavoriteAccount(@RequestBody CreateFavoriteAccountRequest request);

    /** 更新常用帳號 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/accounts/favorite-accounts/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    UpdateFavoriteAccountResponse updateFavoriteAccount(@RequestBody UpdateFavoriteAccountRequest request);

    /** 刪除常用帳號 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/accounts/favorite-accounts/delete", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean deleteFavoriteAccount(@RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @RequestHeader("companyKind") Integer companyKind, @RequestHeader("uidDup") String uidDup, @RequestHeader("payerAccount") String payerAccount, @RequestParam("designateFlag") String designateFlag, @RequestParam("payeeBank") String payeeBank, @RequestHeader("payeeAccount") String payeeAccount);

    /** 排序常用帳號 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/accounts/favorite-accounts-sort/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean sortFavoriteAccount(@RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @RequestHeader("companyKind") Integer companyKind, @RequestHeader("uidDup") String uidDup, @RequestHeader("payerAccount") String payerAccount, @RequestParam("designateFlag") String designateFlag, @RequestHeader("bankAccounts") List<String> bankAccounts);

}
