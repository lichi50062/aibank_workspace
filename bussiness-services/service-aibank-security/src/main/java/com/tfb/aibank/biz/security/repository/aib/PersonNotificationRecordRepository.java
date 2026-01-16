package com.tfb.aibank.biz.security.repository.aib;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.security.repository.aib.entities.PersonNotificationRecordEntity;

// @formatter:off
/**
 * @(#)PersonNotificationRecordRepository.java
 * 
 * <p>Description:個人化通知訊息 Repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/24, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface PersonNotificationRecordRepository extends JpaRepository<PersonNotificationRecordEntity, Integer> {

}
