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
import java.util.stream.Collectors;

import com.ibm.tw.commons.util.NumberUtils;
import com.tfb.aibank.chl.component.exchangeratehistory.ExchangeRateHistoryCacheManager;
import com.tfb.aibank.common.model.BondOverview;
import com.tfb.aibank.common.type.InvestItemType;

// @formatter:off
/**
 * @(#)BondInvestItem.java
 * 
 * <p>Description:[海外債券]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/15, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BondInvestItem extends InvestItem {

    private List<BondOverview> list;
    // 是否為計算含息
    private boolean isInclude;

    private ExchangeRateHistoryCacheManager exRateCacheManager;

    public BondInvestItem(List<BondOverview> list, ExchangeRateHistoryCacheManager exRateCacheManager) {
        this.list = list;
        this.exRateCacheManager = exRateCacheManager;
    }

    /** 持有商品牌卡 - 參考報酬率 (2) 海外債報酬率顯示含息報酬率 */
    @Override
    public BigDecimal getRefRate() {
        this.isInclude = true;
        return getRewardIncludeRate();
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
     * (不)含息報酬 - 參考市值折臺
     *
     * @return
     */
    @Override
    protected BigDecimal refAmtNtd() {
        BigDecimal refAmtNtd = BigDecimal.ZERO;
        log.delete(0, log.length()); // clear
        for (BondOverview item : list) {
            // N(A)JWEE010 .HFMTID1=0001、1002{參考市值折臺}資料來源抓RefAmtNT
            if (item.getBondTxType().isInventory() || item.getBondTxType().isRedemptionInTransit()) {
                log.append("[0001/1002]:").append(item.getRefAmtNT()).append(",");
                refAmtNtd = refAmtNtd.add(NumberUtils.defaultValue(item.getRefAmtNT(), BigDecimal.ZERO));
            }
            // N(A)JWEE010 .HFMTID1=1001{參考市值折臺}資料來源TrustAmtNT
            if (item.getBondTxType().isSubscriptionInTransit()) {
                log.append("[1001]:").append(item.getTrustAmtNT()).append(",");
                refAmtNtd = refAmtNtd.add(NumberUtils.defaultValue(item.getTrustAmtNT(), BigDecimal.ZERO));
            }
        }
        logger.debug("{} {} 參考市值折臺 refAmtNtd:{} [RefAmtNT/TrustAmtNT] :{}", investType(), this.isInclude ? "含息" : "不含息", refAmtNtd, log.toString());
        return refAmtNtd;
    }

    /* 調整後累積現金配息折臺 */
    @Override
    protected BigDecimal adjAmtNtd() {
        List<BondOverview> invAmtBondList = list.stream().filter(o -> o.getBondTxType().isInventory() || o.getBondTxType().isTransit()).collect(Collectors.toList());
        BigDecimal adjAmtNtd = BigDecimal.ZERO;
        log.delete(0, log.length()); // clear
        for (BondOverview item : invAmtBondList) {
            log.append(item.getTrustCur()).append(item.getAdjustInterest()).append(",");
            adjAmtNtd = adjAmtNtd.add(NumberUtils.defaultValue(getTwdAmt(exRateCacheManager, item.getTrustCur(), item.getAdjustInterest()), BigDecimal.ZERO));

            // 海外債券：{含息報酬}-{已付前手息}+{應收前手息}
            if (this.isInclude) {
                if (item.getFrontfee1() != null && item.getFrontfee2() != null)
                    log.append(item.getTrustCur()).append(" Frontfee1:").append(item.getFrontfee1()).append("-Frontfee2:").append(item.getFrontfee2()).append(",");
                adjAmtNtd = adjAmtNtd.subtract(NumberUtils.defaultValue(getTwdAmt(exRateCacheManager, item.getTrustCur(), item.getFrontfee1()), BigDecimal.ZERO)).add(NumberUtils.defaultValue(getTwdAmt(exRateCacheManager, item.getTrustCur(), item.getFrontfee2()), BigDecimal.ZERO));
            }
        }

        logger.debug("{} {} 調整後累積現金配息折臺 adjAmtNtd:{} [AdjustInterest]:{}", investType(), this.isInclude ? "含息" : "不含息", adjAmtNtd, log.toString());
        return adjAmtNtd;
    }

    /**
     * (不)含息報酬 - 投資金額折臺
     *
     * @return
     */
    @Override
    protected BigDecimal invAmtNtd() {
        // 海外債券 BOND i. NJWEE010 .HFMTID = 0001、1001 、1002，{參考市值折臺}
        List<BondOverview> invAmtBondList = list.stream().filter(o -> o.getBondTxType().isInventory() || o.getBondTxType().isTransit()).collect(Collectors.toList());
        this.sumInvAmtNtd = invAmtBondList.stream().map(BondOverview::getTrustAmtNT).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);

        logger.debug("{} {} 投資金額折臺 sumInvAmtNtd [TrustAmtNT]:{}", investType(), this.isInclude ? "含息" : "不含息", sumInvAmtNtd);
        return sumInvAmtNtd;
    }

    @Override
    public InvestItemType investType() {
        return InvestItemType.BOND;
    }

}
