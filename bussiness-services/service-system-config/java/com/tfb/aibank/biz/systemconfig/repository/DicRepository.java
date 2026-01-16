package com.tfb.aibank.biz.systemconfig.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tfb.aibank.biz.systemconfig.repository.entities.DicEntity;
import com.tfb.aibank.biz.systemconfig.repository.entities.pk.DicEntityPk;

//@formatter:off
/**
 * @(#)DicRepository.java
 * 
 * <p>Description:AIBank Dic repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/13, John Chang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@Transactional(readOnly = true)
public interface DicRepository extends JpaRepository<DicEntity, DicEntityPk> {

}
