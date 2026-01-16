package com.tfb.aibank.chl.creditcard.resource.dto;

import java.util.List;

// @formatter:off
/**
 * @(#)CEW332RResponse.java
 * 
 * <p>Description:歸戶下附卡查詢電文下行 Body</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/05, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CEW332RResponse {

    /** 回應碼 */
    private String rspCode;

    /** 傳送序號 */
    private String spRefId;

    /** 歸戶ID */
    private String vnAcid;

    /** 公司名稱 */
    private String vnCpnm;

    /** 現職職稱 */
    private String vnTitl;

    /** 公司電話區碼 */
    private String vnTlo1;

    /** 公司電話 */
    private String vnTlo2;

    /** 公司電話分機 */
    private String vnTel2;

    /** 電子信箱 */
    private String vnMail;

    /** 資料筆數 */
    private Integer occur;

    /**
     * The Tx repeats.
     */
    private List<CEW332RRepeat> txRepeats;

    /**
     * Gets sp ref id.
     *
     * @return the sp ref id
     */
    public String getSpRefId() {
        return spRefId;
    }

    /**
     * Sets sp ref id.
     *
     * @param spRefId
     *            the sp ref id
     */
    public void setSpRefId(String spRefId) {
        this.spRefId = spRefId;
    }

    /**
     * Gets vn acid.
     *
     * @return the vn acid
     */
    public String getVnAcid() {
        return vnAcid;
    }

    /**
     * Sets vn acid.
     *
     * @param vnAcid
     *            the vn acid
     */
    public void setVnAcid(String vnAcid) {
        this.vnAcid = vnAcid;
    }

    /**
     * Gets vn cpnm.
     *
     * @return the vn cpnm
     */
    public String getVnCpnm() {
        return vnCpnm;
    }

    /**
     * Sets vn cpnm.
     *
     * @param vnCpnm
     *            the vn cpnm
     */
    public void setVnCpnm(String vnCpnm) {
        this.vnCpnm = vnCpnm;
    }

    /**
     * Gets vn titl.
     *
     * @return the vn titl
     */
    public String getVnTitl() {
        return vnTitl;
    }

    /**
     * Sets vn titl.
     *
     * @param vnTitl
     *            the vn titl
     */
    public void setVnTitl(String vnTitl) {
        this.vnTitl = vnTitl;
    }

    /**
     * Gets vn tlo 1.
     *
     * @return the vn tlo 1
     */
    public String getVnTlo1() {
        return vnTlo1;
    }

    /**
     * Sets vn tlo 1.
     *
     * @param vnTlo1
     *            the vn tlo 1
     */
    public void setVnTlo1(String vnTlo1) {
        this.vnTlo1 = vnTlo1;
    }

    /**
     * Gets vn tlo 2.
     *
     * @return the vn tlo 2
     */
    public String getVnTlo2() {
        return vnTlo2;
    }

    /**
     * Sets vn tlo 2.
     *
     * @param vnTlo2
     *            the vn tlo 2
     */
    public void setVnTlo2(String vnTlo2) {
        this.vnTlo2 = vnTlo2;
    }

    /**
     * Gets vn tel 2.
     *
     * @return the vn tel 2
     */
    public String getVnTel2() {
        return vnTel2;
    }

    /**
     * Sets vn tel 2.
     *
     * @param vnTel2
     *            the vn tel 2
     */
    public void setVnTel2(String vnTel2) {
        this.vnTel2 = vnTel2;
    }

    /**
     * Gets vn mail.
     *
     * @return the vn mail
     */
    public String getVnMail() {
        return vnMail;
    }

    /**
     * Sets vn mail.
     *
     * @param vnMail
     *            the vn mail
     */
    public void setVnMail(String vnMail) {
        this.vnMail = vnMail;
    }

    /**
     * Gets occur.
     *
     * @return the occur
     */
    public Integer getOccur() {
        return occur;
    }

    /**
     * Sets occur.
     *
     * @param occur
     *            the occur
     */
    public void setOccur(Integer occur) {
        this.occur = occur;
    }

    /**
     * Gets tx repeats.
     *
     * @return the tx repeats
     */
    public List<CEW332RRepeat> getTxRepeats() {
        return txRepeats;
    }

    /**
     * Sets tx repeats.
     *
     * @param txRepeats
     *            the tx repeats
     */
    public void setTxRepeats(List<CEW332RRepeat> txRepeats) {
        this.txRepeats = txRepeats;
    }

    public String getRspCode() {
        return rspCode;
    }

    public void setRspCode(String rspCode) {
        this.rspCode = rspCode;
    }
}
