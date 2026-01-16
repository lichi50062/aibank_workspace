/**
 * 
 */
package com.tfb.aibank.biz.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.user.repository.entities.CardMemberRecordEntity;

//@formatter:off
/**
* @(#)CardMemberRecordRepository.java
* 
* <p>Description:信用卡專區會員資料 Repository</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/02, John Chang 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public interface CardMemberRecordRepository extends JpaRepository<CardMemberRecordEntity, Integer> {

}