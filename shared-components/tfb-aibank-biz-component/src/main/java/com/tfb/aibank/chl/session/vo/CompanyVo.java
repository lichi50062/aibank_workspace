package com.tfb.aibank.chl.session.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.tfb.aibank.common.type.CompanyBUType;
import com.tfb.aibank.common.type.CompanyKindType;
import com.tfb.aibank.common.type.CompanyStatusType;
import com.tfb.aibank.common.type.UaaLevelType;

// @formatter:off
/**
 * @(#)CompanyEntityVo.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/03, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CompanyVo {

    /** 公司內部鍵值 */
    private Integer companyKey;

    /** 公司統編或身份證字號 */
    private String companyUid;

    /** 公司統編或身份證字號誤別碼 */
    private String uidDup = "0";

    /**
     * 公司狀態
     * <ul>
     * <li>1：正常</li>
     * <li>0：暫停</li>
     * <li>-1：註銷</li>
     * <li>-2：虛擬公司（未申請網銀的關係戶）</li>
     * </ul>
     *
     * @see com.tfb.aibank.common.type.CompanyStatusType
     */
    private Integer status = CompanyStatusType.ALIVE.getCode();

    /**
     * 公司類型
     * <ul>
     * <li>1: 企業戶</li>
     * <li>2: 個人戶</li>
     * </ul>
     * 
     * @see com.tfb.aibank.common.type.CompanyKindType
     */
    private Integer companyKind = CompanyKindType.COMPANY.getCode();

    /**
     * 公司BU類型
     * <ul>
     * <li>1: DBU</li>
     * <li>2: OBU</li>
     * </ul>
     *
     * @see com.tfb.aibank.common.type.CompanyBUType
     */
    private Integer companyBUType = CompanyBUType.DBU.getCode();

    /** 公司戶名 */
    private String companyName;

    /** 公司英文名稱 */
    private String companyEname;

    /**
     * 申請的授權中心類別
     * <ul>
     * <li>0：無授權中心功能</li>
     * <li>1：單層授權</li>
     * <li>2：兩層式授權</li>
     * </ul>
     *
     * @see com.tfb.aibank.common.type.UaaLevelType
     */
    private Integer uaaLevel = UaaLevelType.TYPE0.getCode();

    /** 公司預設流程鍵值 */
    private Integer defaultFlowSchemaKey = 0;

    /** 流程套餐設定人員鍵值 */
    private Integer schemaSuitOperatorKey = 0;

    /** 流程套餐設定時間 */
    private Date schemaSuitCreateTime;

    /** 創立/出生日期 */
    private Date establishDate;

    /**
     * 往來分行代碼
     */
    private String defaultBranchId;

    /** 聯絡電話 */
    private String tel;

    /** 傳真電話 */
    private String fax;

    /** 行動電話 */
    private String mobile;

    /** 公司Email Address */
    private String emails;

    /** 啟用餘額不足重試扣款 */
    private Integer retryFlag = 0;

    /** 是否啟用雙重登入認證 */
    private Integer loginFlag = 0;

    /** 是否啟用電子薪資單服務 */
    private Integer salaryFlag = 0;

    /** 是否啟用線上約定收款人 */
    private Integer onlinePayeeFlag = 0;

    /** 授權管理者是否兼具交易權限 */
    private Integer root1TxFlag = 0;

    /** 授權主管是否兼具交易權限 */
    private Integer root2TxFlag = 0;

    /** 所有存款帳號均視為約定轉出帳號（包含未來新開立的帳號） */
    private Integer payerAccountFlag = 0;

    /** 所有轉出帳號均視為約定轉入帳號（包含未來新約定的轉出帳號） */
    private Integer payeeAccountFlag = 0;

    /** 否啟用傳真服務 */
    private Integer faxFlag = 0;

    /** 是否啟用ATM轉帳 */
    private Integer twAtmFlag = 1;

    /** 是否啟用通匯轉帳 */
    private Integer twRemitFlag = 1;

    /** 是否啟用FXML轉帳 */
    private Integer twFxmlFlag = 1;

    /** 轉入帳號是否限常用收款人 */
    private Integer twPayeeFlag = 0;

    /** ATM付款金額上限（元） */
    private BigDecimal twAmtQuota = new BigDecimal(2000000);

    /** 跨行通匯付款金額上限（元） */
    private BigDecimal twInterRemitQuota = new BigDecimal(50000000);

    /** 開戶時間 */
    private Date registerTime;

    /** 網銀帳戶註銷時間 */
    private Date cancelTime;

    /** 最後更新分行代碼 */
    private String lastBranchId;

    /** 最後更新經辦鍵值 */
    private Integer lastEditorKey = 0;

    /** 最後更新主管鍵值 */
    private Integer lastManagerKey = 0;

    /** 最後更新時間 */
    private Date lastUpdateTime;

    /** 申請功能限查詢 (1 : 不能勾選申辦業務子系統) */
    private Integer queryOnlyFlag = 0;

    /** 是否已重做 KYC ( 1: 是, 0: 否) */
    private Integer kycRevalidated = 0;

    /** 重做 KYC 日期 */
    private Date kycRevalidatedDate;

    /** for TFB 客群代碼 */
    private String massCheck;

    /** 交易被授權人身分證字號 */
    private String customeAuditId;

    /**
     * @return the customeAuditId
     */
    public String getCustomeAuditId() {
        return customeAuditId;
    }

    /**
     * @param customeAuditId
     *            the customeAuditId to set
     */
    public void setCustomeAuditId(String customeAuditId) {
        this.customeAuditId = customeAuditId;
    }

    /**
     * @return the companyKey
     */
    public Integer getCompanyKey() {
        return companyKey;
    }

    /**
     * @param companyKey
     *            the companyKey to set
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * @return the companyUid
     */
    public String getCompanyUid() {
        return companyUid;
    }

    /**
     * @param companyUid
     *            the companyUid to set
     */
    public void setCompanyUid(String companyUid) {
        this.companyUid = companyUid;
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
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
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
     * @return the companyBUType
     */
    public Integer getCompanyBUType() {
        return companyBUType;
    }

    /**
     * @param companyBUType
     *            the companyBUType to set
     */
    public void setCompanyBUType(Integer companyBUType) {
        this.companyBUType = companyBUType;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName
     *            the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the companyEname
     */
    public String getCompanyEname() {
        return companyEname;
    }

    /**
     * @param companyEname
     *            the companyEname to set
     */
    public void setCompanyEname(String companyEname) {
        this.companyEname = companyEname;
    }

    /**
     * @return the uaaLevel
     */
    public Integer getUaaLevel() {
        return uaaLevel;
    }

    /**
     * @param uaaLevel
     *            the uaaLevel to set
     */
    public void setUaaLevel(Integer uaaLevel) {
        this.uaaLevel = uaaLevel;
    }

    /**
     * @return the defaultFlowSchemaKey
     */
    public Integer getDefaultFlowSchemaKey() {
        return defaultFlowSchemaKey;
    }

    /**
     * @param defaultFlowSchemaKey
     *            the defaultFlowSchemaKey to set
     */
    public void setDefaultFlowSchemaKey(Integer defaultFlowSchemaKey) {
        this.defaultFlowSchemaKey = defaultFlowSchemaKey;
    }

    /**
     * @return the schemaSuitOperatorKey
     */
    public Integer getSchemaSuitOperatorKey() {
        return schemaSuitOperatorKey;
    }

    /**
     * @param schemaSuitOperatorKey
     *            the schemaSuitOperatorKey to set
     */
    public void setSchemaSuitOperatorKey(Integer schemaSuitOperatorKey) {
        this.schemaSuitOperatorKey = schemaSuitOperatorKey;
    }

    /**
     * @return the schemaSuitCreateTime
     */
    public Date getSchemaSuitCreateTime() {
        return schemaSuitCreateTime;
    }

    /**
     * @param schemaSuitCreateTime
     *            the schemaSuitCreateTime to set
     */
    public void setSchemaSuitCreateTime(Date schemaSuitCreateTime) {
        this.schemaSuitCreateTime = schemaSuitCreateTime;
    }

    /**
     * @return the establishDate
     */
    public Date getEstablishDate() {
        return establishDate;
    }

    /**
     * @param establishDate
     *            the establishDate to set
     */
    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    /**
     * @return the defaultBranchId
     */
    public String getDefaultBranchId() {
        return defaultBranchId;
    }

    /**
     * @param defaultBranchId
     *            the defaultBranchId to set
     */
    public void setDefaultBranchId(String defaultBranchId) {
        this.defaultBranchId = defaultBranchId;
    }

    /**
     * @return the tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel
     *            the tel to set
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax
     *            the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     *            the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the emails
     */
    public String getEmails() {
        return emails;
    }

    /**
     * @param emails
     *            the emails to set
     */
    public void setEmails(String emails) {
        this.emails = emails;
    }

    /**
     * @return the retryFlag
     */
    public Integer getRetryFlag() {
        return retryFlag;
    }

    /**
     * @param retryFlag
     *            the retryFlag to set
     */
    public void setRetryFlag(Integer retryFlag) {
        this.retryFlag = retryFlag;
    }

    /**
     * @return the loginFlag
     */
    public Integer getLoginFlag() {
        return loginFlag;
    }

    /**
     * @param loginFlag
     *            the loginFlag to set
     */
    public void setLoginFlag(Integer loginFlag) {
        this.loginFlag = loginFlag;
    }

    /**
     * @return the salaryFlag
     */
    public Integer getSalaryFlag() {
        return salaryFlag;
    }

    /**
     * @param salaryFlag
     *            the salaryFlag to set
     */
    public void setSalaryFlag(Integer salaryFlag) {
        this.salaryFlag = salaryFlag;
    }

    /**
     * @return the onlinePayeeFlag
     */
    public Integer getOnlinePayeeFlag() {
        return onlinePayeeFlag;
    }

    /**
     * @param onlinePayeeFlag
     *            the onlinePayeeFlag to set
     */
    public void setOnlinePayeeFlag(Integer onlinePayeeFlag) {
        this.onlinePayeeFlag = onlinePayeeFlag;
    }

    /**
     * @return the root1TxFlag
     */
    public Integer getRoot1TxFlag() {
        return root1TxFlag;
    }

    /**
     * @param root1TxFlag
     *            the root1TxFlag to set
     */
    public void setRoot1TxFlag(Integer root1TxFlag) {
        this.root1TxFlag = root1TxFlag;
    }

    /**
     * @return the root2TxFlag
     */
    public Integer getRoot2TxFlag() {
        return root2TxFlag;
    }

    /**
     * @param root2TxFlag
     *            the root2TxFlag to set
     */
    public void setRoot2TxFlag(Integer root2TxFlag) {
        this.root2TxFlag = root2TxFlag;
    }

    /**
     * @return the payerAccountFlag
     */
    public Integer getPayerAccountFlag() {
        return payerAccountFlag;
    }

    /**
     * @param payerAccountFlag
     *            the payerAccountFlag to set
     */
    public void setPayerAccountFlag(Integer payerAccountFlag) {
        this.payerAccountFlag = payerAccountFlag;
    }

    /**
     * @return the payeeAccountFlag
     */
    public Integer getPayeeAccountFlag() {
        return payeeAccountFlag;
    }

    /**
     * @param payeeAccountFlag
     *            the payeeAccountFlag to set
     */
    public void setPayeeAccountFlag(Integer payeeAccountFlag) {
        this.payeeAccountFlag = payeeAccountFlag;
    }

    /**
     * @return the faxFlag
     */
    public Integer getFaxFlag() {
        return faxFlag;
    }

    /**
     * @param faxFlag
     *            the faxFlag to set
     */
    public void setFaxFlag(Integer faxFlag) {
        this.faxFlag = faxFlag;
    }

    /**
     * @return the twAtmFlag
     */
    public Integer getTwAtmFlag() {
        return twAtmFlag;
    }

    /**
     * @param twAtmFlag
     *            the twAtmFlag to set
     */
    public void setTwAtmFlag(Integer twAtmFlag) {
        this.twAtmFlag = twAtmFlag;
    }

    /**
     * @return the twRemitFlag
     */
    public Integer getTwRemitFlag() {
        return twRemitFlag;
    }

    /**
     * @param twRemitFlag
     *            the twRemitFlag to set
     */
    public void setTwRemitFlag(Integer twRemitFlag) {
        this.twRemitFlag = twRemitFlag;
    }

    /**
     * @return the twFxmlFlag
     */
    public Integer getTwFxmlFlag() {
        return twFxmlFlag;
    }

    /**
     * @param twFxmlFlag
     *            the twFxmlFlag to set
     */
    public void setTwFxmlFlag(Integer twFxmlFlag) {
        this.twFxmlFlag = twFxmlFlag;
    }

    /**
     * @return the twPayeeFlag
     */
    public Integer getTwPayeeFlag() {
        return twPayeeFlag;
    }

    /**
     * @param twPayeeFlag
     *            the twPayeeFlag to set
     */
    public void setTwPayeeFlag(Integer twPayeeFlag) {
        this.twPayeeFlag = twPayeeFlag;
    }

    /**
     * @return the twAmtQuota
     */
    public BigDecimal getTwAmtQuota() {
        return twAmtQuota;
    }

    /**
     * @param twAmtQuota
     *            the twAmtQuota to set
     */
    public void setTwAmtQuota(BigDecimal twAmtQuota) {
        this.twAmtQuota = twAmtQuota;
    }

    /**
     * @return the twInterRemitQuota
     */
    public BigDecimal getTwInterRemitQuota() {
        return twInterRemitQuota;
    }

    /**
     * @param twInterRemitQuota
     *            the twInterRemitQuota to set
     */
    public void setTwInterRemitQuota(BigDecimal twInterRemitQuota) {
        this.twInterRemitQuota = twInterRemitQuota;
    }

    /**
     * @return the registerTime
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * @param registerTime
     *            the registerTime to set
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * @return the cancelTime
     */
    public Date getCancelTime() {
        return cancelTime;
    }

    /**
     * @param cancelTime
     *            the cancelTime to set
     */
    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    /**
     * @return the lastBranchId
     */
    public String getLastBranchId() {
        return lastBranchId;
    }

    /**
     * @param lastBranchId
     *            the lastBranchId to set
     */
    public void setLastBranchId(String lastBranchId) {
        this.lastBranchId = lastBranchId;
    }

    /**
     * @return the lastEditorKey
     */
    public Integer getLastEditorKey() {
        return lastEditorKey;
    }

    /**
     * @param lastEditorKey
     *            the lastEditorKey to set
     */
    public void setLastEditorKey(Integer lastEditorKey) {
        this.lastEditorKey = lastEditorKey;
    }

    /**
     * @return the lastManagerKey
     */
    public Integer getLastManagerKey() {
        return lastManagerKey;
    }

    /**
     * @param lastManagerKey
     *            the lastManagerKey to set
     */
    public void setLastManagerKey(Integer lastManagerKey) {
        this.lastManagerKey = lastManagerKey;
    }

    /**
     * @return the lastUpdateTime
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * @param lastUpdateTime
     *            the lastUpdateTime to set
     */
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * @return the queryOnlyFlag
     */
    public Integer getQueryOnlyFlag() {
        return queryOnlyFlag;
    }

    /**
     * @param queryOnlyFlag
     *            the queryOnlyFlag to set
     */
    public void setQueryOnlyFlag(Integer queryOnlyFlag) {
        this.queryOnlyFlag = queryOnlyFlag;
    }

    /**
     * @return the kycRevalidated
     */
    public Integer getKycRevalidated() {
        return kycRevalidated;
    }

    /**
     * @param kycRevalidated
     *            the kycRevalidated to set
     */
    public void setKycRevalidated(Integer kycRevalidated) {
        this.kycRevalidated = kycRevalidated;
    }

    /**
     * @return the kycRevalidatedDate
     */
    public Date getKycRevalidatedDate() {
        return kycRevalidatedDate;
    }

    /**
     * @param kycRevalidatedDate
     *            the kycRevalidatedDate to set
     */
    public void setKycRevalidatedDate(Date kycRevalidatedDate) {
        this.kycRevalidatedDate = kycRevalidatedDate;
    }

    /**
     * @return the massCheck
     */
    public String getMassCheck() {
        return massCheck;
    }

    /**
     * @param massCheck
     *            the massCheck to set
     */
    public void setMassCheck(String massCheck) {
        this.massCheck = massCheck;
    }

}
