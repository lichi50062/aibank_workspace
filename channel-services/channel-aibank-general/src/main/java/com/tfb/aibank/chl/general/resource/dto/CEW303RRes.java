package com.tfb.aibank.chl.general.resource.dto;

import java.math.BigDecimal;
import java.util.Date;

// @formatter:off
/**
 * @(#)CEW303RRes.java
 * 
 * <p>Description:CEW303R 信用卡帳號資訊 API下行欄位</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/23, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

/** 信用卡帳號資訊 */
public class CEW303RRes {

    /** 歸戶信用額度 */
    private BigDecimal acctIdCram;

    /** 歸戶可用餘額 */
    private BigDecimal acctIdPcbl;

    /** 國內預借現金信用額度 */
    private BigDecimal acctIdTmmh;

    /** 國內預借現金可用餘額 */
    private BigDecimal acctIdCcbl;

    /** 本期應繳金額 */
    private BigDecimal acctIdSbal;

    /** 本期最低應繳金額 */
    private BigDecimal acctIdMinp;

    /** 本月帳單結帳日 */
    private Date acctIdCldy;

    /** 本月帳單繳款截止日 */
    private Date acctIdDudy;

    /** 最近繳款日 */
    private Date acctIdLpdy;

    /** 本期已繳金額 */
    private BigDecimal acctIdPayd;

    /** 代扣行(中文) */
    private String acctIdAcbh;

    /** 繳款帳號(自動扣款帳號) */
    private String acctIdAcno;

    /** 國外預借現金信用額度 */
    private BigDecimal acctIdCurTmmh;

    /** 國外預借現金可用餘額 */
    private BigDecimal acctIdCURCcbl;

    /** 本期帳單剩餘應繳金額 */
    private BigDecimal acctIdDpayd;

    /** 下期結帳日前已請款消費金額 */
    private BigDecimal acctIdub;

    /** 分期未到期餘額 */
    private BigDecimal acctIdoreS;

    /** 循環信用利率 */
    private String acctIdintr;

    /** 循環利率適用到期年月 */
    private String acctIdintrYM;

    /** 帳單分期利率 */
    private String acctIdDintr;

    /** 帳單分期利率適用年月 */
    private String acctIdDintrYM;

    /** 遲繳狀況 */
    private String acctIdDlusts;

    /** 自行繳款帳號 */
    private String acctIdFAcno;

    /** 轉帳帳號 ID 英文轉換數字 */
    private String acctIdChNum;

    /**
     * @return the acctIdCram
     */
    public BigDecimal getAcctIdCram() {
        return acctIdCram;
    }

    /**
     * @param acctIdCram
     *            the acctIdCram to set
     */
    public void setAcctIdCram(BigDecimal acctIdCram) {
        this.acctIdCram = acctIdCram;
    }

    /**
     * @return the acctIdPcbl
     */
    public BigDecimal getAcctIdPcbl() {
        return acctIdPcbl;
    }

    /**
     * @param acctIdPcbl
     *            the acctIdPcbl to set
     */
    public void setAcctIdPcbl(BigDecimal acctIdPcbl) {
        this.acctIdPcbl = acctIdPcbl;
    }

    /**
     * @return the acctIdTmmh
     */
    public BigDecimal getAcctIdTmmh() {
        return acctIdTmmh;
    }

    /**
     * @param acctIdTmmh
     *            the acctIdTmmh to set
     */
    public void setAcctIdTmmh(BigDecimal acctIdTmmh) {
        this.acctIdTmmh = acctIdTmmh;
    }

    /**
     * @return the acctIdCcbl
     */
    public BigDecimal getAcctIdCcbl() {
        return acctIdCcbl;
    }

    /**
     * @param acctIdCcbl
     *            the acctIdCcbl to set
     */
    public void setAcctIdCcbl(BigDecimal acctIdCcbl) {
        this.acctIdCcbl = acctIdCcbl;
    }

    /**
     * @return the acctIdSbal
     */
    public BigDecimal getAcctIdSbal() {
        return acctIdSbal;
    }

    /**
     * @param acctIdSbal
     *            the acctIdSbal to set
     */
    public void setAcctIdSbal(BigDecimal acctIdSbal) {
        this.acctIdSbal = acctIdSbal;
    }

    /**
     * @return the acctIdMinp
     */
    public BigDecimal getAcctIdMinp() {
        return acctIdMinp;
    }

    /**
     * @param acctIdMinp
     *            the acctIdMinp to set
     */
    public void setAcctIdMinp(BigDecimal acctIdMinp) {
        this.acctIdMinp = acctIdMinp;
    }

    /**
     * @return the acctIdCldy
     */
    public Date getAcctIdCldy() {
        return acctIdCldy;
    }

    /**
     * @param acctIdCldy
     *            the acctIdCldy to set
     */
    public void setAcctIdCldy(Date acctIdCldy) {
        this.acctIdCldy = acctIdCldy;
    }

    /**
     * @return the acctIdDudy
     */
    public Date getAcctIdDudy() {
        return acctIdDudy;
    }

    /**
     * @param acctIdDudy
     *            the acctIdDudy to set
     */
    public void setAcctIdDudy(Date acctIdDudy) {
        this.acctIdDudy = acctIdDudy;
    }

    /**
     * @return the acctIdLpdy
     */
    public Date getAcctIdLpdy() {
        return acctIdLpdy;
    }

