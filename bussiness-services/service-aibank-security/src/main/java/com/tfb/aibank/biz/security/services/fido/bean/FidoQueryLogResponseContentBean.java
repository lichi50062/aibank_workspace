/**
 * 
 */
package com.tfb.aibank.biz.security.services.fido.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author john
 *
 */
public class FidoQueryLogResponseContentBean {

    @SerializedName("MobileOid")
    private String mobileOid;

    @SerializedName("CreatedTime")
    private String createdTime;

    @SerializedName("UserId")
    private String userId;

    @SerializedName("DeviceId")
    private String deviceId;

    @SerializedName("FidoKeyId")
    private String fidoKeyId;

    @SerializedName("BusiNo")
    private String busiNo;

    @SerializedName("DeviceOs")
    private String deviceOs;

    @SerializedName("DeviceName")
    private String deviceName;

    @SerializedName("Invalid")
    private String invalid;

    /**
     * @return the mobileOid
     */
    public String getMobileOid() {
        return mobileOid;
    }

    /**
     * @param mobileOid
     *            the mobileOid to set
     */
    public void setMobileOid(String mobileOid) {
        this.mobileOid = mobileOid;
    }

    /**
     * @return the createdTime
     */
    public String getCreatedTime() {
        return createdTime;
    }

    /**
     * @param createdTime
     *            the createdTime to set
     */
    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
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
     * @return the deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId
     *            the deviceId to set
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return the fidoKeyId
     */
    public String getFidoKeyId() {
        return fidoKeyId;
    }

    /**
     * @param fidoKeyId
     *            the fidoKeyId to set
     */
    public void setFidoKeyId(String fidoKeyId) {
        this.fidoKeyId = fidoKeyId;
    }

    /**
     * @return the busiNo
     */
    public String getBusiNo() {
        return busiNo;
    }

    /**
     * @param busiNo
     *            the busiNo to set
     */
    public void setBusiNo(String busiNo) {
        this.busiNo = busiNo;
    }

    /**
     * @return the deviceOs
     */
    public String getDeviceOs() {
        return deviceOs;
    }

    /**
     * @param deviceOs
     *            the deviceOs to set
     */
    public void setDeviceOs(String deviceOs) {
        this.deviceOs = deviceOs;
    }

    /**
     * @return the deviceName
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * @param deviceName
     *            the deviceName to set
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    /**
     * @return the invalid
     */
    public String getInvalid() {
        return invalid;
    }

    /**
     * @param invalid
     *            the invalid to set
     */
    public void setInvalid(String invalid) {
        this.invalid = invalid;
    }

    //
    // /** 應用組合 */
    // @SerializedName("Action")
    // private String action;
    //
    // /** 選擇的驗證方式 */
    // @SerializedName("SelectType")
    // private String selectType;
    //
    // /** 驗證時間 */
    // @SerializedName("VerifyTime")
    // private String verifyTime;
    //
    // /** 簽章內文 */
    // @SerializedName("Plaintext")
    // private String plaintext;
    //
    // /** OutputParams 子項目 */
    // @SerializedName("FidoParams")
    // private String fidoParams;
    //
    // /**
    // * @return the action
    // */
    // public String getAction() {
    // return action;
    // }
    //
    // /**
    // * @param action
    // * the action to set
    // */
    // public void setAction(String action) {
    // this.action = action;
    // }
    //
    // /**
    // * @return the selectType
    // */
    // public String getSelectType() {
    // return selectType;
    // }
    //
    // /**
    // * @param selectType
    // * the selectType to set
    // */
    // public void setSelectType(String selectType) {
    // this.selectType = selectType;
    // }
    //
    // /**
    // * @return the verifyTime
    // */
    // public String getVerifyTime() {
    // return verifyTime;
    // }
    //
    // /**
    // * @param verifyTime
    // * the verifyTime to set
    // */
    // public void setVerifyTime(String verifyTime) {
    // this.verifyTime = verifyTime;
    // }
    //
    // /**
    // * @return the plaintext
    // */
    // public String getPlaintext() {
    // return plaintext;
    // }
    //
    // /**
    // * @param plaintext
    // * the plaintext to set
    // */
    // public void setPlaintext(String plaintext) {
    // this.plaintext = plaintext;
    // }
    //
    // /**
    // * @return the fidoParams
    // */
    // public String getFidoParams() {
    // return fidoParams;
    // }
    //
    // /**
    // * @param fidoParams
    // * the fidoParams to set
    // */
    // public void setFidoParams(String fidoParams) {
    // this.fidoParams = fidoParams;
    // }

}
