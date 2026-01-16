package com.tfb.aibank.biz.systemconfig.services.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "NCAOT006信用卡登錄活動資訊")
public class NCAOT006Activity {

    @Schema(description = "活動代碼")
    private String activityCode;

    @Schema(description = "活動名稱")
    private String activityName;

    @Schema(description = "活動起日")
    private String upTime;

    @Schema(description = "活動迄日")
    private String downTime;

    @Schema(description = "活動迄日")
    private String realDownTime;;

    @Schema(description = "活動內容")
    private String activityContent;

    @Schema(description = "活動網址")
    private String activityUrl;

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    public String getDownTime() {
        return downTime;
    }

    public void setDownTime(String downTime) {
        this.downTime = downTime;
    }

    public String getRealDownTime() {
        return realDownTime;
    }

    public void setRealDownTime(String realDownTime) {
        this.realDownTime = realDownTime;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    public String getActivityUrl() {
        return activityUrl;
    }

    public void setActivityUrl(String activityUrl) {
        this.activityUrl = activityUrl;
    }
}
