/**
 * 
 */
package com.tfb.aibank.biz.user.services.login.model;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)ChangeUuidAndPinBlockRequest.java
* 
* <p>Description:變更密碼有效時間</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/02, John Chang 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class UpdatePwdValidTimeRequest {

    @Schema(description = "身份證字號")
    String custId;

    /** [誤別碼] 公司統編或身份證字號誤別碼 */
    @Schema(description = "誤別碼")
    private String uidDup;

    /**
     * @return the uidDup
     */
    public String getUidDup() {
        return uidDup;
    }

    /**
     * @param uidDup
     *            the uidDup to set
     */
    public void setUidDup(String uidDup) {
        this.uidDup = uidDup;
    }

    @Schema(description = "使用者代號")
    String userId;

    @Schema(description = "Company Kind")
    Integer companyKind;

    /** 登入身份 */
    @Schema(description = "登入身份")
    private String loginType;

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
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the companyKind
     */
    public Integer getCompanyKind() {
        return companyKind;
    }

    /**
     * @param companyKind
     *            the companyKind to set
     */
    public void setCompanyKind(Integer companyKind) {
        this.companyKind = companyKind;
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

}
