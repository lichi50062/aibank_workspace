package com.tfb.aibank.chl.general.qu001.model;

import com.tfb.aibank.chl.component.exchangerate.ExchangeRate;
import com.tfb.aibank.chl.component.homepagecard.HomepageCard;

import java.math.BigDecimal;


// @formatter:off
/**
 * @(#)CardDataDeposit.java
 *
 * <p>Description: 功能牌卡-存款資料</p>
 *
 * <p>Modify History:</p>
 * v1, 2023/05/22, MartyPan
 * <ol>
 *  <li>功能牌卡-存款資料</li>
 * </ol>
 */
//@formatter:on
public class CardDataDeposit extends CardDataParent {

    private BigDecimal totalAmt;

    private BigDecimal totalAmtNTD;

    private BigDecimal totalAmtFRG;

    public CardDataDeposit() {
        super();
    }

    public CardDataDeposit(HomepageCard homepageCard) {
        super(homepageCard);
    }

    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    public BigDecimal getTotalAmtNTD() {
        return totalAmtNTD;
    }

    public void setTotalAmtNTD(BigDecimal totalAmtNTD) {
        this.totalAmtNTD = totalAmtNTD;
    }

    public BigDecimal getTotalAmtFRG() {
        return totalAmtFRG;
    }

    public void setTotalAmtFRG(BigDecimal totalAmtFRG) {
        this.totalAmtFRG = totalAmtFRG;
    }
}
