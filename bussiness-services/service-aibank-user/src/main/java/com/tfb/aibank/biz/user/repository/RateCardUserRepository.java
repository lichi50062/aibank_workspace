package com.tfb.aibank.biz.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.tfb.aibank.biz.user.repository.entities.RateCardUserEntity;

@Transactional
public interface RateCardUserRepository extends JpaRepository<RateCardUserEntity, Integer>, JpaSpecificationExecutor<RateCardUserEntity> {

    /**
     * 以公司鍵值和使用者鍵值查詢，並依照CurrencySort由小到大排序
     *
     * @param companyKey
     *            公司鍵值
     * @param userKey
     *            使用者鍵值
     * @return
     */
    List<RateCardUserEntity> findByCompanyKeyAndUserKeyOrderByCurrencySortAsc(Integer companyKey, Integer userKey);

}