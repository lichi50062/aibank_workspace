package com.tfb.aibank.chl.system.ot010.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.system.ot010.model.NSTOT010030Rq;
import com.tfb.aibank.chl.system.ot010.model.NSTOT010030Rs;
import com.tfb.aibank.chl.system.resource.NotificationResource;
import com.tfb.aibank.chl.system.resource.dto.UpdateDeviceAuthTranRequest;

//@formatter:off
/**
* @(#)NSTOT010030Task.java
* 
* <p>Description:提高非約轉限額</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/04/12, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NSTOT010030Task extends AbstractAIBankBaseTask<NSTOT010030Rq, NSTOT010030Rs> {

    @Autowired
    private NotificationResource notificationResource;

    @Override
    public void validate(NSTOT010030Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NSTOT010030Rq rqData, NSTOT010030Rs rsData) throws ActionException {

        UpdateDeviceAuthTranRequest request = new UpdateDeviceAuthTranRequest();
        request.setTxKey(rqData.getTxKxy());
        request.setStatus(rqData.getStatus());
        request.setDeviceId(getRequest().getDeviceIxd());
        notificationResource.updateDeviceAuthTran(request);
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
