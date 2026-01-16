package com.tfb.aibank.biz.pushlistener.repository.aib;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tfb.aibank.biz.pushlistener.repository.aib.entities.CustomizedNotificationRecordEntity;

// @formatter:off
/**
 * @(#)CustomizedNotificationRecordRepository.java
 * 
 * <p>Description:智能通知訊息 Repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/01, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Transactional
public interface CustomizedNotificationRecordRepository extends JpaRepository<CustomizedNotificationRecordEntity, Integer> {

    List<CustomizedNotificationRecordEntity> findByCustomizedNotificationRecordKey(Integer customizedNotificationRecordKey);

}
