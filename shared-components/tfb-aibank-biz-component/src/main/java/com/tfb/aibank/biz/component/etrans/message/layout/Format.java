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
package com.tfb.aibank.biz.component.etrans.message.layout;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

// @formatter:off
/**
 * @(#)Format.java
 * 
 * <p>Description:e化繳費網 晶片卡前置系統 電文格式 xml 設定檔 mapping</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/16, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@XmlAccessorType(XmlAccessType.FIELD)
public class Format {

    private List<Record> RECORD = new ArrayList<Record>();

    public List<Record> getRecordList() {
        return RECORD;
    }

    public void setRecordList(List<Record> recordList) {
        this.RECORD = recordList;
    }

    public void addRecord(Record record) {
        RECORD.add(record);
    }

}
