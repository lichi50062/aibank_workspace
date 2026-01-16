/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2013.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.common.model;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)TxHeadRq.java
 * 
 * <p>Description:EAI電文Head上行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "")
public class TxHeadRq implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 電文代號 */
    @Schema(description = "")
    private String HTXTID;

    /** 工作站代號 */
    @Schema(description = "")
    private String HWSID;

    /** 櫃代 */
    @Schema(description = "")
    private String HTLID;

    /** 金資序號 */
    @Schema(description = "")
    private String HSTANO;

    /** C:繼續 E:結束 */
    @Schema(description = "")
    private String HRETRN;

    /**  */
    @Schema(description = "")
    private String TXMSRN;

    /** 訊息代號 */
    @Schema(description = "訊息代號")
    private String HDRVQ1;

    /** 格式 */
    @Schema(description = "格式")
    private String HFMTID;

    /**  */
    @Schema(description = "")
    private String HFUNC;

    /**  */
    @Schema(description = "")
    private String PAGEFLG;

    /**  */
    @Schema(description = "")
    private String TSTACD;

    /**  */
    @Schema(description = "")
    private String TXMODE;

    /**  */
    @Schema(description = "")
    private String HPVDQ2;

    /**  */
    @Schema(description = "")
    private String HDTLEN;

    /**  */
    @Schema(description = "")
    private String HSLGNF;

    /**  */
    @Schema(description = "")
    private String HSYDAY;

    public String getHTXTID() {
        return HTXTID;
    }

    public void setHTXTID(String hTXTID) {
        HTXTID = hTXTID;
    }

    public String getHWSID() {
        return HWSID;
    }

    public void setHWSID(String hWSID) {
        HWSID = hWSID;
    }

    public String getHTLID() {
        return HTLID;
    }

    public void setHTLID(String hTLID) {
        HTLID = hTLID;
    }

    public String getHSTANO() {
        return HSTANO;
    }

    public void setHSTANO(String hSTANO) {
        HSTANO = hSTANO;
    }

    public String getHRETRN() {
        return HRETRN;
    }

    public void setHRETRN(String hRETRN) {
        HRETRN = hRETRN;
    }

    public String getTXMSRN() {
        return TXMSRN;
    }

    public void setTXMSRN(String tXMSRN) {
        TXMSRN = tXMSRN;
    }

    public String getHDRVQ1() {
        return HDRVQ1;
    }

    public void setHDRVQ1(String hDRVQ1) {
        HDRVQ1 = hDRVQ1;
    }

    public String getHFMTID() {
        return HFMTID;
    }

    public void setHFMTID(String hFMTID) {
        HFMTID = hFMTID;
    }

    public String getHFUNC() {
        return HFUNC;
    }

    public void setHFUNC(String hFUNC) {
        HFUNC = hFUNC;
    }

    public String getPAGEFLG() {
        return PAGEFLG;
    }

    public void setPAGEFLG(String pAGEFLG) {
        PAGEFLG = pAGEFLG;
    }

    public String getTSTACD() {
        return TSTACD;
    }

    public void setTSTACD(String tSTACD) {
        TSTACD = tSTACD;
    }

    public String getTXMODE() {
        return TXMODE;
    }

    public void setTXMODE(String tXMODE) {
        TXMODE = tXMODE;
    }

    public String getHPVDQ2() {
        return HPVDQ2;
    }

    public void setHPVDQ2(String hPVDQ2) {
        HPVDQ2 = hPVDQ2;
    }

    public String getHDTLEN() {
        return HDTLEN;
    }

    public void setHDTLEN(String hDTLEN) {
        HDTLEN = hDTLEN;
    }

    public String getHSLGNF() {
        return HSLGNF;
    }

    public void setHSLGNF(String hSLGNF) {
        HSLGNF = hSLGNF;
    }

    public String getHSYDAY() {
        return HSYDAY;
    }

    public void setHSYDAY(String hSYDAY) {
        HSYDAY = hSYDAY;
    }

}
