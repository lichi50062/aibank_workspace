/**
 * 
 */
package com.tfb.aibank.biz.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tfb.aibank.biz.security.repository.entities.MbGestureProfileEntity;

import jakarta.transaction.Transactional;

//@formatter:off
/**
 * 行動手勢基本資料檔
* @(#)UserLoginRepository.java
* 
* <p>Description:[程式說明]</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/02, John Chang 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public interface MbGestureProfileRepository extends JpaRepository<MbGestureProfileEntity, Integer> {

    MbGestureProfileEntity findByDeviceInfoKey(Integer deviceInfoKey);

    /**
     * 刪除所有特定的 UserKey
     * 
     * @param userKey
     */
    @Modifying
    @Transactional
    @Query(value = "delete from AIBANK_GESTURE_PROFILE m where m.USER_KEY = ?1", nativeQuery = true)
    void deleteByUserKey(Integer userKey);

    /**
     * 依照公司鍵值和使用者鍵值刪除資料
     * 
     * @param companyKey
     * @param userKey
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "delete from AIBANK_GESTURE_PROFILE m where m.COMPANY_KEY = ?1 and m.USER_KEY = ?2", nativeQuery = true)
    int deleteByCompanyKeyAndUserKey(Integer companyKey, Integer userKey);

}
