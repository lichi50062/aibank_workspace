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
package com.ibm.tw.ibmb.task;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.ibm.tw.commons.error.IErrorCode;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.DatabaseException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.gson.GsonHelper;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.base.Constants;
import com.ibm.tw.ibmb.base.model.ClientRequest;
import com.ibm.tw.ibmb.base.model.ClientResponse;
import com.ibm.tw.ibmb.base.model.RqData;
import com.ibm.tw.ibmb.base.model.RsData;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.component.errorcode.ErrorCodeCacheManager;
import com.ibm.tw.ibmb.component.log.AsyncLogProcessor;
import com.ibm.tw.ibmb.exception.FeignActionException;
import com.ibm.tw.ibmb.model.B2CWebUser;
import com.ibm.tw.ibmb.type.LoginSystemType;
import com.ibm.tw.ibmb.type.SessionKey;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.ibm.tw.ibmb.util.IBUtils.ErrorDescription;
import com.ibm.tw.ibmb.util.ValidateParamUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// @formatter:off
/**
 * @(#)AbstractBaseTask.java
 * 
 * <p>Description:基礎的Task</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/05, Alex LS Chen	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public abstract class AbstractBaseTask<R extends RqData, S extends RsData> {

    /**
     * Logger
     */
    protected static final IBLog logger = IBLog.getLog(AbstractBaseTask.class);
    protected static final IBLog performanceLogger = IBLog.getLog("PERFORMANCE_LOGGER");
    private static final String CACHE_PREFIX = "cache:";
    /**
     * Spring ApplicationContext
     */
    @Autowired
    protected ApplicationContext context;

    /**
     * 上行資料
     */
    private ClientRequest request;

    /**
     * 下行資料
     */
    private ClientResponse response;

    /**
     * 系統 taskId
     */
    protected String taskId;

    /**
     * 交易代號 TxnId, ex:FAO01011
     */
    private String txnId;

    /**
     * 此次交易獨立的Id
     */
    private String txnUid;

    /**
     * Method名稱 ex:010
     */
    protected String method;

    /**
     * 此次連線的Session
     */
    private String sessionId;

    /**
     * 語系
     */
    private Locale locale;

    /**
     * 錯誤欄位
     */
    private Map<String, String> errorFieldMap = new HashMap<String, String>();

    /**
     * 底層的Request
     */
    private HttpServletRequest httpServletRequest;

    /**
     * 底層的Response
     */
    private HttpServletResponse httpServletResponse;

    /**
     * Spring整體的環境變數
     */
    private Environment environment;

    /** 是否發生 Hystrix Timeout */
    private boolean interruptted = false;

    protected LoginSystemType channelId;

    /** 是否為開發環境 */
    private boolean devEnv = false;

    /** 是否不需更新 timeout 設定 */
    private Boolean skipUpdateTimeoutValue = null;

    @Autowired
    protected ErrorCodeCacheManager errorCodeCacheManager;

    @Autowired
    @Qualifier("cacheManagerRedisTemplate")
    private RedisTemplate<String, String> cacheRedisTemplate;

    @Autowired
    protected AsyncLogProcessor asyncLogProcessor;

    /** 前端傳入的 resource path */
    private String resourcePath;

    /**
     * 操作紀錄
     */
    // private OperationLogEntity operationLog;

    // *********************************************
    // Constructor
    // *********************************************

    /**
     * 建構子
     */
    public AbstractBaseTask() {
        super();
        String clzName = getClass().getSimpleName();
        this.taskId = StringUtils.left(clzName, 8);
        txnId = StringUtils.mid(this.taskId, 3, 5);
        method = StringUtils.right(this.taskId, 3);
    }

    // *********************************************
    // getter & setter
    // *********************************************

    public ClientRequest getRequest() {
        return this.request;
    }

    /**
     * 
     * @param request
     */
    public void setRequest(ClientRequest request) {
        this.request = request;
    }

    /**
     * 
     * @return
     */
    protected ClientResponse getResponse() {
        return this.response;
    }

    /**
     * 
     * @param rs
     */
    public void setResponse(ClientResponse response) {
        this.response = response;
    }

    /**
     * 
     * @return
     */
    public String getTxnId() {
        return txnId;
    }

    /**
     * 
     * @return
     */
    public String getMethod() {
        return method;
    }

    /**
     * 設定語系設定
     * 
     * @param locale
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * 取得現在地區設定
     * 
     * 目前只支援zh_TW, zh_CN, en
     *
     * @return
     */
    protected Locale getLocale() {

        if (locale != null) {
            return locale;
        }

        String localeStr = request.getLocale();
        if (StringUtils.indexOf(localeStr, "CN") > -1) {
            locale = Locale.SIMPLIFIED_CHINESE;
        }
        else if (StringUtils.indexOf(localeStr, "en") > -1) {
            locale = Locale.US;
        }
        else {
            locale = Locale.TRADITIONAL_CHINESE;
        }

        return locale;
    }

    /**
     * 設定底層的request
     * 
     * @param httpServletRequest
     */
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * @return the clientIp
     */
    public String getClientIp() {
        return getRequest().getClientIp();
    }

    /**
     * 請求當下的頁面代碼 ex:NDSQU001010
     * 
     * @return
     */
    public String getFromPage() {
        return getRequest().getFromPage();
    }

    /**
     * 取得底層的request
     * 
     * @return
     */
    public HttpServletRequest getHttpServletRequest() {
        return this.httpServletRequest;
    }

    /**
     * 設定底層的Response
     * 
     * @return
     */
    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    /**
     * 取得底層的Response
     * 
     * @return
     */
    public HttpServletResponse getHttpServletResponse() {
        return this.httpServletResponse;
    }

    /**
     * 取得環境變數
     * 
     * @return
     */
    public Environment getEnvironment() {
        return this.environment;
    }

    /**
     * 設定環境變數
     * 
     * @param environment
     */
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * 取得Context
     * 
     * @return
     */
    public ApplicationContext getContext() {
        return context;
    }

    public String getTxnUid() {
        return txnUid;
    }

    public void setTxnUid(String txnUid) {
        this.txnUid = txnUid;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    // *********************************************
    // abstract method
    // *********************************************
    /**
     * 交易自行實做的資料檢核
     * 
     * @throws ActionException
     * @throws AlertActionException
     */
    public void validate(R rqData) throws ActionException {
        throw new RuntimeException("not implemented");
    }

    /**
     * 撰寫交易邏輯，並回傳response
     * 
     * @param rqData
     * @param rsData
     * @throws ActionException
     * @throws DatabaseException
     * @throws MicroserviceException
     */
    public void handle(R rqData, S rsData) throws ActionException {
        throw new RuntimeException("not implemented");
    }

    public void validateSession(String taskId) throws ActionException {
        throw new RuntimeException("not implemented");
    }

    public void validateDupUid(String taskId) throws ActionException {
        throw new RuntimeException("not implemented");
    }

    /**
     * 是否需留存HTML
     * 
     * @param taskId
     * @param pageId
     * @throws ActionException
     */
    public void savePageHTML(String taskId, String pageId) throws ActionException {
        throw new RuntimeException("not implemented");
    }

    /**
     * 任務牆任務更新
     * 
     * @param taskId
     * @param pageId
     * @param rsData
     * @throws ActionException
     */
    public void updateMissionWall(String taskId, String pageId, S rsData) throws ActionException {
        throw new RuntimeException("not implemented");
    }

    /**
     * 重新塞入資料到session
     * 
     * @throws ActionException
     */
    public void reStore() throws ActionException {

    }

    protected void handleValidateException(ActionException e) throws ActionException {
        throw e;
    }

    // *********************************************
    // private method
    // *********************************************

    /**
     * 做基本的檢查, 在此會檢核 登入狀態, 是否 timeout ... 等
     * 
     * @throws MicroserviceException
     */
    protected final void validate() throws ActionException {
        this.devEnv = checkDevEnv();

        if (!this.isDevEnv()) {

            boolean hasSession = true;
            B2CWebUser<?> user = null;
            // 取得 session
            HttpSession session = this.getHttpServletRequest().getSession(false);
            if (session == null) {
                hasSession = false;
            }

            // Extendible validation
            validateUserState(isRequireLogin(), isRequireACL(), hasSession, user);

            // 檢查交易安控
            checkTxSecurityType();

            // 檢查Session 有效性
            if (hasSession)
                validateSession(this.taskId);

            // 檢查重號戶進入功能檢核處理
            if (hasSession)
                validateDupUid(this.taskId);

            // 走到這裡表示權限都正確了, 接下來處理 timeout 狀態, DB 中的單位為分, 要再 * 60
            int taskTimeout = getTimeout();
            // 設定session expired 時間, 多留一分鐘供前端倒數等處理
            if (!this.isSkipUpdateTimeoutValue()) {
                // 特定底層交易不覆寫 maxInactiveInterval
                this.getHttpServletRequest().getSession().setMaxInactiveInterval((taskTimeout + 2) * 60);
            }
        }
        else {
            // 檢查交易安控
            checkTxSecurityType();

            // 設定session expired 時間, 多留一分鐘供前端倒數等處理
            this.getHttpServletRequest().getSession().setMaxInactiveInterval(360);
        }
    }

    /**
     * 預設是10分鐘
     * 
     * @return
     */
    protected int getTimeout() {
        return 10;
    }

    /** 是否需檢查權限 */
    public abstract boolean isRequireACL();

    /** 檢查交易安控 */
    protected abstract void checkTxSecurityType() throws ActionException;

    /**
     * @param startTime
     * @param endTime
     * @return
     */
    protected boolean isInTimeRange(Date startTime, Date endTime) {
        // start/end 都有設
        boolean result = false;
        if (startTime != null && endTime != null) {
            if (endTime.getTime() > startTime.getTime()) {
                result = (startTime.getTime() <= System.currentTimeMillis()) && (System.currentTimeMillis() <= endTime.getTime());
            }
            else {
                result = (startTime.getTime() <= System.currentTimeMillis()) || (System.currentTimeMillis() <= endTime.getTime());
            }
        }
        // 只設 start
        else if (startTime != null) {
            result = (startTime.getTime() <= System.currentTimeMillis());
        }
        // 只設 end
        else if (endTime != null) {
            result = (endTime.getTime() >= System.currentTimeMillis());
        }
        return result;
    }

    /**
     * 檢核使用者狀態
     * 
     * @param isRequireLogin
     * @param requireACL
     * @param hasSession
     * @param user
     * @throws ActionException
     */
    protected void validateUserState(boolean isRequireLogin, boolean requireACL, boolean hasSession, B2CWebUser<?> user) throws ActionException {
        // 若有 session, 找出是否有 login user (user 有可能從 sub-class 傳入）
        HttpSession currentSession = getHttpServletRequest().getSession(false);
        if (hasSession && user == null) {
            // fortify: Redundant Null Check
            if (currentSession != null) {
                user = GsonHelper.newInstance().fromJson((String) currentSession.getAttribute(getLoginUserSessionKey().name()), B2CWebUser.class);
            }
        }

        if (isRequireLogin) {
            // 需登入，檢查 session
            if (!hasSession) {
                throw ExceptionUtils.getActionException(CommonErrorCode.SESSION_EXPIRED);
            }
            // 需登入, 檢查使用者是否已登入
            // 若 session 中有User, 但 Alive 標示己被移除，則視為未登入
            if (user != null && currentSession != null && currentSession.getAttribute(SessionKey.ALIVE.name()) == null) {
                logger.debug("user had been kicked");
                user = null;
            }
            if (user == null) {
                throw ExceptionUtils.getActionException(CommonErrorCode.USER_NOT_LOGIN);
            }
        }

        // 若該交易 requireACL 為1, 且使用者有登入, 則不論 requireLogin 是否為 1 皆需判斷該交易是否需權限
        if (user != null && requireACL) {
            String userPermission = isUserHasPermission(user);
            if (userPermission != null)
                throw new ActionException(CommonErrorCode.PERMISSION_DENIED, userPermission);
        }
    }

    // default 叫這個，其它交易可複寫
    protected SessionKey getLoginUserSessionKey() {
        return SessionKey.LOGIN_USER;
    }

    /**
     * 驗證使用者是否有權限，拉出來供 override
     * 
     * @param user
     * @return
     */
    protected String isUserHasPermission(B2CWebUser<?> user) throws ActionException {
        return null;
    }

    /**
     * 是否為開發環境
     * 
     * @return
     */
    private boolean checkDevEnv() {
        String[] activeProfiles = getEnvironment().getActiveProfiles();

        String redisHost = getEnvironment().getProperty("aibank.common.cache.redis.host");
        // 本機開發時redis是使用本機，以此判斷是否在開發環境
        if ("localhost".equals(redisHost)) {
            return true;
        }

        for (String profile : activeProfiles) {
            // temporary disable SIT task check
            if (StringUtils.startsWithAny(profile, "local", "default")) {
                if (logger.isDebugEnabled()) {
                    logger.debug("local development, current Active Profiles(): " + profile);
                }
                return false;
            }
        }
        return false;
    }

    /**
     * 是否為開發環境
     * 
     * @return
     */
    public boolean isDevEnv() {
        return this.devEnv;
    }

    /**
     * 是否支援未登入交易
     * 
     * @return
     */
    protected boolean supportGuestOP() {
        return !isRequireLogin();
    }

    /**
     * 設定Response的錯誤
     * 
     * @param e
     */
    protected void setRsError(ActionException ae) {
        ErrorDescription errorDescription = IBUtils.getErrorDescMessage(errorCodeCacheManager, ae, getLocale(), getFromPage());
        response.setRsError(ae.getSystemId(), ae.getStatus().getErrorCode(), errorDescription, ae.getExtraParamMap());
    }

    /**
     * 丟出 ActionException, 由process()產生統一錯誤頁訊息
     *
     * @param code
     * @throws ActionException
     */
    protected void throwActionException(IErrorCode code) throws ActionException {
        throw ExceptionUtils.getActionException(code);
    }

    /**
     * 以 SYSTEM_ID 和 ERROR_CODE 從 DB 讀取錯誤訊息
     * 
     * @param systemId
     * @param errorCode
     * @return
     */
    protected String getErrorDesc(String systemId, String errorCode) {
        return IBUtils.getErrorDesc(errorCodeCacheManager, systemId, errorCode, getLocale(), getFromPage());
    }

    // *********************************************
    // public method
    // *********************************************

    /**
     * 供BaseApplication呼叫的
     */
    @SuppressWarnings("unchecked")
    public void process() {
        processLoggingContext();
        long start = System.currentTimeMillis();
        // default session timeout 5mins
        HttpSession session = this.httpServletRequest.getSession(false);
        if (session != null && session.getMaxInactiveInterval() < 300) {
            session.setMaxInactiveInterval(300);
        }
        try {
            checkloginlog();
            // 共用檢核
            try {
                validate();
            }
            catch (ActionException e) {
                handleValidateException(e);
            }
            // Parse Rq Msg & New Rs Instance
            Gson gson = GsonHelper.newInstance();
            String content = gson.toJson(request.getRqData());

            R rqData = null;
            Class<?> rqClass = null;
            String rqBeanName = this.taskId + this.method + Constants.MSG_RQ_SUFFIX;
            rqClass = context.getType(rqBeanName);
            rqData = (R) gson.fromJson(content, rqClass);

            String rsBeanName = this.taskId + this.method + Constants.MSG_RS_SUFFIX;

            S rsData = (S) context.getType(rsBeanName).getDeclaredConstructor(new Class<?>[0]).newInstance(new Object[0]);

            this.asyncLogProcessor.doRQLog("========== {}{} rqData==========\r\n{}", this.taskId, this.method, rqData);

            // 檢核傳入Rq資料
            logger.debug("========== {}{} validate start==========", this.taskId, this.method);

            validate(rqData);

            logger.debug("========== {}{} validate end============", this.taskId, this.method);

            if (interruptted) {
                throw ExceptionUtils.getActionException(CommonErrorCode.HYSTRIX_TIMEOUT);
            }
            // 若 validate() 驗證失敗，則中止程序，返回頁面
            if (!getErrorFieldMap().isEmpty()) {
                response.setRsError(CommonErrorCode.TASK_FIELD_ERROR.getErrorCode(), CommonErrorCode.TASK_FIELD_ERROR.getMemo());
                response.setErrorField(getErrorFieldMap());
                return;
            }
            // 執行交易邏輯
            logger.debug("========== {}{} handle start==========", this.taskId, this.method);

            handle(rqData, rsData);

            logger.debug("========== {}{} handle end============", this.taskId, this.method);

            savePageHTML(this.taskId, this.method);

            updateMissionWall(this.taskId, this.method, rsData);

            if (interruptted) {
                throw ExceptionUtils.getActionException(CommonErrorCode.HYSTRIX_TIMEOUT);
            }

            this.asyncLogProcessor.doRSLog("========== {}{} rsData==========\r\n{}", this.taskId, this.method, rsData);

            response.setRsData(rsData);
            reStore();
        }
        catch (Exception t) { // 【Fortify：Poor Error Handling: Overly Broad Throws】需要攔截所有例外
            handleException(t);
        }
        finally {
            handleFinally();
        }
        // #4504 Poor Error Handling: Throw Inside Finally
        long end = System.currentTimeMillis();
        long cost = end - start;
        MDC.put("cost", String.valueOf(cost));
        performanceLogger.info(this.getClass().getSimpleName() + ":" + cost);
        MDC.remove("cost");
    }

    protected abstract void handleFinally();

    protected ActionException handleException(Exception e) {
        ActionException ex = null;
        if (e instanceof ServiceException) {
            // 前端導向統一錯誤頁面, 顯示ex:[XX-1234] OOOOOOOOOOOO
            logger.info("BaseTask.process() Catch ServiceException，", e);
            ex = ExceptionUtils.getActionException(e);
            setRsError(ex);
        }
        else if (e instanceof FeignActionException) {
            // 前端導向統一錯誤頁面, 顯示ex:[XX-1234] OOOOOOOOOOOO
            logger.info("BaseTask.process() Catch FeignActionException，", e);
            ex = ExceptionUtils.getActionException(e);
            setRsError(ex);
        }
        else if (e instanceof ActionException) {
            // 前端導向統一錯誤頁面, 顯示ex:[XX-1234] OOOOOOOOOOOO
            logger.info("BaseTask.process() Catch ActionException，", e);
            ex = ExceptionUtils.getActionException(e);
            setRsError(ex);
        }
        else {
            // 前端導向統一錯誤頁面, 未控制錯誤,會顯示[SVC-9999] PIB unknown exception
            logger.error("BaseTask.process() Catch Throwable，", e);
            ex = ExceptionUtils.getActionException(e);
            setRsError(ex);
        }
        return ex;
    }

    protected void processLoggingContext() {
        // nothing
    }

    // *********************************************
    // Public Method - cache 相關
    // *********************************************
    /**
     * 設定Cache
     * 
     * @param key
     * @param data
     */
    public void setCache(String key, Object data) {
        // 因為放進 cache 的 Class 不是每個微服務都有，因此必需轉換成 json string 再存入
        Gson gson = GsonHelper.newInstance();
        String str = gson.toJson(data);
        BoundHashOperations<String, Object, Object> cacheOps = getCacheOps();
        key = ValidateParamUtils.validParam(key);
        str = ValidateParamUtils.validParam(str);
        cacheOps.put(key, str);

        cacheOps.expire(getTimeout(), TimeUnit.MINUTES); // force update expires
    }

    private BoundHashOperations<String, Object, Object> getCacheOps() {
        // 用到 cache 時，一定需要 session id, 所以在此強制建立 session
        if (StringUtils.isBlank(getSessionId())) {
            setSessionId(this.httpServletRequest.getSession(true).getId());
        }
        String sessionId = getSessionId();
        String txnId = getRequest().getTxnIxd();
        String cacheKey = getCacheKey(sessionId, txnId);
        BoundHashOperations<String, Object, Object> boundHashOps = this.cacheRedisTemplate.boundHashOps(cacheKey);
        boundHashOps.expire(getTimeout(), TimeUnit.MINUTES); // force update expires
        return boundHashOps;
    }

    private String getCacheKey(String sessionId, String txnId) {
        String cacheKey = String.format("%s%s:%s", CACHE_PREFIX, sessionId, StringUtils.trimToEmptyEx(txnId));
        return cacheKey;
    }

    // 給 keep alive 專用的
    protected void touchCacheObjects() {
        getCacheOps();
    }

    /**
     * 取得Cache的資料
     * 
     * @param key
     * @return
     */
    public String getCache(String key) {
        return getCache(key, String.class);
    }

    /**
     * 取得Cache的資料
     * 
     * @param key
     * @return
     */
    public <T> T getCache(String kxy, Class<T> clazz) {
        Gson gson = GsonHelper.newInstance();
        String str = (String) getCacheOps().get(kxy);
        return gson.fromJson(str, clazz);
    }

    /**
     * 取得Cache的資料
     *
     * @param key
     * @return
     */
    public <T> T getCache(String key, Type type) {
        Gson gson = GsonHelper.newInstance();
        String str = (String) getCacheOps().get(key);
        return gson.fromJson(str, type);
    }

    /**
     * 取得Cache的資料
     * 
     * @param key
     * @return
     */
    public <T> List<T> getCacheList(String key, Class<T> clazz) {
        Gson gson = GsonHelper.newInstance();
        String str = (String) getCacheOps().get(key);
        Type listType = getType(clazz);
        return gson.fromJson(str, listType);
    }

    /**
     * 取得Cache的資料，若不存在會回傳空陣列
     * 
     * @param key
     * @return
     */
    public <T> List<T> getCacheListOrEmpty(String key, Class<T> clazz) {
        return Optional.ofNullable(getCacheList(key, clazz)).orElse(new ArrayList<>());
    }

    public <K, V> Map<K, V> getCacheMap(String key, Class<K> keyClazz, Class<V> valueClazz) {
        String str = (String) getCacheOps().get(key);
        Type type = getMapType(keyClazz, valueClazz);
        Gson gson = GsonHelper.newInstance();
        return gson.fromJson(str, type);
    }

    /**
     * 設定Session
     * 
     * @param key
     * @param data
     */
    public void setToSession(SessionKey sessionKey, String data) {
        httpServletRequest.getSession().setAttribute(sessionKey.name(), data);
    }

    /**
     * 設定Session
     * 
     * @param key
     * @param data
     */
    public void setToSession(SessionKey sessionKey, Object data) {
        Gson gson = GsonHelper.newInstance();
        String str = gson.toJson(data);
        // Fortify: Trust Boundary Violation
        httpServletRequest.getSession().setAttribute(sessionKey.name(), ValidateParamUtils.validParam(str));
    }

    /**
     * 取得Session的資料
     * 
     * @param key
     * @return
     */
    public String getFromSession(SessionKey sessionKey) {
        return (String) httpServletRequest.getSession().getAttribute(sessionKey.name());
    }

    /**
     * 取得Session的資料
     * 
     * @param key
     * @return
     */
    public <T> T getFromSession(SessionKey sessionKey, Class<T> clazz) {
        if (SessionKey.LOGIN_USER.equals(sessionKey)) {
            throw new IllegalArgumentException("取用 login user 請一律使用 getLoginUser()");
        }
        Gson gson = GsonHelper.newInstance();
        String str = (String) httpServletRequest.getSession().getAttribute(sessionKey.name());
        return gson.fromJson(str, clazz);
    }

    public <T> List<T> getListFromSession(SessionKey sessionKey, Class<T> clazz) {
        Gson gson = GsonHelper.newInstance();
        String str = (String) httpServletRequest.getSession().getAttribute(sessionKey.name());
        Type listType = getType(clazz);
        return gson.fromJson(str, listType);
    }

    /**
     * 移除Session的資料
     * 
     * @param key
     * @return
     */
    public void removeSession(SessionKey sessionKey) {
        httpServletRequest.getSession().removeAttribute(sessionKey.name());
    }

    @SuppressWarnings("serial")
    private static <T> Type getType(Class<T> type) {
        // @format:off
        return new TypeToken<ArrayList<T>>() {
        }.where(new TypeParameter<T>() {
        }, type).getType();
        // @format:on
    }

    private static <K, V> Type getMapType(Class<K> keyType, Class<V> valueType) {
        return getMapTypeToken(keyType, valueType).getType();
    }

    @SuppressWarnings("serial")
    private static <K, V> TypeToken<Map<K, V>> getMapTypeToken(Class<K> keyType, Class<V> valueType) {
        // @format:off
        return new TypeToken<Map<K, V>>() {
        }.where(new TypeParameter<K>() {
        }, keyType).where(new TypeParameter<V>() {
        }, valueType);
        // @format:on
    }

    /**
     * 錯誤訊息
     * 
     * @return
     */
    public Map<String, String> getErrorFieldMap() {
        return errorFieldMap;
    }

    public void setErrorFieldMap(Map<String, String> errorFieldMap) {
        this.errorFieldMap = errorFieldMap;
    }

    public void addErrorFieldMap(String field, String message) {
        this.errorFieldMap.put(field, message);
    }

    public boolean hasErrorField() {
        return (this.errorFieldMap.size() > 0);
    }

    /**
     * 檢查重複登入
     * 
     * @throws ActionException
     */
    protected void checkloginlog() throws ActionException {

    }

    public String getResource() {
        return request.getResource();
    }

    public String getDeviceIxd() {
        return request.getDeviceIxd();
    }

    public String getTrackingIxd() {
        return request.getTrackingIxd();
    }

    public String getTxnIxd() {
        return request.getTxnIxd();
    }

    public String getModel() {
        return request.getModel();
    }

    public String getPlatform() {
        return request.getPlatform();
    }

    /**
     * 取出顯示用的平台內容: ios -> iOS, android -> Android
     * 
     * @return
     */
    public String getPlatformDisplay() {
        return IBUtils.displayPlatform(request.getPlatform());
    }

    public String getVersion() {
        return request.getVersion();
    }

    public String getNetwork() {
        return request.getNetwork();
    }

    public String getAppVer() {
        return request.getAppVer();
    }

    public String getClientTime() {
        return request.getClientTime();
    }

    public String getToken() {
        return request.getToken();
    }

    public String getFromSys() {
        return request.getFromSys();
    }

    /**
     * @return the taskId
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * @param interruptted
     *            the interruptted to set
     */
    public void setInterruptted(boolean interruptted) {
        this.interruptted = interruptted;
    }

    /**
     * @return the interruptted
     */
    public boolean isInterruptted() {
        return interruptted;
    }

    /**
     * @return the channelId
     */
    public LoginSystemType getChannelId() {
        return channelId;
    }

    /**
     * @param channelId
     *            the channelId to set
     */
    public void setChannelId(LoginSystemType channelId) {
        this.channelId = channelId;
    }

    /**
     * @return the skipUpdateTimeoutValue
     */
    public boolean isSkipUpdateTimeoutValue() {
        if (skipUpdateTimeoutValue == null) {
            skipUpdateTimeoutValue = StringUtils.containsIgnoreCase(environment.getProperty("aibank.channel.skipTimeoutSetting", "twaibankc_general_ot004,twaibankc_general_ot050,twaibankm_general_ot004,twaibankm_general_ot005"), getTaskId());
        }
        return skipUpdateTimeoutValue;
    }

    /**
     * extension point for subclass
     * 
     * @return
     */
    protected boolean isRequireLogin() {
        return true;
    }

    public String getSecuritySPEL() {
        return "isAuthenticated()";
    }

    /**
     * @return the resourcePath
     */
    public String getResourcePath() {
        return resourcePath;
    }

    /**
     * @param resourcePath
     *            the resourcePath to set
     */
    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    /**
     * 取得頁面代碼 (依請求路徑解析) ex:NGNQU001010
     * 
     * @return
     */
    public String getPageId() {
        return getTaskId() + getMethod();
    }

    protected void moveCache(String currentSessionId, String newSessionId) {
        String oldCacheKey = getCacheKey(currentSessionId, getRequest().getTxnIxd());
        String newCacheKey = getCacheKey(newSessionId, getRequest().getTxnIxd());
        this.cacheRedisTemplate.rename(oldCacheKey, newCacheKey);
    }

}
