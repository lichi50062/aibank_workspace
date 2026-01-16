/**
 * 
 */
package com.tfb.aibank.chl.general.resource.dto;

//@formatter:off
/**
* @(#)ChangeUuidAndPinBlockRequest.java
* 
* <p>Description:變更使用者代碼與密碼</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/02, John Chang 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class ChangeUuidAndPinBlockRequest {

    /** 身份證字號 */
    String custId;

    /** 使用者代號 */
    String userId;

    /** 新使用者代號 */
    String newUserId;

    /** 使用者密碼 */
    String pinblock;

    /** 新使用者密碼 */
    String newPinblock;

    /** Company Kind */
    Integer companyKind;

    /** 變更項目 */
    String changeItem;

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
     * @return the pinblock
     */
    public String getPinblock() {
        return pinblock;
    }

    /**
     * @param pinblock
     *            the pinblock to set
     */
    public void setPinblock(String pinblock) {
        this.pinblock = pinblock;
    }

    /**
     * @return the newPinblock
     */
    public String getNewPinblock() {
        return newPinblock;
    }

    /**
     * @param newPinblock
     *            the newPinblock to set
     */
    public void setNewPinblock(String newPinblock) {
        this.newPinblock = newPinblock;
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
     * @return the newUserId
     */
    public String getNewUserId() {
        return newUserId;
    }

    /**
     * @param newUserId
     *            the newUserId to set
     */
    public void setNewUserId(String newUserId) {
        this.newUserId = newUserId;
    }

    /**
     * @return the changeItem
     */
    public String getChangeItem() {
        return changeItem;
    }

    /**
     * @param changeItem
     *            the changeItem to set
     */
    public void setChangeItem(String changeItem) {
        this.changeItem = changeItem;
    }

}
