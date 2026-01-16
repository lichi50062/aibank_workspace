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
 import com.google.gson.JsonObject; /**
 * @(#)ActivityRq.java
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
public class ActivityRq implements CardPromoteBaseRq {

    @Override
    public String getPath() {
        return "/api/activity";
    }

    // 取數據中台API回覆result內容發送
    private JsonObject json;

    public JsonObject getJson() {
        return json;
    }

    public void setJson(JsonObject json) {
        this.json = json;
    }
}
