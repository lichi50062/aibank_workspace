package com.tfb.aibank.biz.systemconfig.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.systemconfig.repository.entities.MenuKeywordMappingEntity;

// @formatter:off
/**
 * @(#)MenuKeywordMappingRepository.java
 * 
 * <p>Description:選單關鍵字對照表 repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/05, Marty Pan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface MenuKeywordMappingRepository extends JpaRepository<MenuKeywordMappingEntity, Integer> {

    List<MenuKeywordMappingEntity> findByMenuIdIn(List<String> menuIds);

}
