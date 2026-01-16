package com.tfb.aibank.chl.creditcard.resource.dto;

import java.math.BigDecimal;
import java.util.Date;

//@formatter:off
/**
* @(#)CEW314RB100Repeat.java
* 
* <p>Description:CEW314RB100Repeat API下行txRepeat.</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW314RB100Repeat {
    /** 消費日期 X(7) */
    private Date b3phdy;
    /** 消費說明 X(38) */
    private String b3txt0;
    /** 入帳日期 X(7) */
    private Date b3stdy;
    /** 幣別 X(3) */
    private String b3curr;
    /** 外幣日 X(7) */
    private Date b3fcdy;
    /** 外幣金額 X(11) */
    private BigDecimal b3fcam;
    /** 台幣金額 X(9) */
    private BigDecimal b3lcam;
    /** 正負號 X(1) */
    private String b3sing;
    /** 消費地 X(3) */
    private String b3rctr;
    /** 類別 X(2) */
    private String b3type;
    /** 消費說明2 X(70) */
    private String b3txt1;
    /** 虛擬卡號 X(16) */
    private String b3tokn;
    /** 虛擬卡類別 X(1) */
    private String b3tktp;
    /** 有遮蔽卡號 X(16) */
    private String b3cdno;

    /**
     * @return the b3phdy
     */
    public Date getB3phdy() {
        return b3phdy;
    }

    /**
     * @param b3phdy
     *            the b3phdy to set
     */
    public void setB3phdy(Date b3phdy) {
        this.b3phdy = b3phdy;
    }

    /**
     * @return the b3txt0
     */
    public String getB3txt0() {
        return b3txt0;
    }

    /**
     * @param b3txt0
     *            the b3txt0 to set
     */
    public void setB3txt0(String b3txt0) {
        this.b3txt0 = b3txt0;
    }

    /**
     * @return the b3stdy
     */
    public Date getB3stdy() {
        return b3stdy;
    }

    /**
     * @param b3stdy
     *            the b3stdy to set
     */
    public void setB3stdy(Date b3stdy) {
        this.b3stdy = b3stdy;
    }

    /**
     * @return the b3curr
     */
    public String getB3curr() {
        return b3curr;
    }

    /**
     * @param b3curr
     *            the b3curr to set
     */
    public void setB3curr(String b3curr) {
        this.b3curr = b3curr;
    }

    /**
     * @return the b3fcdy
     */
    public Date getB3fcdy() {
        return b3fcdy;
    }

    /**
     * @param b3fcdy
     *            the b3fcdy to set
     */
    public void setB3fcdy(Date b3fcdy) {
        this.b3fcdy = b3fcdy;
    }

    /**
     * @return the b3fcam
     */
    public BigDecimal getB3fcam() {
        return b3fcam;
    }

    /**
     * @param b3fcam
     *            the b3fcam to set
     */
    public void setB3fcam(BigDecimal b3fcam) {
        this.b3fcam = b3fcam;
    }

    /**
     * @return the b3lcam
     */
    public BigDecimal getB3lcam() {
        return b3lcam;
    }

    /**
     * @param b3lcam
     *            the b3lcam to set
     */
    public void setB3lcam(BigDecimal b3lcam) {
        this.b3lcam = b3lcam;
    }

    /**
     * @return the b3sing
     */
    public String getB3sing() {
        return b3sing;
    }

    /**
     * @param b3sing
     *            the b3sing to set
     */
    public void setB3sing(String b3sing) {
        this.b3sing = b3sing;
    }

    /**
     * @return the b3rctr
     */
    public String getB3rctr() {
        return b3rctr;
    }

    /**
     * @param b3rctr
     *            the b3rctr to set
     */
    public void setB3rctr(String b3rctr) {
        this.b3rctr = b3rctr;
    }

    /**
     * @return the b3type
     */
    public String getB3type() {
        return b3type;
    }

    /**
     * @param b3type
     *            the b3type to set
     */
    public void setB3type(String b3type) {
        this.b3type = b3type;
    }

    /**
     * @return the b3txt1
     */
    public String getB3txt1() {
        return b3txt1;
    }

    /**
     * @param b3txt1
     *            the b3txt1 to set
     */
    public void setB3txt1(String b3txt1) {
        this.b3txt1 = b3txt1;
    }

    /**
     * @return the b3tokn
     */
    public String getB3tokn() {
        return b3tokn;
    }

    /**
     * @param b3tokn
     *            the b3tokn to set
     */
    public void setB3tokn(String b3tokn) {
        this.b3tokn = b3tokn;
    }

    /**
     * @return the b3tktp
     */
    public String getB3tktp() {
        return b3tktp;
    }

    /**
     * @param b3tktp
     *            the b3tktp to set
     */
    public void setB3tktp(String b3tktp) {
        this.b3tktp = b3tktp;
    }

    /**
     * @return the b3cdno
     */
    public String getB3cdno() {
        return b3cdno;
    }

    /**
     * @param b3cdno
     *            the b3cdno to set
     */
    public void setB3cdno(String b3cdno) {
        this.b3cdno = b3cdno;
    }

}
