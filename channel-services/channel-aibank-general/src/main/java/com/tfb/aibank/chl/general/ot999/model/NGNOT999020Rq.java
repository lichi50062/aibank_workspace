package com.tfb.aibank.chl.general.ot999.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NGNOT999020Rq.java 
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
public class NGNOT999020Rq implements RqData {

    /**
     * 生物辨識比對值
     */
    private String challenge;

    /**
     * FIDO認證值
     */
    private String code;

 
    
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
     * E2EE給前端uid/uuid加密時，是否帶入之時間因子
     */
    private boolean isUidUuidNeedWithTime;

    /**
     * E2EE給前端PWD加密時，是否帶入之時間因子
     */
    private boolean isPwdNeedWithTime;
    
    /**
     * 使用的 AuthType (此交易只有一般登入會有此值, 快登由010Cache 決定) 
     */
    
    private String currentAuthType;
        
    /***
     * 3 層式密碼由前端選擇帶上來 m1:一般, m2: 信用卡
     */
    private String loginType;
    
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
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
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

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public boolean isUidUuidNeedWithTime() {
        return isUidUuidNeedWithTime;
    }

    public void setUidUuidNeedWithTime(boolean isUidUuidNeedWithTime) {
        this.isUidUuidNeedWithTime = isUidUuidNeedWithTime;
    }

    public boolean isPwdNeedWithTime() {
        return isPwdNeedWithTime;
    }
    /**
     * 
     * @param isPwdNeedWithTime
     */
    public void setPwdNeedWithTime(boolean isPwdNeedWithTime) {
        this.isPwdNeedWithTime = isPwdNeedWithTime;
    }

    public String getCurrentAuthType() {
        return currentAuthType;
    }

    public void setCurrentAuthType(String currentAuthType) {
        this.currentAuthType = currentAuthType;
    }
    /**
     * 
     * @return
     */
    public String getLoginType() {
        return loginType;
    }
    /**
     * 
     * @param loginType
     */
    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
 
}
