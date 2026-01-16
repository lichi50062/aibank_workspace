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
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.tfb.aibank.biz.user.repository.entities.HomePageCardUserEntity;
import com.tfb.aibank.biz.user.repository.entities.pk.HomePageCardUserPk;

//@formatter:off
/**
* @(#)HomePageCardUserRepository.java
* 
* <p>Description:使用者首頁牌卡設定 - Repository</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22, HankChans
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public interface HomePageCardUserRepository extends JpaRepository<HomePageCardUserEntity, HomePageCardUserPk> {

    /**
     * 以公司鍵值和使用者鍵值查詢，並依照CardSort由小到大排序
     * 
     * @param companyKey
     *            公司鍵值
     * @param userKey
     *            使用者鍵值
     * @return
     */
    List<HomePageCardUserEntity> findByCompanyKeyAndUserKeyOrderByCardSortAsc(Integer companyKey, Integer userKey);

    /**
     * 以公司鍵值和使用者鍵值刪除
     * 
     * @param companyKey
     *            公司鍵值
     * @param userKey
     *            使用者鍵值
     * @return
     */
    @Modifying
    @Transactional
    int deleteByCompanyKeyAndUserKey(Integer companyKey, Integer userKey);

}
