package com.tfb.aibank.biz.security;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

import com.tfb.aibank.biz.security.services.fido2.Fido2Service;
import com.tfb.aibank.biz.security.services.fido2.model.RemoveUserFido2Request;
import com.tfb.aibank.biz.security.services.fido2.model.RemoveUserFido2Response;
import com.tfb.aibank.biz.security.services.fido2.model.Fido2PushNotifyRequest;
import com.tfb.aibank.biz.security.services.fido2.model.Fido2PushNotifyResponse;
import com.tfb.aibank.biz.security.services.fido2.model.QueryUserInfoFido2Request;
import com.tfb.aibank.biz.security.services.fido2.model.QueryUserInfoFido2Response;
import com.tfb.aibank.biz.security.services.fido2.model.UpdateCreditCardFido2FlagRequest;
import org.apache.xmlbeans.XmlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.ibmb.base.BaseController;
import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.tfb.aibank.biz.component.twcaapi.model.TwcaApiLoginRs;
import com.tfb.aibank.biz.component.twcaapi.model.TwcaApiQueryCertRs;
import com.tfb.aibank.biz.component.twcaapi.model.TwcaApiQueryVerifyResultRs;
import com.tfb.aibank.biz.component.twcaapi.model.TwcaApiRevokeCertRs;
import com.tfb.aibank.biz.security.gateway.model.CheckSecuirtyRulesRequest;
import com.tfb.aibank.biz.security.gateway.model.CheckSecuirtyRulesResponse;
import com.tfb.aibank.biz.security.gateway.model.DecryptRSAEncodedTextRequest;
import com.tfb.aibank.biz.security.gateway.model.DecryptRSAEncodedTextResponse;
import com.tfb.aibank.biz.security.gateway.model.EncodeWithSecretRequest;
import com.tfb.aibank.biz.security.gateway.model.EncodeWithSecretResponse;
import com.tfb.aibank.biz.security.gateway.model.ValidateWithPasswordRuleRequest;
import com.tfb.aibank.biz.security.gateway.model.ValidateWithPasswordRuleRequestResponse;
import com.tfb.aibank.biz.security.services.ServiceFactory;
import com.tfb.aibank.biz.security.services.devicebinding.DeviceBindingService;
import com.tfb.aibank.biz.security.services.devicebinding.model.AddMbDeviceInfoRequest;
import com.tfb.aibank.biz.security.services.devicebinding.model.BindingAuthFlagRequest;
import com.tfb.aibank.biz.security.services.devicebinding.model.BindingAuthFlagResponse;
import com.tfb.aibank.biz.security.services.devicebinding.model.DeleteMbDeviceInfoRequest;
import com.tfb.aibank.biz.security.services.devicebinding.model.UpdateLoginTypeRequest;
import com.tfb.aibank.biz.security.services.devicebinding.model.UpdateLoginTypeResponse;
import com.tfb.aibank.biz.security.services.fido.FidoService;
import com.tfb.aibank.biz.security.services.fido.model.DoRevokeRequest;
import com.tfb.aibank.biz.security.services.fido.model.DoRevokeResponse;
import com.tfb.aibank.biz.security.services.fido.model.LoginRequest;
import com.tfb.aibank.biz.security.services.fido.model.LoginResponse;
import com.tfb.aibank.biz.security.services.fido.model.QueryLogResponse;
import com.tfb.aibank.biz.security.services.fido.model.QueryVerifyResultRequest;
import com.tfb.aibank.biz.security.services.fido.model.QueryVerifyResultResponse;
import com.tfb.aibank.biz.security.services.mid.MidService;
import com.tfb.aibank.biz.security.services.mid.model.MidLoginRequest;
import com.tfb.aibank.biz.security.services.mid.model.MidLoginResponse;
import com.tfb.aibank.biz.security.services.mid.model.MidQueryVerifyResultRequest;
import com.tfb.aibank.biz.security.services.mid.model.MidQueryVerifyResultResponse;
import com.tfb.aibank.biz.security.services.motp.MotpAuthService;
import com.tfb.aibank.biz.security.services.motp.MotpBindService;
import com.tfb.aibank.biz.security.services.motp.MotpLogService;
import com.tfb.aibank.biz.security.services.motp.model.CheckMotpStatusRequest;
import com.tfb.aibank.biz.security.services.motp.model.CheckMotpStatusResponse;
import com.tfb.aibank.biz.security.services.motp.model.ConfirmBindDeviceRequest;
import com.tfb.aibank.biz.security.services.motp.model.ConfirmBindDeviceResponse;
import com.tfb.aibank.biz.security.services.motp.model.CreateBindDeviceRequest;
import com.tfb.aibank.biz.security.services.motp.model.CreateBindDeviceResponse;
import com.tfb.aibank.biz.security.services.motp.model.CreatePushOtpRequest;
import com.tfb.aibank.biz.security.services.motp.model.CreatePushOtpResponse;
import com.tfb.aibank.biz.security.services.motp.model.DeleteBindDeviceRequest;
import com.tfb.aibank.biz.security.services.motp.model.DeleteBindDeviceResponse;
import com.tfb.aibank.biz.security.services.motp.model.SaveMotpLogRequest;
import com.tfb.aibank.biz.security.services.motp.model.VerifyPushOtpRequest;
import com.tfb.aibank.biz.security.services.motp.model.VerifyPushOtpResponse;
import com.tfb.aibank.biz.security.services.otp.OtpBindService;
import com.tfb.aibank.biz.security.services.otp.model.ApplyOtpRequest;
import com.tfb.aibank.biz.security.services.otp.model.ApplyOtpResponse;
import com.tfb.aibank.biz.security.services.otp.model.DeleteOtpRequest;
import com.tfb.aibank.biz.security.services.otp.model.DeleteOtpResponse;
import com.tfb.aibank.biz.security.services.serviceImpl.E2eeService;
import com.tfb.aibank.biz.security.services.twcaapi.TwcaApiServise;
import com.tfb.aibank.biz.security.services.twcaapi.model.CertificateActLogModel;
import com.tfb.aibank.common.type.E2EEDBKeyType;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIResponseException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)ServiceSecurityController.java
 * 
 * <p>Description:安控類 API 入口</p>
 * <p>每支API請填寫說明，格式及內容示意如左 @Operation(summary = "{動詞} + ({名詞} 或 {形容詞})")</p>
 * <p>此類別作為API的接口，負責轉發請求。應避免在此類別中對資料本身進行加工處理，亦禁止@Autowired其他資源。</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/24, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@RestController
