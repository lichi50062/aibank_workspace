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

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NGNAG001011Rq.java
* 
* <p>Description:免登速查 - 更新設定 - RQ</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NGNAG001011Rq implements RqData {

    /** 免登速查是否開啟 */
    private boolean qsearchFlag;

    /**
     * @return the qsearchFlag
     */
    public boolean isQsearchFlag() {
        return qsearchFlag;
    }

    /**
     * @param qsearchFlag
     *            the qsearchFlag to set
     */
    public void setQsearchFlag(boolean qsearchFlag) {
        this.qsearchFlag = qsearchFlag;
    }

}
