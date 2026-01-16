/**
 * 
 */
package com.tfb.aibank.biz.user.services.devicebindings.model;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)UpdateNotficationRequest.java
* 
* <p>Description:更新推播通知設定 - Request</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/02, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class UpdateNotficationRequest {

    /** 身分證字號 */
    @Schema(description = "身分證字號")
    private String custId;

    /** 使用者代號 */
    @Schema(description = "使用者代號")
    private String userId;

    /** 誤別碼 */
    @Schema(description = "誤別碼")
    private String uidDup;

    /** 公司類型 */
    @Schema(description = "公司類型")
    private Integer companyKind;

    /**
     * 是否授權訊息通知，1:已授權；0或空白:未授權; -1 本次不設定
     */
    @Schema(description = "是否授權訊息通知")
    private int notificationAgreeFlag;

    /**
     * 是否同意開啟Line 1:已授權；0或空白; -1 本次不設定
     */
    @Schema(description = "是否同意開啟Line")
    private int lineAgreeFlag;

    /**
     * 行動電話
     */
    @Schema(description = "行動電話")
    private String mobileNo;

    /** 生日 */
    @Schema(description = "生日")
    private Date birthday;

    /** 用戶代碼. */
    @Schema(description = "用戶代碼")
    private String nameCode;

    /**
     * 版本號
     */
    @Schema(description = "版本號")
    private String version;

    /** 用戶類型 */
    @Schema(description = "用戶類型")
    private Integer type;

    /**
     * @return the type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version
     *            the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the notificationAgreeFlag
     */
    public int getNotificationAgreeFlag() {
        return notificationAgreeFlag;
    }

    /**
     * @param notificationAgreeFlag
     *            the notificationAgreeFlag to set
     */
    public void setNotificationAgreeFlag(int notificationAgreeFlag) {
        this.notificationAgreeFlag = notificationAgreeFlag;
    }

    /**
     * @return the lineAgreeFlag
     */
    public int getLineAgreeFlag() {
        return lineAgreeFlag;
    }

    /**
     * @param lineAgreeFlag
     *            the lineAgreeFlag to set
     */
    public void setLineAgreeFlag(int lineAgreeFlag) {
        this.lineAgreeFlag = lineAgreeFlag;
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
     * @return the mobileNo
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * @param mobileNo
     *            the mobileNo to set
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     *            the birthday to set
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

}
