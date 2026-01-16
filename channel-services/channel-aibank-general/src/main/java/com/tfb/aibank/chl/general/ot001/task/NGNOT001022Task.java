package com.tfb.aibank.chl.general.ot001.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.nimbusds.oauth2.sdk.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001022Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001022Rs;
import com.tfb.aibank.chl.general.ot001.task.service.PreLoginCache;
import com.tfb.aibank.chl.general.service.LoginService;

//@formatter:off
/**
* @(#)NGNOT001022Task.java 
* 
* <p>Description:設定新密碼頁</p>
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
public class NGNOT001022Task extends AbstractAIBankBaseTask<NGNOT001022Rq, NGNOT001022Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT001022Task.class);

    @Override
    public void validate(NGNOT001022Rq rqData) throws ActionException {
    }

    @Override
    public void handle(NGNOT001022Rq rqData, NGNOT001022Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001022 start====");
        
        PreLoginCache cache = getCache(LoginService.PRE_LOGIN_CACHE_KEY, PreLoginCache.class);

        if (cache != null) {
            logger.debug("uuid: {}", cache.getUuid());
            rsData.setUuid(cache.getUuid());
        }

        populatePreLoginMBAccessLog(cache);
    }

    // 8438 登入前交易寫入 custid, companyKind
    private void populatePreLoginMBAccessLog(PreLoginCache cache) {
        this.populatePreLoginMBAccessLogByCustId(cache.getUid());
        this.populatePreLoginMBAccessLogByCompany(cache.getCompanyKindByLoginType());
        this.populatePreLoginMBAccessLogByUserId(cache.getUuid());
    }

    protected String getMessage(String messageId) {
        return I18NUtils.getMessage(messageId);
    }

    protected String getMessage(String messageId, Object... params) {
        return I18NUtils.getMessage(messageId, params);
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
