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
package com.ibm.tw.ibmb.base;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.lang3.RegExUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JndiDataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.gson.GsonHelper;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.ibmb.base.model.ClientRequest;
import com.ibm.tw.ibmb.base.model.ClientResponse;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.ibm.tw.ibmb.component.errorcode.ErrorCodeCacheManager;
import com.ibm.tw.ibmb.component.feign.LoggingFeignRequestInterceptor;
import com.ibm.tw.ibmb.task.AbstractBaseTask;
import com.ibm.tw.ibmb.type.LoginSystemType;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.ibm.tw.ibmb.util.IBUtils.ErrorDescription;
import com.ibm.tw.ibmb.util.MethodSecuiryCheckUtils;
import com.ibm.tw.ibmb.util.ValidateParamUtils;

import feign.Logger;
import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// @formatter:off
@RestController
@ComponentScan(basePackages = { 
        "com.ibm.tw.ibmb.component", 
        "com.ibm.tw.ibmb.chl.component", 
        "com.tfb.aibank.component", 
        "com.tfb.aibank.chl" })
@EnableFeignClients(basePackages = { 
        "com.ibm.tw.ibmb.component", 
        "com.ibm.tw.ibmb.chl.component", 
        "com.tfb.aibank.component", 
        "com.tfb.aibank.chl"
})
@EnableAutoConfiguration(
        exclude = { 
                DataSourceAutoConfiguration.class, 
                DataSourceTransactionManagerAutoConfiguration.class, 
                HibernateJpaAutoConfiguration.class, 
                JndiDataSourceAutoConfiguration.class 
        }
)
@EnableConfigurationProperties
@EnableAsync
//@formatter:on
public abstract class AbstractBaseChannelApplication extends AbstractBaseApplication implements InitializingBean {
    private static final String X_FORWARDED_FOR = "X-Forwarded-For";

    private static final String LAST_TOKEN_MAP_REDIS_KEY = "lastTokenKeyMap";

    private static final IBLog logger = IBLog.getLog(AbstractBaseChannelApplication.class);

    /** 下載 Action 註記 */
    private static final String DOWNLOAD_ACTION_FLAG = "com.ibm.tw.ibmb.base.AbstractBaseChannelApplication.download";

    @Autowired
    private ApplicationContext context;

    @Qualifier("stringRedisTemplate")
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 底層的Request
     */
    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 底層的Response
     */
    @Autowired
    private HttpServletResponse httpServletResponse;

    @Autowired
    private MessageSource messageSource;

    /**
     * Spring整體的環境變數
     */
    @Autowired
    private Environment environment;

    @Value("${aibank.channel.use-hystrix-command:true}")
    private boolean useHystrixCommand = true;

    @Value("${aibank.channel.validate-token:false}")
    private boolean validateDupToken = false;

    private final AtomicBoolean isAtomicScope = new AtomicBoolean(false);

    @Bean
    RequestInterceptor loggingFeignRequestInterceptor() {
        return new LoggingFeignRequestInterceptor();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        I18NUtils.setMessageSource(messageSource);
    }

    @GetMapping(value = "/{txnId}/{method}/{downloadKey}")
    public HttpEntity<?> download(@PathVariable("txnId") String txnId, @PathVariable("method") String method, @PathVariable("downloadKey") String downloadKey, HttpServletRequest thishttpServletRequest) throws ActionException {
        txnId = ValidateParamUtils.validParam(txnId);
        method = ValidateParamUtils.validParam(method);
        downloadKey = ValidateParamUtils.validParam(downloadKey);
        // 【FORTIFY：Access Control: Database】
        // String downloadReqJSON = (String) IBUtils.execute(this.redisTemplate.opsForValue(), "get", downloadKey, Object.class);
        String downloadReqJSON = this.redisTemplate.opsForValue().get(downloadKey);
        if (StringUtils.isBlank(downloadReqJSON)) {
            return ResponseEntity.notFound().build();
        }
        ClientRequest req = GsonHelper.newInstance().fromJson(downloadReqJSON, ClientRequest.class);
        long clientTime = thishttpServletRequest.getDateHeader("date");
        if (clientTime <= 0) {
            clientTime = System.currentTimeMillis();
        }
        req.setClientTime(String.valueOf(clientTime));
        // 從 /{txnId}/{method}/{downloadKey} 入口進入時 添加註記
        thishttpServletRequest.setAttribute(DOWNLOAD_ACTION_FLAG, true);
        return entry(txnId, method, req, thishttpServletRequest);
    }

