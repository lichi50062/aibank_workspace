package com.tfb.aibank.biz.user.services.sso;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.xmlbeans.XmlException;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.exception.DatabaseException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.AESUtils;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.JsonUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.component.crypto.AESCipherUtils;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProvider;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProviderFactory;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.ibm.tw.ibmb.util.ValidateParamUtils;
import com.tfb.aibank.biz.component.identity.IdentityData;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.user.gateway.EsbChannelGateway;
import com.tfb.aibank.biz.user.repository.AiSsoAuthDataRepository;
import com.tfb.aibank.biz.user.repository.AiSsoSettingRepository;
import com.tfb.aibank.biz.user.repository.CompanyRepository;
import com.tfb.aibank.biz.user.repository.MbDeviceInfoRepository;
import com.tfb.aibank.biz.user.repository.MbGestureProfileRepository;
import com.tfb.aibank.biz.user.repository.UserLoginRepository;
import com.tfb.aibank.biz.user.repository.UserRepository;
import com.tfb.aibank.biz.user.repository.entities.AiSsoAuthDataEntity;
import com.tfb.aibank.biz.user.repository.entities.AiSsoSettingEntity;
import com.tfb.aibank.biz.user.repository.entities.CompanyEntity;
import com.tfb.aibank.biz.user.repository.entities.MbDeviceInfoEntity;
import com.tfb.aibank.biz.user.repository.entities.MbGestureProfileEntity;
import com.tfb.aibank.biz.user.repository.entities.UserEntity;
import com.tfb.aibank.biz.user.repository.entities.UserLoginEntity;
import com.tfb.aibank.biz.user.resource.SecurityResource;
import com.tfb.aibank.biz.user.resource.model.EncodeWithSecretRequest;
import com.tfb.aibank.biz.user.resource.model.EncodeWithSecretResponse;
import com.tfb.aibank.biz.user.services.login.model.ExecuteUserLoginRequest;
import com.tfb.aibank.biz.user.services.sso.model.DoFastValidateUserRequest;
import com.tfb.aibank.biz.user.services.sso.model.DoFastValidateUserResponse;
import com.tfb.aibank.biz.user.services.sso.model.FastLoginGetTokenResponse;
import com.tfb.aibank.biz.user.services.sso.model.GenerateTokenRequest;
import com.tfb.aibank.biz.user.services.sso.model.GenerateTokenResponse;
import com.tfb.aibank.biz.user.services.sso.model.GetFastValidateUserRequest;
import com.tfb.aibank.biz.user.services.sso.model.GetFastValidateUserResponse;
import com.tfb.aibank.biz.user.services.sso.model.GetSsoSettingRequest;
import com.tfb.aibank.biz.user.services.sso.model.GetSsoSettingResponse;
import com.tfb.aibank.biz.user.services.sso.model.GetTokenRequest;
import com.tfb.aibank.biz.user.services.sso.model.GetTokenResponse;
import com.tfb.aibank.biz.user.services.sso.model.SsoUserData;
import com.tfb.aibank.chl.session.type.CustomerType;
import com.tfb.aibank.chl.type.PwType;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.common.type.CompanyKindType;
import com.tfb.aibank.common.type.E2EEHsmType;
import com.tfb.aibank.common.type.SsoStatusType;
import com.tfb.aibank.common.type.TxStatusType;
import com.tfb.aibank.common.type.UserStatusType;
import com.tfb.aibank.common.util.BizExceptionUtils;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIResponseException;
import com.tfb.aibank.integration.eai.msg.CEW013RRS;
import com.tfb.aibank.integration.eai.msg.EB5556981RS;

import tw.com.ibm.mf.eb.CEW013RSvcRsType;

