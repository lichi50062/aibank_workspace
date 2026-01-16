/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tfb.aibank.biz.user.repository.entities.FinancialDataOrderEntity;
import com.tfb.aibank.biz.user.repository.entities.pk.FinancialDataOrderEntityPk;

// @formatter:off
/**
 * @(#)FinancialDataOrderRepository.java
 * 
 * <p>Description:[投資理財資料排序 Repository]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/26, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Transactional
public interface FinancialDataOrderRepository extends JpaRepository<FinancialDataOrderEntity, FinancialDataOrderEntityPk> {

    List<FinancialDataOrderEntity> findByCompanyKeyAndUserKeyAndTaskId(Integer companyKxy, Integer userKxy, String taskId);

}
