package com.tfb.aibank.chl.creditcard.resource.dto;

/**
 * 信用卡掛失紀錄 rs
 * 
 * @author Evan Wang
 *
 */
public class CreateCardLossRecordResponse {
    /** 新增資料鍵值 */
    private Long lossKey;

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

}
