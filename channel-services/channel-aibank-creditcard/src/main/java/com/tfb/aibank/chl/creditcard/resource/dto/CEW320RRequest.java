/**
 * 
 */
package com.tfb.aibank.chl.creditcard.resource.dto;

//@formatter:off
/**
* @(#)CEW320RRequest.java
* 
* <p>Description:CEW320R 預借現金密碼設定 Request</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/22, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW320RRequest {

    /** 卡號 */
    private String vicdno;

    /** 交易類別 */
    private String vitxtp;

    /** CVV2 */
    private String vicvv2;

    /** 持卡人ID */
    private String vicdid;

    /** 預借現金密碼 */
    private String vipwde;

    /**
     * @return the vicdno
     */
    public String getVicdno() {
        return vicdno;
    }

    /**
     * @param vicdno
     *            the vicdno to set
     */
    public void setVicdno(String vicdno) {
        this.vicdno = vicdno;
    }

    /**
     * @return the vitxtp
     */
    public String getVitxtp() {
        return vitxtp;
    }

    /**
     * @param vitxtp
     *            the vitxtp to set
     */
    public void setVitxtp(String vitxtp) {
        this.vitxtp = vitxtp;
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
     * @return the vicdid
     */
    public String getVicdid() {
        return vicdid;
    }

    /**
     * @param vicdid
     *            the vicdid to set
     */
    public void setVicdid(String vicdid) {
        this.vicdid = vicdid;
    }

    /**
     * @return the vipwde
     */
    public String getVipwde() {
        return vipwde;
    }

    /**
     * @param vipwde
     *            the vipwde to set
     */
    public void setVipwde(String vipwde) {
        this.vipwde = vipwde;
    }

}
