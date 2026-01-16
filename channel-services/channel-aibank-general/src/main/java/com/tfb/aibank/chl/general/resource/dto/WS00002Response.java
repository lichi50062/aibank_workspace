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
package com.tfb.aibank.chl.general.resource.dto;

// @formatter:off
import java.io.Serializable;
import java.util.List;
/**
 * @(#)WS00002RS.java
 * 
 * <p>Description:保單資料 RS</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/06, Jackie Chien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class WS00002Response implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 訊息代碼 */
    private String statusCode;

    /** 錯誤訊息 */
    private String errorMessage;

    /** 檢核結果 */
    private List<PolyInfoItem> polyInfo;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<PolyInfoItem> getPolyInfo() {
        return polyInfo;
    }

    public void setPolyInfo(List<PolyInfoItem> polyInfo) {
        this.polyInfo = polyInfo;
    }
}
