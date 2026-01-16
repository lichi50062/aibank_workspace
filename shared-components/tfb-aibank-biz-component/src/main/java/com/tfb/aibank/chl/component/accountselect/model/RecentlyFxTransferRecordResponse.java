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
package com.tfb.aibank.chl.component.accountselect.model;

import java.util.List;

// @formatter:off
/**
 * @(#)RecentlyFxTransferRecordResponse.java
 * 
 * <p>Description:取得最近轉帳紀錄 - Response</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/23, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class RecentlyFxTransferRecordResponse {

    /** 最近轉帳紀錄 */
    private List<RecentlyFxTransferRecord> records;

    /**
     * @return the records
     */
    public List<RecentlyFxTransferRecord> getRecords() {
        return records;
    }

    /**
     * @param records
     *            the records to set
     */
    public void setRecords(List<RecentlyFxTransferRecord> records) {
        this.records = records;
    }

}
