package com.tfb.aibank.chl.component.tasktime;

// @formatter:off
/**
 * @(#)TaskTime.java
 * 
 * <p>Description:交易作業時間</p>
 * 
 * <p>Modify History:</p>
 * <ol>v1.0, 2023/6/19, 
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class TaskTime {

    /**
     * 交易功能代號
     */
    private String taskId;

    /**
     * 是否區分通路別
     */
    private Integer channelFlag = 0;

    /**
     * 不分通路的起始營業時間 (HH:mm)
     */
    private String generalStartTime;

    /**
     * 不分通路的結束營業時間 (HH:mm)
     */
    private String generalEndTime;

    /**
     * FXML轉帳起始營業時間(HH:mm)
     */
    private String fxmlStartTime;

    /**
     * FXML轉帳結束營業時間(HH:mm)
     */
    private String fxmlEndTime;

    /**
     * 通匯轉帳起始營業時間(HH:mm)
     */
    private String remitStartTime;

    /**
     * 通匯轉帳結束營業時間(HH:mm)
     */
    private String remitEndTime;

    /**
     * 預約付款天數
     */
    private Integer reserveDays = 180;

    /**
     * @return taskId
     * @see #taskId
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * @param taskId
     * @see #taskId
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * @return channelFlag
     * @see #channelFlag
     */
    public Integer getChannelFlag() {
        return channelFlag;
    }

    /**
     * @param channelFlag
     * @see #channelFlag
     */
    public void setChannelFlag(Integer channelFlag) {
        this.channelFlag = channelFlag;
    }

    /**
     * @return generalStartTime
     * @see #generalStartTime
     */
    public String getGeneralStartTime() {
        return generalStartTime;
    }

    /**
     * @param generalStartTime
     * @see #generalStartTime
     */
    public void setGeneralStartTime(String generalStartTime) {
        this.generalStartTime = generalStartTime;
    }

    /**
     * @return generalEndTime
     * @see #generalEndTime
     */
    public String getGeneralEndTime() {
        return generalEndTime;
    }

    /**
     * @param generalEndTime
     *            的設定的 generalEndTime
     */
    public void setGeneralEndTime(String generalEndTime) {
        this.generalEndTime = generalEndTime;
    }

    /**
     * @return fxmlStartTime
     */
    public String getFxmlStartTime() {
        return fxmlStartTime;
    }

    /**
     * @param fxmlStartTime
     *            的設定的 fxmlStartTime
     */
    public void setFxmlStartTime(String fxmlStartTime) {
        this.fxmlStartTime = fxmlStartTime;
    }

    /**
     * @return fxmlEndTime
     */
    public String getFxmlEndTime() {
        return fxmlEndTime;
    }

    /**
     * @param fxmlEndTime
     *            的設定的 fxmlEndTime
     */
    public void setFxmlEndTime(String fxmlEndTime) {
        this.fxmlEndTime = fxmlEndTime;
    }

    /**
     * @return remitStartTime
     */
    public String getRemitStartTime() {
        return remitStartTime;
    }

    /**
     * @param remitStartTime
     *            的設定的 remitStartTime
     */
    public void setRemitStartTime(String remitStartTime) {
        this.remitStartTime = remitStartTime;
    }

    /**
     * @return remitEndTime
     */
    public String getRemitEndTime() {
        return remitEndTime;
    }

    /**
     * @param remitEndTime
     *            的設定的 remitEndTime
     */
    public void setRemitEndTime(String remitEndTime) {
        this.remitEndTime = remitEndTime;
    }

    /**
     * @return reserveDays
     * @see #reserveDays
     */
    public Integer getReserveDays() {
        return reserveDays;
    }

    /**
     * @param reserveDays
     * @see #reserveDays
     */
    public void setReserveDays(Integer reserveDays) {
        this.reserveDays = reserveDays;
    }

}
