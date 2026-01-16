package com.tfb.aibank.chl.general.qu003.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003070Rq;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003070Rs;
import com.tfb.aibank.chl.general.qu003.service.NGNQU003Service;

// @formatter:off
/**
 * @(#)NGNQU003070Task.java
 * 
 * <p>Description:優惠 070 各會員等級權益頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/14, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NGNQU003070Task extends AbstractAIBankBaseTask<NGNQU003070Rq, NGNQU003070Rs> {

    @Autowired
    private NGNQU003Service service;

    @Override
    public void validate(NGNQU003070Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NGNQU003070Rq rqData, NGNQU003070Rs rsData) throws ActionException {

    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
