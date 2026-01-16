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
 * @(#)EB552171ReqRep.java
 * 
 * <p>Description:網路銀行轉出帳號建檔及維護 上行電文 Repeat</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/04, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "網路銀行轉出帳號建檔及維護 上行電文 Repeat")
public class EB552171ReqRep implements Serializable {

    private static final long serialVersionUID = -6421891659460291201L;

    /** 選項 一般帳戶-提高非約轉限額 提高item_no＝y, 預設item_no＝n，數位存款帳戶提高非約轉限額 item_no=c:提高, item_no=d:調降 */
    @Schema(description = "選項  一般帳戶-提高非約轉限額 提高item_no＝y, 預設item_no＝n，數位存款帳戶提高非約轉限額 item_no=c:提高, item_no=d:調降")
    private String itemNo;

    /** 轉出帳號 預設定之帳 */
    @Schema(description = "轉出帳號 預設定之帳")
    private String acnoOut;

    /** 轉出戶況檢核 */
    @Schema(description = "轉出戶況檢核")
    private String func;

    /** 限約定轉入帳號記註 */
    @Schema(description = "限約定轉入帳號記註")
    private String acnoinFlg;

    /** 外幣轉出功能 */
    @Schema(description = "外幣轉出功能")
    private String busEb;

    /** 申請日期 */
    @Schema(description = "申請日期")
    private String reqDate;

    /** 檔案維護序號(h) */
    @Schema(description = "檔案維護序號(h)")
    private String dbAppNo;

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getAcnoOut() {
        return acnoOut;
    }

    public void setAcnoOut(String acnoOut) {
        this.acnoOut = acnoOut;
    }

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getAcnoinFlg() {
        return acnoinFlg;
    }

    public void setAcnoinFlg(String acnoinFlg) {
        this.acnoinFlg = acnoinFlg;
    }

    public String getBusEb() {
        return busEb;
    }

    public void setBusEb(String busEb) {
        this.busEb = busEb;
    }

    public String getReqDate() {
        return reqDate;
    }

    public void setReqDate(String reqDate) {
        this.reqDate = reqDate;
    }

    public String getDbAppNo() {
        return dbAppNo;
    }

    public void setDbAppNo(String dbAppNo) {
        this.dbAppNo = dbAppNo;
    }
}
