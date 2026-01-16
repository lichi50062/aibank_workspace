/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.devicebinding.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ibm.tw.ibmb.annotations.FormatDate;

// @formatter:off
/**
 * @(#)TrustDeviceInfo.java
 * 
 * <p>Description:[TWO_FACTOR_AUTH 雙重驗證記錄檔 & TRUST_DEVICE信任裝置記錄檔]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/20, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class TrustDeviceInfo {
    public TrustDeviceInfo() {
    }

    /** 身分證字號含誤別碼 */
    private String custIdDup;

    /** 身分證字號 */
    private String custId;

    /** 使用者代號 */
    private String userId;

    /** 公司類型 */
    private Integer companyKind;

    /** 誤別碼 */
    private String uidDup;

    /** 裝置鍵值 */
    private String deviceUuid;

    /** 是否開啟雙重驗證 */
    private boolean isOn;

    private List<TrustDeviceDetail> list = new ArrayList<>();

    // @formatter:off
    /**
     * @(#)TrustDeviceInfo.java
     * 
     * <p>Description: 已信任裝置 DBuserNEB.TRUST_DEVICE 資料]</p>
     * 
     * <p>Modify History:</p>
     * v1.0, 2025/05/20, leiley	
     * <ol>
     *  <li>初版</li>
     * </ol>
     */
    // @formatter:on
    public static class TrustDeviceDetail {
        public TrustDeviceDetail() {
        }

        /**
         * 裝置類型<br>
         * WEB：網頁 APP：Fubon+
         */
        private String deviceType;

        /** 裝置名稱 */
        private String deviceName;

        /** 裝置鍵值 */
        private String deviceId;

        /** OS */
        private String os;

        /** 瀏覽器類型 */
        private String browserType;

        /** 建立時間 */
        @FormatDate(pattern = "yyyy/MM/dd")
        private Date createTime;

        // 以下為 已綁裝置資訊
        /** 上次登入時間：TWO_FACTOR_AUTH.UPDATE_TIME */
        @FormatDate(pattern = "yyyy/MM/dd HH:mm")
        private Date lastLoginTime;

        /** 雙重驗證設定時間 AIBANK_MB_DEVICE_INFO.TWOSTEP_FLAG_TIME */
        @FormatDate(pattern = "yyyy/MM/dd")
        private Date twostepFlagTime;

        /**
         * @return the twostepFlagTime
         */
        public Date getTwostepFlagTime() {
            return twostepFlagTime;
        }

        /**
         * @param twostepFlagTime
         *            the twostepFlagTime to set
         */
        public void setTwostepFlagTime(Date twostepFlagTime) {
            this.twostepFlagTime = twostepFlagTime;
        }

        /**
         * @return the lastLoginTime
         */
        public Date getLastLoginTime() {
            return lastLoginTime;
        }

        /**
         * @param lastLoginTime
         *            the lastLoginTime to set
         */
        public void setLastLoginTime(Date lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        /**
         * @return the deviceType
         */
        public String getDeviceType() {
            return deviceType;
        }

        /**
         * @param deviceType
         *            the deviceType to set
         */
        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
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
         * @return the os
         */
        public String getOs() {
            return os;
        }

        /**
         * @param os
         *            the os to set
         */
        public void setOs(String os) {
            this.os = os;
        }

        /**
         * @return the browserType
         */
        public String getBrowserType() {
            return browserType;
        }

        /**
         * @param browserType
         *            the browserType to set
         */
        public void setBrowserType(String browserType) {
            this.browserType = browserType;
        }

        /**
         * @return the createTime
         */
        public Date getCreateTime() {
            return createTime;
        }

        /**
         * @param createTime
         *            the createTime to set
         */
        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

    }

    /**
     * @return the list
     */
    public List<TrustDeviceDetail> getList() {
        return list;
    }

    /**
     * @param list
     *            the list to set
     */
    public void setList(List<TrustDeviceDetail> list) {
        this.list = list;
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

}
