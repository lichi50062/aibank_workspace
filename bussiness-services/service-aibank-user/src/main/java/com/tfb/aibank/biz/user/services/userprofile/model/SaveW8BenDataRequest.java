/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.user.services.userprofile.model;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)SaveW8BenDataRequest.java
 * 
 * <p>Description:W8Ben相關簽署資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/05, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "W8Ben相關簽署資料")
public class SaveW8BenDataRequest {
    @Schema(description = "平台")
    private String platForm;

    @Schema(description = "身分證字號")
    private String custId;

    @Schema(description = "誤別碼")
    private String uidDup;

    @Schema(description = "使用者代號")
    private String userUuid;

    @Schema(description = "公司類型")
    private Integer companyKind;

    @Schema(description = "文件編號")
    private String docNo;

    @Schema(description = "文件版本")
    private String docVer;

    @Schema(description = "最終受益人姓名-英文")
    private String engName;

    @Schema(description = "國籍")
    private String countryCod;

    @Schema(description = "戶籍地址")
    private String busAddr;

    @Schema(description = "現在居住地址")
    private String currAddr;

    @Schema(description = "U.S. taxpayer identification number")
    private String itin;

    @Schema(description = "Foreign tax identifying number")
    private String ftin;

    @Schema(description = "Check if FTIN not legally required")
    private String ftinCheck;

    @Schema(description = "Reference numbers")
    private String referenceNum;

    @Schema(description = "Date of birth")
    private String bday;

    @Schema(description = "英文國家名")
    private String engCountryName;

    @Schema(description = "租稅協議第幾條")
    private String taxAgreementNum;

    @Schema(description = "費率")
    private String rate;

    @Schema(description = "收入類型說明")
    private String incomeType;

    @Schema(description = "額外條件說明")
    private String extraConditions;

    @Schema(description = "簽署日期")
    private Date txDate;

    @Schema(description = "建立時間")
    private Date createTime;

    @Schema(description = "CLIENT IP")
    private String clientIp;

    @Schema(description = "CLIENT PORT")
    private String clientPort;

    @Schema(description = "交易存取記錄追蹤編號")
    private String traceId;

    @Schema(description = "錯誤代碼")
    private String errorCode;

    @Schema(description = "錯誤信息")
    private String errorMsg;

    @Schema(description = "交易狀態")
    private String txStatus;

    @Schema(description = "寄發通知信的email")
    private String email;

    /**
     * @return the platForm
     */
    public String getPlatForm() {
        return platForm;
    }

