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

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tfb.aibank.biz.user.repository.entities.NightModeEntity;

//@formatter:off
/**
* @(#)NightModeRepository.java
* 
* <p>Description:[夜間模式設定檔]</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/22, leiley 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Transactional(readOnly = true)
public interface NightModeRepository extends JpaRepository<NightModeEntity, String> {

}
