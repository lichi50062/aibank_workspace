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

import com.tfb.aibank.chl.component.userdatacache.model.Fido2PushNotifyRequest;
import com.tfb.aibank.chl.component.userdatacache.model.Fido2PushNotifyResponse;
import com.tfb.aibank.chl.component.userdatacache.model.QueryUserInfoFido2Request;
import com.tfb.aibank.chl.component.userdatacache.model.QueryUserInfoFido2Response;
import com.tfb.aibank.chl.component.userdatacache.model.RemoveUserFido2Request;
import com.tfb.aibank.chl.component.userdatacache.model.RemoveUserFido2Response;
import com.tfb.aibank.chl.component.userdatacache.model.UpdateCreditCardFido2FlagRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tfb.aibank.chl.component.userdatacache.model.BindingAuthFlagRequest;
import com.tfb.aibank.chl.component.userdatacache.model.BindingAuthFlagResponse;
import com.tfb.aibank.chl.component.userdatacache.model.CheckMotpStatusRequest;
import com.tfb.aibank.chl.component.userdatacache.model.CheckMotpStatusResponse;

// @formatter:off
/**
 * @(#)SecurityResource.java
 * 
 * <p>Description:安控服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/12, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-user-data-cache-to-security-service", url = "${aibank.common.feign.service-aibank-security-url:http://svc-biz-security:8080}")
public interface SecurityResource {

    /** 使用MOTP驗證前檢查狀態 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/motp-auths/check", produces = MediaType.APPLICATION_JSON_VALUE)
    public CheckMotpStatusResponse checkMotpStatus(@RequestBody CheckMotpStatusRequest request);

    /** 查詢/設定裝置綁定授權註記 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/binding-auth-flag/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public BindingAuthFlagResponse getBindingAuthFlag(@RequestBody BindingAuthFlagRequest request);

    /** FIDO2 API 使用者註銷 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-card/fido2-flag/remove-user", produces = MediaType.APPLICATION_JSON_VALUE)
    RemoveUserFido2Response removeUserFido2(@RequestBody RemoveUserFido2Request request);

    /** FIDO2 更新 AIBANK_MB_DEVICE_INFO 表 CREDIT_CARD_FIDO2_FLAG 註記 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-card/fido2-flag/update", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean updateCreditCardFido2Flag(@RequestBody UpdateCreditCardFido2FlagRequest request);

    /** FIDO2 API 查詢會員狀態 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-card/fido2-flag/query-user-info", produces = MediaType.APPLICATION_JSON_VALUE)
    QueryUserInfoFido2Response queryUserInfoFido2(@RequestBody QueryUserInfoFido2Request request);

    /** FIDO2 推播狀態查詢 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-card/fido2-flag/push", produces = MediaType.APPLICATION_JSON_VALUE)
    Fido2PushNotifyResponse getPushSubscriptionStatus(@RequestBody Fido2PushNotifyRequest request);
}
