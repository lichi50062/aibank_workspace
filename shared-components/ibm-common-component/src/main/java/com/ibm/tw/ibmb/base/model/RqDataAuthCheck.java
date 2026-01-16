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
package com.ibm.tw.ibmb.base.model;

public abstract class RqDataAuthCheck implements RqData {

    /**
     * 裝置是否支援生物辨識
     */
    private boolean bioSupport;

    public boolean isBioSupport() {
        return bioSupport;
    }

    public void setBioSupport(boolean bioSupport) {
        this.bioSupport = bioSupport;
    }

}
