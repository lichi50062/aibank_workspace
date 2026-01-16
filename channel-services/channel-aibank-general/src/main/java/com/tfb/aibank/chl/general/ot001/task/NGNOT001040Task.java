package com.tfb.aibank.chl.general.ot001.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001040Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001040Rs;
import com.tfb.aibank.common.code.AIBankConstants;

//@formatter:off
/**
* @(#)NGNOT001040Task.java 
* 
* <p>Description:登入問題引導頁</p>
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
public class NGNOT001040Task extends AbstractAIBankBaseTask<NGNOT001040Rq, NGNOT001040Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT001040Task.class);

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Override
    public void validate(NGNOT001040Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNOT001040Rq rqData, NGNOT001040Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001040 start====");

        String data = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "DIGITAL_DEPOSIT_URL");
        rsData.setDigitalDepositUrl(data);

        data = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "APPLY_USERID_PASSWORD_URL");
        rsData.setApplyUseridPasswordUrl(data);

        data = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "ENABLE_CREDITCARD_URL");
        rsData.setEnableCreditcardUrl(data);

        data = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "APPLY_CREDITCARD_USER_URL");
        rsData.setApplyCreditcardUserUrl(data);

    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
