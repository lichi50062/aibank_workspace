package com.tfb.aibank.chl.general.qu003.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003013Rq;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003013Rs;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003Output;
import com.tfb.aibank.chl.general.qu003.service.NGNQU003Service;

// @formatter:off
/**
 * @(#)NGNQU003013Task.java
 * 
 * <p>Description:優惠 013  點擊數</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/14, 
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NGNQU003013Task extends AbstractAIBankBaseTask<NGNQU003013Rq, NGNQU003013Rs> {

    @Autowired
    private NGNQU003Service service;

    @Override
    public void validate(NGNQU003013Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NGNQU003013Rq rqData, NGNQU003013Rs rsData) throws ActionException {
        NGNQU003Output output = new NGNQU003Output();
        service.getPromotionClickMap(output);

        rsData.setPromotionClickCountMap(output.getPromotionClickCountMap());
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
