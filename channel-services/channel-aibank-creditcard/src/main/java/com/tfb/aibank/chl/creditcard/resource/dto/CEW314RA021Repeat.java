package com.tfb.aibank.chl.creditcard.resource.dto;

import java.math.BigDecimal;
import java.util.Date;

//@formatter:off
/**
* @(#)CEW314RA021Repeat.java
* 
* <p>Description:CEW314RA021Repeat API下行txRepeat.</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW314RA021Repeat {
    /** 信用額度 X(9) */
    private BigDecimal aacram;
    /** 結帳日 X(7) */
    private Date aacldy;
    /** 繳款期限 X(7) */
    private Date aalmdy;
    /** 循環利率 X(5) */
    private BigDecimal aaintr;
    /** 利率有效年月 X(5) */
    private String aainym;
    /** 預借現金額度 X(9) */
    private BigDecimal aancam;
    /** 年度累計利息 X(9) */
    private String aaifam;
    /** 正負號 X(1) */
    private String aaifas;
    /** 年度累計費用 X(9) */
    private String aasta2;
    /** 正負號 X(1) */
    private String aastf2;
    /** 帳單分期利率 X(5) */
    private String aadint;
    /** 上期應繳 X(9) */
    private BigDecimal aaolbl;
    /** 正負號 X(1) */
    private String aaolin;
    /** 繳退金額 X(9) */
    private BigDecimal aalpam;
    /** 正負號 X(1) */
    private String aalpin;
    /** 本期調整 X(9) */
    private BigDecimal aanebl;
    /** 正負號 X(1) */
    private String aanein;
    /** 本期新增 X(9) */
    private BigDecimal aacire;
    /** 正負號 X(1) */
    private String aaciin;
    /** 本期應繳 X(9) */
    private BigDecimal aatpay;
    /** 正負號 X(1) */
    private String aatpin;
    /** 最低應繳 X(9) */
    private BigDecimal aaminp;
    /** 正負號 X(1) */
    private String aamiin;
    /** 結帳日循環信用本金 X(9) */
    private String aalsra;
    /** 正負號 X(1) */
    private String aalsrs;

    /**
     * @return the aacram
     */
    public BigDecimal getAacram() {
        return aacram;
    }

    /**
     * @param aacram
     *            the aacram to set
     */
    public void setAacram(BigDecimal aacram) {
        this.aacram = aacram;
    }

    /**
     * @return the aacldy
     */
    public Date getAacldy() {
        return aacldy;
    }

    /**
     * @param aacldy
     *            the aacldy to set
     */
    public void setAacldy(Date aacldy) {
        this.aacldy = aacldy;
    }

    /**
     * @return the aalmdy
     */
    public Date getAalmdy() {
        return aalmdy;
    }

    /**
     * @param aalmdy
     *            the aalmdy to set
     */
    public void setAalmdy(Date aalmdy) {
        this.aalmdy = aalmdy;
    }

    /**
     * @return the aaintr
     */
    public BigDecimal getAaintr() {
        return aaintr;
    }

    /**
     * @param aaintr
     *            the aaintr to set
     */
    public void setAaintr(BigDecimal aaintr) {
        this.aaintr = aaintr;
    }

    /**
     * @return the aainym
     */
    public String getAainym() {
        return aainym;
    }

    /**
     * @param aainym
     *            the aainym to set
     */
    public void setAainym(String aainym) {
        this.aainym = aainym;
    }

    /**
     * @return the aancam
     */
    public BigDecimal getAancam() {
        return aancam;
    }

    /**
     * @param aancam
     *            the aancam to set
     */
    public void setAancam(BigDecimal aancam) {
        this.aancam = aancam;
    }

    /**
     * @return the aaifam
     */
    public String getAaifam() {
        return aaifam;
    }

    /**
     * @param aaifam
     *            the aaifam to set
     */
    public void setAaifam(String aaifam) {
        this.aaifam = aaifam;
    }

    /**
     * @return the aaifas
     */
    public String getAaifas() {
        return aaifas;
    }

    /**
     * @param aaifas
     *            the aaifas to set
     */
    public void setAaifas(String aaifas) {
        this.aaifas = aaifas;
    }

    /**
     * @return the aasta2
     */
    public String getAasta2() {
        return aasta2;
    }

    /**
     * @param aasta2
     *            the aasta2 to set
     */
    public void setAasta2(String aasta2) {
        this.aasta2 = aasta2;
    }

    /**
     * @return the aastf2
     */
    public String getAastf2() {
        return aastf2;
    }

    /**
     * @param aastf2
     *            the aastf2 to set
     */
    public void setAastf2(String aastf2) {
        this.aastf2 = aastf2;
    }

    /**
     * @return the aadint
     */
    public String getAadint() {
        return aadint;
    }

    /**
     * @param aadint
     *            the aadint to set
     */
    public void setAadint(String aadint) {
        this.aadint = aadint;
    }

    /**
     * @return the aaolbl
     */
    public BigDecimal getAaolbl() {
        return aaolbl;
    }

    /**
     * @param aaolbl
     *            the aaolbl to set
     */
    public void setAaolbl(BigDecimal aaolbl) {
        this.aaolbl = aaolbl;
    }

    /**
     * @return the aaolin
     */
    public String getAaolin() {
        return aaolin;
    }

    /**
     * @param aaolin
     *            the aaolin to set
     */
    public void setAaolin(String aaolin) {
        this.aaolin = aaolin;
    }

    /**
     * @return the aalpam
     */
    public BigDecimal getAalpam() {
        return aalpam;
    }

    /**
     * @param aalpam
     *            the aalpam to set
     */
    public void setAalpam(BigDecimal aalpam) {
        this.aalpam = aalpam;
    }

    /**
     * @return the aalpin
     */
    public String getAalpin() {
        return aalpin;
    }

    /**
     * @param aalpin
     *            the aalpin to set
     */
    public void setAalpin(String aalpin) {
        this.aalpin = aalpin;
    }

    /**
     * @return the aanebl
     */
    public BigDecimal getAanebl() {
        return aanebl;
    }

    /**
     * @param aanebl
     *            the aanebl to set
     */
    public void setAanebl(BigDecimal aanebl) {
        this.aanebl = aanebl;
    }

    /**
     * @return the aanein
     */
    public String getAanein() {
        return aanein;
    }

    /**
     * @param aanein
     *            the aanein to set
     */
    public void setAanein(String aanein) {
        this.aanein = aanein;
    }

    /**
     * @return the aacire
     */
    public BigDecimal getAacire() {
        return aacire;
    }

    /**
     * @param aacire
     *            the aacire to set
     */
    public void setAacire(BigDecimal aacire) {
        this.aacire = aacire;
    }

    /**
     * @return the aaciin
     */
    public String getAaciin() {
        return aaciin;
    }

    /**
     * @param aaciin
     *            the aaciin to set
     */
    public void setAaciin(String aaciin) {
        this.aaciin = aaciin;
    }

    /**
     * @return the aatpay
     */
    public BigDecimal getAatpay() {
        return aatpay;
    }

    /**
     * @param aatpay
     *            the aatpay to set
     */
    public void setAatpay(BigDecimal aatpay) {
        this.aatpay = aatpay;
    }

    /**
     * @return the aatpin
     */
    public String getAatpin() {
        return aatpin;
    }

    /**
     * @param aatpin
     *            the aatpin to set
     */
    public void setAatpin(String aatpin) {
        this.aatpin = aatpin;
    }

    /**
     * @return the aaminp
     */
    public BigDecimal getAaminp() {
        return aaminp;
    }

    /**
     * @param aaminp
     *            the aaminp to set
     */
    public void setAaminp(BigDecimal aaminp) {
        this.aaminp = aaminp;
    }

    /**
     * @return the aamiin
     */
    public String getAamiin() {
        return aamiin;
    }

    /**
     * @param aamiin
     *            the aamiin to set
     */
    public void setAamiin(String aamiin) {
        this.aamiin = aamiin;
    }

    /**
     * @return the aalsra
     */
    public String getAalsra() {
        return aalsra;
    }

    /**
     * @param aalsra
     *            the aalsra to set
     */
    public void setAalsra(String aalsra) {
        this.aalsra = aalsra;
    }

    /**
     * @return the aalsrs
     */
    public String getAalsrs() {
        return aalsrs;
    }

    /**
     * @param aalsrs
     *            the aalsrs to set
     */
    public void setAalsrs(String aalsrs) {
        this.aalsrs = aalsrs;
    }

}
