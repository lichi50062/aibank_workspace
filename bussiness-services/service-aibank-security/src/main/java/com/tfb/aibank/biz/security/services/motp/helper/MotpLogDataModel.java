/**
 * 
 */
package com.tfb.aibank.biz.security.services.motp.helper;

import com.tfb.aibank.biz.component.identity.IdentityData;
import com.tfb.aibank.component.motplog.MotpLogActionType;

/**
 * @author john
 *
 */
public class MotpLogDataModel {

    IdentityData identityData;

    /** 身分證字號 */
    private String custId;

    /** 使用者代號 */
    private String userId;

    private MotpLogActionType type;

    private String serviceName;

    private String serviceMethod;

    /**
     * @return the identityData
     */
    public IdentityData getIdentityData() {
        return identityData;
    }

    /**
     * @param identityData
     *            the identityData to set
     */
    public void setIdentityData(IdentityData identityData) {
        this.identityData = identityData;
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
    public MotpLogActionType getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(MotpLogActionType type) {
        this.type = type;
    }

    /**
     * @return the serviceName
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * @param serviceName
     *            the serviceName to set
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * @return the serviceMethod
     */
    public String getServiceMethod() {
        return serviceMethod;
    }

    /**
     * @param serviceMethod
     *            the serviceMethod to set
     */
    public void setServiceMethod(String serviceMethod) {
        this.serviceMethod = serviceMethod;
    }

}
