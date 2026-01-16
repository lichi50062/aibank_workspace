package com.tfb.aibank.biz.user.repository.entities;

import java.io.Serializable;
import java.util.Date;

import com.tfb.aibank.chl.session.type.LoginStatusType;

import jakarta.persistence.Basic;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)UserLoginEntity.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Cacheable(false)
@Table(name = "USER_LOGIN")
public class UserLoginEntity implements Serializable {

    /**
     * <code>serialVersionUID</code> 的註解
     */
    private static final long serialVersionUID = 8463947578174220381L;

    /** 使用者鍵值 */
    @Id
    @Column(name = "USER_KEY")
    private Integer userKey;

    /** 公司鍵值 */
    @Basic
    @Column(name = "COMPANY_KEY")
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
    @Basic
    @Column(name = "STATUS")
    private Integer status = LoginStatusType.NEVER.getCode();

    /** 密碼錯誤次數 */
    @Basic
    @Column(name = "PWD_ERROR_COUNT")
    private Integer pwdErrorCount = 0;

    /** 密碼錯誤時間 */
    @Basic
    @Column(name = "PWD_ERROR_TIME")
    private Date pwdErrorTime;

    /** 密碼變更的時間 */
    @Basic
    @Column(name = "PWD_CHANGE_TIME")
    private Date pwdChangeTime;

    /** 是否強制變更密碼 */
    @Basic
    @Column(name = "PWD_FORCE_CHANGE")
    private Integer pwdForceChange = 0;

    /** AP server host name */
    @Basic
    @Column(name = "SERVER_ID")
    private String serverId;

    /** 最近登入的Http Session Id */
    @Basic
    @Column(name = "SESSION_ID")
    private String sessionId;

    /** 最近登入來源IP */
    @Basic
    @Column(name = "CLIENT_IP")
    private String clientIp;

    /** 最近登入時間 (登入時寫入，不管登入成功或失敗) */
    @Basic
    @Column(name = "LOGIN_TIME")
    private Date loginTime;

    /** 最後存取時間 */
    @Basic
    @Column(name = "LAST_ACCESS_TIME")
    private Date lastAccessTime;

    /**
     * <p>
     * 上次登入時間
     * </p>
     * 效能考量 於登入成功後將LOGINTIME複製到此欄位
     */
    @Basic
    @Column(name = "LAST_LOGIN_TIME")
    private Date lastLoginTime;

    /**
     * <p>
     * 登入結果 0: 成功 1: 失敗
     * </p>
     */
    @Basic
    @Column(name = "LOGIN_RESULT")
    private Integer loginResult;

    /**
     * <p>
     * 上次登入結果 0: 成功 1: 失敗
     * </p>
     * 效能考量 於登入處理結束後將LOGIN_RESULT複製到此欄位
     */
    @Basic
    @Column(name = "LAST_LOGIN_RESULT")
    private Integer lastLoginResult;

    /**
     * 前一次登入來源IP 效能考量 於登入處理結束後將CLIENT_IP複製到此欄位
     **/
    @Basic
    @Column(name = "LAST_CLIENT_IP")
    private String lastClientIp;

    /**
     * 國別中文 (from clientIP)
     */
    @Basic
    @Column(name = "NATION_NAME")
    private String nationName;

    /**
     * 前一次登入國別中文 (from clientIP) 效能考量 於登入處理結束後將nationName複製到此欄位
     */
    @Basic
    @Column(name = "LAST_NATION_NAME")
    private String lastNationName;

    /** 提示訊息 */
    @Basic
    @Column(name = "WARNING_MESSAGE")
    private String warningMessage;

    /** 登入Token, 用於判斷使用者重覆登入(同一session登入第二次) */
    @Basic
    @Column(name = "SIGNON_TOKEN")
    private String signonToken;

    /**
     * 是否已顯示過首次登入功能提示 (0: 否 1:是)
     */
    @Basic
    @Column(name = "MAIN_COACH_MARK")
    private Integer mainCoachMark;

    /**
     * 是否已顯示過個人訊息功能提示 (0: 否 1:是)
     */
    @Basic
    @Column(name = "PM_COACH_MARK")
    private Integer pmCoachMark;

