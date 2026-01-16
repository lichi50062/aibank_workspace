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
package com.tfb.aibank.chl.creditcard.resource.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.tfb.aibank.common.model.TxHeadRq;

// @formatter:off
/**
 * @(#)EBCC002Request.java
 * 
 * <p>Description:信用卡額度申請(永調)啟案上送電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/17, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EBCC002Request extends TxHeadRq {

    private static final long serialVersionUID = 837270223583543314L;

    /** 處理方式：1.正卡调升 2.正卡調降 3.附卡限额 */
    private String adjType;

    /** 客户id */
    private String custId;

    /** 歸戶id */
    private String mCustId;

    /** 目前信用額度 */
    private BigDecimal befAdjAmt;

    /** 希望調整後額度 */
    private BigDecimal adjAmt;

    /** 申請永調來源（代碼） */
    private String applySrc;

    /** 變更額度用途（代碼） */
    private String adjUsage;

    /** 變更額度用途-其他說明欄 */
    private String adjMemo;

    /** 保費全額 */
    private BigDecimal insurAmt;

    /** 年數 */
    private String insurYear;

    /** 簡訊通知方式 */
    private String smsFlag;

    /** 簡訊 */
    private String smsPhone;

    /** e-mail通知方式 */
    private String emailFlag;

    /** e-mail */
    private String email;

    /** 公司名稱 */
    private String compNm;

    /** 職稱 */
    private String jobTitle;

    /** 年資（年） */
    private String curSeniorityYear;

    /** 年資（月） */
    private String curSeniorityMonth;

    /** 任職公司電話-區號 */
    private String oTelArea;

    /** 任職公司電話 */
    private String oTelNumber;

    /** 任職公司電話-分機 */
    private String oExtension;

    /** ip */
    private String ip;

    /** 線上申請日期 */
    private Date applyDate;

    /** 線上申請時間 */
    private Date applyTime;

    /** 身份認證日期 */
    private Date idAuthDate;

    /** 身份認證時間 */
    private Date idAuthTime;

    /** 身份驗證方式 */
    private String idAuthType;

    /** 檔案個數 */
    private Integer filecnt;

    /** 檔案名稱1 */
    private String filename1;

    /** 檔案名稱2 */
    private String filename2;

    /** 檔案名稱3 */
    private String filename3;

    /** 檔案名稱4 */
    private String filename4;

    /** 檔案名稱5 */
    private String filename5;

    /** 檔案名稱6 */
    private String filename6;

    /** 檔案名稱7 */
    private String filename7;

    /** 檔案名稱8 */
    private String filename8;

    /** 檔案名稱9 */
    private String filename9;

    /** 檔案名稱10 */
    private String filename10;

    /** 公司地址-郵遞區號 */
    private String oZip;

    /** 公司地址 */
    private String oAddr;

    /** 所得代碼 */
    private String aiIncomeCode;

    /** 年收入 */
    private String aiAnnualIncome;

    /** 白名單試算可調高後額度 */
    private BigDecimal aiAdjAmt;

    /** 資料更新時間 */
    private Date aiUpdDatetime;

    /** 是否有「免財力證明調額白名單」 */
    private boolean haveCustomerCardApply;

    public String getAdjType() {
        return adjType;
    }

    public void setAdjType(String adjType) {
        this.adjType = adjType;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getmCustId() {
        return mCustId;
    }

    public void setmCustId(String mCustId) {
        this.mCustId = mCustId;
    }

    public BigDecimal getBefAdjAmt() {
        return befAdjAmt;
    }

    public void setBefAdjAmt(BigDecimal befAdjAmt) {
        this.befAdjAmt = befAdjAmt;
    }

    public BigDecimal getAdjAmt() {
        return adjAmt;
    }

    public void setAdjAmt(BigDecimal adjAmt) {
        this.adjAmt = adjAmt;
    }

    public String getApplySrc() {
        return applySrc;
    }

    public void setApplySrc(String applySrc) {
        this.applySrc = applySrc;
    }

    public String getAdjUsage() {
        return adjUsage;
    }

    public void setAdjUsage(String adjUsage) {
        this.adjUsage = adjUsage;
    }

    public String getAdjMemo() {
        return adjMemo;
    }

    public void setAdjMemo(String adjMemo) {
        this.adjMemo = adjMemo;
    }

    public BigDecimal getInsurAmt() {
        return insurAmt;
    }

    public void setInsurAmt(BigDecimal insurAmt) {
        this.insurAmt = insurAmt;
    }

    public String getInsurYear() {
        return insurYear;
    }

    public void setInsurYear(String insurYear) {
        this.insurYear = insurYear;
    }

    public String getSmsFlag() {
        return smsFlag;
    }

    public void setSmsFlag(String smsFlag) {
        this.smsFlag = smsFlag;
    }

    public String getSmsPhone() {
        return smsPhone;
    }

    public void setSmsPhone(String smsPhone) {
        this.smsPhone = smsPhone;
    }

    public String getEmailFlag() {
        return emailFlag;
    }

    public void setEmailFlag(String emailFlag) {
        this.emailFlag = emailFlag;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompNm() {
        return compNm;
    }

    public void setCompNm(String compNm) {
        this.compNm = compNm;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCurSeniorityYear() {
        return curSeniorityYear;
    }

    public void setCurSeniorityYear(String curSeniorityYear) {
        this.curSeniorityYear = curSeniorityYear;
    }

    public String getCurSeniorityMonth() {
        return curSeniorityMonth;
    }

    public void setCurSeniorityMonth(String curSeniorityMonth) {
        this.curSeniorityMonth = curSeniorityMonth;
    }

    public String getoTelArea() {
        return oTelArea;
    }

    public void setoTelArea(String oTelArea) {
        this.oTelArea = oTelArea;
    }

    public String getoTelNumber() {
        return oTelNumber;
    }

    public void setoTelNumber(String oTelNumber) {
        this.oTelNumber = oTelNumber;
    }

    public String getoExtension() {
        return oExtension;
    }

    public void setoExtension(String oExtension) {
        this.oExtension = oExtension;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getIdAuthDate() {
        return idAuthDate;
    }

    public void setIdAuthDate(Date idAuthDate) {
        this.idAuthDate = idAuthDate;
    }

    public Date getIdAuthTime() {
        return idAuthTime;
    }

    public void setIdAuthTime(Date idAuthTime) {
        this.idAuthTime = idAuthTime;
    }

    public String getIdAuthType() {
        return idAuthType;
    }

    public void setIdAuthType(String idAuthType) {
        this.idAuthType = idAuthType;
    }

    public Integer getFilecnt() {
        return filecnt;
    }

    public void setFilecnt(Integer filecnt) {
        this.filecnt = filecnt;
    }

    public String getFilename1() {
        return filename1;
    }

    public void setFilename1(String filename1) {
        this.filename1 = filename1;
    }

    public String getFilename2() {
        return filename2;
    }

    public void setFilename2(String filename2) {
        this.filename2 = filename2;
    }

    public String getFilename3() {
        return filename3;
    }

    public void setFilename3(String filename3) {
        this.filename3 = filename3;
    }

    public String getFilename4() {
        return filename4;
    }

    public void setFilename4(String filename4) {
        this.filename4 = filename4;
    }

    public String getFilename5() {
        return filename5;
    }

    public void setFilename5(String filename5) {
        this.filename5 = filename5;
    }

    public String getFilename6() {
        return filename6;
    }

    public void setFilename6(String filename6) {
        this.filename6 = filename6;
    }

    public String getFilename7() {
        return filename7;
    }

    public void setFilename7(String filename7) {
        this.filename7 = filename7;
    }

    public String getFilename8() {
        return filename8;
    }

    public void setFilename8(String filename8) {
        this.filename8 = filename8;
    }

    public String getFilename9() {
        return filename9;
    }

    public void setFilename9(String filename9) {
        this.filename9 = filename9;
    }

    public String getFilename10() {
        return filename10;
    }

    public void setFilename10(String filename10) {
        this.filename10 = filename10;
    }

    public String getoAddr() {
        return oAddr;
    }

    public void setoAddr(String oAddr) {
        this.oAddr = oAddr;
    }

    public String getAiIncomeCode() {
        return aiIncomeCode;
    }

    public void setAiIncomeCode(String aiIncomeCode) {
        this.aiIncomeCode = aiIncomeCode;
    }

    public String getAiAnnualIncome() {
        return aiAnnualIncome;
    }

    public void setAiAnnualIncome(String aiAnnualIncome) {
        this.aiAnnualIncome = aiAnnualIncome;
    }

    public BigDecimal getAiAdjAmt() {
        return aiAdjAmt;
    }

    public void setAiAdjAmt(BigDecimal aiAdjAmt) {
        this.aiAdjAmt = aiAdjAmt;
    }

    public Date getAiUpdDatetime() {
        return aiUpdDatetime;
    }

    public void setAiUpdDatetime(Date aiUpdDatetime) {
        this.aiUpdDatetime = aiUpdDatetime;
    }

    public String getoZip() {
        return oZip;
    }

    public void setoZip(String oZip) {
        this.oZip = oZip;
    }

    public boolean isHaveCustomerCardApply() {
        return haveCustomerCardApply;
    }

    public void setHaveCustomerCardApply(boolean haveCustomerCardApply) {
        this.haveCustomerCardApply = haveCustomerCardApply;
    }

}
