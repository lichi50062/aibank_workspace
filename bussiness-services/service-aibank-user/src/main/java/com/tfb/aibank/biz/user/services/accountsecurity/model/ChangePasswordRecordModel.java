package com.tfb.aibank.biz.user.services.accountsecurity.model;

import java.util.Date;

//@formatter:off
/**
* @(#)ChangePasswordRecordModel.java
* 
* <p>Description:變更密碼紀錄</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/04, Edward Tien
* <ol>
*  <li>初版</li>
* </ol>
*/
public class ChangePasswordRecordModel {

    /** 交易存取記錄鍵值 */
    private Integer accessLogKey;

    /** 變更項目 0：網銀密碼 1：網銀使用者代碼 2：網銀使用者代碼與網銀密碼 3：重設語音密碼 4：臨櫃提款密碼 */
    private String changeItem;

    /** 公司鍵值 */
    private Integer companyKey;

    /** 建立時間 */
    private Date createTime;

    /** 上送主機交易時間 */
    private Date hostTxTime;

    /** 用戶代碼 */
    private String nameCode;

    /** 晶片金融卡密碼變更使用 */
    private String outAccount;

    /** 晶片金融卡密碼變更使用 */
    private String outBank;

    /** 資料鍵值 (CHANGE_PASS-WORD_RECORD_SEQ) */
    private Integer recordKey;

    /** 交易日期 */
    private Date txDate;

    /** 交易錯誤代碼 */
    private String txErrorCode;

    /** 交易錯誤訊息 */
    private String txErrorDesc;

    /** 錯誤系統代碼 */
    private String txErrorSystemId;

    /** 交易狀態 0：交易成功 1：交易失敗 4：交易未明 */
    private String txStatus;

    /** 更新時間 */
    private Date updateTime;

    /** 使用者代碼 */
    private String userId;

    /** 使用者鍵值 */
    private Integer userKey;

    /** 客戶IP */
    private String clientIp;

    /** 交易來源 */
    private String txSource;

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
     * 取得變更項目 0：網銀密碼 1：網銀使用者代碼 2：網銀使用者代碼與網銀密碼 3：重設語音密碼 4：臨櫃提款密碼
     * 
     * @return String 變更項目 0：網銀密碼 1：網銀使用者代碼 2：網銀使用者代碼與網銀密碼 3：重設語音密碼 4：臨櫃提款密碼
     */
    public String getChangeItem() {
        return this.changeItem;
    }

    /**
     * 設定變更項目 0：網銀密碼 1：網銀使用者代碼 2：網銀使用者代碼與網銀密碼 3：重設語音密碼 4：臨櫃提款密碼
     * 
     * @param changeItem
     *            要設定的變更項目 0：網銀密碼 1：網銀使用者代碼 2：網銀使用者代碼與網銀密碼 3：重設語音密碼 4：臨櫃提款密碼
     */
    public void setChangeItem(String changeItem) {
        this.changeItem = changeItem;
    }

    /**
     * 取得公司鍵值
     * 
     * @return Integer 公司鍵值
     */
    public Integer getCompanyKey() {
        return this.companyKey;
    }

    /**
     * 設定公司鍵值
     * 
     * @param companyKey
     *            要設定的公司鍵值
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
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
     * 取得上送主機交易時間
     * 
     * @return Date 上送主機交易時間
     */
    public Date getHostTxTime() {
        return this.hostTxTime;
    }

    /**
     * 設定上送主機交易時間
     * 
     * @param hostTxTime
     *            要設定的上送主機交易時間
     */
    public void setHostTxTime(Date hostTxTime) {
        this.hostTxTime = hostTxTime;
    }

    /**
     * 取得用戶代碼
     * 
     * @return String 用戶代碼
     */
    public String getNameCode() {
        return this.nameCode;
    }

    /**
     * 設定用戶代碼
     * 
     * @param nameCode
     *            要設定的用戶代碼
     */
    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    /**
     * 取得晶片金融卡密碼變更使用
     * 
     * @return String 晶片金融卡密碼變更使用
     */
    public String getOutAccount() {
        return this.outAccount;
    }

    /**
     * 設定晶片金融卡密碼變更使用
     * 
     * @param outAccount
     *            要設定的晶片金融卡密碼變更使用
     */
    public void setOutAccount(String outAccount) {
        this.outAccount = outAccount;
    }

