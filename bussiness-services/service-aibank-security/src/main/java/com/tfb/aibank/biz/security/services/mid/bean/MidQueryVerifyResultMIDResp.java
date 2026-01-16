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

//@formatter:off
/**
* @(#)MidQueryVerifyResultMIDParams.java
* 
* <p>Description:台網MID驗證 - QueryVerifyResult回傳物件 - MIDResp</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/06, HankChan   
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class MidQueryVerifyResultMIDResp {

    private String code;

    private String message;

    private String msisdn;

    private String payload;

    private String reqSeq;

    private String result;

    private String rspSeq;

    private String rspTime;

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the msisdn
     */
    public String getMsisdn() {
        return msisdn;
    }

    /**
     * @param msisdn
     *            the msisdn to set
     */
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    /**
     * @return the payload
     */
    public String getPayload() {
        return payload;
    }

    /**
     * @param payload
     *            the payload to set
     */
    public void setPayload(String payload) {
        this.payload = payload;
    }

    /**
     * @return the reqSeq
     */
    public String getReqSeq() {
        return reqSeq;
    }

    /**
     * @param reqSeq
     *            the reqSeq to set
     */
    public void setReqSeq(String reqSeq) {
        this.reqSeq = reqSeq;
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result
     *            the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * @return the rspSeq
     */
    public String getRspSeq() {
        return rspSeq;
    }

    /**
     * @param rspSeq
     *            the rspSeq to set
     */
    public void setRspSeq(String rspSeq) {
        this.rspSeq = rspSeq;
    }

    /**
     * @return the rspTime
     */
    public String getRspTime() {
        return rspTime;
    }

    /**
     * @param rspTime
     *            the rspTime to set
     */
    public void setRspTime(String rspTime) {
        this.rspTime = rspTime;
    }

}
