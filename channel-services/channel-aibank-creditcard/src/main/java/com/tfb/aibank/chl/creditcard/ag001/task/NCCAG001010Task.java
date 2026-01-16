package com.tfb.aibank.chl.creditcard.ag001.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.ag001.model.NCCAG001010Rq;
import com.tfb.aibank.chl.creditcard.ag001.model.NCCAG001010Rs;
import com.tfb.aibank.chl.creditcard.ag001.model.NCCAG001CardInfo;
import com.tfb.aibank.chl.creditcard.ag001.service.NCCAG001CacheVo;
import com.tfb.aibank.chl.creditcard.ag001.service.NCCAG001Service;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.common.type.CreditCardHoldType;

//@formatter:off
/**
* @(#)NCCAG001010Task.java
*
* <p>Description:預借現金密碼設定 功能首頁</p>
*
* <p>Modify History:</p>
* v1.0, 2023/09/22 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NCCAG001010Task extends AbstractAIBankBaseTask<NCCAG001010Rq, NCCAG001010Rs> {
    @Autowired
    protected NCCAG001Service service;

    @Autowired
    private UserDataCacheService userDataCacheService;

    @Override
    public void validate(NCCAG001010Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG001010Rq rqData, NCCAG001010Rs rsData) throws ActionException {

        AIBankUser aibankUser = getLoginUser();

        /** 是否為特殊戶或未有信用卡 */
        userDataCacheService.validateCreditCardStatus(getLoginUser());

        /** 未持有信用卡 */
        userDataCacheService.getAllCreditCards(aibankUser, getLocale());

        List<CreditCard> availableCards = userDataCacheService.getEffectiveCreditCards(aibankUser, getLocale());

        boolean isHasActiveCard = false;
        // fortify: Redundant Null Check
        if (availableCards != null) {
            for (CreditCard card : availableCards) {
                if (StringUtils.equals(card.getCardActiveCode(), "Y")) {
                    isHasActiveCard = true;
                }
            }
        }
        if (!isHasActiveCard) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.CREDIT_CARD_NOT_FOUND);
        }

        if (availableCards == null || availableCards.size() == 0) {
            throw new ActionException(ErrorCode.CREDIT_CARD_NOT_FOUND);
        }

        List<NCCAG001CardInfo> pCards = new ArrayList<NCCAG001CardInfo>();
        List<NCCAG001CardInfo> sCards = new ArrayList<NCCAG001CardInfo>();
        for (CreditCard creditCard : availableCards) {
            if (creditCard.getCardHoldType() == CreditCardHoldType.PRIMARY_CARD) {
                NCCAG001CardInfo aCard = new NCCAG001CardInfo();
                aCard.setCardKey(creditCard.getCardKey());
                aCard.setCardName(creditCard.getCardName() + " ···· " + creditCard.getCardNo().substring(12));
                aCard.setIsPrimary(1);
                aCard.setCardNo(creditCard.getCardNo());
                pCards.add(aCard);
                rsData.setHasPrimary(true);
            }
            if (creditCard.getCardHoldType() == CreditCardHoldType.SUPPLEMENTARY_CARD) {
                NCCAG001CardInfo aCard = new NCCAG001CardInfo();
                aCard.setCardKey(creditCard.getCardKey());
                aCard.setCardName(creditCard.getCardName() + " ···· " + creditCard.getCardNo().substring(12));
                aCard.setIsPrimary(2);
                aCard.setCardNo(creditCard.getCardNo());
                pCards.add(aCard);
                rsData.setHasSupp(true);
            }
        }

        IBUtils.sort(pCards, "cardNo", false);
        IBUtils.sort(sCards, "cardNo", false);

        List<NCCAG001CardInfo> cards = new ArrayList<NCCAG001CardInfo>();

        NCCAG001CardInfo pCard = new NCCAG001CardInfo();
        pCard.setIsPrimary(0);
        cards.add(pCard);

        for (NCCAG001CardInfo p : pCards) {
            cards.add(p);
        }

        NCCAG001CardInfo sCard = new NCCAG001CardInfo();
        sCard.setIsPrimary(0);
        cards.add(sCard);

        for (NCCAG001CardInfo s : sCards) {
            cards.add(s);
        }

        for (NCCAG001CardInfo c : cards) {
            // fortify - Critical - Privacy Violation - 將打印 cardNo 改 cardName
            //logger.debug("===>{},{}", c.getCardName(), c.getIsPrimary());
            c.setCardNo(null);
        }

        NCCAG001CacheVo cache = new NCCAG001CacheVo();
        cache.setCards(availableCards);
        setCache(NCCAG001Service.NCCAG001_CACHE_KEY, cache);

        rsData.setCards(cards);

        // 啟動交易安控驗證
        super.initTxSecurity();

    }

}