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
package com.tfb.aibank.biz.security.services.twcaapi;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.JsonUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.biz.component.identity.IdentityData;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.component.twcaapi.TwcaUtils;
import com.tfb.aibank.biz.component.twcaapi.model.TwcaApiCertInfo;
import com.tfb.aibank.biz.component.twcaapi.model.TwcaApiLoginOutputParams;
import com.tfb.aibank.biz.component.twcaapi.model.TwcaApiLoginRq;
import com.tfb.aibank.biz.component.twcaapi.model.TwcaApiLoginRs;
import com.tfb.aibank.biz.component.twcaapi.model.TwcaApiQueryCertRq;
import com.tfb.aibank.biz.component.twcaapi.model.TwcaApiQueryCertRs;
import com.tfb.aibank.biz.component.twcaapi.model.TwcaApiQueryVerifyResultOutputParams;
import com.tfb.aibank.biz.component.twcaapi.model.TwcaApiQueryVerifyResultRq;
import com.tfb.aibank.biz.component.twcaapi.model.TwcaApiQueryVerifyResultRs;
import com.tfb.aibank.biz.component.twcaapi.model.TwcaApiRevokeCertRq;
import com.tfb.aibank.biz.component.twcaapi.model.TwcaApiRevokeCertRs;
import com.tfb.aibank.biz.security.repository.CertificateActLogDataRepository;
import com.tfb.aibank.biz.security.repository.entities.CertificateActLogDataEntity;
import com.tfb.aibank.biz.security.services.twcaapi.model.CertificateActLogModel;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.common.util.TxCodeUtils;

