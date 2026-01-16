package com.tfb.aibank.chl.creditcard.tx005.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCTX005010Rs.java
 * 
 * <p>Description:額度調整 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/04, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCTX005010Rs implements RsData {

    /** 信用額度 */
    private BigDecimal creditLimit;

    /** 調整項目，調高、調降、附卡額度 */
    private List<NCCTX005AdjustItem> adjustItems;

    /** 額度用途，7:人壽保費扣繳(分期)、2:人壽保費扣繳、3:一般消費需求、4:出國需求、6:其他 */
    private List<QuotaUsageType> quotaUsageList;

    /** 附卡清單 */
    private List<NCCTX005CreditCard> supplementaryCards;

    /** 電子信箱(隱碼) */
    private String maskEmail;

    /** 白名單額度 */
    private BigDecimal cardApplyAdjustAmt;

    public List<NCCTX005AdjustItem> getAdjustItems() {
        return adjustItems;
    }

    public void setAdjustItems(List<NCCTX005AdjustItem> adjustItems) {
        this.adjustItems = adjustItems;
    }

    public List<QuotaUsageType> getQuotaUsageList() {
        return quotaUsageList;
    }

    public void setQuotaUsageList(List<QuotaUsageType> quotaUsageList) {
        this.quotaUsageList = quotaUsageList;
    }

    public String getMaskEmail() {
        return maskEmail;
    }

    public void setMaskEmail(String maskEmail) {
        this.maskEmail = maskEmail;
    }

    public List<NCCTX005CreditCard> getSupplementaryCards() {
        return supplementaryCards;
    }

    public void setSupplementaryCards(List<NCCTX005CreditCard> supplementaryCards) {
        this.supplementaryCards = supplementaryCards;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getCardApplyAdjustAmt() {
        return cardApplyAdjustAmt;
    }

    public void setCardApplyAdjustAmt(BigDecimal cardApplyAdjustAmt) {
        this.cardApplyAdjustAmt = cardApplyAdjustAmt;
    }

}
