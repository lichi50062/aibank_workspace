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

import java.math.BigDecimal;
import java.util.Arrays;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.component.security.otp.type.OtpTxSeedTransferType;

// @formatter:off
/**
 * @(#)OtpTxSeedTransfer.java
 * 
 * <p>Description:OTP共用交易因子 - 轉帳</p>
 * <ul>
 * <li>臺幣轉帳:【台北富邦銀行】臺幣轉帳交易驗證碼XXXXXX，交易代碼XXXXX，轉入帳號後4碼 XXXX，臺幣 XXXXX元。驗證碼勿給他人以防詐騙。</li>
 * <li>外幣轉帳:【台北富邦銀行】外幣轉帳交易驗證碼XXXXXX，交易代碼XXXXX，轉入帳號後4碼 XXXX，X幣 XXXXX元。驗證碼勿給他人以防詐騙。</li>
 * <li>繳卡費(他人):【台北富邦銀行】繳卡費交易驗證碼：XXXXXX，交易代碼XXXXX，繳款帳號後4碼 XXXX，臺幣 XXXXX元。驗證碼勿給他人以防詐騙。</li>
 * </ul>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/24, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class OtpTxSeedTransfer extends OtpTxSeed {

    /** OTP 交易資訊 - 轉出銀行代碼 */
    private String outBank = null;

    /** OTP 交易資訊 - 轉出帳號 */
    private String outAcct = null;

    /** OTP 交易資訊 - 轉入銀行代碼 */
    private String inBank = null;

    /** OTP 交易資訊 - 轉入帳號 */
    private String inAcct = null;

    /** OTP 交易資訊 - 轉帳金額 */
    private BigDecimal txAmt = null;

    /** 交易幣別 */
    private String txCur;

    /** 交易幣別 */
    private String txCurName;

    /**
     * 轉帳方式
     * 
     * 0. 轉入帳號
     * 
     * 1. 手機號碼
     */
    private int transferType;

    /** OTP共用交易因子 - 轉帳類型 */
    private OtpTxSeedTransferType otpTxSeedTransferType = OtpTxSeedTransferType.TW_TRANSFER;

    /**
     * @return the outBank
     */
    public String getOutBank() {
        return outBank;
    }

    /**
     * @param outBank
     *            the outBank to set
     */
    public void setOutBank(String outBank) {
        this.outBank = outBank;
    }

    /**
     * @return the outAcct
     */
    public String getOutAcct() {
        return outAcct;
    }

    /**
     * @param outAcct
     *            the outAcct to set
     */
    public void setOutAcct(String outAcct) {
        this.outAcct = outAcct;
    }

    /**
     * @return the inBank
     */
    public String getInBank() {
        return inBank;
    }

    /**
     * @param inBank
     *            the inBank to set
     */
    public void setInBank(String inBank) {
        this.inBank = inBank;
    }

    /**
     * @return the inAcct
     */
    public String getInAcct() {
        return inAcct;
    }

    /**
     * @param inAcct
     *            the inAcct to set
     */
    public void setInAcct(String inAcct) {
        this.inAcct = inAcct;
    }

    /**
     * @return the txAmt
     */
    public BigDecimal getTxAmt() {
        return txAmt;
    }

    /**
     * @param txAmt
     *            the txAmt to set
     */
    public void setTxAmt(BigDecimal txAmt) {
        this.txAmt = txAmt;
    }

    /**
     * @return the txCur
     */
    public String getTxCur() {
        return txCur;
    }

    /**
     * @param txCur
     *            the txCur to set
     */
    public void setTxCur(String txCur) {
        this.txCur = txCur;
    }

    /**
     * @return the txCurName
     */
    public String getTxCurName() {
        return txCurName;
    }

    /**
     * @param txCurName
     *            the txCurName to set
     */
    public void setTxCurName(String txCurName) {
        this.txCurName = txCurName;
    }

    /**
     * @return the otpTxSeedTransferType
     */
    public OtpTxSeedTransferType getOtpTxSeedTransferType() {
        return otpTxSeedTransferType;
    }

    /**
     * @param otpTxSeedTransferType
     *            the otpTxSeedTransferType to set
     */
    public void setOtpTxSeedTransferType(OtpTxSeedTransferType otpTxSeedTransferType) {
        this.otpTxSeedTransferType = otpTxSeedTransferType;
    }

    /**
     * @return the transferType
     */
    public int getTransferType() {
        return transferType;
    }

    /**
     * @param transferType
     *            the transferType to set
     */
    public void setTransferType(int transferType) {
        this.transferType = transferType;
    }

    /**
     * 取得此次交易客製化推播訊息內容
     * 
     * @return
     */
    @Override
    public String getSendMessage() {

        if (otpTxSeedTransferType.isTwTransfer()) {

            if (transferType == 0) {
                // 【台北富邦銀行】臺幣轉帳交易驗證碼{0}，交易代碼{1}，轉入帳號後4碼 {2}，臺幣 {3}元。驗證碼勿給他人以防詐騙。
                return I18NUtils.getMessage("otp.auth.message-transfer-tw", Arrays.asList(OTP_REPLACER, TX_CODE_REPLACER, StringUtils.right(inAcct, 4), IBUtils.formatMoney(txAmt)).toArray());
            }
            else {
                // 【台北富邦銀行】臺幣轉帳交易驗證碼{0}，交易代碼{1}，手機號碼後4碼 {2}，臺幣 {3}元。驗證碼勿給他人以防詐騙。
                return I18NUtils.getMessage("otp.auth.message-transfer-tw-mobile", Arrays.asList(OTP_REPLACER, TX_CODE_REPLACER, StringUtils.right(inAcct, 4), IBUtils.formatMoney(txAmt)).toArray());

            }

        }
        else if (otpTxSeedTransferType.isFxTransfer()) {

            // 【台北富邦銀行】外幣轉帳交易驗證碼{0}，交易代碼{1}，轉入帳號後4碼 {2}，{3} {4}元。驗證碼勿給他人以防詐騙。
            return I18NUtils.getMessage("otp.auth.message-transfer-fx", Arrays.asList(OTP_REPLACER, TX_CODE_REPLACER, StringUtils.right(inAcct, 4), txCurName, IBUtils.formatMoney(txAmt, txCur)).toArray());
        }
        else if (otpTxSeedTransferType.isCardPaymentOthers()) {

            // 【台北富邦銀行】繳卡費交易驗證碼：{0}，交易代碼{1}，繳款帳號後4碼 {2}，臺幣 {3}元。驗證碼勿給他人以防詐騙。
            return I18NUtils.getMessage("otp.auth.message-card-payment-others", Arrays.asList(OTP_REPLACER, TX_CODE_REPLACER, StringUtils.right(inAcct, 4), IBUtils.formatMoney(txAmt)).toArray());
        }

        return StringUtils.EMPTY;
    }

    /**
     * 組合該次交易相關參數
     * 
     * @return
     */
    @Override
    public String getTxFactors() {

        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.defaultString(getOutBank())).append("&");
        sb.append(StringUtils.defaultString(getOutAcct())).append("&");
        sb.append(StringUtils.defaultString(getInBank())).append("&");
        sb.append(StringUtils.defaultString(getInAcct())).append("&");
        sb.append(ConvertUtils.bigDecimal2Str(getTxAmt()));
        return sb.toString();
    }

}
