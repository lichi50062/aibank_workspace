package com.tfb.aibank.chl.general.ot005.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ot005.model.NGNOT005013Rq;
import com.tfb.aibank.chl.general.ot005.model.NGNOT005013Rs;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.resource.dto.RetriveDeviceStatusResponse;
import com.tfb.aibank.chl.general.resource.dto.UpdateMbDeviceInfoRequest;
import com.tfb.aibank.chl.general.service.LoginService;

//@formatter:off
/**
* @(#)NGNOT001013Task.java 
* 
* <p>Description:解除快登</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20230522, JohnChang
*  <li>初版</li>
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on

@Component
@Scope("prototype")
public class NGNOT005013Task extends AbstractAIBankBaseTask<NGNOT005013Rq, NGNOT005013Rs> {

    @Autowired
    UserResource userResource;

    @Override
    public void validate(NGNOT005013Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNOT005013Rq rqData, NGNOT005013Rs rsData) throws ActionException {

        RetriveDeviceStatusResponse cache = getCache(LoginService.DEVICE_BINDING_CACHE_KEY, RetriveDeviceStatusResponse.class);

        UpdateMbDeviceInfoRequest request = new UpdateMbDeviceInfoRequest();
        request.setCustId(cache.getCustId());
        request.setUidDup(cache.getUidDup());
        request.setUserId(cache.getUserId());
        request.setCompanyKind(cache.getCompanyKind());
        request.setDeviceUuid(getRequest().getDeviceIxd());
        request.setUpdateLoginFlag(true);
        request.setLoginFlag(0);
        userResource.updateMbDeviceInfo(request);
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }

}
