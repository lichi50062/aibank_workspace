package com.tfb.aibank.chl.creditcard.ag003.task;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.ag003.model.NCCAG003015Rq;
import com.tfb.aibank.chl.creditcard.ag003.model.NCCAG003015Rs;

// @formatter:off
/**
 * @(#)NCCAG003015Task.java
 * 
 * <p>Description:信用卡email 安控驗證 015 action</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCAG003015Task extends AbstractAIBankBaseTask<NCCAG003015Rq, NCCAG003015Rs> {

    @Override
    public void validate(NCCAG003015Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NCCAG003015Rq rqData, NCCAG003015Rs rsData) throws ActionException {
        // 重置交易安控
        super.resetTxSecurity();
        // 啟動交易安控驗證
        super.initTxSecurity();
    }

}
