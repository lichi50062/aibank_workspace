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
package com.tfb.aibank.chl.general.ot001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NGNOT001102Rs.java 
* 
* <p>Description:FIDO 綁定註銷</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/10, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NGNOT001102Rs implements RsData {

    /** 是否成功 */
    private boolean isSuccess;

    /** 是否QueryLog無資料 */
    private boolean noQueryLogData;

    /** API回覆錯誤代碼 */
    private String errorCode;

    /** API回覆錯誤訊息 */
    private String errorDesc;

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
     * @return the noQueryLogData
     */
    public boolean isNoQueryLogData() {
        return noQueryLogData;
    }

    /**
     * @param noQueryLogData
     *            the noQueryLogData to set
     */
    public void setNoQueryLogData(boolean noQueryLogData) {
        this.noQueryLogData = noQueryLogData;
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
     * @return the errorDesc
     */
    public String getErrorDesc() {
        return errorDesc;
    }

    /**
     * @param errorDesc
     *            the errorDesc to set
     */
    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

}
