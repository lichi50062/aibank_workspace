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
package com.tfb.aibank.chl.general.ag001.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.devicebinding.DeviceBindingService;
import com.tfb.aibank.chl.component.devicebinding.model.CheckUserDeviceStatusCondition;
import com.tfb.aibank.chl.component.devicebinding.model.CheckUserDeviceStatusResult;
import com.tfb.aibank.chl.general.ag001.model.NGNAG001010Rq;
import com.tfb.aibank.chl.general.ag001.model.NGNAG001010Rs;

//@formatter:off
/**
* @(#)NGNAG001010Task.java
* 
* <p>Description:免登速查 - 設定頁 - Task</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NGNAG001010Task extends AbstractAIBankBaseTask<NGNAG001010Rq, NGNAG001010Rs> {

    @Autowired
    private DeviceBindingService deviceBindingService;

    @Override
    public void validate(NGNAG001010Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNAG001010Rq rqData, NGNAG001010Rs rsData) throws ActionException {

        // 檢查使用者與裝置綁定狀態
        CheckUserDeviceStatusResult result = checkUserDeviceStatus();

        // 使用者已綁定目前使用裝置，可繼續執行
        // 取得免登速查註記
        int qsearchFlag = result.getUserBinding().getModel().getQsearchFlag() == null ? 0 : result.getUserBinding().getModel().getQsearchFlag();
        rsData.setQsearchFlag(qsearchFlag);
    }

    /**
     * 檢查使用者與裝置綁定狀態
     * 
     * @return
     * @throws ActionException
     */
    private CheckUserDeviceStatusResult checkUserDeviceStatus() throws ActionException {
        CheckUserDeviceStatusCondition condition = new CheckUserDeviceStatusCondition();
        condition.setDeviceIxd(getDeviceIxd());
        condition.setLoginUser(getLoginUser());
        condition.setLocale(getLocale().toString());
        CheckUserDeviceStatusResult result = new CheckUserDeviceStatusResult();
        deviceBindingService.checkUserDeviceStatus(condition, result);
        if (!result.getStatus().isBind()) {
            throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
        }
        return result;
    }

}
