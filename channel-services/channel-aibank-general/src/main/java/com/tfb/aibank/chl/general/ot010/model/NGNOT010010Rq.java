/**
 * 
 */
package com.tfb.aibank.chl.general.ot010.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NGNOT010010Rq.java
*
* <p>Description: Onboarding-無痛移轉</p>
*
* <p>Modify History:</p>
* <ol>v1, 2024/06/04, John Chang
* </ol>
*/
//@formatter:on
@Component
public class NGNOT010010Rq implements RqData {

    /**
     * 存取權杖
     */
    private String accessToken;

    /**
     * @return the accessToken
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @param accessToken
     *            the accessToken to set
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
