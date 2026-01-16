package com.tfb.aibank.biz.user.services.login.model;

import java.util.Date;
import java.util.List;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.session.vo.CardUserProfileVo;
import com.tfb.aibank.chl.session.vo.CompanyVo;
import com.tfb.aibank.chl.session.vo.EB5556981Response;
import com.tfb.aibank.chl.session.vo.MbDeviceInfoVo;
import com.tfb.aibank.chl.session.vo.UserLoginVo;
import com.tfb.aibank.chl.session.vo.UserVo;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @(#)AbstractExecuteUserLoginResponse.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/18, bensonlin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
public abstract class AbstractExecuteUserLoginResponse {

    /** 登入異常代碼 */
    @Schema(description = "錯誤碼")
    private String errorCode;

    /** 錯誤描述 */
    @Schema(description = "錯誤描述")
    private String systemId;

    /** 錯誤系統別 */
    @Schema(description = "錯誤系統別")
    private String errorDesc;

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

    private String eUid;

    @Schema(description = "下一個進入的交易代號")
    private String nextTaskId;

    @Schema(description = "下一個進入的交易代參數")
    private String nextParam;

    private boolean needDisplayMergeContent;

    /** 0-不發 1-成功通知 2-失敗通知 */
    @Schema(description = "通知信種類")
    private int mailType;

    /** 0-不發 1-成功通知 2-失敗通知 */
    @Schema(description = "簡訊通知種類")
    private int smsType;

    /** 0-未綁定，未訂閱 1-不推播，2-推播 */
    @Schema(description = "推播通知種類")
    private int pushType;

    /** 電話號碼 */
    @Schema(description = "電話號碼")
    private String mobileNo;

    /** 生日 */
    @Schema(description = "生日")
    private Date birthDay;

    @Schema(description = "E-Mail")
    private List<String> email;

    /** 首次啟用狀態 */
    @Schema(description = "首次啟用狀態")
    private boolean isEnableStatus;

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

    // 同一綁定裝置登入
    @Schema(description = "同一綁定裝置登入")
    private boolean isTheSame;

    // 存戶 / 卡戶
    @Schema(description = "存戶/卡戶")
    private boolean isBankUser;

    // 卡友 email;
    @Schema(description = "卡友 email")
    private String cardEmail = "";

    // 多使用者代碼客戶判斷
    @Schema(description = "多使用者代碼客戶判斷")
    private boolean isMultiUser;

    // 登入國家
    @Schema(description = "登入國家")
    private String countryCode;

    // 登入國名
    @Schema(description = "登入國名")
    private String countryName;

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

    /** 登入異常代碼 */
    private ActionException error = null;

    /**
     * EB_LOGIN_LOG_B PK
     */
    @Schema(description = "EB_LOGIN_LOG_B PK")
    private Integer loginLogPk;
    
    
    /**
     * 雙重驗證
     */
    @Schema(description = "雙重驗證")
    private boolean isTwoFactorAuth;

    /**
     * 
     */
    public AbstractExecuteUserLoginResponse() {
        super();
        this.companyVo = new CompanyVo();
        this.userVo = new UserVo();
        this.loginMsgRs = new EB5556981Response();
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
     * @return the userEntityVo
     */
    public UserVo getUserVo() {
        if (userVo == null) {
            userVo = new UserVo();
        }
        return userVo;
    }

    /**
     * @param userEntityVo
     *            the userEntityVo to set
     */
    public void setUserVo(UserVo userEntityVo) {
        this.userVo = userEntityVo;
    }

    /**
     * @return the loginMsgRs
     */
    public EB5556981Response getLoginMsgRs() {
        if (loginMsgRs == null) {
            loginMsgRs = new EB5556981Response();
        }
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
     * @return the companyEntityVo
     */
    public CompanyVo getCompanyVo() {
        if (companyVo == null) {
            companyVo = new CompanyVo();
        }
        return companyVo;
    }

    /**
     * @param companyEntityVo
     *            the companyEntityVo to set
     */
    public void setCompanyVo(CompanyVo companyVo) {
        this.companyVo = companyVo;
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
    public CardUserProfileVo getCardUserProfileVo() {
        if (cardUserProfileVo == null) {
            cardUserProfileVo = new CardUserProfileVo();
        }
        return cardUserProfileVo;
    }

    /**
     * @param cardUserProfileEntity
     *            the cardUserProfileEntity to set
     */
    public void setCardUserProfileVo(CardUserProfileVo cardUserProfileVo) {
        this.cardUserProfileVo = cardUserProfileVo;
    }

    /**
     * @return the mbDeviceInfoEntity
     */
    public MbDeviceInfoVo getMbDeviceInfoVo() {
        if (mbDeviceInfoVo == null) {
            mbDeviceInfoVo = new MbDeviceInfoVo();
        }
        return mbDeviceInfoVo;
    }

    /**
     * @param mbDeviceInfoEntity
     *            the mbDeviceInfoEntity to set
     */
    public void setMbDeviceInfoVo(MbDeviceInfoVo mbDeviceInfoVo) {
        this.mbDeviceInfoVo = mbDeviceInfoVo;
    }

    /**
     * @return the userLoginEntity
     */
    public UserLoginVo getUserLoginVo() {
        if (userLoginVo == null) {
            userLoginVo = new UserLoginVo();
        }
        return userLoginVo;
    }

    /**
     * @param userLoginEntity
     *            the userLoginEntity to set
     */
    public void setUserLoginVo(UserLoginVo userLoginVo) {
        this.userLoginVo = userLoginVo;
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
     * @return the loginLogPk
     */
    public Integer getLoginLogPk() {
        return loginLogPk;
    }

    /**
     * @param loginLogPk
     *            the loginLogPk to set
     */
    public void setLoginLogPk(Integer loginLogPk) {
        this.loginLogPk = loginLogPk;
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
     * 
     * @return
     */
    public boolean isTwoFactorAuth() {
        return isTwoFactorAuth;
    }
    /**
     * 
     * @param isTwoFactorAuth
     */
    public void setTwoFactorAuth(boolean isTwoFactorAuth) {
        this.isTwoFactorAuth = isTwoFactorAuth;
    }
}
