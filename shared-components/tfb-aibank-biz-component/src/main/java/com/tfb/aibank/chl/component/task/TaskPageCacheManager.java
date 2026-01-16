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

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)TaskPageCacheManager.java
 * 
 * <p>Description:交易頁面 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/15, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class TaskPageCacheManager extends AbstractCacheManager {

    @Autowired
    private TaskResource resource;

    private Map<String, TaskPage> taskPageMap = new LinkedHashMap<>();

    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.TASK_PAGE_CACHE_KEY;
    }

    @Override
    protected void loadCache() {
        List<TaskPage> tasks = resource.getAllTaskPages().getData();
        Map<String, TaskPage> resultMap = new LinkedHashMap<>();
        tasks.forEach(t -> {
            resultMap.put(t.getTaskPageId(), t);
        });
        this.taskPageMap = resultMap;
    }

    @Override
    protected boolean isFirstLoad() {
        return this.taskPageMap.isEmpty();
    }

    public TaskPage getTaskPagesById(String pageId) {
        return this.taskPageMap.get(pageId);
    }

    public String getTaskPageName(String pageId) {
        TaskPage taskPage = taskPageMap.get(pageId);
        if (taskPage == null || StringUtils.isBlank(taskPage.getTaskPageName())) {
            return StringUtils.EMPTY;
        }
        return taskPage.getTaskPageName();
    }

    public List<TaskPage> getAllTaskPages() {
        return new ArrayList<>(this.taskPageMap.values());
    }
}
