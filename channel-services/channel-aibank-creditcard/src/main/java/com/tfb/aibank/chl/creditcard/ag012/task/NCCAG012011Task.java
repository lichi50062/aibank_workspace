package com.tfb.aibank.chl.creditcard.ag012.task;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.ag012.cache.NCCAG012CacheData;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012011Rq;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012011Rs;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012CardCollect;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012CardInfo;
import com.tfb.aibank.chl.creditcard.ag012.service.NCCAG012Service;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.model.creditcard.CreditCard;

// @formatter:off
/**
 * @(#)NCCAG012020Task.java
 * 
 * <p>Description:信用卡交易設定 011 重新查詢</p>
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
public class NCCAG012011Task extends AbstractAIBankBaseTask<NCCAG012011Rq, NCCAG012011Rs> {

    @Autowired
    private NCCAG012Service service;

    @Override
    public void validate(NCCAG012011Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG012011Rq rqData, NCCAG012011Rs rsData) throws ActionException {
        NCCAG012CacheData cache = getCache(NCCAG012Service.NCCAG012_CACHE_KEY, NCCAG012CacheData.class);
        List<NCCAG012CardCollect> cardCollects = cache.getCardCollects();
        List<CreditCard> effectCards = userDataCacheService.getEffectiveCreditCards(getLoginUser(), getLocale());
        Optional<CreditCard> effectCardOtp = effectCards.stream().filter(effectCard -> {
            return StringUtils.equals(effectCard.getCardKey(), rqData.getCardKey());
        }).findAny();
		Boolean isAllError = true;
        for (var cardCollect : cardCollects) {
            if (StringUtils.equals(cardCollect.getPrimaryCardInfo().getCardKey(), rqData.getCardKey())) {
                NCCAG012CardInfo primaryCardInfo = new NCCAG012CardInfo();
                if (effectCardOtp.isPresent()) {
                    service.queryCardControlData(effectCardOtp.get(), primaryCardInfo);
                    primaryCardInfo.setIsCe5552rError2Time(true);
                    cardCollect.setPrimaryCardInfo(primaryCardInfo);
					isAllError = isAllError && Boolean.TRUE.equals(primaryCardInfo.getIsCe5552rError());
                }
            }
            for (var cardInfo : cardCollect.getSuppleCardInfos()) {
                if (StringUtils.equals(cardInfo.getCardKey(), rqData.getCardKey())) {
                    if (effectCardOtp.isPresent()) {
                        service.queryCardControlData(effectCardOtp.get(), cardInfo);
                        cardInfo.setIsCe5552rError2Time(true);
						isAllError = isAllError && Boolean.TRUE.equals(cardInfo.getIsCe5552rError());
                    }
                }
            }
        }
		// 電文全錯
		if (Boolean.TRUE.equals(isAllError)) {
			throw ExceptionUtils.getActionException(ErrorCode.CARD_NO_DATA_ERROR);
		}
        rsData.setCardCollects(cardCollects);
        setCache(NCCAG012Service.NCCAG012_CACHE_KEY, cache);
    }

}
