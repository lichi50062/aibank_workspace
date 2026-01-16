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
package com.tfb.aibank.chl.component.suspendtask;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)SuspendTaskCacheManager.java
 * 
 * <p>Description:暫停交易設定檔 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/12/25, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class SuspendTaskCacheManager extends AbstractCacheManager {

    @Autowired
    private SuspendTaskResource resource;

    private Map<String, SuspendTask> dataMap = null;

    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.SUSPEND_TASK_CACHE_KEY;
    }

    @Override
    protected void loadCache() {
        List<SuspendTask> dataList = resource.getSuspendTaskList().getData();
        if (CollectionUtils.isNotEmpty(dataList)) {
            Map<String, SuspendTask> map = new HashMap<>();
            dataList.forEach(data -> {
                map.put(data.getTaskId(), data);
            });
            this.dataMap = map;
        }
        else {
            this.dataMap = Collections.emptyMap();
        }
    }

    @Override
    protected boolean isFirstLoad() {
        return this.dataMap == null;
    }

    /**
     * <p>
     * 讀取「暫停交易」資訊
     * </p>
     * <p>
     * 檢查交易代號在現在系統時間，是否為「暫停交易」狀態
     * </p>
     * 
     * @param taskId
     * @return null:表示不是暫停交易
     */
    public SuspendTask getSuspendTask(String taskId) {
        SuspendTask loadSuspendTask = this.dataMap.get(taskId);
        if (loadSuspendTask == null) {
            return null;
        }
        SuspendTask suspendTask = new SuspendTask();
        suspendTask.setTaskId(taskId);

        SuspendData suspendData = getSuspendData(loadSuspendTask);
        if (suspendData == null) {
            return null;
        }
        suspendTask.setSuspendData(suspendData);

        return suspendTask;
    }

    private SuspendData getSuspendData(SuspendTask suspendTask) {
        if (suspendTask == null || CollectionUtils.isEmpty(suspendTask.getSuspendDataList())) {
            return null;
        }
        for (SuspendData suspendData : suspendTask.getSuspendDataList()) {
            if (DateUtils.between(new Date(), suspendData.getStartTime(), suspendData.getEndTime())) {
                return suspendData;
            }
        }
        return null;
    }

    /**
     * 檢查此交易是否為暫停交易
     * 
     * @param taskId
     * @return true:是暫停交易
     */
    public boolean checkSuspendTask(String taskId) {
        SuspendTask suspendTask = this.dataMap.get(taskId);
        if (suspendTask == null) {
            return false;
        }
        return checkSuspendTask(suspendTask);
    }

    private boolean checkSuspendTask(SuspendTask suspendTask) {
        if (suspendTask == null || CollectionUtils.isEmpty(suspendTask.getSuspendDataList())) {
            return false;
        }
        boolean isSuspend = false;
        for (SuspendData suspendRange : suspendTask.getSuspendDataList()) {
            isSuspend = DateUtils.between(new Date(), suspendRange.getStartTime(), suspendRange.getEndTime());
            if (isSuspend) {
                break;
            }
        }
        return isSuspend;
    }

    /**
     * 讀取暫停交易訊息
     * 
     * @param taskId
     * @param userLocale
     * @return
     */
    public String getSuspendTaskMessage(String taskId, Locale userLocale) {
        String message = StringUtils.EMPTY;
        SuspendTask suspendTask = getSuspendTask(taskId);
        if (suspendTask != null && suspendTask.getSuspendData() != null && MapUtils.isNotEmpty(suspendTask.getSuspendData().getMessages())) {
            message = suspendTask.getSuspendData().getMessages().get(userLocale.toString());
        }
        return message;
    }
}
