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
public class CheckServiceStatusResponse extends VideoAuthApiRsBase {

    /**
     * 客服是否服務中
     */
    private boolean officeHour = false;

    /**
     * @return the officeHour
     */
    public boolean isOfficeHour() {
        return officeHour;
    }

    /**
     * @param officeHour
     *            the officeHour to set
     */
    public void setOfficeHour(boolean officeHour) {
        this.officeHour = officeHour;
    }

}
