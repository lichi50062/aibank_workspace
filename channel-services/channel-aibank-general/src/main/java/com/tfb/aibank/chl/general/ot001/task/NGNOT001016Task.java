package com.tfb.aibank.chl.general.ot001.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.biz.type.TwoFactorAuthResponseType;
import com.tfb.aibank.biz.type.TwoFactorStatusType;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.devicemodel.DeviceModelCacheManager;
import com.tfb.aibank.chl.general.error.ErrorCode;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001016Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001016Rs;
import com.tfb.aibank.chl.general.ot001.task.service.TwoFactorAuthCache;
import com.tfb.aibank.chl.general.resource.NotificationResource;
import com.tfb.aibank.chl.general.resource.dto.TwoFactorAuthResponse;
import com.tfb.aibank.chl.general.resource.dto.TwoFactorAuthUserResponse;
import com.tfb.aibank.chl.general.resource.dto.UpdateTwoFactorAuthRequest;
import com.tfb.aibank.chl.general.service.LoginService;
import com.tfb.aibank.chl.general.type.TwoFactorAuthStepType;
import com.tfb.aibank.chl.general.type.TwoFactorAuthType;
import com.tfb.aibank.common.error.AIBankErrorCode;

//@formatter:off
/**
* @(#)NGNOT001016Task.java 
* 

* <p>Description:雙重認證</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20250612, benson
*  <li>初版</li>
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on

@Component
@Scope("prototype")
public class NGNOT001016Task extends AbstractAIBankBaseTask<NGNOT001016Rq, NGNOT001016Rs> {

    private TwoFactorAuthCache cache;

    @Autowired
    private LoginService loginService;

    @Autowired
    private NotificationResource notificationResource;

    @Autowired
    private DeviceModelCacheManager deviceModelCacheManager;

    @Override
    public void validate(NGNOT001016Rq rqData) throws ActionException {

        cache = getCache(LoginService.TWO_FACTOR_CACHE_KEY, TwoFactorAuthCache.class);

        if (cache == null) {
            throw ExceptionUtils.getActionException(CommonErrorCode.UNAUTHORIZED_OPERATIONAL);
        }

    }

    @Override
    public void handle(NGNOT001016Rq rqData, NGNOT001016Rs rsData) throws ActionException {

        Integer companyKey = cache.getCompanyKey();
        Integer userKey = cache.getUserKey();
        Long seq = cache.getSeq();

        TwoFactorAuthStepType preAuthStep = TwoFactorAuthStepType.find(cache.getAuthStep());

        Integer action = Optional.ofNullable(rqData.getAction()).orElse(0);
        switch (action) {
        case 0 -> initTwoFactorAuth(rsData, companyKey, userKey, preAuthStep);
        case 1 -> queryTwoFactorAuth(rsData, companyKey, userKey, seq, preAuthStep);
        case 2 -> timeoutTwoFactorAuth(rsData, companyKey, userKey, seq, preAuthStep);
        case 3 -> cancelTwoFactorAuth(rsData, companyKey, userKey, seq, preAuthStep);
        default -> throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }

    }

    private void cancelTwoFactorAuth(NGNOT001016Rs rsData, Integer companyKey, Integer userKey, Long seq, TwoFactorAuthStepType authStep) throws ActionException {
        // TIMEOUT
        if (TwoFactorAuthStepType.SEND_SUCCESS != authStep && TwoFactorAuthStepType.WAIT != authStep) {
            // 狀態異常
            logger.error("cancelTwoFactorAuth invalid authStep: {}, seq: {}", authStep, seq);
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
        String deviceId = cache.getDeviceId();
        String clientIp = this.getClientIp();

        // 倒數已歸0, 更新狀態為 CANCEL
        TwoFactorAuthUserResponse cancelResponse = loginService.handleTwoFactorAuth(TwoFactorAuthType.DEVICE, seq, companyKey, userKey, TwoFactorStatusType.CANCEL, deviceId, clientIp, cache.getCountryName(), cache.getPersonNotificationRecordKey());

        rsData.setResult(1);
        rsData.setAuthStep(TwoFactorAuthStepType.CANCEL.getCode());
        cache.setAuthStep(TwoFactorAuthStepType.CANCEL.getCode());

        // 更新通知狀態
        UpdateTwoFactorAuthRequest updateNotificationRq = new UpdateTwoFactorAuthRequest();
        updateNotificationRq.setPersonNotificationRecordKey(cache.getPersonNotificationRecordKey());

        updateNotificationRq.setUpdatePersonNotificationOnly(true);
        updateNotificationRq.setUpdateAction(TwoFactorStatusType.TIMEOUT.toString());
        updateNotificationRq.setLocale(this.getLocale().toString());

        Boolean updateNotificationResult = notificationResource.updateTwoFactorAuthUpdateNotificationOnly(updateNotificationRq);

        logger.debug("cancelTwoFactorAuthUser: {}", cancelResponse);

        logger.info("updateNotificationResult: {}", updateNotificationResult);

        setCache(LoginService.TWO_FACTOR_CACHE_KEY, this.cache);
    }

    private void timeoutTwoFactorAuth(NGNOT001016Rs rsData, Integer companyKey, Integer userKey, Long seq, TwoFactorAuthStepType authStep) throws ActionException {
        // TIMEOUT
        if (TwoFactorAuthStepType.SEND_SUCCESS != authStep && TwoFactorAuthStepType.WAIT != authStep) {
            // 狀態異常
            logger.error("timeoutTwoFactorAuth invalid authStep: {}, seq: {}", authStep, seq);
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
        String deviceId = cache.getDeviceId();
        String clientIp = this.getClientIp();

        // 倒數已歸0, 更新狀態為 TIMEOUT
        TwoFactorAuthUserResponse timeoutResponse = loginService.handleTwoFactorAuth(TwoFactorAuthType.DEVICE, seq, companyKey, userKey, TwoFactorStatusType.TIMEOUT, deviceId, clientIp, cache.getCountryName(), cache.getPersonNotificationRecordKey());

        rsData.setResult(1);
        rsData.setAuthStep(TwoFactorAuthStepType.TIMEOUT.getCode());
        cache.setAuthStep(TwoFactorAuthStepType.TIMEOUT.getCode());

        // 更新通知狀態
        UpdateTwoFactorAuthRequest updateNotificationRq = new UpdateTwoFactorAuthRequest();
        updateNotificationRq.setPersonNotificationRecordKey(cache.getPersonNotificationRecordKey());
        updateNotificationRq.setUpdatePersonNotificationOnly(true);
        updateNotificationRq.setUpdateAction(TwoFactorStatusType.TIMEOUT.toString());
        updateNotificationRq.setLocale(this.getLocale().toString());

        Boolean updateNotificationResult = notificationResource.updateTwoFactorAuthUpdateNotificationOnly(updateNotificationRq);

        logger.info("timeoutTwoFactorAuthUser: {}, {}", timeoutResponse, updateNotificationResult);
        setCache(LoginService.TWO_FACTOR_CACHE_KEY, this.cache);
    }

    private void queryTwoFactorAuth(NGNOT001016Rs rsData, Integer companyKey, Integer userKey, Long seq, TwoFactorAuthStepType authStep) throws ActionException {
        if (TwoFactorAuthStepType.SEND_SUCCESS != authStep && TwoFactorAuthStepType.WAIT != authStep) {
            // 狀態異常]
            logger.error("queryTwoFactorAuth invalid authStep: {}, {}", authStep, seq);
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
        String deviceId = cache.getDeviceId();
        String clientIp = this.getClientIp();

        TwoFactorAuthUserResponse queryAuthResponse = loginService.queryTwoFactorAuth(TwoFactorAuthType.DEVICE, seq, companyKey, userKey, deviceId, clientIp, cache.getCountryName());
        TwoFactorStatusType status = TwoFactorStatusType.findByType(queryAuthResponse.getStatus());
        ActionException ex = null;

        if (TwoFactorStatusType.SUCCESS == status) {
            logger.debug("success status: " + status);
            authStep = TwoFactorAuthStepType.SUCCESS;
        }
        else if (TwoFactorStatusType.WAIT == status) {
            logger.debug("wait status: " + status);
            authStep = TwoFactorAuthStepType.WAIT;
        }
        else if (TwoFactorStatusType.TIMEOUT == status) {
            logger.warn("timeout status: " + status);
            authStep = TwoFactorAuthStepType.TIMEOUT;
            ex = ExceptionUtils.getActionException(ErrorCode.TWO_FACTOR_TIMEOUT);
        }
        else if (TwoFactorStatusType.FAIL == status) {
            logger.error("fail status: " + status);
            authStep = TwoFactorAuthStepType.FAIL;
            ex = ExceptionUtils.getActionException(ErrorCode.TWO_FACTOR_FAILED);
        }
        else {
            logger.error("unknown status: " + status);
            authStep = TwoFactorAuthStepType.FAIL;
            ex = ExceptionUtils.getActionException(ErrorCode.TWO_FACTOR_FAILED);
        }

        rsData.setAuthStep(authStep.getCode());
        rsData.setResult(1);

        cache.setAuthStep(authStep.getCode());

        setCache(LoginService.TWO_FACTOR_CACHE_KEY, cache);

        if (ex != null) {
            throw ex;
        }
    }

    private void initTwoFactorAuth(NGNOT001016Rs rsData, Integer companyKey, Integer userKey, TwoFactorAuthStepType authStep) throws ActionException {
        if (TwoFactorAuthStepType.UNKNOWN != authStep && TwoFactorAuthStepType.INIT != authStep && TwoFactorAuthStepType.TIMEOUT != authStep) {
            // throw ex 不能重覆設置狀態
            logger.error("twofactor initTwoFactorAuth invalid authStep: {}", authStep);
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
        String deviceId = cache.getDeviceId(); // 目前裝置 deviceId
        String clientIp = this.getClientIp();
        String deviceName = deviceModelCacheManager.getDeviceMarketingNameOrModel(this.getModel());
        String deviceOs = this.getPlatformDisplay();
        String location = cache.getCountryName();

        // 新增 DB: TWO_FACTOR_AUTH
        TwoFactorAuthUserResponse initAuthUserResponse = loginService.initTwoFactorAuth(TwoFactorAuthType.DEVICE, companyKey, userKey, deviceId, clientIp, location);
        authStep = TwoFactorAuthStepType.INIT;
        logger.debug("twofactor initTwoFactorAuthUser: " + initAuthUserResponse);
        logger.debug("model: {}, deviceName: {}", this.getModel(), deviceName);

        // SEQ, 不能放到前端
        cache.setSeq(initAuthUserResponse.getSeq());
        // 8928
        String loginTimeStr = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
        TwoFactorAuthResponse sendNotificationAuthResponse = loginService.sendTwoFactorAuthNotification(companyKey, userKey, initAuthUserResponse, deviceId, deviceName, deviceOs, clientIp, location, loginTimeStr, getLocale());
        logger.debug("twofactor initTwoFactorAuthUser sendTwoFactorAuthNotification: " + sendNotificationAuthResponse);

        // 失敗, 導回登入頁 拋錯
        TwoFactorAuthResponseType responseType = TwoFactorAuthResponseType.find(sendNotificationAuthResponse.getStatus());

        switch (Optional.ofNullable(responseType).orElse(TwoFactorAuthResponseType.UNKNOWN_ERROR)) {
        case UNKNOWN_ERROR, COMPANY_KEY_CANNOT_BE_EMPTY, USER_KEY_CANNOT_BE_EMPTY, PUSH_TITLE_CANNOT_BE_EMPTY, PUSH_CONTENT_CANNOT_BE_EMPTY -> {
            logger.error("twofactor error: {}, {}", companyKey, userKey, responseType);
            throw new ActionException(AIBankErrorCode.TWO_FACTOR_API_VERIFY_FAILED);
        }
        case UNABLE_TO_FIND_BIND_INFO -> {
            logger.error("twofactor error: {}, {}", companyKey, userKey, responseType);
            throw new ActionException(AIBankErrorCode.TWO_FACTOR_API_NOTBINDED);
        }
        case PUSH_NOTIFICATION_DISABLED -> {
            logger.error("twofactor error: {}, {}", companyKey, userKey, responseType);
            throw new ActionException(AIBankErrorCode.TWO_FACTOR_API_DISABLE_NOTIFYFLAG);
        }
        case SUCCESS -> {
            logger.debug("twofactor initTwoFactorAuthUser Success");
            authStep = TwoFactorAuthStepType.SEND_SUCCESS;
        }
        default -> {
            logger.error("twofactor unknown: {} {} {} ", companyKey, userKey, sendNotificationAuthResponse.getStatus());
            throw new ActionException(AIBankErrorCode.TWO_FACTOR_API_VERIFY_FAILED);
        }
        }
        // 回寫資料
        // 驗證間隔時間
        rsData.setCheckSeconds(loginService.getCheckTwoFactorCheckSeconds());
        rsData.setAuthStep(authStep.getCode());
        rsData.setResult(1);
        rsData.setBindedDevice(cache.getMbDeviceInfoVoBindedByOtherDevice() == null ? "" : deviceModelCacheManager.getDeviceMarketingNameOrModel(cache.getMbDeviceInfoVoBindedByOtherDevice().getModel())); // 顯示綁定裝置
        rsData.setLoginTime(loginTimeStr);

        // Init 重送時需更新 PersonNotificationRecordKey
        cache.setPersonNotificationRecordKey(sendNotificationAuthResponse.getPersonNotificationRecordKey());
        cache.setAuthStep(authStep.getCode());

        setCache(LoginService.TWO_FACTOR_CACHE_KEY, this.cache);
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }

}
