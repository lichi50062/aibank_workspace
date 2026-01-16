/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.creditcard.qu008.model;

// @formatter:off
/**
 * @(#)NCCQU008InstallmentDetailVo.java
 * 
 * <p>Description:分期細項 </p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/05, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCQU008InstallmentDetailVo {

    /** 消費說明 */
    private String billDesc;

    /** 消費說明細項 */
    private String billDetailDesc;

    /** 消費日期 毫秒 */
    private Long billTime;

    /** 分期金額 or 消費金額 */
    private String installmentAmt;

    /** 期數 */
    private String installmentAlreadyPaid;

    /** 分期年利率 */
    private String installmentAnnualRate;

    /** 申請日期 */
    private String applyDate;

    /**
     * 卡片 若卡片已停用，顯示格式：“富邦信用卡 ….”+{卡號末四碼}。 若卡片未停用 照供通業務 {信用卡名稱}+<空白>+{信用卡卡號}
     */
    private String cardDisplay;

    /** 消費日期 yyyy/mm/dd */
    private String billDate;

    /** 入帳日期 yyyy/mm/dd */
    private String nccDay;

    /** ------------------ 未分期用 ------------------ */
    /** 是否未分期未申請 */
    private Boolean isNoinstallmentAndNoApply = false;

    /** 是否未分期未請款 */
    private Boolean isNoinstallmentAndNoGetMoney = false;

    /**
     * @return the billDesc
     */
    public String getBillDesc() {
        return billDesc;
    }

    /**
     * @param billDesc
     *            the billDesc to set
     */
    public void setBillDesc(String billDesc) {
        this.billDesc = billDesc;
    }

    /**
     * @return the billTime
     */
    public Long getBillTime() {
        return billTime;
    }

    /**
     * @param billTime
     *            the billTime to set
     */
    public void setBillTime(Long billTime) {
        this.billTime = billTime;
    }

    /**
     * @return the installmentAmt
     */
    public String getInstallmentAmt() {
        return installmentAmt;
    }

    /**
     * @param installmentAmt
     *            the installmentAmt to set
     */
    public void setInstallmentAmt(String installmentAmt) {
        this.installmentAmt = installmentAmt;
    }

    /**
     * @return the installmentAlreadyPaid
     */
    public String getInstallmentAlreadyPaid() {
        return installmentAlreadyPaid;
    }

    /**
     * @param installmentAlreadyPaid
     *            the installmentAlreadyPaid to set
     */
    public void setInstallmentAlreadyPaid(String installmentAlreadyPaid) {
        this.installmentAlreadyPaid = installmentAlreadyPaid;
    }

    /**
     * @return the installmentAnnualRate
     */
    public String getInstallmentAnnualRate() {
        return installmentAnnualRate;
    }

    /**
     * @param installmentAnnualRate
     *            the installmentAnnualRate to set
     */
    public void setInstallmentAnnualRate(String installmentAnnualRate) {
        this.installmentAnnualRate = installmentAnnualRate;
    }

    /**
     * @return the applyDate
     */
    public String getApplyDate() {
        return applyDate;
    }

    /**
     * @param applyDate
     *            the applyDate to set
     */
    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    /**
     * @return the cardDisplay
     */
    public String getCardDisplay() {
        return cardDisplay;
    }

    /**
     * @param cardDisplay
     *            the cardDisplay to set
     */
    public void setCardDisplay(String cardDisplay) {
        this.cardDisplay = cardDisplay;
    }

    /**
     * @return the billDate
     */
    public String getBillDate() {
        return billDate;
    }

    /**
     * @param billDate
     *            the billDate to set
     */
    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    /**
     * @return the nccDay
     */
    public String getNccDay() {
        return nccDay;
    }

    /**
     * @param nccDay
     *            the nccDay to set
     */
    public void setNccDay(String nccDay) {
        this.nccDay = nccDay;
    }

    /**
     * @return the billDetailDesc
     */
    public String getBillDetailDesc() {
        return billDetailDesc;
    }

    /**
     * @param billDetailDesc
     *            the billDetailDesc to set
     */
    public void setBillDetailDesc(String billDetailDesc) {
        this.billDetailDesc = billDetailDesc;
    }

    /**
     * @return the isNoinstallmentAndNoApply
     */
    public Boolean getIsNoinstallmentAndNoApply() {
        return isNoinstallmentAndNoApply;
    }

    /**
     * @param isNoinstallmentAndNoApply
     *            the isNoinstallmentAndNoApply to set
     */
    public void setIsNoinstallmentAndNoApply(Boolean isNoinstallmentAndNoApply) {
        this.isNoinstallmentAndNoApply = isNoinstallmentAndNoApply;
    }

    /**
     * @return the isNoinstallmentAndNoGetMoney
     */
    public Boolean getIsNoinstallmentAndNoGetMoney() {
        return isNoinstallmentAndNoGetMoney;
    }

    /**
     * @param isNoinstallmentAndNoGetMoney
     *            the isNoinstallmentAndNoGetMoney to set
     */
    public void setIsNoinstallmentAndNoGetMoney(Boolean isNoinstallmentAndNoGetMoney) {
        this.isNoinstallmentAndNoGetMoney = isNoinstallmentAndNoGetMoney;
    }

}
