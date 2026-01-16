package com.tfb.aibank.chl.general.ag004.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ag004.model.NGNAG004011Rq;
import com.tfb.aibank.chl.general.ag004.model.NGNAG004011Rs;
import com.tfb.aibank.chl.general.ag004.service.NGNAG004Service;

// @formatter:off
/**
 * @(#)NGNAG004011Task.java
 * 
 * <p>Description:匯率設定 011 儲存action</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/14, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NGNAG004011Task extends AbstractAIBankBaseTask<NGNAG004011Rq, NGNAG004011Rs> {

    @Autowired
    private NGNAG004Service service;

    @Override
    public void validate(NGNAG004011Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NGNAG004011Rq rqData, NGNAG004011Rs rsData) throws ActionException {

        service.doRateCardUser(getLoginUser(), rqData.getCurrencyInHomeArea(), getLocale());
    }

}
