package com.tfb.aibank.chl.creditcard.ag009.task;

import java.util.Date;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.time.DateUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.ag009.cache.NCCAG009CacheData;
import com.tfb.aibank.chl.creditcard.ag009.model.NCCAG009030Rq;
import com.tfb.aibank.chl.creditcard.ag009.model.NCCAG009030Rs;
import com.tfb.aibank.chl.creditcard.ag009.service.NCCAG009Service;
import com.tfb.aibank.chl.model.creditcard.CreditCard;

//@formatter:off
/**
 * @(#)NCCAG009030Task.java
 * 
 * <p>Description:信用卡掛失 結果錯誤頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/22, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@Component
@Scope("prototype")
public class NCCAG009030Task extends AbstractAIBankBaseTask<NCCAG009030Rq, NCCAG009030Rs> {

    @Override
    public void validate(NCCAG009030Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG009030Rq rqData, NCCAG009030Rs rsData) throws ActionException {
        NCCAG009CacheData cache = getCache(NCCAG009Service.NCCAG009_CACHE_KEY, NCCAG009CacheData.class);

        CreditCard card = Optional.ofNullable(cache).map(NCCAG009CacheData::getCreditCard).orElseThrow(() -> new NullPointerException("no card in txn cache"));
        rsData.setErrorMsg(cache.getErrorMsg());
        rsData.setTxnStatus(cache.getTxnStatus());
        rsData.setTxnTime(DateUtils.getDateTimeStr(new Date()));
        rsData.setCardName(card.getCardName());
        rsData.setCardCategory(card.getCardLevelDesc());
        rsData.setPrimaryCardNo(cache.getPrimaryCardNo());
        rsData.setSupplementaryCardNos(cache.getSupplementaryCardNos());
    }

}