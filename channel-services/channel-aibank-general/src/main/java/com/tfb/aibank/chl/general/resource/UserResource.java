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
package com.tfb.aibank.chl.general.resource;

import java.util.Date;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.tfb.aibank.chl.component.devicebinding.model.RetrieveUserBindingResponse;
import com.tfb.aibank.chl.component.userdatacache.model.EB552170Response;
import com.tfb.aibank.chl.general.qu001.model.InvestmentOverview;
import com.tfb.aibank.chl.general.resource.dto.AiDataSyncStatusModel;
import com.tfb.aibank.chl.general.resource.dto.ChangePasswordRecord;
import com.tfb.aibank.chl.general.resource.dto.ChangePasswordTips;
import com.tfb.aibank.chl.general.resource.dto.ChangeUuidAndPinBlockRequest;
import com.tfb.aibank.chl.general.resource.dto.ChangeUuidAndPinBlockResponse;
import com.tfb.aibank.chl.general.resource.dto.DoFastValidateUserRequest;
import com.tfb.aibank.chl.general.resource.dto.DoFastValidateUserResponse;
import com.tfb.aibank.chl.general.resource.dto.EB555692Request;
import com.tfb.aibank.chl.general.resource.dto.EB555692Response;
import com.tfb.aibank.chl.general.resource.dto.ExecuteTransferUserLoginResponse;
import com.tfb.aibank.chl.general.resource.dto.ExecuteTwoFactorAuthUserPostLoginRequest;
import com.tfb.aibank.chl.general.resource.dto.ExecuteTwoFactorAuthUserPostLoginResponse;
import com.tfb.aibank.chl.general.resource.dto.ExecuteUserLoginRequest;
import com.tfb.aibank.chl.general.resource.dto.ExecuteUserLoginResponse;
import com.tfb.aibank.chl.general.resource.dto.ExecuteUserLogoutRequest;
import com.tfb.aibank.chl.general.resource.dto.ExecuteUserLogoutResponse;
import com.tfb.aibank.chl.general.resource.dto.FEP512161Res;
import com.tfb.aibank.chl.general.resource.dto.FEP512166Res;
import com.tfb.aibank.chl.general.resource.dto.GetFastValidateUserRequest;
import com.tfb.aibank.chl.general.resource.dto.GetFastValidateUserResponse;
import com.tfb.aibank.chl.general.resource.dto.LineBindStatusResponse;
import com.tfb.aibank.chl.general.resource.dto.MbDeviceInfo;
import com.tfb.aibank.chl.general.resource.dto.OnboardingLogRequest;
import com.tfb.aibank.chl.general.resource.dto.QuickSearchResponse;
import com.tfb.aibank.chl.general.resource.dto.RateCardUser;
import com.tfb.aibank.chl.general.resource.dto.RetrieveMultiUserBindingResponse;
import com.tfb.aibank.chl.general.resource.dto.RetrieveUserHomePageCardResponse;
import com.tfb.aibank.chl.general.resource.dto.RetriveDeviceStatusResponse;
import com.tfb.aibank.chl.general.resource.dto.TwoFactorAuthUserRequest;
import com.tfb.aibank.chl.general.resource.dto.TwoFactorAuthUserResponse;
import com.tfb.aibank.chl.general.resource.dto.UnLoginEmailsResponse;
import com.tfb.aibank.chl.general.resource.dto.UpdateDataSyncResourceRequest;
import com.tfb.aibank.chl.general.resource.dto.UpdateMbDeviceInfoRequest;
import com.tfb.aibank.chl.general.resource.dto.UpdateNotficationNoPainRequest;
import com.tfb.aibank.chl.general.resource.dto.UpdateNotficationRequest;
import com.tfb.aibank.chl.general.resource.dto.UpdateNotficationResponse;
import com.tfb.aibank.chl.general.resource.dto.UpdatePatternlockRequest;
import com.tfb.aibank.chl.general.resource.dto.UpdatePatternlockResponse;
import com.tfb.aibank.chl.general.resource.dto.UpdatePwdValidTimeRequest;
import com.tfb.aibank.chl.general.resource.dto.UpdateUserDeviceBindingRequest;
import com.tfb.aibank.chl.general.resource.dto.UpdateUserHomePageCardRequest;
import com.tfb.aibank.chl.general.resource.dto.UserProfile;

