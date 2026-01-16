package com.tfb.aibank.chl.general.ot010.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.type.SessionKey;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ot010.model.NGNOT010010Rq;
import com.tfb.aibank.chl.general.ot010.model.NGNOT010010Rs;
import com.tfb.aibank.chl.general.service.OnboardingCache;
import com.tfb.aibank.common.type.OnboardingType;

//@formatter:off
/**
* @(#)NGNOT010010Task.java
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
public class NGNOT010010Task extends AbstractAIBankBaseTask<NGNOT010010Rq, NGNOT010010Rs> {

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Override
    public void validate(NGNOT010010Rq rqData) throws ActionException {
    }

    @Override
    public void handle(NGNOT010010Rq rqData, NGNOT010010Rs rsData) throws ActionException {

        OnboardingCache cache = new OnboardingCache();
        int onboardingType = 1;

        // 無痛移轉開關說明
        String transferOnboarding = systemParamCacheManager.getValue("AIBANK", "ONBOARDING_TRANSFER_FLAG");

        // 開啟？
        if (StringUtils.equals(transferOnboarding, "Y")) {
            rsData.setTransferOnboarding(true);
        }

        // 開啟 且有帶入 AccessToken
        if (StringUtils.isNotBlank(rqData.getAccessToken()) && rsData.isTransferOnboarding()) {
            onboardingType = OnboardingType.TRANSFER_FROM_MB.getCode();
            cache.setOnboardingType(onboardingType);
            cache.setAccessToken(rqData.getAccessToken());
            setToSession(SessionKey.ONBOARDING_CACHE_KEY, cache);
        }

        rsData.setOnboardingType(onboardingType);
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
