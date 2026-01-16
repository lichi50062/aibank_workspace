/**
 * 
 */
package com.tfb.aibank.chl.creditcard.resource.dto;

//@formatter:off
/**
* @(#)CEW431RRequest.java
* 
* <p>Description:CEW431R 信用卡OTP Request</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/22, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW431RRequest {

    /** 身分證號 */
    private String scrdno;

    /** 交易類別 */
    private String stxnid;

    /** 交易時間 */
    private String sdttm;

    /** 作業類別 */
    private String stypno;

    /** 交易時間 */
    private String sauthc;

    /**
     * @return the scrdno
     */
    public String getScrdno() {
        return scrdno;
    }

    /**
     * @param scrdno
     *            the scrdno to set
     */
    public void setScrdno(String scrdno) {
        this.scrdno = scrdno;
    }

    /**
     * @return the stxnid
     */
    public String getStxnid() {
        return stxnid;
    }

    /**
     * @param stxnid
     *            the stxnid to set
     */
    public void setStxnid(String stxnid) {
        this.stxnid = stxnid;
    }

    /**
     * @return the sdttm
     */
    public String getSdttm() {
        return sdttm;
    }

    /**
     * @param sdttm
     *            the sdttm to set
     */
    public void setSdttm(String sdttm) {
        this.sdttm = sdttm;
    }

    /**
     * @return the stypno
     */
    public String getStypno() {
        return stypno;
    }

    /**
     * @param stypno
     *            the stypno to set
     */
    public void setStypno(String stypno) {
        this.stypno = stypno;
    }

    /**
     * @return the sauthc
     */
    public String getSauthc() {
        return sauthc;
    }

    /**
     * @param sauthc
     *            the sauthc to set
     */
    public void setSauthc(String sauthc) {
        this.sauthc = sauthc;
    }

}
