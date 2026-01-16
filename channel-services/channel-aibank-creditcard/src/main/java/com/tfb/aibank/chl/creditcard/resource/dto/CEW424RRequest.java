package com.tfb.aibank.chl.creditcard.resource.dto;

//@formatter:off
/**
* @(#)CEW424RRequest.java
* 
* <p>Description:CEW424R 異動信用卡暱稱 上行</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW424RRequest {
    /** 功能代碼 X(1) */
    private String vifunc;
    /** 身分證字號 X(11) */
    private String viidno;
    /** 卡號 X(16) */
    private String vicdno;
    /** 卡片暱稱 X(14) */
    private String vinknm;

    /**
     * @return the vifunc
     */
    public String getVifunc() {
        return vifunc;
    }

    /**
     * @param vifunc
     *            the vifunc to set
     */
    public void setVifunc(String vifunc) {
        this.vifunc = vifunc;
    }

    /**
     * @return the viidno
     */
    public String getViidno() {
        return viidno;
    }

    /**
     * @param viidno
     *            the viidno to set
     */
    public void setViidno(String viidno) {
        this.viidno = viidno;
    }

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
     * @return the vinknm
     */
    public String getVinknm() {
        return vinknm;
    }

    /**
     * @param vinknm
     *            the vinknm to set
     */
    public void setVinknm(String vinknm) {
        this.vinknm = vinknm;
    }

}
