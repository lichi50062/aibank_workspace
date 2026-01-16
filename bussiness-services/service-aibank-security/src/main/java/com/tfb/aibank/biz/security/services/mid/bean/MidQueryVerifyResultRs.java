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
package com.tfb.aibank.biz.security.services.mid.bean;

import com.google.gson.annotations.SerializedName;

//@formatter:off
/**
* @(#)MidQueryVerifyResultRs.java
* 
* <p>Description:台網MID驗證 - QueryVerifyResult回傳物件</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/06, HankChan   
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class MidQueryVerifyResultRs extends MidRs {

    @SerializedName("ResultCode")
    private String resultCode;

    @SerializedName("ReturnCode")
    private String returnCode;

    @SerializedName("ReturnCodeDesc")
    private String returnCodeDesc;

    @SerializedName("OutputParams")
    private String outputParams;

    /**
     * @return the resultCode
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * @param resultCode
     *            the resultCode to set
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * @return the returnCode
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * @param returnCode
     *            the returnCode to set
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * @return the returnCodeDesc
     */
    public String getReturnCodeDesc() {
        return returnCodeDesc;
    }

    /**
     * @param returnCodeDesc
     *            the returnCodeDesc to set
     */
    public void setReturnCodeDesc(String returnCodeDesc) {
        this.returnCodeDesc = returnCodeDesc;
    }

    /**
     * @return the outputParams
     */
    public String getOutputParams() {
        return outputParams;
    }

    /**
     * @param outputParams
     *            the outputParams to set
     */
    public void setOutputParams(String outputParams) {
        this.outputParams = outputParams;
    }

    @Override
    public String getIdentifyData(String hashKey) {
        return getBusinessNo() + getApiVersion() + getHashKeyNo() + getVerifyNo() + getResultCode() + getReturnCode() + getOutputParams() + hashKey;
    }

}
