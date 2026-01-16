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
package com.tfb.aibank.biz.component.e2ee;

// @formatter:off
/**
 * @(#)E2EEWithTimeValidType.java
 * 
 * <p>Description:時間因子參數合規類型</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/07, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum E2EEWithTimeValidType {

    CHECK_TIME_NORMAL(true, 2, false),

    CHECK_TIME_BUT_PARAMS_INVALID(true, 1, true),

    NOT_CHECK_TIME_NORMAL(true, 1, true),

    NOT_CHECK_TIME_BUT_WITH_TIME(true, 2, true),

    NOT_SETTING_WITH_TIME_IS_NULL(true, 1, true);

    private boolean isValid;
    private Integer isWithTime;
    private Boolean isIgnoreCheckTime;

    private E2EEWithTimeValidType(boolean isValid, Integer isWithTime, Boolean isIgnoreCheckTime) {
        this.isValid = isValid;
        this.isWithTime = isWithTime;
        this.isIgnoreCheckTime = isIgnoreCheckTime;
    }

    public boolean isValid() {
        return isValid;
    }

    public Integer getIsWithTime() {
        return isWithTime;
    }

    public boolean isIgnoreCheckTime() {
        return isIgnoreCheckTime;
    }

}
