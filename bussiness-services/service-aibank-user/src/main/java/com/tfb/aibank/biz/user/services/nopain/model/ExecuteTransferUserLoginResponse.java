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
package com.tfb.aibank.biz.user.services.nopain.model;

import java.util.Date;
import java.util.List;

import com.tfb.aibank.chl.session.vo.CardUserProfileVo;
import com.tfb.aibank.chl.session.vo.CompanyVo;
import com.tfb.aibank.chl.session.vo.EB5556981Response;
import com.tfb.aibank.chl.session.vo.MbDeviceInfoVo;
import com.tfb.aibank.chl.session.vo.UserLoginVo;
import com.tfb.aibank.chl.session.vo.UserVo;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)ExecuteTransferUserLoginResponse.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/02, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class ExecuteTransferUserLoginResponse {

    public ExecuteTransferUserLoginResponse() {
        this.companyVo = new CompanyVo();
        this.userVo = new UserVo();
        this.userLoginVo = new UserLoginVo();
        this.cardUserProfileVo = new CardUserProfileVo();
        this.mbDeviceInfoVo = new MbDeviceInfoVo();
    }

    /** 登入異常代碼 */
    @Schema(description = "錯誤碼")
    private String errorCode;

    /** 錯誤描述 */
    @Schema(description = "錯誤描述")
    private String systemId;

    /** 錯誤系統別 */
    @Schema(description = "錯誤系統別")
    private String errorDesc;

    // 身分證字號
    @Schema(description = "身分證字號")
    private String uid;

    // 誤別碼
    @Schema(description = "誤別碼")
    private String uidDup;

    // 使用者代碼
    @Schema(description = "使用者代碼")
    private String uuid;

    // 使用者類型
    @Schema(description = "使用者類型")
    private Integer companyKind;

    // 會員類別
    @Schema(description = "會員類別")
    private String customerType;

    @Schema(description = "User_Profile_Entity")
    private UserVo userVo;

    /** Company_Entity */
    @Schema(description = "Company_Entity")
    private CompanyVo companyVo;

    @Schema(description = "EB5556981 登入下行電文")
    private EB5556981Response loginMsgRs;

    @Schema(description = "Card_User_Profile_Entity")
    private CardUserProfileVo cardUserProfileVo;

    @Schema(description = "MB_Device_Info_Entity")
    private MbDeviceInfoVo mbDeviceInfoVo;

    @Schema(description = "User_Log_Entity")
    private UserLoginVo userLoginVo;

    @Schema(description = "Name Code")
    private String nameCode;

    /** 電話號碼 */
    @Schema(description = "電話號碼")
    private String mobileNo;

    /** 生日 */
    @Schema(description = "生日")
    private Date birthDay;

    @Schema(description = "E-Mail")
    private List<String> email;

    /** 登入狀態 */
    @Schema(description = "登入狀態")
    private int loginStatus;

    /** 登入的IP判斷國別資料 */
    @Schema(description = "國別資料")
    private Integer locationId;

    /** 重複登入 平台 */
    @Schema(description = "重複登入平台")
    private String channel;

    /** 前次登入時間 */
    @Schema(description = "前次登入時間")
    private Date preLoginDate;

    // 登入成功狀態
    @Schema(description = "登入狀態")
    private boolean isSuccess;

    // 存戶 / 卡戶
    @Schema(description = "存戶/卡戶")
    private boolean isBankUser;

    // 卡友 email;
    @Schema(description = "卡友 email")
    private String cardEmail = "";

    // 多使用者代碼客戶判斷
    @Schema(description = "多使用者代碼客戶判斷")
    private boolean isMultiUser;

    @Schema(description = "USER_KEY")
    private Integer userKey;

    @Schema(description = "COMPANY_KEY")
    private Integer companyKey;

    /** 顯示變更密碼提示 */
    @Schema(description = "顯示變更密碼提示")
    private boolean isShowChangeTip;

    /** 是否在 AccountCreditcardCheck 黑名單中 */
    @Schema(description = "是否在 AccountCreditcardCheck 黑名單中")
    private boolean isInAccountCreditcardCheck;

    /** Banc 與 400 的生日是否相同 */
    @Schema(description = "Banc 與 400 的生日是否相同")
    private boolean isSameBirthday;

    /** 登入方式 */
    @Schema(description = "登入方式")
    private String loginMethod;

    /** 目前裝置綁定資訊(原行銀) */
    @Schema(description = "目前裝置綁定資訊(原行銀)")
    private List<String> deviceBindingInfo;

    /** 目前裝置訂閱推播資訊(原行銀) */
    @Schema(description = "目前裝置訂閱推播資訊(原行銀)")
    private List<String> deviceSubPushCode;

    /**
     * 用戶旅程註記
     */
    @Schema(description = "用戶旅程註記")
    private int onboardingType;

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
     * @return the systemId
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * @param systemId
     *            the systemId to set
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    /**
     * @return the errorDesc
     */
    public String getErrorDesc() {
        return errorDesc;
    }

    /**
     * @param errorDesc
     *            the errorDesc to set
     */
    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    /**
     * @return the userVo
     */
    public UserVo getUserVo() {
        return userVo;
    }

    /**
     * @param userVo
     *            the userVo to set
     */
    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }

    /**
     * @return the companyVo
     */
    public CompanyVo getCompanyVo() {
        return companyVo;
    }

    /**
     * @param companyVo
     *            the companyVo to set
     */
    public void setCompanyVo(CompanyVo companyVo) {
        this.companyVo = companyVo;
    }

    /**
     * @return the loginMsgRs
     */
    public EB5556981Response getLoginMsgRs() {
        return loginMsgRs;
    }

    /**
     * @param loginMsgRs
     *            the loginMsgRs to set
     */
    public void setLoginMsgRs(EB5556981Response loginMsgRs) {
        this.loginMsgRs = loginMsgRs;
    }

    /**
     * @return the cardUserProfileVo
     */
    public CardUserProfileVo getCardUserProfileVo() {
        return cardUserProfileVo;
    }

    /**
     * @param cardUserProfileVo
     *            the cardUserProfileVo to set
     */
    public void setCardUserProfileVo(CardUserProfileVo cardUserProfileVo) {
        this.cardUserProfileVo = cardUserProfileVo;
    }

    /**
     * @return the mbDeviceInfoVo
     */
    public MbDeviceInfoVo getMbDeviceInfoVo() {
        return mbDeviceInfoVo;
    }

    /**
     * @param mbDeviceInfoVo
     *            the mbDeviceInfoVo to set
     */
    public void setMbDeviceInfoVo(MbDeviceInfoVo mbDeviceInfoVo) {
        this.mbDeviceInfoVo = mbDeviceInfoVo;
    }

    /**
     * @return the userLoginVo
     */
    public UserLoginVo getUserLoginVo() {
        return userLoginVo;
    }

    /**
     * @param userLoginVo
     *            the userLoginVo to set
     */
    public void setUserLoginVo(UserLoginVo userLoginVo) {
        this.userLoginVo = userLoginVo;
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
     * @return the mobileNo
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * @param mobileNo
     *            the mobileNo to set
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     * @return the birthDay
     */
    public Date getBirthDay() {
        return birthDay;
    }

    /**
     * @param birthDay
     *            the birthDay to set
     */
    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    /**
     * @return the email
     */
    public List<String> getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(List<String> email) {
        this.email = email;
    }

    /**
     * @return the loginStatus
     */
    public int getLoginStatus() {
        return loginStatus;
    }

    /**
     * @param loginStatus
     *            the loginStatus to set
     */
    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }

    /**
     * @return the locationId
     */
    public Integer getLocationId() {
        return locationId;
    }

    /**
     * @param locationId
     *            the locationId to set
     */
    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    /**
     * @return the channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * @param channel
     *            the channel to set
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * @return the preLoginDate
     */
    public Date getPreLoginDate() {
        return preLoginDate;
    }

    /**
     * @param preLoginDate
     *            the preLoginDate to set
     */
    public void setPreLoginDate(Date preLoginDate) {
        this.preLoginDate = preLoginDate;
    }

    /**
     * @return the isSuccess
     */
    public boolean isSuccess() {
        return isSuccess;
    }

    /**
     * @param isSuccess
     *            the isSuccess to set
     */
    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    /**
     * @return the isBankUser
     */
    public boolean isBankUser() {
        return isBankUser;
    }

    /**
     * @param isBankUser
     *            the isBankUser to set
     */
    public void setBankUser(boolean isBankUser) {
        this.isBankUser = isBankUser;
    }

    /**
     * @return the cardEmail
     */
    public String getCardEmail() {
        return cardEmail;
    }

    /**
     * @param cardEmail
     *            the cardEmail to set
     */
    public void setCardEmail(String cardEmail) {
        this.cardEmail = cardEmail;
    }

    /**
     * @return the isMultiUser
     */
    public boolean isMultiUser() {
        return isMultiUser;
    }

    /**
     * @param isMultiUser
     *            the isMultiUser to set
     */
    public void setMultiUser(boolean isMultiUser) {
        this.isMultiUser = isMultiUser;
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
     * @return the isShowChangeTip
     */
    public boolean isShowChangeTip() {
        return isShowChangeTip;
    }

    /**
     * @param isShowChangeTip
     *            the isShowChangeTip to set
     */
    public void setShowChangeTip(boolean isShowChangeTip) {
        this.isShowChangeTip = isShowChangeTip;
    }

    /**
     * @return the isInAccountCreditcardCheck
     */
    public boolean isInAccountCreditcardCheck() {
        return isInAccountCreditcardCheck;
    }

    /**
     * @param isInAccountCreditcardCheck
     *            the isInAccountCreditcardCheck to set
     */
    public void setInAccountCreditcardCheck(boolean isInAccountCreditcardCheck) {
        this.isInAccountCreditcardCheck = isInAccountCreditcardCheck;
    }

    /**
     * @return the isSameBirthday
     */
    public boolean isSameBirthday() {
        return isSameBirthday;
    }

    /**
     * @param isSameBirthday
     *            the isSameBirthday to set
     */
    public void setSameBirthday(boolean isSameBirthday) {
        this.isSameBirthday = isSameBirthday;
    }

    /**
     * @return the loginMethod
     */
    public String getLoginMethod() {
        return loginMethod;
    }

    /**
     * @param loginMethod
     *            the loginMethod to set
     */
    public void setLoginMethod(String loginMethod) {
        this.loginMethod = loginMethod;
    }

    /**
     * @return the deviceBindingInfo
     */
    public List<String> getDeviceBindingInfo() {
        return deviceBindingInfo;
    }

    /**
     * @param deviceBindingInfo
     *            the deviceBindingInfo to set
     */
    public void setDeviceBindingInfo(List<String> deviceBindingInfo) {
        this.deviceBindingInfo = deviceBindingInfo;
    }

    /**
     * @return the deviceSubPushCode
     */
    public List<String> getDeviceSubPushCode() {
        return deviceSubPushCode;
    }

    /**
     * @param deviceSubPushCode
     *            the deviceSubPushCode to set
     */
    public void setDeviceSubPushCode(List<String> deviceSubPushCode) {
        this.deviceSubPushCode = deviceSubPushCode;
    }

    /**
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid
     *            the uid to set
     */
    public void setUid(String uid) {
        this.uid = uid;
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
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     *            the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
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
     * @return the customerType
     */
    public String getCustomerType() {
        return customerType;
    }

    /**
     * @param customerType
     *            the customerType to set
     */
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    /**
     * @return the onboardingType
     */
    public int getOnboardingType() {
        return onboardingType;
    }

    /**
     * @param onboardingType
     *            the onboardingType to set
     */
    public void setOnboardingType(int onboardingType) {
        this.onboardingType = onboardingType;
    }

}
