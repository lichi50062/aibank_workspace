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
package com.tfb.aibank.biz.security.services.mid;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.message.BasicHeader;

import com.google.gson.Gson;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.httpclient.CustomHttpResponse;
import com.ibm.tw.commons.httpclient.HttpClient5;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.JsonUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.biz.component.identity.IdentityData;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.security.repository.MotpMidDataRepository;
import com.tfb.aibank.biz.security.repository.entities.MotpMidDataEntity;
import com.tfb.aibank.biz.security.services.mid.bean.MidLoginOutputParams;
import com.tfb.aibank.biz.security.services.mid.bean.MidLoginRs;
import com.tfb.aibank.biz.security.services.mid.bean.MidQueryVerifyResultMIDResp;
import com.tfb.aibank.biz.security.services.mid.bean.MidQueryVerifyResultOutputParams;
import com.tfb.aibank.biz.security.services.mid.bean.MidQueryVerifyResultRs;
import com.tfb.aibank.biz.security.services.mid.model.MidLoginRequest;
import com.tfb.aibank.biz.security.services.mid.model.MidLoginResponse;
import com.tfb.aibank.biz.security.services.mid.model.MidQueryVerifyResultRequest;
import com.tfb.aibank.biz.security.services.mid.model.MidQueryVerifyResultResponse;
import com.tfb.aibank.biz.security.services.mid.type.MidDataStatusType;
import com.tfb.aibank.biz.security.services.mid.type.MidParam;
import com.tfb.aibank.biz.security.services.mid.type.MidStatusType;
import com.tfb.aibank.common.error.AIBankErrorCode;

