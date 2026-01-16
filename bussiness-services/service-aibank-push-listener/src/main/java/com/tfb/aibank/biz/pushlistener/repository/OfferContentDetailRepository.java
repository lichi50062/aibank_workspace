package com.tfb.aibank.biz.pushlistener.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tfb.aibank.biz.pushlistener.repository.entities.OfferContentDetailEntity;

// @formatter:off
/**
 * @(#)OfferContentDetailRepository.java
 * 
 * <p>Description:情境文案檔 Repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/10, Evan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Transactional
public interface OfferContentDetailRepository extends JpaRepository<OfferContentDetailEntity, Integer> {

    List<OfferContentDetailEntity> findByTemplateId(String templateId);
}