//@formatter:off
/**
 * @(#)UserResource.java
 * 
 * <p>Description:溝通 service-aibank-user 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/22, HankChan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@FeignClient(name = "aibank-chl-general-to-user-service", url = "${aibank.common.feign.service-aibank-user-url:http://svc-biz-user:8080}")
public interface UserResource {

    /** EB5556981 客戶身分驗證 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/authentications/customer/add", produces = MediaType.APPLICATION_JSON_VALUE)
    ExecuteUserLoginResponse executeUserLogin(@RequestBody ExecuteUserLoginRequest request);

    /** 無痛移轉登入 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/no-pain/customer/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ExecuteTransferUserLoginResponse executeTransferUserLogin(//
            @RequestHeader("accessToken") String accessToken, //
            @RequestHeader("onboardingType") String onboardingType, //
            @RequestParam("deviceIxd") String deviceIxd);

    // 新增無痛移轉資料庫Log
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/no-pain/log/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> addOnboardingTransferLog(@RequestBody OnboardingLogRequest request);

    // 是否進行過無痛移轉")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/no-pain/log/query", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Boolean isAlreadyTransfered( //
            @RequestHeader("custId") String custId, //
            @RequestHeader("uidDup") String uidDup, //
            @RequestHeader("userId") String userId, //
            @RequestHeader("companyKind") Integer companyKind, //
            @RequestParam("deviceIxd") String deviceIxd //
    );

    /** 檢核是否變更過使用者代碼或使用者密碼註記 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/pwd-userid-changed/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean isHasChangedPwd(@RequestParam("deviceuuid") String deviceUuid);

    /** 登出 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/authentications/customer/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    ExecuteUserLogoutResponse executeUserLogout(@RequestBody ExecuteUserLogoutRequest request);

    /** 更新推播通知設定 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/notification/update", produces = MediaType.APPLICATION_JSON_VALUE)
    UpdateNotficationResponse updateNotfication(@RequestBody UpdateNotficationRequest request);

    /** 更新推播通知設定 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/linepnp/update", produces = MediaType.APPLICATION_JSON_VALUE)
    UpdateNotficationResponse updateLinePNP(@RequestBody UpdateNotficationRequest request);


    /** 更新移轉推播通知設定 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/transfer-notification/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public UpdateNotficationResponse updateNotfication4NoPain(UpdateNotficationNoPainRequest request);

    /** 變更使用者代號密碼 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/authentications/customer/update", produces = MediaType.APPLICATION_JSON_VALUE)
    ChangeUuidAndPinBlockResponse changeUuidAndPinBlock(@RequestBody ChangeUuidAndPinBlockRequest request);

    /** 重設密碼有效時間 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/authentications/secret/update", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean updatePwdValidTime(@RequestBody UpdatePwdValidTimeRequest request);

    /** 更新使用者裝置綁定資料 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/device-bindings/update", produces = MediaType.APPLICATION_JSON_VALUE)
    MbDeviceInfo updateUserDeviceBinding(@RequestHeader("custId") String custId, @RequestHeader("udiDup") String udiDup, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind, @RequestBody UpdateUserDeviceBindingRequest request);

    /** 取得使用者首頁牌卡設定 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/homepage-cards/get", produces = MediaType.APPLICATION_JSON_VALUE)
    RetrieveUserHomePageCardResponse retrieveUserHomePageCard(@RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @RequestParam("uidDup") String uidDup, @RequestParam("companyKind") Integer companyKind);

    /** 取得使用者首頁牌卡設定(未登入以綁定裝置查詢) */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/homepage-cards/{deviceUuid}/get", produces = MediaType.APPLICATION_JSON_VALUE)
    RetrieveUserHomePageCardResponse retrieveUserHomePageCardByDevice(@PathVariable("deviceUuid") String deviceUuid);

    /** 更新使用者首頁牌卡設定 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/homepage-cards/update", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean updateUserHomePageCard(@RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @RequestParam("uidDup") String uidDup, @RequestParam("companyKind") Integer companyKind, @RequestBody UpdateUserHomePageCardRequest request);

    /** 更新使用者首頁牌卡設定(未登入以綁定裝置更新) */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/homepage-cards/{deviceUuid}/update", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean updateUserHomePageCardByDevice(@PathVariable("deviceUuid") String deviceUuid, @RequestBody UpdateUserHomePageCardRequest request);

    /** 更新使用者大頭貼及暱稱設定 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/user-nickname/update", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean updateUserNickname(@RequestBody UserProfile m);

    /** 查詢客戶投資資產總覽 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/investment/overview/get", produces = MediaType.APPLICATION_JSON_VALUE)
    InvestmentOverview getInvestOverviewData(@RequestHeader("custId") String custId, @RequestHeader("nameCode") String nameCode, @RequestParam("dbu") boolean dbu, @RequestParam("haveCreditCard") boolean haveCreditCard);

    /** 查詢免登速查狀態及資訊 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/quick-search/status/get", produces = MediaType.APPLICATION_JSON_VALUE)
    QuickSearchResponse getQuickSearchStatus(@RequestHeader("deviceuuid") String deviceuuid);

    /** 更新快登圖形登入設定 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/patternlock/update", produces = MediaType.APPLICATION_JSON_VALUE)
    UpdatePatternlockResponse updatePatternlock(@RequestBody UpdatePatternlockRequest updatePatternlockRequest);

    /** 查詢網路銀行歸戶資產 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/user-deposit-overview/get", produces = MediaType.APPLICATION_JSON_VALUE)
    EB555692Response sendEB555692ForDepositAmount(@RequestBody EB555692Request eb555692Request);

    /** 查詢資料表(RATE_CARD_USER)，取得使用者首頁匯率幣別牌卡設定 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/rate-card-user/get", produces = MediaType.APPLICATION_JSON_VALUE)
    List<RateCardUser> retrieveUserRateCards(@RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind, @RequestParam("uidDup") String uidDup);

    /** 取得裝置綁定狀態 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/device-status/get", produces = MediaType.APPLICATION_JSON_VALUE)
    RetriveDeviceStatusResponse retriveDeviceStatus(@RequestHeader("deviceId") String deviceId, @RequestParam("phoneModel") String phoneModel, @RequestParam("phoneVersion") String phoneVersion);

    /**
     * 新增 資料表 UserRateCard
     */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/rate-card-users/add", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean addUserRateCard(@RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind, @RequestParam("uidDup") String uidDup, @RequestBody List<RateCardUser> models);

    /** 刪除資料(RATE_CARD_USER) */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/rate-card-users/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean deleteUserRateCards(@RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind, @RequestParam("uidDup") String uidDup);

    /** 取得多使用者代碼客戶綁定狀態 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/device-bindings/multi-user-status/get", produces = MediaType.APPLICATION_JSON_VALUE)
    RetrieveMultiUserBindingResponse retrieveMultiUserBinding(@RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind, @RequestParam("uidDup") String uidDup);

    /** 取得多使用者代碼註記 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/user-code-memos/get", produces = MediaType.APPLICATION_JSON_VALUE)
    EB552170Response sendEB552170ForSingleUser(@RequestHeader("custId") String custId, @RequestHeader("dup") String dup);

    /** 更新使用者裝置綁定資料 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/authentications/device-binding/status/update", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean updateMbDeviceInfo(@RequestBody UpdateMbDeviceInfoRequest request);

    /** 登入前取得email */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/emails/get", produces = MediaType.APPLICATION_JSON_VALUE)
    UnLoginEmailsResponse getUnLoginEmails(@RequestHeader("custId") String custId, @RequestParam("loginType") int loginType);

    /** 逾半年以上未變更網銀密碼記錄 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/account-security/change-password-tips/get", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ChangePasswordTips> getChangePasswordTips(@RequestHeader("custId") String custId, @RequestParam("uidDup") String uidDup, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind);

    /** 查詢變更密碼紀錄 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/account-security/change-password-record/get", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ChangePasswordRecord> getChangePasswordRecord(@RequestHeader("custId") String custId, @RequestParam("uidDup") String uidDup, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind);

    /** 取得使用者綁定資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/device-bindings/get", produces = MediaType.APPLICATION_JSON_VALUE)
    RetrieveUserBindingResponse retrieveUserBinding(@RequestHeader("custId") String custId, @RequestHeader("udiDup") String udiDup, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind, @RequestParam("locale") String locale);

    /** 檢核客戶是否已綁定LINE帳號 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/linebc/line-bind/get", produces = MediaType.APPLICATION_JSON_VALUE)
    LineBindStatusResponse checkLineBindStatus(@RequestHeader("custId") String custId);

    /** 是否有半年內 Line 拒絕紀錄 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/line-refuse-record/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean isRefuseLineRegister(@RequestHeader("custId") String custId);

    /** 檢查快速登入身分認證資料 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/fast-validate/query", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetFastValidateUserResponse getFastValidateUser(@RequestBody GetFastValidateUserRequest request);

    /** 快速身分認證 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/fast-validate/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public DoFastValidateUserResponse doFastValidateUser(@RequestBody DoFastValidateUserRequest request);

    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/data-sync/query", produces = MediaType.APPLICATION_JSON_VALUE)
    AiDataSyncStatusModel getUserDataSyncStatusInfo(@RequestHeader("custId") String custId, @RequestParam("dup") String dup, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind);

    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/data-sync/update", produces = MediaType.APPLICATION_JSON_VALUE)
    AiDataSyncStatusModel updateUserDataSyncStatus(@RequestBody UpdateDataSyncResourceRequest updateDataSyncResourceRequest);

    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/birthday/get", produces = MediaType.APPLICATION_JSON_VALUE)
    Date getCustomerBirthday(@RequestHeader("custId") String custId, @RequestParam("idType") String idType);

    /** 客戶分群跨行手續費優惠查詢 | [不分群轉帳/提款手續費優惠資訊] */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/fee-discounts/vip/get", produces = MediaType.APPLICATION_JSON_VALUE)
    FEP512166Res getVipFeeDiscounts(@RequestHeader("custId") String custId);

    /** 自動化手續費優惠查詢 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/fee-discounts/automation/get", produces = MediaType.APPLICATION_JSON_VALUE)
    FEP512161Res getAutomationFeeDiscounts(@RequestParam("acctId") String acctId);
    
    /** 雙重驗證成功後流程 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/authentications/two-factor-auth/post-login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ExecuteTwoFactorAuthUserPostLoginResponse executeTwoFactorAuthUserPostLogin(@RequestBody ExecuteTwoFactorAuthUserPostLoginRequest request) ;

    /** 信任裝置數量 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/trust-device/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer countTrustDeviceByUser(@RequestHeader("companyKey") Integer companyKey, @RequestHeader("userKey") Integer userKey);
    
    /** 雙重認證 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/two-factor/handle", produces = MediaType.APPLICATION_JSON_VALUE)
    public TwoFactorAuthUserResponse handleTwoFactorAuthentication(@RequestBody TwoFactorAuthUserRequest request);

}
