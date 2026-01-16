/**
 * 
 */
package com.tfb.aibank.chl.creditcard.resource.dto;

//@formatter:off
/**
* @(#)CardSettingCashPwdRequest.java
* 
* <p>Description:CARD_SETTING_CASH_PWD 資料表新增/更新 Request</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/10/12, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class UpdateCardSettingCashPwdRequest {

    /** 鍵值 */
    private Integer borrowKey;

    /** 交易狀態 */
    private String txStatus;

    /** 交易錯誤代碼 */
    private String txErrorCode;

    /** 交易錯誤訊息 */
    private String txErrorDesc;

    /** 錯誤系統代碼 */
    private String txErrorSystemId;

    /**
     * @return the borrowKey
     */
    public Integer getBorrowKey() {
        return borrowKey;
    }

    /**
     * @param borrowKey
     *            the borrowKey to set
     */
    public void setBorrowKey(Integer borrowKey) {
        this.borrowKey = borrowKey;
    }

    /**
     * @return the txStatus
     */
    public String getTxStatus() {
        return txStatus;
    }

    /**
     * @param txStatus
     *            the txStatus to set
     */
    public void setTxStatus(String txStatus) {
        this.txStatus = txStatus;
    }

    /**
     * @return the txErrorCode
     */
    public String getTxErrorCode() {
        return txErrorCode;
    }

    /**
     * @param txErrorCode
     *            the txErrorCode to set
     */
    public void setTxErrorCode(String txErrorCode) {
        this.txErrorCode = txErrorCode;
    }

    /**
     * @return the txErrorDesc
     */
    public String getTxErrorDesc() {
        return txErrorDesc;
    }

    /**
     * @param txErrorDesc
     *            the txErrorDesc to set
     */
    public void setTxErrorDesc(String txErrorDesc) {
        this.txErrorDesc = txErrorDesc;
    }

    /**
     * @return the txErrorSystemId
     */
    public String getTxErrorSystemId() {
        return txErrorSystemId;
    }

    /**
     * @param txErrorSystemId
     *            the txErrorSystemId to set
     */
    public void setTxErrorSystemId(String txErrorSystemId) {
        this.txErrorSystemId = txErrorSystemId;
    }

}
