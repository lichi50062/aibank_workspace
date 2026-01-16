package com.tfb.aibank.chl.creditcard.qu008.cache;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008BilledDetailVo;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008InstallmentAreaVo;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008InterestCalResult;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008MonthlyEstimate;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008TxnDataRq;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW205RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW220RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW222RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW315RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW343RResponse;

// @formatter:off
/**
 * @(#)NCCQU008CacheData.java
 * 
 * <p>Description:信用卡分期管理 cache</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/05, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on

public class NCCQU008CacheData {

    /** 分期類型 進入交易參數 */
    private String installmentType;

    /** 消費資訊 */
    private NCCQU008TxnDataRq billDates = new NCCQU008TxnDataRq();

    /** 分期未入帳金額 Cew326R */
    private NCCQU008InstallmentAreaVo installmentArea;

    /** 取得簽訂約定書註記 */
    private Boolean agreementFlag;

    /** 分期查詢清單 包含(單筆與其他) */
    private CEW343RResponse installmentTxnRs;

    /** 帳單分期查詢清單 */
    private CEW315RResponse installmentBillRs;

    /** 消費金額 */
    private BigDecimal purchaseAmt;

    /** 單筆申請中未請款 */
    private CEW222RResponse singleIntallmentsInApplyNoGetMoney;

    /** 結帳日前消費明細查詢首頁 */
    private CEW205RResponse cew205RResponse;

    // ----------------以下確認頁 結果頁資訊------------------------------------

    /** 利息計算結果 */
    private List<NCCQU008InterestCalResult> interestCalResults;

    /** 明細區塊 */
    private NCCQU008BilledDetailVo billedDetailSelect = new NCCQU008BilledDetailVo();

    /** 是否帳單流程 */
    private Boolean isBillProcess;

    /** 分期資訊 */
    private String installmentData;

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

    /** 手續費率 CEW220R_Rs. Feerate */
    private BigDecimal freerate;

    // -------------- 結果確認頁用 -----------------

    /** 選擇方案之分期 */
    private String period;

    /** 選擇方案之分期 描述 */
    private String periodDesc;

    /** 未請款明細區塊 資料 */
    private List<NCCQU008BilledDetailVo> unBilledDetails;

    /** 未請款明細區塊 查詢結果 */
    private String unBilledDetailQueryStatus;

    /** 已請款明細區塊 資料 */
    private List<NCCQU008BilledDetailVo> billedDetails;

    /** 已請款明細區塊 查詢結果 */
    private String billedDetailQueryStatus;

    /** Ce4153R 是否發查過 */
    private Boolean isCe4153RIn030HasCheck;

    /** Cew205 是否發查過 */
    private Boolean isCew205RIn030HasCheck;

    /** 每月預估金額 */
    private NCCQU008MonthlyEstimate monthlyEstimate;

    /** 指定消費分期試算交易 */
    private Map<String, CEW220RResponse> periodCew220rMap = new HashMap<>();

    /**
     * @return the installmentType
     */
    public String getInstallmentType() {
        return installmentType;
    }

    /**
     * @param installmentType
     *            the installmentType to set
     */
    public void setInstallmentType(String installmentType) {
        this.installmentType = installmentType;
    }

    /**
     * @return the billDates
     */
    public NCCQU008TxnDataRq getBillDates() {
        return billDates;
    }

    /**
     * @param billDates
     *            the billDates to set
     */
    public void setBillDates(NCCQU008TxnDataRq billDates) {
        this.billDates = billDates;
    }

    /**
     * @return the installmentArea
     */
    public NCCQU008InstallmentAreaVo getInstallmentArea() {
        return installmentArea;
    }

    /**
     * @param installmentArea
     *            the installmentArea to set
     */
    public void setInstallmentArea(NCCQU008InstallmentAreaVo installmentArea) {
        this.installmentArea = installmentArea;
    }

    /**
     * @return the agreementFlag
     */
    public Boolean getAgreementFlag() {
        return agreementFlag;
    }

    /**
     * @param agreementFlag
     *            the agreementFlag to set
     */
    public void setAgreementFlag(Boolean agreementFlag) {
        this.agreementFlag = agreementFlag;
    }

    /**
     * @return the installmentTxnRs
     */
    public CEW343RResponse getInstallmentTxnRs() {
        return installmentTxnRs;
    }

    /**
     * @param installmentTxnRs
     *            the installmentTxnRs to set
     */
    public void setInstallmentTxnRs(CEW343RResponse installmentTxnRs) {
        this.installmentTxnRs = installmentTxnRs;
    }

    /**
     * @return the installmentBillRs
     */
    public CEW315RResponse getInstallmentBillRs() {
        return installmentBillRs;
    }

    /**
     * @param installmentBillRs
     *            the installmentBillRs to set
     */
    public void setInstallmentBillRs(CEW315RResponse installmentBillRs) {
        this.installmentBillRs = installmentBillRs;
    }

    /**
     * @return the purchaseAmt
     */
    public BigDecimal getPurchaseAmt() {
        return purchaseAmt;
    }

    /**
     * @param purchaseAmt
     *            the purchaseAmt to set
     */
    public void setPurchaseAmt(BigDecimal purchaseAmt) {
        this.purchaseAmt = purchaseAmt;
    }

    /**
     * @return the interestCalResults
     */
    public List<NCCQU008InterestCalResult> getInterestCalResults() {
        return interestCalResults;
    }

    /**
     * @param interestCalResults
     *            the interestCalResults to set
     */
    public void setInterestCalResults(List<NCCQU008InterestCalResult> interestCalResults) {
        this.interestCalResults = interestCalResults;
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

    /**
     * @return the billedDetailSelect
     */
    public NCCQU008BilledDetailVo getBilledDetailSelect() {
        return billedDetailSelect;
    }

    /**
     * @param billedDetailSelect
     *            the billedDetailSelect to set
     */
    public void setBilledDetailSelect(NCCQU008BilledDetailVo billedDetailSelect) {
        this.billedDetailSelect = billedDetailSelect;
    }

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
     * @return the period
     */
    public String getPeriod() {
        return period;
    }

    /**
     * @param period
     *            the period to set
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * @return the periodDesc
     */
    public String getPeriodDesc() {
        return periodDesc;
    }

    /**
     * @param periodDesc
     *            the periodDesc to set
     */
    public void setPeriodDesc(String periodDesc) {
        this.periodDesc = periodDesc;
    }

    /**
     * @return the unBilledDetails
     */
    public List<NCCQU008BilledDetailVo> getUnBilledDetails() {
        return unBilledDetails;
    }

    /**
     * @param unBilledDetails
     *            the unBilledDetails to set
     */
    public void setUnBilledDetails(List<NCCQU008BilledDetailVo> unBilledDetails) {
        this.unBilledDetails = unBilledDetails;
    }

    /**
     * @return the unBilledDetailQueryStatus
     */
    public String getUnBilledDetailQueryStatus() {
        return unBilledDetailQueryStatus;
    }

    /**
     * @param unBilledDetailQueryStatus
     *            the unBilledDetailQueryStatus to set
     */
    public void setUnBilledDetailQueryStatus(String unBilledDetailQueryStatus) {
        this.unBilledDetailQueryStatus = unBilledDetailQueryStatus;
    }

    /**
     * @return the billedDetails
     */
    public List<NCCQU008BilledDetailVo> getBilledDetails() {
        return billedDetails;
    }

    /**
     * @param billedDetails
     *            the billedDetails to set
     */
    public void setBilledDetails(List<NCCQU008BilledDetailVo> billedDetails) {
        this.billedDetails = billedDetails;
    }

    /**
     * @return the billedDetailQueryStatus
     */
    public String getBilledDetailQueryStatus() {
        return billedDetailQueryStatus;
    }

    /**
     * @param billedDetailQueryStatus
     *            the billedDetailQueryStatus to set
     */
    public void setBilledDetailQueryStatus(String billedDetailQueryStatus) {
        this.billedDetailQueryStatus = billedDetailQueryStatus;
    }

    /**
     * @return the isCe4153RIn030HasCheck
     */
    public Boolean getIsCe4153RIn030HasCheck() {
        return isCe4153RIn030HasCheck;
    }

    /**
     * @param isCe4153RIn030HasCheck
     *            the isCe4153RIn030HasCheck to set
     */
    public void setIsCe4153RIn030HasCheck(Boolean isCe4153RIn030HasCheck) {
        this.isCe4153RIn030HasCheck = isCe4153RIn030HasCheck;
    }

    /**
     * @return the isCew205RIn030HasCheck
     */
    public Boolean getIsCew205RIn030HasCheck() {
        return isCew205RIn030HasCheck;
    }

    /**
     * @param isCew205RIn030HasCheck
     *            the isCew205RIn030HasCheck to set
     */
    public void setIsCew205RIn030HasCheck(Boolean isCew205RIn030HasCheck) {
        this.isCew205RIn030HasCheck = isCew205RIn030HasCheck;
    }

    /**
     * @return the monthlyEstimate
     */
    public NCCQU008MonthlyEstimate getMonthlyEstimate() {
        return monthlyEstimate;
    }

    /**
     * @param monthlyEstimate
     *            the monthlyEstimate to set
     */
    public void setMonthlyEstimate(NCCQU008MonthlyEstimate monthlyEstimate) {
        this.monthlyEstimate = monthlyEstimate;
    }

    /**
     * @return the freerate
     */
    public BigDecimal getFreerate() {
        return freerate;
    }

    /**
     * @param freerate
     *            the freerate to set
     */
    public void setFreerate(BigDecimal freerate) {
        this.freerate = freerate;
    }

    /**
     * @return the singleIntallmentsInApplyNoGetMoney
     */
    public CEW222RResponse getSingleIntallmentsInApplyNoGetMoney() {
        return singleIntallmentsInApplyNoGetMoney;
    }

    /**
     * @param singleIntallmentsInApplyNoGetMoney
     *            the singleIntallmentsInApplyNoGetMoney to set
     */
    public void setSingleIntallmentsInApplyNoGetMoney(CEW222RResponse singleIntallmentsInApplyNoGetMoney) {
        this.singleIntallmentsInApplyNoGetMoney = singleIntallmentsInApplyNoGetMoney;
    }

    /**
     * @return the cew205RResponse
     */
    public CEW205RResponse getCew205RResponse() {
        return cew205RResponse;
    }

    /**
     * @param cew205rResponse
     *            the cew205RResponse to set
     */
    public void setCew205RResponse(CEW205RResponse cew205rResponse) {
        cew205RResponse = cew205rResponse;
    }

    /**
     * @return the periodCew220rMap
     */
    public Map<String, CEW220RResponse> getPeriodCew220rMap() {
        return periodCew220rMap;
    }

    /**
     * @param periodCew220rMap
     *            the periodCew220rMap to set
     */
    public void setPeriodCew220rMap(Map<String, CEW220RResponse> periodCew220rMap) {
        this.periodCew220rMap = periodCew220rMap;
    }

}
