package com.tfb.aibank.biz.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tfb.aibank.biz.user.repository.entities.UserEntity;

import jakarta.transaction.Transactional;

// @formatter:off
/**
 * @(#)UserRepository.java
 * 
 * <p>Description:使用者資訊 Repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByUserKey(Integer userKey);

    @Query("SELECT u FROM UserEntity u, CompanyEntity c WHERE u.companyKey = c.companyKey AND c.companyUid = :companyUid AND c.status > 0 AND u.userUuid = :userUuid AND u.status >= 0 and c.companyKind in (:companyKind)")
    List<UserEntity> findByUidUUidCompany(@Param("companyUid") String companyUid, @Param("userUuid") String userUuid, @Param("companyKind") List<String> companyKind);

    UserEntity findByCompanyKeyAndUserKey(Integer companyKey, Integer userKey);

    List<UserEntity> findByCompanyKey(Integer companyKey);

    @Query("SELECT u FROM UserEntity u, CompanyEntity c WHERE u.companyKey = c.companyKey AND c.companyUid = :companyUid AND c.status > 0 AND u.userUuid = :userUuid AND u.status >= 0 and c.companyKind = :companyKind")
    List<UserEntity> findByUidUUidCompany(@Param("companyUid") String companyUid, @Param("userUuid") String userUuid, @Param("companyKind") int companyKind);

    @Query("SELECT u FROM UserEntity u, CompanyEntity c WHERE u.companyKey = c.companyKey AND c.companyUid = :companyUid AND u.status > 0 AND (c.companyKind = 3 or c.companyKind = 4) AND u.userUuid = :userUuid")
    List<UserEntity> findCardUser(@Param("companyUid") String companyUid, @Param("userUuid") String userUuid);

    @Query("SELECT u FROM UserEntity u, CompanyEntity c  WHERE u.companyKey = c.companyKey AND c.companyUid = :companyUid AND c.status > 0 AND u.userUuid = :userUuid AND u.status >= 0 AND c.uidDup = :uidDup and c.companyKind = :companyKind")
    UserEntity findByUidAndidDupAndUuid(@Param("companyUid") String companyUid, @Param("uidDup") String uidDup, @Param("userUuid") String userUuid, @Param("companyKind") int companyKind);
}
