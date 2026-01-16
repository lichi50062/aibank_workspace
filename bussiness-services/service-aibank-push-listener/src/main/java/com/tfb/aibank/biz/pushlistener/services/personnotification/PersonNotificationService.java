package com.tfb.aibank.biz.pushlistener.services.personnotification;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.biz.pushlistener.model.UpdateStatusModel;
import com.tfb.aibank.biz.pushlistener.repository.aib.PersonNotificationRecordRepository;
import com.tfb.aibank.biz.pushlistener.repository.aib.entities.PersonNotificationRecordEntity;

// @formatter:off
/**
 * @(#)PersonNotificationService.java
 * 
 * <p>Description:推播「個人化訊息」</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class PersonNotificationService {

    private static IBLog logger = IBLog.getLog(PersonNotificationService.class);

    private UpdateStatusModel model;

    private PersonNotificationRecordRepository repository;

    public PersonNotificationService(UpdateStatusModel model, PersonNotificationRecordRepository repository) {
        this.model = model;
        this.repository = repository;
    }

    public void handle() {
        // 主鍵
        Integer key = ConvertUtils.str2Int(model.getRowId().split("_")[1]);

        List<PersonNotificationRecordEntity> entities = repository.findByPersonNotificationRecordKey(key);

        if (CollectionUtils.isNotEmpty(entities)) {
            PersonNotificationRecordEntity entity = entities.get(0);
            entity.setSendStatus(StringUtils.equals(model.getStatus(), "2") ? "S" : "F"); // 非成功(S)就全部記成失敗(F)
            // 更新推播時間
            entity.setPushTime(new Date());
            repository.save(entity);
            logger.info("PERSON_NOTIFICATION_RECORD 更新成功。{}", IBUtils.attribute2Str(entity));
        }
        else {
            logger.info("依主鍵查詢資料表(PERSON_NOTIFICATION_RECORD)，查無資料。PK：{}", key);
        }
    }
}
