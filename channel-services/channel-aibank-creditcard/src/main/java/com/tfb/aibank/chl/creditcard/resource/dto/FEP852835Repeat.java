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
package com.tfb.aibank.chl.creditcard.resource.dto;

// @formatter:off
/**
 * @(#)FEP852835Repeat.java
 * 
 * <p>Description:查詢簽帳金融卡卡號對應卡片性質</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/21, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FEP852835Repeat {
    /** 帳號 */
    private String acno;

    /** 卡號 */
    private String cardNo;

    /** 卡別 */
    private String cardType;

    /** 卡片狀態 */
    private String carStatus;

    /** 性質 */
    private String carFlg;

    /** VD 卡到期年月 */
    private String endDate;

    /** 數位存款開卡狀態 */
    private String digicardstatus;

    /** 無卡提款註記 */
    private String ncwdFlag;

    /**
     * @return the acno
     */
    public String getAcno() {
        return acno;
    }

    /**
     * @param acno
     *            the acno to set
     */
    public void setAcno(String acno) {
        this.acno = acno;
    }

    /**
     * @return the cardNo
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * @param cardNo
     *            the cardNo to set
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * @return the carType
     */
    public String getCarType() {
        return cardType;
    }

    /**
     * @param carType
     *            the carType to set
     */
    public void setCarType(String cardType) {
        this.cardType = cardType;
    }

    /**
     * @return the carStatus
     */
    public String getCarStatus() {
        return carStatus;
    }

    /**
     * @param carStatus
     *            the carStatus to set
     */
    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    /**
     * @return the carFlg
     */
    public String getCarFlg() {
        return carFlg;
    }

    /**
     * @param carFlg
     *            the carFlg to set
     */
    public void setCarFlg(String carFlg) {
        this.carFlg = carFlg;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the digicardstatus
     */
    public String getDigicardstatus() {
        return digicardstatus;
    }

    /**
     * @param digicardstatus
     *            the digicardstatus to set
     */
    public void setDigicardstatus(String digicardstatus) {
        this.digicardstatus = digicardstatus;
    }

    /**
     * @return the ncwdFlag
     */
    public String getNcwdFlag() {
        return ncwdFlag;
    }

    /**
     * @param ncwdFlag
     *            the ncwdFlag to set
     */
    public void setNcwdFlag(String ncwdFlag) {
        this.ncwdFlag = ncwdFlag;
    }

}
