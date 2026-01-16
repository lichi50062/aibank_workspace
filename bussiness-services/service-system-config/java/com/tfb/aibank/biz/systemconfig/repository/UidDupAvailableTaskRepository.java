/**
 * 
 */
package com.tfb.aibank.biz.systemconfig.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.systemconfig.repository.entities.UidDupAvailableTaskEntity;

//@formatter:off
/**
* @(#)TxSystemMapRepository.java
* 
* <p>Description:提供有誤別碼使用者登入時，限制其可使用的功能 repository</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/05, John 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public interface UidDupAvailableTaskRepository extends JpaRepository<UidDupAvailableTaskEntity, String> {

}
