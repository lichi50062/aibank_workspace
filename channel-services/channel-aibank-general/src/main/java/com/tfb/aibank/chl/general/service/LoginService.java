/**
 * 
 */
package com.tfb.aibank.chl.general.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.AESUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.base.model.ClientRequest;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.ibm.tw.ibmb.component.crypto.AESCipherUtils;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProvider;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProviderFactory;
import com.ibm.tw.ibmb.util.IBUtils;
import com.ibm.tw.ibmb.util.IBUtils.ErrorDescription;
import com.tfb.aibank.biz.component.e2ee.E2EEUtils;
import com.tfb.aibank.biz.type.TwoFactorStatusType;
import com.tfb.aibank.chl.component.countryname.IpLocationAndCountryNameCacheManager;
import com.tfb.aibank.chl.component.countryname.model.IpLocationAndCountryName;
import com.tfb.aibank.chl.component.devicebinding.DeviceBindingService;
import com.tfb.aibank.chl.component.devicebinding.model.CheckUserDeviceStatusCondition;
import com.tfb.aibank.chl.component.devicebinding.model.CheckUserDeviceStatusResult;
import com.tfb.aibank.chl.component.devicebinding.type.UserDeviceBindStatusType;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.general.ot001.task.service.LoginMailData;
import com.tfb.aibank.chl.general.ot001.task.service.LoginMailType;
import com.tfb.aibank.chl.general.resource.NotificationResource;
import com.tfb.aibank.chl.general.resource.SecurityResource;
import com.tfb.aibank.chl.general.resource.SystemConfigResource;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.resource.dto.DecryptRSAEncodedTextRequest;
import com.tfb.aibank.chl.general.resource.dto.DecryptRSAEncodedTextResponse;
import com.tfb.aibank.chl.general.resource.dto.DeviceInfo;
import com.tfb.aibank.chl.general.resource.dto.DoRevokeRequest;
import com.tfb.aibank.chl.general.resource.dto.DoRevokeResponse;
import com.tfb.aibank.chl.general.resource.dto.EncodeWithSecretRequest;
import com.tfb.aibank.chl.general.resource.dto.EncodeWithSecretResponse;
import com.tfb.aibank.chl.general.resource.dto.ExecuteUserLoginRequest;
import com.tfb.aibank.chl.general.resource.dto.ExecuteUserLoginResponse;
import com.tfb.aibank.chl.general.resource.dto.LoginResponse;
import com.tfb.aibank.chl.general.resource.dto.PassRuleMsg;
import com.tfb.aibank.chl.general.resource.dto.PassRuleType;
import com.tfb.aibank.chl.general.resource.dto.QueryLogResponse;
import com.tfb.aibank.chl.general.resource.dto.QueryLogResponseRepeat;
import com.tfb.aibank.chl.general.resource.dto.QueryVerifyResultRequest;
import com.tfb.aibank.chl.general.resource.dto.QueryVerifyResultResponse;
import com.tfb.aibank.chl.general.resource.dto.RetrieveMultiUserBindingResponse;
import com.tfb.aibank.chl.general.resource.dto.RetriveDeviceStatusResponse;
import com.tfb.aibank.chl.general.resource.dto.TwoFactorAuthRequest;
import com.tfb.aibank.chl.general.resource.dto.TwoFactorAuthResponse;
import com.tfb.aibank.chl.general.resource.dto.TwoFactorAuthUserRequest;
import com.tfb.aibank.chl.general.resource.dto.TwoFactorAuthUserResponse;
import com.tfb.aibank.chl.general.resource.dto.UpdateMbDeviceInfoRequest;
import com.tfb.aibank.chl.general.resource.dto.ValidateWithPasswordRuleRequest;
import com.tfb.aibank.chl.general.resource.dto.ValidateWithPasswordRuleResponse;
import com.tfb.aibank.chl.general.resource.vo.twofactor.AppInfoVo;
import com.tfb.aibank.chl.general.service.model.DoLoginBaseRq;
import com.tfb.aibank.chl.general.type.TwoFactorAuthHandleActionType;
import com.tfb.aibank.chl.general.type.TwoFactorAuthType;
import com.tfb.aibank.chl.general.type.TwoFactorChannelType;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.common.type.E2EEHsmType;
import com.tfb.aibank.chl.general.resource.dto.PushTxnModel;

