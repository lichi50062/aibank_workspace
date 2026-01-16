package com.tfb.aibank.chl.general.resource;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.tfb.aibank.chl.component.userdatacache.model.BindingAuthFlagRequest;
import com.tfb.aibank.chl.component.userdatacache.model.BindingAuthFlagResponse;
import com.tfb.aibank.chl.general.resource.dto.AddMbDeviceInfoRequest;
import com.tfb.aibank.chl.general.resource.dto.CheckSecuirtyRulesRequest;
import com.tfb.aibank.chl.general.resource.dto.CheckSecuirtyRulesResponse;
import com.tfb.aibank.chl.general.resource.dto.ConfirmBindDeviceRequest;
import com.tfb.aibank.chl.general.resource.dto.ConfirmBindDeviceResponse;
import com.tfb.aibank.chl.general.resource.dto.CreateBindDeviceRequest;
import com.tfb.aibank.chl.general.resource.dto.CreateBindDeviceResponse;
import com.tfb.aibank.chl.general.resource.dto.DecryptRSAEncodedTextRequest;
import com.tfb.aibank.chl.general.resource.dto.DecryptRSAEncodedTextResponse;
import com.tfb.aibank.chl.general.resource.dto.DeleteMbDeviceInfoRequest;
import com.tfb.aibank.chl.general.resource.dto.DoRevokeRequest;
import com.tfb.aibank.chl.general.resource.dto.DoRevokeResponse;
import com.tfb.aibank.chl.general.resource.dto.EncodeWithSecretRequest;
import com.tfb.aibank.chl.general.resource.dto.EncodeWithSecretResponse;
import com.tfb.aibank.chl.general.resource.dto.LoginRequest;
import com.tfb.aibank.chl.general.resource.dto.LoginResponse;
import com.tfb.aibank.chl.general.resource.dto.QueryLogResponse;
import com.tfb.aibank.chl.general.resource.dto.QueryVerifyResultRequest;
import com.tfb.aibank.chl.general.resource.dto.QueryVerifyResultResponse;
import com.tfb.aibank.chl.general.resource.dto.UpdateLoginTypeRequest;
import com.tfb.aibank.chl.general.resource.dto.UpdateLoginTypeResponse;
import com.tfb.aibank.chl.general.resource.dto.ValidateWithPasswordRuleRequest;
import com.tfb.aibank.chl.general.resource.dto.ValidateWithPasswordRuleResponse;

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
@FeignClient(name = "aibank-chl-general-to-security-service", url = "${aibank.common.feign.service-aibank-security-url:http://svc-biz-security:8080}")
public interface SecurityResource {

    // E2EE取得 Public RSA Kxy
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1/publickey/get", produces = MediaType.APPLICATION_JSON_VALUE)
    String getRSAPublicKxy();

    // E2EE 解密 UID 跟 使用者代號
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/uid-uuid/update", produces = MediaType.APPLICATION_JSON_VALUE)
    DecryptRSAEncodedTextResponse decryptRSAEncodedText(@RequestBody DecryptRSAEncodedTextRequest request);

    // E2EE 登入密碼轉換
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/secret/update", produces = MediaType.APPLICATION_JSON_VALUE)
    EncodeWithSecretResponse encodewithSecret(@RequestBody EncodeWithSecretRequest request);

    // 登入後判斷是否需要修改密碼
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/secret-modify/check", produces = MediaType.APPLICATION_JSON_VALUE)
    CheckSecuirtyRulesResponse checkSecuirtyRules(@RequestBody CheckSecuirtyRulesRequest request);

    // 密碼檢核
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/secret/check", produces = MediaType.APPLICATION_JSON_VALUE)
    ValidateWithPasswordRuleResponse validateWithPasswordRule(@RequestBody ValidateWithPasswordRuleRequest request);

    // FIDO 登入
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/fido-login/update", produces = MediaType.APPLICATION_JSON_VALUE)
    LoginResponse fidoLogin(@RequestBody LoginRequest request);

    // FIDO 複驗交易結果
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/verify-rsult/check", produces = MediaType.APPLICATION_JSON_VALUE)
    QueryVerifyResultResponse queryVerifyResult(@RequestBody QueryVerifyResultRequest request);

    // FIDO Query-Log
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1/log/get", produces = MediaType.APPLICATION_JSON_VALUE)
    QueryLogResponse queryLog(@RequestHeader("custId") String custId);

    // FIDO 解綁
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/binding/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    DoRevokeResponse doRevoke(@RequestBody DoRevokeRequest request);

    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/binding/update", produces = MediaType.APPLICATION_JSON_VALUE)
    UpdateLoginTypeResponse updateLoginType(UpdateLoginTypeRequest request);

    /** 新增 MB_DEVICE_INFO */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/mb-device-info/add", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean addMbDeviceInfo(@RequestBody AddMbDeviceInfoRequest request);

    /** 刪除 MB_DEVICE_INFO */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/mb-device-info/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean deleteMbDeviceInfo(@RequestBody DeleteMbDeviceInfoRequest request);

    /** 建立MOTP設備綁定 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/motp-bindings/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public CreateBindDeviceResponse createBindDevice(@RequestBody CreateBindDeviceRequest request);

    /** 確認建立MOTP設備綁定 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/motp-bindings/confirm-create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ConfirmBindDeviceResponse confirmBindDevice(@RequestBody ConfirmBindDeviceRequest request);

    /** 查詢/設定裝置綁定授權註記 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/binding-auth-flag/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public BindingAuthFlagResponse getBindingAuthFlag(@RequestBody BindingAuthFlagRequest request);

}
