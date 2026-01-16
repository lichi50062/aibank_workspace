package com.tfb.aibank.biz.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.user.repository.entities.GeoIPLocationDataEntity;

// @formatter:off
/**
 * @(#)GeoIPLocationDataRepository.java
 * 
 * <p>Description:GEO IP LITE CITY IP位置城市對應表 Repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface GeoIPLocationDataRepository extends JpaRepository<GeoIPLocationDataEntity, Integer> {

    GeoIPLocationDataEntity findByLocationId(Integer locationId);
}
