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
package com.tfb.aibank.chl.general.ot001.task.service;

import java.util.Arrays;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.tfb.aibank.chl.component.security.otp.bean.txseed.OtpTxSeedCommon;

// @formatter:off
/**
 * @(#)OtpTxSeedTwoFactor.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/24, bensonlin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class OtpTxSeedTwoFactor extends OtpTxSeedCommon {

    // TODO
    @Override
    public String getSendMessage() {

        // 【台北富邦銀行】{0}交易驗證碼{1}，交易代碼{2}。驗證碼勿給他人以防詐騙。
        return I18NUtils.getMessage("otp.auth.message", Arrays.asList(TX_NAME_REPLACER, OTP_REPLACER, TX_CODE_REPLACER).toArray());
    }
}
