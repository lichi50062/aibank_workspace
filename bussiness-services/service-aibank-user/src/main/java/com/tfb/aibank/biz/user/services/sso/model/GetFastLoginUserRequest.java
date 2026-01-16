/**
 * 
 */
package com.tfb.aibank.biz.user.services.sso.model;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)GetFastLoginUserRequest.java 
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
public class GetFastLoginUserRequest {

    
    /** 平台代碼 */
    @Schema(description = "平台代碼")
    private String platformId;
    
    
    // 平台使用者代碼
    @Schema(description = "平台使用者代碼")
    private String platformUserAccount;

    // 使用者身分證字號
    @Schema(description = "使用者身分證字號")
    private String platformUserVerify;

    // 平台保留參數
    @Schema(description = "平台保留參數")
    private String platformParams;

    /**
     * @return the platformId
     */
    public String getPlatformId() {
        return platformId;
    }

    /**
     * @param platformId the platformId to set
     */
    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    /**
     * @return the platformUserAccount
     */
    public String getPlatformUserAccount() {
        return platformUserAccount;
    }

    /**
     * @param platformUserAccount the platformUserAccount to set
     */
    public void setPlatformUserAccount(String platformUserAccount) {
        this.platformUserAccount = platformUserAccount;
    }

    /**
     * @return the platformUserVerify
     */
    public String getPlatformUserVerify() {
        return platformUserVerify;
    }

    /**
     * @param platformUserVerify the platformUserVerify to set
     */
    public void setPlatformUserVerify(String platformUserVerify) {
        this.platformUserVerify = platformUserVerify;
    }

    /**
     * @return the platformParams
     */
    public String getPlatformParams() {
        return platformParams;
    }

    /**
     * @param platformParams the platformParams to set
     */
    public void setPlatformParams(String platformParams) {
        this.platformParams = platformParams;
    }
    
    
    
}
