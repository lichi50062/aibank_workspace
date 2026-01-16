package com.tfb.aibank.chl.session.vo;

import java.util.Date;

import com.tfb.aibank.chl.session.type.LoginStatusType;

import jakarta.persistence.Column;

// @formatter:off
/**
 * @(#)UserLoginVo.java
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
public class UserLoginVo {

    /** 使用者鍵值 */
    private Integer userKey;

    /** 公司鍵值 */
    private Integer companyKey;

    /**
     * 登入狀態
     * <ul>
     * <li>-1: 從未登入</li>
     * <li>-2: 使用鎖定</li>
     * <li>0: 正常登出</li>
     * <li>1: 已經登入</li>
     * </ul>
     * 
     * @see com.ibm.tw.biz.common.type.LoginStatusType
     */
    private Integer status = LoginStatusType.NEVER.getCode();

    /** 密碼錯誤次數 */
    private Integer pwdErrorCount = 0;

    /** 密碼錯誤時間 */
    private Date pwdErrorTime;

    /** 密碼變更的時間 */
    private Date pwdChangeTime;

    /** 是否強制變更密碼 */
    private Integer pwdForceChange = 0;

    /** AP server host name */
    private String serverId;

    /** 最近登入的Http Session Id */
    private String sessionId;

    /** 最近登入來源IP */
    private String clientIp;

    /** 最近登入時間 (登入時寫入，不管登入成功或失敗) */
    private Date loginTime;

    /** 最後存取時間 */
    private Date lastAccessTime;

    /**
     * <p>
     * 上次登入時間
     * </p>
     * 效能考量 於登入成功後將LOGINTIME複製到此欄位
     */
    private Date lastLoginTime;

    /**
     * <p>
     * 登入結果 0: 成功 1: 失敗
     * </p>
     */
    private Integer loginResult;

    /**
     * <p>
     * 上次登入結果 0: 成功 1: 失敗
     * </p>
     * 效能考量 於登入處理結束後將LOGIN_RESULT複製到此欄位
     */
    private Integer lastLoginResult;

    /**
     * 前一次登入來源IP 效能考量 於登入處理結束後將CLIENT_IP複製到此欄位
     **/
    private String lastClientIp;

    /**
     * 國別中文 (from clientIP)
     */
    private String nationName;

    /**
     * 前一次登入國別中文 (from clientIP) 效能考量 於登入處理結束後將nationName複製到此欄位
     */
    private String lastNationName;

    /** 提示訊息 */
    private String warningMessage;

    /** 登入Token, 用於判斷使用者重覆登入(同一session登入第二次) */
    private String signonToken;

    /**
     * 是否已顯示過首次登入功能提示 (0: 否 1:是)
     */

    @Column(name = "MAIN_COACH_MARK")
    private Integer mainCoachMark;

    /**
     * 是否已顯示過個人訊息功能提示 (0: 否 1:是)
     */
    private Integer pmCoachMark;

    /**
     * 是否已顯示過收支管家功能提示 (0: 否 1:是)
     */
    private Integer acctMgrCoachMark;

    /**
     * 通路代碼: 0：網銀 1：網路ATM 2：媒體薪轉 3：e化繳費網 4：行銀 5：信用卡會員 6：員工持股儲蓄信託 7：員工分紅認股信託FINI保管專區
     * 
     * @mobile
     */
    private String channelId;

    /**
     * 前一次登入的 channel id 通路代碼: 0：網銀 1：網路ATM 2：媒體薪轉 3：e化繳費網 4：行銀 5：信用卡會員 6：員工持股儲蓄信託 7：員工分紅認股信託FINI保管專區
     * 
     * @mobile
     */
    private String lastChannelId;

    /**
     * 彈跳顯示時間
     * 
     * @mobile
     */
    private Date popupTime;

    /**
     * 基金總覽顯示方式：0-簡易版;1-詳細版
     * 
     * @mobile
     */
    private String mfdShowtype;

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
     * @return the pwdErrorCount
     */
    public Integer getPwdErrorCount() {
        return pwdErrorCount;
    }

    /**
     * @param pwdErrorCount
     *            the pwdErrorCount to set
     */
    public void setPwdErrorCount(Integer pwdErrorCount) {
        this.pwdErrorCount = pwdErrorCount;
    }

    /**
     * @return the pwdErrorTime
     */
    public Date getPwdErrorTime() {
        return pwdErrorTime;
    }

    /**
     * @param pwdErrorTime
     *            the pwdErrorTime to set
     */
    public void setPwdErrorTime(Date pwdErrorTime) {
        this.pwdErrorTime = pwdErrorTime;
    }

    /**
     * @return the pwdChangeTime
     */
    public Date getPwdChangeTime() {
        return pwdChangeTime;
    }

    /**
     * @param pwdChangeTime
     *            the pwdChangeTime to set
     */
    public void setPwdChangeTime(Date pwdChangeTime) {
        this.pwdChangeTime = pwdChangeTime;
    }

    /**
     * @return the pwdForceChange
     */
    public Integer getPwdForceChange() {
        return pwdForceChange;
    }

    /**
     * @param pwdForceChange
     *            the pwdForceChange to set
     */
    public void setPwdForceChange(Integer pwdForceChange) {
        this.pwdForceChange = pwdForceChange;
    }

    /**
     * @return the serverId
     */
    public String getServerId() {
        return serverId;
    }

    /**
     * @param serverId
     *            the serverId to set
     */
    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    /**
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId
     *            the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * @return the clientIp
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * @param clientIp
     *            the clientIp to set
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * @return the loginTime
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * @param loginTime
     *            the loginTime to set
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * @return the lastAccessTime
     */
    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    /**
     * @param lastAccessTime
     *            the lastAccessTime to set
     */
    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    /**
     * @return the lastLoginTime
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * @param lastLoginTime
     *            the lastLoginTime to set
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * @return the loginResult
     */
    public Integer getLoginResult() {
        return loginResult;
    }

    /**
     * @param loginResult
     *            the loginResult to set
     */
    public void setLoginResult(Integer loginResult) {
        this.loginResult = loginResult;
    }

    /**
     * @return the lastLoginResult
     */
    public Integer getLastLoginResult() {
        return lastLoginResult;
    }

    /**
     * @param lastLoginResult
     *            the lastLoginResult to set
     */
    public void setLastLoginResult(Integer lastLoginResult) {
        this.lastLoginResult = lastLoginResult;
    }

    /**
     * @return the lastClientIp
     */
    public String getLastClientIp() {
        return lastClientIp;
    }

    /**
     * @param lastClientIp
     *            the lastClientIp to set
     */
    public void setLastClientIp(String lastClientIp) {
        this.lastClientIp = lastClientIp;
    }

    /**
     * @return the nationName
     */
    public String getNationName() {
        return nationName;
    }

    /**
     * @param nationName
     *            the nationName to set
     */
    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    /**
     * @return the lastNationName
     */
    public String getLastNationName() {
        return lastNationName;
    }

    /**
     * @param lastNationName
     *            the lastNationName to set
     */
    public void setLastNationName(String lastNationName) {
        this.lastNationName = lastNationName;
    }

    /**
     * @return the warningMessage
     */
    public String getWarningMessage() {
        return warningMessage;
    }

    /**
     * @param warningMessage
     *            the warningMessage to set
     */
    public void setWarningMessage(String warningMessage) {
        this.warningMessage = warningMessage;
    }

    /**
     * @return the signonToken
     */
    public String getSignonToken() {
        return signonToken;
    }

    /**
     * @param signonToken
     *            the signonToken to set
     */
    public void setSignonToken(String signonToken) {
        this.signonToken = signonToken;
    }

    /**
     * @return the mainCoachMark
     */
    public Integer getMainCoachMark() {
        return mainCoachMark;
    }

    /**
     * @param mainCoachMark
     *            the mainCoachMark to set
     */
    public void setMainCoachMark(Integer mainCoachMark) {
        this.mainCoachMark = mainCoachMark;
    }

    /**
     * @return the pmCoachMark
     */
    public Integer getPmCoachMark() {
        return pmCoachMark;
    }

    /**
     * @param pmCoachMark
     *            the pmCoachMark to set
     */
    public void setPmCoachMark(Integer pmCoachMark) {
        this.pmCoachMark = pmCoachMark;
    }

    /**
     * @return the acctMgrCoachMark
     */
    public Integer getAcctMgrCoachMark() {
        return acctMgrCoachMark;
    }

    /**
     * @param acctMgrCoachMark
     *            the acctMgrCoachMark to set
     */
    public void setAcctMgrCoachMark(Integer acctMgrCoachMark) {
        this.acctMgrCoachMark = acctMgrCoachMark;
    }

    /**
     * @return the channelId
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * @param channelId
     *            the channelId to set
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * @return the lastChannelId
     */
    public String getLastChannelId() {
        return lastChannelId;
    }

    /**
     * @param lastChannelId
     *            the lastChannelId to set
     */
    public void setLastChannelId(String lastChannelId) {
        this.lastChannelId = lastChannelId;
    }

    /**
     * @return the popupTime
     */
    public Date getPopupTime() {
        return popupTime;
    }

    /**
     * @param popupTime
     *            the popupTime to set
     */
    public void setPopupTime(Date popupTime) {
        this.popupTime = popupTime;
    }

    /**
     * @return the mfdShowtype
     */
    public String getMfdShowtype() {
        return mfdShowtype;
    }

    /**
     * @param mfdShowtype
     *            the mfdShowtype to set
     */
    public void setMfdShowtype(String mfdShowtype) {
        this.mfdShowtype = mfdShowtype;
    }

}