    /**
     * 取得晶片金融卡密碼變更使用
     * 
     * @return String 晶片金融卡密碼變更使用
     */
    public String getOutBank() {
        return this.outBank;
    }

    /**
     * 設定晶片金融卡密碼變更使用
     * 
     * @param outBank
     *            要設定的晶片金融卡密碼變更使用
     */
    public void setOutBank(String outBank) {
        this.outBank = outBank;
    }

    /**
     * 取得資料鍵值 (CHANGE_PASS-WORD_RECORD_SEQ)
     * 
     * @return Integer 資料鍵值 (CHANGE_PASS-WORD_RECORD_SEQ)
     */
    public Integer getRecordKey() {
        return this.recordKey;
    }

    /**
     * 設定資料鍵值 (CHANGE_PASS-WORD_RECORD_SEQ)
     * 
     * @param recordKey
     *            要設定的資料鍵值 (CHANGE_PASS-WORD_RECORD_SEQ)
     */
    public void setRecordKey(Integer recordKey) {
        this.recordKey = recordKey;
    }

    /**
     * 取得交易日期
     * 
     * @return Date 交易日期
     */
    public Date getTxDate() {
        return this.txDate;
    }

    /**
     * 設定交易日期
     * 
     * @param txDate
     *            要設定的交易日期
     */
    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    /**
     * 取得交易錯誤代碼
     * 
     * @return String 交易錯誤代碼
     */
    public String getTxErrorCode() {
        return this.txErrorCode;
    }

    /**
     * 設定交易錯誤代碼
     * 
     * @param txErrorCode
     *            要設定的交易錯誤代碼
     */
    public void setTxErrorCode(String txErrorCode) {
        this.txErrorCode = txErrorCode;
    }

    /**
     * 取得交易錯誤訊息
     * 
     * @return String 交易錯誤訊息
     */
    public String getTxErrorDesc() {
        return this.txErrorDesc;
    }

    /**
     * 設定交易錯誤訊息
     * 
     * @param txErrorDesc
     *            要設定的交易錯誤訊息
     */
    public void setTxErrorDesc(String txErrorDesc) {
        this.txErrorDesc = txErrorDesc;
    }

    /**
     * 取得錯誤系統代碼
     * 
     * @return String 錯誤系統代碼
     */
    public String getTxErrorSystemId() {
        return this.txErrorSystemId;
    }

    /**
     * 設定錯誤系統代碼
     * 
     * @param txErrorSystemId
     *            要設定的錯誤系統代碼
     */
    public void setTxErrorSystemId(String txErrorSystemId) {
        this.txErrorSystemId = txErrorSystemId;
    }

    /**
     * 取得交易狀態 0：交易成功 1：交易失敗 4：交易未明
     * 
     * @return String 交易狀態 0：交易成功 1：交易失敗 4：交易未明
     */
    public String getTxStatus() {
        return this.txStatus;
    }

    /**
     * 設定交易狀態 0：交易成功 1：交易失敗 4：交易未明
     * 
     * @param txStatus
     *            要設定的交易狀態 0：交易成功 1：交易失敗 4：交易未明
     */
    public void setTxStatus(String txStatus) {
        this.txStatus = txStatus;
    }

    /**
     * 取得更新時間
     * 
     * @return Date 更新時間
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 設定更新時間
     * 
     * @param updateTime
     *            要設定的更新時間
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 取得使用者代碼
     * 
     * @return String 使用者代碼
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * 設定使用者代碼
     * 
     * @param userId
     *            要設定的使用者代碼
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 取得使用者鍵值
     * 
     * @return Integer 使用者鍵值
     */
    public Integer getUserKey() {
        return this.userKey;
    }

    /**
     * 設定使用者鍵值
     * 
     * @param userKey
     *            要設定的使用者鍵值
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    /**
     * 取得 clientIp
     * 
     * @return 傳回 clientIp。
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * 設定 clientIp
     * 
     * @param clientIp
     *            要設定的 clientIp。
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * 取得 交易來源
     * 
     * @return 傳回 交易來源。
     */
    public String getTxSource() {
        return txSource;
    }

    /**
     * 設定 交易來源
     * 
     * @param txSource
     *            要設定的 交易來源。
     */
    public void setTxSource(String txSource) {
        this.txSource = txSource;
    }

}
