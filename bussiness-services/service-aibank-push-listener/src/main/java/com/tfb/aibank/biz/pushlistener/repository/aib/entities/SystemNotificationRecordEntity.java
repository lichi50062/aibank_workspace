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
 * @(#)SystemNotificationRecordEntity.java
 * 
 * <p>Description:系統通知訊息 Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "SYSTEM_NOTIFICATION_RECORD")
public class SystemNotificationRecordEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 通知記錄鍵值 */
    @Id
    @Column(name = "SYSTEM_NOTIFICATION_RECORD_KEY")
    private Integer systemNotificationRecordKey;

    /** 公告類Topic */
    @Basic
    @Column(name = "PUSH_TOPIC")
    private String pushTopic;

    /** 是否要發送推播，0:無需推播；1:要推播 */
    @Basic
    @Column(name = "IS_PUSH")
    private int isPush;

    /** 推播代碼，1:個人化訊息；2:行銷訊息；3:系統公告；4:智能訊息 */
    @Basic
    @Column(name = "PUSH_CODE")
    private String pushCode;

    /** 業務別，DS:存款、PY:繳費、CC:信用卡、MF:基金、NF:投資(含奈米投&奈米理財)、LN:貸款、BO:資產負債、PO:點數、PS:個人服務、GN:共用 */
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

    /** 傳送狀態，W:等待發送；P:正在發送；S:發送成功；F:發送失敗 */
    @Basic
    @Column(name = "SEND_STATUS")
    private String sendStatus;

    /** 狀態，O:開啟；C:關閉' */
    @Basic
    @Column(name = "STATUS")
    private String status;

    /** 上稿類推播鍵值,只有上稿產生才有值' */
    @Basic
    @Column(name = "UPLOAD_KEY")
    private Integer uploadKey;

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

    public Integer getSystemNotificationRecordKey() {
        return systemNotificationRecordKey;
    }

    public void setSystemNotificationRecordKey(Integer systemNotificationRecordKey) {
        this.systemNotificationRecordKey = systemNotificationRecordKey;
    }

    public String getPushTopic() {
        return pushTopic;
    }

    public void setPushTopic(String pushTopic) {
        this.pushTopic = pushTopic;
    }

    public int getIsPush() {
        return isPush;
    }

    public void setIsPush(int isPush) {
        this.isPush = isPush;
    }

    public String getPushCode() {
        return pushCode;
    }

    public void setPushCode(String pushCode) {
        this.pushCode = pushCode;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getPushMessage() {
        return pushMessage;
    }

    public void setPushMessage(String pushMessage) {
        this.pushMessage = pushMessage;
    }

    public String getTitleMessage() {
        return titleMessage;
    }

    public void setTitleMessage(String titleMessage) {
        this.titleMessage = titleMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageLinkTitle() {
        return messageLinkTitle;
    }

    public void setMessageLinkTitle(String messageLinkTitle) {
        this.messageLinkTitle = messageLinkTitle;
    }

    public String getMessageLink() {
        return messageLink;
    }

    public void setMessageLink(String messageLink) {
        this.messageLink = messageLink;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUploadKey() {
        return uploadKey;
    }

    public void setUploadKey(Integer uploadKey) {
        this.uploadKey = uploadKey;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public Integer getResendCount() {
        return resendCount;
    }

    public void setResendCount(Integer resendCount) {
        this.resendCount = resendCount;
    }

}
