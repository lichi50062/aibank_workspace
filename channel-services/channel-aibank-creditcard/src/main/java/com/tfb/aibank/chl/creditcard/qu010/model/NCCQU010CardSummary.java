package com.tfb.aibank.chl.creditcard.qu010.model;

import java.math.BigDecimal;
import java.util.List;

// @formatter:off
/**
 * @(#)NCCQU010CardSummary.java
 * 
 * <p>Description:消費分析 輸出專用物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/25, Warren Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCQU010CardSummary {

    /** 查詢結果: 0: 查詢成功, 1: 查詢失敗 */
    private int queryResult;

    /** 卡牌標題 */
    private String title;

    /** 卡牌內容1 */
    private String content1;

    /** 卡牌內容2 */
    private String content2;

    /** 失敗標題 */
    private String errorTitle;

    /** 失敗訊息 */
    private String errorMessage;

    /** 百分比 */
    private List<NCCQU010BarChartData> barChartData;

    /** 最高消費類別佔比 */
    private BigDecimal highestCategoryRatio;

    /** 牌卡種類 */
    private String cardType;

    /**
     * @return the queryResult
     */
    public int getQueryResult() {
        return queryResult;
    }

    /**
     * @param queryResult
     *            the queryResult to set
     */
    public void setQueryResult(int queryResult) {
        this.queryResult = queryResult;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the content1
     */
    public String getContent1() {
        return content1;
    }

    /**
     * @param content1
     *            the content1 to set
     */
    public void setContent1(String content1) {
        this.content1 = content1;
    }

    /**
     * @return the content2
     */
    public String getContent2() {
        return content2;
    }

    /**
     * @param content2
     *            the content2 to set
     */
    public void setContent2(String content2) {
        this.content2 = content2;
    }

    /**
     * @return the errorTitle
     */
    public String getErrorTitle() {
        return errorTitle;
    }

    /**
     * @param errorTitle
     *            the errorTitle to set
     */
    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage
     *            the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the barChartData
     */
    public List<NCCQU010BarChartData> getBarChartData() {
        return barChartData;
    }

    /**
     * @param barChartData
     *            the barChartData to set
     */
    public void setBarChartData(List<NCCQU010BarChartData> barChartData) {
        this.barChartData = barChartData;
    }

    /**
     * @return the highestCategoryRatio
     */
    public BigDecimal getHighestCategoryRatio() {
        return highestCategoryRatio;
    }

    /**
     * @param highestCategoryRatio
     *            the highestCategoryRatio to set
     */
    public void setHighestCategoryRatio(BigDecimal highestCategoryRatio) {
        this.highestCategoryRatio = highestCategoryRatio;
    }

    /**
     * @return the cardType
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * @param cardType
     *            the cardType to set
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

}
