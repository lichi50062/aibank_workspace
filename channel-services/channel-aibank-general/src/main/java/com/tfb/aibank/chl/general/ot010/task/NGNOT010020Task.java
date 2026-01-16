package com.tfb.aibank.chl.general.ot010.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.i18n.I18NCacheManager;
import com.ibm.tw.ibmb.component.i18n.I18nModel;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.type.SessionKey;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ot010.model.NGNOT010020Rq;
import com.tfb.aibank.chl.general.ot010.model.NGNOT010020Rs;
import com.tfb.aibank.chl.general.service.OnboardingCache;

//@formatter:off
/**
* @(#)NGNOT010020Task.java
*
* <p>Description: Onboarding-無痛移轉</p>
*
* <p>Modify History:</p>
* <ol>v1, 2024/06/04, John Chang
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NGNOT010020Task extends AbstractAIBankBaseTask<NGNOT010020Rq, NGNOT010020Rs> {

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Autowired
    private I18NCacheManager i18NCacheManager;

    @Override
    public void validate(NGNOT010020Rq rqData) throws ActionException {
    }

    @Override
    public void handle(NGNOT010020Rq rqData, NGNOT010020Rs rsData) throws ActionException {

        // 無痛移轉開關說明
        String transferOnboarding = systemParamCacheManager.getValue("AIBANK", "ONBOARDING_TRANSFER_FLAG");

        // 開啟？
        if (StringUtils.equals(transferOnboarding, "Y")) {
            rsData.setTransferOnboarding(true);
        }

        I18nModel defaultNickNameModel = i18NCacheManager.getSingleResult(getLocale(), "ONBOARDING", "NICKNAME");
        if (defaultNickNameModel != null) {
            rsData.setDefaultNickName(defaultNickNameModel.getValue());
        }

        OnboardingCache cache = getFromSession(SessionKey.ONBOARDING_CACHE_KEY, OnboardingCache.class);
        if (cache != null)
            rsData.setOnboardingType(cache.getOnboardingType());
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
