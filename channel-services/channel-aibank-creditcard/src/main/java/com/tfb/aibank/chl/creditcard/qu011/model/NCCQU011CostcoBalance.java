/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.creditcard.qu011.model;

import java.math.BigDecimal;
import java.util.Date;

import com.ibm.tw.ibmb.annotations.FormatDate;
import com.tfb.aibank.chl.creditcard.resource.dto.VB0051Response;

// @formatter:off
/**
 * @(#)NCCQU011CostcoBalance.java
 * 
 * <p>Description:好多金餘額</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCQU011CostcoBalance {

    public NCCQU011CostcoBalance() {
        // default constructor
    }

    public NCCQU011CostcoBalance(VB0051Response res) {
        this.rspCode = res.getRspCode();
        this.signFlg1 = res.getSignFlg1();
        this.lastamt = res.getLastamt();
        this.signFlg2 = res.getSignFlg2();
        this.useamt = res.getUseamt();
        this.signFlg3 = res.getSignFlg3();
        this.addamt = res.getAddamt();
        this.signFlg4 = res.getSignFlg4();
        this.adjamt = res.getAdjamt();
        this.signFlg5 = res.getSignFlg5();
        this.thisamt = res.getThisamt();
        this.signFlg6 = res.getSignFlg6();
        this.expamt = res.getExpamt();
        this.expdate = res.getExpdate();
    }

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
    @FormatDate(pattern = "yyyy/MM/dd")
    private Date expdate;

    /** 期數 ex:yyyy/MM */
    private String period;

    public String getSignFlg1() {
        return signFlg1;
    }

    public void setSignFlg1(String signFlg1) {
        this.signFlg1 = signFlg1;
    }

    public BigDecimal getLastamt() {
        return lastamt;
    }

    public void setLastamt(BigDecimal lastamt) {
        this.lastamt = lastamt;
    }

    public String getSignFlg2() {
        return signFlg2;
    }

    public void setSignFlg2(String signFlg2) {
        this.signFlg2 = signFlg2;
    }

    public BigDecimal getUseamt() {
        return useamt;
    }

    public void setUseamt(BigDecimal useamt) {
        this.useamt = useamt;
    }

    public String getSignFlg3() {
        return signFlg3;
    }

    public void setSignFlg3(String signFlg3) {
        this.signFlg3 = signFlg3;
    }

    public BigDecimal getAddamt() {
        return addamt;
    }

    public void setAddamt(BigDecimal addamt) {
        this.addamt = addamt;
    }

    public String getSignFlg4() {
        return signFlg4;
    }

    public void setSignFlg4(String signFlg4) {
        this.signFlg4 = signFlg4;
    }

    public BigDecimal getAdjamt() {
        return adjamt;
    }

    public void setAdjamt(BigDecimal adjamt) {
        this.adjamt = adjamt;
    }

    public String getSignFlg5() {
        return signFlg5;
    }

    public void setSignFlg5(String signFlg5) {
        this.signFlg5 = signFlg5;
    }

    public BigDecimal getThisamt() {
        return thisamt;
    }

    public void setThisamt(BigDecimal thisamt) {
        this.thisamt = thisamt;
    }

    public String getSignFlg6() {
        return signFlg6;
    }

    public void setSignFlg6(String signFlg6) {
        this.signFlg6 = signFlg6;
    }

    public BigDecimal getExpamt() {
        return expamt;
    }

    public void setExpamt(BigDecimal expamt) {
        this.expamt = expamt;
    }

    public Date getExpdate() {
        return expdate;
    }

    public void setExpdate(Date expdate) {
        this.expdate = expdate;
    }

    public String getRspCode() {
        return rspCode;
    }

    public void setRspCode(String rspCode) {
        this.rspCode = rspCode;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

}
