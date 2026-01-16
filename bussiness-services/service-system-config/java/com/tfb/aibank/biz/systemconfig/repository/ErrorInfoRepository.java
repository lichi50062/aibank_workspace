package com.tfb.aibank.biz.systemconfig.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tfb.aibank.biz.systemconfig.repository.entities.ErrorInfoEntity;
import com.tfb.aibank.biz.systemconfig.repository.entities.pk.ErrorInfoEntityPk;

// @formatter:off
/**
 * @(#)ErrorInfoRepository.java
 * 
 * <p>Description:錯誤代碼客製化資料表 repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/24, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Transactional(readOnly = true)
public interface ErrorInfoRepository extends JpaRepository<ErrorInfoEntity, ErrorInfoEntityPk> {

}
