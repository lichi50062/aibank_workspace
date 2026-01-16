package com.tfb.aibank.biz.systemconfig.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tfb.aibank.biz.systemconfig.repository.dto.RoleTaskDto;
import com.tfb.aibank.biz.systemconfig.repository.entities.RoleTaskEntity;
import com.tfb.aibank.biz.systemconfig.repository.entities.pk.RoleTaskEntityPk;

// @formatter:off
/**
 * @(#)RoleTaskRepository.java
 * 
 * <p>Description:角色交易清單 repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/05, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Transactional(readOnly = true)
public interface RoleTaskRepository extends JpaRepository<RoleTaskEntity, RoleTaskEntityPk> {

    @Query("select new com.tfb.aibank.biz.systemconfig.repository.dto.RoleTaskDto(a.roleKey, a.roleName, b.taskId) from RoleEntity a INNER JOIN RoleTaskEntity b ON a.roleKey = b.roleKey")
    List<RoleTaskDto> fetchAllRoleTaskData();

}