public class ServiceSecurityController extends BaseController {

    @Autowired
    private ServiceFactory serviceFactory;

    @Operation(summary = "E2EE取得 Public RSA Kxy")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1/publickey/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<String> getRSAPublicKxy() throws XmlException, EAIException, EAIResponseException, IOException, ActionException {
        E2eeService e2eeService = serviceFactory.getE2eeService();
        return BaseServiceResponse.of(e2eeService.getRsaPublicKxy());
    }

    @Operation(summary = "E2EE 解密 UID 跟 使用者代號")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/uid-uuid/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<DecryptRSAEncodedTextResponse> decryptRSAEncodedText(@RequestBody DecryptRSAEncodedTextRequest request) throws ActionException {
        E2eeService e2eeService = serviceFactory.getE2eeService();
        return BaseServiceResponse.of(e2eeService.decryptRSAEncodedText(request.getEncodedText(), request.getIsPwdWithTime()));
    }

    @Operation(summary = "E2EE 登入密碼轉換")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/secret/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<EncodeWithSecretResponse> encodewithSecret(@RequestBody EncodeWithSecretRequest request) throws ActionException {
        E2eeService e2eeService = serviceFactory.getE2eeService();
        return BaseServiceResponse.of(e2eeService.encodewithPasswordRule(request));
    }

