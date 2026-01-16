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
package com.tfb.aibank.biz.security.repository.aib;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tfb.aibank.biz.security.repository.aib.entities.MbDevicePushInfoEntity;

//@formatter:off
/**
* @(#)MbDevicePushInfoRepository.java
* 
* <p>Description:[推播訂閱]</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/12/28, Horance chou 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Transactional
public interface MbDevicePushInfoRepository extends JpaRepository<MbDevicePushInfoEntity, Integer> {

    @Modifying
    @Transactional
    @Query(value = "delete from MB_DEVICE_PUSH_INFO m where m.COMPANY_KEY = ?1 and m.USER_KEY = ?2", nativeQuery = true)
    int deleteByCompanyKeyAndUserKey(Integer companyKey, Integer userKey);

    /**
     * 刪除所有特定的 UserKey
     * 
     * @param userKey
     */
    @Modifying
    @Transactional
    @Query(value = "delete from MB_DEVICE_PUSH_INFO m where m.USER_KEY = ?1", nativeQuery = true)
    void deleteByUserKey(Integer userKey);

    /**
     * 刪除所有特定的 UserKey
     * 
     * @param userKey
     */
    @Modifying
    @Transactional
    @Query(value = "delete from MB_DEVICE_PUSH_INFO m where m.DEVICE_UUID = ?1", nativeQuery = true)
    void deleteByDeviceID(String userKey);

    /**
     * 以公司鍵值和使用者鍵值查詢
     * 
     * @param companyKey
     *            公司鍵值
     * @param userKey
     *            使用者鍵值
     * @param deviceUuId
     *            行動裝置UUID
     * @return
     */
    List<MbDevicePushInfoEntity> findByCompanyKeyAndUserKeyAndDeviceUuId(Integer companyKey, Integer userKey, String deviceUuId);

    MbDevicePushInfoEntity findByCompanyKeyAndUserKey(Integer companyKey, Integer userKey);
}
