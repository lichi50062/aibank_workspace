package com.tfb.aibank.chl.general.qu004.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.qu004.model.NGNQU004020Rq;
import com.tfb.aibank.chl.general.qu004.model.NGNQU004020Rs;
import com.tfb.aibank.chl.general.qu004.model.NGNQU004Input;
import com.tfb.aibank.chl.general.qu004.model.NGNQU004Output;
import com.tfb.aibank.chl.general.qu004.service.NGNQU004Service;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NGNQU004020Task.java
 * 
 * <p>Description:所有功能 020 功能首頁</p>
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
public class NGNQU004020Task extends AbstractAIBankBaseTask<NGNQU004020Rq, NGNQU004020Rs> {

    @Autowired
    @Qualifier("NGNQU004Service")
    private NGNQU004Service service;

    private NGNQU004Input dataInput = new NGNQU004Input();

    private NGNQU004Output dataOutput = new NGNQU004Output();

    @Override
    public void validate(NGNQU004020Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NGNQU004020Rq rqData, NGNQU004020Rs rsData) throws ActionException {

        AIBankUser loginUser = getLoginUser();

        dataInput.setLocale(getLocale());

        service.prepare020Datas(loginUser, dataInput, dataOutput, getAppVer(), getLocale(), getMenuCategory());

        rsData.setMenusForSearching(dataOutput.getMenusForSearching());
        rsData.setFaqTypeVos(dataOutput.getFaqTypeVos());
        rsData.setGuideBizVos(dataOutput.getGuideBizVos());
        rsData.setPopularKeywords(dataOutput.getPopularKeywords());

        if (logger.isDebugEnabled()) {
            logger.debug(IBUtils.attribute2Str(rsData));
        }
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
