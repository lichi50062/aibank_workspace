package com.tfb.aibank.chl.creditcard.ag003.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCAG003010Rs.java
 * 
 * <p>Description:信用卡email設定 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG003010Rs implements RsData {

    /** 原始email */
    private String originEmail;

    /**
     * @return the originEmail
     */
    public String getOriginEmail() {
        return originEmail;
    }

    /**
     * @param originEmail
     *            the originEmail to set
     */
    public void setOriginEmail(String originEmail) {
        this.originEmail = originEmail;
    }

}
