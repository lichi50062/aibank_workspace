package com.tfb.aibank.chl.creditcard.resource.dto;

//@formatter:off
/**
* @(#)CEW218RRequest.java
* 
* <p>Description:CEW218R 附卡:帳單明細查詢 上行</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW218RRequest {
    /** 卡號 X(16) */
    private String vqcdno;
    /** 查詢月份 X(1) */
    private String vqqrym;

    /**
     * @return the vqcdno
     */
    public String getVqcdno() {
        return vqcdno;
    }

    /**
     * @param vqcdno
     *            the vqcdno to set
     */
    public void setVqcdno(String vqcdno) {
        this.vqcdno = vqcdno;
    }

    /**
     * @return the vqqrym
     */
    public String getVqqrym() {
        return vqqrym;
    }

    /**
     * @param vqqrym
     *            the vqqrym to set
     */
    public void setVqqrym(String vqqrym) {
        this.vqqrym = vqqrym;
    }

}
