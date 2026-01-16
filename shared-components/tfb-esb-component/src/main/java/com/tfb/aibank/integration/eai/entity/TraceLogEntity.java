/*
 * ===========================================================================
 * IBM Confidential
 * AIS Source Materials
 * 
 * 
 * (C) Copyright IBM Corp. 2011.
 *
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.entity;

import java.util.Date;

import com.ibm.tw.ibmb.component.log.ITraceLogEntity;

//@formatter:off
/**
 * @(#)TraceLogEntity.java
 * 
 * <p>Description:電文發送記錄</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/30, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on

public class TraceLogEntity implements ITraceLogEntity<Object> {
	
	private Object traceLogKey;

	/** ACCESS LOG鍵值 */
	private String accessLogKey;

	/** 發送電文來源系統別 */
	private String fromSystem;

	/**
	 * 交易電文代號
	 */
	private String txnId;
	
	/**
	 * 電文發送序號
	 */
	private String hostSeqNo;
	
	/** 額外註記 */
	private String memo;

	/** 訊息記錄時間 */
	private Date createTime;
	
	/** 主機回應時間 */
    private Integer responseTime;
    
    /**
     * 錯誤代碼
     */
    private String errorCode;

    /**
     * 錯誤訊息描述
     */
    private String errorDesc;

    /**
     * 錯誤系統代碼
     */
    private String errorSystemId;

	public Object getTraceLogKey() {
		return traceLogKey;
	}

	public void setTraceLogKey(Object traceLogKey) {
		this.traceLogKey = traceLogKey;
	}

	public String getAccessLogKey() {
		return accessLogKey;
	}

	public void setAccessLogKey(String accessLogKey) {
		this.accessLogKey = accessLogKey;
	}

	public String getFromSystem() {
		return fromSystem;
	}

	public void setFromSystem(String fromSystem) {
		this.fromSystem = fromSystem;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

    /**
     * 取得 txnId
     * 
     * @return 傳回 txnId。
     */
    public String getTxnId() {
        return txnId;
    }

    /**
     * 設定 txnId
     * 
     * @param txnId 要設定的 txnId。
     */
    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    /**
     * 取得 hostSeqNo
     * 
     * @return 傳回 hostSeqNo。
     */
    public String getHostSeqNo() {
        return hostSeqNo;
    }

    /**
     * 設定 hostSeqNo
     * 
     * @param hostSeqNo 要設定的 hostSeqNo。
     */
    public void setHostSeqNo(String hostSeqNo) {
        this.hostSeqNo = hostSeqNo;
    }

    /**
     * 取得 responseTime
     * 
     * @return 傳回 responseTime。
     */
    public Integer getResponseTime() {
        return responseTime;
    }

    /**
     * 設定 responseTime
     * 
     * @param responseTime 要設定的 responseTime。
     */
    public void setResponseTime(Integer responseTime) {
        this.responseTime = responseTime;
    }

    /**
     * 取得 errorCode
     * 
     * @return 傳回 errorCode。
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * 設定 errorCode
     * 
     * @param errorCode 要設定的 errorCode。
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 取得 errorDesc
     * 
     * @return 傳回 errorDesc。
     */
    public String getErrorDesc() {
        return errorDesc;
    }

    /**
     * 設定 errorDesc
     * 
     * @param errorDesc 要設定的 errorDesc。
     */
    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    /**
     * 取得 errorSystemId
     * 
     * @return 傳回 errorSystemId。
     */
    public String getErrorSystemId() {
        return errorSystemId;
    }

    /**
     * 設定 errorSystemId
     * 
     * @param errorSystemId 要設定的 errorSystemId。
     */
    public void setErrorSystemId(String errorSystemId) {
        this.errorSystemId = errorSystemId;
    }

}
