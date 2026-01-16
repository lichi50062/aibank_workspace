/**
 * 
 */
package com.tfb.aibank.biz.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.user.repository.entities.ChangePasswordTipsEntity;

//@formatter:off
/**
* @(#)ChangePasswordTipsRepository.java
* 
* <p>Description:逾半年以上未變更網銀密碼記錄 Repository</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/02, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public interface ChangePasswordTipsRepository extends JpaRepository<ChangePasswordTipsEntity, Integer> {

    List<ChangePasswordTipsEntity> findByCompanyKeyAndUserKey(Integer companyKey, Integer userKey);
}