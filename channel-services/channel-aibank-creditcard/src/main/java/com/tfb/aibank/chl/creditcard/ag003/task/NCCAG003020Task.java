package com.tfb.aibank.chl.creditcard.ag003.task;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.ag003.cache.NCCAG003CacheData;
import com.tfb.aibank.chl.creditcard.ag003.model.NCCAG003020Rq;
import com.tfb.aibank.chl.creditcard.ag003.model.NCCAG003020Rs;
import com.tfb.aibank.chl.creditcard.ag003.service.NCCAG003Service;

// @formatter:off
/**
 * @(#)NCCAG003020Task.java
 * 
 * <p>Description:信用卡email設定 020 結果頁</p>
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
public class NCCAG003020Task extends AbstractAIBankBaseTask<NCCAG003020Rq, NCCAG003020Rs> {

    @Override
    public void validate(NCCAG003020Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG003020Rq rqData, NCCAG003020Rs rsData) throws ActionException {
        NCCAG003CacheData cache = getCache(NCCAG003Service.NCCAG003_CACHE_KEY, NCCAG003CacheData.class);
        rsData.setTxnResult(cache.getTxnResult());
        rsData.setNewEmail(cache.getNewEmail());
    }

}
