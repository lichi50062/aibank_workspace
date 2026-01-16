package com.tfb.aibank.chl.system.ot004.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NSTOT004010Rq.java
 * 
 * <p>Description:handshake之後向後端取值 010 </p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NSTOT004010Rq implements RqData {

    private String pushToken;

    /**
     * 曾經登入 = 1
     */
    private int hasLogined;

    /**
     * 啟動資訊紀錄
     */
    private String initInfo;

    /**
     * @return the pushToken
     */
    public String getPushToken() {
        return pushToken;
    }

    /**
     * @param pushToken
     *            the pushToken to set
     */
    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    /**
     * @return the hasLogined
     */
    public int getHasLogined() {
        return hasLogined;
    }

    /**
     * @param hasLogined
     *            the hasLogined to set
     */
    public void setHasLogined(int hasLogined) {
        this.hasLogined = hasLogined;
    }

    public String getInitInfo() {
        return initInfo;
    }

    public void setInitInfo(String initInfo) {
        this.initInfo = initInfo;
    }

}
