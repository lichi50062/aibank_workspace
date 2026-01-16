package com.tfb.aibank.chl.creditcard.qu008.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008012Rq;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008012Rs;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008Output;
import com.tfb.aibank.chl.creditcard.qu008.service.NCCQU008Service;

// @formatter:off
/**
 * @(#)NCCQU008012Task.java
 * 
 * <p>Description:信用卡分期管理 012 每月預估金額</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/02/07, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCQU008012Task extends AbstractAIBankBaseTask<NCCQU008012Rq, NCCQU008012Rs> {

    @Autowired
    private NCCQU008Service service;

    NCCQU008Output output = new NCCQU008Output();

    @Override
    public void validate(NCCQU008012Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCQU008012Rq rqData, NCCQU008012Rs rsData) throws ActionException {
        service.getMonthlyEstimateAmt(getLoginUser().getCustId(), output);
        rsData.setMonthlyEstimate(output.getMonthlyEstimate());
    }

}
