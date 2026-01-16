/**
 * 
 */
package com.tfb.aibank.biz.security.services.fido;

import java.util.Date;
import java.util.List;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.DataMaskUtil;
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
import com.tfb.aibank.biz.security.services.fido.helper.FidoServiceHelper;
import com.tfb.aibank.biz.security.services.fido.model.DoRevokeRequest;
import com.tfb.aibank.biz.security.services.fido.model.DoRevokeResponse;
import com.tfb.aibank.biz.security.services.fido.model.LoginRequest;
import com.tfb.aibank.biz.security.services.fido.model.LoginResponse;
import com.tfb.aibank.biz.security.services.fido.model.QueryLogResponse;
import com.tfb.aibank.biz.security.services.fido.model.QueryLogResponseRepeat;
import com.tfb.aibank.biz.security.services.fido.model.QueryVerifyResultRequest;
import com.tfb.aibank.biz.security.services.fido.model.QueryVerifyResultResponse;
import com.tfb.aibank.common.error.AIBankErrorCode;

//@formatter:off
/**
* @(#)FidoService.java
* 
* <p>Description:FIDO 相關服務</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/09, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class FidoService {

    private static final IBLog logger = IBLog.getLog(FidoService.class);

    /** 取得使用者服務 */
    private IdentityService identityService;

    private FidoServiceHelper fidoServiceHelper;

    private MbDeviceInfoRepository mbDeviceInfoRepository;

    private MbGestureProfileRepository mbGestureProfileRepository;

    private MbDevicePushInfoRepository mbDevicePushInfoRepository;

    private TrustDeviceRepository trustDeviceRepository;

    public FidoService(IdentityService identityService, FidoServiceHelper fidoServiceHelper, MbDeviceInfoRepository mbDeviceInfoRepository, MbGestureProfileRepository mbGestureProfileRepository, MbDevicePushInfoRepository mbDevicePushInfoRepository, TrustDeviceRepository trustDeviceRepository) {
        this.identityService = identityService;
        this.fidoServiceHelper = fidoServiceHelper;
        this.mbDeviceInfoRepository = mbDeviceInfoRepository;
        this.mbDevicePushInfoRepository = mbDevicePushInfoRepository;
        this.mbGestureProfileRepository = mbGestureProfileRepository;
        this.trustDeviceRepository = trustDeviceRepository;
    }

    /**
     * Login
     * 
     * @return
     * @throws ActionException
     */
    public LoginResponse login(LoginRequest request) throws ActionException {

        QueryLogResponse queryLogResponse = queryLog(request.getCustId());
        try {
            if ("FIDO".equals(request.getAction())) {
                if (queryLogResponse.getData() != null && queryLogResponse.getData().size() > 0) {
                    for (QueryLogResponseRepeat m : queryLogResponse.getData()) {
                        DoRevokeRequest rq = new DoRevokeRequest();
                        rq.setCustId(request.getCustId());
                        rq.setUidDup(request.getUidDup());
                        rq.setUserId(request.getUserId());
                        rq.setCompanyKind(request.getCompanyKind());
                        rq.setKeyId(m.getFidoKeyId());
                        rq.setDeviceId(request.getDeviceId());
                        doRevoke(rq);
                        logger.debug("deoke {}-{} succ", request.getCustId(), request.getCustId(), m.getFidoKeyId());
                    }
                }
            }
        }
        catch (Exception ex) { // 【Fortify：Poor Error Handling: Overly Broad Throws】需要攔截所有例外
            logger.error("DeVokde in Login fail", ex);
        }

        if ("SIGN".equals(request.getAction())) {
            if ("1".equals(queryLogResponse.getStatus())) {
                if ("F2057101".equals(queryLogResponse.getError())) {
                    disableFastLogin(request);
                }
                LoginResponse response = new LoginResponse();
                response.setStatus(queryLogResponse.getStatus());
                response.setError(queryLogResponse.getError());
                return response;
            }
        }

        LoginResponse loginResponse = fidoServiceHelper.login(request);

        logger.debug("====================> login RS={}, {}, ", loginResponse.getStatus(), loginResponse.getError());
        if ("SIGN".equals(request.getAction())) {
            if ("1".equals(loginResponse.getStatus())) {
                if ("F2057101".equals(loginResponse.getError())) {
                    disableFastLogin(request);
                }
                loginResponse.setStatus(queryLogResponse.getError());
            }
        }
        return loginResponse;

    }

    private void disableFastLogin(LoginRequest request) throws ActionException {
        IdentityData identityData = null;
        try {
            identityData = identityService.query(request.getCustId(), request.getUidDup(), request.getUserId(), request.getCompanyKind());
            mbDeviceInfoRepository.disableFastLoginByCompanyKeyAndUserKey(identityData.getCompanyKey(), identityData.getUserKey());
        }
        catch (CryptException e) {
            logger.error(e.getMessage(), e);
            throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
        }
    }

    /**
     * QueryVerifyResult
     * 
     * @return
     * @throws ActionException
     */
    public QueryVerifyResultResponse queryVerifyResult(QueryVerifyResultRequest request) throws ActionException {

        // ** QueryVerifyResult */
        QueryVerifyResultResponse queryVerifyResultResponse = fidoServiceHelper.queryVerifyResult(request);
        IdentityData identityData = null;
        try {
            if (StringUtils.isBlank(request.getCustId())) {
                logger.error("custId is not correct {}", DataMaskUtil.maskCustId(request.getCustId()));
                throw ExceptionUtils.getActionException(AIBankErrorCode.USER_NOT_FOUND);
            }
            if (StringUtils.isBlank(request.getUidDup())) {
                request.setUidDup("0");
            }
            identityData = identityService.query(request.getCustId(), request.getUidDup(), request.getUserId(), request.getCompanyKind());
        }
        catch (CryptException e) {
            logger.error("", e);
        }
        if (identityData == null || identityData.getCompanyKey() == 0) {
            logger.error("Company / UserProfile not found");
            return queryVerifyResultResponse;
        }

        if ("0".equals(queryVerifyResultResponse.getVerifyCode())) {
            queryVerifyResultResponse.setStatus("0");
            if (request.isRegisterDevice()) {
                saveDevice(identityData, request);
            }
        }

        return queryVerifyResultResponse;
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
    private void saveDevice(IdentityData identityData, QueryVerifyResultRequest request) {

        MbDeviceInfoEntity mbDeviceInfoEntity;
        MbDevicePushInfoEntity mbDevicePushInfoEntity;

        // 【FORTIFY：Access Control: Database】COMPANY_KEY、USER_KEY 就是身分識別屬性欄位
        List<MbDeviceInfoEntity> currentDeviceInfo = mbDeviceInfoRepository.findByCompanyKeyAndUserKeyAndDeviceUuId(identityData.getCompanyKey(), identityData.getUserKey(), ValidateParamUtils.validParam(request.getDeviceId()));

        if (currentDeviceInfo != null && currentDeviceInfo.size() > 0) {
            logger.info("裝置綁定, 既有裝置 {},{} = {}", identityData.getCompanyKey(), request.getDeviceId(), currentDeviceInfo.size());
            /** 目前手機已經綁定 */
            mbDeviceInfoEntity = currentDeviceInfo.get(0);

            // 【FORTIFY：Access Control: Database】COMPANY_KEY、USER_KEY 就是身分識別屬性欄位
            List<MbDevicePushInfoEntity> currentDevicePushInfo = mbDevicePushInfoRepository.findByCompanyKeyAndUserKeyAndDeviceUuId(identityData.getCompanyKey(), identityData.getUserKey(), ValidateParamUtils.validParam(request.getDeviceId()));
            if (currentDevicePushInfo != null && currentDevicePushInfo.size() > 0) {
                logger.info("裝置綁定, 既有裝置(push) {},{} = {}", identityData.getCompanyKey(), request.getDeviceId(), currentDevicePushInfo.size());
                /** 目前手機 MBDevicePushInfo 有資料 */
                mbDevicePushInfoEntity = currentDevicePushInfo.get(0);
            }
            else {
                mbDevicePushInfoEntity = getNewMbDevicePushInfoEntity(identityData, request);
            }
        }
        else {
            // Fortify - Log Forging//logger.info("裝置綁定, 本裝置未榜 {},{}", identityData.getCompanyKey(), request.getDeviceId());

            /** 目前手機沒綁定，刪除其他的 */
            /** (i) 刪除裝置綁定資料： DELETE FROM MB_DEVICE_INFO WHERE USER_KEY= AI Bank User之USER_KEY */
            mbDeviceInfoRepository.deleteByUserKey(identityData.getUserKey());
            mbDevicePushInfoRepository.deleteByUserKey(identityData.getUserKey());
            // #9122 裝置綁定&解除的時間點，刪除DB信任裝置資料
            logger.info("trust_device_deleteByUserKey fido saveDevice: >>> {}", identityData.getUserKey());
            trustDeviceRepository.deleteByUserKey(identityData.getUserKey());

            /** (i) 刪除裝置綁定資料： DELETE FROM MB_DEVICE_INFO WHERE DEVICE_UUID = 目前綁定的裝置 ID */
            mbDeviceInfoRepository.deleteByDeviceID(ValidateParamUtils.validParam(request.getDeviceId()));
            mbDevicePushInfoRepository.deleteByDeviceID(ValidateParamUtils.validParam(request.getDeviceId()));

            /** (ii) 刪除手勢設定資料： DELETE FROM AIBANK_GESTURE_PROFILE WHERE USER_KEY= AI Bank User之USER_KEY */
            mbGestureProfileRepository.deleteByUserKey(identityData.getUserKey());

            /** iii. 新增裝置綁定資料：寫入MB_DEVICE_INFO */
            mbDeviceInfoEntity = getNewMbDeviceInfoEntity(identityData, request);
            mbDevicePushInfoEntity = getNewMbDevicePushInfoEntity(identityData, request);
        }

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

    private MbDevicePushInfoEntity getNewMbDevicePushInfoEntity(IdentityData identityData, QueryVerifyResultRequest request) {
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

    private MbDeviceInfoEntity getNewMbDeviceInfoEntity(IdentityData identityData, QueryVerifyResultRequest request) {

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
     * QUERY-LOG
     * 
     * @param custId
     * @return
     * @throws ActionException
     */
    public QueryLogResponse queryLog(String custId) throws ActionException {

        return fidoServiceHelper.queryLog(custId);
    }

    /**
     * DO-REVOKE
     * 
     * @param request
     * @return
     * @throws ActionException
     */
    public DoRevokeResponse doRevoke(DoRevokeRequest request) throws ActionException {
        IdentityData identityData = null;
        try {
            identityData = identityService.query(request.getCustId(), request.getUidDup(), request.getUserId(), request.getCompanyKind());
        }
        catch (CryptException e) {
            logger.error(e.getMessage(), e);
            throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
        }

        // 【FORTIFY：Access Control: Database】COMPANY_KEY、USER_KEY 就是身分識別屬性欄位
        List<MbDeviceInfoEntity> currentDeviceInfo = mbDeviceInfoRepository.findByCompanyKeyAndUserKeyAndDeviceUuId(identityData.getCompanyKey(), identityData.getUserKey(), ValidateParamUtils.validParam(request.getDeviceId()));

        if (currentDeviceInfo != null && currentDeviceInfo.size() > 0) {
            logger.info("裝置綁定, 解綁，既有裝置 {} {}", identityData.getCompanyKey(), request.getDeviceId());
        }
        else {
            logger.info("裝置綁定, 解綁，新裝置 {} {}", identityData.getCompanyKey(), request.getDeviceId());
            mbDeviceInfoRepository.deleteByCompanyKeyAndUserKey(identityData.getCompanyKey(), identityData.getUserKey());
            mbGestureProfileRepository.deleteByCompanyKeyAndUserKey(identityData.getCompanyKey(), identityData.getUserKey());
            mbDevicePushInfoRepository.deleteByCompanyKeyAndUserKey(identityData.getCompanyKey(), identityData.getUserKey());
            // #9122 裝置綁定&解除的時間點，刪除DB信任裝置資料
            logger.info("trust_device_deleteByUserKey doRevoke: >>> {}", identityData.getUserKey());
            trustDeviceRepository.deleteByUserKey(identityData.getUserKey());
        }

        DoRevokeResponse doRevokeResponse = fidoServiceHelper.doRevoke(request);

        return doRevokeResponse;
    }

}
