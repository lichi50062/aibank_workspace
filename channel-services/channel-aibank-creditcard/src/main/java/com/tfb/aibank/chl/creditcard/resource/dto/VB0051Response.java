package com.tfb.aibank.chl.creditcard.resource.dto;

import java.math.BigDecimal;
import java.util.Date;

//@formatter:off
/**
* @(#)VB0051Response.java
* 
* <p>Description:VB0051 富御金餘額查詢 API下行欄位.</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class VB0051Response {

    /** 回覆碼 */
    private String rspCode;

    /** 上期結餘正負符號 X(1) */
    private String signFlg1;

    /** 上期結餘 9(9) */
    private BigDecimal lastamt;

    /** 本期使用正負符號 X(1) */
    private String signFlg2;

    /** 本期使用 9(9) */
    private BigDecimal useamt;

    /** 本期增加正負符號 X(1) */
    private String signFlg3;

    /** 本期增加 9(9) */
    private BigDecimal addamt;

    /** 調整金額正負符號 X(1) */
    private String signFlg4;

    /** 調整金額 9(9) */
    private BigDecimal adjamt;

    /** 本期結餘正負符號 X(1) */
    private String signFlg5;

    /** 本期結餘 9(9) */
    private BigDecimal thisamt;

    /** 最近到期點數正負符號 X(1) */
    private String signFlg6;

    /** 最近到期點數 9(9) */
    private BigDecimal expamt;

    /** 到期日 9(7) */
    private Date expdate;

    /**
     * @return the signFlg1
     */
    public String getSignFlg1() {
        return signFlg1;
    }

    /**
     * @param signFlg1
     *            the signFlg1 to set
     */
    public void setSignFlg1(String signFlg1) {
        this.signFlg1 = signFlg1;
    }

    /**
     * @return the lastamt
     */
    public BigDecimal getLastamt() {
        return lastamt;
    }

    /**
     * @param lastamt
     *            the lastamt to set
     */
    public void setLastamt(BigDecimal lastamt) {
        this.lastamt = lastamt;
    }

    /**
     * @return the signFlg2
     */
    public String getSignFlg2() {
        return signFlg2;
    }

    /**
     * @param signFlg2
     *            the signFlg2 to set
     */
    public void setSignFlg2(String signFlg2) {
        this.signFlg2 = signFlg2;
    }

    /**
     * @return the useamt
     */
    public BigDecimal getUseamt() {
        return useamt;
    }

    /**
     * @param useamt
     *            the useamt to set
     */
    public void setUseamt(BigDecimal useamt) {
        this.useamt = useamt;
    }

    /**
     * @return the signFlg3
     */
    public String getSignFlg3() {
        return signFlg3;
    }

    /**
     * @param signFlg3
     *            the signFlg3 to set
     */
    public void setSignFlg3(String signFlg3) {
        this.signFlg3 = signFlg3;
    }

    /**
     * @return the addamt
     */
    public BigDecimal getAddamt() {
        return addamt;
    }

    /**
     * @param addamt
     *            the addamt to set
     */
    public void setAddamt(BigDecimal addamt) {
        this.addamt = addamt;
    }

    /**
     * @return the signFlg4
     */
    public String getSignFlg4() {
        return signFlg4;
    }

    /**
     * @param signFlg4
     *            the signFlg4 to set
     */
    public void setSignFlg4(String signFlg4) {
        this.signFlg4 = signFlg4;
    }

    /**
     * @return the adjamt
     */
    public BigDecimal getAdjamt() {
        return adjamt;
    }

    /**
     * @param adjamt
     *            the adjamt to set
     */
    public void setAdjamt(BigDecimal adjamt) {
        this.adjamt = adjamt;
    }

    /**
     * @return the signFlg5
     */
    public String getSignFlg5() {
        return signFlg5;
    }

    /**
     * @param signFlg5
     *            the signFlg5 to set
     */
    public void setSignFlg5(String signFlg5) {
        this.signFlg5 = signFlg5;
    }

    /**
     * @return the thisamt
     */
    public BigDecimal getThisamt() {
        return thisamt;
    }

    /**
     * @param thisamt
     *            the thisamt to set
     */
    public void setThisamt(BigDecimal thisamt) {
        this.thisamt = thisamt;
    }

    /**
     * @return the signFlg6
     */
    public String getSignFlg6() {
        return signFlg6;
    }

    /**
     * @param signFlg6
     *            the signFlg6 to set
     */
    public void setSignFlg6(String signFlg6) {
        this.signFlg6 = signFlg6;
    }

    /**
     * @return the expamt
     */
    public BigDecimal getExpamt() {
        return expamt;
    }

    /**
     * @param expamt
     *            the expamt to set
     */
    public void setExpamt(BigDecimal expamt) {
        this.expamt = expamt;
    }

    /**
     * @return the expdate
     */
    public Date getExpdate() {
        return expdate;
    }

    /**
     * @param expdate
     *            the expdate to set
     */
    public void setExpdate(Date expdate) {
        this.expdate = expdate;
    }

    public String getRspCode() {
        return rspCode;
    }

    public void setRspCode(String rspCode) {
        this.rspCode = rspCode;
    }

}
