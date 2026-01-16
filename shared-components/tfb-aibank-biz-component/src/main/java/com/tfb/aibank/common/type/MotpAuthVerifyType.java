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
package com.tfb.aibank.common.type;

import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)MotpAuthVerifyType.java
 * 
 * <p>Description:使用MOTP驗證前檢查狀態類型</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/15, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum MotpAuthVerifyType {

    /**
     * 檢查成功
     */
    SUCCESS("1", Boolean.TRUE),

    /**
     * MOTP服務暫停
     */
    MOTP_SERVICE_UNAVALIBLE("2", Boolean.FALSE),

    /**
     * 尚未綁定裝置
     */
    NO_BINDING_DEVICE("3", Boolean.FALSE),

    /**
     * MOTP綁定狀態異常
     */
    BINDING_STATUS_ABNORMAL("4", Boolean.FALSE),

    /**
     * 狀態異常
     */
    UNKNOWN("99", Boolean.FALSE),

    ;

    /**
     * 代碼
     */
    private String code;

    /**
     * 是否可執行MOTP驗證
     */
    private boolean isValid;

    MotpAuthVerifyType(String code, boolean isValid) {
        this.code = code;
        this.isValid = isValid;
    }

    public static MotpAuthVerifyType find(String code) {
        for (MotpAuthVerifyType type : MotpAuthVerifyType.values()) {
            if (StringUtils.equals(type.getCode(), code)) {
                return type;
            }
        }
        return MotpAuthVerifyType.UNKNOWN;
    }

    public boolean isValid() {
        return isValid;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

}
