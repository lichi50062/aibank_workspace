package com.tfb.aibank.chl.creditcard.ag009.cache;

import java.util.List;

import com.tfb.aibank.chl.model.creditcard.CreditCard;

// @formatter:off
/**
 * @(#)NCCAG009CacheData.java
 * 
 * <p>Description:信用卡掛失 cache</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/22, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on

public class NCCAG009CacheData {
    /** 掛失卡片資料 */
    private CreditCard creditCard;

    /** 正卡卡號 */
    private String primaryCardNo;

    /** 附卡卡號 */
    private List<String> supplementaryCardNos;

    /** 交易狀態 */
    private String txnStatus;

    /** 錯誤描述 */
    private String errorMsg;

    /**
     * @return the creditCard
     */
    public CreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * @param creditCard
     *            the creditCard to set
     */
    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
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
     * @return the supplementaryCardNos
     */
    public List<String> getSupplementaryCardNos() {
        return supplementaryCardNos;
    }

    /**
     * @param supplementaryCardNos
     *            the supplementaryCardNos to set
     */
    public void setSupplementaryCardNos(List<String> supplementaryCardNos) {
        this.supplementaryCardNos = supplementaryCardNos;
    }

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

}
