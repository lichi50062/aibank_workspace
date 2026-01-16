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
package com.tfb.aibank.biz.user.services.ratecarduser;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.biz.component.identity.IdentityData;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.user.repository.RateCardUserRepository;
import com.tfb.aibank.biz.user.repository.entities.RateCardUserEntity;
import com.tfb.aibank.biz.user.services.ratecarduser.model.RateCardUserModel;

//@formatter:off
/**
* @(#)RateCardService.java
* 
* <p>Description:使用者匯率牌卡服務</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22,
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class RateCardUserService {

    private static final IBLog logger = IBLog.getLog(RateCardUserService.class);

    /**
     * 取得使用者服務
     */
    private IdentityService identityService;

    /**
     * 使用者首頁牌卡設定 Repository
     */
    private RateCardUserRepository rateCardUserRepository;

    public RateCardUserService(IdentityService identityService, RateCardUserRepository rateCardUserRepository) {
        this.identityService = identityService;
        this.rateCardUserRepository = rateCardUserRepository;
    }

    /**
     * 取得使用者匯率幣別牌卡設定
     */
    public List<RateCardUserModel> retrieveUserRateCards(String userId, String userUuid, Integer companyKind, String uidDup) throws ActionException {
        logger.debug("== retrieveUserRateCards ==, userId[{}], userUuid[{}], companyKind[{}]", userId, userUuid, companyKind);
        List<RateCardUserModel> cards = new ArrayList<>();
        IdentityData user = getUser(userId, uidDup, userUuid, companyKind);
        logger.debug("== retrieveUserRateCards ==, IdentityData: {}", user);
        List<RateCardUserEntity> entityList = rateCardUserRepository.findByCompanyKeyAndUserKeyOrderByCurrencySortAsc(user.getCompanyKey(), user.getUserKey());
        if (CollectionUtils.isNotEmpty(entityList)) {
            for (RateCardUserEntity entity : entityList) {
                cards.add(convertEntityToModel(entity));
            }
        }
        return cards;
    }

    /**
     * 轉換Entity to Model
     *
     * @param entity
     */
    private RateCardUserModel convertEntityToModel(RateCardUserEntity entity) {
        RateCardUserModel model = new RateCardUserModel();
        model.setRateCardKey(entity.getRateCardKey());
        model.setCompanyKey(entity.getCompanyKey());
        model.setUserKey(entity.getUserKey());
        model.setCurrencyEname1(entity.getCurrencyEname1());
        model.setCurrencyEname2(entity.getCurrencyEname2());
        model.setCurrencySort(entity.getCurrencySort());
        model.setCreateTime(entity.getCreateTime());
        model.setUpdateTime(entity.getUpdateTime());
        return model;
    }

    /**
     * Model轉換Entity
     * 
     * @param entity
     */
    private RateCardUserEntity convertModelToEntity(RateCardUserModel model, Integer companyKey, Integer userKey) {
        RateCardUserEntity entity = new RateCardUserEntity();
        entity.setRateCardKey(model.getRateCardKey());
        entity.setCompanyKey(companyKey);
        entity.setUserKey(userKey);
        entity.setCurrencyEname1(model.getCurrencyEname1());
        entity.setCurrencyEname2(model.getCurrencyEname2());
        entity.setCurrencySort(model.getCurrencySort());
        entity.setCreateTime(model.getCreateTime());
        entity.setUpdateTime(model.getUpdateTime());
        return entity;
    }

    /**
     * 取得使用者資料
     *
     * @param userId
     * @param userUuid
     * @param companyKind
     * @return
     * @throws ActionException
     */
    private IdentityData getUser(String userId, String uidDup, String userUuid, Integer companyKind) throws ActionException {
        try {
            IdentityData identityData = identityService.query(userId, uidDup, userUuid, companyKind);
            if (identityData != null && identityData.isAliveUser()) {
                return identityData;
            }
        }
        catch (CryptException e) {
            logger.error(e.getMessage(), e);
        }
        throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
    }

    /**
     * 更新rateCardUser
     * 
     * @param model
     * @return
     * @throws ActionException
     */
    public Boolean saveRateCardUser(String custIxd, String userIxd, Integer companyKind, String uidDup, List<RateCardUserModel> models) throws ActionException {
        IdentityData user = getUser(custIxd, uidDup, userIxd, companyKind);
        List<RateCardUserEntity> rateCardUserEntity = models.stream().map(entity -> convertModelToEntity(entity, user.getCompanyKey(), user.getUserKey())).toList();

        rateCardUserRepository.saveAll(rateCardUserEntity);
        return true;
    }

    /**
     * 刪除 rateCardUser
     * 
     * @param model
     * @return
     * @throws ActionException
     */
    public Boolean deleteAllRateCardUserByUser(String custId, String userId, Integer companyKind, String uidDup) throws ActionException {
        IdentityData user = getUser(custId, uidDup, userId, companyKind);

        List<RateCardUserEntity> entityLists = rateCardUserRepository.findByCompanyKeyAndUserKeyOrderByCurrencySortAsc(user.getCompanyKey(), user.getUserKey());
        rateCardUserRepository.deleteAll(entityLists);
        return true;
    }

}
