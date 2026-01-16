package com.tfb.aibank.chl.general.qu001.model;

import com.tfb.aibank.chl.component.homepagecard.HomepageCard;

import java.math.BigDecimal;


// @formatter:off
/**
 * @(#)CardDataCreditCard.java
 *
 * <p>Description: 功能牌卡-信用卡資料</p>
 *
 * <p>Modify History:</p>
 * v1, 2023/05/22, MartyPan
 * <ol>
 *  <li>功能牌卡-信用卡資料</li>
 * </ol>
 */
//@formatter:on
public class CardDataCreditCard extends CardDataParent {

    /**
     * 臺幣消費金額總計
     */
    private BigDecimal consumptionAmt;

    /**
     * 信用額度 (100 % base for 進度條)
     * */
    private BigDecimal creditLimit;
    /**
     * 已使用額度的百分比值
     * */
    private double creditUsedPercentage;
    /**
     * 剩餘額度
     * */
    private BigDecimal remainingAmount;

    /**
     * 尚未繳納資料
     * */
    private BillToPay billToPay;

    public CardDataCreditCard() {
        super();
    }

    public CardDataCreditCard(HomepageCard homepageCard) {
        super(homepageCard);
    }

    public BigDecimal getConsumptionAmt() {
        return consumptionAmt;
    }

    public void setConsumptionAmt(BigDecimal consumptionAmt) {
        this.consumptionAmt = consumptionAmt;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public double getCreditUsedPercentage() {
        return creditUsedPercentage;
    }

    public void setCreditUsedPercentage(double creditUsedPercentage) {
        this.creditUsedPercentage = creditUsedPercentage;
    }

    public BigDecimal getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(BigDecimal remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public BillToPay getBillToPay() {
        return billToPay;
    }

    public void setBillToPay(BillToPay billToPay) {
        this.billToPay = billToPay;
    }

    public static class BillToPay {

        private int billMonthInt;
        private String billMonthNumStr;

        private String billMonthDisp;

        private BigDecimal amtToPay;

        public BillToPay() {
        }

        public BillToPay(int billMonthInt, String billMonthNumStr, String billMonthDisp, BigDecimal amtToPay) {
            this.billMonthInt = billMonthInt;
            this.billMonthNumStr = billMonthNumStr;
            this.billMonthDisp = billMonthDisp;
            this.amtToPay = amtToPay;
        }

        public int getBillMonthInt() {
            return billMonthInt;
        }

        public void setBillMonthInt(int billMonthInt) {
            this.billMonthInt = billMonthInt;
        }

        public String getBillMonthNumStr() {
            return billMonthNumStr;
        }

        public void setBillMonthNumStr(String billMonthNumStr) {
            this.billMonthNumStr = billMonthNumStr;
        }

        public String getBillMonthDisp() {
            return billMonthDisp;
        }

        public void setBillMonthDisp(String billMonthDisp) {
            this.billMonthDisp = billMonthDisp;
        }

        public BigDecimal getAmtToPay() {
            return amtToPay;
        }

        public void setAmtToPay(BigDecimal amtToPay) {
            this.amtToPay = amtToPay;
        }
    }
}
