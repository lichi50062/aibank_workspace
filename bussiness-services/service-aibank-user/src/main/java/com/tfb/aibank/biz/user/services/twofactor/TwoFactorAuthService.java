package com.tfb.aibank.biz.user.services.twofactor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.biz.user.repository.MbDeviceInfoRepository;
import com.tfb.aibank.biz.user.repository.TrustDeviceRepository;
import com.tfb.aibank.biz.user.repository.TwoFactorAuthRepository;
import com.tfb.aibank.biz.user.repository.aib.MbDevicePushInfoRepository;
import com.tfb.aibank.biz.user.repository.aib.entities.MbDevicePushInfoEntity;
import com.tfb.aibank.biz.user.repository.entities.MbDeviceInfoEntity;
import com.tfb.aibank.biz.user.repository.entities.TrustDeviceEntity;
import com.tfb.aibank.biz.user.repository.entities.TwoFactorAuthEntity;
import com.tfb.aibank.biz.user.services.login.LoginUser;
import com.tfb.aibank.biz.user.services.login.model.ExecuteUserLoginRequest;
import com.tfb.aibank.biz.user.services.twofactor.model.TwoFactorAuthUserRequest;
import com.tfb.aibank.biz.user.services.twofactor.model.TwoFactorAuthUserResponse;

