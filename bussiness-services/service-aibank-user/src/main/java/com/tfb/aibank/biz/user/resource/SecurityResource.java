package com.tfb.aibank.biz.user.resource;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.tfb.aibank.biz.user.resource.model.CheckSecuirtyRulesRequest;
import com.tfb.aibank.biz.user.resource.model.CheckSecuirtyRulesResponse;
import com.tfb.aibank.biz.user.resource.model.DecryptRSAEncodedTextRequest;
import com.tfb.aibank.biz.user.resource.model.DecryptRSAEncodedTextResponse;
import com.tfb.aibank.biz.user.resource.model.EncodeWithSecretRequest;
import com.tfb.aibank.biz.user.resource.model.EncodeWithSecretResponse;

import io.swagger.v3.oas.annotations.Operation;

// @formatter:off
/**
 * @(#)SecurityResource.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "service-aibank-security", url = "${aibank.common.feign.service-aibank-security-url:http://svc-biz-security:8080}")
public interface SecurityResource {

    @Operation(summary = "E2EE取得 Public RSA Kxy")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1/publickey/get", produces = MediaType.APPLICATION_JSON_VALUE)
    String getRSAPublicKxy();

    @Operation(summary = "E2EE 解密 UID 跟 使用者代號")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/uid-uuid/update", produces = MediaType.APPLICATION_JSON_VALUE)
    DecryptRSAEncodedTextResponse decryptRSAEncodedText(@RequestBody DecryptRSAEncodedTextRequest request);

    @Operation(summary = "E2EE 登入密碼轉換")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/secret/update", produces = MediaType.APPLICATION_JSON_VALUE)
    EncodeWithSecretResponse encodewithSecret(@RequestBody EncodeWithSecretRequest request);

    @Operation(summary = "登入後判斷是否需要修改密碼")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/secret-modify/check", produces = MediaType.APPLICATION_JSON_VALUE)
    CheckSecuirtyRulesResponse checkSecuirtyRules(@RequestBody CheckSecuirtyRulesRequest request);

    @Operation(summary = "新密碼是否已到可寫入時間")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1/pwd-already-valid/get", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean isPasswordAdvancedEnable(@RequestHeader("companyUid") String companyUid);

    @Operation(summary = "使用者是否已使用新密碼驗證流程")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1/user-pwd-advanced/get", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean isCCUserLoginWithNewPassword(@RequestHeader("userPwdFlg") String userPwdFlg, @RequestHeader("companyUid") String companyUid);

    @Operation(summary = "提供判斷此平台是否已開發新密碼驗證流程，且已到達可使用新密碼流程的時間")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1/new-pwd-process/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean isCCUserNewLoginProcessEnable(@RequestHeader("companyUid") String companyUid);
}