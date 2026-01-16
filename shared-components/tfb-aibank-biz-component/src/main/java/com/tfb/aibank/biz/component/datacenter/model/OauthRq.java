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
* @(#)OfferRankingRq.java
* 
* <p>Description:Oauth - Rq</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/12/21, Alex PY Li 
* <ol>
*  <li>初版</li>
* </ol>
* 
*/
//@formatter:on
public class OauthRq implements DataCenterBaseRq {

    /*
     * (non-Javadoc)
     * 
     * @see com.tfb.aibank.biz.component.datacenter.model.DataCenterBaseRq#getPath()
     */
    @Override
    public String getPath() {
        return "/accessToken";
    }

    /**
     * 固定為 client_credentials
     */
    private String grant_type;

    /**
     * 應用程式的客戶端ID
     */
    private String client_id;

    /** 應用程式的客戶端密鑰 */
    private String client_secret;

    /**
     * @return the grant_type
     */
    public String getGrant_type() {
        return grant_type;
    }

    /**
     * @param grant_type
     *            the grant_type to set
     */
    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    /**
     * @return the client_id
     */
    public String getClient_id() {
        return client_id;
    }

    /**
     * @param client_id
     *            the client_id to set
     */
    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    /**
     * @return the client_secrxt
     */
    public String getClient_secret() {
        return client_secret;
    }

    /**
     * @param client_secrxt
     *            the client_secrxt to set
     */
    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

}
