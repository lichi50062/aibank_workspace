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
package com.tfb.aibank.biz.security.services.motp;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.component.crypto.AESCipherUtils;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProvider;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProviderFactory;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.biz.component.identity.IdentityData;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.security.repository.DeviceModelRepository;
import com.tfb.aibank.biz.security.repository.MbDeviceInfoRepository;
import com.tfb.aibank.biz.security.repository.MotpDeviceInfoRepository;
import com.tfb.aibank.biz.security.repository.MotpMidDataRepository;
import com.tfb.aibank.biz.security.repository.entities.DeviceModelEntity;
import com.tfb.aibank.biz.security.repository.entities.MbDeviceInfoEntity;
import com.tfb.aibank.biz.security.repository.entities.MotpDeviceInfoEntity;
import com.tfb.aibank.biz.security.repository.entities.MotpMidDataEntity;
import com.tfb.aibank.biz.security.services.motp.bean.CreateOtpUserRs;
import com.tfb.aibank.biz.security.services.motp.bean.DeleteOtpUserRs;
import com.tfb.aibank.biz.security.services.motp.bean.GetUserValidTokensRs;
import com.tfb.aibank.biz.security.services.motp.bean.InitPushRs;
import com.tfb.aibank.biz.security.services.motp.bean.RegisterTokenRs;
import com.tfb.aibank.biz.security.services.motp.bean.ViewUsersTokensRs;
import com.tfb.aibank.biz.security.services.motp.helper.MotpLogDataHelper;
import com.tfb.aibank.biz.security.services.motp.helper.MotpLogDataModel;
import com.tfb.aibank.biz.security.services.motp.helper.model.DeviceModel;
import com.tfb.aibank.biz.security.services.motp.model.ConfirmBindDeviceRequest;
import com.tfb.aibank.biz.security.services.motp.model.ConfirmBindDeviceResponse;
import com.tfb.aibank.biz.security.services.motp.model.CreateBindDeviceRequest;
import com.tfb.aibank.biz.security.services.motp.model.CreateBindDeviceResponse;
import com.tfb.aibank.biz.security.services.motp.model.DeleteBindDeviceRequest;
import com.tfb.aibank.biz.security.services.motp.model.DeleteBindDeviceResponse;
import com.tfb.aibank.biz.security.services.motp.type.MotpDeviceStatus;
import com.tfb.aibank.biz.security.services.motp.type.MotpStatusType;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.component.motplog.MotpLogActionType;

