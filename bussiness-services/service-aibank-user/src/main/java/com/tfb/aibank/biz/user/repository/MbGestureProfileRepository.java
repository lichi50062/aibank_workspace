/**
 * 
 */
package com.tfb.aibank.biz.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tfb.aibank.biz.user.repository.entities.MbGestureProfileEntity;

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

    public MbGestureProfileEntity findByDeviceInfoKey(Integer deviceInfoKey);

    /**
     * 依deviceInfoKey刪除 MbGestureProfileEntity
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM AIBANK_GESTURE_PROFILE WHERE DEVICE_INFO_KEY = ?1 ", nativeQuery = true)
    void deleteByDeviceInfoKey(Integer deviceInfoKey);

}
