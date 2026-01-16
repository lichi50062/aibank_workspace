package com.tfb.aibank.chl.creditcard.resource.dto;

// @formatter:off
/**
 * @(#)CE4150RRequest.java
 * 
 * <p>Description:綜所稅分期設定交易(CE4150R) 上行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/03/14, Gang Rong
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class CE4150RRequest {

    /** 交易類別 */
    private String txtype;

    /** 身份證字號 */
    private String pid;

    /** 繳稅金額 */
    private String taxAmt;
    
    /** 卡號 */
    private String pin;

    /** 消費日期 */
    private String pchday;

    /** 授權碼 */
    private String authCode;

    /** 分期期數 */
    private String period;
    
    /** 通路類別 */
    private String chel;

	public String getTxtype() {
		return txtype;
	}

	public void setTxtype(String txtype) {
		this.txtype = txtype;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(String taxAmt) {
		this.taxAmt = taxAmt;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getPchday() {
		return pchday;
	}

	public void setPchday(String pchday) {
		this.pchday = pchday;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getChel() {
		return chel;
	}

	public void setChel(String chel) {
		this.chel = chel;
	}
}
