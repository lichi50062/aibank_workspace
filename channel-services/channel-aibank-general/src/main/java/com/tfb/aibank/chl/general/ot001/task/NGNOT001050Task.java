package com.tfb.aibank.chl.general.ot001.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.devicebinding.DeviceBindingService;
import com.tfb.aibank.chl.component.devicebinding.model.CheckUserDeviceStatusCondition;
import com.tfb.aibank.chl.component.devicebinding.model.CheckUserDeviceStatusResult;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001050Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001050Rs;
import com.tfb.aibank.chl.general.ot001.task.service.DeviceBindingCache;
import com.tfb.aibank.chl.general.service.LoginService;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.error.AIBankErrorCode;

//@formatter:off
/**
* @(#)NGNOT001050Task.java 
* 
* <p>Description:移轉設定頁</p>
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
public class NGNOT001050Task extends AbstractAIBankBaseTask<NGNOT001050Rq, NGNOT001050Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT001050Task.class);

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Autowired
    private DeviceBindingService deviceBindingService;

    @Override
    public void validate(NGNOT001050Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNOT001050Rq rqData, NGNOT001050Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001050 start====");

        AIBankUser loginUser = getLoginUser();

        if (loginUser.getOnboardingType() < 2) {
            throw new ActionException(AIBankErrorCode.SSO_DATA_ERROR);
        }

        DeviceBindingCache cache = getCache(LoginService.DEVICE_TRANSFER_CACHE_KEY, DeviceBindingCache.class);
        if (cache == null) {
            throw new ActionException(AIBankErrorCode.DATA_NOT_FOUND);
        }

        if (rqData.isFidoBindFail()) {
            cache.setFastLogin(LoginService.TRANSFER_FAIL);
        }

        // 檢查使用者與裝置綁定狀態
        CheckUserDeviceStatusCondition condition = new CheckUserDeviceStatusCondition();
        condition.setDeviceIxd(getDeviceIxd());
        condition.setLoginUser(getLoginUser());
        condition.setLocale(getLocale().toString());
        CheckUserDeviceStatusResult result = new CheckUserDeviceStatusResult();
        deviceBindingService.checkUserDeviceStatus(condition, result);

        rsData.setBindingStatus(cache.isOtpAuthed());
        if (!cache.isOtpAuthed()) {
            super.initTxSecurity(I18NUtils.getMessage("ngn.ot001.otp.transfer"));
        }

        rsData.setTransferNeed(cache.isTransferNeed());
        rsData.setNotification(cache.getNotification() == LoginService.IS_TRANSFER);
        rsData.setQuickSearch(cache.getQuickSearch() == LoginService.IS_TRANSFER);
        rsData.setNoCardwithDraw(cache.getNoCardwithDraw() == LoginService.IS_TRANSFER);
        rsData.setMotpSetting(cache.getMotpSetting() == LoginService.IS_TRANSFER);
        rsData.setPhoneTransfer(cache.getPhoneTransfer() == LoginService.IS_TRANSFER);
        rsData.setTransferQuota(cache.getTransferQuota() == LoginService.IS_TRANSFER);

        setCache(LoginService.DEVICE_TRANSFER_CACHE_KEY, cache);

    }

}
