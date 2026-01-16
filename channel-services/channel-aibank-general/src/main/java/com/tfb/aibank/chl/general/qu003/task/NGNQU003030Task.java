package com.tfb.aibank.chl.general.qu003.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003030Rq;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003030Rs;
import com.tfb.aibank.chl.general.qu003.service.NGNQU003Service;

// @formatter:off
/**
 * @(#)NGNQU003030Task.java
 * 
 * <p>Description:優惠 030 所有優惠詳細頁</p>
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
public class NGNQU003030Task extends AbstractAIBankBaseTask<NGNQU003030Rq, NGNQU003030Rs> {

    @Autowired
    private NGNQU003Service service;

    @Override
    public void validate(NGNQU003030Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NGNQU003030Rq rqData, NGNQU003030Rs rsData) throws ActionException {

    }

}
