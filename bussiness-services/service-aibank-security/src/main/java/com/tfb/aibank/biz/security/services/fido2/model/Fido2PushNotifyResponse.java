package com.tfb.aibank.biz.security.services.fido2.model;

// @formatter:off
/**
 * @(#)FidoPushNotifyResponse.java
 * 
 * <p>Description:</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/5/19, billchang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public class Fido2PushNotifyResponse {

    /** 推播代碼 */
    private String pushCode;

    /** 訊息設定 0:未開啟訊息通知(全天不可發送) 1:已開啟訊息通知(全天可發送) 2:夜間勿擾(2200~0800不發送) */
    private Integer notifyPass;

    /** 是否授權訊息通知，1:已授權；0或空白:未授權 */
    private Integer notifyFlag;

    /** 是否開啟推播通知 */
    private boolean isEnable;

    /** 是否訂閱推播通知 */
    private boolean isSubscribe;

    public String getPushCode() {
        return pushCode;
    }

    public void setPushCode(String pushCode) {
        this.pushCode = pushCode;
    }

    public boolean isSubscribe() {
        return isSubscribe;
    }

    public void setSubscribe(boolean isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    public Integer getNotifyPass() {
        return notifyPass;
    }

    public void setNotifyPass(Integer notifyPass) {
        this.notifyPass = notifyPass;
    }

    public Integer getNotifyFlag() {
        return notifyFlag;
    }

    public void setNotifyFlag(Integer notifyFlag) {
        this.notifyFlag = notifyFlag;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean isEnable) {
        this.isEnable = isEnable;
    }

}
