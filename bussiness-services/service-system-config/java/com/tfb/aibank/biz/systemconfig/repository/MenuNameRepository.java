package com.tfb.aibank.biz.systemconfig.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tfb.aibank.biz.systemconfig.repository.entities.MenuNameEntity;
import com.tfb.aibank.biz.systemconfig.repository.entities.pk.MenuNameEntityPk;

// @formatter:off
/**
 * @(#)MenuNameRepository.java
 * 
 * <p>Description:選單名稱 repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/05, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Transactional(readOnly = true)
public interface MenuNameRepository extends JpaRepository<MenuNameEntity, MenuNameEntityPk> {

}
