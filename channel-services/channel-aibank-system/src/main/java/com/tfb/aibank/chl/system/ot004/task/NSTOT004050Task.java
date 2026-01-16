package com.tfb.aibank.chl.system.ot004.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.system.ot004.model.AddWidgetRecordInput;
import com.tfb.aibank.chl.system.ot004.model.AddWidgetRecordOutput;
import com.tfb.aibank.chl.system.ot004.model.NSTOT004050Rq;
import com.tfb.aibank.chl.system.ot004.model.NSTOT004050Rs;
import com.tfb.aibank.chl.system.ot004.service.NSTOT004Service;

//@formatter:off
/**
* @(#)NSTOT004050Rq.java
* 
* <p>Description: 050 留存紀錄</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/06/25, John
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NSTOT004050Task extends AbstractAIBankBaseTask<NSTOT004050Rq, NSTOT004050Rs> {

    @Autowired
    private NSTOT004Service service;

    @Override
    public void validate(NSTOT004050Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NSTOT004050Rq rqData, NSTOT004050Rs rsData) throws ActionException {
        if (!StringUtils.isBlank(rqData.getMemo())) {
            logger.info("{}==>{}", rqData.getTag(), rqData.getMemo());
            AddWidgetRecordInput input = new AddWidgetRecordInput();
            AddWidgetRecordOutput output = new AddWidgetRecordOutput();
            input.setMemo(rqData.getMemo());
            input.setRequest(getRequest());
            input.setAibankUser(getLoginUser());
            service.addWidgetRecord(input, output);
        }
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }

}
