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
package com.tfb.aibank.biz.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tfb.aibank.biz.user.repository.entities.CompanyEntity;

import jakarta.transaction.Transactional;

// @formatter:off
/**
 * @(#)CompanyRepository.java
 * 
 * <p>Description:公司資料 Repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, John Chang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Transactional
public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {

    CompanyEntity findByCompanyKey(Integer companyKey);

    @Query("select m from CompanyEntity m where m.companyUid = :companyUid and m.uidDup = :uidDup and m.status = 1 and m.companyKind = :companyKind ")
    CompanyEntity findByUidDupAndKind(@Param("companyUid") String companyUid, @Param("uidDup") String uidDup, @Param("companyKind") int companyKind);

}
