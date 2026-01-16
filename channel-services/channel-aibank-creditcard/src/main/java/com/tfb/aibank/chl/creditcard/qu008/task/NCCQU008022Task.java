package com.tfb.aibank.chl.creditcard.qu008.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.qu008.cache.NCCQU008CacheData;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008022Rq;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008022Rs;
import com.tfb.aibank.chl.creditcard.qu008.service.NCCQU008Service;

// @formatter:off
/**
 * @(#)NCCQU008020Task.java
 * 
 * <p>Description:信用卡分期管理 020 分期注意事項頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/02, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCQU008022Task extends AbstractAIBankBaseTask<NCCQU008022Rq, NCCQU008022Rs> {

    @Autowired
    private NCCQU008Service service;

    @Override
    public void validate(NCCQU008022Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCQU008022Rq rqData, NCCQU008022Rs rsData) throws ActionException {
        NCCQU008CacheData cache = getCache(NCCQU008Service.CACHE_KEY, NCCQU008CacheData.class);
        if (cache == null) {
            throw ExceptionUtils.getActionException(CommonErrorCode.SESSION_EXPIRED);
        }
        // #1749 簽訂約定書後回寫簽訂狀態
        service.getAgreementFlag(getLoginUser(), cache, "5", "");
        if (Boolean.TRUE.equals(cache.getAgreementFlag())) {
            rsData.setTargetPagePath("040");
        }
        else {
            rsData.setTargetPagePath("021");
        }
        setCache(NCCQU008Service.CACHE_KEY, cache);
    }

}
