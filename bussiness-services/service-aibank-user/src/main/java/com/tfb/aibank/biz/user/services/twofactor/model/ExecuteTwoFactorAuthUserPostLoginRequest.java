package com.tfb.aibank.biz.user.services.twofactor.model;

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
 * @(#)ExecuteTwoFactorAuthUserPostLoginRequest.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/13, Benson	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "ExecuteTwoFactorAuthUserPostLoginRequest")
public class ExecuteTwoFactorAuthUserPostLoginRequest {

    @Schema(description = "Client_IP")
    private String clientIP;
    
    @Schema(description = "Session ID")
    private String currentSessionId;
    
    @Schema(description = "UID")
    private String uid;
    
    @Schema(description = "UUID")
    private String uuid;
;
    @Schema(description = "User Agent")
    private String userAgent;
    @Schema(description = "ScreenHeight")
    private Integer screenHeight;
    
    @Schema(description = "ScreenWidth")
    private Integer screenWidth;

    // 以下為Login Response　寫入 
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
    
    @Schema(description = "Signon Token")
    private String signonToken;

    public ExecuteTwoFactorAuthUserPostLoginRequest() {
        super();
    }

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }

    public CompanyVo getCompanyVo() {
        return companyVo;
    }

    public void setCompanyVo(CompanyVo companyVo) {
        this.companyVo = companyVo;
    }

    public EB5556981Response getLoginMsgRs() {
        return loginMsgRs;
    }

    public void setLoginMsgRs(EB5556981Response loginMsgRs) {
        this.loginMsgRs = loginMsgRs;
    }

    public CardUserProfileVo getCardUserProfileVo() {
        return cardUserProfileVo;
    }

    public void setCardUserProfileVo(CardUserProfileVo cardUserProfileVo) {
        this.cardUserProfileVo = cardUserProfileVo;
    }

    public MbDeviceInfoVo getMbDeviceInfoVo() {
        return mbDeviceInfoVo;
    }

    public void setMbDeviceInfoVo(MbDeviceInfoVo mbDeviceInfoVo) {
        this.mbDeviceInfoVo = mbDeviceInfoVo;
    }

    public UserLoginVo getUserLoginVo() {
        return userLoginVo;
    }

    public void setUserLoginVo(UserLoginVo userLoginVo) {
        this.userLoginVo = userLoginVo;
    }

    public String getNameCode() {
        return nameCode;
    }

    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    public String geteUid() {
        return eUid;
    }

    public void seteUid(String eUid) {
        this.eUid = eUid;
    }

    public String getNextTaskId() {
        return nextTaskId;
    }

    public void setNextTaskId(String nextTaskId) {
        this.nextTaskId = nextTaskId;
    }

    public String getNextParam() {
        return nextParam;
    }

    public void setNextParam(String nextParam) {
        this.nextParam = nextParam;
    }

    public boolean isNeedDisplayMergeContent() {
        return needDisplayMergeContent;
    }

    public void setNeedDisplayMergeContent(boolean needDisplayMergeContent) {
        this.needDisplayMergeContent = needDisplayMergeContent;
    }

    public int getMailType() {
        return mailType;
    }

    public void setMailType(int mailType) {
        this.mailType = mailType;
    }

    public int getSmsType() {
        return smsType;
    }

    public void setSmsType(int smsType) {
        this.smsType = smsType;
    }

    public int getPushType() {
        return pushType;
    }

    public void setPushType(int pushType) {
        this.pushType = pushType;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public boolean isEnableStatus() {
        return isEnableStatus;
    }

    public void setEnableStatus(boolean isEnableStatus) {
        this.isEnableStatus = isEnableStatus;
    }

    public int getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Date getPreLoginDate() {
        return preLoginDate;
    }

    public void setPreLoginDate(Date preLoginDate) {
        this.preLoginDate = preLoginDate;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isTheSame() {
        return isTheSame;
    }

    public void setTheSame(boolean isTheSame) {
        this.isTheSame = isTheSame;
    }

    public boolean isBankUser() {
        return isBankUser;
    }

    public void setBankUser(boolean isBankUser) {
        this.isBankUser = isBankUser;
    }

    public String getCardEmail() {
        return cardEmail;
    }

    public void setCardEmail(String cardEmail) {
        this.cardEmail = cardEmail;
    }

    public boolean isMultiUser() {
        return isMultiUser;
    }

    public void setMultiUser(boolean isMultiUser) {
        this.isMultiUser = isMultiUser;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getUserKey() {
        return userKey;
    }

    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    public Integer getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    public boolean isShowChangeTip() {
        return isShowChangeTip;
    }

    public void setShowChangeTip(boolean isShowChangeTip) {
        this.isShowChangeTip = isShowChangeTip;
    }

    public boolean isInAccountCreditcardCheck() {
        return isInAccountCreditcardCheck;
    }

    public void setInAccountCreditcardCheck(boolean isInAccountCreditcardCheck) {
        this.isInAccountCreditcardCheck = isInAccountCreditcardCheck;
    }

    public boolean isSameBirthday() {
        return isSameBirthday;
    }

    public void setSameBirthday(boolean isSameBirthday) {
        this.isSameBirthday = isSameBirthday;
    }

    public Integer getLoginLogPk() {
        return loginLogPk;
    }

    public void setLoginLogPk(Integer loginLogPk) {
        this.loginLogPk = loginLogPk;
    }

    public boolean isTwoFactorAuth() {
        return isTwoFactorAuth;
    }

    public void setTwoFactorAuth(boolean isTwoFactorAuth) {
        this.isTwoFactorAuth = isTwoFactorAuth;
    }

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public String getCurrentSessionId() {
        return currentSessionId;
    }

    public void setCurrentSessionId(String currentSessionId) {
        this.currentSessionId = currentSessionId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Integer getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(Integer screenHeight) {
        this.screenHeight = screenHeight;
    }

    public Integer getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(Integer screenWidth) {
        this.screenWidth = screenWidth;
    }

    public String getSignonToken() {
        return signonToken;
    }

    public void setSignonToken(String signonToken) {
        this.signonToken = signonToken;
    }

 

 
    
}

