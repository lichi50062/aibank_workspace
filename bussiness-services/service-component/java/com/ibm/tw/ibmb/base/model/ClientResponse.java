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
package com.ibm.tw.ibmb.base.model;

import java.util.Map;

import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.IBUtils.ErrorDescription;

// @formatter:off
/**
 * @(#)ClientResponse.java
 * 
 * <p>Description:由MobileFirst下行的回傳值</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/01, Alex LS Chen	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class ClientResponse {

    /** 回應錯誤來源的系統別 */
    private String sys = IBSystemId.SVC.getSystemId();

    /** 錯誤碼： 系統別為SVC且錯誤碼為0000:成功, 1001:欄位錯誤, 1002:彈出錯誤顯示 */
    private String code = "0000";

    /** 錯誤描述：成功時為空白或無此欄位 */
    private String desc;

    /** 錯誤註記，1:引導；2:錯誤：成功時為空白或無此欄位 */
    private int errorFlag;

    /** 錯誤標題：成功時為空白或無此欄位 */
    private String title;

    /** 提示文字區塊：成功時為空白或無此欄位 */
    private String info;

    /** 轉導按鈕名稱1：成功時為空白或無此欄位 */
    private String directButtonName1;

    /** 轉導交易1：成功時為空白或無此欄位 */
    private String directTaskId1;

    /** 轉導按鈕名稱2：成功時為空白或無此欄位 */
    private String directButtonName2;

    /** 轉導交易2：成功時為空白或無此欄位 */
    private String directTaskId2;

    /** 交易重覆檢核用Token */
    private String token;

    /** Optional, code回應0113會由此取值, 錯誤內容, 只接受一層的key:stringValue, 但數量不限 */
    private Object errorField;

    /** 回應訊息, 由交易自行定義 */
    private Object rsData;

    /** 伺服器端時間 */
    private Long serverTime;

    /** 交易存取記錄追蹤編號 */
    private String traceId;

    public ClientResponse() {
        this.serverTime = System.currentTimeMillis();
    }

    public void setRsError(String statusCode, String statusDesc) {
        setCode(statusCode);
        setDesc(statusDesc);
    }

    public void setRsError(String systemId, String statusCode, String errorDesc, String errorDetail) {
        setSys(systemId);
        setCode(statusCode);
        setDesc(errorDesc);
    }

    public void setRsError(String systemId, String statusCode, ErrorDescription errorDescription) {
        setSys(systemId);
        setCode(statusCode);
        setDesc(errorDescription.getErrorDesc());
        setErrorFlag(errorDescription.getErrorFlag());
        setTitle(errorDescription.getTitle());
        setInfo(errorDescription.getInfo());
        setDirectButtonName1(errorDescription.getDirectButtonName1());
        setDirectTaskId1(errorDescription.getDirectTaskId1());
        setDirectButtonName2(errorDescription.getDirectButtonName2());
        setDirectTaskId2(errorDescription.getDirectTaskId2());
    }

    public void setRsError(String systemId, String statusCode, ErrorDescription errorDescription, Map<String, String> extraParamMap) {
        setRsError(systemId, statusCode, errorDescription);
        if (null != extraParamMap) {
            final String ERROR_INFO_KEY_TARGETTASK = "TARGET_TASK";
            final String ERROR_INFO_KEY_TARGETTASK2 = "TARGET_TASK2";

            if (extraParamMap.containsKey(ERROR_INFO_KEY_TARGETTASK) && StringUtils.equals(ERROR_INFO_KEY_TARGETTASK, getDirectTaskId1())) {
                setDirectTaskId1(extraParamMap.get(ERROR_INFO_KEY_TARGETTASK));
            }
            else if (!ERROR_INFO_KEY_TARGETTASK.equals(getDirectTaskId1()) && extraParamMap.containsKey(getDirectTaskId1())) {
                setDirectTaskId1(getDirectTaskId1() + extraParamMap.get(getDirectTaskId1()));

            }

            if (extraParamMap.containsKey(ERROR_INFO_KEY_TARGETTASK2) && StringUtils.equals(ERROR_INFO_KEY_TARGETTASK2, getDirectTaskId2())) {
                setDirectTaskId2(extraParamMap.get(ERROR_INFO_KEY_TARGETTASK2));
            }
            else if (!ERROR_INFO_KEY_TARGETTASK2.equals(getDirectTaskId2()) && extraParamMap.containsKey(getDirectTaskId2())) {
                setDirectTaskId2(getDirectTaskId2() + extraParamMap.get(getDirectTaskId2()));

            }

        }
    }

    public Object getRsData() {
        return rsData;
    }

    public void setRsData(Object rsData) {
        this.rsData = rsData;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getErrorField() {
        return errorField;
    }

    public void setErrorField(Object errorField) {
        this.errorField = errorField;
    }

    public Long getServerTime() {
        return serverTime;
    }

    public void setServerTime(Long serverTime) {
        this.serverTime = serverTime;
    }

    public int getErrorFlag() {
        return errorFlag;
    }

    public void setErrorFlag(int errorFlag) {
        this.errorFlag = errorFlag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirectButtonName1() {
        return directButtonName1;
    }

    public void setDirectButtonName1(String directButtonName1) {
        this.directButtonName1 = directButtonName1;
    }

    public String getDirectTaskId1() {
        return directTaskId1;
    }

    public void setDirectTaskId1(String directTaskId1) {
        this.directTaskId1 = directTaskId1;
    }

    public String getDirectButtonName2() {
        return directButtonName2;
    }

    public void setDirectButtonName2(String directButtonName2) {
        this.directButtonName2 = directButtonName2;
    }

    public String getDirectTaskId2() {
        return directTaskId2;
    }

    public void setDirectTaskId2(String directTaskId2) {
        this.directTaskId2 = directTaskId2;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

}
