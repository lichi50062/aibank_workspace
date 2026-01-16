package com.tfb.aibank.chl.creditcard.ag012.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCAG012030Rs.java
 * 
 * <p>Description:信用卡交易設定 030 確認頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/03/11, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG012030Rs implements RsData {
    /** 鎖定狀態資訊 */
    private NCCAG012LockStatusInfo lockStatusInfo;

    /** 國內/外標題 */
    private String remarkTitleInOutCountry;
    /** 國內/外內容 */
    private String remarkContentInOutCountry;

    /** 是否去條款頁 */
    private Boolean isGoToRemarkPage;

    /**
     * @return the lockStatusInfo
     */
    public NCCAG012LockStatusInfo getLockStatusInfo() {
        return lockStatusInfo;
    }

    /**
     * @param lockStatusInfo
     *            the lockStatusInfo to set
     */
    public void setLockStatusInfo(NCCAG012LockStatusInfo lockStatusInfo) {
        this.lockStatusInfo = lockStatusInfo;
    }

    /**
     * @return the remarkTitleInOutCountry
     */
    public String getRemarkTitleInOutCountry() {
        return remarkTitleInOutCountry;
    }

    /**
     * @param remarkTitleInOutCountry
     *            the remarkTitleInOutCountry to set
     */
    public void setRemarkTitleInOutCountry(String remarkTitleInOutCountry) {
        this.remarkTitleInOutCountry = remarkTitleInOutCountry;
    }

    /**
     * @return the remarkContentInOutCountry
     */
    public String getRemarkContentInOutCountry() {
        return remarkContentInOutCountry;
    }

    /**
     * @param remarkContentInOutCountry
     *            the remarkContentInOutCountry to set
     */
    public void setRemarkContentInOutCountry(String remarkContentInOutCountry) {
        this.remarkContentInOutCountry = remarkContentInOutCountry;
    }

    /**
     * @return the isGoToRemarkPage
     */
    public Boolean getIsGoToRemarkPage() {
        return isGoToRemarkPage;
    }

    /**
     * @param isGoToRemarkPage
     *            the isGoToRemarkPage to set
     */
    public void setIsGoToRemarkPage(Boolean isGoToRemarkPage) {
        this.isGoToRemarkPage = isGoToRemarkPage;
    }

}
