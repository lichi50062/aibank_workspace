/**
 * 
 */
package com.tfb.aibank.biz.user.services.sso.model;

//@formatter:off
/**
* @(#)SsoUserData.java 
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
public class SsoUserData {

    private String uid;

    private String uidDup;

    private String uuid;

    private String nameCode;

    private String companyKind;

    private String companyKey;

    private String userKey;
    
    private String loginData;

    /**
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid
     *            the uid to set
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

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

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     *            the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the nameCode
     */
    public String getNameCode() {
        return nameCode;
    }

    /**
     * @param nameCode
     *            the nameCode to set
     */
    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    /**
     * @return the companyKind
     */
    public String getCompanyKind() {
        return companyKind;
    }

    /**
     * @param companyKind
     *            the companyKind to set
     */
    public void setCompanyKind(String companyKind) {
        this.companyKind = companyKind;
    }

    /**
     * @return the companyKey
     */
    public String getCompanyKey() {
        return companyKey;
    }

    /**
     * @param companyKey
     *            the companyKey to set
     */
    public void setCompanyKey(String companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * @return the userKey
     */
    public String getUserKey() {
        return userKey;
    }

    /**
     * @param userKey
     *            the userKey to set
     */
    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }
    /**
     * 
     * @return
     */
    public String getLoginData() {
        return loginData;
    }
    /**
     * 
     * @param loginData
     */
    public void setLoginData(String loginData) {
        this.loginData = loginData;
    }

}
