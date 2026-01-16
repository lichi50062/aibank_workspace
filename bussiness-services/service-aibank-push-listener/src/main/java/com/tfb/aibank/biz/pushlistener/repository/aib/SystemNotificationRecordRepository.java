package com.tfb.aibank.biz.pushlistener.repository.aib;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.pushlistener.repository.aib.entities.SystemNotificationRecordEntity;

// @formatter:off
/**
 * @(#)SystemNotificationRecordRepository.java
 * 
 * <p>Description:系統通知訊息 Repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface SystemNotificationRecordRepository extends JpaRepository<SystemNotificationRecordEntity, Integer> {

    List<SystemNotificationRecordEntity> findBySystemNotificationRecordKey(Integer systemNotificationRecordKey);

}
