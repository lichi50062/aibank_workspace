package com.tfb.aibank.chl.general.qu001.model;



// @formatter:off
import java.math.BigDecimal;
import java.util.Map; /**
 * @(#)InvestmentOverview.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * <ol>v1.0, 2023/6/10, Marty
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class InvestmentOverview {

    private BigDecimal investTtlAmt;

    private Map<String, BigDecimal> productValMap;

    public BigDecimal getInvestTtlAmt() {
        return investTtlAmt;
    }

    public void setInvestTtlAmt(BigDecimal investTtlAmt) {
        this.investTtlAmt = investTtlAmt;
    }

    public Map<String, BigDecimal> getProductValMap() {
        return productValMap;
    }

    public void setProductValMap(Map<String, BigDecimal> productValMap) {
        this.productValMap = productValMap;
    }
}
