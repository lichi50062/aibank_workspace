/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2013.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai;

// @formatter:off
/**
 * @(#)EAIChannel.java
 * 
 * <p>Description:電文發送通道設定</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum EAIChannel {
    /** 個銀 */
    InternetBank,

    /** 企銀 */
    EB,

    /** 基金 */
    AS400,

    /** 行銀 */
    MOBILE,

    /** CBS */
    CBS,

    /** EB對應CBS */
    CBS_EB,

    /** AS400對應CBS */
    CBS_AS400,

    /**  */
    eFamily,

    /** 由網銀進入，使用AS400 */
    IB_AS400,

    /** 由網銀進入，使用CBS */
    IB_CBS;
}
