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
package com.tfb.aibank.chl.component.bankselect.model;

// @formatter:off
/**
 * @(#)BankRecord.java
 * 
 * <p>Description:銀行資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/08, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BankRecord {

    /** 銀行代碼 */
    private String bankId;

    /** 銀行名稱 */
    private String bankName;

    /**
     * Constructor
     */
    public BankRecord() {
        super();
    }

    /**
     * Constructor
     * 
     * @param bankId
     * @param bankName
     */
    public BankRecord(String bankId, String bankName) {
        super();
        this.bankId = bankId;
        this.bankName = bankName;
    }

    /**
     * @return the bankId
     */
    public String getBankId() {
        return bankId;
    }

    /**
     * @param bankId
     *            the bankId to set
     */
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName
     *            the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

}
