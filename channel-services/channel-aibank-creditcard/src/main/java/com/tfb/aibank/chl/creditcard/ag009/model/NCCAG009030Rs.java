package com.tfb.aibank.chl.creditcard.ag009.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCAG009030Rs.java
 * 
 * <p>Description:信用卡掛失 結果失敗頁 RS</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/22, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on

@Component
public class NCCAG009030Rs implements RsData {

    /** 交易狀態 */
    private String txnStatus;

    /** 錯誤描述 */
    private String errorMsg;

    /** 交易時間 */
    private String txnTime;

    /** 卡片名稱 */
    private String cardName;

    /** 卡種 */
    private String cardCategory;

    /** 正卡卡號 */
    private String primaryCardNo;

    /** 附卡卡號 */
    private List<String> supplementaryCardNos;

    /**
     * @return the txnStatus
     */
    public String getTxnStatus() {
        return txnStatus;
    }

    /**
     * @param txnStatus
     *            the txnStatus to set
     */
    public void setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
    }

    /**
     * @return the errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * @param errorMsg
     *            the errorMsg to set
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * @return the txnTime
     */
    public String getTxnTime() {
        return txnTime;
    }

    /**
     * @param txnTime
     *            the txnTime to set
     */
    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
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
     * @return the cardCategory
     */
    public String getCardCategory() {
        return cardCategory;
    }

    /**
     * @param cardCategory
     *            the cardCategory to set
     */
    public void setCardCategory(String cardCategory) {
        this.cardCategory = cardCategory;
    }

    /**
     * @return the primaryCardNo
     */
    public String getPrimaryCardNo() {
        return primaryCardNo;
    }

    /**
     * @param primaryCardNo
     *            the primaryCardNo to set
     */
    public void setPrimaryCardNo(String primaryCardNo) {
        this.primaryCardNo = primaryCardNo;
    }

    /**
     * @return the supplementaryCardNo
     */
    public List<String> getSupplementaryCardNos() {
        return supplementaryCardNos;
    }

    /**
     * @param supplementaryCardNo
     *            the supplementaryCardNo to set
     */
    public void setSupplementaryCardNos(List<String> supplementaryCardNos) {
        this.supplementaryCardNos = supplementaryCardNos;
    }

}
