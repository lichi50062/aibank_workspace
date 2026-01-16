package com.tfb.aibank.chl.creditcard.ag002.model;


import com.ibm.tw.ibmb.base.model.RsData;
import org.springframework.stereotype.Component;

// @formatter:off
/**
 *
 * <p>Description:信用卡電子帳單設定 終止電子帳單 寄送 Email</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/08/22, Aaron
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:off
@Component
public class NCCAG002012Rs implements RsData {
	
	private boolean hasDigiLifeCard;

	/**
	 * @return the hasDigiLifeCard
	 */
	public boolean isHasDigiLifeCard() {
		return hasDigiLifeCard;
	}

	/**
	 * @param hasDigiLifeCard the hasDigiLifeCard to set
	 */
	public void setHasDigiLifeCard(boolean hasDigiLifeCard) {
		this.hasDigiLifeCard = hasDigiLifeCard;
	}
	
	

}
