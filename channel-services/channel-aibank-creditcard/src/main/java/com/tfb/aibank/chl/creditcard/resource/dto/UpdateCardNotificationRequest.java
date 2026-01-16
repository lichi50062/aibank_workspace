/**
 * 
 */
package com.tfb.aibank.chl.creditcard.resource.dto;

//@formatter:off
/**
* @(#)UpdateCardNotificationRequest.java
* 
* <p>Description:提供「信用卡消費訊息通知設定」相關服務</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/23, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class UpdateCardNotificationRequest {

    /** 使用者ID */
    private String custId;

    /** 使用者代號 */
    private String userId;

    /** 用戶代碼 */
    private String nameCode;

    /** 卡號 */
    private String custCDNO;

    /** 申請消費訊息通知服務註記 */
    private String custANOS;

    /**
     * @return the custCDNO
     */
    public String getCustCDNO() {
        return custCDNO;
    }

    /**
     * @param custCDNO
     *            the custCDNO to set
     */
    public void setCustCDNO(String custCDNO) {
        this.custCDNO = custCDNO;
    }

    /**
     * @return the custANOS
     */
    public String getCustANOS() {
        return custANOS;
    }

    /**
     * @param custANOS
     *            the custANOS to set
     */
    public void setCustANOS(String custANOS) {
        this.custANOS = custANOS;
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

}
