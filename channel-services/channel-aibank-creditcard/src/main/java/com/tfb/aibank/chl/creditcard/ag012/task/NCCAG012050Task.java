package com.tfb.aibank.chl.creditcard.ag012.task;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.ag012.cache.NCCAG012CacheData;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012050Rq;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012050Rs;
import com.tfb.aibank.chl.creditcard.ag012.service.NCCAG012Service;

// @formatter:off
/**
 * @(#)NCCAG012050Task.java
 * 
 * <p>Description:信用卡交易設定 050 結果頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/03/11, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCAG012050Task extends AbstractAIBankBaseTask<NCCAG012050Rq, NCCAG012050Rs> {

    @Override
    public void validate(NCCAG012050Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG012050Rq rqData, NCCAG012050Rs rsData) throws ActionException {
        NCCAG012CacheData cache = getCache(NCCAG012Service.NCCAG012_CACHE_KEY, NCCAG012CacheData.class);
        rsData.setCardInfo(cache.getCardInfoForTx());
        rsData.setTxnResult(cache.getTxnResult());
        rsData.setCardKeyForTxn(cache.getCardKeyForTxn());
        setCache(NCCAG012Service.NCCAG012_CACHE_KEY, cache);
    }

}
