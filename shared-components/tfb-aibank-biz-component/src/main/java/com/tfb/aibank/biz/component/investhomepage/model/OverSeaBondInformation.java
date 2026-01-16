package com.tfb.aibank.biz.component.investhomepage.model;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
 * @(#)OverSeaBondInformation.java
 *
 * <p>Description:海外債</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/05/27, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class OverSeaBondInformation {
    /** 海外債券代碼 */
    @Schema(description = "海外債券代碼")
    private String bondNo;
    /** 海外債券名稱 */
    @Schema(description = "海外債券名稱")
    private String bondName;
    /** 參考殖利率 */
    @Schema(description = "參考殖利率")
    private BigDecimal ytmytc;
    /** 報價日期 */
    @Schema(description = "報價日期")
    private String peferPriceDate;
    /** 最低申購面額 */
    @Schema(description = "最低申購面額")
    private BigDecimal miniBuyAmt;
    /**
     * 參考申購報價
     */
    @Schema(description = "參考申購報價")
    private BigDecimal referPrice;
    /** 顯示紅心 */
    @Schema(description = "顯示紅心")
    private boolean showRedHeart;

    /**
     * YTM/YTC正負號
     */
    @Schema(description = "YTM/YTC正負號")
    private String ytmytcSign;
    
    /** 可申購 */
    private boolean canBuy;
    

    public String getBondNo() {
        return bondNo;
    }

    public void setBondNo(String bondNo) {
        this.bondNo = bondNo;
    }

    public String getBondName() {
        return bondName;
    }

    public void setBondName(String bondName) {
        this.bondName = bondName;
    }

    public BigDecimal getYtmytc() {
        return ytmytc;
    }

    public void setYtmytc(BigDecimal ytmytc) {
        this.ytmytc = ytmytc;
    }

    public String getPeferPriceDate() {
        return peferPriceDate;
    }

    public void setPeferPriceDate(String peferPriceDate) {
        this.peferPriceDate = peferPriceDate;
    }

    public BigDecimal getMiniBuyAmt() {
        return miniBuyAmt;
    }

    public void setMiniBuyAmt(BigDecimal miniBuyAmt) {
        this.miniBuyAmt = miniBuyAmt;
    }

    public BigDecimal getReferPrice() {
        return referPrice;
    }

    public void setReferPrice(BigDecimal referPrice) {
        this.referPrice = referPrice;
    }

    /**
     * @return {@link #showRedHeart}
     */
    public boolean isShowRedHeart() {
        return showRedHeart;
    }

    /**
     * @param showRedHeart
     *            {@link #showRedHeart}
     */
    public void setShowRedHeart(boolean showRedHeart) {
        this.showRedHeart = showRedHeart;
    }

    public String getYtmytcSign() {
        return ytmytcSign;
    }

    public void setYtmytcSign(String ytmytcSign) {
        this.ytmytcSign = ytmytcSign;
    }

    public boolean isCanBuy() {
        return canBuy;
    }

    public void setCanBuy(boolean canBuy) {
        this.canBuy = canBuy;
    }

}
