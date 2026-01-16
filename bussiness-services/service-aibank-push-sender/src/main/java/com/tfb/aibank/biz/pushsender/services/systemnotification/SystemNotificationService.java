package com.tfb.aibank.biz.pushsender.services.systemnotification;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.biz.pushsender.repository.aib.SystemNotificationRecordRepository;
import com.tfb.aibank.biz.pushsender.repository.aib.entities.SystemNotificationRecordEntity;
import com.tfb.aibank.biz.pushsender.resource.PushResource;
import com.tfb.aibank.biz.pushsender.resource.dto.ReceivePushV2Request;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.type.NotificationSendStatus;
import com.tfb.aibank.common.type.RowIdPrefixType;

import feign.FeignException;
import feign.codec.DecodeException;

// @formatter:off
/**
 * @(#)SystemNotificationService.java
 * 
 * <p>Description:推播「系統公告訊息」</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/01, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class SystemNotificationService {

    private static IBLog logger = IBLog.getLog(SystemNotificationService.class);

    private SystemNotificationRecordRepository repository;

    private PushResource pushResource;

    private SystemParamCacheManager sysParamCacheManager;

    public SystemNotificationService(SystemNotificationRecordRepository repository, PushResource pushResource, SystemParamCacheManager sysParamCacheManager) {
        this.repository = repository;
        this.pushResource = pushResource;
        this.sysParamCacheManager = sysParamCacheManager;
    }

    public void handle() {
        // 推播識別鍵值
        String pushKey = UUID.randomUUID().toString();
        // 系統時間
        Date sysDate = new Date();
        // 請取「等待發送」或「發送失敗」的訊息清單，更新狀態為「正在發送(SEND_STATUS=P)」，最多1000筆
        int resendCount = ConvertUtils.str2Int(this.sysParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "PUSH_RESEND_COUNT"), 2);
        int updateCnt = repository.updateSendStatus(sysDate, resendCount, pushKey);
        logger.info("【系統公告訊息】更新傳送狀態=P的資料筆數，共 {} 筆", updateCnt);
        // 讀取待發送訊息資料
        List<SystemNotificationRecordEntity> entities = repository.findBySendStatusAndIsPushAndPushKey(NotificationSendStatus.PROCESS.getCode(), 1, pushKey);
        logger.info("【系統公告訊息】此次處理的資料筆數，共 {} 筆", entities.size());
        String appName = this.sysParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "PUSH_APPNAME");
        if (CollectionUtils.isNotEmpty(entities)) {
            for (SystemNotificationRecordEntity entity : entities) {
                // 逐筆處理
                ReceivePushV2Request request = buildRequest(entity, appName);
                try {
                    pushResource.receive(request);
                }
                catch (FeignException e) {
                    if (!(e instanceof DecodeException)) {
                        try {
                            // 發生 FeignException 且不是 DecodeException，要回寫狀態為 F
                            repository.updateSendStatusByKey("F", entity.getSystemNotificationRecordKey());
                        }
                        catch (Exception e1) {
                            logger.error("error update send status", e1);
                        }
                    }
                    logger.error("error sending message to push server", e);
                }
            }
        }
    }

    private ReceivePushV2Request buildRequest(SystemNotificationRecordEntity entity, String appName) {

        ReceivePushV2Request request = new ReceivePushV2Request();
        request.setAppName(appName);
        request.setBroadcast(true);
        request.setPriority(entity.getPriority());
        request.setRowId(RowIdPrefixType.SYSTEM.getPrefix() + entity.getSystemNotificationRecordKey());
        request.setPushTarget(entity.getPushTopic());
        request.setPushTitle(entity.getTitleMessage());
        request.setPushContent(entity.getPushMessage());
        request.setPlatform(null);

        return request;
    }

}
