/**
 * 
 */
package com.tfb.aibank.chl.general.ot001.task.service;

import com.ibm.tw.commons.type.IEnum;

/**
 * @author john
 *
 */

public enum LoginMailType implements IEnum {

    LOGIN_SUCC(0, 0, 11, "ngn.ot001.mail.login.succ.result", "ngn.ot001.mail.login.succ.subject", "ngn.ot001.mail.login.succ.title", "ngn.ot001.mail.login.succ.subtitle", "", "台北富邦行動銀行登入「成功」通知", ""),

    LOGIN_FAIL(0, 1, 11, "ngn.ot001.mail.login.fail.result", "ngn.ot001.mail.login.fail.subject", "ngn.ot001.mail.login.fail.title", "ngn.ot001.mail.login.fail.subtitle", "", "台北富邦行動銀行登入「失敗」通知", ""),

    CARD_LOGIN_SUCC(0, 0, 11, "ngn.ot001.mail.card.login.succ.result", "ngn.ot001.mail.card.login.succ.subject", "ngn.ot001.mail.card.login.succ.title", "ngn.ot001.mail.card.login.succ.subtitle", "", "台北富邦行動銀行信用卡登入「成功」通知", ""),

    CARD_LOGIN_FAIL(0, 1, 11, "ngn.ot001.mail.card.login.fail.result", "ngn.ot001.mail.card.login.fail.subject", "ngn.ot001.mail.card.login.fail.title", "ngn.ot001.mail.card.login.fail.subtitle", "", "台北富邦行動銀行信用卡登入「失敗」通知", ""),

    PATTERN_LOGIN_SUCC(0, 0, 11, "ngn.ot001.mail.pattern.login.succ.result", "ngn.ot001.mail.pattern.login.succ.subject", "ngn.ot001.mail.pattern.login.succ.title", "ngn.ot001.mail.pattern.login.succ.subtitle", "", "台北富邦行動銀行手勢密碼登入「成功」通知", ""),

    PATTERN_LOGIN_FAIL(0, 1, 11, "ngn.ot001.mail.pattern.login.fail.result", "ngn.ot001.mail.pattern.login.fail.subject", "ngn.ot001.mail.pattern.login.fail.title", "ngn.ot001.mail.pattern.login.fail.subtitle", "", "台北富邦行動銀行手勢密碼登入「失敗」通知", ""),

    BIO_LOGIN_SUCC(0, 0, 11, "ngn.ot001.mail.bio.login.succ.result", "ngn.ot001.mail.bio.login.succ.subject", "ngn.ot001.mail.bio.login.succ.title", "ngn.ot001.mail.bio.login.succ.subtitle", "", "台北富邦行動銀行生物辨識登入「成功」通知", ""),

    BIO_LOGIN_FAIL(0, 1, 11, "ngn.ot001.mail.bio.login.fail.result", "ngn.ot001.mail.bio.login.fail.subject", "ngn.ot001.mail.bio.login.fail.title", "ngn.ot001.mail.bio.login.fail.subtitle", "", "台北富邦行動銀行生物辨識登入「失敗」通知", ""),

    CHANGE_IDPWD_SUCC(4, 0, 0, "ngn.ot001.mail.change.idpwd.succ.result", "ngn.ot001.mail.change.idpwd.succ.subject", "ngn.ot001.mail.change.idpwd.succ.title", "ngn.ot001.mail.change.idpwd.succ.subtitle", "", "變更使用者代碼/密碼成功通知", ""),

    CHANGE_IDPWD_FAIL(4, 1, 0, "ngn.ot001.mail.change.idpwd.fail.result", "ngn.ot001.mail.change.idpwd.fail.subject", "ngn.ot001.mail.change.idpwd.fail.title", "ngn.ot001.mail.change.idpwd.fail.subtitle", "", "變更使用者代碼/密碼失敗通知", ""),

    CHANGE_PWD_SUCC(4, 0, 0, "ngn.ot001.mail.change.pwd.succ.result", "ngn.ot001.mail.change.pwd.succ.subject", "ngn.ot001.mail.change.pwd.succ.title", "ngn.ot001.mail.change.pwd.succ.subtitle", "", "變更密碼成功通知", ""),

    CHANGE_PWD_FAIL(4, 1, 0, "ngn.ot001.mail.change.pwd.fail.result", "ngn.ot001.mail.change.pwd.fail.subject", "ngn.ot001.mail.change.pwd.fail.title", "ngn.ot001.mail.change.pwd.fail.subtitle", "", "變更密碼失敗通知", ""),

    CHANGE_ID_SUCC(4, 0, 0, "ngn.ot001.mail.change.id.succ.result", "ngn.ot001.mail.change.id.succ.subject", "ngn.ot001.mail.change.id.succ.title", "ngn.ot001.mail.change.id.succ.subtitle", "", "變更使用者代碼成功通知", ""),

    CHANGE_ID_FAIL(4, 1, 0, "ngn.ot001.mail.change.id.fail.result", "ngn.ot001.mail.change.id.fail.subject", "ngn.ot001.mail.change.id.fail.title", "ngn.ot001.mail.change.id.fail.subtitle", "", "變更使用者代碼失敗通知", ""),

