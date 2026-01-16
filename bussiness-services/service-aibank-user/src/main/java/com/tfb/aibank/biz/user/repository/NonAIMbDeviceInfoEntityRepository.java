/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.user.repository.entities.NonAIMbDeviceInfoEntity;

// @formatter:off
/**
 * @(#)NonAIMbDeviceInfoEntityRepository.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/29, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface NonAIMbDeviceInfoEntityRepository extends JpaRepository<NonAIMbDeviceInfoEntity, Integer> {

    List<NonAIMbDeviceInfoEntity> findByCompanyKeyAndUserKey(Integer companyKey, Integer userKey);
}
