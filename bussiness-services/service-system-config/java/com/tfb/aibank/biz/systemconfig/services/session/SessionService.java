package com.tfb.aibank.biz.systemconfig.services.session;

import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.type.SessionKey;
import com.ibm.tw.ibmb.util.ValidateParamUtils;
import com.tfb.aibank.biz.systemconfig.services.session.model.KickSessionRequest;
import com.tfb.aibank.biz.systemconfig.services.session.model.KickSessionResponse;
import com.tfb.aibank.component.session.LocalSessionManager;

public class SessionService {

    private static final IBLog logger = IBLog.getLog(SessionService.class);

    /**
     * 線上人數 key_prefix
     */
    private static final String REDIS_SESSION_KEY_PREFIX = "spring:session:sessions:";

    /**
     * 登入人數 key
     */
    private static final String LOGIN_SESSION_KEY_PREFIX = "LocalSessionManager";

    /**
     * 是否達上限暫停登入 key_prefix
     */
    private static final String AIBANK_MAX_LOGIN_FORBID = "AIBANK_MAX_LOGIN_FORBID";

    private RedisTemplate<String, String> redisTemplate;

    private LocalSessionManager sessionManager;

    public SessionService(RedisTemplate<String, String> sessionRedisTemplate, LocalSessionManager localSessionManager) {
        this.redisTemplate = sessionRedisTemplate;
        this.sessionManager = localSessionManager;
    }

    public KickSessionResponse kickSession(KickSessionRequest request) {
        KickSessionResponse response = new KickSessionResponse();
        if (!validate(request, response)) {
            return response;
        }

        Long res = 0L;
        String lastSessionId = sessionManager.getAppSessionId(ValidateParamUtils.validParam(request.getSessionID()));
        String targetRedisKey = "spring:session:sessions:" + StringUtils.trimToEmptyEx(lastSessionId);
        String targetAttrName = "sessionAttr:" + SessionKey.LOGIN_USER.name();
        try {
            // 先處理同一個 cluster
            BoundHashOperations<String, Object, Object> boundHashOps = redisTemplate.boundHashOps(targetRedisKey);
            res = boundHashOps.delete(targetAttrName);
            logger.debug("踢除同 redis cluster sessionId: {}, result: {} ", targetRedisKey, res);
            long res2 = boundHashOps.delete("sessionAttr:" + SessionKey.ALIVE.name());
            logger.debug("刪除 alive flag: {} ", res2);
            if (StringUtils.isNotBlank(lastSessionId)) {
                sessionManager.removeAppMapping(lastSessionId);
            }
            sessionManager.removeECashMapping(request.getSessionID());
        }

        catch (Exception e) { // 【Fortify：Poor Error Handling: Overly Broad Throws】需要攔截所有例外
            logger.warn("error deleting session user: " + lastSessionId, e);
            fillErrorMessage(response, e);
        }
        return response;
    }

    private void fillErrorMessage(KickSessionResponse response, Exception e) {
        response.setResult(false);
        response.setErrorCode("S_21_common_9999_00_001");
        response.setErrorDesc("未知的錯誤");
    }

    private boolean validate(KickSessionRequest request, KickSessionResponse res) {
        res.setResult(true);
        res.setErrorCode("0000");
        res.setErrorDesc("成功");
        res.setSystemId("AIBANK_APP");
        if (StringUtils.isBlank(request.getAuthorityCode())) {
            fillErrorMessage(res, "authorityCode");
            return false;
        }
        if (StringUtils.isBlank(request.getCorpIdNum())) {
            fillErrorMessage(res, "corpIdNum");
            return false;
        }
        if (StringUtils.isBlank(request.getSessionID())) {
            fillErrorMessage(res, "sessionID");
            return false;
        }
        if (StringUtils.isBlank(request.getSystemId())) {
            fillErrorMessage(res, "systemId");
            return false;
        }
        return true;
    }

    private void fillErrorMessage(KickSessionResponse res, String message) {
        res.setResult(false);
        res.setErrorCode("S_21_common_9998_00_001");
        res.setErrorDesc(message + "為必填");
    }

    /** 查詢線上人數 */
    public int queryOnlineUserCount() {
        return sessionManager.scanCacheKeyByPattern(REDIS_SESSION_KEY_PREFIX + "*").size();
    }

    /** 查詢線上登入人數 */
    public int queryOnlineLoginUserCount() {
        int commonSessionCount = sessionManager.getSetSizeAsInt(LocalSessionManager.COMMON_SESSION_KEY);
        // 需要把所有以 LOGIN_SESSION_KEY_PREFIX 結尾的 redisKey Set 裡面的資料量加總
        int loginFlagPrefixSessionCount = sessionManager.scanCacheKeyByPattern("*" + LOGIN_SESSION_KEY_PREFIX).stream().map(loginCacheKey -> redisTemplate.opsForSet().members(loginCacheKey).size()).reduce(Integer::sum).orElse(0);
        return commonSessionCount + loginFlagPrefixSessionCount;
    }

    /**
     * 達上限暫停登入設定
     * 
     * @param maxLoginForbid
     * @return
     */
    public boolean setMaxLoginForbid(String maxLoginForbid) {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        valueOps.set(AIBANK_MAX_LOGIN_FORBID, maxLoginForbid);
        return true;
    }

    /**
     * 達上限暫停登入是否開啟
     * 
     * @return
     */
    public boolean isMaxLoginForbidOpen() {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        String maxLoginForbid = valueOps.get(AIBANK_MAX_LOGIN_FORBID);
        return StringUtils.equals(maxLoginForbid, "1");
    }

}
