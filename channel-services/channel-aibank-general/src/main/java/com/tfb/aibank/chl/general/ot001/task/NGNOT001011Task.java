package com.tfb.aibank.chl.general.ot001.task;

import java.util.Date;
import java.util.Map;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.error.ErrorStatus;
import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.AESUtils;
import com.ibm.tw.commons.util.JsonUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.ibm.tw.ibmb.util.IBUtils.ErrorDescription;
import com.ibm.tw.ibmb.validate.ValidatorUtility;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.notification.model.CreatePersonalNotificationRequest;
import com.tfb.aibank.chl.component.satisfaction.AibankServiceSettingCacheManager;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.general.error.ErrorCode;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001011Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001011Rs;
import com.tfb.aibank.chl.general.ot001.task.service.DeviceBindingCache;
import com.tfb.aibank.chl.general.ot001.task.service.LoginMailData;
import com.tfb.aibank.chl.general.ot001.task.service.LoginMailType;
import com.tfb.aibank.chl.general.ot001.task.service.NGNOT001Service;
import com.tfb.aibank.chl.general.ot001.task.service.PreLoginCache;
import com.tfb.aibank.chl.general.ot001.task.service.TwoFactorAuthCache;
import com.tfb.aibank.chl.general.resource.SecurityResource;
import com.tfb.aibank.chl.general.resource.dto.ExecuteUserLoginRequest;
import com.tfb.aibank.chl.general.resource.dto.ExecuteUserLoginResponse;
import com.tfb.aibank.chl.general.resource.dto.LoginResponse;
import com.tfb.aibank.chl.general.resource.dto.PassRuleMsg;
import com.tfb.aibank.chl.general.resource.dto.QueryVerifyResultResponse;
import com.tfb.aibank.chl.general.resource.dto.RetriveDeviceStatusResponse;
import com.tfb.aibank.chl.general.service.LoginService;
import com.tfb.aibank.chl.general.service.NGNService;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.session.resource.SessionResource;
import com.tfb.aibank.chl.session.type.CustomerType;
import com.tfb.aibank.chl.type.PushCodeType;
import com.tfb.aibank.chl.type.PwType;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.common.type.AtferLoginJobType;
import com.tfb.aibank.common.type.CompanyKindType;
import com.tfb.aibank.common.type.NotificationSendStatus;

