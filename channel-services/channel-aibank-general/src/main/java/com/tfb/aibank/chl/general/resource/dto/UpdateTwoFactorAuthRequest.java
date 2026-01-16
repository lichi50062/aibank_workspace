/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.general.resource.dto;

// @formatter:off
/**
 * @(#)UpdateTwoFactorAuthRequest.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/26, bensonlin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class UpdateTwoFactorAuthRequest {
    /**
     * 
     */
    private String updateAction;
    /**
     * 
     */
    private Integer personNotificationRecordKey;
    /**
     * 
     */
    private boolean updatePersonNotificationOnly;
    /**
     * 語系
     */
    private String locale;

    public String getUpdateAction() {
        return updateAction;
    }

    public void setUpdateAction(String updateAction) {
        this.updateAction = updateAction;
    }

    public Integer getPersonNotificationRecordKey() {
        return personNotificationRecordKey;
    }

    public void setPersonNotificationRecordKey(Integer personNotificationRecordKey) {
        this.personNotificationRecordKey = personNotificationRecordKey;
    }

    public boolean isUpdatePersonNotificationOnly() {
        return updatePersonNotificationOnly;
    }

    public void setUpdatePersonNotificationOnly(boolean updatePersonNotificationOnly) {
        this.updatePersonNotificationOnly = updatePersonNotificationOnly;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

}
