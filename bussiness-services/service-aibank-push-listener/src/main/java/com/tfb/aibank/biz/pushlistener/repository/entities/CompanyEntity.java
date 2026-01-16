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
package com.tfb.aibank.biz.pushlistener.repository.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.tfb.aibank.biz.pushlistener.repository.entities.support.CompanyEntityListener;
import com.tfb.aibank.common.type.CompanyBUType;
import com.tfb.aibank.common.type.CompanyKindType;
import com.tfb.aibank.common.type.CompanyStatusType;
import com.tfb.aibank.common.type.UaaLevelType;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;;

// @formatter:off
/**
 * @(#)CompanyEntity.java
 * 
 * <p>Description:公司資料 Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/17, Evan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "COMPANY")
@EntityListeners(CompanyEntityListener.class)
public class CompanyEntity implements Serializable {

    private static final long serialVersionUID = -2816913068713694078L;

    /** 公司內部鍵值 */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPANY_SEQ")
    @SequenceGenerator(name = "COMPANY_SEQ", sequenceName = "COMPANY_SEQ", allocationSize = 1)
    @Column(name = "COMPANY_KEY")
    private Integer companyKey;

    /** 公司統編或身份證字號 */
    @Basic
    @Column(name = "COMPANY_UID")
    private String companyUid;

    /** 公司統編或身份證字號誤別碼 */
    @Basic
    @Column(name = "UID_DUP")
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
     * @see com.ibm.tw.biz.b2c.uaa.type.CompanyStatusType
     */
    @Basic
    @Column(name = "STATUS")
    private Integer status = CompanyStatusType.ALIVE.getCode();

    /**
     * 公司類型
     * <ul>
     * <li>1: 企業戶</li>
     * <li>2: 個人戶</li>
     * </ul>
     * 
     * @see com.ibm.tw.biz.b2c.uaa.type.CompanyKindType
     */
    @Basic
    @Column(name = "COMPANY_KIND")
    private Integer companyKind = CompanyKindType.COMPANY.getCode();

    /**
     * 公司BU類型
     * <ul>
     * <li>1: DBU</li>
     * <li>2: OBU</li>
     * </ul>
     *
     * @see com.ibm.tw.biz.b2c.uaa.type.CompanyBUType
     */
    @Basic
    @Column(name = "COMPANY_BU_TYPE")
    private Integer companyBUType = CompanyBUType.DBU.getCode();

    /** 公司戶名 */
    @Basic
    @Column(name = "COMPANY_NAME")
    private String companyName;

    /** 公司英文名稱 */
    @Basic
    @Column(name = "COMPANY_ENAME")
    private String companyEname;

    /**
     * 申請的授權中心類別
     * <ul>
     * <li>0：無授權中心功能</li>
     * <li>1：單層授權</li>
     * <li>2：兩層式授權</li>
     * </ul>
     *
     * @see com.ibm.tw.biz.b2c.uaa.type.UaaLevelType
     */
    @Basic
    @Column(name = "UAA_LEVEL")
    private Integer uaaLevel = UaaLevelType.TYPE0.getCode();

    /** 公司預設流程鍵值 */
    @Basic
    @Column(name = "DEFAULT_FLOW_SCHEMA_KEY")
    private Integer defaultFlowSchemaKey = 0;

    /** 流程套餐設定人員鍵值 */
    @Basic
    @Column(name = "SCHEMA_SUIT_OPERATOR_KEY")
    private Integer schemaSuitOperatorKey = 0;

    /** 流程套餐設定時間 */
    @Basic
    @Column(name = "SCHEMA_SUIT_CREATE_TIME")
    private Date schemaSuitCreateTime;

    /** 創立/出生日期 */
    @Basic
    @Column(name = "ESTABLISH_DATE")
    private Date establishDate;

    /**
     * 往來分行代碼
     */
    @Basic
    @Column(name = "DEFAULT_BRANCH_ID")
    private String defaultBranchId;

    /** 聯絡電話 */
    @Basic
    @Column(name = "TEL")
    private String tel;

    /** 傳真電話 */
    @Basic
    @Column(name = "FAX")
    private String fax;

    /** 行動電話 */
    @Basic
    @Column(name = "MOBILE")
    private String mobile;

    /** 公司Email Address */
    @Basic
    @Column(name = "EMAILS")
    private String emails;

    /** 啟用餘額不足重試扣款 */
    @Basic
    @Column(name = "RETRY_FLAG")
    private Integer retryFlag = 0;

    /** 是否啟用雙重登入認證 */
    @Basic
    @Column(name = "LOGIN_FLAG")
    private Integer loginFlag = 0;

    /** 是否啟用電子薪資單服務 */
    @Basic
    @Column(name = "SALARY_FLAG")
    private Integer salaryFlag = 0;

    /** 是否啟用線上約定收款人 */
    @Basic
    @Column(name = "ONLINE_PAYEE_FLAG")
    private Integer onlinePayeeFlag = 0;

    /** 授權管理者是否兼具交易權限 */
    @Basic
    @Column(name = "ROOT1_TX_FLAG")
    private Integer root1TxFlag = 0;

    /** 授權主管是否兼具交易權限 */
    @Basic
    @Column(name = "ROOT2_TX_FLAG")
    private Integer root2TxFlag = 0;

    /** 所有存款帳號均視為約定轉出帳號（包含未來新開立的帳號） */
    @Basic
    @Column(name = "PAYER_ACCOUNT_FLAG")
    private Integer payerAccountFlag = 0;

    /** 所有轉出帳號均視為約定轉入帳號（包含未來新約定的轉出帳號） */
    @Basic
    @Column(name = "PAYEE_ACCOUNT_FLAG")
    private Integer payeeAccountFlag = 0;

    /** 否啟用傳真服務 */
    @Basic
    @Column(name = "FAX_FLAG")
    private Integer faxFlag = 0;

    /** 是否啟用ATM轉帳 */
    @Basic
    @Column(name = "TW_ATM_FLAG")
    private Integer twAtmFlag = 1;

    /** 是否啟用通匯轉帳 */
    @Basic
    @Column(name = "TW_REMIT_FLAG")
    private Integer twRemitFlag = 1;

    /** 是否啟用FXML轉帳 */
    @Basic
    @Column(name = "TW_FXML_FLAG")
    private Integer twFxmlFlag = 1;

    /** 轉入帳號是否限常用收款人 */
    @Basic
    @Column(name = "TW_PAYEE_FLAG")
    private Integer twPayeeFlag = 0;

    /** ATM付款金額上限（元） */
    @Basic
    @Column(name = "TW_AMT_QUOTA")
    private BigDecimal twAmtQuota = new BigDecimal(2000000);

    /** 跨行通匯付款金額上限（元） */
    @Basic
    @Column(name = "TW_INTER_REMIT_QUOTA")
    private BigDecimal twInterRemitQuota = new BigDecimal(50000000);

    /** 開戶時間 */
    @Basic
    @Column(name = "REGISTER_TIME")
    private Date registerTime;

    /** 網銀帳戶註銷時間 */
    @Basic
    @Column(name = "CANCEL_TIME")
    private Date cancelTime;

    /** 最後更新分行代碼 */
    @Basic
    @Column(name = "LAST_BRANCH_ID")
    private String lastBranchId;

    /** 最後更新經辦鍵值 */
    @Basic
    @Column(name = "LAST_EDITOR_KEY")
    private Integer lastEditorKey = 0;

    /** 最後更新主管鍵值 */
    @Basic
    @Column(name = "LAST_MANAGER_KEY")
    private Integer lastManagerKey = 0;

    /** 最後更新時間 */
    @Basic
    @Column(name = "LAST_UPDATE_TIME")
    private Date lastUpdateTime;

    /** 申請功能限查詢 (1 : 不能勾選申辦業務子系統) */
    @Basic
    @Column(name = "QUERY_ONLY_FLAG")
    private Integer queryOnlyFlag = 0;

    /** 是否已重做 KYC ( 1: 是, 0: 否) */
    @Basic
    @Column(name = "KYC_REVALIDATED")
    private Integer kycRevalidated = 0;

    /** 重做 KYC 日期 */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "KYC_REVALIDATED_DATE")
    private Date kycRevalidatedDate;

    /** for TFB 客群代碼 */
    @Basic
    @Column(name = "MASS_CHECK")
    private String massCheck;

    /** 交易被授權人身分證字號 */
    @Basic
    @Column(name = "CUSTOM_AUDIT_ID")
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

    public CompanyEntity() {
        super();
    }

    /**
     * 取得公司內部鍵值
     *
     * @return
     */
    public Integer getCompanyKey() {
        return companyKey;
    }

    /**
     * 設定公司內部鍵值
     *
     * @param companyKey
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * 取得公司統編或身份證字號
     *
     * @return
     */
    public String getCompanyUid() {
        return companyUid;
    }

    /**
     * 設定公司統編或身份證字號
     *
     * @param companyUid
     */
    public void setCompanyUid(String companyUid) {
        this.companyUid = companyUid;
    }

    /**
     * 取得 公司狀態
     * <ul>
     * <li>1：正常</li>
     * <li>0：虛擬企銀關係戶（不可使用網銀，無使用者）</li>
     * <li>-1：註銷</li>
     * </ul>
     *
     * @return
     * @see com.ibm.tw.biz.b2c.uaa.type.CompanyStatusType
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 設定公司狀態
     * <ul>
     * <li>1：正常</li>
     * <li>0：虛擬企銀關係戶（不可使用網銀，無使用者）</li>
     * <li>-1：註銷</li>
     * </ul>
     *
     * @param status
     * @see com.ibm.tw.biz.b2c.uaa.type.CompanyStatusType
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 取得公司戶名
     *
     * @return
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 設定公司戶名
     *
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 取得公司英文名稱
     *
     * @return
     */
    public String getCompanyEname() {
        return companyEname;
    }

    /**
     * 設定公司英文名稱
     *
     * @param companyEname
     */
    public void setCompanyEname(String companyEname) {
        this.companyEname = companyEname;
    }

    /**
     * 取得申請的授權中心類別
     * <ul>
     * <li>0：無授權中心功能</li>
     * <li>1：單層授權</li>
     * <li>2：兩層式授權</li>
     * </ul>
     *
     * @return
     * @see com.ibm.tw.biz.b2c.uaa.type.UaaLevelType
     */
    public Integer getUaaLevel() {
        return uaaLevel;
    }

    /**
     * 設定申請的授權中心類別
     * <ul>
     * <li>0：無授權中心功能</li>
     * <li>1：單層授權</li>
     * <li>2：兩層式授權</li>
     * </ul>
     *
     * @param uaaLevel
     *            要設定的 uaaLevel。
     * @see com.ibm.tw.biz.b2c.uaa.type.UaaLevelType
     */
    public void setUaaLevel(Integer uaaLevel) {
        this.uaaLevel = uaaLevel;
    }

    /**
     * 取得公司預設流程鍵值
     *
     * @return
     */
    public Integer getDefaultFlowSchemaKey() {
        return defaultFlowSchemaKey;
    }

    /**
     * 設定公司預設流程鍵值
     *
     * @param defaultFlowSchemaKey
     */
    public void setDefaultFlowSchemaKey(Integer defaultFlowSchemaKey) {
        this.defaultFlowSchemaKey = defaultFlowSchemaKey;
    }

    /**
     * 取得創立/出生日期
     *
     * @return
     */
    public Date getEstablishDate() {
        return establishDate;
    }

    /**
     * 設定創立/出生日期
     *
     * @param establishDate
     */
    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    /**
     * 取得 流程套餐設定人員鍵值
     *
     * @return
     */
    public Integer getSchemaSuitOperatorKey() {
        return schemaSuitOperatorKey;
    }

    /**
     * 設定流程套餐設定人員鍵值
     *
     * @param schemaSuitOperatorKey
     */
    public void setSchemaSuitOperatorKey(Integer schemaSuitOperatorKey) {
        this.schemaSuitOperatorKey = schemaSuitOperatorKey;
    }

    /**
     * 取得流程套餐設定時間
     *
     * @return
     */
    public Date getSchemaSuitCreateTime() {
        return schemaSuitCreateTime;
    }

    /**
     * 設定流程套餐設定時間
     *
     * @param schemaSuitCreateTime
     */
    public void setSchemaSuitCreateTime(Date schemaSuitCreateTime) {
        this.schemaSuitCreateTime = schemaSuitCreateTime;
    }

    /**
     * @see #companyKind
     *
     * @return
     */
    public Integer getCompanyKind() {
        return companyKind;
    }

    /**
     * @see #companyKind
     *
     * @param companyKind
     */
    public void setCompanyKind(Integer companyKind) {
        this.companyKind = companyKind;
    }

    /**
     * 取得公司BU類型
     *
     * @return
     * @see com.ibm.tw.biz.b2c.uaa.type.CompanyBUType
     */
    public Integer getCompanyBUType() {
        return companyBUType;
    }

    /**
     * 設定公司BU類型
     *
     * @param companyBUType
     * @see com.ibm.tw.biz.b2c.uaa.type.CompanyBUType
     */
    public void setCompanyBUType(Integer companyBUType) {
        this.companyBUType = companyBUType;
    }

    /**
     * @see #defaultBranchId
     *
     * @return
     */
    public String getDefaultBranchId() {
        return defaultBranchId;
    }

    /**
     * @see #defaultBranchId
     *
     * @param brancId
     */
    public void setDefaultBranchId(String defaultBranchId) {
        this.defaultBranchId = defaultBranchId;
    }

    /**
     * 取得開戶時間
     *
     * @return
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * 取得聯絡電話
     *
     * @return
     */
    public String getTel() {
        return tel;
    }

    /**
     * 設定聯絡電話
     *
     * @param tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 取得 fax
     *
     * @return 傳回 fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * 設定 fax
     *
     * @param fax
     *            要設定的 fax
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * 行動電話
     *
     * @return
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 設定行動電話
     *
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 取得Email Address
     *
     * @return
     */
    public String getEmails() {
        return emails;
    }

    /**
     * 設定Email Address
     *
     * @param emails
     */
    public void setEmails(String emails) {
        this.emails = emails;
    }

    /**
     * @see #retryFlag
     * @return
     */
    public Integer getRetryFlag() {
        return retryFlag;
    }

    /**
     * @see #retryFlag
     * @param retryFlag
     */
    public void setRetryFlag(Integer retryFlag) {
        this.retryFlag = retryFlag;
    }

    /**
     * @return loginFlag
     * @see #loginFlag
     */
    public Integer getLoginFlag() {
        return loginFlag;
    }

    /**
     * @param loginFlag
     * @see #loginFlag
     */
    public void setLoginFlag(Integer loginFlag) {
        this.loginFlag = loginFlag;
    }

    /**
     * @return salaryFlag
     * @see #salaryFlag
     */
    public Integer getSalaryFlag() {
        return salaryFlag;
    }

    /**
     * @param salaryFlag
     * @see #salaryFlag
     */
    public void setSalaryFlag(Integer salaryFlag) {
        this.salaryFlag = salaryFlag;
    }

    /**
     * @return onlinePayeeFlag
     * @see #onlinePayeeFlag
     */
    public Integer getOnlinePayeeFlag() {
        return onlinePayeeFlag;
    }

    /**
     * @param onlinePayeeFlag
     * @see #onlinePayeeFlag
     */
    public void setOnlinePayeeFlag(Integer onlinePayeeFlag) {
        this.onlinePayeeFlag = onlinePayeeFlag;
    }

    /**
     * @return root1TxFlag
     * @see #root1TxFlag
     */
    public Integer getRoot1TxFlag() {
        return root1TxFlag;
    }

    /**
     * @param root1TxFlag
     * @see #root1TxFlag
     */
    public void setRoot1TxFlag(Integer root1TxFlag) {
        this.root1TxFlag = root1TxFlag;
    }

    /**
     * @return root2TxFlag
     * @see #root2TxFlag
     */
    public Integer getRoot2TxFlag() {
        return root2TxFlag;
    }

    /**
     * @param root2TxFlag
     * @see #root2TxFlag
     */
    public void setRoot2TxFlag(Integer root2TxFlag) {
        this.root2TxFlag = root2TxFlag;
    }

    /**
     * @see #payerAccountFlag
     * @return
     */
    public Integer getPayerAccountFlag() {
        return payerAccountFlag;
    }

    /**
     * @see #payerAccountFlag
     * @param payerAccountFlag
     */
    public void setPayerAccountFlag(Integer payerAccountFlag) {
        this.payerAccountFlag = payerAccountFlag;
    }

    /**
     * @see #payeeAccountFlag
     * @return
     */
    public Integer getPayeeAccountFlag() {
        return payeeAccountFlag;
    }

    /**
     * @see #payeeAccountFlag
     * @param payeeAccountFlag
     */
    public void setPayeeAccountFlag(Integer payeeAccountFlag) {
        this.payeeAccountFlag = payeeAccountFlag;
    }

    /**
     * @return faxFlag
     * @see #faxFlag
     */
    public Integer getFaxFlag() {
        return faxFlag;
    }

    /**
     * @param faxFlag
     * @see #faxFlag
     */
    public void setFaxFlag(Integer faxFlag) {
        this.faxFlag = faxFlag;
    }

    /**
     * @return twAtmFlag
     * @see #twAtmFlag
     */
    public Integer getTwAtmFlag() {
        return twAtmFlag;
    }

    /**
     * @param twAtmFlag
     * @see #twAtmFlag
     */
    public void setTwAtmFlag(Integer twAtmFlag) {
        this.twAtmFlag = twAtmFlag;
    }

    /**
     * @return twRemitFlag
     * @see #twRemitFlag
     */
    public Integer getTwRemitFlag() {
        return twRemitFlag;
    }

    /**
     * @param twRemitFlag
     * @see #twRemitFlag
     */
    public void setTwRemitFlag(Integer twRemitFlag) {
        this.twRemitFlag = twRemitFlag;
    }

    /**
     * @return twFxmlFlag
     * @see #twFxmlFlag
     */
    public Integer getTwFxmlFlag() {
        return twFxmlFlag;
    }

    /**
     * @param twFxmlFlag
     * @see #twFxmlFlag
     */
    public void setTwFxmlFlag(Integer twFxmlFlag) {
        this.twFxmlFlag = twFxmlFlag;
    }

    /**
     * @return twPayeeFlag
     * @see #twPayeeFlag
     */
    public Integer getTwPayeeFlag() {
        return twPayeeFlag;
    }

    /**
     * @param twPayeeFlag
     * @see #twPayeeFlag
     */
    public void setTwPayeeFlag(Integer twPayeeFlag) {
        this.twPayeeFlag = twPayeeFlag;
    }

    /**
     * @return twAmtQuota
     */
    public BigDecimal getTwAmtQuota() {
        return twAmtQuota;
    }

    /**
     * @param twAmtQuota
     *            的設定的 twAmtQuota
     */
    public void setTwAmtQuota(BigDecimal twAmtQuota) {
        this.twAmtQuota = twAmtQuota;
    }

    /**
     * @return twInterRemitQuota
     */
    public BigDecimal getTwInterRemitQuota() {
        return twInterRemitQuota;
    }

    /**
     * @param twInterRemitQuota
     *            的設定的 twInterRemitQuota
     */
    public void setTwInterRemitQuota(BigDecimal twInterRemitQuota) {
        this.twInterRemitQuota = twInterRemitQuota;
    }

    /**
     * 設定開戶時間
     *
     * @param registerTime
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 取得網銀帳戶註銷時間
     *
     * @return
     */
    public Date getCancelTime() {
        return cancelTime;
    }

    /**
     * 設定網銀帳戶註銷時間
     *
     * @param cancelTime
     */
    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    /**
     * @return lastBranchId
     * @see #lastBranchId
     */
    public String getLastBranchId() {
        return lastBranchId;
    }

    /**
     * @param lastBranchId
     * @see #lastBranchId
     */
    public void setLastBranchId(String lastBranchId) {
        this.lastBranchId = lastBranchId;
    }

    /**
     * @return lastEditorKey
     * @see #lastEditorKey
     */
    public Integer getLastEditorKey() {
        return lastEditorKey;
    }

    /**
     * @param lastEditorKey
     * @see #lastEditorKey
     */
    public void setLastEditorKey(Integer lastEditorKey) {
        this.lastEditorKey = lastEditorKey;
    }

    /**
     * @return lastManagerKey
     * @see #lastManagerKey
     */
    public Integer getLastManagerKey() {
        return lastManagerKey;
    }

    /**
     * @param lastManagerKey
     * @see #lastManagerKey
     */
    public void setLastManagerKey(Integer lastManagerKey) {
        this.lastManagerKey = lastManagerKey;
    }

    /**
     * 取得最後更新時間
     *
     * @return
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * 設定最後更新時間
     *
     * @param lastUpdateTime
     */
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * @return queryOnlyFlag
     * @see #queryOnlyFlag
     */
    public Integer getQueryOnlyFlag() {
        return queryOnlyFlag;
    }

    /**
     * @param queryOnlyFlag
     * @see #queryOnlyFlag
     */
    public void setQueryOnlyFlag(Integer queryOnlyFlag) {
        this.queryOnlyFlag = queryOnlyFlag;
    }

    /**
     * 取得 公司統編或身份證字號誤別碼
     * 
     * @return 傳回 companyUidDup。
     */
    public String getUidDup() {
        return uidDup;
    }

    /**
     * 設定 公司統編或身份證字號誤別碼
     * 
     * @param companyUidDup
     *            要設定的 companyUidDup。
     */
    public void setUidDup(String companyUidDup) {
        this.uidDup = companyUidDup;
    }

    /**
     * 取得 needRedoKyc
     * 
     * @return 傳回 needRedoKyc。
     */
    public Integer getKycRevalidated() {
        return kycRevalidated;
    }

    /**
     * 設定 needRedoKyc
     * 
     * @param needRedoKyc
     *            要設定的 needRedoKyc。
     */
    public void setKycRevalidated(Integer needRedoKyc) {
        this.kycRevalidated = needRedoKyc;
    }

    /**
     * 取得 kycLastUpdate
     * 
     * @return 傳回 kycLastUpdate。
     */
    public Date getKycRevalidatedDate() {
        return kycRevalidatedDate;
    }

    /**
     * 設定 kycLastUpdate
     * 
     * @param kycLastUpdate
     *            要設定的 kycLastUpdate。
     */
    public void setKycRevalidatedDate(Date kycLastUpdate) {
        this.kycRevalidatedDate = kycLastUpdate;
    }

    /**
     * 取得 massCheck
     * 
     * @return 傳回 massCheck。
     */
    public String getMassCheck() {
        return massCheck;
    }

    /**
     * 設定 massCheck
     * 
     * @param massCheck
     *            要設定的 massCheck。
     */
    public void setMassCheck(String massCheck) {
        this.massCheck = massCheck;
    }

}