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
package com.tfb.aibank.chl.component.exchangeratehistory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.component.exchangerate.ExchangeRate;
import com.tfb.aibank.chl.component.exchangerate.ExchangeRateResource;

// @formatter:off
/**
 *  @(#)ExchangeRateHistoryCacheManager.java
 * 
 * <p>Description:歷史匯率檔 Cache Manager</p>
 * <p>此CacheMaager目前只存3個月前～最近資料</p>
 * <p>Modify History:</p>
 * v1.0, 2023/05/25
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class ExchangeRateHistoryCacheManager extends AbstractCacheManager {

    @Autowired
    private ExchangeRateHistoryResource exchangeRateHistoryResource;

    @Autowired
    private ExchangeRateResource exchangeRateResource;

    private List<ExchangeRateHistory> exchangeRateHistoryList = new ArrayList<>();

    private Map<String, ExchangeRateHistory> exchangeRateOneMonthAgoMap = new HashMap<>();

    /**
     * 臺對外，美元對外 1週, 1月, 3月間的sell最低和buy最高map
     */
    private Map<String, Map<String, ExRateHistoryMax>> threeRangesExRateHistoryRangeMap = new HashMap<>();

    /** 前一工作日收盤價 */
    private List<ExchangeRateHistory> yesterdayExchangeRateList = new ArrayList<>();

    private String loadAndCacheDate = "";

    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.EXCHANGE_RATE_HISTORY_CACHE_KEY;
    }

    @Override
    protected void loadCache() {
        Date startDate = DateUtils.addMonth(new Date(), -3);
        Date endDate = new Date();

        List<ExchangeRateHistory> exRateHistories = exchangeRateHistoryResource.getExchangeRateHistoryBetween(startDate, endDate);

        if (CollectionUtils.isNotEmpty(exRateHistories)) {
            List<ExchangeRate> exchangeRateList = exchangeRateResource.findExchangeRateByRateTypeAndExchangeTypeNo("0", "0");
            if (CollectionUtils.isNotEmpty(exchangeRateList)) {
                final Set<String> exRateCursToUse = exchangeRateList.stream().map(ExchangeRate::getCurrencyEname1).collect(Collectors.toSet());
                exRateHistories = exRateHistories.stream().filter(erh -> exRateCursToUse.contains(erh.getCurrencyEname1())).collect(Collectors.toList());
            }
            this.exchangeRateHistoryList = exRateHistories;
            this.processPrevisBizDayExchangeRateData();
        }

        Date endDateOneM = DateUtils.addMonth(new Date(), -1); // 結束日為系統日 減 一個月
        Date startDateOneM = DateUtils.addDay(endDateOneM, -10);
        List<ExchangeRateHistory> histories = exchangeRateHistoryResource.findTwdFxAndUsdFxByTxTimeBetween(startDateOneM, DateUtils.addDays(endDateOneM, 1));

        if (CollectionUtils.isNotEmpty(histories)) {
            Map<String, List<ExchangeRateHistory>> hisMap = histories.stream().collect(Collectors.groupingBy(max -> {
                String eName1 = max.getCurrencyEname1();
                // RATE_TYPE = '0' (臺幣對外幣)時，CurrencyEname2都是NULL，把TWD補進去
                String eName2 = StringUtils.equals("0", max.getRateType()) ? "TWD" : max.getCurrencyEname2();
                return eName1 + "_" + eName2 + "_" + max.getRateType();
            }));
            if (logger.isDebugEnabled())
                logger.debug("== getHistoryRatesMapOneMonthAgo == hisMap: {}", IBUtils.attribute2Str(hisMap));

            Map<String, ExchangeRateHistory> exchangeRateOneMonthAgoMap = new HashMap<>();
            for (Map.Entry<String, List<ExchangeRateHistory>> entry : hisMap.entrySet()) {
                List<ExchangeRateHistory> his = entry.getValue();
                IBUtils.sort(his, "txTime", true);
                exchangeRateOneMonthAgoMap.put(entry.getKey(), his.get(0));
            }
            this.exchangeRateOneMonthAgoMap = exchangeRateOneMonthAgoMap;
        }

        this.threeRangesExRateHistoryRangeMap = exchangeRateHistoryResource.getThreeRangesExRateHistoryRangeMap();

        // 執行loadCache時，把動作的日期記起來
        this.loadAndCacheDate = DateFormatUtils.CE_DATE_FORMAT.format(new Date());
    }

    void processPrevisBizDayExchangeRateData() {
        Set<String> allCurSet = exchangeRateHistoryList.stream().map(ExchangeRateHistory::getCurrencyEname1).collect(Collectors.toSet());

        List<ExchangeRateHistory> yesterdayExchangeRateListTmp = new ArrayList<>();
        for (String curCode : allCurSet) {
            List<ExchangeRateHistory> curRateHiss = exchangeRateHistoryList.stream().filter(getExRateHisPredicate(curCode)).collect(Collectors.toList());
            if (logger.isDebugEnabled()) {
                logger.debug(" == processPrevisBizDayExchangeRateData, curCode: {}, curRateHiss ==, {}", curCode, IBUtils.attribute2Str(curRateHiss));
            }
            if (CollectionUtils.isNotEmpty(curRateHiss)) {
                IBUtils.sort(curRateHiss, new String[] { "txTime" }, new boolean[] { true });
                yesterdayExchangeRateListTmp.add(curRateHiss.get(0));
            }
        }
        if (CollectionUtils.isNotEmpty(yesterdayExchangeRateListTmp)) {
            yesterdayExchangeRateList = yesterdayExchangeRateListTmp;
        }

    }

    private Predicate<ExchangeRateHistory> getExRateHisPredicate(String curCode) {
        return (exRate) -> "0".equals(exRate.getRateFlag()) && "0".equals(exRate.getRateType()) && curCode.equals(exRate.getCurrencyEname1());
    }

    @Override
    protected boolean isFirstLoad() {
        return StringUtils.isEmpty(loadAndCacheDate);
    }

    /**
     * 依傳入Predicate 篩選資料
     */
    public List<ExchangeRateHistory> getExchangeRateHistoryByPredicate(Predicate<ExchangeRateHistory> predicate) {
        List<ExchangeRateHistory> hists = new ArrayList<>(this.exchangeRateHistoryList);
        return hists.stream().filter(predicate).collect(Collectors.toList());
    }

    public List<ExchangeRateHistory> getExchangeRateHistory() {
        return this.exchangeRateHistoryList;
    }

    public ExchangeRateHistory getExchangeRateHistoryAboutOneMonthAgoByKey(String curMapKey) {
        return exchangeRateOneMonthAgoMap.getOrDefault(curMapKey, null);
    }

    public Map<String, Map<String, ExRateHistoryMax>> getThreeRangesExRateHistoryRangeMap() {
        return this.threeRangesExRateHistoryRangeMap;
    }

    /** 取得前一工作日匯率收盤價 */
    public List<ExchangeRateHistory> getPreviousBizdayExchangeRates() {
        return this.yesterdayExchangeRateList;
    }

    public String getLoadAndCacheDate() {
        return this.loadAndCacheDate;
    }

    /**
     * 取得 cache Timeout 時間
     * 
     * @return
     */
    @Override
    protected Duration cacheTimeout() {
        return Duration.ofHours(3);
    }

}
