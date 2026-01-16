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
package com.ibm.tw.ibmb.model;

// @formatter:off
/**
 * @(#)LabelValueBean.java
 * 
 * <p>Description:A simple JavaBean to represent label-value pairs.</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/05, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class LabelValueBean {

    public LabelValueBean() {
        // default constructor
    }

    public LabelValueBean(String label, String value) {
        this.label = label;
        this.value = value;
    }

    private String label;

    private String value;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
