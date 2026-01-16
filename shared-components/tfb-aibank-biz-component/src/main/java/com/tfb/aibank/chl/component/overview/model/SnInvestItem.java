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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibm.tw.commons.util.NumberUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.component.exchangeratehistory.ExchangeRateHistoryCacheManager;
import com.tfb.aibank.common.model.BondOverview;
import com.tfb.aibank.common.type.InvestItemType;

// @formatter:off
/**
 * @(#)SnInvestItem.java
 * 
 * <p>Description:[境內外結構型商品]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/15, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class SnInvestItem extends InvestItem {

    private List<BondOverview> list;

    private ExchangeRateHistoryCacheManager exRateCacheManager;

    // 是否為計算含息
    private boolean isInclude;

    public SnInvestItem(List<BondOverview> list, ExchangeRateHistoryCacheManager exRateCacheManager) {
        this.list = list;
        this.exRateCacheManager = exRateCacheManager;
    }

    /** 持有商品牌卡 - 參考報酬率 (2) 境內外結構型商品報酬率顯示含息報酬率 */
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
            // DBU：NJWEE010.HFMTID = 0001, 0002，{參考市值折臺}資料來源：NJWEE010_RS.RefAmtNT
            if (item.getBondTxType().isInventory() || item.getBondTxType().isRedemptionInTransit()) {
                log.append("[0001/0002]:").append(item.getRefAmtNT()).append(",");
                refAmtNtd = refAmtNtd.add(NumberUtils.defaultValue(item.getRefAmtNT(), BigDecimal.ZERO));
            }
            // ii. NJWEE010.HFMTID = 1001 ，{參考市值折臺}資料來源：NJWEE010_RS.TrustAmtNT
            else if (item.getBondTxType().isSubscriptionInTransit()) {
                log.append("[1001]:").append(item.getTrustAmtNT()).append(",");
                refAmtNtd = refAmtNtd.add(NumberUtils.defaultValue(item.getTrustAmtNT(), BigDecimal.ZERO));
            }
        }

        logger.debug("{} 參考市值折臺 {} [RefAmtNT/TrustAmtNT]:{}", investType(), refAmtNtd, log.toString());
        return refAmtNtd;
    }

    /** 調整後累積現金配息折臺 */
    @Override
    protected BigDecimal adjAmtNtd() {
        BigDecimal adjAmtNtd = BigDecimal.ZERO;
        log.delete(0, log.length()); // clear
        Map<String, BondOverview> eviNumMap = new HashMap<String, BondOverview>();

        // 若0001與1002的電文回傳憑證號碼AJWEE010.EviNum相等時， 1002{調整後累積現金配息折臺}視為0計算
        if (list != null)
            list.stream().filter(item -> item.getBondTxType().isInventory()).forEach(item -> eviNumMap.put(item.getEviNum(), item));

        // DBU：NJWEE010.HFMTID = 0001, 1001, 1002，{調整後累積現金配息折臺}資料來源：NJWEE010_RS.AdjustInterest
        for (BondOverview item : list) {
            // 若0001與1002的電文回傳憑證號碼AJWEE010.EviNum相等時， 1002{調整後累積現金配息折臺}視為0計算
            if (item.getBondTxType().isRedemptionInTransit() && !eviNumMap.containsKey(item.getEviNum())) {
                log.append("[1002]:").append(item.getEviNum()).append(" ").append(item.getTrustCur()).append(item.getAdjustInterest()).append(",");
                adjAmtNtd = adjAmtNtd.add(NumberUtils.defaultValue(getTwdAmt(exRateCacheManager, item.getTrustCur(), item.getAdjustInterest()), BigDecimal.ZERO));
            }
            else if (item.getBondTxType().isInventory() || item.getBondTxType().isSubscriptionInTransit()) {
                log.append("[0001/1001]:").append(item.getEviNum()).append(" ").append(item.getTrustCur()).append(item.getAdjustInterest()).append(",");
                adjAmtNtd = adjAmtNtd.add(NumberUtils.defaultValue(getTwdAmt(exRateCacheManager, item.getTrustCur(), item.getAdjustInterest()), BigDecimal.ZERO));
            }
        }

        logger.debug("{} 調整後累積現金配息折臺 {} [AdjustInterest]:{}", investType(), adjAmtNtd, log.toString());
        return adjAmtNtd;
    }

    /**
     * (不)含息報酬 - 投資金額折臺
     *
     * @return
     */
    @Override
    protected BigDecimal invAmtNtd() {
        this.sumInvAmtNtd = BigDecimal.ZERO;
        log.delete(0, log.length()); // clear
        for (BondOverview item : list) {
            if (this.isInclude) {
                // NJWEE010.HFMTID = 0001, 1001, 1002 ：NJWEE010_RS.TrustAmtNT
                if (item.getBondTxType().isInventory() || item.getBondTxType().isTransit()) {
                    log.append("[0001/1001/1002]:").append(item.getTrustAmtNT()).append(",");
                    sumInvAmtNtd = sumInvAmtNtd.add(NumberUtils.defaultValue(item.getTrustAmtNT(), BigDecimal.ZERO));
                }
            }
            else {
                // HFMTID = 0001, 0002，取 TrustAmtNT
                if (item.getBondTxType().isInventory() || item.getBondTxType().isRedemptionInTransit()) {
                    log.append("[0001/0002]:").append(item.getTrustAmtNT()).append(",");
                    sumInvAmtNtd = sumInvAmtNtd.add(NumberUtils.defaultValue(item.getTrustAmtNT(), BigDecimal.ZERO));
                }
                // HFMTID = 1001，取 LoanSts = 2 的 TrustAmtNT
                else if (item.getBondTxType().isSubscriptionInTransit()) {
                    log.append("[1001] LoanSts [").append(item.getLoanSts()).append("]:").append(item.getTrustAmtNT()).append(",");
                    if (StringUtils.equals(item.getLoanSts(), "2")) {
                        sumInvAmtNtd = sumInvAmtNtd.add(NumberUtils.defaultValue(item.getTrustAmtNT(), BigDecimal.ZERO));
                    }
                }
            }
        }
        logger.debug("{} {} 投資金額折臺 sumInvAmtNtd:{} [TrustAmtNT]:{}", investType(), this.isInclude ? "含息" : "不含息", sumInvAmtNtd, log.toString());
        return sumInvAmtNtd;
    }

    @Override
    public InvestItemType investType() {
        return InvestItemType.SN;
    }

}
