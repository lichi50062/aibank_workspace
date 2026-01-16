package com.tfb.aibank.chl.general.ot001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NGNOT001101Rq.java 
* 
* <p>Description:登入 FIDO 綁定初始化 QueryVerifyResult</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20230605, JohnChang
*  <li>初版</li6
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on

@Component
public class NGNOT001101Rq implements RqData {

    // 設定的密碼型態
    private int loginPasswordType;

    // 推播Token
    private String pushToken;

    private String enableNotification;
    // 發送通知種類 (0為不發送, Null 或 1 = Email (向下相容)
    private Integer sendNotificationMethod;

    /**
     * @return the loginPasswordType
     */
    public int getLoginPasswordType() {
        return loginPasswordType;
    }

    /**
     * @param loginPasswordType
     *            the loginPasswordType to set
     */
    public void setLoginPasswordType(int loginPasswordType) {
        this.loginPasswordType = loginPasswordType;
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
     * @return the enableNotification
     */
    public String getEnableNotification() {
        return enableNotification;
    }

    /**
     * @param enableNotification
     *            the enableNotification to set
     */
    public void setEnableNotification(String enableNotification) {
        this.enableNotification = enableNotification;
    }

    /**
     * 
     * @return
     */
    public Integer getSendNotificationMethod() {
        return sendNotificationMethod;
    }

    /**
     * 
     * @param sendNotificationMethod
     */
    public void setSendNotificationMethod(Integer sendNotificationMethod) {
        this.sendNotificationMethod = sendNotificationMethod;
    }

}
