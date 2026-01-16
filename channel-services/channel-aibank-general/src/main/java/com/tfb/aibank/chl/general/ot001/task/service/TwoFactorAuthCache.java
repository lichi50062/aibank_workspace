package com.tfb.aibank.chl.general.ot001.task.service;

import java.util.Date;
import java.util.List;

import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthKeepData;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001011Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001011Rs;
import com.tfb.aibank.chl.general.resource.dto.ExecuteUserLoginRequest;
import com.tfb.aibank.chl.general.resource.dto.ExecuteUserLoginResponse;
import com.tfb.aibank.chl.session.vo.CardUserProfileVo;
import com.tfb.aibank.chl.session.vo.CompanyVo;
import com.tfb.aibank.chl.session.vo.EB5556981Response;
import com.tfb.aibank.chl.session.vo.MbDeviceInfoVo;
import com.tfb.aibank.chl.session.vo.UserLoginVo;
import com.tfb.aibank.chl.session.vo.UserVo;
import com.tfb.aibank.chl.type.TxSecurityStepType;

/**
 * // @formatter:off
/**
 * @(#)TwoFactorAuthCache.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/24, bensonlin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class TwoFactorAuthCache {
    // 0 -> 未知, 1 -> 初始, 2 -> 成功, 3 -> 失敗
    private Integer authStep;

    private Long seq;
    /** 訊息Key值 */
    private Integer personNotificationRecordKey;

    private boolean isTwoFactorAuth;

    /** loginRequest */
    private String sessionId;

    private boolean isDupLogin;

    private boolean isPwdWithTime;

    private String pushToken;
    /** 身份證字號 */
    private String uid;

    /** 使用者代號 */
    private String uuid;

    /** 登入身份 */
    private String loginType;

    /** 裝置代號(此次操作的) */
    private String deviceId;

    /** //0:密碼輸入, 1:手勢 */
    private String pwType;
    /** loginRequest */

    /** loginResponse */
    /** 登入異常代碼 */
    private String errorCode;

    /** 錯誤描述 */
    private String systemId;

    /** 錯誤系統別 */
    private String errorDesc;

    /** User_Profile_Entity */
    private UserVo userVo;

    /** Company_Entity */
    private CompanyVo companyVo;

    /** EB5556981 登入下行電文 */
    private EB5556981Response loginMsgRs;

    /** Card_User_Profile_Entity */
    private CardUserProfileVo cardUserProfileVo;

    /** MB_Device_Info_Entity */
    private MbDeviceInfoVo mbDeviceInfoVo;

    /** MB_Device_Info_Entity 雙重驗證會是其它裝置 */
    private MbDeviceInfoVo mbDeviceInfoVoBindedByOtherDevice;

    /** User_Log_Entity */
    private UserLoginVo userLoginVo;

    /** Name Code */
    private String nameCode;

    private String eUid;

    /** 下一個進入的交易代號 */
    private String nextTaskId;

    /** 下一個進入的交易代參數 */
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

    /** 卡友生日 */
    private Date birthDay;

    /** E-mail */
    private List<String> email;

    /** 首次啟用狀態狀態 */
    private boolean isEnableStatus;

    /** 登入狀態 */
    private int loginStatus;

    /** 登入的IP判斷國別資料 */
    private Integer locationId;

    /** 重複登入 平台 */
    private String channel;

    /** 前次登入時間 */
    private Date preLoginDate;

    /** 登入成功狀態 */
    private boolean isSuccess;

    /** 同一綁定裝置登入 */
    private boolean isTheSame;

    /** 存戶 / 卡戶 */
    private boolean isBankUser;

    /** 卡友 email; */
    private String cardEmail = "";

    /** 多使用者代碼客戶判斷 */
    private boolean isMultiUser;

    /** 登入國家 */
    private String countryCode;

    /** 登入國名 */
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
     * OTP 驗證
     */
    private OtpAuthKeepData otpAuthKeepData;

    /** 交易安控機制 - 執行步驟類別 (OTP, EMAIL 驗證使用) */
    private TxSecurityStepType txSecurityStepType = TxSecurityStepType.UNKNOWN;

    public TwoFactorAuthCache() {
        super();
    }

    public TwoFactorAuthCache(ExecuteUserLoginRequest request, ExecuteUserLoginResponse response, NGNOT001011Rq rqData, NGNOT001011Rs rsData) {
        super();
        this.authStep = 1;

        this.pushToken = rqData.getPushToken();
        this.deviceId = request.getDeviceId();

        this.uid = request.getUid();
        this.uuid = request.getUuid();
        this.loginType = request.getLoginType();
        this.pwType = request.getPwType();
        this.sessionId = request.getCurrentSessionId();
        this.isPwdWithTime = request.getIsPwdWithTime();

        this.errorCode = response.getErrorCode();
        this.systemId = response.getSystemId();
        this.errorDesc = response.getErrorDesc();
        this.userVo = response.getUserVo();
        this.companyVo = response.getCompanyVo();
        this.loginMsgRs = response.getLoginMsgRs();
        this.cardUserProfileVo = response.getCardUserProfileVo();
        this.mbDeviceInfoVo = response.getMbDeviceInfoVo();
        this.mbDeviceInfoVoBindedByOtherDevice = response.getMbDeviceInfoVoBindedByOtherDevice();
        this.userLoginVo = response.getUserLoginVo();
        this.nameCode = response.getNameCode();
        this.eUid = response.geteUid();
        this.nextTaskId = response.getNextTaskId();
        this.nextParam = response.getNextParam();
        this.needDisplayMergeContent = response.isNeedDisplayMergeContent();
        this.mailType = response.getMailType();
        this.smsType = response.getSmsType();
        this.pushType = response.getPushType();
        this.mobileNo = response.getMobileNo();
        this.birthDay = response.getBirthDay();
        this.email = response.getEmail();
        this.isEnableStatus = response.isEnableStatus();
        this.loginStatus = response.getLoginStatus();
        this.locationId = response.getLocationId();
        this.channel = response.getChannel();
        this.preLoginDate = response.getPreLoginDate();
        this.isSuccess = response.isSuccess();
        this.isTheSame = response.isTheSame();
        this.isBankUser = response.isBankUser();
        this.cardEmail = response.getCardEmail();
        this.isMultiUser = response.isMultiUser();
        this.countryCode = response.getCountryCode();
        this.countryName = response.getCountryName();
        this.userKey = response.getUserKey();
        this.companyKey = response.getCompanyKey();
        this.isShowChangeTip = response.isShowChangeTip();
        this.isInAccountCreditcardCheck = response.isInAccountCreditcardCheck();
        this.isSameBirthday = response.isSameBirthday();
        this.loginLogPk = response.getLoginLogPk();
        this.isTwoFactorAuth = response.isTwoFactorAuth();
        this.signonToken = response.getUserLoginVo().getSignonToken();

    }

    public Integer getAuthStep() {
        return authStep;
    }

    public void setAuthStep(Integer authStep) {
        this.authStep = authStep;
    }

    // public ExecuteUserLoginRequest getUserLoginRequest() {
    // return userLoginRequest;
    // }
    //
    // public void setUserLoginRequest(ExecuteUserLoginRequest userLoginRequest) {
    // this.userLoginRequest = userLoginRequest;
    // }
    //
    // public ExecuteUserLoginResponse getUserLoginResponse() {
    // return userLoginResponse;
    // }
    //
    // public void setUserLoginResponse(ExecuteUserLoginResponse userLoginResponse) {
    // this.userLoginResponse = userLoginResponse;
    // }
    //
    // public NGNOT001011Rq getRqData() {
    // return rqData;
    // }
    //
    // public void setRqData(NGNOT001011Rq rqData) {
    // this.rqData = rqData;
    // }
    //
    // public NGNOT001011Rs getRsData() {
    // return rsData;
    // }
    //
    // public void setRsData(NGNOT001011Rs rsData) {
    // this.rsData = rsData;
    // }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long verifySeq) {
        this.seq = verifySeq;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
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

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public boolean isDupLogin() {
        return isDupLogin;
    }

    public void setDupLogin(boolean isDupLogin) {
        this.isDupLogin = isDupLogin;
    }

    public boolean isPwdWithTime() {
        return isPwdWithTime;
    }

    public void setPwdWithTime(boolean isPwdWithTime) {
        this.isPwdWithTime = isPwdWithTime;
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

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPwType() {
        return pwType;
    }

    public void setPwType(String pwType) {
        this.pwType = pwType;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public String getSignonToken() {
        return signonToken;
    }

    public void setSignonToken(String signonToken) {
        this.signonToken = signonToken;
    }

    public MbDeviceInfoVo getMbDeviceInfoVoBindedByOtherDevice() {
        return mbDeviceInfoVoBindedByOtherDevice;
    }

    public void setMbDeviceInfoVoBindedByOtherDevice(MbDeviceInfoVo mbDeviceInfoVoBindedByOtherDevice) {
        this.mbDeviceInfoVoBindedByOtherDevice = mbDeviceInfoVoBindedByOtherDevice;
    }

    public TxSecurityStepType getTxSecurityStepType() {
        return txSecurityStepType;
    }

    public void setTxSecurityStepType(TxSecurityStepType txSecurityStepType) {
        this.txSecurityStepType = txSecurityStepType;
    }

    public OtpAuthKeepData getOtpAuthKeepData() {
        return otpAuthKeepData;
    }

    public void setOtpAuthKeepData(OtpAuthKeepData otpAuthKeepData) {
        this.otpAuthKeepData = otpAuthKeepData;
    }

    public Integer getPersonNotificationRecordKey() {
        return personNotificationRecordKey;
    }

    public void setPersonNotificationRecordKey(Integer personNotificationRecordKey) {
        this.personNotificationRecordKey = personNotificationRecordKey;
    }

}
