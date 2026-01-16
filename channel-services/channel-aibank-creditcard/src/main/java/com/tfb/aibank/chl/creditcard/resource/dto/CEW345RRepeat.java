package com.tfb.aibank.chl.creditcard.resource.dto;

import java.util.Date;

//@formatter:off
/**
* @(#)CEW345RRepeat.java
* 
* <p>Description:CEW345R 保費權益設定查詢 API下行txRepeat.</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/02/10, leiping	
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW345RRepeat {
    /** 卡別 X(4) */
    private String crdTyp;
    /** 卡號 X(16) */
    private String crdNo;
    /** 五萬元以上保費權益最近一次變更日期 X(8) */
    private Date changeDateA;
    /** 五萬元以上保費權益 X(1) */
    private String insuTypeA;
    /** 未達五萬元保費權益最近一次變更日期 X(8) */
    private Date changeDateB;
    /** 未達五萬元保費權益 X(1) */
    private String insuTypeB;
	/**
	 * @return the crdTyp
	 */
	public String getCrdTyp() {
		return crdTyp;
	}
	/**
	 * @param crdTyp the crdTyp to set
	 */
	public void setCrdTyp(String crdTyp) {
		this.crdTyp = crdTyp;
	}
	/**
	 * @return the crdNo
	 */
	public String getCrdNo() {
		return crdNo;
	}
	/**
	 * @param crdNo the crdNo to set
	 */
	public void setCrdNo(String crdNo) {
		this.crdNo = crdNo;
	}
	/**
	 * @return the changeDateA
	 */
	public Date getChangeDateA() {
		return changeDateA;
	}
	/**
	 * @param changeDateA the changeDateA to set
	 */
	public void setChangeDateA(Date changeDateA) {
		this.changeDateA = changeDateA;
	}
	/**
	 * @return the insuTypeA
	 */
	public String getInsuTypeA() {
		return insuTypeA;
	}
	/**
	 * @param insuTypeA the insuTypeA to set
	 */
	public void setInsuTypeA(String insuTypeA) {
		this.insuTypeA = insuTypeA;
	}
	/**
	 * @return the changeDateB
	 */
	public Date getChangeDateB() {
		return changeDateB;
	}
	/**
	 * @param changeDateB the changeDateB to set
	 */
	public void setChangeDateB(Date changeDateB) {
		this.changeDateB = changeDateB;
	}
	/**
	 * @return the insuTypeB
	 */
	public String getInsuTypeB() {
		return insuTypeB;
	}
	/**
	 * @param insuTypeB the insuTypeB to set
	 */
	public void setInsuTypeB(String insuTypeB) {
		this.insuTypeB = insuTypeB;
	}

   

}
