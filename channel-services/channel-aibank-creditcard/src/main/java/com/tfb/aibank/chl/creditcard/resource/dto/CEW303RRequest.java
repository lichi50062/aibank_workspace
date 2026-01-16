package com.tfb.aibank.chl.creditcard.resource.dto;

//@formatter:off
/**
* @(#)CEW303RRequest.java
* 
* <p>Description:CEW303R 額度及繳款查詢 上行</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW303RRequest {

    /** 信用卡號號 */
    private String custCdno;

    /**
     * @return the custCdno
     */
    public String getCustCdno() {
        return custCdno;
    }

    /**
     * @param custCdno
     *            the custCdno to set
     */
    public void setCustCdno(String custCdno) {
        this.custCdno = custCdno;
    }

}
