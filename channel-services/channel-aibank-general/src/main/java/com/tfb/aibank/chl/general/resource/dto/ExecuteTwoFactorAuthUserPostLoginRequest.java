package com.tfb.aibank.chl.general.resource.dto;

import java.util.Date;
import java.util.List;

import com.tfb.aibank.chl.general.ot001.task.service.TwoFactorAuthCache;
import com.tfb.aibank.chl.session.vo.CardUserProfileVo;
import com.tfb.aibank.chl.session.vo.CompanyVo;
import com.tfb.aibank.chl.session.vo.EB5556981Response;
import com.tfb.aibank.chl.session.vo.MbDeviceInfoVo;
import com.tfb.aibank.chl.session.vo.UserLoginVo;
import com.tfb.aibank.chl.session.vo.UserVo;

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
public class ExecuteTwoFactorAuthUserPostLoginRequest {
    // 額外帶入
    private String clientIP;
    // 額外帶入
    private String currentSessionId;
    // 額外帶入
    private String uid;
    // 額外帶入 uuid 
    private String uuid;
    
    private String userAgent;
    
    private Integer screenHeight;
    
    private Integer screenWidth;

    
    private UserVo userVo;

    /** Company_Entity */
    private CompanyVo companyVo;

    private EB5556981Response loginMsgRs;

    private CardUserProfileVo cardUserProfileVo;

    private MbDeviceInfoVo mbDeviceInfoVo;

    private UserLoginVo userLoginVo;

    private String nameCode;

    private String eUid;

    private String nextTaskId;

    private String nextParam;

    private boolean needDisplayMergeContent;

    /** 0-不發 1-成功通知 2-失敗通知 */
    private int mailType;

    /** 0-不發 1-成功通知 2-失敗通知 */
    private int smsType;

    /** 0-未綁定，未訂閱 1-不推播，2-推播 */
    private int pushType;

    /** 電話號碼 */
    private String mobileNo;

    /** 生日 */
    private Date birthDay;

    private List<String> email;

    /** 首次啟用狀態 */
    private boolean isEnableStatus;

    /** 登入狀態 */
    private int loginStatus;

    /** 登入的IP判斷國別資料 */
    private Integer locationId;

    /** 重複登入 平台 */
    private String channel;

    /** 前次登入時間 */
    private Date preLoginDate;

    // 登入成功狀態
    private boolean isSuccess;

    // 同一綁定裝置登入
    private boolean isTheSame;

    // 存戶 / 卡戶
    private boolean isBankUser;

    // 卡友 email;
    private String cardEmail = "";

    // 多使用者代碼客戶判斷
    private boolean isMultiUser;

    // 登入國家
    private String countryCode;

    // 登入國名
    private String countryName;

    private Integer userKey;

    private Integer companyKey;

    /** 顯示變更密碼提示 */
    private boolean isShowChangeTip;

    /** 是否在 AccountCreditcardCheck 黑名單中 */
    private boolean isInAccountCreditcardCheck;

    /** Banc 與 400 的生日是否相同 */
    private boolean isSameBirthday;

    /**
     * EB_LOGIN_LOG_B PK
     */
    private Integer loginLogPk;
    
    /**
     *
     */
    private String signonToken;
    
    /**
     * 雙重驗證
     */
    private boolean isTwoFactorAuth;

    //ExecuteUserLoginResponse twoFactorCache
    public ExecuteTwoFactorAuthUserPostLoginRequest(TwoFactorAuthCache twoFactorCache) {
        super();
        this.birthDay = twoFactorCache.getBirthDay();
        this.cardEmail = twoFactorCache.getCardEmail();
        this.cardUserProfileVo = twoFactorCache.getCardUserProfileVo();
        this.channel = twoFactorCache.getChannel();
        this.companyKey = twoFactorCache.getCompanyKey();
        this.companyVo = twoFactorCache.getCompanyVo();
        this.countryCode = twoFactorCache.getCountryCode();
        this.countryName = twoFactorCache.getCountryName();
        this.email = twoFactorCache.getEmail();
        this.eUid = twoFactorCache.geteUid();
        this.isBankUser = twoFactorCache.isBankUser();
        this.isEnableStatus = twoFactorCache.isEnableStatus();
        this.isInAccountCreditcardCheck = twoFactorCache.isInAccountCreditcardCheck();
        this.isMultiUser = twoFactorCache.isMultiUser();
        this.isSameBirthday = twoFactorCache.isSameBirthday();
        this.isShowChangeTip = twoFactorCache.isShowChangeTip();
        this.isTheSame = twoFactorCache.isTheSame();
        this.isTwoFactorAuth = twoFactorCache.isTwoFactorAuth();
        this.locationId = twoFactorCache.getLocationId();
        this.loginLogPk = twoFactorCache.getLoginLogPk();
        this.loginMsgRs = twoFactorCache.getLoginMsgRs();
        this.loginStatus = twoFactorCache.getLoginStatus();
        this.mailType = twoFactorCache.getMailType();
        this.mbDeviceInfoVo = twoFactorCache.getMbDeviceInfoVo();
        this.mobileNo = twoFactorCache.getMobileNo();
        this.nameCode = twoFactorCache.getNameCode();
        this.needDisplayMergeContent = twoFactorCache.isNeedDisplayMergeContent();
        this.nextParam = twoFactorCache.getNextParam();
        this.nextTaskId = twoFactorCache.getNextTaskId();
        this.preLoginDate = twoFactorCache.getPreLoginDate();
        this.pushType = twoFactorCache.getPushType();
        this.smsType = twoFactorCache.getSmsType();
        this.userKey = twoFactorCache.getUserKey();
        this.userLoginVo = twoFactorCache.getUserLoginVo();
        this.userVo = twoFactorCache.getUserVo();
        this.isSuccess = twoFactorCache.isSuccess();
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
