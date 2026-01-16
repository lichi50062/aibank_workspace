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
import com.tfb.aibank.chl.system.ot015.model.NSTOT015012Rq;
import com.tfb.aibank.chl.system.ot015.model.NSTOT015012Rs;
import com.tfb.aibank.chl.system.ot015.service.NSTOT015Service;

// @formatter:off
/**
 * @(#)NSTOT015012Task.java
 * 
 * <p>Description:廣告版位 012 廣告成效記錄</p>
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
public class NSTOT015012Task extends AbstractAIBankBaseTask<NSTOT015012Rq, NSTOT015012Rs> {

    @Autowired
    private NSTOT015Service service;

    @Override
    public void validate(NSTOT015012Rq rqData) throws ActionException {
        if (rqData.getHomepageCard() == null) {
            throwActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
    }

    @Override
    public void handle(NSTOT015012Rq rqData, NSTOT015012Rs rsData) throws ActionException {
        service.saveClickLog(rqData.getHomepageCard());
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
