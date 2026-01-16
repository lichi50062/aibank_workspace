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
package com.tfb.aibank.biz.component.videoauthapi.model;

// @formatter:off
/**
 * @(#)VideoAuthStatus.java
 * 
 * <p>Description:[VideoAuthStatus]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/19, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum VideoAuthStatus {
    INIT, // 開始視訊驗證
    USER_DATA, // 已成功取得完整的UserData相關資訊
    SUCCESS, // 已成功與客服通話，正常結束
    FAIL, // 通話未完成(未與客服通話<自行掛斷/未授權視訊鏡頭/不支援此裝置/不支援瀏覽器/連線失敗/異常掛斷>)
    VERIFY_SUCCESS, // 視訊驗證客服回應<通過驗證>
    VERIFY_FAIL, // 視訊驗證客服回應<不通過驗證>
    // VERIFY_ACCIDENT, //視訊驗證客服回應<因其他未知因素驗證失敗>，需求已拿掉，同VERIFY_FAIL
    VERIFYING, // 視訊驗證客服回應<驗證中>
    VERIFY_EXPIRE, // 批次更新時，將已過期的資料寫入過期註記
    VERIFY_USER_EXP, // 使用者離申請時超過2小時，系統判定為逾期狀態，須重新申請
    AUTH_COMPLETE // 已完成視訊驗證流程，使用者完成後續綁定相關流程
}
