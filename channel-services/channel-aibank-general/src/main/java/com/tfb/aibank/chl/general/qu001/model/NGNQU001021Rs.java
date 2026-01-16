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
package com.tfb.aibank.chl.general.qu001.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#).java
* 
* <p>Description:</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/03/05, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NGNQU001021Rs implements RsData {

    private List<TopValueProduct> topValueProducts;

    public List<TopValueProduct> getTopValueProducts() {
        return topValueProducts;
    }

    public void setTopValueProducts(List<TopValueProduct> topValueProducts) {
        this.topValueProducts = topValueProducts;
    }
}
