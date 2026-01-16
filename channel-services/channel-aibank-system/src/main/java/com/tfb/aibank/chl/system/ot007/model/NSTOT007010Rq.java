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
package com.tfb.aibank.chl.system.ot007.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NSTOT007010Rq.java
* 
* <p>Description:個人化體驗 - RQ</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/08, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NSTOT007010Rq implements RqData {

    /** 版位ID */
    private String actionPointId;
    /** 前頁PageID */
    private String prePageId;
    /** 當夜PageID */
    private String curPageId;

    /**
     * @return the actionPointId
     */
    public String getActionPointId() {
        return actionPointId;
    }

    /**
     * @param actionPointId
     *            the actionPointId to set
     */
    public void setActionPointId(String actionPointId) {
        this.actionPointId = actionPointId;
    }

    public String getPrePageId() {
        return prePageId;
    }

    public void setPrePageId(String prePageId) {
        this.prePageId = prePageId;
    }

    public String getCurPageId() {
        return curPageId;
    }

    public void setCurPageId(String curPageId) {
        this.curPageId = curPageId;
    }
}
