package com.tfb.aibank.biz.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.user.repository.entities.ReadChangeRightsInterestsEntity;

// @formatter:off
/**
 * @(#)ReadChangeRightsInterestsRepository.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface ReadChangeRightsInterestsRepository extends JpaRepository<ReadChangeRightsInterestsEntity, Integer> {

    List<ReadChangeRightsInterestsEntity> findByCompanyKeyAndUserKey(Integer companyKey, Integer userKey);
}
