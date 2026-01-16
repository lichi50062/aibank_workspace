package com.tfb.aibank.chl.creditcard.tx001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.Constants;
import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NCCTX001040Rs.java
*
* <p>Description:預借現金 結果頁</p>
*
* <p>Modify History:</p>
* v1.0, 2023/09/22 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCTX001040Rs implements RsData {

    /** 是否成功 */
    private boolean isSuccess;

    /** 交易時間 */
    private String hostTxTime;

    /** 預借現金金額 */
    private String amount;

    /** 現金手續費 */
    private String fee;

    /** 總還款金額 */
    private String totalAmount;

    /** 卡片 */
    private String cardName;

    /** 入帳帳號 */
    private String accountName;

    /** 跨行 */
    private boolean isOtherBank;

    /** 開立數位存款ＵＲＬ */
    private String digitalDepositUrl;

    /** 信用卡客服電話 */
    private String helpPhone;

    /** 跨行手續費 */
    private String interbankFee;

    /** 系統代碼 */
    private String systemId = "";

    /** 錯誤代碼 */
    private String errorCode = Constants.STATUS_CODE_SUCCESS;

    /** 狀態描述 */
    private String errorDesc = "";

    /**
     * @return the interbankFee
     */
    public String getInterbankFee() {
        return interbankFee;
    }

    /**
     * @param interbankFee
     *            the interbankFee to set
     */
    public void setInterbankFee(String interbankFee) {
        this.interbankFee = interbankFee;
    }

    /**
     * @return the hostTxTime
     */
    public String getHostTxTime() {
        return hostTxTime;
    }

    /**
     * @param hostTxTime
     *            the hostTxTime to set
     */
    public void setHostTxTime(String hostTxTime) {
        this.hostTxTime = hostTxTime;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount
     *            the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return the fee
     */
    public String getFee() {
        return fee;
    }

    /**
     * @param fee
     *            the fee to set
     */
    public void setFee(String fee) {
        this.fee = fee;
    }

    /**
     * @return the totalAmount
     */
    public String getTotalAmount() {
        return totalAmount;
    }

    /**
     * @param totalAmount
     *            the totalAmount to set
     */
    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * @return the cardName
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * @param cardName
     *            the cardName to set
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /**
     * @return the accountName
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * @param accountName
     *            the accountName to set
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * @return the isOtherBank
     */
    public boolean isOtherBank() {
        return isOtherBank;
    }

    /**
     * @param isOtherBank
     *            the isOtherBank to set
     */
    public void setOtherBank(boolean isOtherBank) {
        this.isOtherBank = isOtherBank;
    }

    /**
     * @return the digitalDepositUrl
     */
    public String getDigitalDepositUrl() {
        return digitalDepositUrl;
    }

    /**
     * @param digitalDepositUrl
     *            the digitalDepositUrl to set
     */
    public void setDigitalDepositUrl(String digitalDepositUrl) {
        this.digitalDepositUrl = digitalDepositUrl;
    }

    /**
     * @return the isSuccess
     */
    public boolean isSuccess() {
        return isSuccess;
    }

    /**
     * @param isSuccess
     *            the isSuccess to set
     */
    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    /**
     * @return the systemId
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * @param systemId
     *            the systemId to set
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode
     *            the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorDesc
     */
    public String getErrorDesc() {
        return errorDesc;
    }

    /**
     * @param errorDesc
     *            the errorDesc to set
     */
    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    /**
     * @return the helpPhone
     */
    public String getHelpPhone() {
        return helpPhone;
    }

    /**
     * @param helpPhone
     *            the helpPhone to set
     */
    public void setHelpPhone(String helpPhone) {
        this.helpPhone = helpPhone;
    }

}
