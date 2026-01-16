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
 * 綜所稅分期設定交易 下行電文 repeat
 * 
 * @author Gang Rong
 */
public class CE4150RRepeat {

    /** 卡號 */
    private String pin;

    /** 消費日期 */
    private Date pchday;

    /** 授權碼 */
    private String authCode;

    /** 繳稅金額 */
    private BigDecimal taxAmt;

    /** 分期利率 */
    private BigDecimal rate;

    /** 卡別 */
    private String ctype;

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

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
}
