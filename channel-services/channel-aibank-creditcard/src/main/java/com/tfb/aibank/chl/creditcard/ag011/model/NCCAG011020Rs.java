package com.tfb.aibank.chl.creditcard.ag011.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCAG011020Rs.java
 * 
 * <p>Description:好市多會費代扣繳申請 020 </p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/05, Leiley
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG011020Rs implements RsData {

    /** 條款標題 */
    private String contractTitle;
    /** 條款內文 */
    private String contractContent;

    /**
     * @return the contractTitle
     */
    public String getContractTitle() {
        return contractTitle;
    }

    /**
     * @param contractTitle
     *            the contractTitle to set
     */
    public void setContractTitle(String contractTitle) {
        this.contractTitle = contractTitle;
    }

    /**
     * @return the contractContent
     */
    public String getContractContent() {
        return contractContent;
    }

    /**
     * @param contractContent
     *            the contractContent to set
     */
    public void setContractContent(String contractContent) {
        this.contractContent = contractContent;
    }

}
