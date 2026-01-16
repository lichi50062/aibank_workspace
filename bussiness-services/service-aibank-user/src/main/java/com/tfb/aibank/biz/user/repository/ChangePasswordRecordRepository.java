/**
 * 
 */
package com.tfb.aibank.biz.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.user.repository.entities.ChangePasswordRecordEntity;

//@formatter:off
/**
* @(#)ChangePasswordRecordRepository.java
* 
* <p>Description:變更密碼紀錄 Repository</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/02, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public interface ChangePasswordRecordRepository extends JpaRepository<ChangePasswordRecordEntity, Integer> {

    List<ChangePasswordRecordEntity> findByCompanyKeyAndUserKey(Integer companyKey, Integer userKey);

}