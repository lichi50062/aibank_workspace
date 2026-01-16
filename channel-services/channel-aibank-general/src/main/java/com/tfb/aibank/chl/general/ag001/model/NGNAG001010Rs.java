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
package com.tfb.aibank.chl.general.ag001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NGNAG001010Rs.java
* 
* <p>Description:免登速查 - 設定頁 - RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NGNAG001010Rs implements RsData {

    /**
     * 免登速查
     * <ul>
     * <li>1:已開啟</li>
     * <li>0:未開啟</li>
     * </ul>
     */
    private int qsearchFlag;

    /**
     * @return the qsearchFlag
     */
    public int getQsearchFlag() {
        return qsearchFlag;
    }

    /**
     * @param qsearchFlag
     *            the qsearchFlag to set
     */
    public void setQsearchFlag(int qsearchFlag) {
        this.qsearchFlag = qsearchFlag;
    }

}
