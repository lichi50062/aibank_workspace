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
package com.tfb.aibank.chl.component.session;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.session.Session;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.google.gson.Gson;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.gson.GsonHelper;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.model.B2CWebUser;
import com.ibm.tw.ibmb.type.SessionKey;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.component.session.LocalSessionManager;

@EnableScheduling
@ConditionalOnProperty(name = "aibank.channel.session-cleaner.enabled", havingValue = "true", matchIfMissing = false)
@Component
public class SessionCleanupTask implements InitializingBean {

    private static IBLog logger = IBLog.getLog(SessionCleanupTask.class);

    private static final String LAST_TOKEN_MAP_REDIS_KEY = "lastTokenKeyMap";
    private static final String CACHE_PREFIX = "cache:";

    private static final String REDIS_SESSION_KEY_PREFIX = "spring:session:sessions:";

    @Autowired
    @Qualifier("stringRedisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    @Qualifier("cacheManagerRedisTemplate")
    private RedisTemplate<String, String> cacheRedisTemplate;

    private RedisIndexedSessionRepository redisIndexedSessionRepository;

    @Autowired
    private LocalSessionManager sessionManager;

    @Autowired
    private UserResource userResource;

    private Method saveSessionMethod;

    private Method loadSessionMethod;

    private void findSessionMethods() {
        // use reflection to prevent type access
        Method[] declaredMethods = RedisIndexedSessionRepository.class.getDeclaredMethods();
        for (Method m : declaredMethods) {
            if ("save".equals(m.getName())) {
                saveSessionMethod = m;
            }
            else if ("getSession".equals(m.getName()) && m.getParameterCount() > 1) {
                loadSessionMethod = m;
                ReflectionUtils.makeAccessible(loadSessionMethod); // 解決 Fortify 掃描出 Access Specifier Manipulation
            }
        }
        this.redisIndexedSessionRepository = new RedisIndexedSessionRepository(createRedisTemplate());
        logger.info("found save session method: {}, getSession method: {}", saveSessionMethod, loadSessionMethod);
    }

    private RedisTemplate<String, Object> createRedisTemplate() {
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(RedisSerializer.java());
        redisTemplate.setHashValueSerializer(RedisSerializer.java());
        redisTemplate.setConnectionFactory(this.redisTemplate.getConnectionFactory());
        redisTemplate.setBeanClassLoader(this.getClass().getClassLoader());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    // execute at Nth second of every minute
    @Scheduled(cron = "${aibank.channel.session-cleaner.cron:${random.int[0,59]} * * * * *}")
    public void checkSessionExpiry() {
        logger.debug("SessionCleanupTask running");
        long start = System.currentTimeMillis();
        // 共用區 pending_session_manager sessionId 掃描
        Iterator<String> sessionIdCommonIterator = sessionManager.commonCacheIterator();
        sessionIdCommonIterator.forEachRemaining(sessionId -> cleanupSessionIfNeeded(sessionId, true));
        // 當前 環境 sessionId 掃描
        Iterator<String> sessionIdIterator = sessionManager.iterator();
        sessionIdIterator.forEachRemaining(sessionId -> cleanupSessionIfNeeded(sessionId, false));
        logger.debug("SessionCleanupTask cost: " + (System.currentTimeMillis() - start));
    }

    private void cleanupSessionIfNeeded(String sessionId, boolean isCommon) {
        String redisSessionExpiresKey = REDIS_SESSION_KEY_PREFIX + sessionId;
        long expires = redisTemplate.getExpire(redisSessionExpiresKey);
        Session session = loadSession(sessionId);

        if (session != null) {
            if (expires < 60) {
                // do cleanup
                logger.debug("clean up session for: " + sessionId);
                if (cleanupSession(sessionId, session)) {
                    if (isCommon) {
                        sessionManager.removeCommonSessionId(sessionId);
                    }
                    else {
                        sessionManager.removeSessionId(sessionId);
                    }
                    logger.debug("session id {} removed from list after successful cleanup", sessionId);
                }
            }
            else {
                logger.trace("session ttl for {} is {}", sessionId, expires);
            }
        }
        else {
            // session 找不到，表示己經在 redis expired, 直接移除
            if (isCommon) {
                sessionManager.removeCommonSessionId(sessionId);
            }
            else {
                sessionManager.removeSessionId(sessionId);
            }
            logger.debug("session id {} removed from list, already expired in redis", sessionId);
        }
    }

    private Session loadSession(String id) {
        try {
            return (Session) loadSessionMethod.invoke(this.redisIndexedSessionRepository, new Object[] { id, true });
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
            logger.error("error loading session from redis", e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private boolean cleanupSession(String id, Session session) {
        // default 會清除 id list
        boolean success = true;
        if (session != null) {
            Gson gson = GsonHelper.newInstance();
            B2CWebUser<String> b2cWebUser = gson.fromJson((String) session.getAttribute(SessionKey.LOGIN_USER.name()), B2CWebUser.class);

            if (b2cWebUser != null) {
                AIBankUser aibankuser = gson.fromJson(b2cWebUser.getUserData(), AIBankUser.class);
                try {
                    ExecuteUserLogoutRequest request = new ExecuteUserLogoutRequest();
                    request.setCustId(aibankuser.getCustIdWithDup());
                    request.setUserId(aibankuser.getUserId());
                    request.setCompanyKind(aibankuser.getCompanyKind());
                    request.setLoginLogPk(aibankuser.getLoginLogPk());
                    userResource.executeUserLogout(request);
                }
                catch (Exception e) { // 【Fortify：Poor Error Handling: Overly Broad Throws】需要攔截所有例外
                    logger.warn("清除使用者登入資訊失敗!", e);
                    if (e instanceof ServiceException) {
                        // 是打 biz 的錯誤，只有這種情況不清除 session id, 待1分鐘後處理
                        success = false;
                    }
                }

            }
            else {
                logger.debug("session 中無 login user, 不需處理");
            }
        }
        if (success) {
            redisTemplate.delete(LAST_TOKEN_MAP_REDIS_KEY + ":" + id);
            cacheRedisTemplate.delete(CACHE_PREFIX + id);
            logger.debug("刪除 last token map");
            // 清除 mapping id 紀錄
            String ecashSessionId = sessionManager.getECashSessionId(id);
            if (StringUtils.isNotBlank(ecashSessionId)) {
                sessionManager.removeECashMapping(ecashSessionId);
            }
            sessionManager.removeAppMapping(id);
        }

        return success;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.findSessionMethods();
    }

}
