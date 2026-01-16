package com.tfb.aibank.chl.creditcard.ag002.model;


import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 *
 * <p>Description:信用卡電子帳單設定 條款頁</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/08/22, Aaron
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:off
@Component
public class NCCAG002020Rs implements RsData {
	// 條款標題
	private String termsTitle;

	// 條款內容
	private String termsContent;

	/**
	 * @return the termsTitle
	 */
	public String getTermsTitle() {
		return termsTitle;
	}

	/**
	 * @param termsTitle the termsTitle to set
	 */
	public void setTermsTitle(String termsTitle) {
		this.termsTitle = termsTitle;
	}

	/**
	 * @return the termsContent
	 */
	public String getTermsContent() {
		return termsContent;
	}

	/**
	 * @param termsContent the termsContent to set
	 */
	public void setTermsContent(String termsContent) {
		this.termsContent = termsContent;
	}
	
	
	

}
