package com.tfb.aibank.chl.creditcard.ag007.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.creditcard.ag007.model.NCCAG007Output;
import com.tfb.aibank.chl.creditcard.resource.CreditCardResource;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW013RRes;
import com.tfb.aibank.chl.creditcard.service.NCCService;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.code.AIBankConstants;

// @formatter:off
/**
 * @(#)NCCAG007Service.java
 * 
 * <p>Description:CHL NCCAG007 服務類別，撰寫此交易獨有的「邏輯」程式，所有method必須宣告 void</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/08, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class NCCAG007Service extends NCCService {

    @Autowired
    private CreditCardResource creditCardResource;

    /**
     * 檢查信用卡狀態，判斷「是否為特殊戶」或「未持有信用卡」
     * 
     * @param aiBankUser
     * @param dataOutput
     */
    public void checkCreditCardStatus(AIBankUser aiBankUser, NCCAG007Output dataOutput) {
        dataOutput.setCheckCreditCardStatus(userDataCacheService.checkCreditCardStatus(aiBankUser));
    }

    /**
     * 依 CATEGORY、PARAM_KEY 從 SYSTEM_PARAM 取值
     * 
     * @param category
     * @param key
     * @param dataOutput
     */
    public void getSystemParamValue(String category, String key, NCCAG007Output dataOutput) {
        dataOutput.setParamValue(systemParamCacheManager.getValue(category, key));
    }

    /**
     * 判斷信用卡是否可綁定
     * 
     * @param aiBankUser
     * @param userLocale
     * @param cardNo
     * @param dataOutput
     * @throws ActionException
     */
    public void checkBindingStatus(AIBankUser aiBankUser, Locale userLocale, String cardNo, String currentAppVer, NCCAG007Output dataOutput) throws ActionException {

        CreditCard creditCard = userDataCacheService.getCreditCard(aiBankUser, cardNo, userLocale);

        boolean checkBindingStatus = StringUtils.equals(creditCard.getCardStatus(), "0") && creditCard.isCardActive();
        // #8090 信用卡總覽apple pay判定特定版次測試
        String dbPayDbVer = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "APPLEPAY_BACKEND_PILOT");
        if (IBUtils.compareAppVersion(currentAppVer, dbPayDbVer) < 0) {
            checkBindingStatus = checkBindingStatus && creditCard.isTknFlag();
        }
        dataOutput.setCheckBindingStatus(checkBindingStatus);
    }

    /**
     * 持卡人英文姓名
     * 
     * @param custId
     * @param dataOutput
     */
    public void getEngName(String custId, NCCAG007Output dataOutput) {
        CEW013RRes cew013RRes = creditCardResource.sendCEW013R(custId);
        dataOutput.setEngName(cew013RRes.getEngName());
    }

    /**
     * 信用卡名稱
     * 
     * @param aiBankUser
     * @param userLocale
     * @param cardNo
     * @param dataOutput
     * @throws ActionException
     */
    public void getCardName(AIBankUser aiBankUser, Locale userLocale, String cardNo, NCCAG007Output dataOutput) throws ActionException {
        CreditCard creditCard = userDataCacheService.getCreditCard(aiBankUser, cardNo, userLocale);
        dataOutput.setCardName(creditCard.getCardName());
    }

    /**
     * 卡片效期
     * 
     * @param aiBankUser
     * @param userLocale
     * @param cardNo
     * @param dataOutput
     * @throws ActionException
     */
    public void getCardExpire(AIBankUser aiBankUser, Locale userLocale, String cardNo, NCCAG007Output dataOutput) throws ActionException {
        CreditCard creditCard = userDataCacheService.getCreditCard(aiBankUser, cardNo, userLocale);
        dataOutput.setCardExpire(creditCard.getCardExpired());
    }

}
