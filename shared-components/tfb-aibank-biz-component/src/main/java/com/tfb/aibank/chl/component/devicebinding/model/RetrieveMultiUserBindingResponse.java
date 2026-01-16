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
package com.tfb.aibank.chl.component.devicebinding.model;

// @formatter:off
/**
 * @(#)RetrieveMultiUserBindingResponse.java
 * 
 * <p>Description:取得多使用者代碼客戶綁定狀態 - Response</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/04, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class RetrieveMultiUserBindingResponse {

    /** 是否有同ID但不同使用者代碼客戶已綁定資料 */
    private boolean isOtherUserBind;

    /** 綁定使用者代號 */
    private String otherUserId;

    /** 綁定使用者登入類型 */
    private int otherUserLoginType;

    /**
     * @return the isOtherUserBind
     */
    public boolean isOtherUserBind() {
        return isOtherUserBind;
    }

    /**
     * @param isOtherUserBind
     *            the isOtherUserBind to set
     */
    public void setOtherUserBind(boolean isOtherUserBind) {
        this.isOtherUserBind = isOtherUserBind;
    }

    /**
     * @return the otherUserId
     */
    public String getOtherUserId() {
        return otherUserId;
    }

    /**
     * @param otherUserId
     *            the otherUserId to set
     */
    public void setOtherUserId(String otherUserId) {
        this.otherUserId = otherUserId;
    }

    /**
     * @return the otherUserLoginType
     */
    public int getOtherUserLoginType() {
        return otherUserLoginType;
    }

    /**
     * @param otherUserLoginType
     *            the otherUserLoginType to set
     */
    public void setOtherUserLoginType(int otherUserLoginType) {
        this.otherUserLoginType = otherUserLoginType;
    }

}
