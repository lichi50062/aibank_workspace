/**
 * 
 */
package com.tfb.aibank.biz.systemconfig.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.systemconfig.repository.entities.CcAvalibleTaskEntity;

//@formatter:off
/**
* @(#)TxSystemMapRepository.java
* 
* <p>Description:重號名單控管機制，限制其可使用的功能 repository</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/05, John 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public interface CcAvalibleTaskRepository extends JpaRepository<CcAvalibleTaskEntity, String> {

}
