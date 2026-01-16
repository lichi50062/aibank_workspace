package com.tfb.aibank.biz.user.services.login.model;

// @formatter:off
/**
 * @(#)DeviceInfo.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class DeviceInfo {

    /** Native App版本號 */
    private String appVersion;

    /** Resourece版號 */
    private String resVersion;

    /** Device Id */
    private String id;

    /** Device Model */
    private String model;

    /** Device Platform */
    private String platform;

    /** Device Platform Version */
    private String version;

    /** 1:WIFI 2:mobile */
    private String network;

    /** 裝置是否Root或JB 1:是 0:否 -1:預設 */
    private String jbFlag;

    /** 舊版本Device ID */
    private String oldId;

    /**
     * @return the appVersion
     */
    public String getAppVersion() {
        return appVersion;
    }

    /**
     * @param appVersion
     *            the appVersion to set
     */
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    /**
     * @return the resVersion
     */
    public String getResVersion() {
        return resVersion;
    }

    /**
     * @param resVersion
     *            the resVersion to set
     */
    public void setResVersion(String resVersion) {
        this.resVersion = resVersion;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model
     *            the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @param platform
     *            the platform to set
     */
    public void setPlatform(String platform) {
        this.platform = platform;
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
     * @return the network
     */
    public String getNetwork() {
        return network;
    }

    /**
     * @param network
     *            the network to set
     */
    public void setNetwork(String network) {
        this.network = network;
    }

    /**
     * @return the jbFlag
     */
    public String getJbFlag() {
        return jbFlag;
    }

    /**
     * @param jbFlag
     *            the jbFlag to set
     */
    public void setJbFlag(String jbFlag) {
        this.jbFlag = jbFlag;
    }

    /**
     * @return the oldId
     */
    public String getOldId() {
        return oldId;
    }

    /**
     * @param oldId
     *            the oldId to set
     */
    public void setOldId(String oldId) {
        this.oldId = oldId;
    }

}
