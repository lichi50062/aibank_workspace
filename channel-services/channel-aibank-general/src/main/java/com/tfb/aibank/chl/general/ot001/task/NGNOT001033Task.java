package com.tfb.aibank.chl.general.ot001.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.error.ErrorCode;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001033Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001033Rs;
import com.tfb.aibank.chl.general.ot001.task.service.PatternLockCache;
import com.tfb.aibank.chl.general.resource.SecurityResource;
import com.tfb.aibank.chl.general.service.LoginService;
import com.tfb.aibank.chl.session.AIBankUser;

//@formatter:off
/**
* @(#)NGNOT001033Task.java 
* 
* <p>Description:確認圖形頁</p>
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
public class NGNOT001033Task extends AbstractAIBankBaseTask<NGNOT001033Rq, NGNOT001033Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT001033Task.class);

    @Autowired
    SecurityResource securityResource;

    @Autowired
    LoginService loginHelper;

    @Override
    public void validate(NGNOT001033Rq rqData) throws ActionException {

        if (StringUtils.isBlank(rqData.getPinBlock())) {
            throw new ActionException(ErrorCode.INBOUND_DATA_EMPTY);
        }
    }

    @Override
    public void handle(NGNOT001033Rq rqData, NGNOT001033Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001033 start====");

        AIBankUser aiBankuser = getLoginUser();

        String pinBlock = loginHelper.encodewithSecret(aiBankuser.getCustId(), aiBankuser.getUserId(), rqData.getPinBlock());
        PatternLockCache cache = getCache(LoginService.PATTERN_LOCK_CACHE_KEY, PatternLockCache.class);

        cache.setPinBlock(pinBlock);
        setCache(LoginService.PATTERN_LOCK_CACHE_KEY, cache);

        rsData.setCoefficient(cache.getCoefficient());
        rsData.setOnboardingType(aiBankuser.getOnboardingType());

        rsData.setMaskCustId(DataMaskUtil.maskCustId(this.getLoginUser().getCustId()));

    }

    protected String getMessage(String messageId) {
        return I18NUtils.getMessage(messageId);
    }

    protected String getMessage(String messageId, Object... params) {
        return I18NUtils.getMessage(messageId, params);
    }
}
