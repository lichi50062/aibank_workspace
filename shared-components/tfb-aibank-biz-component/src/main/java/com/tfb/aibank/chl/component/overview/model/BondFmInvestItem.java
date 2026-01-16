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
import java.util.List;
import java.util.Objects;

import com.tfb.aibank.chl.component.exchangeratehistory.ExchangeRateHistoryCacheManager;
import com.tfb.aibank.common.model.OdsVpbnd1002;
import com.tfb.aibank.common.type.InvestItemType;

// @formatter:off
/**
 * @(#)BondInvestItem.java
 * 
 * <p>Description:[外國債券 (自營)]]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/09/13, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BondFmInvestItem extends InvestItem {

    private List<OdsVpbnd1002> list;

    // private ExchangeRateHistoryCacheManager exRateCacheManager;

    public BondFmInvestItem(List<OdsVpbnd1002> list, ExchangeRateHistoryCacheManager exRateCacheManager) {
        this.list = list;
        // this.exRateCacheManager = exRateCacheManager;
    }

    /**
     * 持有商品牌卡 - 參考報酬率 (2) 外國債券(自營) 顯示不含息報酬率
     * <p>
     * {報酬率} 計算公式= {總參考市值}-{投資成本}/{投資成本}
     */
    @Override
    public BigDecimal getRefRate() {
        return getRewardFreeRate();
    }

    /**
     * 不含息報酬:[參考市值折臺] - [投資金額折臺]
     */
    @Override
    public BigDecimal getRewardFree() {
        return super.getRewardFree();
    }

    /**
     * 外國債券自營 含息報酬來源 調整為 DBUSERBKLOG.ODS_VPBND_1002_A/B pnlAmtTwd
     * 
     */
    @Override
    public BigDecimal getRewardInclude() {
        // 外國債券自營 含息報酬來源 調整為 DBUSERBKLOG.ODS_VPBND_1002_A/B pnlAmtTwd
        BigDecimal rewardInclude = list.stream().map(OdsVpbnd1002::getPnlAmtTwd).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
        logger.error("{} 含息報酬[pnlAmtTwd]:{}", investType(), rewardInclude);
        return rewardInclude;
    }

    /**
     * (不)含息報酬 - 參考市值折臺
     *
     * @return
     */
    @Override
    protected BigDecimal refAmtNtd() {
        // (8) {報酬率} 計算公式= {總參考市值}-{投資成本}/{投資成本}。
        // DBUSERBKLOG.ODS_VPBND_1002_A/B marketValueAmtTwd
        this.sumMarketAmtNtd = list.stream().map(OdsVpbnd1002::getMarketValueAmtTwd).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);

        logger.error("{} 參考市值折臺[marketValueAmtTwd]:{}", investType(), this.sumMarketAmtNtd);
        return this.sumMarketAmtNtd;
    }

    /* 調整後累積現金配息折臺 */
    @Override
    protected BigDecimal adjAmtNtd() {
        BigDecimal adjAmtNtd = BigDecimal.ZERO;
        logger.error("{} 調整後累積現金配息折臺[]:{}", investType(), adjAmtNtd);
        return adjAmtNtd;
    }

    /**
     * (不)含息報酬 - 投資金額折臺
     *
     * @return
     */
    @Override
    protected BigDecimal invAmtNtd() {
        // DBUSERBKLOG.ODS_VPBND_1002_A/B invest_cost_amt_twd
        this.sumInvAmtNtd = list.stream().map(OdsVpbnd1002::getInvestCostAmtTwd).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);

        logger.error("{} 投資金額折臺 sumInvAmtNtd [investCostAmtTwd ]:{}", investType(), sumInvAmtNtd);
        return sumInvAmtNtd;
    }

    @Override
    public InvestItemType investType() {
        return InvestItemType.BONDFM;
    }

}
