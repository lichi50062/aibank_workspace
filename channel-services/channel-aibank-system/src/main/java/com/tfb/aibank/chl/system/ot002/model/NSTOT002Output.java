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
package com.tfb.aibank.chl.system.ot002.model;

import com.tfb.aibank.chl.component.remarkcontent.RemarkContent;

// @formatter:off
/**
 * @(#)NSTOT002010Form.java
 * 
 * <p>Description:資料物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/24, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NSTOT002Output {

    private RemarkContent remarkContent;

    public RemarkContent getRemarkContent() {
        return remarkContent;
    }

    public void setRemarkContent(RemarkContent remarkContent) {
        this.remarkContent = remarkContent;
    }

}
