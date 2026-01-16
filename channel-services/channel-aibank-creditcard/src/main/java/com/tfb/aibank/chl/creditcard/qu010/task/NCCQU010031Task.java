package com.tfb.aibank.chl.creditcard.qu010.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010031Rq;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010031Rs;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010Cache;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010Input;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010Output;
import com.tfb.aibank.chl.creditcard.qu010.service.NCCQU010Service;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NCCQU010031Task.java
 * 
 * <p>Description:消費分析 030 類別分析頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/25, Warren Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCQU010031Task extends AbstractAIBankBaseTask<NCCQU010031Rq, NCCQU010031Rs> {

    private NCCQU010Input input = new NCCQU010Input();
    private NCCQU010Output output = new NCCQU010Output();

    @Autowired
    private NCCQU010Service service;

    @Autowired
    protected UserDataCacheService userDataCacheService;

    @Override
    public void validate(NCCQU010031Rq rqData) throws ActionException {
        if (StringUtils.isEmpty(rqData.getSelectedYearMonth()) || StringUtils.isEmpty(rqData.getItemCategory())) {
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
    }

    @Override
    public void handle(NCCQU010031Rq rqData, NCCQU010031Rs rsData) throws ActionException {

        NCCQU010Cache cache = getCache(NCCQU010Service.CACHE_KEY, NCCQU010Cache.class);

        if (cache.getMonthDetailData() != null && StringUtils.equals(rqData.getSelectedYearMonth(), cache.getMonthDetailData().getYearMonth()) && StringUtils.equals(rqData.getItemCategory(), cache.getMonthDetailData().getItemCategory())) {
            rsData.setMonthDetailData(cache.getMonthDetailData());
            return;
        }

        input.setCategoryGroup(rqData.getCategoryGroup());
        input.setSelectedYearMonth(rqData.getSelectedYearMonth());
        input.setItemCategory(rqData.getItemCategory());
        input.setSkip(0);
        input.setLocale(getLocale());

        AIBankUser aibankUser = getLoginUser();

        // 發送 各類別消費明細查詢 OAuth API
        service.queryCategoryDetails(aibankUser, input, output);
        input.setCategoryDetails(output.getCategoryDetails());

        List<CreditCard> creditCards = userDataCacheService.getAllCreditCards(aibankUser, getLocale());
        input.setCreditCards(creditCards);

        // 取得各月份各類別消費明細資料
        service.getMonthDetailData(input, output);

        rsData.setMonthDetailData(output.getMonthDetailData());
        rsData.setQueryResult(0);

        cache.setMonthDetailData(output.getMonthDetailData());
        setCache(NCCQU010Service.CACHE_KEY, cache);
    }
}
