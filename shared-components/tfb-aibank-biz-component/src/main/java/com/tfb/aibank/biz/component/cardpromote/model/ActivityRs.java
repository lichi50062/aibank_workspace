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
package com.tfb.aibank.biz.component.cardpromote.model;

// @formatter:off
/**
 * @(#)ActivityRs.java
 *
 * <p>Description:信用卡優惠網</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/08/07, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class ActivityRs implements CardPromoteBaseRs {
    // 結果
    // 1:成功
    // 0:失敗
    private int result;
    // 資料
    private DataBean data;
    // errmsg
    private String errmsg;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
