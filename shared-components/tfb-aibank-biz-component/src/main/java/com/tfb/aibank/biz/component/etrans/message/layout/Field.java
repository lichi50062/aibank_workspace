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

// @formatter:off
/**
 * @(#)Field.java
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
public class Field {

    public static final String TYPE_CHARACTER = "CHARACTER";
    public static final String TYPE_BINARY = "BINARY";
    public static final String ALIGN_RIGHT = "RIGHT";
    public static final String ALIGN_LEFT = "LEFT";

    @XmlAttribute
    protected String id = "";

    @XmlAttribute
    protected String maxLen = "";

    @XmlAttribute
    protected String minLen = "";

    @XmlAttribute
    protected String padding = "";

    @XmlAttribute
    protected String align = "";

    @XmlAttribute
    protected String type = "";

    @XmlAttribute(name = "default")
    protected String attr_default = "";

    // --------------------------------------------

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaxLen() {
        return maxLen;
    }

    public void setMaxLen(String maxLen) {
        this.maxLen = maxLen;
    }

    public String getMinLen() {
        return minLen;
    }

    public void setMinLen(String minLen) {
        this.minLen = minLen;
    }

    public String getPadding() {
        return padding;
    }

    public void setPadding(String padding) {
        this.padding = padding;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttr_default() {
        return attr_default;
    }

    public void setAttr_default(String attr_default) {
        this.attr_default = attr_default;
    }

}
