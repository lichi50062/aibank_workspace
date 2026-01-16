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
package com.tfb.aibank.biz.user.repository.aib;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.tfb.aibank.biz.user.repository.aib.entities.MbDevicePushInfoEntity;

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

    /**
     * 以公司鍵值和使用者鍵值查詢
     * 
     * @param deviceUuId
     *            行動裝置UUID
     * 
     * @return
     */
    @Query("select m from MbDevicePushInfoEntity m where m.deviceUuId = :deviceUuId")
    List<MbDevicePushInfoEntity> findByDeviceUuid(@Param("deviceUuId") String deviceUuId);

    /**
     * 以公司鍵值和使用者鍵值查詢
     * 
     * @param companyKey
     *            公司鍵值
     * @param userKey
     *            使用者鍵值
     * @return
     */
    List<MbDevicePushInfoEntity> findByCompanyKeyAndUserKey(Integer companyKey, Integer userKey);

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
    @Query("select m from MbDevicePushInfoEntity m where m.companyKey = ?1 and m.userKey = ?2 and m.deviceUuId = ?3 ")
    List<MbDevicePushInfoEntity> findByCompanyKeyAndUserKeyAndDeviceUuId(Integer companyKey, Integer userKey, String deviceUuid);

    @Modifying
    @Query(value = "UPDATE FIRST_DOWNLOAD_RECORD set PUSH_TOKEN = ?1 where DEVICE_UUID = ?2", nativeQuery = true)
    int updateFirstDownloadRecord(String pushToken, String deviceUuixd);
}
