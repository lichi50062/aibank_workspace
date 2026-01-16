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
package com.tfb.aibank.biz.component.datacenter.model;

//@formatter:off
/**
 * @(#)PromoStatsRq.java
 *
 * <p>Description:數據中台 API- 取得客戶所屬標籤 - Rq</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/08/07, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 *
 */
//@formatter:on
public class PromoStatsRq implements DataCenterBaseRq {

    /*
     * (non-Javadoc)
     *
     * @see com.tfb.aibank.biz.component.datacenter.model.DataCenterBaseRq#getPath()
     */
    @Override
    public String getPath() {
        return "/promo-stats";
    }

    // 轉換過的客戶身分證號碼
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