// @formatter:off
/**
 * @(#)MotpBindService.java
 * 
 * <p>Description:MOTP綁定服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/10, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class MotpBindService {

    private static final IBLog logger = IBLog.getLog(MotpBindService.class);

    /** AES Cipher 工具程式 */
    private AESCipherUtils aesCipherUtils = null;

    private IdentityService identityService;
    private MotpProxyService motpProxyService;
    private MotpDeviceInfoRepository motpDeviceInfoRepository;
    private MotpMidDataRepository motpMidDataRepository;
    private MbDeviceInfoRepository mbDeviceInfoRepository;
    private DeviceModelRepository deviceModelRepository;
    private MotpLogDataHelper motpLogDataHelper;

    public MotpBindService(IdentityService identityService, MotpProxyService motpProxyService, MotpDeviceInfoRepository motpDeviceInfoRepository, MotpMidDataRepository motpMidDataRepository, MbDeviceInfoRepository mbDeviceInfoRepository, DeviceModelRepository deviceModelRepository, MotpLogDataHelper motpLogDataHelper) {
        super();
        SecretKeyProvider<?> provider = SecretKeyProviderFactory.getInstance().getProvider(AIBankConstants.CRYPTO_SECRET_KEY_PROVIDER_DEFAULT);
        aesCipherUtils = new AESCipherUtils(provider);
        this.identityService = identityService;
        this.motpProxyService = motpProxyService;
        this.motpDeviceInfoRepository = motpDeviceInfoRepository;
        this.motpMidDataRepository = motpMidDataRepository;
        this.mbDeviceInfoRepository = mbDeviceInfoRepository;
        this.deviceModelRepository = deviceModelRepository;
        this.motpLogDataHelper = motpLogDataHelper;
    }

    /**
     * 建立MOTP設備綁定
     * 
     * @param request
     * @return
     * @throws ActionException
     * @throws CryptException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public CreateBindDeviceResponse createBindDevice(CreateBindDeviceRequest request) throws ActionException, CryptException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        CreateBindDeviceResponse response = new CreateBindDeviceResponse();

        String account = getEncryptAccount(request.getCustId());
        MotpLogDataModel motpLogData = motpLogDataHelper.createMotpLog(MotpLogActionType.API_GET_USER_VALID_TOKENS, request.getCustId(), request.getUserId(), request.getCompanyKind(), request.getUidDup(), "MotpProxyService", "callAPIGetUserValidTokens");

        // 查詢使用者綁定狀態
        GetUserValidTokensRs getUserValidTokensRs = motpProxyService.callAPIGetUserValidTokens(account, motpLogData);
        if (StringUtils.equals(MotpStatusType.MOTP_STATUS_OK.getCode(), getUserValidTokensRs.getCode())) {
            // code=0，表示已有綁定
            // 已有綁定時呼叫全景DeleteOTPUser，刪除OTP使用者（解除綁定）
            StringBuilder descBuilder = new StringBuilder(4096).append("CompanyKey=").append(motpLogData.getIdentityData().getCompanyKey()).append(",UserKey=").append(motpLogData.getIdentityData().getUserKey()).append(",DeviceIxd=").append(request.getDeviceIxd());
            String desc = StringUtils.left(descBuilder.toString(), 80);
            DeleteOtpUserRs deleteOtpUserRs = motpProxyService.callAPIDeleteOTPUser(account, desc, motpLogData);
            if (!StringUtils.equals(MotpStatusType.MOTP_STATUS_OK.getCode(), deleteOtpUserRs.getCode())) {
                // code≠0，視為刪除失敗，回到「推播條款頁」，並以Toast顯示錯誤訊息：使用者狀態查詢失敗
                logger.error("[MotpBindService] DeleteOtpUser 非預期之狀態碼[{}]{}", deleteOtpUserRs.getCode(), deleteOtpUserRs.getMessage());
                response.setGetUserVaildTokenFail(true);
                response.setErrorCode(deleteOtpUserRs.getCode());
                response.setErrorMsg(deleteOtpUserRs.getMessage());
                return response;
            }
            // 找出使用者已綁定的MOTP_DEVICE_INFO，設定為已註銷
            List<MotpDeviceInfoEntity> bindingList = motpDeviceInfoRepository.findByCompanyKeyAndUserKeyAndEnableAndGroup(motpLogData.getIdentityData().getCompanyKey(), motpLogData.getIdentityData().getUserKey(), MotpDeviceStatus.ENABLE, AIBankConstants.CHANNEL_NAME);
            if (CollectionUtils.isNotEmpty(bindingList)) {
                for (MotpDeviceInfoEntity motpDeviceInfoEntity : bindingList) {
                    motpDeviceInfoEntity.setEnable(MotpDeviceStatus.DISABLE);
                    motpDeviceInfoEntity.setDefeatType("MBANK");
                    motpDeviceInfoEntity.setDefeatTime(new Date());
                }
                motpDeviceInfoRepository.saveAll(bindingList);
            }
        }
        else if (!StringUtils.equals(MotpStatusType.MOTP_USER_ACOUNT_NOT_EXIST.getCode(), getUserValidTokensRs.getCode())) {
            // 不為66203時屬於不可預期之狀態碼，回到「推播條款頁」，並以Toast顯示錯誤訊息：使用者狀態查詢失敗
            logger.error("[MotpBindService] GetUserValidTokens 非預期之狀態碼[{}]{}", getUserValidTokensRs.getCode(), getUserValidTokensRs.getMessage());
            response.setGetUserVaildTokenFail(true);
            response.setErrorCode(getUserValidTokensRs.getCode());
            response.setErrorMsg(getUserValidTokensRs.getMessage());
            return response;
        }
        // code=66203，表示此帳號不存在，繼續下一步驟

        // 建立MOTP綁定帳戶
        StringBuilder descBuilder = new StringBuilder(4096).append("CompanyKey=").append(motpLogData.getIdentityData().getCompanyKey()).append(",UserKey=").append(motpLogData.getIdentityData().getUserKey()).append(",DeviceIxd=").append(request.getDeviceIxd());
        String desc = StringUtils.left(descBuilder.toString(), 80);
        CreateOtpUserRs createOtpUserRs = motpProxyService.callAPICreateOtpUser(account, desc, motpLogData);
        if (!StringUtils.equals(MotpStatusType.MOTP_STATUS_OK.getCode(), createOtpUserRs.getCode())) {
            // code≠0，建立MOTP綁定帳戶，回到「推播條款頁」
            logger.error("[MotpBindService] CreateOtpUser 非預期之狀態碼[{}]{}", createOtpUserRs.getCode(), createOtpUserRs.getMessage());
            response.setCreateOtpUserFail(true);
            response.setErrorCode(createOtpUserRs.getCode());
            response.setErrorMsg(createOtpUserRs.getMessage());
            return response;
        }

        // 取得帳戶綁定資訊
        InitPushRs initPushRs = motpProxyService.callAPIInitPush(account, motpLogData);
        if (!StringUtils.equals(MotpStatusType.MOTP_STATUS_OK.getCode(), initPushRs.getCode())) {
            // code≠0，取得帳戶綁定資訊失敗，回到「推播條款頁」
            logger.error("[MotpBindService] InitPushFail 非預期之狀態碼[{}]{}", initPushRs.getCode(), initPushRs.getMessage());
            response.setInitPushFail(true);
            response.setErrorCode(initPushRs.getCode());
            response.setErrorMsg(initPushRs.getMessage());
            return response;
        }

        // 更新行動裝置設定檔
        MbDeviceInfoEntity mbDeviceInfoEntity = null;
        List<MbDeviceInfoEntity> mbDeviceInfoEntityList = mbDeviceInfoRepository.findByCompanyKeyAndUserKeyAndDeviceUuId(motpLogData.getIdentityData().getCompanyKey(), motpLogData.getIdentityData().getUserKey(), request.getDeviceIxd());
        if (CollectionUtils.isEmpty(mbDeviceInfoEntityList)) {
            logger.error("[MotpBindService] createBindDevice 找不到行動裝置設定檔");
            return response;
        }
        mbDeviceInfoEntity = mbDeviceInfoEntityList.get(0);
        mbDeviceInfoEntity.setMotpFlag(0);
        mbDeviceInfoEntity.setMotpFlagDate(new Date());
        mbDeviceInfoEntity.setUpdateTime(new Date());
        mbDeviceInfoRepository.save(mbDeviceInfoEntity);

        // 取得行動裝置型號對應表資料
        if (StringUtils.isNoneBlank(request.getModel(), request.getLocale())) {

            DeviceModelEntity deviceModel = deviceModelRepository.findById(request.getModel()).orElse(null);
            if (deviceModel != null) {
                DeviceModel model = new DeviceModel();
                model.setDeviceModel(deviceModel.getDeviceModel());
                model.setBrand(deviceModel.getBrand());
                model.setMarketingName(deviceModel.getMarketingName());
                response.setDeviceModel(model);
            }
        }

        // 設置回傳資料
        response.setAccount(initPushRs.getData().getAccount());
        response.setFlag(initPushRs.getData().getFlag());
        response.setIk(initPushRs.getData().getIk());
        response.setPushKey(initPushRs.getData().getPushKey());
        response.setType(initPushRs.getData().getType());

        return response;
    }

    /**
     * 確認建立MOTP設備綁定
     * 
     * @param request
     * @return
     * @throws CryptException
     * @throws ActionException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public ConfirmBindDeviceResponse confirmBindDevice(ConfirmBindDeviceRequest request) throws CryptException, ActionException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        ConfirmBindDeviceResponse response = new ConfirmBindDeviceResponse();

        String account = getEncryptAccount(request.getCustId());

        MotpLogDataModel motpLogData = motpLogDataHelper.createMotpLog(MotpLogActionType.API_REGISTER_TOKEN, request.getCustId(), request.getUserId(), request.getCompanyKind(), request.getUidDup(), "MotpProxyService", "callAPIRegisterToken");

        // 完成註冊綁定資訊
        String mobileType = getMobileType(request.getDevicePlatform());
        RegisterTokenRs registerTokenRs = motpProxyService.callAPIRegisterToken(account, request.getDeviceId(), request.getSn(), request.getPushId(), mobileType, motpLogData);
        if (!StringUtils.equals(MotpStatusType.MOTP_STATUS_OK.getCode(), registerTokenRs.getCode())) {
            // code≠0，註冊失敗，回到「推播條款頁」
            logger.error("[MotpBindService] RegisterToken 非預期之狀態碼[{}]{}", registerTokenRs.getCode(), registerTokenRs.getMessage());
            response.setRegisterTokenFail(true);
            response.setErrorCode(registerTokenRs.getCode());
            response.setErrorMsg(registerTokenRs.getMessage());
            return response;
        }

        // 更新行動裝置設定檔
        MbDeviceInfoEntity mbDeviceInfoEntity = null;
        List<MbDeviceInfoEntity> mbDeviceInfoEntityList = mbDeviceInfoRepository.findByCompanyKeyAndUserKeyAndDeviceUuId(motpLogData.getIdentityData().getCompanyKey(), motpLogData.getIdentityData().getUserKey(), request.getDeviceIxd());
        if (CollectionUtils.isEmpty(mbDeviceInfoEntityList)) {
            logger.error("[MotpBindService] confirmBindDevice 找不到行動裝置設定檔");
            return response;
        }
        mbDeviceInfoEntity = mbDeviceInfoEntityList.get(0);
        mbDeviceInfoEntity.setMotpFlag(1);
        mbDeviceInfoEntity.setMotpFlagDate(new Date());
        mbDeviceInfoEntity.setUpdateTime(new Date());
        mbDeviceInfoRepository.save(mbDeviceInfoEntity);

        // 新增MOTP裝置綁定資訊
        MotpDeviceInfoEntity motpDeviceInfoEntity = generateMotpDeviceInfo(request, motpLogData.getIdentityData(), account, mbDeviceInfoEntity);
        motpDeviceInfoEntity = motpDeviceInfoRepository.save(motpDeviceInfoEntity);

        // 將MID驗證資訊關聯至MOTP_DEVICE_INFO
        updateMidAuthData(motpDeviceInfoEntity.getMotpDeviceKey(), request.getMotpMidDataKeyList());

        return response;
    }

    /**
     * 停用MOTP設備綁定
     * 
     * @param request
     * @return
     * @throws CryptException
     * @throws ActionException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public DeleteBindDeviceResponse deleteBindDevice(DeleteBindDeviceRequest request) throws CryptException, ActionException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        DeleteBindDeviceResponse response = new DeleteBindDeviceResponse();

        String account = getEncryptAccount(request.getCustId());
        MotpLogDataModel motpLogData = motpLogDataHelper.createMotpLog(MotpLogActionType.API_DELETE_OTP_USER, request.getCustId(), request.getUserId(), request.getCompanyKind(), request.getUidDup(), "MotpProxyService", "callAPIDeleteOTPUser");

        // 取得使用者載具資訊
        ViewUsersTokensRs viewUsersTokensRs = motpProxyService.callAPIViewUsersTokens(account, motpLogData);
        if (StringUtils.equals(MotpStatusType.MOTP_USER_ACOUNT_NOT_EXIST.getCode(), viewUsersTokensRs.getCode())) {
            // code = 66203，表示此帳號不存在，回到「功能首頁」
            response.setUserNotBind(true);
            response.setErrorCode(viewUsersTokensRs.getCode());
            response.setErrorMsg(viewUsersTokensRs.getMessage());
            return response;
        }
        else if (!StringUtils.equals(MotpStatusType.MOTP_STATUS_OK.getCode(), viewUsersTokensRs.getCode())) {
            // 不為0時屬於不可預期之狀態碼，回到「功能首頁」
            logger.error("[MotpBindService] ViewUsersTokens 非預期之狀態碼[{}]{}", viewUsersTokensRs.getCode(), viewUsersTokensRs.getMessage());
            response.setGetUserVaildTokenFail(true);
            response.setErrorCode(viewUsersTokensRs.getCode());
            response.setErrorMsg(viewUsersTokensRs.getMessage());
            return response;
        }
        // code=0，表示已有綁定，繼續下一步驟

        // 刪除OTP使用者（解除綁定）
        StringBuilder descBuilder = new StringBuilder(4096).append("CompanyKey=").append(motpLogData.getIdentityData().getCompanyKey()).append(",UserKey=").append(motpLogData.getIdentityData().getUserKey()).append(",DeviceIxd=").append(request.getDeviceIxd());
        String desc = StringUtils.left(descBuilder.toString(), 80);
        DeleteOtpUserRs deleteOtpUserRs = motpProxyService.callAPIDeleteOTPUser(account, desc, motpLogData);
        if (!StringUtils.equals(MotpStatusType.MOTP_STATUS_OK.getCode(), deleteOtpUserRs.getCode())) {
            // code≠0，視為刪除失敗
            logger.error("[MotpBindService] DeleteOtpUser 非預期之狀態碼[{}]{}", deleteOtpUserRs.getCode(), deleteOtpUserRs.getMessage());
            response.setDeleteOtpUserFail(true);
            response.setErrorCode(deleteOtpUserRs.getCode());
            response.setErrorMsg(deleteOtpUserRs.getMessage());
            return response;
        }

        // 更新行動裝置設定檔
        MbDeviceInfoEntity mbDeviceInfoEntity = null;
        List<MbDeviceInfoEntity> mbDeviceInfoEntityList = mbDeviceInfoRepository.findByCompanyKeyAndUserKey(motpLogData.getIdentityData().getCompanyKey(), motpLogData.getIdentityData().getUserKey());
        if (CollectionUtils.isEmpty(mbDeviceInfoEntityList)) {
            logger.info("motp8380_2: companyKey: " + motpLogData.getIdentityData().getCompanyKey() + ", userKey: " + motpLogData.getIdentityData().getUserKey());
            logger.error("[MotpBindService] deleteBindDevice 找不到行動裝置設定檔");
            throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
        }
        mbDeviceInfoEntity = mbDeviceInfoEntityList.get(0);
        mbDeviceInfoEntity.setMotpFlag(0);
        mbDeviceInfoEntity.setMotpFlagDate(new Date());
        mbDeviceInfoEntity.setUpdateTime(new Date());
        mbDeviceInfoRepository.save(mbDeviceInfoEntity);

        // 更新MOTP裝置綁定資訊
        MotpDeviceInfoEntity motpDeviceInfoEntity = null;
        List<MotpDeviceInfoEntity> motpDeviceInfoEntityList = motpDeviceInfoRepository.findByCompanyKeyAndUserKeyAndEnableAndGroup(motpLogData.getIdentityData().getCompanyKey(), motpLogData.getIdentityData().getUserKey(), MotpDeviceStatus.ENABLE, AIBankConstants.CHANNEL_NAME);
        if (CollectionUtils.isEmpty(motpDeviceInfoEntityList)) {
            logger.error("[MotpBindService] deleteBindDevice 找不到MOTP裝置綁定資訊");
            throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
        }
        motpDeviceInfoEntity = motpDeviceInfoEntityList.get(0);
        motpDeviceInfoEntity.setEnable(MotpDeviceStatus.DISABLE);
        motpDeviceInfoEntity.setDefeatType("MBANK");
        motpDeviceInfoEntity.setDefeatTime(new Date());
        motpDeviceInfoRepository.save(motpDeviceInfoEntity);

        // 設置回傳資料
        response.setClientId(motpDeviceInfoEntity.getClientId());

        return response;
    }

    /**
     * 產生MOTP裝置綁定資訊
     * 
     * @param request
     * @param identityData
     * @param account
     * @param mbDeviceInfoEntity
     * @return
     */
    private MotpDeviceInfoEntity generateMotpDeviceInfo(ConfirmBindDeviceRequest request, IdentityData identityData, String account, MbDeviceInfoEntity mbDeviceInfoEntity) {
        MotpDeviceInfoEntity motpDeviceInfoEntity = new MotpDeviceInfoEntity();
        Date now = new Date();
        motpDeviceInfoEntity.setCompanyKey(identityData.getCompanyKey());
        motpDeviceInfoEntity.setUserKey(identityData.getUserKey());
        motpDeviceInfoEntity.setDeviceInfoKey(mbDeviceInfoEntity.getDeviceInfoKey());
        motpDeviceInfoEntity.setDeviceUuid(request.getDeviceIxd());
        motpDeviceInfoEntity.setPushId(request.getPushId());
        motpDeviceInfoEntity.setAccountId(account);
        motpDeviceInfoEntity.setSn(request.getSn());
        motpDeviceInfoEntity.setClientId(request.getClientId());
        motpDeviceInfoEntity.setMachineCode(request.getDeviceId());
        motpDeviceInfoEntity.setEnable(MotpDeviceStatus.ENABLE);
        motpDeviceInfoEntity.setGroup(AIBankConstants.CHANNEL_NAME);
        motpDeviceInfoEntity.setModel(request.getModel());
        motpDeviceInfoEntity.setClientIp(request.getClientIp());
        motpDeviceInfoEntity.setDevicePlatform(IBUtils.displayPlatform(request.getDevicePlatform()));
        motpDeviceInfoEntity.setDevicePlatformVersion(request.getDevicePlatformVersion());
        motpDeviceInfoEntity.setSessionId(request.getSessionId());
        motpDeviceInfoEntity.setCreateTime(now);
        motpDeviceInfoEntity.setCompleteTime(now);
        motpDeviceInfoEntity.setSessionId(request.getSessionId());
        motpDeviceInfoEntity.setMotpTermsVer(request.getMotpTermsVer());
        return motpDeviceInfoEntity;
    }

    /**
     * 將MID驗證資訊關聯至MOTP_DEVICE_INFO
     * 
     * @param deviceInfoKey
     * @param motpMidDataKeyList
     */
    private void updateMidAuthData(Integer deviceInfoKey, List<Integer> motpMidDataKeyList) {
        try {
            if (CollectionUtils.isNotEmpty(motpMidDataKeyList)) {
                for (Integer key : motpMidDataKeyList) {
                    // 【FORTIFY：Access Control: Database】誤判，條件為主鍵
                    Optional<MotpMidDataEntity> entityOpt = motpMidDataRepository.findById(key);
                    if (entityOpt.isPresent()) {
                        MotpMidDataEntity entity = entityOpt.get();
                        entity.setMotpDeviceKey(deviceInfoKey);
                        motpMidDataRepository.save(entity);
                    }
                }
            }
        }
        catch (Exception e) { // 【Fortify：Poor Error Handling: Overly Broad Throws】需要攔截所有例外
            logger.warn("[MotpBindService] 將MID驗證資訊關聯至MOTP_DEVICE_INFO時發生錯誤，不影響程序。", e);
        }
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
    private MotpLogDataModel getUser(String custId, String userId, int companyKind, String uidDup) throws ActionException {

        try {
            IdentityData identityData = identityService.query(custId, uidDup, userId, companyKind);
            if (identityData != null && identityData.isAliveUser()) {
                MotpLogDataModel logModel = new MotpLogDataModel();

                logModel.setCustId(custId);
                logModel.setUserId(userId);
                logModel.setIdentityData(identityData);
                return logModel;
            }
        }
        catch (CryptException e) {
            logger.error(e.getMessage(), e);
        }
        throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
    }

    /**
     * 轉換手機類型
     * 
     * @param devicePlatform
     * @return
     */
    private String getMobileType(String devicePlatform) {
        if (StringUtils.equalsIgnoreCase("android", devicePlatform)) {
            return "1";
        }
        else if (StringUtils.equalsIgnoreCase("ios", devicePlatform)) {
            return "2";
        }
        return "";
    }

    private String getEncryptAccount(String custId) throws CryptException {
        return aesCipherUtils.encrypt(custId) + "AIBANK";
    }

}