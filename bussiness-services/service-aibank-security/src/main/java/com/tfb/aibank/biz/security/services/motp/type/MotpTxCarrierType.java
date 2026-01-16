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
package com.tfb.aibank.biz.security.services.motp.type;

//@formatter:off
/**
* @(#)MotpTxCarrierType.java
* 
* <p>Description:MOTP交易推播載具驗證狀態</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/14, HankChan   
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum MotpTxCarrierType {

    OFFLINE(0, "離線載具"),

    PUSH_NOTIFY(1, "推播載具");

    private int code;

    private String desc;

    MotpTxCarrierType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public boolean isOffline() {
        return equals(OFFLINE);
    }

    public boolean isPushNotify() {
        return equals(PUSH_NOTIFY);
    }

}
