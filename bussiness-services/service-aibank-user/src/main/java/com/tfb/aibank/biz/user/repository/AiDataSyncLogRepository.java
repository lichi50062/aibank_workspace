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

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.user.repository.entities.AiDataSyncLogEntity;

//@formatter:off
/**
* @(#)AiDataSyncLogRepository.java
* 
* <p>Description: 資料彙整狀態歷程檔repository</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/03/06, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public interface AiDataSyncLogRepository extends JpaRepository<AiDataSyncLogEntity, Integer> {

}
