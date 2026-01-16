package com.tfb.aibank.biz.pushlistener.repository.aib;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.pushlistener.repository.aib.entities.FirstDownloadRecordEntity;

// @formatter:off
/**
 * @(#)FirstDownloadRecordRepository.java
 * 
 * <p>Description:首次開啟APP紀錄 Repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/24, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface FirstDownloadRecordRepository extends JpaRepository<FirstDownloadRecordEntity, Integer> {

    List<FirstDownloadRecordEntity> findByFirstDownloadRecordKey(Integer firstDownloadRecordKey);
}
