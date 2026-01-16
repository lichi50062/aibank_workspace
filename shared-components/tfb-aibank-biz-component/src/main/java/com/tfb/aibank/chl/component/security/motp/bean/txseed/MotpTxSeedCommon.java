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
package com.tfb.aibank.chl.component.security.motp.bean.txseed;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Date;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.component.security.motp.type.MotpTxSeedType;

//@formatter:off
/**
* @(#)MotpTxSeed.java
* 
* <p>Description:MOTP共用交易因子</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/29, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class MotpTxSeedCommon extends MotpTxSeed {

    private static MotpTxSeedType motpTxSeedType = MotpTxSeedType.COMMON;

    /** 建立日期 */
    private Date createDate;

    /** 驗證授權說明 */
    private String authDesc;

    public MotpTxSeedCommon(String taskId, String taskName) {
        this(taskId, taskName, null);
    }

    public MotpTxSeedCommon(String taskId, String taskName, String authDesc) {
        super();
        this.createDate = new Date();
        this.authDesc = authDesc;
        if (StringUtils.isBlank(authDesc)) {
            // 推播交易驗證
            this.authDesc = I18NUtils.getMessage("motp.auth.message.common.default-desc");
        }
        super.setTaskId(taskId);
        super.setTaskName(taskName);
    }

    @Override
    public MotpTxSeedType getMotpTxSeedType() {
        return motpTxSeedType;
    }

    @Override
    public String getTxFactor() {
        StringBuilder sb = new StringBuilder();
        sb.append(getTaskId()).append("&");
        sb.append(System.currentTimeMillis()).append("&");
        sb.append(new SecureRandom().nextDouble());
        return sb.toString();
    }

    @Override
    public String toString() {
        return "MotpTxSeedCommon [taskId=" + getTaskId() + ", createDate=" + createDate + ", authDesc=" + authDesc + "]";
    }

    /**
     * 取得此次交易客製化推播訊息
     * <p>
     * 富邦行動銀行「{0}」交易確認通知，交易代號{1}，請點選查看
     * </p>
     */
    @Override
    public String getTitle() {
        return I18NUtils.getMessage("motp.auth.message.title") + "@" + I18NUtils.getMessage("motp.auth.message.common.title", Arrays.asList(getTaskName(), TX_CODE_REPLACER).toArray());
    }

    /**
     * 取得此次交易客製化推播訊息
     * <p>
     * 【交易驗證碼】你於富邦AIBank的「{authDesc}」交易驗證碼 {1}，交易代碼 {2}。(有效時間 {3}，依本行系統時間為主)
     * </p>
     */
    @Override
    public String getMessage() {
        return I18NUtils.getMessage("motp.auth.message.common.content", Arrays.asList(getTaskName(), MOTP_REPLACER, TX_CODE_REPLACER, AUTH_DEADLINE_REPLACER).toArray());
    }

}
