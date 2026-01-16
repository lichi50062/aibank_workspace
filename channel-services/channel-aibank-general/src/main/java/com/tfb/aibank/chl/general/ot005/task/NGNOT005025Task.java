package com.tfb.aibank.chl.general.ot005.task;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.devicebinding.DeviceBindingService;
import com.tfb.aibank.chl.component.satisfaction.AibankServiceSettingCacheManager;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.general.ot005.model.NGNOT005025Rq;
import com.tfb.aibank.chl.general.ot005.model.NGNOT005025Rs;
import com.tfb.aibank.chl.general.resource.SecurityResource;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.service.LoginService;
import com.tfb.aibank.chl.session.resource.SessionResource;
import com.tfb.aibank.component.session.LocalSessionManager;

//@formatter:off
/**
* @(#)NGNOT005010Task.java 
* 
* <p>Description:登入首頁</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20250305, Benson
*  <li>初版</li>
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on

@Component
@Scope("prototype")
public class NGNOT005025Task extends AbstractAIBankBaseTask<NGNOT005025Rq, NGNOT005025Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT005025Task.class);


    @Override
    public void validate(NGNOT005025Rq rqData) throws ActionException {
    }

    @Override
    public void handle(NGNOT005025Rq rqData, NGNOT005025Rs rsData) throws ActionException {
        logger.debug("==== NGNOT005025 start====");
 
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
    /*
     * 只處理中文
     * @see com.ibm.tw.ibmb.task.AbstractBaseTask#getLocale()
     */
    @Override
    protected Locale getLocale() {
       return Locale.TRADITIONAL_CHINESE;
    }

}
