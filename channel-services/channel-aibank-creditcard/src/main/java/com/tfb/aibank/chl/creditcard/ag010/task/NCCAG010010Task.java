package com.tfb.aibank.chl.creditcard.ag010.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.ag010.model.NCCAG010010Rq;
import com.tfb.aibank.chl.creditcard.ag010.model.NCCAG010010Rs;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NCCAG010010Task.java
 * 
 * <p>Description:變更密碼(信用卡) 功能首頁</p>
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
public class NCCAG010010Task extends AbstractAIBankBaseTask<NCCAG010010Rq, NCCAG010010Rs> {

    @Autowired
    private UserDataCacheService userCacheService;

    @Override
    public void validate(NCCAG010010Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG010010Rq rqData, NCCAG010010Rs rsData) throws ActionException {
        AIBankUser loginUser = getLoginUser();
        // 檢核信用卡特殊戶、或未持有信用
        userCacheService.validateCreditCardStatus(loginUser);
        // #8404 供前端檢核使用
        rsData.setUuid(loginUser.getUserId());
        
        
    }

}
