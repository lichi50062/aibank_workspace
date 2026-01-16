package com.tfb.aibank.chl.creditcard.qu008.task;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008020Rq;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008020Rs;

// @formatter:off
/**
 * @(#)NCCQU008020Task.java
 * 
 * <p>Description:信用卡分期管理 020 分期注意事項頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/02, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCQU008020Task extends AbstractAIBankBaseTask<NCCQU008020Rq, NCCQU008020Rs> {

    @Override
    public void validate(NCCQU008020Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCQU008020Rq rqData, NCCQU008020Rs rsData) throws ActionException {

    }

}
