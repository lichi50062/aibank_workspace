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

@XmlAccessorType(XmlAccessType.FIELD)
public class Iteration {

    private List<Field> FIELD = new ArrayList<Field>();

    @XmlAttribute
    protected String id = "";

    @XmlAttribute
    protected String secLen = "";

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

    public String getSecLen() {
        return secLen;
    }

    public void setSecLen(String secLen) {
        this.secLen = secLen;
    }

}
