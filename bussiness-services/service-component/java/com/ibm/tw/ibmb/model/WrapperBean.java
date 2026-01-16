/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.ibm.tw.ibmb.model;

// @formatter:off
/**
 * @(#)WrapperBean.java
 * 
 * <p>Description:Wrapper Bean</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/02, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class WrapperBean<M> {

    public WrapperBean() {
        // default constructor
    }

    public WrapperBean(M args) {
        this.value = args;
    }

    private M value;

    public M getValue() {
        return value;
    }

    public void setValue(M value) {
        this.value = value;
    }

}
