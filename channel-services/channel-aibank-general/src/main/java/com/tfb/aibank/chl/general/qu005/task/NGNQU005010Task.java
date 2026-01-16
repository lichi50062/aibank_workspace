package com.tfb.aibank.chl.general.qu005.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.qu005.model.NGNQU005010Rq;
import com.tfb.aibank.chl.general.qu005.model.NGNQU005010Rs;
import com.tfb.aibank.chl.general.qu005.model.NGNQU005Output;
import com.tfb.aibank.chl.general.qu005.service.NGNQU005Service;
import com.tfb.aibank.chl.general.utils.NGNUtils;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NGNQU005010Task.java
 * 
 * <p>Description:帳戶安全中心 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/04, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NGNQU005010Task extends AbstractAIBankBaseTask<NGNQU005010Rq, NGNQU005010Rs> {

    @Autowired
    @Qualifier("NGNQU005Service")
    private NGNQU005Service service;

    @Autowired
    private NGNUtils utils;

    private NGNQU005Output dataOutput = new NGNQU005Output();

    @Override
    public void validate(NGNQU005010Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NGNQU005010Rq rqData, NGNQU005010Rs rsData) throws ActionException {

        AIBankUser loginUser = getLoginUser();

        // 「帳號安全區塊」密碼
        service.checkChangePwdStatus(loginUser, dataOutput);
        rsData.setPwChgDate(dataOutput.getPwChgDate());
        rsData.setChangePwdHalfYear(dataOutput.isChangePwdHalfYear());
        rsData.setKeepOldPwdHalfYear(dataOutput.isKeepOldPwdHalfYear());
        rsData.setMonth(dataOutput.getMonth());
        // 「帳號安全區塊」Email
        rsData.setGeneral(loginUser.getCustomerType().isGeneral());
        rsData.setUserEmail(DataMaskUtil.maskEmail(loginUser.getEmail()));
        rsData.setEmailExists(StringUtils.isNotBlank(loginUser.getEmail()));
        rsData.setEmailValid(utils.checkEmail(loginUser.getEmail()));

        service.checkDeviceStatus(loginUser, getLocale(), this.getRequest(), dataOutput);
        // 「交易驗證區塊」OTP
        rsData.setEnableMOTP(dataOutput.isEnableMOTP());
        // 「裝置設定區塊」FIDO
        rsData.setEnableFIDO(dataOutput.isEnableFIDO());
        rsData.setBiometricsDesc(dataOutput.getBiometricsDesc());
        // 「裝置設定區塊」裝置綁定
        rsData.setTwoStepFlag(dataOutput.isTwoStepFlag());
        rsData.setDeviceBinding(dataOutput.isDeviceBinding());
        rsData.setDeviceName(dataOutput.getDeviceName());
        // 「軟體與系統區塊」APP版本
        rsData.setAppIsLatestVersion(dataOutput.isAppIsLatestVersion());
        rsData.setAppVersion(dataOutput.getAppVersion());
        rsData.setDbVersion(dataOutput.getDbVersion());
        // 「軟體與系統區塊」手機作業系統
        rsData.setMobileOS(dataOutput.getMobileOS());

        if (logger.isDebugEnabled()) {
            logger.debug(IBUtils.attribute2Str(rsData));
        }
    }

}
