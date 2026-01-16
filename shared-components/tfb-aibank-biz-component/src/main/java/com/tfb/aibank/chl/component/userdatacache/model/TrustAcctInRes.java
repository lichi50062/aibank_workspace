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

import java.util.List;

import com.tfb.aibank.common.model.TxHeadRs;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EB5556911Res.java
 * 
 * <p>Description:約定轉入帳號 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/04, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "約定轉入帳號 下行電文")
public class TrustAcctInRes extends TxHeadRs {

    private static final long serialVersionUID = 21004947569490313L;

    /** 單雙簽別 */
    @Schema(description = "單雙簽別")
    private String userIdLevel;

    /** 線上約定轉入帳號註記 */
    @Schema(description = "線上約定轉入帳號註記")
    private String ebApplyFlag;

    /** 分行別1 */
    @Schema(description = "分行別1")
    private String branchNo1;

    /** 分行別2 */
    @Schema(description = "分行別2")
    private String branchNo2;

    /** 分行別3 */
    @Schema(description = "分行別3")
    private String branchNo3;

    /** 分行別4 */
    @Schema(description = "分行別4")
    private String branchNo4;

    /** 分行別5 */
    @Schema(description = "分行別5")
    private String branchNo5;

    /** 分行別6 */
    @Schema(description = "分行別6")
    private String branchNo6;

    /** 分行別7 */
    @Schema(description = "分行別7")
    private String branchNo7;

    /** 分行別8 */
    @Schema(description = "分行別8")
    private String branchNo8;

    /** 分行別9 */
    @Schema(description = "分行別9")
    private String branchNo9;

    /** 分行別10 */
    @Schema(description = "分行別10")
    private String branchNo10;

    /** 數位類型 */
    @Schema(description = "數位類型")
    private String digitType;

    /** 分行別 */
    @Schema(description = "分行別")
    private String branchNo;

    /** 幣別 */
    @Schema(description = "幣別")
    private String currCod;

    /** 細項 */
    @Schema(description = "細項")
    private List<TrustAcctInResRep> repeats;

    public String getUserIdLevel() {
        return userIdLevel;
    }

    public void setUserIdLevel(String userIdLevel) {
        this.userIdLevel = userIdLevel;
    }

    public String getEbApplyFlag() {
        return ebApplyFlag;
    }

    public void setEbApplyFlag(String ebApplyFlag) {
        this.ebApplyFlag = ebApplyFlag;
    }

    public String getDigitType() {
        return digitType;
    }

    public void setDigitType(String digitType) {
        this.digitType = digitType;
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    public String getCurrCod() {
        return currCod;
    }

    public void setCurrCod(String currCod) {
        this.currCod = currCod;
    }

    public List<TrustAcctInResRep> getRepeats() {
        return repeats;
    }

    public void setRepeats(List<TrustAcctInResRep> repeats) {
        this.repeats = repeats;
    }

    public String getBranchNo1() {
        return branchNo1;
    }

    public void setBranchNo1(String branchNo1) {
        this.branchNo1 = branchNo1;
    }

    public String getBranchNo2() {
        return branchNo2;
    }

    public void setBranchNo2(String branchNo2) {
        this.branchNo2 = branchNo2;
    }

    public String getBranchNo3() {
        return branchNo3;
    }

    public void setBranchNo3(String branchNo3) {
        this.branchNo3 = branchNo3;
    }

    public String getBranchNo4() {
        return branchNo4;
    }

    public void setBranchNo4(String branchNo4) {
        this.branchNo4 = branchNo4;
    }

    public String getBranchNo5() {
        return branchNo5;
    }

    public void setBranchNo5(String branchNo5) {
        this.branchNo5 = branchNo5;
    }

    public String getBranchNo6() {
        return branchNo6;
    }

    public void setBranchNo6(String branchNo6) {
        this.branchNo6 = branchNo6;
    }

    public String getBranchNo7() {
        return branchNo7;
    }

    public void setBranchNo7(String branchNo7) {
        this.branchNo7 = branchNo7;
    }

    public String getBranchNo8() {
        return branchNo8;
    }

    public void setBranchNo8(String branchNo8) {
        this.branchNo8 = branchNo8;
    }

    public String getBranchNo9() {
        return branchNo9;
    }

    public void setBranchNo9(String branchNo9) {
        this.branchNo9 = branchNo9;
    }

    public String getBranchNo10() {
        return branchNo10;
    }

    public void setBranchNo10(String branchNo10) {
        this.branchNo10 = branchNo10;
    }

}
