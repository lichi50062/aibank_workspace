package com.tfb.aibank.chl.component.userdatacache.model;

import java.math.BigDecimal;


//@formatter:off
/**
* @(#)FC032155Response.java
* 
* <p>Description:取得多使用者代碼註記</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/26, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class EB552170Response {
    /** 非暫停戶數 */
    private BigDecimal opnCnt;

    /** 暫停戶數 */
    private String ntopnCnt;

    /**
     * @return the opnCnt
     */
    public BigDecimal getOpnCnt() {
        return opnCnt;
    }

    /**
     * @param opnCnt
     *            the opnCnt to set
     */
    public void setOpnCnt(BigDecimal opnCnt) {
        this.opnCnt = opnCnt;
    }

    /**
     * @return the ntopnCnt
     */
    public String getNtopnCnt() {
        return ntopnCnt;
    }

    /**
     * @param ntopnCnt
     *            the ntopnCnt to set
     */
    public void setNtopnCnt(String ntopnCnt) {
        this.ntopnCnt = ntopnCnt;
    }

}
