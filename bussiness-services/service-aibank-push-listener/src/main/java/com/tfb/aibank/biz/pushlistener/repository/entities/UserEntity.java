package com.tfb.aibank.biz.pushlistener.repository.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)UserEntity.java
 * 
 * <p>Description:USER_PROFILE Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, Evan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "USER_PROFILE")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 使用者鍵值 */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
    @Column(name = "USER_KEY")
    private Integer userKey;

    /** 公司鍵值 */
    @Basic
    @Column(name = "COMPANY_KEY")
    private Integer companyKey;

    /** 使用者代號 */
    @Basic
    @Column(name = "USER_UUID")
    private String userUuid;

    /** 使用者密碼 */
    @Basic
    @Column(name = "PASSWORD")
    private String secret;
    /** 暱稱 */
    @Basic
    @Column(name = "NICK_NAME")
    private String nickName;
    /** 頭像設定 */
    @Basic
    @Column(name = "AVATAR")
    private String avatar;

    /** 使用者中文姓名 */
    @Basic
    @Column(name = "USER_CNAME")
    private String userCname;

    /** 使用者英文姓名 */
    @Basic
    @Column(name = "USER_ENAME")
    private String userEname;

    /** 狀態 */
    @Basic
    @Column(name = "STATUS")
    private int status;

    /** 使用者類別 */
    @Basic
    @Column(name = "USER_TYPE")
    private int userType;

    /** 語系 */
    @Basic
    @Column(name = "LOCALE")
    private String locale;

    /** OTP安控類型 */
    @Basic
    @Column(name = "OTP_TYPE")
    private int otpType;

    /** */
    @Basic
    @Column(name = "OTP_CERT")
    private String otpCert;

    /** 晶片金融卡安控類型 */
    @Basic
    @Column(name = "SMART_CARD_TYPE")
    private int smartCardType;

    /** FXML安控類型 */
    @Basic
    @Column(name = "FXML_TYPE")
    private int fxmlType;

    /** FXML認證資料 */
    @Basic
    @Column(name = "FXML_CERT")
    private String fxmlCert;

    /** 行動銀行可否登入 */
    @Basic
    @Column(name = "MOBILE_RIGHT")
    private int mobileRight;

    /** 限制登入週期 */
    @Basic
    @Column(name = "LOGIN_PERIOD")
    private String loginPeriod;

    /** 限制登入起始時間 */
    @Basic
    @Column(name = "LOGIN_START_TIME")
    private String loginStartTime;

    /** 限制登入起始時間 */
    @Basic
    @Column(name = "LOGIN_END_TIME")
    private String loginEndTime;

    /** 部門名稱 */
    @Basic
    @Column(name = "DEPARTMENT_NAME")
    private String departmentName;

    /** 聯絡電話（區碼） */
    @Basic
    @Column(name = "TEL1")
    private String tel1;

    /** 聯絡電話 */
    @Basic
    @Column(name = "TEL2")
    private String tel2;

    /** 聯絡電話（分機） */
    @Basic
    @Column(name = "TEL3")
    private String tel3;

    /** 傳真（區碼） */
    @Basic
    @Column(name = "FAX1")
    private String fax1;

    /** 傳真 */
    @Basic
    @Column(name = "FAX2")
    private String fax2;

    /** 使用者所屬戶名代碼 */
    @Basic
    @Column(name = "NAME_CODE")
    private String nameCode;

    /** 行動電話 */
    @Basic
    @Column(name = "MOBILE")
    private String mobile;

    /** Email Address */
    @Basic
    @Column(name = "EMAILS")
    private String emails;

    /** 台幣單筆編輯限額 */
    @Basic
    @Column(name = "TW_EDIT_QUOTA")
    private BigDecimal twEditQuota;

    /** 台幣單筆審核限額 */
    @Basic
    @Column(name = "TW_VERIFY_QUOTA")
    private BigDecimal twVerifyQuota;

    /** 台幣單筆放行限額 */
    @Basic
    @Column(name = "TW_PASS_QUOTA")
    private BigDecimal twPassQuota;

    /** 台幣單日累計放行限額 */
    @Basic
    @Column(name = "TW_DAILY_PASS_QUOTA")
    private BigDecimal twDailyPassQuota;

    /** 外幣單筆編輯限額 */
    @Basic
    @Column(name = "FX_EDIT_QUOTA")
    private BigDecimal fxEditQuota;

    /** 外幣單筆審核限額 */
    @Basic
    @Column(name = "FX_VERIFY_QUOTA")
    private BigDecimal fxVerifyQuota;

    /** 外幣單筆放行限額 */
    @Basic
    @Column(name = "FX_PASS_QUOTA")
    private BigDecimal fxPassQuota;

    /** 外幣單日累計放行限額 */
    @Basic
    @Column(name = "FX_DAILY_PASS_QUOTA")
    private BigDecimal fxDailyPassQuota;

    /** 台幣放行累計日期 */
    @Basic
    @Column(name = "TW_PASS_DATE")
    private Date twPassDate;

    /** 台幣當日放行總金額 */
    @Basic
    @Column(name = "TW_TOTAL_PASS_AMT")
    private BigDecimal twTotalPassAmt;

    /** 外幣放行累計日期 */
    @Basic
    @Column(name = "FX_PASS_DATE")
    private Date fxPassDate;

    /** 外幣當日放行總金額 */
    @Basic
    @Column(name = "FX_TOTAL_PASS_AMT")
    private BigDecimal fxTotalPassAmt;

    /** 是否隱藏外幣帳戶0:否 1:是 */
    @Basic
    @Column(name = "HIDDEN_FX_ACCOUNT")
    private String hiddenFxAccount;

    /** 信用卡網路會員修改密碼時間 */
    @Basic
    @Column(name = "TX_DATE")
    private Date txDate;

    /** 是否隱藏貸款餘額為零帳戶0:否 1:是) */
    @Basic
    @Column(name = "HIDDEN_LOAN_ACCOUNT")
    private String hiddenLoanAccount;

    /**
     * @return the userKey
     */
    public Integer getUserKey() {
        return userKey;
    }

    /**
     * @param userKey
     *            the userKey to set
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
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
     * @return the userUuixd
     */
    public String getUserUuid() {
        return userUuid;
    }

    /**
     * @param userUuixd
     *            the userUuixd to set
     */
    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    /**
     * @return the secrxt
     */
    public String getSecret() {
        return secret;
    }

    /**
     * @param secrxt
     *            the secrxt to set
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * @return the nickName
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName
     *            the nickName to set
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar
     *            the avatar to set
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * @return the userCname
     */
    public String getUserCname() {
        return userCname;
    }

    /**
     * @param userCname
     *            the userCname to set
     */
    public void setUserCname(String userCname) {
        this.userCname = userCname;
    }

    /**
     * @return the userEname
     */
    public String getUserEname() {
        return userEname;
    }

    /**
     * @param userEname
     *            the userEname to set
     */
    public void setUserEname(String userEname) {
        this.userEname = userEname;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the userType
     */
    public int getUserType() {
        return userType;
    }

    /**
     * @param userType
     *            the userType to set
     */
    public void setUserType(int userType) {
        this.userType = userType;
    }

    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @param locale
     *            the locale to set
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * @return the otpType
     */
    public int getOtpType() {
        return otpType;
    }

    /**
     * @param otpType
     *            the otpType to set
     */
    public void setOtpType(int otpType) {
        this.otpType = otpType;
    }

    /**
     * @return the otpCert
     */
    public String getOtpCert() {
        return otpCert;
    }

    /**
     * @param otpCert
     *            the otpCert to set
     */
    public void setOtpCert(String otpCert) {
        this.otpCert = otpCert;
    }

    /**
     * @return the smartCardType
     */
    public int getSmartCardType() {
        return smartCardType;
    }

    /**
     * @param smartCardType
     *            the smartCardType to set
     */
    public void setSmartCardType(int smartCardType) {
        this.smartCardType = smartCardType;
    }

    /**
     * @return the fxmlType
     */
    public int getFxmlType() {
        return fxmlType;
    }

    /**
     * @param fxmlType
     *            the fxmlType to set
     */
    public void setFxmlType(int fxmlType) {
        this.fxmlType = fxmlType;
    }

    /**
     * @return the fxmlCert
     */
    public String getFxmlCert() {
        return fxmlCert;
    }

    /**
     * @param fxmlCert
     *            the fxmlCert to set
     */
    public void setFxmlCert(String fxmlCert) {
        this.fxmlCert = fxmlCert;
    }

    /**
     * @return the mobileRight
     */
    public int getMobileRight() {
        return mobileRight;
    }

    /**
     * @param mobileRight
     *            the mobileRight to set
     */
    public void setMobileRight(int mobileRight) {
        this.mobileRight = mobileRight;
    }

    /**
     * @return the loginPeriod
     */
    public String getLoginPeriod() {
        return loginPeriod;
    }

    /**
     * @param loginPeriod
     *            the loginPeriod to set
     */
    public void setLoginPeriod(String loginPeriod) {
        this.loginPeriod = loginPeriod;
    }

    /**
     * @return the loginStartTime
     */
    public String getLoginStartTime() {
        return loginStartTime;
    }

    /**
     * @param loginStartTime
     *            the loginStartTime to set
     */
    public void setLoginStartTime(String loginStartTime) {
        this.loginStartTime = loginStartTime;
    }

    /**
     * @return the loginEndTime
     */
    public String getLoginEndTime() {
        return loginEndTime;
    }

    /**
     * @param loginEndTime
     *            the loginEndTime to set
     */
    public void setLoginEndTime(String loginEndTime) {
        this.loginEndTime = loginEndTime;
    }

    /**
     * @return the departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName
     *            the departmentName to set
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * @return the tel1
     */
    public String getTel1() {
        return tel1;
    }

    /**
     * @param tel1
     *            the tel1 to set
     */
    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    /**
     * @return the tel2
     */
    public String getTel2() {
        return tel2;
    }

    /**
     * @param tel2
     *            the tel2 to set
     */
    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    /**
     * @return the tel3
     */
    public String getTel3() {
        return tel3;
    }

    /**
     * @param tel3
     *            the tel3 to set
     */
    public void setTel3(String tel3) {
        this.tel3 = tel3;
    }

    /**
     * @return the fax1
     */
    public String getFax1() {
        return fax1;
    }

    /**
     * @param fax1
     *            the fax1 to set
     */
    public void setFax1(String fax1) {
        this.fax1 = fax1;
    }

    /**
     * @return the fax2
     */
    public String getFax2() {
        return fax2;
    }

    /**
     * @param fax2
     *            the fax2 to set
     */
    public void setFax2(String fax2) {
        this.fax2 = fax2;
    }

    /**
     * @return the nameCode
     */
    public String getNameCode() {
        return nameCode;
    }

    /**
     * @param nameCode
     *            the nameCode to set
     */
    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
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
     * @return the twEditQuota
     */
    public BigDecimal getTwEditQuota() {
        return twEditQuota;
    }

    /**
     * @param twEditQuota
     *            the twEditQuota to set
     */
    public void setTwEditQuota(BigDecimal twEditQuota) {
        this.twEditQuota = twEditQuota;
    }

    /**
     * @return the twVerifyQuota
     */
    public BigDecimal getTwVerifyQuota() {
        return twVerifyQuota;
    }

    /**
     * @param twVerifyQuota
     *            the twVerifyQuota to set
     */
    public void setTwVerifyQuota(BigDecimal twVerifyQuota) {
        this.twVerifyQuota = twVerifyQuota;
    }

    /**
     * @return the twPassQuota
     */
    public BigDecimal getTwPassQuota() {
        return twPassQuota;
    }

    /**
     * @param twPassQuota
     *            the twPassQuota to set
     */
    public void setTwPassQuota(BigDecimal twPassQuota) {
        this.twPassQuota = twPassQuota;
    }

    /**
     * @return the twDailyPassQuota
     */
    public BigDecimal getTwDailyPassQuota() {
        return twDailyPassQuota;
    }

    /**
     * @param twDailyPassQuota
     *            the twDailyPassQuota to set
     */
    public void setTwDailyPassQuota(BigDecimal twDailyPassQuota) {
        this.twDailyPassQuota = twDailyPassQuota;
    }

    /**
     * @return the fxEditQuota
     */
    public BigDecimal getFxEditQuota() {
        return fxEditQuota;
    }

    /**
     * @param fxEditQuota
     *            the fxEditQuota to set
     */
    public void setFxEditQuota(BigDecimal fxEditQuota) {
        this.fxEditQuota = fxEditQuota;
    }

    /**
     * @return the fxVerifyQuota
     */
    public BigDecimal getFxVerifyQuota() {
        return fxVerifyQuota;
    }

    /**
     * @param fxVerifyQuota
     *            the fxVerifyQuota to set
     */
    public void setFxVerifyQuota(BigDecimal fxVerifyQuota) {
        this.fxVerifyQuota = fxVerifyQuota;
    }

    /**
     * @return the fxPassQuota
     */
    public BigDecimal getFxPassQuota() {
        return fxPassQuota;
    }

    /**
     * @param fxPassQuota
     *            the fxPassQuota to set
     */
    public void setFxPassQuota(BigDecimal fxPassQuota) {
        this.fxPassQuota = fxPassQuota;
    }

    /**
     * @return the fxDailyPassQuota
     */
    public BigDecimal getFxDailyPassQuota() {
        return fxDailyPassQuota;
    }

    /**
     * @param fxDailyPassQuota
     *            the fxDailyPassQuota to set
     */
    public void setFxDailyPassQuota(BigDecimal fxDailyPassQuota) {
        this.fxDailyPassQuota = fxDailyPassQuota;
    }

    /**
     * @return the twPassDate
     */
    public Date getTwPassDate() {
        return twPassDate;
    }

    /**
     * @param twPassDate
     *            the twPassDate to set
     */
    public void setTwPassDate(Date twPassDate) {
        this.twPassDate = twPassDate;
    }

    /**
     * @return the twTotalPassAmt
     */
    public BigDecimal getTwTotalPassAmt() {
        return twTotalPassAmt;
    }

    /**
     * @param twTotalPassAmt
     *            the twTotalPassAmt to set
     */
    public void setTwTotalPassAmt(BigDecimal twTotalPassAmt) {
        this.twTotalPassAmt = twTotalPassAmt;
    }

    /**
     * @return the fxPassDate
     */
    public Date getFxPassDate() {
        return fxPassDate;
    }

    /**
     * @param fxPassDate
     *            the fxPassDate to set
     */
    public void setFxPassDate(Date fxPassDate) {
        this.fxPassDate = fxPassDate;
    }

    /**
     * @return the fxTotalPassAmt
     */
    public BigDecimal getFxTotalPassAmt() {
        return fxTotalPassAmt;
    }

    /**
     * @param fxTotalPassAmt
     *            the fxTotalPassAmt to set
     */
    public void setFxTotalPassAmt(BigDecimal fxTotalPassAmt) {
        this.fxTotalPassAmt = fxTotalPassAmt;
    }

    /**
     * @return the hiddenFxAccount
     */
    public String getHiddenFxAccount() {
        return hiddenFxAccount;
    }

    /**
     * @param hiddenFxAccount
     *            the hiddenFxAccount to set
     */
    public void setHiddenFxAccount(String hiddenFxAccount) {
        this.hiddenFxAccount = hiddenFxAccount;
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
     * @return the hiddenLoanAccount
     */
    public String getHiddenLoanAccount() {
        return hiddenLoanAccount;
    }

    /**
     * @param hiddenLoanAccount
     *            the hiddenLoanAccount to set
     */
    public void setHiddenLoanAccount(String hiddenLoanAccount) {
        this.hiddenLoanAccount = hiddenLoanAccount;
    }

}