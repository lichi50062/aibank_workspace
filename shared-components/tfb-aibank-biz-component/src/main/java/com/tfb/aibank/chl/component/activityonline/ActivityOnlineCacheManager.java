package com.tfb.aibank.chl.component.activityonline;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.NumberUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;
import com.tfb.aibank.common.code.AIBankConstants;

//@formatter:off
/**
 * @(#)BranchCacheManager.java
 * 
 * <p>Description:活動登錄人數上限 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/12, John Chang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@Component
public class ActivityOnlineCacheManager extends AbstractCacheManager {

    private int onLineCount = 0;

    private long timeStamp = 0;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Autowired
    private CreditCardResource creditCardResource;

    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.ACTIVITY_ONLINE_CACHE_KEY;
    }

    @Override
    protected void loadCache() {
        int interval = 10;
        String param = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "CC_ACTIVITY_CACHEINTERVAL");

        if (StringUtils.isBlank(param) && NumberUtils.isCreatable(param)) {
            interval = ConvertUtils.str2Int(param);
        }
        Date now = new Date();
        long nowTimestamp = now.getTime();
        if ((nowTimestamp - timeStamp) > (interval * 1000)) {
            timeStamp = nowTimestamp;
            onLineCount = creditCardResource.getActivetyOnlineCount();
        }
    }

    @Override
    protected boolean isFirstLoad() {
        return onLineCount == 0;
    }

    public boolean isExceedLimit() {
        int limit = 8000;
        String param = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "CC_ACTIVITY_THRESHOLD");
        if (StringUtils.isNotBlank(param) && NumberUtils.isCreatable(param)) {
            limit = ConvertUtils.str2Int(param);
        }

        loadCache();

        return this.onLineCount >= limit;

    }

    public boolean isExceedLimitForHotActivity() {
        int limit = 8000;
        String param = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "CC_HOT_ACTIVITY_THRESHOLD");
        if (StringUtils.isNotBlank(param) && NumberUtils.isCreatable(param)) {
            limit = ConvertUtils.str2Int(param);
        }

        loadCache();

        return this.onLineCount >= limit;

    }
}