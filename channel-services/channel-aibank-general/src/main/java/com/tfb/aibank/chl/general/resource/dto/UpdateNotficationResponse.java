/**
 * 
 */
package com.tfb.aibank.chl.general.resource.dto;

import java.util.List;

//@formatter:off
/**
* @(#)UpdateNotficationResponse.java
* 
* <p>Description:更新推播通知設定 - Request</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/02, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class UpdateNotficationResponse {

    /** 失敗的設定項目 */
    private List<String> failNotificationTypes;

    /** 成功的設定項目 */
    private List<String> succNotificationTypes;

    /**
     * @return the failNotificationTypes
     */
    public List<String> getFailNotificationTypes() {
        return failNotificationTypes;
    }

    /**
     * @param failNotificationTypes
     *            the failNotificationTypes to set
     */
    public void setFailNotificationTypes(List<String> failNotificationTypes) {
        this.failNotificationTypes = failNotificationTypes;
    }

    /**
     * @return the succNotificationTypes
     */
    public List<String> getSuccNotificationTypes() {
        return succNotificationTypes;
    }

    /**
     * @param succNotificationTypes
     *            the succNotificationTypes to set
     */
    public void setSuccNotificationTypes(List<String> succNotificationTypes) {
        this.succNotificationTypes = succNotificationTypes;
    }

}
