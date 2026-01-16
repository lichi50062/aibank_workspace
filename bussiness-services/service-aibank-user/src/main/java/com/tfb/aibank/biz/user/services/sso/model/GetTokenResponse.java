/**
 * 
 */
package com.tfb.aibank.biz.user.services.sso.model;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)GetTokenResponse.java 
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
public class GetTokenResponse {

    /**
     * Status 0-succ 1-fail
     */
    @Schema(description = "Status")
    private String status;

    /**
     * 登入人員資料
     */
    @Schema(description = "登入人員資料")
    private String userData;

    /**
     * 會員類別
     */
    @Schema(description = "會員類別")
    private String loginType;

    /**
     * 使用者代碼
     */
    @Schema(description = "使用者代碼")
    private String userUuid;

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the userData
     */
    public String getUserData() {
        return userData;
    }

    /**
     * @param userData
     *            the userData to set
     */
    public void setUserData(String userData) {
        this.userData = userData;
    }

    /**
     * @return the loginType
     */
    public String getLoginType() {
        return loginType;
    }

    /**
     * @param loginType
     *            the loginType to set
     */
    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    /**
     * @return the userUuid
     */
    public String getUserUuid() {
        return userUuid;
    }

    /**
     * @param userUuid
     *            the userUuid to set
     */
    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

}
