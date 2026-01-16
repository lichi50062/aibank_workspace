package com.tfb.aibank.biz.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tfb.aibank.biz.user.repository.entities.AiBankCountryNameEntity;
import com.tfb.aibank.biz.user.repository.entities.pk.AiBankCountryNameEntityPk;

/**
 * @(#)AIBankCountryNameRepository.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/20, bensonlin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */

@Repository
public interface AiBankCountryNameRepository extends JpaRepository<AiBankCountryNameEntity, AiBankCountryNameEntityPk> {
    
    
    @Query("SELECT a FROM AiBankCountryNameEntity a WHERE a.countryCode = :countryCode")
    List<AiBankCountryNameEntity> findByCountryName(@Param("countryCode")String countryCode);


}
