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
package com.tfb.aibank.chl.general.qu001.model;

import com.ibm.tw.commons.type.IEnum;

//@formatter:off
/**
* @(#)StockCardStateType.java
* 
* <p>Description: 證券牌卡狀態</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/06/04, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum StockCardStateType implements IEnum {

    SUCCESS(1, "成功"),

    NOT_FOUND(2, "無資料, 已銷戶"),

    NOT_ACTIVE(3, "未開啟自動彙整"),

    NOT_AGREE(4, "有任一帳戶(證券、複委託、期貨)但未勾選同意授權"),

    FAILED(-1, "資料撈取失敗");

    private StockCardStateType(Integer state, String memo) {
        this.state = state;
        this.memo = memo;
    }

    private Integer state;

    private String memo;

    public Integer getState() {
        return state;
    }

    @Override
    public String getMemo() {
        return memo;
    }
}
