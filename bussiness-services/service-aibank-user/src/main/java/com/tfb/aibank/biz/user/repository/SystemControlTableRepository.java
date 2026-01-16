package com.tfb.aibank.biz.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfb.aibank.biz.user.repository.entities.SystemControlEntity;
import com.tfb.aibank.biz.user.repository.entities.pk.SystemControlEntityPk;

/**
 * @(#)SystemControlTableRepository.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/20, bensonlin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Repository
public interface SystemControlTableRepository extends JpaRepository<SystemControlEntity, SystemControlEntityPk> {

}
