package com.tfb.aibank.biz.user.repository.aib;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.user.repository.aib.entities.PushSubscriptionEntity;

// @formatter:off
/**
 * @(#)PushSubscriptionRepository.java
 * 
 * <p>Description:推播訂閱 repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/07, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface PushSubscriptionRepository extends JpaRepository<PushSubscriptionEntity, Integer> {

    List<PushSubscriptionEntity> findByDeviceInfoKey(Integer deviceInfoKey);

    List<PushSubscriptionEntity> findByDeviceInfoKeyAndPushCode(Integer deviceInfoKey, String pushCode);

}