    WELCOME_MAIL(0, 0, 0, "ngn.ot001.mail.welcome.result", "ngn.ot001.mail.welcome.subject", "ngn.ot001.mail.welcome.title", "ngn.ot001.mail.welcome.subject", "", "歡迎信通知", ""),

    BINDING_SUCC(1, 0, 0, "ngn.ot001.mail.binding.succ.result", "ngn.ot001.mail.binding.succ.subject", "ngn.ot001.mail.binding.succ.title", "ngn.ot001.mail.binding.succ.subtitle", "ngn.ot001.mail.binding.succ.content", "裝置綁定成功時", "ngn.ot001.mail.binding.note"),

    BINDING_FAIL(1, 1, 0, "ngn.ot001.mail.binding.fail.result", "ngn.ot001.mail.binding.fail.subject", "ngn.ot001.mail.binding.fail.title", "ngn.ot001.mail.binding.fail.subtitle", "ngn.ot001.mail.binding.fail.content", "裝置綁定失敗時", "ngn.ot001.mail.binding.note"),

    PATTERN_SETTING_SUCC(2, 0, 0, "ngn.ot001.mail.pattern.setting.succ.result", "ngn.ot001.mail.pattern.setting.succ.subject", "ngn.ot001.mail.pattern.setting.succ.title", "ngn.ot001.mail.pattern.setting.succ.subtitle", "ngn.ot001.mail.pattern.setting.succ.content", "快速登入-手勢設定成功時", "ngn.ot001.mail.pattern.setting.note"),

    PATTERN_SETTING_FAIL(2, 1, 0, "ngn.ot001.mail.pattern.setting.fail.result", "ngn.ot001.mail.pattern.setting.fail.subject", "ngn.ot001.mail.pattern.setting.fail.title", "ngn.ot001.mail.pattern.setting.fail.subtitle", "ngn.ot001.mail.pattern.setting.fail.content", "快速登入-手勢設定失敗時", "ngn.ot001.mail.pattern.setting.note"),

    BIO_SETTING_SUCC(2, 0, 0, "ngn.ot001.mail.bio.setting.succ.result", "ngn.ot001.mail.bio.setting.succ.subject", "ngn.ot001.mail.bio.setting.succ.title", "ngn.ot001.mail.bio.setting.succ.subtitle", "ngn.ot001.mail.bio.setting.succ.content", "快速登入-生物辨識設定成功時", "ngn.ot001.mail.bio.setting.note"),

    BIO_SETTING_FAIL(2, 1, 0, "ngn.ot001.mail.bio.setting.fail.result", "ngn.ot001.mail.bio.setting.fail.subject", "ngn.ot001.mail.bio.setting.fail.title", "ngn.ot001.mail.bio.setting.fail.subtitle", "ngn.ot001.mail.bio.setting.fail.content", "快速登入-生物辨識設定失敗時", "ngn.ot001.mail.bio.setting.note"),

    NOTIFICATION_SETTING(3, 0, 0, "ngn.ot001.mail.notification.succ.result", "ngn.ot001.mail.notification.succ.subject", "ngn.ot001.mail.notification.succ.title", "ngn.ot001.mail.notification.succ.subtitle", "ngn.ot001.mail.notification.succ.content", "訊息通知設定結果通知", ""),

    LOGIN_SUCC_TWOFACTOR(0, 0, 11, "ngn.ot001.mail.twofactor.login.succ.result", "ngn.ot001.mail.twofactor.login.succ.subject", "ngn.ot001.mail.twofactor.login.succ.title", "ngn.ot001.mail.twofactor.login.succ.subtitle", "", "台北富邦行動銀行雙重驗證「成功」通知", ""),

    LOGIN_FAIL_TWOFACTOR(0, 1, 11, "ngn.ot001.mail.twofactor.login.fail.result", "ngn.ot001.mail.twofactor.login.fail.subject", "ngn.ot001.mail.twofactor.login.fail.title", "ngn.ot001.mail.twofactor.login.fail.subtitle", "", "台北富邦行動銀行雙重驗證「失敗」通知", "");

    int type;

    int status;

    /**
     * 1 - 說明 10 - IP / Model
     */
    int option;

    String result;

    String subject;

    String title;

    String subtitle;

    String content;

    String memo;

    String note;

    LoginMailType(int type, int status, int option, String result, String subject, String title, String subtitle, String content, String memo, String note) {
        this.type = type;
        this.status = status;
        this.result = result;
        this.option = option;
        this.title = title;
        this.subject = subject;
        this.subtitle = subtitle;
        this.content = content;
        this.memo = memo;
        this.note = note;
    }

    @Override
    public String getMemo() {
        return this.memo;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the option
     */
    public int getOption() {
        return option;
    }

    /**
     * @param option
     *            the option to set
     */
    public void setOption(int option) {
        this.option = option;
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result
     *            the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * @param memo
     *            the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject
     *            the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the subtitle
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * @param subtitle
     *            the subtitle to set
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
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
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note
     *            the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

}