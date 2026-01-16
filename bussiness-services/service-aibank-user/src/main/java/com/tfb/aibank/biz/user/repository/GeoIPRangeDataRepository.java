package com.tfb.aibank.biz.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tfb.aibank.biz.user.repository.entities.GeoIPRangeDataEntity;
import com.tfb.aibank.biz.user.repository.entities.pk.GeoIPRangeDataEntityPk;

// @formatter:off
/**
 * @(#)GeoIPRangeDataRepository.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface GeoIPRangeDataRepository extends JpaRepository<GeoIPRangeDataEntity, GeoIPRangeDataEntityPk> {

    @Query("select r from GeoIPRangeDataEntity r where r.endIp >= :clientIp and r.startIp <= :clientIp")
    List<GeoIPRangeDataEntity> getRangeByIP(@Param("clientIp") Long clientIp);

}
