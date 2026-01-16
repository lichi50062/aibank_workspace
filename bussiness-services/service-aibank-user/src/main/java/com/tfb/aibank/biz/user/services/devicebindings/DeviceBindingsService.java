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
package com.tfb.aibank.biz.user.services.devicebindings;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.collections.CollectionUtils;
import org.apache.xmlbeans.XmlException;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.AESUtils;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.component.context.MDCKey;
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
import com.tfb.aibank.biz.user.repository.AibankPushCategoryRepository;
import com.tfb.aibank.biz.user.repository.BiometricsBlackListRepository;
import com.tfb.aibank.biz.user.repository.CompanyRepository;
import com.tfb.aibank.biz.user.repository.DeviceModelRepository;
import com.tfb.aibank.biz.user.repository.LinePNPRecordRepository;
import com.tfb.aibank.biz.user.repository.MbDeviceInfoRepository;
import com.tfb.aibank.biz.user.repository.MbGestureProfileRepository;
import com.tfb.aibank.biz.user.repository.UserRepository;
import com.tfb.aibank.biz.user.repository.WalletPaymentSettingRepository;
import com.tfb.aibank.biz.user.repository.aib.MbDevicePushInfoRepository;
import com.tfb.aibank.biz.user.repository.aib.PushSubscriptionRepository;
import com.tfb.aibank.biz.user.repository.aib.entities.MbDevicePushInfoEntity;
import com.tfb.aibank.biz.user.repository.aib.entities.PushSubscriptionEntity;
import com.tfb.aibank.biz.user.repository.entities.AibankPushCategoryEntity;
import com.tfb.aibank.biz.user.repository.entities.BiometricsBlackListEntity;
import com.tfb.aibank.biz.user.repository.entities.CompanyEntity;
import com.tfb.aibank.biz.user.repository.entities.DeviceModelEntity;
import com.tfb.aibank.biz.user.repository.entities.LinePNPRecordEntity;
import com.tfb.aibank.biz.user.repository.entities.MbDeviceInfoEntity;
import com.tfb.aibank.biz.user.repository.entities.MbGestureProfileEntity;
import com.tfb.aibank.biz.user.repository.entities.UserEntity;
import com.tfb.aibank.biz.user.repository.entities.WalletPaymentSettingEntity;
import com.tfb.aibank.biz.user.services.devicebindings.model.MbDeviceInfoModel;
import com.tfb.aibank.biz.user.services.devicebindings.model.RetrieveDeviceBindingResponse;
import com.tfb.aibank.biz.user.services.devicebindings.model.RetrieveMultiUserBindingResponse;
import com.tfb.aibank.biz.user.services.devicebindings.model.RetrieveUserBindingResponse;
import com.tfb.aibank.biz.user.services.devicebindings.model.RetriveDeviceStatusResponse;
import com.tfb.aibank.biz.user.services.devicebindings.model.UpdateMbDeviceInfoRequest;
import com.tfb.aibank.biz.user.services.devicebindings.model.UpdateNotficationNoPainRequest;
import com.tfb.aibank.biz.user.services.devicebindings.model.UpdateNotficationRequest;
import com.tfb.aibank.biz.user.services.devicebindings.model.UpdateNotficationResponse;
import com.tfb.aibank.biz.user.services.devicebindings.model.UpdatePatternlockRequest;
import com.tfb.aibank.biz.user.services.devicebindings.model.UpdatePatternlockResponse;
import com.tfb.aibank.biz.user.services.devicebindings.model.UpdateUserDeviceBindingRequest;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIResponseException;
import com.tfb.aibank.integration.eai.msg.CEW323RRS;
import com.tfb.aibank.integration.eai.msg.EB552175RS;

