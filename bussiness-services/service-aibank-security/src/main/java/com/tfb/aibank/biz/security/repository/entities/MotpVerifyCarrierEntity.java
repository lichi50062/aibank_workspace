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
package com.tfb.aibank.biz.security.repository.entities;

import java.io.Serializable;
import java.util.Date;

import com.tfb.aibank.biz.security.services.motp.type.MotpCarrierStatus;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

//@formatter:off
/**
* @(#)MotpVerifyCarrierEntity.java
* 
* <p>Description:MOTP交易認證資訊</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/29, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Entity
@Table(name = "MOTP_VERIFY_CARRIER")
public class MotpVerifyCarrierEntity implements Serializable {

    /**
     * <code>serialVersionUID</code> 的註解
     */
    private static final long serialVersionUID = 1L;

    /** 推播MOTP驗證鍵值 */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOTP_VERIFY_CARRIER_SEQ")
    @SequenceGenerator(name = "MOTP_VERIFY_CARRIER_SEQ", sequenceName = "MOTP_VERIFY_CARRIER_SEQ", allocationSize = 1)
    @Column(name = "MOTP_VERIFY_KEY")
    private Integer motpVerifyKey;

    /** 交易資訊主檔資料(FK) */
    @Basic
    @Column(name = "MOTP_TRANS_KEY")
    private Integer motpTransKey;

    /**
     * 推播OTP驗證碼狀態 0:尚未發送 1:成功發送 2:驗證失敗一次 3:驗證失敗二次 4:驗證失敗三次（交易被迫取消） 5:重新發送（此筆已失效） 9:推播OTP發送失敗
     */
    @Basic
    @Column(name = "CARRIER_STATUS")
    @Enumerated(EnumType.STRING)
    private MotpCarrierStatus carrierStatus;

    /**
     * 推播OTP驗證碼 僅留存，不以此驗證
     */
    @Basic
    @Column(name = "MOTP")
    private String motp;

    /** 推播訊息通知 */
    @Basic
    @Column(name = "TITLE")
    private String title;

    /** 推播訊息內容 */
    @Basic
    @Column(name = "MESSAGE")
    private String message;

    /** 彈跳視窗內容 */
    @Basic
    @Column(name = "POPUP")
    private String popup;

    /** 驗證次數 */
    @Basic
    @Column(name = "VERIFY_COUNT")
    private Integer verifyCount;

    /** 使用者驗證異動時戳 */
    @Basic
    @Column(name = "VERIFY_TIMESTAMP")
    private Date verifyTimestamp;

    /** 建立時間 */
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /** 推播OTP驗證碼狀態描述 */
    @Basic
    @Column(name = "CARRIER_STATUS_DESC")
    private String carrierStatusDesc;

    /**
     * @return the motpVerifyKey
     */
    public Integer getMotpVerifyKey() {
        return motpVerifyKey;
    }

    /**
     * @param motpVerifyKey
     *            the motpVerifyKey to set
     */
    public void setMotpVerifyKey(Integer motpVerifyKey) {
        this.motpVerifyKey = motpVerifyKey;
    }

    /**
     * @return the motpTransKey
     */
    public Integer getMotpTransKey() {
        return motpTransKey;
    }

    /**
     * @param motpTransKey
     *            the motpTransKey to set
     */
    public void setMotpTransKey(Integer motpTransKey) {
        this.motpTransKey = motpTransKey;
    }

    /**
     * @return the carrierStatus
     */
    public MotpCarrierStatus getCarrierStatus() {
        return carrierStatus;
    }

    /**
     * @param carrierStatus
     *            the carrierStatus to set
     */
    public void setCarrierStatus(MotpCarrierStatus carrierStatus) {
        this.carrierStatus = carrierStatus;
    }

    /**
     * @return the motp
     */
    public String getMotp() {
        return motp;
    }

    /**
     * @param motp
     *            the motp to set
     */
    public void setMotp(String motp) {
        this.motp = motp;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
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
     * @return the popup
     */
    public String getPopup() {
        return popup;
    }

    /**
     * @param popup
     *            the popup to set
     */
    public void setPopup(String popup) {
        this.popup = popup;
    }

    /**
     * @return the verifyCount
     */
    public Integer getVerifyCount() {
        return verifyCount;
    }

    /**
     * @param verifyCount
     *            the verifyCount to set
     */
    public void setVerifyCount(Integer verifyCount) {
        this.verifyCount = verifyCount;
    }

    /**
     * @return the verifyTimestamp
     */
    public Date getVerifyTimestamp() {
        return verifyTimestamp;
    }

    /**
     * @param verifyTimestamp
     *            the verifyTimestamp to set
     */
    public void setVerifyTimestamp(Date verifyTimestamp) {
        this.verifyTimestamp = verifyTimestamp;
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
     * @return the carrierStatusDesc
     */
    public String getCarrierStatusDesc() {
        return carrierStatusDesc;
    }

    /**
     * @param carrierStatusDesc
     *            the carrierStatusDesc to set
     */
    public void setCarrierStatusDesc(String carrierStatusDesc) {
        this.carrierStatusDesc = carrierStatusDesc;
    }

}
