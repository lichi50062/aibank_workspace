package com.tfb.aibank.biz.component.investhomepage.model;
//@formatter:off
import java.math.BigDecimal;import io.swagger.v3.oas.annotations.media.Schema; /**
 * @(#)FundInformation.java
 *
 * <p>Description:基金</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/05/24, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class FundInformation {
    /** tab */
    @Schema(description = "tab")
    private String tab;
    /** 基金代碼 */
    @Schema(description = "基金代碼")
    private String fundCode;
    /** 基金名稱 */
    @Schema(description = "基金名稱")
    private String fundName;
    /** 配息方式內容 */
    @Schema(description = "配息方式內容")
    private String typeName;
    /** 計價幣別內容 */
    @Schema(description = "計價幣別內容")
    private String currencyCode;
    /** 計價幣別名稱 */
    @Schema(description = "計價幣別名稱")
    private String currencyName;
    /** 後收型 */
    @Schema(description = "後收型")
    private String isBackend;
    /** 報酬率 */
    @Schema(description = "報酬率")
    private BigDecimal yearValue1;
    /** 顯示紅心 */
    @Schema(description = "顯示紅心")
    private boolean showRedHeart;

    /** REMARK_CONTENT.REMARK_KEY */
    @Schema(description = "REMARK_CONTENT.REMARK_KEY")
    private String remarkKey;

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getIsBackend() {
        return isBackend;
    }

    public void setIsBackend(String isBackend) {
        this.isBackend = isBackend;
    }

    public BigDecimal getYearValue1() {
        return yearValue1;
    }

    public void setYearValue1(BigDecimal yearValue1) {
        this.yearValue1 = yearValue1;
    }

    public String getRemarkKey() {
        return remarkKey;
    }

    public void setRemarkKey(String remarkKey) {
        this.remarkKey = remarkKey;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public boolean isShowRedHeart() {
        return showRedHeart;
    }

    public void setShowRedHeart(boolean showRedHeart) {
        this.showRedHeart = showRedHeart;
    }
}
