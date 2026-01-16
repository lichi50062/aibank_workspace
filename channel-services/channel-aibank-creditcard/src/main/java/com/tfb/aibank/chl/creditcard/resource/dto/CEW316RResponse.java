/**
 *
 */
package com.tfb.aibank.chl.creditcard.resource.dto;

import java.util.Date;
import java.util.List;

//@formatter:off
/**
 * @(#)CEW316RRequest.java
 *
 * <p>Description:CEW316R 信用卡道路救援車號 Request</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/09/22
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class CEW316RResponse {

    /**
     * 上送主機交易時間
     */
    private Date hostTxTime;

    /**
     * emsgId
     */
    private String errorCode;

    private String errorMsg;

    /**
     * 錯誤系統代碼
     */
    private String txErrorSystemId;

    private List<CEW316RRepeat> repeats;

    public CEW316RResponse() {

    }

    public CEW316RResponse(List<CEW316RRepeat> repeats) {
        this.repeats = repeats;
    }

    public List<CEW316RRepeat> getRepeats() {
        return repeats;
    }

    public void setRepeats(List<CEW316RRepeat> repeats) {
        this.repeats = repeats;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Date getHostTxTime() {
        return hostTxTime;
    }

    public void setHostTxTime(Date hostTxTime) {
        this.hostTxTime = hostTxTime;
    }

    public String getTxErrorSystemId() {
        return txErrorSystemId;
    }

    public void setTxErrorSystemId(String txErrorSystemId) {
        this.txErrorSystemId = txErrorSystemId;
    }
}
