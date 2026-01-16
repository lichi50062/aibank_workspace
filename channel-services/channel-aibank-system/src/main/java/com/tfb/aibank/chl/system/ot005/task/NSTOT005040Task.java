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
import com.tfb.aibank.chl.system.ot005.model.NSTOT005040Rq;
import com.tfb.aibank.chl.system.ot005.model.NSTOT005040Rs;
import com.tfb.aibank.common.type.TransOutAcctType;

//@formatter:off
/**
* @(#)NSTOT005040Task.java
* 
* <p>Description:『最近轉帳/常用/約定』帳號選擇元件 - 用轉出取得對應轉入 台幣 常用專用</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/08/22, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NSTOT005040Task extends AbstractAIBankBaseTask<NSTOT005040Rq, NSTOT005040Rs> {

    @Autowired
    private TwAccountSelectorService accountSelectorService;

    @Override
    public void validate(NSTOT005040Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NSTOT005040Rq rqData, NSTOT005040Rs rsData) throws ActionException {

        TwAccountSelector accountSelector = getCache(TwAccountSelectorService.FAVORITE_CACHE_KEY, TwAccountSelector.class);
        if (accountSelector == null) {
            throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
        }

        if (rqData.getType() == 1) {
            // 選擇常用Tab內的一筆轉出帳號
            rsData.setPayees(accountSelectorService.getFavoritePayee(TransOutAcctType.TWD_COMMONLY_USED_AND_AGREED_ACCT, accountSelector, getLoginUser(), getLocale(), rqData.getKey()));
        }
        else if (rqData.getType() == 2) {
            // 選擇約定Tab內的一筆轉出帳號
            rsData.setPayees(accountSelectorService.getAgreedPayee(rqData.isSyncAgreeIn(), TransOutAcctType.TWD_COMMONLY_USED_AND_AGREED_ACCT, accountSelector, getLoginUser(), getLocale(), rqData.getKey()));
        }

        setCache(TwAccountSelectorService.FAVORITE_CACHE_KEY, accountSelector);
    }

}
