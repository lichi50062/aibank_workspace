package com.tfb.aibank.chl.creditcardactivities.resource.dto;

import java.util.List;

//@formatter:off
/**
 * @(#)CEW223RRequest.java
 * 
 * <p>Description:CEW223R 信用卡活動登錄/查詢 Request</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/12, John Chang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class CEW223RRequest {

    /** 身分證號 */
    private String custId;

    /** 交易類別 */
    private String txType;

    /** 活動代碼清單 */
    private List<CEW223RRequestRepeat> repeat;

    /**
     * @return the custId
     */
    public String getCustId() {
        return custId;
    }

    /**
     * @param custId
     *            the custId to set
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    /**
     * @return the txType
     */
    public String getTxType() {
        return txType;
    }

    /**
     * @param txType
     *            the txType to set
     */
    public void setTxType(String txType) {
        this.txType = txType;
    }

    /**
     * @return the repeat
     */
    public List<CEW223RRequestRepeat> getRepeat() {
        return repeat;
    }

    /**
     * @param repeat
     *            the repeat to set
     */
    public void setRepeat(List<CEW223RRequestRepeat> repeat) {
        this.repeat = repeat;
    }

}
