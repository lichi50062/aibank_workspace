/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.tfb.aibank.biz.user.repository.entities.AibankPushCategoryEntity;
import com.tfb.aibank.biz.user.repository.entities.pk.AibankPushCategoryEntityPk;

//@formatter:off
/**
 * @(#)AibankPushCategoryRepository.java
 * 
 * <p>Description:[推播通知類別]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/11/08, Benson 
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@Transactional(readOnly = true)
public interface AibankPushCategoryRepository extends JpaRepository<AibankPushCategoryEntity, AibankPushCategoryEntityPk> {

    /**
     * 依登入者身份取得子項、細項 type:0 : 一般會員 ; type:1 : 信用卡會員
     */
    @Query("select p from AibankPushCategoryEntity p where p.enable=1 and p.type=:type and p.isParent=0 and p.category not in ('MX','DX','LR') and p.locale=:locale order by p.category, p.sort")
    List<AibankPushCategoryEntity> findByCompanyKind(@Param("type") Integer type, @Param("locale") String locale);

}
