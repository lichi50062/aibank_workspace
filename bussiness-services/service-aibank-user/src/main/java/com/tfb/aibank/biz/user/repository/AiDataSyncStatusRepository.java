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

import com.tfb.aibank.biz.user.repository.entities.AiDataSyncStatusEntity;

//@formatter:off
/**
* @(#)AiDataSyncRepository.java
* 
* <p>Description:AI_DATA_SYNC repository</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/02/29, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public interface AiDataSyncStatusRepository extends JpaRepository<AiDataSyncStatusEntity, Integer> {

    AiDataSyncStatusEntity findByCompanyKeyAndUserKey(Integer companyKey, Integer userKey);
}
