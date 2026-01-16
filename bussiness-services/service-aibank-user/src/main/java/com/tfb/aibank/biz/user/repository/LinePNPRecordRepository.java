package com.tfb.aibank.biz.user.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfb.aibank.biz.user.repository.entities.LinePNPRecordEntity;

//@formatter:off
/**
 * 
 * @(#)UserLoginRepository.java
 * 
 * <p>Description:登入身分證限制</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, John Chang 
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public interface LinePNPRecordRepository extends JpaRepository<LinePNPRecordEntity, String> {

    /**
     * 以公司鍵值和使用者鍵值查詢
     * 
     * @param companyKey
     *            公司鍵值
     * @param userKey
     *            使用者鍵值
     * @return
     */
    @Query(value = "SELECT * FROM LINE_PNP_RECORD WHERE COMPANY_UID = ?1", nativeQuery = true)
    LinePNPRecordEntity findByCompanyUid(String custId);

    /**
     * 以公司鍵值和使用者鍵值查詢
     * 
     * @param companyKey
     *            公司鍵值
     * @param userKey
     *            使用者鍵值
     * @return
     */
    @Query(value = "SELECT * FROM LINE_PNP_RECORD WHERE COMPANY_UID = ?1 AND AGREE_FLAG ='N' AND UPDATE_TIME > ?2", nativeQuery = true)
    LinePNPRecordEntity find(String custId, Date fromUpdateTime);

}
