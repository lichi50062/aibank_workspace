/**
 * 
 */
package com.tfb.aibank.chl.system.resource.dto;

import java.util.Date;

//@formatter:off
/**
* @(#)MbDeviceInfoRepository.java
* 
* <p>Description:更新 FIRST_DOWNLOAD_RECORD Request</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/12, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class UpdatePushTokenRequest {

    /**
     * 行動裝置UUID
     */
    private String deviceUuid;

    /**
     * 行動裝置作業系統
     */
    private String platform;

    /**
     * 已發送推播 0:未發送，1:已發送
     */
    private Integer pushFlag;

    /**
     * 行動裝置作業系統
     */
    private Date loginTime;

    /**
     * 推播 Token
     */
    private String pushToken;

    /**
     * 曾經登入 = 1
     */
    private int hasLogined;

    /**
     * @return the deviceUuid
     */
    public String getDeviceUuid() {
        return deviceUuid;
    }

    /**
     * @param deviceUuid
     *            the deviceUuid to set
     */
    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @param platform
     *            the platform to set
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * @return the pushFlag
     */
    public Integer getPushFlag() {
        return pushFlag;
    }

    /**
     * @param pushFlag
     *            the pushFlag to set
     */
    public void setPushFlag(Integer pushFlag) {
        this.pushFlag = pushFlag;
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

}
