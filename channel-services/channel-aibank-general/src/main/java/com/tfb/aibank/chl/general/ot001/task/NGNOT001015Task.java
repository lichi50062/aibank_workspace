package com.tfb.aibank.chl.general.ot001.task;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.biz.component.e2ee.E2EEUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001015Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001015Rs;

//@formatter:off
/**
* @(#)NGNOT001015Task.java 
* 

* <p>Description:重取</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20241125, JohnChang
*  <li>初版</li>
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on

@Component
@Scope("prototype")
public class NGNOT001015Task extends AbstractAIBankBaseTask<NGNOT001015Rq, NGNOT001015Rs> {

    @Override
    public void validate(NGNOT001015Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNOT001015Rq rqData, NGNOT001015Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001015 start====");

        rsData.setPassWithTime(E2EEUtils.getPassWithTime());
        logger.info("TimeFactor: NGNOT001015 {}", rsData.getPassWithTime());

    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }

}
