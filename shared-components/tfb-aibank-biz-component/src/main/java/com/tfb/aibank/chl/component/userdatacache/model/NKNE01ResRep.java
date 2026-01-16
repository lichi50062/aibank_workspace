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
package com.tfb.aibank.chl.component.userdatacache.model;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)NKNE01ResRep.java
 * 
 * <p>Description:股票客戶查詢 下行欄位</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/13, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "股票客戶查詢 下行欄位 Repeat")
public class NKNE01ResRep implements Serializable {

    private static final long serialVersionUID = -4585563068705250115L;

    /** W-8BEN */
    @Schema(description = "W-8BEN")
    private String w8Ben;
    /** 風險預告書 */
    @Schema(description = "風險預告書")
    private String ram;
    /** W-8BEN簽署起日 */
    @Schema(description = "W-8BEN簽署起日")
    private String signStartDate;
    /** W-8BEN簽署迄日 */
    @Schema(description = "W-8BEN簽署迄日")
    private String signEndDate;
    /** 是否為首購 */
    @Schema(description = "是否為首購")
    private String fstTrade;
    /** 是否為當日首購 */
    @Schema(description = "是否為當日首購")
    private String dayFstTrade;

    public String getW8Ben() {
        return w8Ben;
    }

    public void setW8Ben(String w8Ben) {
        this.w8Ben = w8Ben;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getSignStartDate() {
        return signStartDate;
    }

    public void setSignStartDate(String signStartDate) {
        this.signStartDate = signStartDate;
    }

    public String getSignEndDate() {
        return signEndDate;
    }

    public void setSignEndDate(String signEndDate) {
        this.signEndDate = signEndDate;
    }

    public String getFstTrade() {
        return fstTrade;
    }

    public void setFstTrade(String fstTrade) {
        this.fstTrade = fstTrade;
    }

    public String getDayFstTrade() {
        return dayFstTrade;
    }

    public void setDayFstTrade(String dayFstTrade) {
        this.dayFstTrade = dayFstTrade;
    }

}
