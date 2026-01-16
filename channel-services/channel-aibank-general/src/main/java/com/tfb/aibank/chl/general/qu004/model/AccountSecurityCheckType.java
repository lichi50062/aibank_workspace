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
package com.tfb.aibank.chl.general.qu004.model;

import com.ibm.tw.commons.type.IEnum;

// @formatter:off
/**
 * @(#)AccountSecurityCheckType.java
 * 
 * <p>Description:帳戶安全牌卡 檢核項目</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/23, MartyP
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum AccountSecurityCheckType implements IEnum {

    PXD_CHANGE("定期變更密碼"),
    SET_EMAIL("設定Email"),
    SET_PUSHOTP("推播動態密碼"),
    FIDO("FIDO快速登入"),
    DEVICE_BIND("裝置綁定"),
    TWO_STEP("雙重驗證登入"),
    APP_VERSION("APP版本"),
    DEVICE_OS("手機作業系統");

    private String memo;

    AccountSecurityCheckType() {
    }

    AccountSecurityCheckType(String memo) {
        this.memo = memo;
    }

    @Override
    public String getMemo() {
        return this.memo;
    }

}
