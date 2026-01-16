package com.tfb.aibank.biz.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tfb.aibank.biz.user.repository.entities.BiometricsBlackListEntity;
import com.tfb.aibank.biz.user.repository.entities.pk.BiometricsBlackListEntityPk;

// @formatter:off
/**
 * @(#)BiometricsBlackListRepository.java
 * 
 * <p>Description:生物辨識裝置黑名單 BIOMETRICS_BLACK_LIST Repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface BiometricsBlackListRepository extends JpaRepository<BiometricsBlackListEntity, BiometricsBlackListEntityPk> {

    @Query("select x from BiometricsBlackListEntity x where x.phoneModel = :phoneModel and (x.phoneVersion = '*' or x.phoneVersion = :phoneVersion) ")
    List<BiometricsBlackListEntity> findBy(@Param("phoneModel") String phoneModel, @Param("phoneVersion") String phoneVersion);
}
