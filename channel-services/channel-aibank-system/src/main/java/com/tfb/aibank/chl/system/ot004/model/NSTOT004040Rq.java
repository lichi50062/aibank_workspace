package com.tfb.aibank.chl.system.ot004.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NSTOT004040Rq.java
 * 
 * <p>Description: 040 Access Log 紀錄截圖事件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/25, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NSTOT004040Rq implements RqData {

    /** 記錄當前動作的交易代碼 */
    private String execTaskId;

    /** 備忘錄 */
    private String memo;

    public String getExecTaskId() {
        return execTaskId;
    }

    public void setExecTaskId(String execTaskId) {
        this.execTaskId = execTaskId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}
