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
 * @(#)EB552171Res.java
 * 
 * <p>Description:網路銀行轉出帳號建檔及維護下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/04, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "網路銀行轉出帳號建檔及維護下行電文")
public class EB552171Res extends TxHeadRs {

    private static final long serialVersionUID = -2855567872437203873L;

    /** 戶名 */
    @Schema(description = "戶名")
    private String name;

    /** otp 接收行動電話 */
    @Schema(description = "otp 接收行動電話")
    private String otpMobile;

    /** otp 建檔行 */
    @Schema(description = "otp 建檔行")
    private String otpReqBrh;

    /** otp 維護行 */
    @Schema(description = "otp 維護行")
    private String otpChgBrh;

    /** 轉入帳號約定方式 */
    @Schema(description = "轉入帳號約定方式")
    private String tdType;

    private List<EB552171ResRep> repeats;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtpMobile() {
        return otpMobile;
    }

    public void setOtpMobile(String otpMobile) {
        this.otpMobile = otpMobile;
    }

    public String getOtpReqBrh() {
        return otpReqBrh;
    }

    public void setOtpReqBrh(String otpReqBrh) {
        this.otpReqBrh = otpReqBrh;
    }

    public String getOtpChgBrh() {
        return otpChgBrh;
    }

    public void setOtpChgBrh(String otpChgBrh) {
        this.otpChgBrh = otpChgBrh;
    }

    public String getTdType() {
        return tdType;
    }

    public void setTdType(String tdType) {
        this.tdType = tdType;
    }

    public List<EB552171ResRep> getRepeats() {
        return repeats;
    }

    public void setRepeats(List<EB552171ResRep> repeats) {
        this.repeats = repeats;
    }

}
