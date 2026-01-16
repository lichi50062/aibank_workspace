package com.tfb.aibank.biz.systemconfig.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.systemconfig.repository.entities.TxSystemMapEntity;

// @formatter:off
/**
 * @(#)TxSystemMapRepository.java
 * 
 * <p>Description:外部系統代號與系統別對應檔 repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/05, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface TxSystemMapRepository extends JpaRepository<TxSystemMapEntity, String> {

}
