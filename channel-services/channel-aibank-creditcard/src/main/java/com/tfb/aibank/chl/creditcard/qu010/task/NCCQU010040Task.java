package com.tfb.aibank.chl.creditcard.qu010.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010040Rq;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010040Rs;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010Cache;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010CategoryDetailType;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010Input;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010Output;
import com.tfb.aibank.chl.creditcard.qu010.service.NCCQU010Service;
import com.tfb.aibank.chl.creditcard.resource.dto.CategoryDetailResRep;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NCCQU010040Task.java
 * 
 * <p>Description:消費分析 040 月曆消費分析頁</p>
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
public class NCCQU010040Task extends AbstractAIBankBaseTask<NCCQU010040Rq, NCCQU010040Rs> {

    private NCCQU010Input input = new NCCQU010Input();
    private NCCQU010Output output = new NCCQU010Output();

    @Autowired
    private NCCQU010Service service;

    @Autowired
    protected UserDataCacheService userDataCacheService;

    @Override
    public void validate(NCCQU010040Rq rqData) throws ActionException {
        if (StringUtils.isEmpty(rqData.getSelectedYearMonth())) {
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
    }

    @Override
    public void handle(NCCQU010040Rq rqData, NCCQU010040Rs rsData) throws ActionException {
        NCCQU010Cache cache = getCache(NCCQU010Service.CACHE_KEY, NCCQU010Cache.class);
        rsData.setSelectedYearMonth(rqData.getSelectedYearMonth());

        input.setLocale(getLocale());
        try {
            // Call 各類別消費明細 API
            AIBankUser aibankUser = getLoginUser();

            input.setSelectedYearMonth(rqData.getSelectedYearMonth());

            List<CategoryDetailResRep> categoryDetails = new ArrayList<>();
            for (NCCQU010CategoryDetailType category : NCCQU010CategoryDetailType.values()) {
                input.setItemCategory(category.getType());
                input.setCategoryGroup("region");
                input.setSkip(0);

                // 發送 各類別消費明細查詢 OAuth API
                service.queryCategoryDetails(aibankUser, input, output);
                categoryDetails.addAll(output.getCategoryDetails());
            }

            input.setCategoryDetails(categoryDetails);

            List<CreditCard> creditCards = userDataCacheService.getAllCreditCards(aibankUser, getLocale());
            input.setCreditCards(creditCards);

            // 取得各月份各類別消費明細資料
            service.getMonthDetailData(input, output);

            rsData.setMonthDetailData(output.getMonthDetailData());
            rsData.setStartYearMonth(DateUtils.getCEDateStr(DateUtils.getFirstDayOfMonth(DateUtils.getYearMonthDate(cache.getLastYearMonth()))));
            rsData.setEndYearMonth(DateUtils.getCEDateStr(DateUtils.getLastDayOfMonth(DateUtils.getYearMonthDate(cache.getEndYearMonth()))));
            rsData.setQueryResult(output.getQueryResult());
        }
        catch (ServiceException e) {
            logger.error("取得各類別消費明細資料失敗, exception: ", e.getMessage());
            rsData.setQueryResult(1);
        }
    }
}
