package com.tfb.aibank.biz.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.user.repository.entities.BankEmployeeEntity;

// @formatter:off
/**
 * @(#)BankEmployeeRepository.java
 * 
 * <p>Description:員工檔 Repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/04
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface BankEmployeeRepository extends JpaRepository<BankEmployeeEntity, Integer> {

    long countByEmployeeIdAndHrStatus(String employeeId, String hrStatus);

}