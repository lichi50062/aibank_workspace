package com.tfb.aibank.chl.creditcard.qu001.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NCCQU001030Rs.java
* 
* <p>Description:信用卡總覽 帳單詳細頁 RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCQU001030Rs implements RsData {
    /** 當期 */
    private boolean currentPeriod;
    /** 帳單月份 */
    private int month;
    /** 本期應繳 X(9) */
    private BigDecimal aatpay;
    /** 結帳日 X(7) */
    private Date aacldy;
    /** 本期帳單剩餘應繳金額 X(9) */
    private BigDecimal acctIdDpayd;
    /** 最低應繳 X(9) */
    private BigDecimal aaminp;
    /** 正負號 X(1) */
    private String aamiin;
    /** 繳款期限 X(7) */
    private Date aalmdy;
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
    /** 本期已繳金額 X(9) */
    private BigDecimal acctIdPayd;
    /** 最近繳款日 X(7) */
    private Date acctIdLpdy;
    /** 自行繳款帳號 X(14) */
    private String acctIdFAcno;
    /** 轉帳帳號 ID 英文轉換數字 X(2) */
    private String acctIdChNum;
    /** 循環利率 X(5) */
    private BigDecimal aaintr;
    /** 帳單分期利率 X(5) */
    private String aadint;

    /** CEW314R 正卡 正負號 */
    private String aatpin;

    /** 是否帳單分期申請中 */
    private Boolean isBillInstallmentApply;

    public boolean isCurrentPeriod() {
        return currentPeriod;
    }

    public void setCurrentPeriod(boolean currentPeriod) {
        this.currentPeriod = currentPeriod;
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
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @param month
     *            the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    public String getAatpin() {
        return aatpin;
    }

    public void setAatpin(String aatpin) {
        this.aatpin = aatpin;
    }

    /**
     * @return the isBillInstallmentApply
     */
    public Boolean getIsBillInstallmentApply() {
        return isBillInstallmentApply;
    }

    /**
     * @param isBillInstallmentApply
     *            the isBillInstallmentApply to set
     */
    public void setIsBillInstallmentApply(Boolean isBillInstallmentApply) {
        this.isBillInstallmentApply = isBillInstallmentApply;
    }

}