    @Operation(summary = "登入後判斷是否需要修改密碼")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/secret-modify/check", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<CheckSecuirtyRulesResponse> checkSecuirtyRules(@RequestBody CheckSecuirtyRulesRequest request) throws ActionException {
        E2eeService e2eeService = serviceFactory.getE2eeService();
        return BaseServiceResponse.of(e2eeService.checkSecuirtyRules(request));
    }

    @Operation(summary = "密碼檢核")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/secret/check", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<ValidateWithPasswordRuleRequestResponse> validateWithPasswordRule(@RequestBody ValidateWithPasswordRuleRequest request) throws ActionException {
        E2eeService e2eeService = serviceFactory.getE2eeService();
        return BaseServiceResponse.of(e2eeService.validateWithPasswordRule(request));
    }

    @Operation(summary = "新密碼是否已到可寫入時間")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1/pwd-already-valid/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> isPasswordAdvancedEnable(@RequestHeader("companyUid") @Schema(description = "使用者ID") String companyUid) {
        E2eeService e2eeService = serviceFactory.getE2eeService();
        return BaseServiceResponse.of(e2eeService.isPasswordAdvancedEnable(companyUid));
    }

    @Operation(summary = "使用者是否已使用新密碼驗證流程")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1/user-pwd-advanced/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> isCCUserLoginWithNewPassword(@RequestHeader("userPwdFlg") @Schema(description = "新增密碼狀態欄位") String userPwdFlg, @RequestHeader("companyUid") @Schema(description = "使用者ID") String companyUid) {
        E2eeService e2eeService = serviceFactory.getE2eeService();
        return BaseServiceResponse.of(e2eeService.isCCUserLoginWithNewPassword(userPwdFlg, companyUid));
    }

    @Operation(summary = "提供判斷此平台是否已開發新密碼驗證流程，且已到達可使用新密碼流程的時間")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1/new-pwd-process/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> isCCUserNewLoginProcessEnable(@RequestHeader("companyUid") @Schema(description = "使用者ID") String companyUid) {
        E2eeService e2eeService = serviceFactory.getE2eeService();
        return BaseServiceResponse.of(e2eeService.isCCUserNewLoginProcessEnable(companyUid));
    }

    @Operation(summary = "E2EE取得 DB Kxy")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1/dbkey/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<String> getDBAccessKxy() throws ActionException {
        E2eeService e2eeService = serviceFactory.getE2eeService();
        return BaseServiceResponse.of(e2eeService.getDBAccessKxy());
    }

    @Operation(summary = "E2EE取得 DB OTP Kxy")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1/otpkey/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<String> getOTPAccessKxy() throws ActionException {
        E2eeService e2eeService = serviceFactory.getE2eeService();
        return BaseServiceResponse.of(e2eeService.getOTPAccessKxy());
    }

    @Operation(summary = "E2EE加密")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1/data/encrypt", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<String> encryptData(@RequestHeader("raw") @Schema(description = "待加密資料") String raw, @RequestParam(value = "cryptType", required = false) @Schema(description = "加密方法") E2EEDBKeyType cryptType) throws ActionException {
        E2eeService e2eeService = serviceFactory.getE2eeService();
        return BaseServiceResponse.of(e2eeService.encryptData(raw, cryptType));
    }

    @Operation(summary = "E2EE解密")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1/data/decrypt", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<String> decryptData(@RequestHeader("cipher") @Schema(description = "待解密資料") String cipher, @RequestParam(value = "charsetName", required = false) String charsetName, @RequestParam(value = "cryptType", required = false) @Schema(description = "加密方法") E2EEDBKeyType cryptType) throws ActionException {
        E2eeService e2eeService = serviceFactory.getE2eeService();
        return BaseServiceResponse.of(e2eeService.decryptData(cipher, charsetName, cryptType));
    }

    @Operation(summary = "FIDO 登入")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/fido-login/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<LoginResponse> fidoLogin(@RequestBody LoginRequest request) throws ActionException {
        FidoService fidoService = serviceFactory.getFidoService();
        return BaseServiceResponse.of(fidoService.login(request));
    }

    @Operation(summary = "FIDO 複驗交易結果")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/verify-rsult/check", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<QueryVerifyResultResponse> queryVerifyResult(@RequestBody QueryVerifyResultRequest request) throws ActionException {
        FidoService fidoService = serviceFactory.getFidoService();
        return BaseServiceResponse.of(fidoService.queryVerifyResult(request));
    }

    @Operation(summary = "FIDO 查詢Log")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1/log/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<QueryLogResponse> queryLog(@RequestHeader("custId") @Schema(description = "身份證字號") String custId) throws ActionException {
        FidoService e2eeService = serviceFactory.getFidoService();
        return BaseServiceResponse.of(e2eeService.queryLog(custId));
    }

    @Operation(summary = "FIDO 註銷")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/binding/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<DoRevokeResponse> doRevoke(@RequestBody DoRevokeRequest request) throws ActionException {
        FidoService fidoService = serviceFactory.getFidoService();
        return BaseServiceResponse.of(fidoService.doRevoke(request));
    }

    @Operation(summary = "驗證方式變更")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/binding/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<UpdateLoginTypeResponse> updateLoginType(@RequestBody UpdateLoginTypeRequest request) throws ActionException {
        DeviceBindingService deviceBindingService = serviceFactory.getDeviceBindingService();
        return BaseServiceResponse.of(deviceBindingService.updateLoginType(request));
    }

    @Operation(summary = "新增 MB_DEVICE_INFO")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/mb-device-info/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> addMbDeviceInfo(@RequestBody AddMbDeviceInfoRequest request) throws CryptException, ActionException {
        DeviceBindingService deviceBindingService = serviceFactory.getDeviceBindingService();
        return BaseServiceResponse.of(deviceBindingService.addMbDeviceInfo(request));
    }

    @Operation(summary = "刪除 MB_DEVICE_INFO")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/mb-device-info/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> deleteMbDeviceInfo(@RequestBody DeleteMbDeviceInfoRequest request) throws CryptException, ActionException {
        DeviceBindingService deviceBindingService = serviceFactory.getDeviceBindingService();
        return BaseServiceResponse.of(deviceBindingService.deleteMbDeviceInfo(request));
    }

    @Operation(summary = "查詢/設定裝置綁定授權註記")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/binding-auth-flag/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<BindingAuthFlagResponse> getBindingAuthFlag(@RequestBody BindingAuthFlagRequest request) throws CryptException, ActionException {
        request = accessControl(request, BindingAuthFlagRequest.class);
        DeviceBindingService deviceBindingService = serviceFactory.getDeviceBindingService();
        return BaseServiceResponse.of(deviceBindingService.getBindingAuthFlag(request));
    }

    @Operation(summary = "台網MID驗證 - Login")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/twidapi/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<MidLoginResponse> login(@RequestBody MidLoginRequest request) throws NoSuchAlgorithmException, ActionException {
        MidService service = serviceFactory.getMidService();
        return BaseServiceResponse.of(service.login(request));
    }

    @Operation(summary = "台網MID驗證 - QueryVerifyResult")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/twidapi/query-verify-result", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<MidQueryVerifyResultResponse> queryVerifyResult(@RequestBody MidQueryVerifyResultRequest request) throws NoSuchAlgorithmException, ActionException {
        MidQueryVerifyResultRequest accessRequest = accessControl(request, MidQueryVerifyResultRequest.class);
        MidService service = serviceFactory.getMidService();
        return BaseServiceResponse.of(service.queryVerifyResult(accessRequest));
    }

    @Operation(summary = "建立MOTP設備綁定")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/motp-bindings/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<CreateBindDeviceResponse> createBindDevice(@RequestBody CreateBindDeviceRequest request) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, ActionException, CryptException {
        request = accessControl(request, CreateBindDeviceRequest.class);
        MotpBindService service = serviceFactory.getMotpBindService();
        return BaseServiceResponse.of(service.createBindDevice(request));
    }

    @Operation(summary = "確認建立MOTP設備綁定")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/motp-bindings/confirm-create", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<ConfirmBindDeviceResponse> confirmBindDevice(@RequestBody ConfirmBindDeviceRequest request) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, CryptException, ActionException {
        ConfirmBindDeviceRequest accessRequest = accessControl(request, ConfirmBindDeviceRequest.class);
        MotpBindService service = serviceFactory.getMotpBindService();
        return BaseServiceResponse.of(service.confirmBindDevice(accessRequest));
    }

    @Operation(summary = "停用MOTP設備綁定")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/motp-bindings/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<DeleteBindDeviceResponse> deleteBindDevice(@RequestBody DeleteBindDeviceRequest request) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, CryptException, ActionException {
        MotpBindService service = serviceFactory.getMotpBindService();
        return BaseServiceResponse.of(service.deleteBindDevice(request));
    }

    @Operation(summary = "使用MOTP驗證前檢查狀態")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/motp-auths/check", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<CheckMotpStatusResponse> checkMotpStatus(@RequestBody CheckMotpStatusRequest request) throws ActionException {
        MotpAuthService service = serviceFactory.getMotpAuthService();
        return BaseServiceResponse.of(service.checkMotpStatus(request));
    }

    @Operation(summary = "發送推播OTP")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/motp-auths/send", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<CreatePushOtpResponse> createPushOtp(@RequestBody CreatePushOtpRequest request) throws ActionException {
        MotpAuthService service = serviceFactory.getMotpAuthService();
        return BaseServiceResponse.of(service.createPushOtp(request));
    }

    @Operation(summary = "驗證推播OTP")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/motp-auths/verify", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<VerifyPushOtpResponse> verifyPushOtp(@RequestBody VerifyPushOtpRequest request) throws ActionException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        VerifyPushOtpRequest accessRequest = accessControl(request, VerifyPushOtpRequest.class);
        MotpAuthService service = serviceFactory.getMotpAuthService();
        return BaseServiceResponse.of(service.verifyPushOtp(accessRequest));
    }

    @Operation(summary = "申請OTP")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/otp-bindings/apply", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<ApplyOtpResponse> applyOtp(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("uidDup") @Schema(description = "誤別碼") String uidDup, @RequestHeader("userId") @Schema(description = "使用者代號") String userId, @RequestBody ApplyOtpRequest request) throws XmlException, EAIException, EAIResponseException {
        OtpBindService service = serviceFactory.getOtpBindService();
        return BaseServiceResponse.of(service.applyOtp(custId, uidDup, userId, request));
    }

    @Operation(summary = "停用OTP")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/otp-bindings/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<DeleteOtpResponse> deleteOtp(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("uidDup") @Schema(description = "誤別碼") String uidDup, @RequestHeader("userId") @Schema(description = "使用者代號") String userId, @RequestBody DeleteOtpRequest request) throws XmlException, EAIException, EAIResponseException {
        OtpBindService service = serviceFactory.getOtpBindService();
        return BaseServiceResponse.of(service.deleteOtp(custId, uidDup, userId, request));
    }

    @Operation(summary = "寫入MOTP Log")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/motp-log/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> saveMotpLog(@RequestBody SaveMotpLogRequest request) {
        MotpLogService service = serviceFactory.getMotpLogService();
        return BaseServiceResponse.of(service.saveMotpLog(request));
    }

    @Operation(summary = "台網api-QueryCert 查詢憑證清單")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/twca/twid-api/query-cert", produces = { MediaType.APPLICATION_JSON_VALUE })
    public BaseServiceResponse<TwcaApiQueryCertRs> twcaQueryCert(@RequestHeader("custId") @Schema(description = "身份證字號") String custId) throws Exception {
        TwcaApiServise service = serviceFactory.createTwcaApiService();
        return BaseServiceResponse.of(service.queryCert(custId));
    }

    @Operation(summary = "台網api-Login 註冊憑證")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/twca/twid-api/login", produces = { MediaType.APPLICATION_JSON_VALUE })
    public BaseServiceResponse<TwcaApiLoginRs> twcaLogin(@RequestHeader("custId") @Schema(description = "身份證字號") String custId) throws Exception {
        TwcaApiServise service = serviceFactory.createTwcaApiService();
        return BaseServiceResponse.of(service.login(custId));
    }

    @Operation(summary = "台網api-QueryVerifyResult 複驗交易結果")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/twca/twid-api/query-verify-result", produces = { MediaType.APPLICATION_JSON_VALUE })
    public BaseServiceResponse<TwcaApiQueryVerifyResultRs> twcaQueryVerifyResult(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestParam("verifyNo") @Schema(description = "驗證編號") String verifyNo, @RequestParam("token") @Schema(description = "通行證") String token) throws Exception {
        TwcaApiServise service = serviceFactory.createTwcaApiService();
        return BaseServiceResponse.of(service.queryVerifyResult(custId, verifyNo, token));
    }

    @Operation(summary = "台網 註銷憑證 紀錄LOG")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/twca/delete-cert-log/save", produces = { MediaType.APPLICATION_JSON_VALUE })
    public BaseServiceResponse<CertificateActLogModel> saveCertificateLog(@RequestBody CertificateActLogModel model) throws Exception {
        TwcaApiServise service = serviceFactory.createTwcaApiService();
        return BaseServiceResponse.of(service.saveCertificateLog(model));
    }

    @Operation(summary = "台網api-RevokeCert 註銷憑證")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/twca/twid-api/revoke-cert", produces = { MediaType.APPLICATION_JSON_VALUE })
    public BaseServiceResponse<TwcaApiRevokeCertRs> twcaRevokeCert(@RequestParam("hexSerial") @Schema(description = "憑證序號") String hexSerial) throws Exception {
        TwcaApiServise service = serviceFactory.createTwcaApiService();
        return BaseServiceResponse.of(service.revokeCert(hexSerial));
    }

    @Operation(summary = "FIDO2 API 查詢會員狀態")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-card/fido2-flag/query-user-info", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<QueryUserInfoFido2Response> queryUserInfoFido2(@RequestBody QueryUserInfoFido2Request request) throws Exception {
        Fido2Service service = serviceFactory.getFido2Service();
        return BaseServiceResponse.of(service.queryUserInfoFido2(request));
    }

    @Operation(summary = "FIDO2 API 使用者註銷")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-card/fido2-flag/remove-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<RemoveUserFido2Response> removeUserFido2(@RequestBody RemoveUserFido2Request request) throws Exception {
        Fido2Service service = serviceFactory.getFido2Service();
        return BaseServiceResponse.of(service.removeUserFido2(request));
    }

    @Operation(summary = "FIDO2 推播狀態查詢")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-card/fido2-flag/push", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Fido2PushNotifyResponse> getPushSubscriptionStatus(@RequestBody Fido2PushNotifyRequest request) throws Exception {
        Fido2Service service = serviceFactory.getFido2Service();
        return BaseServiceResponse.of(service.getPushSubscriptionStatus(request));
    }

    @Operation(summary = "FIDO2 更新 AIBANK_MB_DEVICE_INFO 表 CREDIT_CARD_FIDO2_FLAG 註記")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-card/fido2-flag/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> updateCreditCardFido2Flag(@RequestBody UpdateCreditCardFido2FlagRequest request) throws Exception {
        Fido2Service service = serviceFactory.getFido2Service();
        return BaseServiceResponse.of(service.updateCreditCardFido2Flag(request));
    }
}
