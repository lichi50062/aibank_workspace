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
package com.tfb.aibank.common.model;

import java.util.Date;

// @formatter:off
/**
 * @(#)AccountDay.java
 * 
 * <p>Description:帳務日資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/06, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class AccountDay {

    /** 帳務日 */
    private Date accountDay;

    /** 資料鍵值 */
    private Integer dayKey;

    /** 更新時間 */
    private Date updateTime;

    public Date getAccountDay() {
        return accountDay;
    }

    public void setAccountDay(Date accountDay) {
        this.accountDay = accountDay;
    }

    public Integer getDayKey() {
        return dayKey;
    }

    public void setDayKey(Integer dayKey) {
        this.dayKey = dayKey;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
