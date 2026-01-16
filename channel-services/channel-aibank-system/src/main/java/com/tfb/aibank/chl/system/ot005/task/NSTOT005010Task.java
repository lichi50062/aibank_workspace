/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.system.ot005.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.accountselect.TwAccountSelectorService;
import com.tfb.aibank.chl.component.accountselect.model.TwAccountSelector;
import com.tfb.aibank.chl.system.ot005.model.NSTOT005010Rq;
import com.tfb.aibank.chl.system.ot005.model.NSTOT005010Rs;
import com.tfb.aibank.common.type.TransOutAcctType;

//@formatter:off
/**
* @(#)NSTOT005010Task.java
* 
* <p>Description:『最近轉帳/常用/約定』帳號選擇元件 - 用轉出取得對應轉入 台幣使用</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/27, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NSTOT005010Task extends AbstractAIBankBaseTask<NSTOT005010Rq, NSTOT005010Rs> {

    @Autowired
    private TwAccountSelectorService accountSelectorService;

    @Override
    public void validate(NSTOT005010Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NSTOT005010Rq rqData, NSTOT005010Rs rsData) throws ActionException {

        TwAccountSelector accountSelector = getCache(TwAccountSelectorService.CACHE_KEY, TwAccountSelector.class);
        if (accountSelector == null) {
            throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
        }

        if (rqData.getType() == 1) {
            // 選擇常用Tab內的一筆轉出帳號
            rsData.setPayees(accountSelectorService.getFavoritePayee(TransOutAcctType.TW_SAVING_CHECK_DOC_NO_TRANS_OUT_ACCT, accountSelector, getLoginUser(), getLocale(), rqData.getKey()));
        }
        else if (rqData.getType() == 2) {
            // 選擇約定Tab內的一筆轉出帳號
            rsData.setPayees(accountSelectorService.getAgreedPayee(rqData.isSyncAgreeIn(), TransOutAcctType.TW_SAVING_CHECK_DOC_NO_TRANS_OUT_ACCT, accountSelector, getLoginUser(), getLocale(), rqData.getKey()));
        }

        setCache(TwAccountSelectorService.CACHE_KEY, accountSelector);
    }

}
