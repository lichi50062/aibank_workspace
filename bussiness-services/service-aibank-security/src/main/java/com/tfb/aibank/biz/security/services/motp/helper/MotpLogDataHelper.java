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
package com.tfb.aibank.biz.security.services.motp.helper;

import java.util.Date;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.biz.component.identity.IdentityData;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.security.repository.MotpLogDataRepository;
import com.tfb.aibank.biz.security.repository.entities.MotpLogDataEntity;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.component.motplog.MotpLogActionType;

// @formatter:off
/**
 * @(#)MotpLogDataHelper.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/03, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class MotpLogDataHelper {

    @Autowired
    private MotpLogDataRepository motpLogDataRepository;

    @Autowired
    private IdentityService identityService;

    private static final IBLog logger = IBLog.getLog(MotpLogDataHelper.class);

    /**
     * 取得使用者資料
     * 
     * @param custId
     * @param userId
     * @param companyKind
     * @param uidDup
     * @return
     * @throws ActionException
     */
    public MotpLogDataModel createMotpLog(MotpLogActionType type, String custId, String userId, int companyKind, String uidDup,

            String serviceName, String serviceMethod

    ) throws ActionException {

        try {
            IdentityData identityData = identityService.query(custId, uidDup, userId, companyKind);
            if (identityData != null && identityData.isAliveUser()) {
                MotpLogDataModel logModel = new MotpLogDataModel();

                logModel.setType(type);
                logModel.setCustId(custId);
                logModel.setUserId(userId);
                logModel.setIdentityData(identityData);
                logModel.setServiceName(serviceName);
                logModel.setServiceMethod(serviceMethod);
                return logModel;
            }
        }
        catch (CryptException e) {
            logger.error(e.getMessage(), e);
        }
        throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
    }

    public MotpLogDataModel createMotpLog(MotpLogActionType type, String custId, String userId,

            Integer companyKey, Integer userKey,

            String serviceName, String serviceMethod) {

        MotpLogDataModel logModel = new MotpLogDataModel();

        logModel.setType(type);
        logModel.setCustId(custId);
        logModel.setUserId(userId);
        logModel.setIdentityData(new IdentityData());
        logModel.getIdentityData().setCompanyKey(companyKey);
        logModel.getIdentityData().setUserKey(userKey);
        logModel.setServiceName(serviceName);
        logModel.setServiceMethod(serviceMethod);
        return logModel;
    }

    public MotpLogDataEntity getMotpLogDataEntity(MotpLogDataModel motpLogData) {
        String deviceId = MDC.get(MDCKey.deviceid.toString());
        MotpLogDataEntity log = new MotpLogDataEntity();

        String sessionid = MDC.get(MDCKey.sessionid.name());
        if (sessionid != null && sessionid.length() > 32) {
            sessionid = sessionid.substring(0, 32);
        }
        else {
            sessionid = "-1";
        }
        String trackingid = MDC.get(MDCKey.trackingid.name());
        if (trackingid != null && trackingid.length() > 20) {
            trackingid = trackingid.substring(0, 20);
        }
        else {
            trackingid = "-1";
        }

        log.setChannel(AIBankConstants.CHANNEL_NAME);
        log.setSessionId(sessionid);
        log.setCompanyUid(motpLogData.getCustId());
        log.setDeviceUuid(deviceId);
        log.setAccessLogKey(trackingid);
        log.setCompanyKey(motpLogData.getIdentityData().getCompanyKey());
        log.setUserKey(motpLogData.getIdentityData().getUserKey());
        log.setType(motpLogData.getType().getType());
        log.setAction(motpLogData.getType().getAction());
        log.setServiceName(motpLogData.getServiceName());
        log.setServiceMethod(motpLogData.getServiceMethod());
        log.setRqData("{}");
        log.setRsData("{}");
        log.setCreateTime(new Date());
        log.setUpdateTime(new Date());
        return log;
    }

    // 以下 for Channel 呼叫
    public void save(MotpLogActionType type,

            String custId, String userId, int companyKind, String uidDup,

            String serviceName, String serviceMethod, String Status, String rqData, String rsData) {

        MotpLogDataEntity log = getMotpLogDataEntity(type,

                custId, userId, companyKind, uidDup,

                serviceName, serviceMethod, Status, rqData, rsData);

        save(log);
    }

    public MotpLogDataEntity getMotpLogDataEntity(MotpLogActionType type,

            String custId, String userId, int companyKind, String uidDup,

            String serviceName, String serviceMethod, String Status, String rqData, String rsData) {

        String deviceId = MDC.get(MDCKey.deviceid.toString());
        Integer companyKey = 0;
        Integer userKey = 0;
        try {
            IdentityData identityData = identityService.query(custId, uidDup, userId, companyKind);
            companyKey = identityData.getCompanyKey();
            userKey = identityData.getUserKey();
        }
        catch (CryptException ex) {
            logger.warn("IdentityData Error", ex);
        }

        if (StringUtils.isBlank(serviceName)) {
            serviceName = MDC.get(MDCKey.pageid.toString());
        }

        MotpLogDataEntity log = new MotpLogDataEntity();

        String sessionid = MDC.get(MDCKey.sessionid.name());
        if (sessionid != null && sessionid.length() > 32) {
            sessionid = sessionid.substring(0, 32);
        }
        else {
            sessionid = "-1";
        }
        String trackingid = MDC.get(MDCKey.trackingid.name());
        if (trackingid != null && trackingid.length() > 20) {
            trackingid = trackingid.substring(0, 20);
        }
        else {
            trackingid = "-1";
        }

        if (StringUtils.isBlank(rqData))
            rqData = "{}";

        if (StringUtils.isBlank(rsData))
            rsData = "{}";

        log.setChannel(AIBankConstants.CHANNEL_NAME);
        log.setSessionId(sessionid);
        log.setCompanyUid(custId);
        log.setDeviceUuid(deviceId);
        log.setAccessLogKey(trackingid);
        log.setCompanyKey(companyKey);
        log.setUserKey(userKey);
        log.setType(type.getType());
        log.setAction(type.getAction());
        log.setServiceName(serviceName);
        log.setServiceMethod(serviceMethod);
        log.setStatus(Status);
        log.setRqData(rqData);
        log.setRsData(rsData);
        log.setCreateTime(new Date());
        log.setUpdateTime(new Date());
        return log;
    }

    public void save(MotpLogDataEntity entity) {
        motpLogDataRepository.save(entity);
    }
}
