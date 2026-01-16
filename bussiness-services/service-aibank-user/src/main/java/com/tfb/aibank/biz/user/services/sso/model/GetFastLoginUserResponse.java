/**
 * 
 */
package com.tfb.aibank.biz.user.services.sso.model;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)GetFastLoginUserResponse.java 
* 
* <p>Description:SSO/快速身份認證</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 2024/01/17, JohnChang
*  <li>初版</li6
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
public class GetFastLoginUserResponse {

    /** statusCode */
    @Schema(description = "statusCode")
    private String statusCode;
    
    /** 認證方式 */
    @Schema(description = "認證方式")
    private String authType;
    
    /** 綁定使用者 */
    @Schema(description = "綁定使用者")
    private String custId;

    /**
     * @return the statusCode
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return the authType
     */
    public String getAuthType() {
        return authType;
    }

    /**
     * @param authType the authType to set
     */
    public void setAuthType(String authType) {
        this.authType = authType;
    }

    /**
     * @return the custId
     */
    public String getCustId() {
        return custId;
    }

    /**
     * @param custId the custId to set
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }
    
}
