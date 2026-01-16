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
package com.tfb.aibank.biz.user.services.homepagecard;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.biz.component.identity.IdentityData;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.user.repository.HomePageCardUserRepository;
import com.tfb.aibank.biz.user.repository.MbDeviceInfoRepository;
import com.tfb.aibank.biz.user.repository.entities.HomePageCardUserEntity;
import com.tfb.aibank.biz.user.repository.entities.MbDeviceInfoEntity;
import com.tfb.aibank.biz.user.services.homepagecard.model.RetrieveUserHomePageCardResponse;
import com.tfb.aibank.biz.user.services.homepagecard.model.UpdateUserHomePageCardRequest;
import com.tfb.aibank.biz.user.services.homepagecard.model.UserHomePageCardModel;

//@formatter:off
/**
* @(#)HomePageCardService.java
* 
* <p>Description:使用者首頁牌卡服務</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class HomePageCardService {

    private static final IBLog logger = IBLog.getLog(HomePageCardService.class);

    /** 取得使用者服務 */
    private IdentityService identityService;

    /** 使用者首頁牌卡設定 Repository */
    private HomePageCardUserRepository homePageCardUserRepository;

    /** 行動裝置設定檔 Repository */
    private MbDeviceInfoRepository mbDeviceInfoRepository;

    public HomePageCardService(IdentityService identityService, HomePageCardUserRepository homePageCardUserRepository, MbDeviceInfoRepository mbDeviceInfoRepository) {
        this.identityService = identityService;
        this.homePageCardUserRepository = homePageCardUserRepository;
        this.mbDeviceInfoRepository = mbDeviceInfoRepository;
    }

    /**
     * 取得使用者首頁牌卡設定
     *
     * @param userId
     * @param userUuid
     * @param uidDup
     * @param companyKind
     * @return
     * @throws ActionException
     */
    public RetrieveUserHomePageCardResponse retrieveUserHomepageCard(String userId, String userUuid, String uidDup, Integer companyKind) throws ActionException {

        RetrieveUserHomePageCardResponse response = new RetrieveUserHomePageCardResponse();
        List<UserHomePageCardModel> cards = new ArrayList<>();

        IdentityData user = getUser(userId, userUuid, uidDup, companyKind);
        List<HomePageCardUserEntity> entityList = homePageCardUserRepository.findByCompanyKeyAndUserKeyOrderByCardSortAsc(user.getCompanyKey(), user.getUserKey());
        if (CollectionUtils.isNotEmpty(entityList)) {
            for (HomePageCardUserEntity entity : entityList) {
                UserHomePageCardModel card = new UserHomePageCardModel();
                convertEntityToModel(entity, card);
                cards.add(card);
            }
        }
        response.setCards(cards);

        return response;
    }

    /**
     * 取得使用者首頁牌卡設定(未登入以綁定裝置查詢)
     * 
     * @param deviceUuid
     * @return
     * @throws ActionException
     */
    public RetrieveUserHomePageCardResponse retrieveUserHomepageCardByDevice(String deviceUuid) throws ActionException {

        RetrieveUserHomePageCardResponse response = new RetrieveUserHomePageCardResponse();
        List<UserHomePageCardModel> cards = new ArrayList<>();

        List<MbDeviceInfoEntity> deviceEntityList = mbDeviceInfoRepository.findByDeviceUuId(deviceUuid);
        if (CollectionUtils.isNotEmpty(deviceEntityList)) {
            if (deviceEntityList.size() > 1) {
                // 超過一筆資料
                throw ExceptionUtils.getActionException("MB_DEVICE_INFO資料有誤", CommonErrorCode.DATA_OWNERSHIP_EXCEPTION);
            }
            // 只能有一筆資料
            MbDeviceInfoEntity deviceEntity = deviceEntityList.get(0);
            if (deviceEntity.getQsearchFlag() != null && deviceEntity.getQsearchFlag().intValue() == 1) {
                response.setBind(true);
                List<HomePageCardUserEntity> entityList = homePageCardUserRepository.findByCompanyKeyAndUserKeyOrderByCardSortAsc(deviceEntity.getCompanyKey(), deviceEntity.getUserKey());
                if (CollectionUtils.isNotEmpty(entityList)) {
                    for (HomePageCardUserEntity entity : entityList) {
                        UserHomePageCardModel card = new UserHomePageCardModel();
                        convertEntityToModel(entity, card);
                        cards.add(card);
                    }
                }
            }
            else {
                // 裝置已綁定，但未開啟免登速查
            }
        }
        else {
            // 此裝置尚未綁定
        }

        response.setCards(cards);

        return response;
    }

    /**
     * 更新使用者首頁牌卡設定
     * 
     * @param userId
     * @param userUuid
     * @param companyKind
     * @param request
     * @return
     * @throws ActionException
     */
    public Boolean updateUserHomePageCard(String userId, String userUuid, String uidDup, Integer companyKind, UpdateUserHomePageCardRequest request) throws ActionException {

        IdentityData user = getUser(userId, userUuid, uidDup, companyKind);

        // 更新使用者首頁牌卡設定 - DB
        deleteAndInsert(user.getCompanyKey(), user.getUserKey(), request.getCardIds());

        return Boolean.TRUE;
    }

    /**
     * 更新使用者首頁牌卡設定(未登入以綁定裝置更新)
     * 
     * @param deviceUuid
     * @param request
     * @return
     * @throws ActionException
     */
    public Boolean updateUserHomePageCardByDevice(String deviceUuid, UpdateUserHomePageCardRequest request) throws ActionException {

        List<MbDeviceInfoEntity> deviceEntityList = mbDeviceInfoRepository.findByDeviceUuId(deviceUuid);
        if (CollectionUtils.isNotEmpty(deviceEntityList)) {
            if (deviceEntityList.size() > 1) {
                // 超過一筆資料
				throw ExceptionUtils.getActionException("MB_DEVICE_INFO資料有誤", CommonErrorCode.DATA_NOT_FOUND);
            }
            // 只能有一筆資料
            MbDeviceInfoEntity deviceEntity = deviceEntityList.get(0);
            if (deviceEntity.getQsearchFlag() != null && deviceEntity.getQsearchFlag().intValue() == 1) {

                // 更新使用者首頁牌卡設定 - DB
                deleteAndInsert(deviceEntity.getCompanyKey(), deviceEntity.getUserKey(), request.getCardIds());
            }
            else {
                // 裝置已綁定，但未開啟免登速查，無法更新
                throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
            }
        }
        else {
            // 此裝置尚未綁定，無法更新
            throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
        }

        return Boolean.TRUE;
    }

    /**
     * 更新使用者首頁牌卡設定 - DB
     * 
     * @param companyKey
     * @param userKey
     * @param cardIds
     */
    public void deleteAndInsert(Integer companyKey, Integer userKey, List<Integer> cardIds) {

        // 刪除目前設定
        homePageCardUserRepository.deleteByCompanyKeyAndUserKey(companyKey, userKey);

        // 寫入新的設定
        if (CollectionUtils.isNotEmpty(cardIds)) {
            int cardSort = 1;
            List<HomePageCardUserEntity> entityList = new ArrayList<>();
            for (Integer cardId : cardIds) {
                HomePageCardUserEntity entity = new HomePageCardUserEntity();
                entity.setCompanyKey(companyKey);
                entity.setUserKey(userKey);
                entity.setCardId(cardId);
                entity.setCardSort(cardSort++);
                entity.setCreateTime(new Date());
                entity.setUpdateTime(new Date());
                entityList.add(entity);
            }
            homePageCardUserRepository.saveAll(entityList);
        }

    }

    /**
     * 轉換Entity to Model
     * 
     * @param entity
     * @param model
     */
    private void convertEntityToModel(HomePageCardUserEntity entity, UserHomePageCardModel model) {
        model.setCompanyKey(entity.getCompanyKey());
        model.setUserKey(entity.getUserKey());
        model.setCardId(entity.getCardId());
        model.setCardSort(entity.getCardSort());
        model.setCreateTime(entity.getCreateTime());
        model.setUpdateTime(entity.getUpdateTime());
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
    private IdentityData getUser(String userId, String userUuid, String uidDup, Integer companyKind) throws ActionException {
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

}
