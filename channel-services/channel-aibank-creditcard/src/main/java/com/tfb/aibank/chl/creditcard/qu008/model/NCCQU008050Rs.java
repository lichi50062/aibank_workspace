package com.tfb.aibank.chl.creditcard.qu008.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCQU008050Rs.java
 * 
 * <p>Description:信用卡分期管理 050 確認頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/02, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU008050Rs implements RsData {

    /** 是否帳單流程 */
    private Boolean isBillProcess;

    /** 分期資訊 */
    private String installmentData = "";

    /** 分期存期 */
    private String installmentDataPeriod;

    /** 分期說明 */
    private String installmentDesc;

    /** 分期金額 */
    private String installmentAmt;

    /** 卡片 */
    private String cardDesc;

    /** 分期年利率 */
    private String installmentRate;

    /** 消費日期 */
    private String purchaseDay;

    /** 入帳日期 */
    private String nccDay;

    /**
     * @return the isBillProcess
     */
    public Boolean getIsBillProcess() {
        return isBillProcess;
    }

    /**
     * @param isBillProcess
     *            the isBillProcess to set
     */
    public void setIsBillProcess(Boolean isBillProcess) {
        this.isBillProcess = isBillProcess;
    }

    /**
     * @return the installmentData
     */
    public String getInstallmentData() {
        return installmentData;
    }

    /**
     * @param installmentData
     *            the installmentData to set
     */
    public void setInstallmentData(String installmentData) {
        this.installmentData = installmentData;
    }

    /**
     * @return the installmentDataPeriod
     */
    public String getInstallmentDataPeriod() {
        return installmentDataPeriod;
    }

    /**
     * @param installmentDataPeriod
     *            the installmentDataPeriod to set
     */
    public void setInstallmentDataPeriod(String installmentDataPeriod) {
        this.installmentDataPeriod = installmentDataPeriod;
    }

    /**
     * @return the installmentDesc
     */
    public String getInstallmentDesc() {
        return installmentDesc;
    }

    /**
     * @param installmentDesc
     *            the installmentDesc to set
     */
    public void setInstallmentDesc(String installmentDesc) {
        this.installmentDesc = installmentDesc;
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
     * @return the cardDesc
     */
    public String getCardDesc() {
        return cardDesc;
    }

    /**
     * @param cardDesc
     *            the cardDesc to set
     */
    public void setCardDesc(String cardDesc) {
        this.cardDesc = cardDesc;
    }

    /**
     * @return the installmentRate
     */
    public String getInstallmentRate() {
        return installmentRate;
    }

    /**
     * @param installmentRate
     *            the installmentRate to set
     */
    public void setInstallmentRate(String installmentRate) {
        this.installmentRate = installmentRate;
    }

    /**
     * @return the purchaseDay
     */
    public String getPurchaseDay() {
        return purchaseDay;
    }

    /**
     * @param purchaseDay
     *            the purchaseDay to set
     */
    public void setPurchaseDay(String purchaseDay) {
        this.purchaseDay = purchaseDay;
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

}
