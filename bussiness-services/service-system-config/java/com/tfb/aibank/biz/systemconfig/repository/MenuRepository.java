package com.tfb.aibank.biz.systemconfig.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.tfb.aibank.biz.systemconfig.repository.entities.MenuEntity;
import com.tfb.aibank.biz.systemconfig.repository.entities.pk.MenuEntityPk;

// @formatter:off
/**
 * @(#)MenuRepository.java
 * 
 * <p>Description:選單 repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/05, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<MenuEntity, MenuEntityPk> {

    public List<MenuEntity> findByCategoryInOrderByOrderNo(@Param("category") List<String> category);

}
