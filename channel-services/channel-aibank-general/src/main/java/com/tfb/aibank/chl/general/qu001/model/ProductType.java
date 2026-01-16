/*
 * ===========================================================================
 *
 * IBM Confidential
 *
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.general.qu001.model;

import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)ProductType.java
 * 
 * <p>Description:首頁卡片產品類別</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/23, MartyP
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum ProductType implements IEnum {

    DEPOSIT(1, "存款"),
    CREDITCARD(2, "信用卡"),
    INVESTMENT(3, "投資"),
    LOAN(4, "貸款"),
    INSURANCE(5, "保險"),
    STOCK(6, "證券"),

    UNKNOWN();

    private int cardId;
    private String memo;

    ProductType() {
    }

    ProductType(int cardId, String memo) {
        this.cardId = cardId;
        this.memo = memo;
    }


    public static ProductType findByName(String name) {
        for (ProductType enumType : ProductType.values()) {
            if (StringUtils.equals(enumType.name(), StringUtils.upperCase(name))) {
                return enumType;
            }
        }
        return UNKNOWN;
    }

    @Override
    public String getMemo() {
        return this.memo;
    }

    public int getCardId() {
        return cardId;
    }
}
