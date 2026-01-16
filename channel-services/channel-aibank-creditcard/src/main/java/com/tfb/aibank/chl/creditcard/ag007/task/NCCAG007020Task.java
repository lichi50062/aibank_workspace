package com.tfb.aibank.chl.creditcard.ag007.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.ag007.model.NCCAG007020Rq;
import com.tfb.aibank.chl.creditcard.ag007.model.NCCAG007020Rs;
import com.tfb.aibank.chl.creditcard.ag007.model.NCCAG007Output;
import com.tfb.aibank.chl.creditcard.ag007.service.NCCAG007Service;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NCCAG007020Task.java
 * 
 * <p>Description:一鍵綁定行動支付 020 判斷信用卡卡號是否可綁定</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/12, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCAG007020Task extends AbstractAIBankBaseTask<NCCAG007020Rq, NCCAG007020Rs> {

    @Autowired
    private NCCAG007Service service;

    @Autowired
    private UserDataCacheService userDataCacheService;

    private NCCAG007Output dataOutput = new NCCAG007Output();

    @Override
    public void validate(NCCAG007020Rq rqData) throws ActionException {
        if (StringUtils.isBlank(rqData.getCardKey())) {
            logger.error("傳入的卡號為空值。");
            throw ExceptionUtils.getActionException(ErrorCode.CARD_NO_NOT_PASSED_IN);
        }
    }

    @Override
    public void handle(NCCAG007020Rq rqData, NCCAG007020Rs rsData) throws ActionException {

        AIBankUser loginUser = getLoginUser();

        CreditCard creditCard = userDataCacheService.getCreditCardByCardKey(getLoginUser(), rqData.getCardKey(), getLocale());
        // 檢查APP版本
        String currentAppVer = StringUtils.defaultString(getRequest().getAppVer()); // 當前裝置上使用的APP版本
        service.checkBindingStatus(loginUser, getLocale(), creditCard.getCardNo(), currentAppVer, dataOutput);

        service.getCardName(loginUser, getLocale(), creditCard.getCardNo(), dataOutput);
        service.getCardExpire(loginUser, getLocale(), creditCard.getCardNo(), dataOutput);

        rsData.setCheckBindingStatus(dataOutput.isCheckBindingStatus());
        rsData.setCardName(dataOutput.getCardName());
        rsData.setCardExpire(dataOutput.getCardExpire());
        if (!dataOutput.isCheckBindingStatus()) {
            // 「共通錯誤頁」顯示錯誤代碼與錯誤訊息：此卡片無法綁定Apple Pay。
            throw ExceptionUtils.getActionException(ErrorCode.CREDIT_CARD_CAN_NOT_BIND_APPLY_PAY);
        }
    }

}
