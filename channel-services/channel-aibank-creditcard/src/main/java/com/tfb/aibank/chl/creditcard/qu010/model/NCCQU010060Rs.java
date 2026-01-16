package com.tfb.aibank.chl.creditcard.qu010.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.annotations.FormatDate;
import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCQU010060Rs.java
 * 
 * <p>Description:消費分析 060 搜尋結果頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/25, Warren Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU010060Rs implements RsData {

    /** 查詢結果: 0: 查詢成功, 1: 查詢失敗 3:沒有資料 4:成功,但還有資料 */
    private int queryResult;

    /** 搜尋關鍵字 */
    private String searchKeyword;

    /** 查詢起日 **/
    @FormatDate
    private Date startDt;

    /** 查詢迄日 **/
    @FormatDate
    private Date endDt;

    /** 整年消費明細搜尋資料 */
    private List<NCCQU010AnnualDetailData> annualDetails = new ArrayList<>();

    /** 消費金額加總 */
    private String txnAmtSumDisplay;

    /** 筆數上限 */
    private int recordLimit;

    /**
     * @return the searchKeyword
     */
    public String getSearchKeyword() {
        return searchKeyword;
    }

    /**
     * @param searchKeyword
     *            the searchKeyword to set
     */
    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    /**
     * @return the startDt
     */
    public Date getStartDt() {
        return startDt;
    }

    /**
     * @param startDt
     *            Set the startDt
     */
    public void setStartDt(Date startDt) {
        this.startDt = startDt;
    }

    /**
     * @return the endDt
     */
    public Date getEndDt() {
        return endDt;
    }

    /**
     * @param endDt
     *            Set the endDt
     */
    public void setEndDt(Date endDt) {
        this.endDt = endDt;
    }

    /**
     * @return the queryResult
     */
    public int getQueryResult() {
        return queryResult;
    }

    /**
     * Set the queryResult
     *
     * @param queryResult
     */
    public void setQueryResult(int queryResult) {
        this.queryResult = queryResult;
    }

    /**
     * @return the annualDetails
     */
    public List<NCCQU010AnnualDetailData> getAnnualDetails() {
        return annualDetails;
    }

    /**
     * @param annualDetails
     *            the annualDetails to set
     */
    public void setAnnualDetails(List<NCCQU010AnnualDetailData> annualDetails) {
        this.annualDetails = annualDetails;
    }

    /**
     * @return the txnAmtSumDisplay
     */
    public String getTxnAmtSumDisplay() {
        return this.txnAmtSumDisplay;
    }

    /**
     * @param txnAmtSumDisplay
     *            the txnAmtSumDisplay to set
     */
    public void setTxnAmtSumDisplay(String txnAmtSumDisplay) {
        this.txnAmtSumDisplay = txnAmtSumDisplay;
    }

    /**
     * @return the recordLimit
     */
    public int getRecordLimit() {
        return recordLimit;
    }

    /**
     * @param recordLimit
     *            the recordLimit to set
     */
    public void setRecordLimit(int recordLimit) {
        this.recordLimit = recordLimit;
    }
}
