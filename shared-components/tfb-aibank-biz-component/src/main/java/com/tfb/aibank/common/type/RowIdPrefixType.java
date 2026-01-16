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

import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)RowIdPrefix.java
 * 
 * <p>Description:IMP Request，上送欄位 ROW_ID 的前綴符號</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum RowIdPrefixType implements IEnum {

    SYSTEM("SYSTEM_", "系統公告"),

    PERSON("PERSON_", "個人化訊息"),

    CUSTOMIZED("CUSTOMIZED_", "智能類訊息"),

    LOGIN("LOGIN_", "再活躍客戶"),

    MOTP("MOTP_", "MOTP通知");

    /** 前綴值 */
    private String prefix;

    /** 狀態說明 */
    private String memo;

    /**
     * Constructor
     *
     * @param statusCode
     * @param memo
     */
    RowIdPrefixType(String prefix, String memo) {
        this.prefix = prefix;
        this.memo = memo;
    }

    public String getPrefix() {
        return prefix;
    }

    /**
     * 取得 memo
     *
     * @return 傳回 memo。
     */
    @Override
    public String getMemo() {
        return memo;
    }

    public static RowIdPrefixType findByPrefix(String rowId) {
        for (RowIdPrefixType type : RowIdPrefixType.values()) {
            if (StringUtils.startsWith(rowId, type.getPrefix())) {
                return type;
            }
        }
        return null;
    }

}