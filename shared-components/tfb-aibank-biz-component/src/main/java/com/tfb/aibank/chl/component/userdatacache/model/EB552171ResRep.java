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
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EB552171ResRep.java
 * 
 * <p>Description:網路銀行轉出帳號建檔及維護下行電文 Repeat</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/04, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "網路銀行轉出帳號建檔及維護下行電文 Repeat")
public class EB552171ResRep implements Serializable {

    private static final long serialVersionUID = 1127584040980612952L;

    /** 轉出帳號 */
    @Schema(description = "轉出帳號")
    private String acnoOut;

    /** 限約定轉入帳號記註 */
    @Schema(description = "限約定轉入帳號記註")
    private String acnoInFlag;

    /** 外幣轉出功能 */
    @Schema(description = "外幣轉出功能")
    private String exFlg;

    /** 狀態別 */
    @Schema(description = "狀態別")
    private String status;

    /** 申請日期 */
    @Schema(description = "申請日期")
    private Date reqDate;

    /** 終止日期 */
    @Schema(description = "終止日期")
    private Date endDate;

    /** 檔案維護序號 */
    @Schema(description = "檔案維護序號")
    private String dbAppNo;

    /** 建檔分行 */
    @Schema(description = "建檔分行")
    private String oriReqBrh;

    /** 異動分行 */
    @Schema(description = "異動分行")
    private String lstChgBrh;

    /** 提高限額註記 */
    @Schema(description = "提高限額註記")
    private String ckType;

    /** 非約轉提高限額註記 已註記 FLAG = Y */
    @Schema(description = "非約轉提高限額註記 已註記 FLAG = Y")
    private String flag;

    /** 數位存款三類視訊註記 已註記 VideoFlag = */
    @Schema(description = "數位存款三類視訊註記 已註記 VideoFlag = Y")
    private String videoFlag;

    public String getAcnoOut() {
        return acnoOut;
    }

    public void setAcnoOut(String acnoOut) {
        this.acnoOut = acnoOut;
    }

    public String getAcnoInFlag() {
        return acnoInFlag;
    }

    public void setAcnoInFlag(String acnoInFlag) {
        this.acnoInFlag = acnoInFlag;
    }

    public String getExFlg() {
        return exFlg;
    }

    public void setExFlg(String exFlg) {
        this.exFlg = exFlg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getReqDate() {
        return reqDate;
    }

    public void setReqDate(Date reqDate) {
        this.reqDate = reqDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDbAppNo() {
        return dbAppNo;
    }

    public void setDbAppNo(String dbAppNo) {
        this.dbAppNo = dbAppNo;
    }

    public String getOriReqBrh() {
        return oriReqBrh;
    }

    public void setOriReqBrh(String oriReqBrh) {
        this.oriReqBrh = oriReqBrh;
    }

    public String getLstChgBrh() {
        return lstChgBrh;
    }

    public void setLstChgBrh(String lstChgBrh) {
        this.lstChgBrh = lstChgBrh;
    }

    public String getCkType() {
        return ckType;
    }

    public void setCkType(String ckType) {
        this.ckType = ckType;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getVideoFlag() {
        return videoFlag;
    }

    public void setVideoFlag(String videoFlag) {
        this.videoFlag = videoFlag;
    }

}
