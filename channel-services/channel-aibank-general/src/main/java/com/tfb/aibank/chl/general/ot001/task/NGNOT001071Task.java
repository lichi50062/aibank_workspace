package com.tfb.aibank.chl.general.ot001.task;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.devicebinding.DeviceBindingService;
import com.tfb.aibank.chl.component.devicebinding.model.CheckUserDeviceStatusCondition;
import com.tfb.aibank.chl.component.devicebinding.model.CheckUserDeviceStatusResult;
import com.tfb.aibank.chl.component.devicebinding.type.UserDeviceBindStatusType;
import com.tfb.aibank.chl.component.userdatacache.model.BindingAuthFlagRequest;
import com.tfb.aibank.chl.component.userdatacache.model.BindingAuthFlagResponse;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001071Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001071Rs;
import com.tfb.aibank.chl.general.ot001.task.service.DeviceBindingCache;
import com.tfb.aibank.chl.general.resource.SecurityResource;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.resource.dto.AddMbDeviceInfoRequest;
import com.tfb.aibank.chl.general.resource.dto.OnboardingLogRequest;
import com.tfb.aibank.chl.general.resource.dto.UpdateNotficationNoPainRequest;
import com.tfb.aibank.chl.general.resource.dto.UpdateNotficationRequest;
import com.tfb.aibank.chl.general.resource.dto.UpdateNotficationResponse;
import com.tfb.aibank.chl.general.resource.dto.UpdateUserDeviceBindingRequest;
import com.tfb.aibank.chl.general.service.LoginService;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.error.AIBankErrorCode;