//@formatter:off
/**
* @(#)SsoAuthService.java 
* 
* <p>Description:SSO/快速身份認證</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 2024/01/17, JohnChang
*  <li>初版</li6
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on
public class SsoAuthService {

    private static final IBLog logger = IBLog.getLog(SsoAuthService.class);

    private IdentityService identityService;

    private AiSsoAuthDataRepository aiSsoAuthDataRepository;

    private AiSsoSettingRepository aiSsoSettingRepository;

    private SystemParamCacheManager systemParamCacheManager;

    private MbDeviceInfoRepository mbDeviceInfoRepository;

    private CompanyRepository companyRepository;

    private UserRepository userProfileRepository;

    private MbGestureProfileRepository mbGestureProfileRepository;

    private SecurityResource securityResource;

    private UserLoginRepository userLoginRepository;

    private EsbChannelGateway esbGateway;
    
    private AESCipherUtils aesCipherUtils = null;


    private static final int MAX_PASSWORD_FAIL_COUNT = 3;

    public SsoAuthService(IdentityService identityService, AiSsoAuthDataRepository aiSsoAuthDataRepository, AiSsoSettingRepository aiSsoSettingRepository, SystemParamCacheManager systemParamCacheManager, MbDeviceInfoRepository mbDeviceInfoRepository, CompanyRepository companyRepository, UserRepository userProfileRepository, MbGestureProfileRepository mbGestureProfileRepository, SecurityResource securityResource, UserLoginRepository userLoginRepository, EsbChannelGateway esbGateway) {

        this.identityService = identityService;
        this.aiSsoAuthDataRepository = aiSsoAuthDataRepository;
        this.aiSsoSettingRepository = aiSsoSettingRepository;
        this.systemParamCacheManager = systemParamCacheManager;
        this.mbDeviceInfoRepository = mbDeviceInfoRepository;
        this.companyRepository = companyRepository;
        this.userProfileRepository = userProfileRepository;
        this.mbGestureProfileRepository = mbGestureProfileRepository;
        this.securityResource = securityResource;
        this.userLoginRepository = userLoginRepository;
        this.esbGateway = esbGateway;
    }

    /**
     * 登入前用，忽略 SSO_FLAG 欄位，取得 AI_SSO_SETTING 資料
     * 
     * @param request
     * @return
     */
    public GetSsoSettingResponse getSsoSetting(GetSsoSettingRequest request) {
        GetSsoSettingResponse response = new GetSsoSettingResponse();

        // 【FORTIFY：Access Control: Database】誤判，條件為主鍵
        AiSsoSettingEntity setting = aiSsoSettingRepository.findByChannelKey(ValidateParamUtils.validParam(request.getChannelKey()));

        if (setting == null) {
            response.setStatus("0");
            response.setOpenUrl(request.getChannelKey());
            return response;
        }

        response.setStatus("1");
        response.setOpenUrl(setting.getOpenUrl());
        response.setModuleType(setting.getModuleType());
        response.setOpenType(setting.getOpenType());
        response.setCustomParam(setting.getCustomeParam());
        response.setSsoFlag("N");
        response.setModuleParam(convertModuleParam(setting.getModuleParam()));
        return response;

    }

    /**
     * 登入後用
     * 
     * @param request
     * @return
     * @throws ActionException
     */
    public GenerateTokenResponse generateToken(GenerateTokenRequest request) throws ActionException {
        GenerateTokenResponse response = new GenerateTokenResponse();

        SsoUserData userData = new SsoUserData();

        try {
            IdentityData identityData = identityService.query(request.getCustId(), request.getUidDup(), request.getUserId(), request.getCompanyKind());

            // 【FORTIFY：Access Control: Database】誤判，條件為主鍵
            AiSsoSettingEntity setting = aiSsoSettingRepository.findByChannelKey(ValidateParamUtils.validParam(request.getChannelKey()));

            if (setting == null) {
                response.setStatus("0");
                return response;
            }

            /** 有設定，但無需 SSO */
            if ("N".equals(setting.getSsoFlag())) {
                response.setStatus("1");
                response.setOpenUrl(setting.getOpenUrl());
                response.setModuleType(setting.getModuleType());
                response.setOpenType(setting.getOpenType());
                response.setSsoFlag(setting.getSsoFlag());
                response.setCustomParam(setting.getCustomeParam());
                response.setChannelName(setting.getChannelName());
                response.setModuleParam(convertModuleParam(setting.getModuleParam()));
                return response;

            }

            /** SSO */
            boolean needLoginData =  setting.getLoginDataFlag() != null  && "Y".equalsIgnoreCase(setting.getLoginDataFlag()) ? true: false;
            
            String token = UUID.randomUUID().toString().replace("-", "");

            /** User Data JSON **/
            userData.setUid(request.getCustId());
            userData.setUidDup(request.getUidDup());
            userData.setUuid(request.getUserId());
            userData.setNameCode(request.getNameCode());
            userData.setCompanyKind(Integer.toString(request.getCompanyKind()));
            userData.setCompanyKey(Long.toString(identityData.getCompanyKey()));
            userData.setUserKey(Long.toString(identityData.getUserKey()));
            if (needLoginData) {
                userData.setLoginData(request.getLoginData() == null ? "" : JsonUtils.getJson(request.getLoginData()));
            }
            /** 儲存到 AI_AUTH_DATA */
            AiSsoAuthDataEntity authData = new AiSsoAuthDataEntity();
            Calendar date = Calendar.getInstance();
            long timeInSecs = date.getTimeInMillis();
            Date after5Mins = new Date(timeInSecs + (5 * 60 * 1000));

            /** AI_SSO_AUTH_DATA */
            authData.setChannelId(setting.getChannelId());
            authData.setCreateTime(date.getTime());
            authData.setExpiredTime(after5Mins);
            authData.setLoginType(getLoginType(request.getCompanyKind()));
            authData.setToken(token);
            authData.setUserUuid(request.getUserId());
            // 20250223 先調整成NoIV加密方式 Ice
            // 20250303 外轉內SSO 固定 NoIV, 內轉外保留原本機制
            String userDataStr = JsonUtils.getJson(userData);
            logger.debug("SsoAuthService GenerateToken UserData: "+ userDataStr);
            if (request.isFromFastLogin()) {
                authData.setUserData(encryptNoIv(userDataStr));
            }else {
                if (StringUtils.equals("FASTLOGIN", request.getType())) {
                    authData.setUserData(encryptNoIv(userDataStr));
                }
                else {
                    authData.setUserData(encrypt(userDataStr));
                }                
            }

            aiSsoAuthDataRepository.save(authData);

            response.setChannelId(setting.getChannelId());
            response.setCustomParam(setting.getCustomeParam());
            response.setFunc(setting.getFunc());
            response.setSourceFrom("AI");
            response.setTxId("AIBANK");
            response.setToken(token);
            response.setOpenType(setting.getOpenType());
            response.setSsoFlag(setting.getSsoFlag());
            response.setOpenUrl(setting.getOpenUrl());
            response.setModuleType(setting.getModuleType());
            response.setChannelName(setting.getChannelName());
            response.setStatus("2");
            response.setModuleParam((convertModuleParam(setting.getModuleParam())));

            String tt = "FBBONUS01--SSOLOGIN--" + token;

            logger.debug(encrypt(tt));
        }
        catch (CryptException e) {
            logger.error("Generate Token Failed", e);
            response.setStatus(TxStatusType.FAIL.getCode());
        }

        return response;

    }

    public GetTokenResponse getToken(GetTokenRequest request) throws ActionException, CryptException {
        GetTokenResponse response = new GetTokenResponse();
        // Fortify - Critical - Privacy Violation log 打印調整
        logger.debug("SSO_AUTH: Encrypted  start" + " , platformId = " + request.getPlatformId());

        String rawToken = decrypt(request.getSignature());
        // Fortify - Critical - Privacy Violation log 打印調整
        logger.debug("SSO_AUTH: Encrypted Token end");
        String[] args = rawToken.split("--");
        // 目標通路
        // 目標
        // Token

        if (args.length != 3) {
            response.setStatus(TxStatusType.FAIL.getCode());
            return response;
        }

        AiSsoAuthDataEntity authData = aiSsoAuthDataRepository.findByToken(args[2]);

        if (authData == null) {
            response.setStatus(TxStatusType.FAIL.getCode());
            logger.error("SSO_AUTH_RESPONSE_ERROR: {}", JsonUtils.getJson(response));
            return response;
        }

        response.setLoginType(authData.getLoginType());
        response.setUserData(authData.getUserData());
        response.setUserUuid(authData.getUserUuid());

        response.setStatus(TxStatusType.SUCCESS.getCode());

        logger.debug("SSO_AUTH_RESPONSE:{}", JsonUtils.getJson(response));

        return response;

    }

    public FastLoginGetTokenResponse getSsoToken(GetTokenRequest request) throws ActionException, CryptException {
        FastLoginGetTokenResponse response = new FastLoginGetTokenResponse();

        logger.debug("SSO_AUTH: Encrypted Token = " + request.getSignature() + " , platformId = " + request.getPlatformId());

        String rawToken = decryptNoIv(request.getSignature());
        logger.info("SSO_AUTH: Encrypted Token = " + rawToken + " , Token = " + rawToken);
        String[] args = rawToken.split("--");
        // 目標通路
        // 目標
        // Token

        if (args.length != 4) {
            response.setStatusCode("3000");
            response.setStatusDesc("解密資訊不相符、解密失敗");
            return response;
        }

        AiSsoAuthDataEntity authData = aiSsoAuthDataRepository.findByToken(args[3]);

        if (authData == null) {
            response.setStatusCode("1000");
            response.setStatusDesc("解密資訊不相符、解密失敗");
            logger.error("SSO_AUTH_RESPONSE_ERROR: {}", JsonUtils.getJson(response));
            return response;
        }

        response.setLoginType(authData.getLoginType());
        response.setUserData(authData.getUserData());
        response.setUserUuid(authData.getUserUuid());

        response.setStatusCode("0000");
        response.setStatusDesc("成功");

        logger.debug("SSO_AUTH_RESPONSE:{}", JsonUtils.getJson(response));

        return response;

    }

    private String encryptForUid(String uid) throws ActionException {
        if (aesCipherUtils == null) {
            SecretKeyProvider<?> provider = SecretKeyProviderFactory.getInstance().getProvider(AIBankConstants.CRYPTO_SECRET_KEY_PROVIDER_DEFAULT);
            aesCipherUtils = new AESCipherUtils(provider);
        }
        try {
            return aesCipherUtils.encrypt(uid);
        }
        catch (CryptException e) {
            logger.error(e.getMessage(), e);
            throw BizExceptionUtils.getActionException(e);
        }
    }

    private String encrypt(String raw) throws ActionException, CryptException {
        String k = systemParamCacheManager.getValue("AIBANK", "SSO_KEY", "");
        String v = systemParamCacheManager.getValue("AIBANK", "SSO_IV", "");
        if (StringUtils.isBlank(raw) || StringUtils.isBlank(k) || StringUtils.isBlank(v)) {
            throw new ActionException(AIBankErrorCode.ARG_NOT_PASS_IN);
        }

        byte[] data = AESUtils.encryptToBytes(raw.getBytes(), k.getBytes(), v.getBytes());

        return new String(Base64.getEncoder().encode(data), Charset.defaultCharset());
    }
    
    

    private String encryptNoIv(String raw) throws ActionException, CryptException {
        String k = systemParamCacheManager.getValue("AIBANK", "SSO_FASTLOGIN_KEY", "");

        byte[] data = AESUtils.encryptToBytes(raw.getBytes(), k.getBytes());

        return new String(Base64.getEncoder().encode(data), StandardCharsets.UTF_8);
    }

    private String decrypt(String raw) throws ActionException, CryptException {
        String k = systemParamCacheManager.getValue("AIBANK", "SSO_KEY", "");
        String v = systemParamCacheManager.getValue("AIBANK", "SSO_IV", "");

        byte[] encryptedRaw = Base64.getDecoder().decode(raw);

        byte[] data = AESUtils.decryptToBytes(encryptedRaw, k.getBytes(StandardCharsets.UTF_8), v.getBytes(StandardCharsets.UTF_8));

        return new String(data, Charset.defaultCharset());
    }

    private String decryptNoIv(String raw) throws ActionException, CryptException {
        String k = systemParamCacheManager.getValue("AIBANK", "SSO_FASTLOGIN_KEY", "");

        byte[] encryptedRaw = Base64.getDecoder().decode(raw);

        byte[] data = AESUtils.decryptToBytes(encryptedRaw, k.getBytes(StandardCharsets.UTF_8));

        return new String(data, StandardCharsets.UTF_8);
    }

    private String getLoginType(int companyKind) {
        if (companyKind == 1 || companyKind == 2) {
            return CustomerType.GENERAL.getLoginType();
        }
        if (companyKind == 3 || companyKind == 4) {
            return CustomerType.CARDMEMBER.getLoginType();
        }
        return CustomerType.GENERAL.getLoginType();

    }

    /**
     * 檢查快速身分認證資料
     * 
     * @param request
     * @return
     */
    public GetFastValidateUserResponse getFastValidateUser(GetFastValidateUserRequest request) {
        GetFastValidateUserResponse response = new GetFastValidateUserResponse();

        /* 有帶 ID */
        if (StringUtils.isNotBlank(request.getPlatformUserVerify())) {
            try {
                response.setCustId(decrypt(request.getPlatformUserVerify()));
            }
            catch (ActionException | CryptException ex) {
                logger.error("", ex);
                response.setStatusCode(SsoStatusType.NOT_REGISTERED.getStatusCode());
                return response;
            }
        }

        if (StringUtils.isBlank(request.getPlatformId())) {
            response.setStatusCode(SsoStatusType.NOT_REGISTERED.getStatusCode());
            return response;
        }

        String[] channels = request.getPlatformId().split("--");

        String channelId = channels[0];
        String func = "";

        if (channels.length == 2) {
            func = channels[1];
        }

        /** 查找 AI_SSO_SETTING */
        // 【FORTIFY：Access Control: Database】誤判，此查詢與個人資訊無關，不用帶入身分識別屬性欄位
        List<AiSsoSettingEntity> aiSsoSettingEntities = aiSsoSettingRepository.findByChannelId(ValidateParamUtils.validParam(channelId));

        if (aiSsoSettingEntities == null || aiSsoSettingEntities.size() == 0) {
            response.setStatusCode(SsoStatusType.NOT_REGISTERED.getStatusCode());
            return response;
        }

        AiSsoSettingEntity aiSsoSettingEntity = null;

        // 7881  
        if (StringUtils.isBlank(func)) {
            response.setStatusCode(SsoStatusType.NOT_REGISTERED.getStatusCode());
            return response;
        }

        for (AiSsoSettingEntity entity : aiSsoSettingEntities) {
//            if (StringUtils.equals(entity.getFunc(), "FASTLOGIN")) {
          if (StringUtils.equals(entity.getFunc(), func)) {
                aiSsoSettingEntity = entity;
            }
        }

        if (aiSsoSettingEntity == null) {
            response.setStatusCode(SsoStatusType.NOT_REGISTERED.getStatusCode());
            return response;
        }

        response.setAuthType(aiSsoSettingEntity.getAuthType());
        response.setStatusCode(SsoStatusType.SUCCESS.getStatusCode());
        response.setChannelKey(aiSsoSettingEntity.getChannelKey());
        response.setOpenType(aiSsoSettingEntity.getOpenType());
        response.setModuleType(aiSsoSettingEntity.getModuleType());
        response.setModuleParam(convertModuleParam(aiSsoSettingEntity.getModuleParam()));
        response.setFunc(func);
        return response;
    }

    /**
     * 快速身分認證
     * 
     * @param request
     * @return
     * @throws ActionException
     */
    public DoFastValidateUserResponse doFastValidateUser(DoFastValidateUserRequest request) throws ActionException {
        DoFastValidateUserResponse response = new DoFastValidateUserResponse();
        
        boolean isQuickLogin = StringUtils.equalsIgnoreCase("Y",  request.getIsSignatureLogin());
        String statusCode = SsoStatusType.SUCCESS.getStatusCode();
        
        if (isQuickLogin && PwType.isPattern(request.getPwType())) {
            // 手勢密碼驗證
            try {
                statusCode = verifyGesturesPwd(request);
            }
            catch (ActionException ex) {
                statusCode = SsoStatusType.FAST_LOGIN_FAIL.getStatusCode();
                logger.error("手勢密碼驗證錯誤 {}", ex);
            }
            if (!SsoStatusType.isSuccessful(statusCode)) {
                response.setStatusCode(statusCode);
                return response;
            }
        }

        LoginStatus status = new LoginStatus();
         
        IdentityData identityData = null;
        Integer userKey = null;
        String uidDup = request.getUidDup();
        Integer companyKind = request.getCompanyKind();
        
        if (isQuickLogin) {//  快登流程
            identityData = getUser(request.getCustId(), request.getUidDup(), request.getUserId(), request.getCompanyKind());
            if (identityData == null) {
                logger.error("查無使用者 {}", request.getCustId());
                response.setStatusCode(SsoStatusType.FAST_LOGIN_FAIL.getStatusCode());
                return response;
            }
            if (CustomerType.isCardMember(request.getLoginType())) {
                status = validateCardMember(request, identityData.getUserKey(), false);
            }else {
                status = validateGeneralMember(request);
            }
            response.setStatusCode(status.getStatusCode());
            response.setMobileNo(status.getMobileNo());
            userKey = identityData.getUserKey();
        } else {
            // 3 層式
            List<UserEntity> queryLoginUser = queryLoginUser(request.getCustId(), request.getUserId(), request.getLoginType());
            if (CollectionUtils.isEmpty(queryLoginUser)) {
                logger.error("查無使用者 {}", request.getCustId());
                response.setStatusCode(SsoStatusType.FAST_LOGIN_FAIL.getStatusCode());
                return response;
            }
            // 信用卡用戶
            if (CustomerType.isCardMember(request.getLoginType())) {
                boolean found = false;
                for (UserEntity user: queryLoginUser) {
                    status = validateCardMember(request, user.getUserKey(), true);
                    if (SsoStatusType.SUCCESS.getStatusCode().equals(status.getStatusCode())){
                        CompanyEntity company = companyRepository.findByCompanyKey(user.getCompanyKey());
                        companyKind = company.getCompanyKind();
                        uidDup = company.getUidDup();
                        userKey = user.getUserKey();
                        found = true;
                        break;
                    }
                }
                response.setCompanyKind(companyKind);
                response.setIddup(uidDup);           
                response.setMobileNo(status.getMobileNo());
                response.setStatusCode(found ? status.getStatusCode() : SsoStatusType.FAST_LOGIN_FAIL.getStatusCode());
            } else { // 一般用戶 
                boolean found = false;
                status = validateGeneralMember(request);
                // IDDUP, COMPANYKIND
                for (UserEntity user: queryLoginUser) {
                    CompanyEntity company = companyRepository.findByCompanyKey(user.getCompanyKey());
                    if (StringUtils.equals(status.getIdno(), getCompanyUidDup(company))) {
                        // 有找到重覆碼的資料
                        userKey = user.getUserKey();
                        uidDup = company.getUidDup();
                        companyKind = company.getCompanyKind();
                        response.setMobileNo(status.getMobileNo());
                        response.setCompanyKind(companyKind);
                        response.setIddup(uidDup);
                        found = true;
                        break;
                    }
                }
                response.setStatusCode(found ? status.getStatusCode() : SsoStatusType.FAST_LOGIN_FAIL.getStatusCode());
            }
        }
        // 產 Token
        if (SsoStatusType.isSuccess(statusCode)) {
            String nameCode = "0001";
            try {
                Optional<UserEntity> user = userProfileRepository.findById(userKey);
                if (user.isPresent()) {
                    nameCode = user.get().getNameCode();
                }
                 
                GenerateTokenRequest req = new GenerateTokenRequest();
                req.setCustId(request.getCustId());
                req.setUserId(request.getUserId());
                req.setCompanyKind(companyKind);
                req.setUidDup(uidDup);
                req.setChannelKey(request.getChannelKey());
                req.setNameCode(nameCode);
                req.setType(request.getTokenType());
                req.setFromFastLogin(true);
                GenerateTokenResponse res = generateToken(req);
                response.setToken(res.getToken());
                response.setChannelName(res.getChannelName());
            }
            catch (Exception e) { // 【Fortify：Poor Error Handling: Overly Broad Throws】需要攔截所有例外
                logger.error("generate token failed ",e);
                response.setStatusCode(SsoStatusType.UNKNOWN.getStatusCode());
            }
        }
        return response;

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
        catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
//        throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
    }

    /**
     * 驗證信用卡戶
     * 
     * @param request
     * @param identityData
     * @return
     */
    private LoginStatus validateCardMember(DoFastValidateUserRequest request, Integer userKey, boolean verifySecret) {

        LoginStatus status = new LoginStatus();
        try {
            UserEntity userEntity = userProfileRepository.findByUserKey(userKey);
            if (userEntity == null) {
                status.setStatusCode(SsoStatusType.DEVICE_NOT_BINDED.getStatusCode());
                return status;
            }
            
            UserLoginEntity userLoginEntity = userLoginRepository.findByUserKey(userKey);
            if (userLoginEntity == null) {
                status.setStatusCode(SsoStatusType.DEVICE_NOT_BINDED.getStatusCode());
                return status;
            }

            // 密碼錯誤已超過最大次數
            if (userLoginEntity.getPwdErrorCount() >= MAX_PASSWORD_FAIL_COUNT) {
                logger.error("密碼錯誤已超過最大次數");
                status.setStatusCode(SsoStatusType.FAST_LOGIN_LOACKED.getStatusCode());
                return status;
            }
            // 使用者狀態
            else if (UserStatusType.ALIVE.getCode() != userEntity.getStatus()) {
                logger.error("使用者狀態非使用中");
                status.setStatusCode(SsoStatusType.CARD_MEMBER_ONLY.getStatusCode());
                return status;
            }
            
            if (verifySecret) {
                String proSecretTmp = this.encodeSecretE2EE(request, E2EEHsmType.DB_3DES_UTF8_PWD_ENHANCE);
                String  proSecret = proSecretTmp.split("@")[0];
                String  proSecretAdvanced = proSecretTmp.split("@")[1];
                if (securityResource.isCCUserLoginWithNewPassword(userEntity.getPwdFlag() == null ? "X" : userEntity.getPwdFlag(), request.getCustId())) {
                    if (!StringUtils.equalsIgnoreCase(proSecretAdvanced, userEntity.getPasswordAdvanced())) {
                        status.setStatusCode(SsoStatusType.FAST_LOGIN_FAIL.getStatusCode());
                        return status;
                    }
                }
                else {
                    if (!StringUtils.equalsIgnoreCase(proSecret, userEntity.getSecret())) {
                        status.setStatusCode(SsoStatusType.FAST_LOGIN_FAIL.getStatusCode());
                        return status;
                    }
                }
            }
        }
        catch (Exception ex) { // 【Fortify：Poor Error Handling: Overly Broad Throws】需要攔截所有例外
            logger.warn("驗證信用卡戶錯誤 {}", ex);
            status.setStatusCode(SsoStatusType.FAST_LOGIN_FAIL.getStatusCode());
            return status;
        }
        status.setStatusCode(SsoStatusType.SUCCESS.getStatusCode());
        status.setMobileNo(getCEW013RMobile(request.getCustId()));
        return status;

    }

    /**
     * 驗證一般會員
     * 
     * @param request
     * @param identityData
     * @return
     * @throws
     */
    private LoginStatus validateGeneralMember(DoFastValidateUserRequest request) {
        LoginStatus status = new LoginStatus();
        String mobileNo = "";
        String idno  = "";
        
        try {
            EB5556981RS rs = sendEB5556981(request, "登入");
            mobileNo = rs.getBody().getMOBILENO();
            idno = rs.getBody().getIDNO();
        }
        catch (EAIResponseException es) {
            status.setStatusCode(SsoStatusType.FAST_LOGIN_FAIL.getStatusCode());
            return status;
        }
        catch (ActionException | CryptException | XmlException | EAIException e) {
            logger.error("EB5556981", e);
            status.setStatusCode(SsoStatusType.FAST_LOGIN_FAIL.getStatusCode());
            return status;
        }

        status.setStatusCode(SsoStatusType.SUCCESS.getStatusCode());
        status.setMobileNo(mobileNo);
        status.setIdno(idno);
        return status;
    }

    /**
     * 登入交易
     * 
     * @param request
     * @param memo
     * @param cryptoProxy
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     * @throws CryptException
     * @throws ActionException
     */
    private EB5556981RS sendEB5556981(DoFastValidateUserRequest request, String memo) throws CryptException, XmlException, EAIException, EAIResponseException, ActionException {
        String cipherPass = "";
        String encAcnoID = "";

        ExecuteUserLoginRequest executeUserLoginRequest = new ExecuteUserLoginRequest();
        executeUserLoginRequest.setUid(request.getCustId());
        executeUserLoginRequest.setUuid(request.getUserId());
        
        executeUserLoginRequest.setPwType(request.getPwType());
//        executeUserLoginRequest.setIsSignatureLogin(PwType.isBio(request.getPwType()) ? "Y" : "N");
        executeUserLoginRequest.setIsSignatureLogin(request.getIsSignatureLogin());
        
        if (  ! request.isQuickLogin() ) {
           // 一般登入
            String encodedPwd = encodeSecretE2EE(request, E2EEHsmType.PWD_EB5556981_CP1047);
            cipherPass = encodedPwd.split("@")[0];
            encAcnoID = encodedPwd.split("@")[1];
        }
        
        return esbGateway.sendEB5556981(executeUserLoginRequest, "登入", cipherPass, encAcnoID);
    }

    /**
     * 
     * @param request
     * @return
     */
    private String encodeSecretE2EE(DoFastValidateUserRequest request, E2EEHsmType type) {
        EncodeWithSecretRequest rq = new EncodeWithSecretRequest();
//        rq.setE2eeHsmType(E2EEHsmType.PWD_EB5556981_CP1047.name());
        rq.setE2eeHsmType(type.name());
        rq.setUid(request.getCustId());
        rq.setUuid(request.getUserId());
        rq.setEncodedSecret(request.getSecret());
        rq.setIsPwdWithTime(request.getPwdWithTime() == null ? false : request.getPwdWithTime());

        EncodeWithSecretResponse rs = securityResource.encodewithSecret(rq);
        
        return rs.getHostSecret();
    }

    /**
     * 手勢密碼驗證
     * 
     * @param request
     * @param loginUser
     * @throws ActionException
     * @throws Exception
     */
    private String verifyGesturesPwd(DoFastValidateUserRequest request) throws ActionException {

        logger.info("手勢密碼驗證:" + request.getDeviceId());

        MbDeviceInfoEntity mbDeviceInfoEntities = mbDeviceInfoRepository.findByDeviceUuid(ValidateParamUtils.validParam(request.getDeviceId()));

        if (mbDeviceInfoEntities == null) {
            logger.error("手勢密碼:查無 MB_DEVICE_INFO");
            // 本手機尚未綁定快速登入
            return SsoStatusType.DEVICE_NOT_BINDED.getStatusCode();
        }

        if (mbDeviceInfoEntities.getEnable() != 1 || mbDeviceInfoEntities.getLoginPasswdType() != 1) {
            logger.error("手勢密碼:MB_DEVICE_INFO 狀態錯誤 LoginPasswdType={}", mbDeviceInfoEntities.getLoginPasswdType());
            // 本手機尚未綁定快速登入
            return SsoStatusType.DEVICE_NOT_BINDED.getStatusCode();
        }

        CompanyEntity companyEntity = companyRepository.findByCompanyKey(mbDeviceInfoEntities.getCompanyKey());

        if (companyEntity == null) {
            logger.error("CompanyEntity not found {}", IBUtils.toDataModel(mbDeviceInfoEntities.getCompanyKey(), Integer.class));
            // 本手機尚未綁定快速登入
            return SsoStatusType.DEVICE_NOT_BINDED.getStatusCode();
        }

        if (!companyEntity.getCompanyUid().equals(request.getCustId())) {
            logger.error("手勢密碼:Company 資訊不符 {} = {}", IBUtils.toDataModel(DataMaskUtil.maskCustId(companyEntity.getCompanyUid()), String.class), IBUtils.toDataModel(DataMaskUtil.maskCustId(request.getCustId()), String.class));
            // 本手機尚未綁定快速登入
            return SsoStatusType.DEVICE_NOT_BINDED.getStatusCode();
        }

        UserEntity userEntity = userProfileRepository.findByUserKey(mbDeviceInfoEntities.getUserKey());
        if (userEntity == null) {
            logger.error("UserEntity not found {}", IBUtils.toDataModel(mbDeviceInfoEntities.getCompanyKey(), Integer.class));
            // 本手機尚未綁定快速登入
            return SsoStatusType.DEVICE_NOT_BINDED.getStatusCode();
        }

        if (!userEntity.getUserUuid().equals(request.getUserId())) {
            logger.error("手勢密碼:User Profile 資訊不符 {} = {}", userEntity.getUserUuid(), request.getUserId());
            // 本手機尚未綁定快速登入
            return SsoStatusType.DEVICE_NOT_BINDED.getStatusCode();
        }

        MbGestureProfileEntity mbGestureProfileEntity = mbGestureProfileRepository.findByDeviceInfoKey(mbDeviceInfoEntities.getDeviceInfoKey());
        if (mbGestureProfileEntity == null) {
            logger.error("MbGestureProfileEntity not found {}", IBUtils.toDataModel(mbDeviceInfoEntities.getCompanyKey(), Integer.class));
            // 本手機尚未綁定快速登入
            return SsoStatusType.DEVICE_NOT_BINDED.getStatusCode();
        }

        // 密碼驗證成功先更為0
        String pinBlock = request.getChallenge().replace("@", "");
        if (mbGestureProfileEntity.getPassword().equals(pinBlock)) {
            mbGestureProfileEntity.setPwdErrorCount(0);
            mbGestureProfileEntity.setStatus(0);
            mbGestureProfileRepository.save(mbGestureProfileEntity);
            return SsoStatusType.SUCCESS.getStatusCode();
        }

        int pwdErrorCount = mbGestureProfileEntity.getPwdErrorCount() + 1;
        mbGestureProfileEntity.setPwdErrorTime(new Date());
        mbGestureProfileEntity.setPwdErrorCount(pwdErrorCount);
        if (pwdErrorCount >= MAX_PASSWORD_FAIL_COUNT) {
            mbGestureProfileEntity.setStatus(-2);
        }
        else {
            if (mbGestureProfileEntity.getStatus() == null) {
                mbGestureProfileEntity.setStatus(0);
            }
        }

        mbGestureProfileRepository.save(mbGestureProfileEntity);

        if (pwdErrorCount >= MAX_PASSWORD_FAIL_COUNT) {
            mbDeviceInfoEntities.setLoginPasswdType(0);
            mbDeviceInfoEntities.setLoginFlag(0);
            mbDeviceInfoRepository.save(mbDeviceInfoEntities);
            // 錯誤代碼+手勢密碼連續輸入錯誤三次已被鎖定，請您先用使用者密碼方式登入後重新進行手勢密碼設定，如有疑問，請洽客戶服務專線02-8751-6665。
            return SsoStatusType.FAST_LOGIN_LOACKED.getStatusCode();
        }
        else if (pwdErrorCount == 1) {
            // 錯誤代碼+手勢密碼輸入錯誤一次，如有疑問，請洽客戶服務專線02-8751-6665。
            return SsoStatusType.FAST_LOGIN_FAIL.getStatusCode();
        }
        else {
            // 錯誤代碼+手勢密碼連續輸入錯誤兩次(連續錯誤三次將被鎖定)，若您已遺忘手勢密碼，可先用使用者密碼登入後至【個人服務>密碼設定/變更>行動銀行手勢密碼設定/變更】中取消手勢密碼，再重新進行手勢密碼設定，如有疑問，請洽客戶服務專線02-8751-6665。
            return SsoStatusType.FAST_LOGIN_FAIL.getStatusCode();
        }

    }

    /**
     * 申辦信用卡客戶資料查詢 只取用電話
     * 
     * @param custId
     * @return
     * @throws Exception
     */
    public String getCEW013RMobile(String custId) {

        try {
            CEW013RRS rs = this.esbGateway.sendCEW013R(custId);

            CEW013RSvcRsType rsBody = rs.getBodyAsType(CEW013RSvcRsType.class);

            return rsBody.getMOBILE();

        }
        catch (XmlException | EAIException | EAIResponseException ex) {
            logger.error("sendCEW013R faile", ex);
        }
        return "";
    }

    private class LoginStatus {
        private String statusCode;
        private String mobileNo;
        private String idno;
        
         /**
         * @return the statusCode
         */
        public String getStatusCode() {
            return statusCode;
        }

        /**
         * @param statusCode
         *            the statusCode to set
         */
        public void setStatusCode(String statusCode) {
            this.statusCode = statusCode;
        }

        /**
         * @return the mobileNo
         */
        public String getMobileNo() {
            return mobileNo;
        }

        /**
         * @param mobileNo
         *            the mobileNo to set
         */
        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }

        public String getIdno() {
            return idno;
        }

        public void setIdno(String idno) {
            this.idno = idno;
        }
        
    }

    private String convertModuleParam(String moduleParam) {

        if (StringUtils.isBlank(moduleParam))
            return "";

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(moduleParam, JsonObject.class);

        StringBuilder convertedString = new StringBuilder();

        for (java.util.Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            logger.debug("key={}, value={}", entry.getKey(), entry.getValue().getAsString());

            if (StringUtils.equals(entry.getKey(), "HideNaviFlag") && StringUtils.equals(entry.getValue().getAsString(), "Y")) {
                convertedString.append("hidenavigationbuttons=yes").append(",");
            }
            if (StringUtils.equals(entry.getKey(), "HideBrowserFlag") && StringUtils.equals(entry.getValue().getAsString(), "Y")) {
                convertedString.append("hidesystembrowserbuttons=yes").append(",");
            }
            if (StringUtils.equals(entry.getKey(), "ModuleType2Title")) {
                convertedString.append("title=" + entry.getValue().getAsString()).append(",");
            }
        }

        if (convertedString.length() > 0) {
            convertedString.deleteCharAt(convertedString.length() - 1);
        }
        return convertedString.toString();
    }
   
    /**
     * 
     * @param uid
     * @param uuid
     * @param loginType
     * @return
     */
    private List<UserEntity> queryLoginUser(String uid, String uuid, String loginType) {
        List<UserEntity> usersInDB = null;

        try {
            // 查DB時只取左邊10碼
            uid = StringUtils.trimToEmptyEx(StringUtils.left(uid, 10));
            if (!CustomerType.isCardMember(loginType)) {
                // 【FORTIFY：Access Control: Database】uid、uuid 就是身分識別屬性欄位
                CompanyKindType companyKind = StringUtils.length(uid) == 8 ? CompanyKindType.COMPANY : CompanyKindType.PERSONAL;
                usersInDB = userProfileRepository.findByUidUUidCompany(encryptForUid(ValidateParamUtils.validParam(uid)), ValidateParamUtils.validParam(uuid), companyKind.getCode());
            }
            else {
                // 查詢信用卡會員
                // 【FORTIFY：Access Control: Database】uid、uuid 就是身分識別屬性欄位
                String encUid = encryptForUid(uid);
                usersInDB = userProfileRepository.findCardUser(encryptForUid(ValidateParamUtils.validParam(uid)), ValidateParamUtils.validParam(uuid));
            }
        }catch(Throwable e ) {
            logger.error("FastLogin Query Login User Failed: ", e );
        }
        return usersInDB;
    }

    /**
     * 回傳 rightPad(UID, 10, ' ') + 誤別碼 'A123456789', '0' --> 'A1234567890' '87178818', '1' --> '87178818 1'
     *
     * @return
     * @throws DatabaseException
     */
    private String getCompanyUidDup(CompanyEntity company) {

        return StringUtils.rightPad(company.getCompanyUid(), 10, ' ') + StringUtils.defaultIfBlank(company.getUidDup(), "0");
    }

}
