/**
 * 
 */
package com.tfb.aibank.chl.creditcard.resource.dto;

//@formatter:off
/**
* @(#)CEW311RRequest.java
* 
* <p>Description:CEW311R 預借現金資料檢核 Request</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/22, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW311RRequest {

    /** 卡號 */
    private String vncdno;

    /** 效期 */
    private String venedym;

    /** CVV2 */
    private String vicvv2;

    /** 生日 */
    private String vnbird;

    /** 交易金額 */
    private String vntxam;

    /**
     * @return the vncdno
     */
    public String getVncdno() {
        return vncdno;
    }

    /**
     * @param vncdno
     *            the vncdno to set
     */
    public void setVncdno(String vncdno) {
        this.vncdno = vncdno;
    }

    /**
     * @return the venedym
     */
    public String getVenedym() {
        return venedym;
    }

    /**
     * @param venedym
     *            the venedym to set
     */
    public void setVenedym(String venedym) {
        this.venedym = venedym;
    }

    /**
     * @return the vicvv2
     */
    public String getVicvv2() {
        return vicvv2;
    }

    /**
     * @param vicvv2
     *            the vicvv2 to set
     */
    public void setVicvv2(String vicvv2) {
        this.vicvv2 = vicvv2;
    }

    /**
     * @return the vnbird
     */
    public String getVnbird() {
        return vnbird;
    }

    /**
     * @param vnbird
     *            the vnbird to set
     */
    public void setVnbird(String vnbird) {
        this.vnbird = vnbird;
    }

    /**
     * @return the vntxam
     */
    public String getVntxam() {
        return vntxam;
    }

    /**
     * @param vntxam
     *            the vntxam to set
     */
    public void setVntxam(String vntxam) {
        this.vntxam = vntxam;
    }

}