    /**
     * 是否已顯示過收支管家功能提示 (0: 否 1:是)
     */
    @Basic
    @Column(name = "ACCT_MGR_COACH_MARK")
    private Integer acctMgrCoachMark;

    /**
     * 通路代碼: 0：網銀 1：網路ATM 2：媒體薪轉 3：e化繳費網 4：行銀 5：信用卡會員 6：員工持股儲蓄信託 7：員工分紅認股信託FINI保管專區
     * 
     * @mobile
     */
    @Basic
    @Column(name = "CHANNEL_ID")
    private String channelId;

    /**
     * 前一次登入的 channel id 通路代碼: 0：網銀 1：網路ATM 2：媒體薪轉 3：e化繳費網 4：行銀 5：信用卡會員 6：員工持股儲蓄信託 7：員工分紅認股信託FINI保管專區
     * 
     * @mobile
     */
    @Basic
    @Column(name = "LAST_CHANNEL_ID")
    private String lastChannelId;

    /**
     * 彈跳顯示時間
     * 
     * @mobile
     */
    @Basic
    @Column(name = "POPUP_TIME")
    private Date popupTime;

    /**
     * 基金總覽顯示方式：0-簡易版;1-詳細版
     * 
     * @mobile
     */
    @Basic
    @Column(name = "MFD_SHOWTYPE")
    private String mfdShowtype;

    /**
     * @see #userKey
     * @return
     */
    public int getUserKey() {
        return userKey;
    }

    /**
     * @see #userKey
     * @param userKey
     */
    public void setUserKey(int userKey) {
        this.userKey = userKey;
    }

    /**
     * @see #companyKey
     * @return
     */
    public Integer getCompanyKey() {
        return companyKey;
    }

    /**
     * @see #companyKey
     * @param companyKey
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * @see #status
     * @return
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @see #status
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @see #pwdErrorCount
     * @return
     */
    public Integer getPwdErrorCount() {
        return pwdErrorCount;
    }

    /**
     * @see #pwdErrorCount
     * @param pwdErrorCount
     */
    public void setPwdErrorCount(Integer pwdErrorCount) {
        this.pwdErrorCount = pwdErrorCount;
    }

    /**
     * @see #pwdErrorTime
     * @return
     */
    public Date getPwdErrorTime() {
        return pwdErrorTime;
    }

    public void setPwdErrorTime(Date pwdErrorTime) {
        this.pwdErrorTime = pwdErrorTime;
    }

    /**
     * @see #pwdChangeTime
     * @return
     */
    public Date getPwdChangeTime() {
        return pwdChangeTime;
    }

    /**
     * @see #pwdChangeTime
     * @param pwdChangeTime
     */
    public void setPwdChangeTime(Date pwdChangeTime) {
        this.pwdChangeTime = pwdChangeTime;
    }

    /**
     * @see #pwdForceChange
     * @return
     */
    public Integer getPwdForceChange() {
        return pwdForceChange;
    }

    /**
     * @see #pwdForceChange
     * @param pwdForceChange
     */
    public void setPwdForceChange(Integer pwdForceChange) {
        this.pwdForceChange = pwdForceChange;
    }

    /**
     * 取得 serverId
     * 
     * @return 傳回 serverId。
     */
    public String getServerId() {
        return serverId;
    }

    /**
     * 設定 serverId
     * 
     * @param serverId
     *            要設定的 serverId。
     */
    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    /**
     * @see #sessionId
     * @return
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @see #sessionId
     * @param sessionId
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * @see #clientIp
     * @return
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * @see #clientIp
     * @param clientIp
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * @see #logigTime
     * @return
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * @see #logigTime
     * @param logigTime
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * @see #lastAccessTime
     * @return
     */
    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    /**
     * @see #lastAccessTime
     * @param lastAccessTime
     */
    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    /**
     * @see #lastLoginTime
     * @return
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * @see #lastLoginTime
     * @param lastLoginTime
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 取得 warningMessage
     * 
     * @return 傳回 warningMessage。
     */
    public String getWarningMessage() {
        return warningMessage;
    }

    /**
     * 設定 warningMessage
     * 
     * @param warningMessage
     *            要設定的 warningMessage。
     */
    public void setWarningMessage(String warningMessage) {
        this.warningMessage = warningMessage;
    }

