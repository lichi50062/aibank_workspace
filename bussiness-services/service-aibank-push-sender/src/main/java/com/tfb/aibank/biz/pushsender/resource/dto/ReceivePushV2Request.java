package com.tfb.aibank.biz.pushsender.resource.dto;

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)PushV2Request.java
 * 
 * <p>Description:IMP請求(Request)物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/24, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "IMP請求(Request)物件")
public class ReceivePushV2Request {

    /**
     * iMP 中設定的 appName
     */
    @Schema(description = "iMP 中設定的 appName")
    private String appName;

    /**
     * 優先序0~9，9為最緊急
     */
    @Schema(description = "優先序0~9，9為最緊急")
    private int priority;

    /**
     * 推播ID
     */
    @Schema(description = "推播ID")
    private String rowId;

    /**
     * 是否為廣播訊息
     */
    @Schema(description = "是否為廣播訊息")
    private boolean broadcast = false;

    /**
     * 推播的Token
     */
    @Schema(description = "推播的Token")
    private String pushTarget;

    /**
     * 推播訊息的表頭
     */
    @Schema(description = "推播訊息的表頭")
    private String pushTitle;

    /**
     * 推播的內容
     */
    @Schema(description = "推播的內容")
    private String pushContent;

    /**
     * device 平台 android/iOS
     */
    @Schema(description = "device 平台 android/iOS")
    private String platform;

    /**
     * 額外的 payload
     */
    @Schema(description = "額外的 payload")
    private Map<String, String> payload;

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

	public Map<String, String> getPayload() {
		return payload;
	}

	public void setPayload(Map<String, String> payload) {
		this.payload = payload;
	}

}
