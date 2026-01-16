package com.tfb.aibank.chl.general.ot005.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.chl.general.resource.vo.MenuForHandShakeVo;

//@formatter:off
/**
* @(#)NGNOT005010Rs.java 
* 
* <p>Description:登入頁</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20250305, Benson
*  <li>初版</li6
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on
@Component
public class NGNOT005010Rs implements RsData {
    
    /**無障礙回到登入首頁執行的動作, 可以為空*/
    private Integer action; 

    // Public Key for UID
    private String publicKeyforUid;

    // Public Key for Secrxt
    private String publicKeyforSecret;

    /** 是否支援快速登入 */
    private boolean isFastLogin;

    /**
     * 是否啟用無痛移轉
     */
    private boolean isNoPainTransferEnable;

    /** 綁定ID */
    private String custId;

    /**
     * 有設定快登時，開啟APP是否直接登入，0:不直接登入；1:直接登入
     */
    private Integer directEzLoginFlag;

    /**
     * 快速登入密碼類型，0:文字密碼；1:手勢密碼；2:指紋；3:臉部；4:Android生物辨識
     */
    private int loginMethod;


    /**
     * FIDO Portal SDK URL
     */
    private String fidoPortalUrl;

    /** 參加單位 */
    private String businessNo;

    /** 忘記代碼密碼 URL */
    private String loginForgetpwUrl;

    /** 隱私權保護政策Url */
    private String privacyLink;

    /** 個資保護聲明Url */
    private String protectLink;

    /** 智能客服 */
    private String chatBotUrl;

    /** 登入身份 */
    private Integer loginType;

    /** 黑名單裝置 = 1 */
    private Integer isInBlackList;

    /**
     * 狀態碼 - 暫時進用快登原因 1 - 您曾變更過使用者代碼或密碼，為維護帳號安全，本次請以帳密登入，之後將可繼續使用快速登入
     */
    private Integer status;

    /**
     * E2EE給前端uid/uuid加密時，是否帶入之時間因子
     */
    private boolean isUidUuidNeedWithTime;
    
    /**
     * E2EE給前端uid/uuid 時間因子
     */
    private String uidUuidWithTime;
    /**
     * 時間因子 有效時間(秒)
     */
    private int timeFactorValidSeconds;

    /**
     * E2EE給前端PWD加密時，是否帶入之時間因子
     */
    private boolean isPwdNeedWithTime;
    /**
     * 
     */
    private String pwdWithTime;

    //////////////////////////////////////////////
    // 以下為無痛移轉資料
    //////////////////////////////////////////////

    /**
     * 登入狀態
     */
    private String loginStatus;

    /** 信用卡會員 */
    private String isCCUser;

    /** Token */
    private String token;

    /**
     * 使用者代號 / 密碼 變更
     */
    private int nextJob;

    /** 進入快速登入，0-不能設定 1-可以設定 2-已綁其他，可以設定 */
    private int fastLoginStatus;

    /** 顯示變更密碼提示 */
    private boolean isShowChangeTip;

    /** Celebrus Encrypted ID */
    private String celebrusId;

    /** 所有選單資訊 */
    private Map<String, List<MenuForHandShakeVo>> menuInfo;

    /** 預設字體大小 */
    private int fontSize;

    /**
     * 用戶旅程註記
     */
    private int onboardingType;

    /** 「是否需提供滿意度問卷」註記 */
    private boolean isShowSatisfactionFlag;

    /** 啟用「滿意度調查」功能清單 ex: key:NPYTX001040 value:繳信用卡款 */
    private Map<String, String> satisfactionTasksMap = new HashMap<>();

    /** 沒有手機，無法繼續登入 */
    private boolean isNoMobileNo;

    /**
     * 是否 '不' 可以執行 無痛移轉
     */
    private boolean noPainTransferNotQualified;

    /**
     * @return the publicKeyforUid
     */
    public String getPublicKeyforUid() {
        return publicKeyforUid;
    }

    /**
     * @param publicKeyforUid
     *            the publicKeyforUid to set
     */
    public void setPublicKeyforUid(String publicKeyforUid) {
        this.publicKeyforUid = publicKeyforUid;
    }

    /**
     * @return the publicKeyforSecret
     */
    public String getPublicKeyforSecret() {
        return publicKeyforSecret;
    }

    /**
     * @param publicKeyforSecret
     *            the publicKeyforSecret to set
     */
    public void setPublicKeyforSecret(String publicKeyforSecret) {
        this.publicKeyforSecret = publicKeyforSecret;
    }

    /**
     * @return the isFastLogin
     */
    public boolean isFastLogin() {
        return isFastLogin;
    }

    /**
     * @param isFastLogin
     *            the isFastLogin to set
     */
    public void setFastLogin(boolean isFastLogin) {
        this.isFastLogin = isFastLogin;
    }

    /**
     * @return the loginMethod
     */
    public int getLoginMethod() {
        return loginMethod;
    }

    /**
     * @param loginMethod
     *            the loginMethod to set
     */
    public void setLoginMethod(int loginMethod) {
        this.loginMethod = loginMethod;
    }


    /**
     * @return the fidoPortalUtl
     */
    public String getFidoPortalUrl() {
        return fidoPortalUrl;
    }

    /**
     * @param fidoPortalUtl
     *            the fidoPortalUtl to set
     */
    public void setFidoPortalUrl(String fidoPortalUrl) {
        this.fidoPortalUrl = fidoPortalUrl;
    }

    /**
     * @return the businessNo
     */
    public String getBusinessNo() {
        return businessNo;
    }

    /**
     * @param businessNo
     *            the businessNo to set
     */
    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    /**
     * @return the loginForgetpwUrl
     */
    public String getLoginForgetpwUrl() {
        return loginForgetpwUrl;
    }

    /**
     * @param loginForgetpwUrl
     *            the loginForgetpwUrl to set
     */
    public void setLoginForgetpwUrl(String loginForgetpwUrl) {
        this.loginForgetpwUrl = loginForgetpwUrl;
    }

    /**
     * @return the privacyLink
     */
    public String getPrivacyLink() {
        return privacyLink;
    }

    /**
     * @param privacyLink
     *            the privacyLink to set
     */
    public void setPrivacyLink(String privacyLink) {
        this.privacyLink = privacyLink;
    }

    /**
     * @return the chatBotUrl
     */
    public String getChatBotUrl() {
        return chatBotUrl;
    }

    /**
     * @param chatBotUrl
     *            the chatBotUrl to set
     */
    public void setChatBotUrl(String chatBotUrl) {
        this.chatBotUrl = chatBotUrl;
    }

    /**
     * @return the loginType
     */
    public Integer getLoginType() {
        return loginType;
    }

    /**
     * @param loginType
     *            the loginType to set
     */
    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
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
     * @return the directEzLoginFlag
     */
    public Integer getDirectEzLoginFlag() {
        return directEzLoginFlag;
    }

    /**
     * @param directEzLoginFlag
     *            the directEzLoginFlag to set
     */
    public void setDirectEzLoginFlag(Integer directEzLoginFlag) {
        this.directEzLoginFlag = directEzLoginFlag;
    }

    /**
     * @return the isInBlackList
     */
    public Integer getIsInBlackList() {
        return isInBlackList;
    }

    /**
     * @param isInBlackList
     *            the isInBlackList to set
     */
    public void setIsInBlackList(Integer isInBlackList) {
        this.isInBlackList = isInBlackList;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    /**
     * @return the isCCUser
     */
    public String getIsCCUser() {
        return isCCUser;
    }

    /**
     * @param isCCUser
     *            the isCCUser to set
     */
    public void setIsCCUser(String isCCUser) {
        this.isCCUser = isCCUser;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     *            the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the nextJob
     */
    public int getNextJob() {
        return nextJob;
    }

    /**
     * @param nextJob
     *            the nextJob to set
     */
    public void setNextJob(int nextJob) {
        this.nextJob = nextJob;
    }

    /**
     * @return the fastLoginStatus
     */
    public int getFastLoginStatus() {
        return fastLoginStatus;
    }

    /**
     * @param fastLoginStatus
     *            the fastLoginStatus to set
     */
    public void setFastLoginStatus(int fastLoginStatus) {
        this.fastLoginStatus = fastLoginStatus;
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
     * @return the celebrusId
     */
    public String getCelebrusId() {
        return celebrusId;
    }

    /**
     * @param celebrusId
     *            the celebrusId to set
     */
    public void setCelebrusId(String celebrusId) {
        this.celebrusId = celebrusId;
    }

    /**
     * @return the menuInfo
     */
    public Map<String, List<MenuForHandShakeVo>> getMenuInfo() {
        return menuInfo;
    }

    /**
     * @param menuInfo
     *            the menuInfo to set
     */
    public void setMenuInfo(Map<String, List<MenuForHandShakeVo>> menuInfo) {
        this.menuInfo = menuInfo;
    }

    /**
     * @return the fontSize
     */
    public int getFontSize() {
        return fontSize;
    }

    /**
     * @param fontSize
     *            the fontSize to set
     */
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    /**
     * @return the isShowSatisfactionFlag
     */
    public boolean isShowSatisfactionFlag() {
        return isShowSatisfactionFlag;
    }

    /**
     * @param isShowSatisfactionFlag
     *            the isShowSatisfactionFlag to set
     */
    public void setShowSatisfactionFlag(boolean isShowSatisfactionFlag) {
        this.isShowSatisfactionFlag = isShowSatisfactionFlag;
    }

    /**
     * @return the satisfactionTasksMap
     */
    public Map<String, String> getSatisfactionTasksMap() {
        return satisfactionTasksMap;
    }

    /**
     * @param satisfactionTasksMap
     *            the satisfactionTasksMap to set
     */
    public void setSatisfactionTasksMap(Map<String, String> satisfactionTasksMap) {
        this.satisfactionTasksMap = satisfactionTasksMap;
    }

    /**
     * @return the loginStatus
     */
    public String getLoginStatus() {
        return loginStatus;
    }

    /**
     * @param loginStatus
     *            the loginStatus to set
     */
    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
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

    /**
     * @return the isNoPainTransferEnable
     */
    public boolean isNoPainTransferEnable() {
        return isNoPainTransferEnable;
    }

    /**
     * @param isNoPainTransferEnable
     *            the isNoPainTransferEnable to set
     */
    public void setNoPainTransferEnable(boolean isNoPainTransferEnable) {
        this.isNoPainTransferEnable = isNoPainTransferEnable;
    }

    /**
     * @return the isNoMobileNo
     */
    public boolean isNoMobileNo() {
        return isNoMobileNo;
    }

    /**
     * @param isNoMobileNo
     *            the isNoMobileNo to set
     */
    public void setNoMobileNo(boolean isNoMobileNo) {
        this.isNoMobileNo = isNoMobileNo;
    }

    /**
     * @return the noPainTransferNotQualified
     */
    public boolean isNoPainTransferNotQualified() {
        return noPainTransferNotQualified;
    }

    /**
     * @param noPainTransferNotQualified
     *            the noPainTransferNotQualified to set
     */
    public void setNoPainTransferNotQualified(boolean noPainTransferNotQualified) {
        this.noPainTransferNotQualified = noPainTransferNotQualified;
    }

    /**
     * @return the protectLink
     */
    public String getProtectLink() {
        return protectLink;
    }

    /**
     * @param protectLink
     *            the protectLink to set
     */
    public void setProtectLink(String protectLink) {
        this.protectLink = protectLink;
    }

    /**
     * @return the isUidUuidNeedWithTime
     */
    public boolean isUidUuidNeedWithTime() {
        return isUidUuidNeedWithTime;
    }

    /**
     * @param isUidUuidNeedWithTime
     *            the isUidUuidNeedWithTime to set
     */
    public void setUidUuidNeedWithTime(boolean isUidUuidNeedWithTime) {
        this.isUidUuidNeedWithTime = isUidUuidNeedWithTime;
    }

    /**
     * @return the isPwdNeedWithTime
     */
    public boolean isPwdNeedWithTime() {
        return isPwdNeedWithTime;
    }

    /**
     * @param isPwdNeedWithTime
     *            the isPwdNeedWithTime to set
     */
    public void setPwdNeedWithTime(boolean isPwdNeedWithTime) {
        this.isPwdNeedWithTime = isPwdNeedWithTime;
    }

    /**
     * @return the uidUuidWithTime
     */
    public String getUidUuidWithTime() {
        return uidUuidWithTime;
    }

    /**
     * @param uidUuidWithTime
     *            the uidUuidWithTime to set
     */
    public void setUidUuidWithTime(String uidUuidWithTime) {
        this.uidUuidWithTime = uidUuidWithTime;
    }

    /**
     * @return the pwdWithTime
     */
    public String getPwdWithTime() {
        return pwdWithTime;
    }

    /**
     * @param pwdWithTime
     *            the pwdWithTime to set
     */
    public void setPwdWithTime(String pwdWithTime) {
        this.pwdWithTime = pwdWithTime;
    }

    /**
     * @return the timeFactorValidSeconds
     */
    public int getTimeFactorValidSeconds() {
        return timeFactorValidSeconds;
    }

    /**
     * @param timeFactorValidSeconds
     *            the timeFactorValidSeconds to set
     */
    public void setTimeFactorValidSeconds(int timeFactorValidSeconds) {
        this.timeFactorValidSeconds = timeFactorValidSeconds;
    }

    /**
     * 
     * @return
     */
    public Integer getAction() {
        return action;
    }
    /**
     * 
     * @param action
     */
    public void setAction(Integer action) {
        this.action = action;
    }
 

}
