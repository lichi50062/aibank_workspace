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
package com.tfb.aibank.chl.component.favoriteaccount.model;

//@formatter:off
/**
* @(#)CreateFavoriteAccountResponse.java
* 
* <p>Description:新增常用帳號 - Response</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/04, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CreateFavoriteAccountResponse {

    /** 是否成功 */
    private boolean isSuccess;

    /** 是否重複 */
    private boolean isDuplicate;

    /**
     * @return the isSuccess
     */
    public boolean isSuccess() {
        return isSuccess;
    }

    /**
     * @param isSuccess
     *            the isSuccess to set
     */
    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    /**
     * @return the isDuplicate
     */
    public boolean isDuplicate() {
        return isDuplicate;
    }

    /**
     * @param isDuplicate the isDuplicate to set
     */
    public void setDuplicate(boolean isDuplicate) {
        this.isDuplicate = isDuplicate;
    }

}
