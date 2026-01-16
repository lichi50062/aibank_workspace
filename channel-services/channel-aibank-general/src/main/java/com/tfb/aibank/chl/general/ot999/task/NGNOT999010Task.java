package com.tfb.aibank.chl.general.ot999.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.biz.component.e2ee.E2EEUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.security.sso.SsoLoginAuthService;
import com.tfb.aibank.chl.component.security.sso.SsoLoginAuthStep;
import com.tfb.aibank.chl.general.ot999.model.NGNOT999010Rq;
import com.tfb.aibank.chl.general.ot999.model.NGNOT999010Rs;
import com.tfb.aibank.chl.general.ot999.task.service.NGNOT999Cache;
import com.tfb.aibank.chl.general.ot999.task.service.NGNOT999Service;
import com.tfb.aibank.chl.general.resource.SecurityResource;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.resource.dto.GetFastValidateUserRequest;
import com.tfb.aibank.chl.general.resource.dto.GetFastValidateUserResponse;
import com.tfb.aibank.chl.general.resource.dto.RetriveDeviceStatusResponse;
import com.tfb.aibank.chl.general.service.LoginService;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.type.SsoStatusType;

//@formatter:off
/**
* @(#)NGNOT999010Task.java 
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
public class NGNOT999010Task extends AbstractAIBankBaseTask<NGNOT999010Rq, NGNOT999010Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT999010Task.class);

    @Autowired
    private UserResource userResource;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Autowired
    private NGNOT999Service service;

    @Autowired
    private SecurityResource securityResource;

    @Autowired
    private SsoLoginAuthService authService;
    
    @Autowired
    private LoginService loginService;

    
    @Override
    public void validate(NGNOT999010Rq rqData) throws ActionException {
    }

    @Override
    public void handle(NGNOT999010Rq rqData, NGNOT999010Rs rsData) throws ActionException {
        logger.debug("==== NGNOT999010 start====");

        generateCallBackUrl(rsData, SsoStatusType.UNKNOWN, null, rqData.getPlatformParams(), "O", "0", "");

        GetFastValidateUserRequest request = new GetFastValidateUserRequest();
        request.setPlatformId(rqData.getPlatformId());
        request.setPlatformParams(rqData.getPlatformParams());
        request.setPlatformUserAccount(rqData.getPlatformUserAccount());
        request.setPlatformUserVerify(rqData.getPlatformUserVerify());
        // Ai Sso Setting
        GetFastValidateUserResponse getFastLoginUserResponse = userResource.getFastValidateUser(request);

        if (!"0000".equals(getFastLoginUserResponse.getStatusCode())) {
            logger.error("failed: {}", getFastLoginUserResponse);
            generateCallBackUrl(rsData, SsoStatusType.find(getFastLoginUserResponse.getStatusCode()), null, rqData.getPlatformParams(), "O", "0", "");
            return;
        }
        
        // #7881 驗證設定值 
        SsoLoginAuthStep ssoAuthStep = null;
        try {
            ssoAuthStep = authService.parse(getFastLoginUserResponse.getAuthType());
        }catch (Throwable e ) {
            logger.error(String.format("Illegal Auth Type %s", getFastLoginUserResponse.getAuthType()), e);
            generateCallBackUrl(rsData, SsoStatusType.NOT_REGISTERED, null, rqData.getPlatformParams(), "O", "0", "");
            return;
        }
        
        try {
            // ** 裝置綁定狀態*/
//            String deviceIxd = "7D3869FC-5EA7-43FE-BCC3-0074C3289261";
//            String model = "iPhone16,1";
//            String version = "";
            
            RetriveDeviceStatusResponse retriveDeviceStatusResponse = userResource.retriveDeviceStatus(getRequest().getDeviceIxd(), getRequest().getModel(), getRequest().getVersion());
