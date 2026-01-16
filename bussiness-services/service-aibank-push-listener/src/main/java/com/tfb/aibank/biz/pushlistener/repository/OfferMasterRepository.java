package com.tfb.aibank.biz.pushlistener.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.tfb.aibank.biz.pushlistener.repository.entities.OfferMasterEntity;

// @formatter:off
/**
 * @(#)OfferMasterRepository.java
 * 
 * <p>Description:情境主檔 Repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/10, Evan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Transactional
public interface OfferMasterRepository extends JpaRepository<OfferMasterEntity, Integer> {
    /** Script */
    // @formatter:off
    String TOP1_BETWEEN_NOW =
            "SELECT om FROM OfferMasterEntity om " +
                    "WHERE om.offerId IN :offerIds " +
                    "AND om.startDate <= CURRENT_TIMESTAMP AND om.endDate >= CURRENT_TIMESTAMP " +
                    "ORDER BY om.createTime DESC";
    // @formatter:on
    @Query(value = TOP1_BETWEEN_NOW)
    List<OfferMasterEntity> queyTop1BetweenNow(@Param("offerIds") List<String> offerIdList);

 // @formatter:off
    String ALL_BETWEEN_NOW =
            "SELECT om FROM OfferMasterEntity om " +
                    "WHERE  om.startDate <= CURRENT_TIMESTAMP AND om.endDate >= CURRENT_TIMESTAMP " +
                    "ORDER BY om.updateTime DESC";
    // @formatter:on
    @Query(value = ALL_BETWEEN_NOW)
    List<OfferMasterEntity> queryAllBetweenNow();

    @Query(value = "SELECT om FROM OfferMasterEntity om WHERE om.offerId = :offerId AND om.startDate <= CURRENT_TIMESTAMP AND om.endDate >= CURRENT_TIMESTAMP ORDER BY om.updateTime DESC")
    List<OfferMasterEntity> queryByOfferId(@Param("offerId") String offerId);
}
