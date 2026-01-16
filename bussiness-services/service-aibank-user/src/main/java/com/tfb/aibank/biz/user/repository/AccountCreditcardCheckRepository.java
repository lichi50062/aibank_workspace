/**
 * 
 */
package com.tfb.aibank.biz.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.user.repository.entities.AccountCreditcardCheckEntity;

//@formatter:off
/**
* @(#)AccountCreditcardCheckRepository.java
* 
* <p>Description:信用卡網路會員資料 Repository</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/05, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public interface AccountCreditcardCheckRepository extends JpaRepository<AccountCreditcardCheckEntity, String> {

    AccountCreditcardCheckEntity findByCompanyUid(String companyUid);

}
