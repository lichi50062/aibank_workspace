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

}
