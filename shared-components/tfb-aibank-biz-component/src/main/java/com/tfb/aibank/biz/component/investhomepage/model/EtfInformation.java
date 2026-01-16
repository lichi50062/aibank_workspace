package com.tfb.aibank.biz.component.investhomepage.model;
//@formatter:off
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema; /**
 * @(#)EtfInformation.java
 *
 * <p>Description:ETF Stock</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/05/24, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class EtfInformation {
    /** 商品代碼 */
    @Schema(description = "商品代碼")
    private String insuranceCode;
    /** 商品名稱 */
    @Schema(description = "商品名稱")
    private String insuranceName;
    /** 近一個月報酬率 */
    @Schema(description = "近一個月報酬率")
    private BigDecimal rtnRate1M;
    /** 市場代碼 */
    @Schema(description = "市場代碼")
    private String marketCode;
    /** 市場 */
    @Schema(description = "市場")
    private String market;

    /** 是否已訂閱 */
    @Schema(description = "是否已訂閱")
    private boolean subscription;

    public String getInsuranceCode() {
        return insuranceCode;
    }

    public void setInsuranceCode(String insuranceCode) {
        this.insuranceCode = insuranceCode;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public BigDecimal getRtnRate1M() {
        return rtnRate1M;
    }

    public void setRtnRate1M(BigDecimal rtnRate1M) {
        this.rtnRate1M = rtnRate1M;
    }

    public String getMarketCode() {
        return marketCode;
    }

    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public boolean isSubscription() {
        return subscription;
    }

    public void setSubscription(boolean subscription) {
        this.subscription = subscription;
    }
}
