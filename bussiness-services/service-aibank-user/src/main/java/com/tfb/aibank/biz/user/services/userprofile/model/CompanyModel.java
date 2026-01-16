package com.tfb.aibank.biz.user.services.userprofile.model;

import java.math.BigDecimal;
import java.util.Date;

import com.tfb.aibank.common.type.UaaLevelType;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)CompanyModel.java
 * 
 * <p>Description:公司資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/03, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "公司資料")
public class CompanyModel {

    /** 公司內部鍵值 */
    @Schema(description = "公司內部鍵值")
    private Integer companyKey;

    /** 公司統編或身份證字號 */
    @Schema(description = "公司統編或身份證字號")
    private String companyUid;

    /** 公司統編或身份證字號誤別碼 */
    @Schema(description = "公司統編或身份證字號誤別碼")
    private String uidDup;

    /** 公司狀態，1：正常；0：暫停；-1：註銷；-2：虛擬公司（未申請網銀的關係戶） */
    @Schema(description = "公司狀態，1：正常；0：暫停；-1：註銷；-2：虛擬公司（未申請網銀的關係戶）")
    private Integer status;

    /** 公司類型，1: 企業戶；2: 個人戶 */
    @Schema(description = "公司類型，1: 企業戶；2: 個人戶")
    private Integer companyKind;

    /** 公司BU類型，1: DBU；2: OBU */
    @Schema(description = "公司BU類型，1: DBU；2: OBU")
    private Integer companyBUType;

    /** 公司戶名 */
    @Schema(description = "公司戶名")
    private String companyName;

    /** 公司英文名稱 */
    @Schema(description = "公司英文名稱")
    private String companyEname;

    /** 申請的授權中心類別，0：無授權中心功能；1：單層授權；2：兩層式授權 */
    @Schema(description = "申請的授權中心類別，0：無授權中心功能；1：單層授權；2：兩層式授權")
    private Integer uaaLevel = UaaLevelType.TYPE0.getCode();

    /** 公司預設流程鍵值 */
    @Schema(description = "公司預設流程鍵值")
    private Integer defaultFlowSchemaKey;

    /** 流程套餐設定人員鍵值 */
    @Schema(description = "流程套餐設定人員鍵值")
    private Integer schemaSuitOperatorKey;

    /** 流程套餐設定時間 */
    @Schema(description = "流程套餐設定時間")
    private Date schemaSuitCreateTime;

    /** 創立/出生日期 */
    @Schema(description = "創立/出生日期")
    private Date establishDate;

    /** 往來分行代碼 */
    @Schema(description = "往來分行代碼")
    private String defaultBranchId;

    /** 聯絡電話 */
    @Schema(description = "聯絡電話")
    private String tel;

    /** 傳真電話 */
    @Schema(description = "傳真電話")
    private String fax;

    /** 行動電話 */
    @Schema(description = "行動電話")
    private String mobile;

    /** 公司Email Address */
    @Schema(description = "公司Email Address")
    private String emails;

    /** 啟用餘額不足重試扣款 */
    @Schema(description = "啟用餘額不足重試扣款")
    private Integer retryFlag;

    /** 是否啟用雙重登入認證 */
    @Schema(description = "是否啟用雙重登入認證")
    private Integer loginFlag;

    /** 是否啟用電子薪資單服務 */
    @Schema(description = "是否啟用電子薪資單服務")
    private Integer salaryFlag;

    /** 是否啟用線上約定收款人 */
    @Schema(description = "是否啟用線上約定收款人")
    private Integer onlinePayeeFlag;

    /** 授權管理者是否兼具交易權限 */
    @Schema(description = "授權管理者是否兼具交易權限")
    private Integer root1TxFlag;

    /** 授權主管是否兼具交易權限 */
    @Schema(description = "授權主管是否兼具交易權限")
    private Integer root2TxFlag;

    /** 所有存款帳號均視為約定轉出帳號（包含未來新開立的帳號） */
    @Schema(description = "所有存款帳號均視為約定轉出帳號（包含未來新開立的帳號）")
    private Integer payerAccountFlag;

    /** 所有轉出帳號均視為約定轉入帳號（包含未來新約定的轉出帳號） */
    @Schema(description = "所有轉出帳號均視為約定轉入帳號（包含未來新約定的轉出帳號）")
    private Integer payeeAccountFlag;

    /** 否啟用傳真服務 */
    @Schema(description = "否啟用傳真服務")
    private Integer faxFlag;

