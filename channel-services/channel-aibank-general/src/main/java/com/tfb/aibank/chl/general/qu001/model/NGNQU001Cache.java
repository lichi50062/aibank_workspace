package com.tfb.aibank.chl.general.qu001.model;

import com.tfb.aibank.chl.general.resource.dto.QuickSearchResponse; /**
 * @(#)NGNQU001CacheData.java
 * 
 * <p>Description: Cache 物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/22, MartyP
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NGNQU001Cache {

    /** 免登速查使用資料 */
    private QuickSearchResponse qsearchData;

    /** DBU/OBU */
    private String userBUType;

    public QuickSearchResponse getQsearchData() {
        return qsearchData;
    }

    public void setQsearchData(QuickSearchResponse qsearchData) {
        this.qsearchData = qsearchData;
    }

    public String getUserBUType() {
        return userBUType;
    }

    public void setUserBUType(String userBUType) {
        this.userBUType = userBUType;
    }
}
