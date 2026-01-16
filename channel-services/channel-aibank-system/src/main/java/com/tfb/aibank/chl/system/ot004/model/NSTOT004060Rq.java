package com.tfb.aibank.chl.system.ot004.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NSTOT004060Rq.java
 * 
 * <p>Description: 060 更新 Push Token</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/11, John
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NSTOT004060Rq implements RqData {

    private String pushToken;

    /**
     * @return the pushToken
     */
    public String getPushToken() {
        return pushToken;
    }

    /**
     * @param pushToken
     *            the pushToken to set
     */
    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

}
