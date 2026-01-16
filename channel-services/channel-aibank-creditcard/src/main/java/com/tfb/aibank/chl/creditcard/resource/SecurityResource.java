package com.tfb.aibank.chl.creditcard.resource;

import com.tfb.aibank.chl.creditcard.resource.dto.Fido2PushNotifyRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.Fido2PushNotifyResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.QueryUserInfoFido2Request;
import com.tfb.aibank.chl.creditcard.resource.dto.QueryUserInfoFido2Response;
import com.tfb.aibank.chl.creditcard.resource.dto.RemoveUserFido2Request;
import com.tfb.aibank.chl.creditcard.resource.dto.RemoveUserFido2Response;
import com.tfb.aibank.chl.creditcard.resource.dto.UpdateCreditCardFido2FlagRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tfb.aibank.chl.creditcard.resource.dto.DecryptRSAEncodedTextRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.DecryptRSAEncodedTextResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.ValidateWithPasswordRuleRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.ValidateWithPasswordRuleResponse;

// @formatter:off
/**
 * @(#)SecurityResource.java
 * 
 * <p>Description:溝通 service-aibank-security 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/22, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-chl-creditcard-to-security-service", url = "${aibank.common.feign.service-aibank-security-url:http://svc-biz-security:8080}")
public interface SecurityResource {

    // E2EE取得 Public RSA Kxy
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1/publickey/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getRSAPublicKxy();

    // E2EE 解密 UID 跟 使用者代號
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/uid-uuid/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public DecryptRSAEncodedTextResponse decryptRSAEncodedText(@RequestBody DecryptRSAEncodedTextRequest request);

    // 密碼檢核
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/secret/check", produces = MediaType.APPLICATION_JSON_VALUE)
    ValidateWithPasswordRuleResponse validateWithPasswordRule(@RequestBody ValidateWithPasswordRuleRequest request);

    /** FIDO2 API 查詢會員狀態 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-card/fido2-flag/query-user-info", produces = MediaType.APPLICATION_JSON_VALUE)
    QueryUserInfoFido2Response queryUserInfoFido2(@RequestBody QueryUserInfoFido2Request request);

    /** FIDO2 API 使用者註銷 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-card/fido2-flag/remove-user", produces = MediaType.APPLICATION_JSON_VALUE)
    RemoveUserFido2Response removeUserFido2(@RequestBody RemoveUserFido2Request request);

    /** FIDO2 推播狀態查詢 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-card/fido2-flag/push", produces = MediaType.APPLICATION_JSON_VALUE)
    Fido2PushNotifyResponse getPushSubscriptionStatus(@RequestBody Fido2PushNotifyRequest request);

    /** FIDO2 更新 AIBANK_MB_DEVICE_INFO 表 CREDIT_CARD_FIDO2_FLAG 註記 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-card/fido2-flag/update", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean updateCreditCardFido2Flag(@RequestBody UpdateCreditCardFido2FlagRequest request);
}