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
package com.tfb.aibank.chl.component.userdatacache.model;

import java.util.List;

// @formatter:off
/**
 * @(#)EB572667Res.java
 * 
 * <p>Description:查詢貸款繳費明細(貸款類型=存單質借)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/11/16, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB572667Res {

    private List<LoanDetailBean> repeats;

    /**
     * @return {@link #repeats}
     */
    public List<LoanDetailBean> getRepeats() {
        return repeats;
    }

    /**
     * @param repeats
     *            {@link #repeats}
     */
    public void setRepeats(List<LoanDetailBean> repeats) {
        this.repeats = repeats;
    }

}
