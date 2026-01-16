package com.tfb.aibank.biz.pushlistener.repository.aib.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)CustomizedNotificationRecordEntity.java
 * 
 * <p>Description:智能通知訊息 Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/01, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "CUSTOMIZED_NOTIFICATION_RECORD")
public class CustomizedNotificationRecordEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 推播訊息鍵值 */
    @Id
    @Column(name = "CUSTOMIZED_NOTIFICATION_RECORD_KEY")
    private Integer customizedNotificationRecordKey;

    /** 公司鍵值 */
    @Basic
    @Column(name = "COMPANY_KEY")
    private Integer companyKey;

    /** 使用者鍵值 */
    @Basic
    @Column(name = "USER_KEY")
    private Integer userKey;

    /** 是否發送推播 */
    @Basic
    @Column(name = "IS_PUSH")
    private int isPush;

    /** 業務別 */
    @Basic
    @Column(name = "BUS_TYPE")
    private String busType;

    /** 推播優先序，0~9，0:最優先；9:最後 */
    @Basic
    @Column(name = "PRIORITY")
    private Integer priority;

    /** 推播訊息 */
    @Basic
    @Column(name = "PUSH_MESSAGE")
    private String pushMessage;

    /** 推播訊息 */
    @Basic
    @Column(name = "PUSH_PARAM")
    private String pushParam;

    /** 標題訊息 */
    @Basic
    @Column(name = "TITLE_MESSAGE")
    private String titleMessage;

    /** 重要訊息 */
    @Basic
    @Column(name = "MESSAGE")
    private String message;

    /** 訊息連結標題 */
    @Basic
    @Column(name = "MESSAGE_LINK_TITLE")
    private String messageLinkTitle;

    /** 訊息連結 */
    @Basic
    @Column(name = "MESSAGE_LINK")
    private String messageLink;

    /** 重要訊息 */
    @Basic
    @Column(name = "MESSAGE_PARAM")
    private String messageParam;

    /** 傳送狀態，W:等待發送；P:正在發送；S:發送成功；F:發送失敗 */
    @Basic
    @Column(name = "SEND_STATUS")
    private String sendStatus;

    /** 狀態，O:開啟；C:關閉 */
    @Basic
    @Column(name = "STATUS")
    private String status;

    /** 是否已讀 */
    @Basic
    @Column(name = "IS_READ")
    private int isRead;

    /** 操作狀態 */
    @Basic
    @Column(name = "IS_ACTION")
    private int isAction;

    /** 開始日期時間 */
    @Basic
    @Column(name = "START_DATE")
    private Date startDate;

    /** 結束日期時間 */
    @Basic
    @Column(name = "END_DATE")
    private Date endDate;

    /** 更新時間 */
    @Basic
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /** 建立時間 */
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /** 傳送時間 */
    @Basic
    @Column(name = "PUSH_TIME")
    private Date pushTime;

    /** 重送推播訊息次數 */
    @Basic
    @Column(name = "RESEND_COUNT")
    private Integer resendCount = 0;

    /** 情境編號 */
    @Basic
    @Column(name = "OFFER_ID")
    private String offerId;

    /**
     * @return the customizedNotificationRecordKey
     */
    public Integer getCustomizedNotificationRecordKey() {
        return customizedNotificationRecordKey;
    }

    /**
     * @param customizedNotificationRecordKey
     *            the customizedNotificationRecordKey to set
     */
    public void setCustomizedNotificationRecordKey(Integer customizedNotificationRecordKey) {
        this.customizedNotificationRecordKey = customizedNotificationRecordKey;
    }

    /**
     * @return the companyKey
     */
    public Integer getCompanyKey() {
        return companyKey;
    }

    /**
     * @param companyKey
     *            the companyKey to set
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * @return the userKey
     */
    public Integer getUserKey() {
        return userKey;
    }

    /**
     * @param userKey
     *            the userKey to set
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
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
     * @return the pushParam
     */
    public String getPushParam() {
        return pushParam;
    }

    /**
     * @param pushParam
     *            the pushParam to set
     */
    public void setPushParam(String pushParam) {
        this.pushParam = pushParam;
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
     * @return the messageParam
     */
    public String getMessageParam() {
        return messageParam;
    }

    /**
     * @param messageParam
     *            the messageParam to set
     */
    public void setMessageParam(String messageParam) {
        this.messageParam = messageParam;
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
     * @return the isAction
     */
    public int getIsAction() {
        return isAction;
    }

    /**
     * @param isAction
     *            the isAction to set
     */
    public void setIsAction(int isAction) {
        this.isAction = isAction;
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

    /**
     * @return the pushTime
     */
    public Date getPushTime() {
        return pushTime;
    }

    /**
     * @param pushTime
     *            the pushTime to set
     */
    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    /**
     * @return the resendCount
     */
    public Integer getResendCount() {
        return resendCount;
    }

    /**
     * @param resendCount
     *            the resendCount to set
     */
    public void setResendCount(Integer resendCount) {
        this.resendCount = resendCount;
    }

    /**
     * @return the offerId
     */
    public String getOfferId() {
        return offerId;
    }

    /**
     * @param offerId
     *            the offerId to set
     */
    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

}
