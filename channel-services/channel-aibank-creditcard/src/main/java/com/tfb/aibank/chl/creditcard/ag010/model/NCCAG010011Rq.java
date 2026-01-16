package com.tfb.aibank.chl.creditcard.ag010.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCAG010011Rq.java
 * 
 * <p>Description:變更密碼(信用卡) 011 </p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/22, Aaron
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG010011Rq implements RqData {

    /** 使用者原密碼 */
    private String eoldpxss;
    /** 使用者密碼 */
    private String enewPxss;

    /**
     * @return the eoldpxss
     */
    public String getEoldpxss() {
        return eoldpxss;
    }

    /**
     * @param eoldpxss
     *            the eoldpxss to set
     */
    public void setEoldpxss(String eoldpxss) {
        this.eoldpxss = eoldpxss;
    }

    /**
     * @return the enewPxss
     */
    public String getEnewPxss() {
        return enewPxss;
    }

    /**
     * @param enewPxss
     *            the enewPxss to set
     */
    public void setEnewPxss(String enewPxss) {
        this.enewPxss = enewPxss;
    }

}
