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

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NSTOT008020Rs.java
 * 
 * <p>Description:裝置綁定條款頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/11, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NSTOT008020Rs implements RsData {

    /** 條款標題 */
    private String contractTitle;

    /** 條款內文 */
    private String contractContent;

    /**
     * @return the contractTitle
     */
    public String getContractTitle() {
        return contractTitle;
    }

    /**
     * @param contractTitle
     *            the contractTitle to set
     */
    public void setContractTitle(String contractTitle) {
        this.contractTitle = contractTitle;
    }

    /**
     * @return the contractContent
     */
    public String getContractContent() {
        return contractContent;
    }

    /**
     * @param contractContent
     *            the contractContent to set
     */
    public void setContractContent(String contractContent) {
        this.contractContent = contractContent;
    }

}
