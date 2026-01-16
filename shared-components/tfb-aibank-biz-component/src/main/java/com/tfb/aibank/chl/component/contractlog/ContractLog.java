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
package com.tfb.aibank.chl.component.contractlog;

import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)ContractLog.java
 * 
 * <p>Description:審閱條款紀錄</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "審閱條款紀錄")
public class ContractLog implements Serializable {

    private static final long serialVersionUID = 2802008335190384629L;

    /**
     * 資料鍵值
     */
    @Schema(description = "資料鍵值")
    private Long recordKey;

    /**
     * 操作類別，1:同意；0:不同意
     */
    @Schema(description = "操作類別，1:同意；0:不同意")
    private int actionType;

    /**
     * 客戶IP
     */
    @Schema(description = "客戶IP")
    private String clientIp;

    /**
     * 新增時間
     */
    @Schema(description = "新增時間")
    private Date createTime;

    /**
     * 條款頁面代碼，格式：交易代碼_頁面代碼
     */
    @Schema(description = "條款頁面代碼，格式：交易代碼_頁面代碼")
    private String pageId;

    /**
     * 文案鍵值
     */
    @Schema(description = "文案鍵值")
    private String remarkKey;

    /**
     * 交談識別碼
     */
    @Schema(description = "交談識別碼")
    private String sessionId;

    /**
     * 額外資訊
     */
    @Schema(description = "額外資訊")
    private String memo;

    public Long getRecordKey() {
        return recordKey;
    }

    public void setRecordKey(Long recordKey) {
        this.recordKey = recordKey;
    }

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getRemarkKey() {
        return remarkKey;
    }

    public void setRemarkKey(String remarkKey) {
        this.remarkKey = remarkKey;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}
