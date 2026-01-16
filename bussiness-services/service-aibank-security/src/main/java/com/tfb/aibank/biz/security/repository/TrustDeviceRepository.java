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
package com.tfb.aibank.biz.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tfb.aibank.biz.security.repository.entities.TrustDeviceEntity;
import com.tfb.aibank.biz.security.repository.entities.pk.TrustDeviceEntityPk;

import jakarta.transaction.Transactional;

//@formatter:off
/**
 * @(#)TrustDeviceRepository.java
 * 
 * <p>Description:[信任裝置記錄檔]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/12, benson 
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public interface TrustDeviceRepository extends JpaRepository<TrustDeviceEntity, TrustDeviceEntityPk> {

    @Modifying
    @Transactional
    @Query(value = "delete from TRUST_DEVICE t where t.USER_KEY = ?1", nativeQuery = true)
    int deleteByUserKey(Integer userKey);

}
