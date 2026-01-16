package com.tfb.aibank.chl.creditcard.qu008.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCQU008010Rq.java
 * 
 * <p>Description:信用卡分期管理 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/02, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU008011Rq implements RqData {

    /** 帳單分期 1 單筆分期 2 */
    private String typeFlag;

    /**
     * @return the typeFlag
     */
    public String getTypeFlag() {
        return typeFlag;
    }

    /**
     * @param typeFlag
     *            the typeFlag to set
     */
    public void setTypeFlag(String typeFlag) {
        this.typeFlag = typeFlag;
    }

}
