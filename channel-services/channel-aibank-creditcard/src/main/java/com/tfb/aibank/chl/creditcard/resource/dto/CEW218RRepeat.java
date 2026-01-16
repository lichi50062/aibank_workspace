package com.tfb.aibank.chl.creditcard.resource.dto;

import java.math.BigDecimal;
import java.util.Date;

//@formatter:off
/**
* @(#)CEW218RRepeat.java
* 
* <p>Description:CEW218R 附卡:帳單明細查詢 API下行txRepeat.</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW218RRepeat {

    /** 卡號 X(16) */
    private String vncard;
    /** 消費日 X(7) */
    private Date vnpchd;
    /** 入帳日 X(7) */
    private Date vnnccd;
    /** 消費說明 X(40) */
    private String vntxt1;
    /** 台幣消費金額 X(9) */
    private BigDecimal vntwd;
    /** 正負符號 X(1) */
    private String vnsign;
    /** 幣別 X(3) */
    private String vnccur;
    /** 原始地金額 X(10) */
    private BigDecimal vncamt;
    /** 卡片暱稱 X(14) */
    private String vnnknm;

    /**
     * @return the vncard
     */
    public String getVncard() {
        return vncard;
    }

    /**
     * @param vncard
     *            the vncard to set
     */
    public void setVncard(String vncard) {
        this.vncard = vncard;
    }


    /**
     * @return the vnpchd
     */
    public Date getVnpchd() {
        return vnpchd;
    }

    /**
     * @param vnpchd
     *            the vnpchd to set
     */
    public void setVnpchd(Date vnpchd) {
        this.vnpchd = vnpchd;
    }

    /**
     * @return the vnnccd
     */
    public Date getVnnccd() {
        return vnnccd;
    }

    /**
     * @param vnnccd
     *            the vnnccd to set
     */
    public void setVnnccd(Date vnnccd) {
        this.vnnccd = vnnccd;
    }

    /**
     * @return the vntxt1
     */
    public String getVntxt1() {
        return vntxt1;
    }

    /**
     * @param vntxt1
     *            the vntxt1 to set
     */
    public void setVntxt1(String vntxt1) {
        this.vntxt1 = vntxt1;
    }

    /**
     * @return the vntwd
     */
    public BigDecimal getVntwd() {
        return vntwd;
    }

    /**
     * @param vntwd
     *            the vntwd to set
     */
    public void setVntwd(BigDecimal vntwd) {
        this.vntwd = vntwd;
    }

    /**
     * @return the vnsign
     */
    public String getVnsign() {
        return vnsign;
    }

    /**
     * @param vnsign
     *            the vnsign to set
     */
    public void setVnsign(String vnsign) {
        this.vnsign = vnsign;
    }

    /**
     * @return the vnccur
     */
    public String getVnccur() {
        return vnccur;
    }

    /**
     * @param vnccur
     *            the vnccur to set
     */
    public void setVnccur(String vnccur) {
        this.vnccur = vnccur;
    }

    /**
     * @return the vncamt
     */
    public BigDecimal getVncamt() {
        return vncamt;
    }

    /**
     * @param vncamt
     *            the vncamt to set
     */
    public void setVncamt(BigDecimal vncamt) {
        this.vncamt = vncamt;
    }

    /**
     * @return the vnnknm
     */
    public String getVnnknm() {
        return vnnknm;
    }

    /**
     * @param vnnknm
     *            the vnnknm to set
     */
    public void setVnnknm(String vnnknm) {
        this.vnnknm = vnnknm;
    }

}
