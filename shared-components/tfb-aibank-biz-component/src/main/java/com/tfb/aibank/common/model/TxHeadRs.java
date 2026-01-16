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
import java.util.Date;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)TxHeadRs.java
 * 
 * <p>Description:EAI電文Head下行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "EAI電文Head下行")
public class TxHeadRs implements Serializable {

    private static final long serialVersionUID = 1L;

    /**  */
    @Schema(description = "")
    private String HMSGID;

    /** 回覆碼 */
    @Schema(description = "回覆碼")
    private String HERRID;

    /** 系統日期(cccMMdd) */
    @Schema(description = "系統日期(cccMMdd)")
    private String HSYDAY;

    /** 系統時間(HHmmss) */
    @Schema(description = "系統時間(HHmmss)")
    private String HSYTIME;

    /**  */
    @Schema(description = "")
    private String HWSID;

    /** 交易序號 (每一天都不會重複) */
    @Schema(description = "交易序號 (每一天都不會重複)")
    private String HSTANO;

    /**  */
    @Schema(description = "")
    private String HDTLEN;

    /**  */
    @Schema(description = "")
    private String HREQQ1;

    /**  */
    @Schema(description = "")
    private String HREPQ1;

    /**  */
    @Schema(description = "")
    private String HDRVQ1;

    /**  */
    @Schema(description = "")
    private String HPVDQ1;

    /**  */
    @Schema(description = "")
    private String HPVDQ2;

    /**  */
    @Schema(description = "")
    private String HSYCVD;

    /** 櫃員代號 */
    @Schema(description = "櫃員代號")
    private String HTLID;

    /** 電文代碼 */
    @Schema(description = "電文代碼")
    private String HTXTID;

    /**  */
    @Schema(description = "")
    private String HFMTID;

    /** 折返註記 C:表示尚有資料，E:表示已是最終筆 */
    @Schema(description = "折返註記 C:表示尚有資料，E:表示已是最終筆")
    private String HRETRN;

    /**  */
    @Schema(description = "")
    private String HSLGNF;

    /**  */
    @Schema(description = "")
    private String HSPSCK;

    /**  */
    @Schema(description = "")
    private String HRTNCD;

    /**  */
    @Schema(description = "")
    private String HSBTRF;

    /**  */
    @Schema(description = "")
    private String HFILL;

    /** 錯誤代號 */
    @Schema(description = "錯誤代號")
    private String EMSGID;

    /** 錯誤描述 */
    @Schema(description = "錯誤描述")
    private String EMSGTXT;

    /**
     * 電文執行時間
     * 
     * @return
     */
    public Date getHostTxTime() {
        Date hostTxTime = null;
        if (StringUtils.isNotBlank(getHSYDAY())) {
            hostTxTime = DateUtils.getROCDate(getHSYDAY(), StringUtils.EMPTY);
        }
        if (hostTxTime != null && StringUtils.isNotBlank(getHSYTIME())) {
            String datetime = DateFormatUtils.SIMPLE_DATE_FORMAT.format(hostTxTime) + getHSYTIME();
            hostTxTime = DateUtils.getDateByDateFormat(datetime, "yyyyMMddHHmmss");
        }
        return hostTxTime;
    }

    public String getHMSGID() {
        return HMSGID;
    }

    public void setHMSGID(String hMSGID) {
        HMSGID = hMSGID;
    }

    public String getHERRID() {
        return HERRID;
    }

    public void setHERRID(String hERRID) {
        HERRID = hERRID;
    }

    public String getHSYDAY() {
        return HSYDAY;
    }

    public void setHSYDAY(String hSYDAY) {
        HSYDAY = hSYDAY;
    }

    public String getHSYTIME() {
        return HSYTIME;
    }

    public void setHSYTIME(String hSYTIME) {
        HSYTIME = hSYTIME;
    }

    public String getHWSID() {
        return HWSID;
    }

    public void setHWSID(String hWSID) {
        HWSID = hWSID;
    }

    public String getHSTANO() {
        return HSTANO;
    }

    public void setHSTANO(String hSTANO) {
        HSTANO = hSTANO;
    }

    public String getHDTLEN() {
        return HDTLEN;
    }

    public void setHDTLEN(String hDTLEN) {
        HDTLEN = hDTLEN;
    }

    public String getHREQQ1() {
        return HREQQ1;
    }

    public void setHREQQ1(String hREQQ1) {
        HREQQ1 = hREQQ1;
    }

    public String getHREPQ1() {
        return HREPQ1;
    }

    public void setHREPQ1(String hREPQ1) {
        HREPQ1 = hREPQ1;
    }

    public String getHDRVQ1() {
        return HDRVQ1;
    }

    public void setHDRVQ1(String hDRVQ1) {
        HDRVQ1 = hDRVQ1;
    }

    public String getHPVDQ1() {
        return HPVDQ1;
    }

    public void setHPVDQ1(String hPVDQ1) {
        HPVDQ1 = hPVDQ1;
    }

    public String getHPVDQ2() {
        return HPVDQ2;
    }

    public void setHPVDQ2(String hPVDQ2) {
        HPVDQ2 = hPVDQ2;
    }

    public String getHSYCVD() {
        return HSYCVD;
    }

    public void setHSYCVD(String hSYCVD) {
        HSYCVD = hSYCVD;
    }

    public String getHTLID() {
        return HTLID;
    }

    public void setHTLID(String hTLID) {
        HTLID = hTLID;
    }

    public String getHTXTID() {
        return HTXTID;
    }

    public void setHTXTID(String hTXTID) {
        HTXTID = hTXTID;
    }

    public String getHFMTID() {
        return HFMTID;
    }

    public void setHFMTID(String hFMTID) {
        HFMTID = hFMTID;
    }

    public String getHRETRN() {
        return HRETRN;
    }

    public void setHRETRN(String hRETRN) {
        HRETRN = hRETRN;
    }

    public String getHSLGNF() {
        return HSLGNF;
    }

    public void setHSLGNF(String hSLGNF) {
        HSLGNF = hSLGNF;
    }

    public String getHSPSCK() {
        return HSPSCK;
    }

    public void setHSPSCK(String hSPSCK) {
        HSPSCK = hSPSCK;
    }

    public String getHRTNCD() {
        return HRTNCD;
    }

    public void setHRTNCD(String hRTNCD) {
        HRTNCD = hRTNCD;
    }

    public String getHSBTRF() {
        return HSBTRF;
    }

    public void setHSBTRF(String hSBTRF) {
        HSBTRF = hSBTRF;
    }

    public String getHFILL() {
        return HFILL;
    }

    public void setHFILL(String hFILL) {
        HFILL = hFILL;
    }

    public String getEMSGID() {
        return EMSGID;
    }

    public void setEMSGID(String eMSGID) {
        EMSGID = eMSGID;
    }

    public String getEMSGTXT() {
        return EMSGTXT;
    }

    public void setEMSGTXT(String eMSGTXT) {
        EMSGTXT = eMSGTXT;
    }

}
