/**
 * 
 */
package com.tfb.aibank.chl.creditcard.qu006.model;

import java.math.BigDecimal;

//@formatter:off
/**
* @(#)NCCQU006010Rs.java
*
* <p>Description:點數回饋中心 功能首頁</p>
*
* <p>Modify History:</p>
* v1.0, 2023/08/10 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NCCQU006010RsBonRepeats {

    /** 紅利認同碼 X(4) */
    private String bonGrop;

    /** 紅利點數 X(9) 含 紅利正負號 X(1) */
    private String bonBsav;

    private BigDecimal bonBsavOriginal;

    /** 紅利認同碼名稱 X(12) */
    private String bonName;

    /** 最近到期點數 X(9) */
    private String bonEamt;

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
    public String getBonBsav() {
        return bonBsav;
    }

    /**
     * @param bonBsav
     *            the bonBsav to set
     */
    public void setBonBsav(String bonBsav) {
        this.bonBsav = bonBsav;
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
     * @return the bonEamt
     */
    public String getBonEamt() {
        return bonEamt;
    }

    /**
     * @param bonEamt
     *            the bonEamt to set
     */
    public void setBonEamt(String bonEamt) {
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

    /**
     * @return the bonBsavOriginal
     */
    public BigDecimal getBonBsavOriginal() {
        return bonBsavOriginal;
    }

    /**
     * @param bonBsavOriginal
     *            the bonBsavOriginal to set
     */
    public void setBonBsavOriginal(BigDecimal bonBsavOriginal) {
        this.bonBsavOriginal = bonBsavOriginal;
    }

}
