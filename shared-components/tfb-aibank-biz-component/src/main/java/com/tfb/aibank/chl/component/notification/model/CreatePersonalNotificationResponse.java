/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.notification.model;

//@formatter:off
/**
* @(#)CreateOtpRecordResponse.java
* 
* <p>Description:建立個人化通知訊息 - Response</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/27, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CreatePersonalNotificationResponse {

    /** 通知記錄鍵值 */
    private Integer personNotificationRecordKey;

    /**
     * @return the personNotificationRecordKey
     */
    public Integer getPersonNotificationRecordKey() {
        return personNotificationRecordKey;
    }

    /**
     * @param personNotificationRecordKey
     *            the personNotificationRecordKey to set
     */
    public void setPersonNotificationRecordKey(Integer personNotificationRecordKey) {
        this.personNotificationRecordKey = personNotificationRecordKey;
    }

}
