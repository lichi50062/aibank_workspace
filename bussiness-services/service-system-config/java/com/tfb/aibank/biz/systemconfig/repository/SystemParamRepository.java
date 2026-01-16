package com.tfb.aibank.biz.systemconfig.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tfb.aibank.biz.systemconfig.repository.entities.SystemParamEntity;
import com.tfb.aibank.biz.systemconfig.repository.entities.pk.SystemParamEntityPk;

// @formatter:off
/**
 * @(#)SystemParamRepository.java
 * 
 * <p>Description:系統參數檔 repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/05, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Transactional(readOnly = true)
public interface SystemParamRepository extends JpaRepository<SystemParamEntity, SystemParamEntityPk> {

    List<SystemParamEntity> findByCategory(String category);

}
