/**
 * 
 */
package com.tfb.aibank.biz.user.services.devicebindings.model;

import io.swagger.v3.oas.annotations.media.Schema;

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
    @Schema(description = "身分證字號")
    private String custId;

    /** 誤別碼 */
    @Schema(description = "誤別碼")
    private String uidDup;

    /** 使用者代號 */
    @Schema(description = "使用者代號")
    private String userId;

    /** 公司類型 */
    @Schema(description = "公司類型")
    private Integer companyKind;

    /** 行動裝置UUID */
    @Schema(description = "行動裝置UUID")
    private String deviceUuid;

    /** 是否更新快速登入註記 */
    @Schema(description = "是否更新快速登入註記")
    private boolean isUpdateLoginFlag;

    /** 是否授權簡易登入 */
    @Schema(description = "是否授權簡易登入")
    private int loginFlag;

    /** 是否更新有設定快登時，開啟APP是否直接登入 */
    @Schema(description = "是否更新有設定快登時，開啟APP是否直接登入")
    private boolean isUpdateDirectEzLoginFlag;

    /** 有設定快登時，開啟APP是否直接登入，0:不直接登入；1:直接登入 */
    @Schema(description = "有設定快登時，開啟APP是否直接登入")
    private int directEzLoginFlag;

    /** 是否更新快速登入密碼類型 */
    @Schema(description = "是否更新快速登入密碼類型")
    private boolean isUpdateLoginPasswdType;

    /** 快速登入密碼類型，0:文字密碼；1:手勢密碼；2:指紋；3:臉部；4:Android生物辨識 */
    @Schema(description = "快速登入密碼類型")
    private int loginPasswdType;

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
     * @return the isUpdateDirectEzLoginFlag
     */
    public boolean isUpdateDirectEzLoginFlag() {
        return isUpdateDirectEzLoginFlag;
    }

    /**
     * @param isUpdateDirectEzLoginFlag
     *            the isUpdateDirectEzLoginFlag to set
     */
    public void setUpdateDirectEzLoginFlag(boolean isUpdateDirectEzLoginFlag) {
        this.isUpdateDirectEzLoginFlag = isUpdateDirectEzLoginFlag;
    }

    /**
     * @return the directEzLoginFlag
     */
    public int getDirectEzLoginFlag() {
        return directEzLoginFlag;
    }

    /**
     * @param directEzLoginFlag
     *            the directEzLoginFlag to set
     */
    public void setDirectEzLoginFlag(int directEzLoginFlag) {
        this.directEzLoginFlag = directEzLoginFlag;
    }

    /**
     * @return the isUpdateLoginPasswdType
     */
    public boolean isUpdateLoginPasswdType() {
        return isUpdateLoginPasswdType;
    }

    /**
     * @param isUpdateLoginPasswdType
     *            the isUpdateLoginPasswdType to set
     */
    public void setUpdateLoginPasswdType(boolean isUpdateLoginPasswdType) {
        this.isUpdateLoginPasswdType = isUpdateLoginPasswdType;
    }

    /**
     * @return the loginPasswdType
     */
    public int getLoginPasswdType() {
        return loginPasswdType;
    }

    /**
     * @param loginPasswdType
     *            the loginPasswdType to set
     */
    public void setLoginPasswdType(int loginPasswdType) {
        this.loginPasswdType = loginPasswdType;
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
