/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tfb.aibank.biz.user.repository.entities.TwoFactorAuthEntity;

//@formatter:off
/**
 * @(#)TwoFactorAuthRepository.java
 * 
 * <p>Description:[雙重驗證記錄檔]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/19, benson 
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@Transactional
public interface TwoFactorAuthRepository extends JpaRepository<TwoFactorAuthEntity, Long> {

    
    public Optional<TwoFactorAuthEntity> findBySeqAndCompanyKeyAndUserKey(Long seq, Integer companyKey, Integer userKey);
    

}
