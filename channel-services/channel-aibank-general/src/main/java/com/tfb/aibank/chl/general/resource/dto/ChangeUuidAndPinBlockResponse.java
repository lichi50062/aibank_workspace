/**
 * 
 */
package com.tfb.aibank.chl.general.resource.dto;


//@formatter:off
/**
* @(#)ChangeUuidAndPinBlockResponse.java
* 
* <p>Description:變更使用者代碼與密碼</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/02, John Chang 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class ChangeUuidAndPinBlockResponse {

    /** 執行結果 */
    private int Status;

    /**
     * @return the status
     */
    public int getStatus() {
        return Status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(int status) {
        Status = status;
    }

}
