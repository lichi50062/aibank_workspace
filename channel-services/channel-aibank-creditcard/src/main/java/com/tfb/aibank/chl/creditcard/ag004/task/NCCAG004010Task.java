package com.tfb.aibank.chl.creditcard.ag004.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.ag004.cache.NCCAG004CacheData;
import com.tfb.aibank.chl.creditcard.ag004.model.NCCAG004010Rq;
import com.tfb.aibank.chl.creditcard.ag004.model.NCCAG004010Rs;
import com.tfb.aibank.chl.creditcard.ag004.model.NCCAG004Repeat;
import com.tfb.aibank.chl.creditcard.ag004.service.NCCAG004Service;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.type.CreditCardHoldType;

//@formatter:off
/**
* @(#)NCCAG004010Task.java
* 
* <p>Description:刷卡通知設定</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/14, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NCCAG004010Task extends AbstractAIBankBaseTask<NCCAG004010Rq, NCCAG004010Rs> {
    @Autowired
    private UserDataCacheService creditcardService;

    @Autowired
    private NCCAG004Service service;

    @Autowired
    protected UserDataCacheService cardService;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Autowired
    private UserDataCacheService userDataCacheSerivce;

    @Override
    public void validate(NCCAG004010Rq rqData) throws ActionException {
        // 若沒有傳入 cardkey，視為非接續的交易
    }

    @Override
    public void handle(NCCAG004010Rq rqData, NCCAG004010Rs rsData) throws ActionException {
        NCCAG004CacheData cache = new NCCAG004CacheData();

        AIBankUser aibankUser = getLoginUser();

        String host = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "SERVER_HOST");

        // 是否正卡人
        boolean isPrimaryCard = creditcardService.isPrimaryCard(getLoginUser(), getLocale());
        String supplementalCardNo = "";

        // 附卡登入者
        if (aibankUser.getCompanyKind() == 4) {
            isPrimaryCard = false;
        }
        if (!isPrimaryCard && aibankUser.getCompanyKind() == 4) {
            supplementalCardNo = getSupplementalCardNo();
        }

        // 是否為特殊戶或未有信用卡 不是會報錯
        creditcardService.validateCreditCardStatus(getLoginUser());

        // 取信用卡email
        String email = service.getCardUserEmail(getLoginUser());

        CreditCard managedCards = null;

        /** 所有卡片 */
        List<CreditCard> creditCards = userDataCacheSerivce.getAllCreditCards(aibankUser, getLocale());

        List<CreditCard> qualifiedCreditCard = new ArrayList<CreditCard>();

        // A. “不提供E-MAIL消費簡訊”判斷條件：CE6220R_RS.CONSUME_MESSAGE=’N’。(已存於AI Bank User)
        // B. 附卡信用卡會員：COMPANY_KIND=4
        // C. 註冊會員之卡號：篩選AI Bank User之User_Key=CARD_USER_PROFILE.USER_KEY對應之CARD_NO (須解密)。
        for (CreditCard card : creditCards) {
            if (!"N".equals(card.getConsumeMessage())) {
                if (isPrimaryCard) {
                    qualifiedCreditCard.add(card);
                }
                else {
                    if (aibankUser.getCompanyKind() == 4) {
                        if (StringUtils.isNotBlank(supplementalCardNo) && supplementalCardNo.equals(card.getCardNo())) {
                            qualifiedCreditCard.add(card);
                        }
                    }
                    else {
                        qualifiedCreditCard.add(card);
                    }
                }
            }
        }

        if (qualifiedCreditCard.size() == 0)
            throw ExceptionUtils.getActionException(ErrorCode.NO_QUALIFIED_CREDIT_CARD);

        List<NCCAG004Repeat> creditCardList = new ArrayList<NCCAG004Repeat>();
        for (CreditCard card : qualifiedCreditCard) {

            NCCAG004Repeat newCard = new NCCAG004Repeat();
            if (CreditCardHoldType.PRIMARY_CARD.getCode().equals(card.getCardHoldType().getCode())) {
                newCard.setCardKey(card.getCardKey());
                newCard.setCardName(card.getCardName());
                newCard.setCardNo(DataMaskUtil.maskCreditCard(card.getCardNo()));
                newCard.setCardFace(host + card.getImageURL());
                newCard.setRadio("Y".equals(card.getApplyConsumeMessage()) ? true : false);
                List<CreditCard> supplementaryCardList = creditcardService.getSupplementaryCardInfo(aibankUser, card.getCardNo(), getLocale());

                if (supplementaryCardList != null && supplementaryCardList.size() > 1) {
                    IBUtils.sort(supplementaryCardList, "cardNo", false);
                }
                // fortify: Redundant Null Check
                if (supplementaryCardList != null) {
                    newCard.setSuppleCardNo(supplementaryCardList.stream().map(CreditCard::getCardNo).map(DataMaskUtil::maskCreditCard).toList());
                }

                creditCardList.add(newCard);
            }
            else if (CreditCardHoldType.SUPPLEMENTARY_CARD.getCode().equals(card.getCardHoldType().getCode())) {
                newCard.setCardKey(card.getCardKey());
                newCard.setCardName(card.getCardName());
                newCard.setCardNo(DataMaskUtil.maskCreditCard(card.getCardNo()));
                newCard.setCardFace(host + card.getImageURL());
                newCard.setRadio("Y".equals(card.getApplyConsumeMessage()) ? true : false);

                creditCardList.add(newCard);
            }
            else {
                continue;
            }

            if (card.getCardKey().equals(rqData.getCardKey())) {

                rsData.setCard(newCard);
                break;
            }
        }

        cache.setEmail(email);
        cache.setAllCreditCards(creditCards);
        cache.setCardList(creditCardList);
        setCache(NCCAG004Service.NCCAG004_CACHE_KEY, cache);

        for (CreditCard card : qualifiedCreditCard) {
            if (card.getCardKey().equals(rqData.getCardKey())) {
                managedCards = card;
                break;
            }
        }

        if (managedCards == null) {
            rsData.setCardNotFound(true);
            return;
        }

        // 若查無Email，顯示未設定Email畫面。
        if (StringUtils.isBlank(email)) {
            rsData.setNoEmail(true);
            return;
        }

        rsData.setEmail(DataMaskUtil.maskEmail(email));

    }

    private String getSupplementalCardNo() {
        return getLoginUser().getCardUserProfileVo().getCardNo();
    }

}