    /**
     * 取得 signonToken
     * 
     * @return 傳回 signonToken。
     */
    public String getSignonToken() {
        return signonToken;
    }

    /**
     * 設定 signonToken
     * 
     * @param signonToken
     *            要設定的 signonToken。
     */
    public void setSignonToken(String signonToken) {
        this.signonToken = signonToken;
    }

    /**
     * 取得 mainCoachMark
     * 
     * @return 傳回 mainCoachMark。
     */
    public Integer getMainCoachMark() {
        return mainCoachMark;
    }

    /**
     * 設定 mainCoachMark
     * 
     * @param mainCoachMark
     *            要設定的 mainCoachMark。
     */
    public void setMainCoachMark(Integer mainCoachMark) {
        this.mainCoachMark = mainCoachMark;
    }

    /**
     * 取得 pmCoachMark
     * 
     * @return 傳回 pmCoachMark。
     */
    public Integer getPmCoachMark() {
        return pmCoachMark;
    }

    /**
     * 設定 pmCoachMark
     * 
     * @param pmCoachMark
     *            要設定的 pmCoachMark。
     */
    public void setPmCoachMark(Integer pmCoachMark) {
        this.pmCoachMark = pmCoachMark;
    }

    /**
     * 取得 acctMgrCoachMark
     * 
     * @return 傳回 acctMgrCoachMark。
     */
    public Integer getAcctMgrCoachMark() {
        return acctMgrCoachMark;
    }

    /**
     * 設定 acctMgrCoachMark
     * 
     * @param acctMgrCoachMark
     *            要設定的 acctMgrCoachMark。
     */
    public void setAcctMgrCoachMark(Integer acctMgrCoachMark) {
        this.acctMgrCoachMark = acctMgrCoachMark;
    }

    /**
     * 取得 loginResult
     * 
     * @return 傳回 loginResult。
     */
    public Integer getLoginResult() {
        return loginResult;
    }

    /**
     * 設定 loginResult
     * 
     * @param loginResult
     *            要設定的 loginResult。
     */
    public void setLoginResult(Integer loginResult) {
        this.loginResult = loginResult;
    }

    /**
     * 取得 lastLoginResult
     * 
     * @return 傳回 lastLoginResult。
     */
    public Integer getLastLoginResult() {
        return lastLoginResult;
    }

    /**
     * 設定 lastLoginResult
     * 
     * @param lastLoginResult
     *            要設定的 lastLoginResult。
     */
    public void setLastLoginResult(Integer lastLoginResult) {
        this.lastLoginResult = lastLoginResult;
    }

    /**
     * 取得 lastClientIp
     * 
     * @return 傳回 lastClientIp。
     */
    public String getLastClientIp() {
        return lastClientIp;
    }

    /**
     * 設定 lastClientIp
     * 
     * @param lastClientIp
     *            要設定的 lastClientIp。
     */
    public void setLastClientIp(String lastClientIp) {
        this.lastClientIp = lastClientIp;
    }

    /**
     * 取得 nationName
     * 
     * @return 傳回 nationName。
     */
    public String getNationName() {
        return nationName;
    }

    /**
     * 設定 nationName
     * 
     * @param nationName
     *            要設定的 nationName。
     */
    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    /**
     * 取得 lastNationName
     * 
     * @return 傳回 lastNationName。
     */
    public String getLastNationName() {
        return lastNationName;
    }

    /**
     * 設定 lastNationName
     * 
     * @param lastNationName
     *            要設定的 lastNationName。
     */
    public void setLastNationName(String lastNationName) {
        this.lastNationName = lastNationName;
    }

    /**
     * 取得 channelId
     * 
     * @return 傳回 channelId。
     * @mobile
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * 設定 channelId
     * 
     * @param channelId
     *            要設定的 channelId。
     * @mobile
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * 取得 lastChannelId
     *
     * @return lastChannelId
     */
    public String getLastChannelId() {
        return lastChannelId;
    }

    /**
     * 設定 lastChannelId
     *
     * @param lastChannelId
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

    /**
     * @param userKey
     *            the userKey to set
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

}
