/**
 * 
 */
package com.tfb.aibank.biz.security.services.devicebinding;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.ValidateParamUtils;
import com.tfb.aibank.biz.component.identity.IdentityData;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.security.repository.MbDeviceInfoRepository;
import com.tfb.aibank.biz.security.repository.MbGestureProfileRepository;
import com.tfb.aibank.biz.security.repository.TrustDeviceRepository;
import com.tfb.aibank.biz.security.repository.aib.MbDevicePushInfoRepository;
import com.tfb.aibank.biz.security.repository.aib.entities.MbDevicePushInfoEntity;
import com.tfb.aibank.biz.security.repository.entities.MbDeviceInfoEntity;
import com.tfb.aibank.biz.security.services.devicebinding.model.AddMbDeviceInfoRequest;
import com.tfb.aibank.biz.security.services.devicebinding.model.BindingAuthFlagRequest;
import com.tfb.aibank.biz.security.services.devicebinding.model.BindingAuthFlagResponse;
import com.tfb.aibank.biz.security.services.devicebinding.model.DeleteMbDeviceInfoRequest;
import com.tfb.aibank.biz.security.services.devicebinding.model.UpdateLoginTypeRequest;
import com.tfb.aibank.biz.security.services.devicebinding.model.UpdateLoginTypeResponse;
import com.tfb.aibank.biz.security.services.fido.FidoService;
import com.tfb.aibank.common.error.AIBankErrorCode;

