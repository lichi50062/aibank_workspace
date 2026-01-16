package com.tfb.aibank.chl.creditcard.ag007.task;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.util.AESUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.ag007.model.NCCAG007010Rq;
import com.tfb.aibank.chl.creditcard.ag007.model.NCCAG007010Rs;
import com.tfb.aibank.chl.creditcard.ag007.model.NCCAG007Output;
import com.tfb.aibank.chl.creditcard.ag007.service.NCCAG007Service;
import com.tfb.aibank.chl.creditcard.ag007.type.NCCAG007BindType;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.code.AIBankConstants;

// @formatter:off
/**
 * @(#)NCCAG007010Task.java
 * 
 * <p>Description:一鍵綁定行動支付 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/08, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCAG007010Task extends AbstractAIBankBaseTask<NCCAG007010Rq, NCCAG007010Rs> {

    @Autowired
    private UserDataCacheService userDataCacheService;
    @Autowired
    private NCCAG007Service service;

    private NCCAG007Output dataOutput = new NCCAG007Output();

    @Override
    public void validate(NCCAG007010Rq rqData) throws ActionException {
        NCCAG007BindType bindType = NCCAG007BindType.findByCode(rqData.getAddType());
        if (bindType.isUnknown()) {
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
    }

    @Override
    public void handle(NCCAG007010Rq rqData, NCCAG007010Rs rsData) throws ActionException {

        AIBankUser loginUser = getLoginUser();

        // 若為信用卡特殊戶、或未持有信用卡，引導至「共通錯誤頁」
        userDataCacheService.validateCreditCardStatus(loginUser);

        NCCAG007BindType bindType = NCCAG007BindType.findByCode(rqData.getAddType());
        if (bindType.isLinePay() || bindType.isUnknown()) {
            service.getSystemParamValue(AIBankConstants.CHANNEL_NAME, "LINEPAY_BIND_URL", dataOutput);
            rsData.setLinePayBindURL(dataOutput.getParamValue());
        }
        else if (StringUtils.isNotBlank(rqData.getCardKey())) {
            CreditCard creditCard = userDataCacheService.getCreditCardByCardKey(getLoginUser(), rqData.getCardKey(), getLocale());

            rsData.setCoefficient(getCoefficient(getRequest().getDeviceIxd(), creditCard.getCardNo()));
        }
    }

    /**
     * 取的加密後的因子
     * 
     * @param deviceId
     * @return
     */
    public String getCoefficient(String deviceId, String cardNo) {
        if (StringUtils.isBlank(deviceId) || deviceId.length() < 16)
            return "";

        String key = deviceId.substring(0, 16);
        byte[] encodeFactor = null;
        try {
            encodeFactor = AESUtils.encryptCoefficient(cardNo.getBytes(), new SecretKeySpec(key.getBytes(), "AES"));
        }
        catch (CryptException e) {
            logger.error("", e);
        }

        return new String(Hex.encode(encodeFactor));

    }

}
