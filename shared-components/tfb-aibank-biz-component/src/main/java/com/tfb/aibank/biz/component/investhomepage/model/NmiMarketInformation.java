package com.tfb.aibank.biz.component.investhomepage.model;
//@formatter:off
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;import jakarta.persistence.Basic;import jakarta.persistence.Column;import jakarta.persistence.Id; /**
 * @(#)OverSeaBondInformation.java
 *
 * <p>Description:奈米投</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/05/27, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class NmiMarketInformation {
    /**
     * 奈米投投資策略，1:奈米1號全球ETF；2:奈米2號台灣ETF
     */
    @Schema(description = "奈米投投資策略，1:奈米1號全球ETF；2:奈米2號台灣ETF")
    private String strategy;
    /** 奈米投名稱 */
    @Schema(description = "奈米投名稱")
    private String nmiLevel;
    /** 近一年報酬率 */
    @Schema(description = "近一年報酬率")
    private String startDateYear1;

    /**
     * 近一年_投組績效
     */
    private BigDecimal totalReturnYear1;

    public String getNmiLevel() {
        return nmiLevel;
    }

    public void setNmiLevel(String nmiLevel) {
        this.nmiLevel = nmiLevel;
    }

    public String getStartDateYear1() {
        return startDateYear1;
    }

    public void setStartDateYear1(String startDateYear1) {
        this.startDateYear1 = startDateYear1;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public BigDecimal getTotalReturnYear1() {
        return totalReturnYear1;
    }

    public void setTotalReturnYear1(BigDecimal totalReturnYear1) {
        this.totalReturnYear1 = totalReturnYear1;
    }
}
