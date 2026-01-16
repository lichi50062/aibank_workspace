package com.tfb.aibank.chl.creditcard.qu001.model;

import java.math.BigDecimal;

//@formatter:off
/**
* @(#)NCCQQU001BillingDetail.java
* 
* <p>Description:信用卡總覽 紅利回饋類別</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/28, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NCCQQU001BonusReward {
    /** 本期新增 */
    private BigDecimal b5tadd;

    /** 正負號 */
    private String b5asin;

    /**
     * @return the b5tadd
     */
    public BigDecimal getB5tadd() {
        return b5tadd;
    }

    /**
     * @param b5tadd
     *            the b5tadd to set
     */
    public void setB5tadd(BigDecimal b5tadd) {
        this.b5tadd = b5tadd;
    }

    /**
     * @return the b5asin
     */
    public String getB5asin() {
        return b5asin;
    }

    /**
     * @param b5asin
     *            the b5asin to set
     */
    public void setB5asin(String b5asin) {
        this.b5asin = b5asin;
    }

}
