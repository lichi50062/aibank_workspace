/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.chl.system.ot015.model;

import java.util.HashMap;
import java.util.Map;

//@formatter:off
/**
* @(#)NSTOT015Cache.java
* 
* <p>Description: NSTOT015 cache</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/05/16, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NSTOT015Cache {

    /**
     * 紀錄各交易廣告版位
     */
    private Map<String, AdCardInfoContainer> txnAdCardMap = new HashMap<>();

    public Map<String, AdCardInfoContainer> getTxnAdCardMap() {
        return txnAdCardMap;
    }

    public void setTxnAdCardMap(Map<String, AdCardInfoContainer> txnAdCardMap) {
        this.txnAdCardMap = txnAdCardMap;
    }
}
