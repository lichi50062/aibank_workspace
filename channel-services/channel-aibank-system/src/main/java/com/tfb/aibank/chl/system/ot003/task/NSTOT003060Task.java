package com.tfb.aibank.chl.system.ot003.task;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.system.ot003.model.NSTOT003060Rq;
import com.tfb.aibank.chl.system.ot003.model.NSTOT003060Rs;

//@formatter:off
/**
* @(#)NSTOT003060Rq.java
* 
* <p>Description:安控驗證共用 - 檢核是否可進行安控 - TASK</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/07/05, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NSTOT003060Task extends AbstractAIBankBaseTask<NSTOT003060Rq, NSTOT003060Rs> {

    @Override
    public void validate(NSTOT003060Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NSTOT003060Rq rqData, NSTOT003060Rs rsData) throws ActionException {

        checkTxSecurity(rqData.getTaskId());

    }

}
