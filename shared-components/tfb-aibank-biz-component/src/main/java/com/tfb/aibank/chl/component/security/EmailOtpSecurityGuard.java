/**
 * 
 */
package com.tfb.aibank.chl.component.security;

import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthKeepData;
import com.tfb.aibank.chl.type.TxSecurityStepType;
import com.tfb.aibank.common.model.TxnResult;

//@formatter:off
/**
* @(#)EmailOtpSecurityGuard.java
* 
* <p>Description:交易安控驗證資料 Email-OTP </p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/27, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class EmailOtpSecurityGuard {

    /** 交易結果 */
    private TxnResult txnResult;

    /** 初始email */
    private String originEmail;

    /** 是否有初始email */
    private Boolean isHasOriginEmail;

    /** 新email */
    private String newEmail;

    /** 驗證流程暫存資料 */
    private OtpAuthKeepData otpAuthKeepData;

    /** 交易安控機制 - 執行步驟類別 */
    private TxSecurityStepType txSecurityStepType = TxSecurityStepType.UNKNOWN;

    /**
     * @return the txnResult
     */
    public TxnResult getTxnResult() {
        return txnResult;
    }

    /**
     * @param txnResult
     *            the txnResult to set
     */
    public void setTxnResult(TxnResult txnResult) {
        this.txnResult = txnResult;
    }

    /**
     * @return the originEmail
     */
    public String getOriginEmail() {
        return originEmail;
    }

    /**
     * @param originEmail
     *            the originEmail to set
     */
    public void setOriginEmail(String originEmail) {
        this.originEmail = originEmail;
    }

    /**
     * @return the isHasOriginEmail
     */
    public Boolean getIsHasOriginEmail() {
        return isHasOriginEmail;
    }

    /**
     * @param isHasOriginEmail
     *            the isHasOriginEmail to set
     */
    public void setIsHasOriginEmail(Boolean isHasOriginEmail) {
        this.isHasOriginEmail = isHasOriginEmail;
    }

    /**
     * @return the newEmail
     */
    public String getNewEmail() {
        return newEmail;
    }

    /**
     * @param newEmail
     *            the newEmail to set
     */
    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    /**
     * @return the otpAuthKeepData
     */
    public OtpAuthKeepData getOtpAuthKeepData() {
        return otpAuthKeepData;
    }

    /**
     * @param otpAuthKeepData
     *            the otpAuthKeepData to set
     */
    public void setOtpAuthKeepData(OtpAuthKeepData otpAuthKeepData) {
        this.otpAuthKeepData = otpAuthKeepData;
    }

    /**
     * @return the txSecurityStepType
     */
    public TxSecurityStepType getTxSecurityStepType() {
        return txSecurityStepType;
    }

    /**
     * @param txSecurityStepType
     *            the txSecurityStepType to set
     */
    public void setTxSecurityStepType(TxSecurityStepType txSecurityStepType) {
        this.txSecurityStepType = txSecurityStepType;
    }

}
