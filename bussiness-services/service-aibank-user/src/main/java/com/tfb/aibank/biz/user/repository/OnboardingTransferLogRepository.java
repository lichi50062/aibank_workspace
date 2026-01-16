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

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfb.aibank.biz.user.repository.entities.OnboardingTransferLogEntity;
import com.tfb.aibank.biz.user.repository.entities.pk.OnboardingTransferLogEntityPk;

// @formatter:off
/**
 * @(#)OnboardingTransferLogRepository.java
 * 
 * <p>Description:無痛移轉資料庫檔</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/07, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public interface OnboardingTransferLogRepository extends JpaRepository<OnboardingTransferLogEntity, OnboardingTransferLogEntityPk> {

    @Query(value = "SELECT * FROM ONBOARDING_TRANSFER_LOG WHERE COMPANY_KEY = ?1  AND USER_KEY = ?2 AND TRANS_ITEM_SUCCESS IS NOT NULL", nativeQuery = true)
    List<OnboardingTransferLogEntity> findSuccessTransfered(Integer companyKxy, Integer userKxy);
}
