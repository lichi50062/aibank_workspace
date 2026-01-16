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
import com.tfb.aibank.common.model.HybridProdOverview;
import com.tfb.aibank.common.type.InvestItemType;

// @formatter:off
/**
 * @(#)SidciInvestItem.java
 * 
 * <p>Description:[組合式商品]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/15, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class SiDciInvestItem extends InvestItem {

    private List<HybridProdOverview> list;

    private ExchangeRateHistoryCacheManager exRateCacheManager;

    // 是否為計算含息
    private boolean isInclude;

    public SiDciInvestItem(List<HybridProdOverview> list, ExchangeRateHistoryCacheManager exRateCacheManager) {
        this.list = list;
        this.exRateCacheManager = exRateCacheManager;
    }

    /** 持有商品牌卡 - 參考報酬率 (2) 組合式商品報酬率顯示含息報酬率 */
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
        for (HybridProdOverview item : list) {
            if (item.getProdType().isSPWEBINQ() || item.getProdType().isSPWEBQ2()) {
                log.append("[SPWEBINQ/SPWEBQ2]:").append(item.getCcyCode()).append(item.getRefAmtOri()).append(",");
                refAmtNtd = refAmtNtd.add(NumberUtils.defaultValue(getTwdAmt(exRateCacheManager, item.getCcyCode(), item.getRefAmtOri()), BigDecimal.ZERO));
            }
            else if (item.getProdType().isBKDCD001()) {
                log.append("[BKDCD001]:");
                // 含息 (7) {DCI參考投資市值}資料來源：BKDCD001_Rs. (CURRENCY+)DCDCURAMOUNT。
                if (this.isInclude) {
                    log.append(item.getCcyCode()).append(item.getRefAmt()).append(",");
                    refAmtNtd = refAmtNtd.add(NumberUtils.defaultValue(getTwdAmt(exRateCacheManager, item.getCcyCode(), item.getRefAmt()), BigDecimal.ZERO));
                }
                // 不含息 (B) {DCI參考投資市值}資料來源：BKDCD001_Rs. DCDCURAMOUNTNTD。
                else {
                    log.append(item.getRefAmtNtd()).append(",");
                    refAmtNtd = refAmtNtd.add(NumberUtils.defaultValue(item.getRefAmtNtd(), BigDecimal.ZERO));
                }
            }
        }

        logger.debug("{} {} 參考市值折臺 refAmtNtd:{} [RefAmtOri/DCDCURAMOUNT/DCDCURAMOUNTNTD]:{}", investType(), this.isInclude ? "含息" : "不含息", refAmtNtd, log.toString());
        return refAmtNtd;
    }

    /** 調整後累積現金配息折臺 */
    @Override
    protected BigDecimal adjAmtNtd() {
        BigDecimal adjAmtNtd = BigDecimal.ZERO;
        log.delete(0, log.length()); // clear
        for (HybridProdOverview item : list) {
            // SPWEBINQ_Rs.(CCY+)PLAMT
            if (item.getProdType().isSPWEBINQ()) {
                log.append("[SPWEBINQ]:").append(item.getCcyCode()).append(item.getPlamt()).append(",");
                adjAmtNtd = adjAmtNtd.add(NumberUtils.defaultValue(getTwdAmt(exRateCacheManager, item.getCcyCode(), item.getPlamt()), BigDecimal.ZERO));
            }
            // 若來源為SPWEBQ2，固定顯示0 (無配息)
            else if (item.getProdType().isSPWEBQ2()) {
                adjAmtNtd = adjAmtNtd.add(BigDecimal.ZERO);
            }
            // {DCI配息金額}資料來源：BKDCD001_Rs. (CURRENCY+) INTERESTAMOUNT。
            else if (item.getProdType().isBKDCD001()) {
                log.append("[BKDCD001]:").append(item.getCcyCode()).append(item.getInterestAmount()).append(",");
                adjAmtNtd = adjAmtNtd.add(NumberUtils.defaultValue(getTwdAmt(exRateCacheManager, item.getCcyCode(), item.getInterestAmount()), BigDecimal.ZERO));
            }
        }

        logger.debug("{} 調整後累積現金配息折臺 adjAmtNtd:{} [Plamt/DcdAmount]:{}", investType(), adjAmtNtd, log.toString());
        return adjAmtNtd;
    }

    /**
     * (不)含息報酬 - 投資金額折臺
     *
     * @return
     */
    @Override
    protected BigDecimal invAmtNtd() {
        // (A) {SI投資金額}資料來源：SPWEBINQ_Rs.(CCY+)IVAMT2、SPWEBQ2_Rs.InvestAmt。
        this.sumInvAmtNtd = BigDecimal.ZERO;
        log.delete(0, log.length()); // clear
        for (HybridProdOverview item : list) {
            if (item.getProdType().isSPWEBINQ()) {
                log.append("[SPWEBINQ]:").append(item.getCcyCode()).append(item.getIvamt2()).append(",");
                sumInvAmtNtd = sumInvAmtNtd.add(NumberUtils.defaultValue(getTwdAmt(exRateCacheManager, item.getCcyCode(), item.getIvamt2()), BigDecimal.ZERO));
            }
            else if (item.getProdType().isSPWEBQ2()) {
                log.append("[SPWEBQ2]:").append(item.getCcyCode()).append(item.getInvestAmt()).append(",");
                sumInvAmtNtd = sumInvAmtNtd.add(NumberUtils.defaultValue(getTwdAmt(exRateCacheManager, item.getCcyCode(), item.getInvestAmt()), BigDecimal.ZERO));
            }
            else if (item.getProdType().isBKDCD001()) {
                log.append("[BKDCD001]:");
                // 含息 (8) {DCI投資金額}資料來源：BKDCD001_Rs. (CURRENCY+)DCDAMOUNT
                if (this.isInclude) {
                    log.append(item.getCcyCode()).append(item.getDcdAmount()).append(",");
                    sumInvAmtNtd = sumInvAmtNtd.add(NumberUtils.defaultValue(getTwdAmt(exRateCacheManager, item.getCcyCode(), item.getDcdAmount()), BigDecimal.ZERO));
                }
                // 不含息 (B) {DCI投資金額}資料來源：BKDCD001_Rs. DCDAMOUNTNTD
                else {
                    log.append(item.getDcdAmountNtd()).append(",");
                    sumInvAmtNtd = sumInvAmtNtd.add(NumberUtils.defaultValue(item.getDcdAmountNtd(), BigDecimal.ZERO));
                }
            }
        }

        logger.debug("{} {} 投資金額折臺:{} [IVAMT2/InvestAmt/DCDAMOUNT/DCDAMOUNTNTD]:{}", investType(), this.isInclude ? "含息" : "不含息", sumInvAmtNtd, log.toString());
        return sumInvAmtNtd;
    }

    @Override
    public InvestItemType investType() {
        return InvestItemType.SIDCI;
    }

}
