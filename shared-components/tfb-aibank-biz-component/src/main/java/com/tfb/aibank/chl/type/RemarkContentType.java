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
package com.tfb.aibank.chl.type;

import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)RemarkContentType.java
 * 
 * <p>Description:文案主檔類型</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, HankChan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum RemarkContentType implements IEnum {

    MEMO("1", "輔助說明"),

    REMARK("2", "備註"),

    TERMS("3", "條款"),

    MEMO_EXTENSION("4", "輔助說明(2)"),

    UNKNOWN("-9", "UNKNOWN");

    /** 類型 */
    private String type;

    /** 說明* */
    private String memo;

    /**
     * Constructor
     * 
     * @param type
     * @param memo
     */
    RemarkContentType(String type, String memo) {
        this.type = type;
        this.memo = memo;
    }

    /**
     * @return
     */
    public String getType() {
        return this.type;
    }

    @Override
    public String getMemo() {
        return this.memo;
    }

    public static RemarkContentType findByType(String type) {
        for (RemarkContentType enumType : RemarkContentType.values()) {
            if (StringUtils.equals(enumType.getType(), type)) {
                return enumType;
            }
        }
        return UNKNOWN;
    }

    /**
     * 是否為 輔助說明
     * 
     * @return
     */
    public boolean isMemo() {
        return equals(MEMO);
    }

    /**
     * 是否為 備註
     * 
     * @return
     */
    public boolean isRemark() {
        return equals(REMARK);
    }

    /**
     * 是否為 條款
     * 
     * @return
     */
    public boolean isTerms() {
        return equals(TERMS);
    }

}
