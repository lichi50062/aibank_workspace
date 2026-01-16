package com.tfb.aibank.chl.general.ag004.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ag004.model.NGNAG004010Rq;
import com.tfb.aibank.chl.general.ag004.model.NGNAG004010Rs;
import com.tfb.aibank.chl.general.ag004.model.vo.FxCurrencyVo;
import com.tfb.aibank.chl.general.ag004.service.NGNAG004Service;
import com.tfb.aibank.chl.general.error.ErrorCode;

// @formatter:off
/**
 * @(#)NGNAG004010Task.java
 * 
 * <p>Description:匯率設定 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/14, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NGNAG004010Task extends AbstractAIBankBaseTask<NGNAG004010Rq, NGNAG004010Rs> {

    @Autowired
    private NGNAG004Service service;

    @Override
    public void validate(NGNAG004010Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNAG004010Rq rqData, NGNAG004010Rs rsData) throws ActionException {
        List<FxCurrencyVo> fxCurrencyVos = new ArrayList<>();

        service.queryFxCurrency("0", getLoginUser(), getLocale(), fxCurrencyVos);

        if (CollectionUtils.isEmpty(fxCurrencyVos)) {
            throwActionException(ErrorCode.NO_RATE_CURRENCY_DATA);
        }

        rsData.setFxCurrencys(fxCurrencyVos);

    }

}
