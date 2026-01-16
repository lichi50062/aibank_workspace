package com.tfb.aibank.chl.creditcardactivities.resource.dto;

//@formatter:off
/**
 * @(#)AddActivetyOnlineRecordRequest.java
 * 
 * <p>Description:新增一筆CC_ACTIVITY_ONLINESTATIS 紀錄 Request</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/12, John Chang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class AddActivetyOnlineRecordRequest {

    /** 公司類型 */
    private Integer companyKind;

    /** 身分證號 */
    private String custId;

    /** 使用者代碼 */
    private String userId;

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

}
