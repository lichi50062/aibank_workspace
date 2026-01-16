package com.tfb.aibank.chl.general.ot001.task;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.countryname.IpLocationAndCountryNameCacheManager;
import com.tfb.aibank.chl.component.countryname.model.IpLocationAndCountryName;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001014Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001014Rs;
import com.tfb.aibank.chl.general.ot001.task.service.LoginMailData;
import com.tfb.aibank.chl.general.ot001.task.service.LoginMailType;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.resource.dto.RetriveDeviceStatusResponse;
import com.tfb.aibank.chl.general.resource.dto.UnLoginEmailsResponse;
import com.tfb.aibank.chl.general.service.LoginService;

//@formatter:off
/**
* @(#)NGNOT001014Task.java 
* 
* <p>Description:生物辨識登入失敗通知</p>
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
public class NGNOT001014Task extends AbstractAIBankBaseTask<NGNOT001014Rq, NGNOT001014Rs> {

    @Autowired
    UserResource userResource;

    @Autowired
    LoginService loginService;

    @Autowired
    private IpLocationAndCountryNameCacheManager ipLocationAndCountryCacheManager;
    

    @Override
    public void validate(NGNOT001014Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNOT001014Rq rqData, NGNOT001014Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001014 start====");

        RetriveDeviceStatusResponse cache = getCache(LoginService.DEVICE_BINDING_CACHE_KEY, RetriveDeviceStatusResponse.class);

        UnLoginEmailsResponse response = userResource.getUnLoginEmails(cache.getCustId(), cache.getLoginType());
        
        String clientIp = getRequest().getClientIp();
        IpLocationAndCountryName countryName = ipLocationAndCountryCacheManager.getCountryNameByIpLocationAndLocale(clientIp, getLocale());
        
        LoginMailData loginMailData = loginService.getLoginMailData(LoginMailType.BIO_LOGIN_FAIL, clientIp, cache.getMarketingName(), getLocale(), null, null, countryName.getCountryName());
        sendMail(cache.getCustId(), cache.getUidDup(), cache.getUserId(), 2, loginMailData.getSubject(), response.getEmails(), loginMailData.getParams());
    }

    protected void sendMail(String custId, String uidDup, String userId, Integer companyKind, String subject, List<String> mailTo, Map<String, String> params) {
        if (mailTo != null) {
            for (String email : mailTo) {
                sendTxnResultMail(custId, uidDup, userId, companyKind, subject, email, params);
            }
        }

    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }

}
