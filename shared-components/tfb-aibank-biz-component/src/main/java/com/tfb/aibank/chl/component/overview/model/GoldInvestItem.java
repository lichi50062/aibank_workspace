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

import com.ibm.tw.commons.util.NumberUtils;
import com.tfb.aibank.chl.component.exchangeratehistory.ExchangeRateHistoryCacheManager;
import com.tfb.aibank.common.model.GoldOverview;
import com.tfb.aibank.common.type.InvestItemType;

// @formatter:off
/**
 * @(#)GoldInvestItem.java
 * 
 * <p>Description:[ 黃金存摺]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/15, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class GoldInvestItem extends InvestItem {

    private List<GoldOverview> list;

    private ExchangeRateHistoryCacheManager exRateCacheManager;

    public GoldInvestItem(List<GoldOverview> list, ExchangeRateHistoryCacheManager exRateCacheManager) {
        this.list = list;
        this.exRateCacheManager = exRateCacheManager;
    }

    /** 持有商品牌卡 - 參考報酬率 (2) 顯示不含息報酬率 */
    @Override
    public BigDecimal getRefRate() {
        return getRewardFreeRate();
    }

    /**
     * 不含息報酬:[參考投資市值] - [投資金額]
     */
    @Override
    public BigDecimal getRewardFree() {
        return super.getRewardFree();
    }

    /**
     * 含息報酬:{參考市值折臺}+{調整後累積現金配息折臺} – {投資金額折臺}
     */
    @Override
    public BigDecimal getRewardInclude() {
        return super.getRewardInclude();
    }

    /**
     * (不)含息報酬 - 參考市值折臺
     *
     * @return
     */
    @Override
    public BigDecimal refAmtNtd() {
        BigDecimal refAmtNtd = BigDecimal.ZERO;
        for (GoldOverview item : list) {
            // C7參考現值： GD320140_Rs.P_VALUE
            refAmtNtd = refAmtNtd.add(NumberUtils.defaultValue(getTwdAmt(exRateCacheManager, item.getCur(), item.getpValue()), BigDecimal.ZERO));
        }

        logger.debug("{} 參考市值折臺[pValue]:{}", investType(), refAmtNtd);
        return refAmtNtd;
    }

    /* 調整後累積現金配息折臺 */
    @Override
    public BigDecimal adjAmtNtd() {
        BigDecimal adjAmtNtd = BigDecimal.ZERO;

        logger.debug("{} 調整後累積現金配息折臺[]:{}", investType(), adjAmtNtd);
        return adjAmtNtd;
    }

    /**
     * (不)含息報酬 - 投資金額折臺
     *
     * @return
     */
    @Override
    public BigDecimal invAmtNtd() {

        this.sumInvAmtNtd = BigDecimal.ZERO;
        for (GoldOverview item : list) {
            // 投資金額：GD320140_Rs.INV_AMT
            this.sumInvAmtNtd = this.sumInvAmtNtd.add(NumberUtils.defaultValue(getTwdAmt(exRateCacheManager, item.getCur(), item.getInvAmt()), BigDecimal.ZERO));
        }

        logger.debug("{} 投資金額折臺 sumInvAmtNtd [InvAmt]:{}", investType(), this.sumInvAmtNtd);
        return sumInvAmtNtd;
    }

    @Override
    public InvestItemType investType() {
        return InvestItemType.GOLD;
    }

}
