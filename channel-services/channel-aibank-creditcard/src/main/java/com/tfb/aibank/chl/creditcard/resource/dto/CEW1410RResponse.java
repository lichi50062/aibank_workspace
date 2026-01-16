package com.tfb.aibank.chl.creditcard.resource.dto;

import java.util.List;

/**
 * 信用卡掛失 rs
 * 
 * @author Evan Wang
 *
 */
public class CEW1410RResponse {
    /** 結果 */
    private String result;

    /** 功能別 */
    private String func;

    /** 持卡人ID */
    private String cardHolderId;

    /** 正卡卡號 */
    private String primaryCardNo;

    /** 附卡卡號 */
    private List<String> supplementaryCardNos;

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result
     *            the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * @return the func
     */
    public String getFunc() {
        return func;
    }

    /**
     * @param func
     *            the func to set
     */
    public void setFunc(String func) {
        this.func = func;
    }

    /**
     * @return the cardHolderId
     */
    public String getCardHolderId() {
        return cardHolderId;
    }

    /**
     * @param cardHolderId
     *            the cardHolderId to set
     */
    public void setCardHolderId(String cardHolderId) {
        this.cardHolderId = cardHolderId;
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

}
