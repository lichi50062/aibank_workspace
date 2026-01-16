/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.user.repository.entities.pk;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

// @formatter:off
/**
 * @(#)DeviceModelPk.java
 * 
 * <p>Description:行動裝置型號對應表 PK</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/20, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class DeviceModelPk implements Serializable {

    private static final long serialVersionUID = -3490894204096060566L;

    /**
     * 裝置型號代碼
     */
    private String deviceModel;

    /**
     * constructor
     */
    public DeviceModelPk() {
        super();
    }

    /**
     * constructor
     * 
     * @param deviceModel
     * @param locale
     */
    public DeviceModelPk(String deviceModel) {
        super();
        this.deviceModel = deviceModel;
    }

    /**
     * @return the deviceModel
     */
    public String getDeviceModel() {
        return deviceModel;
    }

    /**
     * @param deviceModel
     *            the deviceModel to set
     */
    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    @Override
    public int hashCode() {

        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {

        return EqualsBuilder.reflectionEquals(this, obj);
    }

}
