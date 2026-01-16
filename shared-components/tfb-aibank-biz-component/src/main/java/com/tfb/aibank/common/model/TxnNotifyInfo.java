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

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

// @formatter:off
/**
 * @(#)TxnNotifyInfo.java
 * 
 * <p>Description:交易通知作業資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/12, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class TxnNotifyInfo {

    public TxnNotifyInfo() {
        // default constructor
    }

    public TxnNotifyInfo(String subject, Map<String, Object> params) {
        this.subject = subject;
        this.params = params;
    }

    public TxnNotifyInfo(String subject, String mailTo, Map<String, Object> params) {
        this.subject = subject;
        this.mailTo = mailTo;
        this.params = params;
    }

    public TxnNotifyInfo(String message) {
        this.message = message;
    }

    public TxnNotifyInfo(String message, Integer masterKey, Integer detailKey) {
        this.message = message;
        this.masterKey = masterKey;
        this.detailKey = detailKey;
    }

    /** Email 主旨 */
    private String subject;
    /** Email 參數內容 */
    private Map<String, Object> params;
    /** Email 收件人 */
    private String mailTo;

    /** SMS 指定行動電話 */
    private String mobileNo;
    /** SMS 簡訊內容 */
    private String message;
    /** SMS 交易主檔鍵值 */
    private Integer masterKey;
    /** SMS 交易明細檔鍵值 */
    private Integer detailKey;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getMasterKey() {
        return masterKey;
    }

    public void setMasterKey(Integer masterKey) {
        this.masterKey = masterKey;
    }

    public Integer getDetailKey() {
        return detailKey;
    }

    public void setDetailKey(Integer detailKey) {
        this.detailKey = detailKey;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setParams4Str(Map<String, String> params4Str) {
        this.params = params4Str.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> Objects.requireNonNullElse(e.getValue(), "")));
    }

    public void setParams4Obj(Map<String, Object> params4Obj) {
        this.params = params4Obj;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

}
