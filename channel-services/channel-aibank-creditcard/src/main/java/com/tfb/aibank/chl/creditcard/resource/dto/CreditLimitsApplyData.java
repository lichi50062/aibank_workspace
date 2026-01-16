package com.tfb.aibank.chl.creditcard.resource.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

// @formatter:off
/**
 * @(#)CreditLimitsApplyData.java
 * 
 * <p>Description:信用卡固定額度調整交易紀錄檔</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/17, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CreditLimitsApplyData implements Serializable {

    private static final long serialVersionUID = 76411687531638126L;

    /** 記錄檔鍵值. */
    private Integer applyKey;

    /** 交易存取記錄鍵值 */
    private Integer accessLogKey;

    /** 交易存取記錄追蹤編號 */
    private String traceId;

    /** 白名單試算可調高後額度 */
    private BigDecimal aiAdjAmt;

    /** 年收入 */
    private String aiAnnualIncome;

    /** 所得代碼 */
    private String aiIncomeCode;

    /** 資料更新時間 */
    private Date aiUpdDatetime;

    /** [EN]年收入 */
    private String annualIncome;

    /** 線上申請編號 */
    private String applyNo;

    /** 客戶IP */
    private String clientIp;

    /** 公司鍵值 */
    private Integer companyKey;

    /** [EN]目前任職公司 */
    private String companyName;

    /** 建立時間 */
    private Date createTime;

    /** [EN]目前信用額度 */
    private String creditLimits;

    /** [EN]EMAIL */
    private String email;

    /** 上送主機交易時間 */
    private Date hostTxTime;

    /** [EN]保費全額 */
    private String insurance;

    /** 額度用途代碼 1：人壽保費扣繳（分期） 2：人壽保費扣繳 3：一般消費需求 4：出國需求 5：其他 */
    private String limitsPurpose;

    /** [EN]額度用途其他描述 */
    private String limitsPurposeDesc;

    /** 用戶代碼 */
    private String nameCode;

    /** [EN]調整後額度 */
    private String newCreditLimits;

    /** [EN]附卡人調整後信用額度 */
    private String newSecondaryCreditLimits;

    /** 公司地址-縣市代碼 */
    private String officeAddrCityCode;

    /** 公司地址-縣市名稱 */
    private String officeAddrCityName;

    /** 公司地址 */
    private String officeAddrName;

    /** 公司地址-鄉鎮市區代碼 */
    private String officeAddrTownCode;

    /** 公司地址-鄉鎮市區名稱 */
    private String officeAddrTownName;

    /** [EN]任職公司電話 */
    private String officeTel;

    /** [EN]任職公司電話-區碼 */
    private String officeTelArea;

    /** [EN]分機 */
    private String officeTelExt;

    /** [EN]附卡人信用額度 */
    private String secondaryCreditLimits;

    /** [EN]附卡人姓名 */
    private String secondaryName;

    /** [EN]附卡人身分證號 */
    private String secondaryUid;

    /** [EN]職稱 */
    private String title;

    /** 交易日期 */
    private Date txDate;

    /** 交易錯誤代碼 */
    private String txErrorCode;

    /** 錯誤訊息 */
    private String txErrorDesc;

    /** 錯誤系統代碼 */
    private String txErrorSystemId;

    /** 1：網銀 2：行銀 3：AIBank */
    private String txSource;

    /** 交易狀態 0：交易成功 1：交易失敗 4： 交易未明 */
    private String txStatus;

    /** 交易類型 1：調高固定額度 2：調低固定額度 3：調整附卡人指定額度 */
    private String txType;

    /** 更新時間 */
    private Date updateTime;

    /** 上傳檔案個數 */
    private int uploadCount;

    /** 上傳檔案回應說明 */
    private String uploadDesc;

    /** 上傳檔案回應代碼 */
    private String uploadStatus;

    /** 上傳檔案時間 */
    private Date uploadTime;

    /** 使用者代碼 */
    private String userId;

    /** 使用者鍵值 */
    private Integer userKey;

    /** 年資-月 */
    private String workMonth;

    /** 年資-年 */
    private String workYear;

    public Integer getApplyKey() {
        return applyKey;
    }

    public void setApplyKey(Integer applyKey) {
        this.applyKey = applyKey;
    }

    public Integer getAccessLogKey() {
        return accessLogKey;
    }

    public void setAccessLogKey(Integer accessLogKey) {
        this.accessLogKey = accessLogKey;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public BigDecimal getAiAdjAmt() {
        return aiAdjAmt;
    }

    public void setAiAdjAmt(BigDecimal aiAdjAmt) {
        this.aiAdjAmt = aiAdjAmt;
    }

    public String getAiAnnualIncome() {
        return aiAnnualIncome;
    }

    public void setAiAnnualIncome(String aiAnnualIncome) {
        this.aiAnnualIncome = aiAnnualIncome;
    }

    public String getAiIncomeCode() {
        return aiIncomeCode;
    }

    public void setAiIncomeCode(String aiIncomeCode) {
        this.aiIncomeCode = aiIncomeCode;
    }

    public Date getAiUpdDatetime() {
        return aiUpdDatetime;
    }

    public void setAiUpdDatetime(Date aiUpdDatetime) {
        this.aiUpdDatetime = aiUpdDatetime;
    }

    public String getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(String annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public Integer getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreditLimits() {
        return creditLimits;
    }

    public void setCreditLimits(String creditLimits) {
        this.creditLimits = creditLimits;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getHostTxTime() {
        return hostTxTime;
    }

    public void setHostTxTime(Date hostTxTime) {
        this.hostTxTime = hostTxTime;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getLimitsPurpose() {
        return limitsPurpose;
    }

    public void setLimitsPurpose(String limitsPurpose) {
        this.limitsPurpose = limitsPurpose;
    }

    public String getLimitsPurposeDesc() {
        return limitsPurposeDesc;
    }

    public void setLimitsPurposeDesc(String limitsPurposeDesc) {
        this.limitsPurposeDesc = limitsPurposeDesc;
    }

    public String getNameCode() {
        return nameCode;
    }

    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    public String getNewCreditLimits() {
        return newCreditLimits;
    }

    public void setNewCreditLimits(String newCreditLimits) {
        this.newCreditLimits = newCreditLimits;
    }

    public String getNewSecondaryCreditLimits() {
        return newSecondaryCreditLimits;
    }

    public void setNewSecondaryCreditLimits(String newSecondaryCreditLimits) {
        this.newSecondaryCreditLimits = newSecondaryCreditLimits;
    }

    public String getOfficeAddrCityCode() {
        return officeAddrCityCode;
    }

    public void setOfficeAddrCityCode(String officeAddrCityCode) {
        this.officeAddrCityCode = officeAddrCityCode;
    }

    public String getOfficeAddrCityName() {
        return officeAddrCityName;
    }

    public void setOfficeAddrCityName(String officeAddrCityName) {
        this.officeAddrCityName = officeAddrCityName;
    }

    public String getOfficeAddrName() {
        return officeAddrName;
    }

    public void setOfficeAddrName(String officeAddrName) {
        this.officeAddrName = officeAddrName;
    }

    public String getOfficeAddrTownCode() {
        return officeAddrTownCode;
    }

    public void setOfficeAddrTownCode(String officeAddrTownCode) {
        this.officeAddrTownCode = officeAddrTownCode;
    }

    public String getOfficeAddrTownName() {
        return officeAddrTownName;
    }

    public void setOfficeAddrTownName(String officeAddrTownName) {
        this.officeAddrTownName = officeAddrTownName;
    }

    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
    }

    public String getOfficeTelArea() {
        return officeTelArea;
    }

    public void setOfficeTelArea(String officeTelArea) {
        this.officeTelArea = officeTelArea;
    }

    public String getOfficeTelExt() {
        return officeTelExt;
    }

    public void setOfficeTelExt(String officeTelExt) {
        this.officeTelExt = officeTelExt;
    }

    public String getSecondaryCreditLimits() {
        return secondaryCreditLimits;
    }

    public void setSecondaryCreditLimits(String secondaryCreditLimits) {
        this.secondaryCreditLimits = secondaryCreditLimits;
    }

    public String getSecondaryName() {
        return secondaryName;
    }

    public void setSecondaryName(String secondaryName) {
        this.secondaryName = secondaryName;
    }

    public String getSecondaryUid() {
        return secondaryUid;
    }

    public void setSecondaryUid(String secondaryUid) {
        this.secondaryUid = secondaryUid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public String getTxErrorCode() {
        return txErrorCode;
    }

    public void setTxErrorCode(String txErrorCode) {
        this.txErrorCode = txErrorCode;
    }

    public String getTxErrorDesc() {
        return txErrorDesc;
    }

    public void setTxErrorDesc(String txErrorDesc) {
        this.txErrorDesc = txErrorDesc;
    }

    public String getTxErrorSystemId() {
        return txErrorSystemId;
    }

    public void setTxErrorSystemId(String txErrorSystemId) {
        this.txErrorSystemId = txErrorSystemId;
    }

    public String getTxSource() {
        return txSource;
    }

    public void setTxSource(String txSource) {
        this.txSource = txSource;
    }

    public String getTxStatus() {
        return txStatus;
    }

    public void setTxStatus(String txStatus) {
        this.txStatus = txStatus;
    }

    public String getTxType() {
        return txType;
    }

    public void setTxType(String txType) {
        this.txType = txType;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getUploadCount() {
        return uploadCount;
    }

    public void setUploadCount(int uploadCount) {
        this.uploadCount = uploadCount;
    }

    public String getUploadDesc() {
        return uploadDesc;
    }

    public void setUploadDesc(String uploadDesc) {
        this.uploadDesc = uploadDesc;
    }

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getUserKey() {
        return userKey;
    }

    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    public String getWorkMonth() {
        return workMonth;
    }

    public void setWorkMonth(String workMonth) {
        this.workMonth = workMonth;
    }

    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

}
