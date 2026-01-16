/**
 * 
 */
package com.tfb.aibank.chl.creditcard.resource.dto;

import java.math.BigDecimal;

//@formatter:off
/**
* @(#)CEW311RResponse.java
* 
* <p>Description: CEW311R 預借現金資料檢核 Response</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/22, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW311RResponse {

    /** 卡號 */
    private String vncdno;

    /** 效期 */
    private String venedym;

    /** CVV2 */
    private String vicvv2;

    /** 生日 */
    private String vnbird;

    /** 交易金額 */
    private BigDecimal vntxam;

    /** 預借現金手續費 */
    private BigDecimal vnfee;

    /** 手機號碼 */
    private String vnphno;

    /** E-mail */
    private String emailAddress;

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
    public BigDecimal getVntxam() {
        return vntxam;
    }

    /**
     * @param vntxam
     *            the vntxam to set
     */
    public void setVntxam(BigDecimal vntxam) {
        this.vntxam = vntxam;
    }

    /**
     * @return the vnfee
     */
    public BigDecimal getVnfee() {
        return vnfee;
    }

    /**
     * @param vnfee
     *            the vnfee to set
     */
    public void setVnfee(BigDecimal vnfee) {
        this.vnfee = vnfee;
    }

    /**
     * @return the vnphno
     */
    public String getVnphno() {
        return vnphno;
    }

    /**
     * @param vnphno
     *            the vnphno to set
     */
    public void setVnphno(String vnphno) {
        this.vnphno = vnphno;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress
     *            the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