    /**
     * 所有交易統一的入口 (無連結帳唬）
     *
     * @param txnId
     *            交易代碼
     * @param method
     *            交易編號
     * @param request
     *            POST body 內容
     *
     * @return
     * @throws MicroserviceException
     */
    @RequestMapping(value = "/{txnId}/{method}", method = { RequestMethod.POST, RequestMethod.OPTIONS })
    public HttpEntity<?> entry(@PathVariable("txnId") String txnId, @PathVariable("method") String method, @RequestBody ClientRequest request, HttpServletRequest thishttpServletRequest) throws ActionException {

        LoginSystemType channelIdType = getChannelIdFromHeader(thishttpServletRequest);
        IBUtils.setChannelId(channelIdType.getChannelId());
        ClientResponse response = new ClientResponse();
        try {
            // get zuul-session-id from header and set to request
            HttpSession session = httpServletRequest.getSession(false);
            if (logger.isDebugEnabled()) {
                if (session == null) {
                    logger.debug("session is null, request session id is {} ", httpServletRequest.getHeader("x-auth-token"));
                }
            }
            request.setSessionId(session == null ? "" : session.getId());
            request.setClientIp(getRemoteAddr(httpServletRequest));
            // resource path 為 /ngn/ot001/010
            String resourcePath = request.getResource();
            // 處理 Logging MDC
            setLoggingContext(request, channelIdType);
            // 如果是從 /{txnId}/{method}/{downloadKey} 入口進入不進行檢核
            Object downloadActionFlag = httpServletRequest.getAttribute(DOWNLOAD_ACTION_FLAG);
            if (!Boolean.TRUE.equals(downloadActionFlag)) {
                validateDuplicateTxn(request, response);
            }
            // 在此處理 I18nUtils 的 messageResource
            I18NUtils.setLocale(ConvertUtils.str2Locale(request.getLocale()));
            I18NUtils.setMessageSource(messageSource);
            String targetName = StringUtils.upperCase(StringUtils.removeSlashes(resourcePath)) + "Task";
            if (LoginSystemType.AIBANK_WEB.equals(channelIdType)) {
                targetName = "C" + targetName;
            }
            checkScope(targetName);

            Object taskObj = null;
            try {
                taskObj = context.getBean(targetName);
            }
            catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw ExceptionUtils.getActionException(CommonErrorCode.TASK_DISABLED);
            }

            if (taskObj != null && taskObj instanceof AbstractBaseTask) {
                AbstractBaseTask<?, ?> task = (AbstractBaseTask<?, ?>) taskObj;
                task.getPageId();
                // 0. base parameter
                task.setHttpServletRequest(thishttpServletRequest);
                task.setHttpServletResponse(httpServletResponse);
                task.setEnvironment(environment);
                task.setSessionId(request.getSessionId());
                task.setTxnUid(request.getTxnIxd());
                task.setTxnId(txnId);
                task.setMethod(method);
                task.setChannelId(channelIdType);
                task.setResourcePath(resourcePath);
                // set MDC pageid
                MDC.put(MDCKey.pageid.name(), task.getTaskId() + method);
                // set MDC appVersion from request header
                MDC.put(MDCKey.appVersion.name(), thishttpServletRequest.getHeader("X-App-Version"));

                // 1. set request
                task.setRequest(request);

                task.setResponse(response);

                // 2. check security
                if (!MethodSecuiryCheckUtils.check(task.getSecuritySPEL())) {
                    throw new IllegalAccessException("spel check failed for: " + task.getSecuritySPEL());
                }
                // 3. processTask
                task.process();
                HttpHeaders headerMap = new HttpHeaders();

                formatResponse(response);
                headerMap.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                moveTokenToNewSessionId(MDC.get(MDCKey.sessionid.name()), request.getSessionId());
                checkTokenExists(request, response);
                return new ResponseEntity<>(response, headerMap, HttpStatus.OK);

            }
            else {
                throw new IllegalArgumentException("Invalid Access Task");
            }
        }
        catch (Exception ex) {
            // 會到這裡，應該是沒有控制好的部份
            logger.warn("ERR", ex);
            // 依 ActionException 回傳 error status
            ActionException ae = ExceptionUtils.getActionException(ex);
            // 在這裡轉換錯誤訊息
            ErrorDescription errorDesc = IBUtils.getErrorDescMessage(getErrorCodeCacheManager(), ae, ConvertUtils.str2Locale(request.getLocale()), MDC.get(MDCKey.frompage.name()));
            response.setRsError(ae.getSystemId(), ae.getStatus().getErrorCode(), errorDesc);
            HttpHeaders headerMap = new HttpHeaders();
            moveTokenToNewSessionId(MDC.get(MDCKey.sessionid.name()), request.getSessionId());
            formatResponse(response);
            HttpStatus status = ex instanceof IllegalAccessException ? HttpStatus.UNAUTHORIZED : HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(response, headerMap, status);
        }
        finally {
            // 清除 thread locale
            I18NUtils.resetLocale();
            if (logger.isDebugEnabled()) {
                HttpSession lastSession = httpServletRequest.getSession(false);
                logger.debug("session last access time is {} ", lastSession == null ? null : DateFormatUtils.SIMPLE_ISO_DATETIME_FORMAT.format(lastSession.getLastAccessedTime()));
            }
            // 清除所有 MDC 資訊
            MDC.clear();
        }
    }

    private ErrorCodeCacheManager getErrorCodeCacheManager() {
        return context.getBean(ErrorCodeCacheManager.class);
    }

    private void checkScope(String targetName) throws ActionException {
        if (isCheckScope()) {
            Class<?> taskClass = context.getType(targetName);
            Class<?> origClass = ClassUtils.getUserClass(taskClass);
            Scope scope = origClass.getAnnotation(Scope.class);
            if (scope == null || !StringUtils.equalsIgnoreCase(ConfigurableBeanFactory.SCOPE_PROTOTYPE, StringUtils.defaultIfBlank(scope.scopeName(), scope.value()))) {
                throw ExceptionUtils.getActionException(CommonErrorCode.MISSION_SCOPE_ANNOTIATION);
            }
        }
    }

    /**
     * 因應 Foritfy - Race Condition: Singleton Member Field
     * 
     * @return
     */
    protected boolean isCheckScope() {

        synchronized (this) {
            if (isAtomicScope.get()) {
                String[] activeProfiles = environment.getActiveProfiles();
                for (String profile : activeProfiles) {
                    // check scope on non-prd profiles
                    if (!StringUtils.contains(profile, "prd")) {
                        if (logger.isDebugEnabled()) {
                            logger.debug("checking scope, current Active Profiles(): " + profile);
                        }
                        isAtomicScope.set(true);
                        break;
                    }
                }
            }
            return isAtomicScope.get();
        }

    }

    /**
     * @param request
     * @param response
     * @throws ActionException
     */
    protected void checkTokenExists(ClientRequest request, ClientResponse response) throws ActionException {
        if (StringUtils.equalsIgnoreCase("MFPInit", httpServletRequest.getHeader("X-Requested-With")) && StringUtils.isBlank(response.getToken()) && httpServletRequest.getSession(false) != null) {
            logger.debug("init token again");
            // generate again
            validateDuplicateTxn(request, response);
        }
    }

    private LoginSystemType getChannelIdFromHeader(HttpServletRequest thishttpServletRequest) {
        String channelId = thishttpServletRequest.getHeader("X-Channel-Id");
        LoginSystemType type = LoginSystemType.AIBANK_APP;
        if (StringUtils.isBlank(channelId)) {
            return type;
        }
        try {
            type = LoginSystemType.valueOf(channelId);
        }
        catch (IllegalArgumentException e) {
            logger.warn("error parsing channel id, fallback to default");
        }
        return type;
    }

    protected void moveTokenToNewSessionId(String oldSessionId, String newSessionId) {
        if (!validateDupToken) {
            return;
        }
        // session id 沒變，不需執行
        if (StringUtils.equals(oldSessionId, newSessionId)) {
            return;
        }
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String oldKey = LAST_TOKEN_MAP_REDIS_KEY + ":" + oldSessionId;
        String newKey = LAST_TOKEN_MAP_REDIS_KEY + ":" + newSessionId;
        oldKey = ValidateParamUtils.validParam(oldKey);
        newKey = ValidateParamUtils.validParam(newKey);
        // // 【FORTIFY：Access Control: Database】
        // String oldValue = (String) IBUtils.execute(opsForValue, "get", oldKey, Object.class);
        String oldValue = opsForValue.get(oldKey);
        if (StringUtils.isNotBlank(oldValue)) {
            opsForValue.set(newKey, oldValue, 1, TimeUnit.HOURS);
            // // 【FORTIFY：Access Control: Database】
            // Object[] params = new Object[] { newKey, oldValue, 1, TimeUnit.HOURS };
            // Class<?>[] classes = new Class[] { Object.class, Object.class, long.class, TimeUnit.class };
            // IBUtils.execute(opsForValue, "set", params, classes);
            redisTemplate.delete(oldKey);
        }
        if (logger.isTraceEnabled()) {
            logger.trace("move token from {} to {}", oldSessionId, newSessionId);
        }
    }

    protected void validateDuplicateTxn(ClientRequest request, ClientResponse response) throws ActionException {

        if (!validateDupToken) {
            return;
        }
        String requestURI = this.httpServletRequest.getContextPath() + "/" + this.httpServletRequest.getRequestURI();
        String header = this.httpServletRequest.getHeader("X-Requested-With");
        if ("MFPAsync".equals(header)) {
            if (logger.isTraceEnabled()) {
                logger.trace("skipping check for async request: {}", requestURI);
            }
            return;
        }
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String sessionId = request.getSessionId();
        if (StringUtils.isBlank(sessionId)) {
            if (logger.isTraceEnabled()) {
                logger.trace("skipping check for null session: {}", requestURI);
            }
            return;
        }
        String mapKey = LAST_TOKEN_MAP_REDIS_KEY + ":" + sessionId;
        mapKey = ValidateParamUtils.validParam(mapKey);
        if (!"MFPInit".equals(header)) {
            // 【FORTIFY：Access Control: Database】
            // String lastToken = (String) IBUtils.execute(opsForValue, "get", mapKey, Object.class);
            String lastToken = opsForValue.get(mapKey);
            if (logger.isTraceEnabled()) {
                logger.trace("lastToken for {} is {}", sessionId, lastToken);
            }
            if (StringUtils.isNotBlank(lastToken) && !StringUtils.equals(lastToken, request.getToken())) {
                logger.error("invalid token on {} lastToken: {}, requestToken: {}", requestURI, lastToken, request.getToken());
                throw ExceptionUtils.getActionException(CommonErrorCode.DUPLICATED_TXN_TOKEN);
            }
        }
        // token valid
        String newToken = UUID.randomUUID().toString();
        opsForValue.set(mapKey, newToken, 1, TimeUnit.HOURS);
        // // 【FORTIFY：Access Control: Database】
        // Object[] params = new Object[] { mapKey, newToken, 1, TimeUnit.HOURS };
        // Class<?>[] classes = new Class[] { Object.class, Object.class, long.class, TimeUnit.class };
        // IBUtils.execute(opsForValue, "set", params, classes);

        response.setToken(newToken);
        if (logger.isTraceEnabled()) {
            logger.trace("new token for {} is {}", sessionId, newToken);
        }
    }

    private void setLoggingContext(ClientRequest request, LoginSystemType channelId) {
        MDC.put(MDCKey.trackingid.name(), request.getTrackingIxd());
        MDC.put(MDCKey.sessionid.name(), request.getSessionId());
        MDC.put(MDCKey.clientip.name(), request.getClientIp());
        MDC.put(MDCKey.frompage.name(), request.getFromPage());
        MDC.put(MDCKey.deviceid.name(), request.getDeviceIxd());
        MDC.put(MDCKey.channelid.name(), channelId.getChannelId());
        MDC.put(MDCKey.seed.name(), request.getSeed());
        MDC.put(MDCKey.locale.name(), localeFormat(request.getLocale()));
        MDC.put(MDCKey.languagetag.name(), request.getLocale()); // ex: zh-TW
    }

    /**
     * 格式化回傳值
     *
     * @param response
     */
    private void formatResponse(ClientResponse response) {
        try {
            if (response == null || response.getRsData() == null) {
                return;
            }
            response.setServerTime(System.currentTimeMillis());
            response.setRsData(GsonHelper.bean2Map(response.getRsData()));
        }
        catch (Exception ex) {
            // 明確列出問題所在
            logger.warn("error formatting response", ex);
        }
    }

    private static String getRemoteAddr(HttpServletRequest request) {
        String ip = request.getHeader(X_FORWARDED_FOR);
        if (StringUtils.isBlank(ip)) {
            ip = request.getRemoteAddr();
        }
        else if (StringUtils.contains(ip, ",")) {
            ip = StringUtils.substringBefore(ip, ",");
        }
        return ip;
    }

    /**
     * app locale format ex: zh-TW => zh_TW
     * 
     * @param locale
     * @return
     */
    private String localeFormat(String locale) {
        return RegExUtils.replaceAll(locale, "-", "_");
    }

    /**
     * 控制 Spring 數據綁定行為 - for Fortify - Mass Assignment: Insecure Binder Configuration
     * 
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields(new String[] {});
    }

}
