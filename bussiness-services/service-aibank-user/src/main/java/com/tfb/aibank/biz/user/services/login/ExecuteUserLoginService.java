package com.tfb.aibank.biz.user.services.login;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.tfb.aibank.biz.component.etrans.E2EEUtils_AIBank;
import com.tfb.aibank.component.proxy.CryptoProxy;
import com.tfb.aibank.integration.eai.EAIChannel;
import com.tfb.aibank.integration.eai.msg.*;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.xmlbeans.XmlException;
import org.slf4j.MDC;
import org.springframework.core.env.Environment;

import com.ibm.tw.commons.error.ErrorStatus;
import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.exception.DatabaseException;
import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ArrayUtils;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.base.Constants;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.ibm.tw.ibmb.component.crypto.AESCipherUtils;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProvider;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProviderFactory;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.ibm.tw.ibmb.util.ValidateParamUtils;
import com.tfb.aibank.biz.component.identity.IdentityData;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.user.gateway.EsbChannelGateway;
import com.tfb.aibank.biz.user.repository.AccountCreditcardCheckRepository;
import com.tfb.aibank.biz.user.repository.CardMemberRecordRepository;
import com.tfb.aibank.biz.user.repository.CardUserProfileRepository;
import com.tfb.aibank.biz.user.repository.ChangePasswordRecordRepository;
import com.tfb.aibank.biz.user.repository.ChangePasswordTipsRepository;
import com.tfb.aibank.biz.user.repository.CompanyRepository;
import com.tfb.aibank.biz.user.repository.EbLoginLogBRepository;
import com.tfb.aibank.biz.user.repository.GeoIPLocationDataRepository;
import com.tfb.aibank.biz.user.repository.GeoIPRangeDataRepository;
import com.tfb.aibank.biz.user.repository.MbDeviceInfoRepository;
import com.tfb.aibank.biz.user.repository.MbGestureProfileRepository;
import com.tfb.aibank.biz.user.repository.MbLoginLogRepository;
import com.tfb.aibank.biz.user.repository.NonAIMbDeviceInfoEntityRepository;
import com.tfb.aibank.biz.user.repository.ReadChangeRightsInterestsRepository;
import com.tfb.aibank.biz.user.repository.UserLoginRepository;
import com.tfb.aibank.biz.user.repository.UserRepository;
import com.tfb.aibank.biz.user.repository.aib.MbDevicePushInfoRepository;
import com.tfb.aibank.biz.user.repository.aib.PushSubscriptionRepository;
import com.tfb.aibank.biz.user.repository.aib.entities.MbDevicePushInfoEntity;
import com.tfb.aibank.biz.user.repository.aib.entities.PushSubscriptionEntity;
import com.tfb.aibank.biz.user.repository.entities.AccountCreditcardCheckEntity;
import com.tfb.aibank.biz.user.repository.entities.CardMemberRecordEntity;
import com.tfb.aibank.biz.user.repository.entities.CardUserProfileEntity;
import com.tfb.aibank.biz.user.repository.entities.ChangePasswordRecordEntity;
import com.tfb.aibank.biz.user.repository.entities.ChangePasswordTipsEntity;
import com.tfb.aibank.biz.user.repository.entities.CompanyEntity;
import com.tfb.aibank.biz.user.repository.entities.EbLoginLogBEntity;
import com.tfb.aibank.biz.user.repository.entities.GeoIPLocationDataEntity;
import com.tfb.aibank.biz.user.repository.entities.GeoIPRangeDataEntity;
import com.tfb.aibank.biz.user.repository.entities.MbDeviceInfoEntity;
import com.tfb.aibank.biz.user.repository.entities.MbGestureProfileEntity;
import com.tfb.aibank.biz.user.repository.entities.NonAIMbDeviceInfoEntity;
import com.tfb.aibank.biz.user.repository.entities.UserEntity;
import com.tfb.aibank.biz.user.repository.entities.UserLoginEntity;
import com.tfb.aibank.biz.user.repository.piblog.LoginUidLimitationRepository;
import com.tfb.aibank.biz.user.repository.piblog.entities.LoginUidLimitationEntity;
import com.tfb.aibank.biz.user.resource.SecurityResource;
import com.tfb.aibank.biz.user.resource.model.CheckSecuirtyRulesRequest;
import com.tfb.aibank.biz.user.resource.model.CheckSecuirtyRulesResponse;
import com.tfb.aibank.biz.user.resource.model.EncodeWithSecretRequest;
import com.tfb.aibank.biz.user.resource.model.EncodeWithSecretResponse;
import com.tfb.aibank.biz.user.services.login.model.ChangeUuidAndPinBlockRequest;
import com.tfb.aibank.biz.user.services.login.model.ChangeUuidAndPinBlockResponse;
import com.tfb.aibank.biz.user.services.login.model.EbLoginLogBRequest;
import com.tfb.aibank.biz.user.services.login.model.ExecuteUserLoginRequest;
import com.tfb.aibank.biz.user.services.login.model.ExecuteUserLoginResponse;
import com.tfb.aibank.biz.user.services.login.model.UnLoginEmailsResponse;
import com.tfb.aibank.biz.user.services.login.model.UpdatePwdValidTimeRequest;
import com.tfb.aibank.biz.user.services.twofactor.TwoFactorAuthService;
import com.tfb.aibank.biz.user.services.twofactor.model.ExecuteTwoFactorAuthUserPostLoginRequest;
import com.tfb.aibank.biz.user.services.twofactor.model.ExecuteTwoFactorAuthUserPostLoginResponse;
import com.tfb.aibank.chl.session.type.ChannelIdType;
import com.tfb.aibank.chl.session.type.CustomerType;
import com.tfb.aibank.chl.session.type.LoginStatusType;
import com.tfb.aibank.chl.session.vo.CardUserProfileVo;
import com.tfb.aibank.chl.session.vo.CompanyVo;
import com.tfb.aibank.chl.session.vo.EB5556981Response;
import com.tfb.aibank.chl.session.vo.MbDeviceInfoVo;
import com.tfb.aibank.chl.session.vo.UserLoginVo;
import com.tfb.aibank.chl.session.vo.UserVo;
import com.tfb.aibank.chl.type.PwType;
import com.tfb.aibank.chl.type.ResultCodeType;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.common.type.AtferLoginJobType;
import com.tfb.aibank.common.type.CompanyKindType;
import com.tfb.aibank.common.type.E2EEHsmType;
import com.tfb.aibank.common.type.TxSourceType;
import com.tfb.aibank.common.type.TxStatusType;
import com.tfb.aibank.common.type.UserStatusType;
import com.tfb.aibank.common.util.BaNCSUtil;
import com.tfb.aibank.common.util.BizExceptionUtils;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIResponseException;

import feign.FeignException;
import tw.com.ibm.mf.eai.TxRepeatType;
import tw.com.ibm.mf.eb.CE6220RSvcRsType;
import tw.com.ibm.mf.eb.CEW013RSvcRsType;
import tw.com.ibm.mf.eb.EB032154SvcRsType;
import tw.com.ibm.mf.eb.EB067217RepeatType;
import tw.com.ibm.mf.eb.EB5556981SvcRsType;
import tw.com.ibm.mf.eb.EB67050SvcRsType;

