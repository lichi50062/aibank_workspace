package com.tfb.aibank.chl.creditcard.ot003.model;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCOT003020Rq.java
 * 
 * <p>Description:超商繳信用卡款 020 繳款條碼頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/04, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCOT003020Rq implements RqData {
    /** 繳款類別 */
    private String txType;
    /** 繳款金額 */
    private BigDecimal txAmount;

    /**
     * @return the txType
     */
    public String getTxType() {
        return txType;
    }

    /**
     * @param txType
     *            the txType to set
     */
    public void setTxType(String txType) {
        this.txType = txType;
    }

    /**
     * @return the txAmount
     */
    public BigDecimal getTxAmount() {
        return txAmount;
    }

    /**
     * @param txAmount
     *            the txAmount to set
     */
    public void setTxAmount(BigDecimal txAmount) {
        this.txAmount = txAmount;
    }

}
