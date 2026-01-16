package com.tfb.aibank.chl.general.ot005.task;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.countryname.IpLocationAndCountryNameCacheManager;
import com.tfb.aibank.chl.general.ot001.task.service.LoginMailData;
import com.tfb.aibank.chl.general.ot001.task.service.LoginMailType;
import com.tfb.aibank.chl.general.ot005.model.NGNOT005014Rq;
import com.tfb.aibank.chl.general.ot005.model.NGNOT005014Rs;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.resource.dto.RetriveDeviceStatusResponse;
import com.tfb.aibank.chl.general.resource.dto.UnLoginEmailsResponse;
import com.tfb.aibank.chl.general.service.LoginService;

//@formatter:off
/**
* @(#)NGNOT005014Task.java 
* 
* <p>Description:生物辨識登入失敗通知</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20250527, Benson
*  <li>初版</li>
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on

@Component
@Scope("prototype")
public class NGNOT005014Task extends AbstractAIBankBaseTask<NGNOT005014Rq, NGNOT005014Rs> {

    @Autowired
    UserResource userResource;

    @Autowired
    LoginService loginService;

    @Autowired
    private IpLocationAndCountryNameCacheManager ipLocationAndCountryCacheManager;

    @Override
    public void validate(NGNOT005014Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNOT005014Rq rqData, NGNOT005014Rs rsData) throws ActionException {
 
        RetriveDeviceStatusResponse cache = getCache(LoginService.DEVICE_BINDING_CACHE_KEY, RetriveDeviceStatusResponse.class);

        UnLoginEmailsResponse response = userResource.getUnLoginEmails(cache.getCustId(), cache.getLoginType());

        LoginMailData loginMailData = loginService.getLoginMailData(LoginMailType.BIO_LOGIN_FAIL, getRequest().getClientIp(), cache.getMarketingName(), getLocale(), null, null, getCountryName());
        sendMail(cache.getCustId(), cache.getUidDup(), cache.getUserId(), 2, loginMailData.getSubject(), response.getEmails(), loginMailData.getParams());
    }

    protected void sendMail(String custId, String uidDup, String userId, Integer companyKind, String subject, List<String> mailTo, Map<String, String> params) {
        if (mailTo != null) {
            for (String email : mailTo) {
                sendTxnResultMail(custId, uidDup, userId, companyKind, subject, email, params);
            }
        }

    }
    private String getCountryName() {
        return ipLocationAndCountryCacheManager.getCountryNameByIpLocationAndLocale(this.getClientIp(), getLocale()).getCountryName();
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }

}
