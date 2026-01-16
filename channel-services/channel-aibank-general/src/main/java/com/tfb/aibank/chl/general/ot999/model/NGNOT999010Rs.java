package com.tfb.aibank.chl.general.ot999.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NGNOT999010Rs.java 
* 
* <p>Description:快速身份認證</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 2024/01/17, JohnChang
*  <li>初版</li6
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on
@Component
public class NGNOT999010Rs implements RsData {

    // Public Key for UID
    private String publicKeyforUid;

    // Public Key for Secrxt
    private String publicKeyforSecret;

    /** 是否支援快速登入 */
    private boolean isFastLogin;

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
     * 九宮格因子
     */
    private String coefficient;

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
     * 行銀快速身份認證 狀態碼
     */
    private String statusCode;

    /**
     * 行銀快速身份認證 CallBack Url
     */
    private String callBackUrl;

    /**
     * 開啟方式
     */
    private String openType;

    /**
     * 內崁參數
     */
    private String moduleParam;

    /**
     * 網域白名單
     */
    private List<String> whiteListWhenOpenUrl;

    /** Header顯示方式 */
    private String moduleType;
    
    /**
     *  // OR關係的認證類型, 只能是1,2,3
     */
	private List<String> primaryTypes; 
	/**
	 * #後的認證類型, 只能是3 
	 */
    private String fallbackType;
    /**
     * +後的認證類型, 只能是 4
     */
    private String additionalType; 
    
    
    /**
     * E2EE給前端uid/uuid加密時，是否帶入之時間因子
     */
    private boolean isUidUuidNeedWithTime;
    /**
     * 
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
     * @return the coefficient
     */
    public String getCoefficient() {
        return coefficient;
    }

    /**
     * @param coefficient
     *            the coefficient to set
     */
    public void setCoefficient(String coefficient) {
        this.coefficient = coefficient;
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
     * @return the callBackUrl
     */
    public String getCallBackUrl() {
        return callBackUrl;
    }

    /**
     * @param callBackUrl
     *            the callBackUrl to set
     */
    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
    }

    /**
     * @return the statusCode
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode
     *            the statusCode to set
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return the openType
     */
    public String getOpenType() {
        return openType;
    }

    /**
     * @param openType
     *            the openType to set
     */
    public void setOpenType(String openType) {
        this.openType = openType;
    }

    /**
     * @return the whiteListWhenOpenUrl
     */
    public List<String> getWhiteListWhenOpenUrl() {
        return whiteListWhenOpenUrl;
    }

    /**
     * @param whiteListWhenOpenUrl
     *            the whiteListWhenOpenUrl to set
     */
    public void setWhiteListWhenOpenUrl(List<String> whiteListWhenOpenUrl) {
        this.whiteListWhenOpenUrl = whiteListWhenOpenUrl;
    }

    /**
     * @return the moduleType
     */
    public String getModuleType() {
        return moduleType;
    }

    /**
     * @param moduleType
     *            the moduleType to set
     */
    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    /**
     * @return the moduleParam
     */
    public String getModuleParam() {
        return moduleParam;
    }

    /**
     * @param moduleParam
     *            the moduleParam to set
     */
    public void setModuleParam(String moduleParam) {
        this.moduleParam = moduleParam;
    }
    
    /**
     * 
     * @return
     */
	public List<String> getPrimaryTypes() {
		return primaryTypes;
	}

	/**
	 * 
	 * @param primaryTypes
	 */
	public void setPrimaryTypes(List<String> primaryTypes) {
		this.primaryTypes = primaryTypes;
	}
	/**
	 * 
	 * @return
	 */
	public String getFallbackType() {
		return fallbackType;
	}
	/**
	 * 
	 * @param fallbackType
	 */
	public void setFallbackType(String fallbackType) {
		this.fallbackType = fallbackType;
	}
	/**
	 * 
	 * @return
	 */
	public String getAdditionalType() {
		return additionalType;
	}
	/**
	 * 
	 * @param additionalType
	 */
	public void setAdditionalType(String additionalType) {
		this.additionalType = additionalType;
	}

    public boolean isUidUuidNeedWithTime() {
        return isUidUuidNeedWithTime;
    }

    public void setUidUuidNeedWithTime(boolean isUidUuidNeedWithTime) {
        this.isUidUuidNeedWithTime = isUidUuidNeedWithTime;
    }

    public String getUidUuidWithTime() {
        return uidUuidWithTime;
    }

    public void setUidUuidWithTime(String uidUuidWithTime) {
        this.uidUuidWithTime = uidUuidWithTime;
    }

    public int getTimeFactorValidSeconds() {
        return timeFactorValidSeconds;
    }

    public void setTimeFactorValidSeconds(int timeFactorValidSeconds) {
        this.timeFactorValidSeconds = timeFactorValidSeconds;
    }

    public boolean isPwdNeedWithTime() {
        return isPwdNeedWithTime;
    }

    public void setPwdNeedWithTime(boolean isPwdNeedWithTime) {
        this.isPwdNeedWithTime = isPwdNeedWithTime;
    }

    public String getPwdWithTime() {
        return pwdWithTime;
    }

    public void setPwdWithTime(String pwdWithTime) {
        this.pwdWithTime = pwdWithTime;
    }

}
