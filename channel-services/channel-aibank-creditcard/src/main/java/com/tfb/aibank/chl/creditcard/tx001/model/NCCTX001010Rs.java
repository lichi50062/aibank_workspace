package com.tfb.aibank.chl.creditcard.tx001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NCCTX001010Rs.java
*
* <p>Description:預借現金 功能首頁</p>
*
* <p>Modify History:</p>
* v1.0, 2023/09/22 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCTX001010Rs implements RsData {

    /** 最高額度 */
    private int maxBalanceAvailable;

    /** 最高額度 */
    private String displayMaxBalanceAvailable;

    /** 國內信用額度 */
    private String maxCreditDomestic;

    /** 國內可用額度 */
    private String maxBalanceDomestic;

    /** 國外信用額度 */
    private String maxCreditForeign;

    /** 國外可用額度 */
    private String maxBalanceForeign;

    /** 手續費說明 */
    private String feeRemarkTitle;

    /** 手續費說明 */
    private String feeRemarkContent;

    /**
     * @return the maxBalanceAvailable
     */
    public int getMaxBalanceAvailable() {
        return maxBalanceAvailable;
    }

    /**
     * @param maxBalanceAvailable
     *            the maxBalanceAvailable to set
     */
    public void setMaxBalanceAvailable(int maxBalanceAvailable) {
        this.maxBalanceAvailable = maxBalanceAvailable;
    }

    /**
     * @return the maxBalanceDomestic
     */
    public String getMaxBalanceDomestic() {
        return maxBalanceDomestic;
    }

    /**
     * @param maxBalanceDomestic
     *            the maxBalanceDomestic to set
     */
    public void setMaxBalanceDomestic(String maxBalanceDomestic) {
        this.maxBalanceDomestic = maxBalanceDomestic;
    }

    /**
     * @return the maxBalanceForeign
     */
    public String getMaxBalanceForeign() {
        return maxBalanceForeign;
    }

    /**
     * @param maxBalanceForeign
     *            the maxBalanceForeign to set
     */
    public void setMaxBalanceForeign(String maxBalanceForeign) {
        this.maxBalanceForeign = maxBalanceForeign;
    }

    /**
     * @return the maxCreditDomestic
     */
    public String getMaxCreditDomestic() {
        return maxCreditDomestic;
    }

    /**
     * @param maxCreditDomestic
     *            the maxCreditDomestic to set
     */
    public void setMaxCreditDomestic(String maxCreditDomestic) {
        this.maxCreditDomestic = maxCreditDomestic;
    }

    /**
     * @return the maxCreditForeign
     */
    public String getMaxCreditForeign() {
        return maxCreditForeign;
    }

    /**
     * @param maxCreditForeign
     *            the maxCreditForeign to set
     */
    public void setMaxCreditForeign(String maxCreditForeign) {
        this.maxCreditForeign = maxCreditForeign;
    }

    /**
     * @return the displayMaxBalanceAvailable
     */
    public String getDisplayMaxBalanceAvailable() {
        return displayMaxBalanceAvailable;
    }

    /**
     * @param displayMaxBalanceAvailable
     *            the displayMaxBalanceAvailable to set
     */
    public void setDisplayMaxBalanceAvailable(String displayMaxBalanceAvailable) {
        this.displayMaxBalanceAvailable = displayMaxBalanceAvailable;
    }

    /**
     * @return the feeRemarkTitle
     */
    public String getFeeRemarkTitle() {
        return feeRemarkTitle;
    }

    /**
     * @param feeRemarkTitle
     *            the feeRemarkTitle to set
     */
    public void setFeeRemarkTitle(String feeRemarkTitle) {
        this.feeRemarkTitle = feeRemarkTitle;
    }

    /**
     * @return the feeRemarkContent
     */
    public String getFeeRemarkContent() {
        return feeRemarkContent;
    }

    /**
     * @param feeRemarkContent
     *            the feeRemarkContent to set
     */
    public void setFeeRemarkContent(String feeRemarkContent) {
        this.feeRemarkContent = feeRemarkContent;
    }

}
