package com.tfb.aibank.chl.general.ot005.task;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.devicebinding.DeviceBindingService;
import com.tfb.aibank.chl.component.satisfaction.AibankServiceSettingCacheManager;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.general.ot001.task.service.LoginMailData;
import com.tfb.aibank.chl.general.ot001.task.service.LoginMailType;
import com.tfb.aibank.chl.general.ot001.task.service.PreLoginCache;
import com.tfb.aibank.chl.general.ot005.model.NGNOT005023Rq;
import com.tfb.aibank.chl.general.ot005.model.NGNOT005023Rs;
import com.tfb.aibank.chl.general.resource.SecurityResource;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.resource.dto.ChangeUuidAndPinBlockRequest;
import com.tfb.aibank.chl.general.resource.dto.ChangeUuidAndPinBlockResponse;
import com.tfb.aibank.chl.general.service.LoginService;
import com.tfb.aibank.chl.session.resource.SessionResource;
import com.tfb.aibank.common.type.AtferLoginJobType;
import com.tfb.aibank.common.type.ChangRecordItemType;
import com.tfb.aibank.component.session.LocalSessionManager;

//@formatter:off
/**
* @(#)NGNOT005023Task.java 
* 
* <p>Description:密變變更執行</p>
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
public class NGNOT005023Task extends AbstractAIBankBaseTask<NGNOT005023Rq, NGNOT005023Rs> {

    
    private static final IBLog logger = IBLog.getLog(NGNOT005023Task.class);
    
 

    @Override
    public void validate(NGNOT005023Rq rqData) throws ActionException {
        PreLoginCache cache = getCache(LoginService.PRE_LOGIN_CACHE_KEY, PreLoginCache.class);

        if (cache != null) {
            populatePreLoginMBAccessLog(cache);
        }
    }

    @Override
    public void handle(NGNOT005023Rq rqData, NGNOT005023Rs rsData) throws ActionException { 
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

    
    // 8438 登入前交易寫入 custid, companyKind
    private void populatePreLoginMBAccessLog(PreLoginCache cache) {
        this.populatePreLoginMBAccessLogByCustId(cache.getUid());
        this.populatePreLoginMBAccessLogByCompany(cache.getCompanyKindByLoginType());
        this.populatePreLoginMBAccessLogByUserId(cache.getUuid());
    }

}
