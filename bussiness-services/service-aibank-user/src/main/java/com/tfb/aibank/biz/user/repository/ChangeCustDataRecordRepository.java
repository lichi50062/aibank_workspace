/**
 * 
 */
package com.tfb.aibank.biz.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.user.repository.entities.ChangeCustDataRecordEntity;

//@formatter:off
/**
* @(#)ChangePasswordRecordRepository.java
* 
* <p>Description:變更個人資料紀錄 Repository</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/10, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public interface ChangeCustDataRecordRepository extends JpaRepository<ChangeCustDataRecordEntity, Integer> {

}