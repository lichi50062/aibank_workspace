package com.tfb.aibank.chl.system.resource.dto;
// @formatter:off
/**
 * @(#)ChkCoolingPeriodResponse.java
 *
 * <p>Description:查詢冷靜期結束日 - Response</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/03/01, Lillian Tsai
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CoolingPeriodResponse {
    /** KYC冷靜期結束日 */
    private String coolingPeriod;

    /**
     * @return the coolingPeriod
     */
    public String getCoolingPeriod() {
        return coolingPeriod;
    }

    /**
     * @param coolingPeriod
     *         the coolingPeriod to set
     */
    public void setCoolingPeriod(String coolingPeriod) {
        this.coolingPeriod = coolingPeriod;
    }
}
