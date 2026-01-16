package com.tfb.aibank.chl.general.ot005.task;

import java.util.Locale;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ot001.task.service.PreLoginCache;
import com.tfb.aibank.chl.general.ot005.model.NGNOT005021Rq;
import com.tfb.aibank.chl.general.ot005.model.NGNOT005021Rs;
import com.tfb.aibank.chl.general.service.LoginService;

//@formatter:off
/**
* @(#)NGNOT005010Task.java 
* 
* <p>Description:設定新代號與新密碼頁</p>
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
public class NGNOT005021Task extends AbstractAIBankBaseTask<NGNOT005021Rq, NGNOT005021Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT005021Task.class);

    @Override
    public void validate(NGNOT005021Rq rqData) throws ActionException {

        PreLoginCache cache = getCache(LoginService.PRE_LOGIN_CACHE_KEY, PreLoginCache.class);

        if (cache == null) {
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
        
        populatePreLoginMBAccessLog(cache);

    }

    
    @Override
    public void handle(NGNOT005021Rq rqData, NGNOT005021Rs rsData) throws ActionException {
        logger.debug("==== NGNOT005021 start====");
 
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

    private void populatePreLoginMBAccessLog(PreLoginCache cache) {
        // 8438 登入前交易寫入 custid, companyKind
        this.populatePreLoginMBAccessLogByCustId(cache.getUid());
        this.populatePreLoginMBAccessLogByCompany(cache.getCompanyKindByLoginType());
        this.populatePreLoginMBAccessLogByUserId(cache.getUuid());        
    }

}
