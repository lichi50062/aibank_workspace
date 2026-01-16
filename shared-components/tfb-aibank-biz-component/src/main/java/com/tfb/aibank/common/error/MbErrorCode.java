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
package com.tfb.aibank.common.error;

import com.ibm.tw.commons.error.ErrorStatus;
import com.ibm.tw.commons.error.IErrorCode;
import com.ibm.tw.commons.error.SeverityType;

// @formatter:off
/**
 * @(#)MbErrorCode.java
 * 
 * <p>Description:舊系統錯誤代碼</p>
 * <p>MBXXXX系列</p>
 * <p>錯誤系統代碼待確認</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/18, HankChan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum MbErrorCode implements IErrorCode {

    MB3500("3500", "很抱歉，您無法線上申請(訊息代碼：MB3500)，如有疑問，請洽本行客服<a class='link1' href='tel:+886-2-8751-6665'>02-87516665</a>，將由專人為您服務。"),

    MB3501("3501", "很抱歉，您無法線上申請(訊息代碼：MB3501-僅開放個人戶申請。若您欲開啟轉帳服務，請負責人攜帶身分證、公司登記證明文件至任一分行辦理；如欲提高轉帳額度，請負責人攜帶身分證、公司登記證明文件至任一分行，將帳號設為約定帳戶)，如有疑問，請洽<a href='#' class='link1' id='linkToApplyChat'>數位客服</a>。"),

    MB3502("3502", "很抱歉，您無法線上申請(訊息代碼：MB3502-僅開放單一使用者代碼客戶申請，請攜帶身分證至任一分行刪除已不使用之網銀帳號)，如有疑問，請洽<a href='#' class='link1' id='linkToApplyChat'>數位客服</a>。"),

    MB3503("3503", "很抱歉，您無法線上申請(訊息代碼：MB3503-僅開放單一使用者代碼客戶申請，請攜帶身分證至任一分行刪除已不使用之網銀帳號)，如有疑問，請洽<a href='#' class='link1' id='linkToApplyChat'>數位客服</a>。"),

    MB3504("3504", "很抱歉，您無法線上申請(訊息代碼：MB3504-您留存於本行的手機 號碼並非本國行動電話，如有疑問，請洽<a href='#' class='link1' id='linkToApplyChat'>數位客服</a>。"),

    MB3505("3505", "很抱歉，您無法線上申請(訊息代碼：MB3505-此帳戶無法申請此功能)，如有疑問，請洽本行客服專線<a class='link1' href='tel:+886-2-8751-6665'>02-87516665</a>並提供此訊息代碼，由專員為您服務。"),

    MB3506("3506", "很抱歉，您無法線上申請(訊息代碼：MB3506-簡訊驗證碼僅限發送臨櫃留存之手機號碼，請攜帶身分證至任一分行辦理)，如有疑問，請洽<a href='#' class='link1' id='linkToApplyChat'>數位客服</a>。"),

    MB3511("3511", "很抱歉，您無法線上申請(訊息代碼：MB3511-此帳戶登錄為死亡戶無法申請此功能，請繼承人攜帶相關文件至任一分行辦理帳戶繼承事宜)，如有疑問，請洽<a href='#' class='link1' id='linkToApplyChat'>數位客服</a>。"),

    MB3512("3512", "很抱歉，MB3512-很抱歉，您無法線上申請"),

    MB3513("3513", "很抱歉，MB3513-此帳戶無法申請服務"),

    MB3514("3514", "很抱歉，您無法線上申請，如有疑問，請洽本行客服 <a class='link1' href='tel:+886-2-8751-6665'>02-87516665</a>，將由專人為您服務。"),

    MB3515("3515", "很抱歉，您無法線上申請，請洽數位客服。"),

    MB2200("2200", "親愛的客戶您好，為保障用戶資料安全，若您需要查詢或使用信用卡相關服務，請攜帶雙證件至各分行確認個人基本資料。"),

    MB2201("2201", "若您需查詢相關服務，<br>請攜帶雙證件至各分行辦理。");

    /** 異常資料 */
    private ErrorStatus error = null;

    MbErrorCode(String errorCode, String memo) {
        this(errorCode, memo, SeverityType.ERROR);
    }

    /**
     * 使用於Timeout錯誤
     *
     * Constructor
     *
     * @param errorCode
     * @param memo
     * @param severity
     */
    MbErrorCode(String errorCode, String memo, SeverityType severity) {
        error = new ErrorStatus("MB", errorCode, severity, memo);
    }

    /**
     * 取得系統代碼
     *
     * @return
     */
    public String getSystemId() {
        return error.getSystemId();
    }

    /**
     * 取得 錯誤代碼
     *
     * @return
     */
    public String getErrorCode() {
        return error.getErrorCode();
    }

    /**
     * 取得備註說明
     *
     * @return
     */
    public String getMemo() {
        return error.getErrorDesc();
    }

    @Override
    public ErrorStatus getError() {
        return error;
    }
}
