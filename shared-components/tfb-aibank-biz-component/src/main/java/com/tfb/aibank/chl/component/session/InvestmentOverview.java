package com.tfb.aibank.chl.component.session;

// @formatter:off
import java.math.BigDecimal;
import java.util.Map;

import com.tfb.aibank.common.type.InvestItemType; /**
 * @(#)InvestmentOverview.java
 * 
 * <p>Description:[投資總覽-總投資市值]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/19, Leiley
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class InvestmentOverview {

    private BigDecimal investTtlAmt;

    private Map<InvestItemType, BigDecimal> productTypeMap;

    public BigDecimal getInvestTtlAmt() {
        return investTtlAmt;
    }

    public void setInvestTtlAmt(BigDecimal investTtlAmt) {
        this.investTtlAmt = investTtlAmt;
    }

    /**
     * @return the productTypeMap
     */
    public Map<InvestItemType, BigDecimal> getProductTypeMap() {
        return productTypeMap;
    }

    /**
     * @param productTypeMap
     *            the productTypeMap to set
     */
    public void setProductTypeMap(Map<InvestItemType, BigDecimal> productTypeMap) {
        this.productTypeMap = productTypeMap;
    }

}
