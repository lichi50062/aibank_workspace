package com.tfb.aibank.chl.creditcard.ag012.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCAG012021Rq.java
 * 
 * <p>Description:信用卡交易設定 021 上送請求</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/03/11, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG012021Rq implements RqData {
    /** 是否所有都是Lock狀態 */
    private Boolean allLockStatus;
    /** 國內交易金額上限 */
    private String txAmtLimitInCountry;
    /** 國內鎖定實體卡 */
    private Boolean lockRealInCountry;
    /** 國內鎖定非實體卡 */
    private Boolean lockNotRealInCountry;
    /** 國內鎖定行動支付 */
    private Boolean lockActionPayInCountry;
    /** 國外交易金額上限 */
    private String txAmtLimitOutCountry;
    /** 國外鎖定實體卡 */
    private Boolean lockRealOutCountry;
    /** 國外鎖定非實體卡 */
    private Boolean lockNotRealOutCountry;
    /** 國外鎖定行動支付 */
    private Boolean lockActionPayOutCountry;

    /**
     * @return the allLockStatus
     */
    public Boolean getAllLockStatus() {
        return allLockStatus;
    }

    /**
     * @param allLockStatus
     *            the allLockStatus to set
     */
    public void setAllLockStatus(Boolean allLockStatus) {
        this.allLockStatus = allLockStatus;
    }

    /**
     * @return the txAmtLimitInCountry
     */
    public String getTxAmtLimitInCountry() {
        return txAmtLimitInCountry;
    }

    /**
     * @param txAmtLimitInCountry
     *            the txAmtLimitInCountry to set
     */
    public void setTxAmtLimitInCountry(String txAmtLimitInCountry) {
        this.txAmtLimitInCountry = txAmtLimitInCountry;
    }

    /**
     * @return the lockRealInCountry
     */
    public Boolean getLockRealInCountry() {
        return lockRealInCountry;
    }

    /**
     * @param lockRealInCountry
     *            the lockRealInCountry to set
     */
    public void setLockRealInCountry(Boolean lockRealInCountry) {
        this.lockRealInCountry = lockRealInCountry;
    }

    /**
     * @return the lockNotRealInCountry
     */
    public Boolean getLockNotRealInCountry() {
        return lockNotRealInCountry;
    }

    /**
     * @param lockNotRealInCountry
     *            the lockNotRealInCountry to set
     */
    public void setLockNotRealInCountry(Boolean lockNotRealInCountry) {
        this.lockNotRealInCountry = lockNotRealInCountry;
    }

    /**
     * @return the lockActionPayInCountry
     */
    public Boolean getLockActionPayInCountry() {
        return lockActionPayInCountry;
    }

    /**
     * @param lockActionPayInCountry
     *            the lockActionPayInCountry to set
     */
    public void setLockActionPayInCountry(Boolean lockActionPayInCountry) {
        this.lockActionPayInCountry = lockActionPayInCountry;
    }

    /**
     * @return the txAmtLimitOutCountry
     */
    public String getTxAmtLimitOutCountry() {
        return txAmtLimitOutCountry;
    }

    /**
     * @param txAmtLimitOutCountry
     *            the txAmtLimitOutCountry to set
     */
    public void setTxAmtLimitOutCountry(String txAmtLimitOutCountry) {
        this.txAmtLimitOutCountry = txAmtLimitOutCountry;
    }

    /**
     * @return the lockRealOutCountry
     */
    public Boolean getLockRealOutCountry() {
        return lockRealOutCountry;
    }

    /**
     * @param lockRealOutCountry
     *            the lockRealOutCountry to set
     */
    public void setLockRealOutCountry(Boolean lockRealOutCountry) {
        this.lockRealOutCountry = lockRealOutCountry;
    }

    /**
     * @return the lockNotRealOutCountry
     */
    public Boolean getLockNotRealOutCountry() {
        return lockNotRealOutCountry;
    }

    /**
     * @param lockNotRealOutCountry
     *            the lockNotRealOutCountry to set
     */
    public void setLockNotRealOutCountry(Boolean lockNotRealOutCountry) {
        this.lockNotRealOutCountry = lockNotRealOutCountry;
    }

    /**
     * @return the lockActionPayOutCountry
     */
    public Boolean getLockActionPayOutCountry() {
        return lockActionPayOutCountry;
    }

    /**
     * @param lockActionPayOutCountry
     *            the lockActionPayOutCountry to set
     */
    public void setLockActionPayOutCountry(Boolean lockActionPayOutCountry) {
        this.lockActionPayOutCountry = lockActionPayOutCountry;
    }

}
