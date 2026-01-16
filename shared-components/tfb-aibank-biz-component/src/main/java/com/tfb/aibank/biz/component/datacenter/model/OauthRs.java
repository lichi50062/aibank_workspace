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
* @(#)OfferRankingRs.java
* 
* <p>Description:Oauth - Rs</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/12/12, Alex PY Li 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class OauthRs implements DataCenterBaseRs {
    /** 授權服務發行的token */
    private String access_token;
    /** Token 多久會過期 */
    private String expires_in;
    /** Token 類型 */
    private String token_type;
    /** 授權範圍 */
    private String scope;
    /** 相關資訊 */
    private String message;
    
    /**
     * @return the access_token
     */
    public String getAccess_token() {
        return access_token;
    }

    /**
     * @param access_token
     *            the access_token to set
     */
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    /**
     * @return the expires_in
     */
    public String getExpires_in() {
        return expires_in;
    }

    /**
     * @param expires_in
     *            the expires_in to set
     */
    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    /**
     * @return the token_type
     */
    public String getToken_type() {
        return token_type;
    }

    /**
     * @param token_type
     *            the token_type to set
     */
    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    /**
     * @return the scope
     */
    public String getScope() {
        return scope;
    }
    
    /**
     * @param scope
     *            the scope to set
     */
    public void setScope(String scope) {
        this.scope = scope;
    }
    
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }


}
