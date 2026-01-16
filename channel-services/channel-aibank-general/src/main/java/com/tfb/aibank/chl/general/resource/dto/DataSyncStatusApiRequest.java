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
package com.tfb.aibank.chl.general.resource.dto;

//@formatter:off
/**
* @(#)DataSyncStatusApiRequest.java
* 
* <p>Description: 欲更新富邦證券/人壽彙整狀態/p>
* 
* <p>Modify History:</p>
* v1.0, 2024/03/06, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class DataSyncStatusApiRequest {

    /**
     * 富邦證券彙整狀態
     */
    private String secur;

    /**
     * 富邦人壽彙整狀態
     */
    private String insur;

    public String getSecur() {
        return secur;
    }

    public void setSecur(String secur) {
        this.secur = secur;
    }

    public String getInsur() {
        return insur;
    }

    public void setInsur(String insur) {
        this.insur = insur;
    }

    @Override
    public String toString() {
        return "DataSyncStatusApiRequest [secur=" + secur + ", insur=" + insur + "]";
    }
}
