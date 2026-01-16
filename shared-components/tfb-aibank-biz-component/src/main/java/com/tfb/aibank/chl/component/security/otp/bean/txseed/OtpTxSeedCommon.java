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
package com.tfb.aibank.chl.component.security.otp.bean.txseed;

import java.util.Arrays;

import com.ibm.tw.commons.i18n.I18NUtils;

// @formatter:off
/**
 * @(#)OtpTxSeedCommon.java
 * 
 * <p>Description:OTP共用交易因子 - 共用</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/24, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class OtpTxSeedCommon extends OtpTxSeed {

    /**
     * 取得此次交易客製化推播訊息內容
     * 
     * @return
     */
    @Override
    public String getSendMessage() {

        // 【台北富邦銀行】{0}交易驗證碼{1}，交易代碼{2}。驗證碼勿給他人以防詐騙。
        return I18NUtils.getMessage("otp.auth.message", Arrays.asList(TX_NAME_REPLACER, OTP_REPLACER, TX_CODE_REPLACER).toArray());
    }

    /**
     * 組合該次交易相關參數
     * 
     * @return
     */
    @Override
    public String getTxFactors() {

        StringBuilder sb = new StringBuilder();
        sb.append("000").append("&");
        sb.append("000000000000000").append("&");
        sb.append("000").append("&");
        sb.append("000000000000000").append("&");
        sb.append("0");
        return sb.toString();
    }

}
