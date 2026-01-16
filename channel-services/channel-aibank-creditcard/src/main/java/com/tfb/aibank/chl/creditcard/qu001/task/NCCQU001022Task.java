package com.tfb.aibank.chl.creditcard.qu001.task;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001022Rq;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001022Rs;
import com.tfb.aibank.chl.creditcard.qu001.utils.NCCQU001Utils;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW327RRepeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW327RResponse;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;

//@formatter:off
/**
* @(#)NCCQU001022Task.java
* 
* <p>Description:信用卡總覽 卡片管理頁 重新取得保費權益資訊</p>
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
public class NCCQU001022Task extends AbstractAIBankBaseTask<NCCQU001022Rq, NCCQU001022Rs> {
    @Autowired
    private NCCQU001Utils utils;

    @Override
    public void validate(NCCQU001022Rq rqData) throws ActionException {
        logger.debug("NCCQU001022 validate....");
    }

    @Override
    public void handle(NCCQU001022Rq rqData, NCCQU001022Rs rsData) throws ActionException {
        AIBankUser aibankUser = getLoginUser();
        CreditCard creditCard = aibankUser.getAllCreditCards().stream().filter(card -> StringUtils.equals(card.getCardKey(), rqData.getCardKey())).findFirst().orElse(new CreditCard());
        if (creditCard == null)
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        String custId = getLoginUser().getCustId();
        try {
            CEW327RResponse response = utils.sendCEW327R(custId);
            Optional<CEW327RRepeat> opt = response.getTxRepeats().stream().filter(repeat -> StringUtils.equals(creditCard.getCardNo(), repeat.getCdno())).findAny();
            if (opt.isPresent()) {
                rsData.setInsuType(opt.get().getInsutype());
            }
        }
        catch (ServiceException e) {
            logger.warn("取得保費權益資訊 sendCEW327R 查詢失敗:", e);
            rsData.setCew327rError(true);
        }
    }

}