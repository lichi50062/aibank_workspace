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
package com.tfb.aibank.biz.user.services.custdatarecord;

import com.ibm.tw.commons.exception.CryptException;
import com.tfb.aibank.biz.component.identity.IdentityData;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.user.repository.ChangeCustDataRecordRepository;
import com.tfb.aibank.biz.user.repository.entities.ChangeCustDataRecordEntity;
import com.tfb.aibank.biz.user.services.custdatarecord.model.ChangeCustDataRecordModel;

// @formatter:off
/**
 * @(#)CustDataRecord.java
 * 
 * <p>Description:個人資料紀錄</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/10, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CustDataRecordService {

    private ChangeCustDataRecordRepository changeCustDataRecordRepository;

    private IdentityService identityService;

    public CustDataRecordService(ChangeCustDataRecordRepository changeCustDataRecordRepository, IdentityService identityService) {

        this.changeCustDataRecordRepository = changeCustDataRecordRepository;
        this.identityService = identityService;
    }

    public ChangeCustDataRecordModel saveChangeCustDataRecord(String custId, String udiDup, String userId, Integer companyKind, ChangeCustDataRecordModel model) throws CryptException {
        IdentityData identityData = identityService.query(custId, udiDup, userId, companyKind);
        model.setCompanyKey(identityData.getCompanyKey());
        model.setUserKey(identityData.getUserKey());
        model.setUserId(userId);
        ChangeCustDataRecordEntity entity = convertCustDataRecordEntity(model);
        ChangeCustDataRecordEntity newEntity = this.changeCustDataRecordRepository.save(entity);

        return convertCustDataRecordModel(newEntity);
    }

    /**
     * ChangeCustDataRecordModel -> ChangeCustDataRecordEntity
     * 
     * @param model
     * @return
     */
    private ChangeCustDataRecordEntity convertCustDataRecordEntity(ChangeCustDataRecordModel model) {
        ChangeCustDataRecordEntity entity = new ChangeCustDataRecordEntity();
        entity.setRecordKey(model.getRecordKey());
        entity.setCompanyKey(model.getCompanyKey());
        entity.setNameCode(model.getNameCode());
        entity.setUserKey(model.getUserKey());
        entity.setUserId(model.getUserId());
        entity.setChangeItem(model.getChangeItem());
        entity.setCustEmail(model.getCustEmail());
        entity.setTxDate(model.getTxDate());
        entity.setTxStatus(model.getTxStatus());
        entity.setCreateTime(model.getCreateTime());
        entity.setUpdateTime(model.getUpdateTime());
        entity.setClientIp(model.getClientIp());
        entity.setTxSource(model.getTxSource());
        entity.setHostTxTime(model.getHostTxTime());
        entity.setTxErrorDesc(model.getTxErrorDesc());
        entity.setTxErrorSystemId(model.getTxErrorSystemId());
        entity.setTxErrorCode(model.getTxErrorCode());

        return entity;
    }

    /**
     * ChangeCustDataRecordEntity -> ChangeCustDataRecordModel
     * 
     * @param entity
     * @return
     */
    private ChangeCustDataRecordModel convertCustDataRecordModel(ChangeCustDataRecordEntity entity) {
        ChangeCustDataRecordModel model = new ChangeCustDataRecordModel();
        model.setRecordKey(entity.getRecordKey());
        model.setCompanyKey(entity.getCompanyKey());
        model.setNameCode(entity.getNameCode());
        model.setUserKey(entity.getUserKey());
        model.setUserId(entity.getUserId());
        model.setChangeItem(entity.getChangeItem());
        model.setCustEmail(entity.getCustEmail());
        model.setTxDate(entity.getTxDate());
        model.setTxStatus(entity.getTxStatus());
        model.setCreateTime(entity.getCreateTime());
        model.setUpdateTime(entity.getUpdateTime());
        model.setClientIp(entity.getClientIp());
        model.setTxSource(entity.getTxSource());
        model.setHostTxTime(entity.getHostTxTime());
        model.setTxErrorDesc(entity.getTxErrorDesc());
        model.setTxErrorSystemId(entity.getTxErrorSystemId());
        model.setTxErrorCode(entity.getTxErrorCode());

        return model;
    }

}
