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
package com.ibm.tw.ibmb.base;

// @formatter:off
/**
 * @(#)Constants.java
 * 
 * <p>Description:平台用的常數</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class Constants {

    /** 錯誤顯示方式 0:統一錯誤頁面 */
    public static final String ERROR_DISPLAY_NORMAL = "0";
    /** 錯誤顯示方式 1:彈出顯示 */
    public static final String ERROR_DISPLAY_ALERT = "1";
    /** 錯誤顯示方式 2:統一錯誤頁面,但只有錯誤描述 */
    public static final String ERROR_DISPLAY_DESC = "2";
    /** 錯誤顯示方式 3:欄位錯誤 */
    public static final String ERROR_DISPLAY_FIELD = "3";

    /** 狀態碼 0000:成功 */
    public static final String STATUS_CODE_SUCCESS = "0000";

    /** Rq Class Suffix */
    public static final String MSG_RQ_SUFFIX = "Rq";

    /** Rs Class Suffix */
    public static final String MSG_RS_SUFFIX = "Rs";

    /** ESB cmDvcFIDOSvcAdd 交易驗證fido function code */
    public static final String CMDVCFIDOSVCADD_FUNC_VERIFY_TRX = "VerifyTxnSignatureFIDO";

    /** 系統代號參照 */
    public static final String SERVICE_CODE_REFERENCE = "SERVICE_ID";
    /** EAI 01 錯誤代碼參照 */
    public static final String EXTRA_CODE_REFERENCE = "EXTRA_CODE";

}