//@formatter:off
/**
* @(#)NGNOT001011Task.java 
* 
* <p>Description:登入 執行頁</p>
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
public class NGNOT001011Task extends AbstractAIBankBaseTask<NGNOT001011Rq, NGNOT001011Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT001011Task.class);
	
	private boolean isVirtual = true;

    @Autowired
    private LoginService loginService;

    @Autowired
    private SecurityResource securityResource;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Autowired
    private UserDataCacheService userDataCacheService;

    @Autowired
    private SessionResource sessionResource;

    @Autowired
    @Qualifier("NGNService")
    private NGNService ngnService;

    @Autowired
    private AibankServiceSettingCacheManager aibankServiceSettingCacheManager;

    @Autowired
    private NGNOT001Service service;

    @Value("${aibank.channel.invalid-session-before-login:false}")
    private boolean invalidSessionBeforeLogin;

    @Override
    public void validate(NGNOT001011Rq rqData) throws ActionException {
        if (invalidSessionBeforeLogin) {
            invalidSessionBeforeLogin();
        }

        RetriveDeviceStatusResponse cache = getCache(LoginService.DEVICE_BINDING_CACHE_KEY, RetriveDeviceStatusResponse.class);

        if (cache == null) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.LONG_IDLE_TO_RELOAD);
        }

        if (rqData.isForce() || rqData.isContinueLogin()) {
            PreLoginCache preLoginCache = getCache(LoginService.PRE_LOGIN_CACHE_KEY, PreLoginCache.class);

            if (preLoginCache == null) {
                logger.error("重複登入，但是找不到紀錄的資料");
                throw ExceptionUtils.getActionException(AIBankErrorCode.LONG_IDLE_TO_RELOAD);
            }

            if (rqData.isContinueLogin()) {
                // 如果為首登或變更帳密後登入，其加密是沒有時間因子，忽略原系統設定
                cache.setUidUuidNeedWithTime(false);
                cache.setPwdNeedWithTime(false);
                setCache(LoginService.DEVICE_BINDING_CACHE_KEY, cache);
            }

            return;
        }

        if (cache.isUidUuidNeedWithTime() && !rqData.isUidUuidNeedWithTime()) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.TIME_FACTOR_SETTING_ERROR);
        }

        if (cache.isPwdNeedWithTime() && !rqData.isPwdNeedWithTime()) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.TIME_FACTOR_SETTING_ERROR);
        }

        PwType pwType = PwType.findByCode(rqData.getPwType());

        /** 解譯 ID */
        String custId = rqData.getUid();
        if (!StringUtils.isBlank(rqData.getUid())) {
            custId = loginService.decryptText(rqData.getUid(), cache.isUidUuidNeedWithTime());
            rqData.setUid(custId);
        }
        String userId = rqData.getUuid();
        if (!StringUtils.isBlank(rqData.getUuid())) {
            userId = loginService.decryptText(rqData.getUuid(), cache.isUidUuidNeedWithTime());
            rqData.setUuid(userId);
        }

        // 快登手勢 ... custId 會在 cache 中取得
        populatePreLoginMBAccessLogByCustId(StringUtils.isBlank(custId) ? cache.getCustId() : custId);
        populatePreLoginMBAccessLogByUserId(StringUtils.isBlank(userId) ? cache.getUserId() : userId);
        Integer companyKind = "m1".equals(rqData.getLoginType()) ? 2 : 3;
        populatePreLoginMBAccessLogByCompany(companyKind);

        if (pwType == PwType.PATTERN) {
            // 手勢登入，驗證
            String pinBlock = loginService.encodewithSecret(cache.getCustId(), cache.getUserId(), rqData.getChallenge());

            rqData.setUid(cache.getCustId());
            rqData.setUuid(cache.getUserId());
            rqData.setLoginType(cache.getLoginType() == 0 ? CustomerType.GENERAL.getLoginType() : CustomerType.CARDMEMBER.getLoginType());
            rqData.setChallenge(pinBlock);
            populatePreLoginMBAccessLogByCompany(cache.getLoginType() == 0 ? 2 : 3);

            if (loginService.isNotAllowToLogin(rqData.getUid())) {
                // /目前系統忙碌中，暫停登入，請稍後再試
                throwMessageException(getMessage(ErrorCode.SYSTE_IS_BUSY));
            }

            // 檢核是否變更過使用者代碼或使用者密碼註記
            if (loginService.isHasChangedPwd(getDeviceIxd())) {
                if (cache.getCompanyKind() == 1) {
                    cache.setStatus(2);
                    setCache(LoginService.DEVICE_BINDING_CACHE_KEY, cache);
                    logger.info("CHANGE_PWD_FLAG: pwd changed");
                    throw new ActionException(ErrorCode.CORP_PWD_LOGIN_REQUIRED);
                }
                throw new ActionException(ErrorCode.PWD_LOGIN_REQUIRED);
            }

            return;
        }

        /** 快登-驗證FIDO */
        if (pwType == PwType.FACI_ID || pwType == PwType.TOUCH_ID || pwType == PwType.FINGER) {

            LoginResponse loginResponse = getCache(LoginService.FIDO_INFO_KEY, LoginResponse.class);
            // 生物辨識登入，驗證
            QueryVerifyResultResponse response = loginService.doFastLoginQueryVerifyResult(cache, loginResponse, getRequest());

            if (!"0".equals(response.getStatus())) {
                // 快登檢核失敗
                throwMessageException(getMessage(ErrorCode.FAST_LOGIN_FAIL));
                return;
            }

            rqData.setUid(cache.getCustId());
            rqData.setUuid(cache.getUserId());
            rqData.setLoginType(cache.getLoginType() == 0 ? CustomerType.GENERAL.getLoginType() : CustomerType.CARDMEMBER.getLoginType());
            populatePreLoginMBAccessLogByCompany(cache.getLoginType() == 0 ? 2 : 3);

            if (loginService.isNotAllowToLogin(rqData.getUid())) {
                // 目前系統忙碌中，暫停登入，請稍後再試
                throwMessageException(getMessage(ErrorCode.SYSTE_IS_BUSY));
            }

            // 檢核是否變更過使用者代碼或使用者密碼註記
            if (loginService.isHasChangedPwd(getDeviceIxd())) {
                if (cache.getCompanyKind() == 1) {
                    cache.setStatus(2);
                    setCache(LoginService.DEVICE_BINDING_CACHE_KEY, cache);
                    logger.info("CHANGE_PWD_FLAG: pwd changed");
                    throw new ActionException(ErrorCode.CORP_PWD_LOGIN_REQUIRED);
                }
                throwMessageException(getMessage(ErrorCode.PWD_LOGIN_REQUIRED));
            }
            return;
        }
        /** 綁定裝置僅輸入密碼 */
        if (pwType == PwType.BIO_PASSORD) {
            rqData.setUid(cache.getCustId());
            rqData.setUuid(cache.getUserId());
            rqData.setLoginType(cache.getLoginType() == 0 ? CustomerType.GENERAL.getLoginType() : CustomerType.CARDMEMBER.getLoginType());
            populatePreLoginMBAccessLogByCompany(cache.getLoginType() == 0 ? 2 : 3);
            return;
        }

        if (loginService.isNotAllowToLogin(rqData.getUid())) {
            // 目前系統忙碌中，暫停登入，請稍後再試
            throwMessageException(getMessage(ErrorCode.SYSTE_IS_BUSY));
        }

        if (!(CustomerType.isGeneral(rqData.getLoginType()) || CustomerType.isCardMember(rqData.getLoginType()))) {
            // 登入身份錯誤
            throwMessageException(getMessage(ErrorCode.LOGIN_TYPE_NOT_ALLOW));
        }

        boolean isCCMember = CustomerType.isCardMember(rqData.getLoginType());

        if (StringUtils.isBlank(custId)) {
            // 請輸入身分證字號
            throwMessageException(getMessage(ErrorCode.CUST_ID_IS_EMPTY));
        }

        custId = custId.toUpperCase();

        // 位數不為7(大陸同胞)或8或10位，錯誤訊息「身分證字號長度不符」 & 長度限制10
        int uidLength = custId.length();
        if (!(uidLength == 7 || uidLength == 8 || uidLength == 10)) {
            // 身分證字號長度不符
            throwMessageException(getMessage(ErrorCode.CUST_ID_LENGTH_ERROR));
        }

        // 輸入非英數字(A~Z or 0~9)，錯誤訊息「身分證字號限輸入英數字」
        if (!custId.matches("^[A-Z0-9]+$")) {
            // 身分證字號限輸入英數字
            throwMessageException(getMessage(ErrorCode.CUST_ID_ENG_NUM_ONLY));
        }

        // 長度為10，若不符本國人身分證字號或外國人編號驗證，錯誤訊息「身分證字號驗證錯誤」
        // 外國人編號：前面2碼英文+後8碼數字/前面8碼數字+後2碼英文
        if (uidLength == 10) {
            if (!(ValidatorUtility.checkID(custId) || isTFBResidenIDNumber(custId))) { // 台灣身分證字號檢查
                // 身分證字號驗證錯誤
                throwMessageException(getMessage(ErrorCode.CUST_ID_VALIDATE_ERROR));
            }
        }

        if (StringUtils.isBlank(userId)) {
            // 請輸入使用者代碼
            throwMessageException(getMessage(ErrorCode.USER_ID_IS_EMPTY));
        }

        userId = userId.toUpperCase();

        int uuidLength = userId.length();

        // 若長度小於6位，錯誤訊息「使用者代碼長度須為6~10碼；若您為首次登入，請先至網路銀行進行使用者代碼變更」
        if (uuidLength < 1 || uuidLength > 10) {
            if (isCCMember) {
                // 使用者代碼長度須為6~10碼
                throwMessageException(getMessage(ErrorCode.USER_ID_LENGTH_ERROR));
            }
            else {
                // 使用者代碼長度須為6~10碼；若您為首次登入，請先至網路銀行進行使用者代碼變更
                throwMessageException(getMessage(ErrorCode.USER_ID_LENGTH_FIRST_LOGIN));
            }
        }

        // 輸入非英數字(A~Z or 0~9)，錯誤訊息「用者代碼限輸入英數字；若您為首次登入，請先至網路銀行進行使用者密碼變更」
        if (!userId.matches("^[A-Z0-9]+$")) {
            if (isCCMember) {
                // 使用者代碼限輸入英數字
                throwMessageException(getMessage(ErrorCode.USER_ID_ENG_NUM_ONLY));
            }
            else {
                // 使用者代碼限輸入英數字
                throwMessageException(getMessage(ErrorCode.USER_ID_ENG_NUM_FIRST_LOGIN));
            }
        }

        if (isVirtual)
            return;
        /**
         * 使用者密碼檢核
         */
        try {

            PassRuleMsg passRuleMsg = loginService.validateWithPasswordRule(custId, userId, rqData.getSecret(), cache.isPwdNeedWithTime());
            switch (passRuleMsg) {
            case PASSWORD_BLANK_ERR:
                // 請輸入使用者密碼
                throwMessageException(getMessage(ErrorCode.PWD_IS_EMPTY));
            case PASSWORD_LENGTH_ERR:
                // 使用者密碼長度須為6~16碼；若您為首次登入，請先至網路銀行進行使用者密碼變更
                throwMessageException(getMessage(ErrorCode.PWD_LENGTH_FIRST_LOGIN));
            case PASSWORD_ENG_NUM_ERR:
                // 使用者密碼限輸入英數字混雜；若您為首次登入，請先至網路銀行進行使用者密碼變更
                throwMessageException(getMessage(ErrorCode.PWD_ENG_NUM_FIRST_LOGIN));
            default:
                break;
            }

        }
        catch (ActionException e1) {
            throwMessageException(getMessage(ErrorCode.PWD_ENG_NUM_FIRST_LOGIN));
        }
    }

    @Override
    public void handle(NGNOT001011Rq rqData, NGNOT001011Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001011 start====");

        String sessionId = getHttpServletRequest().getSession().getId();
        boolean isDupLogin = false;
        boolean isPwdWithTime = loginService.isPwdNeedWithTime();

        PreLoginCache preLoginCache = new PreLoginCache();
        RetriveDeviceStatusResponse bindingCache = getCache(LoginService.DEVICE_BINDING_CACHE_KEY, RetriveDeviceStatusResponse.class);
        if (bindingCache == null) {
            bindingCache = new RetriveDeviceStatusResponse();
            bindingCache.setMarketingName(getRequest().getModel());
        }

        /** 變更密碼後登入 */
        if (rqData.isContinueLogin()) {
            rqData = getCache(LoginService.PRE_LOGIN_CACHE_KEY, NGNOT001011Rq.class);

            isPwdWithTime = false;
            if (rqData == null) {
                logger.error("接續登入，但是找不到紀錄的資料");
                throw ExceptionUtils.getActionException(CommonErrorCode.UNKNOWN_EXCEPTION);
            }

            if (!rqData.isContinueLogin()) {
                logger.error("接續登入，但是紀錄的資料非接續登入的狀態");
                throw ExceptionUtils.getActionException(CommonErrorCode.UNKNOWN_EXCEPTION);
            }
            isDupLogin = true;
        }
        /** 重複登入 */
        else if (rqData.isForce()) {
            isDupLogin = true;
            preLoginCache = getCache(LoginService.PRE_LOGIN_CACHE_KEY, PreLoginCache.class);

            if (preLoginCache == null) {
                logger.error("重複登入，但是找不到紀錄的資料");
                throw ExceptionUtils.getActionException(CommonErrorCode.UNKNOWN_EXCEPTION);
            }

            preLoginCache.setMarketingName(bindingCache.getMarketingName());
            rqData = preLoginCache.getRqData();
        }
        else {
            preLoginCache = new PreLoginCache(rqData);
            preLoginCache.setMarketingName(bindingCache.getMarketingName());
            setCache(LoginService.PRE_LOGIN_CACHE_KEY, preLoginCache);
        }

        ActionException error = null;
        ExecuteUserLoginResponse userLoginResponse = null;
        // 登入處理
        try {
            userLoginResponse = loginService.executeUserLogin(rqData, getRequest(), sessionId, isDupLogin, isPwdWithTime, getLocale());
        }
        catch (ActionException ex) {
            error = ex;
        }

        // 啟用行銀後，重新登入
        if (userLoginResponse != null && userLoginResponse.isEnableStatus()) {
            error = null;
            try {

                LoginMailData loginMailData = loginService.getLoginMailData(LoginMailType.WELCOME_MAIL, getRequest().getClientIp(), bindingCache.getMarketingName(), getLocale(), null, null, userLoginResponse == null ? "" : userLoginResponse.getCountryName());
                if (userLoginResponse.getEmail() != null && userLoginResponse.getEmail().size() > 0)
                    sendMail(rqData.getUid(), "0", rqData.getUuid(), 2, loginMailData.getSubject(), userLoginResponse.getEmail().get(0), loginMailData.getParams());

                userLoginResponse = loginService.executeUserLogin(rqData, getRequest(), sessionId, isDupLogin, isPwdWithTime, getLocale());
            }
            catch (ActionException ex) {
                error = ex;
            }
        }

        if (userLoginResponse != null && userLoginResponse.getErrorCode() != null) {
            error = new ActionException(userLoginResponse.getSystemId(), userLoginResponse.getErrorCode(), SeverityType.ERROR, userLoginResponse.getErrorDesc());
        }

        if (error != null) { // 有錯誤拋出
            processLoginError(rqData, bindingCache, error, userLoginResponse);
        } // ** 使用者代號長度為 4 */
        else if ("0".equals(rqData.getPwType()) && rqData.getUuid() != null && rqData.getUuid().length() == 4) {
            if (userLoginResponse != null) {
                preLoginCache.setEmail(userLoginResponse.getEmail());
                preLoginCache.setMobileNo(userLoginResponse.getMobileNo());
            }
            preLoginCache.setLoginType(rqData.getLoginType());

            setCache(LoginService.PRE_LOGIN_CACHE_KEY, preLoginCache);
            rsData.setNextJob(2);
            logger.debug("==== NGNOT001011 NextPage to 024 ====");
            return;
        }
        // ** 帳密變更 */
        else if (userLoginResponse != null && userLoginResponse.getNextParam() != null) {

            if (AtferLoginJobType.isReLogin(userLoginResponse.getNextParam())) {

                if (bindingCache.getCompanyKind() == 1) {
                    bindingCache.setStatus(2);
                    setCache(LoginService.DEVICE_BINDING_CACHE_KEY, bindingCache);
                    logger.info("CHANGE_PWD_FLAG: 變更過帳密");
                    throw new ActionException(ErrorCode.CORP_PWD_LOGIN_REQUIRED);
                }
                throw new ActionException(ErrorCode.PWD_LOGIN_REQUIRED);
            }

            preLoginCache.setEmail(userLoginResponse.getEmail());
            preLoginCache.setMobileNo(userLoginResponse.getMobileNo());
            preLoginCache.setLoginType(rqData.getLoginType());
            setCache(LoginService.PRE_LOGIN_CACHE_KEY, preLoginCache);
            rsData.setNextJob(Integer.valueOf(userLoginResponse.getNextParam()));
            logger.debug("==== NGNOT001011 NextPage ====");
            return;
        }
        // 登入成功
        // fortify: null deference
        else if (userLoginResponse != null) {

            // #2733 若使用者為「企業戶」且有設定快登，變更密碼或代碼成功後，下次登入時僅能以「帳密登入」，且登入成功後需解除快登，再導至『快速登入設定』功能，讓使用者重新設定
            if (bindingCache.getStatus() != null) {
                if (bindingCache.getStatus().intValue() == 2) {
                    logger.info("CHANGE_PWD_FLAG: {}", bindingCache.getStatus().intValue());
                    if (loginService.isNeedDeleteFastLogin(this.getRequest(), rqData, bindingCache, userLoginResponse.getCompanyVo().getCompanyKind())) {
                        rsData.setNextJob(4);
                    }
                    else {
                        logger.info("CHANGE_PWD_FLAG: {} {} ", JsonUtils.getJson(rqData), JsonUtils.getJson(bindingCache));
                    }
                }
            }
            // 8647 把 登入流程使用到的參數寫到 TWO_FACTOR_CACHE, 待雙重驗證成功後, 取出相關參數在寫入重建登入後 SESSION BIZ 登入後流程
            if (userLoginResponse.isTwoFactorAuth()) {
                // 1. 建立 TWOFACTOR AUTH CACHE, 2. rsData 寫入 TWOFACTOR AUTH FLAG
                rsData.setTwoFactorAuth(true);
                ExecuteUserLoginRequest userLoginRequest = loginService.buildExecuteUserLoginRequest(rqData, getRequest(), sessionId, isDupLogin, isPwdWithTime, getLocale());
                TwoFactorAuthCache twoFactorAuthCache = new TwoFactorAuthCache(userLoginRequest, userLoginResponse, rqData, rsData);
                setCache(LoginService.TWO_FACTOR_CACHE_KEY, twoFactorAuthCache);
            }
            else {
                processLoginSuccess(rqData, rsData, bindingCache, userLoginResponse);
            }
        }
        logger.debug("==== NGNOT001011 end 3 ====");

        if (StringUtils.isBlank(rsData.getCelebrusId())) {
            rsData.setCelebrusId(" ");
        }

        rsData.setStatus("0000");
    }

    /**
     * 登入失敗處理
     * 
     * @param rqData
     * @param bindingCache
     * @param error
     * @param userLoginResponse
     * @throws ActionException
     */
    private void processLoginError(NGNOT001011Rq rqData, RetriveDeviceStatusResponse bindingCache, ActionException error, ExecuteUserLoginResponse userLoginResponse) throws ActionException {
        service.handleException(getAccessLog(), error);
        String errorCode = error.getErrorCode();

        // #5688 非重複登入才發送通知
        if (!StringUtils.equals(errorCode, "00212")) {
            // 發送通知E-mail
            LoginMailType loginMailType = loginService.getLoginMailType(rqData.getLoginType(), rqData.getPwType(), false);
            // fortify: null deference
            LoginMailData loginMailData = loginService.getLoginMailData(loginMailType, getRequest().getClientIp(), bindingCache.getMarketingName(), getLocale(), userLoginResponse != null ? userLoginResponse.getError() : null, null, userLoginResponse == null ? "" : userLoginResponse == null ? "" : userLoginResponse.getCountryName());
            if (userLoginResponse != null) {
                if (userLoginResponse.getEmail() != null && userLoginResponse.getEmail().size() > 0 && userLoginResponse.getSmsType() != 2) {
                    for (String email : userLoginResponse.getEmail()) {
                        if (StringUtils.isNotBlank(email))
                            sendTxnResultMail(rqData.getUid(), "0", rqData.getUuid(), CompanyKindType.PERSONAL.getCode(), loginMailData.getSubject(), email, loginMailData.getParams());
                    }
                }
                else {
                    if (StringUtils.isNotBlank(userLoginResponse.getMobileNo())) {
                        int companyKind = 2;
                        if (!"m1".equals(rqData.getLoginType())) {
                            companyKind = 3;
                        }
                        sendResultMsg( //
                                rqData.getUid(), "0", rqData.getUuid(), companyKind, //
                                I18NUtils.getMessage("ngn.ot001.sms.login.fail", DateFormatUtils.CE_DATETIME_FORMAT.format(new Date())), null, null, userLoginResponse.getMobileNo());
                    }
                }
            }
        }

        // 首登後之 errorCode, 需去除尾碼A
        if (errorCode.matches("^EP3[48]A$")) {
            errorCode = StringUtils.left(errorCode, 4);
        }

        // 行銀登入需使用者確認
        if (StringUtils.equals(errorCode, "E522") || StringUtils.equals(errorCode, "EP86") || StringUtils.equals(errorCode, "EP85") || StringUtils.equals(errorCode, "EP44") || StringUtils.equals(errorCode, "E519") || StringUtils.equals(errorCode, "EBH7") || StringUtils.equals(errorCode, "EBH6") || StringUtils.equals(errorCode, "EBH5") || StringUtils.equals(errorCode, "EBD4") || StringUtils.equals(errorCode, "EBH9")) {
            throwMessageException(getMessage(ErrorCode.LOGIN_NED_CONFIRM));
        }

        // 首登變更密碼或使用者帳號
        if (StringUtils.equals(errorCode, "3020") || StringUtils.equals(errorCode, "3021") || StringUtils.equals(errorCode, "3022")) {
            throw error;
        }

        // 身分證字號或使用者代碼錯誤。如有疑問，請洽客戶服務專線02-8751-6665
        // if (StringUtils.equals(errorCode, "EP32") || StringUtils.equals(errorCode, "EBC2")) {
        // if (result != null)
        // loginService.sendNotifySMS(rqData, result.getMobileNo(), "NGNOT001");
        // }

        // 重複登入
        if (StringUtils.equals(errorCode, "00212")) {
            Date time = new Date();
            if (userLoginResponse != null && userLoginResponse.getPreLoginDate() != null) {
                time = userLoginResponse.getPreLoginDate();
            }
            throw new ActionException(AIBankErrorCode.USER_LOGIN_DUPLICATE, DateFormatUtils.CE_DATETIME_FORMAT.format(time));
        }

        String errorDesc = IBUtils.getErrorDesc(errorCodeCacheManager, error.getSystemId(), error.getErrorCode(), getLocale(), getFromPage());

        if (StringUtils.isBlank(errorDesc)) {
            errorDesc = error.getErrorDesc();
        }
        // 畫面顯示訊息
        throwMessageException("(" + error.getSystemId() + "-" + error.getErrorCode() + ")" + errorDesc);
    }

    private void processLoginSuccess(NGNOT001011Rq rqData, NGNOT001011Rs rsData, RetriveDeviceStatusResponse bindingCache, ExecuteUserLoginResponse result) {

        // PERFORMANCE TURNING: FirstDownloadRecord 改為由批次處理。
        // MbDevicePushInfo 由前端比對有異動時，由 NSTOT004060 負責更新
        // 更新 FIRST_DOWNLOAD_RECORD
        if (StringUtils.equalsIgnoreCase(systemParamCacheManager.getValue("AIBANK", "ENABLE_UPDATE_FIRST_DL_RECORD", "false"), "true")) {
            service.updateFirstDownloadRecord(getRequest(), rqData.getPushToken());
        }
        // 建立 AIBankUser
        AIBankUser aibankUser = new AIBankUser(result.getUserVo());
        aibankUser.setCompanyKindType(CompanyKindType.find(result.getCompanyVo().getCompanyKind()));
        aibankUser.setCustId(rqData.getUid());
        aibankUser.setUserId(rqData.getUuid());
        aibankUser.setCustomerType(CustomerType.findLoginType(rqData.getLoginType())); // 登入成功後，依登入方式設置登入身份
        aibankUser.setCompanyVo(result.getCompanyVo());
        aibankUser.setCardUserProfileVo(result.getCardUserProfileVo());
        aibankUser.setUserLoginVo(result.getUserLoginVo());
        aibankUser.setMbDeviceInfoVo(result.getMbDeviceInfoVo());
        aibankUser.setLoginMsgRs(result.getLoginMsgRs());
        aibankUser.setEmail(result.getEmail() != null ? result.getEmail().get(0) : "");
        aibankUser.setBirthDay(result.getBirthDay());
        aibankUser.setMobileNo(result.getMobileNo());
        aibankUser.setSameBirthday(result.isSameBirthday());
        aibankUser.setInAccountCreditcardCheck(result.isInAccountCreditcardCheck());
        aibankUser.setMarketingName(bindingCache.getMarketingName());
        aibankUser.setShowSatisfactionFlag(ngnService.checkShowSatisfactionRating(aibankUser)); // 註記「是否提供滿意度問卷」
        aibankUser.setLoginLogPk(result.getLoginLogPk());
        aibankUser.setShowChangeTip(result.isShowChangeTip());
        aibankUser.setCountryName(result.getCountryName());
        aibankUser.setLoginIp(getRequest().getClientIp());
        // 建立 Session
        setLoginUser(aibankUser, ngnService.getTaskList(aibankUser));

        // 針對年紀預設字體大寫
        rsData.setFontSize(ngnService.getFontSize(aibankUser.getBirthDay()));

        // ** 登入成功推播通知*/
        sendPushNotification(aibankUser, result.getPushType());

        // 建立 Menu
        // rsData.setMenuInfo(ngnService.getMenuInfo(aibankUser, getLocale()));

        // 滿意度問卷
        rsData.setShowSatisfactionFlag(aibankUser.isShowSatisfactionFlag()); // 設置「是否需提供滿意度問卷」註記
        rsData.setSatisfactionTasksMap(aibankServiceSettingCacheManager.getDataMap(aibankUser.getCustId(), getLocale()));

        // 寄送通知信
        LoginMailType loginMailType = loginService.getLoginMailType(rqData.getLoginType(), rqData.getPwType(), true);

        if (CustomerType.isCardMember(rqData.getLoginType())) {
            // 是否為信用卡使用者登入
            rsData.setIsCCUser("true");
            aibankUser.setEmail(result.getCardEmail());

            LoginMailData loginMailData = loginService.getLoginMailData(loginMailType, getRequest().getClientIp(), bindingCache.getMarketingName(), getLocale(), null, null, aibankUser.getCountryName());
            sendMail(aibankUser.getCustId(), aibankUser.getUidDup(), aibankUser.getUserId(), aibankUser.getCompanyKind(), loginMailData.getSubject(), result.getCardEmail(), loginMailData.getParams());

            if (result.getMbDeviceInfoVo() != null) {
                rsData.setFastLoginStatus(result.getMbDeviceInfoVo().getDeviceInfoKey() == null ? 1 : 0);
            }

            String mobileNo = userDataCacheService.getOtpMobileFromCEW013R(aibankUser);

            if (Strings.isBlank(mobileNo)) {
                rsData.setFastLoginStatus(0);
            }
            aibankUser.getUserData().setNameCode("0001");
            if (result.getBirthDay() != null) {
                aibankUser.setBirthDay(result.getBirthDay());
            }

        }
        else {
            rsData.setIsCCUser("false");

            LoginMailData loginMailData = loginService.getLoginMailData(loginMailType, getRequest().getClientIp(), bindingCache.getMarketingName(), getLocale(), null, null, aibankUser.getCountryName());

            if (result.getEmail() != null) {
                for (String email : result.getEmail()) {
                    sendMail(aibankUser.getCustId(), aibankUser.getUidDup(), aibankUser.getUserId(), aibankUser.getCompanyKind(), loginMailData.getSubject(), email, loginMailData.getParams());
                }
            }
            /** 快登設定 */
            if (rqData.isFirstLogin()) {
                rsData.setFastLoginStatus(loginService.checkDeviceCanBind(aibankUser, getRequest().getDeviceIxd(), getLocale()));
                if (StringUtils.isBlank(result.getLoginMsgRs().getMobileNo())) {
                    rsData.setFastLoginStatus(0);
                }
            }
            if (result.getLoginMsgRs() != null && result.getLoginMsgRs().getBirthday() != null)
                aibankUser.setBirthDay(result.getLoginMsgRs().getBirthday().getTime());

        }

        // 半年未變更使用者密碼提示
        if (result.isShowChangeTip()) {
            rsData.setShowChangeTip(true);
            logger.debug("==== 客戶半年未變更使用者密碼 ====");
        }

        // 變更 Session
        String newSessionId = changeToNewSession();
        sessionResource.registerAccessSession(aibankUser.getCustId(), aibankUser.getUidDup(), aibankUser.getUserId(), aibankUser.getCompanyKind(), result.getLoginLogPk(), newSessionId);

        // OnBoarding 程序
        if (rsData.getFastLoginStatus() > 0) {
            DeviceBindingCache cache = new DeviceBindingCache();
            setCache(LoginService.DEVICE_TRANSFER_CACHE_KEY, cache);
        }

        // 加密大數據ID
        try {
            String kxy = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "NGNOT001_AES_KEY");
            String iv = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "NGNOT001_AES_IV");
            rsData.setCelebrusId(ngnService.stringToHex(AESUtils.encryptDataToBase64(aibankUser.getCustId() + aibankUser.getUserId(), kxy.getBytes(), iv.getBytes())));
        }
        catch (CryptException ex) {
            logger.warn("Celebrus ID Encryption Fail", ex);
        }
    }

    /**
     * 長者預設字體大小
     * 
     * @param date
     * @return
     */
    // private int getFontSize(Date date) {
    //
    // String ageString = systemParamCacheManager.getValue("AIBANK", "ELDER_AGE");
    //
    // if (ageString == null || date == null)
    // return 0;
    //
    // try {
    // int age = Integer.parseInt(ageString);
    //
    // Calendar cal = Calendar.getInstance();
    // cal.setTime(new Date());
    //
    // cal.add(Calendar.YEAR, age * -1);
    //
    // if (date.before(cal.getTime())) {
    // return 1;
    // }
    // }
    // catch (NumberFormatException ex) {
    // logger.warn("ELDER_AGE 未設定");
    // }
    //
    // return 0;
    // }

    /**
     * 取得 Task 清單
     * 
     * @param user
     * @return
     */
    // private String[] getTaskList(AIBankUser user) {
    //
    // RoleTemplateType roleTemplateType = user.getRoleTemplateType();
    // List<String> allTasks = roleTaskCacheManager.getTasksForRole(roleTemplateType.getName());
    //
    // Set<String> taskList = new HashSet<String>();
    // for (String task : allTasks) {
    // taskList.add(task);
    // }
    // return taskList.toArray(String[]::new);
    // }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }

    /**
     * 拋出 Exception
     * 
     * @param desc
     * @throws ActionException
     */
    protected void throwMessageException(String desc) throws ActionException {
        if (StringUtils.isBlank(desc))
            desc = " ";
        throw new ActionException(ErrorCode.VALIDATE_COMMON_ERROR, desc);
    }

    /**
     * 取得錯誤訊息
     * 
     * @param err
     * @return
     */
    protected String getMessage(ErrorCode err) {
        ErrorStatus error = new ErrorStatus(err);
        ErrorDescription errorDescription = IBUtils.getErrorDescMessage(errorCodeCacheManager, error, getLocale(), getPageId());

        if (errorDescription != null) {
            return errorDescription.getDesc();
        }
        logger.warn("ErrorCode not defined in DB {}", err);
        return "";
    }

    /**
     * 取得錯誤訊息
     * 
     * @param err
     * @return
     */
    protected String getMessage(String messageId) {
        return I18NUtils.getMessage(messageId);
    }

    /**
     * 發送 E-mail
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

    /**
     * 發送推播
     * 
     * 0-未綁定，未訂閱 1-不推播，2-推播
     * 
     * @param user
     * @param deviceInd
     */
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

    private Date getEndDate() {
        String daysStr = systemParamCacheManager.getValue("AIBANK", "NOTIFICATION_DURATION");
        int day = 90;
        if (!StringUtils.isBlank(daysStr)) {
            day = Integer.parseInt(daysStr);
        }
        return DateUtils.addDays(new Date(), day);
    }

    /**
     * 台北富邦專屬ID格式 8碼數字+2碼英文
     * 
     * @param idNumber
     * @return
     */
    private boolean isTFBResidenIDNumber(String idNumber) {
        // boolean result = false;

        if (idNumber == null || idNumber.trim().length() != 10) {
            return false;
        }

        /** AA00000000 */
        if (ValidatorUtility.checkNumeric(idNumber.substring(2)) && ValidatorUtility.checkAlpha(idNumber.substring(0, 2))) {
            return true;
        }

        /** 00000000AA */
        if (ValidatorUtility.checkNumeric(idNumber.substring(0, 8)) && ValidatorUtility.checkAlpha(idNumber.substring(8))) {
            return true;
        }

        /** A8/900000000 */
        if (ValidatorUtility.checkNumeric(idNumber.substring(2)) && ValidatorUtility.checkAlpha(idNumber.substring(0, 1)) && (idNumber.charAt(1) == '8' || idNumber.charAt(1) == '9')) {
            return true;
        }

        return false;
    }

}
