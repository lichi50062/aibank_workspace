package com.tfb.aibank.chl.component.userdatacache.model;

import java.math.BigDecimal;
import java.util.Date;

// @formatter:off
/**
 * @(#)SDACTQ12TxRepeat.java
 *
 * <p>Description:SDACTQ12 信託資產_SI產品約當臺幣</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/07/09, Andy Kuo	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class SDACTQ12TxRepeat {

    /** 產品名稱 */
    private String prdcNm;

    /** 產品代碼 */
    private String sdPrd;

    /** 幣別 */
    private String sdCcy;

    /** 簽約金額 */
    private BigDecimal ivAmt2;

    /** 起息日 */
    private Date depstr;

    /** 參考報價 */
    private BigDecimal sdAmt3;

    /** 報價日期 */
    private Date sddte;

    /** 參考台幣現值 */
    private BigDecimal amount;

    /** 收件編號 */
    private String ivrNo;

    /**
     * @return the prdcNm
     */
    public String getPrdcNm() {
        return prdcNm;
    }

    /**
     * @param prdcNm
     *            the prdcNm to set
     */
    public void setPrdcNm(String prdcNm) {
        this.prdcNm = prdcNm;
    }

    /**
     * @return the sdPrd
     */
    public String getSdPrd() {
        return sdPrd;
    }

    /**
     * @param sdPrd
     *            the sdPrd to set
     */
    public void setSdPrd(String sdPrd) {
        this.sdPrd = sdPrd;
    }

    /**
     * @return the sdCcy
     */
    public String getSdCcy() {
        return sdCcy;
    }

    /**
     * @param sdCcy
     *            the sdCcy to set
     */
    public void setSdCcy(String sdCcy) {
        this.sdCcy = sdCcy;
    }

    /**
     * @return the ivAmt2
     */
    public BigDecimal getIvAmt2() {
        return ivAmt2;
    }

    /**
     * @param ivAmt2
     *            the ivAmt2 to set
     */
    public void setIvAmt2(BigDecimal ivAmt2) {
        this.ivAmt2 = ivAmt2;
    }

    /**
     * @return the depstr
     */
    public Date getDepstr() {
        return depstr;
    }

    /**
     * @param depstr
     *            the depstr to set
     */
    public void setDepstr(Date depstr) {
        this.depstr = depstr;
    }

    /**
     * @return the sdAmt3
     */
    public BigDecimal getSdAmt3() {
        return sdAmt3;
    }

    /**
     * @param sdAmt3
     *            the sdAmt3 to set
     */
    public void setSdAmt3(BigDecimal sdAmt3) {
        this.sdAmt3 = sdAmt3;
    }

    /**
     * @return the sddte
     */
    public Date getSddte() {
        return sddte;
    }

    /**
     * @param sddte
     *            the sddte to set
     */
    public void setSddte(Date sddte) {
        this.sddte = sddte;
    }

    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount
     *            the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return the ivrNo
     */
    public String getIvrNo() {
        return ivrNo;
    }

    /**
     * @param ivrNo
     *            the ivrNo to set
     */
    public void setIvrNo(String ivrNo) {
        this.ivrNo = ivrNo;
    }

}
