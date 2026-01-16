/**
 * 
 */
package com.tfb.aibank.biz.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.user.repository.entities.AiSsoAuthDataEntity;

//@formatter:off
/**
* @(#)AiSsoAuthDataRepository.java
* 
* <p>Description:AI Bank SSO驗證資料表  Repository</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/01/02, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public interface AiSsoAuthDataRepository extends JpaRepository<AiSsoAuthDataEntity, String> {

    AiSsoAuthDataEntity findByToken(String token);
}
