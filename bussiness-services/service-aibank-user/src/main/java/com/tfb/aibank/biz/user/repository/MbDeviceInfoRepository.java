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
import org.springframework.data.repository.query.Param;

import com.tfb.aibank.biz.user.repository.entities.MbDeviceInfoEntity;

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
     * 以公司鍵值查詢
     * 
     * @param companyKeyList
     *            公司鍵值
     * @return
     */
    List<MbDeviceInfoEntity> findByCompanyKeyIn(List<Integer> companyKeyList);

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
    @Query("select m from MbDeviceInfoEntity m where m.companyKey = ?1 and m.userKey = ?2 and m.deviceUuId = ?3 ")
    List<MbDeviceInfoEntity> findByCompanyKeyAndUserKeyAndDeviceUuId(Integer companyKey, Integer userKey, String deviceUuId);

    /**
     * 以公司鍵值和使用者鍵值查詢
     * 
     * @param deviceUuId
     *            行動裝置UUID
     * 
     * @return
     */
    @Query("select m from MbDeviceInfoEntity m where m.deviceUuId = :deviceUuId and m.enable = 1")
    MbDeviceInfoEntity findByDeviceUuid(@Param("deviceUuId") String deviceUuId);

    /**
     * 以行動裝置UUID鍵值查詢
     *
     * @param deviceUuId
     *            行動裝置UUID
     * @return
     */
    List<MbDeviceInfoEntity> findByDeviceUuId(String deviceUuId);

    /**
     * 以 Company
     * 
     * @param companyKey
     * @return
     */
    List<MbDeviceInfoEntity> findByCompanyKey(Integer companyKey);
}