//@formatter:off
/**
* @(#)DeviceBindingService.java
* 
* <p>Description:裝置綁定 相關服務</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/19, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class DeviceBindingService {

    private static final IBLog logger = IBLog.getLog(FidoService.class);

    /** 取得使用者服務 */
    private IdentityService identityService;

    private MbDeviceInfoRepository mbDeviceInfoRepository;

    private MbDevicePushInfoRepository mbDevicePushInfoRepository;

    private MbGestureProfileRepository mbGestureProfileRepository;

    private TrustDeviceRepository trustDeviceRepository;

    public DeviceBindingService(IdentityService identityService, MbDeviceInfoRepository mbDeviceInfoRepository, MbGestureProfileRepository mbGestureProfileRepository, MbDevicePushInfoRepository mbDevicePushInfoRepository, TrustDeviceRepository trustDeviceRepository) {
        this.identityService = identityService;
        this.mbDeviceInfoRepository = mbDeviceInfoRepository;
        this.mbGestureProfileRepository = mbGestureProfileRepository;
        this.mbDevicePushInfoRepository = mbDevicePushInfoRepository;
        this.trustDeviceRepository = trustDeviceRepository;
    }

    public UpdateLoginTypeResponse updateLoginType(UpdateLoginTypeRequest request) throws ActionException {
        UpdateLoginTypeResponse resposne = new UpdateLoginTypeResponse();

        IdentityData identityData = null;
        try {
            if (StringUtils.isBlank(request.getCustId()) || request.getCustId().length() != 11) {
                logger.error("CustId 錯誤 {}", request.getCustId());
                return resposne;
            }
            identityData = identityService.query(request.getCustId().substring(0, 10), request.getCustId().substring(10), request.getUserId(), request.getCompanyKind());
        }
        catch (CryptException e) {
            logger.error("", e);
            throw ExceptionUtils.getActionException(AIBankErrorCode.USER_NOT_FOUND);
        }

        List<MbDeviceInfoEntity> entities = mbDeviceInfoRepository.findByCompanyKeyAndUserKey(identityData.getCompanyKey(), identityData.getUserKey());

        if (entities == null || entities.size() == 0) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.DEVICE_BINDING_FAILED);
        }

        for (MbDeviceInfoEntity entity : entities) {
            if (entity.getEnable() == 1) {
                entity.setLoginPasswdType(request.getLoginPasswordType());
                mbDeviceInfoRepository.save(entity);
                break;
            }
        }

        if (request.getLoginPasswordType() != 1) {
            mbGestureProfileRepository.deleteByUserKey(identityData.getUserKey());
        }

        return resposne;
    }

    /**
     * 新增 MB_DEVICE_INFO Request
     * 
     * @param request
     * @return
     * @throws CryptException
     * @throws ActionException
     */
    public Boolean addMbDeviceInfo(AddMbDeviceInfoRequest request) throws CryptException, ActionException {
        IdentityData identityData = null;
        try {
            identityData = identityService.query(request.getCustId(), request.getUidDup(), request.getUserId(), request.getCompanyKind());
        }
        catch (CryptException e) {
            logger.error(e.getMessage(), e);
            throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
        }
        saveDevice(identityData, request);
        return true;
    }

    /**
     * 刪除 MB_DEVICE_INFO
     * 
     * @param request
     * @return
     * @throws CryptException
     * @throws ActionException
     */
    public Boolean deleteMbDeviceInfo(DeleteMbDeviceInfoRequest request) throws CryptException, ActionException {
        IdentityData identityData = null;
        try {
            identityData = identityService.query(request.getCustId(), request.getUidDup(), request.getUserId(), request.getCompanyKind());
        }
        catch (CryptException e) {
            logger.error(e.getMessage(), e);
            throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
        }
        mbDeviceInfoRepository.deleteByCompanyKeyAndUserKey(identityData.getCompanyKey(), identityData.getUserKey());
        mbGestureProfileRepository.deleteByCompanyKeyAndUserKey(identityData.getCompanyKey(), identityData.getUserKey());
        mbDevicePushInfoRepository.deleteByCompanyKeyAndUserKey(identityData.getCompanyKey(), identityData.getUserKey());
        logger.info("trust_device_deleteByUserKey deleteMbDeviceInfo: >>> {}", identityData.getUserKey());
        trustDeviceRepository.deleteByUserKey(identityData.getUserKey());
        return true;
    }

    /**
     * 裝置綁定
     * 
     * ii. 解除該客戶既有的裝置綁定， (i) 刪除裝置綁定資料： DELETE FROM MB_DEVICE_INFO WHERE USER_KEY= AI Bank User之USER_KEY
     * 
     * (ii) 刪除手勢設定資料： DELETE FROM AIBANK_GESTURE_PROFILE WHERE USER_KEY= AI Bank User之USER_KEY
     * 
     * iii. 新增裝置綁定資料：寫入MB_DEVICE_INFO
     * 
     * @param identityData
     * @param request
     */
    private void saveDevice(IdentityData identityData, AddMbDeviceInfoRequest request) {

        /** (i) 刪除裝置綁定資料： DELETE FROM MB_DEVICE_INFO WHERE USER_KEY= AI Bank User之USER_KEY */
        mbDeviceInfoRepository.deleteByUserKey(identityData.getUserKey());
        mbDevicePushInfoRepository.deleteByUserKey(identityData.getUserKey());

        /** (i) 刪除裝置綁定資料： DELETE FROM MB_DEVICE_INFO WHERE DEVICE_UUID = 目前綁定的裝置 ID */
        mbDeviceInfoRepository.deleteByDeviceID(ValidateParamUtils.validParam(request.getDeviceId()));
        mbDevicePushInfoRepository.deleteByDeviceID(ValidateParamUtils.validParam(request.getDeviceId()));

        /** (ii) 刪除手勢設定資料： DELETE FROM AIBANK_GESTURE_PROFILE WHERE USER_KEY= AI Bank User之USER_KEY */
        mbGestureProfileRepository.deleteByUserKey(identityData.getUserKey());
        // #9122 裝置綁定&解除的時間點，刪除DB信任裝置資料
        logger.info("trust_device_deleteByUserKey saveDevice: >>> {}", identityData.getUserKey());
        trustDeviceRepository.deleteByUserKey(identityData.getUserKey());

        /** iii. 新增裝置綁定資料：寫入MB_DEVICE_INFO */
        MbDeviceInfoEntity mbDeviceInfoEntity = getNewMbDeviceInfoEntity(identityData, request);

        /** iii.-1 新增裝置推播資料：寫入MB_DEVICE_INFO */
        MbDevicePushInfoEntity mbDevicePushInfoEntity = getNewMbDevicePushInfoEntity(identityData, request);

        // FIDO綁定+設定快登
        if (request.getLoginPasswordType() > 0) {
            // [初始時必填欄位，無授權則帶入0]簡易登入 0:未授權 1:已授權
            mbDeviceInfoEntity.setLoginFlag(1);
            // 簡易登入授權日期
            mbDeviceInfoEntity.setLoginAuthDate(new Date());
            // 快速登入密碼類型
            mbDeviceInfoEntity.setLoginPasswdType(request.getLoginPasswordType());
        }

        mbDeviceInfoRepository.save(mbDeviceInfoEntity);
        mbDevicePushInfoRepository.save(mbDevicePushInfoEntity);
    }

    private MbDevicePushInfoEntity getNewMbDevicePushInfoEntity(IdentityData identityData, AddMbDeviceInfoRequest request) {
        MbDevicePushInfoEntity mbDevicePushInfoEntity = new MbDevicePushInfoEntity();
        mbDevicePushInfoEntity.setCompanyKey(identityData.getCompanyKey());
        mbDevicePushInfoEntity.setCreateTime(new Date());
        mbDevicePushInfoEntity.setDevicePlatform(request.getDevicePlatform());
        mbDevicePushInfoEntity.setDeviceUuId(request.getDeviceId());
        mbDevicePushInfoEntity.setNotifyFlag(0);
        mbDevicePushInfoEntity.setNotifyPass(0);
        mbDevicePushInfoEntity.setPushToken(request.getPushToken());
        mbDevicePushInfoEntity.setUpdateTime(new Date());
        mbDevicePushInfoEntity.setUserKey(identityData.getUserKey());
        return mbDevicePushInfoEntity;
    }

    private MbDeviceInfoEntity getNewMbDeviceInfoEntity(IdentityData identityData, AddMbDeviceInfoRequest request) {

        MbDeviceInfoEntity mbDeviceInfoEntity = new MbDeviceInfoEntity();

        mbDeviceInfoEntity.setDeviceUuId(request.getDeviceId());
        mbDeviceInfoEntity.setCompanyKey(identityData.getCompanyKey());
        mbDeviceInfoEntity.setUserKey(identityData.getUserKey());
        mbDeviceInfoEntity.setModel(request.getModel());
        mbDeviceInfoEntity.setDevicePlatform(request.getDevicePlatform());
        mbDeviceInfoEntity.setDevicePlatformVersion(request.getDevicePlatformVersion());
        mbDeviceInfoEntity.setDeviceAlias(request.getDeviceAlias());
        mbDeviceInfoEntity.setLoginFlag(0);
        mbDeviceInfoEntity.setClientIp(request.getIp());
        mbDeviceInfoEntity.setMsgFlag(0);
        mbDeviceInfoEntity.setMsgPassword("0");
        mbDeviceInfoEntity.setEnable(1);
        mbDeviceInfoEntity.setCreateTime(new Date());
        mbDeviceInfoEntity.setUpdateTime(new Date());
        mbDeviceInfoEntity.setLoginType(request.getLoginType());
        mbDeviceInfoEntity.setErrorFlag(0);
        mbDeviceInfoEntity.setQsearchFlag(0);
        mbDeviceInfoEntity.setChgPwdUseridFlag(0);
        mbDeviceInfoEntity.setMotpFlag(0);
        mbDeviceInfoEntity.setDirectEzLoginFlag(1);
        return mbDeviceInfoEntity;
    }

    /**
     * 查詢/設定裝置綁定授權註記
     * 
     * @param request
     * @return
     * @throws CryptException
     */
    public BindingAuthFlagResponse getBindingAuthFlag(BindingAuthFlagRequest request) throws CryptException {
        BindingAuthFlagResponse response = new BindingAuthFlagResponse();
        response.setStatus(1);

        IdentityData identityData = identityService.query(request.getCustId(), request.getUidDup(), request.getUserId(), request.getCompanyKind());

        List<MbDeviceInfoEntity> deviceInfoEntities = mbDeviceInfoRepository.findByCompanyKeyAndUserKeyAndDeviceUuId(identityData.getCompanyKey(), identityData.getUserKey(), request.getDeviceIxd());

        /**
         * 未綁定
         */
        if (CollectionUtils.isEmpty(deviceInfoEntities)) {
            return response;
        }
        MbDeviceInfoEntity deviceInfoEntity = deviceInfoEntities.get(0);

        if (request.getAction() == 0) {
            response.setWithdrawalFlag(deviceInfoEntity.getWithdrawalFlag());
            response.setPhoneTransferFlag(deviceInfoEntity.getPhoneTransferFlag());
            response.setRaiseTransferFlag(deviceInfoEntity.getRaiseTransferFlag());
            response.setStatus(0);
        }
        else {
            // 更新
            if (request.getWithdrawalFlag() != null) {
                deviceInfoEntity.setWithdrawalFlag(request.getWithdrawalFlag());
            }
            if (request.getPhoneTransferFlag() != null) {
                deviceInfoEntity.setPhoneTransferFlag(request.getPhoneTransferFlag());
            }
            if (request.getRaiseTransferFlag() != null) {
                deviceInfoEntity.setRaiseTransferFlag(request.getRaiseTransferFlag());
            }
            mbDeviceInfoRepository.save(deviceInfoEntity);
            response.setStatus(0);
        }

        return response;
    }

}
