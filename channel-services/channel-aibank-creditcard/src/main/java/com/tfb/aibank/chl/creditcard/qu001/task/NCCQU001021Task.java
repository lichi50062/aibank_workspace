package com.tfb.aibank.chl.creditcard.qu001.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001021Rq;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001021Rs;
import com.tfb.aibank.chl.creditcard.qu001.utils.NCCQU001Utils;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW321RResponse;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;

//@formatter:off
/**
* @(#)NCCQU001021Task.java
* 
* <p>Description:信用卡總覽 卡片管理頁 重新載入年度累積消費資料</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NCCQU001021Task extends AbstractAIBankBaseTask<NCCQU001021Rq, NCCQU001021Rs> {
    @Autowired
    private NCCQU001Utils utils;

    @Override
    public void validate(NCCQU001021Rq rqData) throws ActionException {
        logger.debug("NCCQU001021 validate....");
    }

    @Override
    public void handle(NCCQU001021Rq rqData, NCCQU001021Rs rsData) throws ActionException {
        AIBankUser aibankUser = getLoginUser();
        CreditCard creditCard = aibankUser.getAllCreditCards().stream().filter(card -> StringUtils.equals(card.getCardKey(), rqData.getCardKey())).findFirst().orElse(null);
        if (creditCard == null)
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        // 1.電文取得年度累積消費資料
        try {
            CEW321RResponse cew321Rs = utils.sendCEW321R(creditCard.getCardNo());
            rsData.setStrday(cew321Rs.getStrday());
            rsData.setEndday(cew321Rs.getEndday());
            rsData.setOutamt1(cew321Rs.getOutamt1());
            rsData.setOutamt2(cew321Rs.getOutamt2());
            rsData.setOutcnt1(cew321Rs.getOutcnt1());
            rsData.setOutcnt2(cew321Rs.getOutcnt2());
        }
        catch (ServiceException e) {
            logger.warn("取得年度累積消費資料 sendCEW321R 查詢失敗:", e);
            rsData.setCew321rError(true);
        }
    }

}