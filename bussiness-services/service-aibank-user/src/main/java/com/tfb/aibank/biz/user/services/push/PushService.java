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
package com.tfb.aibank.biz.user.services.push;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.biz.user.repository.aib.MbDevicePushInfoRepository;
import com.tfb.aibank.biz.user.repository.aib.entities.MbDevicePushInfoEntity;

import jakarta.persistence.PersistenceException;

// @formatter:off
/**
 * @(#)PushService.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/11, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class PushService {

    private static final IBLog logger = IBLog.getLog(PushService.class);

    private MbDevicePushInfoRepository mbDevicePushInfoRepository;

    public PushService(MbDevicePushInfoRepository mbDevicePushInfoRepository) {

        this.mbDevicePushInfoRepository = mbDevicePushInfoRepository;

    }

    public boolean updatePushToken(String deviceIxd, String pushToken) throws CryptException {
        if (StringUtils.isBlank(pushToken)) {
            return false;
        }
        try {
            int updCnt = mbDevicePushInfoRepository.updateFirstDownloadRecord(pushToken, deviceIxd);

            if (logger.isDebugEnabled()) {
                logger.debug(" {} first download records updated", updCnt);
            }
        }
        catch (PersistenceException e) {
            logger.warn("error updating first download records", e);
        }

        List<MbDevicePushInfoEntity> entities = mbDevicePushInfoRepository.findByDeviceUuid(deviceIxd);

        if (CollectionUtils.isEmpty(entities)) {
            logger.warn("MB_DEVICE_PUSH_INFO 未有此 device 資料");
            return false;
        }

        for (MbDevicePushInfoEntity entity : entities) {
            entity.setPushToken(pushToken);
            entity.setUpdateTime(new Date());
        }
        mbDevicePushInfoRepository.saveAll(entities);

        return true;
    }
}
