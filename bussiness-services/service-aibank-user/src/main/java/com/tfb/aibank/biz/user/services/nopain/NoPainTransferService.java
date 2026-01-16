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
package com.tfb.aibank.biz.user.services.nopain;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.message.BasicHeader;
import org.apache.xmlbeans.XmlException;
import org.slf4j.MDC;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.httpclient.CustomHttpResponse;
import com.ibm.tw.commons.httpclient.HttpClient5;
import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.JsonUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.biz.component.identity.IdentityData;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.user.gateway.EsbChannelGateway;
import com.tfb.aibank.biz.user.repository.CardUserProfileRepository;
import com.tfb.aibank.biz.user.repository.ChangePasswordTipsRepository;
import com.tfb.aibank.biz.user.repository.CompanyRepository;
import com.tfb.aibank.biz.user.repository.MbDeviceInfoRepository;
import com.tfb.aibank.biz.user.repository.MbLoginLogRepository;
import com.tfb.aibank.biz.user.repository.OnboardingTransferLogRepository;
import com.tfb.aibank.biz.user.repository.UserLoginRepository;
import com.tfb.aibank.biz.user.repository.UserRepository;
import com.tfb.aibank.biz.user.repository.aib.MbDevicePushInfoRepository;
import com.tfb.aibank.biz.user.repository.aib.entities.MbDevicePushInfoEntity;
import com.tfb.aibank.biz.user.repository.entities.CardUserProfileEntity;
import com.tfb.aibank.biz.user.repository.entities.ChangePasswordTipsEntity;
import com.tfb.aibank.biz.user.repository.entities.CompanyEntity;
import com.tfb.aibank.biz.user.repository.entities.MbDeviceInfoEntity;
import com.tfb.aibank.biz.user.repository.entities.OnboardingTransferLogEntity;
import com.tfb.aibank.biz.user.repository.entities.UserEntity;
import com.tfb.aibank.biz.user.repository.entities.UserLoginEntity;
import com.tfb.aibank.biz.user.services.nopain.model.AuthorizeProfileResponse;
import com.tfb.aibank.biz.user.services.nopain.model.ExecuteTransferUserLoginResponse;
import com.tfb.aibank.biz.user.services.nopain.model.MbLoginModel;
import com.tfb.aibank.biz.user.services.nopain.model.OnboardingLogRequest;
import com.tfb.aibank.chl.session.type.LoginStatusType;
import com.tfb.aibank.chl.session.vo.EB5556981Response;
import com.tfb.aibank.chl.type.ResultCodeType;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.common.type.OnboardingType;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIResponseException;
import com.tfb.aibank.integration.eai.msg.CEW013RRS;
import com.tfb.aibank.integration.eai.msg.CEW302RRS;

import tw.com.ibm.mf.eb.CEW013RSvcRsType;

