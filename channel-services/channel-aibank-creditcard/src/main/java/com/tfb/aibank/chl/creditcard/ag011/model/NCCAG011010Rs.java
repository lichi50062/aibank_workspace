package com.tfb.aibank.chl.creditcard.ag011.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCAG011010Rs.java
 * 
 * <p>Description:好市多會費代扣繳申請 010 </p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/05, Leiley
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG011010Rs implements RsData {

    /** 是否已申請會費代扣繳 */
    private boolean isApply;

    /**
     * @return the isApply
     */
    public boolean isApply() {
        return isApply;
    }

    /**
     * @param isApply
     *            the isApply to set
     */
    public void setApply(boolean isApply) {
        this.isApply = isApply;
    }
}
