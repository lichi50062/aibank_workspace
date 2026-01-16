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
package com.tfb.aibank.chl.component.notification.model;

import java.util.Date;

//@formatter:off
/**
* @(#)CreateOtpRecordRequest.java
* 
* <p>Description:建立個人化通知訊息 - Request</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/27, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CreatePersonalNotificationRequest {

    /** 身分證字號 */
    private String custId;

    /** 使用者代號 */
    private String userId;

    /** 使用者誤別碼 */
    private String uidDup;

    /** 公司類型 */
    private int companyKind;

    /** 是否要發送推播，0:無需推播；1:要推播 */
    private int isPush;

    /** 推播代碼，1:個人化訊息；2:行銷訊息；3:系統公告；4:智能訊息 */
    private String pushCode;

    /** 業務別，DS:存款、PY:繳費、CC:信用卡、MF:基金、NF:投資(含奈米投&奈米理財)、LN:貸款、BO:資產負債、PO:點數、PS:個人服務、GN:共用 */
    private String busType;

    /** 推播優先序，0~9，0:最優先；9:最後 */
    private Integer priority;

    /** 推播訊息 */
    private String pushMessage;

    /** 標題訊息 */
    private String titleMessage;

    /** 重要訊息 */
    private String message;

    /** 訊息連結標題 */
    private String messageLinkTitle;

    /** 訊息連結 */
    private String messageLink;

    /** 傳送狀態，W:等待發送；P:正在發送；S:發送成功；F:發送失敗 */
    private String sendStatus;

    /** 狀態，O:開啟；C:關閉/刪除/回收 */
    private String status;

    /** 是否已讀，0:未讀；1:已讀 */
    private int isRead;

    /** 上稿類推播鍵值,只有上稿產生才有值 */
    private Integer uploadKey;

    /** 開始日期時間 */
    private Date startDate;

    /** 結束日期時間 */
    private Date endDate;

    /** 更新時間 */
    private Date updateTime;

    /** 建立時間 */
    private Date createTime;

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
    public int getCompanyKind() {
        return companyKind;
    }

    /**
     * @param companyKind
     *            the companyKind to set
     */
    public void setCompanyKind(int companyKind) {
        this.companyKind = companyKind;
    }

    /**
     * @return the isPush
     */
    public int getIsPush() {
        return isPush;
    }

    /**
     * @param isPush
     *            the isPush to set
     */
    public void setIsPush(int isPush) {
        this.isPush = isPush;
    }

    /**
     * @return the pushCode
     */
    public String getPushCode() {
        return pushCode;
    }

    /**
     * @param pushCode
     *            the pushCode to set
     */
    public void setPushCode(String pushCode) {
        this.pushCode = pushCode;
    }

    /**
     * @return the busType
     */
    public String getBusType() {
        return busType;
    }

    /**
     * @param busType
     *            the busType to set
     */
    public void setBusType(String busType) {
        this.busType = busType;
    }

    /**
     * @return the priority
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * @param priority
     *            the priority to set
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * @return the pushMessage
     */
    public String getPushMessage() {
        return pushMessage;
    }

    /**
     * @param pushMessage
     *            the pushMessage to set
     */
    public void setPushMessage(String pushMessage) {
        this.pushMessage = pushMessage;
    }

    /**
     * @return the titleMessage
     */
    public String getTitleMessage() {
        return titleMessage;
    }

    /**
     * @param titleMessage
     *            the titleMessage to set
     */
    public void setTitleMessage(String titleMessage) {
        this.titleMessage = titleMessage;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the messageLinkTitle
     */
    public String getMessageLinkTitle() {
        return messageLinkTitle;
    }

    /**
     * @param messageLinkTitle
     *            the messageLinkTitle to set
     */
    public void setMessageLinkTitle(String messageLinkTitle) {
        this.messageLinkTitle = messageLinkTitle;
    }

    /**
     * @return the messageLink
     */
    public String getMessageLink() {
        return messageLink;
    }

    /**
     * @param messageLink
     *            the messageLink to set
     */
    public void setMessageLink(String messageLink) {
        this.messageLink = messageLink;
    }

    /**
     * @return the sendStatus
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * @param sendStatus
     *            the sendStatus to set
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the isRead
     */
    public int getIsRead() {
        return isRead;
    }

    /**
     * @param isRead
     *            the isRead to set
     */
    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    /**
     * @return the uploadKey
     */
    public Integer getUploadKey() {
        return uploadKey;
    }

    /**
     * @param uploadKey
     *            the uploadKey to set
     */
    public void setUploadKey(Integer uploadKey) {
        this.uploadKey = uploadKey;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     *            the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     *            the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUidDup() {
        return uidDup;
    }

    public void setUidDup(String uidDup) {
        this.uidDup = uidDup;
    }
}
