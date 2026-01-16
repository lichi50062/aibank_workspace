package com.tfb.aibank.chl.general.ot010.task;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ot010.model.NGNOT010030Rq;
import com.tfb.aibank.chl.general.ot010.model.NGNOT010030Rs;
import com.tfb.aibank.common.code.AIBankConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@formatter:off
/**
* @(#)NGNOT010030Task.java
*
* <p>Description: 註冊分流頁</p>
*
*/
//@formatter:on
@Component
@Scope("prototype")
public class NGNOT010030Task extends AbstractAIBankBaseTask<NGNOT010030Rq, NGNOT010030Rs> {

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Override
    public void validate(NGNOT010030Rq rqData) throws ActionException {
    }

    @Override
    public void handle(NGNOT010030Rq rqData, NGNOT010030Rs rsData) throws ActionException {
        String chatBotUrl = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "CHATBOT_URL");
        rsData.setChatBotUrl(chatBotUrl);
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
