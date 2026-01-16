package com.tfb.aibank.chl.creditcard.resource.dto;

// @formatter:off
/**
 * @(#)CEW332RRepeat.java
 * 
 * <p>Description:歸戶下附卡查詢電文下行 Repeat</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/05, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CEW332RRepeat {

    /**
     * 持卡人ID.
     */
    private String vnHdid;

    /**
     * 卡號.
     */
    private String vnCdno;

    /**
     * 卡片額度(單位：千元)
     */
    private String vnCpma;

    /**
     * 中文姓名.
     */
    private String vnCnam;

    /**
     * Gets vn hdid.
     *
     * @return the vn hdid
     */
    public String getVnHdid() {
        return vnHdid;
    }

    /**
     * Sets vn hdid.
     *
     * @param vnHdid
     *            the vn hdid
     */
    public void setVnHdid(String vnHdid) {
        this.vnHdid = vnHdid;
    }

    /**
     * Gets vn cdno.
     *
     * @return the vn cdno
     */
    public String getVnCdno() {
        return vnCdno;
    }

    /**
     * Sets vn cdno.
     *
     * @param vnCdno
     *            the vn cdno
     */
    public void setVnCdno(String vnCdno) {
        this.vnCdno = vnCdno;
    }

    /**
     * Gets vn cpma.
     *
     * @return the vn cpma
     */
    public String getVnCpma() {
        return vnCpma;
    }

    /**
     * Sets vn cpma.
     *
     * @param vnCpma
     *            the vn cpma
     */
    public void setVnCpma(String vnCpma) {
        this.vnCpma = vnCpma;
    }

    /**
     * Gets vn cnam.
     *
     * @return the vn cnam
     */
    public String getVnCnam() {
        return vnCnam;
    }

    /**
     * Sets vn cnam.
     *
     * @param vnCnam
     *            the vn cnam
     */
    public void setVnCnam(String vnCnam) {
        this.vnCnam = vnCnam;
    }
}
