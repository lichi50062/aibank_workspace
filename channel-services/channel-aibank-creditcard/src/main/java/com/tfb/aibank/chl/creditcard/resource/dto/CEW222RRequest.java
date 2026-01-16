package com.tfb.aibank.chl.creditcard.resource.dto;

import java.io.Serializable;

// @formatter:off
/**
 * @(#)CEW222RRequest.java
 * 
 * <p>Description:單筆分期查詢(CEW222R) 上行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/20, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class CEW222RRequest implements Serializable {

    private static final long serialVersionUID = 8812810868426412630L;

    /** 歸戶ID */
    private String accId;

    /**
     * @return the accId
     */
    public String getAccId() {
        return accId;
    }

    /**
     * @param accId
     *            the accId to set
     */
    public void setAccId(String accId) {
        this.accId = accId;
    }

}
