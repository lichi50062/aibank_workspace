package com.tfb.aibank.chl.creditcard.resource.dto;

//@formatter:off
/**
* @(#)CEW336RRepeat.java
* 
* <p>Description:CEW336R 虛擬卡資訊查詢 API下行txRepeat.</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW336RRepeat {

    /** 歸戶ＩＤ X(19) */
    private String vnacid;
    /** 卡號 X(19) */
    private String vncdno;
    /** 效期 X(5) */
    private String vnedym;
    /** CVV2 X(3) */
    private String vncvv2;
    /** 卡類 X(4) */
    private String vncsw;
    /** 開卡註記 X(1) */
    private String vnopc1;

    /**
     * @return the vnacid
     */
    public String getVnacid() {
        return vnacid;
    }

    /**
     * @param vnacid
     *            the vnacid to set
     */
    public void setVnacid(String vnacid) {
        this.vnacid = vnacid;
    }

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
     * @return the vnedym
     */
    public String getVnedym() {
        return vnedym;
    }

    /**
     * @param vnedym
     *            the vnedym to set
     */
    public void setVnedym(String vnedym) {
        this.vnedym = vnedym;
    }

    /**
     * @return the vncvv2
     */
    public String getVncvv2() {
        return vncvv2;
    }

    /**
     * @param vncvv2
     *            the vncvv2 to set
     */
    public void setVncvv2(String vncvv2) {
        this.vncvv2 = vncvv2;
    }

    /**
     * @return the vncsw
     */
    public String getVncsw() {
        return vncsw;
    }

    /**
     * @param vncsw
     *            the vncsw to set
     */
    public void setVncsw(String vncsw) {
        this.vncsw = vncsw;
    }

    /**
     * @return the vnopc1
     */
    public String getVnopc1() {
        return vnopc1;
    }

    /**
     * @param vnopc1
     *            the vnopc1 to set
     */
    public void setVnopc1(String vnopc1) {
        this.vnopc1 = vnopc1;
    }

}
