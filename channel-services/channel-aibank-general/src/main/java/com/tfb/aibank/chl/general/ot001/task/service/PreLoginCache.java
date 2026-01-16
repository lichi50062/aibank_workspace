/**
 * 
 */
package com.tfb.aibank.chl.general.ot001.task.service;

import java.util.List;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001011Rq;
import com.tfb.aibank.chl.general.ot005.model.NGNOT005011Rq;

//@formatter:off
/**
* @(#)PreLoginCache.java 
* 
* <p>Description:登入程序 Helper</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20230522, JohnChang
*  <li>初版</li>
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on
public class PreLoginCache {

    public PreLoginCache(NGNOT001011Rq rq) {
        this.uid = rq.getUid();
        this.uuid = rq.getUuid();
        this.secret = rq.getSecret();
        this.pwType = rq.getPwType();
        this.loginType = rq.getLoginType();
        this.isSignatureLogin = rq.getIsSignatureLogin();
        this.challenge = rq.getChallenge();
        this.agent = rq.getAgent();
        this.screenHeight = rq.getScreenHeight();
        this.screenWidth = rq.getScreenWidth();
    }

    public PreLoginCache(NGNOT005011Rq rq) {
        this.uid = rq.getUid();
        this.uuid = rq.getUuid();
        this.secret = rq.getSecret();
        this.pwType = rq.getPwType();
        this.loginType = rq.getLoginType();
        this.isSignatureLogin = rq.getIsSignatureLogin();
        this.challenge = rq.getChallenge();
        this.agent = rq.getAgent();
        this.screenHeight = rq.getScreenHeight();
        this.screenWidth = rq.getScreenWidth();
    }


    public PreLoginCache() {

    }

    public NGNOT001011Rq getRqData() {
        NGNOT001011Rq rqData = new NGNOT001011Rq();
        rqData.setUid(uid);
        rqData.setUuid(uuid);
        rqData.setSecret(secret);
        rqData.setPwType(pwType);
        rqData.setLoginType(loginType);
        rqData.setIsSignatureLogin(isSignatureLogin);
        rqData.setChallenge(challenge);
        rqData.setAgent(agent);
        rqData.setScreenHeight(screenHeight);
        rqData.setScreenWidth(screenWidth);
        return rqData;
    }
    
    public NGNOT005011Rq getRqDataAcc() {
        NGNOT005011Rq rqData = new NGNOT005011Rq();
        rqData.setUid(uid);
        rqData.setUuid(uuid);
        rqData.setSecret(secret);
        rqData.setPwType(pwType);
        rqData.setLoginType(loginType);
        rqData.setIsSignatureLogin(isSignatureLogin);
        rqData.setChallenge(challenge);
        rqData.setAgent(agent);
        rqData.setScreenHeight(screenHeight);
        rqData.setScreenWidth(screenWidth);
        return rqData;
    }


    /**
     * 統編/身分證字號
     */
    private String uid;

    /**
     * 使用者代碼
     */
    private String uuid;

    /**
     * 密碼
     */
    private String secret;

    /**
     * 密碼型態 0:密碼輸入, 1:手勢
     */
    private String pwType;

    /**
     * 登入身份
     */
    private String loginType;

    /**
     * E-mail
     */
    private List<String> email;

    /**
     * 接續登入
     */
    private boolean continueLogin;

    /**
     * 廠牌名稱
     */
    private String marketingName;

    /**
     * 指紋、顏值登入的驗證 =Y
     */
    private String isSignatureLogin;

    /**
     * 生物辨識比對值
     */
    private String challenge;

    /**
     * 電話
     */
    private String mobileNo;

    /**
     * User Agent
     */
    private String agent;
    /** 螢幕寬 */
    private String screenWidth;
    /** 螢幕高 */
    private String screenHeight;

    /**
     * 
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
     * @return the secrxt
     */
    public String getSecret() {
        return secret;
    }

    /**
     * @param secrxt
     *            the secrxt to set
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * @return the pwType
     */
    public String getPwType() {
        return pwType;
    }

    /**
     * @param pwType
     *            the pwType to set
     */
    public void setPwType(String pwType) {
        this.pwType = pwType;
    }

    /**
     * @return the loginType
     */
    public String getLoginType() {
        return loginType;
    }

    /**
     * @param loginType
     *            the loginType to set
     */
    public void setLoginType(String loginType) {
        this.loginType = loginType;
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
     * @return the continueLogin
     */
    public boolean isContinueLogin() {
        return continueLogin;
    }

    /**
     * @param continueLogin
     *            the continueLogin to set
     */
    public void setContinueLogin(boolean continueLogin) {
        this.continueLogin = continueLogin;
    }

    /**
     * @return the marketingName
     */
    public String getMarketingName() {
        return marketingName;
    }

    /**
     * @param marketingName
     *            the marketingName to set
     */
    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName;
    }

    /**
     * @return the isSignatureLogin
     */
    public String getIsSignatureLogin() {
        return isSignatureLogin;
    }

    /**
     * @param isSignatureLogin
     *            the isSignatureLogin to set
     */
    public void setIsSignatureLogin(String isSignatureLogin) {
        this.isSignatureLogin = isSignatureLogin;
    }

    /**
     * @return the challenge
     */
    public String getChallenge() {
        return challenge;
    }

    /**
     * @param challenge
     *            the challenge to set
     */
    public void setChallenge(String challenge) {
        this.challenge = challenge;
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
     * @return the agent
     */
    public String getAgent() {
        return agent;
    }

    /**
     * @param agent
     *            the agent to set
     */
    public void setAgent(String agent) {
        this.agent = agent;
    }

    /**
     * @return the screenWidth
     */
    public String getScreenWidth() {
        return screenWidth;
    }

    /**
     * @param screenWidth
     *            the screenWidth to set
     */
    public void setScreenWidth(String screenWidth) {
        this.screenWidth = screenWidth;
    }

    /**
     * @return the screenHeight
     */
    public String getScreenHeight() {
        return screenHeight;
    }

    /**
     * @param screenHeight
     *            the screenHeight to set
     */
    public void setScreenHeight(String screenHeight) {
        this.screenHeight = screenHeight;
    }
    
    /**
     * 依LoginType 取出 Company種類 
     * @return
     */
    public Integer getCompanyKindByLoginType() {
        return StringUtils.isBlank(this.getLoginType()) ? null :  "m1".equals(this.getLoginType()) ? 2 : 3;
    }
}
