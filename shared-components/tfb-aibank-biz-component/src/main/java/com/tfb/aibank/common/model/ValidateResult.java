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
package com.tfb.aibank.common.model;

// @formatter:off
/**
 * @(#)ValidateResult.java
 * 
 * <p>Description:驗證用資訊
 * for fortify Access Control: Database
 * </p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/07, Evan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class ValidateResult {

    private Object validateObjMap;

    private Boolean openFlag;

    public ValidateResult(Object validateObjMap, Boolean openFlag) {
        this.validateObjMap = validateObjMap;
        this.openFlag = openFlag;
    }

    /**
     * @return the validateObjMap
     */
    public Object getValidateObjMap() {
        return validateObjMap;
    }

    /**
     * @param validateObjMap
     *            the validateObjMap to set
     */
    public void setValidateObjMap(Object validateObjMap) {
        this.validateObjMap = validateObjMap;
    }

    /**
     * @return the openFlag
     */
    public Boolean getOpenFlag() {
        return openFlag;
    }

    /**
     * @param openFlag
     *            the openFlag to set
     */
    public void setOpenFlag(Boolean openFlag) {
        this.openFlag = openFlag;
    }

}
