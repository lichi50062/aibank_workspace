/**
 * 
 */
package com.tfb.aibank.biz.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.user.repository.entities.KnowledgeCheckLogEntity;

import jakarta.transaction.Transactional;

//@formatter:off
/**
* @(#)KnowledgeCheckLogEntity.java
* 
* <p>Description:交易高齡認知檢核 Repository</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/02/20, Alex PY Li 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Transactional
public interface KnowledgeCheckLogRepository extends JpaRepository<KnowledgeCheckLogEntity, Integer> {

}
