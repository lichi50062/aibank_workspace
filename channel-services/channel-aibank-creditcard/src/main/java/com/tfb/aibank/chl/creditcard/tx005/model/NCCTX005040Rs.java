package com.tfb.aibank.chl.creditcard.tx005.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.annotations.FormatBigDecimal;
import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCTX005040Rs.java
 * 
 * <p>Description:額度調整 040 確認頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/04, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCTX005040Rs implements RsData {

    /** 調整項目 */
    private AdjustItemType adjustItem;

    /** 調整金額 */
    @FormatBigDecimal(thousand = true)
    private BigDecimal adjustAmt;

    /** 目前額度 */
    @FormatBigDecimal(thousand = true)
    private BigDecimal currentQuota;

    /** 額度用途 */
    private QuotaUsageType quotaUsage;
    /** 其他用途(額度用途選擇「其他」時必填) */
    private String otherUsage;
    /** 保費全額(額度用途選擇「人壽保費扣繳(分期)」時必填) */
    private BigDecimal fullInsurAmt;

    /** 中文姓名(隱碼) */
    private String maskCnam;
    /** 持卡人ID(隱碼) */
    private String maskHdid;

    /** 任職公司 */
    private String company;
    /** 職稱 */
    private String jobTitle;
    /** 年資 */
    private String seniority;
    /** 公司電話 */
    private String officeTel;
    /** 公司地址 */
    private String officeAddress;
    /** 財力證明檔案清單 */
    private List<NCCTX005ProofFile> proofFiles;

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

    public AdjustItemType getAdjustItem() {
        return adjustItem;
    }

    public void setAdjustItem(AdjustItemType adjustItem) {
        this.adjustItem = adjustItem;
    }

    public String getMaskCnam() {
        return maskCnam;
    }

    public void setMaskCnam(String maskCnam) {
        this.maskCnam = maskCnam;
    }

    public String getMaskHdid() {
        return maskHdid;
    }

    public void setMaskHdid(String maskHdid) {
        this.maskHdid = maskHdid;
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

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    public QuotaUsageType getQuotaUsage() {
        return quotaUsage;
    }

    public void setQuotaUsage(QuotaUsageType quotaUsage) {
        this.quotaUsage = quotaUsage;
    }

    public List<NCCTX005ProofFile> getProofFiles() {
        return proofFiles;
    }

    public void setProofFiles(List<NCCTX005ProofFile> proofFiles) {
        this.proofFiles = proofFiles;
    }

    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
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
}
