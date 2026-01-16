package com.tfb.aibank.chl.general.ot999.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.security.otp.OtpAuthService;
import com.tfb.aibank.chl.general.ot999.model.NGNOT999050Rq;
import com.tfb.aibank.chl.general.ot999.model.NGNOT999050Rs;
import com.tfb.aibank.chl.general.ot999.task.service.NGNOT999Cache;
import com.tfb.aibank.chl.general.ot999.task.service.NGNOT999Service;
import com.tfb.aibank.chl.type.OtpStatusType;
import com.tfb.aibank.common.type.SsoStatusType;

//@formatter:off
/**
* @(#)NGNOT999050Task.java 
* 
* <p>Description:快速身份認證</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 2024/01/17, JohnChang
*  <li>初版</li6
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on

@Component
@Scope("prototype")
public class NGNOT999050Task extends AbstractAIBankBaseTask<NGNOT999050Rq, NGNOT999050Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT999050Task.class);

    @Autowired
    private OtpAuthService otpAuthService;

    @Autowired
    private NGNOT999Service service;

    @Override
    public void validate(NGNOT999050Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNOT999050Rq rqData, NGNOT999050Rs rsData) throws ActionException {

        NGNOT999Cache cache = getCache(NGNOT999Service.FAST_VALIDATE_CACHE_KEY, NGNOT999Cache.class);
        if (cache == null) {
            service.generateCallBackUrl(rsData,SsoStatusType.UNKNOWN.getStatusCode(), null, "", "O", "0", "");
            return;
        }

        if (StringUtils.equals(OtpStatusType.VERIFIED_OK.getCode(), cache.getKeepData().getModel().getStatus())) {
            service.generateCallBackUrl(rsData, SsoStatusType.SUCCESS.getStatusCode(), cache.getToken(), cache.getRqData().getPlatformParams(), cache.getGetFastValidateUserResponse().getOpenType(), cache.getGetFastValidateUserResponse().getModuleType(), cache.getGetFastValidateUserResponse().getModuleParam());
            return;
        }
        else if (StringUtils.equals(OtpStatusType.INIT_OK.getCode(), cache.getKeepData().getModel().getStatus())) {
            service.generateCallBackUrl(rsData, SsoStatusType.FAST_LOGIN_OTP_CANCEL.getStatusCode(), null, cache.getRqData().getPlatformParams(), cache.getGetFastValidateUserResponse().getOpenType(), cache.getGetFastValidateUserResponse().getModuleType(), cache.getGetFastValidateUserResponse().getModuleParam());
            return;
        }
        else {
            service.generateCallBackUrl(rsData, SsoStatusType.FAST_LOGIN_OTP_FAIL.getStatusCode(), null, cache.getRqData().getPlatformParams(), cache.getGetFastValidateUserResponse().getOpenType(), cache.getGetFastValidateUserResponse().getModuleType(), cache.getGetFastValidateUserResponse().getModuleParam());
            return;
        }

    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
