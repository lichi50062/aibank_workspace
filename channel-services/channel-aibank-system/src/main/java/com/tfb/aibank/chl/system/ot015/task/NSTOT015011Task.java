/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.chl.system.ot015.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.system.ot015.model.NSTOT015011Rq;
import com.tfb.aibank.chl.system.ot015.model.NSTOT015011Rs;
import com.tfb.aibank.chl.system.ot015.model.NSTOT015Output;
import com.tfb.aibank.chl.system.ot015.model.NSTOT015User;
import com.tfb.aibank.chl.system.ot015.service.NSTOT015Service;

// @formatter:off
/**
 * @(#)NSTOT015011Task.java
 * 
 * <p>Description:廣告版位 011 用戶觀看廣告紀錄</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/14, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NSTOT015011Task extends AbstractAIBankBaseTask<NSTOT015011Rq, NSTOT015011Rs> {

    @Autowired
    private NSTOT015Service service;

    private NSTOT015Output output = new NSTOT015Output();

    @Override
    public void validate(NSTOT015011Rq rqData) throws ActionException {
        if (rqData.getHomepageCard() == null) {
            throwActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
    }

    @Override
    public void handle(NSTOT015011Rq rqData, NSTOT015011Rs rsData) throws ActionException {
        NSTOT015User user = null;
        if (isLoggedIn()) {
            user = new NSTOT015User(getLoginUser());
        }
        else {
            // 免登
            user = new NSTOT015User(getRequest().getDeviceIxd());
        }

        service.saveViewLog(user, rqData.getHomepageCard());
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
