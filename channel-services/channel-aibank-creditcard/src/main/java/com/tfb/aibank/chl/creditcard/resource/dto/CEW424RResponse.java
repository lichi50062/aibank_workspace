package com.tfb.aibank.chl.creditcard.resource.dto;

//@formatter:off
/**
* @(#)CEW424RResponse.java
* 
* <p>Description:CEW424R 異動信用卡暱稱 API下行欄位.</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW424RResponse {
    /** 功能代碼 X(1) */
    private String vofunc;
    /** 身分證字號 X(11) */
    private String voidno;
    /** 卡號 X(16) */
    private String vocdno;
    /** 卡片暱稱 X(14) */
    private String vonknm;

    /**
     * @return the vofunc
     */
    public String getVofunc() {
        return vofunc;
    }

    /**
     * @param vofunc
     *            the vofunc to set
     */
    public void setVofunc(String vofunc) {
        this.vofunc = vofunc;
    }

    /**
     * @return the voidno
     */
    public String getVoidno() {
        return voidno;
    }

    /**
     * @param voidno
     *            the voidno to set
     */
    public void setVoidno(String voidno) {
        this.voidno = voidno;
    }

    /**
     * @return the vocdno
     */
    public String getVocdno() {
        return vocdno;
    }

    /**
     * @param vocdno
     *            the vocdno to set
     */
    public void setVocdno(String vocdno) {
        this.vocdno = vocdno;
    }

    /**
     * @return the vonknm
     */
    public String getVonknm() {
        return vonknm;
    }

    /**
     * @param vonknm
     *            the vonknm to set
     */
    public void setVonknm(String vonknm) {
        this.vonknm = vonknm;
    }

}
