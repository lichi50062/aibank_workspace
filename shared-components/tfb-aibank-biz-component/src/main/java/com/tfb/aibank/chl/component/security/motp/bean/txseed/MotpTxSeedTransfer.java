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
package com.tfb.aibank.chl.component.security.motp.bean.txseed;

import java.math.BigDecimal;
import java.util.Arrays;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.component.currencycode.CurrencyCode;
import com.tfb.aibank.chl.component.security.motp.type.MotpTxSeedType;

//@formatter:off
/**
* @(#)MotpTxSeed.java
* 
* <p>Description:MOTP共用交易因子 - 轉帳</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/18, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class MotpTxSeedTransfer extends MotpTxSeed {

    private static MotpTxSeedType motpTxSeedType = MotpTxSeedType.TRANSFER;

    /** 轉出銀行 */
    private String transOutBank;

    /** 轉出帳號 */
    private String transOutAcctNo;

    /** 轉入銀行 */
    private String transInBank;

    /** 轉入帳號 */
    private String transInAcctNo;

    /** 交易幣別 */
    private String txCurr;

    /** 交易幣別名稱 */
    private String txCurrName;

    /** 金額 */
    private BigDecimal txAmt;

    /** 是否為手機轉帳 */
    private boolean isMobileTransfer;

    /** 轉入電話號碼 */
    private String transInMobile;

    public MotpTxSeedTransfer(String taskId, String taskName) {
        super();
        super.setTaskId(taskId);
        super.setTaskName(taskName);
    }

    @Override
    public MotpTxSeedType getMotpTxSeedType() {
        return motpTxSeedType;
    }

    @Override
    public String getTxFactor() {
        StringBuilder sb = new StringBuilder();
        sb.append(transOutBank).append("&");
        sb.append(StringUtils.leftPadZero(StringUtils.defaultString(transOutAcctNo), 16)).append("&");
        sb.append(transInBank).append("&");
        sb.append(StringUtils.leftPadZero(StringUtils.defaultString(transInAcctNo), 16)).append("&");
        sb.append(txCurr).append("&");
        sb.append(StringUtils.leftPadZero(txAmt.toPlainString(), 20)).append("&");
        sb.append(isMobileTransfer).append("&");
        sb.append(StringUtils.defaultString(transInMobile));
        return sb.toString();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * 取得此次交易客製化推播訊息
     * <p>
     * 富邦行動銀行「{0}」交易確認通知，交易代號{1}，請點選查看
     * </p>
     */
    @Override
    public String getTitle() {
        return I18NUtils.getMessage("motp.auth.message.title") + "@" + I18NUtils.getMessage("motp.auth.message.transfer.title", Arrays.asList(getTaskName(), TX_CODE_REPLACER).toArray());
    }

    /**
     * 取得此次交易客製化推播訊息
     * <p>
     * 【交易驗證碼】你於富邦AIBank的轉帳交易驗證碼 {0}， 交易代碼 {1}，請核對轉入{2}後 4 碼 {3}，金額為{4} {5} 元。 (有效時間 {6}，依本行系統時間為主)
     * </p>
     */
    @Override
    public String getMessage() {
        String transInType = isMobileTransfer ? I18NUtils.getMessage("motp.auth.message.transfer.mobile-no") : I18NUtils.getMessage("motp.auth.message.transfer.account");
        String transInNo = isMobileTransfer ? StringUtils.right(transInMobile, 4) : StringUtils.right(transInAcctNo, 4);
        String txAmtStr;
        if (StringUtils.equalsAny(txCurr, CurrencyCode.TWD, CurrencyCode.JPY)) {
            txAmtStr = StringUtils.getMoneyStr(txAmt.toPlainString(), 0);
        }
        else {
            txAmtStr = StringUtils.getMoneyStr(txAmt.toPlainString(), 2);
        }
        return I18NUtils.getMessage("motp.auth.message.transfer.content", Arrays.asList(MOTP_REPLACER, TX_CODE_REPLACER, transInType, transInNo, txCurrName, txAmtStr, AUTH_DEADLINE_REPLACER).toArray());
    }

    /**
     * @return the transOutBank
     */
    public String getTransOutBank() {
        return transOutBank;
    }

    /**
     * @param transOutBank
     *            the transOutBank to set
     */
    public void setTransOutBank(String transOutBank) {
        this.transOutBank = transOutBank;
    }

    /**
     * @return the transOutAcctNo
     */
    public String getTransOutAcctNo() {
        return transOutAcctNo;
    }

    /**
     * @param transOutAcctNo
     *            the transOutAcctNo to set
     */
    public void setTransOutAcctNo(String transOutAcctNo) {
        this.transOutAcctNo = transOutAcctNo;
    }

    /**
     * @return the transInBank
     */
    public String getTransInBank() {
        return transInBank;
    }

    /**
     * @param transInBank
     *            the transInBank to set
     */
    public void setTransInBank(String transInBank) {
        this.transInBank = transInBank;
    }

    /**
     * @return the transInAcctNo
     */
    public String getTransInAcctNo() {
        return transInAcctNo;
    }

    /**
     * @param transInAcctNo
     *            the transInAcctNo to set
     */
    public void setTransInAcctNo(String transInAcctNo) {
        this.transInAcctNo = transInAcctNo;
    }

    /**
     * @return the txCurr
     */
    public String getTxCurr() {
        return txCurr;
    }

    /**
     * @param txCurr
     *            the txCurr to set
     */
    public void setTxCurr(String txCurr) {
        this.txCurr = txCurr;
    }

    /**
     * @return the txCurrName
     */
    public String getTxCurrName() {
        return txCurrName;
    }

    /**
     * @param txCurrName
     *            the txCurrName to set
     */
    public void setTxCurrName(String txCurrName) {
        this.txCurrName = txCurrName;
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
     * @return the isMobileTransfer
     */
    public boolean isMobileTransfer() {
        return isMobileTransfer;
    }

    /**
     * @param isMobileTransfer
     *            the isMobileTransfer to set
     */
    public void setMobileTransfer(boolean isMobileTransfer) {
        this.isMobileTransfer = isMobileTransfer;
    }

    /**
     * @return the transInMobile
     */
    public String getTransInMobile() {
        return transInMobile;
    }

    /**
     * @param transInMobile
     *            the transInMobile to set
     */
    public void setTransInMobile(String transInMobile) {
        this.transInMobile = transInMobile;
    }

}
