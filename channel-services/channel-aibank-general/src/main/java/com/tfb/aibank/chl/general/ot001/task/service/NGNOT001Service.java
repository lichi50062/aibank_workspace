package com.tfb.aibank.chl.general.ot001.task.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.ibmb.base.model.ClientRequest;
import com.tfb.aibank.chl.component.notification.model.CreatePersonalNotificationRequest;
import com.tfb.aibank.chl.general.resource.PreferencesResource;
import com.tfb.aibank.chl.general.resource.dto.UpdatePushTokenRequest;
import com.tfb.aibank.chl.general.service.NGNService;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.type.PushCodeType;
import com.tfb.aibank.common.type.NotificationSendStatus;

//@formatter:off
/**
 * @(#)NGNOT001Service.java
 * 
 * <p>Description:CHL NGNOT001 服務類別，撰寫此交易獨有的「邏輯」程式，所有method必須宣告 void</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/17, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@Service
@Qualifier("NGNOT001Service")
public class NGNOT001Service extends NGNService {
    @Autowired
    private PreferencesResource preferencesResource;
    /**
     * 更新 Push Token to FirstDownloadRecord
     * 
     * @param rqData
     */
    @Async
    public void updateFirstDownloadRecord(ClientRequest clientRequest, String pushToken) {
        UpdatePushTokenRequest request = new UpdatePushTokenRequest();
        request.setDeviceUuid(clientRequest.getDeviceIxd());
        request.setLoginTime(new Date());
        request.setPushToken(pushToken);
        request.setPushFlag(1);
        request.setHasLogined(1);
        try {
            preferencesResource.updatePushToken(request);
        }
        catch (ServiceException ex) {
            logger.warn("更新 Push Tpken fail", ex);
        }
    }
    
}
