package com.tfb.aibank.chl.creditcard.ag003.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCAG003014Rq.java
 * 
 * <p>Description:信用卡 設定 014 action </p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG003014Rq implements RqData {

    /**
     * 新郵件
     */
    private String newEmail;

    /**
     * @return the newEmail
     */
    public String getNewEmail() {
        return newEmail;
    }

    /**
     * @param newEmail
     *            the newEmail to set
     */
    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

}
