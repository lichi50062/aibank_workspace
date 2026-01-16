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
package com.tfb.aibank.chl.creditcard.qu008.type;

import com.ibm.tw.commons.type.IEnum;

// @formatter:off
/**
 * @(#)NCCQU008StatusType.java
 * 
 * <p>Description:查詢結果</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/05, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum NCCQU008StatusType implements IEnum {

    /** 查詢失敗 */
    QUERY_ERROR("查詢失敗"),

    /** 查詢無資料 */
    QUERY_NODATA("查詢無資料"),

    /** 查詢成功 */
    QUERY_SUCCESS("查詢成功");

    /** 狀態說明* */
    private String memo;

    /**
     * Constructor
     *
     * @param memo
     */
    NCCQU008StatusType(String memo) {
        this.memo = memo;
    }

    @Override
    public String getMemo() {
        return memo;
    }

}
