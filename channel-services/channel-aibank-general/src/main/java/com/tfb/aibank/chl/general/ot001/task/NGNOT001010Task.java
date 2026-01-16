package com.tfb.aibank.chl.general.ot001.task;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.AESUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.type.SessionKey;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.biz.component.e2ee.E2EEUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.devicebinding.DeviceBindingService;
import com.tfb.aibank.chl.component.devicebinding.model.CheckUserDeviceStatusCondition;
import com.tfb.aibank.chl.component.devicebinding.model.CheckUserDeviceStatusResult;
import com.tfb.aibank.chl.component.devicebinding.type.UserDeviceBindStatusType;
import com.tfb.aibank.chl.component.devicemodel.DeviceModelCacheManager;
import com.tfb.aibank.chl.component.notification.model.CreatePersonalNotificationRequest;
import com.tfb.aibank.chl.component.satisfaction.AibankServiceSettingCacheManager;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.general.error.ErrorCode;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001010Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001010Rs;
import com.tfb.aibank.chl.general.ot001.task.service.DeviceBindingCache;
import com.tfb.aibank.chl.general.ot001.task.service.LoginMailData;
import com.tfb.aibank.chl.general.ot001.task.service.LoginMailType;
import com.tfb.aibank.chl.general.ot001.task.service.NGNOT001Service;
import com.tfb.aibank.chl.general.ot001.task.service.TwoFactorAuthCache;
import com.tfb.aibank.chl.general.resource.SecurityResource;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.resource.dto.ExecuteTransferUserLoginResponse;
import com.tfb.aibank.chl.general.resource.dto.ExecuteTwoFactorAuthUserPostLoginRequest;
import com.tfb.aibank.chl.general.resource.dto.ExecuteTwoFactorAuthUserPostLoginResponse;
import com.tfb.aibank.chl.general.resource.dto.RetriveDeviceStatusResponse;
import com.tfb.aibank.chl.general.service.LoginService;
import com.tfb.aibank.chl.general.service.NGNService;
import com.tfb.aibank.chl.general.service.OnboardingCache;
import com.tfb.aibank.chl.general.type.TwoFactorAuthStepType;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.session.resource.SessionResource;
import com.tfb.aibank.chl.session.type.CustomerType;
import com.tfb.aibank.chl.type.PushCodeType;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.common.type.CompanyKindType;
import com.tfb.aibank.common.type.DeviceBindingInfoType;
import com.tfb.aibank.common.type.NotificationSendStatus;
import com.tfb.aibank.common.type.OnboardingType;

