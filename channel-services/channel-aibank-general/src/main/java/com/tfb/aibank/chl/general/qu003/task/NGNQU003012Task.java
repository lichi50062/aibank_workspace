package com.tfb.aibank.chl.general.qu003.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003012Rq;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003012Rs;
import com.tfb.aibank.chl.general.qu003.service.NGNQU003Service;

// @formatter:off
/**
 * @(#)NGNQU003012Task.java
 * 
 * <p>Description:優惠 012  增加點擊數</p>
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
public class NGNQU003012Task extends AbstractAIBankBaseTask<NGNQU003012Rq, NGNQU003012Rs> {

    @Autowired
    private NGNQU003Service service;

    @Override
    public void validate(NGNQU003012Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NGNQU003012Rq rqData, NGNQU003012Rs rsData) throws ActionException {
        service.addPromotionClick(rqData.getPromotionKey());
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