    /**
     * @param acctIdLpdy
     *            the acctIdLpdy to set
     */
    public void setAcctIdLpdy(Date acctIdLpdy) {
        this.acctIdLpdy = acctIdLpdy;
    }

    /**
     * @return the acctIdPayd
     */
    public BigDecimal getAcctIdPayd() {
        return acctIdPayd;
    }

    /**
     * @param acctIdPayd
     *            the acctIdPayd to set
     */
    public void setAcctIdPayd(BigDecimal acctIdPayd) {
        this.acctIdPayd = acctIdPayd;
    }

    /**
     * @return the acctIdAcbh
     */
    public String getAcctIdAcbh() {
        return acctIdAcbh;
    }

    /**
     * @param acctIdAcbh
     *            the acctIdAcbh to set
     */
    public void setAcctIdAcbh(String acctIdAcbh) {
        this.acctIdAcbh = acctIdAcbh;
    }

    /**
     * @return the acctIdAcno
     */
    public String getAcctIdAcno() {
        return acctIdAcno;
    }

    /**
     * @param acctIdAcno
     *            the acctIdAcno to set
     */
    public void setAcctIdAcno(String acctIdAcno) {
        this.acctIdAcno = acctIdAcno;
    }

    /**
     * @return the acctIdCurTmmh
     */
    public BigDecimal getAcctIdCurTmmh() {
        return acctIdCurTmmh;
    }

    /**
     * @param acctIdCurTmmh
     *            the acctIdCurTmmh to set
     */
    public void setAcctIdCurTmmh(BigDecimal acctIdCurTmmh) {
        this.acctIdCurTmmh = acctIdCurTmmh;
    }

    /**
     * @return the acctIdCURCcbl
     */
    public BigDecimal getAcctIdCURCcbl() {
        return acctIdCURCcbl;
    }

    /**
     * @param acctIdCURCcbl
     *            the acctIdCURCcbl to set
     */
    public void setAcctIdCURCcbl(BigDecimal acctIdCURCcbl) {
        this.acctIdCURCcbl = acctIdCURCcbl;
    }

    /**
     * @return the acctIdDpayd
     */
    public BigDecimal getAcctIdDpayd() {
        return acctIdDpayd;
    }

    /**
     * @param acctIdDpayd
     *            the acctIdDpayd to set
     */
    public void setAcctIdDpayd(BigDecimal acctIdDpayd) {
        this.acctIdDpayd = acctIdDpayd;
    }

    /**
     * @return the acctIdub
     */
    public BigDecimal getAcctIdub() {
        return acctIdub;
    }

    /**
     * @param acctIdub
     *            the acctIdub to set
     */
    public void setAcctIdub(BigDecimal acctIdub) {
        this.acctIdub = acctIdub;
    }

    /**
     * @return the acctIdoreS
     */
    public BigDecimal getAcctIdoreS() {
        return acctIdoreS;
    }

    /**
     * @param acctIdoreS
     *            the acctIdoreS to set
     */
    public void setAcctIdoreS(BigDecimal acctIdoreS) {
        this.acctIdoreS = acctIdoreS;
    }

    /**
     * @return the acctIdintr
     */
    public String getAcctIdintr() {
        return acctIdintr;
    }

    /**
     * @param acctIdintr
     *            the acctIdintr to set
     */
    public void setAcctIdintr(String acctIdintr) {
        this.acctIdintr = acctIdintr;
    }

    /**
     * @return the acctIdintrYM
     */
    public String getAcctIdintrYM() {
        return acctIdintrYM;
    }

    /**
     * @param acctIdintrYM
     *            the acctIdintrYM to set
     */
    public void setAcctIdintrYM(String acctIdintrYM) {
        this.acctIdintrYM = acctIdintrYM;
    }

    /**
     * @return the acctIdDintr
     */
    public String getAcctIdDintr() {
        return acctIdDintr;
    }

    /**
     * @param acctIdDintr
     *            the acctIdDintr to set
     */
    public void setAcctIdDintr(String acctIdDintr) {
        this.acctIdDintr = acctIdDintr;
    }

    /**
     * @return the acctIdDintrYM
     */
    public String getAcctIdDintrYM() {
        return acctIdDintrYM;
    }

    /**
     * @param acctIdDintrYM
     *            the acctIdDintrYM to set
     */
    public void setAcctIdDintrYM(String acctIdDintrYM) {
        this.acctIdDintrYM = acctIdDintrYM;
    }

    /**
     * @return the acctIdDlusts
     */
    public String getAcctIdDlusts() {
        return acctIdDlusts;
    }

    /**
     * @param acctIdDlusts
     *            the acctIdDlusts to set
     */
    public void setAcctIdDlusts(String acctIdDlusts) {
        this.acctIdDlusts = acctIdDlusts;
    }

    /**
     * @return the acctIdFAcno
     */
    public String getAcctIdFAcno() {
        return acctIdFAcno;
    }

    /**
     * @param acctIdFAcno
     *            the acctIdFAcno to set
     */
    public void setAcctIdFAcno(String acctIdFAcno) {
        this.acctIdFAcno = acctIdFAcno;
    }

    /**
     * @return the acctIdChNum
     */
    public String getAcctIdChNum() {
        return acctIdChNum;
    }

    /**
     * @param acctIdChNum
     *            the acctIdChNum to set
     */
    public void setAcctIdChNum(String acctIdChNum) {
        this.acctIdChNum = acctIdChNum;
    }

}
