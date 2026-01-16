package com.tfb.aibank.chl.creditcard.ag008.task;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.ag008.model.NCCAG008010Rq;
import com.tfb.aibank.chl.creditcard.ag008.model.NCCAG008010Rs;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.type.CreditCardHoldType;

// @formatter:off
/**
 * @(#)NCCAG008010Task.java
 * 
 * <p>Description:信用卡開卡 首頁</p>
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
public class NCCAG008010Task extends AbstractAIBankBaseTask<NCCAG008010Rq, NCCAG008010Rs> {

    @Autowired
    private UserDataCacheService creditcardService;

    @Override
    public void validate(NCCAG008010Rq rqData) throws ActionException {
        List<String> allCreditCardKeys = creditcardService.getEffectiveCreditCards(getLoginUser(), getLocale()).stream().map(cardObj -> cardObj.getCardKey()).toList();
        if (StringUtils.isNotBlank(rqData.getCardKey()) && !allCreditCardKeys.contains(rqData.getCardKey())) {
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
    }

    @Override
    public void handle(NCCAG008010Rq rqData, NCCAG008010Rs rsData) throws ActionException {

        logger.trace("NCCAG008010 是否為特殊戶或未有信用卡....");
        // 是否為特殊戶或未有信用卡 不是會報錯
        creditcardService.validateCreditCardStatus(getLoginUser());

        logger.trace("NCCAG008010 是否傳入cardKey.... ");

        String cardKey = rqData.getCardKey();

        // 是否傳入cardKey
        if (StringUtils.isNotBlank(cardKey)) {

            CreditCard creditCard = creditcardService.getCreditCardByCardKey(getLoginUser(), cardKey, getLocale());

            // 判斷卡號是否啟用
            boolean isCardOpen = creditCard.isCardActive();
            if (isCardOpen) {
                logger.trace("NCCAG008010 卡片啟用");
                // 引導至「共通錯誤頁」顯示錯誤代碼(待確認)與錯誤訊息：此卡片已開卡。
                throwActionException(ErrorCode.CARD_ACTIVATED);
            }
            // Session取片名稱
            String cardName = creditCard.getCardName();
            rsData.setCardName(cardName);
            rsData.setCardNoInput(creditCard.getCardNo());
        }
        else {
            // 可開卡卡號
            List<CreditCard> creditCards = getNoActiveCreditCards(getLoginUser());

            List<CreditCard> primaryCards = creditCards.stream().filter(card -> card.getCardHoldType().equals(CreditCardHoldType.PRIMARY_CARD)).toList();

            List<CreditCard> supplementaryCards = creditCards.stream().filter(card -> !card.getCardHoldType().equals(CreditCardHoldType.PRIMARY_CARD)).toList();

            rsData.setActivePrimaryCards(primaryCards);
            rsData.setActiveSupplementaryCards(supplementaryCards);
        }

    }

    /**
     * 讀取「未啟用的歸戶信用卡」清單 若無尚未啟用信用卡，錯誤訊息：目前您無任何尚未開卡之信用卡。
     * 
     * @param aibankUser
     * @return
     * @throws ActionException
     */
    public List<CreditCard> getNoActiveCreditCards(AIBankUser aibankUser) throws ActionException {
        List<CreditCard> allCreditCards = creditcardService.getEffectiveCreditCards(aibankUser, getLocale());
        List<CreditCard> noActiveCreditCards = allCreditCards.stream().filter(cardInfo -> !cardInfo.isCardActive()).sorted(Comparator.comparing(CreditCard::getCardNo)).toList();
        if (CollectionUtils.isEmpty(noActiveCreditCards)) {
            throwActionException(ErrorCode.ALL_CARDS_ACTIVE);
        }
        return noActiveCreditCards;
    }

}