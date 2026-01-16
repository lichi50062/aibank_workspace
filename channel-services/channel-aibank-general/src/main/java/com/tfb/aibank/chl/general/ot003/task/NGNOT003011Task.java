package com.tfb.aibank.chl.general.ot003.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.accountselect.TwAccountSelectorService;
import com.tfb.aibank.chl.component.accountselect.model.TwAccountSelection;
import com.tfb.aibank.chl.component.accountselect.model.TwAccountSelector;
import com.tfb.aibank.chl.general.ot003.model.NGNOT003011Rq;
import com.tfb.aibank.chl.general.ot003.model.NGNOT003011Rs;

// @formatter:off
/**
 * @(#)NGNOT003011Task.java
 *
 * <p>Description: 收付 準備參數去轉帳</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/06/15, Alex PY Li
 *  <li>[新增說明]</li>
 * </ol>
 */
//@formatter:on
@Component
@Scope("prototype")
public class NGNOT003011Task extends AbstractAIBankBaseTask<NGNOT003011Rq, NGNOT003011Rs> {

    @Autowired
    private TwAccountSelectorService accountSelectorService;

    @Override
    public void validate(NGNOT003011Rq rqData) throws ActionException {
    }

    @Override
    public void handle(NGNOT003011Rq rqData, NGNOT003011Rs rsData) throws ActionException {
        if (StringUtils.isBlank(rqData.getSelectAccount())) {
            throwActionException(CommonErrorCode.UNKNOWN_EXCEPTION);
        }
        TwAccountSelector accountSelector = getCache(TwAccountSelectorService.CACHE_KEY, TwAccountSelector.class);
        if (accountSelector == null) {
            throwActionException(CommonErrorCode.UNKNOWN_EXCEPTION);
        }
        // 由鍵值找到相關資訊
        TwAccountSelection accountSelect = accountSelectorService.findAccountSelection(accountSelector, rqData.getSelectAccount());
        rsData.setPayerAccount(accountSelect.getPayerAccount());
        rsData.setPayeeBank(accountSelect.getPayeeBank());
        rsData.setPayeeAccount(accountSelect.getPayeeAccount());

    }
}