    /**
     * @param platForm
     *            the platForm to set
     */
    public void setPlatForm(String platForm) {
        this.platForm = platForm;
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
     * @return the uidDup
     */
    public String getUidDup() {
        return uidDup;
    }

    /**
     * @param uidDup
     *            the uidDup to set
     */
    public void setUidDup(String uidDup) {
        this.uidDup = uidDup;
    }

    /**
     * @return the userUuid
     */
    public String getUserUuid() {
        return userUuid;
    }

    /**
     * @param userUuid
     *            the userUuid to set
     */
    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    /**
     * @return the companyKind
     */
    public Integer getCompanyKind() {
        return companyKind;
    }

    /**
     * @param companyKind
     *            the companyKind to set
     */
    public void setCompanyKind(Integer companyKind) {
        this.companyKind = companyKind;
    }

    /**
     * @return the docNo
     */
    public String getDocNo() {
        return docNo;
    }

    /**
     * @param docNo
     *            the docNo to set
     */
    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    /**
     * @return the docVer
     */
    public String getDocVer() {
        return docVer;
    }

    /**
     * @param docVer
     *            the docVer to set
     */
    public void setDocVer(String docVer) {
        this.docVer = docVer;
    }

    /**
     * @return the engName
     */
    public String getEngName() {
        return engName;
    }

    /**
     * @param engName
     *            the engName to set
     */
    public void setEngName(String engName) {
        this.engName = engName;
    }

    /**
     * @return the countryCod
     */
    public String getCountryCod() {
        return countryCod;
    }

    /**
     * @param countryCod
     *            the countryCod to set
     */
    public void setCountryCod(String countryCod) {
        this.countryCod = countryCod;
    }

    /**
     * @return the busAddr
     */
    public String getBusAddr() {
        return busAddr;
    }

    /**
     * @param busAddr
     *            the busAddr to set
     */
    public void setBusAddr(String busAddr) {
        this.busAddr = busAddr;
    }

    /**
     * @return the currAddr
     */
    public String getCurrAddr() {
        return currAddr;
    }

    /**
     * @param currAddr
     *            the currAddr to set
     */
    public void setCurrAddr(String currAddr) {
        this.currAddr = currAddr;
    }

    /**
     * @return the itin
     */
    public String getItin() {
        return itin;
    }

    /**
     * @param itin
     *            the itin to set
     */
    public void setItin(String itin) {
        this.itin = itin;
    }

    /**
     * @return the ftin
     */
    public String getFtin() {
        return ftin;
    }

    /**
     * @param ftin
     *            the ftin to set
     */
    public void setFtin(String ftin) {
        this.ftin = ftin;
    }

    /**
     * @return the ftinCheck
     */
    public String getFtinCheck() {
        return ftinCheck;
    }

    /**
     * @param ftinCheck
     *            the ftinCheck to set
     */
    public void setFtinCheck(String ftinCheck) {
        this.ftinCheck = ftinCheck;
    }

    /**
     * @return the referenceNum
     */
    public String getReferenceNum() {
        return referenceNum;
    }

    /**
     * @param referenceNum
     *            the referenceNum to set
     */
    public void setReferenceNum(String referenceNum) {
        this.referenceNum = referenceNum;
    }

    /**
     * @return the bday
     */
    public String getBday() {
        return bday;
    }

    /**
     * @param bday
     *            the bday to set
     */
    public void setBday(String bday) {
        this.bday = bday;
    }

    /**
     * @return the engCountryName
     */
    public String getEngCountryName() {
        return engCountryName;
    }

    /**
     * @param engCountryName
     *            the engCountryName to set
     */
    public void setEngCountryName(String engCountryName) {
        this.engCountryName = engCountryName;
    }

    /**
     * @return the taxAgreementNum
     */
    public String getTaxAgreementNum() {
        return taxAgreementNum;
    }

    /**
     * @param taxAgreementNum
     *            the taxAgreementNum to set
     */
    public void setTaxAgreementNum(String taxAgreementNum) {
        this.taxAgreementNum = taxAgreementNum;
    }

    /**
     * @return the rate
     */
    public String getRate() {
        return rate;
    }

    /**
     * @param rate
     *            the rate to set
     */
    public void setRate(String rate) {
        this.rate = rate;
    }

    /**
     * @return the incomeType
     */
    public String getIncomeType() {
        return incomeType;
    }

    /**
     * @param incomeType
     *            the incomeType to set
     */
    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    /**
     * @return the extraConditions
     */
    public String getExtraConditions() {
        return extraConditions;
    }

    /**
     * @param extraConditions
     *            the extraConditions to set
     */
    public void setExtraConditions(String extraConditions) {
        this.extraConditions = extraConditions;
    }

    /**
     * @return the txDate
     */
    public Date getTxDate() {
        return txDate;
    }

    /**
     * @param txDate
     *            the txDate to set
     */
    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the clientIp
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * @param clientIp
     *            the clientIp to set
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * @return the clientPort
     */
    public String getClientPort() {
        return clientPort;
    }

    /**
     * @param clientPort
     *            the clientPort to set
     */
    public void setClientPort(String clientPort) {
        this.clientPort = clientPort;
    }

    /**
     * @return the traceId
     */
    public String getTraceId() {
        return traceId;
    }

    /**
     * @param traceId
     *            the traceId to set
     */
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode
     *            the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * @param errorMsg
     *            the errorMsg to set
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * @return the txStatus
     */
    public String getTxStatus() {
        return txStatus;
    }

    /**
     * @param txStatus
     *            the txStatus to set
     */
    public void setTxStatus(String txStatus) {
        this.txStatus = txStatus;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
