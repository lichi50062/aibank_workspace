/**
 * 
 */
package com.tfb.aibank.chl.general.resource.dto;

//@formatter:off
/**
* @(#)ExecuteUserLogoutRequest.java
* 
* <p>Description:[程式說明]</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/02, John Chang 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class ExecuteUserLogoutRequest {

    /** 使用者ID */
    private String custId;

    /** 使用者代號 */
    private String userId;

    /** 公司類型 */
    private Integer companyKind;

    /** EB_LOGIN_LOG_B PK */
    private Integer loginLogPk;

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
     * @return the loginLogPk
     */
    public Integer getLoginLogPk() {
        return loginLogPk;
    }

    /**
     * @param loginLogPk
     *            the loginLogPk to set
     */
    public void setLoginLogPk(Integer loginLogPk) {
        this.loginLogPk = loginLogPk;
    }
}
