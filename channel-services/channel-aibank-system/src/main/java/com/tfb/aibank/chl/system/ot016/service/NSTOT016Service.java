/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.chl.system.ot016.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfb.aibank.chl.system.ot016.model.NSTOT016Output;
import com.tfb.aibank.chl.system.resource.NotificationResource;
import com.tfb.aibank.chl.system.resource.dto.AppInfo;
import com.tfb.aibank.chl.system.resource.dto.UpdateTwoFactorAuthRequest;
import com.tfb.aibank.chl.system.service.NSTService;

// @formatter:off
/**
 * @(#)NSTOT016Service.java
 * 
 * <p>Description:CHL NSTOT016 服務類別，撰寫此交易獨有的「邏輯」程式，所有method必須宣告 void</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/27, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class NSTOT016Service extends NSTService {

    @Autowired
    private NotificationResource notificationResource;

    /**
     * 發送查詢 or 更新API (根據updateAction決定)
     *
     * @param updateAction
     * @param personNotificationRecordKey
     * @param appInfo
     * @param trustDevice
     * @param output
     */
    public void updateTwoFactorAuth(String updateAction, Integer personNotificationRecordKey, AppInfo appInfo, boolean trustDevice, NSTOT016Output output, Locale locale) {
        UpdateTwoFactorAuthRequest request = new UpdateTwoFactorAuthRequest();
        // appInfo 帶入Local
        request.setUpdateAction(updateAction);
        request.setPersonNotificationRecordKey(personNotificationRecordKey);
        request.setAppInfo(appInfo);
        request.setTrustDevice(trustDevice);
        request.setLocale(locale.toString());
        Boolean updated = notificationResource.twoFactorAuthUpdate(request);
        output.setUpdateSuccess(updated);
    }
}
