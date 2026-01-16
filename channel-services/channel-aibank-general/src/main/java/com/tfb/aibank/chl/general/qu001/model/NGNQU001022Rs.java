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

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.chl.general.resource.dto.AiDataSyncStatusModel;

//@formatter:off
/**
* @(#).java
* 
* <p>Description:</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/03/06, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NGNQU001022Rs implements RsData {

    private String securStatus;

    private String requestItemSent;

    public String getSecurStatus() {
        return securStatus;
    }

    public void setSecurStatus(String securStatus) {
        this.securStatus = securStatus;
    }

    public String getRequestItemSent() {
        return requestItemSent;
    }

    public void setRequestItemSent(String requestItemSent) {
        this.requestItemSent = requestItemSent;
    }
}
