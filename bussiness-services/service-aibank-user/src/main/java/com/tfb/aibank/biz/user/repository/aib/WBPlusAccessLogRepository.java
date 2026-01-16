package com.tfb.aibank.biz.user.repository.aib;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tfb.aibank.biz.user.repository.aib.entities.WBPlusAccessLogEntity;


// @formatter:off
/**
 * @(#)AiAccessLogRepository.java
 * 
 * <p>Description: AIBANK Access Log repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/02/25, David Huang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Transactional
public interface WBPlusAccessLogRepository extends JpaRepository<WBPlusAccessLogEntity, BigInteger> {

}
