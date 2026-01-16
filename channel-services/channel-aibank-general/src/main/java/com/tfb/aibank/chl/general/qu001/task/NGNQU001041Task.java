package com.tfb.aibank.chl.general.qu001.task;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.qu001.model.DataInput;
import com.tfb.aibank.chl.general.qu001.model.DataOutput;
import com.tfb.aibank.chl.general.qu001.model.NGNQU001041Rq;
import com.tfb.aibank.chl.general.qu001.model.NGNQU001041Rs;
import com.tfb.aibank.chl.general.qu001.service.NGNQU001Service;
import com.tfb.aibank.chl.session.AIBankUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// @formatter:off
/**
 * @(#)NGNQU001041Task.java
 *
 * <p>Description: 首頁-常用功能更新</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/05/22, MartyPan
 *  <li>常用功能更新</li>
 * </ol>
 */
//@formatter:on

@Component
@Scope("prototype")
public class NGNQU001041Task extends AbstractAIBankBaseTask<NGNQU001041Rq, NGNQU001041Rs> {

    @Autowired
    @Qualifier("NGNQU001Service")
    private NGNQU001Service service;


    @Override
    public void validate(NGNQU001041Rq rqData) throws ActionException {
    }

    @Override
    public void handle(NGNQU001041Rq rqData, NGNQU001041Rs rsData) throws ActionException {

        DataInput input = new DataInput();
        input.setUsualTasks(rqData.getUsualTasks());

        AIBankUser aiBankUser = getLoginUser();

        service.updateUsualTasks(aiBankUser, input);

    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
