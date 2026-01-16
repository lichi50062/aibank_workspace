package com.tfb.aibank.chl.system.ot010.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NSTOT010020Rq.java
 * 
 * <p>Description:數位客服</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/12, John Chang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NSTOT010020Rq implements RqData {

    /**
     * 錯誤碼
     */
    private String errorCode;

    /**
     * 要求源
     */
    private String fromDevice;

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
     * @return the fromDevice
     */
    public String getFromDevice() {
        return fromDevice;
    }

    /**
     * @param fromDevice
     *            the fromDevice to set
     */
    public void setFromDevice(String fromDevice) {
        this.fromDevice = fromDevice;
    }

}
