package com.tfb.aibank.biz.pushsender.repository.dto;

// @formatter:off
/**
 * @(#)PersonNotificationDeviceInfoDto.java
 * 
 * <p>Description:資料表「PERSON_NOTIFICATION_RECORD」和「MB_DEVICE_PUSH_INFO」INNER JOIN 回傳的結果</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/01, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class PersonNotificationDeviceInfoDto {

    public PersonNotificationDeviceInfoDto(Integer personNotificationRecordKey, Integer priority, String pushMessage, String titleMessage, String deviceUuId, String devicePlatform, String pushToken, Integer notifyPass) {
        this.personNotificationRecordKey = personNotificationRecordKey;
        this.priority = priority;
        this.pushMessage = pushMessage;
        this.titleMessage = titleMessage;
        this.deviceUuId = deviceUuId;
        this.devicePlatform = devicePlatform;
        this.pushToken = pushToken;
        this.notifyPass = notifyPass;
    }

    /** (PERSON_NOTIFICATION_RECORD)通知記錄鍵值 */
    private Integer personNotificationRecordKey;

    /** (PERSON_NOTIFICATION_RECORD)推播優先序，0~9，0:最優先；9:最後 */
    private Integer priority;

    /** (PERSON_NOTIFICATION_RECORD)推播訊息 */
    private String pushMessage;

    /** (PERSON_NOTIFICATION_RECORD)標題訊息 */
    private String titleMessage;

    /** (MB_DEVICE_PUSH_INFO)行動裝置UUID */
    private String deviceUuId;

    /** (MB_DEVICE_PUSH_INFO)裝置作業系統 */
    private String devicePlatform;

    /** (MB_DEVICE_PUSH_INFO)推播Token */
    private String pushToken;

    /** (MB_DEVICE_PUSH_INFO)訊息設定 0:未開啟訊息通知(全天不可發送) 1:已開啟訊息通知(全天可發送) 2:夜間勿擾(2100~0900不發送) */
    private Integer notifyPass;

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public Integer getPersonNotificationRecordKey() {
        return personNotificationRecordKey;
    }

    public void setPersonNotificationRecordKey(Integer personNotificationRecordKey) {
        this.personNotificationRecordKey = personNotificationRecordKey;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getPushMessage() {
        return pushMessage;
    }

    public void setPushMessage(String pushMessage) {
        this.pushMessage = pushMessage;
    }

    public String getTitleMessage() {
        return titleMessage;
    }

    public void setTitleMessage(String titleMessage) {
        this.titleMessage = titleMessage;
    }

    public String getDeviceUuId() {
        return deviceUuId;
    }

    public void setDeviceUuId(String deviceUuId) {
        this.deviceUuId = deviceUuId;
    }

    public String getDevicePlatform() {
        return devicePlatform;
    }

    public void setDevicePlatform(String devicePlatform) {
        this.devicePlatform = devicePlatform;
    }

	public Integer getNotifyPass() {
		return notifyPass;
	}

	public void setNotifyPass(Integer notifyPass) {
		this.notifyPass = notifyPass;
	}

}
