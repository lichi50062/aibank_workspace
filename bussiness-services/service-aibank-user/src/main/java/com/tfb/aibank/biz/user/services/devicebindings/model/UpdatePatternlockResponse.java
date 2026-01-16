/**
 * 
 */
package com.tfb.aibank.biz.user.services.devicebindings.model;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)UpdateUserDeviceBindingRequest.java
* 
* <p>Description:更新圖形登入綁定資料 - Request</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/02, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class UpdatePatternlockResponse {

    /** 狀態 */
    @Schema(description = "狀態")
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
