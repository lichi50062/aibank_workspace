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

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.user.repository.entities.TrustDeviceEntity;
import com.tfb.aibank.biz.user.repository.entities.pk.TrustDeviceEntityPk;

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

    /**
     * 依登入者取得信任裝置記錄
     */
    List<TrustDeviceEntity> findByCompanyKeyAndUserKeyAndDeviceId(Integer companyKxy, Integer userKxy, String deviceIxd);

    /**
     * 查詢筆數
     * 
     * @param companyKxy
     * @param userKxy
     * @return
     */
    int countByCompanyKeyAndUserKey(Integer companyKxy, Integer userKxy);

}
