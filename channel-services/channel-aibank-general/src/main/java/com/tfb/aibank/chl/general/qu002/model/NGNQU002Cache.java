/**
 * 
 */
package com.tfb.aibank.chl.general.qu002.model;

import java.math.BigDecimal;

//@formatter:off
/**
* @(#)NGNQU002Cache.java
* 
* <p>Description:服務據點 cache</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/11/16, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NGNQU002Cache {
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
    
}