//@formatter:off
/**
* @(#)DeviceBindingsService.java
* 
* <p>Description:裝置綁定服務</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class DeviceBindingsService {

    private static final IBLog logger = IBLog.getLog(DeviceBindingsService.class);

    /** 取得使用者服務 */
    private IdentityService identityService;

    /** 行動裝置設定檔 Repository */
    private MbDeviceInfoRepository mbDeviceInfoRepository;
    /** 行動裝置設定檔 Repository */
    private MbDevicePushInfoRepository mbDevicePushInfoRepository;

    /** 行動手勢基本資料檔 Repository */
    private MbGestureProfileRepository mbGestureProfileRepository;

    private UserRepository userProfileRepository;

    private CompanyRepository companyRepository;

    private LinePNPRecordRepository linePNPRecordRepository;

    private DeviceModelRepository deviceModelRepository;

    private BiometricsBlackListRepository biometricsBlackListRepository;

    private PushSubscriptionRepository pushSubscriptionRepository;

    private AibankPushCategoryRepository aibankPushCategoryRepository;

    private WalletPaymentSettingRepository walletPaymentSettingRepository;

    private EsbChannelGateway esbGateway;

    private SystemParamCacheManager systemParamCacheManager;

    public DeviceBindingsService(IdentityService identityService, MbDeviceInfoRepository mbDeviceInfoRepository, MbGestureProfileRepository mbGestureProfileRepository, CompanyRepository companyRepository, UserRepository userProfileRepository, LinePNPRecordRepository linePNPRecordRepository, DeviceModelRepository deviceModelRepository, BiometricsBlackListRepository biometricsBlackListRepository, MbDevicePushInfoRepository mbDevicePushInfoRepository, PushSubscriptionRepository pushSubscriptionRepository, EsbChannelGateway esbGateway, AibankPushCategoryRepository aibankPushCategoryRepository, WalletPaymentSettingRepository walletPaymentSettingRepository, SystemParamCacheManager systemParamCacheManager) {
        this.identityService = identityService;
        this.mbDeviceInfoRepository = mbDeviceInfoRepository;
        this.mbGestureProfileRepository = mbGestureProfileRepository;
        this.companyRepository = companyRepository;
        this.userProfileRepository = userProfileRepository;
        this.linePNPRecordRepository = linePNPRecordRepository;
        this.deviceModelRepository = deviceModelRepository;
        this.biometricsBlackListRepository = biometricsBlackListRepository;
        this.mbDevicePushInfoRepository = mbDevicePushInfoRepository;
        this.pushSubscriptionRepository = pushSubscriptionRepository;
        this.walletPaymentSettingRepository = walletPaymentSettingRepository;
        this.esbGateway = esbGateway;
        this.aibankPushCategoryRepository = aibankPushCategoryRepository;
        this.systemParamCacheManager = systemParamCacheManager;
    }

    /**
     * 取得使用者綁定資料
     * 
     * @param custId
     * @param userId
     * @param companyKind
     * @param locale
     * @return
     * @throws ActionException
     */
    public RetrieveUserBindingResponse retrieveUserBinding(String custId, String uidDup, String userId, Integer companyKind, String locale) throws ActionException {

        RetrieveUserBindingResponse response = new RetrieveUserBindingResponse();

        IdentityData user = getUser(custId, uidDup, userId, companyKind);
        List<MbDeviceInfoEntity> entityList = mbDeviceInfoRepository.findByCompanyKeyAndUserKey(user.getCompanyKey(), user.getUserKey());
        if (CollectionUtils.isNotEmpty(entityList)) {
            if (entityList.size() > 1) {
                // 超過一筆資料
                throw ExceptionUtils.getActionException("MB_DEVICE_INFO資料有誤", CommonErrorCode.DATA_OWNERSHIP_EXCEPTION);
            }
            // 只能有一筆資料
            MbDeviceInfoEntity entity = entityList.get(0);
            // push 也只會有一筆
            List<MbDevicePushInfoEntity> pushEntites = mbDevicePushInfoRepository.findByCompanyKeyAndUserKey(user.getCompanyKey(), user.getUserKey());
            MbDevicePushInfoEntity pushEntity = null;
            if (CollectionUtils.isEmpty(pushEntites)) {
                pushEntity = getNewMbDevicePushInfoEntity(user, entity.getDeviceUuId(), entity.getDevicePlatform());
                mbDevicePushInfoRepository.save(pushEntity);
            }
            else {
                pushEntity = pushEntites.get(0);
            }

            MbDeviceInfoModel model = new MbDeviceInfoModel();
            convertEntityToModel(entity, pushEntity, model);
            response.setModel(model);
            response.setBind(true);
            // 手機型號資料來源：查詢DEVICE_MODEL.MARKETING_NAME(DEVICE_MODEL.DEVICE_MODEL=MB_DEVICE_INFO.MODEL)
            if (StringUtils.isNoneBlank(entity.getModel(), locale)) {

                DeviceModelEntity deviceModel = deviceModelRepository.findById(entity.getModel()).orElse(null);
                if (deviceModel != null) {
                    response.setDeviceModel(deviceModel.getMarketingName());
                }
                else {
                    response.setDeviceModel(entity.getModel());
                }
            }
        }
        else {
            response.setBind(false);
        }

        return response;
    }

    /**
     * 取得使用者綁定資料(custId,userId)
     *
     * @param custId
     * @param userId
     * @param companyKind
     * @param locale
     * @return
     * @throws ActionException
     */
    public RetrieveUserBindingResponse retrieveUserBindingInfo(String custId, String uidDup, String userId, Integer companyKind, String locale) throws ActionException, CryptException, DecoderException {
        String key = systemParamCacheManager.getValue("AIBANK", "SSO_FASTLOGIN_KEY", "");
        logger.info("[DeviceBindingsService.retrieveUserBindingInfo] key:{}", key);

        byte[] custIdDecode = Hex.decodeHex(custId);
        byte[] userIdDecode = Hex.decodeHex(userId);
        String decCustId = new String(AESUtils.decryptToBytes(custIdDecode, key.getBytes(StandardCharsets.UTF_8)));
        String decUserId = new String(AESUtils.decryptToBytes(userIdDecode, key.getBytes(StandardCharsets.UTF_8)));
        logger.info("[DeviceBindingsService.retrieveUserBindingInfo] decCustId:{}, decUserId:{}", decCustId, decUserId);

        RetrieveUserBindingResponse response = retrieveUserBinding(decCustId, uidDup, decUserId, companyKind, locale);
        return response;
    }

    private MbDevicePushInfoEntity getNewMbDevicePushInfoEntity(IdentityData identityData, String deviceId, String platform) {
        MbDevicePushInfoEntity mbDevicePushInfoEntity = new MbDevicePushInfoEntity();
        mbDevicePushInfoEntity.setCompanyKey(identityData.getCompanyKey());
        mbDevicePushInfoEntity.setCreateTime(new Date());
        mbDevicePushInfoEntity.setDevicePlatform(platform);
        mbDevicePushInfoEntity.setDeviceUuId(deviceId);
        mbDevicePushInfoEntity.setNotifyFlag(0);
        mbDevicePushInfoEntity.setNotifyPass(0);
        mbDevicePushInfoEntity.setPushToken("");
        mbDevicePushInfoEntity.setUpdateTime(new Date());
        mbDevicePushInfoEntity.setUserKey(identityData.getUserKey());
        return mbDevicePushInfoEntity;
    }

    private MbDevicePushInfoEntity getNewMbDevicePushInfoEntity(MbDeviceInfoEntity entity) {
        MbDevicePushInfoEntity mbDevicePushInfoEntity = new MbDevicePushInfoEntity();
        mbDevicePushInfoEntity.setCompanyKey(entity.getCompanyKey());
        mbDevicePushInfoEntity.setCreateTime(new Date());
        mbDevicePushInfoEntity.setDevicePlatform(entity.getDevicePlatform());
        mbDevicePushInfoEntity.setDeviceUuId(entity.getDeviceUuId());
        mbDevicePushInfoEntity.setNotifyFlag(0);
        mbDevicePushInfoEntity.setNotifyPass(0);
        mbDevicePushInfoEntity.setPushToken("");
        mbDevicePushInfoEntity.setUpdateTime(new Date());
        mbDevicePushInfoEntity.setUserKey(entity.getUserKey());
        return mbDevicePushInfoEntity;
    }

    /**
     * 取得裝置綁定資料
     * 
     * @param deviceUuid
     * @return
     * @throws ActionException
     */
    public RetrieveDeviceBindingResponse retrieveDeviceBinding(String deviceUuid) throws ActionException {

        RetrieveDeviceBindingResponse response = new RetrieveDeviceBindingResponse();

        List<MbDeviceInfoEntity> entityList = mbDeviceInfoRepository.findByDeviceUuId(deviceUuid);
        if (CollectionUtils.isNotEmpty(entityList)) {
            if (entityList.size() > 1) {
                // 超過一筆資料
                throw ExceptionUtils.getActionException("MB_DEVICE_INFO資料有誤", CommonErrorCode.DATA_NOT_FOUND);
            }
            // 只能有一筆資料
            MbDeviceInfoEntity entity = entityList.get(0);
            // push 也只會有一筆
            List<MbDevicePushInfoEntity> pushEntites = mbDevicePushInfoRepository.findByDeviceUuid(deviceUuid);
            MbDevicePushInfoEntity pushEntity = null;
            if (CollectionUtils.isEmpty(pushEntites)) {
                pushEntity = getNewMbDevicePushInfoEntity(entity);
                mbDevicePushInfoRepository.save(pushEntity);
            }
            else {
                pushEntity = pushEntites.get(0);
            }
            MbDeviceInfoModel model = new MbDeviceInfoModel();
            convertEntityToModel(entity, pushEntity, model);
            response.setModel(model);
            // 取得使用者資料
            CompanyEntity companyEntity = companyRepository.findByCompanyKey(entity.getCompanyKey());
            if (companyEntity == null) {
                logger.error("用裝置設定找不到Company DEVICE_INFO_KEY={}", entity.getDeviceInfoKey());
                throw ExceptionUtils.getActionException(CommonErrorCode.DATABASE_EXCEPTION);
            }

            response.setCompanyKind(companyEntity.getCompanyKind());
            response.setCustId(companyEntity.getCompanyUid());
            response.setDup(companyEntity.getUidDup());

            UserEntity userEntity = userProfileRepository.findByUserKey(entity.getUserKey());
            if (userEntity == null) {
                logger.error("用裝置設定找不到User DEVICE_INFO_KEY={}", entity.getDeviceInfoKey());
                throw ExceptionUtils.getActionException(CommonErrorCode.DATABASE_EXCEPTION);
            }

            response.setUserId(userEntity.getUserUuid());
            response.setBind(true);
        }
        else {
            response.setBind(false);
        }

        return response;
    }

    /**
     * 取得多使用者代碼客戶綁定狀態
     * 
     * @param custId
     * @param userId
     * @param companyKind
     * @param uidDup
     * @return
     * @throws ActionException
     * @throws CryptException
     */
    public RetrieveMultiUserBindingResponse retrieveMultiUserBinding(String custId, String userId, Integer companyKind, String uidDup) throws ActionException, CryptException {

        RetrieveMultiUserBindingResponse response = new RetrieveMultiUserBindingResponse();
        boolean isOtherUserBind = false;
        String otherUserId = "";
        int otherUserLoginType = 0;

        IdentityData user = getUser(custId, uidDup, userId, companyKind);
        List<IdentityData> users = identityService.queryMultiUser(custId, uidDup);
        if (CollectionUtils.isNotEmpty(users)) {

            List<Integer> companyKeys = users.stream().map(IdentityData::getCompanyKey).toList();
            List<MbDeviceInfoEntity> entityList = mbDeviceInfoRepository.findByCompanyKeyIn(companyKeys);

            if (CollectionUtils.isNotEmpty(entityList)) {

                for (MbDeviceInfoEntity entity : entityList) {
                    if (!entity.getUserKey().equals(user.getUserKey())) {
                        isOtherUserBind = true;
                        otherUserLoginType = ConvertUtils.integer2Int(entity.getLoginType(), 0);
                        UserEntity userEntity = userProfileRepository.findByUserKey(entity.getUserKey());
                        if (userEntity == null) {
                            logger.error("用裝置設定找不到User DEVICE_INFO_KEY={}", entity.getDeviceInfoKey());
                            throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
                        }
                        otherUserId = userEntity.getUserUuid();
                        response.setDeviceUuid(entity.getDeviceUuId());
                        break;
                    }

                }
            }
        }

        response.setOtherUserBind(isOtherUserBind);
        response.setOtherUserId(otherUserId);
        response.setOtherUserLoginType(otherUserLoginType);
        return response;
    }

    /**
     * 更新使用者裝置綁定資料
     * 
     * @param userId
     * @param userUuid
     * @param companyKind
     * @param request
     * @return
     * @throws ActionException
     */
    public MbDeviceInfoModel updateUserDeviceBinding(String userId, String uidDup, String userUuid, Integer companyKind, UpdateUserDeviceBindingRequest request) throws ActionException {

        MbDeviceInfoModel model = new MbDeviceInfoModel();

        IdentityData user = getUser(userId, uidDup, userUuid, companyKind);

        // 【FORTIFY：Access Control: Database】COMPANY_KEY、USER_KEY 就是身分識別屬性欄位
        List<MbDeviceInfoEntity> entityList = mbDeviceInfoRepository.findByCompanyKeyAndUserKeyAndDeviceUuId(user.getCompanyKey(), user.getUserKey(), ValidateParamUtils.validParam(request.getDeviceUuid()));
        if (CollectionUtils.isNotEmpty(entityList) && entityList.size() == 1) {
            // 只能綁一筆資料
            MbDeviceInfoEntity entity = entityList.get(0);
            List<MbDevicePushInfoEntity> pushList = mbDevicePushInfoRepository.findByCompanyKeyAndUserKeyAndDeviceUuId(user.getCompanyKey(), user.getUserKey(), ValidateParamUtils.validParam(request.getDeviceUuid()));

            MbDevicePushInfoEntity pushEntity = null;
            if (CollectionUtils.isEmpty(pushList)) {
                pushEntity = getNewMbDevicePushInfoEntity(entity);
                mbDevicePushInfoRepository.save(pushEntity);
            }
            else {
                pushEntity = pushList.get(0);
            }

            if (request.isUpdateQsearchFlag()) {
                entity.setQsearchFlag(request.getQsearchFlag());
            }
            if (request.isUpdateLoginFlag()) {
                entity.setLoginFlag(request.getLoginFlag());
                entity.setLoginPasswdType(request.getLoginPasswdType());
                entity.setLoginAuthDate(request.getLoginAuthDate());
                // 關閉錢包快登驗證處理
                walletPaymentSettingRepository.findByCompanyKeyAndUserKeyAndDeviceUuid(entity.getCompanyKey(), entity.getUserKey(), entity.getDeviceUuId()).ifPresent(this::disableWalletPaymentSettingBiometrics);
            }
            if (request.isUpdateTwoStepFlag()) {
                entity.setTwostepFlag(request.getTwostepFlag());
                entity.setTwostepFlagTime(new Date());
            }
            if (request.isUpdateNotifyFlag()) {
                pushEntity.setNotifyFlag(request.getNotifyFlag());
                pushEntity.setNotifyPass(request.getNotifyPass());
                pushEntity.setNotifyAuthDate(request.getNotifyAuthDate());
                pushEntity.setUpdateTime(new Date());
            }
            // 錢包註記更新
            if (request.isUpdateWalletFlag()) {
                entity.setWalletFlag(request.getWalletFlag());
            }

            mbDevicePushInfoRepository.save(pushEntity);
            mbDeviceInfoRepository.save(entity);

            convertEntityToModel(entity, pushEntity, model);
        }
        else {

            // 沒資料或是超過一筆資料
            throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
        }

        return model;
    }

    private void disableWalletPaymentSettingBiometrics(WalletPaymentSettingEntity entity) {
        if (entity != null) {
            entity.setFastLoginAuth(false);
            walletPaymentSettingRepository.save(entity);
        }
    }

    /**
     * 轉換Entity to Model
     * 
     * @param entity
     * @param model
     */
    private void convertEntityToModel(MbDeviceInfoEntity entity, MbDevicePushInfoEntity pushEntity, MbDeviceInfoModel model) {
        model.setDeviceInfoKey(entity.getDeviceInfoKey());
        model.setDeviceUuId(entity.getDeviceUuId());
        model.setCompanyKey(entity.getCompanyKey());
        model.setUserKey(entity.getUserKey());
        model.setModel(entity.getModel());
        model.setDevicePlatform(entity.getDevicePlatform());
        model.setDevicePlatformVersion(entity.getDevicePlatformVersion());
        model.setDeviceAlias(entity.getDeviceAlias());
        model.setLoginFlag(entity.getLoginFlag());
        model.setLoginAuthDate(entity.getLoginAuthDate());
        model.setNotifyFlag(pushEntity.getNotifyFlag());
        model.setNotifyAuthDate(pushEntity.getNotifyAuthDate());
        model.setNotifyPass(pushEntity.getNotifyPass());
        model.setClientIp(entity.getClientIp());
        model.setMsgFlag(entity.getMsgFlag());
        model.setMsgPassword(entity.getMsgPassword());
        model.setEnable(entity.getEnable());
        model.setCreateTime(entity.getCreateTime());
        model.setUpdateTime(entity.getUpdateTime());
        model.setLoginType(entity.getLoginType());
        model.setErrorFlag(entity.getErrorFlag());
        model.setLoginPasswdType(entity.getLoginPasswdType());
        model.setQsearchFlag(entity.getQsearchFlag());
        model.setChgPwdUseridFlag(entity.getChgPwdUseridFlag());
        model.setChgPwdUseridDate(entity.getChgPwdUseridDate());
        model.setMotpFlag(entity.getMotpFlag());
        model.setMotpFlagDate(entity.getMotpFlagDate());
        model.setPushToken(pushEntity.getPushToken());
        model.setDirectEzLoginFlag(entity.getDirectEzLoginFlag());
        model.setWalletFlag(entity.getWalletFlag());
        model.setTwostepFlag(entity.getTwostepFlag());
        model.setTwostepFlagTime(entity.getTwostepFlagTime());
        model.setCreditCardFido2Flag(entity.getCreditCardFido2Flag());
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
     * 取得使用者語系
     * 
     * @return
     */
    private String getUserLocale() {
        String userLocale = IBUtils.getExecContext().get(MDCKey.locale.name());
        if (StringUtils.isBlank(userLocale)) {
            userLocale = Locale.TAIWAN.toString();
        }
        return userLocale;
    }

    /**
     * 更新快登圖形登入設定
     * 
     * @param updatePatternlockRequest
     * @return
     * @throws ActionException
     */
    public UpdatePatternlockResponse updatePatternlock(UpdatePatternlockRequest updatePatternlockRequest) throws ActionException {
        UpdatePatternlockResponse response = new UpdatePatternlockResponse();
        IdentityData user = getUser(updatePatternlockRequest.getCustId(), updatePatternlockRequest.getUidDup(), updatePatternlockRequest.getUserId(), updatePatternlockRequest.getCompanyKind());
        List<MbDeviceInfoEntity> mbDeviceInfoEntities = mbDeviceInfoRepository.findByDeviceUuId(ValidateParamUtils.validParam(updatePatternlockRequest.getDeviceUuid()));

        if (mbDeviceInfoEntities == null || mbDeviceInfoEntities.isEmpty()) {
            logger.error("MbDeviceInfoEntity not found!");
            throw ExceptionUtils.getActionException(AIBankErrorCode.DEVICE_BINDING_FAILED);

        }
        MbDeviceInfoEntity mbDeviceInfoEntity = null;

        for (MbDeviceInfoEntity entity : mbDeviceInfoEntities) {
            if (entity.getEnable() == 1) {
                mbDeviceInfoEntity = entity;
                break;
            }
        }
        if (mbDeviceInfoEntity == null) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.DEVICE_BINDING_FAILED);
        }

        MbGestureProfileEntity mbGestureProfileEntity = mbGestureProfileRepository.findByDeviceInfoKey(mbDeviceInfoEntity.getDeviceInfoKey());

        if (mbGestureProfileEntity == null) {
            mbGestureProfileEntity = new MbGestureProfileEntity();

            mbGestureProfileEntity.setCompanyKey(user.getCompanyKey());
            mbGestureProfileEntity.setUserKey(user.getUserKey());
            mbGestureProfileEntity.setDeviceInfoKey(mbGestureProfileEntity.getDeviceInfoKey());
            mbGestureProfileEntity.setCreateTime(new Date());
        }
        // String pinblock = encodewithSecret(E2EEHsmType.PWD_EB5556981_CP1047, updatePatternlockRequest.getCustId(), updatePatternlockRequest.getUserId(), updatePatternlockRequest.getPinblock());

        mbGestureProfileEntity.setPassword(updatePatternlockRequest.getPinblock().replace("@", ""));
        mbGestureProfileEntity.setPwdChangeTime(new Date());
        mbGestureProfileEntity.setPwdErrorCount(0);
        mbGestureProfileEntity.setCoefficient(updatePatternlockRequest.getCoefficient());
        mbGestureProfileEntity.setDeviceInfoKey(mbDeviceInfoEntity.getDeviceInfoKey());

        mbGestureProfileRepository.save(mbGestureProfileEntity);

        mbDeviceInfoEntity.setEnable(1);
        mbDeviceInfoEntity.setLoginFlag(1); // 是否授權簡易登入，1:已授權；0或空白:未授權
        mbDeviceInfoEntity.setLoginPasswdType(1); // 手勢
        mbDeviceInfoEntity.setLoginAuthDate(new Date());
        mbDeviceInfoRepository.save(mbDeviceInfoEntity);

        response.setStatus(0);
        return response;
    }

    /**
     * 取得裝置綁定狀態
     * 
     * @param updatePatternlockRequest
     * @return
     * @throws ActionException
     */
    public RetriveDeviceStatusResponse retriveDeviceStatus(String deviceId, String phoneModel, String phoneVersion) throws ActionException {
        RetriveDeviceStatusResponse response = new RetriveDeviceStatusResponse();
        response.setStatus(0);

        if ("none".equals(deviceId)) {
            return response;
        }

        response.setMarketingName(getMarketingName(phoneModel));

        if (StringUtils.isNotBlank(phoneModel)) {
            phoneModel = phoneModel.toUpperCase();
        }
        if (StringUtils.isNotBlank(phoneVersion)) {
            phoneVersion = phoneVersion.toUpperCase();
        }

        List<BiometricsBlackListEntity> blackLists = biometricsBlackListRepository.findBy(phoneModel, phoneVersion);

        if (!blackLists.isEmpty()) {
            response.setIsInBlackList(1);
            return response;
        }

        MbDeviceInfoEntity mbDeviceInfoEntities = mbDeviceInfoRepository.findByDeviceUuid(deviceId);

        if (mbDeviceInfoEntities != null) {

            // 開啟 APP 立即登入？
            response.setDirectEzLoginFlag(mbDeviceInfoEntities.getDirectEzLoginFlag());

            // 開啟快登？
            response.setFastLogin(mbDeviceInfoEntities.getLoginFlag() != null && mbDeviceInfoEntities.getLoginFlag() == 1 ? true : false);

            // 密碼型態
            response.setPwType(mbDeviceInfoEntities.getLoginPasswdType() != null ? mbDeviceInfoEntities.getLoginPasswdType() : 0);

            // 登入身份
            response.setLoginType(mbDeviceInfoEntities.getLoginType());

            CompanyEntity companyEntity = companyRepository.findByCompanyKey(mbDeviceInfoEntities.getCompanyKey());

            if (companyEntity == null) {
                logger.error("CompanyEntity not found {}", mbDeviceInfoEntities.getCompanyKey());
                throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
            }
            response.setCustId(companyEntity.getCompanyUid());
            response.setUidDup(companyEntity.getUidDup());
            response.setCompanyKind(companyEntity.getCompanyKind());

            UserEntity userEntity = userProfileRepository.findByUserKey(mbDeviceInfoEntities.getUserKey());
            if (userEntity == null) {
                logger.error("UserEntity not found {}", mbDeviceInfoEntities.getCompanyKey());
                throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
            }
            response.setUserId(userEntity.getUserUuid());

            if (1 == response.getPwType()) {
                MbGestureProfileEntity mbGestureProfileEntity = mbGestureProfileRepository.findByDeviceInfoKey(mbDeviceInfoEntities.getDeviceInfoKey());
                if (mbGestureProfileEntity == null) {
                    mbDeviceInfoEntities.setLoginPasswdType(null);
                    mbDeviceInfoEntities.setLoginFlag(0);
                    mbDeviceInfoEntities.setDirectEzLoginFlag(0);
                    mbDeviceInfoRepository.save(mbDeviceInfoEntities);
                    logger.error("MbGestureProfileEntity not found {}", mbDeviceInfoEntities.getCompanyKey());
                    throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
                }
                response.setCoefficient(mbGestureProfileEntity.getCoefficient());
            }

            // 使用者有啟用快登，且已變更使用者代碼或密碼，
            // MB_DEVICE_INFO.CHG_PWD_USERID_FLAG=1且及MB_DEVICE_INFO.CHG_PWD_USERID_DATE> MB_DEVICE_INFO.LOGIN_AUTH_DATE
            if (mbDeviceInfoEntities.getChgPwdUseridDate() != null && mbDeviceInfoEntities.getLoginAuthDate() != null) {
                if (mbDeviceInfoEntities.getChgPwdUseridDate().after(mbDeviceInfoEntities.getLoginAuthDate())) {
                    if (response.getCompanyKind() == 1) {
                        response.setStatus(2);
                        mbDeviceInfoEntities.setLoginFlag(0);
                        mbDeviceInfoRepository.save(mbDeviceInfoEntities);
                    }
                    else {
                        response.setStatus(1);
                    }
                    response.setFastLogin(false);
                    return response;
                }
            }

            // 使用者啟用FIDO2認證
            response.setBindFido2(mbDeviceInfoEntities.getCreditCardFido2Flag() != null && mbDeviceInfoEntities.getCreditCardFido2Flag() == 1);
        }

        return response;
    }

    /**
     * 檢核是否變更過使用者代碼或使用者密碼註記
     * 
     * @param deviceId
     * @return
     * @throws ActionException
     */
    public Boolean isHasChangedPwd(String deviceId) throws ActionException {

        MbDeviceInfoEntity mbDeviceInfoEntities = mbDeviceInfoRepository.findByDeviceUuid(deviceId);

        if (mbDeviceInfoEntities == null) {
            throw new ActionException(AIBankErrorCode.DEVICE_BINDING_STATUS_INVALID);
        }
        // 使用者有啟用快登，且已變更使用者代碼或密碼，
        // MB_DEVICE_INFO.CHG_PWD_USERID_FLAG=1且及MB_DEVICE_INFO.CHG_PWD_USERID_DATE> MB_DEVICE_INFO.LOGIN_AUTH_DATE
        if (mbDeviceInfoEntities.getChgPwdUseridDate() != null && mbDeviceInfoEntities.getLoginAuthDate() != null) {
            if (mbDeviceInfoEntities.getChgPwdUseridDate().after(mbDeviceInfoEntities.getLoginAuthDate())) {
                return true;
            }
        }
        if (mbDeviceInfoEntities.getChgPwdUseridDate() != null && mbDeviceInfoEntities.getLoginAuthDate() == null) {
            return true;
        }
        return false;
    }

    /**
     * 更新推播通知設定
     * 
     * @param request
     * @return
     * @throws ActionException
     */
    public UpdateNotficationResponse updateNotfication(UpdateNotficationRequest request) throws ActionException {

        IdentityData user = getUser(request.getCustId(), request.getUidDup(), request.getUserId(), request.getCompanyKind());

        UpdateNotficationResponse response = new UpdateNotficationResponse();
        List<String> failNotificationTypes = new ArrayList<String>();
        List<String> succNotificationTypes = new ArrayList<String>();

        if (request.getNotificationAgreeFlag() != -1) {
            List<MbDevicePushInfoEntity> entities = mbDevicePushInfoRepository.findByCompanyKeyAndUserKey(user.getCompanyKey(), user.getUserKey());

            int notifyFlag = request.getNotificationAgreeFlag() != 0 ? 1 : 0;

            if (entities == null || entities.isEmpty()) {
                throw ExceptionUtils.getActionException(AIBankErrorCode.MOTP_BINDING_DEVICE_NOT_FOUND);
            }
            for (MbDevicePushInfoEntity entity : entities) {
                entity.setNotifyFlag(notifyFlag);
                entity.setNotifyPass(1);
                entity.setNotifyFlag(1);
                entity.setNotifyAuthDate(new Date());
                entity.setUpdateTime(new Date());
                mbDevicePushInfoRepository.save(entity);
            }
        }

        SecretKeyProvider<?> provider = SecretKeyProviderFactory.getInstance().getProvider(AIBankConstants.CRYPTO_SECRET_KEY_PROVIDER_DEFAULT);
        AESCipherUtils aesCipherUtils = new AESCipherUtils(provider);

        if (request.getLineAgreeFlag() != -1) {
            String eCustId = "";

            try {
                eCustId = aesCipherUtils.encrypt(ValidateParamUtils.validParam(request.getCustId()));
            }
            catch (CryptException e) {
                logger.warn("", e);
            }
            // 【FORTIFY：Access Control: Database】eCustId 就是身分識別屬性欄位
            LinePNPRecordEntity entity = linePNPRecordRepository.findByCompanyUid(eCustId);

            if (entity == null) {
                entity = new LinePNPRecordEntity();
                entity.setCreateTime(new Date());
                entity.setCompanyUid(eCustId);
            }

            entity.setBirthday(request.getBirthday());
            entity.setMobilePhone(request.getMobileNo());
            entity.setPlatform(AIBankConstants.CHANNEL_NAME);
            entity.setTxKind("LOGINAI");
            String agreeFlag = request.getLineAgreeFlag() == 0 ? "N" : "Y";
            entity.setUpdateTime(new Date());
            entity.setAgreeFlag(agreeFlag);

            linePNPRecordRepository.save(entity);

        }
        // #2010
        // 訂閱所有通知項目
        List<MbDeviceInfoEntity> mbdeviceInfo = mbDeviceInfoRepository.findByCompanyKeyAndUserKey(user.getCompanyKey(), user.getUserKey());
        if (mbdeviceInfo == null || mbdeviceInfo.isEmpty()) {
            logger.warn("訂閱所有通知項目失敗，MB_DEVICE_INFO 找不到");
            return null;
        }
        Integer deviceKey = mbdeviceInfo.get(0).getDeviceInfoKey();

        List<AibankPushCategoryEntity> child = this.aibankPushCategoryRepository.findByCompanyKind(request.getType(), getUserLocale());
        logger.debug("category from db: " + child);

        child = CollectionUtils.isEmpty(child) ? new ArrayList<>() : child.stream().filter(o -> IBUtils.checkIsValidVersion(o.getVersion(), request.getVersion())).toList();

        Map<String, List<String>> groupByTx = child.stream().collect(Collectors.groupingBy(AibankPushCategoryEntity::getCategory, // Group by category
                Collectors.mapping(AibankPushCategoryEntity::getPushCode, Collectors.toList())));// Map each entity to its pushCode

        List<String> groups = child.stream().filter(o -> o.getIsGroup() == 1).map(AibankPushCategoryEntity::getPushCode).toList();
        logger.debug("category groups: " + groups);

        for (String group : groups) {
            // TX_A -> TX01, TX02, TX03 不處理, 處理其它的 group = 1
            logger.debug("updateNotfication Group: " + group);
            if (StringUtils.equalsIgnoreCase(group, "TX_A")) {
                continue;
            }
            // 1. 先找有設 Group 的 Push Code,
            List<String> pushCodes = groupByTx.get(group) == null ? new ArrayList<>() : groupByTx.get(group);

            for (String pushCode : pushCodes) {
                logger.debug("updateNotfication tx code: " + pushCode);
                PushSubscriptionEntity push = new PushSubscriptionEntity();
                push.setDeviceInfoKey(deviceKey);
                push.setPushCode(pushCode);
                push.setCreateTime(new Date());
                pushSubscriptionRepository.save(push);
                succNotificationTypes.add(pushCode);
            }
        }

        // 2. 找登入獨有的 Category=LG:LG01,
        List<String> lgPushCodes = groupByTx.get("LG") == null ? new ArrayList<>() : groupByTx.get("LG");
        for (String pushCode : lgPushCodes) {
            logger.debug("updateNotfication lg code: " + pushCode);
            PushSubscriptionEntity push = new PushSubscriptionEntity();
            push.setDeviceInfoKey(deviceKey);
            push.setPushCode(pushCode);
            push.setCreateTime(new Date());
            pushSubscriptionRepository.save(push);
            succNotificationTypes.add(pushCode);
        }

        // 3. Category=PE:PE01~PE05
        List<String> pePushCodes = groupByTx.get("PE") == null ? new ArrayList<>() : groupByTx.get("PE");
        for (String pushCode : pePushCodes) {
            logger.debug("updateNotfication pe code: " + pushCode);
            PushSubscriptionEntity push = new PushSubscriptionEntity();
            push.setDeviceInfoKey(deviceKey);
            push.setPushCode(pushCode);
            push.setCreateTime(new Date());
            pushSubscriptionRepository.save(push);
            succNotificationTypes.add(pushCode);
        }

        // 信用卡刷卡消費通知
        try {
            List<String> cuCategoryCodes = child.stream().filter(p -> p.getIsGroup() == 0 && "CU01".equalsIgnoreCase(p.getPushCode())).map(AibankPushCategoryEntity::getCategory).toList();
            logger.debug("isEmpty: cuCategoryCodes: " + CollectionUtils.isEmpty(cuCategoryCodes));
            if (CollectionUtils.isNotEmpty(cuCategoryCodes)) {
                CEW323RRS rs1 = esbGateway.sendCEW323R(request.getCustId(), "J", "C");
                // TxHead.HERRID = V805,0000，視為電文發送成功
                if (StringUtils.equals(rs1.getHeader().getHERRID(), "V805") || StringUtils.equals(rs1.getHeader().getHERRID(), "0000")) {
                    // 從 DB 找出 CU01
                    for (String pushCode : cuCategoryCodes) {
                        logger.debug("updateNotfication CU01 code: " + pushCode);
                        PushSubscriptionEntity push = new PushSubscriptionEntity();
                        push.setDeviceInfoKey(deviceKey);
                        push.setPushCode(pushCode);
                        push.setCreateTime(new Date());
                        pushSubscriptionRepository.save(push);
                        succNotificationTypes.add(pushCode);
                    }
                }
            }
        }
        catch (XmlException | EAIException | EAIResponseException ex) {
            logger.warn("CEW323R 錯誤 {}", ex);
            failNotificationTypes.add("CU01");
        }
        // 簽帳卡刷卡消費通知
        try {
            List<String> cuCategoryCodes = child.stream().filter(p -> p.getIsGroup() == 0 && "CU02".equalsIgnoreCase(p.getPushCode())).map(AibankPushCategoryEntity::getCategory).toList();
            logger.debug("isEmpty: cuCategoryCodes: " + CollectionUtils.isEmpty(cuCategoryCodes));
            if (CollectionUtils.isNotEmpty(cuCategoryCodes)) {
                CEW323RRS rs1 = esbGateway.sendCEW323R(request.getCustId(), "J", "D");
                // TxHead.HERRID = V805,0000，視為電文發送成功
                if (StringUtils.equals(rs1.getHeader().getHERRID(), "V805") || StringUtils.equals(rs1.getHeader().getHERRID(), "0000")) {
                    // 從 DB 找出 CU02
                    for (String pushCode : cuCategoryCodes) {
                        PushSubscriptionEntity push = new PushSubscriptionEntity();
                        push.setDeviceInfoKey(deviceKey);
                        push.setPushCode(pushCode);
                        push.setCreateTime(new Date());
                        pushSubscriptionRepository.save(push);
                        succNotificationTypes.add(pushCode);
                    }
                }
            }
        }
        catch (XmlException | EAIException | EAIResponseException ex) {
            logger.warn("CEW323R 錯誤 {}", ex);
            failNotificationTypes.add("CU02");
        }

        // 存款通知
        try {
            EB552175RS rs1 = esbGateway.sendEB552175(request.getCustId(), request.getNameCode(), "1");
            // TxHead.HERRID = 0199，視為電文發送成功
            if (StringUtils.equals(rs1.getHeader().getHERRID(), "0199") || StringUtils.equals(rs1.getHeader().getHERRID(), "0000")) {
                for (String group : groups) {
                    // 只處理 Group: TX_A
                    if (!StringUtils.equalsIgnoreCase(group, "TX_A")) {
                        continue;
                    }
                    logger.debug("updateNotfication group (tx_a): " + group);
                    List<String> pushCodes = groupByTx.get(group);

                    for (String pushCode : pushCodes) {
                        logger.debug("updateNotfication code (tx_a): " + pushCode);
                        PushSubscriptionEntity push = new PushSubscriptionEntity();
                        push.setDeviceInfoKey(deviceKey);
                        push.setPushCode(pushCode);
                        push.setCreateTime(new Date());
                        pushSubscriptionRepository.save(push);
                        succNotificationTypes.add(pushCode);
                    }
                }
            }
        }
        catch (XmlException | EAIException | EAIResponseException ex) {
            logger.warn("EB552175 錯誤", ex);
            succNotificationTypes.add("TX01");
            succNotificationTypes.add("TX02");
            succNotificationTypes.add("TX03");
        }

        response.setFailNotificationTypes(failNotificationTypes);
        response.setSuccNotificationTypes(succNotificationTypes);
        return response;
    }


    /**
     * 更新推播通知設定
     *
     * @param request
     * @return
     * @throws ActionException
     */
    public void  updateLinePNP(UpdateNotficationRequest request) throws ActionException {

        UpdateNotficationResponse response = new UpdateNotficationResponse();

        SecretKeyProvider<?> provider = SecretKeyProviderFactory.getInstance().getProvider(AIBankConstants.CRYPTO_SECRET_KEY_PROVIDER_DEFAULT);
        AESCipherUtils aesCipherUtils = new AESCipherUtils(provider);

        if (request.getLineAgreeFlag() != -1) {
            String eCustId = "";

            try {
                eCustId = aesCipherUtils.encrypt(ValidateParamUtils.validParam(request.getCustId()));
            }
            catch (CryptException e) {
                logger.warn("", e);
            }
            // 【FORTIFY：Access Control: Database】eCustId 就是身分識別屬性欄位
            LinePNPRecordEntity entity = linePNPRecordRepository.findByCompanyUid(eCustId);

            if (entity == null) {
                entity = new LinePNPRecordEntity();
                entity.setCreateTime(new Date());
                entity.setCompanyUid(eCustId);
            }

            entity.setBirthday(request.getBirthday());
            entity.setMobilePhone(request.getMobileNo());
            entity.setPlatform(AIBankConstants.CHANNEL_NAME);
            entity.setTxKind("LOGINAI");
            String agreeFlag = request.getLineAgreeFlag() == 0 ? "N" : "Y";
            entity.setUpdateTime(new Date());
            entity.setAgreeFlag(agreeFlag);

            linePNPRecordRepository.save(entity);

        }
        else{
            logger.info("[DeviceBindingsService.updateLinePNP] LineAgreeFlag:{}", request.getLineAgreeFlag());
        }
    }

    /**
     * 無痛移轉更新推播通知設定
     * 
     * @param request
     * @return
     * @throws ActionException
     */
    public UpdateNotficationResponse updateNotfication4NoPain(UpdateNotficationNoPainRequest request) throws ActionException {

        UpdateNotficationResponse response = new UpdateNotficationResponse();

        IdentityData user = getUser(request.getCustId(), request.getUidDup(), request.getUserId(), request.getCompanyKind());

        List<String> failNotificationTypes = new ArrayList<String>();
        List<String> succNotificationTypes = new ArrayList<String>();

        // 訂閱移轉通知項目
        List<MbDeviceInfoEntity> mbdeviceInfoEntities = mbDeviceInfoRepository.findByCompanyKeyAndUserKey(user.getCompanyKey(), user.getUserKey());
        if (mbdeviceInfoEntities == null || mbdeviceInfoEntities.size() < 1) {
            logger.warn("訂閱移轉通知項目失敗，MB_DEVICE_INFO 找不到");
            return null;
        }
        MbDeviceInfoEntity mbdeviceInfo = mbdeviceInfoEntities.get(0);
        Integer deviceKey = mbdeviceInfo.getDeviceInfoKey();

        boolean isSaveingNotify = false;

        boolean isAnyItemSuccess = false;

        if (request.getNotificationTypes() != null && !request.getNotificationTypes().isEmpty()) {
            for (String notificationType : request.getNotificationTypes()) {

                String flagCEW323R = "";
                // 信用卡刷卡消費通知
                if ("CU01".equals(notificationType)) {
                    flagCEW323R = "C";
                }

                // 簽帳卡刷卡消費通知
                if ("CU02".equals(notificationType)) {
                    flagCEW323R = "D";
                }

                if (StringUtils.isNotBlank(flagCEW323R)) {
                    try {
                        CEW323RRS rs1 = esbGateway.sendCEW323R(request.getCustId(), "J", flagCEW323R);
                        // TxHead.HERRID = V805,0000，視為電文發送成功
                        if (StringUtils.equals(rs1.getHeader().getHERRID(), "V805") || StringUtils.equals(rs1.getHeader().getHERRID(), "0000")) {
                            PushSubscriptionEntity push = new PushSubscriptionEntity();
                            push.setDeviceInfoKey(deviceKey);
                            push.setPushCode(notificationType);
                            push.setCreateTime(new Date());
                            pushSubscriptionRepository.save(push);
                            succNotificationTypes.add(notificationType);
                            isAnyItemSuccess = true;
                        }
                        else {
                            failNotificationTypes.add(notificationType);
                        }
                    }
                    catch (XmlException | EAIException | EAIResponseException ex) {
                        logger.warn("CEW323R 錯誤 {}={}", notificationType, ex);
                        failNotificationTypes.add(notificationType);
                    }
                    continue;
                }

                // 存款通知
                if ("TX01".equals(notificationType) || "TX02".equals(notificationType) || "TX03".equals(notificationType)) {
                    if (!isSaveingNotify) {
                        isSaveingNotify = true;

                        try {
                            EB552175RS rs1 = esbGateway.sendEB552175(request.getCustId(), request.getNameCode(), "1");
                            // TxHead.HERRID = 0199，視為電文發送成功
                            if (StringUtils.equals(rs1.getHeader().getHERRID(), "0199") || StringUtils.equals(rs1.getHeader().getHERRID(), "0000")) {
                                boolean isSucc = true;

                                List<AibankPushCategoryEntity> child = this.aibankPushCategoryRepository.findByCompanyKind(request.getCompanyKind(), getUserLocale());
                                logger.debug("category from db: " + child);
                                child = CollectionUtils.isEmpty(child) ? new ArrayList<>() : child.stream().filter(o -> IBUtils.checkIsValidVersion(o.getVersion(), request.getVersion())).toList();
                                Map<String, List<String>> groupByTx = child.stream().collect(Collectors.groupingBy(AibankPushCategoryEntity::getCategory, // Group by category
                                        Collectors.mapping(AibankPushCategoryEntity::getPushCode, Collectors.toList())));// Map each entity to its pushCode
                                // TX01,TX02,TX03
                                List<String> txAPushCode = groupByTx.get("TX_A");

                                if (CollectionUtils.isNotEmpty(txAPushCode)) {
                                    for (String pushCode : txAPushCode) {
                                        if (isSucc) {
                                            // 從 DB 讀
                                            PushSubscriptionEntity push = new PushSubscriptionEntity();
                                            push.setDeviceInfoKey(deviceKey);
                                            push.setPushCode(pushCode);
                                            push.setCreateTime(new Date());
                                            pushSubscriptionRepository.save(push);
                                            succNotificationTypes.add(pushCode);
                                            isAnyItemSuccess = true;
                                        }
                                        else {
                                            failNotificationTypes.add(pushCode);
                                        }
                                    }
                                }
                            }
                        }
                        catch (XmlException | EAIException | EAIResponseException ex) {
                            logger.warn("EB552175 錯誤 {}", ex);
                            failNotificationTypes.add(notificationType);
                        }

                    }
                    continue;
                }

                PushSubscriptionEntity push = new PushSubscriptionEntity();
                push.setDeviceInfoKey(deviceKey);
                push.setPushCode(notificationType);
                push.setCreateTime(new Date());
                pushSubscriptionRepository.save(push);
                succNotificationTypes.add(notificationType);
                isAnyItemSuccess = true;
            }

            if (isAnyItemSuccess) {
                mbdeviceInfo.setNotifyFlag(1);
            }
            else {
                mbdeviceInfo.setNotifyFlag(0);
            }

            /*
             * MB_DEVICE_PUSH_INFO 的 Notify_Flag 註記
             */
            if (request.getNotificationAgreeFlag() != -1) {
                List<MbDevicePushInfoEntity> entities = mbDevicePushInfoRepository.findByCompanyKeyAndUserKey(user.getCompanyKey(), user.getUserKey());

                if (entities == null || entities.size() == 0) {
                    throw ExceptionUtils.getActionException(AIBankErrorCode.MOTP_BINDING_DEVICE_NOT_FOUND);
                }
                for (MbDevicePushInfoEntity entity : entities) {
                    if (isAnyItemSuccess) {
                        entity.setNotifyPass(1);
                        entity.setNotifyFlag(1);
                    }
                    else {
                        entity.setNotifyPass(0);
                        entity.setNotifyFlag(0);
                    }
                    entity.setNotifyAuthDate(new Date());
                    entity.setUpdateTime(new Date());
                    mbDevicePushInfoRepository.save(entity);
                }
            }

            mbDeviceInfoRepository.save(mbdeviceInfo);
        }

        response.setFailNotificationTypes(failNotificationTypes);
        response.setSuccNotificationTypes(succNotificationTypes);
        return response;

    }

    /**
     * 更新使用者裝置綁定資料
     * 
     * @param userId
     * @param userUuid
     * @param companyKind
     * @param request
     * @return
     * @throws ActionException
     */
    public boolean updateMbDeviceInfo(UpdateMbDeviceInfoRequest request) throws ActionException {

        IdentityData user = getUser(request.getCustId(), request.getUidDup(), request.getUserId(), request.getCompanyKind());

        // 【FORTIFY：Access Control: Database】COMPANY_KEY、USER_KEY 就是身分識別屬性欄位
        List<MbDeviceInfoEntity> entityList = mbDeviceInfoRepository.findByCompanyKeyAndUserKeyAndDeviceUuId(user.getCompanyKey(), user.getUserKey(), ValidateParamUtils.validParam(request.getDeviceUuid()));
        if (CollectionUtils.isNotEmpty(entityList) && entityList.size() == 1) {

            // 只能綁一筆資料
            MbDeviceInfoEntity entity = entityList.get(0);

            /**
             * 是否更新快速登入註記
             */
            if (request.isUpdateLoginFlag()) {
                entity.setLoginFlag(request.getLoginFlag());
                // 開啟快登
                if (request.getLoginFlag() == 1) {
                    entity.setLoginAuthDate(new Date());
                }
                // 關閉快登
                else {
                    entity.setLoginAuthDate(null);
                    entity.setLoginPasswdType(0);
                    mbGestureProfileRepository.deleteByDeviceInfoKey(entity.getDeviceInfoKey());
                }
            }

            /**
             * 是否更新快速登入密碼類型
             */
            if (request.isUpdateLoginPasswdType()) {
                entity.setLoginPasswdType(request.getLoginPasswdType());
            }

            /**
             * 是否更新有設定快登時，開啟APP是否直接登入
             */
            if (request.isUpdateDirectEzLoginFlag()) {
                entity.setDirectEzLoginFlag(request.getDirectEzLoginFlag());
            }

            entity.setUpdateTime(new Date());
            mbDeviceInfoRepository.save(entity);

        }
        else {
            // 沒資料或是超過一筆資料
            throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
        }

        return true;
    }

    private String getMarketingName(String model) {

        List<DeviceModelEntity> deviceModelList = null;
        try {
            deviceModelList = deviceModelRepository.findByDeviceModel(model);
        }
        catch (Exception ex) { // 【Fortify：Poor Error Handling: Overly Broad Throws】需要攔截所有例外
            logger.warn("DEVICE_MODEL 查詢失敗", ex);
        }

        if (deviceModelList == null || deviceModelList.size() == 0) {
            return model;
        }

        return deviceModelList.get(0).getMarketingName();
    }

    /**
     * 是否有推播訂閱
     * 
     * @param deviceuuid
     * @param pushCode
     * @return
     * @throws ActionException
     */
    public Boolean isHasPushSubscription(String deviceuuid, String pushCode, String custId, String uidDup, String userId, Integer companyKind) throws ActionException {
        IdentityData user = getUser(custId, uidDup, userId, companyKind);
        List<MbDeviceInfoEntity> mbDeviceInfoEnts = mbDeviceInfoRepository.findByCompanyKeyAndUserKeyAndDeviceUuId(user.getCompanyKey(), user.getUserKey(), deviceuuid);
        if (CollectionUtils.isNotEmpty(mbDeviceInfoEnts)) {
            List<PushSubscriptionEntity> pushSubcriptionEnts = pushSubscriptionRepository.findByDeviceInfoKeyAndPushCode(mbDeviceInfoEnts.get(0).getDeviceInfoKey(), pushCode);
            return CollectionUtils.isNotEmpty(pushSubcriptionEnts);
        }
        throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
    }

    /**
     * 更新推播設定狀態
     * 
     * @return
     * @throws ActionException
     */
    public Boolean updateDevicePushStatus(String deviceuuid, String custId, String uidDup, String userId, Integer companyKind) throws ActionException {
        IdentityData user = getUser(custId, uidDup, userId, companyKind);

        List<MbDevicePushInfoEntity> pushEntitys = mbDevicePushInfoRepository.findByCompanyKeyAndUserKeyAndDeviceUuId(user.getCompanyKey(), user.getUserKey(), deviceuuid);

        List<MbDeviceInfoEntity> mbDeviceInfoEnts = mbDeviceInfoRepository.findByCompanyKeyAndUserKeyAndDeviceUuId(user.getCompanyKey(), user.getUserKey(), deviceuuid);
        if (CollectionUtils.isNotEmpty(pushEntitys) && CollectionUtils.isNotEmpty(mbDeviceInfoEnts)) {
            MbDevicePushInfoEntity pushEntity = pushEntitys.get(0);
            pushEntity.setNotifyFlag(1);
            pushEntity.setUpdateTime(new Date());

            mbDevicePushInfoRepository.save(pushEntity);

            PushSubscriptionEntity pushSubscriptionEntity = new PushSubscriptionEntity();
            pushSubscriptionEntity.setCreateTime(new Date());
            pushSubscriptionEntity.setPushCode("PE05");
            pushSubscriptionEntity.setDeviceInfoKey(mbDeviceInfoEnts.get(0).getDeviceInfoKey());
            pushSubscriptionRepository.save(pushSubscriptionEntity);
            return true;
        }

        throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
    }

    /**
     * 取消到價通知
     * 
     * @return
     * @throws ActionException
     */
    public Boolean cannelDevicePushStatus(String deviceuuid, String pushCode, String custId, String uidDup, String userId, Integer companyKind) throws ActionException {
        IdentityData user = getUser(custId, uidDup, userId, companyKind);
        List<MbDeviceInfoEntity> mbDeviceInfoEnts = mbDeviceInfoRepository.findByCompanyKeyAndUserKeyAndDeviceUuId(user.getCompanyKey(), user.getUserKey(), deviceuuid);
        if (CollectionUtils.isNotEmpty(mbDeviceInfoEnts)) {
            List<PushSubscriptionEntity> pushSubscriptionEnts = pushSubscriptionRepository.findByDeviceInfoKeyAndPushCode(mbDeviceInfoEnts.get(0).getDeviceInfoKey(), pushCode);
            pushSubscriptionRepository.deleteAll(pushSubscriptionEnts);
            return true;
        }

        throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
    }

    /**
     * 是否有半年內 Line 拒絕紀錄
     * 
     * @param custId
     * @return
     */
    public Boolean isRefuseLineRegister(String custId) {

        Calendar cal = Calendar.getInstance(); // Get current date/month i.e 27 Feb, 2012
        cal.add(Calendar.MONTH, -6);

        String eCustId = "";
        SecretKeyProvider<?> provider = SecretKeyProviderFactory.getInstance().getProvider(AIBankConstants.CRYPTO_SECRET_KEY_PROVIDER_DEFAULT);
        AESCipherUtils aesCipherUtils = new AESCipherUtils(provider);

        try {
            eCustId = aesCipherUtils.encrypt(ValidateParamUtils.validParam(custId));
        }
        catch (CryptException e) {
            logger.warn("", e);
        }

        // 【FORTIFY：Access Control: Database】eCustId 就是身分識別屬性欄位
        LinePNPRecordEntity entity = linePNPRecordRepository.find(eCustId, cal.getTime());

        if (entity == null) {
            return false;
        }

        return true;

    }
}
