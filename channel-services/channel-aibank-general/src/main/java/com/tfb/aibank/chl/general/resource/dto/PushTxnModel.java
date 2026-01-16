package com.tfb.aibank.chl.general.resource.dto;

import java.util.ArrayList;
import java.util.List;

// @formatter:off
/**
 * @(#)PushTxnModel.java
 *
 * <p>Description:[通知設定]</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2025/08/22, eddy
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class PushTxnModel {

    /** 啟用 APP 訊息設定 原始設定 */
    private PushCodeModel notifyInfo;

    /** 訊息安全設定 原始設定 */
    private PushCodeModel msgInfo;

    /** 手機推播 原始設定 */
    private PushCodeModel pushInfo;

    /** 所有 通知設定項目 原始設定清單 */
    private List<PushCodeModel> list = new ArrayList<>();

    /** 身分證字號含誤別碼 */
    private String custIdDup;

    /** 身分證字號 */
    private String custId;

    /** 使用者代號 */
    private String userId;

    /** 公司類型 */
    private Integer companyKind;

    /** 用戶代碼 */
    private String nameCode;

    /** 誤別碼 */
    private String uidDup;

    /** 用戶類型 */
    private Integer type;

    /** 裝置鍵值 */
    private Integer deviceInfoKxy;

    /**
     * 是否授權推播設定，1:已設定；0未設定
     */
    private Integer notifyFlag;

    /**
     * 通知設定，0:未設定(全天可發送)；1:全天不發送；2:21:00~09:00不發送
     */
    private Integer notifyPass;

    /**
     * 是否切換夜間勿擾
     */
    private boolean isNightMode;

    /**
     * 是否授權訊息密碼設定，1:已授權；0或空白:未授權
     */
    private Integer msgFlag;
    /**
     * 推播代碼
     */
    private String pushCode;

    /**
     * 是否開啟
     */
    private boolean isOn;

    /**
     * 更新類型 1:MB_DEVICE_INFO 2:PUSH_SUBSCRIPTION
     */
    private String updateType;

    /**
     * 版本號
     */
    private String version;

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
     * @return the notifyInfo
     */
    public PushCodeModel getNotifyInfo() {
        return notifyInfo;
    }

    /**
     * @param notifyInfo
     *            the notifyInfo to set
     */
    public void setNotifyInfo(PushCodeModel notifyInfo) {
        this.notifyInfo = notifyInfo;
    }

    /**
     * @return the msgInfo
     */
    public PushCodeModel getMsgInfo() {
        return msgInfo;
    }

    /**
     * @param msgInfo
     *            the msgInfo to set
     */
    public void setMsgInfo(PushCodeModel msgInfo) {
        this.msgInfo = msgInfo;
    }

    /**
     * @return the pushInfo
     */
    public PushCodeModel getPushInfo() {
        return pushInfo;
    }

    /**
     * @param pushInfo
     *            the pushInfo to set
     */
    public void setPushInfo(PushCodeModel pushInfo) {
        this.pushInfo = pushInfo;
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
     * @return the updateType
     */
    public String getUpdateType() {
        return updateType;
    }

    /**
     * @param updateType
     *            the updateType to set
     */
    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    /**
     * @return the isOn
     */
    public boolean isOn() {
        return isOn;
    }

    /**
     * @param isOn
     *            the isOn to set
     */
    public void setOn(boolean isOn) {
        this.isOn = isOn;
    }

    /**
     * @return the deviceInfoKxy
     */
    public Integer getDeviceInfoKxy() {
        return deviceInfoKxy;
    }

    /**
     * @param deviceInfoKxy
     *            the deviceInfoKxy to set
     */
    public void setDeviceInfoKxy(Integer deviceInfoKxy) {
        this.deviceInfoKxy = deviceInfoKxy;
    }

    /**
     * @return the pushCode
     */
    public String getPushCode() {
        return pushCode;
    }

    /**
     * @param pushCode
     *            the pushCode to set
     */
    public void setPushCode(String pushCode) {
        this.pushCode = pushCode;
    }

    /**
     * @return the notifyFlag
     */
    public Integer getNotifyFlag() {
        return notifyFlag;
    }

    /**
     * @param notifyFlag
     *            the notifyFlag to set
     */
    public void setNotifyFlag(Integer notifyFlag) {
        this.notifyFlag = notifyFlag;
    }

    /**
     * @return the notifyPass
     */
    public Integer getNotifyPass() {
        return notifyPass;
    }

    /**
     * @param notifyPass
     *            the notifyPass to set
     */
    public void setNotifyPass(Integer notifyPass) {
        this.notifyPass = notifyPass;
    }

    /**
     * @return the msgFlag
     */
    public Integer getMsgFlag() {
        return msgFlag;
    }

    /**
     * @param msgFlag
     *            the msgFlag to set
     */
    public void setMsgFlag(Integer msgFlag) {
        this.msgFlag = msgFlag;
    }

    /**
     * @return the custIdDup
     */
    public String getCustIdDup() {
        return custIdDup;
    }

    /**
     * @param custIdDup
     *            the custIdDup to set
     */
    public void setCustIdDup(String custIdDup) {
        this.custIdDup = custIdDup;
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
     * @return the isNightMode
     */
    public Boolean getIsNightMode() {
        return isNightMode;
    }

    /**
     * @param isNightMode
     *            the isNightMode to set
     */
    public void setIsNightMode(boolean isNightMode) {
        this.isNightMode = isNightMode;
    }

    /**
     * @return the list
     */
    public List<PushCodeModel> getList() {
        return list;
    }

    /**
     * @param list
     *            the list to set
     */
    public void setList(List<PushCodeModel> list) {
        this.list = list;
    }
}
