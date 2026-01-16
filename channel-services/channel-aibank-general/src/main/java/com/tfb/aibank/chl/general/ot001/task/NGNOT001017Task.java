package com.tfb.aibank.chl.general.ot001.task;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.biz.type.TwoFactorStatusType;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.log.MBAccessLog;
import com.tfb.aibank.chl.component.security.otp.OtpAuthService;
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthInitData;
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthKeepData;
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthVerifyData;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001017Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001017Rs;
import com.tfb.aibank.chl.general.ot001.task.service.OtpTxSeedTwoFactor;
import com.tfb.aibank.chl.general.ot001.task.service.TwoFactorAuthCache;
import com.tfb.aibank.chl.general.resource.dto.TwoFactorAuthUserResponse;
import com.tfb.aibank.chl.general.service.LoginService;
import com.tfb.aibank.chl.general.type.TwoFactorAuthStepType;
import com.tfb.aibank.chl.general.type.TwoFactorAuthType;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.type.OtpStatusType;
import com.tfb.aibank.chl.type.TxSecurityStepType;
import com.tfb.aibank.chl.type.TxSecurityType;
import com.tfb.aibank.common.type.MailParamType;

//@formatter:off
/**
* @(#)NGNOT001017Task.java 
* 

* <p>Description:</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20250610, Benson
*  <li>初版</li>
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on

@Component
@Scope("prototype")
public class NGNOT001017Task extends AbstractAIBankBaseTask<NGNOT001017Rq, NGNOT001017Rs> {
    /** OTP驗證結果 成功:0 */
    private static final String OTP_RESULT_SUCCESS = "0";
    /** OTP驗證結果 驗證失敗:1 */
    private static final String OTP_RESULT_VALIDATE_ERROR = "1";
    /** OTP驗證結果 OTP失敗:2 , 不再繼續驗證 */
    private static final String OTP_RESULT_ERROR = "2";

    private TwoFactorAuthCache cache;

    @Autowired
    private OtpAuthService otpAuthService;

    @Autowired
    private LoginService loginService;

    @Override
    public void validate(NGNOT001017Rq rqData) throws ActionException {

        cache = getCache(LoginService.TWO_FACTOR_CACHE_KEY, TwoFactorAuthCache.class);

        if (cache == null) {
            throw ExceptionUtils.getActionException(CommonErrorCode.UNAUTHORIZED_OPERATIONAL);
        }
    }

    @Override
    public void handle(NGNOT001017Rq rqData, NGNOT001017Rs rsData) throws ActionException {

        Integer action = Optional.ofNullable(rqData.getAction()).orElse(0);
        logger.debug("twofactor action: {}", action);

        switch (action) {
        case 0 -> init(rsData);
        case 1 -> sendOtpAndEmail(rsData);
        case 2 -> resendOtpAndEmail(rsData); // 1. cancel -, 2. init
        case 3 -> validateOtp(rqData, rsData);
        case 4 -> cancelTwoFactorAuth();
        default -> throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
    }

    /**
     * 初始化
     * 
     * @param rsData
     * @throws ActionException
     */
    private void init(NGNOT001017Rs rsData) throws ActionException {
        logger.debug("twofactor init");
        // 寫入一筆TWO_FACTOR_AUTH
        Integer companyKey = cache.getCompanyKey();
        Integer userKey = cache.getUserKey();
        String deviceId = cache.getDeviceId(); // 目前裝置 deviceId

        String clientIp = this.getClientIp();
        String location = cache.getCountryName();

        // 新增 DB: TWO_FACTOR_AUTH
        TwoFactorAuthUserResponse initAuthUserResponse = loginService.initTwoFactorAuth(TwoFactorAuthType.OTP, companyKey, userKey, deviceId, clientIp, location);
        TwoFactorAuthStepType authStep = TwoFactorAuthStepType.INIT;
        logger.info("twofactor otp initTwoFactorAuthUser: " + initAuthUserResponse);
        // SEQ, 不能放到前端
        cache.setSeq(initAuthUserResponse.getSeq());
        cache.setAuthStep(authStep.getCode());

        setCache(LoginService.TWO_FACTOR_CACHE_KEY, cache);

    }

    /**
     * 
     * @param rsData
     * @throws ActionException
     */
    private void sendOtpAndEmail(NGNOT001017Rs rsData) throws ActionException {
        logger.debug("twofactor sendOtpAndEmail");
        TwoFactorAuthStepType stepType = TwoFactorAuthStepType.find(cache.getAuthStep());

        if (stepType == null || TwoFactorAuthStepType.UNKNOWN == stepType) {
            logger.error("twofactor resend otp but stepType is wrong: ");
            throw ExceptionUtils.getActionException(CommonErrorCode.UNAUTHORIZED_OPERATIONAL);
        }

        // 自行產出OTP 驗證碼,
        String mobileNo = "";
        String email = "";

        if (cache.isBankUser()) {
            // 存戶
            email = cache.getLoginMsgRs().getEmailAddr();
            mobileNo = cache.getLoginMsgRs().getMobileNo();
        }
        else {
            // 卡戶
            mobileNo = cache.getMobileNo();
            email = cache.getCardEmail();
        }

        OtpTxSeedTwoFactor txSeed = new OtpTxSeedTwoFactor();
        OtpAuthInitData initData = new OtpAuthInitData();
        initData.setTaskId(getTaskId());
        // i18n
        initData.setTaskName(I18NUtils.getMessage("ngn.ot001.otp.twofactor", this.getLocale()));
        initData.setOtpMobile(mobileNo);
        initData.setSendMessage(txSeed.getSendMessage());
        initData.setTxFactors(txSeed.getTxFactors());
        initData.setLocale(getLocale().toString());
        initData.setTxnSendFlag(txSeed.isTxnSendFlag());
        initData.setTxCode(txSeed.getTxCode());
        initData.setExpireTime(txSeed.getExpireTime());

        AIBankUser fakeUser = createAibankFakeUser();

        OtpAuthKeepData otpAuthKeepData = otpAuthService.sendOTP(fakeUser, initData);

        populateRsData(rsData, mobileNo, email, otpAuthKeepData);

        sendEmail(fakeUser, email, otpAuthKeepData);

        cache.setTxSecurityStepType(TxSecurityStepType.OTP_SEND);
        cache.setOtpAuthKeepData(otpAuthKeepData);

        setCache(LoginService.TWO_FACTOR_CACHE_KEY, cache);
    }

    private AIBankUser createAibankFakeUser() {
        AIBankUser fakeUser = new AIBankUser(cache.getUserVo());
        fakeUser.setCompanyVo(cache.getCompanyVo());
        fakeUser.setCustId(cache.getUid());
        fakeUser.setUserId(cache.getUuid());

        if (!cache.isBankUser()) {
            if (StringUtils.isBlank(fakeUser.getNameCode())) {
                fakeUser.getUserData().setNameCode("0001"); // 卡戶在這邊先寫預設值 0001
            }
        }
        return fakeUser;
    }

    private void resendOtpAndEmail(NGNOT001017Rs rsData) throws ActionException {
        logger.debug("twofactor resendOtpAndEmail");

        TxSecurityStepType stepType = cache.getTxSecurityStepType();

        if (stepType == null || TxSecurityStepType.UNKNOWN == stepType) {
            logger.error("twofactor resend otp but stepType is wrong: ");
            throw ExceptionUtils.getActionException(CommonErrorCode.UNAUTHORIZED_OPERATIONAL);
        }
        // cancel first
        TwoFactorAuthUserResponse cancelResponse = loginService.handleTwoFactorAuth(TwoFactorAuthType.OTP, cache.getSeq(), cache.getCompanyKey(), cache.getUserKey(), TwoFactorStatusType.CANCEL, cache.getDeviceId(), this.getClientIp(), cache.getCountryName(), cache.getPersonNotificationRecordKey());
        logger.debug("twofactor:otp cancelResponse: " + cancelResponse);

        String mobileNo = "";
        String email = "";

        if (cache.isBankUser()) {
            // 存戶
            email = cache.getLoginMsgRs().getEmailAddr();
            mobileNo = cache.getLoginMsgRs().getMobileNo();
        }
        else {
            // 卡戶
            mobileNo = cache.getMobileNo();
            email = cache.getCardEmail();
        }

        OtpAuthKeepData otpAuthKeepData = cache.getOtpAuthKeepData();
        AIBankUser fakeUser = createAibankFakeUser();

        OtpAuthKeepData newOtpAuthKeepData = otpAuthService.resendOTP(fakeUser, otpAuthKeepData);

        populateRsData(rsData, mobileNo, email, newOtpAuthKeepData);

        sendEmail(fakeUser, email, newOtpAuthKeepData);

        cache.setTxSecurityStepType(TxSecurityStepType.OTP_SEND);
        cache.setOtpAuthKeepData(newOtpAuthKeepData);

        TwoFactorAuthUserResponse initAuthUserResponse = loginService.initTwoFactorAuth(TwoFactorAuthType.OTP, cache.getCompanyKey(), cache.getUserKey(), cache.getDeviceId(), this.getClientIp(), cache.getCountryName());
        logger.info("twoauth resendOtpAndEmail initTwoFactorAuth: " + initAuthUserResponse);

        cache.setSeq(initAuthUserResponse.getSeq());
        cache.setAuthStep(TwoFactorAuthStepType.INIT.getCode());

        setCache(LoginService.TWO_FACTOR_CACHE_KEY, cache);
    }

    private void validateOtp(NGNOT001017Rq rqData, NGNOT001017Rs rsData) throws ActionException {

        try {
            TxSecurityStepType txSecurityStepType = cache.getTxSecurityStepType();
            TwoFactorAuthStepType authStep = TwoFactorAuthStepType.find(cache.getAuthStep());

            if (txSecurityStepType == null || !txSecurityStepType.isOTPSend() || TwoFactorAuthStepType.INIT != authStep) {
                logger.error("twofactor verify otp invalid stepType: {}, {} ", txSecurityStepType, authStep);
                throw ExceptionUtils.getActionException(CommonErrorCode.UNAUTHORIZED_OPERATIONAL);
            }

            OtpAuthKeepData otpAuthKeepData = cache.getOtpAuthKeepData();
            AIBankUser fakeUser = createAibankFakeUser();

            OtpAuthVerifyData verifyData = new OtpAuthVerifyData();

            verifyData.setUserEncTxCode(rqData.getUserEncTxCode());
            verifyData.setUserTxHash(rqData.getUserTxHash());
            verifyData.setUserOtp(rqData.getUserOtp());

            otpAuthService.validateOTP(fakeUser, otpAuthKeepData, verifyData);

            // 驗證後回應的StatusType
            OtpStatusType statusType = OtpStatusType.findByCode(otpAuthKeepData.getModel().getStatus());
            // OTP 驗證錯誤處理
            if (!OtpStatusType.VERIFIED_OK.equals(statusType)) {
                if (OtpStatusType.EXPIRED_ERROR.equals(statusType)) {
                    // OTP驗證逾時，不可繼續驗證
                    rsData.setOtpResult(OTP_RESULT_ERROR);
                    // 驗證碼已逾時
                    rsData.setOtpErrorDesc(I18NUtils.getMessage("otp.auth.error.expired"));
                    rsData.setOtpErrorDescTW(I18NUtils.getMessage("otp.auth.error.expired", Locale.TAIWAN));
                    cache.setTxSecurityStepType(TxSecurityStepType.OTP_AUTH_FAIL);
                    logger.error("OTP驗證逾時");

                    TwoFactorAuthUserResponse timeoutResponse = loginService.handleTwoFactorAuth(TwoFactorAuthType.OTP, cache.getSeq(), cache.getCompanyKey(), cache.getUserKey(), TwoFactorStatusType.TIMEOUT, cache.getDeviceId(), this.getClientIp(), cache.getCountryName(), cache.getPersonNotificationRecordKey());
                    logger.debug("twofactor:otp timeoutResponse: " + timeoutResponse);

                }
                else if (OtpStatusType.VERIFY_OTP_OVER_LIMIT_ERROR.equals(statusType)) {
                    // OTP驗證錯誤(已達錯誤次數上限)，不可繼續驗證
                    rsData.setOtpResult(OTP_RESULT_ERROR);
                    // 驗證碼錯誤次數已達本次上限
                    rsData.setOtpErrorDesc(I18NUtils.getMessage("otp.auth.error.over-limit"));
                    rsData.setOtpErrorDescTW(I18NUtils.getMessage("otp.auth.error.over-limit", Locale.TAIWAN));
                    cache.setTxSecurityStepType(TxSecurityStepType.OTP_AUTH_FAIL);
                    logger.error("OTP驗證錯誤(已達錯誤次數上限)");
                    TwoFactorAuthUserResponse failResponse = loginService.handleTwoFactorAuth(TwoFactorAuthType.OTP, cache.getSeq(), cache.getCompanyKey(), cache.getUserKey(), TwoFactorStatusType.FAIL, cache.getDeviceId(), this.getClientIp(), cache.getCountryName(), cache.getPersonNotificationRecordKey());
                    logger.error("twofactor:otp failResponse: " + failResponse);
                }
                else if (OtpStatusType.VERIFY_OTP_ERROR.equals(statusType)) {
                    // OTP驗證錯誤(未達錯誤次數上限)
                    rsData.setOtpResult(OTP_RESULT_VALIDATE_ERROR);
                    // 驗證失敗
                    rsData.setOtpErrorDesc(I18NUtils.getMessage("otp.auth.error.normal"));
                    rsData.setOtpErrorDescTW(I18NUtils.getMessage("otp.auth.error.normal", Locale.TAIWAN));
                    cache.setOtpAuthKeepData(otpAuthKeepData);
                    logger.error("OTP驗證錯誤(未達錯誤次數上限)");
                }
                else {
                    // 特殊錯誤(檢核交易代碼錯誤、檢核簡訊動態密碼生成因子失敗)，不可繼續驗證
                    rsData.setOtpResult(OTP_RESULT_ERROR);
                    // 無法執行簡訊動態密碼驗證，請重新執行交易
                    rsData.setOtpErrorDesc(I18NUtils.getMessage("otp.auth.error.unknown"));
                    rsData.setOtpErrorDescTW(I18NUtils.getMessage("otp.auth.error.unknown", Locale.TAIWAN));
                    cache.setTxSecurityStepType(TxSecurityStepType.OTP_AUTH_FAIL);
                    logger.error("OTP驗證異常,Code={},Desc={}", statusType.getCode(), statusType.getDesc());
                    TwoFactorAuthUserResponse failResponse = loginService.handleTwoFactorAuth(TwoFactorAuthType.OTP, cache.getSeq(), cache.getCompanyKey(), cache.getUserKey(), TwoFactorStatusType.FAIL, cache.getDeviceId(), this.getClientIp(), cache.getCountryName(), cache.getPersonNotificationRecordKey());
                    logger.error("twofactor:otp failed: " + failResponse);
                }
                updateAccessLog(this.getTaskId(), TxSecurityType.OTP.getType(), 2);
            }
            else {
                // 驗證成功
                rsData.setOtpResult(OTP_RESULT_SUCCESS);
                cache.setTxSecurityStepType(TxSecurityStepType.OTP_AUTH_SUCCESS);
                cache.setAuthStep(TwoFactorAuthStepType.SUCCESS.getCode());
                logger.debug("OTP驗證成功");
                TwoFactorAuthUserResponse successResponse = loginService.handleTwoFactorAuth(TwoFactorAuthType.OTP, cache.getSeq(), cache.getCompanyKey(), cache.getUserKey(), TwoFactorStatusType.SUCCESS, cache.getDeviceId(), this.getClientIp(), cache.getCountryName(), cache.getPersonNotificationRecordKey());
                logger.debug("twofactor:successResponse: {}", successResponse);
                updateAccessLog(getTaskId(), TxSecurityType.OTP.getType(), 0);
            }
        }
        catch (ServiceException e) {
            rsData.setOtpResult(OTP_RESULT_ERROR);
            // 無法執行簡訊動態密碼驗證，請重新執行交易
            rsData.setOtpErrorDesc(I18NUtils.getMessage("otp.auth.error.unknown"));
            rsData.setOtpErrorDescTW(I18NUtils.getMessage("otp.auth.error.unknown", Locale.TAIWAN));
            cache.setTxSecurityStepType(TxSecurityStepType.OTP_AUTH_FAIL);
            logger.error("OTP驗證未知錯誤", e);
            TwoFactorAuthUserResponse failResponse = loginService.handleTwoFactorAuth(TwoFactorAuthType.OTP, cache.getSeq(), cache.getCompanyKey(), cache.getUserKey(), TwoFactorStatusType.FAIL, cache.getDeviceId(), this.getClientIp(), cache.getCountryName(), cache.getPersonNotificationRecordKey());
            logger.error("twofactor:otp failed: " + failResponse, e);
        }
        finally {
            setCache(LoginService.TWO_FACTOR_CACHE_KEY, cache);
        }
    }

    private void cancelTwoFactorAuth() throws ActionException {
        logger.debug("twofactor cancelTwoFactorAuth");

        TxSecurityStepType stepType = cache.getTxSecurityStepType();

        if (stepType == null || TxSecurityStepType.UNKNOWN == stepType) {
            logger.error("twofactor resend otp invalid stepType ");
            throw ExceptionUtils.getActionException(CommonErrorCode.UNAUTHORIZED_OPERATIONAL);
        }

        TwoFactorAuthUserResponse fancelResponse = loginService.handleTwoFactorAuth(TwoFactorAuthType.OTP, cache.getSeq(), cache.getCompanyKey(), cache.getUserKey(), TwoFactorStatusType.CANCEL, cache.getDeviceId(), this.getClientIp(), cache.getCountryName(), cache.getPersonNotificationRecordKey());
        logger.error("twofactor:otp canceled: " + fancelResponse);

    }

    /**
     * TODO
     * 
     * @param otpAuthKeepData
     * @param sourceTxnId
     * @param output
     */
    protected void buildMailParams(OtpAuthKeepData otpAuthKeepData, String sourceTxnId, Map<String, String> output) {
        output.put("emailOptPass", otpAuthKeepData.getModel().getOtpPass());
        output.put("optPassExpire", DateUtils.getDateTimeStr(otpAuthKeepData.getModel().getExpireTime()));
        output.put("txCode", otpAuthKeepData.getModel().getTxCode());
        String content = I18NUtils.getMessage("ngn.ot001.email.otp.content");
        output.put("content", content);
        output.put("subTitle", I18NUtils.getMessage("ngn.ot001.email.otp.subsubject"));

        if (StringUtils.isNotBlank(sourceTxnId)) {
            output.put(MailParamType.TXN_ID.getCode(), sourceTxnId);
        }

        output.put(MailParamType.TEMPLATE_NAME.getCode(), "NCCAG003_EMAIL");
    }

    private void populateRsData(NGNOT001017Rs rsData, String mobileNo, String email, OtpAuthKeepData otpAuthKeepData) {
        rsData.setPhone(DataMaskUtil.maskMobile(mobileNo));
        rsData.setEmail(email);
        rsData.setTxCode(otpAuthKeepData.getModel().getTxCode());
        rsData.setUserEncTxCode(otpAuthKeepData.getEncTxCode());
        rsData.setUserTxHash(otpAuthKeepData.getHashTxFactor());
        rsData.setCanResend(true);
    }

    private void sendEmail(AIBankUser fakeUser, String email, OtpAuthKeepData otpAuthKeepData) {
        Map<String, String> mailParams = new HashMap<>();
        buildMailParams(otpAuthKeepData, getTaskId(), mailParams);
        String subject = I18NUtils.getMessage("email.validate.subject", 2);
        sendTxnResultMail(fakeUser.getCustId(), fakeUser.getUidDup(), fakeUser.getUserId(), fakeUser.getCompanyKind(), subject, email, mailParams);
    }

    private void updateAccessLog(String taskId, int security, int resultCode) {
        MBAccessLog accessLog = getAccessLog();
        accessLog.setTaskId(taskId);
        accessLog.setSecurityType(security);
        accessLog.setResultCode(resultCode);
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }

}
