package com.tfb.aibank.biz.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.user.repository.entities.CountryNameEntity;

// @formatter:off
/**
 * @(#)CardUserProfileRepository.java
 * 
 * <p>Description:ISO3366國名對照表  Repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/29, Alex PY Li	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface CountryNameRepository extends JpaRepository<CountryNameEntity, String> {
    List<CountryNameEntity> findByIsoCode(String isoCode);
}
