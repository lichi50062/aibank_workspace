/**
 * 
 */
package com.tfb.aibank.chl.component.userdatacache.model;

//@formatter:off
/**
* @(#)EBHN002Response.java
* 
* <p>Description:EBLN010 信貸速貸通</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/10/12 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class EBLN010Response {

    /** 客戶姓名 */
    private String custNm;

    /** 是否符合速貸通資格 */
    private String applyFlag;

    /** 可借款金額 */
    private String loanAmt;

    /** 借款期間(總期間) */
    private String loanPeriodYear;

    /** 帳戶管理費 */
    private String feeAmt;

    /** 第一段期數 */
    private String loanPeriod;

    /** 第一段計息方式 */
    private String loanAdjKind;

    /** 第一段借款利率 */
    private String loanRate;

    /** 第一段每月應繳金額 */
    private String monthPay;

    /** 第二段期數 */
    private String loanPeriod2;

    /** 第二段計息方式 */
    private String loanAdjKind2;

    /** 第二段借款利率 */
    private String loanRate2;

    /** 第二段每月應繳金額 */
    private String monthPay2;

    /** 是否限制清償期間 */
    private String prepayFlag;

    /** 年收入 */
    private String annualIncome;

    /** 客層 */
    private String tstCustLevel;

    /** 信用評等 */
    private String creditLvl;

    /** 產業別 */
    private String compCat;

    /** 行業別 */
    private String icCode;

    /** 職稱別 */
    private String crCode;

    /** 利率優惠說明 */
    private String rateDesc;

    /** 帳戶管理費優惠說明 */
    private String chargeDesc;

    /** 產品分類 */
    private String prodCat;

    /** 專案別 */
    private String projCode;

    /** 專案代號 */
    private String cardProjCode;

    /** 專案細項 */
    private String cardProjSubcode;

    /** 總費用年百分率 */
    private String apr;

    /**
     * @return the custNm
     */
    public String getCustNm() {
        return custNm;
    }

    /**
     * @param custNm
     *            the custNm to set
     */
    public void setCustNm(String custNm) {
        this.custNm = custNm;
    }

    /**
     * @return the applyFlag
     */
    public String getApplyFlag() {
        return applyFlag;
    }

    /**
     * @param applyFlag
     *            the applyFlag to set
     */
    public void setApplyFlag(String applyFlag) {
        this.applyFlag = applyFlag;
    }

    /**
     * @return the loanAmt
     */
    public String getLoanAmt() {
        return loanAmt;
    }

    /**
     * @param loanAmt
     *            the loanAmt to set
     */
    public void setLoanAmt(String loanAmt) {
        this.loanAmt = loanAmt;
    }

    /**
     * @return the loanPeriodYear
     */
    public String getLoanPeriodYear() {
        return loanPeriodYear;
    }

    /**
     * @param loanPeriodYear
     *            the loanPeriodYear to set
     */
    public void setLoanPeriodYear(String loanPeriodYear) {
        this.loanPeriodYear = loanPeriodYear;
    }

    /**
     * @return the feeAmt
     */
    public String getFeeAmt() {
        return feeAmt;
    }

    /**
     * @param feeAmt
     *            the feeAmt to set
     */
    public void setFeeAmt(String feeAmt) {
        this.feeAmt = feeAmt;
    }

    /**
     * @return the loanPeriod
     */
    public String getLoanPeriod() {
        return loanPeriod;
    }

    /**
     * @param loanPeriod
     *            the loanPeriod to set
     */
    public void setLoanPeriod(String loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    /**
     * @return the loanAdjKind
     */
    public String getLoanAdjKind() {
        return loanAdjKind;
    }

    /**
     * @param loanAdjKind
     *            the loanAdjKind to set
     */
    public void setLoanAdjKind(String loanAdjKind) {
        this.loanAdjKind = loanAdjKind;
    }

    /**
     * @return the loanRate
     */
    public String getLoanRate() {
        return loanRate;
    }

    /**
     * @param loanRate
     *            the loanRate to set
     */
    public void setLoanRate(String loanRate) {
        this.loanRate = loanRate;
    }

    /**
     * @return the monthPay
     */
    public String getMonthPay() {
        return monthPay;
    }

    /**
     * @param monthPay
     *            the monthPay to set
     */
    public void setMonthPay(String monthPay) {
        this.monthPay = monthPay;
    }

    /**
     * @return the loanPeriod2
     */
    public String getLoanPeriod2() {
        return loanPeriod2;
    }

    /**
     * @param loanPeriod2
     *            the loanPeriod2 to set
     */
    public void setLoanPeriod2(String loanPeriod2) {
        this.loanPeriod2 = loanPeriod2;
    }

    /**
     * @return the loanAdjKind2
     */
    public String getLoanAdjKind2() {
        return loanAdjKind2;
    }

    /**
     * @param loanAdjKind2
     *            the loanAdjKind2 to set
     */
    public void setLoanAdjKind2(String loanAdjKind2) {
        this.loanAdjKind2 = loanAdjKind2;
    }

    /**
     * @return the loanRate2
     */
    public String getLoanRate2() {
        return loanRate2;
    }

    /**
     * @param loanRate2
     *            the loanRate2 to set
     */
    public void setLoanRate2(String loanRate2) {
        this.loanRate2 = loanRate2;
    }

    /**
     * @return the monthPay2
     */
    public String getMonthPay2() {
        return monthPay2;
    }

    /**
     * @param monthPay2
     *            the monthPay2 to set
     */
    public void setMonthPay2(String monthPay2) {
        this.monthPay2 = monthPay2;
    }

    /**
     * @return the prepayFlag
     */
    public String getPrepayFlag() {
        return prepayFlag;
    }

    /**
     * @param prepayFlag
     *            the prepayFlag to set
     */
    public void setPrepayFlag(String prepayFlag) {
        this.prepayFlag = prepayFlag;
    }

    /**
     * @return the annualIncome
     */
    public String getAnnualIncome() {
        return annualIncome;
    }

    /**
     * @param annualIncome
     *            the annualIncome to set
     */
    public void setAnnualIncome(String annualIncome) {
        this.annualIncome = annualIncome;
    }

    /**
     * @return the tstCustLevel
     */
    public String getTstCustLevel() {
        return tstCustLevel;
    }

    /**
     * @param tstCustLevel
     *            the tstCustLevel to set
     */
    public void setTstCustLevel(String tstCustLevel) {
        this.tstCustLevel = tstCustLevel;
    }

    /**
     * @return the creditLvl
     */
    public String getCreditLvl() {
        return creditLvl;
    }

    /**
     * @param creditLvl
     *            the creditLvl to set
     */
    public void setCreditLvl(String creditLvl) {
        this.creditLvl = creditLvl;
    }

    /**
     * @return the compCat
     */
    public String getCompCat() {
        return compCat;
    }

    /**
     * @param compCat
     *            the compCat to set
     */
    public void setCompCat(String compCat) {
        this.compCat = compCat;
    }

    /**
     * @return the icCode
     */
    public String getIcCode() {
        return icCode;
    }

    /**
     * @param icCode
     *            the icCode to set
     */
    public void setIcCode(String icCode) {
        this.icCode = icCode;
    }

    /**
     * @return the crCode
     */
    public String getCrCode() {
        return crCode;
    }

    /**
     * @param crCode
     *            the crCode to set
     */
    public void setCrCode(String crCode) {
        this.crCode = crCode;
    }

    /**
     * @return the rateDesc
     */
    public String getRateDesc() {
        return rateDesc;
    }

    /**
     * @param rateDesc
     *            the rateDesc to set
     */
    public void setRateDesc(String rateDesc) {
        this.rateDesc = rateDesc;
    }

    /**
     * @return the chargeDesc
     */
    public String getChargeDesc() {
        return chargeDesc;
    }

    /**
     * @param chargeDesc
     *            the chargeDesc to set
     */
    public void setChargeDesc(String chargeDesc) {
        this.chargeDesc = chargeDesc;
    }

    /**
     * @return the prodCat
     */
    public String getProdCat() {
        return prodCat;
    }

    /**
     * @param prodCat
     *            the prodCat to set
     */
    public void setProdCat(String prodCat) {
        this.prodCat = prodCat;
    }

    /**
     * @return the projCode
     */
    public String getProjCode() {
        return projCode;
    }

    /**
     * @param projCode
     *            the projCode to set
     */
    public void setProjCode(String projCode) {
        this.projCode = projCode;
    }

    /**
     * @return the cardProjCode
     */
    public String getCardProjCode() {
        return cardProjCode;
    }

    /**
     * @param cardProjCode
     *            the cardProjCode to set
     */
    public void setCardProjCode(String cardProjCode) {
        this.cardProjCode = cardProjCode;
    }

    /**
     * @return the cardProjSubcode
     */
    public String getCardProjSubcode() {
        return cardProjSubcode;
    }

    /**
     * @param cardProjSubcode
     *            the cardProjSubcode to set
     */
    public void setCardProjSubcode(String cardProjSubcode) {
        this.cardProjSubcode = cardProjSubcode;
    }

    /**
     * @return the apr
     */
    public String getApr() {
        return apr;
    }

    /**
     * @param apr
     *            the apr to set
     */
    public void setApr(String apr) {
        this.apr = apr;
    }

}
