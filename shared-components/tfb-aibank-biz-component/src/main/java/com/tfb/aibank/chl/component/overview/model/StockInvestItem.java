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
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.common.model.StockOverview;
import com.tfb.aibank.common.type.InvestItemType;

// @formatter:off
/**
 * @(#)StockInvestItem.java
 * 
 * <p>Description:[海外ETF/股票]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/15, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class StockInvestItem extends InvestItem {

    private List<StockOverview> list;

    /** ETF 參考市值折臺 BPM001_RS.UK084N.Amount + BPM001_RS.N8084N.AR101。 */
    private BigDecimal etfMarketAmt;

    // 是否為計算含息
    private boolean isInclude;

    public StockInvestItem(List<StockOverview> list) {
        this.list = list;
    }

    public StockInvestItem(List<StockOverview> list, BigDecimal etfMarketAmt) {
        this.list = list;
        this.etfMarketAmt = etfMarketAmt;
    }

    /** 持有商品牌卡 - 參考報酬率 (2) 顯示不含息報酬率 */
    @Override
    public BigDecimal getRefRate() {
        this.isInclude = false;
        return getRewardFreeRate();
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
        // 交易類型 Category , 0001 庫存資訊 0004 賣出委託交易資訊
        List<StockOverview> refAmtStockList = isInclude ? Collections.emptyList() : list.stream().filter(x -> StringUtils.equalsAny(x.getCategory(), "0001", "0004")).collect(Collectors.toList());
        // 含息報酬 - 參考市值折臺來源是 單筆 BPM001_RS.UK084N.Amount + 定期定額BPM001RS.N8084N.AR101
        // 不含息報酬 - 參考市值折臺 0001,0004 單筆 NR088N1.acctbal加總 + 定期定額N8088N1.acctbal加總
        BigDecimal refAmtNtd = isInclude ? this.etfMarketAmt : refAmtStockList.stream().map(StockOverview::getAcctBal).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);

        logger.debug("{} {} 參考市值折臺[AcctBal]:{}", investType(), this.isInclude ? "含息" : "不含息", refAmtNtd);
        return refAmtNtd;
    }

    /* 調整後累積現金配息折臺 (單筆NR088N1.DividendTwd + 定期定額N8088N1.dividendTwd) */
    @Override
    protected BigDecimal adjAmtNtd() {
        BigDecimal adjAmtNtd = BigDecimal.ZERO;
        // 調整後累積現金配息折臺 單筆 NR088N1 0001 0004.dividendTwd 加總 + 定期定額 N8088N1 0001 0004.dividendTwd 加總
        List<StockOverview> adjAmtStockList = list.stream().filter(x -> StringUtils.equalsAny(x.getCategory(), "0001", "0004")).collect(Collectors.toList());
        adjAmtNtd = adjAmtStockList.stream().map(StockOverview::getDividendTwd).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
        logger.debug("{} 調整後累積現金配息折臺 adjAmtNtd [DividendTwd]:{}", investType(), adjAmtNtd);
        return adjAmtNtd;
    }

    /**
     * (不)含息報酬 - 投資金額/投資金額折臺(單筆NR088N1.AcctBalCost, 定期定額N8088N1.AcctBalCost))
     *
     * @return
     */
    @Override
    protected BigDecimal invAmtNtd() {
        // (不)含息報酬 - 投資金額折臺 0001,0004單筆NR088N1.AcctBalCost加總 + 定期定額N8088N1.AcctBalCost加總
        List<StockOverview> invAmtStockList = list.stream().filter(x -> StringUtils.equalsAny(x.getCategory(), "0001", "0004")).collect(Collectors.toList());
        this.sumInvAmtNtd = invAmtStockList.stream().map(StockOverview::getAcctBalCost).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
        logger.debug("{} 投資金額折臺 sumInvAmtNtd [AcctBalCost]:{}", investType(), this.isInclude ? "含息" : "不含息", sumInvAmtNtd);
        return sumInvAmtNtd;
    }

    @Override
    public InvestItemType investType() {
        return InvestItemType.ETF;
    }

}