//@formatter:off
/**
* @(#)LoginHelper.java 
* 
* <p>Description:登入程序 Helper</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20230522, JohnChang
*  <li>初版</li>
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on
@Component
public class LoginService extends NGNService {

    private static final IBLog logger = IBLog.getLog(LoginService.class);

    public static final String PRE_LOGIN_CACHE_KEY = "PRE_LOGIN_CACHE_KEY";
    // 無障礙
    // public static final String PRE_LOGIN_ACC_CACHE_KEY = "PRE_LOGIN_ACC_CACHE_KEY";

    public static final String PATTERN_LOCK_CACHE_KEY = "PATTERN_LOCK_CACHE_KEY";

    public static final String DEVICE_BINDING_CACHE_KEY = "DEVICE_BINDING_CACHE_KEY";
    // 無障礙
    // public static final String DEVICE_BINDING_ACC_CACHE_KEY = "DEVICE_BINDING_ACC_CACHE_KEY";

    public static final String FASTLOGIN_TERMS_REMARK_KEY = "BIOMETRIC_ENABLE";
    // 不需區分一般及無障礙
    public static final String FIDO_INFO_KEY = "FIDO_INFO_KEY";

    public static final String DEVICE_TRANSFER_CACHE_KEY = "DEVICE_TRANSFER_CACHE_KEY";
    // 登入前雙重驗證
    public static final String TWO_FACTOR_CACHE_KEY = "TWO_FACTOR_CACHE_KEY";

    public static final int NO_TRANSFER = 0;

    public static final int IS_TRANSFER = 1;

    public static final int TRANSFER_SUCC = 2;

    public static final int TRANSFER_FAIL = 3;

    @Autowired
    private UserResource userResource;

    @Autowired
    private SecurityResource securityResource;

    @Autowired
    private UserDataCacheService userDataCacheService;

    @Autowired
    private DeviceBindingService deviceBindingService;

    @Autowired
    private SystemConfigResource systemConfigResource;

    @Autowired
    private NotificationResource notificationResource;

    @Autowired
    private IpLocationAndCountryNameCacheManager ipLocationAndCountryCacheManager;

    /**
     * 執行登入動作(OT001, OT005)共用
     * 
     * @param rq
     * @param clientRequest
     * @param sessionId
     * @param mdDupLoginConfirm
     * @param isPwdWithTime
     * @return
     * @throws ActionException
     */
    public ExecuteUserLoginResponse executeUserLogin(DoLoginBaseRq rq, ClientRequest clientRequest, String sessionId, boolean mdDupLoginConfirm, boolean isPwdWithTime, Locale locale) throws ActionException {

        ExecuteUserLoginResponse response = new ExecuteUserLoginResponse();

        ExecuteUserLoginRequest request = buildExecuteUserLoginRequest(rq, clientRequest, sessionId, mdDupLoginConfirm, isPwdWithTime, locale);

        try {
            response = userResource.executeUserLogin(request);
        }
        catch (ServiceException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ActionException(AIBankErrorCode.SERVICE_NOT_AVAILABLE);
        }
        return response;
    }

    /**
     * 產出 User Login Request
     * 
     * @param rq
     * @param clientRequest
     * @param sessionId
     * @param mdDupLoginConfirm
     * @param isPwdWithTime
     * @return
     */
    public ExecuteUserLoginRequest buildExecuteUserLoginRequest(DoLoginBaseRq rq, ClientRequest clientRequest, String sessionId, boolean mdDupLoginConfirm, boolean isPwdWithTime, Locale locale) {
        ExecuteUserLoginRequest request = new ExecuteUserLoginRequest();

        request.setUid(rq.getUid());
        request.setUuid(rq.getUuid());
        request.setSecrxt(rq.getSecret());
        request.setLoginType(rq.getLoginType());
        request.setDeviceId(clientRequest.getDeviceIxd());
        request.setDeviceInfo(getDeviceInfo(clientRequest));

        request.setPwType(rq.getPwType());

        // 生物辨識 或 圖形登入
        request.setIsSignatureLogin(rq.getIsSignatureLogin());
        request.setMdDupLoginConfirm(mdDupLoginConfirm);
        request.setChallenge(rq.getChallenge());

        // 其他資訊
        request.setClientIP(clientRequest.getClientIp());
        request.setCurrentSessionId(sessionId);
        request.setMdDupLoginConfirm(mdDupLoginConfirm);

        /** 時間因子 */
        request.setIsPwdWithTime(isPwdWithTime);

        // 手機資訊
        if (rq.getAgent() != null) {
            try {
                request.setUserAgent(new String(Base64.getDecoder().decode(rq.getAgent()), StandardCharsets.UTF_8));
            }
            catch (Exception ex) {
                logger.warn("cannot find UserAgent");
            }
        }
        request.setScreenHeight(rq.getScreenHeight());
        request.setScreenWidth(rq.getScreenWidth());

        // ip 國別
        String ipAddress = clientRequest.getClientIp();
        request.setLocale(locale.toString());
        IpLocationAndCountryName ipLocation = ipLocationAndCountryCacheManager.getCountryNameByIpLocationAndLocale(ipAddress, locale);
        request.setCountryCode(ipLocation.getCountryCode());
        request.setCountryName(ipLocation.getCountryName());
        request.setIpFrom(ipLocation.getIpFrom());
        request.setIpTo(ipLocation.getIpTo());

        return request;
    }

    /**
     * 
     * @param clientRequest
     * @return
     */
    private DeviceInfo getDeviceInfo(ClientRequest clientRequest) {
        DeviceInfo deviceInfo = new DeviceInfo();

        deviceInfo.setId(clientRequest.getDeviceIxd());
        deviceInfo.setModel(clientRequest.getModel());
        deviceInfo.setNetwork(clientRequest.getNetwork());
        deviceInfo.setPlatform(clientRequest.getPlatform());
        // OS_VERSION
        deviceInfo.setVersion(clientRequest.getVersion());
        // APP_VERSION
        deviceInfo.setAppVersion(clientRequest.getAppVer());
        // RES_VERSION 網頁包
        deviceInfo.setResVersion(clientRequest.getRuntimeVer());

        return deviceInfo;
    }

    /**
     * 
     * @return
     */
    public AESCipherUtils getAESCipherUtils() {
        SecretKeyProvider<?> provider = SecretKeyProviderFactory.getInstance().getProvider(AIBankConstants.CRYPTO_SECRET_KEY_PROVIDER_DEFAULT);
        return new AESCipherUtils(provider);
    }

    /**
     * 
     * @param e2eeHsmType
     * @param uid
     * @param uuid
     * @param secret
     * @return
     */
    public String encodewithSecret(E2EEHsmType e2eeHsmType, String uid, String uuid, String secret) {
        EncodeWithSecretRequest rq = new EncodeWithSecretRequest();
        rq.setE2eeHsmType(e2eeHsmType.name());
        rq.setUid(uid);
        rq.setUuid(uuid);
        rq.setEncodedSecret(secret);
        rq.setCharList(new ArrayList<Character>());
        rq.setNumberlist(new ArrayList<Character>());

        EncodeWithSecretResponse rs = securityResource.encodewithSecret(rq);
        return rs.getHostSecret();
    }

    /**
     * 
     * @param encryptedText
     * @param isetIsPwdWithTime
     * @return
     */
    public String decryptText(String encryptedText, boolean isetIsPwdWithTime) {

        DecryptRSAEncodedTextRequest rqData = new DecryptRSAEncodedTextRequest();
        rqData.setEncodedText(encryptedText);
        rqData.setIsPwdWithTime(isetIsPwdWithTime);
        DecryptRSAEncodedTextResponse rsData = securityResource.decryptRSAEncodedText(rqData);
        return rsData.getRawText();
    }

    /**
     * 
     * @param custId
     * @param userId
     * @param pinblock
     * @param isPwdNeedWithTime
     * @return
     */
    public PassRuleMsg validateWithPasswordRule(String custId, String userId, String pinblock, boolean isPwdNeedWithTime) {
        ValidateWithPasswordRuleRequest rqValidateWithPasswordRuleRequest = new ValidateWithPasswordRuleRequest();
        rqValidateWithPasswordRuleRequest.setCheckRule(PassRuleType.LOGIN_RULE_NORMAL);
        rqValidateWithPasswordRuleRequest.setUid(custId);
        rqValidateWithPasswordRuleRequest.setUuid(userId);
        rqValidateWithPasswordRuleRequest.setEncodedSecrxt(pinblock);
        rqValidateWithPasswordRuleRequest.setIsPwdWithTime(isPwdNeedWithTime);

        ValidateWithPasswordRuleResponse resposne = securityResource.validateWithPasswordRule(rqValidateWithPasswordRuleRequest);
        return resposne.getPassRuleMsg();
    }

    /**
     * 亂數取得9位英數字且不重複 設定
     */
    public static String getFactorString() {
        String randomStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder factor = new StringBuilder();
        SecureRandom rnd = new SecureRandom();
        while (factor.length() < 9) { // length of the random string.
            int index = rnd.nextInt(randomStr.length());
            String s = String.valueOf(randomStr.charAt(index));
            if (factor.indexOf(s) == -1) {
                factor.append(s);
            }
        }

        return factor.toString();
    }

    /**
     * 取的加密後的因子
     * 
     * @param deviceId
     * @return
     */
    public String getCoefficient(String deviceId) {
        if (Strings.isBlank(deviceId) || deviceId.length() < 16)
            return "";

        String key = deviceId.substring(0, 16);
        byte[] encodeFactor = null;
        try {
            String factor = getFactorString();
            logger.info("{}={}", deviceId, factor);
            encodeFactor = AESUtils.encryptCoefficient(factor.getBytes(), new SecretKeySpec(key.getBytes(), "AES"));
        }
        catch (CryptException e) {
            logger.error("", e);
        }
        return Hex.encodeHexString(encodeFactor);
    }

    /**
     * 取得 Fido 需要的 MemberNo
     * 
     * @param memberNo
     * @param deviceId
     * @return
     */
    public String getMemberNo(String memberNo, String deviceId) {
        if (Strings.isBlank(deviceId) || deviceId.length() < 16)
            return "";

        String key = deviceId.substring(0, 16);
        byte[] encodeMemberNo = null;
        try {
            encodeMemberNo = AESUtils.encryptCoefficient(memberNo.getBytes(), new SecretKeySpec(key.getBytes(), "AES"));
        }
        catch (CryptException e) {
            logger.error("", e);
        }
        return Hex.encodeHexString(encodeMemberNo);
    }

    /**
     * 
     * @param loginType
     * @param pwTypeStr
     * @param isSucc
     * @param isTwoFactor
     * @return
     */
    protected LoginMailType getLoginMailType(String loginType, String pwTypeStr, boolean isSucc, boolean isTwoFactor) {

        int pwType = Integer.parseInt(pwTypeStr);

        if (isTwoFactor) {
            return isSucc ? LoginMailType.LOGIN_SUCC_TWOFACTOR : LoginMailType.LOGIN_FAIL_TWOFACTOR;
        }
        else if ("m1".equals(loginType)) {
            switch (pwType) {
            case 0:
                return isSucc ? LoginMailType.LOGIN_SUCC : LoginMailType.LOGIN_FAIL;
            case 1:
                return isSucc ? LoginMailType.PATTERN_LOGIN_SUCC : LoginMailType.PATTERN_LOGIN_FAIL;
            case 2, 3, 4:
                return isSucc ? LoginMailType.BIO_LOGIN_SUCC : LoginMailType.BIO_LOGIN_FAIL;
            default:
                return isSucc ? LoginMailType.LOGIN_SUCC : LoginMailType.LOGIN_FAIL;
            }
        }
        else {
            switch (pwType) {
            case 0:
                return isSucc ? LoginMailType.CARD_LOGIN_SUCC : LoginMailType.CARD_LOGIN_FAIL;
            case 1:
                return isSucc ? LoginMailType.PATTERN_LOGIN_SUCC : LoginMailType.PATTERN_LOGIN_FAIL;
            case 2, 3, 4:
                return isSucc ? LoginMailType.BIO_LOGIN_SUCC : LoginMailType.BIO_LOGIN_FAIL;
            default:
                return isSucc ? LoginMailType.CARD_LOGIN_SUCC : LoginMailType.CARD_LOGIN_FAIL;
            }
        }

    }

    /**
     * 登入Mail 通知種類
     * 
     * @param loginType
     * @param pwTypeStr
     * @param isSucc
     * @return
     */
    public LoginMailType getLoginMailType(String loginType, String pwTypeStr, boolean isSucc) {
        return getLoginMailType(loginType, pwTypeStr, isSucc, false);
    }

    /**
     * 雙重驗證登入 Email種類
     * 
     * @param loginType
     * @param pwTypeStr
     * @param isSucc
     * @return
     */
    public LoginMailType getTwoFactorLoginMailType(String loginType, String pwTypeStr, boolean isSucc) {
        return getLoginMailType(loginType, pwTypeStr, isSucc, true);
    }

    /**
     * 一般登入流程 Login Mail Data (不傳入OS)
     * 
     * @param loginMailType
     * @param clientIp
     * @param model
     * @param locale
     * @param e
     * @param version
     * @param countryName
     * @return
     */
    public LoginMailData getLoginMailData(LoginMailType loginMailType, String clientIp, String model, Locale locale, ActionException e, String version, String countryName) {
        return getLoginMailData(loginMailType, clientIp, model, locale, e, version, countryName, "");
    }

    /**
     * Login Mail Data
     * 
     * @param loginMailType
     * @param clientIp
     * @param model
     * @param locale
     * @param e
     * @param version
     * @return
     */
    public LoginMailData getLoginMailData(LoginMailType loginMailType, String clientIp, String model, Locale locale, ActionException e, String version) {
        return getLoginMailData(loginMailType, clientIp, model, locale, e, version, "", "");
    }

    /**
     * 雙重驗證 LoginMailData (需傳入OS)
     * 
     * @param loginMailType
     * @param clientIp
     * @param model
     * @param locale
     * @param e
     * @param version
     * @param countryName
     * @param os
     * @return
     */
    public LoginMailData getTwoFactorLoginMailData(LoginMailType loginMailType, String clientIp, String model, Locale locale, ActionException e, String version, String countryName, String os) {
        return getLoginMailData(loginMailType, clientIp, model, locale, e, version, countryName, os);
    }

    /**
     * 
     * @param loginMailType
     * @param clientIp
     * @param model
     * @param locale
     * @param e
     * @param version
     * @return
     */
    protected LoginMailData getLoginMailData(LoginMailType loginMailType, String clientIp, String model, Locale locale, ActionException e, String version, String countryName, String os) {
        LoginMailData loginMailData = new LoginMailData();

        loginMailData.setSubject(I18NUtils.getMessage(loginMailType.getSubject()));

        HashMap<String, String> params = new HashMap<>();
        params.put("status", Integer.toString(loginMailType.getStatus()));
        params.put("title", I18NUtils.getMessage(loginMailType.getTitle()));
        params.put("subTitle", I18NUtils.getMessage(loginMailType.getSubtitle()));
        String errorMsg = "";
        if (e != null) {
            ErrorDescription errorDesc = IBUtils.getErrorDescMessage(errorCodeCacheManager, e, locale, MDC.get(MDCKey.frompage.name()));
            if (errorDesc != null) {
                errorMsg = "(" + e.getSystemId() + "-" + e.getErrorCode() + ")" + errorDesc.getErrorDesc();
            }
        }

        String currentTime = DateUtils.getDateTimeStr(new Date());
        params.put("type", Integer.toString(loginMailType.getType()));
        params.put("result", I18NUtils.getMessage(loginMailType.getResult()) + errorMsg);
        params.put("time", currentTime);
        params.put("ip", StringUtils.isBlank(clientIp) ? "" : clientIp);
        params.put("countryName", StringUtils.isBlank(countryName) ? "" : countryName);
        params.put("device", StringUtils.isBlank(model) ? "" : model);
        params.put("note", StringUtils.isBlank(loginMailType.getNote()) ? "" : I18NUtils.getMessage(loginMailType.getNote()));
        params.put("os", StringUtils.isBlank(os) ? "" : os);

        switch (loginMailType.getType()) {
        case 0:
        case 4: {
            if (loginMailType == LoginMailType.WELCOME_MAIL) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());

                String year = Integer.toString(cal.get(Calendar.YEAR));
                String month = Integer.toString(cal.get(Calendar.MONTH) + 1);
                String day = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));

                String content = I18NUtils.getMessage("ngn.ot001.mail.welcome.note", year, month, day);
                params.put("content", content);

                params.put("noTable", "1");
            }
            else if ((loginMailType.getOption() % 10) == 1) {
                params.put("content", I18NUtils.getMessage("ngn.ot001.mail.note"));
                params.put("noTable", "0");
            }
            else {
                params.put("content", "");
                params.put("noTable", "0");
            }
            break;
        }
        case 1: {
            params.put("content", I18NUtils.getMessage(loginMailType.getContent(), currentTime));
            break;
        }
        case 2: {
            params.put("content", I18NUtils.getMessage(loginMailType.getContent(), currentTime));
            params.put("version", version);
            break;
        }
        case 3: {
            params.put("content", I18NUtils.getMessage(loginMailType.getContent(), currentTime));
            break;
        }
        }

        loginMailData.setParams(params);
        return loginMailData;

    }

    public String encodewithSecret(String uid, String uuid, String secret) {
        EncodeWithSecretRequest rq = new EncodeWithSecretRequest();
        rq.setE2eeHsmType(E2EEHsmType.PWD_EB5556981_CP1047.name());
        rq.setUid(uid);
        rq.setUuid(uuid);
        rq.setEncodedSecret(secret);

        EncodeWithSecretResponse rs = securityResource.encodewithSecret(rq);
        return rs.getHostSecret();

    }

    /**
     * 檢核可否綁定
     * 
     * @param loginUser
     * @param deviceId
     * @param locale
     * @return
     */
    public int checkDeviceCanBind(AIBankUser loginUser, String deviceId, Locale locale) {
        // 檢核該裝置是否已被其它ID綁定
        // 檢查使用者與裝置綁定狀態
        CheckUserDeviceStatusCondition condition = new CheckUserDeviceStatusCondition();
        condition.setDeviceIxd(deviceId);
        condition.setLoginUser(loginUser);
        condition.setLocale(locale.toString());
        CheckUserDeviceStatusResult result = new CheckUserDeviceStatusResult();
        deviceBindingService.checkUserDeviceStatusForLogin(condition, result);

        if (result.getStatus().equals(UserDeviceBindStatusType.UNBIND)) {
            return isMultiUserCheckOk(loginUser);
        }
        return 0;
    }

    /**
     * 是否通過 單一戶名 及 多使用者代碼客戶 檢查
     * 
     * @param loginUser
     * @return
     */
    private int isMultiUserCheckOk(AIBankUser loginUser) {
        // 檢核使用者是否為單一戶名
        boolean isNotSingleAcct = userDataCacheService.isNotSingleAccount(loginUser);
        if (isNotSingleAcct) {
            logger.debug("[行動裝置綁定前檢核] 非單一戶名，檢核失敗");
            return 0;
        }

        // 檢核使用者是否為多使用者代碼客戶
        if (!userDataCacheService.getOpnCnFlag(loginUser)) {
            logger.debug("[行動裝置綁定前檢核] 非單一使用者");
            RetrieveMultiUserBindingResponse response = userResource.retrieveMultiUserBinding(loginUser.getCustId(), loginUser.getUserId(), loginUser.getCompanyKind(), loginUser.getUidDup());
            if (response.isOtherUserBind()) {
                logger.debug("[行動裝置綁定前檢核] 非單一使用者，且其他使用者已綁定，檢核失敗");
                return 0;
            }
        }
        return 1;
    }

    /**
     * 登入APP人數檢核
     * 
     * @param custId
     * @return true
     */
    public boolean isNotAllowToLogin(String custId) {

        // A. 判斷是否需檢核登入APP人數超過上限
        String aibankMaxFlag = systemParamCacheManager.getValue("MONITOR", "AIBANK_MAX_FLAG");
        // 1表示需檢核；非1表示不需檢核
        if (aibankMaxFlag == null || !"1".equals(aibankMaxFlag))
            return false;

        // B. 是否阻擋登入APP超過人數上限禁止登入
        // true 代表已達人數上限
        if (!systemConfigResource.isMaxLoginForbid()) {
            return false;
        }

        // C. 客戶白名單資料來源
        String aibankMaxLoginList = systemParamCacheManager.getValue("MONITOR", "AIBANK_MAX_LOGIN_LIST");
        // 包含在白名單
        if (aibankMaxLoginList != null && aibankMaxLoginList.contains(custId)) {
            return false;
        }

        // D. 登入人數上限判斷
        String aibankMaxLoginFlag = systemParamCacheManager.getValue("MONITOR", "AIBANK_FORBIDDEN_LOGIN_FLAG");
        // 1表示需檢核；非1表示不需檢核
        if (aibankMaxLoginFlag == null || !"1".equals(aibankMaxFlag))
            return false;

        return true;

    }

    /**
     * 檢核是否變更過使用者代碼或使用者密碼註記
     * 
     * @param deviceId
     * @return
     */
    public Boolean isHasChangedPwd(String deviceId) {
        return userResource.isHasChangedPwd(deviceId);
    }

    /**
     * E2EE給前端PWD加密時，是否帶入之時間因子
     * 
     * @return
     */
    public boolean isPwdNeedWithTime() {
        String isNeedValidateTimeFlag = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "E2EE_AIBANK_CHECK_PWD_WITHTIME_FLG");
        return StringUtils.equals(isNeedValidateTimeFlag, "1");
    }

    /**
     * E2EE給前端uid/uuid加密時，是否帶入之時間因子
     * 
     * @return
     */
    public boolean isUidUuidNeedWithTime() {
        String isNeedValidateTimeFlag = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "E2EE_AIBANK_CHECK_UID_UUID_WITHTIME_FLG");
        return StringUtils.equals(isNeedValidateTimeFlag, "1");
    }

    /**
     * E2EE給前端密碼加密時，帶入之時間因子
     * 
     * @return
     * @throws IOException
     */
    public String getPwdPassWithTime() throws ActionException {
        String passWithTime;
        if (isPwdNeedWithTime()) {
            passWithTime = E2EEUtils.getPassWithTime();
            logger.info("取得供前端密碼加密時，PWD帶入之時間因子 passWithTime:" + passWithTime);
            return passWithTime;
        }
        else {
            logger.info("時間因子認證尚未開放，PWD不需帶入時間因子");
            return StringUtils.EMPTY;
        }

    }

    /**
     * E2EE給前端uid/uuid加密時，帶入之時間因子
     * 
     * @return
     * @throws IOException
     */
    public String getUidUuidPassWithTime() throws ActionException {
        String passWithTime;

        if (isUidUuidNeedWithTime()) {
            passWithTime = E2EEUtils.getPassWithTime();
            logger.info("取得供前端密碼加密時，UID/UUID帶入之時間因子 passWithTime:" + passWithTime);
            return passWithTime;
        }
        else {
            logger.info("時間因子認證尚未開放，UID/UUID不需帶入時間因子");
            return StringUtils.EMPTY;
        }
    }

    /**
     * 檢查是否需要需要解綁，並解綁
     * 
     * @param rqData
     * @param cache
     * @param companyKind
     * @return
     */
    public boolean isNeedDeleteFastLogin(ClientRequest clientRequest, DoLoginBaseRq rqData, RetriveDeviceStatusResponse cache, Integer companyKind) {

        if (companyKind == null || companyKind != 1) {
            logger.info("CHANGE_PWD_FLAG: companyKind not match");
            return false;
        }

        if (cache.getCustId() == null && cache.getCustId().length() != 8) {
            logger.info("CHANGE_PWD_FLAG: not company");
            return false;
        }

        if (!StringUtils.equals(rqData.getUid(), cache.getCustId())) {
            logger.info("CHANGE_PWD_FLAG: uid not match");
            return false;
        }

        UpdateMbDeviceInfoRequest request = new UpdateMbDeviceInfoRequest();
        request.setCustId(cache.getCustId());
        request.setUidDup(cache.getUidDup());
        request.setUserId(cache.getUserId());
        request.setCompanyKind(cache.getCompanyKind());
        request.setDeviceUuid(clientRequest.getDeviceIxd());
        request.setUpdateLoginFlag(true);
        request.setLoginFlag(0);

        userResource.updateMbDeviceInfo(request);

        try {
            deleteFIDOBinding(cache.getCustId(), cache.getUserId(), cache.getCompanyKind(), cache.getUidDup(), clientRequest.getDeviceIxd());
        }
        catch (ServiceException ex) {
            logger.warn("FIDO 解綁失敗", ex);
        }

        return true;
    }

    /**
     * 執行刪除 FIDO Binding
     * 
     * @param custId
     * @param userId
     * @param companyKind
     * @param uidDup
     * @param deviceId
     */
    public void deleteFIDOBinding(String custId, String userId, Integer companyKind, String uidDup, String deviceId) {
        QueryLogResponse queryLogResponse = securityResource.queryLog(custId + uidDup);

        // QueryLog失敗，不繼續執行
        if (!StringUtils.equals("0", queryLogResponse.getStatus())) {
            logger.info("FIDO綁定註銷異常...QueryLog失敗");
            return;
        }

        // QueryLog沒資料，不繼續執行
        if (CollectionUtils.isEmpty(queryLogResponse.getData())) {
            logger.info("FIDO綁定註銷異常...QueryLog沒資料");
            return;
        }

        // 逐筆KeyId執行DoRevok
        List<String> keyIds = new ArrayList<>();
        for (QueryLogResponseRepeat queryLog : queryLogResponse.getData()) {

            String keyId = StringUtils.defaultString(queryLog.getFidoKeyId());
            DoRevokeRequest doRevokeRequest = new DoRevokeRequest();
            doRevokeRequest.setCustId(custId);
            doRevokeRequest.setUidDup(uidDup);
            doRevokeRequest.setUserId(userId);
            doRevokeRequest.setCompanyKind(companyKind);
            doRevokeRequest.setKeyId(queryLog.getFidoKeyId());
            doRevokeRequest.setDeviceId(deviceId);
            DoRevokeResponse doRevokeResponse = securityResource.doRevoke(doRevokeRequest);

            // DoRevoke失敗
            if (!StringUtils.equals("0", doRevokeResponse.getStatus())) {
                logger.info("FIDO綁定註銷異常...DoRevoke失敗({})", keyId);
                logger.info("FIDO綁定註銷異常...已註銷{}筆({})", keyId, keyIds.stream().collect(Collectors.joining(",")));
                return;
            }
        }
    }

    /**
     * 生物辨識登入驗證
     * 
     * @param cache
     * @param loginResponse
     * @param clientRequest
     */
    public QueryVerifyResultResponse doFastLoginQueryVerifyResult(RetriveDeviceStatusResponse cache, LoginResponse loginResponse, ClientRequest clientRequest) {

        // 生物辨識登入，驗證
        QueryVerifyResultRequest request = new QueryVerifyResultRequest();

        request.setCustId(cache.getCustId());
        request.setUserId(cache.getUserId());
        request.setCompanyKind(cache.getCompanyKind());
        request.setUidDup(cache.getUidDup());
        request.setToken(loginResponse.getToken());
        request.setVerifyNo(loginResponse.getVerifyNo());
        request.setRegisterDevice(false);
        request.setDeviceId(clientRequest.getDeviceIxd());
        request.setModel(clientRequest.getModel());
        request.setDevicePlatform(IBUtils.displayPlatform(clientRequest.getPlatform()));
        request.setDevicePlatformVersion(clientRequest.getVersion());
        request.setDeviceAlias("");
        request.setIp(clientRequest.getClientIp());
        request.setLoginType(0);
        request.setLoginPasswordType(0);

        return securityResource.queryVerifyResult(request);

    }

    /**
     * 初始雙重驗證
     * 
     * @param companyKey
     * @param userKey
     * @return
     * @throws ActionException
     */
    public TwoFactorAuthUserResponse initTwoFactorAuth(TwoFactorAuthType authType, Integer companyKey, Integer userKey, String deviceId, String clientIP, String location) throws ActionException {

        TwoFactorAuthUserRequest twoFactorAuthUserRequest = buildTwoFactorAuthUserRequest(TwoFactorAuthHandleActionType.INIT, authType, null, companyKey, userKey, deviceId, clientIP, location, null);
        twoFactorAuthUserRequest.setStatus(TwoFactorStatusType.WAIT.getType());
        TwoFactorAuthUserResponse authUserResponse = userResource.handleTwoFactorAuthentication(twoFactorAuthUserRequest);
        return authUserResponse;
    }

    /**
     * 發送通知
     * 
     * @param companyKey
     * @param userKey
     * @param authUserResponse
     * @return
     */
    public TwoFactorAuthResponse sendTwoFactorAuthNotification(Integer companyKey, Integer userKey, TwoFactorAuthUserResponse authUserResponse, String deviceId, String deviceName, String os, String clientIp, String location, String loginTimeStr, Locale locale) {
        boolean isAllowAddTrustDevice = isAllowAddTrustDevice(companyKey, userKey);

        TwoFactorAuthRequest authRequest = new TwoFactorAuthRequest();
        authRequest.setCompanyKey(String.valueOf(companyKey));
        authRequest.setUserKey(String.valueOf(userKey));
        authRequest.setPushTitle(I18NUtils.getMessage("twofactor.message.pushtitle", locale));
        authRequest.setPushContent(I18NUtils.getMessage("twofactor.message.pushcontent", locale));

        AppInfoVo appInfo = new AppInfoVo();
        appInfo.setSeq(String.valueOf(authUserResponse.getSeq()));
        appInfo.setChannel(TwoFactorChannelType.APP.getCode());
        appInfo.setDeviceName(deviceName);
        appInfo.setDeviceId(deviceId);
        appInfo.setOs(os);
        appInfo.setIp(clientIp);
        appInfo.setBrowserType("");
        appInfo.setIsAllow(isAllowAddTrustDevice ? "1" : "0");
        appInfo.setTemplateCode("TwoFactorAuth");
        appInfo.setLocation(location);
        appInfo.setLoginTime(loginTimeStr);
        appInfo.setLocale(locale.toString());

        List<AppInfoVo> appInfoList = new ArrayList<>();
        appInfoList.add(appInfo);
        authRequest.setAppInfo(appInfoList);

        TwoFactorAuthResponse authResponse = this.notificationResource.fireTwoFactorNotification(authRequest);
        return authResponse;
    }

    /**
     * 
     * @param seq
     * @param companyKey
     * @param userKey
     * @return
     * @throws ActionException
     */
    public TwoFactorAuthUserResponse handleTwoFactorAuth(TwoFactorAuthType authType, Long seq, Integer companyKey, Integer userKey, TwoFactorStatusType status, String deviceId, String clientIp, String location, Integer personNotificationRecordKey) throws ActionException {
        TwoFactorAuthUserRequest twoFactorAuthUserRequest = buildTwoFactorAuthUserRequest(TwoFactorAuthHandleActionType.UPDATE, authType, seq, companyKey, userKey, deviceId, clientIp, location, personNotificationRecordKey);
        twoFactorAuthUserRequest.setStatus(status.getType());
        TwoFactorAuthUserResponse authUserResponse = userResource.handleTwoFactorAuthentication(twoFactorAuthUserRequest);
        return authUserResponse;
    }

    /**
     * 
     * @param action
     * @param seq
     * @param companyKey
     * @param userKey
     * @return
     */
    public TwoFactorAuthUserRequest buildTwoFactorAuthUserRequest(TwoFactorAuthHandleActionType action, TwoFactorAuthType authType, Long seq, Integer companyKey, Integer userKey, String deviceId, String clientIp, String location, Integer personNotificationRecordKey) {
        TwoFactorAuthUserRequest twoFactorAuthUserRequest = new TwoFactorAuthUserRequest();
        if (seq != null) {
            twoFactorAuthUserRequest.setSeq(seq);
        }
        twoFactorAuthUserRequest.setCompanyKey(companyKey);
        twoFactorAuthUserRequest.setUserKey(userKey);
        twoFactorAuthUserRequest.setAction(action.getCode());
        twoFactorAuthUserRequest.setDeviceId(deviceId);
        twoFactorAuthUserRequest.setIp(clientIp);
        twoFactorAuthUserRequest.setLocation(location);
        twoFactorAuthUserRequest.setAuthType(authType.getType());
        twoFactorAuthUserRequest.setPersonNotificationRecordKey(personNotificationRecordKey);
        return twoFactorAuthUserRequest;
    }

    /**
     * 查詢
     * 
     * @param companyKey
     * @param userKey
     * @return
     * @throws ActionException
     */
    public TwoFactorAuthUserResponse queryTwoFactorAuth(TwoFactorAuthType authType, Long seq, Integer companyKey, Integer userKey, String deviceId, String clientIp, String location) throws ActionException {

        TwoFactorAuthUserRequest twoFactorAuthUserRequest = buildTwoFactorAuthUserRequest(TwoFactorAuthHandleActionType.QUERY, authType, seq, companyKey, userKey, deviceId, clientIp, location, null);
        TwoFactorAuthUserResponse authUserResponse = userResource.handleTwoFactorAuthentication(twoFactorAuthUserRequest);
        return authUserResponse;
    }

    /**
     * 若{信任裝置筆數} 大於或等於 {信任裝置上限}，上送：0 (不可新增) 非上述，上送：1
     * 
     * @param companyKey
     * @param userKey
     * @return
     */
    public boolean isAllowAddTrustDevice(Integer companyKey, Integer userKey) {

        Integer totalTrustDevice = userResource.countTrustDeviceByUser(companyKey, userKey);
        String trustMaxCountValue = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "TURST_MAX_COUNT");

        Integer trustMaxCount = StringUtils.isBlank(trustMaxCountValue) ? 0 : Integer.parseInt(trustMaxCountValue);

        logger.debug("totalTrustDevice: {}, trustMaxCountValue: {}, allowed: {}]", totalTrustDevice, trustMaxCountValue, totalTrustDevice < trustMaxCount);
        return totalTrustDevice < trustMaxCount;
    }

    /**
     * 
     * @return
     */
    public int getCheckTwoFactorCheckSeconds() {

        String checkSecondValue = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "TWO_FACTOR_CHECK_SECONDS");

        return StringUtils.isBlank(checkSecondValue) ? 0 : Integer.parseInt(checkSecondValue);

    }

    public PushTxnModel prepareRequest(AIBankUser loginUser, String clientIp, String traceId, String appVer) {
        PushTxnModel request = new PushTxnModel();
        // 身分證字號含誤別碼
        request.setCustIdDup(loginUser.getCustIdWithDup());
        // 身分證字號
        request.setCustId(loginUser.getCustId());
        // 使用者代號
        request.setUserId(loginUser.getUserId());
        // 公司類型
        request.setCompanyKind(loginUser.getCompanyKind());
        // 用戶代碼
        request.setNameCode(loginUser.getNameCode());
        // 誤別碼
        request.setUidDup(loginUser.getUidDup());
        // 用戶類型 (A). 若為一般客戶：TYPE = 0。 (B). 若為信用卡網路會員：TYPE = 1
        request.setType(loginUser.getCustomerType().isGeneral() ? 0 : 1);
        // 目前裝置版號
        request.setVersion(appVer);
        return request;
    }

}
