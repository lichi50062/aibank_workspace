/**
 * 
 */
package com.tfb.aibank.chl.general.resource.dto;

//@formatter:off
/**
* @(#)DeleteMbDeviceInfoRequest.java
* 
* <p>Description:刪除 MB_DEVICE_INFO Request</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/10/17, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class DeleteMbDeviceInfoRequest {

    /** 使用者ID */
    private String custId;

    /** [誤別碼] 公司統編或身份證字號誤別碼 */
    private String uidDup;

    /** 使用者代號 */
    private String userId;

    /** 公司類型 */
    private Integer companyKind;

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

}
