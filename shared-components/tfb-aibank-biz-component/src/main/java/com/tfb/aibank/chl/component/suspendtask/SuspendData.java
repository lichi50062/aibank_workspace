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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// @formatter:off
/**
 * @(#)SuspendRange.java
 * 
 * <p>Description:暫停資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/12/22, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class SuspendData {

    public SuspendData() {
        // default constructor
    }

    /** 資料主鍵 */
    private Integer masterKey;

    /** 暫停開始時間 */
    private Date startTime;

    /** 暫停結束時間 */
    private Date endTime;

    /** 暫停訊息 key:Locale */
    private Map<String, String> messages = new HashMap<>();

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getMasterKey() {
        return masterKey;
    }

    public void setMasterKey(Integer masterKey) {
        this.masterKey = masterKey;
    }

    public Map<String, String> getMessages() {
        return messages;
    }

    public void setMessages(Map<String, String> messages) {
        this.messages = messages;
    }

}
