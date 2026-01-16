package com.tfb.aibank.chl.creditcard.resource.dto;

import java.math.BigDecimal;
import java.util.Calendar;

//@formatter:off
/**
* @(#)CEW314RB062Repeat.java
* 
* <p>Description:CEW314RB062Repeat API下行txRepeat.</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW314RB062Repeat {
    /** 消費日期 X(7) */
    private Calendar b2phdy;
    /** 消費說明 X(38) */
    private String b2txt0;
    /** 入帳日期 X(7) */
    private Calendar b2stdy;
    /** 台幣金額 X(9) */
    private BigDecimal b2lcam;
    /** 正負號 X(1) */
    private String b2sing;

    /**
     * @return the b2phdy
     */
    public Calendar getB2phdy() {
        return b2phdy;
    }

    /**
     * @param b2phdy
     *            the b2phdy to set
     */
    public void setB2phdy(Calendar b2phdy) {
        this.b2phdy = b2phdy;
    }

    /**
     * @return the b2txt0
     */
    public String getB2txt0() {
        return b2txt0;
    }

    /**
     * @param b2txt0
     *            the b2txt0 to set
     */
    public void setB2txt0(String b2txt0) {
        this.b2txt0 = b2txt0;
    }

    /**
     * @return the b2stdy
     */
    public Calendar getB2stdy() {
        return b2stdy;
    }

    /**
     * @param b2stdy
     *            the b2stdy to set
     */
    public void setB2stdy(Calendar b2stdy) {
        this.b2stdy = b2stdy;
    }

    /**
     * @return the b2lcam
     */
    public BigDecimal getB2lcam() {
        return b2lcam;
    }

    /**
     * @param b2lcam
     *            the b2lcam to set
     */
    public void setB2lcam(BigDecimal b2lcam) {
        this.b2lcam = b2lcam;
    }

    /**
     * @return the b2sing
     */
    public String getB2sing() {
        return b2sing;
    }

    /**
     * @param b2sing
     *            the b2sing to set
     */
    public void setB2sing(String b2sing) {
        this.b2sing = b2sing;
    }

}