// @formatter:off
/**
 * @(#)NoPainTransferService.java
 * 
 * <p>Description:無痛移轉旅程</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/31, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NoPainTransferService {

    private static final int MAX_LINE_LENGTH = 1000000;

    private static final IBLog logger = IBLog.getLog(NoPainTransferService.class);

    private EsbChannelGateway esbGateway;

    private CompanyRepository companyRepository;

    private UserRepository userProfileRepository;

    private UserLoginRepository userLoginRepository;

    private CardUserProfileRepository cardUserProfileRepository;

    private MbDeviceInfoRepository mbDeviceInfoRepository;

    private MbDevicePushInfoRepository mbDevicePushInfoRepository;

    private MbLoginLogRepository mbLoginLogRepository;

    private SystemParamCacheManager systemParamCacheManager;

    private Environment environment;

    private IdentityService identityService;

    private OnboardingTransferLogRepository onboardingTransferLogRepository;

    private ChangePasswordTipsRepository changePasswordTipsRepository;

    public NoPainTransferService(EsbChannelGateway esbGateway,

            CompanyRepository companyRepository,

            UserRepository userProfileRepository,

            UserLoginRepository userLoginRepository,

            CardUserProfileRepository cardUserProfileRepository,

            MbDeviceInfoRepository mbDeviceInfoRepository,

            MbDevicePushInfoRepository mbDevicePushInfoRepository,

            MbLoginLogRepository mbLoginLogRepository,

            Environment environment,

            SystemParamCacheManager systemParamCacheManager,

            OnboardingTransferLogRepository onboardingTransferLogRepository,

            IdentityService identityService,

            ChangePasswordTipsRepository changePasswordTipsRepository) {

        this.esbGateway = esbGateway;
        this.companyRepository = companyRepository;
        this.userProfileRepository = userProfileRepository;
        this.userLoginRepository = userLoginRepository;
        this.cardUserProfileRepository = cardUserProfileRepository;
        this.mbDeviceInfoRepository = mbDeviceInfoRepository;
        this.mbDevicePushInfoRepository = mbDevicePushInfoRepository;
        this.mbLoginLogRepository = mbLoginLogRepository;
        this.environment = environment;
        this.systemParamCacheManager = systemParamCacheManager;
        this.onboardingTransferLogRepository = onboardingTransferLogRepository;
        this.identityService = identityService;
        this.changePasswordTipsRepository = changePasswordTipsRepository;
    }

    /**
     * 無痛轉移使用者登入
     * 
     * @param accessToken
     * @throws ActionException
     */
    public ExecuteTransferUserLoginResponse executeTransferUserLogin(String accessToken, String onboardingType, String deviceIxd) throws ActionException {
        ExecuteTransferUserLoginResponse response = new ExecuteTransferUserLoginResponse();
        // 身份認證平台取資料
        AuthorizeProfileResponse authorizeProfileResponse = getSSOLoginData(accessToken);

        response.setErrorCode("1");
        response.setErrorDesc(authorizeProfileResponse.getMessage());
        if (!StringUtils.equals("200", authorizeProfileResponse.getStatus())) {
            return response;
        }

        MbLoginModel data = authorizeProfileResponse.getData();
        if (data == null) {
            logger.error("AuthorizeProfile Data is null");
            throw new ActionException(AIBankErrorCode.SSO_AUTH_DATA_ERROR);
        }

        /** 檢核基本回應，必須提供 */
        if (StringUtils.isBlank(data.getUid()) //
                || StringUtils.isBlank(data.getUidDup()) //
                || StringUtils.isBlank(data.getUuid()) //
                || StringUtils.isBlank(data.getCompanyKind())) {
            logger.error("AuthorizeProfile Data is null");
            throw new ActionException(AIBankErrorCode.SSO_AUTH_DATA_ERROR);
        }

        /** 沒有 Login_Method，代表無痛移轉結果的新增欄位為空 */
        if (StringUtils.isBlank(data.getLoginMethod())) {
            logger.error("AuthorizeProfile Data is null");
            throw new ActionException(AIBankErrorCode.SSO_AUTH_DATA_ERROR);
        }

        Integer companyKey = Integer.parseInt(data.getCompanyKey());
        Integer userKey = Integer.parseInt(data.getUserKey());

        Optional<CompanyEntity> companyEntity = companyRepository.findById(companyKey);

        /** Company */
        if (companyEntity.isEmpty()) {
            logger.error("CompanyEntity is not found key={} ", data.getCompanyKey());
            throw new ActionException(AIBankErrorCode.SSO_AUTH_DATA_ERROR);
        }

        /** 比對 CustId */
        if (!companyEntity.get().getCompanyUid().equals(data.getUid())) {
            logger.error("CompanyEntity is not match key={}, {} ", data.getCompanyKey(), data.getUid());
            throw new ActionException(AIBankErrorCode.SSO_AUTH_DATA_ERROR);
        }
        response.setUid(data.getUid());
        response.setCompanyKind(Integer.parseInt(data.getCompanyKind()));
        response.setUidDup(data.getUidDup());

        /** USER_PROFILE */
        Optional<UserEntity> userEntity = userProfileRepository.findById(userKey);
        if (userEntity.isEmpty()) {
            logger.error("UserEntity is not found key={} ", data.getUserKey());
            throw new ActionException(AIBankErrorCode.SSO_AUTH_DATA_ERROR);
        }
        UserEntity user = userEntity.get();
        /** 比對 UserId */
        if (!user.getUserUuid().equals(data.getUuid())) {
            logger.error("UserEntity is not match key={}, {} ", data.getUserKey(), data.getUuid());
            throw new ActionException(AIBankErrorCode.SSO_AUTH_DATA_ERROR);
        }

        /** 驗證完成 */
        if (StringUtils.equals("NONE", onboardingType)) {
            response.setOnboardingType(OnboardingType.TRANSFER_FROM_MB.getCode());
        }
        else {
            response.setOnboardingType(OnboardingType.TRANSFER_BY_AIBANK.getCode());
        }

        response.setUuid(data.getUuid());
        response.setCustomerType(data.getLoginType());

        EB5556981Response loginMsgRs = getEB5556981Response(authorizeProfileResponse);
        response.setLoginMsgRs(loginMsgRs);

        /** MB_DEVICE_PROFILE */
        /** 本機是否已綁定，有任何綁定，視為一般登入 */
        List<MbDeviceInfoEntity> mbDeviceEntities = mbDeviceInfoRepository.findByDeviceUuId(deviceIxd);
        if (!CollectionUtils.isEmpty(mbDeviceEntities)) {
            response.setOnboardingType(OnboardingType.AIBANK.getCode());
        }

        /** 一般會員 */
        if (StringUtils.equals(data.getLoginType(), "m1")) {
            response.setMobileNo(loginMsgRs.getMobileNo());
            response.setEmail(getEmailList(loginMsgRs.getEmailAddr()));
            if (loginMsgRs.getBirthday() != null)
                response.setBirthDay(loginMsgRs.getBirthday().getTime());

            // 判斷客戶是否半年未變更使用者密碼
            if (loginMsgRs.getPwChgDate() != null) {
                if (isMoreThanXMonth(loginMsgRs.getPwChgDate(), 6)) {
                    try {
                        response.setShowChangeTip(isShowChangeTip(companyEntity.get().getCompanyKey(), user.getUserKey()));
                    }
                    catch (Exception ex) {
                        response.setShowChangeTip(false);
                    }
                }
            }
        }
        /** 信用卡會員 */
        else {
            /** CARD_USER_PROFILE */
            CardUserProfileEntity cardUserEntity = cardUserProfileRepository.findByUserKey(userKey);
            if (cardUserEntity == null) {
                logger.error("CardUserEntity is not found key={} ", data.getUserKey());
                throw new ActionException(AIBankErrorCode.SSO_AUTH_DATA_ERROR);
            }
            /** 發查電話 */
            response.setMobileNo(getCEW013RMobile(data.getUid()));

            // 卡友
            CEW302RRS rs = sendCEW302R(data.getUid());
            if (rs != null && rs.getBody() != null) {
                if (rs.getBody().getBIRTHD().getTime() != null) {
                    response.setBirthDay(rs.getBody().getBIRTHD().getTime());
                }
                response.setCardEmail(rs.getBody().getEMAILADDRESS());
            }
            NoPainServerHelper.copyCardUserEntity(cardUserEntity, response.getCardUserProfileVo());

            // 判斷客戶是否半年未變更使用者密碼
            if (isMoreThanXMonth(user.getTxDate(), 6)) {
                response.setShowChangeTip(true);
            }
        }

        // 更新 USER_PROFILE.FIRST_LOGIN
        user.setFirstLogin(new Date());
        userProfileRepository.save(user);

        response.setNameCode(data.getNameCode());
        response.setLoginMethod(data.getLoginMethod());
        // 更新 USER_LOGIN
        UserLoginEntity userLoginEntity = updateUserLoginData(response, data);

        // 複製資料到 response
        NoPainServerHelper.copyCompanyEntity(companyEntity.get(), response.getCompanyVo());
        NoPainServerHelper.copyUserEntity(userEntity.get(), response.getUserVo());
        NoPainServerHelper.copyLoginEntity(userLoginEntity, response.getUserLoginVo());
        if (!mbDeviceEntities.isEmpty()) {
            List<MbDevicePushInfoEntity> mbDevicePushInfoEntity = mbDevicePushInfoRepository.findByCompanyKeyAndUserKey(companyKey, userKey);
            if (CollectionUtils.isNotEmpty(mbDevicePushInfoEntity)) {
                NoPainServerHelper.copyMbDeviceInfoEntity(mbDeviceEntities.get(0), mbDevicePushInfoEntity.get(0), null);
            }
        }

        response.setDeviceBindingInfo(string2List(data.getDeviceBindingInfo()));
        response.setDeviceSubPushCode(string2List(data.getDeviceSubPushCode()));
        response.setErrorCode("0");
        return response;
    }

    /**
     * 判斷是否要提醒變更密碼
     *
     * true:
     *
     * 1. ChangePasswordTipsEntity 沒有就提醒，並加上一筆
     *
     * 2. ChangePasswordTipsEntity 有，且上次提醒日超過6個月，並更新時間
     */
    private boolean isShowChangeTip(Integer companyKey, Integer userKey) {
        List<ChangePasswordTipsEntity> entities = changePasswordTipsRepository.findByCompanyKeyAndUserKey(companyKey, userKey);
        boolean isShow = true;
        if (CollectionUtils.isNotEmpty(entities)) {
            ChangePasswordTipsEntity changePasswordTipsEntity = entities.get(0);
            if (!isMoreThanXMonth(changePasswordTipsEntity.getTxDate(), 6)) {
                isShow = false;
            }
        }
        return isShow;
    }

    /**
     * 早於 x 個月前
     *
     * @param date
     * @param xMonth
     * @return true = 早於 x 個月前
     */
    private boolean isMoreThanXMonth(Date date, int xMonth) {
        if (date == null)
            return true;
        Calendar compareDate = Calendar.getInstance();
        compareDate.setTime(date);
        return isMoreThanXMonth(compareDate, xMonth);
    }

    private boolean isMoreThanXMonth(Calendar calendar, int xMonth) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, xMonth * -1);
        return calendar.compareTo(cal) == -1 ? true : false;
    }

    /**
     * 更新 USER_LOGIN 資料
     * 
     * @param loginUser
     * @param request
     * @throws ActionException
     */
    private UserLoginEntity updateUserLoginData(ExecuteTransferUserLoginResponse response, MbLoginModel data) throws ActionException {
        Date loginTime = new Date();

        /** USER_LOGIN */
        Optional<UserLoginEntity> userloginEntity = userLoginRepository.findById(Integer.parseInt(data.getUserKey()));
        if (userloginEntity.isEmpty()) {
            logger.error("UserLoginEntity is not found key={} ", data.getUserKey());
            throw new ActionException(AIBankErrorCode.SSO_AUTH_DATA_ERROR);
        }
        UserLoginEntity loginEntity = userloginEntity.get();
        loginEntity.setServerId(getServerId());
        // 成功, 判斷 loginTime 是否比 lastLoginTime新 , 若是, 則要copy
        boolean needCopy = (loginEntity.getLastLoginTime() == null || loginEntity.getLoginTime() == null) || loginEntity.getLoginTime().after(loginEntity.getLastLoginTime());
        if (needCopy) {
            // 留下前一次的 client ip
            loginEntity.setLastClientIp(loginEntity.getClientIp());
            // 留下前一次的 登入時間
            loginEntity.setLastLoginTime(loginEntity.getLoginTime());
            // 留下前一次的登入狀態
            loginEntity.setLastLoginResult(loginEntity.getLoginResult());
            // 留下前一次的國別狀態
            loginEntity.setLastNationName(loginEntity.getNationName());
            // 留下前一次的 channel id
            loginEntity.setLastChannelId(loginEntity.getChannelId());
        }
        // 成功時更新以下欄位
        loginEntity.setClientIp(MDC.get(MDCKey.clientip.name()));
        loginEntity.setLoginTime(loginTime);
        loginEntity.setSessionId(fixSessionId(MDCKey.sessionid.name()));
        loginEntity.setLoginResult(ResultCodeType.RESULT_OK.getResultCode());

        loginEntity.setStatus(LoginStatusType.LOGIN.getCode());
        loginEntity.setLastAccessTime(loginTime);
        String signonToken = new String(Hex.encodeHex(DigestUtils.sha256((IBSystemId.AIBANK.getSystemId() + "-" + loginEntity.getSessionId() + "-" + System.currentTimeMillis()).getBytes())));
        loginEntity.setSignonToken(signonToken);
        loginEntity.setChannelId("M"); // AIBANK

        userLoginRepository.save(loginEntity);
        return loginEntity;
    }

    private String getServerId() {
        String podName = IBUtils.getInstanceId(environment);
        if (StringUtils.isNotBlank(podName) && podName.length() > 16) {
            return StringUtils.right(podName, 16);
        }
        return podName;
    }

    /**
     * 避免SessionID過長
     * 
     * @param sessionId
     * @return
     */
    private String fixSessionId(String sessionId) {
        if (sessionId == null)
            return "";
        String sessId = sessionId.replace("-", "");
        if (sessId.length() > 32)
            return sessId.substring(0, 32);
        return sessId;
    }

    private List<String> getEmailList(String... emails) {
        List<String> emailList = new ArrayList<String>();
        for (String email : emails) {
            emailList.add(email);
        }
        return emailList;
    }

    private AuthorizeProfileResponse getSSOLoginData(String accessToken) throws ActionException {
        return getAuthorizeProfile(getSsoUrl(), accessToken);
    }

    /*
     * 申辦信用卡客戶資料查詢 只取用電話**
     * 
     * @param custId
     * 
     * @return
     * 
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

    private EB5556981Response getEB5556981Response(AuthorizeProfileResponse data) throws ActionException {
        EB5556981Response response = new EB5556981Response();

        // 行銀回傳的登入電文解析
        String doc = data.getData().getLoginData();
        if (StringUtils.isBlank(doc)) {
            return response;
        }

        response.setIdno(getValue(doc, "IDNO"));
        response.setNameCod(getValue(doc, "NAME_COD"));
        response.setUserId(getValue(doc, "USER_ID"));
        response.setUserIdLevel(getValue(doc, "USER_ID_LEVEL"));
        response.setEbillCheck(getValue(doc, "EBILL_CHECK"));
        response.setEbillEndDate(getDate(doc, "EBILL_END_DATE"));
        response.setBillCheck(getValue(doc, "BILL_CHECK"));
        response.setBillEndDate(getDate(doc, "BILL_END_DATE"));
        response.setSalaryCheck(getValue(doc, "SALARY_CHECK"));
        response.setPromoCheck(getValue(doc, "PROMO_CHECK"));
        response.setCustName(getValue(doc, "CUST_NAME"));
        response.setEmailAddr(getValue(doc, "EMAIL_ADDR"));
        response.setMobileNo(getValue(doc, "MOBILE_NO"));
        response.setBirthday(getDate(doc, "BIRTHDAY"));
        response.setIdCheck(getValue(doc, "ID_CHECK"));
        response.setMassChk(getValue(doc, "MASS_CHK"));
        response.setError(getValue(doc, "ERROR"));
        response.setPwChgDate(getDate(doc, "PW_CHG_DATE"));
        response.setUseridChgDate(getDate(doc, "USERID_CHG_DATE"));
        response.setEbillStrDate(getDate(doc, "EBILL_STR_DATE"));
        response.setMbSts(getValue(doc, "MB_STS"));
        response.setOtpMobile(getValue(doc, "OTP_MOBILE"));
        response.setNational(getValue(doc, "NATIONAL"));

        return response;
    }

    private Calendar getDate(String doc, String nodeName) {
        String data = getValue(doc, nodeName);

        if (StringUtils.isBlank(data))
            return null;
        try {
            Date date = DateFormatUtils.SQL_DATE_FORMAT.parse(data);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal;
        }
        catch (ParseException e) {
            return null;
        }
    }

    /** 電文內取值 */
    private String getValue(String doc, String nodeName) {
        int start = doc.indexOf(nodeName);
        if (start < 0)
            return "";
        start = doc.indexOf(">", start + 1);
        int end = doc.indexOf(nodeName, start);
        if (end < 0)
            return "";
        return doc.substring(start + 1, end - 2);
    }

    /**
     * 發查 CEW302R
     * 
     * @param custId
     * @return
     */
    private CEW302RRS sendCEW302R(String custId) {
        CEW302RRS rs = null;

        try {
            rs = esbGateway.sendCEW302R(custId);
        }
        catch (XmlException | EAIException | EAIResponseException ex) {
            logger.error("", ex);
            return null;
        }

        return rs;
    }

    /**
     * 身份認證平台 URL
     * 
     * @return
     */
    private String getSsoUrl() {
        return systemParamCacheManager.getValue("AIBANK", "AUTH_CENTER_URL");
    }

    /**
     * 執行API Get請求
     * 
     * @param connectUrl
     * @param accessToken
     * @return
     * @throws ActionException
     */
    private AuthorizeProfileResponse getAuthorizeProfile(String connectUrl, String accessToken) throws ActionException {

        CustomHttpResponse response = null;

        logger.info("SSO Service: Call={}, accessToken={}", connectUrl, accessToken);
        List<Header> headers = Arrays.asList( //
                new BasicHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE), //
                new BasicHeader("Authorization", "Bearer " + accessToken) //
        );

        response = HttpClient5.doGet(connectUrl, headers, null, 60);

        int statusCode = response.getCode();
        String rsPayload = StringUtils.defaultString(response.getResponse());
        logger.info("SSO Service: StatusCode={}, Payload={}", statusCode, rsPayload);

        AuthorizeProfileResponse rs = null;
        if (StringUtils.isNotBlank(rsPayload)) {
            rs = JsonUtils.getObject(rsPayload, AuthorizeProfileResponse.class);
        }
        else {
            throw ExceptionUtils.getActionException(AIBankErrorCode.SSO_DATA_ERROR);
        }

        return rs;
    }

    /**
     * "XXX,YYY,ZZZ" ==> List<String>
     * 
     * @param str
     * @return
     */
    private List<String> string2List(String str) {
        List<String> list = new ArrayList<String>();
        if (StringUtils.isBlank(str))
            return list;

        String[] items = str.split(",");
        if (items.length == 0)
            return list;

        for (String s : items) {
            list.add(s);
        }
        return list;
    }

    public static final int NO_TRANSFER = 0;

    public static final int IS_TRANSFER = 1;

    public static final int TRANSFER_SUCC = 2;

    public static final int TRANSFER_FAIL = 3;

    /**
     * 新增 無痛移轉資料庫 Log
     * 
     * @param request
     * @return
     * @throws CryptException
     */
    public boolean addOnboardingTransferLog(OnboardingLogRequest request) throws CryptException {

        IdentityData identityData = identityService.query(request.getCustId(), request.getUidDup(), request.getUserId(), request.getCompanyKind());

        OnboardingTransferLogEntity entity = new OnboardingTransferLogEntity();

        List<MbDeviceInfoEntity> mbDeviceEntities = mbDeviceInfoRepository.findByCompanyKeyAndUserKey(identityData.getCompanyKey(), identityData.getUserKey());

        if (mbDeviceEntities == null || mbDeviceEntities.size() > 1) {
            return false;
        }

        if (!StringUtils.equals(mbDeviceEntities.get(0).getDeviceUuId(), request.getDeviceIxd())) {
            logger.warn("MbDeviceInfo 裝置 UUID 錯誤 {}/{}/{}/{}", identityData.getCompanyKey(), identityData.getUserKey(), mbDeviceEntities.get(0).getDeviceUuId(), request.getDeviceIxd());
        }

        StringBuilder originItem = new StringBuilder();
        StringBuilder tranItemSucc = new StringBuilder();
        StringBuilder tranItemFail = new StringBuilder();

        // 定訊息通知
        // private int notification;
        // 免登速查
        // private int quickSearch;
        // 推播動態密碼MOTP
        // private int motpSetting;
        // 無卡提款
        // private int noCardwithDraw;
        // 手機號碼收款
        // private int phoneTransfer;
        // 提高非約轉額度
        // private int transferQuota;

        // NOTIFY：已綁定訊息通知
        // QQUERY：已綁定免登速查
        // NOCARD：已綁定無卡提款
        // PUMOTP：已綁定推播動態密碼MOTP
        // PHORCV：已綁定手機號碼收款
        // ENTXLI：已綁定提高飛約轉額度

        // 訊息通知
        if (request.getNotification() != NO_TRANSFER) {
            originItem.append("NOTIFY");
            originItem.append(",");
        }
        if (request.getNotification() == TRANSFER_SUCC) {
            tranItemSucc.append("NOTIFY");
            tranItemSucc.append(",");
        }
        if (request.getNotification() == TRANSFER_FAIL) {
            tranItemFail.append("NOTIFY");
            tranItemFail.append(",");
        }

        // 免登速查
        if (request.getQuickSearch() != NO_TRANSFER) {
            originItem.append("QQUERY");
            originItem.append(",");
        }
        if (request.getQuickSearch() == TRANSFER_SUCC) {
            tranItemSucc.append("QQUERY");
            tranItemSucc.append(",");
        }
        if (request.getQuickSearch() == TRANSFER_FAIL) {
            tranItemFail.append("QQUERY");
            tranItemFail.append(",");
        }

        // 推播動態密碼MOTP
        if (request.getMotpSetting() != NO_TRANSFER) {
            originItem.append("PUMOTP");
            originItem.append(",");
        }
        if (request.getMotpSetting() == TRANSFER_SUCC) {
            tranItemSucc.append("PUMOTP");
            tranItemSucc.append(",");
        }
        if (request.getMotpSetting() == TRANSFER_FAIL) {
            tranItemFail.append("PUMOTP");
            tranItemFail.append(",");
        }

        // 無卡提款
        if (request.getNoCardwithDraw() != NO_TRANSFER) {
            originItem.append("NOCARD");
            originItem.append(",");
        }
        if (request.getNoCardwithDraw() == TRANSFER_SUCC) {
            tranItemSucc.append("NOCARD");
            tranItemSucc.append(",");
        }
        if (request.getNoCardwithDraw() == TRANSFER_FAIL) {
            tranItemFail.append("NOCARD");
            tranItemFail.append(",");
        }

        // 手機號碼收款
        if (request.getPhoneTransfer() != NO_TRANSFER) {
            originItem.append("PHORCV");
            originItem.append(",");
        }
        if (request.getPhoneTransfer() == TRANSFER_SUCC) {
            tranItemSucc.append("PHORCV");
            tranItemSucc.append(",");
        }
        if (request.getPhoneTransfer() == TRANSFER_FAIL) {
            tranItemFail.append("PHORCV");
            tranItemFail.append(",");
        }

        // 提高飛約轉額度
        if (request.getTransferQuota() != NO_TRANSFER) {
            originItem.append("ENTXLI");
            originItem.append(",");
        }
        if (request.getTransferQuota() == TRANSFER_SUCC) {
            tranItemSucc.append("ENTXLI");
            tranItemSucc.append(",");
        }
        if (request.getTransferQuota() == TRANSFER_FAIL) {
            tranItemFail.append("ENTXLI");
            tranItemFail.append(",");
        }

        StringBuilder originPushItem = new StringBuilder(0);
        StringBuilder tranPushItemSucc = new StringBuilder(0);
        StringBuilder tranPushItemFail = new StringBuilder(0);
        // fortify: Denial of Service: StringBuilder - limit size
        if (request.getSuccNotificationTypes() != null && request.getSuccNotificationTypes().size() > 0)
            for (String item : request.getSuccNotificationTypes()) {
                originPushItem.append(StringUtils.left(item, MAX_LINE_LENGTH));
                originPushItem.append(",");
                tranPushItemSucc.append(StringUtils.left(item, MAX_LINE_LENGTH));
                tranPushItemSucc.append(",");
            }

        if (request.getFailNotificationTypes() != null && request.getFailNotificationTypes().size() > 0)
            for (String item : request.getFailNotificationTypes()) {
                originPushItem.append(StringUtils.left(item, MAX_LINE_LENGTH));
                originPushItem.append(",");
                tranPushItemFail.append(StringUtils.left(item, MAX_LINE_LENGTH));
                tranPushItemFail.append(",");
            }

        entity.setCompanyKey(identityData.getCompanyKey());
        entity.setUserKey(identityData.getUserKey());
        entity.setDeviceInfoKey(mbDeviceEntities.get(0).getDeviceInfoKey());
        entity.setDeviceUuid(request.getDeviceIxd());

        entity.setOnboardingType(Integer.toString(request.getOnboardingType()));

        entity.setOriginItem(originItem.toString());
        entity.setTransItemSuccess(tranItemSucc.toString());
        entity.setTransItemFail(tranItemFail.toString());

        entity.setOriginPushItem(originPushItem.toString());
        entity.setTransPushItemSuccess(tranPushItemSucc.toString());
        entity.setTransPushItemFail(tranPushItemFail.toString());

        entity.setCreateTime(new Date());

        onboardingTransferLogRepository.save(entity);
        return true;
    }

    /**
     * 是否進行過無痛移轉
     * 
     * @param request
     * @return
     * @throws CryptException
     */
    public Boolean isAlreadyTransfered(String custId, String uidDup, String userId, int companyKind, String deviceIxd) throws CryptException {

        IdentityData identityData = identityService.query(custId, uidDup, userId, companyKind);

        List<OnboardingTransferLogEntity> entities = onboardingTransferLogRepository.findSuccessTransfered(identityData.getCompanyKey(), identityData.getUserKey());

        if (CollectionUtils.isNotEmpty(entities))
            for (OnboardingTransferLogEntity entity : entities) {

                if (StringUtils.equals(entity.getDeviceUuid(), deviceIxd)) {
                    return true;
                }

            }
        return false;
    }

}
