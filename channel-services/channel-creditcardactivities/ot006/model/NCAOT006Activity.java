package com.tfb.aibank.chl.creditcardactivities.ot006.model;

import java.util.Date;

//@formatter:off
/**
  * @(#)NCCOT006Activity.java
  * 
  * <p>Description:信用卡活動物件</p>
  * 
  * <p>Modify History:</p>
  * v1.0, 2023/09/12, John Chang
  * <ol>
  *  <li>初版</li>
  * </ol>
  */
//@formatter:on
public class NCAOT006Activity {

    /** 活動代碼 */
    private String activityCode;

    /** 活動名稱 */
    private String activityName;

    /** 活動起日 */
    private String upTime;

    /** 活動迄日 */
    private String downTime;

    /** 活動迄日 */
    private Date realDownTime;;

    /** 登錄日期 */
    private String registerTime;

    /** 活動內容 */
    private String activityContent;

    /** 活動網址 */
    private String activityUrl;

    /** 已登錄活動 */
    private boolean isRegistered;

    /** 已額滿 */
    private boolean isFull;

    /** 登錄狀態 */
    private String status;

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
    public String getUpTime() {
        return upTime;
    }

    /**
     * @param upTime
     *            the upTime to set
     */
    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    /**
     * @return the downTime
     */
    public String getDownTime() {
        return downTime;
    }

    /**
     * @param downTime
     *            the downTime to set
     */
    public void setDownTime(String downTime) {
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

    /**
     * @return the isRegistered
     */
    public boolean isRegistered() {
        return isRegistered;
    }

    /**
     * @param isRegistered
     *            the isRegistered to set
     */
    public void setRegistered(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    /**
     * @return the isFull
     */
    public boolean isFull() {
        return isFull;
    }

    /**
     * @param isFull
     *            the isFull to set
     */
    public void setFull(boolean isFull) {
        this.isFull = isFull;
    }

    /**
     * @return the registerTime
     */
    public String getRegisterTime() {
        return registerTime;
    }

    /**
     * @param registerTime
     *            the registerTime to set
     */
    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the realDownTime
     */
    public Date getRealDownTime() {
        return realDownTime;
    }

    /**
     * @param realDownTime
     *            the realDownTime to set
     */
    public void setRealDownTime(Date realDownTime) {
        this.realDownTime = realDownTime;
    }

}
