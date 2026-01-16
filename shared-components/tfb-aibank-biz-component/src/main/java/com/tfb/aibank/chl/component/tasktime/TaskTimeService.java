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

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;

// @formatter:off
/**
 * @(#)TaskTimeService.java
 * 
 * <p>Description:查看使用交易功能時是否在允許的時間區間</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27,
 * <ol>
 *  <li>初版</li>
 * </ol>
 * 
 * @see TFB-SA-共通業務辦法.docx - 2.5.4. 營業時間
 */
// @formatter:on
@Service
public class TaskTimeService {

    private static final IBLog logger = IBLog.getLog(TaskTimeService.class);

    @Autowired
    private TaskTimeCacheManager taskTimeCacheManager;

    /** 傳入時間是否在傳入交易代號的營業時間內 */
    public boolean isBusinessTime(Date date, String taskId) {
        if (null == date || StringUtils.isEmpty(taskId) || null == taskTimeCacheManager.getTaskTimeById(taskId))
            return false;

        TaskTime tasktime = taskTimeCacheManager.getTaskTimeById(taskId);

        // 有任一值為空也return false
        if (StringUtils.isAnyEmpty(tasktime.getGeneralStartTime(), tasktime.getGeneralEndTime()))
            return false;

        String startTime = tasktime.getGeneralStartTime();
        String endTime = tasktime.getGeneralEndTime();

        // 取得HHmm
        String currentTime = DateFormatUtils.SIMPLE_TIME_FORMAT_HOUR_MINUTE.format(date);
        startTime = StringUtils.replaceAll(startTime, ":", "");
        endTime = StringUtils.replaceAll(endTime, ":", "");

        boolean nowAfterStart = currentTime.compareTo(startTime) > 0;
        boolean nowBeforeEnd = currentTime.compareTo(endTime) < 0;
        logger.debug("nowAfterStart: {} , nowBeforeEnd: {}", nowAfterStart, nowBeforeEnd);
        return nowAfterStart && nowBeforeEnd;
    }

    /** 傳入時間是否在傳入交易代號的營業時間內 */
    public TaskTime getTaskTime(String taskId) {
        if (StringUtils.isEmpty(taskId) || null == taskTimeCacheManager.getTaskTimeById(taskId))
            return null;

        TaskTime tasktime = taskTimeCacheManager.getTaskTimeById(taskId);

        // 有任一值為空也return false
        if (StringUtils.isAnyEmpty(tasktime.getGeneralStartTime(), tasktime.getGeneralEndTime()))
            return null;

        String startTime = tasktime.getGeneralStartTime();
        String endTime = tasktime.getGeneralEndTime();

        logger.debug("startTime: {} , endTime: {}", startTime, endTime);
        return tasktime;
    }
}
