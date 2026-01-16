package com.tfb.aibank.chl.creditcard.ag005.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.tfb.aibank.chl.creditcard.ag005.model.NCCAG005Output;
import com.tfb.aibank.chl.creditcard.service.NCCService;
import com.tfb.aibank.common.code.AIBankConstants;

// @formatter:off
/**
 * @(#)NCCAG005Service.java
 * 
 * <p>Description:CHL NCCAG005 服務類別，撰寫此交易獨有的「邏輯」程式，所有method必須宣告 void</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/28, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class NCCAG005Service extends NCCService {

    public static final String CACHE_KEY = "NCCAG005_CACHE_KEY";
    public static final String CEW345R_VALID_TIME = "2025/04/01 09:00:00";//保費權益設定新增單筆金額五萬以下設定生效時間

    /**
     * 發查電文 CEW327R
     * 
     * @param type
     * @param input
     * @param dataOutput
     */
    public void sendCEW327R(String type, String input, NCCAG005Output dataOutput) {
        if (StringUtils.equals(type, "1")) {
            dataOutput.setCew327RResponse(creditCardResource.sendCEW327RByCustId(input));
        }
        else if (StringUtils.equals(type, "2")) {
            dataOutput.setCew327RResponse(creditCardResource.sendCEW327RByCardNo(input));
        }
        else {
            throw new RuntimeException("查詢方式不合理。");
        }
    }

    /**
     * 發送電文 CEW328R
     * 
     * @param cardNo
     * @param insurFeeBenefits
     */
    public void sendCEW328R(String cardNo, String insurFeeBenefits) {
        creditCardResource.sendCEW328R(cardNo, insurFeeBenefits, "K");
    }
    
    /**
     * 發查電文 CEW345R
     * 
     * @param type
     * @param input
     * @param dataOutput
     */
    public void sendCEW345R(String type, String input, NCCAG005Output dataOutput) {
        if (StringUtils.equals(type, "1")) {
        	dataOutput.setCew345RResponse(creditCardResource.sendCEW345RByCustId(input));
        }
        else if (StringUtils.equals(type, "2")) {
            dataOutput.setCew345RResponse(creditCardResource.sendCEW345RByCardNo(input));
        }
        else {
            throw new RuntimeException("查詢方式不合理。");
        }
    }
    /**
     * 發送電文 CEW346R
     * 
     * @param cardNo
     * @param insurTypeA
     * @param insurTypeB
     */
    public void sendCEW346R(String cardNo, String insurTypeA, String insurTypeB) {
        creditCardResource.sendCEW346R(cardNo, insurTypeA, insurTypeB, "K");
    }
    
	/**
	 * 判斷指定卡保費權益設定，新增單筆分期五萬以下是否生效
	 * 
	 */
	public boolean getCEW345Valid() {
		try {
			String cew345rValidTime = getCEW345ValidTime();
			Date startTime = DateUtils.getDateByDateFormat(cew345rValidTime, "yyyy/MM/dd HH:mm:ss");
			return startTime.compareTo(new Date()) <= 0;
		} catch (Exception e) {
			logger.error("取指定卡保費權益設定，新增單筆分期五萬以下是否生效錯誤" + e.getMessage());
		}
		return false;
	}

	/**
	 * 取得新增單筆分期五萬以下生效時間
	 * 
	 */
	public String getCEW345ValidTime() {
		try {
			return systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "CEW345R_VALID_TIME",
					CEW345R_VALID_TIME);
		} catch (Exception e) {
			return StringUtils.EMPTY;
		}
	}
    
    /**
     * 發送電文 CEW013R
     *
     * @param custId
     * @param output
     */
    public void sendCEW013R(String custId, NCCAG005Output output) {
        output.setCew013rRes(creditCardResource.sendCEW013R(custId));
    }
}
