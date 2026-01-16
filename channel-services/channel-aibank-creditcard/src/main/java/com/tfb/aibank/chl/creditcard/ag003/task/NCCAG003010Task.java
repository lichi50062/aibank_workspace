package com.tfb.aibank.chl.creditcard.ag003.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.ag003.cache.NCCAG003CacheData;
import com.tfb.aibank.chl.creditcard.ag003.model.NCCAG003010Rq;
import com.tfb.aibank.chl.creditcard.ag003.model.NCCAG003010Rs;
import com.tfb.aibank.chl.creditcard.ag003.service.NCCAG003Service;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.creditcard.resource.dto.CE6210RResponse;

// @formatter:off
/**
 * @(#)NCCAG003010Task.java
 * 
 * <p>Description:信用卡email設定 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCAG003010Task extends AbstractAIBankBaseTask<NCCAG003010Rq, NCCAG003010Rs> {
    @Autowired
    private UserDataCacheService creditcardService;

    @Autowired
    private NCCAG003Service service;

    @Override
    public void validate(NCCAG003010Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG003010Rq rqData, NCCAG003010Rs rsData) throws ActionException {

        // 是否正卡人
        boolean isPrimaryCard = creditcardService.isPrimaryCard(getLoginUser(), getLocale());
        if (!isPrimaryCard) {
            throw ExceptionUtils.getActionException(ErrorCode.PRIMARY_CARDHOLDER_ONLY);
        }

        // 是否為特殊戶或未有信用卡 不是會報錯
        creditcardService.validateCreditCardStatus(getLoginUser());

        // 取信用卡email
        CE6210RResponse emailRs = new CE6210RResponse();
        service.getCardUserEmail(getLoginUser(), emailRs);
        rsData.setOriginEmail(emailRs.geteMail());

        NCCAG003CacheData cache = new NCCAG003CacheData();
        cache.setOriginEmail(emailRs.geteMail());
        cache.setIsHasOriginEmail(StringUtils.isNotBlank(emailRs.geteMail()));
        setCache(NCCAG003Service.NCCAG003_CACHE_KEY, cache);

        // 啟動Email-OTP交易安控驗證
        initEmailOtpTxSecurity(emailRs.geteMail());
    }

}