//@formatter:off
/**
* @(#)NGNOT001010Task.java 
* 
* <p>Description:登入首頁</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20230522, JohnChang
*  <li>初版</li>
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on

@Component
@Scope("prototype")
public class NGNOT001010Task extends AbstractAIBankBaseTask<NGNOT001010Rq, NGNOT001010Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT001010Task.class);

    @Autowired
    private SecurityResource securityResource;

    @Autowired
    private UserResource userResource;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Autowired
    private UserDataCacheService userDataCacheService;

    @Autowired
    private AibankServiceSettingCacheManager aibankServiceSettingCacheManager;

    @Autowired
    private LoginService loginService;

    @Autowired
    private SessionResource sessionResource;

    @Autowired
    private DeviceBindingService deviceBindingService;

    @Autowired
    private NGNOT001Service service;

    @Autowired
    @Qualifier("NGNService")
    private NGNService ngnService;

    @Autowired
    private DeviceModelCacheManager deviceModelCacheManager;

    @Override
    public void validate(NGNOT001010Rq rqData) throws ActionException {
    }

    @Override
    public void handle(NGNOT001010Rq rqData, NGNOT001010Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001010 start====");

        /** E2EE給前端PWD加密時，是否帶入之時間因子 */
        populateTimeFactorToRs(rsData);

        // Device Status
        RetriveDeviceStatusResponse retriveDeviceStatusResponse = populateDeviceStatusToRs(rsData);

        populateGeneralInfoToRs(rsData);

        processTwoFactorAuthResult(rqData, rsData);
        // 雙重驗證有執行的狀態立即回傳結果
        if (rsData.isTwoFactorAuthFail() || rsData.isTwoFactorAuthSuccess()) {
            return;
        }

        String transferOnboarding = systemParamCacheManager.getValue("AIBANK", "ONBOARDING_TRANSFER_FLAG");

        // 是否啟用無痛移轉程?
        if (!StringUtils.equals(transferOnboarding, "Y")) {
            return;
        }
        rsData.setNoPainTransferEnable(true);

        String accessToken = rqData.getAccessToken();

        OnboardingCache cache = getFromSession(SessionKey.ONBOARDING_CACHE_KEY, OnboardingCache.class);
        if (cache != null) {
            accessToken = cache.getAccessToken();
        }

        if (accessToken != null) {
            if (!StringUtils.equals(rqData.getCustomParams(), "1")) {
                String minAppVersion = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "ONBOARDING_TRANSFER_PILOT");
                if (StringUtils.isNotBlank(minAppVersion)) {
                    // 版本太舊？
                    if (IBUtils.compareAppVersion(getRequest().getAppVer(), minAppVersion) < 0) {
                        // 不可執行無痛
                        rsData.setNoPainTransferNotQualified(true);
                        return;
                    }
                }
            }
            executeTransferUserLogin(rqData.isFirstLogin(), rsData, accessToken, rqData.getCustomParams(), retriveDeviceStatusResponse == null ? "" : retriveDeviceStatusResponse.getMarketingName(), rqData.getOnboardingType());
        }

    }

    /**
     * Rq TwoFactorAuth 帶入狀態為 True 才判斷雙重驗證執行結果, 不為 True 可能為第1次進到頁面情況
     * 
     * @param rqData
     * @param rsData
     * @throws ActionException
     */
    private void processTwoFactorAuthResult(NGNOT001010Rq rqData, NGNOT001010Rs rsData) throws ActionException {
        // 雙重驗證失敗不繼續執行後續流程

        if (rqData.isTwoFactorAuthFail()) {

            rsData.setTwoFactorAuthFail(true);
            rsData.setTwoFactorAuthDesc(rqData.getTwoFactorAuthDesc());

            TwoFactorAuthCache twoFactorCache = getCache(LoginService.TWO_FACTOR_CACHE_KEY, TwoFactorAuthCache.class);

            if (twoFactorCache == null) {
                logger.warn("TwoFactorAuthFailed cannot find cache: {}");
                return;
            }
            // 清空 Cache
            setCache(LoginService.TWO_FACTOR_CACHE_KEY, null);

            LoginMailType loginMailType = loginService.getTwoFactorLoginMailType(twoFactorCache.getLoginType(), twoFactorCache.getPwType(), false);
            LoginMailData loginMailData = loginService.getTwoFactorLoginMailData(loginMailType, getRequest().getClientIp(), this.getModel(), getLocale(), null, null, twoFactorCache.getCountryName(), getPlatformDisplay());

            if (CustomerType.isCardMember(twoFactorCache.getLoginType())) {
                if (StringUtils.isBlank(twoFactorCache.getCardEmail())) {
                    logger.warn("TwoFactorAuthFailed cannot find email: {}", twoFactorCache.getUuid());
                    return;
                }
                else {
                    sendMail(twoFactorCache.getUid(), "0", twoFactorCache.getUuid(), twoFactorCache.getCompanyVo() != null ? twoFactorCache.getCompanyVo().getCompanyKind() : CompanyKindType.PRIMARY.getCode(), loginMailData.getSubject(), twoFactorCache.getCardEmail(), loginMailData.getParams());
                }
            }
            else {
                if (CollectionUtils.isEmpty(twoFactorCache.getEmail())) {
                    logger.warn("TwoFactorAuthFailed cannot find email: {}", twoFactorCache.getUuid());
                    return;
                }
                else {
                    for (String email : twoFactorCache.getEmail()) {
                        sendMail(twoFactorCache.getUid(), "0", twoFactorCache.getUuid(), CompanyKindType.PERSONAL.getCode(), loginMailData.getSubject(), email, loginMailData.getParams());
                    }
                }
            }

        }
        else if (rqData.isTwoFactorAuthSuccess()) {
            // 執行登入後流程
            // 檢查TWOFACTOR CACHE 狀態是否正確, 正確才能執行
            TwoFactorAuthCache twoFactorCache = getCache(LoginService.TWO_FACTOR_CACHE_KEY, TwoFactorAuthCache.class);
            TwoFactorAuthStepType preAuthStep = twoFactorCache == null ? TwoFactorAuthStepType.ERROR : TwoFactorAuthStepType.find(twoFactorCache.getAuthStep());

            if (TwoFactorAuthStepType.SUCCESS != preAuthStep) {
                logger.error("TwoFactorAuth Illegal Status: TwoFactor Success But Not! {}, {}", twoFactorCache.getUuid(), preAuthStep);
                throw ExceptionUtils.getActionException(ErrorCode.TWO_FACTOR_FAILED);
            }
            // POST LOGIN, 建立 USER
            ExecuteTwoFactorAuthUserPostLoginRequest twoFactorPostLoginRequest = buildTwoFactorPostLoginRequest(twoFactorCache);

            ExecuteTwoFactorAuthUserPostLoginResponse twoFactorPostLoginResponse = userResource.executeTwoFactorAuthUserPostLogin(twoFactorPostLoginRequest);

            if (!twoFactorPostLoginResponse.isSuccess()) {
                logger.error("Illegal Status: twoFactorPostLoginResponse: " + twoFactorPostLoginResponse);
                throw ExceptionUtils.getActionException(ErrorCode.TWO_FACTOR_FAILED);
            }

            // 更新 FIRST_DOWNLOAD_RECORD
            // 確認TWOFACTOR 更新 PushToken to FirstDownloadRecord?
            if (StringUtils.equalsIgnoreCase(systemParamCacheManager.getValue("AIBANK", "ENABLE_UPDATE_FIRST_DL_RECORD", "false"), "true")) {
                service.updateFirstDownloadRecord(getRequest(), twoFactorCache.getPushToken());
            }
            // 建立 AIBankUser
            AIBankUser aibankUser = buildAIBankUserForTwoFactor(twoFactorCache, twoFactorPostLoginResponse);

            if (logger.isDebugEnabled()) {
                logger.debug("aibankUser: {}", aibankUser);
            }
            // 建立 Session
            setLoginUser(aibankUser, ngnService.getTaskList(aibankUser));

            // 針對年紀預設字體大寫
            rsData.setFontSize(ngnService.getFontSize(aibankUser.getBirthDay()));
            // ** 登入成功推播通知*/
            sendPushNotification(aibankUser, twoFactorPostLoginResponse.getPushType());

            // 滿意度問卷
            rsData.setShowSatisfactionFlag(aibankUser.isShowSatisfactionFlag()); // 設置「是否需提供滿意度問卷」註記
            rsData.setSatisfactionTasksMap(aibankServiceSettingCacheManager.getDataMap(aibankUser.getCustId(), getLocale()));

            LoginMailType loginMailType = loginService.getTwoFactorLoginMailType(twoFactorCache.getLoginType(), twoFactorCache.getPwType(), true);

            if (CustomerType.isCardMember(twoFactorCache.getLoginType())) {
                // 是否為信用卡使用者登入
                rsData.setIsCCUser("true");

                aibankUser.setEmail(twoFactorPostLoginResponse.getCardEmail());

                LoginMailData loginMailData = loginService.getTwoFactorLoginMailData(loginMailType, getRequest().getClientIp(), this.getModel(), getLocale(), null, null, twoFactorCache.getCountryName(), getPlatformDisplay());

                sendMail(aibankUser.getCustId(), aibankUser.getUidDup(), aibankUser.getUserId(), aibankUser.getCompanyKind(), loginMailData.getSubject(), twoFactorPostLoginResponse.getCardEmail(), loginMailData.getParams());

                if (twoFactorPostLoginResponse.getMbDeviceInfoVo() != null) {
                    rsData.setFastLoginStatus(twoFactorPostLoginResponse.getMbDeviceInfoVo().getDeviceInfoKey() == null ? 1 : 0);
                }

                String mobileNo = userDataCacheService.getOtpMobileFromCEW013R(aibankUser);

                if (Strings.isBlank(mobileNo)) {
                    rsData.setFastLoginStatus(0);
                }

                aibankUser.getUserData().setNameCode("0001");

                if (twoFactorPostLoginResponse.getBirthDay() != null) {
                    aibankUser.setBirthDay(twoFactorPostLoginResponse.getBirthDay());
                }
            }
            else {
                rsData.setIsCCUser("false");

                LoginMailData loginMailData = loginService.getTwoFactorLoginMailData(loginMailType, getRequest().getClientIp(), this.getModel(), getLocale(), null, null, twoFactorCache.getCountryName(), getPlatformDisplay());

                if (twoFactorPostLoginResponse.getEmail() != null) {
                    for (String email : twoFactorPostLoginResponse.getEmail()) {
                        sendMail(aibankUser.getCustId(), aibankUser.getUidDup(), aibankUser.getUserId(), aibankUser.getCompanyKind(), loginMailData.getSubject(), email, loginMailData.getParams());
                    }
                }
                /** 快登設定 */
                if (rqData.isFirstLogin()) {
                    rsData.setFastLoginStatus(loginService.checkDeviceCanBind(aibankUser, getRequest().getDeviceIxd(), getLocale()));
                    if (StringUtils.isBlank(twoFactorPostLoginResponse.getLoginMsgRs().getMobileNo())) {
                        rsData.setFastLoginStatus(0);
                    }
                }
                if (twoFactorPostLoginResponse.getLoginMsgRs() != null && twoFactorPostLoginResponse.getLoginMsgRs().getBirthday() != null) {
                    aibankUser.setBirthDay(twoFactorPostLoginResponse.getLoginMsgRs().getBirthday().getTime());
                }
            }

            // 半年未變更使用者密碼提示
            if (twoFactorPostLoginResponse.isShowChangeTip()) {
                rsData.setShowChangeTip(true);
                logger.debug("==== 客戶半年未變更使用者密碼 ====");
            }

            // 變更 Session
            String newSessionId = changeToNewSession();

            sessionResource.registerAccessSession(aibankUser.getCustId(), aibankUser.getUidDup(), aibankUser.getUserId(), aibankUser.getCompanyKind(), twoFactorPostLoginResponse.getLoginLogPk(), newSessionId);

            // 加密大數據ID
            try {
                String kxy = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "NGNOT001_AES_KEY");
                String iv = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "NGNOT001_AES_IV");
                rsData.setCelebrusId(ngnService.stringToHex(AESUtils.encryptDataToBase64(aibankUser.getCustId() + aibankUser.getUserId(), kxy.getBytes(), iv.getBytes())));
            }
            catch (CryptException ex) {
                logger.warn("Celebrus ID Encryption Fail", ex);
            }

            rsData.setTwoFactorAuthSuccess(true);
            // 清掉 TWOFACTORCACHE
            setCache(LoginService.TWO_FACTOR_CACHE_KEY, null);
        }
    }

    private ExecuteTwoFactorAuthUserPostLoginRequest buildTwoFactorPostLoginRequest(TwoFactorAuthCache twoFactorCache) {
        String sessionId = twoFactorCache.getSessionId();

        ExecuteTwoFactorAuthUserPostLoginRequest twoFactorPostLoginRequest = new ExecuteTwoFactorAuthUserPostLoginRequest(twoFactorCache);
        twoFactorPostLoginRequest.setUid(twoFactorCache.getUid());
        twoFactorPostLoginRequest.setUuid(twoFactorCache.getUid());
        twoFactorPostLoginRequest.setCurrentSessionId(sessionId);
        return twoFactorPostLoginRequest;
    }

    private AIBankUser buildAIBankUserForTwoFactor(TwoFactorAuthCache twoFactorCache, ExecuteTwoFactorAuthUserPostLoginResponse twoFactorPostLoginResponse) {
        AIBankUser aibankUser = new AIBankUser(twoFactorPostLoginResponse.getUserVo());
        aibankUser.setCompanyKindType(CompanyKindType.find(twoFactorPostLoginResponse.getCompanyVo().getCompanyKind()));
        aibankUser.setCustId(twoFactorCache.getUid());
        aibankUser.setUserId(twoFactorCache.getUuid());
        aibankUser.setCustomerType(CustomerType.findLoginType(twoFactorCache.getLoginType())); // 登入成功後，依登入方式設置登入身份
        aibankUser.setCompanyVo(twoFactorPostLoginResponse.getCompanyVo());
        aibankUser.setCardUserProfileVo(twoFactorPostLoginResponse.getCardUserProfileVo());
        aibankUser.setUserLoginVo(twoFactorPostLoginResponse.getUserLoginVo());
        aibankUser.setMbDeviceInfoVo(twoFactorPostLoginResponse.getMbDeviceInfoVo());
        aibankUser.setLoginMsgRs(twoFactorPostLoginResponse.getLoginMsgRs());
        aibankUser.setEmail(twoFactorPostLoginResponse.getEmail() != null ? twoFactorPostLoginResponse.getEmail().get(0) : "");
        aibankUser.setBirthDay(twoFactorPostLoginResponse.getBirthDay());
        aibankUser.setMobileNo(twoFactorPostLoginResponse.getMobileNo());
        aibankUser.setSameBirthday(twoFactorPostLoginResponse.isSameBirthday());
        aibankUser.setInAccountCreditcardCheck(twoFactorPostLoginResponse.isInAccountCreditcardCheck());
        aibankUser.setShowSatisfactionFlag(ngnService.checkShowSatisfactionRating(aibankUser)); // 註記「是否提供滿意度問卷」
        aibankUser.setLoginLogPk(twoFactorPostLoginResponse.getLoginLogPk());
        aibankUser.setShowChangeTip(twoFactorPostLoginResponse.isShowChangeTip());
        aibankUser.setCountryName(twoFactorCache.getCountryName());
        aibankUser.setLoginIp(getRequest().getClientIp());
        aibankUser.setMarketingName(deviceModelCacheManager.getDeviceMarketingNameOrModel(getRequest().getModel()));
        return aibankUser;
    }

    private void populateGeneralInfoToRs(NGNOT001010Rs rsData) {
        String fidoPortalUrl = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "FIDO_SDK_URL");
        rsData.setFidoPortalUrl(fidoPortalUrl);

        String businessNo = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "FIDO_BUSINESS_NO");
        rsData.setBusinessNo(businessNo);

        String url = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "LOGIN_FORGETPW_URL");
        rsData.setLoginForgetpwUrl(url);

        String privacyurl = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "PRIVACY_POLICY_URL");
        rsData.setPrivacyLink(privacyurl);

        String protectUrl = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "PERSONAL_INFO_POLICY_URL");
        rsData.setProtectLink(protectUrl);

        String chatBotUrl = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "CHATBOT_URL");
        rsData.setChatBotUrl(chatBotUrl);

        String forgetCreditUrl = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "LOGIN_FORGETPW_CREDIT_URL");
        rsData.setForgetCreditUrl(forgetCreditUrl);
    }

    /**
     * 查詢狀置狀態寫入RS
     * 
     * @param rsData
     * @return
     */
    private RetriveDeviceStatusResponse populateDeviceStatusToRs(NGNOT001010Rs rsData) {
        RetriveDeviceStatusResponse retriveDeviceStatusResponse = null;
        try {

            String disableFifo = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "DISABLE_FIDO");

            // ** 裝置綁定狀態*/
            retriveDeviceStatusResponse = userResource.retriveDeviceStatus(getRequest().getDeviceIxd(), getRequest().getModel(), getRequest().getVersion());

            rsData.setFastLogin(retriveDeviceStatusResponse.isFastLogin());
            if (StringUtils.equals("Y", disableFifo)) {
                rsData.setFastLogin(false);
            }
            rsData.setCoefficient(retriveDeviceStatusResponse.getCoefficient());
            rsData.setLoginMethod(retriveDeviceStatusResponse.getPwType());
            rsData.setLoginType(retriveDeviceStatusResponse.getLoginType());
            rsData.setCustId(retriveDeviceStatusResponse.getCustId());
            rsData.setDirectEzLoginFlag(retriveDeviceStatusResponse.getDirectEzLoginFlag());
            rsData.setIsInBlackList(retriveDeviceStatusResponse.getIsInBlackList());
            rsData.setStatus(retriveDeviceStatusResponse.getStatus());

            retriveDeviceStatusResponse.setUidUuidNeedWithTime(rsData.isUidUuidNeedWithTime());
            retriveDeviceStatusResponse.setPwdNeedWithTime(rsData.isPwdNeedWithTime());
            setCache(LoginService.DEVICE_BINDING_CACHE_KEY, retriveDeviceStatusResponse);

        }
        catch (ServiceException ex) {
            logger.warn("取得裝置綁定狀態失敗，只能以帳密登入", ex);
            rsData.setFastLogin(false);
        }
        return retriveDeviceStatusResponse;
    }

    private void populateTimeFactorToRs(NGNOT001010Rs rsData) {
        rsData.setPwdNeedWithTime(loginService.isPwdNeedWithTime());
        if (rsData.isPwdNeedWithTime()) {
            String tmpTimeFactorValidSeconds = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "TIME_FACTOR_VALID_SECONDS");
            int timeFactorValidSeconds = 280;
            if (StringUtils.isNotBlank(tmpTimeFactorValidSeconds)) {
                /** 資料庫為時間因子容許時間差，此處保留 20 秒緩衝時間給通訊或程式運作 */
                timeFactorValidSeconds = Integer.parseInt(tmpTimeFactorValidSeconds) - 20;
            }

            rsData.setTimeFactorValidSeconds(timeFactorValidSeconds);
            rsData.setPwdWithTime(E2EEUtils.getPassWithTime());
        }

        /** E2EE給前端uid/uuid加密時，是否帶入之時間因子 */
        rsData.setUidUuidNeedWithTime(loginService.isUidUuidNeedWithTime());
        if (rsData.isUidUuidNeedWithTime())
            rsData.setUidUuidWithTime(E2EEUtils.getPassWithTime());

        try {
            String kxy = securityResource.getRSAPublicKxy();
            rsData.setPublicKeyforUid(kxy);
            rsData.setPublicKeyforSecret(kxy);

        }
        catch (ServiceException ex) {
            logger.error("取得公鑰失敗", ex);
        }
    }

    private void executeTransferUserLogin(boolean isFirstLogin, NGNOT001010Rs rsData, String accessToken, String customParams, String marketName, String onboardingType) throws ActionException {
        if (StringUtils.isBlank(onboardingType)) {
            onboardingType = "NONE";
        }
        ExecuteTransferUserLoginResponse loginData = userResource.executeTransferUserLogin(accessToken, onboardingType, getRequest().getDeviceIxd());

        if (StringUtils.equals(loginData.getErrorCode(), "1")) {
            throw new ActionException(AIBankErrorCode.SSO_AUTH_DATA_ERROR);
        }

        if (StringUtils.isEmpty(loginData.getMobileNo())) {
            rsData.setNoMobileNo(true);
            return;
        }

        if (loginData.getLoginStatus() != 0) {
            return;
        }
        DeviceBindingCache cache = new DeviceBindingCache();

        // 更新 FIRST_DOWNLOAD_RECORD
        // updatePushToken(rqData.getPushToken());

        // 建立 AIBankUser
        AIBankUser aibankUser = new AIBankUser(loginData.getUserVo());
        aibankUser.setCompanyKindType(CompanyKindType.find(loginData.getCompanyKind()));
        aibankUser.setCustId(loginData.getUid());
        aibankUser.setUserId(loginData.getUuid());
        aibankUser.setCustomerType(CustomerType.findLoginType(loginData.getCustomerType())); // 登入成功後，依登入方式設置登入身份
        aibankUser.setCompanyVo(loginData.getCompanyVo());
        aibankUser.setCardUserProfileVo(loginData.getCardUserProfileVo());
        aibankUser.setUserLoginVo(loginData.getUserLoginVo());
        aibankUser.setMbDeviceInfoVo(loginData.getMbDeviceInfoVo());
        aibankUser.setLoginMsgRs(loginData.getLoginMsgRs());
        aibankUser.setEmail(loginData.getEmail() != null ? loginData.getEmail().get(0) : "");
        aibankUser.setBirthDay(loginData.getBirthDay());
        aibankUser.setMobileNo(loginData.getMobileNo());
        aibankUser.setSameBirthday(loginData.isSameBirthday());
        aibankUser.setInAccountCreditcardCheck(loginData.isInAccountCreditcardCheck());
        aibankUser.setMarketingName(marketName);
        aibankUser.setShowSatisfactionFlag(loginService.checkShowSatisfactionRating(aibankUser)); // 註記「是否提供滿意度問卷」
        aibankUser.setShowChangeTip(loginData.isShowChangeTip());

        /**
         * 是否行銀是快登
         */
        if (StringUtils.isNotBlank(loginData.getLoginMethod()) && !StringUtils.equals(loginData.getLoginMethod(), "PWD")) {
            cache.setMBFastLogin(true);
        }

        /** 無痛移轉判斷 */
        /** 是否為快登，否則視為一般登入 */
        aibankUser.setOnboardingType(OnboardingType.AIBANK.getCode());

        // OnboardingType==2 驗證成功，
        // 如果 customeParem=1 則僅登入
        // 如果 customeParem=3 由 AIBank 發動
        if (StringUtils.equals(customParams, "3") && cache.isMBFastLogin()) {
            aibankUser.setOnboardingType(OnboardingType.TRANSFER_BY_AIBANK.getCode());
        }
        else if (StringUtils.equals(customParams, "1")) {
            aibankUser.setOnboardingType(OnboardingType.AIBANK.getCode());
        }
        else {
            aibankUser.setOnboardingType(OnboardingType.TRANSFER_FROM_MB.getCode());
        }

        // 沒有移轉項目
        if (isNoTransferItem(loginData.getDeviceBindingInfo())) {
            aibankUser.setOnboardingType(OnboardingType.AIBANK.getCode());
        }

        /** 進行過無痛移轉 */
        if (userResource.isAlreadyTransfered(aibankUser.getCustId(), aibankUser.getUidDup(), aibankUser.getUserId(), aibankUser.getCompanyKind(), getRequest().getDeviceIxd())) {
            aibankUser.setOnboardingType(OnboardingType.AIBANK.getCode());
        }

        // 本裝置綁定狀態
        UserDeviceBindStatusType bindingStatus = checkDeviceBindingStatus(aibankUser);
        if (bindingStatus.isBind() || bindingStatus.isBindedDeviceNotAvailable()) {
            aibankUser.setOnboardingType(OnboardingType.AIBANK.getCode());
        }

        aibankUser.setDeviceBindingInfo(loginData.getDeviceBindingInfo());
        aibankUser.setDeviceSubPushCode(loginData.getDeviceSubPushCode());

        // 建立 Session
        setLoginUser(aibankUser, loginService.getTaskList(aibankUser));

        // 針對年紀預設字體大瀉
        rsData.setFontSize(loginService.getFontSize(aibankUser.getBirthDay()));

        // 滿意度問卷
        rsData.setShowSatisfactionFlag(aibankUser.isShowSatisfactionFlag()); // 設置「是否需提供滿意度問卷」註記
        rsData.setSatisfactionTasksMap(aibankServiceSettingCacheManager.getDataMap(aibankUser.getCustId(), getLocale()));

        if (CustomerType.isCardMember(loginData.getCustomerType())) {
            // 是否為信用卡使用者登入
            rsData.setIsCCUser("true");
            aibankUser.setEmail(loginData.getCardEmail());

            if (loginData.getMbDeviceInfoVo() != null) {
                rsData.setFastLoginStatus(loginData.getMbDeviceInfoVo().getDeviceInfoKey() == null ? 1 : 0);
            }

            String mobileNo = userDataCacheService.getOtpMobileFromCEW013R(aibankUser);

            if (Strings.isBlank(mobileNo)) {
                rsData.setFastLoginStatus(0);
            }
            aibankUser.getUserData().setNameCode("0001");
            if (loginData.getBirthDay() != null) {
                aibankUser.setBirthDay(loginData.getBirthDay());
            }

        }
        else {
            rsData.setIsCCUser("false");

            /** 快登設定 */
            if (isFirstLogin) {
                rsData.setFastLoginStatus(loginService.checkDeviceCanBind(aibankUser, getRequest().getDeviceIxd(), getLocale()));
                if (StringUtils.isBlank(loginData.getLoginMsgRs().getMobileNo())) {
                    rsData.setFastLoginStatus(0);
                }
            }
            if (loginData.getLoginMsgRs() != null && loginData.getLoginMsgRs().getBirthday() != null)
                aibankUser.setBirthDay(loginData.getLoginMsgRs().getBirthday().getTime());

        }

        // 變更 Session
        String newSessionId = changeToNewSession();
        sessionResource.registerAccessSession(aibankUser.getCustId(), aibankUser.getUidDup(), aibankUser.getUserId(), aibankUser.getCompanyKind(), 0, newSessionId);
        MDC.put(MDCKey.custid.name(), aibankUser.getCustId());
        // 加密大數據ID
        try {
            String kxy = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "NGNOT001_AES_KEY");
            String iv = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "NGNOT001_AES_IV");
            rsData.setCelebrusId(loginService.stringToHex(AESUtils.encryptDataToBase64(aibankUser.getCustId() + aibankUser.getUserId(), kxy.getBytes(), iv.getBytes())));
        }
        catch (CryptException ex) {
            logger.warn("Celebrus ID Encryption Fail", ex);
        }

        setNotificationItem(cache, loginData.getDeviceBindingInfo(), loginData.getDeviceSubPushCode());
        setCache(LoginService.DEVICE_TRANSFER_CACHE_KEY, cache);

        rsData.setLoginStatus("0000");
        if (cache.isTransferNeed()) {
            rsData.setOnboardingType(aibankUser.getOnboardingType());
        }
        else {
            rsData.setOnboardingType(OnboardingType.AIBANK.getCode());
        }

    }

    private UserDeviceBindStatusType checkDeviceBindingStatus(AIBankUser loginuser) {
        // 檢查使用者與裝置綁定狀態
        CheckUserDeviceStatusCondition condition = new CheckUserDeviceStatusCondition();
        condition.setDeviceIxd(getDeviceIxd());
        condition.setLoginUser(loginuser);
        condition.setLocale(getLocale().toString());
        CheckUserDeviceStatusResult result = new CheckUserDeviceStatusResult();
        deviceBindingService.checkUserDeviceStatus(condition, result);

        return result.getStatus();
    }

    private void setNotificationItem(DeviceBindingCache cache, List<String> deviceBindingInfo, List<String> deviceSubPushCode) {
        if (!CollectionUtils.isEmpty(deviceBindingInfo)) {
            cache.setTransferNeed(true);
            for (String type : deviceBindingInfo) {
                switch (DeviceBindingInfoType.find(type)) {
                case NOTIFY: {
                    if (CollectionUtils.isEmpty(deviceSubPushCode)) {
                        logger.warn("有設定訊息轉移，但沒有目前裝置訂閱推播資訊(原行銀)");
                        cache.setNotification(LoginService.NO_TRANSFER);
                    }
                    else {
                        cache.setNotification(LoginService.IS_TRANSFER);
                    }
                    break;
                }
                case QQUERY:
                    cache.setQuickSearch(LoginService.IS_TRANSFER);
                    break;
                case NOCARD:
                    cache.setNoCardwithDraw(LoginService.IS_TRANSFER);
                    break;
                case PUMOTP:
                    cache.setMotpSetting(LoginService.IS_TRANSFER);
                    break;
                case PHORCV:
                    cache.setPhoneTransfer(LoginService.IS_TRANSFER);
                    break;
                case ENTXLI:
                    cache.setTransferQuota(LoginService.IS_TRANSFER);
                    break;
                default:
                    break;
                }
            }
        }
    }

    /**
     * 是否沒有移轉項目
     * 
     * @param items
     * @return
     */
    private boolean isNoTransferItem(List<String> items) {
        if (items == null)
            return true;

        if (items.size() == 1)
            return true;

        return false;

    }

    // TODO Refactor NGNOT001011
    private void sendPushNotification(AIBankUser user, int pushType) {

        if (pushType == 0)
            return;

        CreatePersonalNotificationRequest request = new CreatePersonalNotificationRequest();

        Date now = new Date();

        request.setCustId(user.getCustId());
        request.setUserId(user.getUserId());
        request.setCompanyKind(user.getCompanyKind());

        // 是否發送推播
        request.setIsPush(pushType - 1);
        // 推播代碼
        request.setPushCode(PushCodeType.PERSONAL.getCode());
        // 業務別
        request.setBusType("4");
        // 推播優先序
        request.setPriority(9);
        // 標題訊息
        request.setTitleMessage(I18NUtils.getMessage("ngn.ot001.psuh.title"));
        // 推播訊息
        // 重要訊息

        String param0 = DateFormatUtils.SIMPLE_DATE_TIME_FORMAT.format(new Date());

        request.setPushMessage(I18NUtils.getMessage("ngn.ot001.psuh.pushMessage", param0));

        String deviceModel = user.getMarketingName();
        String ip = user.getLoginIp();
        String countryName = user.getCountryName();

        request.setMessage(StringUtils.isBlank(countryName) ? I18NUtils.getMessage("ngn.ot001.psuh.message.nocountryname", param0, deviceModel, ip) : I18NUtils.getMessage("ngn.ot001.psuh.message", param0, deviceModel, ip, countryName));

        // 傳送狀態
        request.setSendStatus(NotificationSendStatus.WAITING.getCode());
        // 狀態
        request.setStatus("O");
        // 是否已讀
        request.setIsRead(0);
        // 開始日期時間
        request.setStartDate(now);
        // 結束日期時間
        request.setEndDate(getEndDate());
        // 更新時間
        request.setUpdateTime(now);
        // 建立時間
        request.setCreateTime(now);

        sendPushNotification(request);
    }

    // TODO Refactor NGNOT001011
    private Date getEndDate() {
        String daysStr = systemParamCacheManager.getValue("AIBANK", "NOTIFICATION_DURATION");
        int day = 90;
        if (!StringUtils.isBlank(daysStr)) {
            day = Integer.parseInt(daysStr);
        }
        return DateUtils.addDays(new Date(), day);
    }

    /**
     * TODO Refactor NGNOT001011 發送 E-mail
     * 
     * @param custId
     * @param uidDup
     * @param userId
     * @param companyKind
     * @param subject
     * @param mailTo
     * @param params
     */
    protected void sendMail(String custId, String uidDup, String userId, Integer companyKind, String subject, String mailTo, Map<String, String> params) {
        if (!StringUtils.isBlank(mailTo)) {
            sendTxnResultMail(custId, uidDup, userId, companyKind, subject, mailTo, params);
        }
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
