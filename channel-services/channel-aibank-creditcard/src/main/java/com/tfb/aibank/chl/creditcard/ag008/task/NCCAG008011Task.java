package com.tfb.aibank.chl.creditcard.ag008.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.ag008.model.NCCAG008011Rq;
import com.tfb.aibank.chl.creditcard.ag008.model.NCCAG008011Rs;
import com.tfb.aibank.chl.creditcard.ag008.service.NCCAG008Service;
import com.tfb.aibank.chl.creditcard.resource.dto.CardActivateModel;
import com.tfb.aibank.chl.creditcard.resource.dto.CreditCardApplyModel;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.common.model.TxnResult;

// @formatter:off
/**
 * @(#)NCCAG008011Task.java
 * 
 * <p>Description:信用卡開卡 開卡action</p>
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
public class NCCAG008011Task extends AbstractAIBankBaseTask<NCCAG008011Rq, NCCAG008011Rs> {

    @Autowired
    private UserDataCacheService creditcardService;

    @Autowired
    private NCCAG008Service service;

    @Override
    public void validate(NCCAG008011Rq rqData) throws ActionException {
        // 欄位驗證
        service.validateCardNo(this, "cardNoIndex", rqData.getCardNo(), getLocale());
        service.validateBirthday(this, "cardHolderBirth", rqData.getBirthday(), getLocale());
        service.validateCvv(this, "cardCvv", rqData.getCardCvv2(), getLocale());
        service.validateCardExpireDate(this, "cardExpireDate", rqData.getCardNedy(), getLocale());

    }

    @Override
    public void handle(NCCAG008011Rq rqData, NCCAG008011Rs rsData) throws ActionException {

        CardActivateModel createCardActivateResponse = new CardActivateModel();
        // 寫入DB 開卡紀錄
        service.createCardActivateLog(getLoginUser(), getClientIp(), rqData, createCardActivateResponse);

        CreditCardApplyModel cardApplyModel = new CreditCardApplyModel();

        service.applyCreditCard(rqData, cardApplyModel, getLocale(), getAccessLog());

        CreditCard creditCard = getLoginUser().getAllCreditCards().stream().filter(card -> StringUtils.equals(card.getCardNo(), rqData.getCardNo())).findFirst().orElse(new CreditCard());

        // 更新開卡資料
        service.updateCreditCard(createCardActivateResponse, getLoginUser(), cardApplyModel);

        TxnResult txnResult = createTxnResult(cardApplyModel.getTxnStatus(), cardApplyModel.getSendToHostTime(), cardApplyModel.getErrorSystemId(), cardApplyModel.getErrorCode(), cardApplyModel.getErrorDesc());

        rsData.setTxnResult(txnResult);

        // 清空系統暫存信用卡資料
        creditcardService.cleanCache(getLoginUser());

        // 更新系統暫存信用卡資料
        CreditCard newCreditCardInSession = creditcardService.getCreditCard(getLoginUser(), creditCard.getCardNo(), getLocale());

        rsData.setCardKey(newCreditCardInSession.getCardKey());

    }

}