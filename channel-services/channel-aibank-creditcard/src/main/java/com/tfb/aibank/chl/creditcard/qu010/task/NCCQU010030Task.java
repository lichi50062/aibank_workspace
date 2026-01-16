package com.tfb.aibank.chl.creditcard.qu010.task;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010030Rq;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010030Rs;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010Cache;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010Input;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010MonthCategoryData;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010Output;
import com.tfb.aibank.chl.creditcard.qu010.service.NCCQU010Service;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NCCQU010030Task.java
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
public class NCCQU010030Task extends AbstractAIBankBaseTask<NCCQU010030Rq, NCCQU010030Rs> {

    private NCCQU010Input input = new NCCQU010Input();
    private NCCQU010Output output = new NCCQU010Output();
    private static final String NCCQU010_030_ENABLE = "NCCQU010_030_ENABLE";

    @Autowired
    private NCCQU010Service service;

    @Autowired
    protected UserDataCacheService userDataCacheService;

    @Override
    public void validate(NCCQU010030Rq rqData) throws ActionException {
        if (StringUtils.isEmpty(rqData.getCategoryGroup())) {
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
    }

    @Override
    public void handle(NCCQU010030Rq rqData, NCCQU010030Rs rsData) throws ActionException {
        // 取得消費類別清單
        service.getConsumptionCategories(output);
        rsData.setConsumptionCategories(output.getConsumptionCategories());

        service.getConditionalMessageEnabled(NCCQU010_030_ENABLE, output);
        rsData.setConditionalMessageEnabled(output.isConditionalMessageEnabled());

        NCCQU010Cache cache = getCache(NCCQU010Service.CACHE_KEY, NCCQU010Cache.class);

        input.setLatestYearAnalysisYearMonths(cache.getLatestYearAnalysisYearMonths());

        input.setLocale(getLocale());

        // 取得分析月清單
        service.getYearMonths(input, output);
        input.setStartYearMonth(cache.getLatestYearAnalysisYearMonths().get(cache.getLatestYearAnalysisYearMonths().size() - 1));
        input.setEndYearMonth(cache.getLatestYearAnalysisYearMonths().get(0));
        int latestYear = DateUtils.getYearIntFromDate(DateUtils.getDateByDateFormat(input.getEndYearMonth(), DateFormatUtils.SQL_YEAR_MONTH_FORMAT.getPattern()));
        rsData.setThisYearMonths(output.getYearMonths().stream().filter(x -> x.getTxYear() == latestYear).collect(Collectors.toList()));
        rsData.setLastYearMonths(output.getYearMonths().stream().filter(x -> x.getTxYear() != latestYear).collect(Collectors.toList()));

        // 檢查是否有跨去年
        service.checkYear(input, output);
        rsData.setHasLastYear(output.getHasLastYear());
        rsData.setCurrentYearDisplay(output.getCurrentYearDisplay());
        rsData.setLastYearDisplay(output.getLastYearDisplay());

        List<NCCQU010MonthCategoryData> monthCategoryData = cache.getMonthCategoryData().stream().filter(x -> StringUtils.equals(x.getCategoryGroup(), rqData.getCategoryGroup())).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(monthCategoryData)) {
            rsData.setMonthCategoryData(monthCategoryData);
            return;
        }

        try {
            AIBankUser aibankUser = getLoginUser();
            input.setCategoryGroup(rqData.getCategoryGroup());

            // 取過去 2 年的各消費類別金額查詢
            if (CollectionUtils.isEmpty(cache.getLatestTwoYearCategoryData().get(rqData.getCategoryGroup()))) {
                service.getLatestTwoYearCategoryData(aibankUser, input, output);
                cache.getLatestTwoYearCategoryData().put(rqData.getCategoryGroup(), output.getLatestTwoYearCategoryData());
            }
            output.setLatestTwoYearCategoryData(cache.getLatestTwoYearCategoryData().get(rqData.getCategoryGroup()));

            if (CollectionUtils.isEmpty(output.getLatestTwoYearCategoryData())) {
                rsData.setQueryResult(1);
                return;
            }

            // 發送 各消費類別金額查詢 OAuth API
            service.filterCategoryConsumption(aibankUser, input, output, cache);

            List<CreditCard> creditCards = userDataCacheService.getAllCreditCards(aibankUser, getLocale());
            input.setCreditCards(creditCards);

            input.setLatestYearCategoryData(output.getCategoryConsumption().getCategoryStats());
            input.setLatestYearAnalysisYearMonths(cache.getLatestYearAnalysisYearMonths());
            input.setLatestSixMonthConsumptionAvg(cache.getLatestSixMonthConsumptionAvg());
            input.setLatestYearConsumptionAvg(cache.getLatestYearConsumptionAvg());
            input.setLatestTwoYearConsumptionData(cache.getLatestTwoYearConsumptionData());
            input.setLatestTwoYearCategoryData(cache.getLatestTwoYearCategoryData().get(rqData.getCategoryGroup()));

            // 取得每月各類別消費資料
            service.getMonthCategoryData(input, output);

            rsData.setMonthCategoryData(output.getMonthCategoryData());
            rsData.setQueryResult(0);

            cache.setMonthCategoryData(output.getMonthCategoryData());
            setCache(NCCQU010Service.CACHE_KEY, cache);
        }
        catch (ServiceException e) {
            logger.error("取得各類別消費資料失敗, exception: ", e.getMessage());
            rsData.setQueryResult(1);
        }
    }
}
