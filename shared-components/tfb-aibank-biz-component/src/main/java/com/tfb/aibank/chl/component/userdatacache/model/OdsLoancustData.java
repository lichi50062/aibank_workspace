/**
 * 
 */
package com.tfb.aibank.chl.component.userdatacache.model;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author john
 *
 */
public class OdsLoancustData {

    private static final long serialVersionUID = 1L;

    /**
     * AIB_最高可貸額度(元)
     */
    @Schema(description = "AIB_最高可貸額度(元)")
    private Long aibAmt;

    /**
     * AIB_優惠方案迄日
     */
    @Schema(description = "AIB_優惠方案迄日")
    private String aibEdate;

    /**
     * AIB_名單分類
     */
    @Schema(description = "AIB_名單分類")
    private Integer aibFlag;

    /**
     * AIB_借款期間(總期間)
     */
    @Schema(description = "AIB_借款期間(總期間)")
    private Integer aibPeriod;

    /**
     * AIB_分期攤還區之借款利率(首N期固定)
     */
    @Schema(description = "AIB_分期攤還區之借款利率(首N期固定)")
    private BigDecimal aibRate1;

    /**
     * AIB_分期型第一段期數
     */
    @Schema(description = "AIB_分期型第一段期數")
    private Integer aibRate1Period;

    /**
     * AIB_分期攤還區之借款利率(第N+1期後即時機動)
     */
    @Schema(description = "AIB_分期攤還區之借款利率(第N+1期後即時機動)")
    private BigDecimal aibRate2;

    /**
     * AIB_帳戶管理費
     */
    @Schema(description = "AIB_帳戶管理費")
    private Integer aibServiceCharge;

    /**
     * 客戶ID
     */
    @Schema(description = "客戶ID")
    private String custId;

    /**
     * 房貸_額度
     */
    @Schema(description = "房貸_額度")
    private BigDecimal hlClmAmt;

    /**
     * 名單分類
     */
    @Schema(description = "名單分類")
    private String listOrder;

    /**
     * 信貸_速貸通額度
     */
    @Schema(description = "信貸_速貸通額度")
    private BigDecimal plExpressAmt;

    /**
     * 信貸_新核卡額度
     */
    @Schema(description = "信貸_新核卡額度")
    private BigDecimal plGm5Amt;

    /**
     * 信貸_觔斗雲額度
     */
    @Schema(description = "信貸_觔斗雲額度")
    private BigDecimal plJdyAmt;

    /**
     * 資料日
     */
    @Schema(description = "資料日")
    private Date snapDate;

    /**
     * @return the aibAmt
     */
    public Long getAibAmt() {
        return aibAmt;
    }

    /**
     * @param aibAmt
     *            the aibAmt to set
     */
    public void setAibAmt(Long aibAmt) {
        this.aibAmt = aibAmt;
    }

    /**
     * @return the aibEdate
     */
    public String getAibEdate() {
        return aibEdate;
    }

    /**
     * @param aibEdate
     *            the aibEdate to set
     */
    public void setAibEdate(String aibEdate) {
        this.aibEdate = aibEdate;
    }

    /**
     * @return the aibFlag
     */
    public Integer getAibFlag() {
        return aibFlag;
    }

    /**
     * @param aibFlag
     *            the aibFlag to set
     */
    public void setAibFlag(Integer aibFlag) {
        this.aibFlag = aibFlag;
    }

    /**
     * @return the aibPeriod
     */
    public Integer getAibPeriod() {
        return aibPeriod;
    }

    /**
     * @param aibPeriod
     *            the aibPeriod to set
     */
    public void setAibPeriod(Integer aibPeriod) {
        this.aibPeriod = aibPeriod;
    }

    /**
     * @return the aibRate1
     */
    public BigDecimal getAibRate1() {
        return aibRate1;
    }

    /**
     * @param aibRate1
     *            the aibRate1 to set
     */
    public void setAibRate1(BigDecimal aibRate1) {
        this.aibRate1 = aibRate1;
    }

    /**
     * @return the aibRate1Period
     */
    public Integer getAibRate1Period() {
        return aibRate1Period;
    }

    /**
     * @param aibRate1Period
     *            the aibRate1Period to set
     */
    public void setAibRate1Period(Integer aibRate1Period) {
        this.aibRate1Period = aibRate1Period;
    }

    /**
     * @return the aibRate2
     */
    public BigDecimal getAibRate2() {
        return aibRate2;
    }

    /**
     * @param aibRate2
     *            the aibRate2 to set
     */
    public void setAibRate2(BigDecimal aibRate2) {
        this.aibRate2 = aibRate2;
    }

    /**
     * @return the aibServiceCharge
     */
    public Integer getAibServiceCharge() {
        return aibServiceCharge;
    }

    /**
     * @param aibServiceCharge
     *            the aibServiceCharge to set
     */
    public void setAibServiceCharge(Integer aibServiceCharge) {
        this.aibServiceCharge = aibServiceCharge;
    }

    /**
     * @return the custId
     */
    public String getCustId() {
        return custId;
    }

    /**
     * @param custId
     *            the custId to set
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    /**
     * @return the hlClmAmt
     */
    public BigDecimal getHlClmAmt() {
        return hlClmAmt;
    }

    /**
     * @param hlClmAmt
     *            the hlClmAmt to set
     */
    public void setHlClmAmt(BigDecimal hlClmAmt) {
        this.hlClmAmt = hlClmAmt;
    }

    /**
     * @return the listOrder
     */
    public String getListOrder() {
        return listOrder;
    }

    /**
     * @param listOrder
     *            the listOrder to set
     */
    public void setListOrder(String listOrder) {
        this.listOrder = listOrder;
    }

    /**
     * @return the plExpressAmt
     */
    public BigDecimal getPlExpressAmt() {
        return plExpressAmt;
    }

    /**
     * @param plExpressAmt
     *            the plExpressAmt to set
     */
    public void setPlExpressAmt(BigDecimal plExpressAmt) {
        this.plExpressAmt = plExpressAmt;
    }

    /**
     * @return the plGm5Amt
     */
    public BigDecimal getPlGm5Amt() {
        return plGm5Amt;
    }

    /**
     * @param plGm5Amt
     *            the plGm5Amt to set
     */
    public void setPlGm5Amt(BigDecimal plGm5Amt) {
        this.plGm5Amt = plGm5Amt;
    }

    /**
     * @return the plJdyAmt
     */
    public BigDecimal getPlJdyAmt() {
        return plJdyAmt;
    }

    /**
     * @param plJdyAmt
     *            the plJdyAmt to set
     */
    public void setPlJdyAmt(BigDecimal plJdyAmt) {
        this.plJdyAmt = plJdyAmt;
    }

    /**
     * @return the snapDate
     */
    public Date getSnapDate() {
        return snapDate;
    }

    /**
     * @param snapDate
     *            the snapDate to set
     */
    public void setSnapDate(Date snapDate) {
        this.snapDate = snapDate;
    }

}
