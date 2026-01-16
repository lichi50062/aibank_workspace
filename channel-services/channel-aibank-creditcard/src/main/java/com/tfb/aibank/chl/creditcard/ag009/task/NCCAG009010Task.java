package com.tfb.aibank.chl.creditcard.ag009.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.ag009.cache.NCCAG009CacheData;
import com.tfb.aibank.chl.creditcard.ag009.model.NCCAG009010Rq;
import com.tfb.aibank.chl.creditcard.ag009.model.NCCAG009010Rs;
import com.tfb.aibank.chl.creditcard.ag009.service.NCCAG009Service;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.common.type.CreditCardHoldType;

// @formatter:off
/**
 * @(#)NCCAG009010Task.java
 * 
 * <p>Description:信用卡掛失 首頁</p>
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
public class NCCAG009010Task extends AbstractAIBankBaseTask<NCCAG009010Rq, NCCAG009010Rs> {

    @Autowired
    private UserDataCacheService creditcardService;

    @Override
    public void validate(NCCAG009010Rq rqData) throws ActionException {
        List<String> allCreditCardKeys = creditcardService.getEffectiveCreditCards(getLoginUser(), getLocale()).stream().map(cardObj -> cardObj.getCardKey()).toList();
        if (!allCreditCardKeys.contains(rqData.getCardKey())) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.DATA_NOT_FOUND);
        }
    }

    @Override
    public void handle(NCCAG009010Rq rqData, NCCAG009010Rs rsData) throws ActionException {

        AIBankUser aibankUser = getLoginUser();

        logger.trace("NCCAG009010 是否為特殊戶或未有信用卡....");
        // 是否為特殊戶或未有信用卡
        creditcardService.validateCreditCardStatus(aibankUser);
        logger.trace("NCCAG009010 是否傳入卡號.... ");

        String cardKey = rqData.getCardKey();

        // 是否傳入卡號
        if (StringUtils.isBlank(cardKey)) {
            throwActionException(ErrorCode.CARD_NO_NOT_PASSED_IN);
        }

        CreditCard creditCard = creditcardService.getCreditCardByCardKey(aibankUser, cardKey, getLocale());

        // 卡片是正卡
        // #286 正卡掛失不顯示副卡
        if (CreditCardHoldType.PRIMARY_CARD.getCode().equals(creditCard.getCardHoldType().getCode())) {
            rsData.setPrimaryCardNo(creditCard.getCardNo());
        }
        // 卡片是附卡
        else {
            List<String> supplementaryCardNoList = new ArrayList<>();
            supplementaryCardNoList.add(creditCard.getCardNo());
            rsData.setSupplementaryCardNos(supplementaryCardNoList);
        }

        // Session取片名稱
        String cardName = creditCard.getCardName();
        rsData.setCardName(cardName);
        rsData.setCardCategory(creditCard.getCardLevelDesc());
        rsData.setCardFront(creditCard.getImageURL());

        rsData.setIsHceCard(creditCard.isHceCard());
        rsData.setIsTsccFlag(creditCard.isTsccFlag());

        rsData.setCardNo(creditCard.getCardNo());

        rsData.setCardType(creditCard.getCardType());

        setNccag009Cache(creditCard, rsData);
    }

    private void setNccag009Cache(CreditCard creditCard, NCCAG009010Rs rsData) {
        NCCAG009CacheData cache = new NCCAG009CacheData();
        cache.setCreditCard(creditCard);
        cache.setPrimaryCardNo(rsData.getPrimaryCardNo());
        cache.setSupplementaryCardNos(rsData.getSupplementaryCardNos());
        setCache(NCCAG009Service.NCCAG009_CACHE_KEY, cache);
    }

}