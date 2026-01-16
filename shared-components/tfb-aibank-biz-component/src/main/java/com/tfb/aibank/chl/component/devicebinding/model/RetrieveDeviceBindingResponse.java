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
package com.tfb.aibank.chl.component.devicebinding.model;

//@formatter:off
/**
* @(#)RetrieveDeviceBindingResponse.java
* 
* <p>Description:取得裝置綁定資料 - Response</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/19, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class RetrieveDeviceBindingResponse {

    /** 裝置綁定狀態 */
    private boolean isBind;

    /** 綁定裝置設定 */
    private MbDeviceInfo model;

    /** 綁定裝置身份證字號 */
    private String custId;

    /** 綁定使用者代號 */
    private String userId;

    /**
     * 公司類型
     */
    private Integer companyKind;

    /**
     * 誤別碼
     */
    private String dup;

    /**
     * 免登速查flag=> Y/N
     */
    private String qsearchFlag;

    /**
     * @return the isBind
     */
    public boolean isBind() {
        return isBind;
    }

    /**
     * @param isBind
     *            the isBind to set
     */
    public void setBind(boolean isBind) {
        this.isBind = isBind;
    }

    /**
     * @return the model
     */
    public MbDeviceInfo getModel() {
        return model;
    }

    /**
     * @param model
     *            the model to set
     */
    public void setModel(MbDeviceInfo model) {
        this.model = model;
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
     * @param companyKind the companyKind to set
     */
    public void setCompanyKind(Integer companyKind) {
        this.companyKind = companyKind;
    }

    /**
     * @return the dup
     */
    public String getDup() {
        return dup;
    }

    /**
     * @param dup
     *            the dup to set
     */
    public void setDup(String dup) {
        this.dup = dup;
    }

    public String getQsearchFlag() {
        return qsearchFlag;
    }

    public void setQsearchFlag(String qsearchFlag) {
        this.qsearchFlag = qsearchFlag;
    }
}
