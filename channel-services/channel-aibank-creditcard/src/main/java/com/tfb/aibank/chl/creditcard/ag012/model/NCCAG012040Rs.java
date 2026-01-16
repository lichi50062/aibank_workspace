package com.tfb.aibank.chl.creditcard.ag012.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCAG012040Rs.java
 * 
 * <p>Description:信用卡交易設定 040 條款頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/03/11, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG012040Rs implements RsData {

    /** 條款Title */
    private String remarkTitle;

    /** 條款內容 */
    private String remarkContent;

    /**
     * @return the remarkTitle
     */
    public String getRemarkTitle() {
        return remarkTitle;
    }

    /**
     * @param remarkTitle
     *            the remarkTitle to set
     */
    public void setRemarkTitle(String remarkTitle) {
        this.remarkTitle = remarkTitle;
    }

    /**
     * @return the remarkContent
     */
    public String getRemarkContent() {
        return remarkContent;
    }

    /**
     * @param remarkContent
     *            the remarkContent to set
     */
    public void setRemarkContent(String remarkContent) {
        this.remarkContent = remarkContent;
    }

}
