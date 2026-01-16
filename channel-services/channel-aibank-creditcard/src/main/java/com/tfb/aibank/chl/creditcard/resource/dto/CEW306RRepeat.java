package com.tfb.aibank.chl.creditcard.resource.dto;

import java.math.BigDecimal;

//@formatter:off
/**
* @(#)CEW306RRepeat.java
* 
* <p>Description:CEW306R 信用卡紅利積點查詢 API下行txRepeat.</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW306RRepeat {

    /** 紅利認同碼 X(4) */
    private String bonGrop;
    /** 紅利點數 X(9) */
    private BigDecimal bonBsav;
    /** 紅利正負號 X(1) */
    private String bonsing;
    /** 紅利認同碼名稱 X(12) */
    private String bonName;
    /** 是否可折抵房貸 X(1) */
    private String bonCTYP;
    /** 最近到期點數 X(9) */
    private BigDecimal bonEamt;
    /** 到期日 X(7) */
    private String bonEday;

    /**
     * @return the bonGrop
     */
    public String getBonGrop() {
        return bonGrop;
    }

    /**
     * @param bonGrop
     *            the bonGrop to set
     */
    public void setBonGrop(String bonGrop) {
        this.bonGrop = bonGrop;
    }

    /**
     * @return the bonBsav
     */
    public BigDecimal getBonBsav() {
        return bonBsav;
    }

    /**
     * @param bonBsav
     *            the bonBsav to set
     */
    public void setBonBsav(BigDecimal bonBsav) {
        this.bonBsav = bonBsav;
    }

    /**
     * @return the bonsing
     */
    public String getBonsing() {
        return bonsing;
    }

    /**
     * @param bonsing
     *            the bonsing to set
     */
    public void setBonsing(String bonsing) {
        this.bonsing = bonsing;
    }

    /**
     * @return the bonName
     */
    public String getBonName() {
        return bonName;
    }

    /**
     * @param bonName
     *            the bonName to set
     */
    public void setBonName(String bonName) {
        this.bonName = bonName;
    }

    /**
     * @return the bonCTYP
     */
    public String getBonCTYP() {
        return bonCTYP;
    }

    /**
     * @param bonCTYP
     *            the bonCTYP to set
     */
    public void setBonCTYP(String bonCTYP) {
        this.bonCTYP = bonCTYP;
    }

    /**
     * @return the bonEamt
     */
    public BigDecimal getBonEamt() {
        return bonEamt;
    }

    /**
     * @param bonEamt
     *            the bonEamt to set
     */
    public void setBonEamt(BigDecimal bonEamt) {
        this.bonEamt = bonEamt;
    }

    /**
     * @return the bonEday
     */
    public String getBonEday() {
        return bonEday;
    }

    /**
     * @param bonEday
     *            the bonEday to set
     */
    public void setBonEday(String bonEday) {
        this.bonEday = bonEday;
    }

}
