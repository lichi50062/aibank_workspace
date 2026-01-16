package com.tfb.aibank.chl.general.qu003.model;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NGNQU003050Rs.java
 * 
 * <p>Description:優惠 050 點數兌換頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/14, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NGNQU003050RsData implements RsData {
    // 點數餘額
    private BigDecimal bonBsav;
    // 到期點數
    private BigDecimal bonEamt;
    // 熱門福華點兌換
    private String eday;

    public BigDecimal getBonBsav() {
        return bonBsav;
    }

    public void setBonBsav(BigDecimal bonBsav) {
        this.bonBsav = bonBsav;
    }

    public BigDecimal getBonEamt() {
        return bonEamt;
    }

    public void setBonEamt(BigDecimal bonEamt) {
        this.bonEamt = bonEamt;
    }

    public String getEday() {
        return eday;
    }

    public void setEday(String eday) {
        this.eday = eday;
    }
}