//            RetriveDeviceStatusResponse retriveDeviceStatusResponse = userResource.retriveDeviceStatus(deviceIxd, model, version);

            /** 檢查此裝置是否已有綁定快速登入 */
            if ((ssoAuthStep.hasQuickLogin() && !ssoAuthStep.hasThreeLayerInParimary())  &&  !retriveDeviceStatusResponse.isFastLogin()) {
                logger.warn("set quickLogin but tyreeLayer not in primary : user fastLogin is needed "  );
                generateCallBackUrl(rsData, SsoStatusType.DEVICE_NOT_BINDED, null, rqData.getPlatformParams(), getFastLoginUserResponse.getOpenType(), getFastLoginUserResponse.getModuleType(), getFastLoginUserResponse.getModuleParam());
                return;
            }

            /** 檢查此裝置綁定快速登入之身分證字號是否與身分認證平台傳入的身分證字號相同 */
            if ((ssoAuthStep.hasQuickLogin() && !ssoAuthStep.hasThreeLayerInParimary()) &&  getFastLoginUserResponse.getCustId() != null && !getFastLoginUserResponse.getCustId().equals(retriveDeviceStatusResponse.getCustId())) {
                logger.warn("cust id not equal ");
                generateCallBackUrl(rsData, SsoStatusType.ID_NOT_MATCH, null, rqData.getPlatformParams(), getFastLoginUserResponse.getOpenType(), getFastLoginUserResponse.getModuleType(), getFastLoginUserResponse.getModuleParam());
                return;
            }

            /** (檢查此裝置是否為黑名單裝置 */
            if (ssoAuthStep.hasQuickLogin() &&  retriveDeviceStatusResponse.getIsInBlackList() != null && retriveDeviceStatusResponse.getIsInBlackList() == 1) {
                logger.warn("has quick login, device is in blacklist {} ", retriveDeviceStatusResponse);
                generateCallBackUrl(rsData, SsoStatusType.DEVICE_IN_BLACK_LIST, null, rqData.getPlatformParams(), getFastLoginUserResponse.getOpenType(), getFastLoginUserResponse.getModuleType(), getFastLoginUserResponse.getModuleParam());
                return;
            }

            try {
                String kxy = securityResource.getRSAPublicKxy();
                rsData.setPublicKeyforUid(kxy);
                rsData.setPublicKeyforSecret(kxy);
            }
            catch (ServiceException ex) {
                logger.error("取得公鑰失敗", ex);
            }

            /** 檢核認證方式與快速登入綁定的類型是否一致 */
            // #7881: 有設定快登才需檢查
            if (ssoAuthStep.hasQuickLogin()) {
            // 圖型
//            if ("1".equals(getFastLoginUserResponse.getAuthType()) || "3".equals(getFastLoginUserResponse.getAuthType())) {
                if ("1".equals(getFastLoginUserResponse.getAuthType())) {
                    if (retriveDeviceStatusResponse.getPwType() != 1) { // 7881 3=threelayer
                        logger.warn("檢核認證方式與快速登入綁定的類型不一致 {} {}", getFastLoginUserResponse.getAuthType(), getFastLoginUserResponse);
                        generateCallBackUrl(rsData, SsoStatusType.AUTH_EXPIRED, null, rqData.getPlatformParams(), getFastLoginUserResponse.getOpenType(), getFastLoginUserResponse.getModuleType(), getFastLoginUserResponse.getModuleParam());
                        return;
                    }
                }
                // 生物辨識
                if ("2".equals(getFastLoginUserResponse.getAuthType()) ) { // 7881 4=otp
                    if (!(retriveDeviceStatusResponse.getPwType() == 2 || retriveDeviceStatusResponse.getPwType() == 3 || retriveDeviceStatusResponse.getPwType() == 4)) {
                        logger.warn("檢核認證方式與快速登入綁定的類型不一致 {} {}", getFastLoginUserResponse.getAuthType(), getFastLoginUserResponse);
                        generateCallBackUrl(rsData, SsoStatusType.AUTH_EXPIRED, null, rqData.getPlatformParams(), getFastLoginUserResponse.getOpenType(), getFastLoginUserResponse.getModuleType(), getFastLoginUserResponse.getModuleParam());
                        return;
                    }
                }
            }

            // #7881
            if (ssoAuthStep.hasThreeLayer()) {
                populatePwdNeedWithTime(rsData);
            }
            
            rsData.setFastLogin(retriveDeviceStatusResponse.isFastLogin());
            rsData.setCoefficient(retriveDeviceStatusResponse.getCoefficient());
            /**
             * * 認證方式
               * 0-帳密
               * 1=圖型
               * 2=Face ID
               * 3=Touch ID
               * 4=Android
               * 5=Bio密碼
               * 快速登入密碼類型，0:文字密碼；1:手勢密碼；2:指紋；3:臉部；4:Android生物辨識
             */
            rsData.setLoginMethod(retriveDeviceStatusResponse.getPwType());
            /**
             * #7881 登入身份別 0:一般會員登入 1:信用卡會員登入 (MB_DEVICE_INFO) 所定義的值
             * 若只有3層式密碼, 此處需要設為-1, 讓前端顯示登入身分選項
             */
            if (ssoAuthStep.hasQuickLogin() && retriveDeviceStatusResponse.isFastLogin()) {
                rsData.setLoginType(retriveDeviceStatusResponse.getLoginType());    
            }else {
                rsData.setLoginType(-1);
            }
            
            rsData.setCustId(retriveDeviceStatusResponse.getCustId());
            rsData.setDirectEzLoginFlag(retriveDeviceStatusResponse.getDirectEzLoginFlag());
            rsData.setIsInBlackList(retriveDeviceStatusResponse.getIsInBlackList());
            rsData.setStatus(retriveDeviceStatusResponse.getStatus());
            rsData.setStatusCode(getFastLoginUserResponse.getStatusCode());
            rsData.setModuleParam(method);
            // #7881
            rsData.setPrimaryTypes(ssoAuthStep.getPrimaryTypeCodes());
            rsData.setAdditionalType(ssoAuthStep.getAdditionalTypeCode());
            rsData.setFallbackType(ssoAuthStep.getFallbackTypeCode());
            
            NGNOT999Cache cache = new NGNOT999Cache();
            cache.setRetriveDeviceStatusResponse(retriveDeviceStatusResponse);
            cache.setRqData(rqData);
            cache.setGetFastValidateUserResponse(getFastLoginUserResponse);
            cache.setSsoAuthStep(ssoAuthStep);
            setCache(NGNOT999Service.FAST_VALIDATE_CACHE_KEY, cache);
            setCache(LoginService.DEVICE_BINDING_CACHE_KEY, retriveDeviceStatusResponse);
        }
        catch (ServiceException ex) {
            logger.error("Populate Response Failed: ",ex);
            generateCallBackUrl(rsData, SsoStatusType.NOT_REGISTERED, null, rqData.getPlatformParams(), "O", "0", "");
            return;
        }

        try {
            String fidoPortalUrl = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "FIDO_SDK_URL");
            rsData.setFidoPortalUrl(fidoPortalUrl);

            String businessNo = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "FIDO_BUSINESS_NO");
            rsData.setBusinessNo(businessNo);

            String url = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "LOGIN_FORGETPW_URL");
            rsData.setLoginForgetpwUrl(url);

            String privacyurl = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "PRIVACY_POLICY_URL");
            rsData.setPrivacyLink(privacyurl);

            String chatBotUrl = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "CHATBOT_URL");
            rsData.setChatBotUrl(chatBotUrl);

        }
        catch (ServiceException ex) {
            logger.error("Get System Pram Failed: ", ex);
        }

    }
    
    /**
     * 時間因子設定
     * @param rsData
     */
    private void populatePwdNeedWithTime(NGNOT999010Rs rsData) {
        // 設定時間因子等參數
        /** E2EE給前端PWD加密時，是否帶入之時間因子 */
        rsData.setPwdNeedWithTime(loginService.isPwdNeedWithTime());
        if (rsData.isPwdNeedWithTime()) {
            String tmpTimeFactorValidSeconds = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "TIME_FACTOR_VALID_SECONDS");
            int timeFactorValidSeconds = 280;
            if (StringUtils.isNotBlank(tmpTimeFactorValidSeconds)) {
                /** 資料庫為時間因子容許時間差，此處保留 20 秒緩衝時間給通訊或程式運作 */
                timeFactorValidSeconds = Integer.parseInt(tmpTimeFactorValidSeconds) - 20;
                logger.debug("TimeFactor: timeFactorValidSeconds: {}", timeFactorValidSeconds);
            }
            rsData.setTimeFactorValidSeconds(timeFactorValidSeconds);
            rsData.setPwdWithTime(E2EEUtils.getPassWithTime());
            logger.debug("TimeFactor: pwdWithTime: {}", rsData.getPwdWithTime());
        }
        /** E2EE給前端uid/uuid加密時，是否帶入之時間因子 */
        rsData.setUidUuidNeedWithTime(loginService.isUidUuidNeedWithTime());
        if (rsData.isUidUuidNeedWithTime()) {
            rsData.setUidUuidWithTime(E2EEUtils.getPassWithTime());
            logger.debug("TimeFactor: UidUuidNeedWithTime: {}", rsData.getUidUuidWithTime());
        }
    }

    /**
     * 組合callback URL
     * 
     * @param rsData
     * @param statusCode
     * @param token
     * @param platformParams
     */
    private void generateCallBackUrl(NGNOT999010Rs rsData, SsoStatusType statusCode, String token, String platformParams, String openType, String moduleType, String moduleParam) {
        StringBuilder sb = new StringBuilder();

        rsData.setStatusCode(statusCode.getStatusCode());

        sb.append(systemParamCacheManager.getValue("AIBANK", "LOGIN_CALLBACK_URL", "https://ebankcld.taipeifubon.com.tw/B2CCLD/GW/AuthService/quickAuth/authenticate/result"));
        sb.append("?");
        sb.append("statusCode=");
        sb.append(statusCode);
        sb.append("&");

        sb.append("statusDesc=");
        sb.append(statusCode.getMemo());

        if (StringUtils.isNotBlank(token)) {
            sb.append("&");
            sb.append("token=");
            sb.append(token);
        }

        if (StringUtils.isNotBlank(platformParams)) {
            sb.append("&");
            sb.append("platformParams=");
            sb.append(platformParams);
        }

        rsData.setCallBackUrl(sb.toString());

        if (StringUtils.equals(openType, "I")) {
            rsData.setWhiteListWhenOpenUrl(service.getWhitList());
            rsData.getWhiteListWhenOpenUrl().add(service.getDomain(rsData.getCallBackUrl()));
        }
        rsData.setOpenType(openType);
        rsData.setModuleType(moduleType);
        rsData.setModuleParam(moduleParam);
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
