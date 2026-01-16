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

import com.tfb.aibank.biz.pushlistener.repository.entities.MbDeviceInfoEntity;

import jakarta.transaction.Transactional;

//@formatter:off
/**
 * @(#)MbDeviceInfoRepository.java
 * 
 * <p>Description:行動裝置設定檔 Repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/12, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@Transactional
public interface MbDeviceInfoRepository extends JpaRepository<MbDeviceInfoEntity, Integer> {

    MbDeviceInfoEntity findByCompanyKeyAndUserKey(Integer companyKey, Integer userKey);

    List<MbDeviceInfoEntity> findByCompanyKey(Integer companyKey);

    @Query("SELECT m FROM MbDeviceInfoEntity m WHERE m.companyKey in :companyKxyList")
    List<MbDeviceInfoEntity> findByCompanyKeyList(@Param("companyKxyList") List<Integer> companyKxyList);

}
