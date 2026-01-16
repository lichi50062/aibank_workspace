package com.tfb.aibank.chl.creditcardactivities.resource.dto;

import java.util.Date;

//@formatter:off
/**
 * @(#)CreditCardActivityResponseRepeat.java
 * 
 * <p>Description:Credit_Card_HOT_Activity 信用卡熱門活動查詢 Response</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/08/15, Lichi Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class CreditCardHotActivityResponseRepeat{

    /** 活動代碼 */
    private String activityCode;

    /** 活動名稱 */
    private String activityName;

    /** 活動起日 */
    private Date upTime;

    /** 活動迄日 */
    private Date downTime;

    /** 活動內容 */
    private String activityContent;

    /** 活動網址 */
    private String activityUrl;

    /**
     * @return the activityCode
     */
    public String getActivityCode() {
        return activityCode;
    }

    /**
     * @param activityCode
     *            the activityCode to set
     */
    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    /**
     * @return the activityName
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * @param activityName
     *            the activityName to set
     */
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    /**
     * @return the upTime
     */
    public Date getUpTime() {
        return upTime;
    }

    /**
     * @param upTime
     *            the upTime to set
     */
    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    /**
     * @return the downTime
     */
    public Date getDownTime() {
        return downTime;
    }

    /**
     * @param downTime
     *            the downTime to set
     */
    public void setDownTime(Date downTime) {
        this.downTime = downTime;
    }

    /**
     * @return the activityContent
     */
    public String getActivityContent() {
        return activityContent;
    }

    /**
     * @param activityContent
     *            the activityContent to set
     */
    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    /**
     * @return the activityUrl
     */
    public String getActivityUrl() {
        return activityUrl;
    }

    /**
     * @param activityUrl
     *            the activityUrl to set
     */
    public void setActivityUrl(String activityUrl) {
        this.activityUrl = activityUrl;
    }

}
