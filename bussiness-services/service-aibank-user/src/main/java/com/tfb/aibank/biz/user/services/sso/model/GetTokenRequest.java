/**
 * 
 */
package com.tfb.aibank.biz.user.services.sso.model;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)GetTokenRequest.java 
* 
* <p>Description:SSO/快速身份認證</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 2024/01/17, JohnChang
*  <li>初版</li6
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
public class GetTokenRequest {

    /** 權杖加密 */
    @Schema(description = "權杖加密")
    private String signature;

    /** 目標通路 */
    @Schema(description = "目標通路")
    private String platformId;

    /**
     * @return the signature
     */
    public String getSignature() {
        return signature;
    }

    /**
     * @param signature
     *            the signature to set
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * @return the platformId
     */
    public String getPlatformId() {
        return platformId;
    }

    /**
     * @param platformId
     *            the platformId to set
     */
    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

}
