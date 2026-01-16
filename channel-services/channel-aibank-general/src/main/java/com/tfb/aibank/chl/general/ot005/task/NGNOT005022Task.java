package com.tfb.aibank.chl.general.ot005.task;

import java.util.Locale;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ot001.task.service.PreLoginCache;
import com.tfb.aibank.chl.general.ot005.model.NGNOT005022Rq;
import com.tfb.aibank.chl.general.ot005.model.NGNOT005022Rs;
import com.tfb.aibank.chl.general.service.LoginService;

//@formatter:off
/**
* @(#)NGNOT005010Task.java 
* 
* <p>Description:設定新密碼頁</p>
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
public class NGNOT005022Task extends AbstractAIBankBaseTask<NGNOT005022Rq, NGNOT005022Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT005022Task.class);


    @Override
    public void validate(NGNOT005022Rq rqData) throws ActionException {
        PreLoginCache cache = getCache(LoginService.PRE_LOGIN_CACHE_KEY, PreLoginCache.class);

        if (cache != null) {
            logger.debug("uuid: {}", cache.getUuid());
            populatePreLoginMBAccessLog(cache);
         }
        

    }

    @Override
    public void handle(NGNOT005022Rq rqData, NGNOT005022Rs rsData) throws ActionException {
        logger.debug("==== NGNOT005022 start====");
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
