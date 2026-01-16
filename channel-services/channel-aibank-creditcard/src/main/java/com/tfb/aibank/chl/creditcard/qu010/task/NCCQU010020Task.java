package com.tfb.aibank.chl.creditcard.qu010.task;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010020Rq;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010020Rs;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010Cache;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010Input;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010Output;
import com.tfb.aibank.chl.creditcard.qu010.service.NCCQU010Service;

// @formatter:off
/**
 * @(#)NCCQU010020Task.java
 * 
 * <p>Description:消費分析 020 趨勢分析頁</p>
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
public class NCCQU010020Task extends AbstractAIBankBaseTask<NCCQU010020Rq, NCCQU010020Rs> {

    private NCCQU010Input input = new NCCQU010Input();
    private NCCQU010Output output = new NCCQU010Output();
    private static final String NCCQU010_020_ENABLE = "NCCQU010_020_ENABLE";

    @Autowired
    private NCCQU010Service service;

    @Override
    public void validate(NCCQU010020Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCQU010020Rq rqData, NCCQU010020Rs rsData) throws ActionException {
        // 取得時間區間清單
        service.getPeriods(output);
        rsData.setPeriods(output.getPeriods());
        service.getConditionalMessageEnabled(NCCQU010_020_ENABLE, output);
        rsData.setConditionalMessageEnabled(output.isConditionalMessageEnabled());

        rsData.setThisYear(service.getThisYear());

        NCCQU010Cache cache = getCache(NCCQU010Service.CACHE_KEY, NCCQU010Cache.class);
        input.setLatestSixMonthYearMonths(cache.getLatestSixMonthAnalysisYearMonths());
        input.setLatestYearAnalysisYearMonths(cache.getLatestYearAnalysisYearMonths());
        input.setLatestSixMonthConsumptionAvg(cache.getLatestSixMonthConsumptionAvg());
        input.setLatestYearConsumptionAvg(cache.getLatestYearConsumptionAvg());
        input.setLatestTwoYearConsumptionData(cache.getLatestTwoYearConsumptionData());
        input.setLocale(getLocale());

        if (CollectionUtils.isNotEmpty(cache.getLatestTwoYearConsumptionData()) && cache.getLatestSixMonthConsumptionAvg() != null && cache.getLatestYearConsumptionAvg() != null) {
            rsData.setLatestSixMonthAvgAmtDisplay(getAmtDisplay(cache.getLatestSixMonthConsumptionAvg()));
            rsData.setLatestYearAvgAmtDisplay(getAmtDisplay(cache.getLatestYearConsumptionAvg()));

            // 取得每月消費匯總資料
            service.getMonthConsumptionData(input, output);
            rsData.setLatestSixMonthConsumptionData(output.getLatestSixMonthConsumptionData());
            rsData.setLatestYearMonthConsumptionData(output.getLatestYearMonthConsumptionData());

            /** 預設時間區間 */
            String cardType = cache.getCardType();
            if (StringUtils.equals(cardType, "A2") || StringUtils.equals(cardType, "B2")) {
                rsData.setDefaultPeriod(1);
            }
            rsData.setTxnDateTime(new Date());
            rsData.setQueryResult(0);

            return;
        }

        try {
            input.setStartYearMonth(cache.getStartYearMonth());
            input.setEndYearMonth(cache.getEndYearMonth());

            // 發送消費金額彙總資訊查詢API以及各消費類別金額查詢API，取得牌卡資料
            service.queryConsumptionSummary(getLoginUser(), input, output, false);

            if (output.getConsumptionSummary() == null) {
                // 取得錯誤資訊
                service.getErrorInfo("NoConsumptionSummary", output);
                rsData.setQueryResult(3);
                return;
            }

            // 計算最近2年內資料
            service.calculateConsumptionData(input, output);

            // 消費金額匯總資料
            cache.setLatestTwoYearConsumptionData(output.getLatestTwoYearConsumptionData());
            cache.setLatestSixMonthConsumptionAvg(output.getLatestSixMonthConsumptionAvg());
            cache.setLatestYearConsumptionAvg(output.getLatestYearConsumptionAvg());
            setCache(NCCQU010Service.CACHE_KEY, cache);

            rsData.setLatestSixMonthAvgAmtDisplay(getAmtDisplay(output.getLatestSixMonthConsumptionAvg()));
            rsData.setLatestYearAvgAmtDisplay(getAmtDisplay(output.getLatestYearConsumptionAvg()));

            // 取得每月消費匯總資料
            service.getMonthConsumptionData(input, output);
            rsData.setLatestSixMonthConsumptionData(output.getLatestSixMonthConsumptionData());
            rsData.setLatestYearMonthConsumptionData(output.getLatestYearMonthConsumptionData());

            /** 預設時間區間 */
            String cardType = cache.getCardType();
            if (StringUtils.equals(cardType, "A2") || StringUtils.equals(cardType, "B2")) {
                rsData.setDefaultPeriod(1);
            }
            rsData.setTxnDateTime(new Date());
            rsData.setQueryResult(0);

        }
        catch (ServiceException e) {
            logger.error("取得牌卡資料失敗, exception: ", e.getMessage());

            rsData.setQueryResult(1);
            rsData.setLatestSixMonthAvgAmtDisplay("--");
            rsData.setLatestYearAvgAmtDisplay("--");
        }
    }

    private String getAmtDisplay(BigDecimal amt) {
        if (amt == null) {
            return "--";
        }

        String sign = amt.compareTo(BigDecimal.ZERO) < 0 ? "-" : "";
        return sign + "$" + IBUtils.formatMoney(amt.abs(), "TWD");
    }
}
