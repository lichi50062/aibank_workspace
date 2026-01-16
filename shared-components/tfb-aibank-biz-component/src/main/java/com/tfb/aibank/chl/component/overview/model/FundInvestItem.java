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

import com.ibm.tw.commons.util.NumberUtils;
import com.tfb.aibank.common.model.FundOverview;
import com.tfb.aibank.common.type.InvestItemType;

// @formatter:off
/**
 * @(#)FundInvestItem.java
 * 
 * <p>Description:[基金]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/15, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FundInvestItem extends InvestItem {

    private List<FundOverview> list;

    private boolean isDbu;

    // 是否為計算含息
    private boolean isInclude;

    public FundInvestItem(boolean isDbu, List<FundOverview> list) {
        this.list = list;
        this.isDbu = isDbu;
    }

    /** 持有商品牌卡 - 參考報酬率 -> (2) 基金報酬率顯示含息報酬率 */
    @Override
    public BigDecimal getRefRate() {
        this.isInclude = true;
        return super.getRewardIncludeRate();
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
    public BigDecimal refAmtNtd() {

        BigDecimal refAmt = BigDecimal.ZERO;
        for (FundOverview item : list) {
            // logger.debug("fundCategory:{},FundTxType:{},isInclude:{}", item.getFundCategory(), item.getFundTxType(), isInclude);
            if (isInclude) {
                // DBU
                if (isDbu) {
                    // NFEE072.HFMTID = 0001、0002、0003、0004、0005，{參考市值折臺}資料來源：NFEE072.CurBalNT，後2碼為小數位
                    // NFEE072.HFMTID =1001{參考市值折臺}資料來源：NFEE072. CurAmtNT，後2碼為小數位
                    // NFEE072.HFMTID =1002、1003{參考市值折臺}資料來源：NFEE072.RefAmtNT，後2碼為小數位
                    if (item.getFundTxType().isInventory())
                        refAmt = refAmt.add(NumberUtils.defaultValue(item.getCurBalNT(), BigDecimal.ZERO));
                    else if (item.getFundTxType().isPurchaseInTransit())
                        refAmt = refAmt.add(NumberUtils.defaultValue(item.getCurAmtNT(), BigDecimal.ZERO));
                    else if (item.getFundTxType().isConversionInTransit() || item.getFundTxType().isRedemptionInTransit())
                        refAmt = refAmt.add(NumberUtils.defaultValue(item.getRefAmtNT(), BigDecimal.ZERO));
                }
                // OBU
                else {
                    // NFEE071.HFMTID = 0001，{參考市值折臺}資料來源：NFEE071.CurBalNT
                    // NFEE071.HFMTID =1001{參考市值折臺}資料來源：NFEE071. CurAmtNT，後2碼為小數位
                    // NFEE071.HFMTID =1002、1003{參考市值折臺}資料來源：NFEE071.RefAmtNT，後2碼為小數位
                    if (item.getFundTxType().isSinglePurchase())
                        refAmt = refAmt.add(NumberUtils.defaultValue(item.getCurBalNT(), BigDecimal.ZERO));
                    else if (item.getFundTxType().isPurchaseInTransit())
                        refAmt = refAmt.add(NumberUtils.defaultValue(item.getCurAmtNT(), BigDecimal.ZERO));
                    else if (item.getFundTxType().isConversionInTransit() || item.getFundTxType().isRedemptionInTransit())
                        refAmt = refAmt.add(NumberUtils.defaultValue(item.getRefAmtNT(), BigDecimal.ZERO));
                }
                // logger.debug("CurBalNT:{}, refAmt:{}", item.getCurBalNT(), refAmt);
            }
            else {
                // DBU - 1001 NFEE072_RS.CurAmtNT , 1002，1003 NFEE072_RS.RefAmtNT, 0001 ~ 0005 (inventory) NFEE072_RS.RefAmtNT
                // OBU - 1001 NFEE071_RS.CurAmtNT , 1002，1003 NFEE071_RS.RefAmtNT, 0001 ~ 0005 (inventory) NFEE071_RS.CurBalNT
                if (item.getFundTxType().isPurchaseInTransit()) {
                    refAmt = refAmt.add(NumberUtils.defaultValue(item.getCurAmtNT(), BigDecimal.ZERO));
                }
                else if (item.getFundTxType().isConversionInTransit() || item.getFundTxType().isRedemptionInTransit()) {
                    refAmt = refAmt.add(NumberUtils.defaultValue(item.getRefAmtNT(), BigDecimal.ZERO));
                }
                else if (item.getFundTxType().isInventory()) {
                    refAmt = isDbu ? refAmt.add(NumberUtils.defaultValue(item.getCurBalNT(), BigDecimal.ZERO)) : refAmt.add(NumberUtils.defaultValue(item.getCurBalNT(), BigDecimal.ZERO));
                }
                // logger.debug("getCurAmtNT:{},getRefAmtNT:{}, refAmt:{}", item.getCurAmtNT(), item.getRefAmtNT(), refAmt);
            }
        }

        logger.debug("{} {} 參考市值折臺[CurBalNT/RefAmtNT]:{}", investType(), this.isInclude ? "含息" : "不含息", refAmt);
        return refAmt;
    }

    /* 調整後累積現金配息折臺 */
    @Override
    public BigDecimal adjAmtNtd() {
        BigDecimal adjAmt = BigDecimal.ZERO;
        for (FundOverview item : list) {
            // logger.debug("fundCategory:{},FundTxType:{},item adjAmtNtd:{}", item.getFundCategory(), item.getFundTxType(), item.getAccAllocateRewNT());
            // 資料來源： NFEE072.AccAllocateRewNT，後2碼為小數位
            if (isDbu) {
                adjAmt = adjAmt.add(NumberUtils.defaultValue(item.getAccAllocateRewNT(), BigDecimal.ZERO));
            }
            else {
                // NFEE071.HFMTID =0001： NFEE071.AccAllocateRewNT
                // NFEE071.HFMTID =1001、1002、1003無回傳{調整後累積現金配息折臺}AccAllocateRewNT欄位，請以0計算。
                if (item.getFundTxType().isSinglePurchase() || item.getFundTxType().isTransit())
                    adjAmt = adjAmt.add(NumberUtils.defaultValue(item.getAccAllocateRewNT(), BigDecimal.ZERO));
            }
        }

        logger.debug("{} 調整後累積現金配息折臺[AccAllocateRewNT]:{}", investType(), adjAmt);
        return adjAmt;
    }

    /**
     * (不)含息報酬 - 投資金額折臺
     *
     * @return
     */
    @Override
    public BigDecimal invAmtNtd() {

        // 過濾null值並獲取curAmtNT, 使用reduce進行求和
        this.sumInvAmtNtd = list.stream().map(FundOverview::getCurAmtNT).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);

        logger.debug("{} 投資金額折臺 sumInvAmtNtd [curAmtNT]:{}", investType(), sumInvAmtNtd);
        return sumInvAmtNtd;
    }

    @Override
    public InvestItemType investType() {
        return InvestItemType.FUND;
    }

}
