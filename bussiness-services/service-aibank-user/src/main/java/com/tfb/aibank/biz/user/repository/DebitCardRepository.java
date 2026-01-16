package com.tfb.aibank.biz.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.user.repository.entities.DebitCardEntity;
import com.tfb.aibank.biz.user.repository.entities.pk.DebitCardEntityPk;

// @formatter:off
/**
 * @(#)DebitCardRepository.java
 * 
 * <p>Description:簽帳卡卡片訊息 Repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/04, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface DebitCardRepository extends JpaRepository<DebitCardEntity, DebitCardEntityPk> {

}
