package com.tfb.aibank.biz.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.DecoderException;
import org.apache.xmlbeans.XmlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.util.JsonUtils;
import com.ibm.tw.ibmb.base.BaseController;
import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.ibm.tw.ibmb.util.MethodSecuiryCheckUtils;
import com.ibm.tw.ibmb.util.ValidateParamUtils;
import com.tfb.aibank.biz.user.services.ServiceFactory;
import com.tfb.aibank.biz.user.services.accountsecurity.AccountSecurityService;
import com.tfb.aibank.biz.user.services.accountsecurity.model.ChangePasswordRecordModel;
import com.tfb.aibank.biz.user.services.accountsecurity.model.ChangePasswordTipsModel;
import com.tfb.aibank.biz.user.services.bankingunit.BankingUnitService;
import com.tfb.aibank.biz.user.services.bpm.BPMService;
import com.tfb.aibank.biz.user.services.bpm.model.InvestmentDetailOverviewModel;
import com.tfb.aibank.biz.user.services.bpm.model.InvestmentOverviewModel;
import com.tfb.aibank.biz.user.services.communication.CommunicationInfoService;
import com.tfb.aibank.biz.user.services.communication.model.EB12020007Res;
import com.tfb.aibank.biz.user.services.companychange.CompanyChangeService;
import com.tfb.aibank.biz.user.services.costco.CostcoCardService;
import com.tfb.aibank.biz.user.services.costco.model.CEW466RRes;
import com.tfb.aibank.biz.user.services.costco.model.CardCostcoDuesModel;
import com.tfb.aibank.biz.user.services.custdatarecord.CustDataRecordService;
import com.tfb.aibank.biz.user.services.custdatarecord.model.ChangeCustDataRecordModel;
import com.tfb.aibank.biz.user.services.customergroupid.CustomerGroupIdService;
import com.tfb.aibank.biz.user.services.customergroupid.model.CustomerGroupIdModel;
import com.tfb.aibank.biz.user.services.datasync.AiDataSyncStatusService;
import com.tfb.aibank.biz.user.services.datasync.model.AiDataSyncStatusModel;
import com.tfb.aibank.biz.user.services.datasync.model.UpdateDataSyncResourceRequest;
import com.tfb.aibank.biz.user.services.debitcard.DebitCardService;
import com.tfb.aibank.biz.user.services.debitcard.model.DebitCardModel;
import com.tfb.aibank.biz.user.services.depositassets.DepositAssetService;
import com.tfb.aibank.biz.user.services.depositassets.model.EB555692Request;
import com.tfb.aibank.biz.user.services.depositassets.model.EB555692Response;
import com.tfb.aibank.biz.user.services.depositassets.model.InvAssetOverviewRequest;
import com.tfb.aibank.biz.user.services.depositassets.model.InvAssetOverviewResponse;
import com.tfb.aibank.biz.user.services.devicebindings.DeviceBindingsService;
import com.tfb.aibank.biz.user.services.devicebindings.model.MbDeviceInfoModel;
import com.tfb.aibank.biz.user.services.devicebindings.model.RetrieveDeviceBindingResponse;
import com.tfb.aibank.biz.user.services.devicebindings.model.RetrieveMultiUserBindingResponse;
import com.tfb.aibank.biz.user.services.devicebindings.model.RetrieveUserBindingResponse;
import com.tfb.aibank.biz.user.services.devicebindings.model.RetriveDeviceStatusResponse;
import com.tfb.aibank.biz.user.services.devicebindings.model.UpdateMbDeviceInfoRequest;
import com.tfb.aibank.biz.user.services.devicebindings.model.UpdateNotficationNoPainRequest;
import com.tfb.aibank.biz.user.services.devicebindings.model.UpdateNotficationRequest;
import com.tfb.aibank.biz.user.services.devicebindings.model.UpdateNotficationResponse;
import com.tfb.aibank.biz.user.services.devicebindings.model.UpdatePatternlockRequest;
import com.tfb.aibank.biz.user.services.devicebindings.model.UpdatePatternlockResponse;
import com.tfb.aibank.biz.user.services.devicebindings.model.UpdateUserDeviceBindingRequest;
import com.tfb.aibank.biz.user.services.email.EmailService;
import com.tfb.aibank.biz.user.services.email.model.ModifyEmailRequest;
import com.tfb.aibank.biz.user.services.email.model.SaveChangeCustDataRequest;
import com.tfb.aibank.biz.user.services.employee.EmpolyeeService;
import com.tfb.aibank.biz.user.services.employee.model.RTWEBP02ResRep;
import com.tfb.aibank.biz.user.services.employee.model.SavingTrustDetail;
import com.tfb.aibank.biz.user.services.encryptasset.EncryptAssetService;
import com.tfb.aibank.biz.user.services.feediscount.FeeDiscountService;
import com.tfb.aibank.biz.user.services.feediscount.model.FEP512161Response;
import com.tfb.aibank.biz.user.services.feediscount.model.FEP512166Response;
import com.tfb.aibank.biz.user.services.financialdataorder.FinancialDataOrderService;
import com.tfb.aibank.biz.user.services.financialdataorder.model.FinancialDataOrderModel;
import com.tfb.aibank.biz.user.services.homepagecard.HomePageCardService;
import com.tfb.aibank.biz.user.services.homepagecard.model.RetrieveUserHomePageCardResponse;
import com.tfb.aibank.biz.user.services.homepagecard.model.UpdateUserHomePageCardRequest;
import com.tfb.aibank.biz.user.services.identitytype.IdentityTypeService;
import com.tfb.aibank.biz.user.services.identitytype.model.FinancialIndustryModel;
import com.tfb.aibank.biz.user.services.inveligiblecheck.InvestEligibleCheckService;
import com.tfb.aibank.biz.user.services.inveligiblecheck.model.EB032675Res;
import com.tfb.aibank.biz.user.services.knowledgecheck.KnowledgeCheckService;
import com.tfb.aibank.biz.user.services.knowledgecheck.model.KnowledgeCheckRequest;
import com.tfb.aibank.biz.user.services.linebindcheck.LineBindCheckService;
import com.tfb.aibank.biz.user.services.linebindcheck.model.LineBindStatusResponse;
import com.tfb.aibank.biz.user.services.log.WBPlusAccessLogService;
import com.tfb.aibank.biz.user.services.log.model.WBPlusAccessLogRequest;
import com.tfb.aibank.biz.user.services.login.ExecuteChangeCcUserPinService;
import com.tfb.aibank.biz.user.services.login.ExecuteUserLoginService;
import com.tfb.aibank.biz.user.services.login.ExecuteUserLogoutService;
import com.tfb.aibank.biz.user.services.login.LoginSessionService;
import com.tfb.aibank.biz.user.services.login.model.ChangeUuidAndPinBlockRequest;
import com.tfb.aibank.biz.user.services.login.model.ChangeUuidAndPinBlockResponse;
import com.tfb.aibank.biz.user.services.login.model.EbLoginLogBRequest;
import com.tfb.aibank.biz.user.services.login.model.ExecuteChangeCcUserPinRequest;
import com.tfb.aibank.biz.user.services.login.model.ExecuteChangeCcUserPinResponse;
import com.tfb.aibank.biz.user.services.login.model.ExecuteUserLoginRequest;
import com.tfb.aibank.biz.user.services.login.model.ExecuteUserLoginResponse;
import com.tfb.aibank.biz.user.services.login.model.ExecuteUserLogoutRequest;
import com.tfb.aibank.biz.user.services.login.model.ExecuteUserLogoutResponse;
import com.tfb.aibank.biz.user.services.login.model.UnLoginEmailsResponse;
import com.tfb.aibank.biz.user.services.login.model.UpdatePwdValidTimeRequest;
import com.tfb.aibank.biz.user.services.nopain.NoPainTransferService;
import com.tfb.aibank.biz.user.services.nopain.model.ExecuteTransferUserLoginResponse;
import com.tfb.aibank.biz.user.services.nopain.model.OnboardingLogRequest;
import com.tfb.aibank.biz.user.services.otp.OtpService;
import com.tfb.aibank.biz.user.services.otp.model.RetrieveUserOTPStatusResponse;
import com.tfb.aibank.biz.user.services.push.PushService;
import com.tfb.aibank.biz.user.services.quicksearch.QuickSearchService;
import com.tfb.aibank.biz.user.services.quicksearch.model.QuickSearchModel;
import com.tfb.aibank.biz.user.services.ratecarduser.RateCardUserService;
import com.tfb.aibank.biz.user.services.ratecarduser.model.RateCardUserModel;
import com.tfb.aibank.biz.user.services.sso.SsoAuthService;
import com.tfb.aibank.biz.user.services.sso.model.DoFastValidateUserRequest;
import com.tfb.aibank.biz.user.services.sso.model.DoFastValidateUserResponse;
import com.tfb.aibank.biz.user.services.sso.model.FastLoginGetTokenResponse;
import com.tfb.aibank.biz.user.services.sso.model.GenerateTokenRequest;
import com.tfb.aibank.biz.user.services.sso.model.GenerateTokenResponse;
import com.tfb.aibank.biz.user.services.sso.model.GetFastValidateUserRequest;
import com.tfb.aibank.biz.user.services.sso.model.GetFastValidateUserResponse;
import com.tfb.aibank.biz.user.services.sso.model.GetSsoSettingRequest;
import com.tfb.aibank.biz.user.services.sso.model.GetSsoSettingResponse;
import com.tfb.aibank.biz.user.services.sso.model.GetTokenRequest;
import com.tfb.aibank.biz.user.services.sso.model.GetTokenResponse;
import com.tfb.aibank.biz.user.services.twofactor.TwoFactorAuthService;
import com.tfb.aibank.biz.user.services.twofactor.model.ExecuteTwoFactorAuthUserPostLoginRequest;
import com.tfb.aibank.biz.user.services.twofactor.model.ExecuteTwoFactorAuthUserPostLoginResponse;
import com.tfb.aibank.biz.user.services.twofactor.model.TwoFactorAuthUserRequest;
import com.tfb.aibank.biz.user.services.twofactor.model.TwoFactorAuthUserResponse;
import com.tfb.aibank.biz.user.services.unfatcaflag.UnFatcaFlagService;
import com.tfb.aibank.biz.user.services.user.UserService;
import com.tfb.aibank.biz.user.services.user.model.EB172650Res;
import com.tfb.aibank.biz.user.services.user.model.GesturesPwdRequest;
import com.tfb.aibank.biz.user.services.user.model.UserDataModel;
import com.tfb.aibank.biz.user.services.usermark.UserMarkService;
import com.tfb.aibank.biz.user.services.usermark.model.EB552170Response;
import com.tfb.aibank.biz.user.services.usermark.model.FC032155Response;
import com.tfb.aibank.biz.user.services.userprofile.UserProfileService;
import com.tfb.aibank.biz.user.services.userprofile.model.SaveW8BenDataRequest;
import com.tfb.aibank.biz.user.services.userprofile.model.UserProfileModel;
import com.tfb.aibank.biz.user.services.usualtask.UsualTaskService;
import com.tfb.aibank.biz.user.services.usualtask.model.UsualTaskModel;
import com.tfb.aibank.chl.component.userdatacache.model.CustomerInformation;
import com.tfb.aibank.chl.component.userdatacache.model.EB032151Req;
import com.tfb.aibank.chl.component.userdatacache.model.EB67115Res;
import com.tfb.aibank.chl.component.userdatacache.model.HasTrustAcct;
import com.tfb.aibank.common.type.DataSyncStatusUpdateType;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIResponseException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)ServiceUserController.java
 * 
 * <p>Description:使用者相關類 API 入口</p>
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
public class ServiceUserController extends BaseController {

