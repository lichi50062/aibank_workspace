package com.tfb.aibank.biz.security.repository.aib;

import com.tfb.aibank.biz.security.repository.aib.entities.PushSubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
// @formatter:off
/**
 * @(#)PushSubscriptionRepository.java
 * 
 * <p>Description:</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/5/16, billchang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public interface PushSubscriptionRepository extends JpaRepository<PushSubscriptionEntity, Integer> {

    List<PushSubscriptionEntity> findByDeviceInfoKeyAndPushCode(Integer deviceInfoKey, String pushCode);
}
