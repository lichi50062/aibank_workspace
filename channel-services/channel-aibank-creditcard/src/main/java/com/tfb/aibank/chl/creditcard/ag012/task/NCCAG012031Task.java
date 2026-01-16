package com.tfb.aibank.chl.creditcard.ag012.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.ag012.cache.NCCAG012CacheData;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012031Rq;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012031Rs;
import com.tfb.aibank.chl.creditcard.ag012.service.NCCAG012Service;
import com.tfb.aibank.chl.creditcard.resource.dto.CE5552RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CardControlSettingModel;
import com.tfb.aibank.common.model.TxnResult;

// @formatter:off
/**
 * @(#)NCCAG012031Task.java
 * 
 * <p>Description:信用卡交易設定 031 上送設定</p>
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
public class NCCAG012031Task extends AbstractAIBankBaseTask<NCCAG012031Rq, NCCAG012031Rs> {

    @Autowired
    private NCCAG012Service service;

    @Override
    public void validate(NCCAG012031Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG012031Rq rqData, NCCAG012031Rs rsData) throws ActionException {
        NCCAG012CacheData cache = getCache(NCCAG012Service.NCCAG012_CACHE_KEY, NCCAG012CacheData.class);
        CE5552RRequest request = new CE5552RRequest();
        service.buildTxCE5552RRequest(cache.getCardInfoForTx(), getLoginUser(), getLocale(), request);
        CardControlSettingModel model = new CardControlSettingModel();
        service.insertCardControlSetting(getLoginUser(), request, model);
        TxnResult txnResult = new TxnResult();
        service.sendCE5552RForTx(request, txnResult, model);
        service.updateCardControlSetting(model);
        cache.setTxnResult(txnResult);
        rsData.setTxnResult(txnResult);

        setCache(NCCAG012Service.NCCAG012_CACHE_KEY, cache);
    }

}
