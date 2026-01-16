package com.tfb.aibank.biz.user.services.login;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.MDC;
import org.springframework.orm.jpa.JpaSystemException;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.exception.DatabaseException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.ibm.tw.ibmb.component.crypto.AESCipherUtils;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProvider;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProviderFactory;
import com.ibm.tw.ibmb.util.ValidateParamUtils;
import com.tfb.aibank.biz.user.repository.ChangePasswordRecordRepository;
import com.tfb.aibank.biz.user.repository.MbDeviceInfoRepository;
import com.tfb.aibank.biz.user.repository.NonAIMbDeviceInfoEntityRepository;
import com.tfb.aibank.biz.user.repository.UserLoginRepository;
import com.tfb.aibank.biz.user.repository.UserRepository;
import com.tfb.aibank.biz.user.repository.aib.entities.MbDevicePushInfoEntity;
import com.tfb.aibank.biz.user.repository.entities.CardUserProfileEntity;
import com.tfb.aibank.biz.user.repository.entities.ChangePasswordRecordEntity;
import com.tfb.aibank.biz.user.repository.entities.CompanyEntity;
import com.tfb.aibank.biz.user.repository.entities.MbDeviceInfoEntity;
import com.tfb.aibank.biz.user.repository.entities.NonAIMbDeviceInfoEntity;
import com.tfb.aibank.biz.user.repository.entities.UserEntity;
import com.tfb.aibank.biz.user.repository.entities.UserLoginEntity;
import com.tfb.aibank.biz.user.resource.SecurityResource;
import com.tfb.aibank.biz.user.resource.model.EncodeWithSecretRequest;
import com.tfb.aibank.biz.user.resource.model.EncodeWithSecretResponse;
import com.tfb.aibank.biz.user.services.login.model.ExecuteChangeCcUserPinRequest;
import com.tfb.aibank.biz.user.services.login.model.ExecuteChangeCcUserPinResponse;
import com.tfb.aibank.chl.session.vo.CardUserProfileVo;
import com.tfb.aibank.chl.session.vo.CompanyVo;
import com.tfb.aibank.chl.session.vo.MbDeviceInfoVo;
import com.tfb.aibank.chl.session.vo.UserLoginVo;
import com.tfb.aibank.chl.session.vo.UserVo;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.common.type.E2EEHsmType;
import com.tfb.aibank.common.type.TxSourceType;
import com.tfb.aibank.common.type.TxStatusType;
import com.tfb.aibank.common.util.BizExceptionUtils;

