/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.biz.user.services.datasync;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.tfb.aibank.biz.user.services.datasync.model.FubonStockCommonApiRequest;
import com.tfb.aibank.biz.user.services.datasync.model.FubonStockDataSyncStatusApiRequest;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.message.BasicHeader;
import org.springframework.http.MediaType;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.httpclient.CustomHttpResponse;
import com.ibm.tw.commons.httpclient.HttpClient5;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.JsonUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.biz.component.identity.IdentityData;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.user.repository.AiDataSyncLogRepository;
import com.tfb.aibank.biz.user.repository.AiDataSyncStatusRepository;
import com.tfb.aibank.biz.user.repository.SequenceRepositoryCustom;
import com.tfb.aibank.biz.user.repository.entities.AiDataSyncLogEntity;
import com.tfb.aibank.biz.user.repository.entities.AiDataSyncStatusEntity;
import com.tfb.aibank.biz.user.services.datasync.model.AiDataSyncStatusModel;
import com.tfb.aibank.biz.user.services.datasync.model.ApiRequestUser;
import com.tfb.aibank.biz.user.services.datasync.model.DataSyncStatusApiRequest;
import com.tfb.aibank.biz.user.services.datasync.model.DataSyncStatusApiResponse;
import com.tfb.aibank.biz.user.services.datasync.model.UpdateDataSyncResourceRequest;
import com.tfb.aibank.biz.user.services.datasync.model.UpdateInsurAuthTokenRequest;
import com.tfb.aibank.biz.user.services.datasync.model.UpdateInsurDataSyncStatusApiRequest;
import com.tfb.aibank.biz.user.services.datasync.model.UpdateSecurDataSyncStatusApiRequest;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.type.DataSyncStatusApiResponseType;
import com.tfb.aibank.common.type.DataSyncStatusUpdateType;
import com.tfb.aibank.common.type.UpdateDataSyncStatusType;
import com.tfb.aibank.common.util.BizExceptionUtils;

