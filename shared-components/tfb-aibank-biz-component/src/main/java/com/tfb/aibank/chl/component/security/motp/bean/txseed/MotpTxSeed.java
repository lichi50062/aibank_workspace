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

import java.util.HashMap;
import java.util.Map;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.component.security.motp.type.MotpTxSeedType;

//@formatter:off
/**
* @(#)MotpTxSeed.java
* 
* <p>Description:MOTP交易因子</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/29, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public abstract class MotpTxSeed {

    /** MOTP置換子 */
    protected static final String MOTP_REPLACER = "{0}";

    /** 交易代碼置換子 */
    protected static final String TX_CODE_REPLACER = "{1}";

    /** 有效時間置換子 */
    protected static final String AUTH_DEADLINE_REPLACER = "{2}";

    /**
     * 使用此次MOTP認證之交易
     */
    private String taskId;

    /**
     * 使用此次MOTP認證之交易名稱
     */
    private String taskName;

    /**
     * 提供行銀顯示客製化推播MOTP頁面使用
     * 
     * @return
     */
    public abstract MotpTxSeedType getMotpTxSeedType();

    /**
     * 供MOTP產生交易因子使用
     * 
     * @return
     */
    public abstract String getTxFactor();

    /**
     * 實作toString 供Logger使用
     */
    public abstract String toString();

    /**
     * 取得此次交易客製化推播訊息標題
     * 
     * @return
     */
    public abstract String getTitle();

    /**
     * 取得此次交易客製化推播訊息內容
     * 
     * @return
     */
    public abstract String getMessage();

    /**
     * 取得此次交易客製化參數 供行銀收到推播訊息顯示用
     * 
     * @return
     */
    public Map<String, String> getCustInfo() {
        Map<String, String> labelValue = new HashMap<>();
        // 交易名稱
        labelValue.put("taskName", getTaskName());
        // 交易代碼
        labelValue.put("txCode", TX_CODE_REPLACER);
        // 驗證碼
        labelValue.put("motp", MOTP_REPLACER);
        return labelValue;
    }

    /**
     * 供驗證交易因子使用
     * 
     * @param txFactor
     * @return
     */
    public boolean verifyTxFactor(String txFactor) {
        if (StringUtils.isBlank(txFactor)) {
            return false;
        }
        return this.getTxFactor().equals(txFactor);
    }

    /**
     * @return the taskId
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * @param taskId
     *            the taskId to set
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * @return the taskName
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * @param taskName
     *            the taskName to set
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

}
