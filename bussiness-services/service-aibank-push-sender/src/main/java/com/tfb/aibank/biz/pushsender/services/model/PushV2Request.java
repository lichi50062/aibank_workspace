package com.tfb.aibank.biz.pushsender.services.model;

/**
 * IMP v2 Request
 * 
 * @author Edward Tien
 */
public class PushV2Request {

    /**
     * iMP 中設定的 appName
     */
    private String appName;

    /**
     * 優先序0~9，9為最緊急
     */
    private int priority;

    /**
     * 推播ID
     */
    private String rowId;

    /**
     * 是否為廣播訊息
     */
    private boolean broadcast = false;

    /**
     * 推播的Token
     */
    private String pushTarget;

    /**
     * 推播訊息的表頭
     */
    private String pushTitle;

    /**
     * 推播的內容
     */
    private String pushContent;

    /**
     * device 平台 android/iOS
     */
    private String platform;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public boolean isBroadcast() {
        return broadcast;
    }

    public void setBroadcast(boolean broadcast) {
        this.broadcast = broadcast;
    }

    public String getPushTarget() {
        return pushTarget;
    }

    public void setPushTarget(String pushTarget) {
        this.pushTarget = pushTarget;
    }

    public String getPushTitle() {
        return pushTitle;
    }

    public void setPushTitle(String pushTitle) {
        this.pushTitle = pushTitle;
    }

    public String getPushContent() {
        return pushContent;
    }

    public void setPushContent(String pushContent) {
        this.pushContent = pushContent;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

}