// @formatter:off
/**
 * @(#)TwoFactorAuthService.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/24, bensonlin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class TwoFactorAuthService {

    private static final IBLog logger = IBLog.getLog(TwoFactorAuthService.class);

    private TrustDeviceRepository trustDeviceRepository;
    private MbDeviceInfoRepository mbDeviceInfoRepository;
    private TwoFactorAuthRepository twoFactorAuthRepository;
    private MbDevicePushInfoRepository mbDevicePushInfoRepository;

    public TwoFactorAuthService(TrustDeviceRepository trustDeviceRepository, MbDeviceInfoRepository mbDeviceInfoRepository, MbDevicePushInfoRepository mbDevicePushInfoRepository, //
            TwoFactorAuthRepository twoFactorAuthRepository) {
        this.trustDeviceRepository = trustDeviceRepository;
        this.mbDeviceInfoRepository = mbDeviceInfoRepository;
        this.twoFactorAuthRepository = twoFactorAuthRepository;
        this.mbDevicePushInfoRepository = mbDevicePushInfoRepository;
    }

    /**
     * 需執行雙重驗證: loginUser.isTwoFactorAuth
     * 
     * @param loginUser
     * @return
     */
    public void populateLoginUserForTwoFactorAuthentication(LoginUser loginUser, ExecuteUserLoginRequest request) {

        boolean isDeviceBindedByOtherAndTurnOnTwoFactorAuth = isDeviceBindedByOtherAndTurnOnTwoFactorAuth(loginUser);

        if (isDeviceBindedByOtherAndTurnOnTwoFactorAuth) {
            // 目前使用的裝置是否為信任裝置
            List<TrustDeviceEntity> trustDeviceList = trustDeviceRepository.findByCompanyKeyAndUserKeyAndDeviceId(loginUser.getCompanyKey(), loginUser.getUserKey(), request.getDeviceId());
            boolean isTrustDevice = CollectionUtils.isNotEmpty(trustDeviceList) ? true : false;

            logger.debug("two_factor_auth TrustDevice: {}", isTrustDevice);
            // 有開啟及本裝置不為已信任才需使用雙重驗證
            loginUser.setIsTwoFactorAuth(isDeviceBindedByOtherAndTurnOnTwoFactorAuth && !isTrustDevice);
            // 為信任裝置寫一筆記錄到TWOFACTORAUTH
            if (isTrustDevice) {
                logger.debug("two_factor_auth addTrustDevice: {}, {}", loginUser.getCompanyKey(), loginUser.getUserKey());
                addTrustDeviceToTwoFactorAuth(loginUser, request);
            }
        }
        logger.info("two_factor_auth_enabled: {}, {}", isDeviceBindedByOtherAndTurnOnTwoFactorAuth, loginUser.isTwoFactorAuth());
    }

    /**
     * 信任裝置加入雙重驗證記錄
     * 
     * @param loginUser
     * @param request
     */
    private void addTrustDeviceToTwoFactorAuth(LoginUser loginUser, ExecuteUserLoginRequest request) {

        try {
            Date now = new Date();

            TwoFactorAuthEntity authEntity = new TwoFactorAuthEntity();
            authEntity.setAuthType("TRUST");
            authEntity.setCompanyKey(loginUser.getCompanyKey());
            authEntity.setUserKey(loginUser.getUserKey());
            authEntity.setDeviceId(request.getDeviceId());
            authEntity.setIp(request.getClientIP());
            authEntity.setLocation(request.getCountryName());
            authEntity.setStatus("SUCCESS");
            authEntity.setCreateTime(now);
            authEntity.setUpdateTime(now);

            twoFactorAuthRepository.save(authEntity);
        }
        catch (Throwable e) {
            logger.error(String.format("addTrustDeviceToTwoFactorAuth failed: %d, %d ", loginUser.getCompanyKey(), loginUser.getUserKey()), e);
        }
    }

    /**
     * 計算信任裝置筆數
     * 
     * @param companyKey
     * @param userKey
     * @param deviceUuid
     * @return
     */
    public int countTrustDeviceByUser(Integer companyKey, Integer userKey) {
        logger.debug("companyKey:{}, userKey:{}", companyKey, userKey);
        int totalCount = trustDeviceRepository.countByCompanyKeyAndUserKey(companyKey, userKey);
        return totalCount;
    }

    /**
     * 處理雙重認證
     * 
     * @param request
     */
    @Transactional
    public TwoFactorAuthUserResponse handleTwoFactorAuth(TwoFactorAuthUserRequest request) {

        TwoFactorAuthUserResponse response = new TwoFactorAuthUserResponse(request);
        // 新增
        if (request.getAction() == 1) {
            Date now = new Date();

            TwoFactorAuthEntity authEntity = new TwoFactorAuthEntity();
            authEntity.setAuthType(request.getAuthType());
            authEntity.setCompanyKey(request.getCompanyKey());
            authEntity.setUserKey(request.getUserKey());
            authEntity.setDeviceId(request.getDeviceId());
            authEntity.setIp(request.getIp());
            authEntity.setLocation(request.getLocation());
            authEntity.setStatus(request.getStatus());

            authEntity.setCreateTime(now);
            authEntity.setUpdateTime(now);

            authEntity = this.twoFactorAuthRepository.save(authEntity);

            Long seq = authEntity.getSeq();
            response.setSeq(seq);

        }
        else if (request.getAction() == 2) {
            // 查詢結果
            if (request.getSeq() == null) {
                // TODO
            }
            Optional<TwoFactorAuthEntity> twoFactorAuthOptional = this.twoFactorAuthRepository.findBySeqAndCompanyKeyAndUserKey(request.getSeq(), request.getCompanyKey(), request.getUserKey());

            response.setStatus(twoFactorAuthOptional.isPresent() ? twoFactorAuthOptional.get().getStatus() : "");
        }
        else if (request.getAction() == 3) {
            // 更新狀態
            Long seq = request.getSeq();

            if (seq == null) {
                logger.error("update twofactorauth seq is null:{} ", seq);
                return response;
            }

            Optional<TwoFactorAuthEntity> twoFactorAuthOptional = this.twoFactorAuthRepository.findBySeqAndCompanyKeyAndUserKey(seq, request.getCompanyKey(), request.getUserKey());

            if (twoFactorAuthOptional.isPresent()) {
                TwoFactorAuthEntity authEntity = twoFactorAuthOptional.get();
                authEntity.setStatus(request.getStatus());
                authEntity.setUpdateTime(new Date());
                authEntity.setIp(request.getIp());
                authEntity.setDeviceId(request.getDeviceId());
                authEntity.setAuthType(request.getAuthType());

                twoFactorAuthRepository.save(authEntity);
            }
            else {
                logger.error("update twofactorauth not found, seq:{} ", seq);
            }

        }

        return response;

    }

    /**
     * 同個帳號下已裝置有開啟需要雙重驗證
     * 
     * @param loginUser
     * @return
     */
    private boolean isDeviceBindedByOtherAndTurnOnTwoFactorAuth(LoginUser loginUser) {

        try {
            // 查出使用者下全部裝置
            List<MbDeviceInfoEntity> mbDeviceInfoEntities = mbDeviceInfoRepository.findByCompanyKeyAndUserKey(loginUser.getCompanyKey(), loginUser.getUserKey());
            if (!mbDeviceInfoEntities.isEmpty()) {
                // DeviceInfo
                MbDeviceInfoEntity allDeviceInfo = mbDeviceInfoEntities.get(0);
                // 綁定的裝置是目前使用的裝置, 不需執行雙重驗證判斷
                if (loginUser.getMbDeviceInfoEntity() != null && StringUtils.equals(allDeviceInfo.getDeviceUuId(), loginUser.getMbDeviceInfoEntity().getDeviceUuId())) {
                    return false;
                }
                loginUser.setMbDeviceInfoEntityBindedByOtherDevice(allDeviceInfo);
                // Push Info
                List<MbDevicePushInfoEntity> mbDevicePushInfoEntities = mbDevicePushInfoRepository.findByCompanyKeyAndUserKey(loginUser.getCompanyKey(), loginUser.getUserKey());
                if (mbDevicePushInfoEntities != null && !mbDevicePushInfoEntities.isEmpty()) {
                    loginUser.setMbDevicePushInfoEntityBindedByOtherDevice(mbDevicePushInfoEntities.get(0));
                }
                return allDeviceInfo.getTwostepFlag() != null && 1 == allDeviceInfo.getTwostepFlag().intValue();
            }
        }
        catch (Exception ex) {
            logger.error("findByCompanyKeyAndUserKey failed " + loginUser.getCompanyKey() + ", " + loginUser.getUserKey(), ex);
        }
        return false;
    }

}
