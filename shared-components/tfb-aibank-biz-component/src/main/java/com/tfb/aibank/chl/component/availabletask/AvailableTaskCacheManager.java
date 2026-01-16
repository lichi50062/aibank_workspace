package com.tfb.aibank.chl.component.availabletask;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)AvailableTaskCacheManager.java
 * 
 * <p>Description:控管可用TASK清單 CacheManager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/11/20, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class AvailableTaskCacheManager extends AbstractCacheManager {

    @Autowired
    private AvailableTaskResource resource;

    private Map<String, CcAvalibleTaskEntityVo> ccAvailableTask = new LinkedHashMap<>();

    private Map<String, UidDupAvailableTaskEntityVo> uidDupAvailableTask = new LinkedHashMap<>();

    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.AVAILABLE_TASK_CACHE_KEY;
    }

    @Override
    protected void loadCache() {
        AvailableTaskResponse response = resource.getAvailableTask();

        List<CcAvalibleTaskEntityVo> ccList = response.getCcAvalibleTaskEntityVo();
        Map<String, CcAvalibleTaskEntityVo> ccMap = new LinkedHashMap<>();
        for (CcAvalibleTaskEntityVo c : ccList) {
            ccMap.put(c.getTaskId(), c);
        }

        List<UidDupAvailableTaskEntityVo> uuList = response.getUidDupAvailableTaskEntityVo();
        Map<String, UidDupAvailableTaskEntityVo> uuMap = new LinkedHashMap<>();
        for (UidDupAvailableTaskEntityVo u : uuList) {
            uuMap.put(u.getTaskId(), u);
        }

        this.ccAvailableTask = ccMap;
        this.uidDupAvailableTask = uuMap;
    }

    @Override
    protected boolean isFirstLoad() {
        return this.ccAvailableTask.isEmpty() && this.uidDupAvailableTask.isEmpty();
    }

    public CcAvalibleTaskEntityVo getCcAvailableTask(String taskId) {
        if (this.ccAvailableTask.isEmpty()) {
            loadCache();
        }
        return this.ccAvailableTask.get(taskId);
    }

    public UidDupAvailableTaskEntityVo getUidDupAvailableTask(String taskId) {
        if (this.uidDupAvailableTask.isEmpty()) {
            loadCache();
        }
        return this.uidDupAvailableTask.get(taskId);
    }

}
