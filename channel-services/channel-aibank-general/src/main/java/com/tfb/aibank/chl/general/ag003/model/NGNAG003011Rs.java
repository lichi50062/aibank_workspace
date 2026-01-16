package com.tfb.aibank.chl.general.ag003.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NGNAG003011Rs.java
 * 
 * <p>Description:大頭貼及暱稱設定 011 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/19, Leiley
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NGNAG003011Rs implements RsData {

    private boolean isSuccess;

    /**
     * @return the isSuccess
     */
    public boolean isSuccess() {
        return isSuccess;
    }

    /**
     * @param isSuccess
     *            the isSuccess to set
     */
    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

}
