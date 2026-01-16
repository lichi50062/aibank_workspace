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
package com.tfb.aibank.biz.component.openapi.model;

import java.util.List;

// @formatter:off
/**
 * @(#)OpenAPIBankListRs.java
 * 
 * <p>Description:查詢參加單位 Response</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/12, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class OpenAPIBankListRs implements OpenAPIRs {

    /**
     * 交易結果代碼
     */
    private String code;

    /**
     * 當前參加單數總計數量 若code為4001，則回傳此欄位
     */
    private String totalCounts;

    /**
     * 當前參加單位列表 若code為4001，則回傳此欄位
     */
    private List<OpenAPIBankListRecord> bankInfo;

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the totalCounts
     */
    public String getTotalCounts() {
        return totalCounts;
    }

    /**
     * @param totalCounts
     *            the totalCounts to set
     */
    public void setTotalCounts(String totalCounts) {
        this.totalCounts = totalCounts;
    }

    /**
     * @return the bankInfo
     */
    public List<OpenAPIBankListRecord> getBankInfo() {
        return bankInfo;
    }

    /**
     * @param bankInfo
     *            the bankInfo to set
     */
    public void setBankInfo(List<OpenAPIBankListRecord> bankInfo) {
        this.bankInfo = bankInfo;
    }

}
