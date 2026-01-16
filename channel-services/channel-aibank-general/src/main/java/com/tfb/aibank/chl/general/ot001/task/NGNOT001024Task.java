package com.tfb.aibank.chl.general.ot001.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001024Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001024Rs;
import com.tfb.aibank.chl.general.ot001.task.service.PreLoginCache;
import com.tfb.aibank.chl.general.resource.SecurityResource;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.service.LoginService;

//@formatter:off
/**
* @(#)NGNOT001024Task.java 
* 
* <p>Description:設定新使用者代碼頁</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20240605, JohnChang
*  <li>初版</li6
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on

@Component
@Scope("prototype")
public class NGNOT001024Task extends AbstractAIBankBaseTask<NGNOT001024Rq, NGNOT001024Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT001024Task.class);

    @Autowired
    LoginService loginHelper;

    @Autowired
    SecurityResource securityResource;

    @Autowired
    UserResource userResource;

    @Override
    public void validate(NGNOT001024Rq rqData) throws ActionException {

        PreLoginCache cache = getCache(LoginService.PRE_LOGIN_CACHE_KEY, PreLoginCache.class);

        if (cache == null) {
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }

        populatePreLoginMBAccessLog(cache);
    }

    // 8438 登入前交易寫入 custid, companyKind
    private void populatePreLoginMBAccessLog(PreLoginCache cache) {
        this.populatePreLoginMBAccessLogByCustId(cache.getUid());
        this.populatePreLoginMBAccessLogByCompany(cache.getCompanyKindByLoginType());
        this.populatePreLoginMBAccessLogByUserId(cache.getUuid());
    }

    @Override
    public void handle(NGNOT001024Rq rqData, NGNOT001024Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001024 start====");

    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
