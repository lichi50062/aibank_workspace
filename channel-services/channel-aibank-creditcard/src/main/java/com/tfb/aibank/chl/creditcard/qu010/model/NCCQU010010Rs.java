package com.tfb.aibank.chl.creditcard.qu010.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCQU010010Rs.java
 * 
 * <p>Description:消費分析 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/25, Warren Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU010010Rs implements RsData {

    /** 查詢結果: 0: 查詢成功, 1: 查詢失敗, 2: 未持有信用卡, 3: 近一年無信用卡帳單資訊 */
    private int queryResult;

    /** 消費趨勢 */
    private NCCQU010CardSummary trendSummary;

    /** 消費佔比 */
    private NCCQU010CardSummary ratioSummary;

    /** 消費分佈 */
    private NCCQU010CardSummary distributionSummary;

    /** 失敗標題 */
    private String errorTitle;

    /** 失敗訊息 */
    private String errorMessage;

    /** 信用卡推薦 */
    private NCCQU010CreditCardRecommendation creditCardRecommendation;

    /** 分析起始月 */
    private String currentAnalysisMonth;

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
     * @return the trendSummary
     */
    public NCCQU010CardSummary getTrendSummary() {
        return trendSummary;
    }

    /**
     * @param trendSummary
     *            the trendSummary to set
     */
    public void setTrendSummary(NCCQU010CardSummary trendSummary) {
        this.trendSummary = trendSummary;
    }

    /**
     * @return the ratioSummary
     */
    public NCCQU010CardSummary getRatioSummary() {
        return ratioSummary;
    }

    /**
     * @param ratioSummary
     *            the ratioSummary to set
     */
    public void setRatioSummary(NCCQU010CardSummary ratioSummary) {
        this.ratioSummary = ratioSummary;
    }

    /**
     * @return the distributionSummary
     */
    public NCCQU010CardSummary getDistributionSummary() {
        return distributionSummary;
    }

    /**
     * @param distributionSummary
     *            the distributionSummary to set
     */
    public void setDistributionSummary(NCCQU010CardSummary distributionSummary) {
        this.distributionSummary = distributionSummary;
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
     * @return the creditCardRecommendation
     */
    public NCCQU010CreditCardRecommendation getCreditCardRecommendation() {
        return creditCardRecommendation;
    }

    /**
     * @param creditCardRecommendation
     *            the creditCardRecommendation to set
     */
    public void setCreditCardRecommendation(NCCQU010CreditCardRecommendation creditCardRecommendation) {
        this.creditCardRecommendation = creditCardRecommendation;
    }

    /**
     * @return the currentAnalysisMonth
     */
    public String getCurrentAnalysisMonth() {
        return currentAnalysisMonth;
    }

    /**
     * @param currentAnalysisMonth
     *            the currentAnalysisMonth to set
     */
    public void setCurrentAnalysisMonth(String currentAnalysisMonth) {
        this.currentAnalysisMonth = currentAnalysisMonth;
    }
}
