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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

// @formatter:off
/**
 * @(#)Tx.java
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
@XmlRootElement(name = "TX")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tx {

    private Format FORMAT = new Format();

    @XmlAttribute
    protected String value = "";

    // ------------------------------------------------------

    public Format getFormat() {
        return FORMAT;
    }

    public void setFormat(Format FORMAT) {
        this.FORMAT = FORMAT;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
