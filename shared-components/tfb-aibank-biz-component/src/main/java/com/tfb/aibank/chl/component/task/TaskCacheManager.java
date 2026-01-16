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
package com.tfb.aibank.chl.component.task;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)TaskCacheManager.java
 * 
 * <p>Description:交易設定檔 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class TaskCacheManager extends AbstractCacheManager {

    @Autowired
    private TaskResource resource;

    private Map<String, Task> taskMap = new LinkedHashMap<>();

    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.TASK_CACHE_KEY;
    }

    @Override
    protected void loadCache() {
        List<Task> tasks = resource.getAllTasks().getData();
        Map<String, Task> resultMap = new LinkedHashMap<>();
        tasks.forEach(t -> {
            resultMap.put(t.getTaskId(), t);
        });
        this.taskMap = resultMap;
    }

    @Override
    protected boolean isFirstLoad() {
        return this.taskMap.isEmpty();
    }

    public Task getTaskById(String taskId) {
        return this.taskMap.get(taskId);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(this.taskMap.values());
    }
}