//@formatter:off
/**
* @(#)OpenAPIService.java
* 
* <p>Description:台網MID驗證 - API介接服務</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/05, HankChan   
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class MidService {

    private static final IBLog logger = IBLog.getLog(MidService.class);

    private static final String MEMBER_NO = "9999999999";
    private static final String CONTENT_TYPE_FORM_URLENCODED_UTF8 = "application/x-www-form-urlencoded; charset=utf-8";
    private static final int CONNECTION_TIMEOUT = 60;

    private IdentityService identityService;
    private SystemParamCacheManager systemParamCacheManager;
    private MotpMidDataRepository motpMidDataRepository;

    public MidService(IdentityService identityService, SystemParamCacheManager systemParamCacheManager, MotpMidDataRepository motpMidDataRepository) {
        this.identityService = identityService;
        this.systemParamCacheManager = systemParamCacheManager;
        this.motpMidDataRepository = motpMidDataRepository;
    }

    /**
     * 台網MID驗證 - Login
     * 
     * @param request
     * @return
     * @throws ActionException
     * @throws NoSuchAlgorithmException
     */
    public MidLoginResponse login(MidLoginRequest request) throws ActionException, NoSuchAlgorithmException {

        MidLoginResponse response = new MidLoginResponse();

        // 產生初始MID認證資料
        MotpMidDataEntity midData = generateInitMidData(request);

        // 建立Login參數
        String hashKey = systemParamCacheManager.getValue(MidParam.MOTP_MID_HASH_KEY);
        Map<String, String> formData = buildLoginParam(hashKey);
        logger.info("Mid Service(Login): RqPayload={}", Arrays.toString(formData.entrySet().toArray()));

        // 寫入一筆MID認證資料
        midData.setLoginVerifyNo(formData.getOrDefault("VerifyNo", StringUtils.EMPTY));
        midData.setCreateTime(new Date());
        midData = motpMidDataRepository.save(midData);
        response.setMotpMidKey(midData.getMotpMidKey());

        String loginPath = systemParamCacheManager.getValue(MidParam.MOTP_MID_LOGIN_PATH);
        List<Header> headers = Arrays.asList(new BasicHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_FORM_URLENCODED_UTF8));
        CustomHttpResponse httpResponse = HttpClient5.doPost(loginPath, headers, formData, CONNECTION_TIMEOUT);
        int statusCode = httpResponse.getCode();
        String rsPayload = StringUtils.defaultString(httpResponse.getResponse());
        logger.info("Mid Service(Login): StatusCode={}, RsPayload={}", statusCode, rsPayload);

        if (statusCode == HttpStatus.SC_SUCCESS) {

            MidLoginRs rs = JsonUtils.getObject(rsPayload, MidLoginRs.class);

            // 驗證Response驗證碼
            boolean verifyIdentifyNo = MidUtils.verifyRespIdentifyNo(rs, hashKey);
            if (verifyIdentifyNo) {
                response.setReturnCode(rs.getReturnCode());
                response.setReturnCodeDesc(rs.getReturnCodeDesc());
                if (StringUtils.equals(rs.getReturnCode(), "0")) {
                    midData.setMidStatus(MidDataStatusType.LOGIN_SUCCESS);
                    MidLoginOutputParams outputParams = JsonUtils.getObject(rs.getOutputParams(), MidLoginOutputParams.class);
                    response.setToken(outputParams.getToken());
                    response.setVerifyNo(rs.getVerifyNo());
                    response.setReqSeq(outputParams.getMidOutputParams().getMidaOutputParams().getReqSeq());
                    response.setProfile(outputParams.getMidOutputParams().getMidaOutputParams().getProfile());
                    response.setSessionKey(outputParams.getMidOutputParams().getMidaOutputParams().getSessionKey());
                    response.setClauseVer(systemParamCacheManager.getValue(MidParam.MOTP_MID_CLAUSE_VER));
                    midData.setLoginToken(response.getToken());
                    String tokenTime = outputParams.getTimeStamp();
                    Date tokenCreateTime = new Date();
                    if (StringUtils.isNotBlank(tokenTime)) {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
                            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                            String dateStr = tokenTime.substring(0, tokenTime.length() > 5 ? tokenTime.length() - 5 : tokenTime.length());
                            tokenCreateTime = sdf.parse(dateStr);
                        }
                        catch (ParseException e) {
                            logger.error("轉換通行證生成(MidTokenTimestamp)日期時發生錯誤", e);
                        }
                    }
                    midData.setLoginTokenCreateTime(tokenCreateTime);
                    midData.setMidClauseVer(response.getClauseVer());
                    midData.setMidReqSeq(response.getReqSeq());
                    midData.setMidSessionKey(response.getSessionKey());
                }
                else {
                    midData.setMidStatus(MidDataStatusType.LOGIN_FAIL);
                }
                midData.setLoginResultCode(rs.getResultCode());
                midData.setLoginReturnCode(rs.getReturnCode());
                midData.setLoginReturnDesc(rs.getReturnCodeDesc());
                midData.setUpdateTime(new Date());
                motpMidDataRepository.save(midData);
            }
            else {
                midData.setMidStatus(MidDataStatusType.LOGIN_INDENTITY_NO_INVALID);
                midData.setUpdateTime(new Date());
                motpMidDataRepository.save(midData);
                throw ExceptionUtils.getActionException(AIBankErrorCode.TWID_API_SERVICE_ERROR);
            }
        }
        else {
            midData.setMidStatus(MidDataStatusType.LOGIN_ERROR);
            midData.setUpdateTime(new Date());
            motpMidDataRepository.save(midData);
            throw ExceptionUtils.getActionException(AIBankErrorCode.TWID_API_SERVICE_ERROR);
        }

        return response;
    }

    /**
     * 台網MID驗證 - QueryVerifyResult
     * 
     * @param request
     * @return
     * @throws ActionException
     * @throws NoSuchAlgorithmException
     */
    public MidQueryVerifyResultResponse queryVerifyResult(MidQueryVerifyResultRequest request) throws ActionException, NoSuchAlgorithmException {

        MidQueryVerifyResultResponse response = new MidQueryVerifyResultResponse();

        // 取回MID認證資料 // 【FORTIFY：Access Control: Database】誤判，條件為主鍵
        MotpMidDataEntity midData = motpMidDataRepository.findById(request.getMotpMidKey()).orElse(null);
        if (midData == null) {
            // 查無驗證資訊，請重新操作
            throw new ActionException(AIBankErrorCode.MOTP_MID_VERIFY_KEY_LOST);
        }

        // 更新SDK回傳資料
        String midAuthCode = request.getMidAuthCode();
        String midAuthMsg = request.getMidAuthMsg();
        midData.setMidAuthCode(midAuthCode);
        midData.setMidAuthMsg(midAuthMsg);

        // SDK驗證成功
        if (StringUtils.equals(MidStatusType.MID_SUCCESS.getCode(), midAuthCode)) {
            logger.info("Mid Service(QueryVerifyResult): SDK驗證成功");
            midData.setMidStatus(MidDataStatusType.MID_AUTH_SUCCESS);
            midData.setUpdateTime(new Date());
            motpMidDataRepository.save(midData);
        }
        // SDK驗證失敗
        else {
            logger.info("Mid Service(QueryVerifyResult): SDK驗證失敗。MidAuthCode={}, MidAuthMsg={}", midAuthCode, midAuthMsg);
            midData.setMidStatus(MidDataStatusType.MID_AUTH_FAIL);
            response.setReturnCode(midAuthCode);
            response.setReturnCodeDesc(midAuthMsg);
            midData.setUpdateTime(new Date());
            motpMidDataRepository.save(midData);
            return response;
        }

        String hashKey = systemParamCacheManager.getValue(MidParam.MOTP_MID_HASH_KEY);
        Map<String, String> formData = buildQueryVerifyResultParam(hashKey, midData.getLoginVerifyNo(), midData.getLoginToken());
        logger.info("Mid Service(QueryVerifyResult): RqPayload={}", Arrays.toString(formData.entrySet().toArray()));

        String urlString = systemParamCacheManager.getValue(MidParam.MOTP_MID_VERIFY_RESULT_PATH);
        List<Header> headers = Arrays.asList(new BasicHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_FORM_URLENCODED_UTF8));
        CustomHttpResponse httpResponse = HttpClient5.doPost(urlString, headers, formData, CONNECTION_TIMEOUT);
        int statusCode = httpResponse.getCode();
        String rsPayload = StringUtils.defaultString(httpResponse.getResponse());
        logger.info("Mid Service(QueryVerifyResult): StatusCode={}, RsPayload={}", statusCode, rsPayload);

        if (statusCode == HttpStatus.SC_SUCCESS) {

            MidQueryVerifyResultRs rs = JsonUtils.getObject(rsPayload, MidQueryVerifyResultRs.class);

            // 驗證Response驗證碼
            boolean verifyIdentifyNo = MidUtils.verifyRespIdentifyNo(rs, hashKey);
            if (verifyIdentifyNo) {
                response.setReturnCode(rs.getReturnCode());
                response.setReturnCodeDesc(rs.getReturnCodeDesc());
                if (StringUtils.equals(rs.getReturnCode(), "0")) {
                    midData.setMidStatus(MidDataStatusType.VERIFY_SUCCESS);
                    MidQueryVerifyResultOutputParams outputParams = JsonUtils.getObject(rs.getOutputParams(), MidQueryVerifyResultOutputParams.class);
                    response.setVerifyTime(outputParams.getVerifyTime());
                    MidQueryVerifyResultMIDResp midResp = JsonUtils.getObject(outputParams.getMidParams().getMidResp(), MidQueryVerifyResultMIDResp.class);
                    midData.setVerifyRspSeq(midResp.getRspSeq());
                    String rspTimeStr = midResp.getRspTime();
                    Date rspTime = new Date();
                    if (StringUtils.isNotBlank(rspTimeStr)) {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
                            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                            String dateStr = rspTimeStr.substring(0, rspTimeStr.length() > 5 ? rspTimeStr.length() - 5 : rspTimeStr.length());
                            rspTime = sdf.parse(dateStr);
                        }
                        catch (ParseException e) {
                            logger.error("轉換通行證生成(MIDResp.RspTime)日期時發生錯誤", e);
                        }
                    }
                    midData.setVerifyRspTime(rspTime);
                    midData.setVerifyMidCode(midResp.getCode());
                    midData.setVerifyMidMsg(midResp.getMessage());
                }
                else {
                    midData.setMidStatus(MidDataStatusType.VERIFY_FAIL);
                }
                midData.setVerifyResultCode(rs.getResultCode());
                midData.setVerifyReturnCode(rs.getReturnCode());
                midData.setVerifyReturnDesc(rs.getReturnCodeDesc());
                midData.setUpdateTime(new Date());
                motpMidDataRepository.save(midData);
            }
            else {
                midData.setMidStatus(MidDataStatusType.VERIFY_INDENTITY_NO_INVALID);
                midData.setUpdateTime(new Date());
                motpMidDataRepository.save(midData);
                throw ExceptionUtils.getActionException(AIBankErrorCode.TWID_API_SERVICE_ERROR);
            }
        }
        else {
            midData.setMidStatus(MidDataStatusType.VERIFY_ERROR);
            midData.setUpdateTime(new Date());
            motpMidDataRepository.save(midData);
            throw ExceptionUtils.getActionException(AIBankErrorCode.TWID_API_SERVICE_ERROR);
        }

        return response;
    }

    /**
     * 產生初始MID認證資料
     * 
     * @param request
     * @return
     * @throws ActionException
     */
    private MotpMidDataEntity generateInitMidData(MidLoginRequest request) throws ActionException {

        IdentityData identityData = getUser(request.getCustId(), request.getUserId(), request.getCompanyKind(), request.getUidDup());

        MotpMidDataEntity midData = new MotpMidDataEntity();
        midData.setCompanyKey(identityData.getCompanyKey());
        midData.setUserKey(identityData.getUserKey());
        midData.setDeviceInfoKey(request.getDeviceInfoKey());
        midData.setDeviceUuid(request.getDeviceIxd());
        midData.setMidStatus(MidDataStatusType.INIT);
        midData.setLoginMemberNo(request.getCustId());
        midData.setLoginMobileNo(request.getMobileNo());

        return midData;
    }

    /**
     * 建立Login參數
     * 
     * @param hashKey
     * @return
     * @throws NoSuchAlgorithmException
     */
    private Map<String, String> buildLoginParam(String hashKey) throws NoSuchAlgorithmException {
        Map<String, String> formData = new HashMap<>();
        String businessNo = systemParamCacheManager.getValue(MidParam.MOTP_MID_BUSSINESS_NO);
        String apiVersion = systemParamCacheManager.getValue(MidParam.MOTP_MID_API_VERSION);
        String hashKeyNo = systemParamCacheManager.getValue(MidParam.MOTP_MID_HASH_KEY_NO);
        String verifyNo = getVerifyNo();
        String inputParams = getInputParams();
        String identifyNo = MidUtils.getIdentifyNo(businessNo + apiVersion + hashKeyNo + verifyNo + inputParams + hashKey);
        formData.put("BusinessNo", businessNo);
        formData.put("ApiVersion", apiVersion);
        formData.put("HashKeyNo", hashKeyNo);
        formData.put("VerifyNo", verifyNo);
        formData.put("InputParams", inputParams);
        formData.put("IdentifyNo", identifyNo);
        return formData;
    }

    /**
     * 建立QueryVerifyResult參數
     * 
     * @param hashKey
     * @param verifyNo
     * @param token
     * @return
     * @throws NoSuchAlgorithmException
     */
    private Map<String, String> buildQueryVerifyResultParam(String hashKey, String verifyNo, String token) throws NoSuchAlgorithmException {
        Map<String, String> formData = new HashMap<>();
        String businessNo = systemParamCacheManager.getValue(MidParam.MOTP_MID_BUSSINESS_NO);
        String apiVersion = systemParamCacheManager.getValue(MidParam.MOTP_MID_API_VERSION);
        String hashKeyNo = systemParamCacheManager.getValue(MidParam.MOTP_MID_HASH_KEY_NO);
        String identifyNo = MidUtils.getIdentifyNo(businessNo + apiVersion + hashKeyNo + verifyNo + MEMBER_NO + token + hashKey);
        formData.put("BusinessNo", businessNo);
        formData.put("ApiVersion", apiVersion);
        formData.put("HashKeyNo", hashKeyNo);
        formData.put("VerifyNo", verifyNo);
        formData.put("MemberNo", MEMBER_NO);
        formData.put("Token", token);
        formData.put("IdentifyNo", identifyNo);
        return formData;
    }

    /**
     * 取得驗證編號
     * 
     * @return
     */
    private String getVerifyNo() {
        // 目前產生為45碼 前14碼時戳，後32碼亂數字串
        long currnetTime = System.currentTimeMillis();
        // return currnetTime + RandomStringUtils.random(32, "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray());

        return currnetTime + UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 以下參數以 JSON「字串」存放於此，並請依順序加入
     * 
     * @return
     */
    private String getInputParams() {
        HashMap<String, Object> inputParams = new LinkedHashMap<>();
        // 不傳給台網真實的user身分證資訊，僅傳10個9代替，台網不會驗證此欄位
        inputParams.put("MemberNo", MEMBER_NO);
        inputParams.put("MemberNoType", "0");
        inputParams.put("Action", "SIGN");
        inputParams.put("Plaintext", MEMBER_NO);
        inputParams.put("CAType", "9");
        inputParams.put("AssignCertPassword", StringUtils.EMPTY);
        inputParams.put("ErrCodeType", "1");
        inputParams.put("IsCpntProcess", "N");
        inputParams.put("MIDInputParams", getMIDInputParams());
        return new Gson().toJson(inputParams);
    }

    /**
     * InputParams 子項目 （當 Action 為 SIGN 且 CAType 包含 8、9 時需存在）[]
     * 
     * @return
     */
    private HashMap<String, String> getMIDInputParams() {
        HashMap<String, String> midInputParams = new LinkedHashMap<>();
        midInputParams.put("Platform", "1");
        return midInputParams;
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
    private IdentityData getUser(String custId, String userId, int companyKind, String uidDup) throws ActionException {
        try {
            IdentityData identityData = identityService.query(custId, uidDup, userId, companyKind);
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