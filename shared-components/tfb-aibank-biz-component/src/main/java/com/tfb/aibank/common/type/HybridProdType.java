/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.common.type;

import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)HybridProdTxType.java
 * 
 * <p>Description:組合式商品類型</p>
 * <p>庫存(0001)、申購在途(1001)、贖回在途(1002)</p>
 * 
 * <p>Modify History:</p>
* v1.0, 2024/05/03, leiley 
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum HybridProdType implements IEnum {

    /** SPWEBINQ台外幣組合式商品 */
    SPWEBINQ("SPWEBINQ", "結構式投資(SI)"),

    /** SPWEBQ2組合式商品DCD總覽 */
    SPWEBQ2("SPWEBQ2", "SPWEBQ2組合式商品DCD總覽"),

    /** BKDCD001非保本外幣組合式商品DCD總覽 */
    BKDCD001("BKDCD001", "BKDCD001非保本外幣組合式商品DCD總覽"),

    UNKNOWN(UNKNOWN_STR_CODE, "未知");

    private String code;

    private String memo;

    /**
     * Constructor
     *
     * @param code
     * @param memo
     */
    HybridProdType(String code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public String getCode() {
        return code;
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

    public static HybridProdType find(String code) {
        for (HybridProdType type : HybridProdType.values()) {
            if (StringUtils.equals(type.getCode(), code)) {
                return type;
            }
        }
        return null;
    }

    /** SPWEBINQ */
    public boolean isSPWEBINQ() {
        return equals(SPWEBINQ);
    }

    /** SPWEBQ2 */
    public boolean isSPWEBQ2() {
        return equals(SPWEBQ2);
    }

    /** BKDCD001 */
    public boolean isBKDCD001() {
        return equals(BKDCD001);
    }
}