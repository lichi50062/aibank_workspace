/**
 * 
 */
package com.tfb.aibank.chl.general.resource.dto;

//@formatter:off
/**
* @(#)UpdateLoginTypeRequest.java
* 
* <p>Description:驗證方式變更</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/09, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class UpdateLoginTypeRequest {

    /** 使用者ID */
    private String custId;

    /** 使用者代號 */
    private String userId;

    /** 公司類型 */
    private Integer companyKind;

    /** 快速登入密碼類型 */
    private Integer loginPasswordType;

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
     * @return the loginPasswordType
     */
    public Integer getLoginPasswordType() {
        return loginPasswordType;
    }

    /**
     * @param loginPasswordType
     *            the loginPasswordType to set
     */
    public void setLoginPasswordType(Integer loginPasswordType) {
        this.loginPasswordType = loginPasswordType;
    }

}
