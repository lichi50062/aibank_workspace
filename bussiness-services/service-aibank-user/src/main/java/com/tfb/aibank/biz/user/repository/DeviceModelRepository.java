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

import com.tfb.aibank.biz.user.repository.entities.DeviceModelEntity;

import jakarta.transaction.Transactional;

//@formatter:off
/**
* @(#)DeviceModelRepository.java
* 
* <p>Description:行動裝置型號對應表 Repository</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/20, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Transactional
public interface DeviceModelRepository extends JpaRepository<DeviceModelEntity, String> {

    List<DeviceModelEntity> findByDeviceModel(String deviceModel);

}
