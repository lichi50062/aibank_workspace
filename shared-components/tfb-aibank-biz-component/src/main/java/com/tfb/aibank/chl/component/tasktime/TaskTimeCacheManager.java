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
package com.tfb.aibank.chl.component.tasktime;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)TaskTimeCacheManager.java
 * 
 * <p>Description:依各交易定義可執行即時交易之時間, tasktime Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class TaskTimeCacheManager extends AbstractCacheManager {

    @Autowired
    private TaskTimeResource resource;

    private Map<String, TaskTime> tasktimeMap = new LinkedHashMap<>();

    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.TASK_TIME_CACHE_KEY;
    }

    @Override
    protected void loadCache() {
        List<TaskTime> tasks = resource.getAllTaskTimes();
        Map<String, TaskTime> resultMap = new LinkedHashMap<>();
        tasks.forEach(t -> {
            resultMap.put(t.getTaskId(), t);
        });
        this.tasktimeMap = resultMap;
    }

    @Override
    protected boolean isFirstLoad() {
        return this.tasktimeMap.isEmpty();
    }

    public TaskTime getTaskTimeById(String taskId) {
        return this.tasktimeMap.get(taskId);
    }
}
