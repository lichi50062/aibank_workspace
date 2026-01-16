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

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.security.repository.entities.MotpTransDataEntity;

// @formatter:off
/**
 * @(#)MotpTransDataRepository.java
 * 
 * <p>Description:MOTP交易認證資訊 - Repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/15, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface MotpTransDataRepository extends JpaRepository<MotpTransDataEntity, Integer> {

}
