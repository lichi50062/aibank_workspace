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
package com.tfb.aibank.biz.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.security.repository.entities.MotpDeviceInfoEntity;
import com.tfb.aibank.biz.security.services.motp.type.MotpDeviceStatus;

// @formatter:off
/**
 * @(#)MotpDeviceInfoRepository.java
 * 
 * <p>Description:MOTP裝置綁定資訊 - Repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/10, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface MotpDeviceInfoRepository extends JpaRepository<MotpDeviceInfoEntity, Integer> {

    public List<MotpDeviceInfoEntity> findByCompanyKeyAndUserKeyAndEnableAndGroup(Integer companyKey, Integer userKey, MotpDeviceStatus enable, String group);

}
