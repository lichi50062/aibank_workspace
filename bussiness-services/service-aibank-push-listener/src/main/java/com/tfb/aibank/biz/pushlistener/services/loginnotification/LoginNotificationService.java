package com.tfb.aibank.biz.pushlistener.services.loginnotification;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ConvertUtils;
import com.tfb.aibank.biz.pushlistener.model.UpdateStatusModel;
import com.tfb.aibank.biz.pushlistener.repository.aib.FirstDownloadRecordRepository;
import com.tfb.aibank.biz.pushlistener.repository.aib.entities.FirstDownloadRecordEntity;

// @formatter:off
/**
 * @(#)LoginNotificationService.java
 * 
 * <p>Description:再活躍客戶推播</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class LoginNotificationService {

    private static IBLog logger = IBLog.getLog(LoginNotificationService.class);

    private UpdateStatusModel model;

    private FirstDownloadRecordRepository repository;

    public LoginNotificationService(UpdateStatusModel model, FirstDownloadRecordRepository repository) {
        this.model = model;
        this.repository = repository;
    }

    public void handle() {
        // 主鍵
        Integer key = ConvertUtils.str2Int(model.getRowId().split("_")[1]);

        List<FirstDownloadRecordEntity> entities = repository.findByFirstDownloadRecordKey(key);

        if (CollectionUtils.isNotEmpty(entities)) {
            FirstDownloadRecordEntity entity = entities.get(0);
            entity.setPushFlag(1);
            repository.save(entity);
            logger.info("FIRST_DOWNLOAD_RECORD 更新成功");
        }
        else {
            logger.info("依主鍵查詢資料表(FIRST_DOWNLOAD_RECORD)，查無資料。PK：{}", key);
        }
    }
}
