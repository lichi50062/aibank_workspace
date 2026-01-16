package com.tfb.aibank.biz.pushlistener.services.systemnotification;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.biz.pushlistener.model.UpdateStatusModel;
import com.tfb.aibank.biz.pushlistener.repository.aib.SystemNotificationRecordRepository;
import com.tfb.aibank.biz.pushlistener.repository.aib.entities.SystemNotificationRecordEntity;

// @formatter:off
/**
 * @(#)SystemNotificationService.java
 * 
 * <p>Description:推播「系統公告訊息」</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class SystemNotificationService {

    private static IBLog logger = IBLog.getLog(SystemNotificationService.class);

    private UpdateStatusModel model;

    private SystemNotificationRecordRepository repository;

    public SystemNotificationService(UpdateStatusModel model, SystemNotificationRecordRepository repository) {
        this.model = model;
        this.repository = repository;
    }

    public void handle() {
        // 主鍵
        Integer key = ConvertUtils.str2Int(model.getRowId().split("_")[1]);

        List<SystemNotificationRecordEntity> entities = repository.findBySystemNotificationRecordKey(key);

        if (CollectionUtils.isNotEmpty(entities)) {
            SystemNotificationRecordEntity entity = entities.get(0);
            entity.setSendStatus(StringUtils.equals(model.getStatus(), "2") ? "S" : "F"); // 非成功(S)就全部記成失敗(F)
            repository.save(entity);
            logger.info("SYSTEM_NOTIFICATION_RECORD 更新成功。{}", IBUtils.attribute2Str(entity));
        }
        else {
            logger.info("依主鍵查詢資料表(SYSTEM_NOTIFICATION_RECORD)，查無資料。PK：{}", key);
        }
    }
}
