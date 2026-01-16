package com.tfb.aibank.chl.creditcard.qu001.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.validate.ValidatorUtility;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001024Rq;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001024Rs;
import com.tfb.aibank.chl.creditcard.qu001.utils.NCCQU001Utils;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW424RRequest;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;

//@formatter:off
/**
* @(#)NCCQU001024Task.java
* 
* <p>Description:信用卡總覽 卡片管理頁 更新卡片暱稱</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/02, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NCCQU001024Task extends AbstractAIBankBaseTask<NCCQU001024Rq, NCCQU001024Rs> {
    @Autowired
    private NCCQU001Utils utils;

    @Autowired
    private UserDataCacheService creditcardService;

    @Override
    public void validate(NCCQU001024Rq rqData) throws ActionException {
        logger.debug("NCCQU001024 validate....");
        String cardName = rqData.getNickName();
        if (StringUtils.length(cardName) > 6) {
            this.addErrorFieldMap("nickName", I18NUtils.getMessage("validate.cardexpire.formaterror.desc"));
        }
        else if (ValidatorUtility.checkEmoji(cardName)) {
            this.addErrorFieldMap("nickName", I18NUtils.getMessage("validate.cardexpire.formaterror.desc"));
        }
    }

    @Override
    public void handle(NCCQU001024Rq rqData, NCCQU001024Rs rsData) throws ActionException {
        AIBankUser aibankUser = getLoginUser();
        CreditCard creditCard = aibankUser.getAllCreditCards().stream().filter(card -> StringUtils.equals(card.getCardKey(), rqData.getCardKey())).findFirst().orElse(null);
        if (creditCard == null)
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        CEW424RRequest request = new CEW424RRequest();
        request.setVifunc("S");
        request.setVicdno(creditCard.getCardNo());
        request.setVinknm(rqData.getNickName());
        try {
            utils.sendCEW424R(getLoginUser().getCustId(), request);

            // 成功更新系統暫存信用卡資料
            creditcardService.updateCachedCreditCard(aibankUser, creditCard);
            creditCard.setCardNickname(rqData.getNickName());

            rsData.setCardKey(creditCard.getCardKey());
        }
        catch (ServiceException e) {
            logger.error("異動信用卡暱稱 sendCEW424R 查詢失敗:", e);
            throwActionException(ErrorCode.CHANGE_CREDIT_CARD_NICKNAME_ERROR);
        }
    }

}