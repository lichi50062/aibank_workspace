package com.tfb.aibank.chl.creditcard.ag010.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCAG010010Rs.java
 * 
 * <p>Description:變更密碼(信用卡) 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/22, Aaron
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:off
@Component
public class NCCAG010010Rs implements RsData {
    /**使用者代號, 共前端檢核*/
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
