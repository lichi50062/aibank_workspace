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

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)RoleTaskCacheManager.java
 * 
 * <p>Description:角色交易清單 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class RoleTaskCacheManager extends AbstractCacheManager {

    @Autowired
    private TaskResource resource;

    private Map<String, List<String>> roleTaskMap = new LinkedHashMap<>();

    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.ROLE_TASK_CACHE_KEY;
    }

    @Override
    protected void loadCache() {
        this.roleTaskMap = resource.getRoleTaskMapping().getData();
    }

    @Override
    protected boolean isFirstLoad() {
        return this.roleTaskMap.isEmpty();
    }

    public List<String> getTasksForRole(String roleName) {
        return this.roleTaskMap.getOrDefault(roleName, Collections.emptyList());
    }

}
