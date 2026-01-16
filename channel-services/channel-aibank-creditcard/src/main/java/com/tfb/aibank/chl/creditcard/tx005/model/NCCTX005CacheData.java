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
package com.tfb.aibank.chl.creditcard.tx005.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.tfb.aibank.chl.creditcard.resource.dto.CustomerCardApply;

// @formatter:off
/**
 * @(#)NCCTX005CacheData.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/04, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCTX005CacheData {

    /** 信用額度 */
    private BigDecimal creditLimit;
    /** 調整項目 */
    private AdjustItemType adjustItem;
    /** 目前額度 */
    private BigDecimal currentQuota;
    /** 調整額度 */
    private BigDecimal adjustAmt;
    /** (白名單)用戶辦卡申請記錄表 */
    private CustomerCardApply customerCardApply;

    /** 額度用途 */
    private QuotaUsageType quotaUsage;
    /** 其他用途(額度用途選擇「其他」時必填) */
    private String otherUsage;
    /** 保費全額(額度用途選擇「人壽保費扣繳(分期)」時必填) */
    private BigDecimal fullInsurAmt;

    /** 附卡清單 */
    private List<NCCTX005CreditCard> supplementaryCards;
    /** 選擇的附卡 */
    private NCCTX005CreditCard supplementaryCard;

    /** 工作資訊 */
    private NCCTX005WorkInfo workInfo;

    /** 公司名稱 */
    private String company;
    /** 職稱 */
    private String jobTitle;
    /** 公司電話-區碼 */
    private String officeTelArea;
    /** 公司電話 */
    private String officeTel;
    /** 公司電話-分機 */
    private String officeTelExt;
    /** 公司地址-縣市代碼 */
    private String cityCode1;
    /** 公司地址-縣市 */
    private String cityName;
    /** 公司地址-鄉鎮市區 */
    private String areaName;
    /** 公司地址-郵遞區號 */
    private String zipcode;
    /** 公司地址 */
    private String officeAddress;
    /** 公司地址-郵遞區號 */
    private String officeZip;
    /** 年資-年 */
    private String seniorityY;
    /** 年資-月 */
    private String seniorityM;

    /** 財力證明檔案清單 */
    private List<NCCTX005ProofFile> proofFiles = new ArrayList<>();

    public AdjustItemType getAdjustItem() {
        return adjustItem;
    }

    public void setAdjustItem(AdjustItemType adjustItem) {
        this.adjustItem = adjustItem;
    }

    public QuotaUsageType getQuotaUsage() {
        return quotaUsage;
    }

    public void setQuotaUsage(QuotaUsageType quotaUsage) {
        this.quotaUsage = quotaUsage;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public List<NCCTX005CreditCard> getSupplementaryCards() {
        return supplementaryCards;
    }

    public void setSupplementaryCards(List<NCCTX005CreditCard> supplementaryCards) {
        this.supplementaryCards = supplementaryCards;
    }

    public NCCTX005CreditCard getSupplementaryCard() {
        return supplementaryCard;
    }

    public void setSupplementaryCard(NCCTX005CreditCard supplementaryCard) {
        this.supplementaryCard = supplementaryCard;
    }

    public BigDecimal getAdjustAmt() {
        return adjustAmt;
    }

    public void setAdjustAmt(BigDecimal adjustAmt) {
        this.adjustAmt = adjustAmt;
    }

    public BigDecimal getCurrentQuota() {
        return currentQuota;
    }

    public void setCurrentQuota(BigDecimal currentQuota) {
        this.currentQuota = currentQuota;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getOfficeTelArea() {
        return officeTelArea;
    }

    public void setOfficeTelArea(String officeTelArea) {
        this.officeTelArea = officeTelArea;
    }

    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
    }

    public String getOfficeTelExt() {
        return officeTelExt;
    }

    public void setOfficeTelExt(String officeTelExt) {
        this.officeTelExt = officeTelExt;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public NCCTX005WorkInfo getWorkInfo() {
        return workInfo;
    }

    public void setWorkInfo(NCCTX005WorkInfo workInfo) {
        this.workInfo = workInfo;
    }

    public String getSeniorityY() {
        return seniorityY;
    }

    public void setSeniorityY(String seniorityY) {
        this.seniorityY = seniorityY;
    }

    public String getSeniorityM() {
        return seniorityM;
    }

    public void setSeniorityM(String seniorityM) {
        this.seniorityM = seniorityM;
    }

    public List<NCCTX005ProofFile> getProofFiles() {
        return proofFiles;
    }

    public void setProofFiles(List<NCCTX005ProofFile> proofFiles) {
        this.proofFiles = proofFiles;
    }

    public String getOtherUsage() {
        return otherUsage;
    }

    public void setOtherUsage(String otherUsage) {
        this.otherUsage = otherUsage;
    }

    public BigDecimal getFullInsurAmt() {
        return fullInsurAmt;
    }

    public void setFullInsurAmt(BigDecimal fullInsurAmt) {
        this.fullInsurAmt = fullInsurAmt;
    }

    public CustomerCardApply getCustomerCardApply() {
        return customerCardApply;
    }

    public void setCustomerCardApply(CustomerCardApply customerCardApply) {
        this.customerCardApply = customerCardApply;
    }

    public String getOfficeZip() {
        return officeZip;
    }

    public void setOfficeZip(String officeZip) {
        this.officeZip = officeZip;
    }

    public String getCityCode1() {
        return cityCode1;
    }

    public void setCityCode1(String cityCode1) {
        this.cityCode1 = cityCode1;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

}
