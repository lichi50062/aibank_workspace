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
package com.ibm.tw.ibmb.component.cache;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;

@Aspect
@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CacheAdvisor {

    private static final IBLog logger = IBLog.getLog(CacheAdvisor.class);

    private static final Map<Class<?>, Object> LOCK_MAP = new HashMap<>();

    @Before("execution(* com.ibm.tw.ibmb.component.cache.AbstractCacheManager+.get*(..)) or execution(* com.ibm.tw.ibmb.component.cache.AbstractCacheManager+.is*(..))")
    private void checkCacheState(JoinPoint joinPoint) {
        if (StringUtils.equals(joinPoint.getSignature().getName(), "isFirstLoad")) {
            logger.trace("isFirstLoad called");
            return;
        }
        Object target = joinPoint.getTarget();
        AbstractCacheManager cacheMgr = (AbstractCacheManager) target;
        AbstractLRUCacheManager lruCacheMgr = null;
        boolean isLRUCache = (target instanceof AbstractLRUCacheManager);
        if (isLRUCache) {
            lruCacheMgr = (AbstractLRUCacheManager) cacheMgr;
        }
        Class<?> targetCls = target.getClass();
        Object lock = getLock(targetCls);
        logger.trace("lock object for {} is {}", targetCls, lock);
        // 因為 checkExpired 己改為 110 秒 check 一次，不需考慮 performance overhead, 故移除 Double Locking
        synchronized (lock) {
            boolean firstLoad = (null != lruCacheMgr && isLRUCache) ? lruCacheMgr.isFirstLoad(joinPoint.getArgs()) : cacheMgr.isFirstLoad();
            boolean checkExpired = cacheMgr.checkExpired();
            logger.trace("isFirstLoad is {}, checkExpired is {}", firstLoad, checkExpired);
            if (firstLoad || checkExpired) {
                logger.trace("in sync block: isFirstLoad is {}, checkExpired is {}", firstLoad, checkExpired);
                if (isLRUCache) {
                    if (null != lruCacheMgr && checkExpired) {
                        // 若是 expired, 需先清除全部資料
                        lruCacheMgr.cleanAll();
                    }
                    if (null != lruCacheMgr) {
                        lruCacheMgr.loadCacheByKey(joinPoint.getArgs());
                    }
                }
                else {
                    cacheMgr.loadCache();
                }
                cacheMgr.updateExpireCheck();
            }
        }
    }

    /**
     * 依 target class name 取得 lock
     * 
     * @param targetCls
     * @return
     */
    private Object getLock(Class<?> targetCls) {
        Object lock = LOCK_MAP.get(targetCls);
        if (lock == null) {
            lock = new Object();
            LOCK_MAP.put(targetCls, lock);
        }
        return lock;
    }
}
