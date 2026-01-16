/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.pushlistener.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tfb.aibank.biz.pushlistener.repository.entities.CompanyEntity;

import jakarta.transaction.Transactional;

// @formatter:off
/**
 * @(#)CompanyRepository.java
 * 
 * <p>Description:公司資料 Repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/07, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Transactional
public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {

    @Query("SELECT c FROM CompanyEntity c WHERE c.companyUid = :companyUid AND c.uidDup = :uidDup AND c.companyKind = :companyKind")
    List<CompanyEntity> findByCompanyUidAndUidDupAndCompanyKind(@Param("companyUid") String custId, @Param("uidDup") String dup, @Param("companyKind") Integer companyKind);

    @Query("SELECT c FROM CompanyEntity c WHERE c.companyUid = :companyUid AND c.uidDup = :uidDup")
    List<CompanyEntity> findByCompanyUidAndUidDup(@Param("companyUid") String custId, @Param("uidDup") String dup);

    CompanyEntity findByCompanyKey(Integer companyKey);

    @Query("SELECT c.companyKey FROM CompanyEntity c  WHERE c.companyUid = :companyUid AND c.uidDup = :uidDup")
    List<Integer> findCompanyKeyByCompanyUidAndUidDup(@Param("companyUid") String custId, @Param("uidDup") String dup);

    @Query("SELECT c FROM CompanyEntity c WHERE c.companyUid = :companyUid AND c.companyKind in (1,2)")
    List<CompanyEntity> findNonCardMember(@Param("companyUid") String custId);

}
