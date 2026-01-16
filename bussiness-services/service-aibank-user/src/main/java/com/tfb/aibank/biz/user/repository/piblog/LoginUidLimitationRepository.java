package com.tfb.aibank.biz.user.repository.piblog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tfb.aibank.biz.user.repository.piblog.entities.LoginUidLimitationEntity;

// @formatter:off
/**
 * @(#)LoginUidLimitationRepository.java
 * 
 * <p>Description:登入身分證限制</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/03, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface LoginUidLimitationRepository extends JpaRepository<LoginUidLimitationEntity, Integer> {

    LoginUidLimitationEntity findByChannelAndHashUid(String channel, String hashUid);

    @Query("select m from LoginUidLimitationEntity m where m.channel = :channel and m.deviceId = :deviceId ")
    LoginUidLimitationEntity findByChannelAndDeviceId(@Param("channel") String channel, @Param("deviceId") String deviceId);

}
