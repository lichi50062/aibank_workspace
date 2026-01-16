package com.tfb.aibank.chl.creditcard.qu001.task;

import java.util.List;
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
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001CardData;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001013Rq;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001013Rs;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001Cache;
import com.tfb.aibank.chl.creditcard.qu001.utils.NCCQU001Utils;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW336RRepeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW336RResponse;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;

//@formatter:off
/**
* @(#)NCCQU001013Task.java
* 
* <p>Description:信用卡總覽 功能首頁 查看完整卡號</p>
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
public class NCCQU001013Task extends AbstractAIBankBaseTask<NCCQU001013Rq, NCCQU001013Rs> {

    @Autowired
    private NCCQU001Utils utils;

    @Override
    public void validate(NCCQU001013Rq rqData) throws ActionException {
        doTxConfirmCheck();
    }

    @Override
    public void handle(NCCQU001013Rq rqData, NCCQU001013Rs rsData) throws ActionException {
        AIBankUser aibankUser = getLoginUser();
        NCCQU001Cache cache = getCache(NCCQU001Utils.NCCQU001_CACHE_KEY, NCCQU001Cache.class);
        String cardKey = rqData.getCardKey();
        Optional<NCCQQU001CardData> opt = cache.getCardInfos().stream().filter(cardInfo -> StringUtils.equals(cardKey, cardInfo.getCardKey())).findAny();
        if (opt.isPresent()) {
            CreditCard creditCard = aibankUser.getAllCreditCards().stream().filter(card -> StringUtils.equals(card.getCardKey(), cardKey)).findFirst().orElse(new CreditCard());
            if (opt.get().isVirtualCard()) {
                try {
                    CEW336RResponse cew336r = utils.sendCEW336R(aibankUser.getCustId());
                    List<CEW336RRepeat> cew336rRepeats = cew336r.getTxRepeats();
                    Optional<CEW336RRepeat> repeatOpt = cew336rRepeats.stream().filter(repeat -> StringUtils.equals(creditCard.getCardNo(), repeat.getVncdno())).findAny();
                    if (repeatOpt.isPresent()) {
                        CEW336RRepeat repeat = repeatOpt.get();
                        rsData.setCvv(repeat.getVncvv2());
                        String mmyy = utils.expiryCCCmm2mmYY(repeat.getVnedym());
                        rsData.setCardExpired(mmyy);
                    }
                    setCache(NCCQU001Utils.NCCQU001_CACHE_KEY, cache);
                }
                catch (ServiceException e) {
                    logger.warn("查看完整卡號 sendCEW336R 查詢失敗:", e);
                }
            }
            rsData.setCardNo(creditCard.getCardNo());
        }
        else {
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
    }

}