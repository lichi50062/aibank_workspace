/**
 * 
 */
package com.tfb.aibank.chl.system.resource.dto;

/**
 * @author john
 *
 */
public class GetTokenResponse {

    /**
     * Status 0-succ 1-fail
     */
    private String status;

    /**
     * 登入人員資料
     */
    private String userData;

    /**
     * 會員類別
     */
    private String loginType;

    /**
     * 使用者代碼
     */
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
