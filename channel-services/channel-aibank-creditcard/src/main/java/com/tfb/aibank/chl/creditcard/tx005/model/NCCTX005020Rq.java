package com.tfb.aibank.chl.creditcard.tx005.model;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCTX005020Rq.java
 * 
 * <p>Description:額度調整 020 申請資料輸入頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/04, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCTX005020Rq implements RqData {

    /** 調整項目，1:調高、2:調降、3:附卡額度 */
    private String adjustItem;

    /** 調整金額 */
    private BigDecimal adjustAmt;

    /** 額度用途，7:人壽保費扣繳(分期)、2:人壽保費扣繳、3:一般消費需求、4:出國需求、6:其他 */
    private String quotaUsage;

    /** 其他用途(額度用途選擇「其他」時必填) */
    private String otherUsage;

    /** 保費全額(額度用途選擇「人壽保費扣繳(分期)」時必填) */
    private BigDecimal fullInsurAmt;

    /** 選擇的附卡(索引值) */
    private int supplementaryCardIdx;

    public AdjustItemType getAdjustItemType() {
        return AdjustItemType.findByCode(this.adjustItem);
    }

    public String getAdjustItem() {
        return adjustItem;
    }

    public void setAdjustItem(String adjustItem) {
        this.adjustItem = adjustItem;
    }

    public BigDecimal getAdjustAmt() {
        return adjustAmt;
    }

    public void setAdjustAmt(BigDecimal adjustAmt) {
        this.adjustAmt = adjustAmt;
    }

    public QuotaUsageType getQuotaUsageType() {
        if (getAdjustItemType().isTurnUp()) {
            return QuotaUsageType.findByCode(this.quotaUsage);
        }
        return null;
    }

    public String getQuotaUsage() {
        return quotaUsage;
    }

    public void setQuotaUsage(String quotaUsage) {
        this.quotaUsage = quotaUsage;
    }

    public String getOtherUsage() {
        return otherUsage;
    }

    public void setOtherUsage(String otherUsage) {
        this.otherUsage = otherUsage;
    }

    public BigDecimal getFullInsurAmt() {
        return fullInsurAmt;
    }

    public void setFullInsurAmt(BigDecimal fullInsurAmt) {
        this.fullInsurAmt = fullInsurAmt;
    }

    public int getSupplementaryCardIdx() {
        return supplementaryCardIdx;
    }

    public void setSupplementaryCardIdx(int supplementaryCardIdx) {
        this.supplementaryCardIdx = supplementaryCardIdx;
    }

}
