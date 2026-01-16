package com.tfb.aibank.chl.creditcard.tx005.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.annotations.FormatBigDecimal;
import com.ibm.tw.ibmb.annotations.FormatDate;
import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.common.model.TxnResult;

// @formatter:off
/**
 * @(#)NCCTX005050Rs.java
 * 
 * <p>Description:額度調整 050 結果頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/04, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCTX005050Rs implements RsData {

    /** 交易結果 */
    private TxnResult txnResult;

    /** 調整項目 */
    private AdjustItemType adjustItem;

    /** 調整金額 */
    @FormatBigDecimal(thousand = true)
    private BigDecimal adjustAmt;

    /** 交易時間 */
    @FormatDate(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date hostTxTime;

    /** 目前額度 */
    @FormatBigDecimal(thousand = true)
    private BigDecimal currentQuota;

    /** 額度用途 */
    private QuotaUsageType quotaUsage;

    /** 中文姓名(隱碼) */
    private String maskCnam;
    /** 持卡人ID(隱碼) */
    private String maskHdid;

    /** Email */
    private String email;

    public TxnResult getTxnResult() {
        return txnResult;
    }

    public void setTxnResult(TxnResult txnResult) {
        this.txnResult = txnResult;
    }

    public AdjustItemType getAdjustItem() {
        return adjustItem;
    }

    public void setAdjustItem(AdjustItemType adjustItem) {
        this.adjustItem = adjustItem;
    }

    public BigDecimal getAdjustAmt() {
        return adjustAmt;
    }

    public void setAdjustAmt(BigDecimal adjustAmt) {
        this.adjustAmt = adjustAmt;
    }

    public Date getHostTxTime() {
        return hostTxTime;
    }

    public void setHostTxTime(Date hostTxTime) {
        this.hostTxTime = hostTxTime;
    }

    public BigDecimal getCurrentQuota() {
        return currentQuota;
    }

    public void setCurrentQuota(BigDecimal currentQuota) {
        this.currentQuota = currentQuota;
    }

    public QuotaUsageType getQuotaUsage() {
        return quotaUsage;
    }

    public void setQuotaUsage(QuotaUsageType quotaUsage) {
        this.quotaUsage = quotaUsage;
    }

    public String getMaskCnam() {
        return maskCnam;
    }

    public void setMaskCnam(String maskCnam) {
        this.maskCnam = maskCnam;
    }

    public String getMaskHdid() {
        return maskHdid;
    }

    public void setMaskHdid(String maskHdid) {
        this.maskHdid = maskHdid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
