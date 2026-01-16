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
package com.tfb.aibank.chl.creditcard.resource.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 查核定稅分期設定交易 下行電文 repeat
 * 
 * @author Gang Rong
 */
public class CE4152RRepeat {

    /** 卡號 */
    private String pin;

    /** 消費日期 */
    private Date pchday;

    /** 授權碼 */
    private String authCode;

    /** 消費金額 */
    private BigDecimal amt;

    /** 稅款類別 */
    private String type;
    
    /** 卡別 */
    private String ctype;
    
    /** 產品利率 */
    private BigDecimal rate;

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public Date getPchday() {
		return pchday;
	}

	public void setPchday(Date pchday) {
		this.pchday = pchday;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
}
