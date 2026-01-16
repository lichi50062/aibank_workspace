package com.tfb.aibank.biz.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.user.repository.entities.UserLoginEntity;

import jakarta.transaction.Transactional;

// @formatter:off
/**
 * @(#)UserLoginRepository.java
 * 
 * <p>Description:客戶登入紀錄</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Transactional
public interface UserLoginRepository extends JpaRepository<UserLoginEntity, Integer> {

    UserLoginEntity findByUserKey(Integer userKey);

}
