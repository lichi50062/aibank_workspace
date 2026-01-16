package com.tfb.aibank.biz.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.user.repository.entities.W8benInfoLogEntity;

//@formatter:off
/**
* @(#)W8benInfoLogEntity.java
* 
* <p>Description: W-8BEN簽署畫面資料檔 Repository</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/02/15, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public interface W8benInfoLogRepository extends JpaRepository<W8benInfoLogEntity, Integer> {

}
