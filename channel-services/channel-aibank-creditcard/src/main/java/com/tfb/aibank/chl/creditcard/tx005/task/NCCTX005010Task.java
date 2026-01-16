package com.tfb.aibank.chl.creditcard.tx005.task;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.validate.ValidatorUtility;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005010Rq;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005010Rs;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005CacheData;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005CreditCard;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005Output;
import com.tfb.aibank.chl.creditcard.tx005.service.NCCTX005Service;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.type.CreditCardIdType;

// @formatter:off
/**
 * @(#)NCCTX005010Task.java
 * 
 * <p>Description:額度調整 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/04, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCTX005010Task extends AbstractAIBankBaseTask<NCCTX005010Rq, NCCTX005010Rs> {

    @Autowired
    private NCCTX005Service service;
    @Autowired
    private UserDataCacheService userDataCacheService;

    private NCCTX005Output dataOutput = new NCCTX005Output();

    @Override
    public void validate(NCCTX005010Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCTX005010Rq rqData, NCCTX005010Rs rsData) throws ActionException {

        AIBankUser loginUser = getLoginUser();

        Locale userLocale = getLocale();

        // 信用卡戶況與註記
        userDataCacheService.validateCreditCardStatus(loginUser);

        // 登入ID是否為本國人ID
        if (ValidatorUtility.isForeignId(loginUser.getCustId())) {
            // 非本國人
            throw ExceptionUtils.getActionException(ErrorCode.UNABLE_TO_ADJUST_THE_AMOUNT);
        }

        // 檢查客戶是否擁有信用卡，且為正卡持有人
        CreditCardIdType creditCardIdType = userDataCacheService.getCreditCardIdType(loginUser, userLocale);
        if (!creditCardIdType.isPrimaryCard()) {
            throw ExceptionUtils.getActionException(ErrorCode.QUOTA_APPLY_PRIMARY_CARDHOLDER_ONLY);
        }

        // 取得客戶歸戶下之附卡與額度以及客戶工作資訊
        service.sendCEW332R(loginUser, userLocale, dataOutput);
        rsData.setMaskEmail(DataMaskUtil.maskEmail(dataOutput.getWorkInfo().getEmail()));
        List<NCCTX005CreditCard> creditCards = dataOutput.getCreditCards();
        rsData.setSupplementaryCards(creditCards);

        // 「調整項目」下拉選單，根據額度調整項目 : 調高、調降、附卡額度調整，分別發送電文檢核此客戶是否可啟案
        service.getAdjustItems(loginUser, creditCards, userLocale, dataOutput);
        rsData.setAdjustItems(dataOutput.getAdjustItems());

        // 取得信用卡歸戶額度資料
        service.getCreditLimit(loginUser, userLocale, creditCards, dataOutput);
        rsData.setCreditLimit(dataOutput.getCreditLimit());

        // 免財力證明調額白名單
        service.getCustomerCardApply(loginUser.getCustId(), dataOutput);
        if (dataOutput.getCustomerCardApply() != null && dataOutput.getCustomerCardApply().getAdjustAmt() != null) {
            rsData.setCardApplyAdjustAmt(dataOutput.getCustomerCardApply().getAdjustAmt());
        }
        else {
            rsData.setCardApplyAdjustAmt(BigDecimal.ZERO);
        }

        // 額度用途選項
        service.getQuotaUsageList(dataOutput);
        rsData.setQuotaUsageList(dataOutput.getQuotaUsageList());

        NCCTX005CacheData cache = new NCCTX005CacheData();
        cache.setWorkInfo(dataOutput.getWorkInfo());
        cache.setCreditLimit(rsData.getCreditLimit());
        cache.setSupplementaryCards(creditCards);
        cache.setCustomerCardApply(dataOutput.getCustomerCardApply());
        setCache(NCCTX005Service.CACHE_KEY, cache);

        // 除移附卡清單裡未隱碼的敏資
        for (NCCTX005CreditCard card : rsData.getSupplementaryCards()) {
            card.setVnCnam(null);
            card.setVnHdid(null);
        }
    }
}
