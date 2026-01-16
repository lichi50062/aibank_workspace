package com.tfb.aibank.biz.user.services.email.model;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)EB12020024RQ.java
* 
* <p>Description:Email資料重複判斷</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/22, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Schema(description = "Email資料重複判斷")
public class CheckEmailDuplicationModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Email重號 Y：輸入之Email與他人重複 */
    @Schema(description = "Email重號 Y：輸入之Email與他人重複")
    private String sts1;

    /** 行動電話重號 */
    @Schema(description = "行動電話重號")
    private String sts2;

    /** 數位存款類別 */
    @Schema(description = "數位存款類別")
    private String sts3;

    /** 預留 */
    @Schema(description = "預留")
    private String sts4;

    /** 預留 */
    @Schema(description = "預留")
    private String sts5;

    /** 預留 */
    @Schema(description = "預留")
    private String memo;

    /** 相同資料ID(手機ORemail) */
    @Schema(description = "相同資料ID(手機ORemail)")
    private String id;

    /**
     * @return the sts1
     */
    public String getSts1() {
        return sts1;
    }

    /**
     * @param sts1
     *            the sts1 to set
     */
    public void setSts1(String sts1) {
        this.sts1 = sts1;
    }

    /**
     * @return the sts2
     */
    public String getSts2() {
        return sts2;
    }

    /**
     * @param sts2
     *            the sts2 to set
     */
    public void setSts2(String sts2) {
        this.sts2 = sts2;
    }

    /**
     * @return the sts3
     */
    public String getSts3() {
        return sts3;
    }

    /**
     * @param sts3
     *            the sts3 to set
     */
    public void setSts3(String sts3) {
        this.sts3 = sts3;
    }

    /**
     * @return the sts4
     */
    public String getSts4() {
        return sts4;
    }

    /**
     * @param sts4
     *            the sts4 to set
     */
    public void setSts4(String sts4) {
        this.sts4 = sts4;
    }

    /**
     * @return the sts5
     */
    public String getSts5() {
        return sts5;
    }

    /**
     * @param sts5
     *            the sts5 to set
     */
    public void setSts5(String sts5) {
        this.sts5 = sts5;
    }

    /**
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo
     *            the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

}
