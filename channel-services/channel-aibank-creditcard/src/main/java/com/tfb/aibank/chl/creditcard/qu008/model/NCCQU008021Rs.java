package com.tfb.aibank.chl.creditcard.qu008.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCQU008021Rs.java
 * 
 * <p>Description:信用卡分期管理 021 條款頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/02, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU008021Rs implements RsData {
    /**
     * 條款標題
     */
    private String termsTitle;

    /**
     * 條款內容
     */
    private String termsContent;

    /**
     * 目標page代號 e.g. 010
     */
    private String targetPagePath;

    /** 是否清除歷程 */
    private Boolean isCleanHistory;

    /**
     * 是否帳單分期流程
     */
    private Boolean isBillProcess;

    /**
     * @return the termsTitle
     */
    public String getTermsTitle() {
        return termsTitle;
    }

    /**
     * @param termsTitle
     *            the termsTitle to set
     */
    public void setTermsTitle(String termsTitle) {
        this.termsTitle = termsTitle;
    }

    /**
     * @return the termsContent
     */
    public String getTermsContent() {
        return termsContent;
    }

    /**
     * @param termsContent
     *            the termsContent to set
     */
    public void setTermsContent(String termsContent) {
        this.termsContent = termsContent;
    }

    /**
     * @return the targetPagePath
     */
    public String getTargetPagePath() {
        return targetPagePath;
    }

    /**
     * @param targetPagePath
     *            the targetPagePath to set
     */
    public void setTargetPagePath(String targetPagePath) {
        this.targetPagePath = targetPagePath;
    }

    /**
     * @return the isCleanHistory
     */
    public Boolean getIsCleanHistory() {
        return isCleanHistory;
    }

    /**
     * @param isCleanHistory
     *            the isCleanHistory to set
     */
    public void setIsCleanHistory(Boolean isCleanHistory) {
        this.isCleanHistory = isCleanHistory;
    }

    /**
     * @return the isBillProcess
     */
    public Boolean getIsBillProcess() {
        return isBillProcess;
    }

    /**
     * @param isBillProcess
     *            the isBillProcess to set
     */
    public void setIsBillProcess(Boolean isBillProcess) {
        this.isBillProcess = isBillProcess;
    }

}