    @Autowired
    private ServiceFactory serviceFactory;

    @Operation(summary = "查詢網路銀行歸戶資產")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/user-deposit-overview/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<EB555692Response> sendEB555692ForDepositAmount(@RequestBody EB555692Request eb555692Request) throws XmlException, EAIException, EAIResponseException, ActionException {
        DepositAssetService assetsService = serviceFactory.createDepositAssetService();
        return BaseServiceResponse.of(assetsService.sendEB555692ForDepositAmount(eb555692Request));
    }

    @Operation(summary = "驗證客戶身分")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/authentications/customer/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<ExecuteUserLoginResponse> executeUserLogin(@RequestBody ExecuteUserLoginRequest request) throws ActionException {
        ExecuteUserLoginRequest accessRequest = accessControl(request, ExecuteUserLoginRequest.class);
        ExecuteUserLoginService executeUserLoginService = serviceFactory.executeUserLoginService();
        return BaseServiceResponse.of(executeUserLoginService.executeUserLogin(accessRequest));
    }

    @Operation(summary = "新增登入Log")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/authentications/log/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> addEbLoginLogB(@RequestBody EbLoginLogBRequest request) throws ActionException {
        ExecuteUserLoginService executeUserLoginService = serviceFactory.executeUserLoginService();
        return BaseServiceResponse.of(executeUserLoginService.addEbLoginLogB(request));
    }

    @Operation(summary = "更換信用卡客戶密碼")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/authentications/cccustomer/changepin/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<ExecuteChangeCcUserPinResponse> executeChangeCcUserPin(@RequestBody ExecuteChangeCcUserPinRequest request) {
        ExecuteChangeCcUserPinService executeChangeCcUserPinService = serviceFactory.executeChangeUserPinService();
        return BaseServiceResponse.of(executeChangeCcUserPinService.executeChangeCcUserPin(request));
    }

    @Operation(summary = "登出")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/authentications/customer/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<ExecuteUserLogoutResponse> executeUserLogout(@RequestBody ExecuteUserLogoutRequest request) throws CryptException {
        ExecuteUserLogoutRequest accessRequest = accessControl(request, ExecuteUserLogoutRequest.class);
        ExecuteUserLogoutService executeUserLogoutService = serviceFactory.executeUserLogoutService();
        return BaseServiceResponse.of(executeUserLogoutService.executeUserLogout(accessRequest));
    }

    @Operation(summary = "登入前取得email")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/emails/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<UnLoginEmailsResponse> getUnLoginEmails(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestParam("loginType") @Schema(description = "登入身份") int loginType) {
        ExecuteUserLoginService executeUserLoginService = serviceFactory.executeUserLoginService();
        return BaseServiceResponse.of(executeUserLoginService.getUnLoginEmails(custId, loginType));
    }

    @Operation(summary = "取得Costco會員資訊")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/costco-member/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<CEW466RRes> getCostcoMemberInfo(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestParam("requestCode") @Schema(description = "查詢代碼") String requestCode, @RequestParam("autoRenew") @Schema(description = "更新代碼") String autoRenew) throws XmlException, EAIException, EAIResponseException {
        CostcoCardService costcoCardService = serviceFactory.createCostcoCardService();
        return BaseServiceResponse.of(costcoCardService.sendCEW466R(custId, requestCode, autoRenew));
    }

