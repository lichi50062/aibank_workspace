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
package com.tfb.aibank.chl.creditcard.tx005.model;

import java.math.BigDecimal;

// @formatter:off
/**
 * @(#)NCCTX005CreditCard.java
 * 
 * <p>Description:附卡信用卡資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/05, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCTX005CreditCard {

    /** 中文姓名 */
    private String vnCnam;

    /** 持卡人ID */
    private String vnHdid;

    /** 卡片額度 */
    private BigDecimal vnCpma;

    /** 卡號 */
    private String vnCdno;

    /** 中文姓名(隱碼) */
    private String maskCnam;

    /** 持卡人ID(隱碼) */
    private String maskHdid;

    /** 持卡人姓名 */
    private String custName;

    public String getVnHdid() {
        return vnHdid;
    }

    public void setVnHdid(String vnHdid) {
        this.vnHdid = vnHdid;
    }

    public String getVnCnam() {
        return vnCnam;
    }

    public void setVnCnam(String vnCnam) {
        this.vnCnam = vnCnam;
    }

    public BigDecimal getVnCpma() {
        return vnCpma;
    }

    public void setVnCpma(BigDecimal vnCpma) {
        this.vnCpma = vnCpma;
    }

    public String getMaskCnam() {
        return maskCnam;
    }

    public void setMaskCnam(String maskCnam) {
        this.maskCnam = maskCnam;
    }

    public String getMaskHdid() {
        return maskHdid;
    }

    public void setMaskHdid(String maskHdid) {
        this.maskHdid = maskHdid;
    }

    public String getVnCdno() {
        return vnCdno;
    }

    public void setVnCdno(String vnCdno) {
        this.vnCdno = vnCdno;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

}
