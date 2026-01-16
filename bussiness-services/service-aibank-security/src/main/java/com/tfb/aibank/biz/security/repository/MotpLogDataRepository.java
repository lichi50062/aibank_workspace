/**
 * 
 */
package com.tfb.aibank.biz.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.security.repository.entities.MotpLogDataEntity;

/*
 * @formatter:off
 * 
 * @(#)MotpLogDataRepository.java
 * 
 * <p>Description:MOTP MID認證資料 - Repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/06, HankChan   
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface MotpLogDataRepository extends JpaRepository<MotpLogDataEntity, Integer> {

}