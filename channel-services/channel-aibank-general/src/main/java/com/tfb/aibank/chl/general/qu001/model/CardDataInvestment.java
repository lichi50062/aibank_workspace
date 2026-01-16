package com.tfb.aibank.chl.general.qu001.model;

import com.tfb.aibank.chl.component.homepagecard.HomepageCard;

import java.math.BigDecimal;
import java.util.List;

// @formatter:off
/**
 * @(#)CardDataInvestment.java
 *
 * <p>Description: 功能牌卡-投資資料</p>
 *
 * <p>Modify History:</p>
 * v1, 2023/05/22, MartyPan
 * <ol>
 *  <li>功能牌卡-投資資料</li>
 * </ol>
 */
//@formatter:on
public class CardDataInvestment extends CardDataParent {

    /**
     * 投資總金額
     */
    private BigDecimal totalAmount;

    /**
     * 市值最高商品
     */
    private List<TopValueProduct> topValueProducts;

    /**
     * 單一戶名
     */
    private Boolean singleName;

    public CardDataInvestment() {
        super();
    }

    public CardDataInvestment(HomepageCard homepageCard) {
        super(homepageCard);
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<TopValueProduct> getTopValueProducts() {
        return topValueProducts;
    }

    public void setTopValueProducts(List<TopValueProduct> topValueProducts) {
        this.topValueProducts = topValueProducts;
    }

    public Boolean isSingleName() {
        return singleName;
    }

    public void setSingleName(Boolean singleName) {
        this.singleName = singleName;
    }
}
