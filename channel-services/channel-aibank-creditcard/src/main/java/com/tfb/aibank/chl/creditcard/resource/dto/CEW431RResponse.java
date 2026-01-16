/**
 * 
 */
package com.tfb.aibank.chl.creditcard.resource.dto;

//@formatter:off
/**
* @(#)CEW431RResponse.java
* 
* <p>Description:CEW431R 信用卡OTPResponse</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/22, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW431RResponse {

    /** 狀態 */
    private int status;

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

}
