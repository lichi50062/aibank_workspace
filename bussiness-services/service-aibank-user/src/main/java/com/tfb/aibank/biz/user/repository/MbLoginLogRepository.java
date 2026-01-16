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

// @formatter:off
/**
 * @(#)MbLoginLogRepository.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/27, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tfb.aibank.biz.user.repository.entities.MbLoginLogEntity;

//@formatter:on
@Transactional
public interface MbLoginLogRepository extends JpaRepository<MbLoginLogEntity, Integer> {

}
