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
import jakarta.xml.bind.annotation.XmlAttribute;

// @formatter:off
/**
 * @(#)Record.java
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
public class Record {

    private List<Field> FIELD = new ArrayList<Field>();

    /**
     * BOND8030 的 rcvFromHost 有 ITERATION
     */
    private List<Iteration> ITERATION = null;

    @XmlAttribute
    protected String id = "";

    // ------------------------------------------------------

    public List<Field> getFieldList() {
        return FIELD;
    }

    public void setFieldList(List<Field> fieldList) {
        this.FIELD = fieldList;
    }

    public void addField(Field field) {
        FIELD.add(field);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Iteration> getITERATION() {
        return ITERATION;
    }

    public void setITERATION(List<Iteration> iTERATION) {
        ITERATION = iTERATION;
    }

}
