package com.tfb.aibank.biz.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.user.repository.entities.CardUserProfileEntity;

// @formatter:off
/**
 * @(#)CardUserProfileRepository.java
 * 
 * <p>Description:信用卡專區會員資料 Repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface CardUserProfileRepository extends JpaRepository<CardUserProfileEntity, Integer> {

    CardUserProfileEntity findByUserKey(Integer userKey);

}
