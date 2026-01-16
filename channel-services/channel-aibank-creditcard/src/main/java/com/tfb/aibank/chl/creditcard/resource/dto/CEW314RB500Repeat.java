package com.tfb.aibank.chl.creditcard.resource.dto;

import java.math.BigDecimal;
import java.util.Calendar;

//@formatter:off
/**
* @(#)CEW314RB500Repeat.java
* 
* <p>Description:CEW314RB500Repeat API下行txRepeat.</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW314RB500Repeat {
    /** 說明 X(16) */
    private String b5txt0;
    /** 上期結餘 X(9) */
    private BigDecimal b5lbal;
    /** 本期使用 X(9) */
    private BigDecimal b5tuse;
    /** 本期新增 X(9) */
    private BigDecimal b5tadd;
    /** 調整點數 X(9) */
    private BigDecimal b5tadj;
    /** 本期結餘 X(9) */
    private BigDecimal b5tbal;
    /** 正負號 X(1) */
    private String b5bsin;
    /** 正負號 X(1) */
    private String b5usin;
    /** 正負號 X(1) */
    private String b5asin;
    /** 正負號 X(1) */
    private String b5jsin;
    /** 正負號 X(1) */
    private String b5tsin;
    /** 最近到期點數 X(9) */
    private BigDecimal b5exbo;
    /** 到期日 X(7) */
    private Calendar b5exdy;

    /**
     * @return the b5txt0
     */
    public String getB5txt0() {
        return b5txt0;
    }

    /**
     * @param b5txt0
     *            the b5txt0 to set
     */
    public void setB5txt0(String b5txt0) {
        this.b5txt0 = b5txt0;
    }

    /**
     * @return the b5lbal
     */
    public BigDecimal getB5lbal() {
        return b5lbal;
    }

    /**
     * @param b5lbal
     *            the b5lbal to set
     */
    public void setB5lbal(BigDecimal b5lbal) {
        this.b5lbal = b5lbal;
    }

    /**
     * @return the b5tuse
     */
    public BigDecimal getB5tuse() {
        return b5tuse;
    }

    /**
     * @param b5tuse
     *            the b5tuse to set
     */
    public void setB5tuse(BigDecimal b5tuse) {
        this.b5tuse = b5tuse;
    }

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
     * @return the b5tadj
     */
    public BigDecimal getB5tadj() {
        return b5tadj;
    }

    /**
     * @param b5tadj
     *            the b5tadj to set
     */
    public void setB5tadj(BigDecimal b5tadj) {
        this.b5tadj = b5tadj;
    }

    /**
     * @return the b5tbal
     */
    public BigDecimal getB5tbal() {
        return b5tbal;
    }

    /**
     * @param b5tbal
     *            the b5tbal to set
     */
    public void setB5tbal(BigDecimal b5tbal) {
        this.b5tbal = b5tbal;
    }

    /**
     * @return the b5bsin
     */
    public String getB5bsin() {
        return b5bsin;
    }

    /**
     * @param b5bsin
     *            the b5bsin to set
     */
    public void setB5bsin(String b5bsin) {
        this.b5bsin = b5bsin;
    }

    /**
     * @return the b5usin
     */
    public String getB5usin() {
        return b5usin;
    }

    /**
     * @param b5usin
     *            the b5usin to set
     */
    public void setB5usin(String b5usin) {
        this.b5usin = b5usin;
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

    /**
     * @return the b5jsin
     */
    public String getB5jsin() {
        return b5jsin;
    }

    /**
     * @param b5jsin
     *            the b5jsin to set
     */
    public void setB5jsin(String b5jsin) {
        this.b5jsin = b5jsin;
    }

    /**
     * @return the b5tsin
     */
    public String getB5tsin() {
        return b5tsin;
    }

    /**
     * @param b5tsin
     *            the b5tsin to set
     */
    public void setB5tsin(String b5tsin) {
        this.b5tsin = b5tsin;
    }

    /**
     * @return the b5exbo
     */
    public BigDecimal getB5exbo() {
        return b5exbo;
    }

    /**
     * @param b5exbo
     *            the b5exbo to set
     */
    public void setB5exbo(BigDecimal b5exbo) {
        this.b5exbo = b5exbo;
    }

    /**
     * @return the b5exdy
     */
    public Calendar getB5exdy() {
        return b5exdy;
    }

    /**
     * @param b5exdy
     *            the b5exdy to set
     */
    public void setB5exdy(Calendar b5exdy) {
        this.b5exdy = b5exdy;
    }

}
