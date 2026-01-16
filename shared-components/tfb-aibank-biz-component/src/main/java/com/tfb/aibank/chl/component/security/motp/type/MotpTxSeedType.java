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
package com.tfb.aibank.chl.component.security.motp.type;

//@formatter:off
/**
* @(#)MotpTxSeedType.java
* 
* <p>Description:MOTP交易類型</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/14, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum MotpTxSeedType {

    /**
     * 一般認證交易
     */
    COMMON,

    /**
     * 轉帳
     */
    TRANSFER;
}
