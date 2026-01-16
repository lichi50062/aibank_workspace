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
package com.tfb.aibank.biz.user.repository.piblog;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.user.repository.piblog.entities.CardCostcoDuesEntity;

// @formatter:off
/**
 * @(#)CardCostcoDuesRepository.java
 * 
 * <p>Description:[COSTCO會費代扣申請紀錄]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/07, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface CardCostcoDuesRepository extends JpaRepository<CardCostcoDuesEntity, Integer> {

}
