package com.tfb.aibank.chl.creditcard.ag012.task;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.ag012.cache.NCCAG012CacheData;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012060Rq;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012060Rs;
import com.tfb.aibank.chl.creditcard.ag012.service.NCCAG012Service;

// @formatter:off
/**
 * @(#)NCCAG012060Task.java
 * 
 * <p>Description:信用卡交易設定 060 失敗結果頁</p>
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
public class NCCAG012060Task extends AbstractAIBankBaseTask<NCCAG012060Rq, NCCAG012060Rs> {

    @Override
    public void validate(NCCAG012060Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG012060Rq rqData, NCCAG012060Rs rsData) throws ActionException {
        NCCAG012CacheData cache = getCache(NCCAG012Service.NCCAG012_CACHE_KEY, NCCAG012CacheData.class);
        rsData.setCardInfo(cache.getCardInfoForTx());
        rsData.setTxnResult(cache.getTxnResult());
        rsData.setCardKeyForTxn(cache.getCardKeyForTxn());
        setCache(NCCAG012Service.NCCAG012_CACHE_KEY, cache);
    }

}
