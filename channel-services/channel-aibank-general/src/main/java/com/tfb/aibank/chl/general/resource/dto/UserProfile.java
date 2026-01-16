package com.tfb.aibank.chl.general.resource.dto;

import java.math.BigDecimal;
import java.util.Date;

// @formatter:off
/**
 * @(#)UserProfile.java
 * 
 * <p>Description:使用者資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/03, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class UserProfile {

    /** 公司類型 */
    private Integer companyKind;

    /** 誤別碼 */
    private String uidDup;

    /** 使用者鍵值 */
    private Integer userKey;
    /** 身分證字號 */
    private String custId;
    /** 使用者代號 */
    private String userUuid;
    /** 使用者密碼 */
    private String paxword;
    /** 暱稱 */
    private String nickName;
    /** 頭像設定 */
    private String avatar;
    /** 使用者中文姓名 */
    private String userCname;
    /** 使用者英文姓名 */
    private String userEname;
    /** 狀態 */
    private String status;
    /** 使用者類別 */
    private String userType;
    /** 語系 */
    private String locale;
    /** OTP安控類型 */
    private String otpType;
    /**  */
    private String otpCert;
    /** 聯絡電話（區碼） */
    private String tel1;
    /** 聯絡電話 */
    private String tel2;
    /** 聯絡電話（分機） */
    private String tel3;
    /** 傳真（區碼） */
    private String fax1;
    /** 傳真 */
    private String fax2;
    /** 使用者所屬戶名代碼 */
    private String nameCode;
    /** 行動電話 */
    private String mobile;
    /** Email Address */
    private String emails;
    /** 台幣單筆編輯限額 */
    private BigDecimal twEditQuota;
    /** 台幣單筆審核限額 */
    private BigDecimal twVerifyQuota;
    /** 台幣單筆放行限額 */
    private BigDecimal twPassQuota;
    /** 台幣單日累計放行限額 */
    private BigDecimal twDailyPassQuota;
    /** 外幣單筆編輯限額 */
    private BigDecimal fxEditQuota;
    /** 外幣單筆審核限額 */
    private BigDecimal fxVerifyQuota;
    /** 外幣單筆放行限額 */
    private BigDecimal fxPassQuota;
    /** 外幣單日累計放行限額 */
    private BigDecimal fxDailyPassQuota;
    /** 台幣放行累計日期 */
    private Date twPassDate;
    /** 台幣當日放行總金額 */
    private BigDecimal twTotalPassAmt;
    /** 外幣放行累計日期 */
    private Date fxPassDate;
    /** 外幣當日放行總金額 */
    private BigDecimal fxTotalPassAmt;
    /** 是否隱藏外幣帳戶0:否 1:是 */
    private String hiddenFxAccount;
    /** 信用卡網路會員修改密碼時間 */
    private Date txDate;
    /** 是否隱藏貸款餘額為零帳戶0:否 1:是) */
    private String hiddenLoanAccount;

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
     * @return the paxword
     */
    public String getPaxword() {
        return paxword;
    }

    /**
     * @param paxword
     *            the paxword to set
     */
    public void setPaxword(String paxword) {
        this.paxword = paxword;
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
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the userType
     */
    public String getUserType() {
        return userType;
    }

    /**
     * @param userType
     *            the userType to set
     */
    public void setUserType(String userType) {
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
    public String getOtpType() {
        return otpType;
    }

    /**
     * @param otpType
     *            the otpType to set
     */
    public void setOtpType(String otpType) {
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
