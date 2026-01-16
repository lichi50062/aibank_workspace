package com.tfb.aibank.biz.component.videoauthapi.model;

// @formatter:off
/**
 * @(#)CheckServiceStatusResponse.java
 * 
 * <p>Description:[視訊 api : checkServiceStatus 上下班時間]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/18, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CheckServiceStatusRequest {

    private String tenantID;

    private String entityTypeID;

    private String siteID;

    public CheckServiceStatusRequest(String tenantID, String entityTypeID, String siteID) {
        this.tenantID = tenantID;
        this.entityTypeID = entityTypeID;
        this.siteID = siteID;
    }

    /**
     * @return the tenantID
     */
    public String getTenantID() {
        return tenantID;
    }

    /**
     * @param tenantID
     *            the tenantID to set
     */
    public void setTenantID(String tenantID) {
        this.tenantID = tenantID;
    }

    /**
     * @return the entityTypeID
     */
    public String getEntityTypeID() {
        return entityTypeID;
    }

    /**
     * @param entityTypeID
     *            the entityTypeID to set
     */
    public void setEntityTypeID(String entityTypeID) {
        this.entityTypeID = entityTypeID;
    }

    /**
     * @return the siteID
     */
    public String getSiteID() {
        return siteID;
    }

    /**
     * @param siteID
     *            the siteID to set
     */
    public void setSiteID(String siteID) {
        this.siteID = siteID;
    }

}
