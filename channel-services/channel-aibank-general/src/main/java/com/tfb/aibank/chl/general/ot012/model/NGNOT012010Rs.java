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
package com.tfb.aibank.chl.general.ot012.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NGNOT012010Rq.java
* 
* <p>Description:共用跳頁功能 010 功能首頁</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/07/11, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NGNOT012010Rs implements RsData {

    private String target;

    private String customParams;

    /**
     * @return the target
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target
     *            the target to set
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * @return the customParams
     */
    public String getCustomParams() {
        return customParams;
    }

    /**
     * @param customParams
     *            the customParams to set
     */
    public void setCustomParams(String customParams) {
        this.customParams = customParams;
    }

}
