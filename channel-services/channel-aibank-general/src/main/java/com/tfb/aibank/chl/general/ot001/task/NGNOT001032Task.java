package com.tfb.aibank.chl.general.ot001.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001032Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001032Rs;
import com.tfb.aibank.chl.general.ot001.task.service.PatternLockCache;
import com.tfb.aibank.chl.general.service.LoginService;

//@formatter:off
/**
* @(#)NGNOT001032Task.java 
* 
* <p>Description:設定圖形頁</p>
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
public class NGNOT001032Task extends AbstractAIBankBaseTask<NGNOT001032Rq, NGNOT001032Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT001032Task.class);

    @Autowired
    LoginService loginHelper;

    @Override
    public void validate(NGNOT001032Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNOT001032Rq rqData, NGNOT001032Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001032 start====");

        saveContractLog(LoginService.FASTLOGIN_TERMS_REMARK_KEY, "031");

        PatternLockCache cache = new PatternLockCache();

        String oefficient = loginHelper.getCoefficient(getDeviceId());
        cache.setCoefficient(oefficient);
        setCache(LoginService.PATTERN_LOCK_CACHE_KEY, cache);
        rsData.setCoefficient(oefficient);
        rsData.setOnboardingType(getLoginUser().getOnboardingType());

        rsData.setMaskCustId(DataMaskUtil.maskCustId(this.getLoginUser().getCustId()));

    }

    private String getDeviceId() {
        return getRequest().getDeviceIxd();
    }

    protected String getMessage(String messageId) {
        return I18NUtils.getMessage(messageId);
    }

    protected String getMessage(String messageId, Object... params) {
        return I18NUtils.getMessage(messageId, params);
    }

}
