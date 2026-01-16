package com.tfb.aibank.chl.creditcard.tx005.task;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.validate.ValidatorUtility;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.tx005.model.AdjustItemType;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005020Rq;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005020Rs;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005CacheData;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005CreditCard;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005Output;
import com.tfb.aibank.chl.creditcard.tx005.model.QuotaUsageType;
import com.tfb.aibank.chl.creditcard.tx005.service.NCCTX005Service;

// @formatter:off
/**
 * @(#)NCCTX005020Task.java
 * 
 * <p>Description:額度調整 020 申請資料輸入頁</p>
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
public class NCCTX005020Task extends AbstractAIBankBaseTask<NCCTX005020Rq, NCCTX005020Rs> {

    @Autowired
    private NCCTX005Service service;

    private NCCTX005Output dataOutput = new NCCTX005Output();

    @Override
    public void handle(NCCTX005020Rq rqData, NCCTX005020Rs rsData) throws ActionException {

        AdjustItemType adjustItem = rqData.getAdjustItemType();

        NCCTX005CacheData cache = super.getCache(NCCTX005Service.CACHE_KEY, NCCTX005CacheData.class);

        cache.setAdjustItem(adjustItem);
        cache.setAdjustAmt(rqData.getAdjustAmt());

        switch (adjustItem) {
        case TURN_UP:
            cache.setCurrentQuota(cache.getCreditLimit());
            cache.setQuotaUsage(rqData.getQuotaUsageType());
            cache.setOtherUsage(rqData.getOtherUsage());
            cache.setFullInsurAmt(rqData.getFullInsurAmt());
            break;
        case DOWNGRADE:
            cache.setCurrentQuota(cache.getCreditLimit());
            break;
        case ADDITIONAL_CARD_LIMIT:
            NCCTX005CreditCard creditCard = cache.getSupplementaryCards().get(rqData.getSupplementaryCardIdx());
            cache.setSupplementaryCard(creditCard);
            cache.setCurrentQuota(creditCard.getVnCpma());
            break;
        }
        setCache(NCCTX005Service.CACHE_KEY, cache);

        service.getCityAndArea(getLocale(), dataOutput);

        rsData.setAdjustItem(adjustItem);
        rsData.setCities(dataOutput.getCities());
        rsData.setCompany(cache.getWorkInfo().getCompany());
        rsData.setJobTitle(cache.getWorkInfo().getJobTitle());
        rsData.setOfficeTelArea(cache.getWorkInfo().getOfficeTelArea());
        rsData.setOfficeTel(StringUtils.isBlank(cache.getWorkInfo().getOfficeTel()) ? StringUtils.EMPTY : DataMaskUtil.maskMobile(cache.getWorkInfo().getOfficeTel()));
        rsData.setOfficeTelExt(cache.getWorkInfo().getOfficeTelExt());
    }

    @Override
    public void validate(NCCTX005020Rq rqData) throws ActionException {
        if (StringUtils.isBlank(rqData.getAdjustItem())) {
            // 請選擇調整項目
            addErrorFieldMap("adjustItem", I18NUtils.getMessage("ncctx005.010.validate.adjust-item.required"));
        }
        else {
            NCCTX005CacheData cache = getCache(NCCTX005Service.CACHE_KEY, NCCTX005CacheData.class);
            AdjustItemType adjustItemType = rqData.getAdjustItemType();
            if (adjustItemType.isTurnUp()) {
                // 檢核調整額度
                validateAdjustAmt(rqData.getAdjustAmt(), "adjustAmtTurnUp", cache.getCreditLimit());

                if (StringUtils.isBlank(rqData.getQuotaUsage())) {
                    // 請選擇額度用途
                    addErrorFieldMap("quotaUsage", I18NUtils.getMessage("ncctx005.010.validate.quota-usage.required"));
                }
                else {
                    QuotaUsageType quotaUsageType = rqData.getQuotaUsageType();
                    // 其他用途
                    if (quotaUsageType.isOther()) {
                        if (StringUtils.isBlank(rqData.getOtherUsage())) {
                            // 請輸入其他用途
                            addErrorFieldMap("otherUsage", I18NUtils.getMessage("ncctx005.010.validate.other-usage.required"));
                        }
                        else {
                            String otherUsage = rqData.getOtherUsage();
                            if (!ValidatorUtility.checkChinese(otherUsage) && !ValidatorUtility.checkEngAndNumber(otherUsage)) {
                                // 長度限制為20個連續中文或40個連續英數字
                                addErrorFieldMap("otherUsage", I18NUtils.getMessage("ncctx005.010.validate.other-usage.format"));
                            }
                            else if (ValidatorUtility.checkChinese(otherUsage) && otherUsage.length() > 20) {
                                // 長度限制為20個連續中文或40個連續英數字
                                addErrorFieldMap("otherUsage", I18NUtils.getMessage("ncctx005.010.validate.other-usage.format"));
                            }
                            else if (ValidatorUtility.checkEngAndNumber(otherUsage) && otherUsage.length() > 40) {
                                // 長度限制為20個連續中文或40個連續英數字
                                addErrorFieldMap("otherUsage", I18NUtils.getMessage("ncctx005.010.validate.other-usage.format"));
                            }
                            else if (!ValidatorUtility.isValidChar(otherUsage)) {
                                // 不可輸入特殊符號
                                addErrorFieldMap("otherUsage", I18NUtils.getMessage("ncctx005.010.validate.other-usage.special-char"));
                            }
                        }
                    }
                    // 人壽保費扣繳(分期)
                    else if (quotaUsageType.isLifeInsuranceFeeInstallment()) {
                        if (rqData.getFullInsurAmt() == null) {
                            // 請輸入保費全額
                            addErrorFieldMap("fullInsurAmt", I18NUtils.getMessage("ncctx005.010.validate.full-insurance-fee.required"));
                        }
                        else if (!ValidatorUtility.isPositiveInteger(rqData.getFullInsurAmt())) {
                            // 請輸入整數
                            addErrorFieldMap("fullInsurAmt", I18NUtils.getMessage("ncctx005.010.validate.integer.required"));
                        }
                    }
                }
            }
            else if (adjustItemType.isDowngrade()) {
                // 檢核調整額度
                validateAdjustAmt(rqData.getAdjustAmt(), "adjustAmtTurnDown", cache.getCreditLimit());
            }
            else if (adjustItemType.isAdditionalCardLimit()) {
                if (rqData.getSupplementaryCardIdx() == -1) {
                    // 請選擇欲調整的附卡人額度
                    addErrorFieldMap("supplementaryCardIdx", I18NUtils.getMessage("ncctx005.010.validate.supplementary-card-adjusted-amount.required"));
                }
                // 檢核調整額度
                validateAdjustAmt(rqData.getAdjustAmt(), "supplementaryAdjustAmt", cache.getCreditLimit());
            }
        }
    }

    private void validateAdjustAmt(BigDecimal adjustAmt, String formControlName, BigDecimal creditLimit) {
        if (adjustAmt == null) {
            // 請輸入調整金額
            addErrorFieldMap(formControlName, I18NUtils.getMessage("ncctx005.010.validate.adjust-amt.required"));
        }
        else if (!ValidatorUtility.isPositiveInteger(adjustAmt)) {
            // 請輸入整數
            addErrorFieldMap(formControlName, I18NUtils.getMessage("ncctx005.010.validate.integer.required"));
        }
        else {
            if (StringUtils.equals(formControlName, "adjustAmtTurnUp")) {
                if (adjustAmt.compareTo(creditLimit) <= 0) {
                    // 調高後額度需大於目前信用額度
                    addErrorFieldMap(formControlName, I18NUtils.getMessage("ncctx005.010.validate.adjust-amt.turn-up"));
                }
            }
            else if (StringUtils.equals(formControlName, "adjustAmtTurnDown")) {
                if (adjustAmt.compareTo(new BigDecimal("30000")) < 0 || adjustAmt.compareTo(creditLimit) >= 0) {
                    // 調降後額度需大於等於臺幣30,000元及小於您目前信用額度
                    addErrorFieldMap(formControlName, I18NUtils.getMessage("ncctx005.010.validate.adjust-amt.downgrade"));
                }
            }
            else if (StringUtils.equals(formControlName, "supplementaryAdjustAmt")) {
                if (adjustAmt.remainder(new BigDecimal("1000")).compareTo(BigDecimal.ZERO) != 0) {
                    // 調整後附卡人信用額度需以仟元為單位
                    addErrorFieldMap(formControlName, I18NUtils.getMessage("ncctx005.010.validate.adjust-amt.supplementary-card.unit"));
                }
                else if (adjustAmt.compareTo(new BigDecimal("3000")) < 0 || adjustAmt.compareTo(creditLimit) >= 0) {
                    // 調整後附卡人信用額度需大於等於臺幣3,000元及小於正卡人目前信用額度
                    addErrorFieldMap(formControlName, I18NUtils.getMessage("ncctx005.010.validate.adjust-amt.additional-card-limit"));
                }
            }
        }
    }

}
