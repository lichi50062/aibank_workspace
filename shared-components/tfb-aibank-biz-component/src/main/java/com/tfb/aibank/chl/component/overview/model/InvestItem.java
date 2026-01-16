/*
 * ===========================================================================
 *
 * IBM Confidential
 *
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.overview.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.component.currencycode.CurrencyCode;
import com.tfb.aibank.chl.component.exchangeratehistory.ExchangeRateHistory;
import com.tfb.aibank.chl.component.exchangeratehistory.ExchangeRateHistoryCacheManager;
import com.tfb.aibank.common.type.InvestItemType;

// @formatter:off
/**
 * @(#)InvestItem.java
 * 
 * <p>Description:[各投資種類父類]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/15, leiley 
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public abstract class InvestItem {

    protected IBLog logger = IBLog.getLog(getClass().getName());

    /** 持有商品牌卡 類型 */
    public abstract InvestItemType investType();

    /** 參考市值折臺 */
    protected abstract BigDecimal refAmtNtd();

    /** 調整後累積現金配息折臺 */
    protected abstract BigDecimal adjAmtNtd();

    /** 投資金額折臺 */
    protected abstract BigDecimal invAmtNtd();

    /** 持有商品 - 投資金額折臺 */
    protected BigDecimal sumInvAmtNtd;
    /** 參考投資總市值 */
    protected BigDecimal sumMarketAmtNtd;

    /** 持有商品 - 參考報酬率 */
    public abstract BigDecimal getRefRate();

    protected StringBuilder log = new StringBuilder();

    public String getRefRateSign() {
        return getRefRate().compareTo(BigDecimal.ZERO) >= 0 ? "+" : "-";
    }

    /**
     * 不含息報酬:[參考市值折臺] - [投資金額折臺]
     */
    public BigDecimal getRewardFree() {
        BigDecimal rewardFree = BigDecimal.ZERO;

        // 參考市值折臺
        BigDecimal refNtd = refAmtNtd();
        // 投資金額折臺
        BigDecimal invNtd = invAmtNtd();

        rewardFree = refNtd.subtract(invNtd);

        logger.debug("{} 不含息報酬 exclude [refNtd:{} - invNtd:{} = {}]", investType(), refNtd, invNtd, rewardFree);
        return rewardFree;
    }

    /**
     * 不含息報酬率 (不含息報酬 / 投資金額折台)
     */
    public BigDecimal getRewardFreeRate() {
        BigDecimal rate = BigDecimal.ZERO;
        BigDecimal invAmtNtd = invAmtNtd();
        BigDecimal rewardFree = getRewardFree();
        if (invAmtNtd.compareTo(BigDecimal.ZERO) != 0) {
            rate = rewardFree.divide(invAmtNtd, 6, RoundingMode.HALF_UP);
        }

        rate = rate.setScale(4, RoundingMode.HALF_UP);
        logger.debug("{} 不含息報酬率 excludeRate [rewardFree:{} / invAmtNtd:{} = {}]", investType(), rewardFree, invAmtNtd, rate);
        return rate.multiply(new BigDecimal("100"));
    }

    /**
     * 含息報酬:{參考市值折臺} + {調整後累積現金配息折臺} – {投資金額折臺} <br>
     * 海外債券：{含息報酬}-{已付前手息}+{應收前手息}
     */
    public BigDecimal getRewardInclude() {

        BigDecimal rewardInclude = BigDecimal.ZERO;

        // 參考市值折臺
        BigDecimal refAmtNtd = refAmtNtd();
        // 調整後累積現金配息折臺
        BigDecimal adjAmtNtd = adjAmtNtd();
        // 投資金額折臺
        BigDecimal invNtd = invAmtNtd();

        rewardInclude = refAmtNtd.add(adjAmtNtd).subtract(invNtd);

        logger.debug("{} 含息報酬 include [refNtd:{} + adjNtd:{} - invNtd:{} = {}]", investType(), refAmtNtd, adjAmtNtd, invNtd, rewardInclude);
        return rewardInclude;
    }

    /**
     * 含息報酬率 (含息報酬 / 投資金額折台)
     */
    public BigDecimal getRewardIncludeRate() {
        BigDecimal rate = BigDecimal.ZERO;
        BigDecimal invAmtNtd = invAmtNtd();
        BigDecimal rewardInclude = getRewardInclude();
        if (invAmtNtd.compareTo(BigDecimal.ZERO) != 0) {
            rate = rewardInclude.divide(invAmtNtd, 6, RoundingMode.HALF_UP);
        }

        rate = rate.setScale(4, RoundingMode.HALF_UP);
        logger.debug("{} 含息報酬率 includeRate [rewardInclude:{} / invNtd:{} = {}]", investType(), rewardInclude, invAmtNtd, rate);
        return rate.multiply(new BigDecimal("100"));
    }

    /**
     * 金額加總(含正負號)
     */
    protected BigDecimal addSignMoney(String sign, BigDecimal money, BigDecimal totalAmt) {
        if (money == null) {
            return BigDecimal.ZERO;
        }
        else {
            String prefix = this.getDisplayStr(sign);
            BigDecimal addMoney = new BigDecimal(prefix + String.valueOf(money));
            return totalAmt.add(addMoney);
        }
    }

    /**
     * 金額加總(含正負號)
     */
    protected BigDecimal addSignMoney(String sign, Integer money, BigDecimal totalAmt) {
        if (money == null) {
            return null;
        }
        else {
            String prefix = this.getDisplayStr(sign);
            BigDecimal addMoney = new BigDecimal(prefix + String.valueOf(money));
            return totalAmt.add(addMoney);
        }
    }

    /**
     * UI顯示字串
     */
    protected String getDisplayStr(String value) {
        if (StringUtils.isBlank(value)) {
            return "";
        }
        else {
            return value;
        }
    }

    /** 持有商品 - 總投資金額折臺(不含息之投資金額) */
    public BigDecimal getSumInvAmtNtd() {
        // 在取得不含息報酬時會初始化
        return sumInvAmtNtd;
    }

    /** 參考投資總市值 */
    public BigDecimal getSumMarketAmtNtd() {
        // 在取得不含息報酬時會初始化
        return sumMarketAmtNtd;
    }

    public BigDecimal getTwdAmt(ExchangeRateHistoryCacheManager exRateCacheManager, String curCode, BigDecimal amt) {
        if (amt == null) {
            return BigDecimal.ZERO;
        }

        if (StringUtils.equals(curCode, CurrencyCode.TWD)) {
            return amt;
        }

        BigDecimal rate = getRate(exRateCacheManager, curCode);
        return amt.multiply(rate);
    }

    public BigDecimal getRate(ExchangeRateHistoryCacheManager exRateCacheManager, String curCode) {
        if (StringUtils.isBlank(curCode)) {
            return BigDecimal.ZERO;
        }
        if (StringUtils.equals(curCode, CurrencyCode.TWD)) {
            return BigDecimal.ONE;
        }
        // exRateHistoryCacheManager is register on XXXInvestItem constructor
        List<ExchangeRateHistory> allExchangeRate = exRateCacheManager.getPreviousBizdayExchangeRates();
        if (allExchangeRate.isEmpty()) {
            throw new RuntimeException("取得前一工作日匯率收盤價異常");
        }
        List<ExchangeRateHistory> curRateHiss = allExchangeRate.stream().filter(x -> StringUtils.equals(x.getRateFlag(), "0") && StringUtils.equals(x.getRateType(), "0") && StringUtils.equals(x.getCurrencyEname1(), curCode)).toList();
        logger.debug("curCode:{}, rate:{}", curCode, curRateHiss.get(0).getBuy());
        return curRateHiss.get(0).getBuy();
    }
}
