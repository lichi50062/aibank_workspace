/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.component.ipofund;

import java.util.Date;

// @formatter:off
/**
 * @(#)FundIpo.java
 * 
 * <p>Description:募集中基金資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/04/10, Rong Gang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FundIpo {

    /** 基金代號 */
    private String prdId;

    /** IPO預購起日 */
    private Date ipoStart;

    /** IPO預購迄日 */
    private Date ipoEnd;

    /** IPO募集起日(預約扣款起日) */
    private Date ipoRaiseStart;

    /** IPO募集迄日(預約扣款迄日) */
    private Date ipoRaiseEnd;

	public String getPrdId() {
		return prdId;
	}

	public void setPrdId(String prdId) {
		this.prdId = prdId;
	}

	public Date getIpoStart() {
		return ipoStart;
	}

	public void setIpoStart(Date ipoStart) {
		this.ipoStart = ipoStart;
	}

	public Date getIpoEnd() {
		return ipoEnd;
	}

	public void setIpoEnd(Date ipoEnd) {
		this.ipoEnd = ipoEnd;
	}

	public Date getIpoRaiseStart() {
		return ipoRaiseStart;
	}

	public void setIpoRaiseStart(Date ipoRaiseStart) {
		this.ipoRaiseStart = ipoRaiseStart;
	}

	public Date getIpoRaiseEnd() {
		return ipoRaiseEnd;
	}

	public void setIpoRaiseEnd(Date ipoRaiseEnd) {
		this.ipoRaiseEnd = ipoRaiseEnd;
	}
}
