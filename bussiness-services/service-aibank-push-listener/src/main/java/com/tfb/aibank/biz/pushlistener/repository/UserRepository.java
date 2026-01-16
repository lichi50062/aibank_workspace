package com.tfb.aibank.biz.pushlistener.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tfb.aibank.biz.pushlistener.repository.entities.UserEntity;

import jakarta.transaction.Transactional;

// @formatter:off
/**
 * @(#)UserRepository.java
 * 
 * <p>Description:使用者資訊 Repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/18, Evan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    List<UserEntity> findByCompanyKey(Integer companyKey);

    @Query("SELECT u FROM UserEntity u, CompanyEntity c  WHERE u.companyKey = c.companyKey AND c.companyUid = :companyUid  AND  c.uidDup = :uidDup")
    List<UserEntity> findByCompanyUidAndDup(@Param("companyUid") String companyUid, @Param("uidDup") String uidDup);
}
