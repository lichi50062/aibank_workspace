package com.tfb.aibank.chl.system.ot004.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.system.ot004.model.NSTOT004060Rq;
import com.tfb.aibank.chl.system.ot004.model.NSTOT004060Rs;
import com.tfb.aibank.chl.system.resource.UserResource;

//@formatter:off
/**
* @(#)NSTOT004060Rq.java
* 
* <p>Description: 060 更新 Push Token</p>
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
public class NSTOT004060Task extends AbstractAIBankBaseTask<NSTOT004060Rq, NSTOT004060Rs> {

    @Autowired
    private UserResource uerResource;

    @Override
    public void validate(NSTOT004060Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NSTOT004060Rq rqData, NSTOT004060Rs rsData) throws ActionException {

        Boolean isUpdateSuccess = uerResource.updateDevicePushStatus(getRequest().getDeviceIxd(), rqData.getPushToken());
        if (Boolean.FALSE.equals(isUpdateSuccess)) {
            logger.info("更新PushToken失敗");
        }
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }

}
