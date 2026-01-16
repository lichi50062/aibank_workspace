package com.tfb.aibank.biz.pushlistener.services.customizednotification;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.biz.pushlistener.model.UpdateStatusModel;
import com.tfb.aibank.biz.pushlistener.repository.aib.CustomizedNotificationRecordRepository;
import com.tfb.aibank.biz.pushlistener.repository.aib.entities.CustomizedNotificationRecordEntity;

// @formatter:off
/**
 * @(#)MarketNotificationService.java
 * 
 * <p>Description:推播「行銷類訊息」</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CustomizedNotificationService {

    private static IBLog logger = IBLog.getLog(CustomizedNotificationService.class);

    private UpdateStatusModel model;

    private CustomizedNotificationRecordRepository repository;

    public CustomizedNotificationService(UpdateStatusModel model, CustomizedNotificationRecordRepository repository) {
        this.model = model;
        this.repository = repository;
    }

    public void handle() {
        // 主鍵
        Integer key = ConvertUtils.str2Int(model.getRowId().split("_")[1]);

        List<CustomizedNotificationRecordEntity> entities = repository.findByCustomizedNotificationRecordKey(key);

        if (CollectionUtils.isNotEmpty(entities)) {
            CustomizedNotificationRecordEntity entity = entities.get(0);
            entity.setSendStatus(StringUtils.equals(model.getStatus(), "2") ? "S" : "F"); // 非成功(S)就全部記成失敗(F)
            // 更新推播時間
            entity.setPushTime(new Date());
            repository.save(entity);
            logger.info("CUSTOMIZED_NOTIFICATION_RECORD 更新成功。{}", IBUtils.attribute2Str(entity));
        }
        else {
            logger.info("依主鍵查詢資料表(CUSTOMIZED_NOTIFICATION_RECORD)，查無資料。PK：{}", key);
        }
    }
}