// @formatter:off
/**
 * @(#)ExecuteUserLoginService.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/03, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class ExecuteUserLoginService {

    private static final IBLog logger = IBLog.getLog(ExecuteUserLoginService.class);

    private EsbChannelGateway esbGateway;

    private UserRepository userProfileRepository;

    private UserLoginRepository userLoginRepository;

    private AESCipherUtils aesCipherUtils = null;

    private SystemParamCacheManager systemParamCacheManager;

    private SecurityResource securityResource;

    private CompanyRepository companyRepository;

    private GeoIPRangeDataRepository geoIPRangeDataRepository;

    private GeoIPLocationDataRepository geoIPLocationDataRepository;

    private CardUserProfileRepository cardUserProfileRepository;

    private MbDeviceInfoRepository mbDeviceInfoRepository;

    private LoginUidLimitationRepository loginUidLimitationRepository;

    private ChangePasswordTipsRepository changePasswordTipsRepository;

    private MbGestureProfileRepository mbGestureProfileRepository;

    private IdentityService identityService;

    private CardMemberRecordRepository cardMemberRecordRepository;

    private ChangePasswordRecordRepository changePasswordRecordRepository;

    private AccountCreditcardCheckRepository accountCreditcardCheckRepository;

    private MbDevicePushInfoRepository mbDevicePushInfoRepository;

    // private MbLoginLogRepository mbLoginLogRepository;

    private EbLoginLogBRepository ebLoginLogBRepository;

    private Environment environment;

    private PushSubscriptionRepository pushSubscriptionRepository;

    private NonAIMbDeviceInfoEntityRepository nonAIMbDeviceInfoEntityRepository;

    private TwoFactorAuthService twoFactorAuthService;

    private static final String[] NOTIFY_ERROR_CODE = { "EP32", "EP36", "EP38", "WP31", "WP36", "WP34", "WP37", "WP38", "WP39", "EP34", "EG21", "EG22", "EG23", "EG24", "EM92", "EP28", "EBC6", "EBH8" };

    private static final String[] ENABLE_ERROR_CODE = { "E522", "EP86", "EP85", "EP44", "E519", "EBH7", "EBH6", "EBH5", "EBD4", "EBH9" };

    private static final String[] GESTURE_ERROR_CODE = { "0241", "0242", "0243" };

    /** 信用卡 登入失敗ERROR_CODE */
    private static final String[] CCLOGIN_ERROR_CODE = { AIBankErrorCode.USER_NOT_FOUND.getErrorCode(), AIBankErrorCode.PASSWORD_FAIL_COUNT.getErrorCode(), AIBankErrorCode.USER_IS_STOPPED.getErrorCode(), AIBankErrorCode.CCUSER_LOGIN_PASSWORD_VALIDATE_ERROR.getErrorCode() };

    private static final int MAX_PASSWORD_FAIL_COUNT = 3;

    public ExecuteUserLoginService(EsbChannelGateway esbChannelGateway, //
            UserRepository userProfileRepository, //
            SystemParamCacheManager systemParamCacheManager, //
            UserLoginRepository userLoginRepository, //
            SecurityResource ecurityResource, //
            CompanyRepository companyRepository, //
            ReadChangeRightsInterestsRepository //
            readChangeRightsInterestsRepository, //
            GeoIPRangeDataRepository geoIPRangeDataRepository, //
            GeoIPLocationDataRepository geoIPLocationDataRepository, //
            CardUserProfileRepository cardUserProfileRepository, //
            MbDeviceInfoRepository mbDeviceInfoRepository, //
            IdentityService identityService, //
            LoginUidLimitationRepository loginUidLimitationRepository, //
            ChangePasswordTipsRepository changePasswordTipsRepository, //
            MbGestureProfileRepository mbGestureProfileRepository, //
            ChangePasswordRecordRepository changePasswordRecordRepository, //
            CardMemberRecordRepository cardMemberRecordRepository, //
            AccountCreditcardCheckRepository accountCreditcardCheckRepository, //
            MbDevicePushInfoRepository mbDevicePushInfoRepository, //
            MbLoginLogRepository mbLoginLogRepository, //
            Environment environment, //
            NonAIMbDeviceInfoEntityRepository nonAIMbDeviceInfoEntityRepository, //
            EbLoginLogBRepository ebLoginLogBRepository, //
            PushSubscriptionRepository pushSubscriptionRepository, TwoFactorAuthService twoFactorAuthService) {
        this.esbGateway = esbChannelGateway;
        this.userProfileRepository = userProfileRepository;
        this.systemParamCacheManager = systemParamCacheManager;
        this.userLoginRepository = userLoginRepository;
        this.securityResource = ecurityResource;
        this.companyRepository = companyRepository;
        this.geoIPRangeDataRepository = geoIPRangeDataRepository;
        this.geoIPLocationDataRepository = geoIPLocationDataRepository;
        this.cardUserProfileRepository = cardUserProfileRepository;
        this.mbDeviceInfoRepository = mbDeviceInfoRepository;
        this.identityService = identityService;
        this.loginUidLimitationRepository = loginUidLimitationRepository;
        this.changePasswordTipsRepository = changePasswordTipsRepository;
        this.mbGestureProfileRepository = mbGestureProfileRepository;
        this.changePasswordRecordRepository = changePasswordRecordRepository;
        this.cardMemberRecordRepository = cardMemberRecordRepository;
        this.accountCreditcardCheckRepository = accountCreditcardCheckRepository;
        this.mbDevicePushInfoRepository = mbDevicePushInfoRepository;
        this.environment = environment;
        this.nonAIMbDeviceInfoEntityRepository = nonAIMbDeviceInfoEntityRepository;
        this.ebLoginLogBRepository = ebLoginLogBRepository;
        this.pushSubscriptionRepository = pushSubscriptionRepository;
        this.twoFactorAuthService = twoFactorAuthService;
    }

    public ExecuteUserLoginResponse executeUserLogin(ExecuteUserLoginRequest request) throws ActionException {

        if (request.getUuid() != null) {
            request.setUuid(request.getUuid().trim());
        }

        LoginUser loginUser = process(request);

        if (loginUser.isSuccess()) {
            // 登入成功
            processSuccessLogin(loginUser, request);
        }
        else {
            // 登入失敗
            processFailLogin(loginUser, request);
        }

        ExecuteUserLoginResponse response = new ExecuteUserLoginResponse();
        copyProperties(response, loginUser);

        if (!loginUser.isTwoFactorAuth()) {
            // 寫入 EB_LOGIN_LOG_B
            Integer pk = saveEbLoginLogB(request, loginUser);
            response.setLoginLogPk(pk);
        }

        if (loginUser.getError() != null) {
            response.setErrorCode(loginUser.getError().getErrorCode());
            response.setErrorDesc(loginUser.getError().getErrorDesc());
            response.setSystemId(loginUser.getError().getSystemId());
            response.setError(loginUser.getError());
        }
        return response;
    }

    /**
     * 雙重驗證成我後執行流程
     * 
     * @param loginUser
     * @param request
     * @return
     * @throws ActionException
     */
    public ExecuteTwoFactorAuthUserPostLoginResponse executeTwoFactorAuthUserPostLogin(ExecuteTwoFactorAuthUserPostLoginRequest request) throws ActionException {

        ExecuteTwoFactorAuthUserPostLoginResponse loginUserResponse = new ExecuteTwoFactorAuthUserPostLoginResponse(request);

        // TODO 處理過程中有錯誤寫到 loginUserResponse.setError()
        UserLoginEntity loginEntity = convertToUserLoginEntity(request);
        CompanyEntity companyEntity = convertToCompanyEntity(request);
        UserEntity userEntity = convertToUserEntity(request);

        updateTwoFactorUserPostLoginData(request, loginEntity, companyEntity, userEntity);

        String signonToken = loginEntity.getSignonToken();
        request.setSignonToken(signonToken);

        // 取得推播狀態
        populatePushTypePostLoginSuccess(request, loginUserResponse);

        Integer loginLogKey = addEbLoginLogB(request, loginUserResponse);

        loginUserResponse.setLoginLogPk(loginLogKey);

        if (!request.isBankUser()) {
            return loginUserResponse;
        }

        populateBirthAndAccountCreditCardCheck(request, loginUserResponse);

        // loginUserResponse.setSuccess(true);
        return loginUserResponse;
    }

    private Integer addEbLoginLogB(ExecuteTwoFactorAuthUserPostLoginRequest request, ExecuteTwoFactorAuthUserPostLoginResponse loginUserResponse) throws ActionException {
        EbLoginLogBEntity log = new EbLoginLogBEntity();
        log.setUserUuid(request.getUuid());
        log.setClientIp(request.getClientIP());
        log.setLoginTime(new Date());
        log.setUserAgent(request.getUserAgent());
        log.setScreenHeight(request.getScreenHeight());
        log.setScreenWidth(request.getScreenWidth());
        log.setServerId(getServerId());
        log.setCompanyUid(encrypt(request.getUid()));
        log.setUidDup("0");
        log.setChannelId("M");
        if (loginUserResponse.isSuccess()) {
            log.setCompanyKey(request.getCompanyKey());
            log.setUserKey(request.getUserKey());
            log.setSignonToken(request.getSignonToken());
            log.setNameCode(loginUserResponse.getNameCode());
        }
        else {
            log.setErrorCode(loginUserResponse.getError().getErrorCode());
            log.setErrorDesc(loginUserResponse.getError().getErrorDesc());
            log.setErrorSystemId(loginUserResponse.getError().getSystemId());
        }

        ebLoginLogBRepository.save(log);
        logger.info("EB_LOGIN_LOG_SEQ(TWOFACTOR)={}", log.getLoginLogKey());
        Integer loginLogKey = log.getLoginLogKey();
        return loginLogKey;
    }

    private void populatePushTypePostLoginSuccess(ExecuteTwoFactorAuthUserPostLoginRequest request, ExecuteTwoFactorAuthUserPostLoginResponse loginUserResponse) {
        int pushType = getPushType(request.getCompanyKey(), request.getUserKey(), request.getMbDeviceInfoVo().getDeviceUuId(), request.isTwoFactorAuth());
        loginUserResponse.setPushType(pushType);
    }

    private void populateBirthAndAccountCreditCardCheck(ExecuteTwoFactorAuthUserPostLoginRequest request, ExecuteTwoFactorAuthUserPostLoginResponse loginUserResponse) throws ActionException {
        AccountCreditcardCheckEntity accountCreditcardCheckEntity = accountCreditcardCheckRepository.findByCompanyUid(ValidateParamUtils.validParam(request.geteUid()));

        if (accountCreditcardCheckEntity == null) {
            loginUserResponse.setInAccountCreditcardCheck(false);
        }
        else {

            loginUserResponse.setInAccountCreditcardCheck(true);

            Date birthday = null;

            if (request.getLoginMsgRs() != null && request.getLoginMsgRs().getBirthday() != null) {
                birthday = request.getLoginMsgRs().getBirthday().getTime();
            }

            if (birthday == null) {
                loginUserResponse.setSameBirthday(false);

            }

            String rocBirthday = DateFormatUtils.SIMPLE_DATE_FORMAT.format(birthday.getTime());

            try {

                esbGateway.sendCEW319R("G", request.getUid(), null, rocBirthday, null, null);
            }
            catch (XmlException | EAIException | EAIResponseException ex) {
                loginUserResponse.setSameBirthday(false);
            }
            loginUserResponse.setSameBirthday(true);

        }
    }

    private void updateTwoFactorUserPostLoginData(ExecuteTwoFactorAuthUserPostLoginRequest request, UserLoginEntity loginEntity, CompanyEntity companyEntity, UserEntity userEntity) {
        Date loginTime = new Date();

        if (loginEntity == null) {
            UserRegisterationService registerationService = new UserRegisterationService(companyRepository, userProfileRepository, userLoginRepository);
            loginEntity = registerationService.createNewUserLoginEntity(companyEntity, userEntity);
        }

        loginEntity.setServerId(getServerId());

        boolean needCopy = (loginEntity.getLastLoginTime() == null || loginEntity.getLoginTime() == null) || loginEntity.getLoginTime().after(loginEntity.getLastLoginTime());

        if (request.isSuccess()) {
            if (needCopy) {
                // 留下前一次的 client ip
                loginEntity.setLastClientIp(loginEntity.getClientIp());
                // 留下前一次的 登入時間
                loginEntity.setLastLoginTime(loginEntity.getLoginTime());
                // 留下前一次的登入狀態
                loginEntity.setLastLoginResult(loginEntity.getLoginResult());
                // 留下前一次的國別狀態
                loginEntity.setLastNationName(loginEntity.getNationName());
                // 留下前一次的 channel id
                loginEntity.setLastChannelId(loginEntity.getChannelId());
            }
            // 成功時更新以下欄位
            loginEntity.setClientIp(request.getClientIP());
            loginEntity.setLoginTime(loginTime);
            loginEntity.setSessionId(fixSessionId(request.getCurrentSessionId()));
            loginEntity.setLoginResult(request.isSuccess() ? ResultCodeType.RESULT_OK.getResultCode() : ResultCodeType.RESULT_FAILED.getResultCode());
            loginEntity.setNationName(request.getCountryName());

            // 0617 雙重驗證只有３層式密碼
            // if (PwType.isPassword(request.getPwType())) {
            // loginEntity.setPwdErrorCount(0);
            // }

            loginEntity.setStatus(LoginStatusType.LOGIN.getCode());
            loginEntity.setLastAccessTime(loginTime);
            // generate signon token, sha(SystemId + sessionId + time millis)
            // 2022/6/20 JingXian 行銀白箱修補, A3 Weak Cryptographic Hash
            String signonToken = new String(Hex.encodeHex(DigestUtils.sha256((IBSystemId.AIBANK.getSystemId() + "-" + loginEntity.getSessionId() + "-" + System.currentTimeMillis()).getBytes())));
            if (StringUtils.isNotBlank(signonToken) && signonToken.length() > 40) {
                signonToken = signonToken.substring(0, 40);
            }
            loginEntity.setSignonToken(signonToken);
            loginEntity.setChannelId("M"); // @mobile
        }
        else {
            // 失敗時直接紀錄於 lastXXXX 欄位
            loginEntity.setLastClientIp(request.getClientIP());
            loginEntity.setLastLoginTime(loginTime);
            loginEntity.setLastLoginResult(request.isSuccess() ? ResultCodeType.RESULT_OK.getResultCode() : ResultCodeType.RESULT_FAILED.getResultCode());
            loginEntity.setNationName(request.getCountryName());
            loginEntity.setLastChannelId(ChannelIdType.AIBank.getChannelId());
        }

        userLoginRepository.save(loginEntity);
    }

    private CompanyEntity convertToCompanyEntity(ExecuteTwoFactorAuthUserPostLoginRequest request) {
        CompanyVo vo = request.getCompanyVo();

        if (vo == null) {
            return null;
        }

        CompanyEntity entity = new CompanyEntity();

        // 直接屬性對應轉換 - 所有屬性名稱都相同
        entity.setCompanyKey(vo.getCompanyKey());
        entity.setCompanyUid(vo.getCompanyUid());
        entity.setUidDup(vo.getUidDup());
        entity.setStatus(vo.getStatus());
        entity.setCompanyKind(vo.getCompanyKind());
        entity.setCompanyBUType(vo.getCompanyBUType());
        entity.setCompanyName(vo.getCompanyName());
        entity.setCompanyEname(vo.getCompanyEname());
        entity.setUaaLevel(vo.getUaaLevel());
        entity.setDefaultFlowSchemaKey(vo.getDefaultFlowSchemaKey());
        entity.setSchemaSuitOperatorKey(vo.getSchemaSuitOperatorKey());
        entity.setSchemaSuitCreateTime(vo.getSchemaSuitCreateTime());
        entity.setEstablishDate(vo.getEstablishDate());
        entity.setDefaultBranchId(vo.getDefaultBranchId());
        entity.setTel(vo.getTel());
        entity.setFax(vo.getFax());
        entity.setMobile(vo.getMobile());
        entity.setEmails(vo.getEmails());
        entity.setRetryFlag(vo.getRetryFlag());
        entity.setLoginFlag(vo.getLoginFlag());
        entity.setSalaryFlag(vo.getSalaryFlag());
        entity.setOnlinePayeeFlag(vo.getOnlinePayeeFlag());
        entity.setRoot1TxFlag(vo.getRoot1TxFlag());
        entity.setRoot2TxFlag(vo.getRoot2TxFlag());
        entity.setPayerAccountFlag(vo.getPayerAccountFlag());
        entity.setPayeeAccountFlag(vo.getPayeeAccountFlag());
        entity.setFaxFlag(vo.getFaxFlag());
        entity.setTwAtmFlag(vo.getTwAtmFlag());
        entity.setTwRemitFlag(vo.getTwRemitFlag());
        entity.setTwFxmlFlag(vo.getTwFxmlFlag());
        entity.setTwPayeeFlag(vo.getTwPayeeFlag());
        entity.setTwAmtQuota(vo.getTwAmtQuota());
        entity.setTwInterRemitQuota(vo.getTwInterRemitQuota());
        entity.setRegisterTime(vo.getRegisterTime());
        entity.setCancelTime(vo.getCancelTime());
        entity.setLastBranchId(vo.getLastBranchId());
        entity.setLastEditorKey(vo.getLastEditorKey());
        entity.setLastManagerKey(vo.getLastManagerKey());
        entity.setLastUpdateTime(vo.getLastUpdateTime());
        entity.setQueryOnlyFlag(vo.getQueryOnlyFlag());
        entity.setKycRevalidated(vo.getKycRevalidated());
        entity.setKycRevalidatedDate(vo.getKycRevalidatedDate());
        entity.setMassCheck(vo.getMassCheck());
        entity.setCustomeAuditId(vo.getCustomeAuditId());

        return entity;

    }

    private UserEntity convertToUserEntity(ExecuteTwoFactorAuthUserPostLoginRequest request) {
        UserVo vo = request.getUserVo();

        if (vo == null) {
            return null;
        }

        UserEntity entity = new UserEntity();

        // 直接對應的屬性
        entity.setUserKey(vo.getUserKey());
        entity.setCompanyKey(vo.getCompanyKey());
        entity.setUserUuid(vo.getUserUuid());
        entity.setNickName(vo.getNickName());
        entity.setAvatar(vo.getAvatar());
        entity.setUserCname(vo.getUserCname());
        entity.setUserEname(vo.getUserEname());
        entity.setStatus(vo.getStatus());
        entity.setUserType(vo.getUserType());
        entity.setLocale(vo.getLocale());
        entity.setOtpType(vo.getOtpType());
        entity.setOtpCert(vo.getOtpCert());
        entity.setTel1(vo.getTel1());
        entity.setTel2(vo.getTel2());
        entity.setTel3(vo.getTel3());
        entity.setFax1(vo.getFax1());
        entity.setFax2(vo.getFax2());
        entity.setNameCode(vo.getNameCode());
        entity.setMobile(vo.getMobile());
        entity.setEmails(vo.getEmails());
        entity.setTwEditQuota(vo.getTwEditQuota());
        entity.setTwVerifyQuota(vo.getTwVerifyQuota());
        entity.setTwPassQuota(vo.getTwPassQuota());
        entity.setTwDailyPassQuota(vo.getTwDailyPassQuota());
        entity.setFxEditQuota(vo.getFxEditQuota());
        entity.setFxVerifyQuota(vo.getFxVerifyQuota());
        entity.setFxPassQuota(vo.getFxPassQuota());
        entity.setFxDailyPassQuota(vo.getFxDailyPassQuota());
        entity.setTwPassDate(vo.getTwPassDate());
        entity.setTwTotalPassAmt(vo.getTwTotalPassAmt());
        entity.setFxPassDate(vo.getFxPassDate());
        entity.setFxTotalPassAmt(vo.getFxTotalPassAmt());
        entity.setHiddenFxAccount(vo.getHiddenFxAccount());
        entity.setTxDate(vo.getTxDate());
        entity.setHiddenLoanAccount(vo.getHiddenLoanAccount());

        // 特殊處理：密碼欄位名稱不同
        // VO 中是 paxword，Entity 中是 secret
        entity.setSecret(vo.getPaxword());

        // Entity 中有但 VO 中沒有的欄位，設定預設值
        // entity.setSmartCardType(0); // 預設值
        // entity.setFxmlType(0); // 預設值
        // entity.setFxmlCert(null); // 預設值
        // entity.setMobileRight(0); // 預設值
        // entity.setLoginPeriod(null); // 預設值
        // entity.setLoginStartTime(null); // 預設值
        // entity.setLoginEndTime(null); // 預設值
        // entity.setDepartmentName(null); // 預設值
        // entity.setFirstLogin(null); // 預設值
        // entity.setPwdFlag(UserEntity.PWD_FLG_INIT); // 使用預設的初始狀態
        // entity.setPasswordAdvanced(null); // 預設值

        return entity;
    }

    private UserLoginEntity convertToUserLoginEntity(ExecuteTwoFactorAuthUserPostLoginRequest request) {
        UserLoginVo vo = request.getUserLoginVo();

        if (vo == null) {
            return null;
        }

        UserLoginEntity entity = new UserLoginEntity();

        // 直接屬性對應轉換
        entity.setUserKey(vo.getUserKey());
        entity.setCompanyKey(vo.getCompanyKey());
        entity.setStatus(vo.getStatus());
        entity.setPwdErrorCount(vo.getPwdErrorCount());
        entity.setPwdErrorTime(vo.getPwdErrorTime());
        entity.setPwdChangeTime(vo.getPwdChangeTime());
        entity.setPwdForceChange(vo.getPwdForceChange());
        entity.setServerId(vo.getServerId());
        entity.setSessionId(vo.getSessionId());
        entity.setClientIp(vo.getClientIp());
        entity.setLoginTime(vo.getLoginTime());
        entity.setLastAccessTime(vo.getLastAccessTime());
        entity.setLastLoginTime(vo.getLastLoginTime());
        entity.setLoginResult(vo.getLoginResult());
        entity.setLastLoginResult(vo.getLastLoginResult());
        entity.setLastClientIp(vo.getLastClientIp());
        entity.setNationName(vo.getNationName());
        entity.setLastNationName(vo.getLastNationName());
        entity.setWarningMessage(vo.getWarningMessage());
        entity.setSignonToken(vo.getSignonToken());
        entity.setMainCoachMark(vo.getMainCoachMark());
        entity.setPmCoachMark(vo.getPmCoachMark());
        entity.setAcctMgrCoachMark(vo.getAcctMgrCoachMark());
        entity.setChannelId(vo.getChannelId());
        entity.setLastChannelId(vo.getLastChannelId());
        entity.setPopupTime(vo.getPopupTime());
        entity.setMfdShowtype(vo.getMfdShowtype());

        return entity;
    }

    /**
     * 新增 EbLoginLogB Log
     * 
     * @param request
     * @return
     */
    public Boolean addEbLoginLogB(EbLoginLogBRequest request) {
        EbLoginLogBEntity log = new EbLoginLogBEntity();

        try {
            log.setUserUuid(request.getUuid());
            log.setClientIp(request.getClientIP());
            log.setLoginTime(new Date());
            log.setUserAgent(request.getUserAgent());
            log.setScreenHeight(request.getScreenHeight());
            log.setScreenWidth(request.getScreenWidth());
            log.setServerId(getServerId());
            log.setCompanyUid(encrypt(request.getUid()));
            log.setChannelId("M");
            log.setUidDup("0");

            if (request.getError() != null) {
                ActionException error = request.getError();
                log.setErrorCode(error.getErrorCode());
                log.setErrorDesc(error.getErrorDesc());
                log.setErrorSystemId(error.getSystemId());
            }
            ebLoginLogBRepository.save(log);
            logger.info("EB_LOGIN_LOG_SEQ={}", log.getLoginLogKey());
            return true;
        }
        catch (Exception ex) {
            logger.warn("EB_LOGIN_LOG_B 錯誤 EB_LOGIN_LOG_SEQ={}", log.getLoginLogKey());
            return false;
        }
    }

    private Integer saveEbLoginLogB(ExecuteUserLoginRequest request, LoginUser loginUser) {
        EbLoginLogBEntity log = new EbLoginLogBEntity();
        try {
            log.setUserUuid(request.getUuid());
            log.setClientIp(request.getClientIP());
            log.setLoginTime(new Date());
            log.setUserAgent(request.getUserAgent());
            log.setScreenHeight(request.getScreenHeight());
            log.setScreenWidth(request.getScreenWidth());
            log.setServerId(getServerId());
            log.setCompanyUid(encrypt(request.getUid()));
            log.setUidDup("0");
            log.setChannelId("M");
            log.setNationCode(loginUser.getCountryCode());
            log.setNationName(loginUser.getCountryName());
            log.setStartIp(loginUser.getIpFrom() == null ? "" : loginUser.getIpFrom().toString());
            log.setEndIp(loginUser.getIpEnd() == null ? "" : loginUser.getIpEnd().toString());

            if (loginUser.isSuccess()) {
                log.setCompanyKey(loginUser.getCompanyKey());
                log.setUserKey(loginUser.getUserKey());
                log.setSignonToken(loginUser.getUserLoginEntity().getSignonToken());
                log.setNameCode(loginUser.getNameCode());
            }
            else {
                log.setErrorCode(loginUser.getError().getErrorCode());
                log.setErrorDesc(loginUser.getError().getErrorDesc());
                log.setErrorSystemId(loginUser.getError().getSystemId());

            }
            ebLoginLogBRepository.save(log);
            logger.info("EB_LOGIN_LOG_SEQ={}", log.getLoginLogKey());
            return log.getLoginLogKey();
        }
        catch (Exception ex) {
            logger.warn("EB_LOGIN_LOG_B 錯誤 EB_LOGIN_LOG_SEQ={}", log.getLoginLogKey());
            return null;
        }
    }

    /**
     * 網路狀態轉換
     *
     * @param network
     * @return
     */
    private int getNetwork(String network) {
        if (StringUtils.equals(network, "Unknown")) {
            return 0;
        }
        if (StringUtils.equals(network, "Wifi")) {
            return 1;
        }

        return 2;
    }

    public LoginUser process(ExecuteUserLoginRequest request) {

        LoginUser loginUser = new LoginUser();

        ActionException error = null;

        // 處理 UID
        try {

            loginUser.setCustId(request.getUid());
            loginUser.seteUid(encrypt(request.getUid()));

            /** 檢核登入之Device ID是否在登入限制名單內 */
            checkLoginUuidLimitation(request.getDeviceId(), request.getUid());

            List<UserEntity> usersInDB = queryLoginUser(request.getUid(), request.getUuid(), request.getLoginType());

            boolean needValidateAfterTxn = false;
            loginUser.setMultiUser(false);
            if (usersInDB.size() > 1) {
                // 找到多筆時, 在電文之後再檢核重覆登入
                needValidateAfterTxn = true;
                loginUser.setMultiUser(false);
            }
            else if (usersInDB.size() == 1) {
                // 只有一筆, 繼續
                loginUser.setUserEntity(usersInDB.get(0));
                loginUser.setUserLoginEntity(getLoginEntity(loginUser.getUserKey(), loginUser.getUserEntity()));
                loginUser.setCompanyEntity(null);
            }

            // 查詢國別, IP
            loginUser.setCountryCode(request.getCountryCode());
            loginUser.setCountryName(request.getCountryName());
            loginUser.setIpFrom(request.getIpFrom());
            loginUser.setIpEnd(request.getIpTo());

            if (CustomerType.isCardMember(request.getLoginType())) {
                loginUser = processCcUser(request, loginUser, usersInDB);
                loginUser.setBankUser(false);
            }
            else {
                loginUser = processEbUser(request, loginUser, needValidateAfterTxn, usersInDB);
                loginUser.setBankUser(true);
            }
            // 8647 檢查啟開雙重驗證FLAG
            this.twoFactorAuthService.populateLoginUserForTwoFactorAuthentication(loginUser, request);

        }
        catch (DatabaseException | CryptException | EAIResponseException | EAIException | XmlException e) {
            error = BizExceptionUtils.getBizActionException(e);
        }
        catch (ActionException e) {
            String errorCode = e.getErrorCode();
            // 檢核使用者代碼/密碼之是否符合安控規範
            if (!"Y".equals(request.getIsSignatureLogin()) && ("EP34".equals(errorCode) || "EP38".equals(errorCode)) && !PwType.isPattern(request.getPwType())) {
                logger.error("StringUtils.equals(\"1\", pwType):" + PwType.isPattern(request.getPwType()));
                // check 是否為首次登入, checkSecuirtyRules 回覆 11 表示兩者皆不符, 即為首次登入
                int securityRulesResult = checkSecuirtyRules(request.getUid(), request.getUuid(), request.getSecrxt(), request.getIsPwdWithTime());
                if (11 == securityRulesResult) {
                    e.setErrorCode(errorCode + "A");
                }
            }
            EB5556981SvcRsType loginMsgRs = loginUser.getLoginMsgRs();
            String uidForMsg = "";
            if (loginMsgRs == null || StringUtils.isBlank(loginMsgRs.getIDNO())) {
                uidForMsg = StringUtils.rightPad(request.getUid(), 10, ' ') + '0';
            }
            else {
                uidForMsg = loginMsgRs.getIDNO();
            }
            if (ArrayUtils.contains(NOTIFY_ERROR_CODE, errorCode)) {
                // 在 NOTIFY_ERROR_CODE 內的要發Mail通知
                try {
                    // SecurityNotificationUtils.sendLoginFailNotifyEmail(SecurityNotificationUtils.getUserEmailByEB032159(param.getUid(), request), loginType, e);
                    getUserEmailByEB67050(loginUser);
                    loginUser.setMailType(2);
                }
                catch (XmlException | EAIException | EAIResponseException | ActionException ex) {
                    logger.warn("無法取得使用者 email", ex);
                }
            }
            // 該客戶為本行客戶但非本行網路銀行客戶，且非信用卡會員
            if (("EP32".equals(errorCode) || "EBC2".equals(errorCode)) && !CustomerType.isCardMember(request.getLoginType())) {
                try {
                    // EP32 要發簡訊, MOBILE_NO長度等於10位且開頭前2碼為09才需發送
                    String mobileno = getUserMobileForEP32(uidForMsg);
                    if (mobileno != null) {
                        loginUser.setMobileNo(mobileno.trim());
                        loginUser.setSmsType(2);
                    }

                    // if (StringUtils.length(mobileno) == 10 && StringUtils.startsWith(mobileno, "09")) {
                    // loginUser.setMobileNo(mobileno);
                    // loginUser.setSmsType(2);
                    // }
                }
                catch (XmlException | EAIException | EAIResponseException ex) {
                    logger.warn("無法取得使用者 mobile no", ex);
                }
            }

            // 信用卡登入失敗通知
            if (CustomerType.isCardMember(request.getLoginType())) {
                if (StringUtils.equals(e.getErrorCode(), "0203") || ArrayUtils.contains(CCLOGIN_ERROR_CODE, errorCode)) {
                    try {
                        loginUser.setEmail(getUserEmailByEB032159(request.getUid()));
                        loginUser.setMailType(2);
                    }
                    catch (XmlException | EAIException | EAIResponseException ex) {
                        logger.warn("無法取得使用者 email", ex);
                    }
                }
                else if (StringUtils.equals(e.getErrorCode(), "0204")) {
                    try {
                        loginUser.setEmail(getUserEmailByEB032159(request.getUid()));
                        loginUser.setMailType(2);
                    }
                    catch (XmlException | EAIException | EAIResponseException ex) {
                        logger.warn("無法取得使用者 email", ex);
                    }
                }
            }

            // 手勢登入失敗發email通知
            if (PwType.isPattern(request.getPwType())) {
                if (ArrayUtils.contains(GESTURE_ERROR_CODE, errorCode)) {
                    try {
                        loginUser.setEmail(getUserEmailByEB032159(request.getUid()));
                        loginUser.setMailType(2);
                    }
                    catch (XmlException | EAIException | EAIResponseException ex) {
                        logger.warn("無法取得使用者 email", ex);
                    }
                }
            }

            error = e;
        }
        catch (Exception ex) { // 【Fortify：Poor Error Handling: Overly Broad Throws】需要攔截所有例外
            logger.error("process() 未預期的錯誤。" + ex.getMessage(), ex);
            error = ExceptionUtils.getActionException(ex);
        }
        finally {
            loginUser.setError(error);
        }
        return loginUser;
    }

    /**
     * 複製回傳內容
     *
     * @param response
     * @param loginUser
     */
    private void copyProperties(ExecuteUserLoginResponse response, LoginUser loginUser) {

        response.setEnableStatus(loginUser.isEnableStatus());
        response.setEmail(loginUser.getEmail());
        response.setNextParam(loginUser.getNextParam());
        response.setNextTaskId(loginUser.getNextTaskId());
        response.setChannel(loginUser.getChannel());
        response.setPreLoginDate(loginUser.getPreLoginDate());
        response.setNameCode(loginUser.getNameCode());
        response.setCardEmail(loginUser.getCardEmail());
        response.setMobileNo(loginUser.getMobileNo());
        response.setLoginStatus(loginUser.getLoginStatus());
        response.setMultiUser(loginUser.isMultiUser());
        response.setSuccess(loginUser.isSuccess());
        response.setTheSame(loginUser.isTheSame());
        response.setBankUser(loginUser.isBankUser());
        response.setCompanyKey(loginUser.getCompanyKey());
        response.setUserKey(loginUser.getUserKey());
        response.setMailType(loginUser.getMailType());
        response.setSmsType(loginUser.getSmsType());
        response.setPushType(loginUser.getPushType());
        response.setShowChangeTip(loginUser.isShowChangeTip());
        response.setBirthDay(loginUser.getBirthDay());
        response.setInAccountCreditcardCheck(loginUser.isInAccountCreditcardCheck());
        response.setSameBirthday(loginUser.isSameBirthday());
        response.setTwoFactorAuth(loginUser.isTwoFactorAuth());
        response.setCountryCode(loginUser.getCountryCode());
        response.setCountryName(loginUser.getCountryName());

        copyUserEntity(loginUser.getUserEntity(), response.getUserVo());
        copyCompanyEntity(loginUser.getCompanyEntity(), response.getCompanyVo());
        copyLoginMsgRs(loginUser.getLoginMsgRs(), response.getLoginMsgRs());
        copyLoginEntity(loginUser.getUserLoginEntity(), response.getUserLoginVo());
        copyCardUserEntity(loginUser.getCardUserProfileEntity(), response.getCardUserProfileVo());
        copyMbDeviceInfoEntity(loginUser.getMbDeviceInfoEntity(), loginUser.getMbDevicePushInfoEntity(), response.getMbDeviceInfoVo());
        // 相同帳號其它裝置雙重驗證使用
        copyMbDeviceInfoEntity(loginUser.getMbDeviceInfoEntityBindedByOtherDevice(), loginUser.getMbDevicePushInfoEntityBindedByOtherDevice(), response.getMbDeviceInfoVoBindedByOtherDevice());

    }

    /**
     * 卡友登入
     *
     * @param request
     * @param loginUser
     * @param usersInDB
     * @return
     * @throws ActionException
     */
    private LoginUser processCcUser(ExecuteUserLoginRequest request, LoginUser loginUser, List<UserEntity> usersInDB) throws ActionException {

        UserLoginEntity loginEntity = loginUser.getUserLoginEntity();
        String proSecretAdvanced = "";
        try {
            if (loginUser.getUserEntity() == null) {
                // 找不到 USER_PROFILE
                throw new ActionException(AIBankErrorCode.USER_NOT_FOUND);
            }

            // 查 USER_LOGIN
            if (loginEntity == null) {
                loginEntity = getLoginEntity(loginUser.getCompanyKey(), loginUser.getUserEntity());
            }
            loginEntity.setNationName(loginUser.getCountryName());

            String uid = StringUtils.trimToEmptyEx(StringUtils.left(request.getUid(), 10));
            String proSecret = "";
            if (!PwType.isPattern(request.getPwType()) && !"Y".equals(request.getIsSignatureLogin())) {
                // 查DB時只取左邊10碼
                String proSecretTmp = encodewithSecret(E2EEHsmType.DB_3DES_UTF8_PWD_ENHANCE, request.getUid(), request.getUuid(), request.getSecrxt(), request.getIsPwdWithTime());
                proSecret = proSecretTmp.split("@")[0];
                proSecretAdvanced = proSecretTmp.split("@")[1];
            }

            // 密碼錯誤已超過最大次數
            if (loginEntity.getPwdErrorCount() >= MAX_PASSWORD_FAIL_COUNT) {
                logger.error("密碼錯誤已超過最大次數");
                throw new ActionException(AIBankErrorCode.PASSWORD_FAIL_COUNT);
            }
            // 使用者狀態
            else if (UserStatusType.ALIVE.getCode() != loginUser.getUserEntity().getStatus()) {
                logger.error("使用者狀態非使用中");
                throw new ActionException(AIBankErrorCode.USER_IS_STOPPED);
            }
            // 1:手勢
            else if (PwType.isPattern(request.getPwType())) {

                // 手勢密碼驗證
                try {
                    verifyGesturesPwd(request, loginUser);
                }
                catch (ActionException ex) {
                    getUserEmailByEB67050(loginUser);
                    throw ex;
                }
            }

            // 指紋、顏值登入的驗證。改以FIDO 驗證
            else if ("Y".equals(request.getIsSignatureLogin())) {
                logger.debug("FIDO");
            }
            // 驗證密碼
            else {
                boolean isCompareFail = true;
                // 變 Y 後就沒回頭路, 以後需要一直用 Y 來驗證
                if (securityResource.isCCUserLoginWithNewPassword(loginUser.getUserEntity().getPwdFlag() == null ? "X" : loginUser.getUserEntity().getPwdFlag(), request.getUid())) {
                    if (StringUtils.equalsIgnoreCase(proSecretAdvanced, loginUser.getUserEntity().getPasswordAdvanced())) {
                        // loginUser.getUserEntity().setPwdFlag(UserEntity.PWD_FLG_NEW_PASS);
                        isCompareFail = false;
                    }
                }
                else {
                    if (StringUtils.equalsIgnoreCase(proSecret, loginUser.getUserEntity().getSecret())) {
                        isCompareFail = false;
                    }
                }
                if (isCompareFail) {
                    // update pass-word error time
                    loginEntity.setPwdErrorTime(new Date());
                    int pwdErrorCount = loginEntity.getPwdErrorCount() == null ? 1 : loginEntity.getPwdErrorCount() + 1;
                    loginEntity.setPwdErrorCount(pwdErrorCount);
                    userLoginRepository.save(loginEntity);
                    if (pwdErrorCount >= MAX_PASSWORD_FAIL_COUNT) {
                        throw new ActionException(AIBankErrorCode.PASSWORD_FAIL_COUNT);
                    }
                    else {
                        // 密碼輸入錯誤，請重新輸入，若您忘記使用者代碼及密碼，可點選「忘記使用者代碼/密碼」進行重設。
                        logger.warn("密碼輸入錯誤");
                        throw new ActionException(AIBankErrorCode.CCUSER_LOGIN_PASSWORD_VALIDATE_ERROR);
                    }
                }
            }

            // 當 pwdFlag=1 執行驗證新密碼的正確性, 若正確設定 pwdFlag=Y , 日後不需在異動狀態, 都以新的驗證
            processCCuserCheckPasswordAdvenced(request.getUid(), loginUser.getUserEntity(), proSecretAdvanced);

            // 如果使用者登入完畢，在此將其PasswordAdvanced更新，條件是PasswordAdvenced欄位值為空
            // 20241112 使用者第1次遇到需開啟新密碼, 先算出新密碼後, 將密碼寫入DB(只需寫1次) pwdFlag=1 , N+1 次的登入流程, 仍使用舊密碼因為 PwdFlag != Y ( = 1), 在N+1次(密碼驗證成功), 才將狀態設成 Y, 此後 N+2 後的登入流程都是使用新密碼驗證
            processCCuserCheckUpdatePasswordAdvenced(request.getUid(), loginUser.getUserEntity(), proSecretAdvanced);

            validate(loginUser, "0001", request.getClientIP(), request.getCurrentSessionId(), request.isMdDupLoginConfirm());

            // 登入成功!
            CardUserProfileEntity card = cardUserProfileRepository.findByUserKey(loginUser.getUserKey());
            loginUser.setCardUserProfileEntity(card);
            loginUser.setUserEntity(usersInDB.get(0));

            CompanyEntity company = companyRepository.findByCompanyKey(loginUser.getUserEntity().getCompanyKey());
            loginUser.setCompanyEntity(company);

            // 信用卡有效性檢核
            boolean found = false;
            if ("0".equals(card.getCardMasterFlag())) {
                found = true;
                // 正卡會員
                CEW301RRS rs = sendCEW301R(loginUser.getCompanyEntity().getCompanyUid());
                String ACCOUNTSTSCODE = StringUtils.trimToEmptyEx(rs.getBody().getACCOUNTSTSCODE());
                if ("Y".equals(ACCOUNTSTSCODE)) {
                    // 特殊戶況註記
                    found = false;
                }
                else if ("N".equals(ACCOUNTSTSCODE) && "N".equals(StringUtils.trimToEmptyEx(rs.getBody().getCARDHOLDERCODE()))) {
                    // 非特殊戶況但無帳戶餘額
                    found = false;
                }

            }
            else {
                /** 改以 CE6220R 檢查 */
                found = isCreditCardAvailable(request.getUid(), "3");
            }

            // CEW302R無此卡片，註銷此帳號
            if (!found) {
                // 停用紀錄
                CardMemberRecordEntity record = new CardMemberRecordEntity();
                record.setCardKey(card.getCardKey());
                record.setAccessLogKey(0);
                record.setChangeItem("1");
                record.setStopReason("d");
                record.setCompanyUid(request.getUid());
                record.setCreateTime(new Date());
                record.setCsGroupName("無效會員登入");
                record.setCsId("LOGIN_CC");
                record.setUserUuid(request.getUuid());
                record.setTxStatus("0");
                cardMemberRecordRepository.save(record);

                // 註銷
                UserEntity user = loginUser.getUserEntity();
                user.setStatus(UserStatusType.DELETED.getCode());
                userProfileRepository.save(user);

                throw new ActionException(AIBankErrorCode.USER_IS_STOPPED);
            }

            // 判斷客戶是否半年未變更使用者密碼
            if (loginUser.getUserEntity() != null) {
                if (isMoreThanXMonth(loginUser.getUserEntity().getTxDate(), 6)) {
                    loginUser.setShowChangeTip(true);
                }
            }

            // 發查電話
            getCEW013RMobile(uid, loginUser);

            // 查詢 MbDeviceInfo
            loginUser.setMbDeviceInfoEntity(getMbDeviceInfoRepository(loginUser.getCompanyKey(), loginUser.getUserKey(), request.getDeviceId()));
            loginUser.setMbDevicePushInfoEntity(getMbDevicePushInfoRepository(loginUser.getCompanyKey(), loginUser.getUserKey(), request.getDeviceId()));

            MbDeviceInfoEntity mbDeviceInfoEntities = loginUser.getMbDeviceInfoEntity();
            if (mbDeviceInfoEntities != null) {
                /** 帳密登入後，解除快登禁用 */
                if (mbDeviceInfoEntities.getChgPwdUseridFlag() == 1 && mbDeviceInfoEntities.getUserKey().equals(loginUser.getUserEntity().getUserKey()) && mbDeviceInfoEntities.getDeviceUuId() != null && mbDeviceInfoEntities.getDeviceUuId().equals(request.getDeviceId())) {
                    mbDeviceInfoEntities.setChgPwdUseridDate(null);
                    mbDeviceInfoEntities.setChgPwdUseridFlag(0);
                    mbDeviceInfoRepository.save(mbDeviceInfoEntities);
                }
            }
        }
        catch (XmlException | EAIException | EAIResponseException ex) {
            logger.error(ex.getMessage(), ex);
            throw BizExceptionUtils.getBizActionException(ex);
        }
        return loginUser;
    }

    /**
     * 一般會員登入
     *
     * @param request
     * @param loginUser
     * @param needValidateAfterTxn
     * @param usersInDB
     * @return
     * @throws ActionException
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     * @throws CryptException
     */
    private LoginUser processEbUser(ExecuteUserLoginRequest request, LoginUser loginUser, boolean needValidateAfterTxn, List<UserEntity> usersInDB) throws ActionException, EAIResponseException, EAIException, XmlException, CryptException {

        // fortify: Redundant Null Check
        if (loginUser == null) {
            throw ExceptionUtils.getActionException(new IllegalArgumentException("loginUser in required"));
        }

        boolean isMbDeviceInfoChanged = false;
        try {
            // 手勢密碼驗證
            if (PwType.isPattern(request.getPwType())) {
                // 手勢密碼驗證
                try {
                    verifyGesturesPwd(request, loginUser);
                }
                catch (ActionException ex) {
                    getUserEmailByEB67050(loginUser);
                    // 手勢密碼驗證錯誤，拋 Exception
                    throw ex;
                }
            }
            // 指紋、顏值登入的驗證。
            else if ("Y".equals(request.getIsSignatureLogin())) {
                verifySignatureLogin(request.getDeviceId());
            }
            EB5556981RS rs = null;
            try {
                rs = sendEB5556981(request, "登入");
            }
            catch (CryptException e) {
                logger.error("", e);
                ActionException es = ExceptionUtils.getActionException(AIBankErrorCode.HSM_RESPONSE_ERROR);
                throw es;
            }
            catch (XmlException e) {
                ActionException es = BizExceptionUtils.getBizActionException(e);
                throw es;
            }
            catch (EAIException e) {
                if ("TX900".equals(e.getExtraCode())) {
                    rs = sendEB5556981(request, "登入(第二次)");
                }
                else {
                    if (StringUtils.isNotBlank(e.getExtraCode()) && e.getExtraCode().substring(0, 1).equals("X")) {
                        throw e;
                    }
                    throw new ActionException("AI", e.getErrorCode().getError().getErrorCode(), SeverityType.ERROR, e.getErrorCode().getError().getErrorDesc(), e);
                }
            }
            catch (EAIResponseException es) {
                EB5556981RS _rs = ((EB5556981RS) es.getResponse());
                if (_rs == null) {
                    throw es;
                }

                rs = _rs;
                String herrCode = _rs.getBody().getEMSGID();

                if (logger.isDebugEnabled()) {
                    logger.debug("#### herrCode:" + herrCode);
                }

                // 未啟用行銀
                if (ArrayUtils.contains(ENABLE_ERROR_CODE, herrCode)) {
                    logger.debug("啟用行銀");
                    sendEB552190(request.getUid(), request.getUuid(), request.getSecrxt(), request.getIsPwdWithTime());
                    // 設定為剛啟用行銀狀態
                    loginUser.setEnableStatus(true);
                    getUserEmailByEB67050(loginUser);
                    // throw ExceptionUtils.getActionException(new ErrorStatus(IBSystemId.AI.getSystemId(), es.getErrorCode(), SeverityType.ERROR, es.getErrorMessage()));
                    throw es;
                }
                else {
                    if (!"EBC5".equals(herrCode) && !"EP35".equals(herrCode) && !"EBD3".equals(herrCode)) {
                        throw ExceptionUtils.getActionException(new ErrorStatus(IBSystemId.AI.getSystemId(), es.getErrorCode(), SeverityType.ERROR, es.getErrorMessage()));
                    }
                }
            }

            EB5556981SvcRsType svcRsBody = rs == null ? null : rs.getBody();
            if (svcRsBody == null) {
                throw new ActionException("取得電文下行失敗", CommonErrorCode.UNKNOWN_EXCEPTION);
            }
            if (logger.isDebugEnabled()) {
                logger.debug("#### " + svcRsBody.getEMSGTXT());
            }

            // 取得下行先存入 result
            loginUser.setLoginMsgRs(svcRsBody);
            String idno = svcRsBody.getIDNO();
            String userid = svcRsBody.getUSERID();
            String namecod = svcRsBody.getNAMECOD();
            // 在此判斷 HERRID 是否為 EP35
            String hErrId = rs.getErrorCode();

            loginUser.setNameCode(namecod);

            if (!"EBC5".equals(hErrId) && !"EP35".equals(hErrId) && !ArrayUtils.contains(ENABLE_ERROR_CODE, hErrId)) {
                // 判斷 ERROR Message
                String errId = svcRsBody.getERROR();
                if (StringUtils.isNotBlank(errId)) {
                    // 有 error 訊息
                    throw new ActionException(IBSystemId.AI.getSystemId(), errId, SeverityType.ERROR, errId, null);
                }
            }
            else {
                // 若為 EP35, 表示首登, 要導頁至變更, id 為使用者輸入的 id 補空白到10位再加0
                idno = StringUtils.rightPad(request.getUid(), 10, ' ') + "0";
                userid = request.getUuid();
                namecod = "0001";
                // 暫存 NameCode
                loginUser.setTempNameCode(namecod);
            }

            if ("EBD3".equals(hErrId)) {
                idno = StringUtils.rightPad(request.getUid(), 10, ' ') + "0";
            }

            // 判斷USER_ID_LEVEL
            if ("3".equals(svcRsBody.getUSERIDLEVEL())) {
                throw new ActionException(AIBankErrorCode.USER_ID_LEVEL_ERROR);
            }
            // 登入成功, 依電文下行取得重覆碼後再找出正確的 userEntity 並檢核
            boolean found = false;
            for (UserEntity userEntity : usersInDB) {
                // B2CPibUser pibUser = new B2CPibUser(userEntity);
                CompanyEntity company = companyRepository.findByCompanyKey(userEntity.getCompanyKey());
                if (StringUtils.equals(idno, getCompanyUidDup(company))) {
                    if (loginUser.getUserEntity() == null || !Objects.equals(loginUser.getUserKey(), userEntity.getUserKey())) {
                        // 找到的不一樣
                        loginUser.setUserEntity(userEntity);
                    }
                    loginUser.setCompanyEntity(company);
                    found = true;
                    break;
                }
            }

            // 還是找不到, 新增一筆紀錄
            if (loginUser == null || !found) {
                UserRegisterationService registerationService = new UserRegisterationService(companyRepository, userProfileRepository, userLoginRepository);
                registerationService.registerNewCompanyAndUser(aesCipherUtils, loginUser, idno, userid.trim());
                validate(loginUser, namecod, request.getClientIP(), request.getCurrentSessionId(), request.isMdDupLoginConfirm());
            }
            else {
                loginUser.getUserEntity().setUserUuid(userid);
                // 否則, 再次檢核重覆登入
                validate(loginUser, namecod, request.getClientIP(), request.getCurrentSessionId(), request.isMdDupLoginConfirm());
            }

            /** 更新資料庫資料 **/
            String massChk = svcRsBody.getMASSCHK();
            massChk = StringUtils.trimToEmptyEx(massChk);
            loginUser.getCompanyEntity().setMassCheck(massChk);

            // 查詢 MbDeviceInfo
            loginUser.setMbDeviceInfoEntity(getMbDeviceInfoRepository(loginUser.getCompanyKey(), loginUser.getUserKey(), request.getDeviceId()));
            loginUser.setMbDevicePushInfoEntity(getMbDevicePushInfoRepository(loginUser.getCompanyKey(), loginUser.getUserKey(), request.getDeviceId()));

            // 帳密登入
            if (!"Y".equals(request.getIsSignatureLogin()) && !PwType.isPattern(request.getPwType())) {
                // 8408 登入後密碼代號檢核判斷改成與舊行銀程式一致
                // 若主機回應碼為EP35, 則強制需變更密碼

                int securityRulesResult = checkSecuirtyRules(request.getUid(), request.getUuid(), request.getSecrxt(), request.getIsPwdWithTime());
                if (securityRulesResult != 0) {
                    logger.warn("checkSecuirtyRules failed: " + securityRulesResult);
                }

                if (("EBC5".equals(hErrId) || "EP35".equals(hErrId))) {
                    // #666
                    // 透過Web ATM重設的密碼，會符合安控，但登入時會回傳EBC5，此時要再導變更密碼
                    // 變更使用者密碼
                    loginUser.setNextTaskId(getSystemParam("PIB", "TASK_CHANGE_PWD", ""));
                    loginUser.setNextParam(AtferLoginJobType.CHANG_PINBLOCK.getType());
                }
                // check 是否為首次登入, checkSecuirtyRules 回覆 11 表示兩者皆不符, 即為首次登入
                if (11 == securityRulesResult) {
                    // 變更使用者代碼及密碼
                    loginUser.setNextTaskId(getSystemParam("PIB", "TASK_CHANGE_NAME_PWD", ""));
                    loginUser.setNextParam(AtferLoginJobType.CHANG_USERID_PINBLOCK.getType());
                }
                else if (10 == securityRulesResult) {
                    // 變更使用者代碼
                    loginUser.setNextTaskId(getSystemParam("PIB", "TASK_CHANGE_NAME", ""));
                    loginUser.setNextParam(AtferLoginJobType.CHANG_USERID.getType());
                }
                else if (1 == securityRulesResult) {
                    // 變更使用者密碼
                    loginUser.setNextTaskId(getSystemParam("PIB", "TASK_CHANGE_PWD", ""));
                    loginUser.setNextParam(AtferLoginJobType.CHANG_PINBLOCK.getType());
                }

                if (loginUser.getNextParam() != null) {
                    getUserEmailByEB67050(loginUser);
                }

                // 判斷客戶是否半年未變更使用者密碼
                if (loginUser.getNextParam() == null) {
                    // 一般會員
                    if (svcRsBody.getPWCHGDATE() != null) {
                        if (isMoreThanXMonth(svcRsBody.getPWCHGDATE(), 6)) {
                            try {
                                loginUser.setShowChangeTip(isShowChangeTip(loginUser.getCompanyKey(), loginUser.getUserKey()));
                            }
                            catch (Exception ex) { // 【Fortify：Poor Error Handling: Overly Broad Throws】需要攔截所有例外
                                loginUser.setShowChangeTip(false);
                            }
                        }
                    }
                }

                if ("0000".equals(hErrId)) {
                    MbDeviceInfoEntity mbDeviceInfoEntity = loginUser.getMbDeviceInfoEntity();

                    if (mbDeviceInfoEntity != null) {
                        /** 帳密登入後，解除快登禁用 */
                        if (mbDeviceInfoEntity.getChgPwdUseridFlag() == 1 && mbDeviceInfoEntity.getUserKey().equals(loginUser.getUserEntity().getUserKey()) && mbDeviceInfoEntity.getDeviceUuId() != null && mbDeviceInfoEntity.getDeviceUuId().equals(request.getDeviceId())) {
                            mbDeviceInfoEntity.setChgPwdUseridDate(null);
                            mbDeviceInfoEntity.setChgPwdUseridFlag(0);
                            isMbDeviceInfoChanged = true;
                        }
                    }
                }
            }
            // 快登登入
            else {
                // (i) 檢核電文EB5556981_Rs.TxHead.HERRID=EBD3，表示客戶曾臨櫃取得密碼函或是在ATM重置密碼。
                // (ii) 判斷客戶類型，若COMPANY.COMPANY_UID長度<>8，表示”個人戶”；否則為”非個人戶”。
                // (iii) 更新該客戶變更密碼註記，更新MB_DEVICE_INFO.CHG_PWD_USERID_FLAG為1，MB_DEVICE_INFO.CHG_PWD_USERID_DATE為系統日。
                // (iv) 解除該客戶快登設定，更新MB_DEVICE_INFO. LOGIN_FLAG=0, LOGIN_PASSWD_TYPE=0。

                if ("EBD3".equals(hErrId)) {
                    loginUser.setNextParam(AtferLoginJobType.RE_LOGIN.getType());
                    // 更新該客戶變更密碼註記
                    if (loginUser.getMbDeviceInfoEntity() != null) {
                        loginUser.getMbDeviceInfoEntity().setChgPwdUseridFlag(1);
                        loginUser.getMbDeviceInfoEntity().setChgPwdUseridDate(new Date());
                    }

                    isMbDeviceInfoChanged = true;
                }
            }

            // 新增判斷此使用者是否為日盛會員首次登入行動銀行，需顯示導讀內容
            // 3/28 改為不需判斷
        }
        catch (EAIResponseException ere) {
            loginUser.setLoginMsgRs((EB5556981SvcRsType) ere.getResponse().getBody());
            throw ere;
        }
        finally {
            if (loginUser.getUserEntity() != null) {
                userProfileRepository.save(loginUser.getUserEntity());
            }
            if (loginUser.getUserLoginEntity() != null) {
                userLoginRepository.save(loginUser.getUserLoginEntity());
            }

            if (isMbDeviceInfoChanged && loginUser.getMbDeviceInfoEntity() != null) {
                mbDeviceInfoRepository.save(loginUser.getMbDeviceInfoEntity());
            }

        }
        return loginUser;
    }

    /**
     * 如果使用者登入完畢，在此將其PxsswxrdAdvanced更新，條件是PxsswxrdAdvenced欄位值為空
     * 
     * @param companyUxd
     * 
     * @param lxginUsxr
     * 
     * @param newPxsswxrd
     */
    private void processCCuserCheckUpdatePasswordAdvenced(String companyUid, UserEntity userEntity, String newPassword) {
        // 如果使用者登入完畢，在此將其pxsswordAdvanced更新
        if (securityResource.isPasswordAdvancedEnable(companyUid)) {
            logger.info("processCCuserCheckUpdatePasswordAdvenced PASSWORD_ADVANCED密碼，開放更新");

            if (userEntity.isNeedUpdatePasswordAdvanced()) {
                logger.info("processCCuserCheckUpdatePasswordAdvenced 需要更新 UserEntity PASSWORD_ADVANCED = [SHA512] and PWD_FLAG = 1");
                userEntity.setPasswordAdvanced(newPassword);
                userEntity.setPwdFlag(UserEntity.PWD_FLG_USER_CHANGED);
                try {
                    userProfileRepository.save(userEntity);
                    logger.info("processCCuserCheckUpdatePasswordAdvenced 成功更新 UserEntity PASSWORD_ADVANCED and PWD_FLAG");
                }
                catch (Exception e) {
                    logger.error("processCCuserCheckUpdatePasswordAdvenced 在存入UserEntity時，發生錯誤", e);
                }
            }
            else {
                logger.info("processCCuserCheckUpdatePasswordAdvenced 不需更新");
            }
        }
        else {
            logger.info("processCCuserCheckUpdatePasswordAdvenced PASSWORD_ADVANCED密碼，未開放更新");
        }
    }

    /**
     * 完成登入驗證後，判斷是否需要檢查新密碼，並更新至PWD_FLAG欄位 ( 只處理第1次資轉後的判斷, 第1次用新的密碼登入後, 日後都用新密碼)
     * 
     * @param loginUser
     * @param newPxsswxrd
     */
    private void processCCuserCheckPasswordAdvenced(String companyUid, UserEntity userEntity, String newPassword) {
        // 新增一個開關，判斷該開關是否為true且已經到達啟用驗證新密碼驗證流程時間，
        // >>如果皆通過檢核，則繼續以下判斷，如未通過檢核，則不驗證新密碼正確性
        if (securityResource.isCCUserNewLoginProcessEnable(companyUid)) {
            // [密碼安全性提升-二階] 完成登入驗證後，判斷是否需要檢查新密碼，並更新至PWD_FLAG欄位
            if (userEntity.isNeedCheckPasswordAdvanced()) {
                if (StringUtils.equalsIgnoreCase(userEntity.getPasswordAdvanced(), newPassword)) {
                    userEntity.setPwdFlag(UserEntity.PWD_FLG_NEW_PASS);
                    try {
                        userProfileRepository.save(userEntity);
                        logger.info("LoginHelper 成功更新 UserEntity PWD_FLAG");
                    }
                    catch (IllegalArgumentException e) {
                        logger.error("LoginHelper在存入UserEntity時，發生錯誤", e);
                    }
                }
            }
        }
    }

    /**
     * 0-未綁定，未訂閱 1-不推播，2-推播
     */
    private int getPushType(Integer companyKey, Integer userKey, String deviceId, boolean twoFactorAuth) {

        List<MbDeviceInfoEntity> mbDeviceInfoEntities = mbDeviceInfoRepository.findByCompanyKeyAndUserKey(companyKey, userKey);

        // 未綁定
        if (CollectionUtils.isEmpty(mbDeviceInfoEntities)) {
            return 0;
        }

        // 非雙重驗證及同裝置
        if (!twoFactorAuth && StringUtils.equals(mbDeviceInfoEntities.get(0).getDeviceUuId(), deviceId)) {
            return 0;
        }

        // 未訂閱
        List<PushSubscriptionEntity> pushEntities = pushSubscriptionRepository.findByDeviceInfoKeyAndPushCode(mbDeviceInfoEntities.get(0).getDeviceInfoKey(), "LG01");
        if (CollectionUtils.isEmpty(pushEntities)) {
            return 0;
        }

        // 查 MB_DEVICE_PUSH_INFO 判斷NOTIFY_PASS
        List<MbDevicePushInfoEntity> mbDevicePushInfoEntities = mbDevicePushInfoRepository.findByCompanyKeyAndUserKey(companyKey, userKey);

        // 查無資料，防呆，不該發生
        if (CollectionUtils.isEmpty(mbDevicePushInfoEntities)) {
            return 0;
        }

        // 3.1 若 NOTIFY_PASS = 0，表示不推播，寫入 PERSON_NOTIFICATION_RECORD 的 IS_PUSH = 0
        // 3.2 若 NOTIFY_PASS ≠ 0，表示推播，寫入 PERSON_NOTIFICATION_RECORD 的 IS_PUSH = 1
        if (mbDevicePushInfoEntities.get(0).getNotifyPass() == 0) {
            return 1;
        }
        else {
            return 2;
        }

    }

    private MbDevicePushInfoEntity getMbDevicePushInfoRepository(Integer companyKey, Integer userKey, String deviceId) {
        try {
            // 【FORTIFY：Access Control: Database】COMPANY_KEY、USER_KEY 就是身分識別屬性欄位
            List<MbDevicePushInfoEntity> mbDevicePushInfoEntities = mbDevicePushInfoRepository.findByCompanyKeyAndUserKeyAndDeviceUuId(companyKey, userKey, deviceId);

            if (mbDevicePushInfoEntities != null && !mbDevicePushInfoEntities.isEmpty()) {
                return mbDevicePushInfoEntities.get(0);
            }
        }
        catch (Exception ex) { // 【Fortify：Poor Error Handling: Overly Broad Throws】需要攔截所有例外
            logger.info("查詢產生例外，不影響程序。");
            logger.error(ex.getMessage(), ex);
        }

        return null;
    }

    /**
     * 是否有信用卡
     *
     * @param custId
     * @param type
     * @return
     * @throws ActionException
     */
    private boolean isCreditCardAvailable(String custId, String type) throws ActionException {

        List<String> allCards = null;
        try {
            allCards = sendCE6220R(custId, type);
        }
        catch (XmlException | EAIException | EAIResponseException e) {
            logger.warn("發送電文 CE622R 失敗，不影響程序。", e);
            // #7940 若真沒有收到回覆電文，也不能直接註銷信用卡登入，只能是登入失敗
            throw BizExceptionUtils.getBizActionException(e);
        }

        if (allCards == null || allCards.size() == 0) {
            return false;
        }
        return true;
    }

    /**
     * 早於 x 個月前
     *
     * @param date
     * @param xMonth
     * @return true = 早於 x 個月前
     */
    private boolean isMoreThanXMonth(Date date, int xMonth) {
        if (date == null) {
            return true;
        }
        Calendar compareDate = Calendar.getInstance();
        compareDate.setTime(date);
        return isMoreThanXMonth(compareDate, xMonth);
    }

    private boolean isMoreThanXMonth(Calendar calendar, int xMonth) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, xMonth * -1);
        return calendar.compareTo(cal) == -1 ? true : false;
    }

    /**
     * 判斷是否要提醒變更密碼
     *
     * true:
     *
     * 1. ChangePasswordTipsEntity 沒有就提醒，並加上一筆
     *
     * 2. ChangePasswordTipsEntity 有，且上次提醒日超過6個月，並更新時間
     */
    private boolean isShowChangeTip(Integer companyKey, Integer userKey) {
        List<ChangePasswordTipsEntity> entities = changePasswordTipsRepository.findByCompanyKeyAndUserKey(companyKey, userKey);
        boolean isShow = true;
        if (CollectionUtils.isNotEmpty(entities)) {
            ChangePasswordTipsEntity changePasswordTipsEntity = entities.get(0);
            if (!isMoreThanXMonth(changePasswordTipsEntity.getTxDate(), 6)) {
                isShow = false;
            }
        }
        return isShow;
    }

    /**
     * 呼叫EB032159 取得 email
     *
     * @param uid
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    private List<String> getUserEmailByEB032159(String uid) throws XmlException, EAIException, EAIResponseException {
        EB032159RS rs = esbGateway.sendEB032159(uid);
        List<String> emailList = new ArrayList<String>();

        String email = StringUtils.trimToEmptyEx(rs.getBody().getEMAILADDR());
        logger.info("EB032159電文 email" + email);
        emailList.add(email);

        return emailList;
    }

    /**
     * 取得ID+誤別碼下所有 e-mail
     *
     * @param uid
     * @param idType
     * @return
     * @throws ActionException
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    private List<String> getUserEmailByEB67050(String uid) throws ActionException, XmlException, EAIException, EAIResponseException {
        String idType = BaNCSUtil.getIDTYPE(uid);

        EB067217RS eb067217rs = esbGateway.sendEB067217(uid, idType);

        List<String> emailList = new ArrayList<String>();
        for (TxRepeatType txRepeatType : eb067217rs.getBody().getTxRepeatList()) {
            EB067217RepeatType repeatType = (EB067217RepeatType) txRepeatType.changeType(EB067217RepeatType.type);
            String id = repeatType.getIDNO();
            if (StringUtils.isBlank(id)) {
                continue;
            }
            EB67050RS eb67050rs = esbGateway.sendEB67050(id, idType);

            emailList.add(eb67050rs.getBody().getEMAILADDR());
        }
        return emailList;
    }

    private void getUserEmailByEB67050(LoginUser loginUser) throws ActionException, XmlException, EAIException, EAIResponseException {

        String idType = BaNCSUtil.getIDTYPE(loginUser.getCustId());
        EB067217RS eb067217rs = esbGateway.sendEB067217(loginUser.getCustId(), idType);
        String mobileNo = "";

        List<String> emailList = new ArrayList<String>();
        for (TxRepeatType txRepeatType : eb067217rs.getBody().getTxRepeatList()) {
            EB067217RepeatType repeatType = (EB067217RepeatType) txRepeatType.changeType(EB067217RepeatType.type);
            String id = repeatType.getIDNO();
            String type = repeatType.getIDTYPE();
            if (StringUtils.isBlank(id)) {
                continue;
            }
            if (StringUtils.isBlank(type)) {
                type = idType;
            }
            EB67050RS eb67050rs;
            try {
                // fortify: Poor Style: Value Never Read
                eb67050rs = esbGateway.sendEB67050(id, type);
                EB67050SvcRsType body = eb67050rs.getBody();
                emailList.add(body.getEMAILADDR());
                if (StringUtils.isNotBlank(body.getMOBILE())) {
                    mobileNo = body.getMOBILE().trim();
                }
            }
            catch (EAIResponseException ex) {
                logger.warn("EB67050 發生錯誤", ex);
            }

        }
        loginUser.setEmail(emailList);
        loginUser.setMobileNo(mobileNo);
    }

    /**
     * 取得行動電話 該客戶為本行客戶但非本行網路銀行客戶
     *
     * @param uid
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    private String getUserMobileForEP32(String uid) throws XmlException, EAIException, EAIResponseException {

        EB032154RS rs = esbGateway.sendEB032154(uid);

        EB032154SvcRsType rsType = rs.getBody();
        String mobileNo = "";

        if (!StringUtils.isEmpty(rsType.getHandphone())) {
            mobileNo = rsType.getHandphone();
        }
        logger.info("EB032154電文 mobileno" + rsType.getHandphone());

        return mobileNo;
    }

    /**
     * 登入交易
     *
     * @param request
     * @param memo
     * @param cryptoProxy
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     * @throws CryptException
     * @throws ActionException
     */
    private EB5556981RS sendEB5556981(ExecuteUserLoginRequest request, String memo) throws CryptException, XmlException, EAIException, EAIResponseException, ActionException {
        String cipherPass = "";
        String encAcnoID = "";
        if (!"Y".equals(request.getIsSignatureLogin()) && !PwType.isPattern(request.getPwType())) {
            String encodedPwd = "";
            try {
                encodedPwd = encodewithSecret(E2EEHsmType.PWD_EB5556981_CP1047, request.getUid(), request.getUuid(), request.getSecrxt(), request.getIsPwdWithTime());
            }
            catch (RuntimeException ex) {
                logger.error(ex.getMessage(), ex);
                if (ex instanceof FeignException) {
                    ActionException e = getActionExceptionFromException(ex.getLocalizedMessage());
                    if (e != null) {
                        throw e;
                    }
                }

                throw ex;
            }
            cipherPass = encodedPwd.split("@")[0];
            encAcnoID = encodedPwd.split("@")[1];
        }

        return esbGateway.sendEB5556981(request, "登入", cipherPass, encAcnoID);
    }

    /**
     * @return
     * @throws ActionException
     * @throws Exception
     * @throws EAIException
     * @throws XmlException
     */
    //private EB5556986RS sendEB5556986(ExecuteUserLoginRequest request, CryptoProxy cryptoProxy) throws Exception {
    //    // E2EE Encrypt
    //    ArrayList<Character> numberList = null;
    //    ArrayList<Character> charList = null;
    //    String cipherPass = "";
    //    String encAcnoID = "";
    //    String cipherPassAdv = "";
    //    String encAcnoIDAdv = "";
    //    try {
    //        // EB5556986.PASS-WORD@EB5556986.ACNOID@EB5556986.ACNOID_ADVANCED
    //        String encodedPwd = encodeWithPasswordRule(E2EEHsmType.PWD_EB5556986_CP1047_PWD_ENHANCE, request.getUid(), null, request.getSecret(), numberList, charList, isPwdWithTime, request);
    //        // PASS-WORD
    //        cipherPass = encodedPwd.split("@")[0];
    //        // ACNOID
    //        encAcnoID = encodedPwd.split("@")[1];
    //        // ACNOID-ADV
    //        encAcnoIDAdv = encodedPwd.split("@")[2];
    //    } catch (E2EEUtils_AIBank.E2EEException e) {
    //        logger.error("[E2EE]encode pwd失敗，無法繼續登入");
    //        ActionException aEx = ExceptionUtils.getActionException(e);
    //        if("66216".equals(e.getMessage())) {
    //            aEx = ExceptionUtils.getActionException(PibLoginErrorCode.SESSION_TIMEOUT);
    //            logger.error("[E2EE]encode pwd失敗，無法繼續登入:66216");
    //        }
    //        throw ExceptionUtils.getActionException(aEx);
    //    }
    //
    //
    //    EB5556986Helper helper = new EB5556986Helper();
    //    helper.setHTLID("2004011");
    //    helper.setIDNO(StringUtils.upperCase(StringUtils.trimToEmpty(param.getUid())));
    //    helper.setNAME_COD("0001");  // 登入時寫死 0001
    //    helper.setITEM_NO("2");
    //    helper.setUSER_ID(StringUtils.upperCase(StringUtils.trimToEmpty(param.getUuid())));
    //    helper.setUSERTYPEC(cipherPass);
    //    helper.setNEWUSER_ID("");
    //    helper.setACNO_ID(encAcnoID);
    //    helper.setREMARK("");
    //    helper.setBUS_EB_5("");
    //    helper.setACNO_IN(StringUtils.equals(loginType, "m3") ? this.accountNo : "");
    //    //[一般會員]密碼安全性提升
    //    helper.setACNO_ID_ADVANCED(encAcnoIDAdv);
    //    helper.setREMARK_ADVANCED("");
    //
    //
    //
    //    EB5556986RS rs = helper.execute(EAIChannel.CBS, B2CUtils.getAccessLogKeyFromRequestScope(request), memo);
    //    return esbGateway.sendEB5556986(request, "登入", cipherPass, encAcnoID);
    //}

    /**
     * 檢查信用卡狀態
     *
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    private CEW301RRS sendCEW301R(String uid) throws XmlException, EAIException, EAIResponseException {
        return esbGateway.sendCEW301R(uid);
    }

    /**
     * 加密為DB儲存的加密模式
     *
     * @param e2eeHsmType
     * @param uixd
     * @param uuixd
     * @param secrxt
     * @return
     */
    private String encodewithSecret(E2EEHsmType e2eeHsmType, String uid, String uuid, String secret, boolean pwdWithTime) {
        EncodeWithSecretRequest rq = new EncodeWithSecretRequest();
        rq.setE2eeHsmType(e2eeHsmType.name());
        rq.setUid(uid);
        rq.setUuid(uuid);
        rq.setEncodedSecret(secret);
        rq.setIsPwdWithTime(pwdWithTime);

        EncodeWithSecretResponse rs = securityResource.encodewithSecret(rq);
        return rs.getHostSecret();
    }

    /**
     * 檢查密碼的狀態
     *
     * @param uixd
     * @param uuixd
     * @param secrxt
     * @return
     */
    private int checkSecuirtyRules(String uid, String uuid, String secret, Boolean isPwdWithTime) {

        CheckSecuirtyRulesRequest rq = new CheckSecuirtyRulesRequest();
        rq.setUid(uid);
        rq.setUuid(uuid);
        rq.setEncodedSecret(secret);
        rq.setIsPwdWithTime(isPwdWithTime);

        CheckSecuirtyRulesResponse rs = securityResource.checkSecuirtyRules(rq);
        return rs.getStatus();
    }

    /**
     * 取得DB USER_PROFILE內的使用者資料
     *
     * @param id
     * @param account
     * @param pass-word
     * @return
     * @throws DatabaseException
     * @throws ActionException
     * @throws LoginActionException
     */
    private List<UserEntity> queryLoginUser(String uid, String uuid, String loginType) throws DatabaseException, ActionException {

        CompanyKindType companyKind = null;
        // 查DB時只取左邊10碼
        uid = StringUtils.trimToEmptyEx(StringUtils.left(uid, 10));
        List<UserEntity> usersInDB = null;
        if (!CustomerType.isCardMember(loginType)) {
            companyKind = StringUtils.length(uid) == 8 ? CompanyKindType.COMPANY : CompanyKindType.PERSONAL;
            // 【FORTIFY：Access Control: Database】uid、uuid 就是身分識別屬性欄位
            usersInDB = userProfileRepository.findByUidUUidCompany(encrypt(ValidateParamUtils.validParam(uid)), ValidateParamUtils.validParam(uuid), companyKind.getCode());
        }
        else {
            // 查詢信用卡會員
            // 【FORTIFY：Access Control: Database】uid、uuid 就是身分識別屬性欄位
            usersInDB = userProfileRepository.findCardUser(encrypt(ValidateParamUtils.validParam(uid)), ValidateParamUtils.validParam(uuid));
        }
        return usersInDB;
    }

    /**
     * 加密資訊
     *
     * @param uxd
     * @return
     * @throws ActionException
     */
    private String encrypt(String uid) throws ActionException {
        if (aesCipherUtils == null) {
            SecretKeyProvider<?> provider = SecretKeyProviderFactory.getInstance().getProvider(AIBankConstants.CRYPTO_SECRET_KEY_PROVIDER_DEFAULT);
            aesCipherUtils = new AESCipherUtils(provider);
        }
        try {
            return aesCipherUtils.encrypt(uid);
        }
        catch (CryptException e) {
            logger.error(e.getMessage(), e);
            throw BizExceptionUtils.getActionException(e);
        }
    }

    /**
     * 取得 USER_LOGIN 資料
     *
     * @param userKey
     * @param userEntity
     * @return
     */
    private UserLoginEntity getLoginEntity(int userKey, UserEntity userEntity) {
        UserLoginEntity userLoginEntity = userLoginRepository.findByUserKey(userKey);

        if (userLoginEntity == null) {
            userLoginEntity = new UserLoginEntity();
            userLoginEntity.setCompanyKey(userEntity.getCompanyKey());
            userLoginEntity.setUserKey(userEntity.getUserKey());
            userLoginEntity.setStatus(-1);
            userLoginEntity.setPwdErrorCount(0);
            userLoginEntity.setPwdErrorTime(null);
            userLoginEntity.setPwdChangeTime(null);
            userLoginEntity.setPwdForceChange(0);
            userLoginEntity.setSessionId("");
            userLoginEntity.setClientIp("");
            userLoginEntity.setLoginTime(null);
            userLoginEntity.setLastAccessTime(null);
            userLoginEntity.setLastLoginTime(null);
            userLoginRepository.save(userLoginEntity);
        }

        return userLoginEntity;

    }

    /**
     * 回傳 rightPad(UID, 10, ' ') + 誤別碼 'A123456789', '0' --> 'A1234567890' '87178818', '1' --> '87178818 1'
     *
     * @return
     * @throws DatabaseException
     */
    private String getCompanyUidDup(CompanyEntity company) {

        return StringUtils.rightPad(company.getCompanyUid(), 10, ' ') + StringUtils.defaultIfBlank(company.getUidDup(), "0");
    }

    /**
     * 登入檢核
     *
     * @param result
     *            loginResult
     * @param userEntity
     * @return
     */
    private void validate(LoginUser loginUser, String nameCode, String clientIP, String sessionId, boolean mdDupLoginConfirm) throws ActionException {
        // 檢核是否已登入

        // loginUser.refreshLoginEntity(); 只為了清空 cache，應該不用了
        UserLoginEntity loginEntity = loginUser.getUserLoginEntity();

        if (loginEntity == null) {
            loginEntity = getLoginEntity(loginUser.getUserKey(), loginUser.getUserEntity());
            loginUser.setUserLoginEntity(loginEntity);
        }
        // 如果沒有登入資料，不繼續驗
        if (loginEntity == null) {
            return;
        }

        loginEntity.setNationName(loginUser.getCountryCode());

        LoginStatusType status = LoginStatusType.find(loginEntity == null ? LoginStatusType.LOGIN.getCode() : loginEntity.getStatus());

        // (a) 若N分鐘內，重複登入
        Date lastAccessTime = loginEntity.getLastAccessTime();
        Date minLastAccessTime = DateUtils.addSeconds(new Date(), getSystemParam("PIB", "DEFAULT_TASK_TIMEOUT", 360) * -1);

        // 是否為登入狀態且最後存取時間未超過參數值
        if (lastAccessTime != null && LoginStatusType.LOGIN.equals(status) && (lastAccessTime != null && !lastAccessTime.before(minLastAccessTime))) {
            // 已登入, 判斷是否同IP & 同 session ID
            String lastClientIp = loginEntity.getClientIp();
            Date currentLoginTime = loginEntity.getLoginTime();
            String currentIp = clientIP;
            boolean confirmed = mdDupLoginConfirm; // @mobile
            // 是否可重覆登入
            boolean allowDuplicateLogin = getSystemParam(AIBankConstants.CHANNEL_NAME, "ALLOW_DUPLICATE_LOGIN", false);

            logger.info("USER_LOGIN_DUPLICATE, check confirmed = {}, {}, {}", confirmed, lastClientIp, currentIp);
            loginUser.setPreLoginDate(currentLoginTime);
            if (!allowDuplicateLogin) {
                // 是否是已確認後?
                if (!confirmed) {
                    // 不同 sessionId, 顯示後踢前確認頁
                    logger.error("USER_LOGIN_DUPLICATE, IP diff confirmed = {}, {}", confirmed);

                    String channel = "";
                    if (ChannelIdType.MobileBank.getChannelId().equals(loginEntity.getChannelId())) {
                        // 行動銀行
                        channel = "mobile";
                    }
                    else if (ChannelIdType.eBank.getChannelId().equals(loginEntity.getChannelId())) {
                        // 網銀
                        channel = "b2c";
                    }
                    loginUser.setChannel(channel);

                    String lastLoginTime = DateFormatUtils.CE_DATETIME_FORMAT.format(new Date());
                    throw new ActionException(AIBankErrorCode.USER_LOGIN_DUPLICATE, lastLoginTime);
                }
                else {
                    // if (StringUtils.equals(loginEntity.getChannelId(), ChannelIdType.AIBank.getChannelId())) {
                    // return;
                    // }
                    // else {
                    // // 使用者已登入其他系統，請登出後再進行行動銀行登入！
                    // throw new ActionException(AIBankErrorCode.USER_LOGINED_IN_OTHER);
                    // }

                }
            }
        }
        // 檢核登入上限
        checkLoginMonitor(loginUser);

    }

    /**
     * 流量管理，本部分待規格確認後修正
     *
     * @param loginUser
     */
    public void checkLoginMonitor(LoginUser loginUser) {
        boolean enable = "Y".equals(getSystemParam("MONITOR", "MB_ENABLE", "Y"));
        boolean enableLogin = "Y".equals(getSystemParam("MONITOR", "MB_ENABLE_LOGIN", "Y"));
        // boolean autoLockInter = "Y".equals(getSystemParam("MONITOR", "MB_AUTO_LOCK", "Y"));

        // 判斷行內或行外 (未設定或找不到視為行外)
        // String netType = "INTER"; // StringUtils.contains(intranetServers, B2CWebSystem.getServerId()) ? "INTRA" : "INTER";
        // 有 enable monitor 才檢查人數
        if (enable && enableLogin) {
            // 取得人數上限
            // int maxLoginByNettype = ConvertUtils.str2Int(getSystemParam("MONITOR", "MB_MAX_LOGIN", "3000"));
            // 目前的登入人數
            // int loginedSessionCount = ConvertUtils.getDefaultNumber((Integer) PibSystem.getSystemStatus().get("LOGIN_COUNT." + netType), 0);
            // int loginedSessionCount = 0;
            // 超過人數上限
            // @formatter:off
            /**
            if (loginedSessionCount > maxLoginByNettype) {
                // 要自動鎖定才丟出 exception
                if (("INTRA".equals(netType) && autoLockIntra) || ("INTER".equals(netType) && autoLockInter)) {
                    if (!validateAccountLoginCheckPass(loginUser.getCompanyUid(), systemProperty.getValue("MONITOR", "ACCOUNT_LOGIN_CHECK_PASS"))) {
                        throw new ActionException(PibLoginErrorCode.MAX_LOGIN_EXEEDED);
                    }
                }
            }
            **/
            // @formatter:on
        }
    }

    /**
     * 手勢密碼驗證
     * 
     * @param request
     * @param loginUser
     * @throws ActionException
     */
    private void verifyGesturesPwd(ExecuteUserLoginRequest request, LoginUser loginUser) throws ActionException {

        logger.info("手勢密碼驗證:" + request.getDeviceId());

        MbDeviceInfoEntity mbDeviceInfoEntities = loginUser.getMbDeviceInfoEntity();

        if (mbDeviceInfoEntities == null) {
            mbDeviceInfoEntities = mbDeviceInfoRepository.findByDeviceUuid(ValidateParamUtils.validParam(request.getDeviceId()));
        }

        if (mbDeviceInfoEntities == null) {
            logger.error("手勢密碼:查無 MB_DEVICE_INFO");
            throw new ActionException(AIBankErrorCode.DEVICE_BINDING_STATUS_INVALID);
        }

        if (mbDeviceInfoEntities.getEnable() != 1 || mbDeviceInfoEntities.getLoginPasswdType() != 1) {
            logger.error("手勢密碼:MB_DEVICE_INFO 狀態錯誤 LoginPasswdType={}", mbDeviceInfoEntities.getLoginPasswdType());
            throw new ActionException(AIBankErrorCode.DEVICE_BINDING_STATUS_INVALID);
        }

        CompanyEntity companyEntity = companyRepository.findByCompanyKey(mbDeviceInfoEntities.getCompanyKey());

        if (companyEntity == null) {
            logger.error("CompanyEntity not found {}", mbDeviceInfoEntities.getCompanyKey());
            throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
        }

        if (!companyEntity.getCompanyUid().equals(request.getUid())) {
            logger.error("手勢密碼:Company 資訊不符 {} = {}", IBUtils.toDataModel(DataMaskUtil.maskCustId(companyEntity.getCompanyUid()), String.class), IBUtils.toDataModel(request.getUid(), String.class));
            throw new ActionException(AIBankErrorCode.DEVICE_BINDING_STATUS_INVALID);
        }

        UserEntity userEntity = userProfileRepository.findByUserKey(mbDeviceInfoEntities.getUserKey());
        if (userEntity == null) {
            logger.error("UserEntity not found {}", IBUtils.toDataModel(mbDeviceInfoEntities.getCompanyKey(), Integer.class));
            throw ExceptionUtils.getActionException(AIBankErrorCode.DEVICE_BINDING_STATUS_INVALID);
        }

        if (!userEntity.getUserUuid().equals(request.getUuid())) {
            logger.error("手勢密碼:User Profile 資訊不符 {} = {}", IBUtils.toDataModel(DataMaskUtil.maskCustId(companyEntity.getCompanyUid()), String.class), IBUtils.toDataModel(request.getUid(), String.class));
            throw new ActionException(AIBankErrorCode.DEVICE_BINDING_STATUS_INVALID);
        }

        MbGestureProfileEntity mbGestureProfileEntity = mbGestureProfileRepository.findByDeviceInfoKey(mbDeviceInfoEntities.getDeviceInfoKey());
        if (mbGestureProfileEntity == null) {
            logger.error("MbGestureProfileEntity not found {}", IBUtils.toDataModel(mbDeviceInfoEntities.getCompanyKey(), Integer.class));
            throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
        }

        // 密碼驗證成功先更為0
        String pinBlock = request.getChallenge().replace("@", "");
        if (mbGestureProfileEntity.getPassword().equals(pinBlock)) {
            mbGestureProfileEntity.setPwdErrorCount(0);
            mbGestureProfileEntity.setStatus(0);
            mbGestureProfileRepository.save(mbGestureProfileEntity);
            return;
        }

        int pwdErrorCount = mbGestureProfileEntity.getPwdErrorCount() + 1;
        mbGestureProfileEntity.setPwdErrorTime(new Date());
        mbGestureProfileEntity.setPwdErrorCount(pwdErrorCount);
        if (pwdErrorCount >= MAX_PASSWORD_FAIL_COUNT) {
            mbGestureProfileEntity.setStatus(-2);
        }
        else {
            if (mbGestureProfileEntity.getStatus() == null) {
                mbGestureProfileEntity.setStatus(0);
            }
        }

        mbGestureProfileRepository.save(mbGestureProfileEntity);

        if (pwdErrorCount >= MAX_PASSWORD_FAIL_COUNT) {
            mbDeviceInfoEntities.setLoginPasswdType(0);
            mbDeviceInfoEntities.setLoginFlag(0);
            mbDeviceInfoRepository.save(mbDeviceInfoEntities);
            // 錯誤代碼+手勢密碼連續輸入錯誤三次已被鎖定，請您先用使用者密碼方式登入後重新進行手勢密碼設定，如有疑問，請洽客戶服務專線02-8751-6665。
            throw new ActionException(AIBankErrorCode.GESTURE_LOGIN_PASSWORD_LOCK_ERROR);
        }
        else if (pwdErrorCount == 1) {
            // 錯誤代碼+手勢密碼輸入錯誤一次，如有疑問，請洽客戶服務專線02-8751-6665。
            throw new ActionException(AIBankErrorCode.GESTURE_LOGIN_PASSWORD_VALIDATE_ERROR1);
        }
        else {
            // 錯誤代碼+手勢密碼連續輸入錯誤兩次(連續錯誤三次將被鎖定)，若您已遺忘手勢密碼，可先用使用者密碼登入後至【個人服務>密碼設定/變更>行動銀行手勢密碼設定/變更】中取消手勢密碼，再重新進行手勢密碼設定，如有疑問，請洽客戶服務專線02-8751-6665。
            throw new ActionException(AIBankErrorCode.GESTURE_LOGIN_PASSWORD_VALIDATE_ERROR2);
        }

    }

    /**
     * 驗證指紋、顏值 數位簽章方式的登入。 現已經以FIDO登入取代
     * 
     * @param deviceId
     * @param challenge
     * @param signatureLoginParamJsonString
     * @param signature
     * @throws ActionException
     */
    private void verifySignatureLogin(String deviceId) throws ActionException {
        logger.debug("FIDO");
    }

    /**
     * 取得系統參數
     * 
     * @param category
     * @param kxyName
     * @param defaultVal
     * @return
     */
    public String getSystemParam(String category, String kxyName, String defaultVal) {
        return systemParamCacheManager.getValue(category, kxyName, defaultVal);
    }

    public int getSystemParam(String category, String kxyName, int defaultVal) {
        return ConvertUtils.str2Int(systemParamCacheManager.getValue(category, kxyName, String.valueOf(defaultVal)), defaultVal);
    }

    public boolean getSystemParam(String category, String kxyName, boolean defaultVal) {
        String value = systemParamCacheManager.getValue(category, kxyName, String.valueOf(defaultVal));
        value = value.toUpperCase();
        // 使用equalsIgnoreCase進行不區分大小寫比較
        if ("TRUE".equalsIgnoreCase(value)) {
            return true;
        }
        if ("YES".equalsIgnoreCase(value)) {
            return true;
        }
        if ("1".equalsIgnoreCase(value)) {
            return true;
        }
        return false;
    }

    /**
     * 取得經緯資料
     * 
     * @param clientIP
     * @return
     */
    public GeoIPRangeDataEntity getRangeData(String clientIP) {
        long ipAddress = ConvertUtils.ipAddrStrToInt(clientIP);

        List<GeoIPRangeDataEntity> entities = this.geoIPRangeDataRepository.getRangeByIP(ipAddress);

        if (ipAddress != 0) {
            GeoIPRangeDataEntity geoIPRangeDataEntity = entities.isEmpty() ? null : entities.get(0);
            if (geoIPRangeDataEntity != null && geoIPRangeDataEntity.getStartIp() < ipAddress) {
                GeoIPLocationDataEntity locationDataEntity = geoIPLocationDataRepository.findByLocationId(geoIPRangeDataEntity.getLocationId());
                geoIPRangeDataEntity.setLocationDataEntity(locationDataEntity);
                return geoIPRangeDataEntity;
            }
        }
        return null;
    }

    /**
     * 複製 EB5556981 下行資料
     * 
     * @param source
     * @param target
     */
    private void copyLoginMsgRs(EB5556981SvcRsType source, EB5556981Response target) {

        if (source == null || target == null) {
            return;
        }
        target.setIdno(source.getIDNO());
        target.setNameCod(source.getNAMECOD());
        target.setUserId(source.getUSERID());
        target.setUserIdLevel(source.getUSERIDLEVEL());
        target.setEbillCheck(source.getEBILLCHECK());
        target.setEbillEndDate(source.getEBILLENDDATE());
        target.setBillCheck(source.getBILLCHECK());
        target.setBillEndDate(source.getBILLENDDATE());
        target.setSalaryCheck(source.getSALARYCHECK());
        target.setPromoCheck(source.getPROMOCHECK());
        target.setCustName(source.getCUSTNAME());
        target.setEmailAddr(source.getEMAILADDR());
        target.setMobileNo(source.getMOBILENO());
        target.setBirthday(source.getBIRTHDAY());
        target.setIdCheck(source.getIDCHECK());
        target.setMassChk(source.getMASSCHK());
        target.setError(source.getERROR());
        target.setPwChgDate(source.getPWCHGDATE());
        target.setUseridChgDate(source.getUSERIDCHGDATE());
        target.setEbillStrDate(source.getEBILLSTRDATE());
        target.setMbSts(source.getMBSTS());
        target.setOtpMobile(source.getOTPMOBILE());
        target.setNational(source.getNATIONAL());
        target.setEmailUnc(source.getEmailUnc());
        target.setAddrUnc(source.getAddrUnc());
    }

    /**
     * 複製 USER_PROFILE
     * 
     * @param source
     * @param target
     */
    private void copyUserEntity(UserEntity source, UserVo target) {
        if (source == null || target == null) {
            return;
        }

        target.setUserKey(source.getUserKey());
        target.setCompanyKey(source.getCompanyKey());
        target.setUserUuid(source.getUserUuid());
        target.setPaxword(source.getSecret());
        target.setNickName(source.getNickName());
        target.setAvatar(source.getAvatar());
        target.setUserCname(source.getUserCname());
        target.setUserEname(source.getUserEname());
        target.setStatus(source.getStatus());
        target.setUserType(source.getUserType());
        target.setLocale(source.getLocale());
        target.setOtpType(source.getOtpType());
        target.setOtpCert(source.getOtpCert());
        target.setTel1(source.getTel1());
        target.setTel2(source.getTel2());
        target.setTel3(source.getTel3());
        target.setFax1(source.getFax1());
        target.setFax2(source.getFax2());
        target.setNameCode(source.getNameCode());
        target.setMobile(source.getMobile());
        target.setEmails(source.getEmails());
        target.setTwEditQuota(source.getTwEditQuota());
        target.setTwVerifyQuota(source.getTwVerifyQuota());
        target.setTwPassQuota(source.getTwPassQuota());
        target.setTwDailyPassQuota(source.getTwDailyPassQuota());
        target.setFxEditQuota(source.getFxEditQuota());
        target.setFxVerifyQuota(source.getFxVerifyQuota());
        target.setFxPassQuota(source.getFxPassQuota());
        target.setFxDailyPassQuota(source.getFxDailyPassQuota());
        target.setTwPassDate(source.getTwPassDate());
        target.setTwTotalPassAmt(source.getTwTotalPassAmt());
        target.setFxPassDate(source.getFxPassDate());
        target.setFxTotalPassAmt(source.getFxTotalPassAmt());
        target.setHiddenFxAccount(source.getHiddenFxAccount());
        target.setTxDate(source.getTxDate());
        target.setHiddenLoanAccount(source.getHiddenLoanAccount());
    }

    /**
     * 複製 COMPANY
     * 
     * @param source
     * @param target
     */
    private void copyCompanyEntity(CompanyEntity source, CompanyVo target) {
        if (source == null || target == null) {
            return;
        }

        target.setCompanyKey(source.getCompanyKey());
        target.setCompanyUid(source.getCompanyUid());
        target.setUidDup(source.getUidDup());
        target.setStatus(source.getStatus());
        target.setCompanyKind(source.getCompanyKind());
        target.setCompanyBUType(source.getCompanyBUType());
        target.setCompanyName(source.getCompanyName());
        target.setCompanyEname(source.getCompanyEname());
        target.setUaaLevel(source.getUaaLevel());
        target.setDefaultFlowSchemaKey(source.getDefaultFlowSchemaKey());
        target.setSchemaSuitOperatorKey(source.getSchemaSuitOperatorKey());
        target.setSchemaSuitCreateTime(source.getSchemaSuitCreateTime());
        target.setEstablishDate(source.getEstablishDate());
        target.setDefaultBranchId(source.getDefaultBranchId());
        target.setTel(source.getTel());
        target.setFax(source.getFax());
        target.setMobile(source.getMobile());
        target.setEmails(source.getEmails());
        target.setRetryFlag(source.getRetryFlag());
        target.setLoginFlag(source.getLoginFlag());
        target.setSalaryFlag(source.getSalaryFlag());
        target.setOnlinePayeeFlag(source.getOnlinePayeeFlag());
        target.setRoot1TxFlag(source.getRoot1TxFlag());
        target.setRoot2TxFlag(source.getRoot2TxFlag());
        target.setPayerAccountFlag(source.getPayerAccountFlag());
        target.setPayeeAccountFlag(source.getPayeeAccountFlag());
        target.setFaxFlag(source.getFaxFlag());
        target.setTwAtmFlag(source.getTwAtmFlag());
        target.setTwRemitFlag(source.getTwRemitFlag());
        target.setTwFxmlFlag(source.getTwFxmlFlag());
        target.setTwPayeeFlag(source.getTwPayeeFlag());
        target.setTwAmtQuota(source.getTwAmtQuota());
        target.setTwInterRemitQuota(source.getTwInterRemitQuota());
        target.setRegisterTime(source.getRegisterTime());
        target.setCancelTime(source.getCancelTime());
        target.setLastBranchId(source.getLastBranchId());
        target.setLastEditorKey(source.getLastEditorKey());
        target.setLastManagerKey(source.getLastManagerKey());
        target.setLastUpdateTime(source.getLastUpdateTime());
        target.setQueryOnlyFlag(source.getQueryOnlyFlag());
        target.setKycRevalidated(source.getKycRevalidated());
        target.setKycRevalidatedDate(source.getKycRevalidatedDate());
        target.setMassCheck(source.getMassCheck());
        target.setCustomeAuditId(source.getCustomeAuditId());

    }

    /**
     * 複製 USER_LOGIN
     * 
     * @param source
     * @param target
     */
    private void copyLoginEntity(UserLoginEntity source, UserLoginVo target) {
        if (source == null || target == null) {
            return;
        }

        target.setUserKey(source.getUserKey());
        target.setCompanyKey(source.getCompanyKey());
        target.setStatus(source.getStatus());
        target.setPwdErrorCount(source.getPwdErrorCount());
        target.setPwdErrorTime(source.getPwdErrorTime());
        target.setPwdChangeTime(source.getPwdChangeTime());
        target.setPwdForceChange(source.getPwdForceChange());
        target.setSessionId(source.getSessionId());
        target.setServerId(source.getServerId());
        target.setClientIp(source.getClientIp());
        target.setLoginTime(source.getLoginTime());
        target.setLastAccessTime(source.getLastAccessTime());
        target.setLastLoginTime(source.getLastLoginTime());
        target.setWarningMessage(source.getWarningMessage());
        target.setSignonToken(source.getSignonToken());
        target.setMainCoachMark(source.getMainCoachMark());
        target.setPmCoachMark(source.getPmCoachMark());
        target.setAcctMgrCoachMark(source.getAcctMgrCoachMark());
        target.setLoginResult(source.getLoginResult());
        target.setLastLoginResult(source.getLastLoginResult());
        target.setLastClientIp(source.getLastClientIp());
        target.setNationName(source.getNationName());
        target.setLastNationName(source.getLastNationName());
        target.setMfdShowtype(source.getMfdShowtype());
        target.setChannelId(source.getChannelId());
        target.setLastChannelId(source.getLastChannelId());
        target.setPopupTime(source.getPopupTime());

    }

    /**
     * 複製 CARD_USER_PROFILE
     * 
     * @param source
     * @param target
     */
    private void copyCardUserEntity(CardUserProfileEntity source, CardUserProfileVo target) {
        if (source == null || target == null) {
            return;
        }

        target.setCardKey(source.getCardKey());
        target.setCompanyKey(source.getCompanyKey());
        target.setUserKey(source.getUserKey());
        target.setCardNo(source.getCardNo());
        target.setCardExpired(source.getCardExpired());
        target.setCardCvv2(source.getCardCvv2());
        target.setContractversion(source.getContractversion());
        target.setAccessLogKey(source.getAccessLogKey());
        target.setCreateTime(source.getCreateTime());
        target.setUpdateTime(source.getUpdateTime());
        target.setCardMasterFlag(source.getCardMasterFlag());
    }

    /**
     * 複製 MB_DEVICE_INFO
     * 
     * @param source
     * @param target
     */
    private void copyMbDeviceInfoEntity(MbDeviceInfoEntity source, MbDevicePushInfoEntity pushInfo, MbDeviceInfoVo target) {
        if (source == null || target == null) {
            return;
        }

        target.setDeviceInfoKey(source.getDeviceInfoKey());
        target.setDeviceUuId(source.getDeviceUuId());
        target.setCompanyKey(source.getCompanyKey());
        target.setUserKey(source.getUserKey());
        target.setModel(source.getModel());
        target.setDevicePlatform(source.getDevicePlatform());
        target.setDevicePlatformVersion(source.getDevicePlatformVersion());
        target.setDeviceAlias(source.getDeviceAlias());
        target.setLoginFlag(source.getLoginFlag());
        target.setLoginAuthDate(source.getLoginAuthDate());

        target.setClientIp(source.getClientIp());
        target.setMsgFlag(source.getMsgFlag());
        target.setMsgPassword(source.getMsgPassword());
        target.setEnable(source.getEnable());
        target.setCreateTime(source.getCreateTime());
        target.setUpdateTime(source.getUpdateTime());
        target.setLoginType(source.getLoginType());
        target.setErrorFlag(source.getErrorFlag());
        target.setLoginPasswdType(source.getLoginPasswdType());
        target.setQsearchFlag(source.getQsearchFlag());
        target.setChgPwdUseridFlag(source.getChgPwdUseridFlag());
        target.setChgPwdUseridDate(source.getChgPwdUseridDate());
        target.setMotpFlag(source.getMotpFlag());
        target.setMotpFlagDate(source.getMotpFlagDate());
        target.setDirectEzLoginFlag(source.getDirectEzLoginFlag());
        if (pushInfo != null) {
            target.setNotifyFlag(pushInfo.getNotifyFlag());
            target.setNotifyAuthDate(pushInfo.getNotifyAuthDate());
            target.setNotifyPass(pushInfo.getNotifyPass());
            target.setPushToken(pushInfo.getPushToken());
        }
    }

    /**
     * 申辦信用卡客戶資料查詢 只取用電話
     * 
     * @param custId
     * @return
     * @throws Exception
     */
    public void getCEW013RMobile(String custId, LoginUser loginUser) {

        try {
            CEW013RRS rs = this.esbGateway.sendCEW013R(custId);

            CEW013RSvcRsType rsBody = rs.getBodyAsType(CEW013RSvcRsType.class);

            loginUser.setMobileNo(rsBody.getMOBILE());

        }
        catch (XmlException | EAIException | EAIResponseException ex) {
            logger.error("sendCEW013R faile", ex);
        }
    }

    /**
     * 取得 MbDeviceInfoEntity，若查詢不到，回 null
     */
    public MbDeviceInfoEntity getMbDeviceInfoRepository(Integer companyKey, Integer userKey, String deviceUuId) {

        try {
            // 【FORTIFY：Access Control: Database】COMPANY_KEY、USER_KEY 就是身分識別屬性欄位
            List<MbDeviceInfoEntity> mbDeviceInfoEntities = mbDeviceInfoRepository.findByCompanyKeyAndUserKeyAndDeviceUuId(companyKey, userKey, ValidateParamUtils.validParam(deviceUuId));
            if (!mbDeviceInfoEntities.isEmpty()) {
                return mbDeviceInfoEntities.get(0);
            }
        }
        catch (Exception ex) { // 【Fortify：Poor Error Handling: Overly Broad Throws】需要攔截所有例外
            logger.info("查詢產生例外，不影響程序。");
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    public MbDeviceInfoEntity getMbDeviceInfoRepository(Integer companyKey, Integer userKey) {

        try {
            List<MbDeviceInfoEntity> mbDeviceInfoEntities = mbDeviceInfoRepository.findByCompanyKeyAndUserKey(companyKey, userKey);
            if (!mbDeviceInfoEntities.isEmpty()) {
                return mbDeviceInfoEntities.get(0);
            }
        }
        catch (Exception ex) { // 【Fortify：Poor Error Handling: Overly Broad Throws】需要攔截所有例外
            logger.info("查詢產生例外，不影響程序。");
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * 登入成功紀錄訊息
     * 
     * @param loginUser
     * @param request
     * @throws ActionException
     */
    private void processSuccessLogin(LoginUser loginUser, ExecuteUserLoginRequest request) throws ActionException {

        populateMobileEmailAndNameCode(loginUser, request);
        // 更新 user login data
        processLoginSuccess(loginUser, request);

    }

    private void processLoginSuccess(LoginUser loginUser, ExecuteUserLoginRequest request) throws ActionException {

        updateUserData(loginUser, request);
        // 雙重驗證不執行後續動作
        if (loginUser.isTwoFactorAuth()) {
            return;
        }
        updateUserLoginData(loginUser, request);
        // 取得推播狀態
        populatePushTypeLoginSuccess(loginUser, request);

        if (!loginUser.isBankUser()) {
            return;
        }

        /**
         * 重號戶進入功能檢核處理 1. 查是否在 ACCOUNT_CREDITCARD_CHECK 黑名單中 2. 若在黑名單中，查 CEW302R的生日 與 EB5556981的生日 是否相同
         */
        // 【FORTIFY：Access Control: Database】uid 就是身分識別屬性欄位
        AccountCreditcardCheckEntity accountCreditcardCheckEntity = accountCreditcardCheckRepository.findByCompanyUid(ValidateParamUtils.validParam(loginUser.geteUid()));

        if (accountCreditcardCheckEntity == null) {
            loginUser.setInAccountCreditcardCheck(false);
        }
        else {
            loginUser.setInAccountCreditcardCheck(true);

            Date birthday = getBirthDay(loginUser);

            if (birthday == null) {
                loginUser.setSameBirthday(false);
                return;
            }

            String rocBirthday = DateFormatUtils.SIMPLE_DATE_FORMAT.format(birthday.getTime());

            try {
                esbGateway.sendCEW319R("G", request.getUid(), null, rocBirthday, null, null);
            }
            catch (XmlException | EAIException | EAIResponseException ex) {
                loginUser.setSameBirthday(false);
                return;
            }
            loginUser.setSameBirthday(true);

        }
    }

    private void populatePushTypeLoginSuccess(LoginUser loginUser, ExecuteUserLoginRequest request) {
        loginUser.setPushType(getPushType(loginUser.getCompanyKey(), loginUser.getUserKey(), request.getDeviceId(), false));
    }

    private void populateMobileEmailAndNameCode(LoginUser loginUser, ExecuteUserLoginRequest request) {
        if (loginUser.isBankUser()) {
            if (StringUtils.isNotBlank(loginUser.getLoginMsgRs().getMOBILENO())) {
                loginUser.setMobileNo(loginUser.getLoginMsgRs().getMOBILENO());
            }
            if (loginUser.getEmail() == null || loginUser.getEmail().size() == 0) {
                loginUser.setEmail(getEmailList(loginUser.getLoginMsgRs().getEMAILADDR()));
            }
            loginUser.setBirthDay(getBirthDay(loginUser));

            List<MbDeviceInfoEntity> mbDeviceInfos = mbDeviceInfoRepository.findByCompanyKeyAndUserKey(loginUser.getCompanyKey(), loginUser.getUserKey());
            if (mbDeviceInfos != null && mbDeviceInfos.size() > 0) {
                loginUser.setMbDeviceInfoEntity(mbDeviceInfos.get(0));
            }
        }
        else {
            // 卡友
            CEW302RRS rs = sendCEW302R(request.getUid());
            if (rs != null && rs.getBody() != null) {
                if (rs.getBody().getBIRTHD().getTime() != null) {
                    loginUser.setBirthDay(rs.getBody().getBIRTHD().getTime());
                }
                loginUser.setCardEmail(rs.getBody().getEMAILADDRESS());
            }
            loginUser.setNameCode(loginUser.getUserEntity().getNameCode());
        }

    }

    /**
     * 取得一般會員生日
     * 
     * @param loginUser
     * @return
     */
    private Date getBirthDay(LoginUser loginUser) {
        if (loginUser != null && loginUser.getLoginMsgRs() != null && loginUser.getLoginMsgRs().getBIRTHDAY() != null) {
            return loginUser.getLoginMsgRs().getBIRTHDAY().getTime();
        }
        return null;
    }

    /**
     * 登入失敗紀錄訊息
     * 
     * @param loginUser
     * @param request
     * @throws DatabaseException
     * @throws ActionException
     */
    private void processFailLogin(LoginUser loginUser, ExecuteUserLoginRequest request) {

        if (loginUser.getEmail() == null || loginUser.getEmail().size() == 0) {
            if (!CustomerType.isCardMember(request.getLoginType())) {
                try {
                    getUserEmailByEB67050(loginUser);
                }
                catch (ActionException | XmlException | EAIException | EAIResponseException e) {
                    logger.warn("", e);
                }
            }
            else {
                if (loginUser.getCompanyEntity() != null && loginUser.getCompanyEntity().getEmails() != null) {
                    loginUser.setEmail(getEmailList(loginUser.getCompanyEntity().getEmails()));
                }

            }
        }

    }

    /**
     * 更新 USER_PROFILE.NAME_CODE
     * 
     * @param loginUser
     * @throws ActionException
     */
    private void updateUserData(LoginUser loginUser, ExecuteUserLoginRequest request) throws ActionException {
        // 2020/11/05 RongGang 新增更新email資訊
        String emailAddr = loginUser.isBankUser() ? loginUser.getLoginMsgRs().getEMAILADDR() : loginUser.getCardEmail();
        loginUser.getUserEntity().setEmails(emailAddr);
        if (loginUser.isBankUser()) {
            loginUser.getUserEntity().setNameCode(loginUser.getLoginMsgRs().getNAMECOD());
            if (StringUtils.isBlank(loginUser.getLoginMsgRs().getUSERID())) {
                loginUser.getUserEntity().setUserUuid(request.getUuid());
            }
            else {
                loginUser.getUserEntity().setUserUuid(loginUser.getLoginMsgRs().getUSERID().trim());
            }
        }
        // #2908 新增 FISRT_LOGIN
        if (loginUser.getUserEntity().getFirstLogin() == null) {
            loginUser.getUserEntity().setFirstLogin(new Date());
        }
        userProfileRepository.save(loginUser.getUserEntity());
    }

    /**
     * 更新 USER_LOGIN 資料
     * 
     * @param loginUser
     * @param request
     */
    private void updateUserLoginData(LoginUser loginUser, ExecuteUserLoginRequest request) {
        Date loginTime = new Date();
        UserLoginEntity loginEntity = loginUser.getUserLoginEntity();
        if (loginEntity == null) {
            UserRegisterationService registerationService = new UserRegisterationService(companyRepository, userProfileRepository, userLoginRepository);
            loginEntity = registerationService.createNewUserLoginEntity(loginUser.getCompanyEntity(), loginUser.getUserEntity());
        }
        loginEntity.setServerId(getServerId());
        // 成功, 判斷 loginTime 是否比 lastLoginTime新 , 若是, 則要copy
        boolean needCopy = (loginEntity.getLastLoginTime() == null || loginEntity.getLoginTime() == null) || loginEntity.getLoginTime().after(loginEntity.getLastLoginTime());
        if (loginUser.isSuccess()) {
            if (needCopy) {
                // 留下前一次的 client ip
                loginEntity.setLastClientIp(loginEntity.getClientIp());
                // 留下前一次的 登入時間
                loginEntity.setLastLoginTime(loginEntity.getLoginTime());
                // 留下前一次的登入狀態
                loginEntity.setLastLoginResult(loginEntity.getLoginResult());
                // 留下前一次的國別狀態
                loginEntity.setLastNationName(loginEntity.getNationName());
                // 留下前一次的 channel id
                loginEntity.setLastChannelId(loginEntity.getChannelId());
            }
            // 成功時更新以下欄位
            loginEntity.setClientIp(request.getClientIP());
            loginEntity.setLoginTime(loginTime);
            loginEntity.setSessionId(fixSessionId(request.getCurrentSessionId()));
            loginEntity.setLoginResult(loginUser.isSuccess() ? ResultCodeType.RESULT_OK.getResultCode() : ResultCodeType.RESULT_FAILED.getResultCode());
            loginEntity.setNationName(loginUser.getCountryName());

            // reset pass-word error count if success
            // 密碼登入才更新為0,手勢登入已先更新 MB_GESTURE_PROFILE
            // 密碼、手勢、指紋辦識、臉部辦識的錯誤次數皆為分開計算

            if (PwType.isPassword(request.getPwType())) {
                loginEntity.setPwdErrorCount(0);
            }

            loginEntity.setStatus(LoginStatusType.LOGIN.getCode());
            loginEntity.setLastAccessTime(loginTime);
            // generate signon token, sha(SystemId + sessionId + time millis)
            // 2022/6/20 JingXian 行銀白箱修補, A3 Weak Cryptographic Hash
            String signonToken = new String(Hex.encodeHex(DigestUtils.sha256((IBSystemId.AIBANK.getSystemId() + "-" + loginEntity.getSessionId() + "-" + System.currentTimeMillis()).getBytes())));
            if (StringUtils.isNotBlank(signonToken) && signonToken.length() > 40) {
                signonToken = signonToken.substring(0, 40);
            }
            loginEntity.setSignonToken(signonToken);
            loginEntity.setChannelId("M"); // @mobile
        }
        else {
            // 失敗時直接紀錄於 lastXXXX 欄位
            loginEntity.setLastClientIp(request.getClientIP());
            loginEntity.setLastLoginTime(loginTime);
            loginEntity.setLastLoginResult(loginUser.isSuccess() ? ResultCodeType.RESULT_OK.getResultCode() : ResultCodeType.RESULT_FAILED.getResultCode());
            loginEntity.setNationName(loginUser.getCountryName());
            loginEntity.setLastChannelId(ChannelIdType.AIBank.getChannelId());
        }

        userLoginRepository.save(loginEntity);
    }

    private String getServerId() {
        String podName = IBUtils.getInstanceId(environment);
        if (StringUtils.isNotBlank(podName) && podName.length() > 16) {
            return StringUtils.right(podName, 16);
        }
        return podName;
    }

    private List<String> getEmailList(String... emails) {
        List<String> emailList = new ArrayList<String>();
        for (String email : emails) {
            emailList.add(email);
        }
        return emailList;
    }

    /**
     * 特定ErrorCode 轉換
     *
     * @param error
     * @return
     */
    private String processErrCode(String error) {
        // 丁、 變更失敗，左上角顯示「錯誤代碼 + 錯誤訊息」
        //  E005--> E005A:無此使用者代碼。
        //  E092:DES處理錯誤。
        //  E103 EBA3--> E103A:使用者代碼須為數字或英文大寫字母。
        //  E237 交易資料不完整。
        //  EP35--> EP35A:使用者需變更密碼後方可繼續作業。
        //  EP39 使用者代碼已被終止。
        //  EP42 該使用者代碼已被他人使用。
        //  WP31--> WP31A:密碼不符。端末不同意此使用者繼續作業。
        //  如非以上所述錯誤代碼, 則顯示「錯誤代碼 + 變更密碼及使用者代碼失敗，如有疑問，請洽原開戶行詢問。」
        String errorCode = "";

        if (StringUtils.contains("EBB2,E005,E103,EBA3,EP35,EBC5,WP31", error)) {
            errorCode = error + "A";
        }
        else {
            errorCode = error;
        }

        return errorCode;
    }

    /**
     * 使用者代號/密碼 變更交易
     * 
     * @param request
     * @param memo
     * @param cryptoProxy
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    private EB5556981RS sendEB5556981(ChangeUuidAndPinBlockRequest request, String memo) throws XmlException, EAIException, EAIResponseException {
        String cipherPass = "";
        String encAcnoID = "";

        String encodedPwd = encodewithSecret(E2EEHsmType.PWD_EB5556981_CP1047, request.getCustId(), request.getUserId(), request.getPinblock(), false);
        cipherPass = encodedPwd.split("@")[0];
        encAcnoID = encodedPwd.split("@")[1];

        String newCipherPass = "";
        String newEncAcnoID = "";

        if (StringUtils.isNotBlank(request.getNewPinblock())) {
            String newEncodedPwd = encodewithSecret(E2EEHsmType.PWD_EB5556981_CP1047, request.getCustId(), request.getUserId(), request.getNewPinblock(), false);
            newCipherPass = newEncodedPwd.split("@")[0];
            newEncAcnoID = newEncodedPwd.split("@")[1];
        }

        return esbGateway.sendEB5556981(request, "變更", cipherPass, encAcnoID, newCipherPass, newEncAcnoID);
    }

    /**
     * 啟用行銀
     * 
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     **/
    private EB552190RS sendEB552190(String uid, String uuid, String pinblock, boolean pwdWithTime) throws XmlException, EAIException, EAIResponseException {
        String encodedPwd = encodewithSecret(E2EEHsmType.PWD_3DES_CP1047, uid, uuid, pinblock, pwdWithTime);
        return esbGateway.sendEB552190(uid, uuid, encodedPwd);
    }

    /**
     * 檢查是否是被限制的裝置
     * 
     * @throws ActionException
     */
    private void checkLoginUuidLimitation(String deviceId, String custId) throws ActionException {
        // 【FORTIFY：Access Control: Database】誤判，此查詢與個人資訊無關，不用帶入身分識別屬性欄位
        LoginUidLimitationEntity loginUidLimitationEntity = loginUidLimitationRepository.findByChannelAndDeviceId(AIBankConstants.CHANNEL_NAME, ValidateParamUtils.validParam(deviceId));

        if (loginUidLimitationEntity == null) {
            return;
        }

        byte[] bHashArr = DigestUtils.sha256(custId);
        String shaCustId = new String(Hex.encodeHex(bHashArr));
        shaCustId = shaCustId.toUpperCase();

        if (!loginUidLimitationEntity.getHashUid().equalsIgnoreCase(shaCustId)) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.DEVICE_LIMITATION_ERROR);
        }
    }

    /**
     * 避免SessionID過長
     * 
     * @param sessionId
     * @return
     */
    private String fixSessionId(String sessionId) {
        if (sessionId == null) {
            return "";
        }
        String sessId = sessionId.replace("-", "");
        if (sessId.length() > 32) {
            return sessId.substring(0, 32);
        }
        return sessId;
    }

    /**
     * 解析網路錯誤情境時的錯誤碼
     * 
     * @param msg
     * @return
     */
    private ActionException getActionExceptionFromException(String msg) {
        String systemId = getSubString(msg, "systemId");
        String errorCode = getSubString(msg, "errorCode");
        String errorDesc = getSubString(msg, "errorDesc");
        if (!StringUtils.isBlank(errorCode)) {
            return new ActionException(systemId, errorCode, SeverityType.ERROR, errorDesc);
        }
        return null;
    }

    /**
     * 在字串中，取得 Tag 開頭的 value
     * 
     * @param str
     * @param tag
     * @return
     */
    private String getSubString(String str, String tag) {

        if (str == null || tag == null) {
            return null;
        }

        int idx = str.indexOf(tag);
        int start = 0;
        int end = 0;
        int meet = 0;
        if (idx > -1) {
            for (int i = idx; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c == '"') {
                    meet++;
                    if (meet == 2) {
                        start = i + 1;
                    }
                    if (meet == 3) {
                        end = i - 1;
                        break;
                    }
                }
            }

            if (start > 0 && end > 0) {
                return str.substring(start, end + 1);
            }

        }
        return "";
    }

    /**
     * 未登入時查詢 Email
     * 
     * @param custId
     *            - ID
     * @param loginType
     *            0/1 一般會員/信用卡會員
     */
    public UnLoginEmailsResponse getUnLoginEmails(String custId, int loginType) {

        UnLoginEmailsResponse response = new UnLoginEmailsResponse();
        List<String> eMails = new ArrayList<String>();
        if (loginType == 0) {
            try {
                eMails = getUserEmailByEB67050(custId);
            }
            catch (ActionException | XmlException | EAIException | EAIResponseException e) {
                logger.error("查詢 Email EB67050 失敗", e);
            }
        }
        else {
            try {
                eMails = getUserEmailByEB032159(custId);
            }
            catch (XmlException | EAIException | EAIResponseException e) {
                logger.error("查詢 Email EB032159 失敗", e);
            }
        }
        response.setEmails(eMails);
        return response;
    }

    /**
     * 變更使用者代號密碼
     * 
     * @param request
     * @return
     * @throws CryptException
     * @throws ActionException
     * @throws EAIException
     * @throws XmlException
     * @throws EAIResponseException
     */
    public ChangeUuidAndPinBlockResponse changeUuidAndPinBlock(ChangeUuidAndPinBlockRequest request) throws XmlException, EAIException, ActionException, EAIResponseException, CryptException {
        ChangeUuidAndPinBlockResponse response = new ChangeUuidAndPinBlockResponse();
        response.setStatus(1);

        String uidDup = "0";

        if (StringUtils.isBlank(request.getCustId())) {
            logger.error("CustId 錯誤 {}", IBUtils.toDataModel(DataMaskUtil.maskCustId(request.getCustId()), String.class));
            return response;
        }
        if (request.getCustId().length() == 11) {
            uidDup = request.getCustId().substring(10);
            request.setCustId(request.getCustId().substring(0, 10));
        }
        boolean isNewUser = false;

        IdentityData identityData = identityService.query(request.getCustId(), uidDup, request.getUserId(), request.getCompanyKind());

        if (identityData == null) {
            isNewUser = true;
        }

        ChangePasswordRecordEntity changePasswordRecordEntity = new ChangePasswordRecordEntity();

        changePasswordRecordEntity.setHostTxTime(new Date());
        changePasswordRecordEntity.setTxStatus(TxStatusType.PROCESS.getCode());
        changePasswordRecordEntity.setNameCode("0001");
        changePasswordRecordEntity.setUserId(request.getUserId());
        changePasswordRecordEntity.setChangeItem(request.getChangeItem());
        changePasswordRecordEntity.setCreateTime(new Date());
        changePasswordRecordEntity.setTxDate(new Date());
        changePasswordRecordEntity.setClientIp(MDC.get(MDCKey.clientip.name()));
        changePasswordRecordEntity.setTxSource(TxSourceType.AI_BANK.getCode());
        changePasswordRecordEntity.setAccessLogKey(null);

        if (!isNewUser) {
            changePasswordRecordEntity.setCompanyKey(identityData.getCompanyKey());
            changePasswordRecordEntity.setUserKey(identityData.getUserKey());
            changePasswordRecordRepository.save(changePasswordRecordEntity);
        }

        try {
            EB5556981RS rs = sendEB5556981(request, "變更使用者代碼與密碼");

            changePasswordRecordEntity.setTxStatus(TxStatusType.SUCCESS.getCode());

            String error = StringUtils.trimToEmptyEx(rs.getBody().getERROR());
            String errorCode = StringUtils.EMPTY;
            String errorMessage = StringUtils.EMPTY;
            if (!StringUtils.equals(Constants.STATUS_CODE_SUCCESS, rs.getErrorCode())) {
                errorCode = rs.getErrorCode();
            }
            else if (StringUtils.isBlank(error)) {

                if (!isNewUser) {
                    if (StringUtils.isNoneBlank(request.getNewUserId()) && !request.getNewUserId().equals(request.getUserId())) {
                        List<UserEntity> oldUserEntities = userProfileRepository.findByCompanyKey(identityData.getCompanyKey());

                        // List<UserEntity> updateList = new ArrayList<>();
                        if (!oldUserEntities.isEmpty()) {
                            for (UserEntity oldUserEntity : oldUserEntities) {
                                // 找到相同的 uuid
                                if (StringUtils.equals(request.getUserId(), oldUserEntity.getUserUuid()) && (oldUserEntity.getStatus() > UserStatusType.DELETED.getCode())) {
                                    // oldUserEntity.setStatus(UserStatusType.DELETED.getCode());
                                    oldUserEntity.setUserUuid(request.getNewUserId());
                                    userProfileRepository.save(oldUserEntity);
                                }
                            }
                        }
                    }

                    // 更新 MB_DEVICE_INFO
                    List<MbDeviceInfoEntity> mbDeviceInfoEnties = mbDeviceInfoRepository.findByCompanyKeyAndUserKey(identityData.getCompanyKey(), identityData.getUserKey());
                    if (mbDeviceInfoEnties != null && mbDeviceInfoEnties.size() > 0) {
                        MbDeviceInfoEntity mbDeviceInfoEntity = mbDeviceInfoEnties.get(0);
                        mbDeviceInfoEntity.setChgPwdUseridFlag(1);
                        Date currentTime = new Date();
                        mbDeviceInfoEntity.setChgPwdUseridDate(currentTime);
                        mbDeviceInfoEntity.setUpdateTime(currentTime);
                        try {
                            mbDeviceInfoRepository.save(mbDeviceInfoEntity);
                        }
                        catch (RuntimeException ex) {
                            logger.error("更新 MB_DEVICE_INFO CHG_PWD_USERID_FLAG 失敗", ex);
                        }
                    }

                    // #2733: [CR#150] 快速登入的變更密碼檢核
                    List<NonAIMbDeviceInfoEntity> nonAIMbDeviceInfoEntities = nonAIMbDeviceInfoEntityRepository.findByCompanyKeyAndUserKey(identityData.getCompanyKey(), identityData.getUserKey());
                    if (nonAIMbDeviceInfoEntities != null && nonAIMbDeviceInfoEntities.size() > 0) {
                        for (NonAIMbDeviceInfoEntity mbDeviceInfoEntity : nonAIMbDeviceInfoEntities) {
                            mbDeviceInfoEntity.setChgPwdUseridFlag("Y");
                            Date currentTime = new Date();
                            mbDeviceInfoEntity.setChgPwdUseridDate(currentTime);
                            mbDeviceInfoEntity.setUpdateTime(currentTime);
                            try {
                                nonAIMbDeviceInfoEntityRepository.save(mbDeviceInfoEntity);
                            }
                            catch (RuntimeException ex) {
                                logger.error("更新 MB_DEVICE_INFO CHG_PWD_USERID_FLAG 失敗", ex);
                            }
                        }
                    }
                }

                response.setStatus(0);

            }
            else {
                errorCode = error;
            }
            if (StringUtils.isNotBlank(errorCode)) {
                String errorCodeA = processErrCode(errorCode);
                // 變更密碼及使用者代碼失敗，如有疑問，請洽原開戶行詢問。
                errorMessage = "變更密碼及使用者代碼失敗，如有疑問，請洽原開戶行詢問。";
                throw new ActionException(rs.getSystemId(), errorCodeA, SeverityType.ERROR, errorMessage);
            }
        }
        catch (EAIResponseException ex) {
            changePasswordRecordEntity.setTxStatus(TxStatusType.FAIL.getCode());
            changePasswordRecordEntity.setTxErrorCode(ex.getErrorCode());
            changePasswordRecordEntity.setTxErrorDesc(ex.getErrorMessage());
            changePasswordRecordEntity.setTxErrorSystemId(ex.getErrorSystemId());
            throw ex;
        }
        finally {
            changePasswordRecordEntity.setUpdateTime(new Date());
            if (!isNewUser) {
                changePasswordRecordRepository.save(changePasswordRecordEntity);
            }
        }
        return response;
    }

    /**
     * 重設密碼有效時間
     * 
     * @param request
     * @return
     */
    public Boolean updatePwdValidTime(UpdatePwdValidTimeRequest request) {

        logger.debug("=!=====> {} {} {} {}", DataMaskUtil.maskCustId(request.getCustId()), request.getUidDup(), DataMaskUtil.maskUserId(request.getUserId()), request.getLoginType());
        IdentityData identityData;
        try {
            identityData = identityService.query(request.getCustId(), request.getUidDup(), request.getUserId(), request.getCompanyKind());
        }
        catch (CryptException e) {
            logger.debug("=!=====> 重設密碼有效時間失敗");
            logger.error("重設密碼有效時間失敗", e);
            return false;
        }

        if (identityData == null) {
            logger.debug("=!=====> identityData is null");
            logger.error("重設密碼有效時間失敗");
            return false;
        }

        if (!CustomerType.isCardMember(request.getLoginType())) {
            List<ChangePasswordTipsEntity> entities = changePasswordTipsRepository.findByCompanyKeyAndUserKey(identityData.getCompanyKey(), identityData.getUserKey());
            ChangePasswordTipsEntity changePasswordTipsEntity = null;
            if (CollectionUtils.isEmpty(entities)) {
                changePasswordTipsEntity = new ChangePasswordTipsEntity();
                changePasswordTipsEntity.setCompanyKey(identityData.getCompanyKey());
                changePasswordTipsEntity.setUserKey(identityData.getUserKey());
                changePasswordTipsEntity.setCreateTime(new Date());
                changePasswordTipsEntity.setTxDate(new Date());
            }
            else {
                changePasswordTipsEntity = entities.get(0);
            }

            changePasswordTipsEntity.setTxDate(new Date());
            changePasswordTipsRepository.save(changePasswordTipsEntity);
        }
        else {
            logger.debug("=!=====> 更新 userProfileRepository");
            UserEntity userEntity = userProfileRepository.findByUserKey(identityData.getUserKey());
            userEntity.setTxDate(new Date());
            userProfileRepository.save(userEntity);
        }
        logger.debug("=!=====> 重設密碼有效時間 成功");
        return true;
    }

    /**
     * 發查 CEW302R
     * 
     * @param custId
     * @return
     */
    private CEW302RRS sendCEW302R(String custId) {
        CEW302RRS rs = null;

        try {
            rs = esbGateway.sendCEW302R(custId);
        }
        catch (XmlException | EAIException | EAIResponseException ex) {
            logger.error("", ex);
            return null;
        }

        return rs;

    }

    /**
     * 查詢歸戶信用卡清單
     * 
     * @param pin
     * @param type
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    public List<String> sendCE6220R(String pin, String type) throws XmlException, EAIException, EAIResponseException {

        List<String> result = new ArrayList<>();

        List<CE6220RRS> rsList = this.esbGateway.sendCE6220R(pin, type);

        String now = DateFormatUtils.CARD_EXPIRED_FORMAT.format(new Date());
        String nowYear = now.substring(2, 4);
        String nowMonth = now.substring(0, 2);

        for (CE6220RRS rs : rsList) {

            CE6220RSvcRsType rsBody = rs.getBodyAsType(CE6220RSvcRsType.class);

            // 排除 CARD_NO = 空白 或 CARD_EXPIRED = 空白
            if (StringUtils.isBlank(rsBody.getCARDNO()) || StringUtils.isBlank(rsBody.getCARDEXPIRED()) || StringUtils.length(rsBody.getCARDEXPIRED()) < 4) {
                continue;
            }
            // 排除 CARD_EXPIRED < 系統月
            String cardExpriedYear = rsBody.getCARDEXPIRED().substring(2, 4);
            String cardExpiredMonth = rsBody.getCARDEXPIRED().substring(0, 2);

            if (cardExpriedYear.compareTo(nowYear) < 0) {
                continue;
            }
            else if (cardExpriedYear.compareTo(nowYear) == 0 && cardExpiredMonth.compareTo(nowMonth) < 0) {
                continue;
            }

            result.add(rsBody.getCARDNO());
        }
        return result;
    }

}