    @Operation(summary = "好市多會員自動續約通知")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/costco-member/notify", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<CEW466RRes> notifyCostcoMember(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestParam("requestCode") @Schema(description = "查詢代碼") String requestCode, @RequestParam("voabnd") @Schema(description = "處理結果") String voabnd) throws XmlException, EAIException, EAIResponseException {
        CostcoCardService costcoCardService = serviceFactory.createCostcoCardService();
        return BaseServiceResponse.of(costcoCardService.sendCEW4661R(custId, requestCode, voabnd));
    }

    @Operation(summary = "更新好市多卡交易紀錄檔")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/costco-member/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<CardCostcoDuesModel> saveCardCostcoDues(@RequestBody CardCostcoDuesModel model) throws XmlException, EAIException, EAIResponseException, CryptException {
        CostcoCardService costcoCardService = serviceFactory.createCostcoCardService();
        return BaseServiceResponse.of(costcoCardService.saveCardCostcoDues(model));
    }

    @Operation(summary = "取得裝置綁定狀態")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/device-status/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<RetriveDeviceStatusResponse> retriveDeviceStatus(@RequestHeader("deviceId") @Schema(description = "裝置ID") String deviceId, @RequestParam("phoneModel") @Schema(description = "裝置型號") String phoneModel, @RequestParam("phoneVersion") @Schema(description = "裝置版本") String phoneVersion) throws ActionException {
        deviceId = accessControl(deviceId, String.class);
        phoneModel = accessControl(phoneModel, String.class);
        phoneVersion = accessControl(phoneVersion, String.class);
        DeviceBindingsService service = serviceFactory.createDeviceService();
        return BaseServiceResponse.of(service.retriveDeviceStatus(deviceId, phoneModel, phoneVersion));
    }

    @Operation(summary = "無痛移轉登入")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/no-pain/customer/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<ExecuteTransferUserLoginResponse> executeTransferUserLogin(@RequestHeader("accessToken") String accessToken, @RequestHeader("onboardingType") String onboardingType, @RequestParam("deviceIxd") String deviceIxd) throws ActionException {
        accessToken = accessControl(accessToken, String.class);
        onboardingType = accessControl(onboardingType, String.class);
        deviceIxd = accessControl(deviceIxd, String.class);
        NoPainTransferService service = serviceFactory.createNoPainTransferService();
        return BaseServiceResponse.of(service.executeTransferUserLogin(accessToken, onboardingType, deviceIxd));
    }

    @Operation(summary = "新增無痛移轉資料庫Log")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/no-pain/log/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> addOnboardingTransferLog(@RequestBody OnboardingLogRequest request) throws ActionException, CryptException {
        NoPainTransferService service = serviceFactory.createNoPainTransferService();
        return BaseServiceResponse.of(service.addOnboardingTransferLog(request));
    }

    @Operation(summary = "是否進行過無痛移轉")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/no-pain/log/query", produces = { MediaType.APPLICATION_JSON_VALUE })
    public BaseServiceResponse<Boolean> isAlreadyTransfered( //
            @RequestHeader("custId") @Schema(description = "身份證字號") String custId, //
            @RequestHeader("uidDup") @Schema(description = "誤別碼") String uidDup, //
            @RequestHeader("userId") @Schema(description = "使用者代號") String userId, //
            @RequestHeader("companyKind") @Schema(description = "公司類型") Integer companyKind, //
            @RequestParam("deviceIxd") @Schema(description = "功能代碼") String deviceIxd //
    ) throws ActionException, CryptException {
        NoPainTransferService service = serviceFactory.createNoPainTransferService();
        return BaseServiceResponse.of(service.isAlreadyTransfered(custId, uidDup, userId, companyKind, deviceIxd));
    }

    @Operation(summary = "更新使用者裝置綁定資料")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/authentications/device-binding/status/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> updateMbDeviceInfo(@RequestBody UpdateMbDeviceInfoRequest request) throws ActionException {
        DeviceBindingsService deviceService = serviceFactory.createDeviceService();
        return BaseServiceResponse.of(deviceService.updateMbDeviceInfo(request));
    }

    @Operation(summary = "變更使用者代號密碼")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/authentications/customer/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<ChangeUuidAndPinBlockResponse> changeUuidAndPinBlock(@RequestBody ChangeUuidAndPinBlockRequest request) throws CryptException, ActionException, XmlException, EAIException, EAIResponseException {
        ExecuteUserLoginService executeUserLoginService = serviceFactory.executeUserLoginService();
        return BaseServiceResponse.of(executeUserLoginService.changeUuidAndPinBlock(request));
    }

    @Operation(summary = "重設密碼有效時間")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/authentications/secret/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> updatePwdValidTime(@RequestBody UpdatePwdValidTimeRequest request) {
        ExecuteUserLoginService executeUserLoginService = serviceFactory.executeUserLoginService();
        return BaseServiceResponse.of(executeUserLoginService.updatePwdValidTime(request));
    }

    @Operation(summary = "查詢客戶投資資產總覽")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/user-assets-overview/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<InvAssetOverviewResponse> queryUserAssetOverview(@RequestHeader("custIxd") String custIxd, @RequestHeader("custPxd") String custPxd, @RequestBody InvAssetOverviewRequest req) throws XmlException, EAIException, EAIResponseException, JsonProcessingException {
        DepositAssetService assetsService = serviceFactory.createDepositAssetService();

        req.setCustIxd(custIxd);
        req.setCustPswd32(custPxd);

        InvAssetOverviewResponse response = assetsService.queryAllAssetsAmount(req);

        return BaseServiceResponse.of(response);
    }

    @Operation(summary = "儲存使用者投資商品排序")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/prod-data-order/{companyKind}/add", produces = { MediaType.APPLICATION_JSON_VALUE })
    public BaseServiceResponse<FinancialDataOrderModel> saveUserProdDataOrder(@RequestBody FinancialDataOrderModel data, @RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("userId") @Schema(description = "使用者代號") String userId, @PathVariable("companyKind") @Schema(description = "公司類型") Integer companyKind, @RequestParam("uidDup") @Schema(description = "誤別碼") String uidDup) throws ActionException {
        FinancialDataOrderModel accessRequest = accessControl(data, FinancialDataOrderModel.class);
        Integer accessCompanyKind = accessControl(companyKind, Integer.class);
        FinancialDataOrderService service = serviceFactory.createFinancialDataOrderService();
        return BaseServiceResponse.of(service.saveData(accessRequest, ValidateParamUtils.validParam(custId), ValidateParamUtils.validParam(userId), accessCompanyKind, ValidateParamUtils.validParam(uidDup)));
    }

    @Operation(summary = "取得使用者投資商品排序")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/prod-data-order/{companyKind}/get", produces = { MediaType.APPLICATION_JSON_VALUE })
    public BaseServiceResponse<List<FinancialDataOrderModel>> getUserProdDataOrder(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("userId") @Schema(description = "使用者代號") String userId, @PathVariable("companyKind") @Schema(description = "公司類型") Integer companyKind, @RequestParam("uidDup") @Schema(description = "誤別碼") String uidDup, @RequestParam("taskId") @Schema(description = "功能代碼") String taskId) throws ActionException {
        custId = accessControl(custId, String.class);
        userId = accessControl(userId, String.class);
        companyKind = accessControl(companyKind, Integer.class);
        uidDup = accessControl(uidDup, String.class);
        taskId = accessControl(taskId, String.class);
        FinancialDataOrderService service = serviceFactory.createFinancialDataOrderService();
        return BaseServiceResponse.of(service.findByUser(custId, userId, uidDup, companyKind, taskId));
    }

    @Operation(summary = "查詢客戶投資商品市值大於0")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/investment/overview-avalible-card/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<InvestmentOverviewModel> getMarketValueGreater0(@RequestHeader("custId") String custId, @RequestHeader("nameCode") String nameCode, @RequestParam("dbu") boolean dbu, @RequestParam("haveCreditCard") boolean haveCreditCard) throws XmlException, EAIException, EAIResponseException {
        BPMService service = serviceFactory.createBPMService();
        return BaseServiceResponse.of(service.getMarketValueGreater0(custId, nameCode, dbu, haveCreditCard));
    }

    @Operation(summary = "取得使用者OTP狀態")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/otp-status/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<RetrieveUserOTPStatusResponse> retrieveUserOTPStatus(@RequestHeader("userId") @Schema(description = "身份證字號") String userId, @RequestHeader("userIdDup") @Schema(description = "誤別碼") String userIdDup, @RequestHeader("userUuid") @Schema(description = "使用者代號") String userUuid, @RequestParam("nameCode") @Schema(description = "使用者所屬戶名代碼") String nameCode) throws XmlException, EAIException, EAIResponseException {
        OtpService service = serviceFactory.createOtpService();
        return BaseServiceResponse.of(service.retrieveUserOTPStatus(userId, userIdDup, userUuid, nameCode));
    }

    @Operation(summary = "查詢資料表(USUAL_TASK)，取得指定客戶的常用功能資料")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/usual-task/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<List<UsualTaskModel>> getUsualTasks(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("userId") @Schema(description = "使用者代號") String userId, @RequestParam("uidDup") @Schema(description = "誤別碼") String uidDup, @RequestParam("companyKind") @Schema(description = "公司類型") Integer companyKind) throws ActionException {
        UsualTaskService service = serviceFactory.createUsualTaskService();
        return BaseServiceResponse.of(service.getUserUsualTasks(custId, userId, uidDup, companyKind));
    }

    @Operation(summary = "更新指定客戶的常用功能資料")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/usual-task/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<List<UsualTaskModel>> updateUsualTasks(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("userId") @Schema(description = "使用者代號") String userId, @RequestParam("uidDup") @Schema(description = "誤別碼") String uidDup, @RequestParam("companyKind") @Schema(description = "公司類型") Integer companyKind, @RequestBody List<UsualTaskModel> tasks) throws ActionException {
        UsualTaskService service = serviceFactory.createUsualTaskService();
        return BaseServiceResponse.of(service.updateUserUsualTasks(custId, userId, uidDup, companyKind, tasks));
    }

    @Operation(summary = "取得使用者綁定資料")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/device-bindings/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<RetrieveUserBindingResponse> retrieveUserBinding(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("udiDup") @Schema(description = "誤別碼") String udiDup, @RequestHeader("userId") @Schema(description = "使用者代號") String userId, @RequestParam("companyKind") @Schema(description = "公司類型") Integer companyKind, @RequestParam("locale") @Schema(description = "語系") String locale) throws ActionException {
        DeviceBindingsService service = serviceFactory.createDeviceService();
        return BaseServiceResponse.of(service.retrieveUserBinding(custId, udiDup, userId, companyKind, locale));
    }

    @Operation(summary = "取得使用者綁定資料(custId,userId)")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/device-bindings-info/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<RetrieveUserBindingResponse> retrieveUserBindingInfo(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("userId") @Schema(description = "使用者代號") String userId) throws ActionException, CryptException, DecoderException {
        DeviceBindingsService service = serviceFactory.createDeviceService();
        /**
         * uidDup(現階段不考慮身分證重複誤別碼問題, 所以先帶0), companyKind帶入2(此方法是取的使用者資料, 所以不會使用公司戶 公司戶為1, 個人戶為2), TODO companyKind帶入個人戶2會有無法查詢公司戶1的風險，評估於Phase3討論做法 locale帶入zh_TW(在retrieveUserBinding裡面只有用來判斷是否為空沒有其他用途, 所以先帶入zh_TW或是任何值)
         */
        return BaseServiceResponse.of(service.retrieveUserBindingInfo(custId, "0", userId, 2, "zh_TW"));
    }

    @Operation(summary = "取得裝置綁定資料")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/device-bindings/{deviceUuid}/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<RetrieveDeviceBindingResponse> retrieveDeviceBinding(@PathVariable("deviceUuid") @Schema(description = "行動裝置UUID") String deviceUuid) throws ActionException {
        deviceUuid = accessControl(deviceUuid, String.class);
        DeviceBindingsService service = serviceFactory.createDeviceService();
        return BaseServiceResponse.of(service.retrieveDeviceBinding(deviceUuid));
    }

    @Operation(summary = "取得多使用者代碼客戶綁定狀態")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/device-bindings/multi-user-status/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<RetrieveMultiUserBindingResponse> retrieveMultiUserBinding(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("userId") @Schema(description = "使用者代號") String userId, @RequestParam("companyKind") @Schema(description = "公司類型") Integer companyKind, @RequestParam("uidDup") @Schema(description = "誤別碼") String uidDup) throws ActionException, CryptException {
        DeviceBindingsService service = serviceFactory.createDeviceService();
        return BaseServiceResponse.of(service.retrieveMultiUserBinding(custId, userId, companyKind, uidDup));
    }

    @Operation(summary = "更新使用者裝置綁定資料")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/device-bindings/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<MbDeviceInfoModel> updateUserDeviceBinding(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("udiDup") @Schema(description = "誤別碼") String udiDup, @RequestHeader("userId") @Schema(description = "使用者代號") String userId, @RequestParam("companyKind") @Schema(description = "公司類型") Integer companyKind, @RequestBody UpdateUserDeviceBindingRequest request) throws ActionException {
        DeviceBindingsService service = serviceFactory.createDeviceService();
        return BaseServiceResponse.of(service.updateUserDeviceBinding(custId, udiDup, userId, companyKind, request));
    }

    @Operation(summary = "查詢資料表(HOMEPAGE_CARD_USER)，取得使用者首頁牌卡設定")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/homepage-cards/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<RetrieveUserHomePageCardResponse> retrieveUserHomePageCard(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("userId") @Schema(description = "使用者代號") String userId, @RequestParam("uidDup") @Schema(description = "誤別碼") String uidDup, @RequestParam("companyKind") @Schema(description = "公司類型") Integer companyKind) throws ActionException {
        HomePageCardService service = serviceFactory.createHomePageCardService();
        return BaseServiceResponse.of(service.retrieveUserHomepageCard(custId, userId, uidDup, companyKind));
    }

    @Operation(summary = "查詢資料表(HOMEPAGE_CARD_USER)，取得使用者首頁牌卡設定(未登入以綁定裝置查詢)")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/homepage-cards/{deviceUuid}/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<RetrieveUserHomePageCardResponse> retrieveUserHomePageCardByDevice(@PathVariable("deviceUuid") @Schema(description = "行動裝置UUID") String deviceUuid) throws ActionException {
        deviceUuid = accessControl(deviceUuid, String.class);
        HomePageCardService service = serviceFactory.createHomePageCardService();
        return BaseServiceResponse.of(service.retrieveUserHomepageCardByDevice(deviceUuid));
    }

    @Operation(summary = "更新資料表(HOMEPAGE_CARD_USER)，更新使用者首頁牌卡設定")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/homepage-cards/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> updateUserHomePageCard(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("userId") @Schema(description = "使用者代號") String userId, @RequestParam("uidDup") @Schema(description = "誤別碼") String uidDup, @RequestParam("companyKind") @Schema(description = "公司類型") Integer companyKind, @RequestBody UpdateUserHomePageCardRequest request) throws ActionException {
        HomePageCardService service = serviceFactory.createHomePageCardService();
        return BaseServiceResponse.of(service.updateUserHomePageCard(custId, userId, uidDup, companyKind, request));
    }

    @Operation(summary = "更新資料表(HOMEPAGE_CARD_USER)，更新使用者首頁牌卡設定(未登入以綁定裝置更新)")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/homepage-cards/{deviceUuid}/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> updateUserHomePageCardByDevice(@PathVariable("deviceUuid") @Schema(description = "行動裝置UUID") String deviceUuid, @RequestBody UpdateUserHomePageCardRequest request) throws ActionException {
        deviceUuid = accessControl(deviceUuid, String.class);
        HomePageCardService service = serviceFactory.createHomePageCardService();
        return BaseServiceResponse.of(service.updateUserHomePageCardByDevice(deviceUuid, request));
    }

    @Operation(summary = "查詢客戶分群跨行手續費優惠")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/fee-discounts/vip/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<FEP512166Response> getVipFeeDiscounts(@RequestHeader("custId") String custId) throws XmlException, EAIException, EAIResponseException {
        FeeDiscountService service = serviceFactory.createFeeDiscountService();
        return BaseServiceResponse.of(service.sendFEP512166(custId));
    }

    @Operation(summary = "查詢自動化手續費優惠")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/fee-discounts/automation/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<FEP512161Response> getAutomationFeeDiscounts(@RequestParam("acctId") String acctId) throws XmlException, EAIException, EAIResponseException {
        FeeDiscountService service = serviceFactory.createFeeDiscountService();
        return BaseServiceResponse.of(service.sendFEP512161(acctId));
    }

    @Operation(summary = "查詢客戶投資資產總覽")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/investment/overview/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<InvestmentOverviewModel> getInvestOverviewData(@RequestHeader("custId") String custId, @RequestHeader("nameCode") String nameCode, @RequestParam("dbu") boolean dbu, @RequestParam("haveCreditCard") boolean haveCreditCard) throws XmlException, EAIException, EAIResponseException {
        BPMService service = serviceFactory.createBPMService();
        return BaseServiceResponse.of(service.getUserInvestOverviewValue(custId, nameCode, dbu, haveCreditCard));
    }

    @Operation(summary = "查詢免登速查狀態及資訊")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/quick-search/status/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<QuickSearchModel> getQuickSearchStatus(@RequestHeader("deviceuuid") String deviceuuid) {
        deviceuuid = accessControl(deviceuuid, String.class);
        QuickSearchService service = serviceFactory.createQuickSearchService();
        return BaseServiceResponse.of(service.getQuickSearchStatus(deviceuuid));
    }

    @Operation(summary = "更新快登圖形登入設定")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/patternlock/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<UpdatePatternlockResponse> updatePatternlock(@RequestBody UpdatePatternlockRequest updatePatternlockRequest) throws ActionException {
        DeviceBindingsService service = serviceFactory.createDeviceService();
        return BaseServiceResponse.of(service.updatePatternlock(updatePatternlockRequest));
    }

    @Operation(summary = "更新推播通知設定")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/notification/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<UpdateNotficationResponse> updateNotfication(@RequestBody UpdateNotficationRequest request) throws ActionException {
        request = accessControl(request, UpdateNotficationRequest.class);
        DeviceBindingsService service = serviceFactory.createDeviceService();
        return BaseServiceResponse.of(service.updateNotfication(request));
    }

    @Operation(summary = "更新LinePNP設定")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/linepnp/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateLinePNP(@RequestBody UpdateNotficationRequest request) throws ActionException {
        request = accessControl(request, UpdateNotficationRequest.class);
        DeviceBindingsService service = serviceFactory.createDeviceService();
        service.updateLinePNP(request);
    }

    @Operation(summary = "更新移轉推播通知設定")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/transfer-notification/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<UpdateNotficationResponse> updateNotfication4NoPain(@RequestBody UpdateNotficationNoPainRequest request) throws ActionException {
        request = accessControl(request, UpdateNotficationNoPainRequest.class);
        DeviceBindingsService service = serviceFactory.createDeviceService();
        return BaseServiceResponse.of(service.updateNotfication4NoPain(request));
    }

    @Operation(summary = "查詢資料表(RATE_CARD_USER)，取得使用者首頁匯率幣別牌卡設定")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/rate-card-user/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<List<RateCardUserModel>> retrieveUserRateCards(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("userId") @Schema(description = "使用者代號") String userId, @RequestParam("companyKind") @Schema(description = "公司類型") Integer companyKind, @RequestParam("uidDup") String uidDup) throws ActionException {
        RateCardUserService service = serviceFactory.createRateCardUserService();
        return BaseServiceResponse.of(service.retrieveUserRateCards(custId, userId, companyKind, uidDup));
    }

    @Operation(summary = "查詢使用者BU類型")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/bu-type/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<String> getUserBuType(@RequestHeader(name = "custId", required = false) String custId, @RequestParam(name = "nameCode", required = false) String nameCode, @RequestParam(name = "acctNo", required = false) String acctNo) throws XmlException, EAIException, EAIResponseException, ActionException {
        BankingUnitService service = serviceFactory.createBankingUnitService();
        return BaseServiceResponse.of(service.getBuType(custId, nameCode, acctNo));
    }

    @Operation(summary = "取得本行客戶註記")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/user-marks/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<FC032155Response> sendFC032155(@RequestHeader(name = "custId") String custId) throws XmlException, EAIException, EAIResponseException {
        UserMarkService service = serviceFactory.createUserMarkService();
        return BaseServiceResponse.of(service.sendFC032155(custId));
    }

    @Operation(summary = "更新使用者大頭貼及暱稱設定")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/user-nickname/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> updateUserNickname(@RequestBody UserProfileModel m) throws ActionException {
        UserProfileService service = serviceFactory.createUserProfileService();
        return BaseServiceResponse.of(service.updateUserNickname(m));
    }

    @Operation(summary = "取得多使用者代碼註記")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/user-code-memos/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<EB552170Response> sendEB552170ForSingleUser(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("dup") @Schema(description = "誤別碼") String dup) throws XmlException, EAIException, EAIResponseException {
        UserMarkService service = serviceFactory.createUserMarkService();
        return BaseServiceResponse.of(service.sendEB552170ForSingleUser(custId, dup));
    }

    @Operation(summary = "取得是否金融同業身份")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/is-financial-institution-identity/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<FinancialIndustryModel> getFinancialIdentity(@RequestHeader("custId") @Schema(description = "身份證字號") String custId) throws XmlException, EAIException, EAIResponseException {
        IdentityTypeService service = serviceFactory.createIdentityTypeService();
        return BaseServiceResponse.of(service.isFinancialInstitutionIdentity(custId));
    }

    @Operation(summary = "刪除資料(RATE_CARD_USER)")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/rate-card-users/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> deleteUserRateCards(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("userId") @Schema(description = "使用者代號") String userId, @RequestParam("companyKind") @Schema(description = "公司類型") Integer companyKind, @RequestParam("uidDup") String uidDup) throws ActionException {
        RateCardUserService service = serviceFactory.createRateCardUserService();
        return BaseServiceResponse.of(service.deleteAllRateCardUserByUser(custId, userId, companyKind, uidDup));
    }

    @Operation(summary = "儲存資料(RATE_CARD_USER)")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/rate-card-users/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> addUserRateCards(@RequestHeader("custId") @Schema(description = "身份證字號") String custIxd, @RequestHeader("userId") @Schema(description = "使用者代號") String userIxd, @RequestParam("companyKind") @Schema(description = "公司類型") Integer companyKind, @RequestParam("uidDup") String uidDup, @RequestBody List<RateCardUserModel> models) throws ActionException {
        RateCardUserService service = serviceFactory.createRateCardUserService();
        return BaseServiceResponse.of(service.saveRateCardUser(custIxd, userIxd, companyKind, uidDup, models));
    }

    @Operation(summary = "檢核客戶是否已綁定LINE帳號")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/linebc/line-bind/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<LineBindStatusResponse> checkLineBindStatus(@RequestHeader("custId") String custIxd) {
        LineBindCheckService service = serviceFactory.createLineBindCheckService();
        return BaseServiceResponse.of(service.checkLineBindStatusOldCode(custIxd));
    }

    @Operation(summary = "創建變更個人資料紀錄")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/custdatarecord/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<ChangeCustDataRecordModel> createChangeCustDataRecord(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("udiDup") @Schema(description = "誤別碼") String udiDup, @RequestHeader("userId") @Schema(description = "使用者代號") String userId, @RequestParam("companyKind") @Schema(description = "公司類型") Integer companyKind, @RequestBody ChangeCustDataRecordModel model) throws CryptException {
        CustDataRecordService service = serviceFactory.createCustDataRecordService();

        return BaseServiceResponse.of(service.saveChangeCustDataRecord(custId, udiDup, userId, companyKind, model));
    }

    @Operation(summary = "更新變更個人資料紀錄")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/custdatarecord/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<ChangeCustDataRecordModel> updateChangeCustDataRecord(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("udiDup") @Schema(description = "誤別碼") String udiDup, @RequestHeader("userId") @Schema(description = "使用者代號") String userId, @RequestParam("companyKind") @Schema(description = "公司類型") Integer companyKind, @RequestBody ChangeCustDataRecordModel model) throws CryptException {
        CustDataRecordService service = serviceFactory.createCustDataRecordService();
        return BaseServiceResponse.of(service.saveChangeCustDataRecord(custId, udiDup, userId, companyKind, model));
    }

    @Operation(summary = "更新資料(COMPANY)email")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/company/email/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> updateCompanyEmail(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("udiDup") @Schema(description = "誤別碼") String udiDup, @RequestHeader("userId") @Schema(description = "使用者代號") String userId, @RequestParam("companyKind") @Schema(description = "公司類型") Integer companyKind, @RequestParam("newEmail") String email) throws CryptException {
        CompanyChangeService service = serviceFactory.createCompanyChangeService();
        return BaseServiceResponse.of(service.changeCompanyEmail(custId, udiDup, userId, companyKind, email));
    }

    @Operation(summary = "查詢客戶是否為行員")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/employees/status/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> isEmployee(@RequestHeader("custId") String custId) {
        EmpolyeeService service = serviceFactory.createEmpolyeeService();
        return BaseServiceResponse.of(service.isEmployee(custId));
    }

    @Operation(summary = "查詢客戶是否為'退休'行員")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/employees/status-retired/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> isRetiredEmployee(@RequestHeader("custId") String custId) {
        EmpolyeeService service = serviceFactory.createEmpolyeeService();
        return BaseServiceResponse.of(service.isRetiredEmployee(custId));
    }

    @Operation(summary = "註冊登入Session")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/accounts/user-session/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> registerAccessSession(@RequestHeader("custId") String custId, @RequestHeader("uidDup") String uidDup, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind, @RequestParam("loginLogPk") Integer loginLogPk, @RequestParam("sessionId") String sessionId) throws ActionException {
        custId = accessControl(custId, String.class);
        uidDup = accessControl(uidDup, String.class);
        userId = accessControl(userId, String.class);
        companyKind = accessControl(companyKind, Integer.class);
        loginLogPk = accessControl(loginLogPk, Integer.class);
        sessionId = accessControl(sessionId, String.class);
        LoginSessionService service = serviceFactory.createLoginSessionService();
        return BaseServiceResponse.of(service.registerAccessSession(custId, uidDup, userId, companyKind, loginLogPk, sessionId));
    }

    @Operation(summary = "Email資料重複判斷")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/check-duplication-email/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> checkEmailDuplication(@RequestHeader("custId") String custId, @RequestHeader("email") String email) throws XmlException, EAIException, EAIResponseException {
        EmailService service = serviceFactory.createEmailService();
        return BaseServiceResponse.of(service.checkEmailDuplication(custId, email));
    }

    @Operation(summary = "確認婚姻代碼")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/confirm-marriage-code/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> sendEB12020006(@RequestHeader("custId") String custId, @RequestHeader("email") String email, @RequestHeader("nameCode") String nameCode, @RequestParam("funcCod") String funcCod, @RequestParam("func") String func) throws XmlException, EAIException, EAIResponseException {
        EmailService service = serviceFactory.createEmailService();
        return BaseServiceResponse.of(service.sendEB12020006(custId, email, nameCode, funcCod, func));
    }

    @Operation(summary = "變更客戶信箱")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/user-email/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> modifyEmailByEB12020006(@RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @RequestHeader("email") String email, @RequestHeader(name = "oldCustEmail", required = false) String oldCustEmail, @RequestBody ModifyEmailRequest request) throws XmlException, EAIException, EAIResponseException {
        EmailService service = serviceFactory.createEmailService();
        return BaseServiceResponse.of(service.modifyEmailByEB12020006(custId, userId, email, oldCustEmail, request));
    }

    @Operation(summary = "變更客戶信箱(EB12020061)")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/user-email/updateForUnc", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> modifyEmailByEB12020061(@RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @RequestHeader("email") String email, @RequestHeader(name = "oldCustEmail", required = false) String oldCustEmail, @RequestBody ModifyEmailRequest request) throws XmlException, EAIException, EAIResponseException {
        EmailService service = serviceFactory.createEmailService();
        return BaseServiceResponse.of(service.modifyEmailByEB12020061(custId, userId, email, oldCustEmail, request));
    }

    @Operation(summary = "EMAIL驗證平台")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/user-email-validation/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> modifyEmailByMVC110001(@RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @RequestHeader("email") String email, @RequestHeader(name = "oldCustEmail", required = false) String oldCustEmail, @RequestBody ModifyEmailRequest request) throws XmlException, EAIException, EAIResponseException {
        EmailService service = serviceFactory.createEmailService();
        return BaseServiceResponse.of(service.modifyEmailByMVC110001(custId, userId, email, oldCustEmail, request));
    }

    @Operation(summary = "變更個人Email紀錄")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/change-cust-data/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> saveChangeCustDataRecord(@RequestHeader("userId") String userId, @RequestHeader("email") String email, @RequestHeader(name = "oldMail", required = false) String oldMail, @RequestBody SaveChangeCustDataRequest request) throws CryptException {
        EmailService service = serviceFactory.createEmailService();
        return BaseServiceResponse.of(service.saveChangeCustDataRecord(userId, email, oldMail, request));
    }

    @Operation(summary = "更新登入Session")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/accounts/user-session/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> updateAccessTime(@RequestHeader("custId") String custId, @RequestHeader("uidDup") String uidDup, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind, @RequestParam("sessionId") String sessionId) throws ActionException {
        LoginSessionService service = serviceFactory.createLoginSessionService();
        return BaseServiceResponse.of(service.updateAccessTime(custId, uidDup, userId, companyKind, sessionId));
    }

    @Operation(summary = "取得所有簽帳卡卡面")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/debit-card/picture/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<List<DebitCardModel>> getAllDebitCardImg() {
        DebitCardService service = serviceFactory.createDebitCardService();
        return BaseServiceResponse.of(service.getAllDebitCardImg());
    }

    @Operation(summary = "逾半年以上未變更網銀密碼記錄")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/account-security/change-password-tips/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<List<ChangePasswordTipsModel>> getChangePasswordTips(@RequestHeader("custId") String custId, @RequestParam("uidDup") String uidDup, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind) throws ActionException {
        AccountSecurityService service = serviceFactory.createAccountSecurityService();
        return BaseServiceResponse.of(service.getChangePasswordTips(custId, uidDup, userId, companyKind));
    }

    @Operation(summary = "查詢變更密碼紀錄")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/account-security/change-password-record/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<List<ChangePasswordRecordModel>> getChangePasswordRecord(@RequestHeader("custId") String custId, @RequestParam("uidDup") String uidDup, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind) throws CryptException {
        AccountSecurityService service = serviceFactory.createAccountSecurityService();
        return BaseServiceResponse.of(service.getChangePasswordRecord(custId, uidDup, userId, companyKind));
    }

    @Operation(summary = "取得Table CUSTOMER_GROUP_ID(客群名單)全部資料")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer-group-id/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<List<CustomerGroupIdModel>> getAllCustomerGroupIds() throws ActionException {
        CustomerGroupIdService service = serviceFactory.createCustomerGroupIdService();
        return BaseServiceResponse.of(service.getAllDatas());
    }

    @Operation(summary = "SSO-產生Token")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/info/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<GenerateTokenResponse> generateToken(@RequestBody GenerateTokenRequest request) throws ActionException {
        SsoAuthService service = serviceFactory.createSsoAuthService();
        return BaseServiceResponse.of(service.generateToken(request));
    }

    @Operation(summary = "SSO-取得設定")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/sso/setting/query", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<GetSsoSettingResponse> getSsoSetting(@RequestBody GetSsoSettingRequest request) {
        SsoAuthService service = serviceFactory.createSsoAuthService();
        return BaseServiceResponse.of(service.getSsoSetting(request));
    }

    @Operation(summary = "SSO-取得使用者資訊")
    @PreAuthorize(MethodSecuiryCheckUtils.PERMIT_ALL)
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/info/query", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetTokenResponse getToken(@RequestBody GetTokenRequest request) throws ActionException, CryptException {
        SsoAuthService service = serviceFactory.createSsoAuthService();
        return service.getToken(request);
    }

    @Operation(summary = "查詢客戶各類投資資格註記(禁銷、FATCA、弱勢、專業投資人法人)")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/invest-eligible-data/query", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<EB032675Res> sendEB032675(@RequestHeader("custId") String custId) throws XmlException, EAIException, EAIResponseException {
        InvestEligibleCheckService service = serviceFactory.createInvestEligibleCheckService();
        return BaseServiceResponse.of(service.sendEB032675(custId));
    }

    @Operation(summary = "更新客戶各類投資資格註記")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/invest-eligible-data/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<EB032675Res> sendEB032675Modify(@RequestHeader("custId") String custId, @RequestParam("busAddr1") String busAddr1) throws EAIException, XmlException, EAIResponseException {
        InvestEligibleCheckService service = serviceFactory.createInvestEligibleCheckService();
        return BaseServiceResponse.of(service.sendEB032675Modify(custId, busAddr1));
    }

    @Operation(summary = "取得客戶是否具備FATCA排外註記")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/un-fatca-flag/query", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<EB67115Res> sendEB67115(@RequestHeader("custId") String custId) throws XmlException, EAIException, EAIResponseException {
        UnFatcaFlagService service = serviceFactory.createUnFatcaFlagService();
        return BaseServiceResponse.of(service.sendEB67115(custId));
    }

    @Operation(summary = "新增交易高齡認知檢核")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/knowledge-check/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> addKnowledgeCheck(@RequestBody KnowledgeCheckRequest request) throws CryptException {
        KnowledgeCheckService service = serviceFactory.createKnowledgeCheckService();
        return BaseServiceResponse.of(service.addKnowledgeCheck(request));
    }

    @Operation(summary = "檢核客戶是否開立信託帳號")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/has-trustAcct/query", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> hasTrustAcct(@RequestBody HasTrustAcct request) throws XmlException, EAIException, EAIResponseException {
        DepositAssetService service = serviceFactory.createDepositAssetService();
        return BaseServiceResponse.of(service.hasTrustAcct(request));
    }

    @Operation(summary = "取得客戶基本資料(EB032151)")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/customer-information/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<CustomerInformation> getCustomerInformation(@RequestBody EB032151Req request) throws XmlException, EAIException, EAIResponseException {
        UserProfileService service = serviceFactory.createUserProfileService();
        return BaseServiceResponse.of(service.sendEB032151(request));
    }

    /** 存取簽署畫面資料檔Log檔 */
    @Operation(summary = "存取簽署畫面資料檔Log檔")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/w8ben/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> saveW8BenData(@RequestBody SaveW8BenDataRequest request) throws ActionException {
        UserProfileService service = serviceFactory.createUserProfileService();
        return BaseServiceResponse.of(service.saveW8BenData(request));
    }

    @Operation(summary = "SSO-取得使用者資訊")
    @PreAuthorize(MethodSecuiryCheckUtils.PERMIT_ALL)
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/info/sso_query", produces = MediaType.APPLICATION_JSON_VALUE)
    public FastLoginGetTokenResponse getSsoToken(@RequestBody GetTokenRequest request) throws ActionException, CryptException {
        SsoAuthService service = serviceFactory.createSsoAuthService();
        return service.getSsoToken(request);
    }

    @Operation(summary = "檢查快速登入身分認證資料")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/fast-validate/query", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<GetFastValidateUserResponse> getFastValidateUser(@RequestBody GetFastValidateUserRequest request) {
        SsoAuthService service = serviceFactory.createSsoAuthService();
        return BaseServiceResponse.of(service.getFastValidateUser(request));
    }

    @Operation(summary = "快速身分認證")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/fast-validate/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<DoFastValidateUserResponse> doFastValidateUser(@RequestBody DoFastValidateUserRequest request) throws ActionException {
        SsoAuthService service = serviceFactory.createSsoAuthService();
        return BaseServiceResponse.of(service.doFastValidateUser(request));
    }

    @Operation(summary = "查詢用戶自動彙整")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/data-sync/query", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<AiDataSyncStatusModel> getUserAiDataSyncStatusInfo(@RequestHeader("custId") String custId, @RequestParam("dup") String dup, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind) throws ActionException, CryptException {
        AiDataSyncStatusService service = serviceFactory.createAiDataSyncStatusService();
        return BaseServiceResponse.of(service.getUserDataSyncStatusInfo(custId, dup, userId, companyKind));
    }

    @Operation(summary = "更新富證富壽資料彙整狀態")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/data-sync/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<AiDataSyncStatusModel> updateUserAiDataSyncStatus(@RequestBody UpdateDataSyncResourceRequest updateDataSyncResourceRequest) throws ActionException, CryptException {
        AiDataSyncStatusService service = serviceFactory.createAiDataSyncStatusService();
        return BaseServiceResponse.of(service.updateUserDataSyncStatus(updateDataSyncResourceRequest));
    }

    @Operation(summary = "更新富證富壽資料彙整狀態(回傳更新結果)")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/data-sync-res/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<DataSyncStatusUpdateType> updateUserDataSyncStatusWithResponseType(@RequestBody UpdateDataSyncResourceRequest updateDataSyncResourceRequest) throws ActionException, CryptException {
        AiDataSyncStatusService service = serviceFactory.createAiDataSyncStatusService();
        return BaseServiceResponse.of(service.updateUserDataSyncStatusWithResponseType(updateDataSyncResourceRequest));
    }

    @Operation(summary = "新建一個model並關閉資料彙整")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/data-sync/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<AiDataSyncStatusModel> createNewDataStatusSync(@RequestHeader("custId") String custId, @RequestParam("dup") String dup, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind) throws CryptException, ActionException {
        AiDataSyncStatusService service = serviceFactory.createAiDataSyncStatusService();
        return BaseServiceResponse.of(service.createNewDataStatusSync(custId, dup, userId, companyKind));
    }

    @Operation(summary = "取得用戶是否為專業投資人")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/isProfessionalInvestor", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> isProfessionalInvestor(@RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @RequestParam("nameCode") String nameCode) throws XmlException, EAIException, EAIResponseException {
        InvestEligibleCheckService service = serviceFactory.createInvestEligibleCheckService();
        return BaseServiceResponse.of(service.isProfessionalInvestor(custId, userId, nameCode));
    }

    @Operation(summary = "授權之交易人員身份證字號檢核")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/authorizedIdVerification", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> isProfessionalInvestor(@RequestHeader("custId") String custId, @RequestParam("busAddr1") String busAddr1) throws XmlException, EAIException, EAIResponseException {
        InvestEligibleCheckService service = serviceFactory.createInvestEligibleCheckService();
        return BaseServiceResponse.of(service.authorizedIdVerification(custId, busAddr1));
    }

    @Operation(summary = "加密檔案路徑")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/encrypt/asset", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<String> genFileAesInfo(@RequestBody Map<String, Object> data) throws ActionException {
        EncryptAssetService service = serviceFactory.createEncryptAssetService();
        return BaseServiceResponse.of(service.genFileAesInfo(JsonUtils.getJson(data)));
    }

    @Operation(summary = "取得用戶生日")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/birthday/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Date> getCustomerBirthday(@RequestHeader("custId") String custId, @RequestParam("idType") String idType) {
        QuickSearchService service = serviceFactory.createQuickSearchService();
        return BaseServiceResponse.of(service.getBirthDayByEB67050(custId, idType));
    }

    @Operation(summary = "取的推播訂閱資料 By 行動裝置設定檔鍵值和推播代碼  ")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/device-status/{deviceuuid}/{pushCode}/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> retrivePushSubcriptionStatus(@PathVariable("deviceuuid") @Schema(description = "行動裝置uuid") String deviceUuid, @PathVariable("pushCode") @Schema(description = "推播代碼") String pushCode, @RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("udiDup") @Schema(description = "誤別碼") String udiDup, @RequestHeader("userId") @Schema(description = "使用者代號") String userId, @RequestParam("companyKind") @Schema(description = "公司類型") Integer companyKind) throws ActionException {
        deviceUuid = accessControl(deviceUuid, String.class);
        pushCode = accessControl(pushCode, String.class);
        custId = accessControl(custId, String.class);
        udiDup = accessControl(udiDup, String.class);
        userId = accessControl(userId, String.class);
        companyKind = accessControl(companyKind, Integer.class);
        DeviceBindingsService service = serviceFactory.createDeviceService();
        return BaseServiceResponse.of(service.isHasPushSubscription(deviceUuid, pushCode, custId, udiDup, userId, companyKind));
    }

    @Operation(summary = "是否有半年內 Line 拒絕紀錄")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/line-refuse-record/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> isRefuseLineRegister(@RequestHeader("custId") @Schema(description = "身份證字號") String custId) {
        DeviceBindingsService service = serviceFactory.createDeviceService();
        return BaseServiceResponse.of(service.isRefuseLineRegister(custId));
    }

    @Operation(summary = "更新推播設定狀態")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/device-status/{deviceuuid}/push/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> updateDevicePushStatus(@PathVariable("deviceuuid") @Schema(description = "行動裝置uuid") String deviceUuid, @RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("udiDup") @Schema(description = "誤別碼") String udiDup, @RequestHeader("userId") @Schema(description = "使用者代號") String userId, @RequestParam("companyKind") @Schema(description = "公司類型") Integer companyKind) throws ActionException {
        deviceUuid = accessControl(deviceUuid, String.class);
        custId = accessControl(custId, String.class);
        udiDup = accessControl(udiDup, String.class);
        userId = accessControl(userId, String.class);
        companyKind = accessControl(companyKind, Integer.class);
        DeviceBindingsService service = serviceFactory.createDeviceService();
        return BaseServiceResponse.of(service.updateDevicePushStatus(deviceUuid, custId, udiDup, userId, companyKind));
    }

    @Operation(summary = "檢核是否變更過使用者代碼或使用者密碼註記")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/pwd-userid-changed/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> isHasChangedPwd(@RequestParam("deviceuuid") @Schema(description = "行動裝置uuid") String deviceUuid) throws ActionException {
        deviceUuid = accessControl(deviceUuid, String.class);
        DeviceBindingsService service = serviceFactory.createDeviceService();
        return BaseServiceResponse.of(service.isHasChangedPwd(deviceUuid));
    }

    @Operation(summary = "更新推播Token")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/push/token/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> updateDevicePushStatus(@RequestHeader("deviceuuid") @Schema(description = "行動裝置uuid") String deviceUuid, @RequestHeader("pushToken") @Schema(description = "推播token") String pushToken) throws ActionException, CryptException {
        deviceUuid = accessControl(deviceUuid, String.class);
        pushToken = accessControl(pushToken, String.class);
        PushService service = serviceFactory.createPushService();
        return BaseServiceResponse.of(service.updatePushToken(deviceUuid, pushToken));
    }

    @Operation(summary = "取消推播設定狀態")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/device-status/{deviceuuid}/{pushCode}/push/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> deletePushSubcriptionStatus(@PathVariable("deviceuuid") @Schema(description = "行動裝置設定檔鍵值") String deviceUuid, @PathVariable("pushCode") @Schema(description = "推播代碼") String pushCode, @RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("udiDup") @Schema(description = "誤別碼") String udiDup, @RequestHeader("userId") @Schema(description = "使用者代號") String userId, @RequestParam("companyKind") @Schema(description = "公司類型") Integer companyKind) throws ActionException {
        deviceUuid = accessControl(deviceUuid, String.class);
        pushCode = accessControl(pushCode, String.class);
        custId = accessControl(custId, String.class);
        udiDup = accessControl(udiDup, String.class);
        userId = accessControl(userId, String.class);
        companyKind = accessControl(companyKind, Integer.class);
        DeviceBindingsService service = serviceFactory.createDeviceService();
        return BaseServiceResponse.of(service.cannelDevicePushStatus(deviceUuid, pushCode, custId, udiDup, userId, companyKind));
    }

    @Operation(summary = "客戶基本資料")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/customerinvestbaseinfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<EB032675Res> customerInvestBaseInfo(@RequestHeader("custId") String custId, @RequestParam("busAddr1") String busAddr1) throws XmlException, EAIException, EAIResponseException {
        InvestEligibleCheckService service = serviceFactory.createInvestEligibleCheckService();
        return BaseServiceResponse.of(service.customerInvestBaseInfo(custId, busAddr1));
    }

    @Operation(summary = "查詢網路銀行歸戶資產for資產負債分析")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/user/deposit-overview/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<EB555692Response> sendEB555692FrgUseZeroIfNegative(@RequestBody EB555692Request eb555692Request) throws XmlException, EAIException, EAIResponseException, ActionException {
        DepositAssetService assetsService = serviceFactory.createDepositAssetService();
        return BaseServiceResponse.of(assetsService.sendEB555692FrgUseZeroIfNegative(eb555692Request));
    }

    @Operation(summary = "投資分析明細查詢")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/investment-detail/overview/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<List<InvestmentDetailOverviewModel>> getInvestmentDetailOverview(@RequestHeader("custId") String custId, @RequestHeader("nameCode") String nameCode) throws XmlException, EAIException, EAIResponseException {
        BPMService service = serviceFactory.createBPMService();
        return BaseServiceResponse.of(service.getInvestmentDetailOverview(custId, nameCode));
    }

    @Operation(summary = "儲蓄信託明細")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/saving-details/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<List<SavingTrustDetail>> getSavingDetails(@RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @RequestHeader("nameCode") String nameCode, @RequestParam("beginYm") String beginYm, @RequestParam("endYm") String endYm, @RequestParam("cmpId") String cmpId) throws XmlException, EAIException, EAIResponseException {
        EmpolyeeService service = serviceFactory.createEmpolyeeService();
        return BaseServiceResponse.of(service.getSavingDetails(custId, userId, nameCode, beginYm, endYm, cmpId));
    }

    @Operation(summary = "員工持股信託公司查詢")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/share-hold/companies/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<List<RTWEBP02ResRep>> getShareHoldCompanies(@RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @RequestHeader("nameCode") String nameCode) throws XmlException, EAIException, EAIResponseException {
        EmpolyeeService service = serviceFactory.createEmpolyeeService();
        return BaseServiceResponse.of(service.getShareHoldCompanies(custId, userId, nameCode));
    }

    @Operation(summary = "查詢客戶通訊資料 EB12020007")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/basic-informations/communication/query", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<EB12020007Res> getShareHoldCompanies(@RequestHeader("custId") String custId, @RequestParam("nameCode") String nameCode) throws XmlException, EAIException, EAIResponseException {
        CommunicationInfoService service = serviceFactory.createCommunicationInfoService();
        return BaseServiceResponse.of(service.queryCommunicationInfo(custId, nameCode));
    }

    @Operation(summary = "新增Access Log")
    @PreAuthorize(MethodSecuiryCheckUtils.PERMIT_ALL)
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/wbplusaccesslog/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> addWBPlusAccessLog(@RequestBody WBPlusAccessLogRequest request) throws ActionException {
        WBPlusAccessLogService logService = serviceFactory.createWBPlusAccessLogService();
        return BaseServiceResponse.of(logService.addWBPlusAccessLog(request));
    }

    // ------------------------------------- 使用者資料for錢包用 -------------------------------------
    @Operation(summary = "查詢使用者身分證與使用者代碼")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/get-user-company", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<UserDataModel> getUserAndCompany(@RequestHeader("userKey") Integer userKey, @RequestHeader("companyKey") Integer companyKey) throws XmlException, EAIException, EAIResponseException {
        UserService service = serviceFactory.createUserService();
        return BaseServiceResponse.of(service.getUserAndCompany(userKey, companyKey));
    }

    @Operation(summary = "驗證手勢密碼設定")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/gestures-pwd/validate", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<String> verifyGesturesPwd(@RequestBody GesturesPwdRequest request) throws ActionException {
        UserService service = serviceFactory.createUserService();
        return BaseServiceResponse.of(service.verifyGesturesPwd(request));
    }
    
    
    @Operation(summary = "查詢信任裝置筆數")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/trust-device/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Integer> countTrustDeviceByUser(@RequestHeader("companyKey") Integer companyKey, @RequestHeader("userKey") Integer userKey)throws ActionException {
        TwoFactorAuthService twoFactorAuthService = serviceFactory.createTwoFactorAuthService();
        return BaseServiceResponse.of(twoFactorAuthService.countTrustDeviceByUser(companyKey, userKey));
    }

    @Operation(summary = "更新雙重驗證資訊")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/two-factor/handle", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<TwoFactorAuthUserResponse> handleTwoFactorAuthentication(@RequestBody TwoFactorAuthUserRequest request)throws ActionException {
        TwoFactorAuthService twoFactorAuthService = serviceFactory.createTwoFactorAuthService();
        return BaseServiceResponse.of(twoFactorAuthService.handleTwoFactorAuth(request));
    }

    @Operation(summary = "雙重驗證成功後流程")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/authentications/two-factor-auth/post-login", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<ExecuteTwoFactorAuthUserPostLoginResponse> executeTwoFactorAuthUserPostLogin(@RequestBody ExecuteTwoFactorAuthUserPostLoginRequest request) throws ActionException {
        ExecuteTwoFactorAuthUserPostLoginRequest accessRequest = accessControl(request, ExecuteTwoFactorAuthUserPostLoginRequest.class);
        ExecuteUserLoginService executeUserLoginService = serviceFactory.executeUserLoginService();
        return BaseServiceResponse.of(executeUserLoginService.executeTwoFactorAuthUserPostLogin( accessRequest));
    }




    @Operation(summary = "查詢客戶存款資訊 EB172650")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/basic-informations/deposit/query", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<EB172650Res> sendEB172650(@RequestParam("depositAcct") String depositAcct, @RequestParam("curCode") String curCode) throws XmlException, EAIException, EAIResponseException {
        UserService service = serviceFactory.createUserService();
        return BaseServiceResponse.of(service.sendEB172650(depositAcct, curCode));
    }

}
