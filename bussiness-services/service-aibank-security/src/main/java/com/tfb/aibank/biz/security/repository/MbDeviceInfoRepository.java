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
package com.tfb.aibank.biz.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tfb.aibank.biz.security.repository.entities.MbDeviceInfoEntity;

import jakarta.transaction.Transactional;

//@formatter:off
/**
* @(#)MbDeviceInfoRepository.java
* 
* <p>Description:行動裝置設定檔 Repository</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22, Edward Tien
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Transactional
public interface MbDeviceInfoRepository extends JpaRepository<MbDeviceInfoEntity, Integer> {

    /**
     * 以公司鍵值和使用者鍵值查詢
     * 
     * @param companyKey
     *            公司鍵值
     * @param userKey
     *            使用者鍵值
     * @return
     */
    List<MbDeviceInfoEntity> findByCompanyKeyAndUserKey(Integer companyKey, Integer userKey);

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
    List<MbDeviceInfoEntity> findByCompanyKeyAndUserKeyAndDeviceUuId(Integer companyKey, Integer userKey, String deviceUuId);

    /**
     * 刪除所有特定的 UserKey
     * 
     * @param userKey
     */
    @Modifying
    @Transactional
    @Query(value = "delete from AIBANK_MB_DEVICE_INFO m where m.USER_KEY = ?1", nativeQuery = true)
    void deleteByUserKey(Integer userKey);

    /**
     * 刪除所有特定的 UserKey
     * 
     * @param userKey
     */
    @Modifying
    @Transactional
    @Query(value = "delete from AIBANK_MB_DEVICE_INFO m where m.DEVICE_UUID = ?1", nativeQuery = true)
    void deleteByDeviceID(String userKey);

    /**
     * 依照公司鍵值和使用者鍵值刪除資料
     * 
     * @param companyKey
     * @param userKey
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "delete from AIBANK_MB_DEVICE_INFO m where m.COMPANY_KEY = ?1 and m.USER_KEY = ?2", nativeQuery = true)
    int deleteByCompanyKeyAndUserKey(Integer companyKey, Integer userKey);

    /**
     * 依照公司鍵值和使用者鍵值停用快登
     * 
     * @param companyKey
     * @param userKey
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "update AIBANK_MB_DEVICE_INFO m  set LOGIN_FLAG = 0 where m.COMPANY_KEY = ?1 and m.USER_KEY = ?2", nativeQuery = true)
    int disableFastLoginByCompanyKeyAndUserKey(Integer companyKey, Integer userKey);

}
