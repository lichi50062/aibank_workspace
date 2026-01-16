/**
 * 
 */
package com.tfb.aibank.biz.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.user.repository.entities.EbLoginLogBEntity;

import jakarta.transaction.Transactional;

//@formatter:off
/**
* @(#)EbLoginLogBRepository.java
* 
* <p>Description:Login Log Repository</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/01/08, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Transactional
public interface EbLoginLogBRepository extends JpaRepository<EbLoginLogBEntity, Integer> {

    EbLoginLogBEntity findByLoginLogKey(Integer loginLogKey);
}
