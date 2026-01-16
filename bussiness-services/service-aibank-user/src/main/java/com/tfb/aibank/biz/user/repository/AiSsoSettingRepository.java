/**
 * 
 */
package com.tfb.aibank.biz.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.user.repository.entities.AiSsoSettingEntity;

//@formatter:off
/**
* @(#)AiSsoSettingRepository.java
* 
* <p>Description:AI Bank身分認證平台串接設定 Repository</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/01/02, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public interface AiSsoSettingRepository extends JpaRepository<AiSsoSettingEntity, String> {

    AiSsoSettingEntity findByChannelKey(String channelKey);

    List<AiSsoSettingEntity> findByChannelId(String channelId);
}
