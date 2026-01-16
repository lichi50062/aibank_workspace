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
package com.tfb.aibank.chl.general.resource.dto;

import java.util.List;

// @formatter:off
/**
 * @(#)LineSubscribeCheckResponse.java
 * 
 * <p>Description:Line訂閱通知查詢</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/11/06, Leiley 
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class LineSubscribeCheckResponse {

    /** LINEBC API errorCode */
    private String errorCode;

    /** LINEBC API errorMessage */
    private String errorMessage;

    /**
     * 客戶綁定類型 A:存戶 C:卡戶 B:卡存戶 D:非本行客戶
     */
    private String memberType;

    /** 訂閱通知項目列表 */
    private List<LineSubscribeCheckModel> dataList;

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode
     *            the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage
     *            the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the memberType
     */
    public String getMemberType() {
        return memberType;
    }

    /**
     * @param memberType
     *            the memberType to set
     */
    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    /**
     * @return the dataList
     */
    public List<LineSubscribeCheckModel> getDataList() {
        return dataList;
    }

    /**
     * @param dataList
     *            the dataList to set
     */
    public void setDataList(List<LineSubscribeCheckModel> dataList) {
        this.dataList = dataList;
    }

}
