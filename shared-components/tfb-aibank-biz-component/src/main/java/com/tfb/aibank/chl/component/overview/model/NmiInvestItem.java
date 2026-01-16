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
package com.tfb.aibank.chl.component.overview.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.ibm.tw.commons.util.ArithUtils;
import com.ibm.tw.commons.util.NumberUtils;
import com.tfb.aibank.chl.component.currencycode.CurrencyCode;
import com.tfb.aibank.chl.component.exchangeratehistory.ExchangeRateHistoryCacheManager;
import com.tfb.aibank.common.model.NmiOverview;
import com.tfb.aibank.common.type.InvestItemType;

// @formatter:off
/**
 * @(#)NmiInvestItem.java
 * 
 * <p>Description:[奈米投]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/15, leiley 
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NmiInvestItem extends InvestItem {

    private List<NmiOverview> list;

    /** 參考損益 */
    private BigDecimal profit;
    // 是否為計算含息
    private boolean isInclude;

    private ExchangeRateHistoryCacheManager exRateCacheManager;

    public NmiInvestItem(List<NmiOverview> list, ExchangeRateHistoryCacheManager exRateCacheManager) {
        this.list = list;
        this.exRateCacheManager = exRateCacheManager;
    }

    /**
     * 不含息報酬:[參考市值折臺] - [投資金額折臺]
     */
    @Override
    public BigDecimal getRewardFree() {
        this.isInclude = false;
        return super.getRewardFree();
    }

    /**
     * 含息報酬:{參考市值折臺}+{調整後累積現金配息折臺} – {投資金額折臺}
     */
    @Override
    public BigDecimal getRewardInclude() {
        this.isInclude = true;
        return super.getRewardInclude();
    }

    /**
     * @return the profit
     */
    public BigDecimal getProfit() {
        return profit;
    }

    @Override
    public InvestItemType investType() {
        return InvestItemType.NMI;
    }

    /**
     * 持有商品牌卡 - 參考報酬率
     * <p>
     * {報酬率}計算公式：{參考損益(台幣)} / {投資金額} (以四捨五入ROUND_HALF_UP方式保留四位小數) * 100
     */
    @Override
    public BigDecimal getRefRate() {
        profit = BigDecimal.ZERO; // 參考損益
        log.delete(0, log.length()); // clear
        // (9) {參考損益(台幣)}資料來源：NMP8YB_RS.SignDigitTwd 和NMP8YB_RS.ProfitAndLossTwd
        if (!CollectionUtils.isEmpty(list)) {
            for (NmiOverview o : list) {
                log.append(o.getSignDigitTwd()).append(o.getProfitAndLossTwd()).append(",");
                profit = addSignMoney(o.getSignDigitTwd(), o.getProfitAndLossTwd(), profit);
            }
        }

        logger.debug("{} 參考損益(台幣):{} [ProfitAndLossTwd]:{}", investType(), profit, log.toString());

        return ArithUtils.div(profit, invAmtNtd(), 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 參考市值折臺
     * 
     * @return
     */
    @Override
    protected BigDecimal refAmtNtd() {
        BigDecimal refAmtNtd = new BigDecimal(list.stream().mapToInt(NmiOverview::getMarketValTwd).sum());
        logger.debug("{} 參考市值折臺 refAmtNtd [MarketValTwd]:{}", investType(), refAmtNtd);
        return refAmtNtd;
    }

    /* 調整後累積現金配息折臺 */
    @Override
    protected BigDecimal adjAmtNtd() {
        BigDecimal adjAmtNtd = BigDecimal.ZERO;
        log.delete(0, log.length()); // clear
        for (NmiOverview item : list) {
            if (this.isInclude && item.getMarketValTwd() == 0) {
                continue;
            }
            log.append("PotId:[").append(item.getPotId()).append("]");
            // 前六碼為250900，代表該契約為奈米1號-全球ETF，則{契約幣別}固定為美金
            if (item.getPotId().startsWith("250900")) {
                log.append(item.getDividendamount()).append(",");
                adjAmtNtd = adjAmtNtd.add(NumberUtils.defaultValue(getTwdAmt(exRateCacheManager, CurrencyCode.USD, item.getDividendamount()), BigDecimal.ZERO));
            }
            // 前六碼250901，代表該契約為奈米2號-臺灣ETF，則{契約幣別}固定為台幣
            else if (item.getPotId().startsWith("250901")) {
                log.append(item.getDividendamount()).append(",");
                adjAmtNtd = adjAmtNtd.add(NumberUtils.defaultValue(item.getDividendamount(), BigDecimal.ZERO));
            }
        }
        logger.debug("{} 調整後累積現金配息折臺 adjAmtNtd {} [Dividendamount]:{}", investType(), adjAmtNtd, log.toString());
        return adjAmtNtd;
    }

    /**
     * 投資金額折臺
     * 
     * @return
     */
    @Override
    protected BigDecimal invAmtNtd() {

        if (this.isInclude) {
            this.sumInvAmtNtd = new BigDecimal(list.stream().filter(o -> o.getMarketValTwd() != 0).mapToInt(NmiOverview::getIncreaseAmtTwd).sum());
        }
        else {
            this.sumInvAmtNtd = new BigDecimal(list.stream().mapToInt(NmiOverview::getIncreaseAmtTwd).sum());
        }

        logger.debug("{} {} 投資金額折臺 sumInvAmtNtd [IncreaseAmtTwd]:{}", investType(), this.isInclude ? "含息" : "不含息", sumInvAmtNtd);
        return sumInvAmtNtd;
    }
}
