package com.tfb.aibank.chl.creditcard.tx001.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NCCTX001020Rs.java
*
* <p>Description:預借現金 申請資料輸入頁</p>
*
* <p>Modify History:</p>
* v1.0, 2023/09/22 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCTX001020Rs implements RsData {

    /** 卡片清單 */
    private List<NCCTX001CardInfo> cardInfo;

    /** 銀行清單 */
    private List<NCCTX001BankInfo> bankInfo;

    /** 帳戶清單 */
    private List<NCCTX001AccountInfo> accountInfo;

    /** 是否顯示提示 */
    private boolean isShowSpecial;

    /**
     * @return the isShowSpecial
     */
    public boolean isShowSpecial() {
        return isShowSpecial;
    }

    /**
     * @param isShowSpecial
     *            the isShowSpecial to set
     */
    public void setShowSpecial(boolean isShowSpecial) {
        this.isShowSpecial = isShowSpecial;
    }

    /**
     * @return the cardInfo
     */
    public List<NCCTX001CardInfo> getCardInfo() {
        return cardInfo;
    }

    /**
     * @param cardInfo
     *            the cardInfo to set
     */
    public void setCardInfo(List<NCCTX001CardInfo> cardInfo) {
        this.cardInfo = cardInfo;
    }

    /**
     * @return the bankInfo
     */
    public List<NCCTX001BankInfo> getBankInfo() {
        return bankInfo;
    }

    /**
     * @param bankInfo
     *            the bankInfo to set
     */
    public void setBankInfo(List<NCCTX001BankInfo> bankInfo) {
        this.bankInfo = bankInfo;
    }

    /**
     * @return the accountInfo
     */
    public List<NCCTX001AccountInfo> getAccountInfo() {
        return accountInfo;
    }

    /**
     * @param accountInfo
     *            the accountInfo to set
     */
    public void setAccountInfo(List<NCCTX001AccountInfo> accountInfo) {
        this.accountInfo = accountInfo;
    }

}
