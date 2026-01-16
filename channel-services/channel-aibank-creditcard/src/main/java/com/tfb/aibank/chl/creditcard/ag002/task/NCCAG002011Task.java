package com.tfb.aibank.chl.creditcard.ag002.task;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.ag002.model.NCCAG002011Rq;
import com.tfb.aibank.chl.creditcard.ag002.model.NCCAG002011Rs;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NCCAG002011Task.java
 * 
 * <p>Description:信用卡電子帳單設定 檢查持有數位生活卡</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/22, Aaron	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCAG002011Task extends AbstractAIBankBaseTask<NCCAG002011Rq, NCCAG002011Rs> {

    @Override
    public void validate(NCCAG002011Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG002011Rq rqData, NCCAG002011Rs rsData) throws ActionException {
        AIBankUser loginUser = getLoginUser();
        if (CollectionUtils.isNotEmpty(loginUser.getAllCreditCards())) {
            // 判斷是否持有數位生活卡
            boolean hasDigiLifeCard = loginUser.getAllCreditCards().stream().filter(x -> StringUtils.indexOf(x.getCardType(), "24") == 2).findFirst().isPresent();
            rsData.setHasDigiLifeCard(hasDigiLifeCard);
        }
    }
}
