package com.tfb.aibank.biz.pushlistener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tfb.aibank.biz.pushlistener.repository.entities.OfferNotificationInfoEntity;
import com.tfb.aibank.biz.pushlistener.repository.entities.pk.OfferNotificationInfoEntityPk;

// @formatter:off
/**
 * @(#)OfferNotificationInfoRepository.java
 * 
 * <p>Description:情境推播設定資料 Repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/18, Evan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Transactional
public interface OfferNotificationInfoRepository extends JpaRepository<OfferNotificationInfoEntity, OfferNotificationInfoEntityPk> {

    OfferNotificationInfoEntity findByOfferMasterKeyAndTemplateId(Integer offerMasterKey, String templateId);
}
