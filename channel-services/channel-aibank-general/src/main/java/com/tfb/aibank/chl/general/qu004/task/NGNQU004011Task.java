package com.tfb.aibank.chl.general.qu004.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.qu004.model.NGNQU004011Rq;
import com.tfb.aibank.chl.general.qu004.model.NGNQU004011Rs;
import com.tfb.aibank.chl.general.qu004.model.NGNQU004Input;
import com.tfb.aibank.chl.general.qu004.model.NGNQU004Output;
import com.tfb.aibank.chl.general.qu004.service.NGNQU004Service;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NGNQU004011Task.java
 * 
 * <p>Description:所有功能 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/04, MartyP
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NGNQU004011Task extends AbstractAIBankBaseTask<NGNQU004011Rq, NGNQU004011Rs> {

    @Autowired
    @Qualifier("NGNQU004Service")
    private NGNQU004Service service;

    private final NGNQU004Input dataInput = new NGNQU004Input();
    private final NGNQU004Output dataOutput = new NGNQU004Output();

    @Override
    public void validate(NGNQU004011Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NGNQU004011Rq rqData, NGNQU004011Rs rsData) throws ActionException {

        dataInput.setLocale(getLocale());
        dataInput.setClientRequest(getRequest());

        if (isLoggedIn()) {
            AIBankUser loginUser = getLoginUser();
            rsData.setAvatar(loginUser.getUserData().getAvatar());
            rsData.setNickName(StringUtils.toHalfChar(StringUtils.defaultString(getLoginUser().getUserData().getNickName())));
            service.userAccountSecurityCheck(getLoginUser(), dataInput, dataOutput);
            rsData.setSecurityCheckMap(dataOutput.getSecurityCheckMap());
        }
        service.getNewFunctionsIntro(dataInput, dataOutput);
        rsData.setNewFunctionIntros(dataOutput.getNewFunctionIntros());

    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }

}
