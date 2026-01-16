package com.tfb.aibank.chl.creditcard.resource.dto;

//@formatter:off
/**
* @(#)CEW314RRequest.java
* 
* <p>Description:CEW314R 正卡:帳單明細查詢 上行</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW314RRequest {

    /** 查詢月份 X(1) */
    private String custIdBlmt;

    /**
     * @return the custIdBlmt
     */
    public String getCustIdBlmt() {
        return custIdBlmt;
    }

    /**
     * @param custIdBlmt
     *            the custIdBlmt to set
     */
    public void setCustIdBlmt(String custIdBlmt) {
        this.custIdBlmt = custIdBlmt;
    }

}
