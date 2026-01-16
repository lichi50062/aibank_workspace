package com.tfb.aibank.biz.systemconfig.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tfb.aibank.biz.systemconfig.repository.entities.TaskEntity;
import com.tfb.aibank.biz.systemconfig.repository.entities.TaskPageEntity;

// @formatter:off
/**
 * @(#)TaskPageRepository.java
 * 
 * <p>Description:交易頁面 repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/15, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Transactional(readOnly = true)
public interface TaskPageRepository extends JpaRepository<TaskPageEntity, String> {

}
