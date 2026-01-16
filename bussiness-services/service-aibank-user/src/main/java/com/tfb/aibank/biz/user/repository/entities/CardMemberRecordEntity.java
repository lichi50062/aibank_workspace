/**
 * 
 */
package com.tfb.aibank.biz.user.repository.entities;

import java.util.Date;

import com.tfb.aibank.biz.user.repository.entities.support.CardMemberRecordEntityListener;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

//@formatter:off
/**
* @(#)CardMemberRecordEntity.java
* 
* <p>Description:信用卡專區會員資料 Entity</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/02, Edward Tien    
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Entity
@EntityListeners(CardMemberRecordEntityListener.class)
@Table(name = "CARD_MEMBER_RECORD")
public class CardMemberRecordEntity {

    /**
     * <code>serialVersionUID</code> 的註解
     */
    private static final long serialVersionUID = 1L;

    public static final String SEQUENCE_NAME = "CARD_MEMBER_RECORD_SEQ";

    /**
     * 主檔鍵值
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPANY_SEQ")
    @SequenceGenerator(name = "CARD_MEMBER_RECORD_SEQ", sequenceName = "CARD_MEMBER_RECORD_SEQ", allocationSize = 1)
    @Column(name = "RECORD_KEY")
    private Integer recordKey;

    /**
     * 信用卡專區會員資料鍵值，CARD_USER_PROFILE.CARD_KEY
     */
    @Basic
    @Column(name = "CARD_KEY")
    private Integer cardKey;

    /**
     * 交易存取記錄鍵值
     */
    @Basic
    @Column(name = "ACCESS_LOG_KEY")
    private Integer accessLogKey;

    /**
     * 變更項目 0：密碼解除 1：刪除
     */
    @Basic
    @Column(name = "CHANGE_ITEM")
    private String changeItem;

    /**
     * 停用原因 1：該註冊卡片已停用/掛失 2：已另申請其他會員帳號 3：不常使用 4：帳號/密碼資料疑遭盜用 5：不認同本服務約定條款 6：忘記密碼 7：已非本行卡友 8：服務功能不符需要 9：其它
     */
    @Basic
    @Column(name = "STOP_REASON")
    private String stopReason;

    /**
     * 身分證字號/統一編號，AES加密
     */
    @Basic
    @Column(name = "COMPANY_UID")
    private String companyUid;

    /**
     * 建立時間
     */
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 客服人員群組鍵值
     */
    @Basic
    @Column(name = "CS_GROUP_KEY")
    private Integer csGroupKey;

    /**
     * 客服人員群組名稱
     */
    @Basic
    @Column(name = "CS_GROUP_NAME")
    private String csGroupName;

    /**
     * 客服人員編號
     */
    @Basic
    @Column(name = "CS_ID")
    private String csId;

    /**
     * 客服人員姓名
     */
    @Basic
    @Column(name = "CS_NAME")
    private String csName;

    /**
     * 交易狀態
     */
    @Basic
    @Column(name = "TX_STATUS")
    private String txStatus;

    /**
     * 使用者代碼
     */
    @Basic
    @Column(name = "USER_UUID")
    private String userUuid;

    /**
     * 取得交易存取記錄鍵值
     *
     * @return Integer 交易存取記錄鍵值
     */
    public Integer getAccessLogKey() {
        return this.accessLogKey;
    }

    /**
     * 設定交易存取記錄鍵值
     *
     * @param accessLogKey
     *            要設定的交易存取記錄鍵值
     */
    public void setAccessLogKey(Integer accessLogKey) {
        this.accessLogKey = accessLogKey;
    }

    /**
     * 取得變更項目 0：密碼解除 1：該註冊卡片已停用/掛失 2：已另申請其他會員帳號 3：不常使用 4：帳號/密碼資料疑遭盜用 5：不認同本服務約定條款 6：忘記密碼 7：已非本行卡友 8：服務功能不符需要 9：其它
     *
     * @return String 變更項目 0：密碼解除 1：該註冊卡片已停用/掛失 2：已另申請其他會員帳號 3：不常使用 4：帳號/密碼資料疑遭盜用 5：不認同本服務約定條款 6：忘記密碼 7：已非本行卡友 8：服務功能不符需要 9：其它
     */
    public String getChangeItem() {
        return this.changeItem;
    }

    /**
     * 設定變更項目 0：密碼解除 1：該註冊卡片已停用/掛失 2：已另申請其他會員帳號 3：不常使用 4：帳號/密碼資料疑遭盜用 5：不認同本服務約定條款 6：忘記密碼 7：已非本行卡友 8：服務功能不符需要 9：其它
     *
     * @param changeItem
     *            要設定的變更項目 0：密碼解除 1：該註冊卡片已停用/掛失 2：已另申請其他會員帳號 3：不常使用 4：帳號/密碼資料疑遭盜用 5：不認同本服務約定條款 6：忘記密碼 7：已非本行卡友 8：服務功能不符需要 9：其它
     */
    public void setChangeItem(String changeItem) {
        this.changeItem = changeItem;
    }

    /**
     * 取得身分證字號/統一編號，AES加密
     *
     * @return String 身分證字號/統一編號，AES加密
     */
    public String getCompanyUid() {
        return this.companyUid;
    }

    /**
     * 設定身分證字號/統一編號，AES加密
     *
     * @param companyUid
     *            要設定的身分證字號/統一編號，AES加密
     */
    public void setCompanyUid(String companyUid) {
        this.companyUid = companyUid;
    }

    /**
     * 取得建立時間
     *
     * @return Date 建立時間
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 設定建立時間
     *
     * @param createTime
     *            要設定的建立時間
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 取得客服人員編號
     *
     * @return String 客服人員編號
     */
    public String getCsId() {
        return this.csId;
    }

    /**
     * 設定客服人員編號
     *
     * @param csId
     *            要設定的客服人員編號
     */
    public void setCsId(String csId) {
        this.csId = csId;
    }

    /**
     * 取得客服人員姓名
     *
     * @return String 客服人員姓名
     */
    public String getCsName() {
        return this.csName;
    }

    /**
     * 設定客服人員姓名
     *
     * @param csName
     *            要設定的客服人員姓名
     */
    public void setCsName(String csName) {
        this.csName = csName;
    }

    /**
     * 取得主檔鍵值
     *
     * @return Integer 主檔鍵值
     */
    public Integer getRecordKey() {
        return this.recordKey;
    }

    /**
     * 設定主檔鍵值
     *
     * @param recordKey
     *            要設定的主檔鍵值
     */
    public void setRecordKey(Integer recordKey) {
        this.recordKey = recordKey;
    }

    /**
     * 取得交易狀態
     *
     * @return String 交易狀態
     */
    public String getTxStatus() {
        return this.txStatus;
    }

    /**
     * 設定交易狀態
     *
     * @param txStatus
     *            要設定的交易狀態
     */
    public void setTxStatus(String txStatus) {
        this.txStatus = txStatus;
    }

    /**
     * 取得使用者代碼
     *
     * @return String 使用者代碼
     */
    public String getUserUuid() {
        return this.userUuid;
    }

    /**
     * 設定使用者代碼
     *
     * @param userUuid
     *            要設定的使用者代碼
     */
    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getStopReason() {
        return stopReason;
    }

    public void setStopReason(String stopReason) {
        this.stopReason = stopReason;
    }

    public Integer getCardKey() {
        return cardKey;
    }

    public void setCardKey(Integer cardKey) {
        this.cardKey = cardKey;
    }

    public Integer getCsGroupKey() {
        return csGroupKey;
    }

    public void setCsGroupKey(Integer csGroupKey) {
        this.csGroupKey = csGroupKey;
    }

    public String getCsGroupName() {
        return csGroupName;
    }

    public void setCsGroupName(String csGroupName) {
        this.csGroupName = csGroupName;
    }

}
