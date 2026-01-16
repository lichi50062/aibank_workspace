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
package com.tfb.aibank.biz.security.services.motp.type;

//@formatter:off
/**
* @(#)MotpDeviceStatus.java
* 
* <p>Description:MOTP裝置綁定資訊 - 使用狀態</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/10, HankChan   
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum MotpDeviceStatus {

    /**
     * 初始建立
     */
    INIT,

    /**
     * 確定已啟用
     */
    ENABLE,

    /**
     * 已註銷
     */
    DISABLE,

    /**
     * 被遠端註銷的裝置
     */
    REMOTE_DISABLE,

    /**
     * 遠端刪除後，原裝置資訊已被裝置註銷
     */
    REMOTE_PROFILE_REMOVED,

    /**
     * 未完成註冊 在註冊新裝置時，先前未完成註冊得裝置會被更新成此狀態 INIT --> DEVICE_NOT_ACTIVE
     */
    DEVICE_NOT_ACTIVATION,

    /**
     * 裝置私鑰已經遺失，MOTP已失效，需重新綁定
     */
    DEVICE_KEYPAIR_LOST,

    /**
     * 全景裝置綁定資訊已遺失
     */
    MOTP_ACCOUNT_LOST;

}
