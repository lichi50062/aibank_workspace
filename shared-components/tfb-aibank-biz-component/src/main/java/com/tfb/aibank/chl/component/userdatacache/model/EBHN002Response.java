/**
 * 
 */
package com.tfb.aibank.chl.component.userdatacache.model;

import java.util.List;

//@formatter:off
/**
* @(#)EBHN002Response.java
* 
* <p>Description:EBHN002 房貸可增貸額度</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/10/12 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class EBHN002Response {

    /** 分期型房貸-參考年利率 */
    private String insloanRate;

    /** 分期型房貸-參考年利率加碼值 */
    private String insloanRateAdd;

    /** 分期型房貸-借款期限 */
    private String insloanPeriodTop;

    /** 循環型房貸-參考年利率 */
    private String revloanRate;

    /** 循環型房貸-參考年利率加碼值 */
    private String revloanRateAdd;

    /** 循環型房貸-借款期限 */
    private String revloanPeriodTop;

    /** 分期型房貸-原月付金 */
    private String insloanOriamt;

    /** AO Email */
    private String aoMail;

    /** AO主管Email */
    private String aoManagerMail;

    /** AO姓名 */
    private String aoName;

    /** 申請狀態 */
    private String adjloanDate;

    /** 申請戶數 */
    private String adjloanRate;

    /** 名單資料日期 */
    private String listDate;

    /** 資料筆數 */
    private String count;

    /** 信貸-分期型可(增)貸額度 */
    private String creditAllocation;

    /** 信貸-分期型可(增)貸利率 */
    private String creditRates;

    /** 信貸-循環型可(增)貸額度 */
    private String creditAllocationR;

    /** 信貸-循環型可(增)貸利率 */
    private String creditRatesR;

    /** 筋斗雲房貸名單註記 */
    private String jdyMortgage;

    /** 信貸-循環型可(增)貸利率 */
    private String jdyPloan;

    /** 擔保品清單 */
    private List<EBHN002ResponseRepeat> repeat;

    /**
     * @return the insloanRate
     */
    public String getInsloanRate() {
        return insloanRate;
    }

    /**
     * @param insloanRate
     *            the insloanRate to set
     */
    public void setInsloanRate(String insloanRate) {
        this.insloanRate = insloanRate;
    }

    /**
     * @return the insloanRateAdd
     */
    public String getInsloanRateAdd() {
        return insloanRateAdd;
    }

    /**
     * @param insloanRateAdd
     *            the insloanRateAdd to set
     */
    public void setInsloanRateAdd(String insloanRateAdd) {
        this.insloanRateAdd = insloanRateAdd;
    }

    /**
     * @return the insloanPeriodTop
     */
    public String getInsloanPeriodTop() {
        return insloanPeriodTop;
    }

    /**
     * @param insloanPeriodTop
     *            the insloanPeriodTop to set
     */
    public void setInsloanPeriodTop(String insloanPeriodTop) {
        this.insloanPeriodTop = insloanPeriodTop;
    }

    /**
     * @return the revloanRate
     */
    public String getRevloanRate() {
        return revloanRate;
    }

    /**
     * @param revloanRate
     *            the revloanRate to set
     */
    public void setRevloanRate(String revloanRate) {
        this.revloanRate = revloanRate;
    }

    /**
     * @return the revloanRateAdd
     */
    public String getRevloanRateAdd() {
        return revloanRateAdd;
    }

    /**
     * @param revloanRateAdd
     *            the revloanRateAdd to set
     */
    public void setRevloanRateAdd(String revloanRateAdd) {
        this.revloanRateAdd = revloanRateAdd;
    }

    /**
     * @return the revloanPeriodTop
     */
    public String getRevloanPeriodTop() {
        return revloanPeriodTop;
    }

    /**
     * @param revloanPeriodTop
     *            the revloanPeriodTop to set
     */
    public void setRevloanPeriodTop(String revloanPeriodTop) {
        this.revloanPeriodTop = revloanPeriodTop;
    }

    /**
     * @return the insloanOriamt
     */
    public String getInsloanOriamt() {
        return insloanOriamt;
    }

    /**
     * @param insloanOriamt
     *            the insloanOriamt to set
     */
    public void setInsloanOriamt(String insloanOriamt) {
        this.insloanOriamt = insloanOriamt;
    }

    /**
     * @return the aoMail
     */
    public String getAoMail() {
        return aoMail;
    }

    /**
     * @param aoMail
     *            the aoMail to set
     */
    public void setAoMail(String aoMail) {
        this.aoMail = aoMail;
    }

    /**
     * @return the aoManagerMail
     */
    public String getAoManagerMail() {
        return aoManagerMail;
    }

    /**
     * @param aoManagerMail
     *            the aoManagerMail to set
     */
    public void setAoManagerMail(String aoManagerMail) {
        this.aoManagerMail = aoManagerMail;
    }

    /**
     * @return the aoName
     */
    public String getAoName() {
        return aoName;
    }

    /**
     * @param aoName
     *            the aoName to set
     */
    public void setAoName(String aoName) {
        this.aoName = aoName;
    }

    /**
     * @return the adjloanDate
     */
    public String getAdjloanDate() {
        return adjloanDate;
    }

    /**
     * @param adjloanDate
     *            the adjloanDate to set
     */
    public void setAdjloanDate(String adjloanDate) {
        this.adjloanDate = adjloanDate;
    }

    /**
     * @return the adjloanRate
     */
    public String getAdjloanRate() {
        return adjloanRate;
    }

    /**
     * @param adjloanRate
     *            the adjloanRate to set
     */
    public void setAdjloanRate(String adjloanRate) {
        this.adjloanRate = adjloanRate;
    }

    /**
     * @return the listDate
     */
    public String getListDate() {
        return listDate;
    }

    /**
     * @param listDate
     *            the listDate to set
     */
    public void setListDate(String listDate) {
        this.listDate = listDate;
    }

    /**
     * @return the count
     */
    public String getCount() {
        return count;
    }

    /**
     * @param count
     *            the count to set
     */
    public void setCount(String count) {
        this.count = count;
    }

    /**
     * @return the creditAllocation
     */
    public String getCreditAllocation() {
        return creditAllocation;
    }

    /**
     * @param creditAllocation
     *            the creditAllocation to set
     */
    public void setCreditAllocation(String creditAllocation) {
        this.creditAllocation = creditAllocation;
    }

    /**
     * @return the creditRates
     */
    public String getCreditRates() {
        return creditRates;
    }

    /**
     * @param creditRates
     *            the creditRates to set
     */
    public void setCreditRates(String creditRates) {
        this.creditRates = creditRates;
    }

    /**
     * @return the creditAllocationR
     */
    public String getCreditAllocationR() {
        return creditAllocationR;
    }

    /**
     * @param creditAllocationR
     *            the creditAllocationR to set
     */
    public void setCreditAllocationR(String creditAllocationR) {
        this.creditAllocationR = creditAllocationR;
    }

    /**
     * @return the creditRatesR
     */
    public String getCreditRatesR() {
        return creditRatesR;
    }

    /**
     * @param creditRatesR
     *            the creditRatesR to set
     */
    public void setCreditRatesR(String creditRatesR) {
        this.creditRatesR = creditRatesR;
    }

    /**
     * @return the jdyMortgage
     */
    public String getJdyMortgage() {
        return jdyMortgage;
    }

    /**
     * @param jdyMortgage
     *            the jdyMortgage to set
     */
    public void setJdyMortgage(String jdyMortgage) {
        this.jdyMortgage = jdyMortgage;
    }

    /**
     * @return the jdyPloan
     */
    public String getJdyPloan() {
        return jdyPloan;
    }

    /**
     * @param jdyPloan
     *            the jdyPloan to set
     */
    public void setJdyPloan(String jdyPloan) {
        this.jdyPloan = jdyPloan;
    }

    /**
     * @return the repeat
     */
    public List<EBHN002ResponseRepeat> getRepeat() {
        return repeat;
    }

    /**
     * @param repeat
     *            the repeat to set
     */
    public void setRepeat(List<EBHN002ResponseRepeat> repeat) {
        this.repeat = repeat;
    }

}