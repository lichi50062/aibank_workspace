/*
 * ===========================================================================
 *
 * IBM Confidential
 *
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.component.investhomepage.model;

//@formatter:off
import java.math.BigDecimal;
import java.util.List; /**
* @(#)InvestHomePageResponse.java
* 
* <p>Description:投資首頁 - Rs</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/05/15, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class InvestHomePageResponse {
    //// 市場燈號
    private List<EconomicIndicator> economicIndicators;
    // 基金
    private List<FundInformation> fundInformations;
    // 觀察基金筆數
    private int watchFundCount;
    // {ETF / 股票}近一個月報酬率
    private List<EtfInformation> etfInformations;
    // 海外債券清單
    private List<OverSeaBondInformation> overSeaBonds;
    // 奈米投
    private List<NmiMarketInformation> nmiMarketInformations;
    /**
     * 關鍵話題(TOPIC)
     */
    private List<FinancialCard> financialCards;
    //// 時事風向
    private FinancialCard lastCategory4;
    //// 研究報告
    private List<FinancialCard> financialReports;
    //// 數位理財
    private List<NmiMarketComparison> nmiMarketComparisons;
    //// 網路投保
    private List<FinancialCard> onlineInsurances;
    /**
     * 其他精選理財
     */
    private FinancialCard financialManagement;
    /**
     * 其他精選理財List
     */
    private List<FinancialCard> financialManagements;
    // 答案
    private String answer;
    // QA統計
    private List<FinancialQA> qaList;

    private BigDecimal investTtlAmt;

    private boolean investTtlAmtError;

    public List<EconomicIndicator> getEconomicIndicators() {
        return economicIndicators;
    }

    public void setEconomicIndicators(List<EconomicIndicator> economicIndicators) {
        this.economicIndicators = economicIndicators;
    }

    public List<FundInformation> getFundInformations() {
        return fundInformations;
    }

    public void setFundInformations(List<FundInformation> fundInformations) {
        this.fundInformations = fundInformations;
    }

    public List<EtfInformation> getEtfInformations() {
        return etfInformations;
    }

    public void setEtfInformations(List<EtfInformation> etfInformations) {
        this.etfInformations = etfInformations;
    }

    public List<OverSeaBondInformation> getOverSeaBonds() {
        return overSeaBonds;
    }

    public void setOverSeaBonds(List<OverSeaBondInformation> overSeaBonds) {
        this.overSeaBonds = overSeaBonds;
    }

    public List<NmiMarketInformation> getNmiMarketInformations() {
        return nmiMarketInformations;
    }

    public void setNmiMarketInformations(List<NmiMarketInformation> nmiMarketInformations) {
        this.nmiMarketInformations = nmiMarketInformations;
    }

    public List<FinancialCard> getFinancialCards() {
        return financialCards;
    }

    public void setFinancialCards(List<FinancialCard> financialCards) {
        this.financialCards = financialCards;
    }

    public FinancialCard getLastCategory4() {
        return lastCategory4;
    }

    public void setLastCategory4(FinancialCard lastCategory4) {
        this.lastCategory4 = lastCategory4;
    }

    public List<FinancialCard> getFinancialReports() {
        return financialReports;
    }

    public void setFinancialReports(List<FinancialCard> financialReports) {
        this.financialReports = financialReports;
    }

    public List<NmiMarketComparison> getNmiMarketComparisons() {
        return nmiMarketComparisons;
    }

    public void setNmiMarketComparisons(List<NmiMarketComparison> nmiMarketComparisons) {
        this.nmiMarketComparisons = nmiMarketComparisons;
    }

    public FinancialCard getFinancialManagement() {
        return financialManagement;
    }

    public void setFinancialManagement(FinancialCard financialManagement) {
        this.financialManagement = financialManagement;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<FinancialQA> getQaList() {
        return qaList;
    }

    public void setQaList(List<FinancialQA> qaList) {
        this.qaList = qaList;
    }

    public BigDecimal getInvestTtlAmt() {
        return investTtlAmt;
    }

    public void setInvestTtlAmt(BigDecimal investTtlAmt) {
        this.investTtlAmt = investTtlAmt;
    }

    public List<FinancialCard> getOnlineInsurances() {
        return onlineInsurances;
    }

    public void setOnlineInsurances(List<FinancialCard> onlineInsurances) {
        this.onlineInsurances = onlineInsurances;
    }

    public boolean isInvestTtlAmtError() {
        return investTtlAmtError;
    }

    public void setInvestTtlAmtError(boolean investTtlAmtError) {
        this.investTtlAmtError = investTtlAmtError;
    }

    public int getWatchFundCount() {
        return watchFundCount;
    }

    public void setWatchFundCount(int watchFundCount) {
        this.watchFundCount = watchFundCount;
    }

    public List<FinancialCard> getFinancialManagements() {
        return financialManagements;
    }

    public void setFinancialManagements(List<FinancialCard> financialManagements) {
        this.financialManagements = financialManagements;
    }
}
