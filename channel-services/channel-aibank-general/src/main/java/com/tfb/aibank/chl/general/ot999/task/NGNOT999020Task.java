package com.tfb.aibank.chl.general.ot999.task;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.security.sso.SsoLoginAuthStep;
import com.tfb.aibank.chl.component.security.sso.type.SsoLoginAuthType;
import com.tfb.aibank.chl.general.ot999.model.NGNOT999020Rq;
import com.tfb.aibank.chl.general.ot999.model.NGNOT999020Rs;
import com.tfb.aibank.chl.general.ot999.task.service.NGNOT999Cache;
import com.tfb.aibank.chl.general.ot999.task.service.NGNOT999Service;
import com.tfb.aibank.chl.general.resource.SecurityResource;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.resource.dto.DoFastValidateUserRequest;
import com.tfb.aibank.chl.general.resource.dto.DoFastValidateUserResponse;
import com.tfb.aibank.chl.general.resource.dto.LoginResponse;
import com.tfb.aibank.chl.general.resource.dto.QueryVerifyResultRequest;
import com.tfb.aibank.chl.general.resource.dto.QueryVerifyResultResponse;
import com.tfb.aibank.chl.general.service.LoginService;
import com.tfb.aibank.chl.type.PwType;
import com.tfb.aibank.common.type.SsoStatusType;

