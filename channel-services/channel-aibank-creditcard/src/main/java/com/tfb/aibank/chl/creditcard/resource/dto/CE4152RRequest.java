package com.tfb.aibank.chl.creditcard.resource.dto;

// @formatter:off
/**
 * @(#)CE4153RRequest.java
 * 
 * <p>Description:查核定稅分期設定交易(CE4152R) 上行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/03/14, Gang Rong
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class CE4152RRequest {

    /** 交易類別 */
    private String txtype;

    /** 身份證字號 */
    private String pid;
    
    /** 卡號 */
    private String pin;

    /** 消費日期 */
    private String pchday;

    /** 授權碼 */
    private String authCode;

    /** 消費金額 */
    private String amt;

    /** 分期期數 */
    private String period;

    /** 稅款類別 */
    private String type;
    
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

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChel() {
		return chel;
	}

	public void setChel(String chel) {
		this.chel = chel;
	}
}