//@formatter:off
/**
* @(#)AiDataSyncStatusService.java
* 
* <p>Description: 資料彙整狀態註記檔</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/02/29, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class AiDataSyncStatusService {

    private static final IBLog logger = IBLog.getLog(AiDataSyncStatusService.class);

    private AiDataSyncLogRepository aiDataSyncLogRepository;

    private AiDataSyncStatusRepository aiDataSyncStatusRepository;

    private SystemParamCacheManager systemParamCacheManager;

    private SequenceRepositoryCustom sequenceRepositoryCustom;

    private IdentityService identityService;

    private static final String TXN_REGEX = "[a-zA-Z0-9]{8}";

    private static final Integer TIME_OUT_SECONDS = 180;

    public AiDataSyncStatusService(AiDataSyncStatusRepository aiDataSyncRepository, AiDataSyncLogRepository aiDataSyncLogRepository, SystemParamCacheManager systemParamCacheManager, SequenceRepositoryCustom sequenceRepositoryCustom, IdentityService identityService) {
        this.aiDataSyncStatusRepository = aiDataSyncRepository;
        this.aiDataSyncLogRepository = aiDataSyncLogRepository;
        this.systemParamCacheManager = systemParamCacheManager;
        this.sequenceRepositoryCustom = sequenceRepositoryCustom;
        this.identityService = identityService;
    }

    public AiDataSyncStatusModel getUserDataSyncStatusInfo(String custId, String dup, String userId, Integer companyKind) throws CryptException, ActionException {
        IdentityData identityData = identityService.query(custId, dup, userId, companyKind);
        if (identityData == null) {
            String errorMsg = String.format("user not found: custId: %s, dup: %s, userId: %s, companyKind: %s%n", IBUtils.toDataModel(DataMaskUtil.maskCustId(custId), String.class), dup, IBUtils.toDataModel(DataMaskUtil.maskUserId(userId), String.class), companyKind);
            logger.error(errorMsg);
            throw BizExceptionUtils.getActionException(errorMsg, CommonErrorCode.DATA_NOT_FOUND);
        }

        return getUserDataSyncStatusInfo(identityData.getCompanyKey(), identityData.getUserKey());
    }

    private AiDataSyncStatusModel getUserDataSyncStatusInfo(Integer companyKey, Integer userKey) throws ActionException {
        AiDataSyncStatusEntity entity = aiDataSyncStatusRepository.findByCompanyKeyAndUserKey(companyKey, userKey);

        if (entity == null) {
            String errorMsg = String.format("entity not found, companyKey: %s, userKey: %s%n", IBUtils.toDataModel(companyKey, Integer.class), IBUtils.toDataModel(userKey, Integer.class));
            logger.info(errorMsg);
            return new AiDataSyncStatusModel(companyKey, userKey);
        }

        return new AiDataSyncStatusModel(entity);
    }

    public AiDataSyncStatusModel createNewDataStatusSync(String custId, String dup, String userId, Integer companyKind) throws CryptException, ActionException {
        // gets a default model if no data
        AiDataSyncStatusModel model = getUserDataSyncStatusInfo(custId, dup, userId, companyKind);
        model.setSecurType(0);
        model.setInsurType(0);
        model.setSecurStatus(model.getSecurStatus() == null ? "N" : model.getSecurStatus());
        model.setInsurStatus(model.getInsurStatus() == null ? "N" : model.getInsurStatus());
        aiDataSyncStatusRepository.saveAndFlush(convertFromResponse(model));
        return model;
    }

    /**
     * (原) 更新富證富壽資料彙整狀態
     *
     * @param resourceRequest
     *            ApiRequestUser, txn, updateDataSyncStatusType, changeStatusReq
     * @throws CryptException
     * @throws ActionException
     */
    public AiDataSyncStatusModel updateUserDataSyncStatus(UpdateDataSyncResourceRequest resourceRequest) throws ActionException, CryptException {
        ApiRequestUser user = resourceRequest.getUser();
        updateUserDataSyncStatusWithResponseType(resourceRequest);
        return getUserDataSyncStatusInfo(user.getCustId(), user.getDup(), user.getUserId(), user.getCompanyKind());
    }

    /**
     * 更新富證富壽資料彙整狀態 (回傳更新結果)
     *
     * @param resourceRequest
     *            ApiRequestUser, txn, updateDataSyncStatusType, changeStatusReq
     * @throws CryptException
     * @throws ActionException
     */
    public DataSyncStatusUpdateType updateUserDataSyncStatusWithResponseType(UpdateDataSyncResourceRequest resourceRequest) throws ActionException, CryptException {
        ApiRequestUser user = resourceRequest.getUser();
        String txn = resourceRequest.getTxn();
        UpdateDataSyncStatusType updateStatusType = resourceRequest.getUpdateDataSyncStatusType();
        DataSyncStatusApiRequest requestData = resourceRequest.getChangeStatusReq();

        IdentityData identityData = identityService.query(user.getCustId(), user.getDup(), user.getUserId(), user.getCompanyKind());
        if (identityData == null) {
            String errorMsg = String.format("user not found: custId: %s, dup: %s, userId: %s, companyKind: %s%n", IBUtils.toDataModel(DataMaskUtil.maskCustId(user.getCustId()), String.class), user.getDup(), IBUtils.toDataModel(DataMaskUtil.maskUserId(user.getUserId()), String.class), user.getCompanyKind());
            logger.error(errorMsg);
            throw BizExceptionUtils.getActionException(errorMsg, CommonErrorCode.DATA_NOT_FOUND);
        }

        if (StringUtils.isNotBlank(requestData.getSecur()))
            return updateSecurType(txn, user, identityData, updateStatusType, requestData);

        if (StringUtils.isNotBlank(requestData.getInsur()))
            return updateInsurType(txn, user, identityData, updateStatusType, requestData);

        return null;
    }

    // 發送富證API
    private String sendSecurApiRequest(String url, UpdateSecurDataSyncStatusApiRequest request) throws ActionException {
        String key = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "SECUR_KEY");
        String jwtHeader = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "SECUR_JWT_HEADER");

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));
        Map<String, String> jwtPayload = new HashMap<>();
        long iat = new Date().getTime();
        long exp = iat + (30L * 1000L);
        jwtPayload.put("sub", String.format("%s%s", request.getIdno(), request.getBdate()));
        jwtPayload.put("iss", "bank");
        jwtPayload.put("iat", String.valueOf(iat));
        jwtPayload.put("exp", String.valueOf(exp));
        String jwtPayloadStr = JsonUtils.getJson(jwtPayload);

        String jwtHeader64 = new String(Base64.getEncoder().encode(jwtHeader.getBytes()), StandardCharsets.UTF_8);
        String jwtPayload64 = new String(Base64.getEncoder().encode(jwtPayloadStr.getBytes()), StandardCharsets.UTF_8);
        Mac sha512HMAC = null;
        try {
            sha512HMAC = Mac.getInstance("HmacSHA512");
            SecretKeySpec spec = new SecretKeySpec(Base64.getDecoder().decode(key), "HmacSHA512");
            sha512HMAC.init(spec);
            byte[] macData = sha512HMAC.doFinal((jwtHeader64 + "." + jwtPayload64).getBytes());
            String signature = new String(Base64.getEncoder().encode(macData), StandardCharsets.UTF_8);
            headers.add(new BasicHeader("Authorization", "Bearer " + jwtHeader64 + "." + jwtPayload64 + "." + signature));
        }
        catch (NoSuchAlgorithmException | InvalidKeyException e) {
            logger.error("error while sending secur api request, {}", e.getMessage());
        }

        return sendApiRequest(url, headers, request);
    }

    // 發送富證API
    private String sendSecurApiRequestV2(String url, FubonStockDataSyncStatusApiRequest request) throws ActionException {

        List<Header> headers = new ArrayList<>();
        // 既有
        headers.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));
        headers.add(new BasicHeader("x-core-traceid", UUID.randomUUID().toString()));
        headers.add(new BasicHeader("x-core-timestamp",
                OffsetDateTime.now(ZoneOffset.of("+08:00"))
                        .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)));
        headers.add(new BasicHeader("x-core-transactionid", "test"));
        headers.add(new BasicHeader("Accept-Charset", StandardCharsets.UTF_8.name()));
        headers.add(new BasicHeader("x-core-channel", "epay"));
        headers.add(new BasicHeader(HttpHeaders.ACCEPT_LANGUAGE, "zh-TW"));
        headers.add(new BasicHeader("x-core-label", "mr"));

        try {
            headers.add(new BasicHeader("Authorization", "Bearer " + getJwtToken( request.getIdno(), request.getBdate())));
        }
        catch (NoSuchAlgorithmException | InvalidKeyException e) {
            logger.error("error while sending secur api request, {}", e.getMessage());
        }

        FubonStockCommonApiRequest<FubonStockDataSyncStatusApiRequest> clientRequest = new FubonStockCommonApiRequest<>(request);

        return sendApiRequest(url, headers, clientRequest);
    }

    private String getJwtToken(String idno, String bDate)
            throws NoSuchAlgorithmException, InvalidKeyException {

        String key = systemParamCacheManager.getValue(
                AIBankConstants.CHANNEL_NAME, "SECUR_JWT_KEY");
        String jwtHeader = systemParamCacheManager.getValue(
                AIBankConstants.CHANNEL_NAME, "SECUR_JWT_HEADER");

        Map<String, Object> jwtPayload = new HashMap<>();
        long iatSec = System.currentTimeMillis() / 1000;
        long expSec = iatSec + 30;                         // 30 秒
        jwtPayload.put("sub", idno + bDate);
        jwtPayload.put("iat", iatSec);
        jwtPayload.put("exp", expSec);

        String payloadJson = JsonUtils.getJson(jwtPayload);

        Base64.Encoder urlEncoder = Base64.getUrlEncoder().withoutPadding();
        String header64 = urlEncoder.encodeToString(
                jwtHeader.getBytes(StandardCharsets.UTF_8));
        String payload64 = urlEncoder.encodeToString(
                payloadJson.getBytes(StandardCharsets.UTF_8));


        Mac hmac = Mac.getInstance("HmacSHA512");


        SecretKeySpec keySpec =
                new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512"); // ★

        hmac.init(keySpec);

        String signingInput = header64 + '.' + payload64;
        byte[] mac = hmac.doFinal(signingInput.getBytes(StandardCharsets.UTF_8));
        String signature64 = urlEncoder.encodeToString(mac);

        return signingInput + '.' + signature64;
    }

    // 發送富壽API
    private String sendInsurApiRequest(String url, Object request) throws ActionException {
        String apimType = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "INSUR_APIM_TYPE");
        List<Header> headers = List.of(new BasicHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE), new BasicHeader("apim_type", apimType));
        return sendApiRequest(url, headers, request);
    }

    private String sendApiRequest(String url, List<Header> headers, Object request) throws ActionException {
        CustomHttpResponse response = HttpClient5.doPost(url, headers, JsonUtils.getJson(request), TIME_OUT_SECONDS);
        return StringUtils.defaultString(response.getResponse());
    }

    // 僅發送富邦證券彙整註記異動
    private DataSyncStatusUpdateType updateSecurType(String txn, ApiRequestUser user, IdentityData identityData, UpdateDataSyncStatusType updateStatusType, DataSyncStatusApiRequest requestData) throws ActionException {
        if (!(Pattern.matches(TXN_REGEX, txn) && StringUtils.isNotBlank(requestData.getSecur()))) {
            String errorMsg = String.format("invalid txn or missing secur in request data! txn: %s, requestData: %s%n", txn, requestData);
            logger.error(errorMsg);
            throw BizExceptionUtils.getActionException(errorMsg, CommonErrorCode.DATA_NOT_FOUND);
        }

        String host = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "SECURITIES_HOST");
        String port = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "SECURITIES_PORT");
        String suffix = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "SECUR_ASSET_UPDATE_STATUS");
        if (StringUtils.isBlank(host) || StringUtils.isBlank(port) || StringUtils.isBlank(suffix))
            throw BizExceptionUtils.getActionException("secur url not set", CommonErrorCode.DATA_NOT_FOUND);

        String url = String.format("%s:%s/%s", host, port, suffix);

        AiDataSyncStatusModel dbOriginalDto = Optional.ofNullable(getUserDataSyncStatusInfo(identityData.getCompanyKey(), identityData.getUserKey())).orElse(new AiDataSyncStatusModel());

        FubonStockDataSyncStatusApiRequest request = new FubonStockDataSyncStatusApiRequest();
        request.setIdno(user.getCustId());
        request.setBdate(DateUtils.getDateTimeStrByDateFormat(user.getBirthDay(), "yyyyMMdd"));
        request.setChannel("T_AI_BANK");

        //@formatter:off
        /**
         * 1.初次設定：查詢DB AI_DATA_SYNC_STATUS.SECUR_TYPE=0 or null，上送1。 
         * 2.使用者設定：查詢DB AI_DATA_SYNC_STATUS. SECUR_TYPE=1或2，上送2。 
         * 3.系統自動關閉：API_Rq.resultCode =E401，上送3。待確認 
         * 4.銀行銷戶：取ODS 銷戶資料，此資料為新批次功能仍待確認。
         */
        //@formatter:on
        if (StringUtils.isNotBlank(requestData.getUseType())) {
            request.setUsetype(requestData.getUseType());
        }
        else if (StringUtils.equals("S", requestData.getSecur())) {
            request.setUsetype("3");
        }
        else if (dbOriginalDto.getSecurType() == null || 0 == dbOriginalDto.getSecurType()) {
            request.setUsetype("1");
        }
        else if (1 == dbOriginalDto.getSecurType() || 2 == dbOriginalDto.getSecurType()) {
            request.setUsetype("2");
        }
        else {
            request.setUsetype("2");
        }

        // Y || N
        request.setStatus(getAgreeString(requestData.getSecur()));

        // Fortify:Log Forging// logger.info("update secur request: {}", IBUtils.attribute2Str(request));
        DataSyncStatusApiResponse apiResponse = JsonUtils.getObject(sendSecurApiRequestV2(url, request), DataSyncStatusApiResponse.class);
        DataSyncStatusApiResponseType apiResponseType = DataSyncStatusApiResponseType.findByCode(apiResponse.getStatusCode(true));
        // Fortify:Log Forging//logger.info("apiResponse: {}, apiResponseType: {}, {}", IBUtils.attribute2Str(apiResponse), apiResponseType.getCode(), apiResponseType.getMemo());

        if (!(DataSyncStatusApiResponseType.SUCCESS_SECUR.equals(apiResponseType) || DataSyncStatusApiResponseType.CUSTOMER_ACCOUNT_VALIDATION_FAILED.equals(apiResponseType))) {
            logger.error("富邦證券API失敗, apiResponse.code: {}, apiResponse.message: {}, apiResponseType: {}, msg: {}", apiResponse.getStatusCode(), apiResponse.getStatusMsg(), apiResponseType.getCode(), apiResponseType.getMemo());
            insertAiDataSyncLog(null, convertFromResponse(dbOriginalDto), Integer.parseInt(request.getUsetype()), apiResponse, updateStatusType);
            return DataSyncStatusUpdateType.FAIL;
        }

        // 打開或關閉成功, 都會回Y, 因此要根據returnCode判斷該次要update成什麼
        // 假如成功, 根據當次request要改的部分, 更新database
        // e.g 打開 -> Y(打開成功) -> status = Y
        // 關閉 -> Y(關閉成功) -> status = N
        // 例外狀況: 上送成功(Y or N), 但對方回U(非客戶) -> 這時我們要更新成N
        dbOriginalDto.setSecurType(Integer.parseInt(request.getUsetype()));
        dbOriginalDto.setSecurStatus(DataSyncStatusApiResponseType.SUCCESS_SECUR.equals(apiResponseType) ? request.getStatus() : "N");
        dbOriginalDto.setUpdateTime(new Date());

        AiDataSyncStatusEntity entity = convertFromResponse(dbOriginalDto);
        aiDataSyncStatusRepository.saveAndFlush(entity);

        // save log
        insertAiDataSyncLog(null, entity, request, apiResponse, updateStatusType);
        return DataSyncStatusApiResponseType.SUCCESS_SECUR.equals(apiResponseType) ? DataSyncStatusUpdateType.SUCCESS : DataSyncStatusUpdateType.NON_CUSTOMER;
    }

    // 僅發送富邦人壽彙整註記異動
    private DataSyncStatusUpdateType updateInsurType(String txn, ApiRequestUser user, IdentityData identityData, UpdateDataSyncStatusType updateStatusType, DataSyncStatusApiRequest requestData) throws ActionException {
        if (!(Pattern.matches(TXN_REGEX, txn) && StringUtils.isNotBlank(requestData.getInsur()))) {
            String errorMsg = String.format("invalid txn or missing secur in request data! txn: %s, requestData: %s%n", txn, requestData);
            logger.error(errorMsg);
            throw BizExceptionUtils.getActionException(errorMsg, CommonErrorCode.DATA_NOT_FOUND);
        }

        String tokenUrl = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "INSUR_GET_AUTH_TOKEN_URL");
        String url = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "INSUR_UPDATE_STATUS");

        if (StringUtils.isBlank(tokenUrl) || StringUtils.isBlank(url))
            throw BizExceptionUtils.getActionException(String.format("insur token url or url not set, tokenUrl: %s, url: %s%n", tokenUrl, url), CommonErrorCode.DATA_NOT_FOUND);

        AiDataSyncStatusModel dbOriginalDto = getUserDataSyncStatusInfo(identityData.getCompanyKey(), identityData.getUserKey());

        UpdateInsurAuthTokenRequest tokenRequest = new UpdateInsurAuthTokenRequest();
        tokenRequest.setSysId("AI_BANK");

        Integer logSeq = sequenceRepositoryCustom.getSequenceForDataSyncLog();
        String logSeqStr = StringUtils.leftPadZero(String.valueOf(logSeq), 12);
        String sysCaseNo = String.format("%s%s", DateUtils.getDateTimeStrByDateFormat(new Date(), "yyyyMMdd"), logSeqStr);
        tokenRequest.setSysCaseNo(sysCaseNo);

        // get auth token error
        DataSyncStatusApiResponse authToken = JsonUtils.getObject(sendInsurApiRequest(tokenUrl, tokenRequest), DataSyncStatusApiResponse.class);
        DataSyncStatusApiResponseType tokenResponseType = DataSyncStatusApiResponseType.findByCode(authToken.getReturnCode());
        if (!DataSyncStatusApiResponseType.SUCCESS_INSUR.equals(tokenResponseType)) {
            logger.error("error while getting authToken, returnCode: {}, message: {}, token: {}", authToken.getReturnCode(), authToken.getMessage(), authToken.getAuthToken());
            throw BizExceptionUtils.getActionException(CommonErrorCode.INPUT_OR_OUTPUT_EXCEPTION);
        }

        UpdateInsurDataSyncStatusApiRequest request = new UpdateInsurDataSyncStatusApiRequest();
        request.setSysId(tokenRequest.getSysId());
        request.setSysCaseNo(tokenRequest.getSysCaseNo());
        request.setAuthToken(authToken.getAuthToken());
        request.setCustId(user.getCustId());
        request.setCustBirth(DateUtils.getROCDateStrFromCEDateFormat(user.getBirthDay()));

        //@formatter:off
        /**
         * 必填 
         * 1:初次設定：查詢DB AI_DATA_SYNC_STATUS.INCUR_TYPE=0 or null，上送1。 
         * 2:使用者設定：查詢DB AI_DATA_SYNC_STATUS.INCUR_TYPE=1或2，上送2。 
         * 3:系統自動關閉：API_Rq.statusCode=06，上送3。 
         * 4:銀行銷戶：取ODS 銷戶資料，此資料為新批次功能仍待確認。
         */
        //@formatter:on
        if (StringUtils.isNotBlank(requestData.getUseType())) {
            request.setUpdType(requestData.getUseType());
        }
        else if (StringUtils.equals("S", requestData.getInsur())) {
            request.setUpdType("3");
        }
        else if (dbOriginalDto.getInsurType() == null || 0 == dbOriginalDto.getInsurType()) {
            request.setUpdType("1");
        }
        else if (1 == dbOriginalDto.getInsurType() || 2 == dbOriginalDto.getInsurType()) {
            request.setUpdType("2");
        }
        else {
            request.setUpdType("2");
        }

        request.setIsAgree(getAgreeString(requestData.getInsur()));

        // Fortify:Log Forging//logger.info("update insur request: {}", IBUtils.attribute2Str(request));
        DataSyncStatusApiResponse apiResponse = JsonUtils.getObject(sendInsurApiRequest(url, request), DataSyncStatusApiResponse.class);
        DataSyncStatusApiResponseType apiResponseType = DataSyncStatusApiResponseType.findByCode(apiResponse.getReturnCode());
        // Fortify:Log Forging//logger.info("update insur response: {}", IBUtils.attribute2Str(apiResponse));

        //@formatter:off
        /**
         * 回覆碼 
         * 0000:成功、
         * 1001:系統錯誤、
         * 1002:無效的系統代號、
         * 1003:參數不足、
         * 1004:驗證碼逾期、
         * 1005:無效的驗證碼、
         * 1006:非客戶
         */
        //@formatter:on
        // DataSyncStatusApiResponseType.SUCCESS_INSUR.equals(apiResponseType) || DataSyncStatusApiResponseType.NON_CUSTOMER.equals(apiResponseType)
        if (!(DataSyncStatusApiResponseType.SUCCESS_INSUR.equals(apiResponseType) || DataSyncStatusApiResponseType.NON_CUSTOMER.equals(apiResponseType))) {
            logger.error("富邦人壽API失敗, apiResponse.code: {}, apiResponse.message: {}, apiResponseType: {}, msg: {}", apiResponse.getReturnCode(), apiResponse.getMessage(), apiResponseType.getCode(), apiResponseType.getMemo());
            insertAiDataSyncLog(logSeq, convertFromResponse(dbOriginalDto), Integer.parseInt(request.getUpdType()), apiResponse, updateStatusType);
            return DataSyncStatusUpdateType.FAIL;
        }

        // 打開或關閉成功, 都會回Y, 因此要根據returnCode判斷該次要update成什麼
        // 假如成功, 根據當次request要改的部分, 更新database
        // e.g 打開 -> Y(打開成功) -> status = Y
        // 關閉 -> Y(關閉成功) -> status = N
        // 例外狀況: 上送成功(Y or N), 但對方回U(非客戶) -> 這時我們要更新成N
        dbOriginalDto.setInsurType(Integer.parseInt(request.getUpdType()));
        dbOriginalDto.setInsurStatus(DataSyncStatusApiResponseType.SUCCESS_INSUR.equals(apiResponseType) ? request.getIsAgree() : "N");
        dbOriginalDto.setUpdateTime(new Date());
        AiDataSyncStatusEntity entity = convertFromResponse(dbOriginalDto);
        aiDataSyncStatusRepository.saveAndFlush(entity);

        // save log
        insertAiDataSyncLog(logSeq, entity, request, apiResponse, updateStatusType);
        return DataSyncStatusApiResponseType.SUCCESS_INSUR.equals(apiResponseType) ? DataSyncStatusUpdateType.SUCCESS : DataSyncStatusUpdateType.NON_CUSTOMER;
    }

    private void insertAiDataSyncLog(Integer logSeq, AiDataSyncStatusEntity statusEntity, Object request, DataSyncStatusApiResponse response, UpdateDataSyncStatusType type) {
        AiDataSyncLogEntity log = new AiDataSyncLogEntity();
        if (logSeq != null)
            log.setLogSeq(logSeq);
        log.setCompanyKey(statusEntity.getCompanyKey());
        log.setUserKey(statusEntity.getUserKey());
        if (UpdateDataSyncStatusType.UPDATE_SECUR_TYPE.equals(type)) {
            log.setItem(1);
            log.setStatus(statusEntity.getSecurStatus());
            log.setUseType(Integer.parseInt(((FubonStockDataSyncStatusApiRequest) request).getUsetype()));
//            log.setResultCode(response.getResultCode());
//            log.setResultDesc(response.getResultDesc());
            // v2 用不同回應欄位
            log.setResultCode(response.getStatusCode());
            log.setResultDesc(response.getStatusMsg());
            log.setChannelId("1");  // Fubon+ 為 1
        }

        if (UpdateDataSyncStatusType.UPDATE_INSUR_TYPE.equals(type)) {
            log.setItem(2);
            log.setStatus(statusEntity.getInsurStatus());
            log.setUseType(Integer.parseInt(((UpdateInsurDataSyncStatusApiRequest) request).getUpdType()));
            log.setResultCode(response.getReturnCode());
            log.setResultDesc(response.getMessage());
        }
        log.setCreateTime(new Date());
        aiDataSyncLogRepository.save(log);
    }

    private AiDataSyncStatusEntity convertFromResponse(AiDataSyncStatusModel res) {
        AiDataSyncStatusEntity entity = new AiDataSyncStatusEntity();
        entity.setCompanyKey(res.getCompanyKey());
        entity.setUserKey(res.getUserKey());
        entity.setSecurType(res.getSecurType());
        entity.setSecurStatus(res.getSecurStatus());
        entity.setInsurType(res.getInsurType());
        entity.setInsurStatus(res.getInsurStatus());
        entity.setCreateTime(res.getCreateTime() == null ? new Date() : res.getCreateTime());
        entity.setUpdateTime(res.getUpdateTime());
        return entity;
    }

    private String getAgreeString(String input) throws ActionException {
        if (StringUtils.isN(input) || StringUtils.equals("S", input))
            return "N";

        if (StringUtils.isY(input))
            return "Y";

        logger.error("invalid agreement input detected in getAgreeString(): {}", input);
        throw BizExceptionUtils.getActionException(CommonErrorCode.INPUT_OR_OUTPUT_EXCEPTION);
    }
}
