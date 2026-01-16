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
package com.tfb.aibank.chl.system.ot004.model;

// @formatter:off
/**
 * @(#)ErrorCodeForHandShakeVo.java
 * 
 * <p>Description:handshake 用錯誤資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/28, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class ErrorCodeForHandShake {
    /** 系統別 */
    private String systemId;
    /** 錯誤代號 */
    private String errorCode;
    /** 錯誤對外資訊 */
    private String externalDesc;
    /**
     * 錯誤標題
     */
    private String title;

    /**
     * 轉導按鈕名稱1
     */
    private String directButtonName1;

    /**
     * 轉導交易1
     */
    private String directTaskId1;

    /**
     * 轉導按鈕名稱2
     */
    private String directButtonName2;

    /**
     * 轉導交易2
     */
    private String directTaskId2;

    /**
     * @return the systemId
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * @param systemId
     *            the systemId to set
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

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
     * @return the externalDesc
     */
    public String getExternalDesc() {
        return externalDesc;
    }

    /**
     * @param externalDesc
     *            the externalDesc to set
     */
    public void setExternalDesc(String externalDesc) {
        this.externalDesc = externalDesc;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the directButtonName1
     */
    public String getDirectButtonName1() {
        return directButtonName1;
    }

    /**
     * @param directButtonName1
     *            the directButtonName1 to set
     */
    public void setDirectButtonName1(String directButtonName1) {
        this.directButtonName1 = directButtonName1;
    }

    /**
     * @return the directTaskId1
     */
    public String getDirectTaskId1() {
        return directTaskId1;
    }

    /**
     * @param directTaskId1
     *            the directTaskId1 to set
     */
    public void setDirectTaskId1(String directTaskId1) {
        this.directTaskId1 = directTaskId1;
    }

    /**
     * @return the directButtonName2
     */
    public String getDirectButtonName2() {
        return directButtonName2;
    }

    /**
     * @param directButtonName2
     *            the directButtonName2 to set
     */
    public void setDirectButtonName2(String directButtonName2) {
        this.directButtonName2 = directButtonName2;
    }

    /**
     * @return the directTaskId2
     */
    public String getDirectTaskId2() {
        return directTaskId2;
    }

    /**
     * @param directTaskId2
     *            the directTaskId2 to set
     */
    public void setDirectTaskId2(String directTaskId2) {
        this.directTaskId2 = directTaskId2;
    }

}
