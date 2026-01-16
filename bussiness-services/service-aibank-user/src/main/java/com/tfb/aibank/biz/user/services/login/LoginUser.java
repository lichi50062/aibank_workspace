package com.tfb.aibank.biz.user.services.login;

import java.util.Date;
import java.util.List;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.biz.user.repository.aib.entities.MbDevicePushInfoEntity;
import com.tfb.aibank.biz.user.repository.entities.CardUserProfileEntity;
import com.tfb.aibank.biz.user.repository.entities.CompanyEntity;
import com.tfb.aibank.biz.user.repository.entities.MbDeviceInfoEntity;
import com.tfb.aibank.biz.user.repository.entities.UserEntity;
import com.tfb.aibank.biz.user.repository.entities.UserLoginEntity;

import tw.com.ibm.mf.eb.EB5556981SvcRsType;

// @formatter:off
/**
 * @(#)LoginUser.java
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
public class LoginUser {

    /** 登入異常代碼 */
    private ActionException error = null;

    // * User_Profile Entity
    private UserEntity userEntity;

    // * Company Entity
    private CompanyEntity companyEntity;

    // 登入下行電文
    private EB5556981SvcRsType loginMsgRs;

    // Card_User_Profile
    private CardUserProfileEntity cardUserProfileEntity;

    // MB_Device_Info
    private MbDeviceInfoEntity mbDeviceInfoEntity;
    // MB_Device_Info 其它 裝置
    private MbDeviceInfoEntity mbDeviceInfoEntityBindedByOtherDevice;

    // MB_Device_Push Info
    private MbDevicePushInfoEntity mbDevicePushInfoEntity;

    // MB_Device_Push Info 其它裝置
    private MbDevicePushInfoEntity mbDevicePushInfoEntityBindedByOtherDevice;

 
    // User_Login
    private UserLoginEntity userLoginEntity;

    private String tempNameCode;

    private String nameCode;

    private String eUid;

    private String nextTaskId;

    private String nextParam;

    private boolean needDisplayMergeContent;

    /** 首次啟用狀態狀態 */
    private boolean isEnableStatus;

    private String custId;

    // 重複登入 平台
    private String channel;
    private Date preLoginDate;
    
    // 雙重驗證
    private boolean isTwoFactorAuth;

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

    // 0-不發 1-成功通知 2-失敗通知
    private int mailType;

    // 0-不發 1-成功通知 2-失敗通知
    private int smsType;

    // 0-不發 1-成功通知 2-失敗通知 */
    private int pushType;

    // 電話號碼
    private String mobileNo;

    // 生日
    private Date birthDay;

    // E-mail
    private List<String> email;

    // 登入狀態
    private int loginStatus;

    private boolean isMultiUser;

    // 同一綁定裝置登入
    private boolean isTheSame;

    // 存戶 / 卡戶
    private boolean isBankUser;

    // 卡友 email;
    private String cardEmail = "";

    private String countryCode;

    private String countryName;

    private Long ipFrom;
    
    private Long ipEnd;
    
    /** 是否在 AccountCreditcardCheck 黑名單中 */
    private boolean isInAccountCreditcardCheck;

    /** Banc 與 400 的生日是否相同 */
    private boolean isSameBirthday;

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

    /** 顯示變更密碼提示 */
    private boolean isShowChangeTip;

    /**
     * @return the userEntity
     */
    public UserEntity getUserEntity() {
        return userEntity;
    }

    /**
     * @param userEntity
     *            the userEntity to set
     */
    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    /**
     * @return the loginMsgRs
     */
    public EB5556981SvcRsType getLoginMsgRs() {
        return loginMsgRs;
    }

    /**
     * @param loginMsgRs
     *            the loginMsgRs to set
     */
    public void setLoginMsgRs(EB5556981SvcRsType loginMsgRs) {
        this.loginMsgRs = loginMsgRs;
    }

    /**
     * @return the tempNameCode
     */
    public String getTempNameCode() {
        return tempNameCode;
    }

    /**
     * @param tempNameCode
     *            the tempNameCode to set
     */
    public void setTempNameCode(String tempNameCode) {
        this.tempNameCode = tempNameCode;
    }

    /**
     * @return the eUid
     */
    public String geteUid() {
        return eUid;
    }

    /**
     * @param eUid
     *            the eUid to set
     */
    public void seteUid(String eUid) {
        this.eUid = eUid;
    }

    /**
     * @return the nextTaskId
     */
    public String getNextTaskId() {
        return nextTaskId;
    }

    /**
     * @param nextTaskId
     *            the nextTaskId to set
     */
    public void setNextTaskId(String nextTaskId) {
        this.nextTaskId = nextTaskId;
    }

    /**
     * @return the nextParam
     */
    public String getNextParam() {
        return nextParam;
    }

    /**
     * @param nextParam
     *            the nextParam to set
     */
    public void setNextParam(String nextParam) {
        this.nextParam = nextParam;
    }

    /**
     * @return the needDisplayMergeContent
     */
    public boolean isNeedDisplayMergeContent() {
        return needDisplayMergeContent;
    }

    /**
     * @param needDisplayMergeContent
     *            the needDisplayMergeContent to set
     */
    public void setNeedDisplayMergeContent(boolean needDisplayMergeContent) {
        this.needDisplayMergeContent = needDisplayMergeContent;
    }

    /**
     * @return the mailType
     */
    public int getMailType() {
        return mailType;
    }

    /**
     * @param mailType
     *            the mailType to set
     */
    public void setMailType(int mailType) {
        this.mailType = mailType;
    }

    /**
     * @return the smsType
     */
    public int getSmsType() {
        return smsType;
    }

    /**
     * @param smsType
     *            the smsType to set
     */
    public void setSmsType(int smsType) {
        this.smsType = smsType;
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
     * @return the companyEntity
     */
    public CompanyEntity getCompanyEntity() {
        return companyEntity;
    }

    /**
     * @param companyEntity
     *            the companyEntity to set
     */
    public void setCompanyEntity(CompanyEntity companyEntity) {
        this.companyEntity = companyEntity;
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
     * @return the cardUserProfileEntity
     */
    public CardUserProfileEntity getCardUserProfileEntity() {
        return cardUserProfileEntity;
    }

    /**
     * @param cardUserProfileEntity
     *            the cardUserProfileEntity to set
     */
    public void setCardUserProfileEntity(CardUserProfileEntity cardUserProfileEntity) {
        this.cardUserProfileEntity = cardUserProfileEntity;
    }

    /**
     * @return the mbDeviceInfoEntity
     */
    public MbDeviceInfoEntity getMbDeviceInfoEntity() {
        return mbDeviceInfoEntity;
    }

    /**
     * @param mbDeviceInfoEntity
     *            the mbDeviceInfoEntity to set
     */
    public void setMbDeviceInfoEntity(MbDeviceInfoEntity mbDeviceInfoEntity) {
        this.mbDeviceInfoEntity = mbDeviceInfoEntity;
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
     * @return the userLoginEntity
     */
    public UserLoginEntity getUserLoginEntity() {
        return userLoginEntity;
    }

    /**
     * @param userLoginEntity
     *            the userLoginEntity to set
     */
    public void setUserLoginEntity(UserLoginEntity userLoginEntity) {
        this.userLoginEntity = userLoginEntity;
    }

    public Integer getCompanyKey() {
        if (this.userEntity == null)
            return 0;
        return this.userEntity.getCompanyKey();
    }

    public Integer getUserKey() {
        if (this.userEntity == null)
            return 0;
        return this.userEntity.getUserKey();
    }

    /**
     * @return the isSuccess
     */
    public boolean isSuccess() {
        return (null == error);
    }

    /**
     * @return the isTheSame
     */
    public boolean isTheSame() {
        return isTheSame;
    }

    /**
     * @param isTheSame
     *            the isTheSame to set
     */
    public void setTheSame(boolean isTheSame) {
        this.isTheSame = isTheSame;
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
     * @return the countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @param countryCode
     *            the countryCode to set
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @return the countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName
     *            the countryName to set
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * @return the error
     */
    public ActionException getError() {
        return error;
    }

    /**
     * @param error
     *            the error to set
     */
    public void setError(ActionException error) {
        this.error = error;
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
     * @return the isEnableStatus
     */
    public boolean isEnableStatus() {
        return isEnableStatus;
    }

    /**
     * @param isEnableStatus
     *            the isEnableStatus to set
     */
    public void setEnableStatus(boolean isEnableStatus) {
        this.isEnableStatus = isEnableStatus;
    }

    /**
     * @return the pushType
     */
    public int getPushType() {
        return pushType;
    }

    /**
     * @param pushType
     *            the pushType to set
     */
    public void setPushType(int pushType) {
        this.pushType = pushType;
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

    public MbDevicePushInfoEntity getMbDevicePushInfoEntity() {
        return mbDevicePushInfoEntity;
    }

    public void setMbDevicePushInfoEntity(MbDevicePushInfoEntity mbDevicePushInfoEntity) {
        this.mbDevicePushInfoEntity = mbDevicePushInfoEntity;
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
     * 雙重驗證
     * @return
     */
    public boolean isTwoFactorAuth() {
        return isTwoFactorAuth;
    }
    /**
     * 雙重驗證
     * @param twoFactorAuth
     */
    public void setIsTwoFactorAuth(boolean twoFactorAuth) {
        this.isTwoFactorAuth = twoFactorAuth;
    }
 
    public MbDevicePushInfoEntity getMbDevicePushInfoEntityBindedByOtherDevice() {
        return mbDevicePushInfoEntityBindedByOtherDevice;
    }

    public void setMbDevicePushInfoEntityBindedByOtherDevice(MbDevicePushInfoEntity mbDevicePushInfoEntityBindedByOtherDevice) {
        this.mbDevicePushInfoEntityBindedByOtherDevice = mbDevicePushInfoEntityBindedByOtherDevice;
    }

    public void setTwoFactorAuth(boolean isTwoFactorAuth) {
        this.isTwoFactorAuth = isTwoFactorAuth;
    }

    public MbDeviceInfoEntity getMbDeviceInfoEntityBindedByOtherDevice() {
        return mbDeviceInfoEntityBindedByOtherDevice;
    }

    public void setMbDeviceInfoEntityBindedByOtherDevice(MbDeviceInfoEntity mbDeviceInfoEntityBindedByOtherDevice) {
        this.mbDeviceInfoEntityBindedByOtherDevice = mbDeviceInfoEntityBindedByOtherDevice;
    }
 

    

    public Long getIpFrom() {
        return ipFrom;
    }

    public void setIpFrom(Long ipFrom) {
        this.ipFrom = ipFrom;
    }

    public Long getIpEnd() {
        return ipEnd;
    }

    public void setIpEnd(Long ipEnd) {
        this.ipEnd = ipEnd;
    }

  
}