//@formatter:off
/**
* @(#)NGNOT999020Task.java 
* 
* <p>Description:快速身份認證</p>
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

@Component
@Scope("prototype")
public class NGNOT999020Task extends AbstractAIBankBaseTask<NGNOT999020Rq, NGNOT999020Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT999020Task.class);

    @Autowired
    private SecurityResource securityResource;

    @Autowired
    private UserResource userResource;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Autowired
    private NGNOT999Service service;

    @Autowired
    private LoginService loginService;

    @Override
    public void validate(NGNOT999020Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNOT999020Rq rqData, NGNOT999020Rs rsData) throws ActionException {
        NGNOT999Cache cache = getCache(NGNOT999Service.FAST_VALIDATE_CACHE_KEY, NGNOT999Cache.class);

        if (cache == null) {
            service.generateCallBackUrl(rsData, SsoStatusType.UNKNOWN.getStatusCode(), null, "", "O", "0", "");
            return;
        }

        /**
         * pwType (fromCache) 決定快登的種類 usedSsoAuthType (只在一般登入才會帶上來)
         */
        SsoLoginAuthStep ssoAuthStep = cache.getSsoAuthStep();
        SsoLoginAuthType currentSsoAuthType = SsoLoginAuthType.find(rqData.getCurrentAuthType());

        if (currentSsoAuthType != null && currentSsoAuthType.isThreeLayer()) {
            prepareRqDataForThreeLayer(rqData, cache, ssoAuthStep);
        }
        else { // 不為3層式都視為 快登, 由 Cache 取出種類
            logger.debug("code and challenge: {}, {}, {}", cache.getRetriveDeviceStatusResponse().getPwType(), rqData.getCode(), rqData.getChallenge());
            if (PwType.isPattern(cache.getRetriveDeviceStatusResponse().getPwType())) {
                currentSsoAuthType = SsoLoginAuthType.PATTERN;
                if (StringUtils.isBlank(rqData.getChallenge())) {
                    service.generateCallBackUrl(rsData, SsoStatusType.AUTH_TYPE_NOT_MATH.getStatusCode(), null, cache.getRqData().getPlatformParams(), cache.getGetFastValidateUserResponse().getOpenType(), cache.getGetFastValidateUserResponse().getModuleType(), cache.getGetFastValidateUserResponse().getModuleParam());
                    return;
                }
                else {
                    String pinBlock = service.encodewithSecret(cache.getRetriveDeviceStatusResponse().getCustId(), cache.getRetriveDeviceStatusResponse().getUserId(), rqData.getChallenge());
                    rqData.setChallenge(pinBlock);
                }
            }
            else {
                currentSsoAuthType = SsoLoginAuthType.BIOMETRIC;
                /** 查詢 FIDO 認證結果 */
                LoginResponse loginResponse = getCache(LoginService.FIDO_INFO_KEY, LoginResponse.class);
                // 生物辨識登入，驗證
                QueryVerifyResultRequest request = new QueryVerifyResultRequest();

                request.setCustId(cache.getRetriveDeviceStatusResponse().getCustId());
                request.setUserId(cache.getRetriveDeviceStatusResponse().getUserId());
                request.setCompanyKind(cache.getRetriveDeviceStatusResponse().getCompanyKind());
                request.setUidDup(cache.getRetriveDeviceStatusResponse().getUidDup());
                request.setToken(loginResponse.getToken()); // 空的?
                request.setVerifyNo(loginResponse.getVerifyNo()); // 空的?
                request.setRegisterDevice(false);
                request.setDeviceId(getRequest().getDeviceIxd());
                request.setModel(getRequest().getModel());
                request.setDevicePlatform(this.getPlatformDisplay());
                request.setDevicePlatformVersion(getRequest().getVersion());
                request.setDeviceAlias("");
                request.setIp(getRequest().getClientIp());
                request.setLoginType(cache.getRetriveDeviceStatusResponse().getLoginType());
                request.setLoginPasswordType(0);

                QueryVerifyResultResponse response = securityResource.queryVerifyResult(request);

                if (!"0".equals(response.getStatus())) {
                    Optional<SsoLoginAuthType> nextAuthTypeOptional = ssoAuthStep.getNextAuthType(currentSsoAuthType, false);
                    if (nextAuthTypeOptional.isPresent()) {
                        // 有下一個
                        SsoLoginAuthType nextAuthType = nextAuthTypeOptional.get();
                        rsData.setNextFallBackAuthRequired(true);
                        rsData.setNextAuthType(nextAuthType.getCode());
                        setCache(NGNOT999Service.FAST_VALIDATE_CACHE_KEY, cache);

                        return;
                    }
                    else {
                        // 沒有下一個, 直接失敗
                        service.generateCallBackUrl(rsData, SsoStatusType.AUTH_TYPE_NOT_MATH.getStatusCode(), null, cache.getRqData().getPlatformParams(), cache.getGetFastValidateUserResponse().getOpenType(), cache.getGetFastValidateUserResponse().getModuleType(), cache.getGetFastValidateUserResponse().getModuleParam());
                        setCache(NGNOT999Service.FAST_VALIDATE_CACHE_KEY, cache);
                        return;
                    }
                }
            }
        }

        /** 身份驗證 */
        DoFastValidateUserRequest doFastValidateUserRequest = new DoFastValidateUserRequest();

        if (currentSsoAuthType.isQuickLogin()) {
            logger.info("currentSsoAuthType: quickLogin");

            doFastValidateUserRequest.setCustId(cache.getRetriveDeviceStatusResponse().getCustId());
            doFastValidateUserRequest.setUserId(cache.getRetriveDeviceStatusResponse().getUserId());
            doFastValidateUserRequest.setCompanyKind(cache.getRetriveDeviceStatusResponse().getCompanyKind());
            doFastValidateUserRequest.setUidDup(cache.getRetriveDeviceStatusResponse().getUidDup());
            doFastValidateUserRequest.setChallenge(rqData.getChallenge());
            doFastValidateUserRequest.setIsSignatureLogin("Y");
            doFastValidateUserRequest.setLoginType(0 == cache.getRetriveDeviceStatusResponse().getLoginType() ? "m1" : "m2");
            doFastValidateUserRequest.setPwType(Integer.toString(cache.getRetriveDeviceStatusResponse().getPwType()));
            doFastValidateUserRequest.setTokenType(cache.getGetFastValidateUserResponse().getFunc());
        }
        if (currentSsoAuthType.isThreeLayer()) {
            logger.info("currentSsoAuthType: three layer");

            doFastValidateUserRequest.setCustId(rqData.getUid());
            doFastValidateUserRequest.setUserId(rqData.getUuid());
            doFastValidateUserRequest.setUidDup("0"); // 3層式先填0, 在由validate 驗證後回填到cache
            doFastValidateUserRequest.setSecret(rqData.getSecret());
            doFastValidateUserRequest.setIsSignatureLogin("N");
            doFastValidateUserRequest.setLoginType(rqData.getLoginType());
            doFastValidateUserRequest.setCompanyKind(StringUtils.equals("m1", rqData.getLoginType()) ? 2 : 3); // 依用戶種類填入, 在由validate 驗證後回填到cache
            doFastValidateUserRequest.setPwType("0");
            doFastValidateUserRequest.setTokenType(cache.getGetFastValidateUserResponse().getFunc());
            // OTP 使用
        }

        // 認證
        doFastValidateUserRequest.setChannelKey(cache.getGetFastValidateUserResponse().getChannelKey());
        doFastValidateUserRequest.setDeviceId(getRequest().getDeviceIxd());

        try {

            logger.debug("doFastValidateUser: {}", doFastValidateUserRequest);

            DoFastValidateUserResponse doFastValidateUserResponse = userResource.doFastValidateUser(doFastValidateUserRequest);

            if (SsoStatusType.isSuccessful(doFastValidateUserResponse.getStatusCode())) {
                logger.debug("doFastValidateUser isSuccessful: {}", doFastValidateUserResponse);
                if (StringUtils.equals("m1", rqData.getLoginType()) || StringUtils.equals("m2", rqData.getLoginType())) {
                    // 3層式一般用戶需回填 UserId, CustId, UserIdDup, CompanyKind 等值
                    cache.setUserId(rqData.getUuid());
                    cache.setCustId(rqData.getUid());
                    cache.setUserIdDup(doFastValidateUserResponse.getIddup());
                    cache.setCompanyKind(doFastValidateUserResponse.getCompanyKind() != null ? doFastValidateUserResponse.getCompanyKind() : StringUtils.equals("m1", rqData.getLoginType()) ? 2 : 3);
                    setCache(NGNOT999Service.FAST_VALIDATE_CACHE_KEY, cache);
                }

                // 成功只有在沒有下一步才帶 Token 回去,
                Optional<SsoLoginAuthType> nextAuthTypeOptional = ssoAuthStep.getNextAuthType(currentSsoAuthType, true);

                if (nextAuthTypeOptional.isPresent()) {
                    logger.info("hasNextAuth: {}", nextAuthTypeOptional.get());
                    SsoLoginAuthType nextAuthType = nextAuthTypeOptional.get();
                    rsData.setNextAdditionalAuthRequired(true);
                    rsData.setNextAuthType(nextAuthType.getCode());
                    // 需要執行 OTP
                    if (ssoAuthStep.hasSmsOtpInAdditionalType()) {
                        logger.info("hasSmsOtpInAdditionalType:");

                        cache.setMobileNo(doFastValidateUserResponse.getMobileNo());
                        cache.setChannelName(doFastValidateUserResponse.getChannelName());
                        cache.setToken(doFastValidateUserResponse.getToken());
                        // 先塞OTP 取消時的錯誤訊息
                        service.generateCallBackUrl(rsData, SsoStatusType.FAST_LOGIN_FAIL.getStatusCode(), "", cache.getRqData().getPlatformParams(), cache.getGetFastValidateUserResponse().getOpenType(), cache.getGetFastValidateUserResponse().getModuleType(), cache.getGetFastValidateUserResponse().getModuleParam());
                        setCache(NGNOT999Service.FAST_VALIDATE_CACHE_KEY, cache);
                        return;
                    }
                    else {
                        logger.info("hasNotSmsOtpInAdditionalType:");
                        cache.setToken(doFastValidateUserResponse.getToken());
                        service.generateCallBackUrl(rsData, doFastValidateUserResponse.getStatusCode(), "", cache.getRqData().getPlatformParams(), cache.getGetFastValidateUserResponse().getOpenType(), cache.getGetFastValidateUserResponse().getModuleType(), cache.getGetFastValidateUserResponse().getModuleParam());
                        setCache(NGNOT999Service.FAST_VALIDATE_CACHE_KEY, cache);
                        return;
                    }
                }
                else {
                    logger.info("hasNotNextAuth: ");
                    service.generateCallBackUrl(rsData, doFastValidateUserResponse.getStatusCode(), doFastValidateUserResponse.getToken(), cache.getRqData().getPlatformParams(), cache.getGetFastValidateUserResponse().getOpenType(), cache.getGetFastValidateUserResponse().getModuleType(), cache.getGetFastValidateUserResponse().getModuleParam());
                    return;
                }
            }
            else {
                logger.debug("doFastValidateUser failed: {}", doFastValidateUserResponse);
                // 判斷失敗情況下沒有 Fallback 的選項, 3層式有錯誤不執行 Fallback
                if (SsoLoginAuthType.THREE_LAYER == currentSsoAuthType) {
                    service.generateCallBackUrl(rsData, doFastValidateUserResponse.getStatusCode(), "", cache.getRqData().getPlatformParams(), cache.getGetFastValidateUserResponse().getOpenType(), cache.getGetFastValidateUserResponse().getModuleType(), cache.getGetFastValidateUserResponse().getModuleParam());
                    return;
                }
                Optional<SsoLoginAuthType> nextAuthTypeOptional = ssoAuthStep.getNextAuthType(currentSsoAuthType, false);
                // 有下一個
                if (nextAuthTypeOptional.isPresent()) {
                    SsoLoginAuthType nextAuthType = nextAuthTypeOptional.get();
                    rsData.setNextFallBackAuthRequired(true);
                    rsData.setNextAuthType(nextAuthType.getCode());
                }
                else {
                    // 沒有下一個, 直接失敗
                    service.generateCallBackUrl(rsData, doFastValidateUserResponse.getStatusCode(), "", cache.getRqData().getPlatformParams(), cache.getGetFastValidateUserResponse().getOpenType(), cache.getGetFastValidateUserResponse().getModuleType(), cache.getGetFastValidateUserResponse().getModuleParam());
                    return;
                }
            }
        }
        catch (Throwable e) {
            service.generateCallBackUrl(rsData, SsoStatusType.AUTH_TYPE_NOT_MATH.getStatusCode(), "", cache.getRqData().getPlatformParams(), cache.getGetFastValidateUserResponse().getOpenType(), cache.getGetFastValidateUserResponse().getModuleType(), cache.getGetFastValidateUserResponse().getModuleParam());
            logger.error("Fast Validate Failed: ", e);
        }
        finally {
            setCache(NGNOT999Service.FAST_VALIDATE_CACHE_KEY, cache);
        }
    }

    private void prepareRqDataForThreeLayer(NGNOT999020Rq rqData, NGNOT999Cache cache, SsoLoginAuthStep ssoAuthStep) {
        if (!ssoAuthStep.hasThreeLayer()) { // Cache 沒包含
            // TODO error
        }
        // 解密 RqData
        String userId = rqData.getUuid();
        if (!StringUtils.isBlank(rqData.getUuid())) {
            try {
                userId = loginService.decryptText(rqData.getUuid(), cache.isUidUuidNeedWithTime());
                rqData.setUuid(userId);
            }
            catch (Throwable e) {
                logger.error("解密錯誤: ", e);
            }
        }
        /** 解譯 ID */
        String custId = rqData.getUid();
        if (!StringUtils.isBlank(rqData.getUid())) {
            try {
                custId = loginService.decryptText(rqData.getUid(), cache.isUidUuidNeedWithTime());
                rqData.setUid(custId);
            }
            catch (Throwable e) {
                logger.error("解密錯誤: ", e);
            }
        }
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