    /** 是否啟用ATM轉帳 */
    @Schema(description = "是否啟用ATM轉帳")
    private Integer twAtmFlag;

    /** 是否啟用通匯轉帳 */
    @Schema(description = "是否啟用通匯轉帳")
    private Integer twRemitFlag;

    /** 是否啟用FXML轉帳 */
    @Schema(description = "是否啟用FXML轉帳")
    private Integer twFxmlFlag;

    /** 轉入帳號是否限常用收款人 */
    @Schema(description = "轉入帳號是否限常用收款人")
    private Integer twPayeeFlag;

    /** ATM付款金額上限（元） */
    @Schema(description = "ATM付款金額上限（元）")
    private BigDecimal twAmtQuota;

    /** 跨行通匯付款金額上限（元） */
    @Schema(description = "跨行通匯付款金額上限（元）")
    private BigDecimal twInterRemitQuota;

    /** 開戶時間 */
    @Schema(description = "開戶時間")
    private Date registerTime;

    /** 網銀帳戶註銷時間 */
    @Schema(description = "網銀帳戶註銷時間")
    private Date cancelTime;

    /** 最後更新分行代碼 */
    @Schema(description = "最後更新分行代碼")
    private String lastBranchId;

    /** 最後更新經辦鍵值 */
    @Schema(description = "最後更新經辦鍵值")
    private Integer lastEditorKey;

    /** 最後更新主管鍵值 */
    @Schema(description = "最後更新主管鍵值")
    private Integer lastManagerKey;

    /** 最後更新時間 */
    @Schema(description = "最後更新時間")
    private Date lastUpdateTime;

    /** 申請功能限查詢 (1 : 不能勾選申辦業務子系統) */
    @Schema(description = "申請功能限查詢 (1 : 不能勾選申辦業務子系統)")
    private Integer queryOnlyFlag;

    /** 是否已重做 KYC ( 1: 是, 0: 否) */
    @Schema(description = "是否已重做 KYC ( 1: 是, 0: 否)")
    private Integer kycRevalidated;

    /** 重做 KYC 日期 */
    @Schema(description = "重做 KYC 日期")
    private Date kycRevalidatedDate;

    /** for TFB 客群代碼 */
    @Schema(description = "for TFB 客群代碼")
    private String massCheck;

    /** 交易被授權人身分證字號 */
    @Schema(description = "交易被授權人身分證字號")
    private String customeAuditId;

    public Integer getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    public String getCompanyUid() {
        return companyUid;
    }

    public void setCompanyUid(String companyUid) {
        this.companyUid = companyUid;
    }

    public String getUidDup() {
        return uidDup;
    }

