package com.tfb.aibank.chl.general.ot001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NGNOT001073Rs.java 
* 
* <p>Description:記錄移轉項目</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20230605, JohnChang
*  <li>初版</li6
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on
@Component
public class NGNOT001073Rs implements RsData {

    private boolean motpInitFail;

    /** 使用者已綁定 */
    private boolean userBind;

    /** 查詢使用者綁定狀態失敗 */
    private boolean getUserVaildTokenFail;

    /** 建立MOTP綁定帳戶失敗 */
    private boolean createOtpUserFail;

    /** 取得帳戶綁定資訊失敗 */
    private boolean initPushFail;

    /** 錯誤代碼 */
    private String errorCode;

    /** 錯誤訊息 */
    private String errorMsg;

    /** 初始金鑰 */
    private String ik;

    /** 加密資料之金鑰 */
    private String pushKey;

    /** 保留欄位 */
    private String flag;

    /** App profile類型 */
    private String type;

    /** 使用者名稱 */
    private String account;

    /**
     * @return the motpInitFail
     */
    public boolean isMotpInitFail() {
        return motpInitFail;
    }

    /**
     * @param motpInitFail
     *            the motpInitFail to set
     */
    public void setMotpInitFail(boolean motpInitFail) {
        this.motpInitFail = motpInitFail;
    }

    /**
     * @return the userBind
     */
    public boolean isUserBind() {
        return userBind;
    }

    /**
     * @param userBind
     *            the userBind to set
     */
    public void setUserBind(boolean userBind) {
        this.userBind = userBind;
    }

    /**
     * @return the getUserVaildTokenFail
     */
    public boolean isGetUserVaildTokenFail() {
        return getUserVaildTokenFail;
    }

    /**
     * @param getUserVaildTokenFail
     *            the getUserVaildTokenFail to set
     */
    public void setGetUserVaildTokenFail(boolean getUserVaildTokenFail) {
        this.getUserVaildTokenFail = getUserVaildTokenFail;
    }

    /**
     * @return the createOtpUserFail
     */
    public boolean isCreateOtpUserFail() {
        return createOtpUserFail;
    }

    /**
     * @param createOtpUserFail
     *            the createOtpUserFail to set
     */
    public void setCreateOtpUserFail(boolean createOtpUserFail) {
        this.createOtpUserFail = createOtpUserFail;
    }

    /**
     * @return the initPushFail
     */
    public boolean isInitPushFail() {
        return initPushFail;
    }

    /**
     * @param initPushFail
     *            the initPushFail to set
     */
    public void setInitPushFail(boolean initPushFail) {
        this.initPushFail = initPushFail;
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode
     *            the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * @param errorMsg
     *            the errorMsg to set
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * @return the ik
     */
    public String getIk() {
        return ik;
    }

    /**
     * @param ik
     *            the ik to set
     */
    public void setIk(String ik) {
        this.ik = ik;
    }

    /**
     * @return the pushKey
     */
    public String getPushKey() {
        return pushKey;
    }

    /**
     * @param pushKey
     *            the pushKey to set
     */
    public void setPushKey(String pushKey) {
        this.pushKey = pushKey;
    }

    /**
     * @return the flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * @param flag
     *            the flag to set
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account
     *            the account to set
     */
    public void setAccount(String account) {
        this.account = account;
    }

}
