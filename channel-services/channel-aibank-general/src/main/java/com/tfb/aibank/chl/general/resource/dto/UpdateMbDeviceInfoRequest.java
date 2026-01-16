/**
 * 
 */
package com.tfb.aibank.chl.general.resource.dto;

//@formatter:off
/**
* @(#)UpdateMbDeviceInfoRequest.java
* 
* <p>Description:更新MB_DEVICE_INFO LOGIN_FLAG - Request</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/12, John
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class UpdateMbDeviceInfoRequest {

    /** 身分證字號 */
    private String custId;

    /** 誤別碼 */
    private String uidDup;

    /** 使用者代號 */
    private String userId;

    /** 公司類型 */
    private Integer companyKind;

    /** 行動裝置UUID */
    private String deviceUuid;

    /** 是否更新快速登入註記 */
    private boolean isUpdateLoginFlag;

    /** 是否授權簡易登入 */
    private int loginFlag;

    /**
     * @return the isUpdateLoginFlag
     */
    public boolean isUpdateLoginFlag() {
        return isUpdateLoginFlag;
    }

    /**
     * @param isUpdateLoginFlag
     *            the isUpdateLoginFlag to set
     */
    public void setUpdateLoginFlag(boolean isUpdateLoginFlag) {
        this.isUpdateLoginFlag = isUpdateLoginFlag;
    }

    /**
     * @return the loginFlag
     */
    public int getLoginFlag() {
        return loginFlag;
    }

    /**
     * @param loginFlag
     *            the loginFlag to set
     */
    public void setLoginFlag(int loginFlag) {
        this.loginFlag = loginFlag;
    }

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
     * @return the deviceUuid
     */
    public String getDeviceUuid() {
        return deviceUuid;
    }

    /**
     * @param deviceUuid
     *            the deviceUuid to set
     */
    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
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

}
