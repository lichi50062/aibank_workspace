package com.tfb.aibank.biz.user.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.user.repository.entities.ExchangeRateHistoryEntity;

// @formatter:off
/**
 * @(#)ExchangeRateHistoryRepository.java
 * 
 * <p>Description:歷史匯率檔 Repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface ExchangeRateHistoryRepository extends JpaRepository<ExchangeRateHistoryEntity, Integer> {

    List<ExchangeRateHistoryEntity> findByTxTimeBetween(Date startDate, Date endDate);
}
