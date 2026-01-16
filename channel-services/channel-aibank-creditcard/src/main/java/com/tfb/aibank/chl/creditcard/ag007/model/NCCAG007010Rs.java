package com.tfb.aibank.chl.creditcard.ag007.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCAG007010Rs.java
 * 
 * <p>Description:一鍵綁定行動支付 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/08, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG007010Rs implements RsData {

    /** LINE PAY綁定網址 */
    private String linePayBindURL;

    /** 卡號加密 */
    private String coefficient;

    public String getLinePayBindURL() {
        return linePayBindURL;
    }

    public void setLinePayBindURL(String linePayBindURL) {
        this.linePayBindURL = linePayBindURL;
    }

    public String getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(String coefficient) {
        this.coefficient = coefficient;
    }

}
