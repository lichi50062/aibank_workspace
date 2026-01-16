/**
 * 
 */
package com.tfb.aibank.chl.creditcard.resource.dto;

//@formatter:off
/**
* @(#)EB12020009Response.java
* 
* <p>Description:EB12020009 預借現金 Request</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/22, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class EB12020009Response {

    /** 回傳代碼 */
    private String msgCod;

    /** 訊息中文代碼 */
    private String msgTxt;

    /** 匯費 */
    private String rtFee;

    /**
     * @return the msgCod
     */
    public String getMsgCod() {
        return msgCod;
    }

    /**
     * @param msgCod
     *            the msgCod to set
     */
    public void setMsgCod(String msgCod) {
        this.msgCod = msgCod;
    }

    /**
     * @return the msgTxt
     */
    public String getMsgTxt() {
        return msgTxt;
    }

    /**
     * @param msgTxt
     *            the msgTxt to set
     */
    public void setMsgTxt(String msgTxt) {
        this.msgTxt = msgTxt;
    }

    /**
     * @return the rtFee
     */
    public String getRtFee() {
        return rtFee;
    }

    /**
     * @param rtFee
     *            the rtFee to set
     */
    public void setRtFee(String rtFee) {
        this.rtFee = rtFee;
    }

}
