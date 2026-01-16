package com.tfb.aibank.chl.general.qu002.model;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NGNQU002010Rq.java
 * 
 * <p>Description:服務據點 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/18, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NGNQU002010Rq implements RqData {
    /** 導頁參數 */
    private String GOTO;
    /** 裝置 緯度 */
    private BigDecimal latitude;

    /** 裝置 經度 */
    private BigDecimal longitude;

    /**
     * @return the latitude
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * @param latitude
     *            the latitude to set
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * @param longitude
     *            the longitude to set
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the gOTO
     */
    public String getGOTO() {
        return GOTO;
    }

    /**
     * @param gOTO
     *            the gOTO to set
     */
    public void setGOTO(String gOTO) {
        GOTO = gOTO;
    }

}
