package com.tfb.aibank.biz.user.services.usermark.model;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

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
@Schema(description = "EB552170Response")
public class EB552170Response {
    /** 非暫停戶數 */
    @Schema(description = "簡訊OTP申請註記")
    private BigDecimal opnCnt;

    /** 暫停戶數 */
    @Schema(description = "暫停戶數")
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