// @formatter:off
/**
 * @(#)TwcaAPIServise.java
 * 
 * <p>Description:台網API介接服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/01, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class TwcaApiServise {

    private static final IBLog logger = IBLog.getLog(TwcaApiServise.class);

    private IdentityService identityService;

    private SystemParamCacheManager systemParamCacheManager;

    private CertificateActLogDataRepository certificateActLogDataRepository;

    public TwcaApiServise(IdentityService identityService, SystemParamCacheManager systemParamCacheManager, CertificateActLogDataRepository certificateActLogDataRepository) {
        this.identityService = identityService;
        this.systemParamCacheManager = systemParamCacheManager;
        this.certificateActLogDataRepository = certificateActLogDataRepository;
    }

    /**
     * 查詢憑證.
     * 
     * @param custId
     * @return
     * @throws Exception
     */
    public TwcaApiQueryCertRs queryCert(String custId) throws Exception {
        String hashKey = systemParamCacheManager.getValue(IBSystemId.AIBANK.getSystemId(), "TWCA_API_HASH_KEY");
        String businessNo = systemParamCacheManager.getValue(IBSystemId.AIBANK.getSystemId(), "TWCA_API_BUSSINESS_NO");
        String hashKeyNo = systemParamCacheManager.getValue(IBSystemId.AIBANK.getSystemId(), "TWCA_API_HASH_KEY_NO");
        String identifyNo = TwcaUtils.getIdentifyNo(businessNo + hashKeyNo + custId + hashKey);

        TwcaApiQueryCertRq rq = new TwcaApiQueryCertRq();
        rq.setBusinessNo(businessNo);
        rq.setHashKeyNo(hashKeyNo);
        rq.setMemberNo(custId);
        rq.setIdentifyNo(identifyNo);

        TwcaApiQueryCertRs rs = TwcaUtils.sendAndReceive(rq, TwcaApiQueryCertRs.class, getUrl("QueryCert"), getTimeOut());

        if (!TwcaUtils.verifyRespIdentifyNo(rs, hashKey)) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.TWCA_API_SERVICE_ERROR);
        }

        if (StringUtils.isNotBlank(rs.getCertInfoListJsonData())) {
            rs.setCertInfoList(JsonUtils.getJsonObjectList(rs.getCertInfoListJsonData(), TwcaApiCertInfo.class));
        }

        return rs;
    }

    /**
     * 註冊憑證.
     * 
     * @param custId
     * @return
     * @throws Exception
     */
    public TwcaApiLoginRs login(String custId) throws Exception {
        String hashKey = systemParamCacheManager.getValue(IBSystemId.AIBANK.getSystemId(), "TWCA_API_HASH_KEY");
        String businessNo = systemParamCacheManager.getValue(IBSystemId.AIBANK.getSystemId(), "TWCA_API_BUSSINESS_NO");
        String apiVersion = systemParamCacheManager.getValue(IBSystemId.AIBANK.getSystemId(), "TWCA_API_API_VERSION");
        String hashKeyNo = systemParamCacheManager.getValue(IBSystemId.AIBANK.getSystemId(), "TWCA_API_HASH_KEY_NO");
        String verifyNo = getVerifyNo();
        String inputParams = getInputParams(custId);
        String identifyNo = TwcaUtils.getIdentifyNo(businessNo + apiVersion + hashKeyNo + verifyNo + inputParams + hashKey);

        TwcaApiLoginRq rq = new TwcaApiLoginRq();
        rq.setBusinessNo(businessNo);
        rq.setApiVersion(apiVersion);
        rq.setHashKeyNo(hashKeyNo);
        rq.setVerifyNo(verifyNo);
        rq.setInputParams(inputParams);
        rq.setIdentifyNo(identifyNo);

        TwcaApiLoginRs rs = TwcaUtils.sendAndReceive(rq, TwcaApiLoginRs.class, getUrl("Login"), getTimeOut());

        if (!TwcaUtils.verifyRespIdentifyNo(rs, hashKey)) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.TWCA_API_SERVICE_ERROR);
        }

        rs.setOutputParams(JsonUtils.getObject(rs.getOutputParamsJson(), TwcaApiLoginOutputParams.class));

        return rs;
    }

    /**
     * 驗證註冊結果.
     * 
     * @param verifyNo
     * @param token
     * @return
     * @throws Exception
     */
    public TwcaApiQueryVerifyResultRs queryVerifyResult(String custId, String verifyNo, String token) throws Exception {
        String hashKey = systemParamCacheManager.getValue(IBSystemId.AIBANK.getSystemId(), "TWCA_API_HASH_KEY");
        String businessNo = systemParamCacheManager.getValue(IBSystemId.AIBANK.getSystemId(), "TWCA_API_BUSSINESS_NO");
        String apiVersion = systemParamCacheManager.getValue(IBSystemId.AIBANK.getSystemId(), "TWCA_API_API_VERSION");
        String hashKeyNo = systemParamCacheManager.getValue(IBSystemId.AIBANK.getSystemId(), "TWCA_API_HASH_KEY_NO");
        String identifyNo = TwcaUtils.getIdentifyNo(businessNo + apiVersion + hashKeyNo + verifyNo + custId + token + hashKey);

        TwcaApiQueryVerifyResultRq rq = new TwcaApiQueryVerifyResultRq();
        rq.setBusinessNo(businessNo);
        rq.setApiVersion(apiVersion);
        rq.setHashKeyNo(hashKeyNo);
        rq.setVerifyNo(verifyNo);
        rq.setMemberNo(custId);
        rq.setToken(token);
        rq.setIdentifyNo(identifyNo);

        TwcaApiQueryVerifyResultRs rs = TwcaUtils.sendAndReceive(rq, TwcaApiQueryVerifyResultRs.class, getUrl("QueryVerifyResult"), getTimeOut());

        if (!TwcaUtils.verifyRespIdentifyNo(rs, hashKey)) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.TWCA_API_SERVICE_ERROR);
        }

        if (StringUtils.notEquals(rs.getReturnCode(), "0")) {
            throw new ActionException(IBSystemId.TWID.getSystemId(), rs.getReturnCode(), SeverityType.ERROR, rs.getReturnCodeDesc());
        }

        rs.setOutputParams(JsonUtils.getObject(rs.getOutputParamsJson(), TwcaApiQueryVerifyResultOutputParams.class));

        return rs;
    }

    /**
     * 註銷憑證.
     * 
     * @param hexSerial
     * @return
     * @throws Exception
     */
    public TwcaApiRevokeCertRs revokeCert(String hexSerial) throws Exception {
        String hashKey = systemParamCacheManager.getValue(IBSystemId.AIBANK.getSystemId(), "TWCA_API_HASH_KEY");
        String businessNo = systemParamCacheManager.getValue(IBSystemId.AIBANK.getSystemId(), "TWCA_API_BUSSINESS_NO");
        String hashKeyNo = systemParamCacheManager.getValue(IBSystemId.AIBANK.getSystemId(), "TWCA_API_HASH_KEY_NO");
        String identifyNo = TwcaUtils.getIdentifyNo(businessNo + hashKeyNo + hexSerial + hashKey);

        TwcaApiRevokeCertRq rq = new TwcaApiRevokeCertRq();
        rq.setBusinessNo(businessNo);
        rq.setHashKeyNo(hashKeyNo);
        rq.setHexSerial(hexSerial);
        rq.setIdentifyNo(identifyNo);

        TwcaApiRevokeCertRs rs = TwcaUtils.sendAndReceive(rq, TwcaApiRevokeCertRs.class, getUrl("RevokeCert"), getTimeOut());

        if (!TwcaUtils.verifyRespIdentifyNo(rs, hashKey)) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.TWCA_API_SERVICE_ERROR);
        }

        return rs;
    }

    private int getTimeOut() {
        return ConvertUtils.str2Int(systemParamCacheManager.getValue(IBSystemId.AIBANK.getSystemId(), "TWCA_API_TIMEOUT"), 60);
    }

    private String getUrl(String method) throws ActionException {
        String host = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "TWCA_API_URL");

        if (StringUtils.isBlank(host))
            throw ExceptionUtils.getActionException(AIBankErrorCode.TWCA_API_SERVICE_ERROR);

        if (StringUtils.equals(host.substring(host.length() - 1), "/")) {
            return host + method;
        }
        return host + "/" + method;
    }

    private String getVerifyNo() {
        return DateFormatUtils.SIMPLE_DATETIME_FORMAT.format(new Date()) + TxCodeUtils.genTxCodeNumberType(4);
    }

    /**
     * 以下參數以 JSON「字串」存放於此，並請依順序加入
     * 
     * @param custId
     * 
     * @return
     */
    private String getInputParams(String custId) {
        HashMap<String, Object> inputParams = new LinkedHashMap<>();
        inputParams.put("MemberNo", custId);
        inputParams.put("MemberNoType", "0");
        inputParams.put("Action", "CERT");
        inputParams.put("Plaintext", custId);
        inputParams.put("CAType", StringUtils.EMPTY);
        inputParams.put("AssignCertPassword", StringUtils.EMPTY);
        inputParams.put("ErrCodeType", "1");
        return JsonUtils.getJson(inputParams);
    }

    /**
     * 台網 註銷憑證 紀錄LOG
     * 
     * @param model
     * @return
     */
    public CertificateActLogModel saveCertificateLog(CertificateActLogModel model) throws ActionException {
        // 取得使用者資料
        IdentityData identityData = getUser(model.getCustId(), model.getUserId(), model.getCompanyKind(), model.getUidDup());

        if (identityData == null) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.DATA_NOT_FOUND);
        }

        CertificateActLogDataEntity entity = new CertificateActLogDataEntity();

        entity.setCompanyKey(identityData.getCompanyKey());
        entity.setUserKey(identityData.getUserKey());
        entity.setCustId(model.getCustId());
        entity.setActDate(StringUtils.isBlank(model.getActDate()) ? DateFormatUtils.SIMPLE_DATE_FORMAT.format(new Date()) : model.getActDate());
        entity.setVerifyNo(model.getVerifyNo());
        entity.setToken(model.getToken());
        entity.setResultCode(model.getResultCode());
        entity.setAct(model.getAct());
        entity.setActBizType(model.getActBizType());
        entity.setActUseFunc(model.getActUseFunc());
        entity.setAccessLogKey(model.getAccessLogKey());
        entity.setUserAgent(model.getUserAgent());
        entity.setDeviceModel(model.getDeviceModel());
        entity.setDeviceOS(model.getDeviceOS());
        entity.setCreateTime(model.getCreateTime() == null ? new Date() : model.getCreateTime());
        entity.setTermVer(model.getTermVer());
        entity.setClientIp(model.getClientIp());
        entity.setClientPort(model.getClientPort());
        entity.setSecurityType(model.getSecurityType());
        entity.setTraceId(model.getTraceId());

        CertificateActLogDataEntity newEntity = certificateActLogDataRepository.save(entity);
        model.setCertLogKey(newEntity.getCertLogKey());
        return model;
    }

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
    private IdentityData getUser(String custId, String userId, int companyKind, String uidDup) {
        try {
            IdentityData identityData = identityService.query(custId, uidDup, userId, companyKind);
            if (identityData != null && identityData.isAliveUser()) {
                return identityData;
            }
        }
        catch (CryptException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

}
