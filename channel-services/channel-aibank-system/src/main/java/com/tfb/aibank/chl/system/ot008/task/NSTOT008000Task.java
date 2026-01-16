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
package com.tfb.aibank.chl.system.ot008.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.devicebinding.DeviceBindingService;
import com.tfb.aibank.chl.component.devicebinding.model.CheckUserDeviceStatusCondition;
import com.tfb.aibank.chl.component.devicebinding.model.CheckUserDeviceStatusResult;
import com.tfb.aibank.chl.component.devicebinding.model.ValidateDeviceBindingCondition;
import com.tfb.aibank.chl.component.devicebinding.model.ValidateDeviceBindingResult;
import com.tfb.aibank.chl.component.userdatacache.SecurityResource;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.component.userdatacache.model.BindingAuthFlagRequest;
import com.tfb.aibank.chl.component.userdatacache.model.BindingAuthFlagResponse;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.system.ot008.model.NSTOT008000Rq;
import com.tfb.aibank.chl.system.ot008.model.NSTOT008000Rs;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.common.error.MbErrorCode;

// @formatter:off
/**
 * @(#)NSTOT008000Task.java
 * 
 * <p>Description:裝置綁定檢核頁面初始初始</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/11, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NSTOT008000Task extends AbstractAIBankBaseTask<NSTOT008000Rq, NSTOT008000Rs> {

    @Autowired
    private DeviceBindingService deviceBindingService;

    @Autowired
    private UserDataCacheService userDataCacheService;
    @Autowired
    private SecurityResource securityResource;

    @Override
    public void validate(NSTOT008000Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NSTOT008000Rq rqData, NSTOT008000Rs rsData) throws ActionException {
        AIBankUser user = getLoginUser();

        if (user.getCustomerType().isCardMember()) {
            if (StringUtils.isBlank(userDataCacheService.getOtpMobileFromCEW013R(user))) {
                throw ExceptionUtils.getActionException(AIBankErrorCode.TX_SECURITY_INIT_OTP_AS400_ERROR);
            }
        }
        else {
            if (StringUtils.isBlank(getLoginUser().getLoginMsgRs().getMobileNo())) {
                throw ExceptionUtils.getActionException(MbErrorCode.MB3504.getError());
            }
        }

        // 檢查使用者與裝置綁定狀態
        CheckUserDeviceStatusCondition condition = new CheckUserDeviceStatusCondition();
        condition.setDeviceIxd(getDeviceIxd());
        condition.setLoginUser(getLoginUser());
        condition.setLocale(getLocale().toString());
        CheckUserDeviceStatusResult result = new CheckUserDeviceStatusResult();
        deviceBindingService.checkUserDeviceStatus(condition, result);

        // 檢查是否可以做裝置綁定
        ValidateDeviceBindingCondition validateCondition = new ValidateDeviceBindingCondition();
        validateCondition.setLoginUser(getLoginUser());
        validateCondition.setCheckUserDeviceStatusResult(result);
        ValidateDeviceBindingResult validateResult = new ValidateDeviceBindingResult();
        deviceBindingService.validateDeviceBinding(validateCondition, validateResult);

        // 回傳資料
        rsData.setValid(validateResult.isValid());
        rsData.setAlreadyBind(validateResult.isValid());
        rsData.setChangeDevice(validateResult.isChangeDevice());

        BindingAuthFlagRequest request = new BindingAuthFlagRequest();
        request.setCustId(user.getCustId());
        request.setUserId(user.getUserId());
        request.setUidDup(user.getUidDup());
        request.setCompanyKind(user.getCompanyKind());
        request.setDeviceIxd(getRequest().getDeviceIxd());
        request.setAction(0);
        BindingAuthFlagResponse response = securityResource.getBindingAuthFlag(request);

        if (StringUtils.isBlank(rqData.getTaskId())) {
            // 特定交易已授權
            rsData.setAlreadyAuth(true);
            return;
        }
        rsData.setNeedOtp(true);

        String taskName = getTaskName();

        // 無卡提款
        if (StringUtils.equals(rqData.getTaskId(), "NDSAG005")) {
            if (response != null && response.getWithdrawalFlag() != null && response.getWithdrawalFlag() == 1) {
                rsData.setAlreadyAuth(true);
                return;
            }

            // 已綁未授權
            if (rsData.isValid()) {
                taskName = I18NUtils.getMessage("nstot008.otp.noCardWithdraw") + I18NUtils.getMessage("nstot008.otp.authNote");
            }
            else {
                if (rsData.isChangeDevice()) {
                    taskName = null;
                }
                else {
                    taskName = I18NUtils.getMessage("nstot008.otp.noCardWithdraw");
                }
            }
            rsData.setValid(false);
            rsData.setNeedOtp(true);
        }

        // 變更轉帳額度
        else if (StringUtils.equals(rqData.getTaskId(), "NPSAG006")) {
            if (response != null && response.getRaiseTransferFlag() != null && response.getRaiseTransferFlag() == 1) {
                rsData.setAlreadyAuth(true);
                return;
            }
            rsData.setValid(false);
            rsData.setNeedOtp(true);
            taskName = I18NUtils.getMessage("nstot008.otp.changeQuota");
        }
        else if (!rsData.isValid()) {
            taskName = null;
        }

        if (!rqData.isCheckOnly()) {
            super.initTxSecurity(taskName, rqData.getTaskId(), true);
        }
        if (!rsData.isValid()) {
            rsData.setMotpFlag(StringUtils.equals(userDataCacheService.getMotpFlag(getLoginUser(), getDeviceIxd()), "Y"));
        }
    }

}