//@formatter:off
/**
* @(#)NGNOT001071Task.java 
* 
* <p>Description:移轉處理Task</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20230622, JohnChang
*  <li>初版</li>
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on

@Component
@Scope("prototype")
public class NGNOT001071Task extends AbstractAIBankBaseTask<NGNOT001071Rq, NGNOT001071Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT001071Task.class);

    @Autowired
    private UserResource userResource;

    @Autowired
    private SecurityResource securityResource;

    @Autowired
    private DeviceBindingService deviceBindingService;

    @Override
    public void validate(NGNOT001071Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNOT001071Rq rqData, NGNOT001071Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001071 start====");

        AIBankUser loginUser = getLoginUser();

        DeviceBindingCache cache = getCache(LoginService.DEVICE_TRANSFER_CACHE_KEY, DeviceBindingCache.class);

        if (cache == null) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.DATA_NOT_FOUND);
        }

        if (!cache.isBindingStatus() && cache.isTransferNeed()) {
            UserDeviceBindStatusType userDeviceBindStatusType = checkDeviceBindingStatus();
            if (userDeviceBindStatusType.isUnbind() || userDeviceBindStatusType.isBindedDeviceNotMatch()) {
                // 未綁定，先做裝置綁定
                addDevice(loginUser, rqData);
            }
        }

        // 訊息通知
        UpdateNotficationResponse notifyResponse = null;
        if (cache.getNotification() == LoginService.IS_TRANSFER) {

            UpdateNotficationNoPainRequest request = new UpdateNotficationNoPainRequest();
            request.setCustId(loginUser.getCustId());
            request.setUidDup(loginUser.getUidDup());
            request.setUserId(loginUser.getUserId());
            request.setCompanyKind(loginUser.getCompanyKind());
            request.setMobileNo(this.getLoginUser().getMobileNo());
            request.setBirthday(loginUser.getBirthDay());
            request.setNameCode(loginUser.getNameCode());
            request.setNotificationTypes(loginUser.getDeviceSubPushCode());
            request.setVersion(getAppVer());

            try {
                notifyResponse = userResource.updateNotfication4NoPain(request);
                if (CollectionUtils.isEmpty(notifyResponse.getFailNotificationTypes())) {
                    cache.setNotification(LoginService.TRANSFER_SUCC);
                }
                else {
                    cache.setNotification(LoginService.TRANSFER_FAIL);
                }

            }
            catch (RuntimeException ex) {
                logger.warn("訊息通知設定失敗", ex);
                cache.setNotification(LoginService.TRANSFER_FAIL);
            }
        }

        if (cache.isNotificationAll()) {
            UpdateNotficationRequest request = new UpdateNotficationRequest();
            request.setCustId(loginUser.getCustId());
            request.setUidDup(loginUser.getUidDup());
            request.setUserId(loginUser.getUserId());
            request.setCompanyKind(loginUser.getCompanyKind());
            request.setMobileNo(this.getLoginUser().getMobileNo());
            request.setNotificationAgreeFlag(1);
            request.setLineAgreeFlag(0);
            request.setBirthday(loginUser.getBirthDay());
            request.setNameCode(loginUser.getNameCode());
            // 裝置版本號
            request.setVersion(getAppVer());
            // 用戶類型 (A). 若為一般客戶：TYPE = 0。 (B). 若為信用卡網路會員：TYPE = 1
            request.setType(loginUser.getCustomerType().isGeneral() ? 0 : 1);

            try {
                notifyResponse = userResource.updateNotfication(request);
                if (CollectionUtils.isEmpty(notifyResponse.getFailNotificationTypes())) {
                    cache.setNotification(LoginService.TRANSFER_SUCC);
                }
                else {
                    cache.setNotification(LoginService.TRANSFER_FAIL);
                }
            }
            catch (ServiceException ex) {
                logger.error("訊息通知全部設定失敗", ex);
                cache.setNotification(LoginService.TRANSFER_FAIL);
            }
        }

        // 免登速查
        if (cache.getQuickSearch() == LoginService.IS_TRANSFER) {
            // 快速登入、推播通知、免登速查

            UpdateUserDeviceBindingRequest request = new UpdateUserDeviceBindingRequest();
            request.setDeviceUuid(getDeviceIxd());
            request.setUpdateQsearchFlag(true);
            request.setQsearchFlag(1);
            try {
                userResource.updateUserDeviceBinding(loginUser.getCustId(), loginUser.getUidDup(), loginUser.getUserId(), loginUser.getCompanyKind(), request);
                cache.setQuickSearch(LoginService.TRANSFER_SUCC);
            }
            catch (RuntimeException ex) {
                logger.warn("免登速查設定失敗", ex);
                cache.setQuickSearch(LoginService.TRANSFER_FAIL);
            }
        }

        // 動態密碼設定
        if (cache.getMotpSetting() == LoginService.IS_TRANSFER) {

        }

        // 已綁定無卡提款
        // 已綁定手機號碼收款
        // 已綁定提高非約轉額度
        if (cache.getNoCardwithDraw() == LoginService.IS_TRANSFER || //
                cache.getPhoneTransfer() == LoginService.IS_TRANSFER || //
                cache.getTransferQuota() == LoginService.IS_TRANSFER) {

            BindingAuthFlagRequest request = new BindingAuthFlagRequest();
            request.setAction(1);
            request.setCustId(loginUser.getCustId());
            request.setUserId(loginUser.getUserId());
            request.setUidDup(loginUser.getUidDup());
            request.setCompanyKind(loginUser.getCompanyKind());
            request.setDeviceIxd(getRequest().getDeviceIxd());

            // 已綁定無卡提款
            if (cache.getNoCardwithDraw() == LoginService.IS_TRANSFER) {
                request.setWithdrawalFlag(1);
            }
            if (cache.getPhoneTransfer() == LoginService.IS_TRANSFER) {
                request.setPhoneTransferFlag(1);
            }

            if (cache.getTransferQuota() == LoginService.IS_TRANSFER) {
                request.setRaiseTransferFlag(1);
            }

            // 設定裝置綁定授權註記
            BindingAuthFlagResponse response = securityResource.getBindingAuthFlag(request);

            if (response.getStatus() == 0) {
                if (cache.getNoCardwithDraw() == LoginService.IS_TRANSFER) {
                    cache.setNoCardwithDraw(LoginService.TRANSFER_SUCC);
                }
                if (cache.getPhoneTransfer() == LoginService.IS_TRANSFER) {
                    cache.setPhoneTransfer(LoginService.TRANSFER_SUCC);
                }

                if (cache.getTransferQuota() == LoginService.IS_TRANSFER) {
                    cache.setTransferQuota(LoginService.TRANSFER_SUCC);
                }
            }
            else {
                if (cache.getNoCardwithDraw() == LoginService.IS_TRANSFER) {
                    cache.setNoCardwithDraw(LoginService.TRANSFER_FAIL);
                }
                if (cache.getPhoneTransfer() == LoginService.IS_TRANSFER) {
                    cache.setPhoneTransfer(LoginService.TRANSFER_FAIL);
                }

                if (cache.getTransferQuota() == LoginService.IS_TRANSFER) {
                    cache.setTransferQuota(LoginService.TRANSFER_FAIL);
                }
            }

        }

        setCache(LoginService.DEVICE_TRANSFER_CACHE_KEY, cache);

        OnboardingLogRequest request = new OnboardingLogRequest();
        request.setCustId(loginUser.getCustId());
        request.setUidDup(loginUser.getUidDup());
        request.setUserId(loginUser.getUserId());
        request.setCompanyKind(loginUser.getCompanyKind());
        request.setDeviceIxd(getRequest().getDeviceIxd());
        request.setOnboardingType(loginUser.getOnboardingType());

        request.setNotification(cache.getNotification());
        request.setNoCardwithDraw(cache.getNoCardwithDraw());
        request.setQuickSearch(cache.getQuickSearch());
        request.setMotpSetting(cache.getMotpSetting());
        request.setPhoneTransfer(cache.getPhoneTransfer());
        request.setTransferQuota(cache.getTransferQuota());

        if (notifyResponse != null) {
            request.setFailNotificationTypes(notifyResponse.getFailNotificationTypes());
            request.setSuccNotificationTypes(notifyResponse.getSuccNotificationTypes());
        }

        userResource.addOnboardingTransferLog(request);

    }

    private UserDeviceBindStatusType checkDeviceBindingStatus() {
        // 檢查使用者與裝置綁定狀態
        CheckUserDeviceStatusCondition condition = new CheckUserDeviceStatusCondition();
        condition.setDeviceIxd(getDeviceIxd());
        condition.setLoginUser(getLoginUser());
        condition.setLocale(getLocale().toString());
        CheckUserDeviceStatusResult result = new CheckUserDeviceStatusResult();
        deviceBindingService.checkUserDeviceStatus(condition, result);

        return result.getStatus();
    }

    private void addDevice(AIBankUser user, NGNOT001071Rq rqData) {
        AddMbDeviceInfoRequest request = new AddMbDeviceInfoRequest();

        request.setCustId(user.getCustId());
        request.setUidDup(user.getUidDup());
        request.setUserId(user.getUserId());
        request.setCompanyKind(user.getCompanyKindType().getCode());
        request.setToken("");
        request.setVerifyNo("");
        request.setRegisterDevice(true);
        request.setDeviceId(getRequest().getDeviceIxd());
        request.setModel(getRequest().getModel());
        request.setDevicePlatform(this.getPlatformDisplay());
        request.setDevicePlatformVersion(getRequest().getVersion());
        request.setIp(getRequest().getClientIp());
        request.setLoginType(user.getCustomerType().isGeneral() ? 0 : 1);
        request.setLoginPasswordType(0);
        request.setPushToken(rqData.getPushToken());

        securityResource.addMbDeviceInfo(request);
    }

}
