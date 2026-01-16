/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.biz.user.services.employee.model;

import java.math.BigDecimal;
import java.util.Date;

import com.ibm.tw.commons.util.ConvertUtils;
import com.tfb.aibank.common.type.SavingDetailType;

import io.swagger.v3.oas.annotations.media.Schema;
import tw.com.ibm.mf.eb.RTWEBP010003RepeatType;
import tw.com.ibm.mf.eb.RTWEBP010004RepeatType;
import tw.com.ibm.mf.eb.RTWEBP010005RepeatType;

//@formatter:off
/**
* @(#)SavingTrustDetail.java
* 
* <p>Description: 儲蓄信託明細物件</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/08/19, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Schema(description = "儲蓄信託明細物件")
public class SavingTrustDetail {

    /**
     * 儲蓄信託明細種類
     */
    @Schema(description = "儲蓄信託明細種類")
    private SavingDetailType savingDetailType;

    /**
     * 明細年月份
     */
    @Schema(description = "明細年月份")
    private Date detYm;

    /**
     * 投資標的
     */
    @Schema(description = "投資標的")
    private String invSub;

    /**
     * 公提金提存金額
     */
    @Schema(description = "公提金提存金額")
    private BigDecimal detAmt1;

    /**
     * 自提金提存金額
     */
    @Schema(description = "自提金提存金額")
    private BigDecimal detAmt2;

    /**
     * 作業處理費及保管費
     */
    @Schema(description = "作業處理費及保管費")
    private BigDecimal fee;

    /**
     * 分配單位數
     */
    @Schema(description = "分配單位數")
    private BigDecimal unit;

    /**
     * 累計年月份
     */
    @Schema(description = "累計年月份")
    private Date sumYm;

    /**
     * 累計公提金提存金額
     */
    @Schema(description = "累計公提金提存金額")
    private BigDecimal sumAmt1;

    /**
     * 累計自提金提存金額
     */
    @Schema(description = "累計自提金提存金額")
    private BigDecimal sumAmt2;

    /**
     * 累計單位數
     */
    @Schema(description = "累計單位數")
    private BigDecimal sumUnit;

    /**
     * 淨值日期
     */
    @Schema(description = "淨值日期")
    private Date navDay;

    /**
     * 單位淨值NAV
     */
    @Schema(description = "單位淨值NAV")
    private BigDecimal navVul;

    /**
     * 信託資金淨資產價值
     */
    @Schema(description = "信託資金淨資產價值")
    private BigDecimal netAum;

    public SavingTrustDetail() {
    }

    public SavingTrustDetail(RTWEBP010003RepeatType repeatType) {
        this.savingDetailType = SavingDetailType.DETAILS;
        this.detYm = ConvertUtils.calendar2Date(repeatType.getDetYM());
        this.invSub = repeatType.getInvSub();
        this.detAmt1 = repeatType.getDetamt1();
        this.detAmt2 = repeatType.getDetamt2();
        this.fee = repeatType.getFee();
        this.unit = repeatType.getUnit();
    }

    public SavingTrustDetail(RTWEBP010004RepeatType repeatType) {
        this.savingDetailType = SavingDetailType.ACCUMULATE;
        this.sumYm = ConvertUtils.calendar2Date(repeatType.getSumYM());
        this.sumAmt1 = repeatType.getSumamt1();
        this.sumAmt2 = repeatType.getSumamt2();
        this.sumUnit = repeatType.getSumunit();
    }

    public SavingTrustDetail(RTWEBP010005RepeatType repeatType) {
        this.savingDetailType = SavingDetailType.NET_VALUE;
        this.navDay = ConvertUtils.calendar2Date(repeatType.getNavDay());
        this.navVul = repeatType.getNavVul();
        this.netAum = repeatType.getNetAum();
    }

    public SavingDetailType getSavingDetailType() {
        return savingDetailType;
    }

    public void setSavingDetailType(SavingDetailType savingDetailType) {
        this.savingDetailType = savingDetailType;
    }

    public Date getDetYm() {
        return detYm;
    }

    public void setDetYm(Date detYm) {
        this.detYm = detYm;
    }

    public String getInvSub() {
        return invSub;
    }

    public void setInvSub(String invSub) {
        this.invSub = invSub;
    }

    public BigDecimal getDetAmt1() {
        return detAmt1;
    }

    public void setDetAmt1(BigDecimal detAmt1) {
        this.detAmt1 = detAmt1;
    }

    public BigDecimal getDetAmt2() {
        return detAmt2;
    }

    public void setDetAmt2(BigDecimal detAmt2) {
        this.detAmt2 = detAmt2;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getUnit() {
        return unit;
    }

    public void setUnit(BigDecimal unit) {
        this.unit = unit;
    }

    public Date getSumYm() {
        return sumYm;
    }

    public void setSumYm(Date sumYm) {
        this.sumYm = sumYm;
    }

    public BigDecimal getSumAmt1() {
        return sumAmt1;
    }

    public void setSumAmt1(BigDecimal sumAmt1) {
        this.sumAmt1 = sumAmt1;
    }

    public BigDecimal getSumAmt2() {
        return sumAmt2;
    }

    public void setSumAmt2(BigDecimal sumAmt2) {
        this.sumAmt2 = sumAmt2;
    }

    public BigDecimal getSumUnit() {
        return sumUnit;
    }

    public void setSumUnit(BigDecimal sumUnit) {
        this.sumUnit = sumUnit;
    }

    public Date getNavDay() {
        return navDay;
    }

    public void setNavDay(Date navDay) {
        this.navDay = navDay;
    }

    public BigDecimal getNavVul() {
        return navVul;
    }

    public void setNavVul(BigDecimal navVul) {
        this.navVul = navVul;
    }

    public BigDecimal getNetAum() {
        return netAum;
    }

    public void setNetAum(BigDecimal netAum) {
        this.netAum = netAum;
    }
}