    public void setUidDup(String uidDup) {
        this.uidDup = uidDup;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCompanyKind() {
        return companyKind;
    }

    public void setCompanyKind(Integer companyKind) {
        this.companyKind = companyKind;
    }

    public Integer getCompanyBUType() {
        return companyBUType;
    }

    public void setCompanyBUType(Integer companyBUType) {
        this.companyBUType = companyBUType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyEname() {
        return companyEname;
    }

    public void setCompanyEname(String companyEname) {
        this.companyEname = companyEname;
    }

    public Integer getUaaLevel() {
        return uaaLevel;
    }

    public void setUaaLevel(Integer uaaLevel) {
        this.uaaLevel = uaaLevel;
    }

    public Integer getDefaultFlowSchemaKey() {
        return defaultFlowSchemaKey;
    }

    public void setDefaultFlowSchemaKey(Integer defaultFlowSchemaKey) {
        this.defaultFlowSchemaKey = defaultFlowSchemaKey;
    }

    public Integer getSchemaSuitOperatorKey() {
        return schemaSuitOperatorKey;
    }

    public void setSchemaSuitOperatorKey(Integer schemaSuitOperatorKey) {
        this.schemaSuitOperatorKey = schemaSuitOperatorKey;
    }

    public Date getSchemaSuitCreateTime() {
        return schemaSuitCreateTime;
    }

    public void setSchemaSuitCreateTime(Date schemaSuitCreateTime) {
        this.schemaSuitCreateTime = schemaSuitCreateTime;
    }

    public Date getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    public String getDefaultBranchId() {
        return defaultBranchId;
    }

    public void setDefaultBranchId(String defaultBranchId) {
        this.defaultBranchId = defaultBranchId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public Integer getRetryFlag() {
        return retryFlag;
    }

    public void setRetryFlag(Integer retryFlag) {
        this.retryFlag = retryFlag;
    }

    public Integer getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(Integer loginFlag) {
        this.loginFlag = loginFlag;
    }

    public Integer getSalaryFlag() {
        return salaryFlag;
    }

    public void setSalaryFlag(Integer salaryFlag) {
        this.salaryFlag = salaryFlag;
    }

    public Integer getOnlinePayeeFlag() {
        return onlinePayeeFlag;
    }

    public void setOnlinePayeeFlag(Integer onlinePayeeFlag) {
        this.onlinePayeeFlag = onlinePayeeFlag;
    }

    public Integer getRoot1TxFlag() {
        return root1TxFlag;
    }

    public void setRoot1TxFlag(Integer root1TxFlag) {
        this.root1TxFlag = root1TxFlag;
    }

    public Integer getRoot2TxFlag() {
        return root2TxFlag;
    }

    public void setRoot2TxFlag(Integer root2TxFlag) {
        this.root2TxFlag = root2TxFlag;
    }

    public Integer getPayerAccountFlag() {
        return payerAccountFlag;
    }

    public void setPayerAccountFlag(Integer payerAccountFlag) {
        this.payerAccountFlag = payerAccountFlag;
    }

    public Integer getPayeeAccountFlag() {
        return payeeAccountFlag;
    }

    public void setPayeeAccountFlag(Integer payeeAccountFlag) {
        this.payeeAccountFlag = payeeAccountFlag;
    }

    public Integer getFaxFlag() {
        return faxFlag;
    }

    public void setFaxFlag(Integer faxFlag) {
        this.faxFlag = faxFlag;
    }

    public Integer getTwAtmFlag() {
        return twAtmFlag;
    }

    public void setTwAtmFlag(Integer twAtmFlag) {
        this.twAtmFlag = twAtmFlag;
    }

    public Integer getTwRemitFlag() {
        return twRemitFlag;
    }

    public void setTwRemitFlag(Integer twRemitFlag) {
        this.twRemitFlag = twRemitFlag;
    }

    public Integer getTwFxmlFlag() {
        return twFxmlFlag;
    }

    public void setTwFxmlFlag(Integer twFxmlFlag) {
        this.twFxmlFlag = twFxmlFlag;
    }

    public Integer getTwPayeeFlag() {
        return twPayeeFlag;
    }

    public void setTwPayeeFlag(Integer twPayeeFlag) {
        this.twPayeeFlag = twPayeeFlag;
    }

    public BigDecimal getTwAmtQuota() {
        return twAmtQuota;
    }

    public void setTwAmtQuota(BigDecimal twAmtQuota) {
        this.twAmtQuota = twAmtQuota;
    }

    public BigDecimal getTwInterRemitQuota() {
        return twInterRemitQuota;
    }

    public void setTwInterRemitQuota(BigDecimal twInterRemitQuota) {
        this.twInterRemitQuota = twInterRemitQuota;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getLastBranchId() {
        return lastBranchId;
    }

    public void setLastBranchId(String lastBranchId) {
        this.lastBranchId = lastBranchId;
    }

    public Integer getLastEditorKey() {
        return lastEditorKey;
    }

    public void setLastEditorKey(Integer lastEditorKey) {
        this.lastEditorKey = lastEditorKey;
    }

    public Integer getLastManagerKey() {
        return lastManagerKey;
    }

    public void setLastManagerKey(Integer lastManagerKey) {
        this.lastManagerKey = lastManagerKey;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getQueryOnlyFlag() {
        return queryOnlyFlag;
    }

    public void setQueryOnlyFlag(Integer queryOnlyFlag) {
        this.queryOnlyFlag = queryOnlyFlag;
    }

    public Integer getKycRevalidated() {
        return kycRevalidated;
    }

    public void setKycRevalidated(Integer kycRevalidated) {
        this.kycRevalidated = kycRevalidated;
    }

    public Date getKycRevalidatedDate() {
        return kycRevalidatedDate;
    }

    public void setKycRevalidatedDate(Date kycRevalidatedDate) {
        this.kycRevalidatedDate = kycRevalidatedDate;
    }

    public String getMassCheck() {
        return massCheck;
    }

    public void setMassCheck(String massCheck) {
        this.massCheck = massCheck;
    }

    public String getCustomeAuditId() {
        return customeAuditId;
    }

    public void setCustomeAuditId(String customeAuditId) {
        this.customeAuditId = customeAuditId;
    }

}
