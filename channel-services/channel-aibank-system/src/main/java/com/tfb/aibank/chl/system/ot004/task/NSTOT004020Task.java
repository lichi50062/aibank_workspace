package com.tfb.aibank.chl.system.ot004.task;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.system.ot004.model.NSTOT004020Rq;
import com.tfb.aibank.chl.system.ot004.model.NSTOT004020Rs;

// @formatter:off
/**
 * @(#)NSTOT004020Task.java
 * 
 * <p>Description:020 ping pong，延長後端 timeout 的機制 </p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NSTOT004020Task extends AbstractAIBankBaseTask<NSTOT004020Rq, NSTOT004020Rs> {

    @Override
    public void validate(NSTOT004020Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NSTOT004020Rq rqData, NSTOT004020Rs rsData) throws ActionException {
        touchCacheObjects();
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }

}
