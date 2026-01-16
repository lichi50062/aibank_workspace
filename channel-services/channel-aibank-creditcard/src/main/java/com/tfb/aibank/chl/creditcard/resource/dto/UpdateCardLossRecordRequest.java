package com.tfb.aibank.chl.creditcard.resource.dto;

import java.util.Date;

/**
 * 更新掛失資料紀錄 rq
 * 
 * @author Evan Wang
 *
 */
public class UpdateCardLossRecordRequest {

    /** 更新鍵值 */
    private Long lossKey;

    /** 上送主機交易時間 */
    private Date hostTxTime;

    /** 0：交易成功 1：交易失敗 4：交易未明 */
    private String txStatus;

    // TODO TxHead.HERRID或TxBody.ERROR(待確認)
    /** TxHead.HERRID或TxBody.ERROR(待確認) */
    private String txErrorCode;

    /** 依錯誤訊息對照表設定 */
    private String txErrorDesc;

    /** 依錯誤訊息對照表設定 */
    private String txErrorSystemId;

    /** 身分證號 */
    private String custId;

    /** 公司類型 */
    private Integer companyKind;

    /** 用戶代號 */
    private String userId;

    /**
     * @return the lossKey
     */
    public Long getLossKey() {
        return lossKey;
    }

    /**
     * @param lossKey
     *            the lossKey to set
     */
    public void setLossKey(Long lossKey) {
        this.lossKey = lossKey;
    }

    /**
     * @return the hostTxTime
     */
    public Date getHostTxTime() {
        return hostTxTime;
    }

    /**
     * @param hostTxTime
     *            the hostTxTime to set
     */
    public void setHostTxTime(Date hostTxTime) {
        this.hostTxTime = hostTxTime;
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

    /**
     * @return the custId
     */
    public String getCustId() {
        return custId;
    }

    /**
     * @param custId
     *            the custId to set
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    /**
     * @return the companyKind
     */
    public Integer getCompanyKind() {
        return companyKind;
    }

    /**
     * @param companyKind
     *            the companyKind to set
     */
    public void setCompanyKind(Integer companyKind) {
        this.companyKind = companyKind;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

}
