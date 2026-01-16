package com.tfb.aibank.chl.creditcard.ag012.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.ag012.cache.NCCAG012CacheData;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012010Rq;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012010Rs;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012CardCollect;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012CardInfo;
import com.tfb.aibank.chl.creditcard.ag012.service.NCCAG012Service;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.common.type.CreditCardHoldType;
import com.tfb.aibank.common.type.CreditCardIdType;

// @formatter:off
/**
 * @(#)NCCAG012010Task.java
 * 
 * <p>Description:信用卡交易設定 010 功能首頁</p>
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
public class NCCAG012010Task extends AbstractAIBankBaseTask<NCCAG012010Rq, NCCAG012010Rs> {

    @Autowired
    private NCCAG012Service service;

    @Autowired
    private UserDataCacheService creditcardService;

    @Override
    public void validate(NCCAG012010Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG012010Rq rqData, NCCAG012010Rs rsData) throws ActionException {
        NCCAG012CacheData cache = new NCCAG012CacheData();
        cache.setCardKeyForTxn(rqData.getCardKey());
        // 是否為特殊戶或未有信用卡 不是會報錯
        creditcardService.validateCreditCardStatus(getLoginUser());

        CreditCardIdType creditCardIdType = userDataCacheService.getCreditCardIdType(getLoginUser(), getLocale());
        if (!creditCardIdType.isPrimaryCard()) {
            throw ExceptionUtils.getActionException(ErrorCode.PRIMARY_CARDHOLDER_ERROR);
        }

        // 取得歸戶清單，並篩選當中持卡類別為正卡及正卡項下附卡且非公採卡的資料。
        List<CreditCard> effectCards = userDataCacheService.getEffectiveCreditCards(getLoginUser(), getLocale());
        // 如果是信用卡會員登入 且這個客戶是附卡人 則要去DB CARD_USER_PROFILE 取他是用哪一張卡片登入的 跟歸戶清單比對
        // 有對到的才是真正的歸戶清單
        if (getLoginUser().getCustomerType().isCardMember() && getLoginUser().getCompanyKind() == 4) {
            effectCards = effectCards.stream().filter(card -> StringUtils.equals(card.getCardNo(), getLoginUser().getCardUserProfileVo().getCardNo())).toList();
            boolean isPrimary = effectCards.stream().anyMatch((card -> StringUtils.equals(card.getmCardNo(), card.getCardNo())));
            if (!isPrimary) {
                // #8478
                throw ExceptionUtils.getActionException(ErrorCode.PRIMARY_CARDHOLDER_ERROR);
            }
        }
        effectCards = effectCards.stream().filter(card -> {
            return StringUtils.equals(card.getCardNo(), card.getmCardNo()) || StringUtils.notEquals(StringUtils.trim(card.getCardHoldId()), getLoginUser().getCustId());
        }).filter(card -> {
            return StringUtils.isBlank(card.getVpsF16());
        }).toList();
        if (CollectionUtils.isEmpty(effectCards)) {
            throw ExceptionUtils.getActionException(ErrorCode.PRIMARY_CARDHOLDER_ERROR);
        }

        // (4) 判斷是否有傳入參數{卡片鍵值}：
        if (StringUtils.isNotBlank(rqData.getCardKey())) {
            Optional<CreditCard> targetCardOpt = effectCards.stream().filter(card -> {
                return StringUtils.equals(card.getCardKey(), rqData.getCardKey());
            }).findAny();
            if (targetCardOpt.isPresent()) {
                CreditCard creditCard = targetCardOpt.get();
                NCCAG012CardInfo cardInfo = new NCCAG012CardInfo();
                service.queryCardControlData(creditCard, cardInfo);
                rsData.setCardInfo(cardInfo);
                if (Boolean.TRUE.equals(cardInfo.getIsCe5552rError())) {
                    throw ExceptionUtils.getActionException(ErrorCode.CARD_NO_DATA_ERROR);
                }
                rsData.setIsGoToEdit(true);
            }
        }
        else {
            List<NCCAG012CardCollect> cardCollects = new ArrayList<>();
            Boolean isAllError = true;

            for (var cardData : effectCards) {
                if (CreditCardHoldType.PRIMARY_CARD.getCode().equals(cardData.getCardHoldType().getCode())) {
                    NCCAG012CardCollect cardCollect = new NCCAG012CardCollect();
                    NCCAG012CardInfo primaryCardInfo = new NCCAG012CardInfo();
                    service.queryCardControlData(cardData, primaryCardInfo);
                    isAllError = isAllError && Boolean.TRUE.equals(primaryCardInfo.getIsCe5552rError());
                    cardCollect.setPrimaryCardInfo(primaryCardInfo);
                    List<CreditCard> supplementaryCardList = creditcardService.getSupplementaryCardInfo(getLoginUser(), cardData.getCardNo(), getLocale());
                    List<NCCAG012CardInfo> supplementaryCardInfos = new ArrayList<>();
                    for (var supplementaryCard : supplementaryCardList) {
                        NCCAG012CardInfo supplementaryCardInfo = new NCCAG012CardInfo();
                        service.queryCardControlData(supplementaryCard, supplementaryCardInfo);
                        supplementaryCardInfos.add(supplementaryCardInfo);
                        isAllError = isAllError && Boolean.TRUE.equals(supplementaryCardInfo.getIsCe5552rError());
                    }
                    cardCollect.setSuppleCardInfos(supplementaryCardInfos);
                    cardCollect.setCardImg(cardData.getImageURL());

                    cardCollect.setCardTypeName(Optional.ofNullable(cardData.getCardTypeInfo()).map(cardObj -> cardObj.getCardTypeName()).orElse(""));
                    cardCollects.add(cardCollect);
                }
            }
            // 電文全錯
            if (Boolean.TRUE.equals(isAllError)) {
                throw ExceptionUtils.getActionException(ErrorCode.CARD_NO_DATA_ERROR);
            }
            rsData.setCardCollect(cardCollects);
            cache.setCardCollects(cardCollects);
        }
        setCache(NCCAG012Service.NCCAG012_CACHE_KEY, cache);
    }

}