// @formatter:off
/**
 * @(#)ExecuteUserLoginService.java
 * 
 * <p>Description:更換信用卡使用者密碼</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/03, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class ExecuteChangeCcUserPinService {

    private static final IBLog logger = IBLog.getLog(ExecuteChangeCcUserPinService.class);

    private UserRepository userProfileRepository;

    private UserLoginRepository userLoginRepository;

    private AESCipherUtils aesCipherUtils = null;

    private SecurityResource securityResource;

    private MbDeviceInfoRepository mbDeviceInfoRepository;

    private ChangePasswordRecordRepository changePasswordRecordRepository;

    private NonAIMbDeviceInfoEntityRepository nonAIMbDeviceInfoEntityRepository;

    private static final int MAX_PASSWORD_FAIL_COUNT = 3;

    public ExecuteChangeCcUserPinService(UserRepository userProfileRepository, UserLoginRepository userLoginRepository, SecurityResource securityResource, MbDeviceInfoRepository mbDeviceInfoRepository, ChangePasswordRecordRepository changePasswordRecordRepository, NonAIMbDeviceInfoEntityRepository nonAIMbDeviceInfoEntityRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userLoginRepository = userLoginRepository;
        this.securityResource = securityResource;
        this.mbDeviceInfoRepository = mbDeviceInfoRepository;
        this.changePasswordRecordRepository = changePasswordRecordRepository;
        this.nonAIMbDeviceInfoEntityRepository = nonAIMbDeviceInfoEntityRepository;
    }

    public ExecuteChangeCcUserPinResponse executeChangeCcUserPin(ExecuteChangeCcUserPinRequest request) {

        LoginUser loginUser = verifyCcUser(request);

        processSuccessVerifyPin(loginUser, request);

        ExecuteChangeCcUserPinResponse response = new ExecuteChangeCcUserPinResponse();
        copyProperties(response, loginUser);
        if (loginUser.getError() != null) {
            response.setErrorCode(loginUser.getError().getErrorCode());
            response.setErrorDesc(loginUser.getError().getErrorDesc());
            response.setSystemId(loginUser.getError().getSystemId());
            response.setErrorStatus(loginUser.getError().getStatus());
        }
        return response;
    }

    public LoginUser verifyCcUser(ExecuteChangeCcUserPinRequest request) {

        SecretKeyProvider<?> provider = SecretKeyProviderFactory.getInstance().getProvider(AIBankConstants.CRYPTO_SECRET_KEY_PROVIDER_DEFAULT);
        aesCipherUtils = new AESCipherUtils(provider);

        LoginUser loginUser = new LoginUser();

        ActionException error = null;

        try {

            loginUser.seteUid(encrypt(request.getUid()));
            List<UserEntity> usersInDB;

            usersInDB = queryLoginUser(request.getUid(), request.getUuid());
            if (logger.isDebugEnabled()) {
                logger.debug("usersInDB.size() {}", CollectionUtils.size(usersInDB));
            }
            if (CollectionUtils.size(usersInDB) == 1) {
                // 只有一筆, 繼續
                loginUser.setUserEntity(usersInDB.get(0));
                loginUser.setUserLoginEntity(getLoginEntity(loginUser.getUserKey(), loginUser.getUserEntity()));
                loginUser.setCompanyEntity(null);
                loginUser = verifyCcUser(request, loginUser, usersInDB.get(0));
            }
            else {
                throw new ActionException(AIBankErrorCode.USER_NOT_FOUND);
            }
        }
        catch (ActionException ex) {
            logger.error(ex.getMessage(), ex);
            error = ex;
        }
        catch (DatabaseException ex) {
            logger.error(ex.getMessage(), ex);
            error = BizExceptionUtils.getActionException(ex);
        }
        finally {
            loginUser.setError(error);
        }
        return loginUser;
    }

    private void copyProperties(ExecuteChangeCcUserPinResponse response, LoginUser loginUser) {
        try {
            response.setChannel(loginUser.getChannel());
            response.setPreLoginDate(loginUser.getPreLoginDate());
            response.setNameCode(loginUser.getNameCode());

            response.setLoginStatus(loginUser.getLoginStatus());
            response.setMultiUser(loginUser.isMultiUser());
            response.setSuccess(loginUser.isSuccess());
            response.setBankUser(loginUser.isBankUser());
            response.setCompanyKey(loginUser.getCompanyKey());
            response.setUserKey(loginUser.getUserKey());

            copyUserEntity(loginUser.getUserEntity(), response.getUserVo());
            copyCompanyEntity(loginUser.getCompanyEntity(), response.getCompanyVo());
            copyLoginEntity(loginUser.getUserLoginEntity(), response.getUserLoginVo());
            copyCardUserEntity(loginUser.getCardUserProfileEntity(), response.getCardUserProfileVo());
            copyMbDeviceInfoEntity(loginUser.getMbDeviceInfoEntity(), loginUser.getMbDevicePushInfoEntity(), response.getMbDeviceInfoVo());
        }
        catch (Exception ex) { // 【Fortify：Poor Error Handling: Overly Broad Throws】需要攔截所有例外
            logger.info("資料轉移產生例外，不中止程序。");
            logger.warn(ex.getMessage(), ex);
        }
    }

    private LoginUser verifyCcUser(ExecuteChangeCcUserPinRequest request, LoginUser loginUser, UserEntity usersInDB) throws ActionException {

        UserLoginEntity loginEntity = loginUser.getUserLoginEntity();
        try {
            if (loginUser.getUserEntity() == null) {
                // 找不到
                throw new ActionException(AIBankErrorCode.USER_NOT_FOUND);
            }

            // USER_LOGIN
            if (loginEntity == null)
                loginEntity = getLoginEntity(loginUser.getCompanyKey(), loginUser.getUserEntity());

            // 查DB時只取左邊10碼
            String tmpSecret = encodewithSecret(E2EEHsmType.DB_3DES_UTF8_PWD_ENHANCE, request.getUid(), request.getUuid(), request.getSecrxt());

            String proSecret = tmpSecret.split("@")[0];
            String proSecretAdvanced = tmpSecret.split("@")[1];

            // 已超過最大次數
            if (loginEntity.getPwdErrorCount() >= MAX_PASSWORD_FAIL_COUNT) {
                logger.error("密碼錯誤已超過最大次數");
                throw new ActionException(AIBankErrorCode.PASSWORD_FAIL_3COUNT);
            }
            // 驗證密碼
            else {
                boolean isCompareFail = true;
                String pwdFlag = loginUser.getUserEntity().getPwdFlag();
                if (pwdFlag == null)
                    pwdFlag = "0";
                if (securityResource.isCCUserLoginWithNewPassword(pwdFlag, request.getUid())) {
                    if (StringUtils.equalsIgnoreCase(proSecretAdvanced, loginUser.getUserEntity().getPasswordAdvanced())) {
                        isCompareFail = false;
                    }
                }
                else {
                    if (StringUtils.equalsIgnoreCase(proSecret, loginUser.getUserEntity().getSecret())) {
                        isCompareFail = false;
                    }
                }

                if (isCompareFail) {
                    // update pass-word error time
                    loginEntity.setPwdErrorTime(new Date());
                    int pwdErrorCount = loginEntity.getPwdErrorCount() == null ? 1 : loginEntity.getPwdErrorCount() + 1;
                    loginEntity.setPwdErrorCount(pwdErrorCount);
                    userLoginRepository.save(loginEntity);
                    if (pwdErrorCount >= MAX_PASSWORD_FAIL_COUNT) {
                        throw new ActionException(AIBankErrorCode.PASSWORD_FAIL_3COUNT);
                    }
                    else {
                        // 密碼輸入錯誤，請重新輸入，若您忘記使用者代碼及密碼，可點選「忘記使用者代碼/密碼」進行重設。
                        logger.warn("密碼輸入錯誤 {} ", pwdErrorCount);
                        if (pwdErrorCount == 1) {
                            throw new ActionException(AIBankErrorCode.PASSWORD_FAIL_COUNT1);
                        }
                        else if (pwdErrorCount == 2) {
                            throw new ActionException(AIBankErrorCode.PASSWORD_FAIL_COUNT2);
                        }
                    }
                }
            }

            String newSecret = encodewithSecret(E2EEHsmType.DB_3DES_UTF8_PWD_ENHANCE, request.getUid(), request.getUuid(), request.getNewSecrxt());

            // 成功 更新欄位
            String encPwd = newSecret.split("@")[0];
            String hashEncPwd = newSecret.split("@")[1];
            usersInDB.setSecret(encPwd);
            if (securityResource.isPasswordAdvancedEnable(request.getUid())) {
                // [提高密碼安全性] 信卡會員多一組新密碼
                usersInDB.setPasswordAdvanced(hashEncPwd);
                // 使用者已變更密碼
                usersInDB.setPwdFlag(UserEntity.PWD_FLG_USER_CHANGED);
            }

            usersInDB.setTxDate(new Date());
            userProfileRepository.save(usersInDB);

            // MB_DEVICE_INFO
            List<MbDeviceInfoEntity> mbDeviceInfoEnties = mbDeviceInfoRepository.findByCompanyKeyAndUserKey(usersInDB.getCompanyKey(), usersInDB.getUserKey());
            if (CollectionUtils.isNotEmpty(mbDeviceInfoEnties)) {

                List<MbDeviceInfoEntity> enableMbDeviceInfos = mbDeviceInfoEnties.stream().filter(x -> Integer.valueOf(1).equals(x.getEnable())).map(device -> {
                    device.setChgPwdUseridFlag(1);
                    device.setChgPwdUseridDate(new Date());
                    return device;
                }).toList();

                if (CollectionUtils.isNotEmpty(enableMbDeviceInfos)) {
                    try {
                        mbDeviceInfoRepository.saveAll(enableMbDeviceInfos);
                    }
                    catch (Exception ex) { // 【Fortify：Poor Error Handling: Overly Broad Throws】需要攔截所有例外
                        logger.error("更新 MB_DEVICE_INFO CHG_PWD_USERID_FLAG 失敗，不影響程序。", ex);
                    }
                }
            }

            // #2733: [CR#150] 快速登入的變更密碼檢核
            List<NonAIMbDeviceInfoEntity> nonAIMbDeviceInfoEntities = nonAIMbDeviceInfoEntityRepository.findByCompanyKeyAndUserKey(usersInDB.getCompanyKey(), usersInDB.getUserKey());
            if (nonAIMbDeviceInfoEntities != null && nonAIMbDeviceInfoEntities.size() > 0) {
                for (NonAIMbDeviceInfoEntity mbDeviceInfoEntity : nonAIMbDeviceInfoEntities) {
                    mbDeviceInfoEntity.setChgPwdUseridFlag("Y");
                    Date currentTime = new Date();
                    mbDeviceInfoEntity.setChgPwdUseridDate(currentTime);
                    mbDeviceInfoEntity.setUpdateTime(currentTime);
                    try {
                        nonAIMbDeviceInfoEntityRepository.save(mbDeviceInfoEntity);
                    }
                    catch (RuntimeException ex) {
                        logger.error("更新 MB_DEVICE_INFO CHG_PWD_USERID_FLAG 失敗", ex);
                    }
                }
            }
        }
        catch (ActionException ex) {
            logger.error(ex.getMessage(), ex);
            throw BizExceptionUtils.getBizActionException(ex);
        }
        return loginUser;
    }

    private String encodewithSecret(E2EEHsmType e2eeHsmType, String uid, String uuid, String secret) {
        EncodeWithSecretRequest rq = new EncodeWithSecretRequest();
        rq.setE2eeHsmType(e2eeHsmType.name());
        rq.setUid(uid);
        rq.setUuid(uuid);
        rq.setEncodedSecret(secret);
        rq.setIsPwdWithTime(false);

        EncodeWithSecretResponse rs = securityResource.encodewithSecret(rq);
        return rs.getHostSecret();
    }

    /**
     * 取得使用者
     *
     * @param uid
     *            the uid
     * @param uuid
     *            the uuid
     * @return list
     * @throws DatabaseException
     *             the database exception
     * @throws ActionException
     *             the action exception
     */
    private List<UserEntity> queryLoginUser(String uid, String uuid) throws DatabaseException, ActionException {

        // 查DB時只取左邊10碼
        uid = StringUtils.trimToEmptyEx(StringUtils.left(uid, 10));
        List<UserEntity> usersInDB = null;
        // 查詢信用卡會員 // 【FORTIFY：Access Control: Database】uid、uuid 就是身分識別屬性欄位
        usersInDB = userProfileRepository.findCardUser(encrypt(ValidateParamUtils.validParam(uid)), ValidateParamUtils.validParam(uuid));
        return usersInDB;
    }

    private String encrypt(String uid) throws ActionException {
        if (aesCipherUtils == null)
            return uid;
        try {
            return aesCipherUtils.encrypt(uid);
        }
        catch (CryptException e) {
            logger.error(e.getMessage(), e);
            throw BizExceptionUtils.getActionException(e);
        }
    }

    private UserLoginEntity getLoginEntity(int userKey, UserEntity userEntity) {
        UserLoginEntity userLoginEntity = userLoginRepository.findByUserKey(userKey);

        if (userLoginEntity == null) {
            userLoginEntity = new UserLoginEntity();
            userLoginEntity.setCompanyKey(userEntity.getCompanyKey());
            userLoginEntity.setUserKey(userEntity.getUserKey());
            userLoginEntity.setStatus(-1);
            userLoginEntity.setPwdErrorCount(0);
            userLoginEntity.setPwdErrorTime(null);
            userLoginEntity.setPwdChangeTime(null);
            userLoginEntity.setPwdForceChange(0);
            userLoginEntity.setSessionId("");
            userLoginEntity.setClientIp("");
            userLoginEntity.setLoginTime(null);
            userLoginEntity.setLastAccessTime(null);
            userLoginEntity.setLastLoginTime(null);
            userLoginRepository.save(userLoginEntity);
        }

        return userLoginEntity;

    }

    private void copyUserEntity(UserEntity source, UserVo target) {
        if (source == null || target == null)
            return;
        target.setUserKey(source.getUserKey());
        target.setCompanyKey(source.getCompanyKey());
        target.setUserUuid(source.getUserUuid());
        target.setPaxword(source.getSecret());
        target.setNickName(source.getNickName());
        target.setAvatar(source.getAvatar());
        target.setUserCname(source.getUserCname());
        target.setUserEname(source.getUserEname());
        target.setStatus(source.getStatus());
        target.setUserType(source.getUserType());
        target.setLocale(source.getLocale());
        target.setOtpType(source.getOtpType());
        target.setOtpCert(source.getOtpCert());
        target.setTel1(source.getTel1());
        target.setTel2(source.getTel2());
        target.setTel3(source.getTel3());
        target.setFax1(source.getFax1());
        target.setFax2(source.getFax2());
        target.setNameCode(source.getNameCode());
        target.setMobile(source.getMobile());
        target.setEmails(source.getEmails());
        target.setTwEditQuota(source.getTwEditQuota());
        target.setTwVerifyQuota(source.getTwVerifyQuota());
        target.setTwPassQuota(source.getTwPassQuota());
        target.setTwDailyPassQuota(source.getTwDailyPassQuota());
        target.setFxEditQuota(source.getFxEditQuota());
        target.setFxVerifyQuota(source.getFxVerifyQuota());
        target.setFxPassQuota(source.getFxPassQuota());
        target.setFxDailyPassQuota(source.getFxDailyPassQuota());
        target.setTwPassDate(source.getTwPassDate());
        target.setTwTotalPassAmt(source.getTwTotalPassAmt());
        target.setFxPassDate(source.getFxPassDate());
        target.setFxTotalPassAmt(source.getFxTotalPassAmt());
        target.setHiddenFxAccount(source.getHiddenFxAccount());
        target.setTxDate(source.getTxDate());
        target.setHiddenLoanAccount(source.getHiddenLoanAccount());
    }

    private void copyCompanyEntity(CompanyEntity source, CompanyVo target) {
        if (source == null || target == null)
            return;
        target.setCompanyKey(source.getCompanyKey());
        target.setCompanyUid(source.getCompanyUid());
        target.setUidDup(source.getUidDup());
        target.setStatus(source.getStatus());
        target.setCompanyKind(source.getCompanyKind());
        target.setCompanyBUType(source.getCompanyBUType());
        target.setCompanyName(source.getCompanyName());
        target.setCompanyEname(source.getCompanyEname());
        target.setUaaLevel(source.getUaaLevel());
        target.setDefaultFlowSchemaKey(source.getDefaultFlowSchemaKey());
        target.setSchemaSuitOperatorKey(source.getSchemaSuitOperatorKey());
        target.setSchemaSuitCreateTime(source.getSchemaSuitCreateTime());
        target.setEstablishDate(source.getEstablishDate());
        target.setDefaultBranchId(source.getDefaultBranchId());
        target.setTel(source.getTel());
        target.setFax(source.getFax());
        target.setMobile(source.getMobile());
        target.setEmails(source.getEmails());
        target.setRetryFlag(source.getRetryFlag());
        target.setLoginFlag(source.getLoginFlag());
        target.setSalaryFlag(source.getSalaryFlag());
        target.setOnlinePayeeFlag(source.getOnlinePayeeFlag());
        target.setRoot1TxFlag(source.getRoot1TxFlag());
        target.setRoot2TxFlag(source.getRoot2TxFlag());
        target.setPayerAccountFlag(source.getPayerAccountFlag());
        target.setPayeeAccountFlag(source.getPayeeAccountFlag());
        target.setFaxFlag(source.getFaxFlag());
        target.setTwAtmFlag(source.getTwAtmFlag());
        target.setTwRemitFlag(source.getTwRemitFlag());
        target.setTwFxmlFlag(source.getTwFxmlFlag());
        target.setTwPayeeFlag(source.getTwPayeeFlag());
        target.setTwAmtQuota(source.getTwAmtQuota());
        target.setTwInterRemitQuota(source.getTwInterRemitQuota());
        target.setRegisterTime(source.getRegisterTime());
        target.setCancelTime(source.getCancelTime());
        target.setLastBranchId(source.getLastBranchId());
        target.setLastEditorKey(source.getLastEditorKey());
        target.setLastManagerKey(source.getLastManagerKey());
        target.setLastUpdateTime(source.getLastUpdateTime());
        target.setQueryOnlyFlag(source.getQueryOnlyFlag());
        target.setKycRevalidated(source.getKycRevalidated());
        target.setKycRevalidatedDate(source.getKycRevalidatedDate());
        target.setMassCheck(source.getMassCheck());
        target.setCustomeAuditId(source.getCustomeAuditId());

    }

    private void copyLoginEntity(UserLoginEntity source, UserLoginVo target) {
        if (source == null || target == null)
            return;
        target.setUserKey(source.getUserKey());
        target.setCompanyKey(source.getCompanyKey());
        target.setStatus(source.getStatus());
        target.setPwdErrorCount(source.getPwdErrorCount());
        target.setPwdErrorTime(source.getPwdErrorTime());
        target.setPwdChangeTime(source.getPwdChangeTime());
        target.setPwdForceChange(source.getPwdForceChange());
        target.setSessionId(source.getSessionId());
        target.setServerId(source.getServerId());
        target.setClientIp(source.getClientIp());
        target.setLoginTime(source.getLoginTime());
        target.setLastAccessTime(source.getLastAccessTime());
        target.setLastLoginTime(source.getLastLoginTime());
        target.setWarningMessage(source.getWarningMessage());
        target.setSignonToken(source.getSignonToken());
        target.setMainCoachMark(source.getMainCoachMark());
        target.setPmCoachMark(source.getPmCoachMark());
        target.setAcctMgrCoachMark(source.getAcctMgrCoachMark());
        target.setLoginResult(source.getLoginResult());
        target.setLastLoginResult(source.getLastLoginResult());
        target.setLastClientIp(source.getLastClientIp());
        target.setNationName(source.getNationName());
        target.setLastNationName(source.getLastNationName());
        target.setMfdShowtype(source.getMfdShowtype());
        target.setChannelId(source.getChannelId());
        target.setLastChannelId(source.getLastChannelId());
        target.setPopupTime(source.getPopupTime());

    }

    private void copyCardUserEntity(CardUserProfileEntity source, CardUserProfileVo target) {
        if (source == null || target == null)
            return;
        target.setCardKey(source.getCardKey());
        target.setCompanyKey(source.getCompanyKey());
        target.setUserKey(source.getUserKey());
        target.setCardNo(source.getCardNo());
        target.setCardExpired(source.getCardExpired());
        target.setCardCvv2(source.getCardCvv2());
        target.setContractversion(source.getContractversion());
        target.setAccessLogKey(source.getAccessLogKey());
        target.setCreateTime(source.getCreateTime());
        target.setUpdateTime(source.getUpdateTime());
        target.setCardMasterFlag(source.getCardMasterFlag());
    }

    private void copyMbDeviceInfoEntity(MbDeviceInfoEntity source, MbDevicePushInfoEntity pushInfo, MbDeviceInfoVo target) {
        if (source == null || target == null)
            return;
        target.setDeviceInfoKey(source.getDeviceInfoKey());
        target.setDeviceUuId(source.getDeviceUuId());
        target.setCompanyKey(source.getCompanyKey());
        target.setUserKey(source.getUserKey());
        target.setModel(source.getModel());
        target.setDevicePlatform(source.getDevicePlatform());
        target.setDevicePlatformVersion(source.getDevicePlatformVersion());
        target.setDeviceAlias(source.getDeviceAlias());
        target.setLoginFlag(source.getLoginFlag());
        target.setLoginAuthDate(source.getLoginAuthDate());
        target.setNotifyFlag(pushInfo.getNotifyFlag());
        target.setNotifyAuthDate(pushInfo.getNotifyAuthDate());
        target.setNotifyPass(pushInfo.getNotifyPass());
        target.setClientIp(source.getClientIp());
        target.setMsgFlag(source.getMsgFlag());
        target.setMsgPassword(source.getMsgPassword());
        target.setEnable(source.getEnable());
        target.setCreateTime(source.getCreateTime());
        target.setUpdateTime(source.getUpdateTime());
        target.setLoginType(source.getLoginType());
        target.setErrorFlag(source.getErrorFlag());
        target.setLoginPasswdType(source.getLoginPasswdType());
        target.setQsearchFlag(source.getQsearchFlag());
        target.setChgPwdUseridFlag(source.getChgPwdUseridFlag());
        target.setChgPwdUseridDate(source.getChgPwdUseridDate());
        target.setMotpFlag(source.getMotpFlag());
        target.setMotpFlagDate(source.getMotpFlagDate());
        target.setPushToken(pushInfo.getPushToken());
        target.setDirectEzLoginFlag(source.getDirectEzLoginFlag());
    }

    /**
     * 成功後處理驗證密碼
     * 
     * @param loginUser
     * @param request
     * @throws DatabaseException
     * @throws ActionException
     */
    private void processSuccessVerifyPin(LoginUser loginUser, ExecuteChangeCcUserPinRequest request) {
        // 更新 user login data
        updateUserData(loginUser, request);
        // 更新密碼變更記錄，
        updateChangeRecord(loginUser, request);

    }

    private void updateChangeRecord(LoginUser loginUser, ExecuteChangeCcUserPinRequest request) {
        try {
            ChangePasswordRecordEntity changePasswordRecordEntity = new ChangePasswordRecordEntity();
            Date now = new Date();
            changePasswordRecordEntity.setTxStatus(loginUser.isSuccess() ? TxStatusType.SUCCESS.getCode() : TxStatusType.FAIL.getCode()); // 交易成功(變更成功)
            changePasswordRecordEntity.setUserKey(loginUser.getUserKey());
            changePasswordRecordEntity.setCompanyKey(loginUser.getCompanyKey());

            ActionException err = loginUser.getError();
            if (err != null) {
                changePasswordRecordEntity.setTxErrorDesc(err.getErrorDesc());
                changePasswordRecordEntity.setTxErrorCode(err.getErrorCode());
                changePasswordRecordEntity.setTxErrorSystemId(err.getSystemId());
            }
            changePasswordRecordEntity.setUpdateTime(now);
            changePasswordRecordEntity.setNameCode(request.getNameCode());
            changePasswordRecordEntity.setUserId(request.getUuid());
            changePasswordRecordEntity.setChangeItem("0");
            changePasswordRecordEntity.setCreateTime(now);
            changePasswordRecordEntity.setTxDate(now);
            changePasswordRecordEntity.setClientIp(MDC.get(MDCKey.clientip.name()));
            changePasswordRecordEntity.setTxSource(TxSourceType.AI_BANK.getCode());
            changePasswordRecordRepository.save(changePasswordRecordEntity);
        }
        catch (Exception ex) { // 【Fortify：Poor Error Handling: Overly Broad Throws】需要攔截所有例外
            logger.info("更新失敗，不中止程序。");
            logger.error(ex.getMessage(), ex);
        }

    }

    private void updateUserData(LoginUser loginUser, ExecuteChangeCcUserPinRequest request) throws JpaSystemException {
        UserLoginEntity loginEntity = loginUser.getUserLoginEntity();
        if (loginEntity == null) {
            return;
        }

        if (loginUser.isSuccess()) {
            // 成功
            loginEntity.setPwdChangeTime(new Date());
            loginEntity.setPwdErrorCount(0);
            userLoginRepository.save(loginEntity);
        }

    }

}
