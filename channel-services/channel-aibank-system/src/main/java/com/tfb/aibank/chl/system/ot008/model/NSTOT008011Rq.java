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
package com.tfb.aibank.chl.system.ot008.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NSTOT008011Rq.java
 * 
 * <p>Description:裝置綁定完成通知</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/11, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NSTOT008011Rq implements RqData {

    /** 變更綁定裝置 */
    private boolean changeDevice;

    /**
     * @return the changeDevice
     */
    public boolean isChangeDevice() {
        return changeDevice;
    }

    /**
     * @param changeDevice
     *            the changeDevice to set
     */
    public void setChangeDevice(boolean changeDevice) {
        this.changeDevice = changeDevice;
    }

}
